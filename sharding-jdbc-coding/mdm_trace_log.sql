CREATE TABLE `mdm_trace_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `target_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分发下游系统编码',
  `md_data_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主数据id，全局唯一',
  `md_data_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主数据业务编码,业务主键',
  `md_model` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主数据模型,与表映射',
  `param` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求参数json串',
  `trace_status` tinyint DEFAULT NULL COMMENT '分发状态，1，成功；2，失败；',
  `detail_json` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分发日志详情json',
  `trace_batch` bigint DEFAULT NULL COMMENT '分发批次，long类型，0为历史批次',
  `trace_time` datetime DEFAULT NULL COMMENT '跟踪时间',
  `dispense_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'TB' COMMENT '主数据分发类型：TB同步；YBQL异步全量；YBZL异步增量',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `md_data_id_index` (`md_data_id`) USING BTREE,
  KEY `trace_time_index` (`trace_time`) USING BTREE,
  KEY `TRACE_BATCH_INDEX` (`trace_batch`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1691770385358626819 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='主数据分发日志记录表';



CREATE TABLE `mdm_trace_log_0` (
                                   `id` bigint NOT NULL COMMENT '主键',
                                   `target_code` varchar(128) NOT NULL DEFAULT '' COMMENT '分发下游系统编码',
                                   `md_data_id` varchar(128) DEFAULT NULL COMMENT '主数据id，全局唯一',
                                   `md_data_code` varchar(255) DEFAULT NULL COMMENT '主数据业务编码,业务主键',
                                   `md_model` varchar(128) DEFAULT NULL COMMENT '主数据模型,与表映射',
                                   `param` varchar(5000) DEFAULT NULL COMMENT '请求参数json串',
                                   `trace_status` tinyint DEFAULT NULL COMMENT '分发状态，1，成功；2，失败；',
                                   `detail_json` varchar(5000) DEFAULT NULL COMMENT '分发日志详情json',
                                   `trace_batch` bigint DEFAULT NULL COMMENT '分发批次，long类型，0为历史批次',
                                   `trace_time` datetime DEFAULT NULL COMMENT '跟踪时间',
                                   `dispense_type` varchar(20) DEFAULT 'TB' COMMENT '主数据分发类型：TB同步；YBQL异步全量；YBZL异步增量',
                                   `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
                                   `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
                                   `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   PRIMARY KEY (`id`) USING BTREE,
                                   KEY `md_data_id_index` (`md_data_id`) USING BTREE,
                                   KEY `trace_time_index` (`trace_time`) USING BTREE,
                                   KEY `TRACE_BATCH_INDEX` (`trace_batch`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='主数据分发日志记录表-分表3';