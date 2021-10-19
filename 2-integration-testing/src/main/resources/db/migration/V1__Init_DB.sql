create table citizen (
    id bigserial not null,
    birth_date date not null,
    birth_fed_place varchar(255) not null,
    birth_place varchar(255) not null,
    name varchar(255) not null,
    patronymic varchar(255) not null,
    surname varchar(255) not null,
    primary key (id)
);

create table foreign_passport (
    serial_number bigserial not null,
    issue_date date not null,
    validity_date date not null,
    citizen_id bigint not null,
    issuing_division_code bigint,
    primary key (serial_number)
);

create table issuing_division (
    code bigserial not null,
    name varchar(255) not null,
    primary key (code)
 );

create table passport (
    serial_number bigserial not null,
    issue_date date not null,
    validity_date date not null,
    citizen_id bigint not null,
    issuing_division_code bigint not null,
    primary key (serial_number)
);

alter table foreign_passport
    add constraint fk_foreign_passport_citizen_id_ref_citizen
        foreign key (citizen_id) references citizen;

alter table foreign_passport
    add constraint fk_foreign_passport_issuing_division_code_ref_issuing_division
        foreign key (issuing_division_code) references issuing_division;

alter table passport
    add constraint fk_passport_citizen_id_ref_citizen
        foreign key (citizen_id) references citizen;

alter table passport
    add constraint fk_passport_issuing_division_code_ref_issuing_division
        foreign key (issuing_division_code) references issuing_division;