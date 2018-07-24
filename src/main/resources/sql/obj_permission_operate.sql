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

/*Table structure for table `permission_operate` */

DROP TABLE IF EXISTS `permission_operate`;

CREATE TABLE `permission_operate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permId` int(11) NOT NULL,
  `operId` int(11) NOT NULL,
  `status` int(1) DEFAULT '1',
  PRIMARY KEY (`permId`,`operId`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

/*Data for the table `permission_operate` */

insert  into `permission_operate`(`id`,`permId`,`operId`,`status`) values 
(19,0,0,1),
(1,1,1,1),
(2,1,2,1),
(3,1,3,1),
(4,1,4,1),
(5,2,1,1),
(29,2,3,1),
(15,2,4,1),
(8,3,2,1),
(6,4,1,1),
(9,5,2,1),
(10,5,4,1),
(7,5,5,-2),
(11,5,8,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
