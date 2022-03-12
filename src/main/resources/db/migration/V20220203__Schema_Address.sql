create table address (
    id character varying(64) not null primary key default uuid_generate_v4(),
    created_date timestamp not null default now(),
    created_by character varying(255) not null,
    status_record character varying(255) not null,
    updated_date timestamp without time zone,
    updated_by character varying(255),
    street character varying(100),
    postal_code character varying(255) not null,
    id_kelurahan character varying(64)
);

alter table address
    add constraint kelurahan_unique_postal_code unique (postal_code);
alter table address
    add constraint fk_address_kelurahan foreign key (id_kelurahan) references kelurahan(id);