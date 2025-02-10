-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: gestao_de_curso
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso` (
  `codcurso` int NOT NULL AUTO_INCREMENT,
  `assunto` varchar(1000) NOT NULL,
  `qnt_encontro` int NOT NULL,
  `nom_curso` varchar(150) NOT NULL,
  `situacao` enum('Ativo','Inativo') NOT NULL,
  PRIMARY KEY (`codcurso`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (1,'Aprenda java!',10,'Java','Ativo'),(2,'123',12,'testeeeeeeeeee','Ativo'),(3,'Sim!',15,'A história do Java','Ativo'),(4,'O Java',10,'Java o retorno','Ativo'),(5,'O curso',10,'Java 4 - O inimigo contra ataca','Ativo');
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso_nota`
--

DROP TABLE IF EXISTS `curso_nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso_nota` (
  `est_nota` int NOT NULL AUTO_INCREMENT,
  `codcurso` int NOT NULL,
  `est_curso` int NOT NULL,
  `tip_nota` enum('NOTA1','NOTA2','NOTA3') DEFAULT NULL,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`est_nota`),
  KEY `FKrt8lnc7bt57rbdd9yk2obrb06` (`codcurso`),
  KEY `FKbn6vgmydv774n954vi0166d05` (`est_curso`),
  CONSTRAINT `FKbn6vgmydv774n954vi0166d05` FOREIGN KEY (`est_curso`) REFERENCES `estudante_curso` (`est_curso`),
  CONSTRAINT `FKrt8lnc7bt57rbdd9yk2obrb06` FOREIGN KEY (`codcurso`) REFERENCES `curso` (`codcurso`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso_nota`
--

LOCK TABLES `curso_nota` WRITE;
/*!40000 ALTER TABLE `curso_nota` DISABLE KEYS */;
INSERT INTO `curso_nota` VALUES (28,1,4,'NOTA1',5),(29,1,9,'NOTA1',1),(30,1,6,'NOTA1',1),(31,1,2,'NOTA1',1),(32,1,4,'NOTA1',9.5),(33,1,9,'NOTA2',1);
/*!40000 ALTER TABLE `curso_nota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso_presenca`
--

DROP TABLE IF EXISTS `curso_presenca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso_presenca` (
  `cur_presenca` int NOT NULL AUTO_INCREMENT,
  `codcurso` int NOT NULL,
  `est_curso` int NOT NULL,
  `presenca` enum('Falta','PRESENCA') DEFAULT NULL,
  PRIMARY KEY (`cur_presenca`),
  KEY `FK4rfxdmp5gb9nh5gmd5y7mv147` (`codcurso`),
  KEY `FKluhx5ms2fokx4vrs39fbnpay6` (`est_curso`),
  CONSTRAINT `FK4rfxdmp5gb9nh5gmd5y7mv147` FOREIGN KEY (`codcurso`) REFERENCES `curso` (`codcurso`),
  CONSTRAINT `FKluhx5ms2fokx4vrs39fbnpay6` FOREIGN KEY (`est_curso`) REFERENCES `estudante_curso` (`est_curso`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso_presenca`
--

LOCK TABLES `curso_presenca` WRITE;
/*!40000 ALTER TABLE `curso_presenca` DISABLE KEYS */;
INSERT INTO `curso_presenca` VALUES (1,1,4,'PRESENCA'),(2,1,4,'PRESENCA'),(3,1,4,'PRESENCA'),(4,1,4,'PRESENCA'),(5,1,4,'PRESENCA'),(6,1,4,'PRESENCA'),(7,1,4,'PRESENCA'),(8,1,4,'PRESENCA'),(9,1,4,'PRESENCA'),(10,1,4,'PRESENCA');
/*!40000 ALTER TABLE `curso_presenca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudante_curso`
--

DROP TABLE IF EXISTS `estudante_curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudante_curso` (
  `est_curso` int NOT NULL AUTO_INCREMENT,
  `codcurso` int NOT NULL,
  `codpessoa` int NOT NULL,
  PRIMARY KEY (`est_curso`),
  KEY `FK1sphaml52q8yx3yiy76muojwb` (`codcurso`),
  KEY `FKeo71ko41racmbh592synukh64` (`codpessoa`),
  CONSTRAINT `FK1sphaml52q8yx3yiy76muojwb` FOREIGN KEY (`codcurso`) REFERENCES `curso` (`codcurso`),
  CONSTRAINT `FKeo71ko41racmbh592synukh64` FOREIGN KEY (`codpessoa`) REFERENCES `pessoa` (`codpessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudante_curso`
--

LOCK TABLES `estudante_curso` WRITE;
/*!40000 ALTER TABLE `estudante_curso` DISABLE KEYS */;
INSERT INTO `estudante_curso` VALUES (2,1,4),(4,1,6),(6,1,12),(7,2,3),(8,3,3),(9,1,11),(11,3,6),(14,1,3),(15,4,3),(16,5,2);
/*!40000 ALTER TABLE `estudante_curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `codpessoa` int NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `email` varchar(250) NOT NULL,
  `nome` varchar(500) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `usuario` varchar(20) NOT NULL,
  `role` enum('ADMIN','ESTUDANTE','PROFESSOR') DEFAULT NULL,
  PRIMARY KEY (`codpessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,_binary '','10933238924','hansjorg.legall@example.com','admin','$2a$10$PQcZaZ2JWmhxLaJM9NLb8uxaBtIDrjq.4WaKohIBQS9Uf4Q8BsEpi','079 513 21 78','admin','ADMIN'),(2,_binary '','11111111112','jenny.howard@example.com','professor','$2a$10$Wt/H3c1ePRCpYdQs/sUeU.6Z6sfHJm1oHlUS9OAnSZndqkw3ZTXbW','017687 76400','professor','ESTUDANTE'),(3,_binary '\0','22222222222','ahlam.tuinenburg@example.com','estudante','$2a$10$47Oz3IRA.uMumglSKkvW9OOxmsJRxyvGVgll494vtVmOHq1WGQOCu','(073) 4685636','estudante','ESTUDANTE'),(4,_binary '','22222222223','mrym.ysmy@example.com','estudante2','$2a$10$cyhyhGDgyimJq68yyiRdIedTaC./Ahmblt9XJFDR8HnS5yugtPneW','052-39704897','estudante2','ESTUDANTE'),(5,_binary '','23222222222','grayson.thomas@example.com','professor2','$2a$10$2rxCrBniKyUkH4IxI1KSTuW.lEZeFjGGXMkQ7UmYEIdSX0xG1HPpu','(117)-410-7055','professor2','PROFESSOR'),(6,_binary '','23322211111','adam.harris@example.com','Adam Harris','$2a$10$v5akFHqo16KJd/e8KwXESu0iNm7L2ThdGkQM6oScg1qTYVKjgD5gC','Y78 H99-6045','estudante3','ESTUDANTE'),(7,_binary '','12312312323','kay.wallace@example.com','admin2','$2a$10$Zv1Oai/Qu.sPbxtS2Wu.3egJtICQGIuqLW/AuYvi4//55KuGxSwLO','09-2509-0909','admin2','ADMIN'),(8,_binary '','12345231233','charlie.margaret@example.com','teste','$2a$10$k56xj4ZLsOhAeZxB0TBibOop9Pf1j3g8ZvkbbENYzp1gWaAKYOBcW','S87 O70-5709','teste','PROFESSOR'),(11,_binary '\0','22223333333','philip.pierce@example.com','Philip Pierce','$2a$10$wJIBoIyFumagahRbAYtsX.82t945fMbthQtTMFC4EnN8BpOX/FtAm','015395 35335','estudante4','ESTUDANTE'),(12,_binary '','12233222222','akseli.lehto@example.com','Akseli Lehto','$2a$10$Qr.jpNjnke8C6YAmKd.v2OQ4Z9ymLygPXNJTz38ooXxCkkqWnpB.W','07-668-727','estudante5','ESTUDANTE');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa_endereco`
--

DROP TABLE IF EXISTS `pessoa_endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa_endereco` (
  `pes_endereco` int NOT NULL AUTO_INCREMENT,
  `cep` varchar(255) NOT NULL,
  `cidade` varchar(150) NOT NULL,
  `num` int NOT NULL,
  `rua` varchar(150) NOT NULL,
  `codpessoa` int NOT NULL,
  PRIMARY KEY (`pes_endereco`),
  UNIQUE KEY `UK4p0bwje1pqq119ewkxa265cv3` (`codpessoa`),
  CONSTRAINT `FKolbr9hc4fj3bfqyqjhnwvuqa8` FOREIGN KEY (`codpessoa`) REFERENCES `pessoa` (`codpessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa_endereco`
--

LOCK TABLES `pessoa_endereco` WRITE;
/*!40000 ALTER TABLE `pessoa_endereco` DISABLE KEYS */;
INSERT INTO `pessoa_endereco` VALUES (1,'22222222','Cacador',1,'qqqqqq',1),(3,'123','cacaodr',12,'1',6),(4,'12312312','asd',11111111,'1231',7),(5,'12232-333','TEste Lândia',12,'aaaaaass',8);
/*!40000 ALTER TABLE `pessoa_endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor_curso`
--

DROP TABLE IF EXISTS `professor_curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor_curso` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codcurso` int NOT NULL,
  `codpessoa` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKeop3a5jqf1wnmdsy6b5b22rp0` (`codcurso`),
  KEY `FK5724ntcvlvh9j7hvdi5eo37p4` (`codpessoa`),
  CONSTRAINT `FK5724ntcvlvh9j7hvdi5eo37p4` FOREIGN KEY (`codpessoa`) REFERENCES `pessoa` (`codpessoa`),
  CONSTRAINT `FKduc0tbvcd0d1cptwc1mc2b26s` FOREIGN KEY (`codcurso`) REFERENCES `curso` (`codcurso`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor_curso`
--

LOCK TABLES `professor_curso` WRITE;
/*!40000 ALTER TABLE `professor_curso` DISABLE KEYS */;
INSERT INTO `professor_curso` VALUES (1,1,2),(2,2,2),(5,3,5),(6,4,2),(7,5,2);
/*!40000 ALTER TABLE `professor_curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'gestao_de_curso'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-10  1:48:49
