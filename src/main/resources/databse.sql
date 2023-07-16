create table USER_ACCOUNT
(
USER_NAME VARCHAR(30) not null,
PASSWORD  VARCHAR(30) not null,
primary key (USER_NAME)
);


-- Insert data: ---------------------------------------------------------------

insert into user_account (USER_NAME, PASSWORD)
values ('tom',  'tom001');

insert into user_account (USER_NAME, PASSWORD)
values ('jerry', 'jerry001');