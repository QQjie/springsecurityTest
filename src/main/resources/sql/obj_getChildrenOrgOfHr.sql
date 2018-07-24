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

/* Function  structure for function  `getChildrenOrgOfHr` */

/*!50003 DROP FUNCTION IF EXISTS `getChildrenOrgOfHr` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`192.168.3.138` FUNCTION `getChildrenOrgOfHr`(orgid INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE oTemp VARCHAR(4000);
DECLARE oTempChild VARCHAR(4000);
SET oTemp = '';
SET oTempChild = CAST(orgid AS CHAR);
WHILE oTempChild IS NOT NULL
DO
SET oTemp = CONCAT(oTemp,',',oTempChild);
SELECT GROUP_CONCAT(areaId) INTO oTempChild FROM area_info WHERE FIND_IN_SET(parentAreaId,oTempChild) > 0;
END WHILE;
RETURN oTemp;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
