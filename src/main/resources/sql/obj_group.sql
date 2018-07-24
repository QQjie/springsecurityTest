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

/*Table structure for table `group` */

DROP TABLE IF EXISTS `group`;

CREATE TABLE `group` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '用户组id',
  `groupname` varchar(50) NOT NULL COMMENT '用户组名称',
  `belongarea` varchar(50) NOT NULL COMMENT '所属地区',
  `status` int(1) DEFAULT NULL COMMENT '状态信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `group` */

insert  into `group`(`id`,`groupname`,`belongarea`,`status`) values 
(1,'北京组','北京分院',1),
(2,'长沙组','长沙分院',1),
(3,'南京组','南京分院',1),
(4,'天津组','天津分院',1),
(5,'湖北组','湖北分院',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
