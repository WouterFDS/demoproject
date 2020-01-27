create table opdracht
(
    id             integer generated always as identity
        constraint opdracht_pkey
        primary key,
    naam           varchar,
    belangrijkheid integer
);

alter table opdracht
    owner to postgres;
