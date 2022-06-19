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







