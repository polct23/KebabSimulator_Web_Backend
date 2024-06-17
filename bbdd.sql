-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         11.3.2-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando estructura para tabla kebabsimulatordb.ability
CREATE TABLE IF NOT EXISTS `ability` (
  `idAbility` varchar(50) NOT NULL,
  `abilityName` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `imageURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idAbility`),
  UNIQUE KEY `idAbility` (`idAbility`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla kebabsimulatordb.enemy
CREATE TABLE IF NOT EXISTS `enemy` (
  `idEnemy` varchar(50) NOT NULL,
  `speed` int(11) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `meat` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idEnemy`),
  UNIQUE KEY `idEnemy` (`idEnemy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla kebabsimulatordb.mission
CREATE TABLE IF NOT EXISTS `mission` (
  `description` varchar(50) DEFAULT NULL,
  `reward` int(11) DEFAULT NULL,
  `idMission` varchar(50) NOT NULL,
  PRIMARY KEY (`idMission`),
  UNIQUE KEY `idMission` (`idMission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla kebabsimulatordb.player
CREATE TABLE IF NOT EXISTS `player` (
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

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla kebabsimulatordb.playersability
CREATE TABLE IF NOT EXISTS `playersability` (
  `idAbility` varchar(50) DEFAULT NULL,
  `idPlayer` varchar(50) DEFAULT NULL,
  KEY `fkIdAbility` (`idAbility`),
  KEY `fkIdPlayer` (`idPlayer`),
  CONSTRAINT `fkIdAbility` FOREIGN KEY (`idAbility`) REFERENCES `ability` (`idAbility`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkIdPlayer` FOREIGN KEY (`idPlayer`) REFERENCES `player` (`idPlayer`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- La exportación de datos fue deseleccionada.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
