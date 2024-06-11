-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: book-shopping-mvc-ajax
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES ('aaaaa','ROLE_ADMIN'),('aqilazizov','ROLE_ADMIN'),('sadiq','ROLE_EDIT_BOOK'),('Semulli','ROLE_ADMIN'),('tural','ROLE_ADMIN'),('u1','ROLE_ADMIN'),('u2','ROLE_ADMIN'),('u3','ROLE_ADMIN');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basket_book`
--

DROP TABLE IF EXISTS `basket_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `basket_book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `count` int DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKf6mwqjspd7j6qa4il4tbkv7kv` (`book_id`),
  KEY `FKme8snq1it725oqbjkooh9ykjq` (`order_id`),
  CONSTRAINT `FK9rgkikwyg8n6dtoh58yjo4w92` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FKme8snq1it725oqbjkooh9ykjq` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basket_book`
--

LOCK TABLES `basket_book` WRITE;
/*!40000 ALTER TABLE `basket_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `basket_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `author` varchar(300) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `page_count` int DEFAULT NULL,
  `price` double NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `book_chk_1` CHECK (((`page_count` >= 0) and (`page_count` <= 10000))),
  CONSTRAINT `book_chk_2` CHECK (((`price` >= 0) and (`price` <= 1000)))
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (2,'Aqil Azizov','This is python','d3da8bb5-f062-4848-9a4f-a0ebdbd5d656.jpg','Python',11,100,'u1'),(4,'James Gosling','Java Standart Edition','algorithm.png','Java',112,100,'u1'),(5,'Mike Tyson','This is CSS','python.png','CSS',11,100,'u1'),(6,'Mike Tyson','this is css','3790b49e-cc5c-46df-9fe0-384794391fc9.png','CSS',11,111,'tural'),(7,'James Gosling','gyuvhbh','857c9c25-9a94-4aa2-a0fc-8a5d38c580cf.png','Java',11,11,'sadiq'),(8,'Sadiq Mirishli','This is SQL','sql.png','SQL',111,800,'u1'),(10,'Mike Tyson','This is Algorithms','d3da8bb5-f062-4848-9a4f-a0ebdbd5d656.jpg','Algorithms',33,333,'u1'),(11,'Mike Tyson','This is Python','algorithm.png','Python',100,555,'u1'),(12,'Mike Tyson','','fakeimage.png','dsdsds',11,11,'sadiq'),(13,'James Gosling','This is Java SE book','sql.png','Java Standart Edition',111,100,'u1'),(14,'Mike Tyson','This is C++ Book','algorithm.png','C++',22,111,'u1'),(15,'James Gosling','This is HTML','algorithm.png','HTML',11,111,'Semulli');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `note` varchar(255) DEFAULT NULL,
  `register` varbinary(255) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK624gtjin3po807j3vix093tlf` (`customer_id`),
  CONSTRAINT `FK624gtjin3po807j3vix093tlf` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(150) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('aaaaa','{bcrypt}$2a$10$tM9/pBDLLts9YncpIamciOLIiRhV6otlaBGUKgu6obtA59bI3ZvKS',1),('aqilazizov','{bcrypt}$2a$10$b5vuEr0.ctWoHfFNXRP9b.u.tkXm.ABLq3Umj/QgWe5ZGH10LoOha',1),('sadiq','{bcrypt}$2a$10$KRYOKykgFz7msXhnUl2oj.fWxCcM2aOapvZqP.lAI/ZIwTgvt.2U6',1),('Semulli','{bcrypt}$2a$10$azhuvOsMigcO51x57NRNKuVoQuT3pU5B3F0zvRd7kGp4p/42X7d0W',1),('tural','{bcrypt}$2a$10$B2gYETwhKXEm8/Slto6n3u2qYV5YTZlt2wi7hBNZzhqbS1sykfyh6',1),('u1','{noop}1234',1),('u2','{noop}1234',1),('u3','{noop}1234',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-09 15:31:40
