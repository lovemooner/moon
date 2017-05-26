--1.查看表大小
use information_schema;
select concat(round(sum(data_length/1024/1024),2),'MB') as data_length_MB,
concat(round(sum(index_length/1024/1024),2),'MB') as index_length_MB,
 t.*
from tables t
where
table_name = 'usertb' and TABLE_SCHEMA='bigdata';