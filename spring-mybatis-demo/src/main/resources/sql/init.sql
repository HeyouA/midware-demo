CREATE DATABASE /*!32312 IF NOT EXISTS*/ `test20240319` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

-- test.t_user definition

CREATE TABLE `test20240319.t_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `tenant_id` int NOT NULL COMMENT '租户id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_update_time` (`update_time`) USING BTREE,
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';


INSERT INTO test20240319.t_user(id,tenant_id, name) VALUES(1, 0,'元月');