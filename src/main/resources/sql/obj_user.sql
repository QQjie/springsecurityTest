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

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(2) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`status`) values 
(1,'hj','0c84b7b48f309b59b2eb54c441110deb',1),
(18,'1','bd9d0ab61cfcd04373ca541f44cfecee',1),
(19,'2','bd9d0ab61cfcd04373ca541f44cfecee',1),
(20,'3','bd9d0ab61cfcd04373ca541f44cfecee',1),
(21,'1','bd9d0ab61cfcd04373ca541f44cfecee',1),
(22,'2','bd9d0ab61cfcd04373ca541f44cfecee',1),
(23,'3','bd9d0ab61cfcd04373ca541f44cfecee',1),
(24,'3','bd9d0ab61cfcd04373ca541f44cfecee',1),
(25,'3','bd9d0ab61cfcd04373ca541f44cfecee',1),
(26,'3','bd9d0ab61cfcd04373ca541f44cfecee',1),
(27,'3','bd9d0ab61cfcd04373ca541f44cfecee',1),
(28,'22','bd9d0ab61cfcd04373ca541f44cfecee',1),
(29,'A','bd9d0ab61cfcd04373ca541f44cfecee',1),
(30,'A','bd9d0ab61cfcd04373ca541f44cfecee',1),
(31,'A','bd9d0ab61cfcd04373ca541f44cfecee',1),
(32,'A','bd9d0ab61cfcd04373ca541f44cfecee',1),
(33,'b','bd9d0ab61cfcd04373ca541f44cfecee',1),
(34,'b','bd9d0ab61cfcd04373ca541f44cfecee',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
