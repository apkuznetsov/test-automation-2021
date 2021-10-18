CREATE TABLE IF NOT EXISTS issuing_division
(
    code serial primary key,
    name text NOT NULL
);

CREATE TABLE IF NOT EXISTS citizen
(
    id serial primary key,
    surname text NOT NULL,
    name text NOT NULL,
    patronymic text NOT NULL,
    birth_date date NOT NULL,
    birth_fed_place text NOT NULL,
    birth_place text NOT NULL
);

CREATE TABLE IF NOT EXISTS passport
(
    serial_number serial primary key,
    issuing_division_code serial NOT NULL,
    issue_date date NOT NULL,
    validity_date date NOT NULL,
    citizen_id serial NOT NULL,
    CONSTRAINT passport_fk_issuing_division
        FOREIGN KEY(issuing_division_code)
        REFERENCES issuing_division(code),
    CONSTRAINT passport_fk_citizen
        FOREIGN KEY(citizen_id)
            REFERENCES citizen(id)
);

CREATE TABLE IF NOT EXISTS foreign_passport
(
    serial_number serial primary key,
    issuing_division_code serial NOT NULL,
    issue_date date NOT NULL,
    validity_date date NOT NULL,
    citizen_id serial NOT NULL,
    CONSTRAINT foreign_passport_fk_issuing_division
        FOREIGN KEY(issuing_division_code)
            REFERENCES issuing_division(code),
    CONSTRAINT foreign_passport_fk_citizen
        FOREIGN KEY(citizen_id)
            REFERENCES citizen(id)
);
