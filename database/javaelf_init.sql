/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.225
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 192.168.1.225:3306
 Source Schema         : javaelf

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 05/11/2020 14:12:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for interface_msg
-- ----------------------------
DROP TABLE IF EXISTS `interface_msg`;
CREATE TABLE `interface_msg`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of interface_msg
-- ----------------------------
INSERT INTO `interface_msg` VALUES (1, 'post', 'https://qyapi.weixin.qq.com/cgi-bin/gettoken');
INSERT INTO `interface_msg` VALUES (2, 'post', 'http://127.0.0.1:8091');

-- ----------------------------
-- Table structure for test_case
-- ----------------------------
DROP TABLE IF EXISTS `test_case`;
CREATE TABLE `test_case`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `case_status` int(11) NULL DEFAULT NULL,
  `interfacemsg_id` bigint(20) NULL DEFAULT NULL,
  `desc_case` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `headers_parames` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `body_parames` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `expect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `actual` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_case
-- ----------------------------
INSERT INTO `test_case` VALUES (1, 1, 1, 'corpid/corpsecret参数均为空', '{\"Content-Type\":\"application/json\"}', '{\"corpid\":\"\",\"corpsecret\":\"\"}', '\" \"\"errcode\"\": 41004,', NULL, NULL);
INSERT INTO `test_case` VALUES (2, 1, 1, 'corpid传、corpsecret为空', '{\"Content-Type\":\"application/json\"}', '{\"corpid\":\"${corpidTrue}\",\"corpsecret\":\"\"}', ' \"\"errmsg\"\": \"\"corpsecret missing\"\"\"', NULL, NULL);
INSERT INTO `test_case` VALUES (3, 1, 1, 'corpid为空、corpsecret传', '{\"Content-Type\":\"application/json\"}', '{\"corpid\":\"\",\"corpsecret\":\"${corpsecretTrue}\"}', '\"    \"\"errcode\"\": 41004,', NULL, NULL);
INSERT INTO `test_case` VALUES (4, 1, 1, 'corpid正确传、corpsecret错误传', '{\"Content-Type\":\"application/json\"}', '{\"corpid\":\"${corpidTrue}\",\"corpsecret\":\"${corpidFalse}\"}', '    \"\"errmsg\"\": \"\"corpsecret missing\"\"', NULL, NULL);
INSERT INTO `test_case` VALUES (5, 1, 1, 'corpid错误传、corpsecret正确传', '{\"Content-Type\":\"application/json\"}', '{\"corpid\":\"${corpidFalse}\",\"corpsecret\":\"${corpsecretTrue}\"}', '}\"', NULL, NULL);
INSERT INTO `test_case` VALUES (6, 1, 1, 'corpid、corpsecret均错误传', '{\"Content-Type\":\"application/json\"}', '{\"corpid\":\"${corpidFalse}\",\"corpsecret\":\"${corpidFalse}\"}', '\"    \"\"errcode\"\": 41002,', NULL, NULL);
INSERT INTO `test_case` VALUES (7, 1, 1, 'corpid、corpsecret均正确传', '{\"Content-Type\":\"application/json\"}', '{\"corpid\":\"${corpidTrue}\",\"corpsecret\":\"${corpsecretTrue}\"}', '    \"\"errmsg\"\": \"\"corpid missing\"\"\"', NULL, NULL);

-- ----------------------------
-- Table structure for variable_substitution
-- ----------------------------
DROP TABLE IF EXISTS `variable_substitution`;
CREATE TABLE `variable_substitution`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `reflect_calss` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `reflect_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `reflect_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of variable_substitution
-- ----------------------------
INSERT INTO `variable_substitution` VALUES (1, '${corpidTrue}', '', 'com.javaelf.dataForger.TestDataFactory', 'getCorpidTrue', 'ww27d6f876d80ceec6', '有效corpid');
INSERT INTO `variable_substitution` VALUES (2, '${corpidFalse}', '', 'com.javaelf.dataForger.TestDataFactory', 'getCorpidFalse', 'ww27d6f876d80ceec', '无效corpid');
INSERT INTO `variable_substitution` VALUES (3, '${corpsecretTrue}', '', 'com.javaelf.dataForger.TestDataFactory', 'getCorpsecretTrue', 'elaiRGcWe4JLESzypgnabA3Tvpj8kLiV6zLEqa4Nbw8', '有效corpsecret');
INSERT INTO `variable_substitution` VALUES (4, '${corpsecretFalse}', '', 'com.javaelf.dataForger.TestDataFactory', 'getCorpsecretFalse', 'elaiRGcWe4JLESzypgnabA3Tvpj8kLiV6zLEqa4Nbw', '无效corpsecret');

SET FOREIGN_KEY_CHECKS = 1;
