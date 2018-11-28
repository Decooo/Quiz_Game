-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: quizgame1.cubow3iaz1bh.eu-west-1.rds.amazonaws.com    Database: quizgame1
-- ------------------------------------------------------
-- Server version	5.6.41-log

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id_answer` int(11) NOT NULL AUTO_INCREMENT,
  `id_question` int(11) NOT NULL,
  `content` varchar(500) NOT NULL,
  `correct` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_answer`),
  KEY `question_key_idx` (`id_question`),
  CONSTRAINT `question_key` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,2,'3',0),(2,2,'6',0),(3,2,'5',0),(4,2,'4',1),(5,3,'44',0),(6,3,'48',1),(7,3,'56',0),(8,3,'46',0),(9,4,'Poznan',0),(10,4,'Torun',0),(11,4,'Warszawa',1),(12,4,'Krakow',0),(13,5,'Madryt',1),(14,5,'Rzym',0),(15,5,'Walencja',0),(16,5,'Barcelona',0),(17,6,'Gladki Wierch',0),(18,6,'Sniezka',0),(19,6,'Rysy',1),(20,6,'Babia Gora',0),(21,7,'Wielki Szyszak',0),(22,7,'Smielec',0),(23,7,'Kopa',0),(24,7,'Sniezka',1),(25,8,'Berlin',1),(26,8,'Lipsk',0),(27,8,'Paryz',0),(28,8,'Monachium',0),(29,9,'Krakow',0),(30,9,'Warszawa',1),(31,9,'Lodz',0),(32,9,'Poznan',0),(33,10,'Tatry',0),(34,10,'Gory Ural',1),(35,10,'Himalaje',0),(36,10,'Alpy',0),(37,11,'Spokojny',1),(38,11,'Indyjski',0),(39,11,'Atlantycki',0),(40,11,'Arktyczny',0),(41,12,'1410',1),(42,12,'1412',0),(43,12,'1310',0),(44,12,'1409',0),(45,13,'1981',1),(46,13,'1980',0),(47,13,'1983',0),(48,13,'1982',0),(49,14,'1018',0),(50,14,'1025',1),(51,14,'1010',0),(52,14,'1000',0),(53,15,'Warszawska',0),(54,15,'Poznanska',0),(55,15,'Wroclawska',0),(56,15,'Krakowska',1),(57,16,'Sw. Wojciech',0),(58,16,'Siemowit',0),(59,16,'Boleslaw Chrobry',0),(60,16,'Mieszko I',1);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id_category` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Matematyka'),(2,'Geografia'),(3,'Historia');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id_question` int(11) NOT NULL AUTO_INCREMENT,
  `id_category` int(11) NOT NULL,
  `content` varchar(500) NOT NULL,
  PRIMARY KEY (`id_question`),
  KEY `category_jey_idx` (`id_category`),
  CONSTRAINT `category_jey` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (2,1,'2 + 2 = '),(3,1,'6 * 8 ='),(4,2,'Stolica Polski to:'),(5,2,'Stolica Hiszpani to:'),(6,2,'Najwyzszy szczyt w Polsce to:'),(7,2,'Najwyzszy szczyt w Karkonoszach to:'),(8,2,'Stolica Niemiec to:'),(9,2,'Najwieksze miasto w Polsce to:'),(10,2,'Oddzielaja Europe i Azje?'),(11,2,'Najwiekszy ocean swiata to?'),(12,3,'Bitwa pod Grunwaldem odbyla sie w roku?'),(13,3,'W ktorym roku wprowadzono stan wojenny?'),(14,3,'Koronacja Boleslawa Chrobrego odbyla sie w roku?'),(15,3,'Kazimierz Wielki zalozyl akademie'),(16,3,'Pierwszym historycznym wladca Polski byl?');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ranking`
--

DROP TABLE IF EXISTS `ranking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ranking` (
  `id_ranking` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `result` int(11) NOT NULL,
  PRIMARY KEY (`id_ranking`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ranking`
--

LOCK TABLES `ranking` WRITE;
/*!40000 ALTER TABLE `ranking` DISABLE KEYS */;
INSERT INTO `ranking` VALUES (1,'Gamer',800),(2,'Janusz',2400),(3,'Sosnowicz',4600),(4,'Romanowski',21000),(5,'Edward',100),(6,'Puchatek',5640),(7,'Tomcio',12400),(8,'Gustek',9000),(9,'Ola',1590),(10,'Amadeusz',6210),(11,'Brzoza',4510),(12,'Lipowski',3600),(13,'Mlody',2580),(14,'Gruby',1700),(15,'Gracjan',4000),(16,'niepodleglosciowiec',100),(17,'ProGamer',300),(18,'Gracz',1500),(19,'Testowiec',100),(20,'aaa',600),(21,'asd',1000),(22,'Jozek',1500),(23,'SUper',3600),(24,'Lucjan',300),(25,'Test',1500),(26,'Lucjan',10);
/*!40000 ALTER TABLE `ranking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'quizgame1'
--

--
-- Dumping routines for database 'quizgame1'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-13 15:58:53
