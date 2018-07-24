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

/* Function  structure for function  `getChildAreaId` */

/*!50003 DROP FUNCTION IF EXISTS `getChildAreaId` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`192.168.3.138` FUNCTION `getChildAreaId`(Id INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
    DECLARE tmp VARCHAR(4000);
    DECLARE tmpChild VARCHAR(4000);
    declare tmpbrotherAreaId varchar(4000);
     
    set tmp='';
    set tmpbrotherAreaId='';
    
    SELECT GROUP_CONCAT(brotherAreaId)	into tmpbrotherAreaId from area_rel where areaId=Id;
    
    if tmpbrotherAreaId is not null then	
        SET tmpChild = CONCAT(CAST(Id AS CHAR),',',tmpbrotherAreaId);
    else
	SET tmpChild =CAST(Id AS CHAR);
    end if;
    
    while tmpChild is not null
    do
    set tmp=concat(tmp,',',tmpChild);
    select group_concat(areaId) into tmpChild from area_info where find_in_set(parentAreaId,tmpChild)>0;
    end while;
    
    return tmp;
    
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
