create schema scheduler;


CREATE USER app_user WITH PASSWORD 'test-!132';

create table provider
(
    record_id      serial
        primary key,
    first_name     varchar(50)  not null,
    last_name      varchar(50)  not null,
    specialization varchar(50)  not null,
    email          varchar(100) not null,
    phone_number   varchar(25)  not null,
    address        varchar(100) not null,
    city           varchar(50)  not null,
    state          varchar(30)  not null,
    zip            varchar(9)   not null,
    country        varchar(3)   not null,
    created_date   timestamp,
    modified_date  timestamp,
    created_by     integer,
    modified_by    integer
);

alter table provider
    owner to postgres;

grant select, usage on sequence provider_record_id_seq to app_user;

create index idx_provider_first_name
    on provider (first_name);

create index idx_provider_last_name
    on provider (last_name);

create index idx_provider_zip
    on provider (zip);

grant delete, insert, references, select, trigger, truncate, update on provider to app_user;

create table provider_availability
(
    record_id     serial
        primary key,
    provider_id   integer              not null
        constraint fk_provider_schedule_request
            references provider,
    day_of_year   integer              not null,
    year          integer              not null,
    start_time    time                 not null,
    end_time      time                 not null,
    time_zone     varchar(3)           not null,
    is_active     boolean default true not null,
    created_date  timestamp,
    modified_date timestamp,
    created_by    integer,
    modified_by   integer
);

alter table provider_availability
    owner to postgres;

grant select, usage on sequence provider_availability_record_id_seq to app_user;

grant delete, insert, references, select, trigger, truncate, update on provider_availability to app_user;

create table client
(
    record_id     serial
        primary key,
    first_name    varchar(50)  not null,
    last_name     varchar(50)  not null,
    email         varchar(100) not null,
    phone_number  varchar(25)  not null,
    address       varchar(100) not null,
    city          varchar(50)  not null,
    state         varchar(30)  not null,
    zip           varchar(9)   not null,
    country       varchar(3)   not null,
    created_date  timestamp,
    modified_date timestamp,
    created_by    integer,
    modified_by   integer
);

alter table client
    owner to postgres;

grant select, usage on sequence client_record_id_seq to app_user;

grant delete, insert, references, select, trigger, truncate, update on client to app_user;

create table provider_appointments
(
    record_id                serial
        primary key,
    provider_id              integer not null
        constraint fk_appointments_provider
            references provider,
    client_id                integer not null
        constraint fk_appointments_client
            references client,
    provider_availability_id integer not null
        constraint fk_appointments_provider_availability
            references provider_availability,
    confirmation_id          uuid    not null
        unique,
    is_confirmed             boolean,
    created_date             timestamp,
    modified_date            timestamp,
    created_by               integer,
    modified_by              integer
);

alter table provider_appointments
    owner to postgres;

grant select, usage on sequence provider_appointments_record_id_seq to app_user;

grant delete, insert, references, select, trigger, truncate, update on provider_appointments to app_user;

