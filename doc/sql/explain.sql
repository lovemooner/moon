EXPLAIN  select * from usertb where id=500;

EXPLAIN select count(*) from ld_cart;
EXPLAIN select * from ld_cart where id=7110656;;

ALTER TABLE `usertb` ADD UNIQUE INDEX IDX_USERTB_ID ( `id` );

EXPLAIN  SELECT  * from ld_cart cart join ld_cart_item item on item.CART_ID=cart.ID;

EXPLAIN  SELECT  * from ld_cart_item item where item.CART_ID in (select id from ld_cart);
--ld_cart
EXPLAIN  select * from ld_cart where id=7110657;
EXPLAIN  select * from ld_cart limit 10; --type=const
EXPLAIN  select * from ld_cart where summary='dxqmmfwIxnIPkLI';
EXPLAIN  select * from ld_cart order by summary limit 10;
EXPLAIN  SELECT  * from  ld_cart where STATUS='CREATE' union select * from ld_cart where STATUS='DELETED';
EXPLAIN  SELECT  * from  ld_cart order by last_update limit 10
--ld_cart_item
EXPLAIN  select * from ld_cart_item where cart_id=1;

EXPLAIN  select * from ld_cart_item order by id desc limit 10;
EXPLAIN  select status,count(id) from ld_cart group  by id ;
--ld_product
EXPLAIN  select * from ld_product order by product_name desc limit 1;

--join
EXPLAIN select * from  ld_cart c  join ld_cart_item i on c.id=i.cart_id  limit 20;
EXPLAIN select * from  ld_cart c  join ld_cart_item i on c.id=i.cart_id  join ld_product p on i.product_id=p.id limit 10;
EXPLAIN select * from  ld_cart c,ld_cart_item i where c.id=i.cart_id ;
EXPLAIN select * from  ld_cart c where c.id in(select i.cart_id  from ld_cart_item i)

-- ====================== select_type =====================
--simple，primary，subquery，derived，union，unionresult
-- ====================== type ============================
--all，index，range，ref，eq_ref，const，system，NULL
--ALL，即full table scan，遍历全表
--index为full index scan，只遍历索引树。
--range:常见于between，<，>的查询
--ref: Normal Index
--eq_ref: 常见于PRIMARY KEY或者unique index
--NULL表示在执行语句中，不用查表或索引
-- ========================key 表示查询时使用的索引=============
--PRIMARY
-- ========================rows估算读取的行数===============
-- ========================extra ===============================
--using index：表示在相应的select中使用了覆盖索引(Covering Index:利用索引返回select列表中的字段)。
--using where
--using temporay表示用临时表来存储结果集，常见于排序和分组查询
--using filesort，mysql中无法用索引完成的排序成为文件排序。

--https://my.oschina.net/zimingforever/blog/60233
