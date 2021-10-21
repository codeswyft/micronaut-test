package com.codeswyft.fielder.graphql

import com.codeswyft.fielder.domain.PermissionPO
import com.codeswyft.fielder.domain.RolePO
import com.codeswyft.fielder.domain.UserPO
import com.codeswyft.fielder.model.AuthenticationContext
import com.codeswyft.fielder.model.Role
import com.codeswyft.fielder.repositories.PermissionRepository
import com.codeswyft.fielder.repositories.RoleRepository
import com.codeswyft.fielder.repositories.UserRepository
import com.codeswyft.fielder.util.dataseed.DefaultPermissions
import com.fasterxml.jackson.databind.ObjectMapper
//import graphql.ExecutionInput
import groovy.util.logging.Slf4j
import io.micronaut.context.ApplicationContext
import io.micronaut.core.util.CollectionUtils
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.sql.DataSource
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement
import java.text.SimpleDateFormat
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Slf4j
@SuppressWarnings("GrMethodMayBeStatic")
class BaseFunctionalSpec extends Specification {

    static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneOffset.UTC)

    static ObjectMapper objectMapper

    static PermissionRepository permissionRepository
    static RoleRepository roleRepository
    static UserRepository userRepository

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, getTestProperties())

    static String url
    static String username = 'goliath'
    static String password = 'Password1'

    @Shared
    ApplicationContext context = embeddedServer.getApplicationContext()

    protected Map<String, Object> getTestProperties() {
        return CollectionUtils.mapOf(
            "datasources.default.url", "jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
            "datasources.default.username", "sa",
            "datasources.default.password", "''",
            "datasources.default.driverClassName", "org.h2.Driver",
            "jpa.default.properties.hibernate.hbm2ddl.auto", "create-drop",
            "jpa.default.properties.hibernate.show_sql", false,
            "flyway.datasources.default.enabled", false,
            "data.seeder.scramble-dates", false,
            "pdf-generator.enabled", false
        )
    }

    def setupSpec() {
        log.warn("Running setupSpec...")

        objectMapper = context.getBean(ObjectMapper)

        permissionRepository = context.getBean(PermissionRepository)
        roleRepository = context.getBean(RoleRepository)
        userRepository = context.getBean(UserRepository)
    }

    def cleanupSpec() {
        dropTables(context, false)
        if (context?.isRunning()) {
            context.close()
        }
        if (embeddedServer?.isRunning()) {
            embeddedServer.stop()
        }
    }

    static void dropTables(ApplicationContext context, boolean useTestContainers) {
        dropTablesH2(context)
    }

    static dropTablesH2(ApplicationContext context) {
        DataSource dataSource = context.getBean(DataSource)

        if (!dataSource) {
            return
        }

        Connection c = dataSource.getConnection()
        Statement s = c.createStatement()

        // Disable FK
        s.execute("SET REFERENTIAL_INTEGRITY FALSE")

        // Find all tables and truncate them
        Set<String> tables = new HashSet<String>()
        ResultSet rs = s.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES  where TABLE_SCHEMA='PUBLIC'")
        while (rs.next()) {
            tables.add(rs.getString(1))
        }
        rs.close()
        for (String table : tables) {
            s.executeUpdate("TRUNCATE TABLE " + table)
        }

        // Idem for sequences
        Set<String> sequences = new HashSet<String>()
        rs = s.executeQuery("SELECT SEQUENCE_NAME FROM INFORMATION_SCHEMA.SEQUENCES WHERE SEQUENCE_SCHEMA='PUBLIC'")
        while (rs.next()) {
            sequences.add(rs.getString(1))
        }
        rs.close()
        for (String seq : sequences) {
            s.executeUpdate("ALTER SEQUENCE " + seq + " RESTART WITH 1")
        }

        // Enable FK
        s.execute("SET REFERENTIAL_INTEGRITY TRUE")
        s.close()
        c.close()
    }

    protected static void compareDates(String actual, String expected) {
        String[] actualTokens = actual.tokenize('T')
        String actual_DatePart = actualTokens[0]
        String actual_TimePart = actualTokens[1]?.replaceAll("Z?\$", "")

        String[] expectedTokens = expected.tokenize('T')
        String expected_DatePart = expectedTokens[0]
        String expected_TimePart = expectedTokens[1]?.replaceAll("Z?\$", "")

        assert actual_DatePart == expected_DatePart
        SimpleDateFormat sdf = new SimpleDateFormat('H:mm:ss')
        Date actualTimeParsed = sdf.parse(actual_TimePart)
        Date expectedTimeParsed = sdf.parse(expected_TimePart)
        int result = expectedTimeParsed.compareTo(actualTimeParsed)
        assert result == 0
    }

    protected AuthenticationContext buildAuthContext(UserPO user) {
        AuthenticationContext authenticationContext = new AuthenticationContext()
        authenticationContext.organizationId = user.organizationId
        authenticationContext.userId = user.id

//        OrganizationPO organizationPO = organizationRepository.findById(user.organizationId).get()
//        authenticationContext.organizationLevel = organizationPO.level
//        authenticationContext.parentOrganizationId = organizationPO.parentOrganizationId

        List<RolePO> userRolePOs = roleRepository.findAllByUserId(user.id)
        List<PermissionPO> allUserPermissions = new ArrayList<PermissionPO>()
        List<Role> userRoles = userRolePOs.collect { rolePO ->
            Role role = objectMapper.convertValue(rolePO, Role)
            List<PermissionPO> rolePermissionPOs = permissionRepository.findAllByRoleId(rolePO.id)
            allUserPermissions.addAll(rolePermissionPOs)
            role.permissionIds = rolePermissionPOs*.id
            return role
        }

        authenticationContext.roles = userRoles
        authenticationContext.allowedOperations = allUserPermissions*.name.sort()
//        authenticationContext.organizationStatus = organizationPO.status
//        authenticationContext.isOrganizationOwner = (user.id == organizationPO.ownerUserId)

        return authenticationContext
    }

    protected AuthenticationContext buildAnonymousAuthContext() {
        AuthenticationContext authenticationContext = new AuthenticationContext()
        RolePO ANONYMOUS = new RolePO(
            id: 'ANONYMOUS',
            name: 'ANONYMOUS',
            description: "Any unauthenticated user",
            isStandard: true,
            permissions: DefaultPermissions.ALL_MISC_PERMISSIONS as Set<PermissionPO>
        )
        authenticationContext.roles = [objectMapper.convertValue(ANONYMOUS, Role)]
        authenticationContext.allowedOperations = ANONYMOUS.permissions.collect { it.name }.unique().sort()

        return authenticationContext
    }

    protected Boolean verifyAddress(actual, expected) {
        assert actual.addressString == expected.addressString
        assert actual.latitude.round(7) == expected.latitude.round(7)
        assert actual.longitude.round(7) == expected.longitude.round(7)
        assert actual.streetNumber == expected.streetNumber
        assert actual.route == expected.route
        assert actual.locality == expected.locality
        assert actual.administrativeAreaLevel1 == expected.administrativeAreaLevel1
        assert actual.administrativeAreaLevel2 == expected.administrativeAreaLevel2
        assert actual.postalCode == expected.postalCode
        assert actual.country == expected.country
        return true
    }

}
