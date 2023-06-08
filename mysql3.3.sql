-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: project
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `agroups`
--

DROP TABLE IF EXISTS `agroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agroups` (
  `id` int NOT NULL AUTO_INCREMENT,
  `gName` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agroups`
--

LOCK TABLES `agroups` WRITE;
/*!40000 ALTER TABLE `agroups` DISABLE KEYS */;
INSERT INTO `agroups` VALUES (12,'OOP2'),(13,'Software Engineering'),(14,'OOP Project');
/*!40000 ALTER TABLE `agroups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupmembers`
--

DROP TABLE IF EXISTS `groupmembers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groupmembers` (
  `group_id` int NOT NULL,
  `member_id` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `isAdmin` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_values` (`group_id`,`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupmembers`
--

LOCK TABLES `groupmembers` WRITE;
/*!40000 ALTER TABLE `groupmembers` DISABLE KEYS */;
INSERT INTO `groupmembers` VALUES (12,46,43,1),(13,46,44,1),(14,46,45,1),(13,47,46,1),(13,17,47,0),(12,17,48,0),(12,18,49,0),(13,48,50,0);
/*!40000 ALTER TABLE `groupmembers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sender_id` int NOT NULL,
  `content` varchar(20000) NOT NULL,
  `created_at` timestamp NOT NULL,
  `group_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (40,46,'Today is your projects presentation good luck guys !! ','2023-06-07 09:19:23',12),(41,46,'please upload your projcet on BB','2023-06-07 09:19:46',12),(43,46,'Final Exam is on SUNDAY 18/06 (08:00 - 10:00)\r\nPLEASE PREPARE WELL','2023-06-07 09:23:56',12),(45,47,'Dear students, welcome to Software Enginnerin!  I am excited to embark on this learning journey together.Please make sure to review the syllabus and course materials. If you have any questions, feel free to reach out to me.','2023-06-07 09:35:04',13),(46,47,'Reminder: Upcoming Assignment Deadline\r\n\r\nHello everyone, just a friendly reminder that the first assignment is due this Friday, [15/06].\r\n\r\nMake sure to submit your work on time. If you need any assistance, don\'t hesitate to ask.','2023-06-07 09:55:52',13),(47,47,'Please work hard','2023-06-07 10:37:16',13),(48,46,'IMPORTANT YOUR presentation is at 2 PM','2023-06-07 10:51:36',12),(49,47,'this is for you','2023-06-07 14:14:47',13),(50,47,'here is another','2023-06-07 14:15:02',13);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `pass` varchar(64) NOT NULL,
  `fname` varchar(30) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (17,'mohammed1','ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f','Mohammed','Ali','mohammed.ali@example.com'),(18,'ahmed1','3c2122a1d02657a5aa61ba86504a111b04c425df3471f3b8d48d76e7557f3a7e','Ahmed','Khalid','ahmed.khalid@example.com'),(19,'fatima1','a6b9933cf8200b08cf520b21876fb6bbd611c40e59796a8125a2a9c8eb73cdf1','Fatima','Hassan','fatima.hassan@example.com'),(20,'abdullah1','dce9d5698866cedb9d7f4165bfbcef71b39d9e3106cbd8f3c7eb00aa15e92b1f','Abdullah','Said','abdullah.said@example.com'),(21,'nour1','74ba33a2c67946c80335c485123111e9450ced2b728b125117499e4bd45f9ee7','Nour','Ibrahim','nour.ibrahim@example.com'),(22,'sara1','a075d17f3d453073853f813838c15b8023b8c487038436354fe599c3942e1f95','Sara','Ahmed','sara.ahmed@example.com'),(23,'ali1','6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090','Ali','Mahmoud','ali.mahmoud@example.com'),(24,'layla1','01c4c0092dc6f090f2d58115c9df6aaebdd5adc595df12bd5dffcc8eaae33006','Layla','Hussein','layla.hussein@example.com'),(25,'omar1','6a158d9847a80e99511b2a7866233e404b305fdb7c953a30deb65300a57a0655','Omar','Abdulaziz','omar.abdulaziz@example.com'),(26,'nadia1','e05f79651d465214e7558a382ed0f0e5a77380a649f4573f3a1036dc4ee10c0b','Nadia','Salim','nadia.salim@example.com'),(27,'hassan1','6e9f78c1c24acdee688a360f1212c9b9989e7469d6a6e39e4ed7ca279f0c7846','Hassan','Amin','hassan.amin@example.com'),(28,'reem1','bd841f1a7ab405a7f3cb0784e96b580a178228d2a6b8fbad291996db34e79c3d','Reem','Mohammed','reem.mohammed@example.com'),(29,'maha1','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','Maha','Kamal','maha.kamal@example.com'),(30,'ali2','c6ba91b90d922e159893f46c387e5dc1b3dc5c101a5a4522f03b987177a24a91','Ali','Ibrahim','ali.ibrahim@example.com'),(31,'nada1','ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae','Nada','Salem','nada.salem@example.com'),(32,'hassan2','e9cee71ab932fde863338d08be4de9dfe39ea049bdafb342ce659ec5450b69ae','Hassan','Rashid','hassan.rashid@example.com'),(33,'layla2','b9c950640e1b3740e98acb93e669c65766f6670dd1609ba91ff41052ba48c6f3','Layla','Omar','layla.omar@example.com'),(34,'mohammed2','daaad6e5604e8e17bd9f108d91e26afe6281dac8fda0091040a7a6d7bd9b43b5','Mohammed','Hamed','mohammed.hamed@example.com'),(35,'salma1','9b8769a4a742959a2d0298c36fb70623f2dfacda8436237df08d8dfd5b37374c','Salma','Saeed','salma.saeed@example.com'),(36,'ahmed2','3700adf1f25fab8202c1343c4b0b4e3fec706d57cad574086467b8b3ddf273ec','Ahmed','Nasser','ahmed.nasser@example.com'),(37,'amira1','6fec2a9601d5b3581c94f2150fc07fa3d6e45808079428354b868e412b76e6bb','Amira','Abdelrahman','amira.abdelrahman@example.com'),(38,'youssef1','fa1de4364cfd94d75e7bda5d0583bcb136d6437c88a36dc06bcd64566a3530ae','Youssef','Mahmoud','youssef.mahmoud@example.com'),(39,'sarah1','d5ab3dfc82bc9a45351f8fa469028e51728fb2d915495e15e4b7d720c961cf4a','Sarah','Saad','sarah.saad@example.com'),(40,'reem2','1c97290885b4be9d60aeb14c12d4d80fcd1586ced411a08efca7e6be4e1a6c5a','Reem','Khalifa','reem.khalifa@example.com'),(41,'ibrahim1','bd94dcda26fccb4e68d6a31f9b5aac0b571ae266d822620e901ef7ebe3a11d4f','Ibrahim','Mohammed','ibrahim.mohammed@example.com'),(42,'laila1','f6ee94ecb014f74f887b9dcc52daecf73ab3e3333320cadd98bcb59d895c52f5','Laila','Salem','laila.salem@example.com'),(43,'mohammed3','10a5eb6cbcff0123f6039820bab1b2f31fc03dca2505a2f8bfa36a37ff7b5559','Mohammed','Hassan','mohammed.hassan@example.com'),(44,'nada2','66bdddf2fd86fd31b40393be9794210ad89107663fb5411c6b63d7963262677a','Nada','Ali','nada.ali@example.com'),(45,'karim1','b6d770c4fc6eae5f552260a719756b7071efc6aa53065af46cf684500f7b49fb','Karim','Ahmed','karim.ahmed@example.com'),(46,'admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','Admin','Admin','adming@example.com'),(47,'sf','2df44271fb4e3dff8d32905bb983cba17112f8ea76ad368e969c85927f123423','SF','SF','sf@example.com');
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

-- Dump completed on 2023-06-08 15:37:47
