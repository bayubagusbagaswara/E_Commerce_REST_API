create table products (
    id character varying(64) not null primary key default uuid_generate_v4(),
    created_at timestamp not null default now(),
    created_by character varying(255) not null,
    status_record character varying(255) not null,
    updated_at timestamp without time zone,
    updated_by character varying(255),
    name character varying(100) not null,
    price numeric(19,2) not null,
    quantity integer not null,
    id_category character varying(64),
    id_product_detail character varying(64)
);

alter table products
    add constraint fk_product_category foreign key (id_category) references categories(id);
alter table products
    add constraint fk_product_product_detail foreign key (id_product_detail) references product_detail(id);