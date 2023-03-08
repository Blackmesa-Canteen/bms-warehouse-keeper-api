-- bms_warehouse_keeper.t_category definition

CREATE TABLE IF NOT EXISTS `t_category` (
                              `id` int unsigned NOT NULL AUTO_INCREMENT,
                              `name` varchar(200) NOT NULL,
                              `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              `valid` tinyint(1) NOT NULL DEFAULT '1',
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `unq_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='item category table';


-- bms_warehouse_keeper.t_category_param definition

CREATE TABLE IF NOT EXISTS `t_category_param` (
                                    `id` int unsigned NOT NULL AUTO_INCREMENT,
                                    `category_id` int unsigned NOT NULL,
                                    `name` varchar(200) NOT NULL,
                                    `is_numeric` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'is numeric parameter',
                                    `unit` varchar(200) DEFAULT NULL COMMENT 'unit',
                                    `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                    `valid` tinyint(1) NOT NULL DEFAULT '1',
                                    PRIMARY KEY (`id`),
                                    KEY `idx_spg_id` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='parameter table for item categories';


-- bms_warehouse_keeper.t_city definition

CREATE TABLE IF NOT EXISTS `t_city` (
                          `id` int unsigned NOT NULL AUTO_INCREMENT,
                          `name` varchar(200) NOT NULL,
                          `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bms_warehouse_keeper.t_consume definition

CREATE TABLE IF NOT EXISTS `t_consume` (
                             `id` int unsigned NOT NULL AUTO_INCREMENT,
                             `sku_id` int unsigned NOT NULL,
                             `num` int unsigned NOT NULL,
                             `warehouse_id` int unsigned NOT NULL,
                             `consumer_id` int unsigned NOT NULL,
                             `status` tinyint NOT NULL DEFAULT '1' COMMENT '1 pending for audit; 2 finished',
                             `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             `keeper_id` int unsigned DEFAULT NULL COMMENT 'audit warehouse keeper id',
                             `valid` tinyint(1) NOT NULL DEFAULT '1',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bms_warehouse_keeper.t_permission definition

CREATE TABLE IF NOT EXISTS `t_permission` (
                                `id` int unsigned NOT NULL AUTO_INCREMENT,
                                `name` varchar(64) NOT NULL,
                                `comment` text NOT NULL DEFAULT (_utf8mb4''),
                                `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `permission_pk2` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bms_warehouse_keeper.t_purchase definition

CREATE TABLE IF NOT EXISTS `t_purchase` (
                              `id` int unsigned NOT NULL AUTO_INCREMENT,
                              `sku_id` int unsigned NOT NULL,
                              `num` int unsigned NOT NULL,
                              `warehouse_id` int unsigned NOT NULL,
                              `purchaser_id` int unsigned NOT NULL,
                              `status` tinyint NOT NULL DEFAULT '1' COMMENT '1 pending for audit; 2 finished',
                              `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              `price` decimal(10,2) unsigned NOT NULL,
                              `keeper_id` int unsigned DEFAULT NULL COMMENT 'audit warehouse keeper id',
                              `valid` tinyint(1) NOT NULL DEFAULT '1',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bms_warehouse_keeper.t_role definition

CREATE TABLE IF NOT EXISTS `t_role` (
                          `id` int unsigned NOT NULL AUTO_INCREMENT,
                          `name` varchar(64) NOT NULL,
                          `comment` text NOT NULL DEFAULT (_utf8mb4''),
                          `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `role_pk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bms_warehouse_keeper.t_role_permission definition

CREATE TABLE IF NOT EXISTS `t_role_permission` (
                                     `id` int unsigned NOT NULL AUTO_INCREMENT,
                                     `role_id` int unsigned NOT NULL,
                                     `permission_id` int unsigned NOT NULL,
                                     `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     PRIMARY KEY (`id`),
                                     UNIQUE KEY `t_role_permission_role_id_IDX` (`role_id`,`permission_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bms_warehouse_keeper.t_sku definition

CREATE TABLE IF NOT EXISTS `t_sku` (
                         `id` int unsigned NOT NULL AUTO_INCREMENT,
                         `spu_id` int unsigned NOT NULL COMMENT 'production ID',
                         `name` varchar(200) NOT NULL COMMENT 'name',
                         `price` decimal(10,2) unsigned NOT NULL COMMENT '价格',
                         `param` json NOT NULL COMMENT 'production category param',
                         `saleable` tinyint(1) NOT NULL COMMENT 'is consumeable',
                         `valid` tinyint(1) NOT NULL COMMENT 'logic delete',
                         `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `dt_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`),
                         KEY `idx_spu_id` (`spu_id`),
                         KEY `idx_saleable` (`saleable`),
                         KEY `idx_valid` (`valid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='item table';


-- bms_warehouse_keeper.t_spu definition

CREATE TABLE IF NOT EXISTS `t_spu` (
                         `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                         `name` varchar(200) NOT NULL COMMENT 'product title',
                         `category_id` int unsigned NOT NULL COMMENT 'category ID',
                         `saleable` tinyint(1) NOT NULL COMMENT 'is on sale for consumer to pick up',
                         `valid` tinyint(1) NOT NULL COMMENT 'is valid',
                         `dt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `dt_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`),
                         KEY `idx_category_id` (`category_id`),
                         KEY `idx_saleable` (`saleable`),
                         KEY `idx_valid` (`valid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='product table';


-- bms_warehouse_keeper.t_user definition

CREATE TABLE IF NOT EXISTS `t_user` (
                          `id` int unsigned NOT NULL AUTO_INCREMENT,
                          `login_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                          `name` varchar(64) NOT NULL,
                          `password` varchar(128) NOT NULL,
                          `role_id` int unsigned NOT NULL,
                          `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'status: 1 working, 2 on vacation, 3 resigned, 4 banned',
                          `phone` varchar(36) NOT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `user_pk2` (`login_id`),
                          KEY `user_role_role_id_fk` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bms_warehouse_keeper.t_warehouse definition

CREATE TABLE IF NOT EXISTS `t_warehouse` (
                               `id` int unsigned NOT NULL AUTO_INCREMENT,
                               `address` varchar(200) NOT NULL,
                               `city_id` int unsigned NOT NULL,
                               `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               `name` varchar(128) NOT NULL,
                               `valid` tinyint(1) NOT NULL DEFAULT '1',
                               PRIMARY KEY (`id`),
                               KEY `t_warehouse_city_id_IDX` (`city_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bms_warehouse_keeper.t_warehouse_sku definition

CREATE TABLE IF NOT EXISTS `t_warehouse_sku` (
                                   `warehouse_id` int unsigned NOT NULL,
                                   `sku_id` int unsigned NOT NULL,
                                   `num` int unsigned NOT NULL,
                                   `unit` varchar(20) NOT NULL,
                                   `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                   `id` int unsigned NOT NULL AUTO_INCREMENT,
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `t_warehouse_sku_sku_id_IDX` (`sku_id`,`warehouse_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
