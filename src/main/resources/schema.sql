drop Table USER if exists;

create table USER(Id serial, name varchar(7),dept varchar(10) NOT NULL, Salary bigint);