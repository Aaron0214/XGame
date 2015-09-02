/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50623
Source Host           : localhost:3306
Source Database       : financial

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2015-09-02 18:18:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for code_dict
-- ----------------------------
DROP TABLE IF EXISTS `code_dict`;
CREATE TABLE `code_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '0，收入；1，消费；2，余额；',
  `value` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code_dict
-- ----------------------------
INSERT INTO `code_dict` VALUES ('1', '0', '收入类型', null);
INSERT INTO `code_dict` VALUES ('2', '0', '收红包', '1');
INSERT INTO `code_dict` VALUES ('3', '0', '工资', '1');
INSERT INTO `code_dict` VALUES ('4', '0', '借/还', '1');
INSERT INTO `code_dict` VALUES ('5', '0', '父母给予', '1');
INSERT INTO `code_dict` VALUES ('6', '0', '其他', '1');
INSERT INTO `code_dict` VALUES ('7', '1', '消费类型', null);
INSERT INTO `code_dict` VALUES ('8', '1', '买东西', '7');
INSERT INTO `code_dict` VALUES ('9', '1', '发红包', '7');
INSERT INTO `code_dict` VALUES ('10', '1', '还钱', '7');
INSERT INTO `code_dict` VALUES ('11', '1', '孝敬父母', '7');
INSERT INTO `code_dict` VALUES ('12', '1', '其他', '7');
INSERT INTO `code_dict` VALUES ('13', '2', '存储类型', null);
INSERT INTO `code_dict` VALUES ('14', '2', '银行卡', '13');
INSERT INTO `code_dict` VALUES ('15', '2', '支付宝', '13');

-- ----------------------------
-- Table structure for financial
-- ----------------------------
DROP TABLE IF EXISTS `financial`;
CREATE TABLE `financial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `member` varchar(255) DEFAULT NULL,
  `type` int(255) DEFAULT NULL,
  `amount` decimal(20,6) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `operate` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of financial
-- ----------------------------
INSERT INTO `financial` VALUES ('1', 'F20150828000002', 'member', '15', '10000.000000', '2015-08-28 17:43:04', '2015-08-28 17:43:04', '收入', null);
INSERT INTO `financial` VALUES ('2', 'F20150902000005', 'member', '15', '10000.000000', '2015-09-02 11:10:32', '2015-08-28 17:43:04', '收入', null);

-- ----------------------------
-- Table structure for instock
-- ----------------------------
DROP TABLE IF EXISTS `instock`;
CREATE TABLE `instock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `member` varchar(255) DEFAULT NULL,
  `amount` decimal(20,6) DEFAULT NULL,
  `store_type` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `operate` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of instock
-- ----------------------------
INSERT INTO `instock` VALUES ('1', 'F20150826000001', 'B', 'A', '2.500000', null, '2015-08-26 15:43:48', '2015-08-26 15:43:48', null);
INSERT INTO `instock` VALUES ('2', 'F20150828000001', 'A', 'member', '2.500000', null, '2015-08-28 14:34:05', '2015-08-26 15:43:48', null);
INSERT INTO `instock` VALUES ('3', 'F20150828000003', 'A', 'member1', '2.500000', null, '2015-08-28 17:46:42', '2015-08-26 15:43:48', null);
INSERT INTO `instock` VALUES ('4', 'F20150828000005', '3', 'member', '10000.000000', null, '2015-08-28 17:53:40', '2015-08-28 17:53:40', null);
INSERT INTO `instock` VALUES ('5', 'F20150828000007', '3', 'member', '10000.000000', null, '2015-08-28 17:57:10', '2015-08-28 17:57:10', null);

-- ----------------------------
-- Table structure for outstock
-- ----------------------------
DROP TABLE IF EXISTS `outstock`;
CREATE TABLE `outstock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `member` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `amount` decimal(10,0) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `operate` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of outstock
-- ----------------------------
INSERT INTO `outstock` VALUES ('1', 'F20150828000007', 'member', 'A', '12', '2015-08-28 16:37:49', '2015-08-28 16:37:49', '买零食', null);

-- ----------------------------
-- Table structure for sn
-- ----------------------------
DROP TABLE IF EXISTS `sn`;
CREATE TABLE `sn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0，用户；1，收入；2，支出；3，余额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sn
-- ----------------------------
INSERT INTO `sn` VALUES ('1', '1', '0');
INSERT INTO `sn` VALUES ('2', '7', '1');
INSERT INTO `sn` VALUES ('3', '7', '2');
INSERT INTO `sn` VALUES ('4', '5', '3');
