-- MySQL dump 10.16  Distrib 10.1.38-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: memsrest
-- ------------------------------------------------------
-- Server version	10.1.38-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `ward_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg90ck1kd0p4rbamwc22jd2oql` (`patient_id`),
  KEY `FK6wh2pds75affdwn5esdi17nm2` (`ward_id`),
  CONSTRAINT `FK6wh2pds75affdwn5esdi17nm2` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`id`),
  CONSTRAINT `FKg90ck1kd0p4rbamwc22jd2oql` FOREIGN KEY (`patient_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,'2020-01-15','19:00',1,1),(3,'2020-01-15','06:00',1,1),(5,'2020-02-10','18:00',4,1),(6,'2020-02-04','08:00',4,3),(7,'2020-02-03','15:00',5,1),(8,'2020-02-05','19:00',5,3),(9,'2020-02-12','20:00',7,1),(10,'2020-02-06','11:00',7,3),(11,'2020-02-13','19:00',7,1),(12,'2020-02-10','16:00',8,1),(13,'2020-02-05','09:00',8,3),(14,'2020-02-03','20:00',8,1),(15,'2020-02-07','18:00',8,3),(16,'2020-02-04','19:00',1,1),(18,'2020-02-18','06:00',4,3),(19,'2020-02-07','19:00',4,3),(20,'2020-02-19','19:00',4,3),(21,'2020-02-08','06:00',1,3),(22,'2020-02-08','07:00',1,3),(23,'2020-02-08','08:00',1,3),(24,'2020-02-08','09:00',1,3),(26,'2020-02-08','06:00',7,1),(27,'2020-02-08','07:00',7,1),(28,'2020-02-08','08:00',7,1),(30,'2020-02-08','12:00',1,3),(31,'2020-01-15','21:00',15,1),(36,'2020-01-16','20:00',4,3);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examination`
--

DROP TABLE IF EXISTS `examination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examination` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `status` varchar(15) NOT NULL,
  `time` varchar(255) DEFAULT NULL,
  `appointment_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `result_id` bigint(20) DEFAULT NULL,
  `ward_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7dqgrq2dnuomi11x7x3vhl5dl` (`appointment_id`),
  KEY `FK4ouqobe5b719kcyx3llr0u65r` (`patient_id`),
  KEY `FKkwkymj5g25kq46r19y16gpeeu` (`result_id`),
  KEY `FKkdsw9c5t4egvho6okt1u77rp4` (`ward_id`),
  CONSTRAINT `FK4ouqobe5b719kcyx3llr0u65r` FOREIGN KEY (`patient_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK7dqgrq2dnuomi11x7x3vhl5dl` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`id`),
  CONSTRAINT `FKkdsw9c5t4egvho6okt1u77rp4` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`id`),
  CONSTRAINT `FKkwkymj5g25kq46r19y16gpeeu` FOREIGN KEY (`result_id`) REFERENCES `result` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examination`
--

LOCK TABLES `examination` WRITE;
/*!40000 ALTER TABLE `examination` DISABLE KEYS */;
INSERT INTO `examination` VALUES (1,'2020-01-15','closed','19:00',1,1,26,1),(3,'2020-01-15','closed','06:00',3,1,NULL,1),(5,'2020-02-10','closed','18:00',5,4,NULL,1),(6,'2020-02-04','closed','08:00',6,4,25,3),(7,'2020-02-03','pending','15:00',7,5,NULL,1),(8,'2020-02-05','closed','19:00',8,5,27,3),(9,'2020-02-12','open','20:00',9,7,NULL,1),(10,'2020-02-06','closed','11:00',10,7,29,3),(11,'2020-02-13','open','19:00',11,7,NULL,1),(12,'2020-02-10','pending','16:00',12,8,NULL,1),(13,'2020-02-05','closed','09:00',13,8,28,3),(14,'2020-02-03','pending','20:00',14,8,NULL,1),(15,'2020-02-07','closed','18:00',15,8,NULL,3),(16,'2020-02-04','pending','19:00',16,1,NULL,1),(18,'2020-02-18','closed','06:00',18,4,32,3),(19,'2020-02-07','closed','19:00',19,4,30,3),(20,'2020-02-19','closed','19:00',20,4,33,3),(21,'2020-02-08','closed','06:00',21,1,38,3),(22,'2020-02-08','closed','07:00',22,1,37,3),(23,'2020-02-08','closed','08:00',23,1,36,3),(24,'2020-02-08','closed','09:00',24,1,35,3),(26,'2020-02-08','open','06:00',26,7,NULL,1),(27,'2020-02-08','open','07:00',27,7,NULL,1),(28,'2020-02-08','open','08:00',28,7,NULL,1),(30,'2020-02-08','pending','12:00',30,1,NULL,3),(33,'2020-01-15','closed','21:00',31,15,39,1),(35,'2020-01-16','closed','20:00',36,4,41,3);
/*!40000 ALTER TABLE `examination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `ward_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg2efq8dps1qkmoom6upg7pgce` (`patient_id`),
  KEY `FKqbhtmc9monpxydcx972adlehs` (`ward_id`),
  CONSTRAINT `FKg2efq8dps1qkmoom6upg7pgce` FOREIGN KEY (`patient_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqbhtmc9monpxydcx972adlehs` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES (25,'2020-02-04','all good','08:00',4,3),(26,'2020-01-15','blood result','19:00',1,1),(27,'2020-02-05','sdadsa','19:00',5,3),(28,'2020-02-05','gdgdfg','09:00',8,3),(29,'2020-02-06','sasdasd','11:00',7,3),(30,'2020-02-07','all good','19:00',4,3),(31,'2020-02-18','test','06:00',4,3),(32,'2020-02-18','test','06:00',4,3),(33,'2020-02-19','fsdfsd','19:00',4,3),(35,'2020-02-08','dsfas','09:00',1,3),(36,'2020-02-08','last','08:00',1,3),(37,'2020-02-08','final','07:00',1,3),(38,'2020-02-08','help','06:00',1,3),(39,'2020-01-15','Results are excellent','21:00',15,1),(40,'2020-01-30','IRM Result nothing to worry about','14:00',8,3),(41,'2020-01-16','Broken bone','20:00',4,3);
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_blood`
--

DROP TABLE IF EXISTS `result_blood`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result_blood` (
  `alt` double NOT NULL,
  `ast` double NOT NULL,
  `hb` double NOT NULL,
  `hct` double NOT NULL,
  `mcv` double NOT NULL,
  `plt` double NOT NULL,
  `rbc` double NOT NULL,
  `sue` double NOT NULL,
  `wbc` double NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKl7v1wxdcllsh9rwwcmo2imfd4` FOREIGN KEY (`id`) REFERENCES `result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_blood`
--

LOCK TABLES `result_blood` WRITE;
/*!40000 ALTER TABLE `result_blood` DISABLE KEYS */;
INSERT INTO `result_blood` VALUES (17,1.9,1.6,1.8,1.9,1.6,23,1.5,14,26),(7,5,9,4,6,6,8,3,1,39);
/*!40000 ALTER TABLE `result_blood` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_irm`
--

DROP TABLE IF EXISTS `result_irm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result_irm` (
  `img` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKati9gg9ch7mr7brsnm855fjl5` FOREIGN KEY (`id`) REFERENCES `result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_irm`
--

LOCK TABLES `result_irm` WRITE;
/*!40000 ALTER TABLE `result_irm` DISABLE KEYS */;
INSERT INTO `result_irm` VALUES ('brain',25),('',27),('images.jfif',28),('blockchain.jpg',29),('MjgyMzcxOQ.png',30),('test.jpg',31),('test.jpg',32),('1_mtkxzB9AobDwmUu20Rlgvg.png',33),('5bcddd6fcfed1d2b2c5e285b_700xauto.png',35),('proba.jpeg',36),('image.png',37),('',38),('irm_brain_new.png',40),('leg_image.png',41);
/*!40000 ALTER TABLE `result_irm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'PATIENT'),(3,'DOCTOR');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `egn` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `is_account_non_expired` bit(1) NOT NULL,
  `is_account_non_locked` bit(1) NOT NULL,
  `is_credentials_non_expired` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `ward_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l3gw64gxibqdtfp5dm5vutxru` (`egn`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FK4qrgk559jbe4eicm18prsl8hy` (`ward_id`),
  CONSTRAINT `FK4qrgk559jbe4eicm18prsl8hy` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'6702157777','patient@gmail.com','Ivan','','','','','Ivanov','$2a$10$S.m6VGtEyKgKaDs7HTceXOLdImBtLrQLB2mwR9tP2mwQemxf4g.Pa','patient',NULL),(2,NULL,'admin@gmail.com',NULL,'','','','',NULL,'$2a$10$/XWA3eN71McyhFg.wDVIbeknuPmfVEEh3S5XOIwANMfozTC1cjCnu','admin',NULL),(3,'7712248945','doctor@gmail.com','Stefania','','','','','Petkova','$2a$10$b.WL1lSbh8J.1f2COaFOre4Kt949wSam8EyfiHP9r1P1qvDVSm8wm','doctor',1),(4,'7804159887','katerina@gmail.com','Katerina','','','','','Stefanova','$2a$10$YnOizItuKP9j7/LGiFSzHO3ic37lswfJUApgW/STOKeUw/z7i4LjG','katerina',NULL),(5,'4504156666','plamen@gmail.com','Plamen','','','','','Kolev','$2a$10$jXG1UEesB3D6MtIhaATGzOGNmRxk6crk04g.CmiahRDOpnYzXGRaq','plamen',NULL),(6,'6503304578','dean@gmail.com','Dean','','','','','Dimitrov','$2a$10$wjbADrHIF0TGxbVoaU5QQ.EGGOTFanvIWu0IqbUfpqkh6NN3Ii2ne','dean',3),(7,'9704164568','pavlinaikoleva19@gmail.com','Pavlina','','','','','Koleva','$2a$10$RIa2SqabNFpzu7LCeN9XGuXG/xLzNzDhE/a7v5/Rv2Pyu4GGhQMne','pavlina',NULL),(8,'1421457777','stela@gmail.com','Stela','','','','','Rumenova','$2a$10$CgOPlxjokcaGcRiOzatF4e9Au8EGt9GBz0HlH.rNqjHPt2.RMsp66','stela',NULL),(15,'8004234542','emiliq@gmail.com','Emiliq','','','','','Taneva','$2a$10$hIsuO8f5ToD.xODFhd/9GOY1Qa7VP.pfCknMRtVi3TDSHh78R4T5u','emiliq',NULL),(16,'7004234542','daniel@gmail.com','Daniel','','','','','Trifonov','$2a$10$QN5HYVZoarenxWe1tHaj3eJ9DtQlltgs.SoFlNBlGO8jZk//zp0C.','daniel',1),(20,'7105234543','radoslav@gmail.com','Radoslav','','','','','Krasimirov','$2a$10$oEpy/gHpPNI3ueJwMc8i2OJiHOrrX4EKD/27WwPmg54VZP6PSvng.','radoslav',3),(22,'6704234546','admin2@gmail.com','Stoqn','','','','','Kolev','$2a$10$Uauow6XDhJCrVhmP7tDilOx3SWCrD1pTETHz8SXDlMKv8Cy21dkXK','admin2',NULL),(23,'7506154543','milena@gmail.com','Milena','','','\0','','Krasimirova','$2a$10$vmXPjRvQry9CV7yDcINVAeE11JwKZ0.xQzfDTyBixCuzB1/1J883W','milena',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,2),(2,1),(3,3),(4,2),(5,2),(6,3),(7,2),(8,2),(15,2),(16,3),(20,3),(22,1),(23,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ward`
--

DROP TABLE IF EXISTS `ward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ward` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_number` varchar(20) NOT NULL,
  `ward_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward`
--

LOCK TABLES `ward` WRITE;
/*!40000 ALTER TABLE `ward` DISABLE KEYS */;
INSERT INTO `ward` VALUES (1,'P250','Blood'),(3,'5454A','Irm'),(4,'A22','Neurology');
/*!40000 ALTER TABLE `ward` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-11 13:12:06
