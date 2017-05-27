EXPLAIN  select * from usertb where id=500;

EXPLAIN select count(id) from usertb;

ALTER TABLE `usertb` ADD UNIQUE INDEX IDX_USERTB_ID ( `id` );

EXPLAIN  SELECT  * from ld_cart cart join ld_cart_item item on item.CART_ID=cart.ID;

EXPLAIN  SELECT  * from ld_cart_item item where item.CART_ID in (select id from ld_cart);

EXPLAIN  SELECT  * from  ld_cart where STATUS='CREATE' union select * from ld_cart where STATUS='DELETED';

-- ====================== select_type =================
--simple，primary，subquery，derived，union，unionresult
-- ====================== type ============================
--all，index，range，ref，eq_ref，const，system，NULL
--ALL，即full table scan，遍历全表
--index为full index scan，只遍历索引树。
--range:常见于between，<，>的查询
--NULL表示在执行语句中，不用查表或索引
-- ========================key 表示查询时使用的索引
-- ========================rows估算读取的行数
-- ========================extra
--using index：表示在相应的select中使用了覆盖索引。
--usingwhere
--using temporay表示用临时表来存储结果集，常见于排序和分组查询
--usingfilesort，mysql中无法用索引完成的排序成为文件排序。

--https://my.oschina.net/zimingforever/blog/60233
