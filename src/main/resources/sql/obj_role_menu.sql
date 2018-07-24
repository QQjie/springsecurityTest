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

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL,
  `menuId` int(11) NOT NULL,
  `status` int(1) DEFAULT '1',
  PRIMARY KEY (`roleId`,`menuId`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `role_menu` */

insert  into `role_menu`(`id`,`roleId`,`menuId`,`status`) values 
(1,1,1,1),
(2,1,2,1),
(3,1,3,1),
(7,1,6,1),
(6,1,10,1),
(4,2,1,-2),
(14,2,2,1),
(15,2,3,1),
(16,2,4,1),
(5,3,1,-2),
(17,3,2,1),
(18,3,3,1),
(19,3,4,1),
(20,3,5,1),
(13,4,0,1),
(8,4,1,-2),
(9,4,2,-2),
(10,4,3,-2),
(11,4,4,-2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
