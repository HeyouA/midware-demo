
CREATE DATABASE /*!32312 IF NOT EXISTS*/ `testsharding1` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/ `testsharding2` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

use testsharding1;

CREATE TABLE `t_user_1` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `tenant_id` int NOT NULL COMMENT '租户id',
    `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_update_time` (`update_time`) USING BTREE,
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';


CREATE TABLE `t_user_2` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `tenant_id` int NOT NULL COMMENT '租户id',
    `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_update_time` (`update_time`) USING BTREE,
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

INSERT INTO testsharding1.t_user_01(id,tenant_id, name) VALUES(1, 0,'元月');