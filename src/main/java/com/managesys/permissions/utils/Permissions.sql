/*
系统菜单和其初始数据
不可以修改其中的任何值
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `pid` int(11) DEFAULT '0' COMMENT '父id',
  `name` varchar(256) DEFAULT NULL COMMENT '名称',
  `path` varchar(1024) DEFAULT NULL COMMENT '路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='后台系统菜单表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表主键',
  `name` varchar(100) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB   DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '权限管理', null, '2017-01-22 22:34:13');
INSERT INTO `sys_menu` VALUES ('2', '1', '角色管理', '/boss/permisManage/tojsp.do', '2017-01-22 22:34:42');
INSERT INTO `sys_menu` VALUES ('3', '2', '新增角色', '/boss/permisManage/addRole.do', '2017-01-22 22:35:26');
INSERT INTO `sys_menu` VALUES ('4', '2', '编辑角色', '/boss/permisManage/updateRole.do', '2017-01-22 22:35:55');
INSERT INTO `sys_menu` VALUES ('5', '2', '查询角色', '/boss/permisManage/getRoleByPage.do', '2017-01-22 22:36:58');
INSERT INTO `sys_menu` VALUES ('6', '2', '删除角色', '/boss/permisManage/deleteRole.do', '2017-01-22 22:37:14');
INSERT INTO `sys_menu` VALUES ('7', '1', '菜单管理', '/boss/sysMenu/tojsp.do', '2017-01-23 21:33:42');
INSERT INTO `sys_menu` VALUES ('8', '7', '添加菜单', '/boss/sysMenu/addMenu.do', '2017-01-23 21:53:29');
INSERT INTO `sys_menu` VALUES ('9', '7', '删除菜单', '/boss/sysMenu/deleteMenu.do', '2017-01-23 21:53:31');
INSERT INTO `sys_menu` VALUES ('10', '7', '修改菜单', '/boss/sysMenu/updateMenu.do', '2017-01-23 21:53:36');
INSERT INTO `sys_menu` VALUES ('11', '7', '查询菜单', '/boss/sysMenu/getTreeMenus.do', '2017-01-23 21:53:34');
INSERT INTO `sys_menu` VALUES ('12', '7', '修改菜单名字', '/boss/sysMenu/updateMenuName.do', '2017-01-23 21:50:02');
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '2015-11-23 20:13:50');
INSERT INTO `sys_role` VALUES ('2', '开发人员', '2017-01-23 21:37:58');
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1', '2017-01-23 21:12:26');
INSERT INTO `sys_role_menu` VALUES ('2', '2', '1', '2017-01-23 21:12:32');
INSERT INTO `sys_role_menu` VALUES ('3', '3', '1', '2017-01-23 21:12:30');
INSERT INTO `sys_role_menu` VALUES ('4', '4', '1', '2017-01-23 21:12:34');
INSERT INTO `sys_role_menu` VALUES ('5', '5', '1', '2017-01-23 21:12:36');
INSERT INTO `sys_role_menu` VALUES ('6', '6', '1', '2017-01-23 21:12:38');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '2', null);
INSERT INTO `sys_role_menu` VALUES ('8', '7', '2', null);
INSERT INTO `sys_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '2017-01-23 20:11:10', '1');
INSERT INTO `sys_user` VALUES ('2', 'develop', '21232f297a57a5a743894a0e4a801fc3', '2017-01-23 21:28:43', '1');
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2', '2');
