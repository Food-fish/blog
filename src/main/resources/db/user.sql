/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 127.0.0.1:3306
 Source Schema         : user

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 13/07/2018 18:02:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `ID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `PID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点id',
  `METHOD` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方式(get set)',
  `CREATEBY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATEDATE` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATEBY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `UPDATEDATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `DELFLAG` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'ROLE_HOME', 'home', '/getAllUser', '', 'ALL', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES ('2', 'ROLE_ADMIN', '10086', '/home', NULL, 'ALL', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for permission_role
-- ----------------------------
DROP TABLE IF EXISTS `permission_role`;
CREATE TABLE `permission_role`  (
  `ID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `ROLE_ID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限主键',
  `PERMISSION_ID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户权限中间表主键',
  `CREATEBY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATEDATE` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATEBY` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `UPDATEDATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `DELFLAG` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission_role
-- ----------------------------
INSERT INTO `permission_role` VALUES ('1', '1', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `permission_role` VALUES ('2', '1', '2', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `permission_role` VALUES ('3', '1', '3', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `ID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `CREATEBY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATEDATE` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATEBY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `UPDATEDATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `DELFLAG` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `role` VALUES ('2', 'ROLE_USER', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user`  (
  `ID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `USER_ID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户主键',
  `ROLE_ID` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限主键',
  `CREATEBY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATEDATE` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATEBY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `UPDATEDATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `DELFLAG` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES ('1', '1', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `role_user` VALUES ('2', '1', '2', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `UUID` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'UID',
  `UID_A` smallint(6) UNSIGNED ZEROFILL NULL DEFAULT 000000 COMMENT 'UID一列',
  `UID_B` smallint(6) UNSIGNED ZEROFILL NULL DEFAULT 000000 COMMENT 'UID二列',
  `UID_C` smallint(6) UNSIGNED ZEROFILL NULL DEFAULT 000000 COMMENT 'UID三列',
  `PASSWORD` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `NICKNAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `EMAIL` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `IMG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `STATUS` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `CREATEBY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATEDATE` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATEBY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `UPDATEDATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `DELFLAG` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `uuid_unique`(`UUID`) USING BTREE COMMENT '快速查询UID',
  UNIQUE INDEX `uuid_uid`(`UID_A`, `UID_B`, `UID_C`) USING BTREE COMMENT 'UUID组合查询',
  UNIQUE INDEX `uuid_login`(`UUID`, `PASSWORD`) USING BTREE COMMENT '登录查询',
  UNIQUE INDEX `uuid_email`(`EMAIL`) USING BTREE COMMENT '邮箱唯一',
  INDEX `uuid_status`(`STATUS`) USING BTREE COMMENT '状态'
) ENGINE = InnoDB AUTO_INCREMENT = 10087 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 10086, 000000, 000000, 000000, '123456', '李俊', '3838438@38.com', NULL, '', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user_friends
-- ----------------------------
DROP TABLE IF EXISTS `user_friends`;
CREATE TABLE `user_friends`  (
  `ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `FSRCUUID` bigint(18) NULL DEFAULT NULL COMMENT '主用户',
  `FDESUUID` bigint(18) NULL DEFAULT NULL COMMENT '被添加用户',
  `FWEIGHT` int(11) NULL DEFAULT NULL COMMENT '关注状态(0:单向;1:双向)',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `user_src`(`FSRCUUID`) USING BTREE COMMENT '主用户查询索引',
  INDEX `user_des`(`FDESUUID`) USING BTREE COMMENT '被关注用户查询索引'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
