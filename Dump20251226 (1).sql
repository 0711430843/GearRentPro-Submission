CREATE DATABASE  IF NOT EXISTS `gearrentpro` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gearrentpro`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: gearrentpro
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `branches`
--

DROP TABLE IF EXISTS `branches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branches` (
  `branch_id` int NOT NULL AUTO_INCREMENT,
  `branch_code` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` text,
  `contact_no` varchar(20) DEFAULT NULL,
  `branch_manager_id` int DEFAULT NULL,
  PRIMARY KEY (`branch_id`),
  UNIQUE KEY `branch_code` (`branch_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branches`
--

LOCK TABLES `branches` WRITE;
/*!40000 ALTER TABLE `branches` DISABLE KEYS */;
INSERT INTO `branches` VALUES (1,'BR001','Colombo Main','77 Galle Rd, Colombo','0112345678',NULL),(2,'BR002','Kandy Branch','12 Peradeniya Rd, Kandy','0812345678',NULL),(3,'BR003','Galle Branch','45 Matara Rd, Galle','0912345678',NULL);
/*!40000 ALTER TABLE `branches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `nic_passport` varchar(50) NOT NULL,
  `contact_no` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` text,
  `membership_level` enum('Regular','Silver','Gold') DEFAULT 'Regular',
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `nic_passport` (`nic_passport`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Arjun Perera','199012345V','0771234561',NULL,NULL,'Gold'),(2,'Dilani Silva','199256789V','0771234562',NULL,NULL,'Silver'),(3,'Kamal Gunarathne','198533445V','0771234563',NULL,NULL,'Regular'),(4,'Sara Fernando','199877889V','0771234564',NULL,NULL,'Gold'),(5,'Nimal Siri','198022334V','0771234565',NULL,NULL,'Regular'),(6,'Priya Madu','199544556V','0771112226','priya@gmail.com',NULL,'Silver'),(7,'Saman Kumara','198899001V','0771112227','saman@gmail.com',NULL,'Regular'),(8,'Ishini De Silva','199322334V','0771112228','ishini@gmail.com',NULL,'Gold'),(9,'Ruwan Bandara','199144332V','0771112229','ruwan@gmail.com',NULL,'Regular'),(10,'Yashoda Jay','199677885V','0771112230','yash@gmail.com',NULL,'Silver');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment` (
  `equipment_id` int NOT NULL AUTO_INCREMENT,
  `category_id` int NOT NULL,
  `branch_id` int NOT NULL,
  `brand` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `purchase_year` year NOT NULL,
  `base_daily_price` decimal(10,2) NOT NULL,
  `security_deposit` decimal(10,2) NOT NULL,
  `current_status` enum('Available','Reserved','Rented','Under Maintenance') DEFAULT 'Available',
  PRIMARY KEY (`equipment_id`),
  KEY `category_id` (`category_id`),
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `equipment_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `equipment_categories` (`category_id`),
  CONSTRAINT `equipment_ibfk_2` FOREIGN KEY (`branch_id`) REFERENCES `branches` (`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (1,1,1,'Sony','A7 III',2023,5000.00,25000.00,'Available'),(2,1,1,'Canon','EOS R5',2022,7500.00,40000.00,'Rented'),(3,1,2,'Nikon','Z6 II',2023,5500.00,25000.00,'Available'),(4,1,3,'Fujifilm','X-T4',2021,4500.00,20000.00,'Available'),(5,2,1,'Sony','24-70mm f2.8',2023,3500.00,15000.00,'Available'),(6,2,1,'Canon','RF 50mm f1.2',2022,4000.00,18000.00,'Available'),(7,2,2,'Sigma','35mm f1.4 Art',2021,2500.00,10000.00,'Available'),(8,2,2,'Nikon','70-200mm f2.8',2022,4500.00,22000.00,'Available'),(9,3,1,'Manfrotto','055 Tripod',2020,1000.00,5000.00,'Available'),(10,3,3,'DJI','Ronin SC',2022,3000.00,12000.00,'Available'),(11,3,2,'Zhiyun','Crane 3S',2023,4000.00,15000.00,'Under Maintenance'),(12,4,1,'Godox','AD600 Pro',2021,2500.00,10000.00,'Available'),(13,4,3,'Aputure','120d Mark II',2022,3500.00,15000.00,'Available'),(14,4,1,'Profoto','B10 Plus',2023,5000.00,25000.00,'Available'),(15,5,2,'Rode','NTG3 Shotgun',2022,1500.00,7000.00,'Available'),(16,5,1,'Zoom','H6 Recorder',2021,2000.00,8000.00,'Available'),(17,5,3,'Sennheiser','G4 Wireless',2023,2500.00,10000.00,'Available'),(18,1,1,'Sony','A6400',2020,3000.00,12000.00,'Available'),(19,2,2,'Tamron','28-75mm',2021,2000.00,8000.00,'Available'),(20,4,1,'Nanlite','Pavotube 30C',2022,1200.00,5000.00,'Available'),(21,1,1,'Sony','A7 IV',2023,6000.00,30000.00,'Available'),(22,1,1,'Canon','EOS R5',2022,7500.00,40000.00,'Rented'),(23,1,2,'Nikon','Z6 II',2023,5500.00,25000.00,'Available'),(24,1,3,'Fujifilm','X-T4',2021,4500.00,20000.00,'Available'),(25,2,1,'Sony','24-70mm f2.8',2023,3500.00,15000.00,'Available'),(26,2,1,'Canon','RF 50mm f1.2',2022,4000.00,18000.00,'Available'),(27,2,2,'Sigma','35mm f1.4 Art',2021,2500.00,10000.00,'Available'),(28,2,2,'Nikon','70-200mm f2.8',2022,4500.00,22000.00,'Available'),(29,3,1,'Manfrotto','055 Tripod',2020,1000.00,5000.00,'Available'),(30,3,3,'DJI','Ronin SC',2022,3000.00,12000.00,'Available'),(31,3,2,'Zhiyun','Crane 3S',2023,4000.00,15000.00,'Under Maintenance'),(32,4,1,'Godox','AD600 Pro',2021,2500.00,10000.00,'Available'),(33,4,3,'Aputure','120d Mark II',2022,3500.00,15000.00,'Available'),(34,4,1,'Profoto','B10 Plus',2023,5000.00,25000.00,'Available'),(35,5,2,'Rode','NTG3 Shotgun',2022,1500.00,7000.00,'Available'),(36,5,1,'Zoom','H6 Recorder',2021,2000.00,8000.00,'Available'),(37,5,3,'Sennheiser','G4 Wireless',2023,2500.00,10000.00,'Available'),(38,1,1,'Sony','A6400',2020,3000.00,12000.00,'Available'),(39,2,2,'Tamron','28-75mm',2021,2000.00,8000.00,'Available'),(40,4,1,'Nanlite','Pavotube 30C',2022,1200.00,5000.00,'Available');
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment_categories`
--

DROP TABLE IF EXISTS `equipment_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment_categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text,
  `base_price_factor` decimal(5,2) NOT NULL DEFAULT '1.00',
  `weekend_multiplier` decimal(4,2) NOT NULL DEFAULT '1.00',
  `default_late_fee` decimal(10,2) DEFAULT '0.00',
  `is_active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment_categories`
--

LOCK TABLES `equipment_categories` WRITE;
/*!40000 ALTER TABLE `equipment_categories` DISABLE KEYS */;
INSERT INTO `equipment_categories` VALUES (1,'Cameras',NULL,1.00,1.20,0.00,1),(2,'Lenses',NULL,0.80,1.15,0.00,1),(3,'Tripods',NULL,0.40,1.10,0.00,1),(4,'Lighting',NULL,0.60,1.25,0.00,1),(5,'Audio',NULL,0.50,1.10,0.00,1);
/*!40000 ALTER TABLE `equipment_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membership_configs`
--

DROP TABLE IF EXISTS `membership_configs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membership_configs` (
  `level` enum('Regular','Silver','Gold') NOT NULL,
  `discount_percent` decimal(5,2) NOT NULL,
  PRIMARY KEY (`level`),
  CONSTRAINT `membership_configs_chk_1` CHECK (((`discount_percent` >= 0) and (`discount_percent` <= 100)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membership_configs`
--

LOCK TABLES `membership_configs` WRITE;
/*!40000 ALTER TABLE `membership_configs` DISABLE KEYS */;
INSERT INTO `membership_configs` VALUES ('Regular',0.00),('Silver',10.00),('Gold',20.00);
/*!40000 ALTER TABLE `membership_configs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rentals`
--

DROP TABLE IF EXISTS `rentals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rentals` (
  `rental_id` int NOT NULL AUTO_INCREMENT,
  `equipment_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `branch_id` int NOT NULL,
  `reservation_id` int DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `actual_return_date` date DEFAULT NULL,
  `calculated_rental_amount` decimal(10,2) NOT NULL,
  `security_deposit` decimal(10,2) NOT NULL,
  `membership_discount_amount` decimal(10,2) DEFAULT '0.00',
  `long_rental_discount_amount` decimal(10,2) DEFAULT '0.00',
  `final_payable_amount` decimal(10,2) NOT NULL,
  `late_fee_charged` decimal(10,2) DEFAULT '0.00',
  `damage_charge` decimal(10,2) DEFAULT '0.00',
  `damage_description` text,
  `payment_status` enum('Paid','Partially Paid','Unpaid') DEFAULT 'Unpaid',
  `rental_status` enum('Active','Returned','Overdue','Cancelled') DEFAULT 'Active',
  PRIMARY KEY (`rental_id`),
  KEY `equipment_id` (`equipment_id`),
  KEY `customer_id` (`customer_id`),
  KEY `branch_id` (`branch_id`),
  KEY `reservation_id` (`reservation_id`),
  CONSTRAINT `rentals_ibfk_1` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`equipment_id`),
  CONSTRAINT `rentals_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `rentals_ibfk_3` FOREIGN KEY (`branch_id`) REFERENCES `branches` (`branch_id`),
  CONSTRAINT `rentals_ibfk_4` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`reservation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rentals`
--

LOCK TABLES `rentals` WRITE;
/*!40000 ALTER TABLE `rentals` DISABLE KEYS */;
INSERT INTO `rentals` VALUES (1,2,1,1,NULL,'2025-12-10','2025-12-20',NULL,60000.00,40000.00,0.00,0.00,60000.00,0.00,0.00,NULL,'Unpaid','Overdue'),(2,2,1,1,NULL,'2025-12-15','2025-12-25',NULL,60000.00,40000.00,0.00,0.00,60000.00,0.00,0.00,NULL,'Unpaid','Overdue'),(3,11,3,2,NULL,'2025-12-10','2025-12-12','2025-12-12',8000.00,15000.00,0.00,0.00,10500.00,0.00,2500.00,'Leg lock loose','Unpaid','Returned'),(4,2,1,1,NULL,'2025-12-10','2025-12-20',NULL,60000.00,40000.00,0.00,0.00,60000.00,0.00,0.00,NULL,'Unpaid','Overdue'),(5,11,3,2,NULL,'2025-12-01','2025-12-05','2025-12-05',16000.00,15000.00,0.00,0.00,18500.00,0.00,2500.00,'Handle loose','Unpaid','Returned'),(6,2,1,1,NULL,'2025-12-15','2025-12-25',NULL,60000.00,40000.00,0.00,0.00,60000.00,0.00,0.00,NULL,'Unpaid','Overdue'),(7,11,3,2,NULL,'2025-12-10','2025-12-12','2025-12-12',8000.00,15000.00,0.00,0.00,10500.00,0.00,2500.00,'Leg lock loose','Unpaid','Returned');
/*!40000 ALTER TABLE `rentals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `reservation_id` int NOT NULL AUTO_INCREMENT,
  `equipment_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `branch_id` int NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `status` enum('Reserved','Cancelled\n);\n\n','Completed') NOT NULL,
  PRIMARY KEY (`reservation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `role` enum('Admin','Branch Manager','Staff') NOT NULL,
  `assigned_branch_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `assigned_branch_id` (`assigned_branch_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`assigned_branch_id`) REFERENCES `branches` (`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
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

-- Dump completed on 2025-12-26 16:56:45
