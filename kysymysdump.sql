-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: kysymykset
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Current Database: `kysymykset`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `kysymykset` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `kysymykset`;

--
-- Table structure for table `kysymys`
--

DROP TABLE IF EXISTS `kysymys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kysymys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teksti` varchar(255) NOT NULL,
  `tyyppiID` varchar(255) DEFAULT NULL,
  `kategoriaID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kysymys`
--

LOCK TABLES `kysymys` WRITE;
/*!40000 ALTER TABLE `kysymys` DISABLE KEYS */;
INSERT INTO `kysymys` VALUES (1,'Mikä pari ei kuulu joukkoon?',NULL,NULL),(2,'Mistä urasta Tommi haaveili pikkupoikana?',NULL,NULL),(3,'Mikä seuraavista ei ole RuntimeException luokasta periytyvä poikkeus?',NULL,NULL),(4,'Mikä on Samun secret talent?',NULL,NULL),(5,'Missä lajissa Jukka haluaisi saavuttaa mustan vyön?',NULL,NULL),(6,'Mikä on Maisan motto?',NULL,NULL),(7,'Mikä on Heikin motto?',NULL,NULL),(8,'Mikä laji on Ollille (Vainio) kaikista rakkain?',NULL,NULL),(9,'Missä maineikkaissa yrityksissä Jan on työskennellyt?',NULL,NULL),(10,'Millä nimellä Java tunnettiin alun perin?',NULL,NULL);
/*!40000 ALTER TABLE `kysymys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kysymysvaihtoehdot`
--

DROP TABLE IF EXISTS `kysymysvaihtoehdot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kysymysvaihtoehdot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teksti` varchar(255) DEFAULT NULL,
  `oikeavastaus` int(11) DEFAULT NULL,
  `kysymysID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `KysymysID` (`kysymysID`),
  CONSTRAINT `KysymysID` FOREIGN KEY (`kysymysID`) REFERENCES `kysymys` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kysymysvaihtoehdot`
--

LOCK TABLES `kysymysvaihtoehdot` WRITE;
/*!40000 ALTER TABLE `kysymysvaihtoehdot` DISABLE KEYS */;
INSERT INTO `kysymysvaihtoehdot` VALUES (1,'boolean Boolean',0,1),(2,'long Long',0,1),(3,'double Double',0,1),(4,'string String',1,1),(5,'Palomies',0,2),(6,'Opettaja',1,2),(7,'Asianajaja',0,2),(8,'Tulkki',0,2),(9,'ArithmeticException',0,3),(10,'ArrayStoreException',0,3),(11,'SecurityException',0,3),(12,'ClassReachException',1,3),(13,'Ulkomailla liftaaminen',1,4),(14,'Kokkaaminen',0,4),(15,'Osaa nimetä kaikki Yhdysvaltojen Osavaltiot',0,4),(16,'Muuttaminen',0,4),(17,'Jujutsu',1,5),(18,'Aikido',0,5),(19,'Ompelu',0,5),(20,'Karate',0,5),(21,'Koti, Uskonto ja Isänmaa!',0,6),(22,'Ihminen ei voi onnistua, jos ei ole valmis ensiksi yrittämään!',0,6),(23,'Se mikä ei tapa, vahvistaa!',0,6),(24,'Ei pidä keskittyä siihen että on paras, vaan siihen että on parempi kuin eilen',1,6),(25,'Doing while learning!',0,7),(26,'Learn, code, conquer!',0,7),(27,'Tulin, näin koodasin!',0,7),(28,'Learning by doing!',1,7),(29,'Futis',1,8),(30,'Jääkiekko',0,8),(31,'Curling',0,8),(32,'Runonlausunta',0,8),(33,'Microsoft ja Nokia',1,9),(34,'Apple ja Nokia',0,9),(35,'Google ja Nokia',0,9),(36,'Amazon ja Twitter',0,9),(37,'Java Only',0,10),(38,'Apple',0,10),(39,'JDK',0,10),(40,'Oak',1,10);
/*!40000 ALTER TABLE `kysymysvaihtoehdot` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-22 14:54:10
