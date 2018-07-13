/*
 Navicat Premium Data Transfer

 Source Server         : luffy
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : chuanyunbang_wechat_data

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 07/13/2018 16:34:30 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `wechat_data`
-- ----------------------------
DROP TABLE IF EXISTS `wechat_data1`;
CREATE TABLE `wechat_data1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tel_nums` varchar(256) DEFAULT NULL,
  `identity` tinyint(1) DEFAULT NULL,
  `msg` text,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1851 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
