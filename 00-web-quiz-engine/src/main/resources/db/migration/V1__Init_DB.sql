create table IF NOT EXISTS quiz_user (
    id bigserial not null,
    email varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
);

create table IF NOT EXISTS quiz (
    id bigserial not null,
    title varchar(255) not null,
    question varchar(255) not null,
    answer varchar(255) not null,
    quiz_user_id bigint,
    primary key (id)
);

create table IF NOT EXISTS quiz_completed (
    id bigserial not null,
    completed_at timestamp not null,
    quiz_id bigint not null,
    quiz_user_id bigint not null,
    is_right boolean not null,
    primary key (id)
);

alter table quiz_user
    add constraint uk_quiz_user_email unique (email);

alter table quiz
    add constraint fk_quiz_user_id
        foreign key (quiz_user_id) references quiz_user;

alter table quiz_completed
    add constraint fk_quiz_completed_quiz_id
        foreign key (quiz_id) references quiz;

alter table quiz_completed
    add constraint fk_quiz_completed_quiz_user_id
        foreign key (quiz_user_id) references quiz_user;