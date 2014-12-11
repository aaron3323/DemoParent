/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2014-11-25 12:08:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `u_resource`
-- ----------------------------
DROP TABLE IF EXISTS `u_resource`;
CREATE TABLE `u_resource` (
  `id` varchar(64) NOT NULL DEFAULT '',
  `pid` varchar(64) DEFAULT NULL,
  `enabled` int(1) DEFAULT NULL,
  `resourcename` varchar(255) DEFAULT NULL,
  `resourcetype` varchar(255) DEFAULT NULL,
  `resourceurl` varchar(255) DEFAULT NULL,
  `access` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_resource
-- ----------------------------
INSERT INTO `u_resource` VALUES ('1', null, '1', 'main页面', 'url', '/main', 'ROLE_MAIN', null);
INSERT INTO `u_resource` VALUES ('2', null, '1', 'login页面', 'url', '/login', 'ROLE_USER', null);
INSERT INTO `u_resource` VALUES ('3', null, '0', '超时页面', 'url', '/timeout', 'permitAll', null);

-- ----------------------------
-- Table structure for `u_role`
-- ----------------------------
DROP TABLE IF EXISTS `u_role`;
CREATE TABLE `u_role` (
  `id` varchar(64) NOT NULL DEFAULT '',
  `enabled` int(1) DEFAULT NULL,
  `rolename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_role
-- ----------------------------
INSERT INTO `u_role` VALUES ('1', '1', '来宾');

-- ----------------------------
-- Table structure for `u_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `u_role_resource`;
CREATE TABLE `u_role_resource` (
  `id` varchar(64) NOT NULL DEFAULT '',
  `roleid` varchar(64) DEFAULT NULL,
  `resourceid` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_role_resource
-- ----------------------------
INSERT INTO `u_role_resource` VALUES ('1', '1', '1');
INSERT INTO `u_role_resource` VALUES ('2', '1', '2');
INSERT INTO `u_role_resource` VALUES ('3', '1', '3');

-- ----------------------------
-- Table structure for `u_user`
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `id` varchar(64) NOT NULL DEFAULT '',
  `enabled` int(1) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES ('1', '1', 'guest', 'guest');

-- ----------------------------
-- Table structure for `u_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `u_user_role`;
CREATE TABLE `u_user_role` (
  `id` varchar(64) NOT NULL DEFAULT '',
  `userid` varchar(64) DEFAULT NULL,
  `roleid` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user_role
-- ----------------------------
INSERT INTO `u_user_role` VALUES ('1', '1', '2');
