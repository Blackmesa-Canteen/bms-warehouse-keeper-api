-- MySQL dump 10.13  Distrib 8.0.31, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: bms_warehouse_keeper
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_category`
--

DROP TABLE IF EXISTS `t_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_category` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='item category table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_category`
--

LOCK TABLES `t_category` WRITE;
/*!40000 ALTER TABLE `t_category` DISABLE KEYS */;
INSERT INTO `t_category` VALUES (1,'mobile phone','2023-02-22 13:09:05','2023-02-22 13:09:05'),(2,'computer','2023-02-22 13:09:05','2023-02-22 13:20:32');
/*!40000 ALTER TABLE `t_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_category_param`
--

DROP TABLE IF EXISTS `t_category_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_category_param` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int unsigned NOT NULL,
  `name` varchar(200) NOT NULL,
  `numeric` tinyint(1) NOT NULL COMMENT 'is numeric parameter',
  `unit` varchar(200) DEFAULT NULL COMMENT 'unit',
  `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_spg_id` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='parameter table for item categories';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_category_param`
--

LOCK TABLES `t_category_param` WRITE;
/*!40000 ALTER TABLE `t_category_param` DISABLE KEYS */;
INSERT INTO `t_category_param` VALUES (1,1,'CPU',0,'','2023-02-22 13:10:24','2023-02-22 13:28:02'),(2,1,'RAM',1,'GB','2023-02-22 13:15:15','2023-02-22 13:15:15'),(3,2,'CPU',0,NULL,'2023-02-22 13:18:36','2023-02-22 13:28:02'),(4,2,'RAM',1,'GB','2023-02-22 13:22:00','2023-02-22 13:27:26');
/*!40000 ALTER TABLE `t_category_param` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_city`
--

DROP TABLE IF EXISTS `t_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_city` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_city`
--

LOCK TABLES `t_city` WRITE;
/*!40000 ALTER TABLE `t_city` DISABLE KEYS */;
INSERT INTO `t_city` VALUES (1,'Melbourne','2023-02-22 13:48:35','2023-02-22 13:48:35'),(2,'Sydney','2023-02-22 13:48:35','2023-02-22 13:48:35');
/*!40000 ALTER TABLE `t_city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_consume`
--

DROP TABLE IF EXISTS `t_consume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_consume` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `sku_id` int unsigned NOT NULL,
  `num` int unsigned NOT NULL,
  `warehouse_id` int unsigned NOT NULL,
  `consumer_id` int unsigned NOT NULL,
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '1 pending for audit; 2 finished',
  `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `keeper_id` int unsigned DEFAULT NULL COMMENT 'audit warehouse keeper id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_consume`
--

LOCK TABLES `t_consume` WRITE;
/*!40000 ALTER TABLE `t_consume` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_consume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_permission`
--

DROP TABLE IF EXISTS `t_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `comment` text NOT NULL DEFAULT (_utf8mb4''),
  `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission_pk2` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES `t_permission` WRITE;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
INSERT INTO `t_permission` VALUES (1,'purchase-product','purchase items, prepare for put into warehouse','2023-02-22 10:45:28','2023-02-22 22:18:26'),(2,'consume-product','consume items from warehouse','2023-02-22 10:45:28','2023-02-22 22:18:26'),(3,'manage-inventory','manange, audit purchase and consume applies','2023-02-22 10:45:28','2023-02-22 22:18:26'),(4,'manage-user','create or disable user in db','2023-02-22 10:45:28','2023-02-22 14:07:02'),(5,'see-inventory','permission to see inventory details','2023-02-22 22:18:26','2023-02-22 22:19:28');
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_purchase`
--

DROP TABLE IF EXISTS `t_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_purchase` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `sku_id` int unsigned NOT NULL,
  `num` int unsigned NOT NULL,
  `warehouse_id` int unsigned NOT NULL,
  `purchaser_id` int unsigned NOT NULL,
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '1 pending for audit; 2 finished',
  `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `price` decimal(10,0) NOT NULL,
  `keeper_id` int unsigned DEFAULT NULL COMMENT 'audit warehouse keeper id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_purchase`
--

LOCK TABLES `t_purchase` WRITE;
/*!40000 ALTER TABLE `t_purchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `comment` text NOT NULL DEFAULT (_utf8mb4''),
  `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_pk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'admin','IT admin role, create and update users, create warehouse','2023-02-22 10:36:06','2023-02-22 13:41:22'),(2,'purchaser','buy items for warehouse','2023-02-22 13:53:33','2023-02-22 14:06:51'),(3,'consumer','get items from warehouse','2023-02-22 10:36:06','2023-02-22 14:06:51'),(4,'warehouse keeper','maintain warehouse','2023-02-22 13:41:22','2023-02-22 14:06:51');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_permission`
--

DROP TABLE IF EXISTS `t_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role_permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int unsigned NOT NULL,
  `permission_id` int unsigned NOT NULL,
  `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_role_permission_role_id_IDX` (`role_id`,`permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_permission`
--

LOCK TABLES `t_role_permission` WRITE;
/*!40000 ALTER TABLE `t_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sku`
--

DROP TABLE IF EXISTS `t_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sku` (
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='item table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sku`
--

LOCK TABLES `t_sku` WRITE;
/*!40000 ALTER TABLE `t_sku` DISABLE KEYS */;
INSERT INTO `t_sku` VALUES (1,1,'iPhone 14 Pro',4000.00,'{\"CPU\": \"A14\", \"RAM\": 8}',1,1,'2023-02-22 13:26:12','2023-02-22 13:26:12'),(2,1,'iPhone 14 Ultra',6000.00,'{\"CPU\": \"A115\", \"RAM\": 16}',1,1,'2023-02-22 13:26:12','2023-02-22 13:26:12'),(3,3,'MacBook Pro 16 inch',8000.00,'{\"CPU\": \"M2 Pro\", \"RAM\": 16}',1,1,'2023-02-22 13:26:12','2023-02-22 13:26:12'),(4,2,'Nokia Fake',1000.00,'{\"CPU\": \"5A\", \"RAM\": 4}',1,1,'2023-02-22 13:26:12','2023-02-22 13:26:12'),(5,3,'MacBook Pro 14 inch',8000.00,'{\"CPU\": \"M2 Pro\", \"RAM\": 8}',1,1,'2023-02-22 13:26:12','2023-02-22 13:26:12');
/*!40000 ALTER TABLE `t_sku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_spu`
--

DROP TABLE IF EXISTS `t_spu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_spu` (
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='product table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_spu`
--

LOCK TABLES `t_spu` WRITE;
/*!40000 ALTER TABLE `t_spu` DISABLE KEYS */;
INSERT INTO `t_spu` VALUES (1,'iPhone14',1,1,1,'2023-02-22 13:20:11','2023-02-22 13:20:11'),(2,'Nokia',1,1,1,'2023-02-22 13:21:16','2023-02-22 13:21:16'),(3,'MackBook Pro',2,1,1,'2023-02-22 13:21:16','2023-02-22 13:30:29');
/*!40000 ALTER TABLE `t_spu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'test@996workers.icu','996 Worker','$2a$10$dDK46uSwGXBh9kKYsjqDAuRGq6xOVjT17uHJfRpIa3UmIjaXVsujS',1,'2023-02-23 16:11:54','2023-02-23 16:11:54',1,'444444111'),(2,'test2@996workers.icu','997 Worker','$2a$10$ASxWPiZ7EFSxTa6bgaq4TurRbx3zM/jCJz0eFfmcLDTSt1H7IKnE.',2,'2023-02-23 16:30:57','2023-02-23 16:30:57',1,'1234555');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_warehouse`
--

DROP TABLE IF EXISTS `t_warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_warehouse` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `address` varchar(200) NOT NULL,
  `city_id` int unsigned NOT NULL,
  `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `t_warehouse_city_id_IDX` (`city_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_warehouse`
--

LOCK TABLES `t_warehouse` WRITE;
/*!40000 ALTER TABLE `t_warehouse` DISABLE KEYS */;
INSERT INTO `t_warehouse` VALUES (1,'North Melbourne 3051',1,'2023-02-22 22:12:51','2023-02-22 22:15:08','NM inventory'),(2,'South Sydney 8081',2,'2023-02-22 22:15:08','2023-02-22 22:15:08','NM inventory');
/*!40000 ALTER TABLE `t_warehouse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_warehouse_sku`
--

DROP TABLE IF EXISTS `t_warehouse_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_warehouse_sku` (
  `warehouse_id` int unsigned NOT NULL,
  `sku_id` int unsigned NOT NULL,
  `num` int unsigned NOT NULL,
  `unit` varchar(20) NOT NULL,
  `dt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_warehouse_sku_sku_id_IDX` (`sku_id`,`warehouse_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_warehouse_sku`
--

LOCK TABLES `t_warehouse_sku` WRITE;
/*!40000 ALTER TABLE `t_warehouse_sku` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_warehouse_sku` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-23 17:38:29
