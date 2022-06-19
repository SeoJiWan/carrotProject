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