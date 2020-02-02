drop database NackademiBankenDB;
create database if not exists NackademiBankenDB;
use NackademiBankenDB;
create table if not exists Customer
(
    id int not null auto_increment primary key,
    firstName varchar(50) not null ,
    lastName varchar(50) not null,
    personalNr varchar(50) not null ,
    pinCode varchar(50) not null
);

insert into Customer
values
(
null,
'Jacob',
'Swenson',
'890206',
'1234'
);
create table if not exists Admin
(
    id int not null auto_increment primary key,
    username varchar(50) not null ,
    password varchar(50) not null
);
create table if not exists Interest
(
    id int not null auto_increment primary key,
    interestType varchar(50) not null,
    interestRate double not null
);
insert into Interest
values
(
null,
'Loan',
2.2
),
(
null,
'Account',
0.1
);
create table if not exists Account
(
    id int not null auto_increment primary key,
    accountName varchar(50) not null,
    customerId int not null ,
    balance double,
    interestId int not null,
    foreign key (customerId) references Customer(id),
    foreign key (interestId) references Interest(id)
);
insert into Account
values
(
null,
'12345',
1,
4200,
2
),
(
null,
'1234567',
1,
12000,
2
);
create table if not exists AccountHistory
(
    id int not null auto_increment primary key,
    accountId int not null,
    action enum('Uttag','Insättning'),
    amount double not null,
    balanceAfterAction double not null ,
    date timestamp not null,
    foreign key (accountId) references Account(id)
);
insert into AccountHistory
values
(
null,
1,
1,
1200,
4500,
now()
),
(
null,
1,
1,
200,
4200,
now()
);

create table if not exists Loan
(
    id int not null auto_increment primary key,
    customerId int not null ,
    Amount double not null ,
    interestId int not null ,
    loanNumber varchar(50) not null,
    loanTime int not null ,
    foreign key (customerId) references Customer(id),
    foreign key (interestId) references Interest(id)
);
insert into Loan
values
(
null,
1,
50000,
1,
'9122 000 221',
120
),
(
null,
1,
1000000,
1,
'9122 0334 221',
480
);