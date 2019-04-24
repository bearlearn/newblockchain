set foreign_key_checks = 0;
drop table if exists 	`transaction_detail`;
create table `transaction_detail`
(
`tx_detail_id` bigint(20) primary key auto_increment,
`tx_hash` varchar(70) not	 null,
`address` varchar(70),
`type` tinyint,
`amount` double,
index `idx_txhash` (`tx_hash`),
index `idx_address` (`address`)
) engine = innodb
default charset = utf8
auto_increment = 1;	




