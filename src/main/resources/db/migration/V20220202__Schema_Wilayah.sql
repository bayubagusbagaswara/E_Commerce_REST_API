-- PROVINSI
create table provinsi (
    id character varying(64) not null primary key default uuid_generate_v4(),
    created_date timestamp not null default now(),
    created_by character varying(255) not null,
    status_record character varying(255) not null,
    updated_date timestamp without time zone,
    updated_by character varying(255),
    code character varying(255) not null,
    name character varying(255) not null,
    constraint provinsi_unique_code unique (code)
);

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
    id_provinsi character varying(64),
    constraint kota_unique_code unique (code),
    constraint fk_kota_provinsi foreign key (id_provinsi) references provinsi(id) on update cascade on delete cascade
);

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
    id_kota character varying(64),
    constraint kecamatan_unique_code unique (code),
    constraint fk_kecamatan_kota foreign key (id_kota) references kota(id) on update cascade on delete cascade
);

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
    id_kecamatan character varying(64),
    constraint kelurahan_unique_code unique (code),
    constraint fk_kelurahan_kecamatan foreign key (id_kecamatan) references kecamatan(id) on update cascade on delete cascade
);