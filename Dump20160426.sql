CREATE DATABASE  IF NOT EXISTS `triporg` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `triporg`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: triporg
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `addresstable`
--

DROP TABLE IF EXISTS `addresstable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addresstable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresstable`
--

LOCK TABLES `addresstable` WRITE;
/*!40000 ALTER TABLE `addresstable` DISABLE KEYS */;
INSERT INTO `addresstable` VALUES (1,'Boston','Albama','Clearway',2115),(2,'Boston','Alaska','roxbry',1234);
/*!40000 ALTER TABLE `addresstable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertisementapttable`
--

DROP TABLE IF EXISTS `advertisementapttable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisementapttable` (
  `advertID` bigint(20) NOT NULL AUTO_INCREMENT,
  `bathrooms` float DEFAULT NULL,
  `category` bigint(20) NOT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `checkIn` datetime DEFAULT NULL,
  `checkOut` datetime DEFAULT NULL,
  `furnished` varchar(255) DEFAULT NULL,
  `maxOccupants` int(11) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `numOfBeds` int(11) DEFAULT NULL,
  `photoName` varchar(255) DEFAULT NULL,
  `postedBy` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `priceExtraOccupant` float DEFAULT NULL,
  `rooms` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `id` bigint(20) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`advertID`),
  UNIQUE KEY `advertID` (`advertID`),
  KEY `FKA367C1CE9AFCD151` (`id`),
  KEY `FKA367C1CE134559FA` (`category`),
  KEY `FKA367C1CE482E4E9E` (`user`),
  CONSTRAINT `FKA367C1CE134559FA` FOREIGN KEY (`category`) REFERENCES `categorytable` (`categoryid`),
  CONSTRAINT `FKA367C1CE482E4E9E` FOREIGN KEY (`user`) REFERENCES `persontable` (`personID`),
  CONSTRAINT `FKA367C1CE9AFCD151` FOREIGN KEY (`id`) REFERENCES `addresstable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisementapttable`
--

LOCK TABLES `advertisementapttable` WRITE;
/*!40000 ALTER TABLE `advertisementapttable` DISABLE KEYS */;
INSERT INTO `advertisementapttable` VALUES (1,1,1,'Apartment','2016-04-27 00:00:00','2016-04-30 00:00:00','Yes',4,'Des',1,'/TripOrg/resources/images/1461674240632apt3.jpg','host',100,10,1,'Title',1,2),(2,1,1,'Apartment','2016-04-29 00:00:00','2016-04-30 00:00:00','Yes',1,'some des',1,'/TripOrg/resources/images/1461674311846apt8.jpg','host',50,10,1,'Brand',2,2);
/*!40000 ALTER TABLE `advertisementapttable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookingtable`
--

DROP TABLE IF EXISTS `bookingtable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookingtable` (
  `bookingID` bigint(20) NOT NULL AUTO_INCREMENT,
  `bookingDate` datetime DEFAULT NULL,
  `checkInDate` datetime DEFAULT NULL,
  `checkOutDate` datetime DEFAULT NULL,
  `occupants` int(11) DEFAULT NULL,
  `rooms` int(11) DEFAULT NULL,
  `advertID` bigint(20) DEFAULT NULL,
  `person_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`bookingID`),
  KEY `FK707692F5A25EFE1A` (`advertID`),
  KEY `FK707692F5B6CD150E` (`person_ID`),
  CONSTRAINT `FK707692F5A25EFE1A` FOREIGN KEY (`advertID`) REFERENCES `advertisementapttable` (`advertID`),
  CONSTRAINT `FK707692F5B6CD150E` FOREIGN KEY (`person_ID`) REFERENCES `usertable` (`personID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookingtable`
--

LOCK TABLES `bookingtable` WRITE;
/*!40000 ALTER TABLE `bookingtable` DISABLE KEYS */;
INSERT INTO `bookingtable` VALUES (1,'2016-04-26 08:39:19','2016-04-28 00:00:00','2016-04-29 00:00:00',1,1,1,1);
/*!40000 ALTER TABLE `bookingtable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorytable`
--

DROP TABLE IF EXISTS `categorytable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorytable` (
  `categoryid` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`categoryid`),
  UNIQUE KEY `categoryid` (`categoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorytable`
--

LOCK TABLES `categorytable` WRITE;
/*!40000 ALTER TABLE `categorytable` DISABLE KEYS */;
INSERT INTO `categorytable` VALUES (1,'Apartment');
/*!40000 ALTER TABLE `categorytable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emailtable`
--

DROP TABLE IF EXISTS `emailtable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emailtable` (
  `id` bigint(20) NOT NULL,
  `emailid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK4FB8D1B247F76FEE` (`id`),
  CONSTRAINT `FK4FB8D1B247F76FEE` FOREIGN KEY (`id`) REFERENCES `persontable` (`personID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emailtable`
--

LOCK TABLES `emailtable` WRITE;
/*!40000 ALTER TABLE `emailtable` DISABLE KEYS */;
INSERT INTO `emailtable` VALUES (1,'vandana.k.tiwari@gmail.com'),(2,'tiwari.v@husky.neu.edu');
/*!40000 ALTER TABLE `emailtable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hosttable`
--

DROP TABLE IF EXISTS `hosttable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hosttable` (
  `govtID` bigint(20) NOT NULL,
  `idType` varchar(255) DEFAULT NULL,
  `personID` bigint(20) NOT NULL,
  PRIMARY KEY (`personID`),
  KEY `FKD49B9F0662618A03` (`personID`),
  CONSTRAINT `FKD49B9F0662618A03` FOREIGN KEY (`personID`) REFERENCES `persontable` (`personID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hosttable`
--

LOCK TABLES `hosttable` WRITE;
/*!40000 ALTER TABLE `hosttable` DISABLE KEYS */;
INSERT INTO `hosttable` VALUES (45672987,'SSN',2);
/*!40000 ALTER TABLE `hosttable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persontable`
--

DROP TABLE IF EXISTS `persontable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persontable` (
  `personID` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`personID`),
  UNIQUE KEY `personID` (`personID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persontable`
--

LOCK TABLES `persontable` WRITE;
/*!40000 ALTER TABLE `persontable` DISABLE KEYS */;
INSERT INTO `persontable` VALUES (1,'Vandana','Tiwari','van','User','van'),(2,'Ree','S','host','Host','host');
/*!40000 ALTER TABLE `persontable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertable`
--

DROP TABLE IF EXISTS `usertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usertable` (
  `personID` bigint(20) NOT NULL,
  PRIMARY KEY (`personID`),
  KEY `FK139906C362618A03` (`personID`),
  CONSTRAINT `FK139906C362618A03` FOREIGN KEY (`personID`) REFERENCES `persontable` (`personID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertable`
--

LOCK TABLES `usertable` WRITE;
/*!40000 ALTER TABLE `usertable` DISABLE KEYS */;
INSERT INTO `usertable` VALUES (1);
/*!40000 ALTER TABLE `usertable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-26  8:40:37
