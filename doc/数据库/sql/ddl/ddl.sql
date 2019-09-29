--  create table ============

 DROP TABLE IF EXISTS `ld_cart`;

CREATE TABLE `ld_cart` (
  `id` bigint(20) NOT NULL,
  `created` bigint(20) NOT NULL,
  `last_update` bigint(20) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `discount` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `member_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `summary` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_ID_CART_SUMMARY` (`summary`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin

 DROP TABLE IF EXISTS `ld_cart_item`
CREATE TABLE `ld_cart_item` (
  `id` bigint(20) NOT NULL,
  `created` bigint(20) NOT NULL,
  `last_update` bigint(20) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `cart_id` bigint(20) DEFAULT NULL,
  `item_amount` int(11) DEFAULT NULL,
  `item_sum` double DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_LD_CART_ITEM_ID` (`id`) USING BTREE,
  UNIQUE KEY `IDX_LD_CART_ITEM_CART_ID` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin

CREATE TABLE `ld_myisam` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin

DELETE FROM ld_cart;

--SQL ALTER TABLE
ALTER TABLE usertb ADD status varchar(10)

ALTER TABLE `ld_cart` ADD UNIQUE INDEX IDX_LD_ORDER_ID ( `id` )
ALTER TABLE `LD_CART` ADD INDEX IDX_LD_ORDER_SUMMARY ( `summary` )

ALTER TABLE `LD_CART_ITEM` ADD UNIQUE INDEX IDX_LD_CART_ITEM_ID ( `id` )
ALTER TABLE `LD_CART_ITEM` ADD UNIQUE INDEX IDX_LD_CART_ITEM_CART_ID ( `CART_ID` );
 show index from ld_cart
  show index from ld_cart_item;



