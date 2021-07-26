**Copy and Paste the following MYSQL commands to make a dummy database for this Project :**

create database test;
use test;
create table product(id varchar(10) primary key, prodname varchar(20), price int);
insert into product values('P001’, ‘Cake’, 5);
insert into product values('P002’, ‘Juice’, 4);
insert into product values('P003’, ‘Chocos’, 6);
insert into product values('P004’, ‘Burger’, 10);
insert into product values('P005’, ‘Banana’, 3);
insert into product values('P006’, ‘Tomato’, 3);

commit;
