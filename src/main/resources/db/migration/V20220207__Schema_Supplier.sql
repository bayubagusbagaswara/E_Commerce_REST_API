create table suppliers (
    id character varying(64) not null primary key default uuid_generate_v4(),
    created_date timestamp not null default now(),
    created_by character varying(255) not null,
    status_record character varying(255) not null,
    updated_date timestamp without time zone,
    updated_by character varying(255),
    email character varying(50) not null,
    gender character varying(255),
    mobile_phone character varying(30),
    name character varying(100) not null,
    id_address character varying(64),
    constraint ck_gender_supplier check (gender in ('NONE', 'MALE', 'FEMALE')),
    constraint suppliers_unique_email unique (email),
    constraint suppliers_unique_mobile_phone unique (mobile_phone),
    constraint fk_supplier_address foreign key (id_address) references supplierAddress(id)
);
