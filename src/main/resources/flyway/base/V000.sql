CREATE TABLE users
(
    id                  character varying(255)      NOT NULL,
    created_at          timestamp without time zone NOT NULL,
    created_by_id       character varying(255)      NOT NULL,
    updated_at          timestamp without time zone NOT NULL,
    updated_by_id       character varying(255)      NOT NULL,
    version             integer,
    email               character varying(255)      NOT NULL,
    first_name          character varying(255),
    job_title           character varying(255),
    last_login          timestamp without time zone,
    last_name           character varying(255),
    mobile_phone_number character varying(255),
    organization_id     character varying(255)      NOT NULL,
    password            character varying(255)      NOT NULL,
    status              character varying(50)       NOT NULL,
    time_zone           character varying(255)
);

CREATE TABLE role
(
    id                 character varying(255)      NOT NULL,
    created_at         timestamp without time zone NOT NULL,
    created_by_id      character varying(255)      NOT NULL,
    updated_at         timestamp without time zone NOT NULL,
    updated_by_id      character varying(255)      NOT NULL,
    version            integer,
    description        text,
    is_standard        boolean                     NOT NULL,
    name               character varying(255)      NOT NULL,
    organization_id    character varying(255)      NOT NULL,
    required_org_level integer                     NOT NULL
);

CREATE TABLE permission
(
    id                 character varying(255)      NOT NULL,
    created_at         timestamp without time zone NOT NULL,
    created_by_id      character varying(255)      NOT NULL,
    updated_at         timestamp without time zone NOT NULL,
    updated_by_id      character varying(255)      NOT NULL,
    version            integer,
    permission_group   character varying(50)       NOT NULL,
    name               character varying(255)      NOT NULL,
    required_org_level integer                     NOT NULL
);

CREATE TABLE role_permission
(
    role_id       character varying(255) NOT NULL,
    permission_id character varying(255) NOT NULL
);

CREATE TABLE user_role
(
    user_id character varying(255) NOT NULL,
    role_id character varying(255) NOT NULL
);

ALTER TABLE ONLY role
    ADD CONSTRAINT idx_role_name_org UNIQUE (name, organization_id);

ALTER TABLE ONLY permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (id);

ALTER TABLE ONLY role_permission
    ADD CONSTRAINT role_permission_pkey PRIMARY KEY (role_id, permission_id);

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_users_email UNIQUE (email);

ALTER TABLE ONLY user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id);

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY role_permission
    ADD CONSTRAINT fk_rolepermission_permission FOREIGN KEY (permission_id) REFERENCES permission (id);

ALTER TABLE ONLY role_permission
    ADD CONSTRAINT fk_rolepermission_role FOREIGN KEY (role_id) REFERENCES role (id);

ALTER TABLE ONLY user_role
    ADD CONSTRAINT fk_userrole_role FOREIGN KEY (role_id) REFERENCES role (id);

ALTER TABLE ONLY user_role
    ADD CONSTRAINT fk_userrole_user FOREIGN KEY (user_id) REFERENCES users (id);


CREATE TABLE tax_rate
(
    id                character varying(255)      NOT NULL,
    created_at        timestamp without time zone NOT NULL,
    created_by_id     character varying(255)      NOT NULL,
    updated_at        timestamp without time zone NOT NULL,
    updated_by_id     character varying(255)      NOT NULL,
    version           integer,
    tax_rate_group_id character varying(255)      NOT NULL,
    name              character varying(255)      NOT NULL,
    rate              numeric(7, 6)               NOT NULL
);

CREATE TABLE tax_rate_group
(
    id                        character varying(255)      NOT NULL,
    created_at                timestamp without time zone NOT NULL,
    created_by_id             character varying(255)      NOT NULL,
    updated_at                timestamp without time zone NOT NULL,
    updated_by_id             character varying(255)      NOT NULL,
    version                   integer,
    organization_id           character varying(255)      NOT NULL,
    name                      character varying(255)      NOT NULL,
    is_archived               boolean DEFAULT false       NOT NULL,
    is_locked                 boolean DEFAULT false       NOT NULL,
    plugin_provider           character varying(255),
    plugin_provider_object_id character varying(255)
);

ALTER TABLE ONLY tax_rate_group
    ADD CONSTRAINT tax_group_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tax_rate
    ADD CONSTRAINT tax_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tax_rate
    ADD CONSTRAINT fk_taxrate_taxrategroup FOREIGN KEY (tax_rate_group_id) REFERENCES tax_rate_group (id);

-- ALTER TABLE ONLY tax_rate_group
--     ADD CONSTRAINT fk_taxrategroup_organization FOREIGN KEY (organization_id) REFERENCES organization (id);

CREATE TABLE invoice
(
    id                           character varying(255)      NOT NULL,
    created_at                   timestamp without time zone NOT NULL,
    created_by_id                character varying(255)      NOT NULL,
    updated_at                   timestamp without time zone NOT NULL,
    updated_by_id                character varying(255)      NOT NULL,
    version                      integer,
    number                       integer                     NOT NULL,
    billing_contact_id           character varying(255),
    description                  text,
    discount_value               numeric(15, 2) DEFAULT 0    NOT NULL,
    discount_type                character varying(50)       NOT NULL,
    notes                        text,
    currency_code                character varying(5)        NOT NULL,
    job_id                       character varying(255)      NOT NULL,
    organization_id              character varying(255)      NOT NULL,
    customer_note                text,
    additional_email             character varying(50),
    status                       character varying(255)      NOT NULL,
    due_date                     timestamp without time zone,
    sub_total                    numeric(15, 2)              NOT NULL,
    total_tax                    numeric(15, 2)              NOT NULL,
    total_discount               numeric(15, 2)              NOT NULL,
    total                        numeric(15, 2)              NOT NULL,
    plugin_provider              character varying(255),
    plugin_provider_invoice_id   character varying(255),
    date_sent_to_customer        timestamp without time zone,
    date_sent_to_plugin_provider timestamp without time zone
);

CREATE TABLE invoice_line_item
(
    id                character varying(255)      NOT NULL,
    created_at        timestamp without time zone NOT NULL,
    created_by_id     character varying(255)      NOT NULL,
    updated_at        timestamp without time zone NOT NULL,
    updated_by_id     character varying(255)      NOT NULL,
    version           integer,
    number            character varying(255)      NOT NULL,
    item_id           character varying(255)      NOT NULL,
    quantity          integer                     NOT NULL,
    unit_price        numeric(15, 2)              NOT NULL,
    tax_rate_group_id character varying(255)      NOT NULL,
    invoice_id        character varying(255)      NOT NULL
);

ALTER TABLE ONLY invoice_line_item
    ADD CONSTRAINT invoice_line_item_pkey PRIMARY KEY (id);

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_pkey PRIMARY KEY (id);

ALTER TABLE ONLY invoice_line_item
    ADD CONSTRAINT fk_invoicelineitem_invoice FOREIGN KEY (invoice_id) REFERENCES invoice (id);

ALTER TABLE ONLY invoice_line_item
    ADD CONSTRAINT fk_invoicelineitem_taxrategroup FOREIGN KEY (tax_rate_group_id) REFERENCES tax_rate_group (id);
