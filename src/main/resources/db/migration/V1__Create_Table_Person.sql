create table if not exists person
(
    id bigint auto_increment primary key,
    address    varchar(255) null,
    first_name varchar(80)  not null,
    gender     varchar(255) null,
    last_name  varchar(80)  not null
);
