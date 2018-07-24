/*
SQLyog Job Agent v12.2.6 (64 bit) Copyright(c) Webyog Inc. All Rights Reserved.


MySQL - 5.6.21-log : Database - youdao
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`youdao` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `youdao`;

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `userName` varchar(100) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createUser` int(11) DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  `status` int(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`loginName`,`password`,`userName`,`email`,`phone`,`createTime`,`updateTime`,`createUser`,`lastLoginTime`,`status`) values 
(1,'admin','0c84b7b48f309b59b2eb54c441110deb','name','111@qq.com','15572222222','2018-07-12 16:03:21','2018-07-13 16:00:49',1,NULL,1),
(2,NULL,'4d3b6c8fbbd86436a3d9fd173d09ba88','user1',NULL,NULL,'2018-07-18 10:42:29','2018-07-18 10:42:29',NULL,NULL,1),
(3,NULL,'4d3b6c8fbbd86436a3d9fd173d09ba88','user2',NULL,NULL,'2018-07-18 10:43:37','2018-07-18 10:43:37',NULL,NULL,1),
(4,NULL,'360a72f91f713e795492795367f52930','1',NULL,NULL,'2018-07-18 10:46:31','2018-07-18 16:52:11',NULL,NULL,1),
(5,NULL,'1f3c2f586171e0df1d23c05777e0b52c','5',NULL,NULL,'2018-07-18 10:54:04','2018-07-20 15:45:32',NULL,NULL,-2),
(6,NULL,'4a40b0102cf7f9b72b61f8c980a068c4','2',NULL,NULL,'2018-07-18 11:04:19','2018-07-18 11:04:19',NULL,NULL,1),
(7,NULL,'360a72f91f713e795492795367f52930','1',NULL,NULL,'2018-07-18 11:11:26','2018-07-18 11:11:26',NULL,NULL,1),
(8,NULL,'360a72f91f713e795492795367f52930','1',NULL,NULL,'2018-07-18 11:23:55','2018-07-18 11:23:55',NULL,NULL,1),
(9,NULL,'360a72f91f713e795492795367f52930','1',NULL,NULL,'2018-07-18 11:25:38','2018-07-18 11:25:38',NULL,NULL,1),
(10,NULL,'566836c9fdadcad6631d2a7b7d0c3f3d','12',NULL,NULL,'2018-07-23 14:24:17','2018-07-23 14:25:48',NULL,NULL,-2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;