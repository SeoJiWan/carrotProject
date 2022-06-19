-- member tbl
create table members (member_id number(10) primary key,
                      identification varchar2(100) constraint member_id_uq unique
                                                   constraint member_id_nn not null,
                      password varchar2(100) constraint member_pwd not null,
                      phone_number varchar(13),
                      address varchar2(1000) constraint member_add not null);
                                                           
create sequence members_seq
                start with 1
                increment by 1
                nocache
                nocycle;
                              
                
desc members;


-- product tbl
create table products (product_id number(10) primary key,
                       name varchar2(100) constraint pro_name_nn not null,
                       quantity number(5) constraint pro_qu_ch check (quantity > 0),
                       price number(9) constraint pro_pr_nn not null,
                       description varchar2(2000));
                       
create sequence products_seq
                start with 1
                increment by 1
                nocache
                nocycle;                    
                
desc products;

                                     
-- sale tbl
create table sales (sale_id number(10) primary key,
                    seller_id number(10) constraint slae_sid_fk references members (member_id),
                    product_id number(10) constraint sale_pid_fk references products (product_id),
                    sale_status varchar2(20) constraint sale_status_ch check (sale_status in ('판매중', '예약중', '판매완료')));

create sequence sales_seq
                start with 1
                increment by 1
                nocache
                nocycle;
                
desc sales;


-- order tbl
create table orders (order_id number(10) primary key,
                     buyer_id number(10) constraint order_bid_fk references members (member_id),
                     sale_id number(10) constraint order_sid_fk references sales (sale_id),
                     order_quantity number(10) constraint order_qu_ch check (order_quantity > 0),
                     order_price number(10) constraint order_pr_nn not null);
                     
create sequence orders_seq
                start with 1
                increment by 1
                nocache
                nocycle;
                
desc orders;


-- board tbl
create table boards (board_id number(10) primary key,
                    member_id number(10) constraint board_mid_fk references members (member_id),
                    title varchar2(200),
                    content varchar2(2000),
                    reg_date date);

create sequence boards_seq
                start with 1
                increment by 1
                nocache
                nocycle;
                
desc boards;



-- reply tbl
create table replys (reply_id number(10) primary key,
                     board_id number(10) constraint reply_bid_fk references boards (board_id),
                     member_id number(10) constraint reply_mid_fk references members (member_id),
                     content varchar2(2000),
                     reg_date date);

create sequence replys_seq
                start with 1
                increment by 1
                nocache
                nocycle;
                     
desc replys;



-- history tbl
create table histories (history_id number(10) primary key,
                        order_id number(10) constraint hs_oid_fk references orders (order_id),
                        sale_id number(10) constraint hs_sid_fk references sales (sale_id));

create sequence histories_seq
                start with 1
                increment by 1
                nocache
                nocycle;
                       
desc histories;

alter table histories drop column sale_id;


-- message tbl
create table messages (message_id number(10) primary key,
                       sender_id number(10) constraint ms_sid_fk references members (member_id),
                       receiver_id number(10) constraint ms_rid_fk references members (member_id),
                       content varchar2(2000),
                       send_date date);

create sequence messages_seq
                start with 1
                increment by 1
                nocache
                nocycle;
                       
desc messages;                       
                    
-- 제약조건 확인
select table_name, constraint_name, constraint_type, status, search_condition
from user_constraints;





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






