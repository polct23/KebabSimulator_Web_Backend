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

-- Volcando datos para la tabla kebabsimulatordb.ability: ~6 rows (aproximadamente)
INSERT INTO `ability` (`idAbility`, `abilityName`, `description`, `value`, `price`, `imageURL`) VALUES
	('AA1', 'Vida Extra 1', 'Aumenta tu vida máxima en un 15%', 15, 200, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-Yhj3T0kNU56QWBDfIm3PTRFW1qYVOG6MSw&s'),
	('AA2', 'Vida Extra 2', 'Aumenta tu vida máxima en un 30%', 30, 300, 'https://i.pinimg.com/564x/95/2c/2a/952c2a8111315b098da9928079fa7353.jpg'),
	('AA3', 'Velocidad 1', 'Aumenta un poco tu velocidad', 10, 100, 'https://images.vexels.com/media/users/3/131405/isolated/lists/eba88a6668c8f1d42dab4097cc99851d-hombre-corriendo-silueta-16.png'),
	('AA4', 'Velocidad 2', 'Aumenta mucho tu velocidad', 20, 200, 'https://img.freepik.com/vector-premium/hombre-corriendo-vector-blanco-negro_753733-81.jpg'),
	('AA5', 'Arma 1', 'Aumenta tu fuerza 20 puntos', 20, 75, 'https://thumbs.dreamstime.com/b/icono-del-vector-de-la-silueta-espada-objeto-aislado-132073721.jpg'),
	('AA6', 'Arma 2', 'Aumenta tu fuerza hasta 50 puntos', 50, 275, 'https://previews.123rf.com/images/zolotonsmailru/zolotonsmailru2009/zolotonsmailru200901411/154853375-silueta-de-una-espada-ilustraci%C3%B3n-de-sables.jpg');

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

-- Volcando datos para la tabla kebabsimulatordb.enemy: ~6 rows (aproximadamente)
INSERT INTO `enemy` (`idEnemy`, `speed`, `description`, `meat`, `name`) VALUES
	('XX1', 15, 'Enemigo muy agresivo y peligroso', 45, 'Escarabajo'),
	('XX2', 3, 'Enemigo mejorado con movimiento aleatorio', 30, 'Cucaracha'),
	('XX3', 3, 'Enemigo inmortal con movimiento fijo', 0, 'Fantasma'),
	('XX4', 10, 'Enemigo con movimiento evasivo', 10, 'Rata'),
	('XX5', 2, 'Enemigo básico con movimiento aleatorio', 10, 'Slime'),
	('XX6', 10, 'Jefe final', 300, '???');

-- Volcando estructura para tabla kebabsimulatordb.mission
CREATE TABLE IF NOT EXISTS `mission` (
  `description` varchar(50) DEFAULT NULL,
  `reward` int(11) DEFAULT NULL,
  `idMission` varchar(50) NOT NULL,
  PRIMARY KEY (`idMission`),
  UNIQUE KEY `idMission` (`idMission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla kebabsimulatordb.mission: ~6 rows (aproximadamente)
INSERT INTO `mission` (`description`, `reward`, `idMission`) VALUES
	('Matar a 5 Slimes', 10, '1'),
	('Recoger la madera', 10, '2'),
	('Matar a 3 ratas', 45, '3'),
	('Buscar la casa del Inspector', 0, '4'),
	('Matar a los bichos de la cueva', 100, '5'),
	('Matar al Inspector', 300, '6');

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

-- Volcando datos para la tabla kebabsimulatordb.player: ~1 rows (aproximadamente)
INSERT INTO `player` (`idPlayer`, `userName`, `password`, `email`, `money`, `currentMission`, `currentLevel`) VALUES
	('QaMbq4R4h50451237155', 'didac', '1234', 'didac@dsa.upc', 0, 0, 0);

-- Volcando estructura para tabla kebabsimulatordb.playersability
CREATE TABLE IF NOT EXISTS `playersability` (
  `idAbility` varchar(50) DEFAULT NULL,
  `idPlayer` varchar(50) DEFAULT NULL,
  KEY `fkIdAbility` (`idAbility`),
  KEY `fkIdPlayer` (`idPlayer`),
  CONSTRAINT `fkIdAbility` FOREIGN KEY (`idAbility`) REFERENCES `ability` (`idAbility`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkIdPlayer` FOREIGN KEY (`idPlayer`) REFERENCES `player` (`idPlayer`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla kebabsimulatordb.playersability: ~0 rows (aproximadamente)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
