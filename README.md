# springboot-sell
springboot-订餐系统

csdn : https://blog.csdn.net/dtttyc/article/details/87336831

SQL
/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : sell

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2019-02-15 21:06:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `detail_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(255) DEFAULT NULL,
  `product_icon` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_price` decimal(19,2) DEFAULT NULL,
  `product_quantity` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  UNIQUE KEY `detail_id` (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('1', '1', 'http://113223', '辣条', '2.20', '2', '1');
INSERT INTO `order_detail` VALUES ('2', '2', 'http://judy.icon', '辣条', '20.00', '1', '1');
INSERT INTO `order_detail` VALUES ('3', '1', 'http://113223', '皮蛋粥', '2.20', '2', null);
INSERT INTO `order_detail` VALUES ('5', '1', 'http://113223', '皮蛋粥', '2.20', '2', null);

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_id` int(32) NOT NULL AUTO_INCREMENT,
  `buyer_name` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态, 默认为新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态, 默认未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('1', '皮蛋肉', '18631687403', '廊坊', '123', '1000.00', '0', '0', '2019-01-14 15:39:32', '2019-02-09 19:03:10');
INSERT INTO `order_master` VALUES ('2', '辣条', '186335734564', '廊坊', '11', '21.00', '0', '0', '2019-01-14 20:39:01', '2019-02-09 19:03:10');
INSERT INTO `order_master` VALUES ('3', '皮蛋肉', '18631687403', '廊坊', '123', '1000.00', '0', '0', '2019-01-14 20:40:01', '2019-02-09 19:03:10');
INSERT INTO `order_master` VALUES ('4', '皮蛋肉', '18631687403', '廊坊', '123', '1000.00', '0', '0', '2019-02-02 15:13:34', '2019-02-09 19:03:10');
INSERT INTO `order_master` VALUES ('5', '皮蛋肉', '18631687403', '廊坊', '123', '1000.00', '0', '0', '2019-02-05 14:47:30', '2019-02-09 19:03:10');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  `category_type` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_id` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES ('3', '1', '3', '2019-02-12 17:04:40', '2019-02-09 23:32:52');
INSERT INTO `product_category` VALUES ('4', '口红', '4', '2019-02-12 17:04:43', '0000-00-00 00:00:00');
INSERT INTO `product_category` VALUES ('5', '泡泡糖', '5', '2019-02-12 17:04:44', '0000-00-00 00:00:00');
INSERT INTO `product_category` VALUES ('6', '皮蛋粥', '6', '2019-02-12 17:04:47', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `category_type` int(11) DEFAULT NULL,
  `product_description` varchar(255) DEFAULT NULL,
  `product_icon` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_price` decimal(19,2) DEFAULT NULL,
  `product_status` int(11) DEFAULT NULL,
  `product_stock` int(11) DEFAULT NULL,
  `version` int(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_id` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for seller_info
-- ----------------------------
DROP TABLE IF EXISTS `seller_info`;
CREATE TABLE `seller_info` (
  `id` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `openid` varchar(64) NOT NULL COMMENT '微信openid',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='卖家信息表';

-- ----------------------------
-- Records of seller_info
-- ----------------------------
