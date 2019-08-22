/*
SQLyog Professional v12.08 (64 bit)
MySQL - 5.6.40 : Database - security-example
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`security-example` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `security-example`;

/*Table structure for table `persistent_logins` */

DROP TABLE IF EXISTS `persistent_logins`;

CREATE TABLE `persistent_logins` (
  `username` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `series` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `persistent_logins` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone_no` char(11) COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号',
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `locked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '锁定',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '启用',
  PRIMARY KEY (`id`,`phone_no`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`phone_no`,`username`,`password`,`locked`,`enable`) values (1,'13200000000','张三','$2a$10$s3bbhyxx86tPj6FRMvMhaOcj3Vq18ux6ZbIuDLZsDyhNvpRGSbNa2',0,1),(2,'13211111111','李四','$2a$10$s3bbhyxx86tPj6FRMvMhaOcj3Vq18ux6ZbIuDLZsDyhNvpRGSbNa2',0,1),(3,'13222222222','王五','$2a$10$s3bbhyxx86tPj6FRMvMhaOcj3Vq18ux6ZbIuDLZsDyhNvpRGSbNa2',0,0),(4,'13233333333','赵六','$2a$10$s3bbhyxx86tPj6FRMvMhaOcj3Vq18ux6ZbIuDLZsDyhNvpRGSbNa2',0,1),(5,'13244444444','德玛西亚','$2a$10$Uta4039M93GmjutXaZ4zQupwu6aKjrcltYk/8yCIU/VgZI9U20JAW',0,1),(6,'','周。','',0,1);

/*Table structure for table `userconnection` */

DROP TABLE IF EXISTS `userconnection`;

CREATE TABLE `userconnection` (
  `userId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `providerId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `providerUserId` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `rank` int(11) NOT NULL,
  `displayName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `profileUrl` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `imageUrl` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `accessToken` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `secret` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `refreshToken` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `UserConnectionRank` (`userId`,`providerId`,`rank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `userconnection` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
