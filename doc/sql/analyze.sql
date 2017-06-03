-- process
show  processlist;
select * from information_schema.innodb_trx;

-- query cache =======================================================================
show variables like '%query_cache%';
show variables like '%timeout%';
show status like '%Qcache%';

--max_connections(Threads就是连接数.), show processlist;只列出前100条 =================
show variables like '%max_connections%'

show status;

-- table info  =========================================================================
select concat(round(sum(data_length/1024/1024),2),'MB') as data_length_MB,concat(round(sum(index_length/1024/1024),2),'MB') as index_length_MB,t.* from tables t
where table_name = 'LD_PRODUCT' and TABLE_SCHEMA='bigdata';

show variables like '%timeout%';
--version
show variables like 'version';

show table status;