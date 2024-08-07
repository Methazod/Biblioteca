-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genero` (
  `id_genero` int unsigned NOT NULL AUTO_INCREMENT,
  `genero` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_genero`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES (1,'Novela negra, thriller o suspense'),(2,'Novela histórica'),(3,'Ficción Literaria'),(4,'Romántica'),(5,'Ciencia ficción'),(6,'Distopía'),(7,'Aventuras'),(8,'Fantasía'),(9,'Contemporáneo'),(10,'Romantasy'),(11,'Mitológico'),(12,'Terror'),(13,'Paranormal'),(14,'Poesía'),(15,'Juvenil'),(16,'Infantil'),(17,'Autoayuda y superación personal'),(18,'Salud y deporte'),(19,'Libros prácticos o manuales'),(20,'Memorias'),(21,'Biografías'),(22,'Cocina'),(23,'Viajes'),(24,'Libros técnicos y especializados'),(25,'De consulta y referencia'),(26,'Divulgativos'),(27,'Libros de texto'),(28,'Arte');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `id_libro` int unsigned NOT NULL AUTO_INCREMENT,
  `titulo` varchar(200) DEFAULT NULL,
  `autor` varchar(200) DEFAULT NULL,
  `fecha_publicacion` date DEFAULT NULL,
  `numero_paginas` int unsigned DEFAULT NULL,
  `genero` int unsigned DEFAULT NULL,
  `saga` varchar(100) DEFAULT NULL,
  `leido` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_libro`),
  KEY `genero` (`genero`),
  CONSTRAINT `libro_ibfk_1` FOREIGN KEY (`genero`) REFERENCES `genero` (`id_genero`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (1,'Psique y Eros','Luna McNamara','2023-06-13',352,9,NULL,1),(2,'Una corte de rosas y espinas','Sarah J. Maas','2015-05-05',464,10,'ACOTAR',1),(3,'Una corte de niebla y furia','Sarah J. Maas','2016-04-22',592,10,'ACOTAR',1),(4,'Una corte de alas y ruina','Sarah J. Maas','2017-05-02',672,10,'ACOTAR',1),(5,'Una corte de hielo y estrellas','Sarah J. Maas','2018-05-01',272,10,'ACOTAR',1),(6,'Una corte de llamas plateadas','Sarah J. Maas','2021-02-16',688,10,'ACOTAR',1),(7,'Los siete maridos de Evelyn Hugo','Taylor Jenkins Reid','2017-06-13',384,9,NULL,1),(8,'Un mundo feliz','Aldous Huxley','1932-01-01',256,6,NULL,0),(9,'Donde no puedas encontrarme','Tamara Molina','2024-05-08',456,4,NULL,0),(10,'El Imperio Final','Brandon Sanderson','2006-07-17',688,8,'MistBorn',0),(11,'Trono de Cristal','Sarah J. Maas','2012-08-02',528,8,'Trono de Cristal',0),(12,'Corona de Medianoche','Sarah J. Maas','2013-08-15',512,8,'Trono de Cristal',0),(13,'La espada del asesino','Sarah J. Maas','2014-03-04',512,8,'Trono de Cristal',0),(14,'Heredera de Fuego','Sarah J. Maas','2014-09-02',672,8,'Trono de Cristal',0),(15,'Reina de Sombras','Sarah J. Maas','2015-09-01',600,8,'Trono de Cristal',0),(16,'Imperio de Tormentas','Sarah J. Maas','2016-09-06',816,8,'Trono de Cristal',0),(17,'Torre del Alba','Sarah J. Maas','2017-09-05',704,8,'Trono de Cristal',0),(18,'Reino de Cenizas','Sarah J. Maas','2018-10-23',960,8,'Trono de Cristal',0),(19,'El viento conoce mi nombre','Isabel Allende','1982-01-01',352,3,NULL,0),(20,'Llámame por tu nombre','André Aciman','2007-01-23',280,4,NULL,0);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'biblioteca'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-07 13:33:18
