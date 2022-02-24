create table product_supplier (
    id_product character varying(64) not null,
    id_supplier character varying(64) not null
);

alter table product_supplier
    add constraint product_supplier_pkey primary key (id_product, id_supplier);
alter table product_supplier
    add constraint fk_product foreign key (id_product) references products(id);
alter table product_supplier
    add constraint fk_supplier foreign key (id_supplier) references suppliers(id);
