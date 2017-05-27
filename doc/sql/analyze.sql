-- query cache =======================================================================
show variables like '%query_cache%';
show status like '%Qcache%';


--max_connections(Threads就是连接数.), show processlist;只列出前100条 =================
show variables like '%max_connections%'
show  processlist;

show status;

-- table info  =========================================================================
use information_schema;
select concat(round(sum(data_length/1024/1024),2),'MB') as data_length_MB,concat(round(sum(index_length/1024/1024),2),'MB') as index_length_MB,t.*
from tables t
 where table_name = 'usertb' and TABLE_SCHEMA='bigdata';

show variables like '%timeout%';
--version
show variables like 'version';