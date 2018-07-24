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

/*Table structure for table `area_info` */

DROP TABLE IF EXISTS `area_info`;

CREATE TABLE `area_info` (
  `areaId` int(11) NOT NULL AUTO_INCREMENT,
  `areaName` varchar(50) DEFAULT NULL,
  `parentAreaId` int(11) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`areaId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `area_info` */

insert  into `area_info`(`areaId`,`areaName`,`parentAreaId`,`createTime`,`modifyTime`) values 
(1,'4',0,'2018-07-04 09:41:16','2018-07-09 14:59:16'),
(2,'天津',1,'2018-07-04 09:41:43','0000-00-00 00:00:00'),
(3,'南京',1,'2018-07-04 09:42:00','0000-00-00 00:00:00'),
(4,'湖北',1,'2018-07-04 09:42:17','0000-00-00 00:00:00'),
(5,'长沙',1,'2018-07-04 09:42:28','0000-00-00 00:00:00'),
(6,'岳麓',5,'2018-07-04 09:42:58','0000-00-00 00:00:00'),
(7,'雨花',5,'2018-07-04 09:43:37','0000-00-00 00:00:00'),
(8,'望城',6,'2018-07-04 09:43:39','0000-00-00 00:00:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
