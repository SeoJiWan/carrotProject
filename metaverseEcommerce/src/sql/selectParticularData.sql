-- 구매왕
select m.identification, o.구매빈도수
from members m
join (select buyer_id, count(*) as 구매빈도수
      from orders
      group by buyer_id) o on (m.member_id = o.buyer_id);

select o.buyer_id, count(*) as 구매빈도수
from orders o
group by buyer_id;

-- 판매왕
select m.identification, 판매빈도수
from members m
join (select s.seller_id, count(*) as 판매빈도수
      from orders o 
      join sales s on (o.sale_id = s. sale_id)
      group by s.seller_id) k on (k.seller_id = m.member_id);

select s.seller_id, count(*) as 판매빈도수
from orders o 
join sales s on (o.sale_id = s. sale_id)
group by s.seller_id;

-- saleInfo
SELECT s.sale_id, s.seller_id, m.identification, s.sale_status, p.name, p.quantity, p.price, p.description
FROM sales s
JOIN products p ON (s.product_id = p.product_id)
JOIN members m ON (s.seller_id = m.member_id);

-- messageInfo
SELECT m.identification, t.content, p.name, t.send_date
FROM messages t 
JOIN members m ON (m.member_id = t.sender_id)
JOIN products p ON (p.product_id = t.product_id)
WHERE t.receiver_id = ?
ORDER BY t.message_id DESC;


-- 내 구매내역
select o.order_id, s.seller_id, m.identification, p.name, o.order_quantity, o.order_price
from orders o
join sales s on (o.sale_id = s.sale_id)
join products p on (s.product_id = p.product_id)
join members m on (s.seller_id = m.member_id)
WHERE o.buyer_id = ?; -- 로그인 아이디

-- 내 판매내역
select s.sale_id, o.buyer_id, m.identification, p.name, o.order_quantity, o.order_price
from orders o
join sales s on (o.sale_id = s.sale_id)
join products p on (s.product_id = p.product_id)
join members m on (o.buyer_id = m.member_id)
WHERE s.seller_id = ?; -- 로그인 아이디



---------- 상품검색 -----------
-- 상품 검색
SELECT s.sale_id, s.seller_id, m.identification, s.sale_status, p.name, p.quantity, p.price, p.description, m.address, s.product_id
FROM sales s
JOIN products p ON (s.product_id = p.product_id)
JOIN members m ON (s.seller_id = m.member_id)
WHERE p.quantity > 0 AND s.seller_id <> ?
ORDER BY s.product_id desc;

-- 키워드로 상품 검색
SELECT s.sale_id, s.seller_id, m.identification, s.sale_status, p.name, p.quantity, p.price, p.description, m.address, s.product_id
FROM sales s 
JOIN products p ON (s.product_id = p.product_id) 
JOIN members m ON (s.seller_id = m.member_id) 
WHERE p.quantity > 0 
AND s.seller_id <> ? 
AND p.name like '%'||?||'%'
ORDER BY s.product_id desc;

-- 주변지역의 상품만 검색
SELECT s.sale_id, s.seller_id, m.identification, s.sale_status, p.name, p.quantity, p.price, p.description, m.address, s.product_id, m.address, o.emd_cd
FROM sales s
JOIN products p ON (s.product_id = p.product_id)
JOIN members m ON (s.seller_id = m.member_id)
JOIN suseong_map o ON (m.address = o.emd_nn)
WHERE p.quantity > 0 AND s.seller_id <> 18
ORDER BY s.product_id desc;

