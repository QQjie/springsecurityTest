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

/* Function  structure for function  `AndOperate` */

/*!50003 DROP FUNCTION IF EXISTS `AndOperate` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`192.168.3.138` FUNCTION `AndOperate`(vals varchar(100)) RETURNS int(11)
label_pro:BEGIN
     DECLARE tmp1 INT(11);
     DECLARE tmp2 INT(11);
     DECLARE tmp3 INT(11);
     DECLARE i INT;
     SET i=1;
     set tmp3=length(vals)-length(Replace(vals,',',''));
     IF(tmp3=0) THEN
	     SET tmp2=cast(vals as signed);
	   #  leave label_pro;
	else 
		loop1:WHILE i< tmp3+1 DO
	
			IF((i%2)=1) THEN
				IF(i=1) THEN
					SET tmp1= ((SPLIT_STR(vals,',',CEIL(i/2)))|(SPLIT_STR(vals,',',CEIL(i/2)+1)));
		
				ELSE  
					SET tmp1=tmp1|SPLIT_STR(vals,',',CEIL(i/2)+1);
	      
			        END IF;
	                 END IF;
	
		SET i=i+1;
		SET tmp2=tmp1;
		END WHILE loop1;
     END IF;
     
     RETURN tmp2;	
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
