-- drop database NackademiBankenDB;
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
    balance double default 0,
    interestId int default 2,
    foreign key (customerId) references Customer(id) on delete cascade,
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
    foreign key (accountId) references Account(id) on delete cascade
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
    interestId int default 1,
    loanNumber varchar(50) not null,
    loanTime int not null ,
    foreign key (customerId) references Customer(id) on delete cascade,
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


-- drop procedure updateBankAccount;
DELIMITER //
create procedure updateBankAccount(IN SPAccountId int, SPAmount int)
begin
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
        begin
            rollback ;
            SELECT 'SQL Exception, Rollback done';
            END;
    start transaction ;
    if exists (select * from Account where Id = SPAccountId)  then
        update Account set balance = (balance + SPAmount)
        where id = SPAccountId;
    commit ;
    end if;
end //
DELIMITER ;

-- call updateBankAccount(1,-100);

-- drop procedure createLoan;
DELIMITER //
create procedure createLoan(IN SPCustomerID int, SPAmount int, SPaccountId int, SPloanNumber varchar(50), SPLoanTime int)
begin
--    DECLARE EXIT HANDLER FOR SQLEXCEPTION
--         begin
-- 			rollback ;
--             SELECT 'SQL Exception, Rollback done';
--             END;
    start transaction ;
    insert into Loan(customerId, Amount, loanNumber, loanTime) values
    (SPCustomerID,SPAmount,SPloanNumber,SPLoanTime);
    update Account set balance = balance + SPAmount where id = SPaccountId;
commit ;
end //
DELIMITER ;



 call createLoan(1,10000,1,'234 456 234',24);

-- drop procedure withdrawFromAccount;
DELIMITER //
create procedure withdrawFromAccount(IN SPAccountId int,SPAmount int )
begin
if(select count(*) from Account where Account.id = SPAccountId ) = 1 then
    update Account set balance = balance - SPAmount where id = SPaccountID;
end if ;
end //
DELIMITER ;


-- call withdrawFromAccount(1,300);
-- set sql_safe_updates = 0;

-- drop trigger accHistoryTrigger;
DELIMITER //
create trigger accHistoryTrigger
    after update on Account
    for each row
    begin
    if old.balance > new.balance then
        insert into AccountHistory(accountId,action,amount,balanceAfterAction,date) 
        values(old.id,'Uttag',(new.balance - old.balance), new.balance, current_timestamp());
        else
        insert into AccountHistory(accountId,action,amount,balanceAfterAction,date) 
        values(old.id,'Insättning',(new.balance - old.balance), new.balance, current_timestamp());
        end if;
    end //
    DELIMITER ;


DELIMITER //
create procedure addCustomer(IN InfirstName varchar(50), InlastName varchar(50),InpersonalNr varchar(50),InpinCode varchar(50))
begin
DECLARE EXIT HANDLER FOR sqlexception
begin
rollback;
SELECT 'SQL EXCEPTIONEN ERROR' as errormessage;
END;
start transaction;
if exists (select * from Customer where personalNr = InpersonalNr) then select 'Kund finns redan i databasen' as errormessage;
else insert into Customer(firstName,lastName,personalNr,pinCode) values (InfirstName,InlastName,InpersonalNr,InpinCode);
commit;
end if;
end //
DELIMITER ;

DELIMITER //
create procedure updateCustomer(IN customerId int, InfirstName varchar(50), InlastName varchar(50),InpersonalNr varchar(50),InpinCode varchar(50))
begin
DECLARE EXIT HANDLER FOR sqlexception
begin
rollback;
SELECT 'SQL EXCEPTIONEN ERROR' as errormessage;
END;
start transaction;
update Customer
set firstName = InfirstName , lastName = Inlastname , personalNr = InpersonalNr , pinCode = InpinCode
where id = customerId;
commit;
end //
DELIMITER ;

-- drop procedure changeRateForAccount;
DELIMITER //
create procedure changeRateForAccount(IN InaccountId int, IninterestRate double)
begin
-- begin
-- DECLARE EXIT HANDLER FOR sqlexception
-- rollback;
-- select 'SQLEXCEPTION' as errormessage;
-- END;
start transaction;
if exists (select * from Interest where interestType like 'Account' and interestRate = IninterestRate ) then 
 update Account set interestId =  (select id from Interest where interestType like 'Account' and interestRate = IninterestRate) where id = InaccountId;
 else  
 insert into Interest(interestType,interestRate) values ('Account',IninterestRate);
 update Account set interestId =  last_insert_id() where id = Inaccountid;
end if;
 commit;
end //
DELIMITER ;

-- call changeRateForAccount(1,0.35);
 
--  drop procedure changeRateForLoan;
DELIMITER //
create procedure changeRateForLoan(IN InloanId int, IninterestRate double)
begin
-- begin
-- DECLARE EXIT HANDLER FOR sqlexception
 -- rollback;
-- select 'SQLEXCEPTION' as errormessage;
-- END;
 start transaction;
if exists (select * from Interest where interestType like 'Loan' and interestRate = IninterestRate ) then 
 update Loan set interestId =  (select id from Interest where interestType like 'Loan' and interestRate = IninterestRate) where Id = InloanId;
 else  
 insert into Interest(interestType,interestRate) values ('Loan',IninterestRate);
 update Loan set interestId =  last_insert_id() where Id = InloanId;
end if;
 commit;
end //
DELIMITER ;

-- call changeRateForLoan(1,2.2);

-- drop procedure deleteCustomer;
DELIMITER //
create procedure deleteCustomer(IN INcustomerId int)
begin
-- begin
-- DECLARE EXIT HANDLER FOR SQLEXCEPTION
-- rollback;
-- SELECT 'SQL EXCEPTION' as errormessage;
-- END;
start transaction;
if exists(select * from Customer where id = IncustomerID) then 
delete from Customer where id = INcustomerId; 
end if;
commit;
end//
DELIMITER ;

-- call deleteCustomer(3);
