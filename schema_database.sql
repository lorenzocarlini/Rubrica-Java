-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: rubrica_db
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.22.04.1

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
-- Table structure for table `Persona`
--

DROP TABLE IF EXISTS `Persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Persona` (
  `id` int NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `indirizzo` varchar(100) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `eta` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Persona`
--

LOCK TABLES `Persona` WRITE;
/*!40000 ALTER TABLE `Persona` DISABLE KEYS */;
INSERT INTO `Persona` VALUES (1,'Mario','Rossi','Via Roma 10','3201234567',25),(2,'Luigi','Russo','Corso Italia 22','3211234567',30),(3,'Anna','Lamborghini','Via Milano 33','3221234567',36),(4,'Giulia','Esposito','Piazza Garibaldi 44','3231234567',40),(5,'Marco','Bianchi','Viale Europa 55','3241234567',45),(6,'Alessandro','Romano','Via Torino 66','3251234567',50),(7,'Francesca','Colombo','Corso Francia 77','3261234567',55),(8,'Chiara','Ricci','Piazza Duomo 88','3271234567',60),(9,'Giovanni','Marino','Via Venezia 99','3281234567',65),(10,'Simone','Greco','Corso Vittorio Emanuele 100','3291234567',70),(11,'Laura','Bruno','Via Dante 101','3301234567',75),(12,'Sara','Gallo','Piazza San Giovanni 102','3311234567',80),(13,'Matteo','Conti','Via Verdi 103','3321234567',85),(14,'Federico','De Luca','Corso Buenos Aires 104','3331234567',90),(15,'Roberta','Mancini','Via Mazzini 105','3341234567',95),(16,'Elena','Costa','Piazza della Repubblica 106','3351234567',28),(17,'Davide','Giordano','Via Nazionale 107','3361234567',33),(18,'Alberto','Rizzo','Corso della Libertà 108','3371234567',38),(19,'Silvia','Lombardi','Via del Corso 109','3381234567',43),(20,'Valentina','Moretti','Piazza Venezia 110','3391234567',48),(21,'Paolo','Barbieri','Via Cavour 111','3401234567',53),(22,'Andrea','Fontana','Corso Umberto I 112','3411234567',58),(23,'Martina','Santoro','Via XX Settembre 113','3421234567',63),(24,'Lorenzo','Mariani','Piazza Dante 114','3431234567',68),(25,'Elisa','Rinaldi','Via Partenope 115','3441234567',73),(26,'Giorgia','Caruso','Corso Mediterraneo 116','3451234567',78),(27,'Antonio','Ferrara','Via del Mare 117','3461234567',83),(28,'Riccardo','Galli','Piazza Marconi 118','3471234567',88),(29,'Barbara','Martini','Via Tasso 119','3481234567',93),(30,'Nicola','Leone','Corso Garibaldi 120','3491234567',98);
/*!40000 ALTER TABLE `Persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Utente`
--

DROP TABLE IF EXISTS `Utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Utente` (
  `username` varchar(25) NOT NULL,
  `password` varchar(300) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Utente`
--

LOCK TABLES `Utente` WRITE;
/*!40000 ALTER TABLE `Utente` DISABLE KEYS */;
INSERT INTO `Utente` VALUES ('Admin','e6c83b282aeb2e022844595721cc00bbda47cb24537c1779f9bb84f04039e1676e6ba8573e588da1052510e3aa0a32a9e55879ae22b0c2d62136fc0a3e85f8bb'),('Mario','503697128ceffe3d991546d9896275798a7bd458c6a32039d35ff3c3e8dd000466a4041c54eacf2d179b3f99a6491bc49c8405e6a86161393d219de510380c34');
/*!40000 ALTER TABLE `Utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-02 20:43:43
