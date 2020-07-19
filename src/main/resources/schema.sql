create table if not exists book(
    id int primary key not null,
    title varchar(255) not null,
    author varchar(255) not null,
    link varchar(255)
);
