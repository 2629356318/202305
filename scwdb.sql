/*
 Navicat Premium Data Transfer

 Source Server         : meng
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : scwdb

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 12/04/2022 16:53:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_cert
-- ----------------------------
DROP TABLE IF EXISTS `t_cert`;
CREATE TABLE `t_cert`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '资质主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资质名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资质表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_cert
-- ----------------------------
INSERT INTO `t_cert` VALUES (1, '营业执照副本');
INSERT INTO `t_cert` VALUES (2, '税务登记证');
INSERT INTO `t_cert` VALUES (3, '组织机构代码证');
INSERT INTO `t_cert` VALUES (4, '单位登记证件');
INSERT INTO `t_cert` VALUES (5, '法定代表人证件');
INSERT INTO `t_cert` VALUES (6, '经营者证件');
INSERT INTO `t_cert` VALUES (7, '手执身份证照片');

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `loginacct` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `userpswd` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电子邮箱',
  `authstatus` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '实名认证状态 0 - 未实名认证， 1 - 实名认证申请中， 2 - 已实名认证',
  `usertype` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 用户类型: 0 - 个人， 1 - 企业',
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实名称',
  `cardnum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `accttype` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户类型: 0 - 企业， 1 - 个体， 2 - 个人， 3 - 政府',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member` VALUES (3, '13721281588', '$2a$10$rqKGHW2ESFBI7AT3IdWmz.c6QtE5e6jdFGjoeBO1bKy0jyosGJ7yC', '13521281544', 'hk109@126.com', '0', '1', '孙老师', '178027197909100618', NULL);
INSERT INTO `t_member` VALUES (4, '18210891544', '$2a$10$BSP0wZJKnFqor49a0d1pTujQ9AeAdJ5fyys6ntuyeaCv.WPAv.iZi', '18210891544', 'hk109@126.com', '0', '0', '王老板', NULL, '2');
INSERT INTO `t_member` VALUES (6, '13521281599', '$2a$10$WwiGvC0j0QBwEHO54ui20OAfmjFKt4J1PbC40KmqqH6ztI9hH6RJC', '13521281544', 'hk109@126.com', '0', '0', '李经理', NULL, '2');
INSERT INTO `t_member` VALUES (7, '13521281544', '$2a$10$t46/b80ea2UVKp6eVxRKQuaXGPX7Rn5QR/yxhXXi.xBLsEyKwcxgO', '13521281544', 'hk109@1111.com', '0', '0', '赵总', NULL, '2');
INSERT INTO `t_member` VALUES (8, '18228427462', '$2a$10$ytOqXfXt4uBxJ1.VVHuHkOM7cCfRauOyR8k88ePLu0hJQOSOyIqGG', '18228427462', '1111@111.com', '0', '0', '邓总', NULL, '2');

-- ----------------------------
-- Table structure for t_member_address
-- ----------------------------
DROP TABLE IF EXISTS `t_member_address`;
CREATE TABLE `t_member_address`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `memberid` int NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_13`(`memberid`) USING BTREE,
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`memberid`) REFERENCES `t_member` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收货地址表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_member_address
-- ----------------------------
INSERT INTO `t_member_address` VALUES (1, 8, '四川文理学院');
INSERT INTO `t_member_address` VALUES (2, 8, '四川简阳');

-- ----------------------------
-- Table structure for t_member_cert
-- ----------------------------
DROP TABLE IF EXISTS `t_member_cert`;
CREATE TABLE `t_member_cert`  (
  `id` int NOT NULL,
  `memberid` int NULL DEFAULT NULL COMMENT '会员ID',
  `certid` int NULL DEFAULT NULL COMMENT '资质ID',
  `iconpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_14`(`memberid`) USING BTREE,
  INDEX `FK_Reference_15`(`certid`) USING BTREE,
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`memberid`) REFERENCES `t_member` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`certid`) REFERENCES `t_cert` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员资质表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_member_cert
-- ----------------------------

-- ----------------------------
-- Table structure for t_member_project_follow
-- ----------------------------
DROP TABLE IF EXISTS `t_member_project_follow`;
CREATE TABLE `t_member_project_follow`  (
  `id` int NOT NULL,
  `projectid` int NULL DEFAULT NULL,
  `memberid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_11`(`projectid`) USING BTREE,
  INDEX `FK_Reference_12`(`memberid`) USING BTREE,
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`memberid`) REFERENCES `t_member` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目关注表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_member_project_follow
-- ----------------------------
INSERT INTO `t_member_project_follow` VALUES (1, 6, 3);

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `memberid` int NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `senddate` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_message
-- ----------------------------

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `memberid` int NULL DEFAULT NULL COMMENT '会员ID',
  `projectid` int NULL DEFAULT NULL COMMENT '项目ID',
  `returnid` int NULL DEFAULT NULL COMMENT '回报ID',
  `ordernum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `createdate` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `money` int NULL DEFAULT NULL COMMENT '支持金额',
  `rtncount` int NULL DEFAULT NULL COMMENT '回报数量',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0 - 待付款， 1 - 交易完成， 9 - 交易关闭',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `invoice` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0 - 不开发票， 1 - 开发票',
  `invoictitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票抬头',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_16`(`returnid`) USING BTREE,
  INDEX `FK_Reference_17`(`projectid`) USING BTREE,
  INDEX `FK_Reference_18`(`memberid`) USING BTREE,
  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`returnid`) REFERENCES `t_return` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_17` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_18` FOREIGN KEY (`memberid`) REFERENCES `t_member` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (1, 3, 6, 5, 'fced847e015b43c4834d7d4528ab167c', '2020-01-11 13:42:13', 102, 1, '0', '北京市五方桥', '1', '很好啊啊', '高回报');
INSERT INTO `t_order` VALUES (2, 3, 6, NULL, '7e5cf3497d554209a01f6c14fe856c9a', '2020-02-03 14:08:49', 102, 1, '0', '河北衡水', '0', '', '');
INSERT INTO `t_order` VALUES (3, 3, 6, NULL, '73fd059298ca4ca1baa6c3438081e902', '2020-02-03 14:08:49', 102, 1, '0', '河北衡水', '0', '', '');
INSERT INTO `t_order` VALUES (4, 3, 6, NULL, '953626a4360943f38f69f80c601945b4', '2020-02-03 14:10:23', 102, 1, '0', '河北衡水', '0', '', '');
INSERT INTO `t_order` VALUES (5, 3, 6, NULL, '35b2189e540f4967a60120ba87a2ab8b', '2020-02-03 14:13:41', 102, 1, '0', '河北衡水', '0', '', '');
INSERT INTO `t_order` VALUES (6, 3, 6, NULL, '6b65956005bd477698381cbf0c6efcbc', '2020-02-03 14:17:33', 102, 1, '0', '河北衡水', '0', '', '');
INSERT INTO `t_order` VALUES (7, 3, 6, NULL, 'ecf3658bc5f64d4b8182a150c4c57694', '2020-02-05 15:51:37', 102, 1, '0', '河北衡水', '0', '', '11');
INSERT INTO `t_order` VALUES (8, 3, 6, NULL, 'ecd372eece6b4464b1a2ed7e203ea9e5', '2020-02-05 15:51:37', 102, 1, '0', '河北衡水', '0', '', '11');
INSERT INTO `t_order` VALUES (9, 8, 8, 9, 'f1cc22f67e15437eb011d454766cea7d', '2021-12-23 20:48:16', 60, 0, '0', '四川简阳', NULL, 'string', 'string');
INSERT INTO `t_order` VALUES (10, 3, 6, NULL, 'b20f27dfc68242398c435b5481dccd86', '2020-02-05 15:59:15', 102, 1, '0', '河北衡水', '0', '', 'r45');
INSERT INTO `t_order` VALUES (11, 3, 6, NULL, '7421dfa72bb1453494225dbcf50def31', '2020-02-05 16:08:47', 102, 1, '0', '河北衡水', '1', '要发票', '会被备注');
INSERT INTO `t_order` VALUES (12, 3, 6, NULL, '94fa7cab2bd44facb06a8ff4477b51b4', '2020-02-05 16:21:32', 102, 1, '0', '河北衡水', '0', '', '3ee');
INSERT INTO `t_order` VALUES (13, 3, 6, NULL, 'd1ee23f079414f30be2ab8e3421dfa85', '2020-02-05 16:23:13', 102, 1, '0', NULL, '1', '要发票', '3333rrf');
INSERT INTO `t_order` VALUES (14, 6, 7, 5, 'e0409421ab8a4512b64b8d687cbd808f', '2020-06-21 14:40:53', 61, 10, '0', '河北石家庄红旗大街100', '1', '北京市中公教育科技股份有限公司', '备注,孙老师推介');
INSERT INTO `t_order` VALUES (15, 7, 7, 6, '5834ba27e6c44258baa54ae474357c3b', '2020-06-26 16:09:28', 13, 2, '0', 'option2', '0', '', '');
INSERT INTO `t_order` VALUES (16, 6, 7, 6, '6777a1c2b7d643d380c55a56ac530492', '2020-06-26 16:15:18', 19, 3, '0', '测试地址', '0', '', '');
INSERT INTO `t_order` VALUES (17, 8, 8, 9, 'f70dc37019f44e2697133153a0083eee', '2021-12-24 19:47:09', 1160, 2, '0', '四川简阳', NULL, '', '');

-- ----------------------------
-- Table structure for t_param
-- ----------------------------
DROP TABLE IF EXISTS `t_param`;
CREATE TABLE `t_param`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `val` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_param
-- ----------------------------

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目简介',
  `money` bigint NULL DEFAULT NULL COMMENT '筹资金额',
  `day` int NULL DEFAULT NULL COMMENT '筹资天数',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0 - 即将开始， 1 - 众筹中， 2 - 众筹成功， 3 - 众筹失败',
  `deploydate` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布日期',
  `supportmoney` bigint NULL DEFAULT NULL COMMENT '支持金额',
  `supporter` int NULL DEFAULT NULL COMMENT '支持者数量',
  `completion` int NULL DEFAULT NULL COMMENT '完成度',
  `memberid` int NULL DEFAULT NULL COMMENT '发起人ID',
  `createdate` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建日期',
  `follower` int NULL DEFAULT NULL COMMENT '关注者数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_project
-- ----------------------------
INSERT INTO `t_project` VALUES (6, '第1个项目', '非常棒的项目', 50000, 88, '5', NULL, 10000, 189, 20, 3, '2020-01-11 13:08:57', 88);
INSERT INTO `t_project` VALUES (7, '测试项目', '这是一个好项目', 100000, 100, '5', NULL, 30000, 68, 30, 6, '2020-06-16 06:50:04', 100);
INSERT INTO `t_project` VALUES (8, '鉴赏', '用心去看', 2000000, 20, '1', NULL, NULL, NULL, NULL, 8, '2021-12-23 18:54:31', NULL);

-- ----------------------------
-- Table structure for t_project_images
-- ----------------------------
DROP TABLE IF EXISTS `t_project_images`;
CREATE TABLE `t_project_images`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `projectid` int NULL DEFAULT NULL,
  `imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imgtype` tinyint NULL DEFAULT NULL COMMENT '0-头部图片 1-详情图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_project_images
-- ----------------------------
INSERT INTO `t_project_images` VALUES (13, 6, 'http://xue.ujiuye.com/uploads_it/2005/QuanGuo/F5Q1275748985AOH.jpg', 0);
INSERT INTO `t_project_images` VALUES (14, 6, 'http://www.dongyimai.com/data/afficheimg/20200410tkhqmy.jpg', 1);
INSERT INTO `t_project_images` VALUES (15, 6, 'http://www.dongyimai.com/data/afficheimg/20190515rihdju.jpg', 1);
INSERT INTO `t_project_images` VALUES (16, 7, 'http://xue.ujiuye.com/uploads_it/1912/QuanGuo/ECA932369863ODK9.jpg', 0);
INSERT INTO `t_project_images` VALUES (17, 7, 'http://www.ujiuye.com/uploadfile/2020/0610/20200610060037292.jpg', 1);
INSERT INTO `t_project_images` VALUES (18, 7, 'http://www.ujiuye.com/uploadfile/2019/0809/20190809052021246.jpg', 1);
INSERT INTO `t_project_images` VALUES (19, 8, 'http://bu-guai.oss-cn-chengdu.aliyuncs.com/pic/xue.png', 0);
INSERT INTO `t_project_images` VALUES (20, 8, 'http://bu-guai.oss-cn-chengdu.aliyuncs.com/pic/xue.png', 1);
INSERT INTO `t_project_images` VALUES (21, 8, 'http://bu-guai.oss-cn-chengdu.aliyuncs.com/pic/xue.png', 1);
INSERT INTO `t_project_images` VALUES (22, 8, 'http://bu-guai.oss-cn-chengdu.aliyuncs.com/pic/xue.png', 1);

-- ----------------------------
-- Table structure for t_project_initiator
-- ----------------------------
DROP TABLE IF EXISTS `t_project_initiator`;
CREATE TABLE `t_project_initiator`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `selfintroduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detailselfintroduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telphone` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotline` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `projectid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_project_initiator
-- ----------------------------

-- ----------------------------
-- Table structure for t_project_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_project_tag`;
CREATE TABLE `t_project_tag`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `projectid` int NULL DEFAULT NULL,
  `tagid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_7`(`projectid`) USING BTREE,
  INDEX `FK_Reference_8`(`tagid`) USING BTREE,
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`tagid`) REFERENCES `t_tag` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目标签关系表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_project_tag
-- ----------------------------
INSERT INTO `t_project_tag` VALUES (8, 6, 1);
INSERT INTO `t_project_tag` VALUES (9, 6, 2);
INSERT INTO `t_project_tag` VALUES (10, NULL, 1);
INSERT INTO `t_project_tag` VALUES (11, NULL, 2);
INSERT INTO `t_project_tag` VALUES (12, 8, 2);
INSERT INTO `t_project_tag` VALUES (13, 8, 3);

-- ----------------------------
-- Table structure for t_project_type
-- ----------------------------
DROP TABLE IF EXISTS `t_project_type`;
CREATE TABLE `t_project_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `projectid` int NULL DEFAULT NULL,
  `typeid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_5`(`projectid`) USING BTREE,
  INDEX `FK_Reference_6`(`typeid`) USING BTREE,
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`typeid`) REFERENCES `t_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目分类关系表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_project_type
-- ----------------------------
INSERT INTO `t_project_type` VALUES (8, 6, 2);
INSERT INTO `t_project_type` VALUES (9, 6, 1);
INSERT INTO `t_project_type` VALUES (10, NULL, 1);
INSERT INTO `t_project_type` VALUES (11, NULL, 2);
INSERT INTO `t_project_type` VALUES (12, 8, 2);
INSERT INTO `t_project_type` VALUES (13, 8, 3);

-- ----------------------------
-- Table structure for t_return
-- ----------------------------
DROP TABLE IF EXISTS `t_return`;
CREATE TABLE `t_return`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `projectid` int NULL DEFAULT NULL COMMENT '项目ID',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0 - 实物回报， 1 虚拟物品回报',
  `supportmoney` int NULL DEFAULT NULL COMMENT '支持金额',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回报内容',
  `count` int NULL DEFAULT NULL COMMENT '0 为不限回报数量',
  `signalpurchase` int NULL DEFAULT NULL COMMENT '单笔限购',
  `purchase` int NULL DEFAULT NULL COMMENT '限购数量',
  `freight` int NULL DEFAULT NULL COMMENT '运费',
  `invoice` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0 - 不开发票， 1 - 开发票',
  `rtndate` int NULL DEFAULT NULL COMMENT '回报时间,众筹成功后多少天进行回报',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '回报表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_return
-- ----------------------------
INSERT INTO `t_return` VALUES (5, 6, NULL, 100, '回报率第一', 1, 10, 1, 2, NULL, 100);
INSERT INTO `t_return` VALUES (6, 7, NULL, 6, 'test001', NULL, 5, 3, 1, NULL, 4);
INSERT INTO `t_return` VALUES (7, 8, NULL, 550, '鉴赏', 1, 30, 10, 60, NULL, 20);
INSERT INTO `t_return` VALUES (8, 8, NULL, 550, '观看', 1, 30, 10, 60, NULL, 20);
INSERT INTO `t_return` VALUES (9, 8, NULL, 550, '心情', 1, 30, 10, 60, NULL, 20);

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `pid` int NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目标签' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES (1, NULL, '颜色');
INSERT INTO `t_tag` VALUES (2, 1, '红色');
INSERT INTO `t_tag` VALUES (3, 1, '白色');
INSERT INTO `t_tag` VALUES (4, NULL, '高度');
INSERT INTO `t_tag` VALUES (5, 4, '1.2米');
INSERT INTO `t_tag` VALUES (6, 4, '1.5米');

-- ----------------------------
-- Table structure for t_transaction
-- ----------------------------
DROP TABLE IF EXISTS `t_transaction`;
CREATE TABLE `t_transaction`  (
  `id` int NOT NULL,
  `ordersn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paysn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `memberid` int NULL DEFAULT NULL,
  `amount` float NULL DEFAULT NULL,
  `paystate` tinyint NULL DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `completiontime` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createat` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `updateat` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_transaction
-- ----------------------------

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目分类' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (1, '农业类', NULL);
INSERT INTO `t_type` VALUES (2, '科技类', NULL);
INSERT INTO `t_type` VALUES (3, '设计类', NULL);
INSERT INTO `t_type` VALUES (4, '公益类', NULL);

SET FOREIGN_KEY_CHECKS = 1;
