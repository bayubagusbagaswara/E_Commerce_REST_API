-- PROVINSI
create table provinsi (
    id character varying(64) not null primary key default uuid_generate_v4(),
    created_date timestamp not null default now(),
    created_by character varying(255) not null,
    status_record character varying(255) not null,
    updated_date timestamp without time zone,
    updated_by character varying(255),
    code character varying(255) not null,
    name character varying(255) not null
);

alter table provinsi
    add constraint provinsi_unique_code unique (code);

-- KOTA
create table kota (
    id character varying(64) not null primary key default uuid_generate_v4(),
    created_date timestamp not null default now(),
    created_by character varying(255) not null,
    status_record character varying(255) not null,
    updated_date timestamp without time zone,
    updated_by character varying(255),
    code character varying(255) not null,
    name character varying(255) not null,
    id_provinsi character varying(64)
);

alter table kota
    add constraint kota_unique_code unique (code);
alter table kota
    add constraint fk_kota_provinsi foreign key (id_provinsi) references provinsi(id);

-- KECAMATAN
create table kecamatan (
    id character varying(64) not null primary key default uuid_generate_v4(),
    created_date timestamp not null default now(),
    created_by character varying(255) not null,
    status_record character varying(255) not null,
    updated_date timestamp without time zone,
    updated_by character varying(255),
    code character varying(255) not null,
    name character varying(255) not null,
    id_kota character varying(64)
);

alter table kecamatan
    add constraint kecamatan_unique_code unique (code);
alter table kecamatan
    add constraint fk_kecamatan_kota foreign key (id_kota) references kota(id);

-- KELURAHAN
create table kelurahan (
    id character varying(64) not null primary key default uuid_generate_v4(),
    created_date timestamp not null default now(),
    created_by character varying(255) not null,
    status_record character varying(255) not null,
    updated_date timestamp without time zone,
    updated_by character varying(255),
    code character varying(255) not null,
    name character varying(255) not null,
    id_kecamatan character varying(64)
);

alter table kelurahan
    add constraint kelurahan_unique_code unique (code);
alter table kelurahan
    add constraint fk_kelurahan_kecamatan foreign key (id_kecamatan) references kecamatan(id);
