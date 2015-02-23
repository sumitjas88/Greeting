create table PERSON (
    name varchar,
    phone_num varchar,
    address varchar,
    PRIMARY KEY (name),
    FOREIGN KEY (address)
);