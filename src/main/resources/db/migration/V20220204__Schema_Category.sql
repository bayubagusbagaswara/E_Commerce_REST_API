create table categories (
    id character varying(64) not null primary key default uuid_generate_v4(),
    created_date timestamp not null default now(),
    created_by character varying(255) not null,
    status_record character varying(255) not null,
    updated_date timestamp without time zone,
    updated_by character varying(255),
    description character varying(500),
    name character varying(50)
);

