create table suppliers (
    id character varying(64) not null primary key default uuid_generate_v4(),
    created_at timestamp not null default now(),
    created_by character varying(255) not null,
    status_record character varying(255) not null,
    updated_at timestamp without time zone,
    updated_by character varying(255),
    email character varying(50) not null,
    gender character varying(255),
    mobile_phone character varying(30),
    name character varying(100) not null,
    id_address character varying(64)
);

alter table suppliers
    add constraint suppliers_unique_email unique (email);
alter table suppliers
    add constraint suppliers_unique_mobile_phone unique (mobile_phone);
alter table suppliers
    add constraint fk_supplier_address foreign key (id_address) references address(id);
