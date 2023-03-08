INSERT INTO bms_warehouse_keeper.t_category (id,name,dt_created,dt_updated,valid) VALUES
                                                                                      (1,'mobile phone','2023-02-22 13:09:05','2023-02-22 13:09:05',1),
                                                                                      (2,'computer','2023-02-22 13:09:05','2023-02-22 13:20:32',1),
                                                                                      (8,'Funiture','2023-03-06 14:24:06','2023-03-06 14:24:06',1),
                                                                                      (9,'Fruit','2023-03-06 14:25:55','2023-03-06 14:25:55',1) ON DUPLICATE KEY UPDATE id=id;
INSERT INTO bms_warehouse_keeper.t_category_param (id,category_id,name,is_numeric,unit,dt_created,dt_updated,valid) VALUES
                                                                                                                        (1,1,'CPU',0,'','2023-02-22 13:10:24','2023-02-22 13:28:02',1),
                                                                                                                        (2,1,'RAM',1,'GB','2023-02-22 13:15:15','2023-02-22 13:15:15',1),
                                                                                                                        (3,2,'CPU',0,NULL,'2023-02-22 13:18:36','2023-02-22 13:28:02',1),
                                                                                                                        (4,2,'RAM',1,'GB','2023-02-22 13:22:00','2023-02-22 13:27:26',1),
                                                                                                                        (6,6,'height',1,'cm','2023-03-06 14:11:03','2023-03-06 14:11:03',1),
                                                                                                                        (7,8,'length',1,'cm','2023-03-06 14:24:06','2023-03-06 03:26:10',1),
                                                                                                                        (8,8,'height',1,'cm','2023-03-06 14:24:06','2023-03-06 03:26:10',1),
                                                                                                                        (9,9,'weight',1,'kg','2023-03-06 14:25:55','2023-03-06 14:25:55',1) ON DUPLICATE KEY UPDATE id=id;
INSERT INTO bms_warehouse_keeper.t_city (id,name,dt_created,dt_updated) VALUES
                                                                            (1,'Melbourne','2023-02-22 13:48:35','2023-02-22 13:48:35'),
                                                                            (2,'Sydney','2023-02-22 13:48:35','2023-02-22 13:48:35'),
                                                                            (3,'New York','2023-03-06 13:52:49','2023-03-06 13:52:49') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO bms_warehouse_keeper.t_consume (id,sku_id,num,warehouse_id,consumer_id,status,dt_created,dt_updated,keeper_id,valid) VALUES
    (1,1,24,2,6,3,'2023-03-06 16:45:48','2023-03-06 06:24:46',6,1) ON DUPLICATE KEY UPDATE id=id;
INSERT INTO bms_warehouse_keeper.t_permission (id,name,comment,dt_created,dt_updated) VALUES
                                                                                          (1,'sys:inventory:purchase','purchase items, prepare for put into warehouse','2023-02-22 10:45:28','2023-02-28 00:25:27'),
                                                                                          (2,'sys:inventory:consume','consume items from warehouse','2023-02-22 10:45:28','2023-02-28 00:25:27'),
                                                                                          (3,'sys:inventory:manage','manange, audit purchase and consume applies','2023-02-22 10:45:28','2023-02-28 00:25:27'),
                                                                                          (4,'sys:user:manage','create or disable user in db','2023-02-22 10:45:28','2023-02-28 00:26:24'),
                                                                                          (5,'sys:inventory:details','permission to see inventory details','2023-02-22 22:18:26','2023-02-28 00:26:46') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO bms_warehouse_keeper.t_purchase (id,sku_id,num,warehouse_id,purchaser_id,status,dt_created,dt_updated,price,keeper_id,valid) VALUES
                                                                                                                                             (1,1,22,2,6,2,'2023-03-06 16:39:51','2023-03-06 05:42:47',2.50,6,1),
                                                                                                                                             (2,1,22,2,6,2,'2023-03-06 16:46:25','2023-03-06 16:46:25',2.50,6,1) ON DUPLICATE KEY UPDATE id=id;
INSERT INTO bms_warehouse_keeper.t_role (id,name,comment,dt_created,dt_updated) VALUES
                                                                                    (1,'admin','IT admin role, create and update users, create warehouse','2023-02-22 10:36:06','2023-02-22 13:41:22'),
                                                                                    (2,'purchaser','buy items for warehouse','2023-02-22 13:53:33','2023-02-22 14:06:51'),
                                                                                    (3,'consumer','get items from warehouse','2023-02-22 10:36:06','2023-02-22 14:06:51'),
                                                                                    (4,'warehouse_keeper','maintain warehouse','2023-02-22 13:41:22','2023-02-25 09:31:19'),
                                                                                    (5,'super','debug_use','2023-02-25 09:35:41','2023-02-25 09:35:47') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO bms_warehouse_keeper.t_role_permission (id,role_id,permission_id,dt_created,dt_updated) VALUES
                                                                                                        (1,1,4,'2023-02-25 09:36:12','2023-02-25 09:36:12'),
                                                                                                        (2,2,1,'2023-02-25 09:36:12','2023-02-25 09:36:12'),
                                                                                                        (3,3,2,'2023-02-25 09:36:12','2023-02-25 09:36:12'),
                                                                                                        (4,4,3,'2023-02-25 09:36:12','2023-02-25 09:36:12'),
                                                                                                        (5,4,5,'2023-02-25 09:36:12','2023-02-26 23:08:20'),
                                                                                                        (6,5,1,'2023-02-25 09:36:12','2023-02-25 09:36:12'),
                                                                                                        (7,5,2,'2023-02-25 09:36:12','2023-02-25 09:36:12'),
                                                                                                        (8,5,3,'2023-02-25 09:36:12','2023-02-25 09:36:12'),
                                                                                                        (9,5,4,'2023-02-25 09:36:12','2023-02-25 09:36:12'),
                                                                                                        (10,5,5,'2023-02-25 09:36:12','2023-02-25 09:36:12'),
                                                                                                        (12,3,5,'2023-03-02 23:20:26','2023-03-02 23:20:26'),
                                                                                                        (13,2,5,'2023-03-02 23:20:26','2023-03-02 23:20:26'),
                                                                                                        (14,1,5,'2023-03-02 23:20:26','2023-03-02 23:20:26') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO bms_warehouse_keeper.t_sku (id,spu_id,name,price,param,saleable,valid,dt_created,dt_updated) VALUES
                                                                                                             (1,1,'iPhone 14 Pro',4000.00,'{"CPU": "A14", "RAM": 8}',1,1,'2023-02-22 13:26:12','2023-02-22 13:26:12'),
                                                                                                             (2,1,'iPhone 14 Ultra',6000.00,'{"CPU": "A115", "RAM": 16}',1,1,'2023-02-22 13:26:12','2023-02-22 13:26:12'),
                                                                                                             (3,3,'MacBook Pro 16 inch',8000.00,'{"CPU": "M2 Pro", "RAM": 16}',1,1,'2023-02-22 13:26:12','2023-02-22 13:26:12'),
                                                                                                             (4,2,'Nokia Fake',1000.00,'{"CPU": "5A", "RAM": 4}',1,1,'2023-02-22 13:26:12','2023-02-22 13:26:12'),
                                                                                                             (5,3,'MacBook Pro 14 inch',8000.00,'{"CPU": "M2 Pro", "RAM": 8}',1,1,'2023-02-22 13:26:12','2023-02-22 13:26:12'),
                                                                                                             (6,4,'Pear Big',99.00,'{"weight": 8}',1,1,'2023-03-06 16:02:18','2023-03-06 16:02:18') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO bms_warehouse_keeper.t_spu (id,name,category_id,saleable,valid,dt_created,dt_updated) VALUES
                                                                                                      (1,'iPhone14',1,1,1,'2023-02-22 13:20:11','2023-02-22 13:20:11'),
                                                                                                      (2,'Nokia',1,1,1,'2023-02-22 13:21:16','2023-02-22 13:21:16'),
                                                                                                      (3,'MackBook Pro',2,1,1,'2023-02-22 13:21:16','2023-02-22 13:30:29'),
                                                                                                      (4,'Pear',9,1,1,'2023-03-06 14:50:08','2023-03-06 05:09:55') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO bms_warehouse_keeper.t_user (id,login_id,name,password,role_id,dt_created,dt_updated,status,phone) VALUES
                                                                                                                   (1,'test@996workers.icu','996 Worker','$2a$10$dDK46uSwGXBh9kKYsjqDAuRGq6xOVjT17uHJfRpIa3UmIjaXVsujS',1,'2023-02-23 16:11:54','2023-02-23 16:11:54',1,'444444111'),
                                                                                                                   (2,'test2@996workers.icu','997 Worker','$2a$10$ASxWPiZ7EFSxTa6bgaq4TurRbx3zM/jCJz0eFfmcLDTSt1H7IKnE.',2,'2023-02-23 16:30:57','2023-02-23 16:30:57',1,'1234555'),
                                                                                                                   (3,'admin@996workers.icu','admin','$2a$10$dDK46uSwGXBh9kKYsjqDAuRGq6xOVjT17uHJfRpIa3UmIjaXVsujS',1,'2023-03-06 02:32:23','2023-03-06 02:32:23',1,'123456'),
                                                                                                                   (4,'purchaser@996workers.icu','purchase man','$2a$10$LBf2e13gWPtJKJRVUJKCWeB4Vbp96KBqvhs/izlUq5BSoyseHJeqK',2,'2023-03-06 13:33:02','2023-03-06 13:33:02',1,'1234555'),
                                                                                                                   (5,'consumer@996workers.icu','consume man','$2a$10$a.T.jc1JohhxP.h.xU00YuACv824hlpjE1ND7bxssUvk9RxdVzvwS',3,'2023-03-06 13:33:36','2023-03-06 13:33:36',1,'1234555'),
                                                                                                                   (6,'warehousekeeper@996workers.icu','warehouse keeper man','$2a$10$/QKd5f0WEyaRciuLgJZbv.BCUZ8PDArCex9Mw5Bbn1V.DOALJNhBu',4,'2023-03-06 13:34:11','2023-03-06 13:34:11',1,'1234555'),
                                                                                                                   (8,'warehousekeeper2@996workers.icu','warehouse keeper man 2','$2a$10$Y4szrEyYB.B4GknTyWdg/uwzVgbKF4Mn2B.33DLJKtdG/X97XRT..',4,'2023-03-06 13:49:13','2023-03-06 13:49:13',1,'1234555') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO bms_warehouse_keeper.t_warehouse (id,address,city_id,dt_created,dt_updated,name,valid) VALUES
                                                                                                       (1,'North Melbourne 3051',1,'2023-02-22 22:12:51','2023-02-22 22:15:08','NM inventory',1),
                                                                                                       (2,'South Sydney 8081',2,'2023-02-22 22:15:08','2023-03-02 23:00:58','Sy inventory',1),
                                                                                                       (3,'World of wonder',1,'2023-03-06 16:15:08','2023-03-06 16:15:08','Big house!',1) ON DUPLICATE KEY UPDATE id=id;
INSERT INTO bms_warehouse_keeper.t_warehouse_sku (warehouse_id,sku_id,num,unit,dt_created,dt_updated,id) VALUES
                                                                                                             (1,1,200,'unit','2023-03-02 05:44:31','2023-03-02 05:46:57',1),
                                                                                                             (1,2,13,'unit','2023-03-02 05:46:57','2023-03-02 05:46:57',2),
                                                                                                             (1,3,80,'unit','2023-03-02 05:46:57','2023-03-02 05:46:57',3),
                                                                                                             (1,4,0,'unit','2023-03-02 05:46:57','2023-03-02 05:46:57',4),
                                                                                                             (1,5,33,'unit','2023-03-02 05:46:57','2023-03-02 05:46:57',5),
                                                                                                             (2,2,233,'unit','2023-03-02 05:46:57','2023-03-02 05:46:57',6),
                                                                                                             (2,3,100,'unit','2023-03-02 05:46:57','2023-03-02 05:46:57',7),
                                                                                                             (2,1,22,'unit','2023-03-06 16:47:21','2023-03-06 16:47:21',9) ON DUPLICATE KEY UPDATE id=id;
