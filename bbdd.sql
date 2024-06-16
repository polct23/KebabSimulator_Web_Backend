-- MariaDB dump 10.19-11.3.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: KebabSimulatorDB
-- ------------------------------------------------------
-- Server version	11.3.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ability`
--

DROP TABLE IF EXISTS `ability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ability` (
  `idAbility` varchar(50) NOT NULL,
  `abilityName` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`idAbility`),
  UNIQUE KEY `idAbility` (`idAbility`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ability`
--

LOCK TABLES `ability` WRITE;
/*!40000 ALTER TABLE `ability` DISABLE KEYS */;
INSERT INTO `ability` VALUES
('12hhd','Cuchillo','Arma mÃ¡xima',3,9000),
('C6m243082445','VidaExtra2','Aumenta tu vida mÃ¡xima en un 30%',30,300),
('ggJw085173193627','Arma1','Aumenta tu fuerza 20',20,75),
('ghbjULV7185477361','VidaExtra1','Aumenta tu vida mÃ¡xima en un 15%',15,200),
('p34466035669','Velocidad2','Aumenta mucho tu velocidad',20,200),
('Qel5325309712031668','Arma2','Aumenta tu fuerza hasta 50',50,275),
('T_pTA066852230','Velocidad1','Aumenta un poco tu velocidad',10,100);
/*!40000 ALTER TABLE `ability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enemy`
--

DROP TABLE IF EXISTS `enemy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enemy` (
  `idEnemy` varchar(50) NOT NULL,
  `speed` int(11) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `meat` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idEnemy`),
  UNIQUE KEY `idEnemy` (`idEnemy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enemy`
--

LOCK TABLES `enemy` WRITE;
/*!40000 ALTER TABLE `enemy` DISABLE KEYS */;
INSERT INTO `enemy` VALUES
('0vfaEq3764051',10,'Enemigo con movimiento evasivo',10,'Rata'),
('bozRmi3C127560865499',3,'Enemigo mejorado con movimiento aleatorio',30,'Cucaracha'),
('fffkjsfkvn339393',3,'Enemigo inmortal con movimiento fijo',0,'Fantasma'),
('MKI06021334109',15,'Enemigo muy agresivo y peligroso',45,'Escarabajo'),
('SAJKjjns890',10,'Boss final',300,'???'),
('zWg468968',2,'Enemigo bÃ¡sico con movimiento aleatorio',10,'Slime');
/*!40000 ALTER TABLE `enemy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mission`
--

DROP TABLE IF EXISTS `mission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mission` (
  `description` longtext DEFAULT NULL,
  `reward` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mission`
--

LOCK TABLES `mission` WRITE;
/*!40000 ALTER TABLE `mission` DISABLE KEYS */;
INSERT INTO `mission` VALUES
('Matar a 5 Slimes',10),
('Recoger la madera',10),
('Matar a 3 Ratas',45),
('Busca la casa del inspector',0),
('Matar a los bichos de la cueva',100),
('Mata al inspector',300);
/*!40000 ALTER TABLE `mission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
  `idPlayer` varchar(50) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `money` double DEFAULT 0,
  `currentMission` int(11) DEFAULT 0,
  `currentLevel` int(11) DEFAULT 0,
  PRIMARY KEY (`idPlayer`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES
('M1038138','Bruno','7777','bruno@gmail.com',0,0,0);
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playersability`
--

DROP TABLE IF EXISTS `playersability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playersability` (
  `idAbility` varchar(50) DEFAULT NULL,
  `idPlayer` varchar(50) DEFAULT NULL,
  KEY `fkIdAbility` (`idAbility`),
  KEY `fkIdPlayer` (`idPlayer`),
  CONSTRAINT `fkIdAbility` FOREIGN KEY (`idAbility`) REFERENCES `ability` (`idAbility`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkIdPlayer` FOREIGN KEY (`idPlayer`) REFERENCES `player` (`idPlayer`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playersability`
--

LOCK TABLES `playersability` WRITE;
/*!40000 ALTER TABLE `playersability` DISABLE KEYS */;
INSERT INTO `playersability` VALUES
('12hhd','M1038138');
/*!40000 ALTER TABLE `playersability` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-16 20:43:12
