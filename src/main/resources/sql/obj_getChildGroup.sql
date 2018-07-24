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

/* Function  structure for function  `getChildGroup` */

/*!50003 DROP FUNCTION IF EXISTS `getChildGroup` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`192.168.3.138` FUNCTION `getChildGroup`(gid INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
    decLARE tmp varchar(4000);
    declare tmpChild varchar(4000);
    set tmp='';
    set tmpChild =cast(gid as char);
    while tmpChild is not null
    do
    set tmp=concat(tmp,',',tmpChild);
    select group_concat(childrenGroupId) into tmpChild from grouprel where find_in_set(groupId,tmpChild)>0;
    end while;
    return tmp;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
