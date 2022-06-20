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