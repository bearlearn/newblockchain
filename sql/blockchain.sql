set foreign_key_checks=0;

-- ----------------------------
-- Table structure for blockchain
-- ----------------------------
drop table if exists `blockchain`;
create table `blockchain` (
  `blockchain_id` int(11) not null auto_increment,
  `name` varchar(50) not null ,
  `type` varchar(20) not null,
  `shortname` varchar(10),
  primary key (`blockchain_id`),
  index `idx_name`(`name`),
  index 	`idx_type` (`type`)
) engine=innodb default charset=utf8 auto_increment=1;

insert into `blockchain` values ('1', 'Bitcoin', 'main', 'BTC');
insert into `blockchain` values ('2', 'Bitcoin', 'testnet', 'BTCTest');
insert into `blockchain` values ('3', 'Ethereum', 'main', 'ETH');




