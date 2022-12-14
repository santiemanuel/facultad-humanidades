-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: universidad
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumnos` (
  `alumno_id` int NOT NULL AUTO_INCREMENT,
  `alumno_lu` int NOT NULL,
  `alumno_nombre` varchar(30) NOT NULL,
  `alumno_apellido` varchar(30) NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`alumno_id`),
  UNIQUE KEY `alumnos_idx` (`alumno_lu`),
  KEY `usuarios_alumnos_fk` (`id_usuario`),
  CONSTRAINT `usuarios_alumnos_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos`
--

LOCK TABLES `alumnos` WRITE;
/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
INSERT INTO `alumnos` VALUES (5,4545,'Aldo','Silvestre',2),(6,323211,'Carlo','Silvestre',3);
/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alumnosxcarreras`
--

DROP TABLE IF EXISTS `alumnosxcarreras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumnosxcarreras` (
  `axc_id` int NOT NULL AUTO_INCREMENT,
  `alumno_id` int NOT NULL,
  `carrera_id` int NOT NULL,
  PRIMARY KEY (`axc_id`),
  UNIQUE KEY `alumnosxcarreras_idx` (`alumno_id`,`carrera_id`),
  KEY `alumnosxcarreras_carreras_fk` (`carrera_id`),
  CONSTRAINT `alumnos_alumnosxcarreras_fk` FOREIGN KEY (`alumno_id`) REFERENCES `alumnos` (`alumno_id`),
  CONSTRAINT `alumnosxcarreras_carreras_fk` FOREIGN KEY (`carrera_id`) REFERENCES `carreras` (`carrera_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnosxcarreras`
--

LOCK TABLES `alumnosxcarreras` WRITE;
/*!40000 ALTER TABLE `alumnosxcarreras` DISABLE KEYS */;
INSERT INTO `alumnosxcarreras` VALUES (9,5,1),(14,5,2),(13,5,4);
/*!40000 ALTER TABLE `alumnosxcarreras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carreras`
--

DROP TABLE IF EXISTS `carreras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carreras` (
  `carrera_id` int NOT NULL AUTO_INCREMENT,
  `carrera_nombre` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`carrera_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carreras`
--

LOCK TABLES `carreras` WRITE;
/*!40000 ALTER TABLE `carreras` DISABLE KEYS */;
INSERT INTO `carreras` VALUES (1,'Profesorado Ciencias de la Educaci??n'),(2,'Profesorado en Filosof??a'),(3,'Profesorado en Historia'),(4,'Profesorado en Letras'),(5,'Licenciatura En Antropolog??a');
/*!40000 ALTER TABLE `carreras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credenciales`
--

DROP TABLE IF EXISTS `credenciales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credenciales` (
  `id_credencial` int NOT NULL AUTO_INCREMENT,
  `salt_and_hash` varchar(80) NOT NULL,
  `pass_algo` varchar(20) NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_credencial`),
  KEY `usuarios_credenciales_fk` (`id_usuario`),
  CONSTRAINT `usuarios_credenciales_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credenciales`
--

LOCK TABLES `credenciales` WRITE;
/*!40000 ALTER TABLE `credenciales` DISABLE KEYS */;
INSERT INTO `credenciales` VALUES (1,'$s0$e0801$hynUKzQmiAS3InrSvFPcfg==$8A94A/maJ7SYfJBSeTuojXaRgLxIgj/p2G5QRbeW1Sg=','scrypt',2),(2,'$s0$e0801$AmtHWi3SnayffNEC3ImfHQ==$H7nK6aU755tZtrNIOBMgvIlC/rqSe3pMKeTe+dcUn60=','scrypt',4);
/*!40000 ALTER TABLE `credenciales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallesmaterias`
--

DROP TABLE IF EXISTS `detallesmaterias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallesmaterias` (
  `detmat_id` int NOT NULL AUTO_INCREMENT,
  `alumno_id` int NOT NULL,
  `materia_id` int NOT NULL,
  PRIMARY KEY (`detmat_id`),
  UNIQUE KEY `detallesmaterias_idx` (`alumno_id`,`materia_id`),
  KEY `materias_formulariosinscripcion_fk` (`materia_id`),
  CONSTRAINT `alumnos_formulariosinscripcion_fk` FOREIGN KEY (`alumno_id`) REFERENCES `alumnos` (`alumno_id`),
  CONSTRAINT `materias_formulariosinscripcion_fk` FOREIGN KEY (`materia_id`) REFERENCES `materias` (`materia_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallesmaterias`
--

LOCK TABLES `detallesmaterias` WRITE;
/*!40000 ALTER TABLE `detallesmaterias` DISABLE KEYS */;
INSERT INTO `detallesmaterias` VALUES (6,5,19),(5,5,23),(3,5,70),(4,5,71);
/*!40000 ALTER TABLE `detallesmaterias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examenes`
--

DROP TABLE IF EXISTS `examenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examenes` (
  `examen_id` int NOT NULL AUTO_INCREMENT,
  `nota` tinyint NOT NULL,
  `mesa_examen_id` int NOT NULL,
  `alumno_id` int NOT NULL,
  PRIMARY KEY (`examen_id`),
  UNIQUE KEY `detalleactas_idx` (`mesa_examen_id`,`alumno_id`),
  KEY `alumnos_detalleactas_fk` (`alumno_id`),
  CONSTRAINT `actas_detalleactas_fk` FOREIGN KEY (`mesa_examen_id`) REFERENCES `mesas_examen` (`mesa_examen_id`),
  CONSTRAINT `alumnos_detalleactas_fk` FOREIGN KEY (`alumno_id`) REFERENCES `alumnos` (`alumno_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examenes`
--

LOCK TABLES `examenes` WRITE;
/*!40000 ALTER TABLE `examenes` DISABLE KEYS */;
INSERT INTO `examenes` VALUES (5,0,3,5);
/*!40000 ALTER TABLE `examenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materias`
--

DROP TABLE IF EXISTS `materias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materias` (
  `materia_id` int NOT NULL AUTO_INCREMENT,
  `materia_nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`materia_id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materias`
--

LOCK TABLES `materias` WRITE;
/*!40000 ALTER TABLE `materias` DISABLE KEYS */;
INSERT INTO `materias` VALUES (1,'Psicolog??a y Aprendizaje'),(2,'Historia de la Educaci??n'),(3,'Pedagog??a y Did??ctica'),(4,'Historia de la Educaci??n Argentina'),(5,'Sociolog??a de la Educaci??n'),(6,'Psicolog??a Social Did??ctica'),(7,'Estad??stica en Educaci??n'),(8,'Investigaci??n Educativa'),(9,'Planeamiento Educacional'),(10,'Pol??tica Educacional'),(11,'Organizaci??n y Administraci??n Educativa'),(12,'Tecnolog??a Educativa'),(13,'Psicolog??a en Educaci??n'),(14,'Integraci??n Educativa'),(15,'Pedagog??a Social'),(16,'Ciclo de Formaci??n Superior'),(17,'Evaluaci??n Educativa'),(18,'Estrategias Did??cticas'),(19,'Pr??ctica Profesional'),(20,'Introducci??n a la Literatura'),(21,'Teor??a y Pr??ctica de la Argumentaci??n'),(22,'L??gica'),(23,'Comprensi??n y producci??n de texto'),(24,'Historia de la Filosof??a Antigua'),(25,'??tica'),(26,'Est??tica'),(27,'Antropolog??a Filos??fica'),(28,'Pensamiento Argentino y Latinoamericano'),(29,'Filosof??a de la Historia'),(30,'Historia de la Filosof??a Medieval'),(31,'Filosof??a y Teor??a Pol??tica'),(32,'Filosof??a del Lenguaje'),(33,'Psicolog??a General'),(34,'Teor??as Psicol??gicas del Aprendizaje'),(35,'Historia de la Filosof??a Moderna'),(36,'Gnoseolog??a'),(37,'Filosof??a de la Ciencia'),(38,'Metaf??sica'),(39,'Derecho Constitucional'),(40,'Historia Antigua'),(41,'Historia Medieval'),(42,'Historia Moderna'),(43,'Historia Contempor??nea'),(44,'Historia de Am??rica'),(45,'Historia Argentina'),(46,'Historia Regional'),(47,'Espacio y Sociedad'),(48,'Comprensi??n y Producci??n de Textos'),(49,'Problem??tica del Conocimiento Cient??fico'),(50,'Historiograf??a'),(51,'Metodolog??a de la Investigaci??n Hist??rica'),(52,'Teor??as del Aprendizaje'),(53,'Lengua Espa??ola'),(54,'Lengua y Cultura Latinas'),(55,'Historia de la Lengua Espa??ola'),(56,'Sem??ntica y Pragm??tica'),(57,'Ling????stica del Texto'),(58,'Psicoling????stica'),(59,'Socioling????stica'),(60,'Teor??a Literaria'),(61,'Literatura Espa??ola'),(62,'Literatura Hispanoamericana'),(63,'Literatura Argentina'),(64,'Literatura Extranjera'),(65,'Literatura Cl??sica Grecolatina'),(66,'Did??ctica de la Lengua y la Literatura'),(67,'Investigaci??n'),(68,'Fundamentos de Antropolog??a Social'),(69,'Antropolog??a y Problem??tica Regional'),(70,'Fundamentos de Prehistoria y Arqueolog??a'),(71,'Fundamentos de Antropolog??a Biol??gica'),(72,'Teor??a e Historia de la Antropolog??a'),(73,'Arqueolog??a Americana'),(74,'Econom??a Pol??tica'),(75,'Etnograf??a Americana'),(76,'Estad??stica'),(77,'Procesos Sociales de Am??rica'),(78,'Idioma Moderno Ingl??s'),(79,'Ciencias Sociales'),(80,'Arqueolog??a Argentina'),(81,'Antropolog??a Econ??mica'),(82,'Organizaci??n Social'),(83,'M??todos y T??cnicas de la Investigaci??n'),(84,'Teor????s del Desarrollo Capitalista'),(85,'Antropolog??a Ecol??gica'),(86,'Antropolog??a Pol??tica'),(87,'Relaciones Inter??tnicas'),(88,'Idioma Moderno'),(89,'Instituciones y Grupos'),(90,'Introducci??n a la Historia'),(91,'Introducci??n a la Filosof??a'),(92,'Psicolog??a del Desarrollo'),(93,'Did??ctica General'),(94,'Introducci??n a la Historia de las Sociedades'),(95,'Sociolog??a');
/*!40000 ALTER TABLE `materias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materiasxcarreras`
--

DROP TABLE IF EXISTS `materiasxcarreras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materiasxcarreras` (
  `mxc_id` int NOT NULL AUTO_INCREMENT,
  `materia_id` int NOT NULL,
  `carrera_id` int NOT NULL,
  PRIMARY KEY (`mxc_id`),
  UNIQUE KEY `materiasxcarreras_idx` (`materia_id`,`carrera_id`),
  KEY `carreras_carrerasxmaterias_fk` (`carrera_id`),
  CONSTRAINT `carreras_carrerasxmaterias_fk` FOREIGN KEY (`carrera_id`) REFERENCES `carreras` (`carrera_id`),
  CONSTRAINT `materias_carrerasxmaterias_fk` FOREIGN KEY (`materia_id`) REFERENCES `materias` (`materia_id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiasxcarreras`
--

LOCK TABLES `materiasxcarreras` WRITE;
/*!40000 ALTER TABLE `materiasxcarreras` DISABLE KEYS */;
INSERT INTO `materiasxcarreras` VALUES (1,1,1),(2,2,1),(4,3,1),(5,4,1),(7,5,1),(8,6,1),(9,7,1),(10,8,1),(11,9,1),(12,10,1),(15,11,1),(16,12,1),(17,13,1),(18,14,1),(19,15,1),(20,16,1),(21,17,1),(22,18,1),(23,19,1),(26,20,2),(27,21,2),(28,22,2),(29,23,2),(30,24,2),(31,25,2),(32,26,2),(33,27,2),(34,28,2),(35,29,2),(36,30,2),(37,31,2),(38,32,2),(40,33,2),(41,34,2),(42,35,2),(43,36,2),(44,37,2),(47,38,2),(49,39,2),(51,40,3),(52,41,3),(53,42,3),(54,43,3),(55,44,3),(56,45,3),(57,46,3),(59,47,3),(60,48,3),(61,49,3),(62,50,3),(65,52,3),(67,53,4),(68,54,4),(69,55,4),(70,56,4),(71,57,4),(72,58,4),(73,59,4),(74,60,4),(75,61,4),(76,62,4),(77,63,4),(78,64,4),(79,65,4),(80,66,4),(83,67,4),(86,68,5),(87,69,5),(88,70,5),(89,71,5),(90,72,5),(91,73,5),(92,74,5),(93,75,5),(95,76,5),(96,77,5),(97,78,5),(98,79,5),(99,80,5),(100,81,5),(101,82,5),(102,83,5),(103,84,5),(104,85,5),(105,86,5),(106,87,5),(14,88,1),(39,88,2),(63,88,3),(84,88,4),(13,89,1),(48,89,2),(25,90,2),(50,90,3),(24,91,2),(58,91,3),(81,91,4),(6,92,1),(45,92,2),(64,92,3),(46,93,2),(66,93,3),(82,94,4),(85,94,5),(3,95,1),(94,95,5);
/*!40000 ALTER TABLE `materiasxcarreras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesas_examen`
--

DROP TABLE IF EXISTS `mesas_examen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mesas_examen` (
  `mesa_examen_id` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `mxc_id` int NOT NULL,
  PRIMARY KEY (`mesa_examen_id`),
  KEY `materiasxcarreras_actas_fk` (`mxc_id`),
  CONSTRAINT `materiasxcarreras_actas_fk` FOREIGN KEY (`mxc_id`) REFERENCES `materiasxcarreras` (`mxc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesas_examen`
--

LOCK TABLES `mesas_examen` WRITE;
/*!40000 ALTER TABLE `mesas_examen` DISABLE KEYS */;
INSERT INTO `mesas_examen` VALUES (2,'2022-10-07',51),(3,'2022-07-10',23),(4,'2022-07-10',24),(5,'2022-07-10',22);
/*!40000 ALTER TABLE `mesas_examen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `email_usuario` varchar(50) NOT NULL,
  `rol_admin` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (2,'aldo@gmail.com',0),(3,'carlos@gmail.com',0),(4,'santi@gmail.com',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'universidad'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-08 15:52:46
