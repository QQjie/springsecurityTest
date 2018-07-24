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

/*Table structure for table `user_group` */

DROP TABLE IF EXISTS `user_group`;

CREATE TABLE `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `userId` int(11) DEFAULT NULL COMMENT '用户ID',
  `groupId` int(11) DEFAULT NULL COMMENT '所属用户组Id',
  `status` int(1) DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user_group` */

insert  into `user_group`(`id`,`userId`,`groupId`,`status`) values 
(1,1,1,1),
(2,2,2,1),
(3,2,3,1),
(4,2,4,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
