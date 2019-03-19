/*
Navicat MySQL Data Transfer

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-03-19 23:21:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `uid` bigint(20) NOT NULL,
  `apply_uid` bigint(20) DEFAULT NULL COMMENT '申请人',
  `apply_time` date DEFAULT NULL,
  `apply_action` int(4) DEFAULT NULL COMMENT '申请好友uid',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for friend_group
-- ----------------------------
DROP TABLE IF EXISTS `friend_group`;
CREATE TABLE `friend_group` (
  `group_id` bigint(20) NOT NULL,
  `create_uid` bigint(20) NOT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `group_order` int(11) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `uid` bigint(20) NOT NULL,
  `friend_id` bigint(20) NOT NULL,
  `friend_type` int(11) NOT NULL,
  `time` date DEFAULT NULL COMMENT '添加时间',
  `group_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL,
  `account` varchar(100) DEFAULT NULL,
  `phone_num` varchar(20) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `sex` int(2) DEFAULT NULL,
  `head_url` varchar(200) DEFAULT NULL,
  `pass_word` varchar(200) DEFAULT NULL,
  `nick_name` varchar(100) DEFAULT NULL,
  `signature` varchar(200) DEFAULT NULL COMMENT '个性签名',
  `qq` varchar(20) DEFAULT NULL,
  `wechat` varchar(50) DEFAULT NULL,
  `alipay` varchar(100) DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `registration` date DEFAULT NULL COMMENT '注册时间',
  `h_address` varchar(200) DEFAULT NULL COMMENT '家庭地址',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
