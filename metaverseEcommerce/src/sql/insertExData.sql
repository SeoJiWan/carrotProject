-- data insert
--member
insert into members
values (members_seq.nextval, '지완', '1234', '010-3880-6637', '범물동');
insert into members
values (members_seq.nextval, '창우', '4321', '010-4732-0557', '범물동');
insert into members
values (members_seq.nextval, '성진', '4321', '010-7322-3211', '호산동');
insert into members
values (members_seq.nextval, '대겸', '3443', '010-1111-2222', '이곡동');

select * from members;


--product
insert into products
values (products_seq.nextval, '에어팟', 1, 100000, '싸게 팔아요');
insert into products
values (products_seq.nextval, '아이폰SE2', 1, 150000, '살 사람');
insert into products
values (products_seq.nextval, '갤럭시S22', 1, 150000, '살 사람');

select * from products;


--sales
insert into sales
values(sales_seq.nextval, 1, 1, '판매중');
insert into sales
values(sales_seq.nextval, 2, 2, '판매중');
insert into sales
values(sales_seq.nextval, 1, 3, '판매중');

select * from sales;


--orders
insert into orders
values(orders_seq.nextval, 3, 1, 1, 10000);
insert into orders
values(orders_seq.nextval, 4, 2, 1, 15000);
insert into orders
values(orders_seq.nextval, 4, 3, 1, 15000);


select * from orders;


--histories
insert into histories
values (histories_seq.nextval, 1);
insert into histories
values (histories_seq.nextval, 2);

select * from histories;






