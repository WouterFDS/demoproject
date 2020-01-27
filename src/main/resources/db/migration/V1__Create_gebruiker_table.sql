create table gebruiker
(
    naam       text    not null
        constraint gebruiker_pkey
            primary key,
    wachtwoord text    not null,
    is_admin   boolean not null
);

alter table gebruiker
    owner to postgres;

