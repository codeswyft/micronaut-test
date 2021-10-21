package com.codeswyft.fielder.repositories

import com.codeswyft.fielder.domain.BasePO
import com.codeswyft.fielder.modules.Violation
import com.codeswyft.fielder.util.ValidationUtil
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import groovy.util.logging.Slf4j
import io.micronaut.transaction.annotation.ReadOnly
import io.micronaut.transaction.annotation.TransactionalAdvice

import javax.inject.Inject
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.Query
import javax.persistence.TypedQuery
import javax.transaction.Transactional
import javax.validation.ConstraintViolationException
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import java.lang.reflect.Constructor
import java.time.LocalDateTime

@Slf4j
@Singleton
@CompileStatic
@TransactionalAdvice
abstract class BaseRepositoryImpl<T extends BasePO> implements BaseRepository<T> {

    @Inject
    ObjectMapper objectMapper

    protected final EntityManager entityManager

    private Class<T> clazz

    BaseRepositoryImpl(EntityManager entityManager, Class<T> clazz) {
        this.entityManager = entityManager
        this.clazz = clazz
    }

    @Override
    @Transactional
    T create(@NotNull T entity) {
        try {
            // always update the createdAt, updatedAt, and updatedById here!
            entity.createdAt = LocalDateTime.now()
            entity.updatedAt = entity.createdAt
            entity.updatedById = entity.createdById
            entityManager.persist(entity)
            entityManager.flush()
        } catch (ConstraintViolationException cve) {
            List<String> violations = []
            cve.constraintViolations.each {
                String className = it.rootBeanClass.simpleName // i.e., CustomerPO
                String message = it.message // must not be null
                String fieldName = it.propertyPath.first().name // i.e., createdById
                violations.add("${className}: ${fieldName} ${message}" as String)
                log.error(it.rootBean.toString())
            }
            log.error("constraint violations! ${violations.toString()}")
            throw cve
        }
        return entity
    }

    @Override
    @Transactional
    T update(@NotNull T entity) {
        // always update the updatedAt here!
        entity.updatedAt = LocalDateTime.now()
        entityManager.merge(entity)
        entityManager.flush()
        return entity
    }

    @Override
    @Transactional
    T deleteById(@NotBlank String id) {
        T obj = findById(id).orElseThrow {
            ValidationUtil.createValidationError('invalid id attempted to be deleted', Violation.INVALID_ID)
        }
        entityManager.remove(obj)
        entityManager.flush()
        return obj
    }

    @Override
    @ReadOnly
    Optional<T> findById(@NotBlank String id) {
        if (id != null && id != '') {
            log.trace "find ${clazz.simpleName} by id: ${id}"
            return Optional.ofNullable(entityManager.find(clazz, id))
        } else {
            log.warn('findById({}) was attempted (null or empty string)', id)
            return Optional.empty()
        }
    }

    @Override
    @ReadOnly
    List<T> findByIds(Collection<String> ids) {
        List<T> found = []
        if (ids) {
            log.trace "find ${clazz.simpleName} by ids: ${ids}"
            String queryStr = """
                SELECT t FROM ${clazz.simpleName} as t
                WHERE t.id in :ids
                ORDER BY t.createdAt
            """
            TypedQuery<T> query = entityManager.createQuery(queryStr, clazz)
            query.setParameter('ids', ids)
            found = query.getResultList()
        }
        return found
    }

    // The following static methods are here to help with converting results from native queries. I got it from:
    // https://stackoverflow.com/questions/13012584/jpa-how-to-convert-a-native-query-result-set-to-pojo-class-collection

    @CompileStatic(TypeCheckingMode.SKIP)
    static <R> R map(Class<R> type, Object[] tuple) {
        List<Class<?>> tupleTypes = new ArrayList<>()
        for (Object field : tuple) {
            tupleTypes.add(field.getClass())
        }
        try {
            // DO NOT CHANGE THIS LINE TO CAST THE ARGUMENT TO toArray (i.e., do not add `as Class<?>` because it breaks things)
            Constructor<R> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]))
            return ctor.newInstance(tuple)
        } catch (Exception e) {
            throw new RuntimeException(e)
        }
    }

    static <R> List<R> map(Class<R> type, List<Object[]> records) {
        List<R> result = new LinkedList<>()
        for (Object[] record : records) {
            result.add(map(type, record))
        }
        return result
    }

    static <R> List<R> getResultList(Query query, Class<R> type) {
        @SuppressWarnings("unchecked")
        List<Object[]> records = query.getResultList()
        return map(type, records)
    }

    @Override
    @Transactional
    //==========================================================================================
    // some tests need the ability to persist specific createdAt/updatedAt/updatedById values
    //==========================================================================================
    T createBackdoor(@NotNull T entity) {
        try {
            entityManager.persist(entity)
            entityManager.flush()
        } catch (ConstraintViolationException cve) {
            List<String> violations = []
            cve.constraintViolations.each {
                String className = it.rootBeanClass.simpleName // CustomerPO
                String message = it.message // must not be null
                String fieldName = it.propertyPath.first().name // createdById
                violations.add("${className}: ${fieldName} ${message}" as String)
                log.error(it.rootBean.toString())
            }
            log.error("constraint violations! ${violations.toString()}")
            throw cve
        }
        return entity
    }
}
