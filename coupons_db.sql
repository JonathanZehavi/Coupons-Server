-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: coupons
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Travel'),(2,'Fashion'),(3,'Food'),(4,'Drinks'),(5,'Furniture'),(6,'Electronics'),(7,'Kitchen'),(8,'Home Decor'),(9,'Education'),(10,'Entertainment');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `company_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hj46lf87q00ohfd02njib276u` (`company_name`),
  UNIQUE KEY `UK_mixodugpv8ytm1alafk5hydnn` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (1,'Mumbai, India','Travel Guru','0547477474'),(2,'California, USA','Apple','837843748374'),(4,'Mexico, USA','Udemy','73264824343'),(5,'Kiryat Gat, Israel','Tamnoon','05646543215'),(6,'Miami Florida, USA','Burger King','025646546'),(7,'Yehiam, Israel','Malka Beer','0564897545'),(8,'Kefar Sava, Israel','Doctor Gav','0654654654'),(10,'Kefar Sava, Israel','Fox Home','0754887554'),(11,'Bnei Brak, Israel','Dan Design Center','0564878445'),(12,'Idaho, USA','TravelingCO','0476637744'),(14,'Texas, USA','Clothes4U','5678986544'),(15,'Rosh Hayin','PersilCO','4434343443'),(20,'Hod Hasharon, Isrel','SanoCO','097473222');
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupons`
--

DROP TABLE IF EXISTS `coupons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupons` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` int NOT NULL,
  `description` varchar(255) NOT NULL,
  `end_date` date NOT NULL,
  `image` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `start_date` date NOT NULL,
  `title` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `company_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdcx0ovgcgvc3v9clxn2b9kvg9` (`company_id`),
  CONSTRAINT `FKdcx0ovgcgvc3v9clxn2b9kvg9` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupons`
--

LOCK TABLES `coupons` WRITE;
/*!40000 ALTER TABLE `coupons` DISABLE KEYS */;
INSERT INTO `coupons` VALUES (3,6,'Flight tickets on sale right now. Don\'t miss this.','2022-12-19','https://cloudfront.slrlounge.com/wp-content/uploads/2014/04/Kim-Leuenberger-2.jpg',250,'2022-09-19','Pay Less, Travel More','Travel',1),(4,40,'Our premium clothes are now available on sale','2022-11-30','https://i.pinimg.com/564x/3f/5d/72/3f5d72e9db3cd63b7f10cd10c382097a.jpg',25,'2022-08-31','Get the best value for your money','Fashion',5),(5,10,'Add free french fries for free for your meal','2022-12-24','https://i.pinimg.com/564x/dd/9f/be/dd9fbec5211ff778bf28f1f5c858f8ce.jpg',5,'2022-09-24','Free food, Can\'t say no to that','Food',6),(6,24,'All of our beers for 50% in the first hour','2022-12-24','https://i.pinimg.com/736x/ec/6a/e3/ec6ae3e769e3ef82c60e6aa202e060f6.jpg',25,'2022-09-24','Free drinks all around','Drinks',7),(7,38,'Mattresses are now for 20% off','2022-12-24','https://i.pinimg.com/564x/c0/b6/28/c0b628467d2330838bd6d8bf9908a92a.jpg',50,'2022-09-24','Sleep like a king','Furniture',8),(8,40,'MacBook Pro 16GB RAM M1 10% off, for a very limited time','2022-12-24','https://i.pinimg.com/564x/4b/1e/31/4b1e314a1f3450f0412c308d16772347.jpg',1500,'2022-09-24','Get the best computer to your desk','Electronics',2),(9,37,'Complete your kitchenware set with our amazing price','2022-12-24','https://i.pinimg.com/564x/3d/fb/f1/3dfbf1b90294df09e720ffdd708f7d7b.jpg',50,'2022-09-24','Complete your kitchen with this items','Kitchen',10),(10,37,'Beatuiful frames for your family\'s photos','2022-12-24','https://i.pinimg.com/564x/c6/5d/8d/c65d8dac60ec2420ff4921c358076955.jpg',50,'2022-09-24','Find a cheaper way to decorate you house','Home Decor',11),(23,39,'Amazing sale on all of our courses don\'t miss out','2022-12-02','https://myelearningworld.com/wp-content/uploads/2022/06/udemy-review.jpg',350,'2022-10-03','Get 50% off for courses','Education',4),(51,40,'Get free tickets to concerts all around the world','2022-12-16','https://i.pinimg.com/564x/61/aa/49/61aa49fcf66be20471aa71e56cf40986.jpg',100,'2022-10-16','Get tickets to concerts','Entertainment',1),(52,33,'Get the cheapest flying tickets you can get','2022-12-16','https://i.pinimg.com/564x/46/22/e8/4622e81a310f3051cb2477e72ed21ab3.jpg',150,'2022-10-16','Fly to EU with amazing offers','Travel',1),(53,8,'The best guided tours, not as expensive as you expected','2022-12-16','https://i.pinimg.com/564x/39/71/f1/3971f1413b72ffcad015abc5ea614018.jpg',150,'2022-10-16','Guided tours in EU','Travel',1),(54,24,'Why bother going around when you can get the whole trip organized just for you','2022-12-16','https://i.pinimg.com/564x/37/cd/57/37cd57c6aaf20d20a5cfdd2c02461588.jpg',150,'2022-10-16','A cheap package for flights + hotels','Travel',1);
/*!40000 ALTER TABLE `coupons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` int NOT NULL,
  `address` varchar(255) NOT NULL,
  `amount_of_children` int NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKpog72rpahj62h7nod9wwc28if` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (2,'Kiryat Ata',1,'04664677366','1992-10-03');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchased_coupons`
--

DROP TABLE IF EXISTS `purchased_coupons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchased_coupons` (
  `purchase_id` int NOT NULL,
  `coupon_id` int NOT NULL,
  KEY `FKle7fg7upq5023xe09vbwq1be0` (`coupon_id`),
  KEY `FKlsu0qw3kpdy37pqducnf36ytm` (`purchase_id`),
  CONSTRAINT `FKle7fg7upq5023xe09vbwq1be0` FOREIGN KEY (`coupon_id`) REFERENCES `coupons` (`id`),
  CONSTRAINT `FKlsu0qw3kpdy37pqducnf36ytm` FOREIGN KEY (`purchase_id`) REFERENCES `purchases` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchased_coupons`
--

LOCK TABLES `purchased_coupons` WRITE;
/*!40000 ALTER TABLE `purchased_coupons` DISABLE KEYS */;
INSERT INTO `purchased_coupons` VALUES (99,3),(99,4),(99,5),(99,6),(99,7),(99,8),(99,9),(99,10),(100,3),(100,4),(100,5),(100,6),(100,7),(100,8),(100,9),(100,10),(101,3),(101,4),(101,5),(101,6),(101,7),(101,8),(101,9),(101,10),(102,3),(102,4),(102,5),(102,6),(102,7),(102,8),(102,9),(102,10),(103,3),(103,4),(103,5),(103,6),(104,3),(104,4),(104,5),(104,6),(105,3),(105,4),(105,5),(105,6),(106,3),(106,4),(106,5),(106,6),(107,3),(107,4),(107,5),(107,6),(108,3),(108,4),(108,5),(108,6),(109,3),(109,4),(109,5),(109,6),(110,3),(110,4),(110,5),(110,6),(111,3),(111,4),(111,5),(111,6),(112,3),(112,4),(112,5),(112,6),(113,3),(113,4),(113,5),(113,6),(114,3),(114,4),(114,5),(114,6),(115,3),(115,4),(116,3),(117,4),(117,5),(117,6),(118,3),(118,3),(118,3),(118,3),(118,3),(118,4),(118,4),(118,4),(118,4),(118,4),(118,5),(118,6),(119,3),(119,3),(119,3),(119,3),(119,3),(119,4),(119,4),(119,4),(119,4),(119,4),(119,5),(119,6),(120,3),(120,4),(120,5),(120,5),(120,5),(120,6),(120,6),(120,6),(120,10),(120,10),(120,10),(120,23),(120,23),(120,23),(120,23),(121,3),(121,8),(121,10),(121,10),(121,10),(121,10),(121,23),(121,23),(121,23),(121,51),(121,51),(121,51),(122,54),(122,54),(122,54),(122,54),(122,53),(122,53),(122,53),(122,53),(122,52),(122,52),(122,52),(122,52),(123,6),(123,6),(123,6),(123,6),(123,6),(123,7),(123,7),(123,7),(123,7),(123,7),(123,7),(123,8),(123,8),(123,8),(123,8),(123,8),(123,9),(123,9),(123,9),(123,9),(124,4),(124,5),(124,6),(124,6),(124,7),(124,7),(124,53),(124,53),(124,53),(124,52),(124,52),(124,52),(124,54),(124,54),(124,54),(124,54),(125,4),(125,4),(125,4),(125,5),(125,5),(125,5),(125,5),(125,6),(125,6),(125,6),(125,6),(126,3),(126,3),(126,3),(126,3),(126,3),(126,3),(126,3),(127,53),(127,53),(127,53),(127,53),(127,53),(127,53),(127,53),(127,53),(127,53),(127,53),(127,53),(128,54),(129,54),(130,53),(130,53),(130,53),(130,52),(130,52),(130,52),(131,4),(131,53),(131,54),(132,51),(132,51),(132,51),(132,51),(132,23),(132,53),(133,54),(133,54),(133,54),(133,53),(133,53),(133,53),(134,3),(134,3),(134,4),(134,4),(135,3),(135,3),(135,4),(135,4),(136,54),(136,53),(136,52),(137,52),(137,53),(137,54),(138,53),(138,53),(138,53),(138,53),(138,53),(138,53),(138,54),(138,54),(138,54),(138,54),(139,3),(139,3),(139,3),(139,3),(139,3),(139,4),(139,4),(139,4),(139,4),(139,4),(139,5),(139,5),(139,5),(139,5),(140,53),(140,53),(140,54),(140,54),(140,52),(140,52),(141,4),(141,5),(141,6),(142,3),(142,3),(142,3),(142,3),(143,3),(143,3),(143,3),(143,3),(143,3),(143,4),(143,4),(143,4),(143,4),(143,5),(143,5),(143,5),(143,5),(144,5),(145,9),(145,9),(145,9),(145,9),(146,53),(146,53),(146,53),(147,54),(148,54),(148,53),(148,52),(148,51),(148,23),(149,51),(149,23),(149,10),(149,9),(149,53),(149,52),(149,54),(150,54),(150,53),(150,52),(150,51),(150,23),(150,10);
/*!40000 ALTER TABLE `purchased_coupons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchases`
--

DROP TABLE IF EXISTS `purchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchases` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` int NOT NULL,
  `customer_id` int DEFAULT NULL,
  `date_of_purchase` datetime(6) NOT NULL,
  `total_price` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9q5yt0n9s4ube31bxfamhir44` (`customer_id`),
  CONSTRAINT `FK9q5yt0n9s4ube31bxfamhir44` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchases`
--

LOCK TABLES `purchases` WRITE;
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
INSERT INTO `purchases` VALUES (91,2,2,'2022-10-04 15:41:37.118000',500),(92,2,2,'2022-10-04 15:42:23.090000',500),(93,2,2,'2022-10-04 15:44:18.891000',500),(94,2,2,'2022-10-04 15:45:58.901000',500),(95,2,2,'2022-10-04 15:48:58.065000',500),(96,2,2,'2022-10-04 15:50:07.198000',500),(97,2,2,'2022-10-04 15:53:15.965000',500),(98,1,2,'2022-10-18 09:13:36.676000',250),(99,8,2,'2022-10-20 10:53:03.614000',7000),(100,8,2,'2022-10-20 11:07:11.569000',875),(101,8,2,'2022-10-20 11:29:57.300000',875),(102,8,2,'2022-10-20 11:31:58.387000',875),(103,4,2,'2022-10-20 11:48:58.279000',2175),(104,4,2,'2022-10-20 11:49:13.764000',2175),(105,4,2,'2022-10-20 11:49:42.234000',2175),(106,4,2,'2022-10-20 11:50:54.640000',2175),(107,4,2,'2022-10-20 11:50:58.353000',2175),(108,4,2,'2022-10-20 11:51:17.959000',2175),(109,4,2,'2022-10-20 11:51:21.408000',2175),(110,4,2,'2022-10-20 11:51:40.147000',2175),(111,4,2,'2022-10-20 12:01:27.907000',2550),(112,4,2,'2022-10-20 12:04:54.737000',2850),(113,4,2,'2022-10-20 12:05:05.093000',2850),(114,4,2,'2022-10-20 12:24:37.199000',2850),(115,2,2,'2022-10-20 13:06:31.112000',4750),(116,1,2,'2022-10-20 13:33:27.920000',1750),(117,3,2,'2022-10-20 13:38:51.390000',425),(118,12,2,'2022-10-21 14:32:26.774000',2675),(119,12,2,'2022-10-21 14:33:53.216000',2675),(120,15,2,'2022-10-21 14:52:36.992000',2575),(121,12,2,'2022-10-21 15:10:49.271000',1850),(122,12,2,'2022-10-21 15:30:51.348000',1800),(123,20,2,'2022-10-21 15:56:38.642000',1000),(124,16,2,'2022-10-21 15:58:37.027000',2075),(125,11,2,'2022-10-21 16:02:22.446000',1450),(126,7,2,'2022-10-22 15:40:55.220000',1750),(127,11,2,'2022-10-22 15:45:28.837000',1650),(128,1,2,'2022-10-22 16:29:59.813000',150),(129,1,2,'2022-10-22 16:31:47.942000',150),(130,6,2,'2022-10-22 16:32:30.370000',900),(131,3,2,'2022-10-22 16:41:50.932000',550),(132,6,2,'2022-10-22 16:45:47.336000',900),(133,6,2,'2022-10-22 16:48:26.863000',900),(134,4,2,'2022-10-22 16:49:51.197000',1000),(135,4,2,'2022-10-22 16:51:18.977000',1000),(136,3,2,'2022-10-22 16:53:51.341000',450),(137,3,2,'2022-10-22 16:54:23.832000',450),(138,10,2,'2022-10-22 17:32:44.427000',1500),(139,14,2,'2022-10-23 07:16:11.633000',1395),(140,6,2,'2022-10-23 07:22:17.036000',900),(141,3,2,'2022-10-23 07:25:28.320000',55),(142,4,2,'2022-10-23 10:19:10.456000',1000),(143,13,2,'2022-10-23 12:58:08.542000',1370),(144,1,2,'2022-10-23 13:55:29.582000',5),(145,4,2,'2022-10-23 15:07:03.314000',200),(146,3,2,'2022-10-23 15:11:35.263000',450),(147,1,2,'2022-10-23 15:13:40.300000',150),(148,5,2,'2022-10-23 15:16:24.160000',900),(149,7,2,'2022-10-23 15:17:23.981000',1000),(150,6,2,'2022-10-23 15:18:10.501000',950);
/*!40000 ALTER TABLE `purchases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_id` int DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,NULL,'Jonatahn','Zehavi','$2a$10$mRlGfG1QDWad4wq4QtloIOSPyd6XxM.SNa3IijwBWUsEzSE50lloa','Customer','yonatancustomer@gmail.com'),(7,1,'Jonatahn','Zehavi','$2a$10$rYE0jNooHeq8YlttfizLmO5J/QEeneOBeMJpnXw991hGXl41D.oYu','Company','yonatancompany@gmail.com'),(16,NULL,'Jonathan','Zehavi','$2a$10$8Ueb3p/wnxe7FwcIQCI0TuLuaH9anvHVclGinN8KpZU./4e11TvxS','Admin','yonatanzehavi@gmail.com');
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

-- Dump completed on 2022-10-24 19:41:20
