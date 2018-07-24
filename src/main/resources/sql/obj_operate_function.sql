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

/*Table structure for table `operate_function` */

DROP TABLE IF EXISTS `operate_function`;

CREATE TABLE `operate_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作id',
  `operationName` varchar(50) DEFAULT NULL COMMENT '操作名称',
  `value` int(2) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL COMMENT 'id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `operate_function` */

insert  into `operate_function`(`id`,`operationName`,`value`,`userId`) values 
(1,'GET',1,NULL),
(2,'POST',2,NULL),
(3,'PUT',4,NULL),
(4,'DELETE',8,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
