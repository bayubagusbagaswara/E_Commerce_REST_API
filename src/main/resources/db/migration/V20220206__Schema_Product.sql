create table products (
    id character varying(64) not null primary key default uuid_generate_v4(),
    created_date timestamp not null default now(),
    created_by character varying(255) not null,
    status_record character varying(255) not null,
    updated_date timestamp without time zone,
    updated_by character varying(255),
    name character varying(100) not null,
    price numeric(19,2) not null default 0,
    quantity integer not null default 0,
    id_category character varying(64),
    id_product_detail character varying(64),
    constraint ck_price_default check (price >= 1),
    constraint ck_quantity_default check (quantity >= 0),
    constraint fk_product_category foreign key (id_category) references categories(id),
    constraint fk_product_product_detail foreign key (id_product_detail) references product_detail(id)
);