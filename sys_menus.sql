CREATE TABLE `sys_menus`  (
  `m_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `m_name` varchar(50) NULL COMMENT '菜单名',
  `m_parentid` int(10) NULL DEFAULT 0 COMMENT '父id；默认为0 ，就是根菜单',
  `m_code` varchar(255) NULL COMMENT '菜单code',
  `m_url` varchar(100) NULL COMMENT '路由url',
  `m_icon` varchar(100) NULL COMMENT '菜单图标',
  `m_isvalid` int(1) NULL DEFAULT 0 COMMENT '是否有效  0有效  1无效',
  `m_order` int(50) NULL COMMENT '排序',
  `m_info` varchar(255) NULL COMMENT '描述',
  `m_createdate` datetime(6) NULL COMMENT '创建时间',
  `m_updatedate` datetime(6) NULL COMMENT '更新时间',
  PRIMARY KEY (`m_id`)
);

