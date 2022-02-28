-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: pusher
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `user_1` int NOT NULL,
                           `user_2` int NOT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `contact_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `name` int DEFAULT NULL,
                        `belong_user_id` int DEFAULT NULL,
                        `time` timestamp NULL DEFAULT NULL,
                        `exist` tinyint(1) DEFAULT NULL,
                        `real_uuid` varchar(50) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_group`
--

DROP TABLE IF EXISTS `file_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file_group` (
                              `file_id` int DEFAULT NULL,
                              `group_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_group`
--

LOCK TABLES `file_group` WRITE;
/*!40000 ALTER TABLE `file_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `file_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_matter`
--

DROP TABLE IF EXISTS `file_matter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file_matter` (
                               `matter_id` int DEFAULT NULL,
                               `file_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_matter`
--

LOCK TABLES `file_matter` WRITE;
/*!40000 ALTER TABLE `file_matter` DISABLE KEYS */;
/*!40000 ALTER TABLE `file_matter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_fan`
--

DROP TABLE IF EXISTS `group_fan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_fan` (
                             `group_id` int DEFAULT NULL,
                             `user_id` int DEFAULT NULL,
                             `display_name` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_fan`
--

LOCK TABLES `group_fan` WRITE;
/*!40000 ALTER TABLE `group_fan` DISABLE KEYS */;
INSERT INTO `group_fan` (`group_id`, `user_id`, `display_name`) VALUES (7,1,'test'),(8,1,NULL),(8,2,'咸鱼群员');
/*!40000 ALTER TABLE `group_fan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_manager`
--

DROP TABLE IF EXISTS `group_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_manager` (
                                 `group_id` int DEFAULT NULL,
                                 `manager_user_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_manager`
--

LOCK TABLES `group_manager` WRITE;
/*!40000 ALTER TABLE `group_manager` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_matter`
--

DROP TABLE IF EXISTS `group_matter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_matter` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `group_id` int DEFAULT NULL,
                                `matter_id` int DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_matter`
--

LOCK TABLES `group_matter` WRITE;
/*!40000 ALTER TABLE `group_matter` DISABLE KEYS */;
INSERT INTO `group_matter` (`id`, `group_id`, `matter_id`) VALUES (2,7,17),(3,7,18);
/*!40000 ALTER TABLE `group_matter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_pusher`
--

DROP TABLE IF EXISTS `group_pusher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_pusher` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `groupname` varchar(50) DEFAULT NULL,
                                `creator_user_id` int DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_pusher`
--

LOCK TABLES `group_pusher` WRITE;
/*!40000 ALTER TABLE `group_pusher` DISABLE KEYS */;
INSERT INTO `group_pusher` (`id`, `groupname`, `creator_user_id`) VALUES (7,'测试群',1),(8,'新群名',1);
/*!40000 ALTER TABLE `group_pusher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matter`
--

DROP TABLE IF EXISTS `matter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matter` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `headline` varchar(100) DEFAULT NULL,
                          `startdate` timestamp NULL DEFAULT NULL,
                          `enddate` timestamp NULL DEFAULT NULL,
                          `location` varchar(100) DEFAULT NULL,
                          `isergency` tinyint(1) DEFAULT NULL,
                          `body` varchar(800) DEFAULT NULL,
                          `has_file` tinyint(1) DEFAULT NULL,
                          `message_id` int DEFAULT NULL,
                          `manager_user_id` int DEFAULT NULL,
                          `repeat` enum('NONE','DAY','WEEK','MONTH','YEAR') DEFAULT NULL,
                          `publish_date` timestamp NULL DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matter`
--

LOCK TABLES `matter` WRITE;
/*!40000 ALTER TABLE `matter` DISABLE KEYS */;
INSERT INTO `matter` (`id`, `headline`, `startdate`, `enddate`, `location`, `isergency`, `body`, `has_file`, `message_id`, `manager_user_id`, `repeat`, `publish_date`) VALUES (17,'上班','2021-07-18 01:34:33','2021-07-18 01:34:33','宿舍',1,'到底上不上',NULL,NULL,1,NULL,'2021-07-19 01:31:21'),(18,'上班','2021-07-18 01:34:33','2021-07-18 01:34:33','宿舍',1,'到底上不上',NULL,NULL,1,NULL,'2021-07-19 03:12:41');
/*!40000 ALTER TABLE `matter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matter_fan`
--

DROP TABLE IF EXISTS `matter_fan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matter_fan` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `matter_id` int DEFAULT NULL,
                              `fan_id` int DEFAULT NULL,
                              `source_type` enum('USER','GROUP') DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matter_fan`
--

LOCK TABLES `matter_fan` WRITE;
/*!40000 ALTER TABLE `matter_fan` DISABLE KEYS */;
INSERT INTO `matter_fan` (`id`, `matter_id`, `fan_id`, `source_type`) VALUES (1,17,1,NULL);
/*!40000 ALTER TABLE `matter_fan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `sender` int DEFAULT NULL,
                           `destination_user_id` int DEFAULT NULL,
                           `destination_matter_id` int DEFAULT NULL,
                           `body` varchar(800) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `uname` varchar(100) DEFAULT NULL,
                        `nickname` varchar(30) DEFAULT NULL,
                        `upassword` varchar(100) DEFAULT NULL,
                        `utel` varchar(20) DEFAULT NULL,
                        `umail` varchar(30) DEFAULT NULL,
                        `salt` varchar(64) DEFAULT NULL,
                        `avatar_id` int DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `uname`, `nickname`, `upassword`, `utel`, `umail`, `salt`, `avatar_id`) VALUES (1,'pusher_1','u1','7268d1079c7f386923eac0d6a1e808b9','13666665555',NULL,'77fb62b511334f5b945520a9d1128a1e',NULL),(2,'hmw','u2','29f352b00457ec5fd24925536b9165aa',NULL,NULL,'21fec5e6b8cd4951bce0f4e413ec5d99',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_fan`
--

DROP TABLE IF EXISTS `user_fan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_fan` (
                            `user_id` int DEFAULT NULL,
                            `fan_user_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_fan`
--

LOCK TABLES `user_fan` WRITE;
/*!40000 ALTER TABLE `user_fan` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_fan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-28 11:45:40
