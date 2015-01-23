-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.19-log - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.1.0.4898
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para itsolver
DROP DATABASE IF EXISTS `itsolver`;
CREATE DATABASE IF NOT EXISTS `itsolver` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `itsolver`;


-- Volcando estructura para tabla itsolver.algorithm_question
DROP TABLE IF EXISTS `algorithm_question`;
CREATE TABLE IF NOT EXISTS `algorithm_question` (
  `question_number` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `no_question_number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `question` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `yes_question_number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`question_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.algorithm_question: ~24 rows (aproximadamente)
/*!40000 ALTER TABLE `algorithm_question` DISABLE KEYS */;
INSERT INTO `algorithm_question` (`question_number`, `no_question_number`, `question`, `yes_question_number`) VALUES
	('Q10', NULL, 'Is the system dynamic?', 'Q11'),
	('Q11', NULL, 'Is Su-Field elements structure coordinated?', 'Q12'),
	('Q12', NULL, 'Is Su-Field elements dynamics coordinated?', 'Q13'),
	('Q13', 'Q14', 'Substitution of Su-Field elements by Ferromag. Subst. And M_Field allowed?', NULL),
	('Q14', 'Q15', 'Introduction of ferromag. Additives in available subst. Allowed?', NULL),
	('Q15', 'Q16', 'Introduction of ferromag. additives in environment allowed?', NULL),
	('Q16', NULL, 'Is use of electrical fields and/or currents allowed?', NULL),
	('Q17', NULL, 'Is Su-M_field dynamic?', 'Q18'),
	('Q18', NULL, 'Is Su-M_Field elements structure coordinated?', 'Q19'),
	('Q19', NULL, 'Are Su-M_Field elements dynamics coordinated?', NULL),
	('Q3', 'Q3.1', 'Measurement problem?', 'Q4.1'),
	('Q3.1', 'Q4', 'Transition to measurement problem possible?', 'Q3.1.1'),
	('Q3.1.1', 'Q4', 'Transition to measurement problem too complicated?', 'Q3.1.2'),
	('Q3.1.2', NULL, 'Transform problem to detection task, then to measurement task', NULL),
	('Q4', 'Q4.1', 'Su-Field complete?', 'Q5'),
	('Q4.1', 'Q4.2', 'Harmful link present?', 'Q4.1.1'),
	('Q4.1.1', NULL, 'Introduction of substances and fields allowed?', NULL),
	('Q4.2', NULL, 'Introduction of substances and fields allowed?', NULL),
	('Q5', 'Q6', 'Harmful link present?', 'Q5.1'),
	('Q5.1', NULL, 'Introduction of substances and fields allowed?', NULL),
	('Q6', 'Q8', 'Are there magnetic substances in Su-Field?', 'Q7'),
	('Q7', 'Q8', 'Introduction of M_Field allowed?', 'Q17'),
	('Q8', 'Q9', 'Complex Su-Field formation allowable?', NULL),
	('Q9', 'Q10', 'Su-Field can be replaced?', NULL);
/*!40000 ALTER TABLE `algorithm_question` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.cbr_case_entity
DROP TABLE IF EXISTS `cbr_case_entity`;
CREATE TABLE IF NOT EXISTS `cbr_case_entity` (
  `case_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cbr_case_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `problem_nature` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cbr_constraint_id` bigint(20) DEFAULT NULL,
  `cbr_contradiction_id` bigint(20) DEFAULT NULL,
  `cbr_resource_id` bigint(20) DEFAULT NULL,
  `cbr_sufield_id` bigint(20) DEFAULT NULL,
  `project_project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`case_id`),
  KEY `FK56EDF98677CD0370` (`cbr_resource_id`),
  KEY `FK56EDF986D6700164` (`cbr_contradiction_id`),
  KEY `FK56EDF98687953764` (`cbr_sufield_id`),
  KEY `FK56EDF9862A3C252A` (`project_project_id`),
  KEY `FK56EDF98643023790` (`cbr_constraint_id`),
  CONSTRAINT `FK56EDF98643023790` FOREIGN KEY (`cbr_constraint_id`) REFERENCES `cbr_constraint` (`cbr_constraint_id`),
  CONSTRAINT `FK56EDF9862A3C252A` FOREIGN KEY (`project_project_id`) REFERENCES `project` (`project_id`),
  CONSTRAINT `FK56EDF98677CD0370` FOREIGN KEY (`cbr_resource_id`) REFERENCES `cbr_resource` (`cbr_resource_id`),
  CONSTRAINT `FK56EDF98687953764` FOREIGN KEY (`cbr_sufield_id`) REFERENCES `cbr_sufield` (`cbr_sufield_id`),
  CONSTRAINT `FK56EDF986D6700164` FOREIGN KEY (`cbr_contradiction_id`) REFERENCES `cbr_contradiction` (`cbr_contradiction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.cbr_case_entity: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `cbr_case_entity` DISABLE KEYS */;
INSERT INTO `cbr_case_entity` (`case_id`, `cbr_case_type`, `problem_nature`, `cbr_constraint_id`, `cbr_contradiction_id`, `cbr_resource_id`, `cbr_sufield_id`, `project_project_id`) VALUES
	(1, 'CONTRADICTION_CBR_TYPE', 'ELIMINATE_HARMFUL_FUNCTION', 1, 1, 1, NULL, NULL),
	(2, 'CONTRADICTION_CBR_TYPE', 'ELIMINATE_HARMFUL_FUNCTION', 2, 2, 2, NULL, NULL),
	(3, 'CONTRADICTION_CBR_TYPE', 'TECHNOLOGY_FORESIGHT', 3, 3, 3, NULL, NULL),
	(4, 'CONTRADICTION_CBR_TYPE', 'COST_REDUCTION', 4, 4, 4, NULL, NULL),
	(5, 'CONTRADICTION_CBR_TYPE', 'COST_REDUCTION', 5, 5, 5, NULL, NULL);
/*!40000 ALTER TABLE `cbr_case_entity` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.cbr_constraint
DROP TABLE IF EXISTS `cbr_constraint`;
CREATE TABLE IF NOT EXISTS `cbr_constraint` (
  `cbr_constraint_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cbr_constraint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.cbr_constraint: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `cbr_constraint` DISABLE KEYS */;
INSERT INTO `cbr_constraint` (`cbr_constraint_id`) VALUES
	(1),
	(2),
	(3),
	(4),
	(5);
/*!40000 ALTER TABLE `cbr_constraint` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.cbr_constraint_constrainidlist
DROP TABLE IF EXISTS `cbr_constraint_constrainidlist`;
CREATE TABLE IF NOT EXISTS `cbr_constraint_constrainidlist` (
  `cbr_constraint_cbr_constraint_id` bigint(20) NOT NULL,
  `cbr_constraint_list` bigint(20) DEFAULT NULL,
  KEY `FK8A0B661AC176EE7A` (`cbr_constraint_cbr_constraint_id`),
  CONSTRAINT `FK8A0B661AC176EE7A` FOREIGN KEY (`cbr_constraint_cbr_constraint_id`) REFERENCES `cbr_constraint` (`cbr_constraint_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.cbr_constraint_constrainidlist: ~26 rows (aproximadamente)
/*!40000 ALTER TABLE `cbr_constraint_constrainidlist` DISABLE KEYS */;
INSERT INTO `cbr_constraint_constrainidlist` (`cbr_constraint_cbr_constraint_id`, `cbr_constraint_list`) VALUES
	(1, 4),
	(1, 5),
	(1, 7),
	(1, 9),
	(1, 10),
	(2, 4),
	(2, 5),
	(2, 12),
	(2, 14),
	(2, 16),
	(3, 4),
	(3, 5),
	(4, 9),
	(4, 10),
	(4, 11),
	(4, 12),
	(4, 13),
	(4, 14),
	(4, 16),
	(5, 9),
	(5, 10),
	(5, 11),
	(5, 12),
	(5, 13),
	(5, 14),
	(5, 16);
/*!40000 ALTER TABLE `cbr_constraint_constrainidlist` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.cbr_contradiction
DROP TABLE IF EXISTS `cbr_contradiction`;
CREATE TABLE IF NOT EXISTS `cbr_contradiction` (
  `cbr_contradiction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `negative_id` bigint(20) DEFAULT NULL,
  `positive_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cbr_contradiction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.cbr_contradiction: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `cbr_contradiction` DISABLE KEYS */;
INSERT INTO `cbr_contradiction` (`cbr_contradiction_id`, `negative_id`, `positive_id`) VALUES
	(1, 3, 5),
	(2, 3, 22),
	(3, 3, 5),
	(4, 2, 9),
	(5, 2, 10);
/*!40000 ALTER TABLE `cbr_contradiction` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.cbr_resource
DROP TABLE IF EXISTS `cbr_resource`;
CREATE TABLE IF NOT EXISTS `cbr_resource` (
  `cbr_resource_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cbr_resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.cbr_resource: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `cbr_resource` DISABLE KEYS */;
INSERT INTO `cbr_resource` (`cbr_resource_id`) VALUES
	(1),
	(2),
	(3),
	(4),
	(5);
/*!40000 ALTER TABLE `cbr_resource` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.cbr_resource_resourceidlist
DROP TABLE IF EXISTS `cbr_resource_resourceidlist`;
CREATE TABLE IF NOT EXISTS `cbr_resource_resourceidlist` (
  `cbr_resource_cbr_resource_id` bigint(20) NOT NULL,
  `cbr_resource_list` bigint(20) DEFAULT NULL,
  KEY `FK7273020CCC6E1C0B` (`cbr_resource_cbr_resource_id`),
  CONSTRAINT `FK7273020CCC6E1C0B` FOREIGN KEY (`cbr_resource_cbr_resource_id`) REFERENCES `cbr_resource` (`cbr_resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.cbr_resource_resourceidlist: ~13 rows (aproximadamente)
/*!40000 ALTER TABLE `cbr_resource_resourceidlist` DISABLE KEYS */;
INSERT INTO `cbr_resource_resourceidlist` (`cbr_resource_cbr_resource_id`, `cbr_resource_list`) VALUES
	(1, 1),
	(1, 4),
	(1, 5),
	(2, 1),
	(2, 4),
	(2, 3),
	(3, 1),
	(3, 4),
	(4, 1),
	(4, 3),
	(4, 4),
	(4, 5),
	(5, 5);
/*!40000 ALTER TABLE `cbr_resource_resourceidlist` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.cbr_sufield
DROP TABLE IF EXISTS `cbr_sufield`;
CREATE TABLE IF NOT EXISTS `cbr_sufield` (
  `cbr_sufield_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `standard_number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sufield_graph_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cbr_sufield_id`),
  KEY `FK3C79C38CBE5B7BD0` (`standard_number`),
  KEY `FK3C79C38CD2B73815` (`sufield_graph_id`),
  CONSTRAINT `FK3C79C38CD2B73815` FOREIGN KEY (`sufield_graph_id`) REFERENCES `sufield_graph` (`sufield_graph_id`),
  CONSTRAINT `FK3C79C38CBE5B7BD0` FOREIGN KEY (`standard_number`) REFERENCES `inventive_standard` (`standard_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.cbr_sufield: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `cbr_sufield` DISABLE KEYS */;
/*!40000 ALTER TABLE `cbr_sufield` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.characteristic
DROP TABLE IF EXISTS `characteristic`;
CREATE TABLE IF NOT EXISTS `characteristic` (
  `characteristic_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`characteristic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.characteristic: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `characteristic` DISABLE KEYS */;
/*!40000 ALTER TABLE `characteristic` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.contradiction
DROP TABLE IF EXISTS `contradiction`;
CREATE TABLE IF NOT EXISTS `contradiction` (
  `contradiction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `negativeCharacteristic_characteristic_id` bigint(20) DEFAULT NULL,
  `positiveCharacteristic_characteristic_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`contradiction_id`),
  KEY `FK3CF20919F4CEFC5` (`positiveCharacteristic_characteristic_id`),
  KEY `FK3CF2091E9E97201` (`negativeCharacteristic_characteristic_id`),
  CONSTRAINT `FK3CF2091E9E97201` FOREIGN KEY (`negativeCharacteristic_characteristic_id`) REFERENCES `characteristic` (`characteristic_id`),
  CONSTRAINT `FK3CF20919F4CEFC5` FOREIGN KEY (`positiveCharacteristic_characteristic_id`) REFERENCES `characteristic` (`characteristic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.contradiction: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `contradiction` DISABLE KEYS */;
/*!40000 ALTER TABLE `contradiction` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.contradiction_contradiction_principle
DROP TABLE IF EXISTS `contradiction_contradiction_principle`;
CREATE TABLE IF NOT EXISTS `contradiction_contradiction_principle` (
  `contradiction_contradiction_id` bigint(20) NOT NULL,
  `contradictionPrinciple_contradiction_principle_id` bigint(20) NOT NULL,
  UNIQUE KEY `contradictionPrinciple_contradiction_principle_id` (`contradictionPrinciple_contradiction_principle_id`),
  KEY `FKA88E8DA09F318FB2` (`contradiction_contradiction_id`),
  KEY `FKA88E8DA01BAC45E1` (`contradictionPrinciple_contradiction_principle_id`),
  CONSTRAINT `FKA88E8DA01BAC45E1` FOREIGN KEY (`contradictionPrinciple_contradiction_principle_id`) REFERENCES `contradiction_principle` (`contradiction_principle_id`),
  CONSTRAINT `FKA88E8DA09F318FB2` FOREIGN KEY (`contradiction_contradiction_id`) REFERENCES `contradiction` (`contradiction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.contradiction_contradiction_principle: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `contradiction_contradiction_principle` DISABLE KEYS */;
/*!40000 ALTER TABLE `contradiction_contradiction_principle` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.contradiction_principle
DROP TABLE IF EXISTS `contradiction_principle`;
CREATE TABLE IF NOT EXISTS `contradiction_principle` (
  `contradiction_principle_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `importance_order` int(11) DEFAULT NULL,
  `solutionPrinciple_solution_principle_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`contradiction_principle_id`),
  KEY `FKF42FCB4EECAF0D55` (`solutionPrinciple_solution_principle_id`),
  CONSTRAINT `FKF42FCB4EECAF0D55` FOREIGN KEY (`solutionPrinciple_solution_principle_id`) REFERENCES `solution_principle` (`solution_principle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.contradiction_principle: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `contradiction_principle` DISABLE KEYS */;
/*!40000 ALTER TABLE `contradiction_principle` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.contradiction_project
DROP TABLE IF EXISTS `contradiction_project`;
CREATE TABLE IF NOT EXISTS `contradiction_project` (
  `project_id` bigint(20) NOT NULL,
  `contradiction_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  KEY `FKC791F16B2521D444` (`project_id`),
  KEY `FKC791F16BDD7B2097` (`contradiction_id`),
  CONSTRAINT `FKC791F16BDD7B2097` FOREIGN KEY (`contradiction_id`) REFERENCES `project_contradiction` (`project_contradiction_id`),
  CONSTRAINT `FKC791F16B2521D444` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.contradiction_project: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `contradiction_project` DISABLE KEYS */;
/*!40000 ALTER TABLE `contradiction_project` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.dashboard
DROP TABLE IF EXISTS `dashboard`;
CREATE TABLE IF NOT EXISTS `dashboard` (
  `dashboard_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`dashboard_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.dashboard: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `dashboard` DISABLE KEYS */;
INSERT INTO `dashboard` (`dashboard_id`) VALUES
	(1);
/*!40000 ALTER TABLE `dashboard` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.dashboard_notification
DROP TABLE IF EXISTS `dashboard_notification`;
CREATE TABLE IF NOT EXISTS `dashboard_notification` (
  `dashboard_dashboard_id` bigint(20) NOT NULL,
  `notificationList_notification_id` bigint(20) NOT NULL,
  UNIQUE KEY `notificationList_notification_id` (`notificationList_notification_id`),
  KEY `FK8353D8B6A0FFABA` (`notificationList_notification_id`),
  KEY `FK8353D8B647960BAF` (`dashboard_dashboard_id`),
  CONSTRAINT `FK8353D8B647960BAF` FOREIGN KEY (`dashboard_dashboard_id`) REFERENCES `dashboard` (`dashboard_id`),
  CONSTRAINT `FK8353D8B6A0FFABA` FOREIGN KEY (`notificationList_notification_id`) REFERENCES `notification` (`notification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.dashboard_notification: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `dashboard_notification` DISABLE KEYS */;
INSERT INTO `dashboard_notification` (`dashboard_dashboard_id`, `notificationList_notification_id`) VALUES
	(1, 4),
	(1, 5),
	(1, 6),
	(1, 8),
	(1, 9);
/*!40000 ALTER TABLE `dashboard_notification` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.ifr
DROP TABLE IF EXISTS `ifr`;
CREATE TABLE IF NOT EXISTS `ifr` (
  `ifr_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `eliminate_deficiencies` longtext COLLATE utf8_unicode_ci,
  `not_create_disadvantages` longtext COLLATE utf8_unicode_ci,
  `not_increase_complexity` longtext COLLATE utf8_unicode_ci,
  `preserve_advantages` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`ifr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.ifr: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ifr` DISABLE KEYS */;
INSERT INTO `ifr` (`ifr_id`, `eliminate_deficiencies`, `not_create_disadvantages`, `not_increase_complexity`, `preserve_advantages`) VALUES
	(1, 'eliminacion', 'desventajas', 'complejidad', 'ventajas');
/*!40000 ALTER TABLE `ifr` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.inventive_standard
DROP TABLE IF EXISTS `inventive_standard`;
CREATE TABLE IF NOT EXISTS `inventive_standard` (
  `standard_number` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci,
  `image_description` longtext COLLATE utf8_unicode_ci,
  `image_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `group_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`standard_number`),
  KEY `FK542F129E4C63CD05` (`group_name`),
  CONSTRAINT `FK542F129E4C63CD05` FOREIGN KEY (`group_name`) REFERENCES `inventive_standard_group` (`group_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.inventive_standard: ~85 rows (aproximadamente)
/*!40000 ALTER TABLE `inventive_standard` DISABLE KEYS */;
INSERT INTO `inventive_standard` (`standard_number`, `description`, `image_description`, `image_url`, `group_name`) VALUES
	('STANDARD 1-1-1', 'If there is an object which is not easy to change as required, and the conditions do not contain any limitations on the introduction of substances and fields, the problem is to be solved by synthesizing a SFM: the object is subjected to the action of a physical field which produces the necessary change in the object.', 'Example: To remove air from a powdered substance, the substance is subjected to centrifugal forces.', 'http://localhost:8080/itsolver/assets/img_standards/st1.png', 'GROUP-1-1'),
	('STANDARD 1-1-2', 'If there is a SFM which is not easy to change as required, and the conditions do not contain any limitations on the introduction of additives to given substances, the problem is to be solved by a transition (permanent or temporary) to an internal complex SFM, introducing additives in the present substances enhancing controllability OP imparting the required properties to the SFM.', 'Example: To detect very small drops of liquid, a luminescent substance is added to the liquid in advance. Then, using ultraviolet light, it is easy to detect the drops.', 'TODO_IMG', 'GROUP-1-1'),
	('STANDARD 1-1-3', 'If there is a SFM which is not easy to change as required, and the conditions contain limitations on the introduction of additives into the existing substances, the problem can bo solved by a transition (permanent or temporary) to an external complex SFM. attaching to one of these substances an external substance which improves controllability or brings the required properties to the SFM.', 'Example: to detect a leakage of gases from a pipe, an outer surface of the pipe is covered with a substance that reacts with the gas and produces visible bubbles.', 'TODO_IMG', 'GROUP-1-1'),
	('STANDARD 1-1-4', 'If there is a SFM that is not easy to change as required, and the conditions contain limitations on the introduction or attachment of substances, the problem has to be solved by synthesizing a SFM using internal environment as the substance.', NULL, 'TODO_IMG', 'GROUP-1-1'),
	('STANDARD 1-1-5', 'If the external environment does not contain ready substances required to synthesize a SFM, these substances can be obtained by replacing the external environment with another one, or by decomposing the environment, or by introducing additives into the environment', 'Example: To improve a coefficient of sliding effect, a liquid lubricant is aerated.', 'TODO_IMG', 'GROUP-1-1'),
	('STANDARD 1-1-6', 'If a minimum (measured, optimal) effect of action is required, but it is difficult or impossible to provide it under the conditions of the problem, use a maximum action, while the excess of the action is then removed. Excess of a substance is removed by a field, while excess of a field is removed by a substance.', 'Example: To paint a part accurately, the part first loaded into a container with the paint, and then subjected to rotation. Excess of paint is removed due to centrifugal forces.', 'TODO_IMG', 'GROUP-1-1'),
	('STANDARD 1-1-7', 'If a maximum effect of action on a substance is required and this is not allowed, the maximum action has to be preserved but directed to another substance attached to the first one.', 'Example: When manufacturing reinforced concrete, it is possible to use metal wire instead of rods. But the wire has to be stretched. To do this it has to be heated up to 700C what is not allowed. The wire is connected to the rod that is heated while the wire remains cold.', 'TODO_IMG', 'GROUP-1-1'),
	('STANDARD 1-1-8-1', 'If a selective effect of action is required (maximum in certain zones, while the minimum is maintained in other zones), the field has to be maximal; then a protective substance is introduced in places where a minimum effect is required.', 'Example: When sealing a glass ampoule with liquid medicine, an overheated glass might destroy the medicine. The ampoule is put into water leaving the ampoule\'s tip above the water. Water protects the rest of the ampoule from overheating.', 'TODO_IMG', 'GROUP-1-1'),
	('STANDARD 1-1-8-2', 'If a selective-maximum effect is required (maximum in certain zones, and minimum in other zones), the field should be minimal; then a substance that produces a local effect interacting with a field (e.g. termite compounds for thermal action or explosive ones for mechanical action) is introduced in places where a maximum effect is required.', 'Example: to weld two metal parts, an exothermic powder producing extra heat is introduced between the parts.', 'TODO_IMG', 'GROUP-1-1'),
	('STANDARD 1-2-1', 'If useful and harmful effects appear between two substances in a SFM and there is no need to maintain a direct contact between the substances, the problem is solved by introducing a third substance between them.', 'Example: To compact walls of a borehole, gases produced during, explosion are used. However, the gases also may cause cracks in the borehole\'s walls. It is proposed to cover the walls by plasticine that transmits pressure but prevents the walls from crack formation.', 'TODO_IMG', 'GROUP-1-2'),
	('STANDARD 1-2-2', 'If there are a useful and a harmful effects between two substances, and there is no need to maintain direct contact between the substances, and it is forbidden or inconvenient to use foreign substances, the problem can be solved by introducing a third substance between the two. which is a modification of the first or the second substances.', 'Example: A hydrodynamic foil\'s surface might be destroyed by a cavitation produced by the friction between the foil and the water when moving at a high speed. It\'s proposed to refrigerate the surface of the foil.  Surrounded water will freeze and form an ice layer on the foil.', 'TODO_IMG', 'GROUP-1-2'),
	('STANDARD 1-2-3', 'If it is required to eliminate the harmful effect of a field upon a substance, the problem can be solved by introducing a second substance that draws off upon itself the harmful effect of the field.', 'Example: To protect underground cables from stresses of ground occurring during frost, cavities are formed in the ground beforehand', 'TODO_IMG', 'GROUP-1-2'),
	('STANDARD 1-2-4', 'If useful and harmful effects appear between two substances in a SFM, and a direct contact between the substances must be maintained, the problem can be solved by transition to a dual SFM, in which the useful effect is provided by the existing field while a new field neutralizes the harmful effect (or transforms the harmful effect into a useful effect).', 'Example:  to help with pollination of a flower, airflow is used. However, it also closes the flower. It is proposed to open the flower with electrostatic discharge.', 'TODO_IMG', 'GROUP-1-2'),
	('STANDARD 1-2-5', 'If it is necessary to decompose a SFM with a magnetic field, the problem is solved by using physical effects, which are capable of \'switching off\' ferromagnetic properties of substances, e.g. by demagnetizing during an impact or during heating above Curie point. Notes:	The magnetic field may appear at the right moment if a system of magnets compensating the effect of each other\'s fields is used. When one of the magnets is demagnetized, a magnetic field arises in the system.', 'Example:  During welding, it\'s difficult to insert a ferromagnetic powder in the welding zone: an electromagnetic field of a welding current makes the particles move away from the welding zone. It is proposed to heat the powders above the Curie point to make them non-magnetic.', NULL, 'GROUP-1-2'),
	('STANDARD 2-1-1', 'Efficiency of SFM can be improved by transforming one of the parts of the SFM into an independently controllable SFM, thus forming a chain SFM.', 'Example: A tractor with movable center of gravity to work on steep slopes.', 'TODO_IMG', 'GROUP-2-1'),
	('STANDARD 2-1-2', 'If it is necessary to improve the efficiency of SFM, and replacement of SFM elements is not allowed, the problem can be solved by the synthesis of a dual SFM through introducing a second SFM which is easy to control.', 'Example: It is proposed to increase control over a melted metal by rotating the metal in a centrifuge.', NULL, 'GROUP-2-1'),
	('STANDARD 2-2-1', 'Efficiency of a SFM can be improved by replacing an uncontrolled (or poorly controlled) field with a well-controlled field, e.g. by replacing a gravitation field with mechanical field, mechanical field with an electric, etc.NOTES: In certain situations, controllability of a field may be improved not only by replacing a given field with another one, but al so by modifying the present field along the following line:Permanent field --> monotonically changing one --> pulsed one -->variable one --> variable in frequency and amplitude --> etc.', 'Example: Instead of a metal blade for non-uniform metal cutting, a water jet can be used', NULL, 'GROUP-2-2'),
	('STANDARD 2-2-2', 'Efficiency of a SFM can be improved by increasing the degree of fragmentation of the object which acts as an instrument in SFM. NOTES:	The standard displays one of the major trends of the technology evolution, i.e. fragmentation of the object or its part interacting with the product.', 'Example A: knife with teeth, then with the abrasive coating.', NULL, 'GROUP-2-2'),
	('STANDARD 2-2-3', 'Efficiency of a SFM can be improved by transition from a solid object to a capillary porous one. The transition is performed as:solid object--> object with one cavity --> object with multiple cavities (perforated) --> capillary porous object --> capillary porous object with a predefined porous structure. NOTES:	Transition to a capillary porous object enables a liquid substance to be placed in the pores and use physical effects.', 'Example: A bunch of capillaries apply liquid glue more accurately on a surface to be glued than a single large-sized tube.', NULL, 'GROUP-2-2'),
	('STANDARD 2-2-4', 'Efficiency of a SFM can be improved by increasing the degree of dynamics of SFM, i.e. by transition to a more flexible, rapidly changing structure of the system. NOTES: Making a substance dynamic starts with dividing it into two joint-coupled parts and continues along the following line: One joint --> many joints --> flexible object. A field can be made more dynamic by transition from a permanent field (or of the field together with a substance) to a pulsed field.', 'Example: A door made of hinged segments.', NULL, 'GROUP-2-2'),
	('STANDARD 2-2-5', 'Efficiency of SFM can be improved by transition from a uniform field or fields with a disordered structure to non-uniform fields or fields with a definite spatial-temporal structure (permanent or variable). Notes: If a certain spatial structure is to be imparted to a substance object, the process can be conducted in a field having a structure that matches the required structure of the substance object.', 'Example: To mix two magnetic powders, a layer of the first powder is put in the layer of the second powder and the non-uniform magnetic field is applied.', NULL, 'GROUP-2-2'),
	('STANDARD 2-2-6', 'Efficiency of a SFM can be improved by transition from substances that are uniform or have a disordered structure to substances that are non-uniform or have a predefined spatial-temporal structure (permanent or variable). NOTES:	In particular, if an intensive effect of a field is required in certain places of a system (points, lines), then substances that produce the required field are introduced in these spots beforehand.', 'Example: To make a porous material with oriented spatial structure the field threats are inserted into the soft material beforehand. After the material solidifies these threats are burned out.', NULL, 'GROUP-2-2'),
	('STANDARD 2-3-1', 'Efficiency of a SFM can be improved by matching (or mismatching) the frequency of acting field with the natural frequency of a product (or tool).', 'Example: 1. The rhythm of massage is synchronized with a pulse of a patient. 2. In arc welding, the frequency of magnetic field is equal to the natural frequency of a melting electrode.', NULL, 'GROUP-2-3'),
	('STANDARD 2-3-2', 'Efficiency of a complex SFM can be improved by matching (or mismatching) frequencies of the fields being used.', 'Example: To coat a part with a material, the material is applied as a powder. To provide a high degree of regularity, the frequencies of pulses of an electrical current and pulses of magnetic field are made equal.', NULL, 'GROUP-2-3'),
	('STANDARD 2-3-3', 'If we are given two incompatible actions, e.g. changing and measuring, one action should be performed during the pauses between another one. In general, pauses in one action should be filled with another useful action.', 'Example: To provide accuracy of contact welding, measurements are conducted during the pauses between the pulses of an electrical current.', NULL, 'GROUP-2-3'),
	('STANDARD 2-4-1', 'Efficiency of a SFM is enhanced by using a ferromagnetic substance and a magnetic field.', 'NOTES: 1. The standard indicates the use of a ferromagnetic substance that is not in a fragmented state. 2. F-SFM is a SFM system in which a disperse ferromagnetic substance and a magnetic field are interacting.', 'TODO_IMG', 'GROUP-2-4'),
	('STANDARD 2-4-10', 'Efficiency of F-SFM can be improved by matching the rhythms of the system\'s elements.', NULL, NULL, 'GROUP-2-4'),
	('STANDARD 2-4-11', 'If it is not allowed to introduce ferromagnetics or to perform magnetization, an E-SFM has to be synthesized using: a) interaction of an external electromagnetic field with currents or b) fed through a contact or induced without a contact, or c) using interaction between these currents. NOTES:	An E-SFM is a SFM in which electric currents interact with each other. The evolution of E-SFMs repeats the line of evolution of complex-boosted SFMs: Simple E-SFM -> complex E-SFM -> E-SFM in the external environment -> E-SFM Dynamisation -> structuring -> matching the rhythms.', NULL, NULL, 'GROUP-2-4'),
	('STANDARD 2-4-12', 'If a magnetic fluid cannot be used, one can use an electrorheologic fluid (a suspension of fine quartz powder in toluene, for instance, with viscosity being changed by the electric field). A SFM with an electrorheologic fluid is a special form of E-SFM.', NULL, NULL, 'GROUP-2-4'),
	('STANDARD 2-4-2', 'Efficiency of control over a SFM can be improved by replacing one of the substances with ferromagnetic particles (or adding ferromagnetic particles) -chips, granules, grains, etc. - and using magnetic or electromagnetic field. NOTES:	Efficiency of control rises with a higher degree of fragmentation of ferromagnetic particles and of the substance in which they are introduced. ferroparticles: granules -> powder -> finely dispersed particles -> magnetic liquid; substance: solid-> grains-> powder-> liquid.', NULL, NULL, 'GROUP-2-4'),
	('STANDARD 2-4-3', 'Efficiency of a ferromagnetic SFM can be improved by using magnetic fluids - colloidal ferromagnetic particles suspended in kerosene, silicone or water.', NULL, NULL, 'GROUP-2-4'),
	('STANDARD 2-4-4', 'Efficiency of a ferromagnetic SFM can be improved by using a capillary porous structure inherent in many F-SFMs.', NULL, NULL, 'GROUP-2-4'),
	('STANDARD 2-4-5', 'If it is required to raise the efficiency of control and replacement of substances with ferromagnetic particles is not allowed, one has to compose internal or external complex ferromagnetic SFM, introducing additives in one of the substances.', NULL, NULL, 'GROUP-2-4'),
	('STANDARD 2-4-6', 'If it is required to raise the efficiency of control and replacement of substances with ferromagnetic particles is not allowed, the ferromagnetic particles should be introduced in the external environment Then, using the magnetic field, the environment parameters should be changed so that the system becomes more controllable.', NULL, NULL, 'GROUP-2-4'),
	('STANDARD 2-4-7', 'Controllability of a ferromagnetic system can be improved by the use of physical effects.', NULL, NULL, 'GROUP-2-4'),
	('STANDARD 2-4-8', 'Efficiency of a F-SFM can be improved by increasing the degree of dynamics in the system, for instance, by transition to a more flexible, rapidly changing structure of the system. NOTES:	Making a substance more dynamic begins with dividing it into two joint-coupled parts and continues along the following line: One joint -> many joints -> flexible substance.A field is made dynamic by going over from a permanent effect of the field (or of the field together with a substance) to a pulsed effect.', NULL, NULL, 'GROUP-2-4'),
	('STANDARD 2-4-9', 'Efficiency of F-SFM can be improved by transition from fields that are uniform or have a disordered structure to fields that are non-uniform or have a definite spatial-temporal structure (permanent or variable). NOTES: If a certain spatial structure is to be imparted to a substance object, the process can be conducted in a field having a structure that matches the required structure of the substance object.', NULL, NULL, 'GROUP-2-4'),
	('STANDARD 3-1-1', 'System efficiency at any stage of its evolution can be improved by combining the system with another system (or systems) to form a bi- or polysystem. NOTES:	For a simple formation of bi- and polysystems, two and more components are combined. Components to be combined may be substances, fields, substance-field pairs and whole SFMs.', 'Example: To process sides of thin glass plates, several plates are put together to prevent glass from breaking.', NULL, 'GROUP-3-1'),
	('STANDARD 3-1-2', 'Efficiency of bi- and polysystems can be improved by developing links between system elements. NOTES: Links between elements of a bi- and polysystem may be made either more rigid or more dynamic.', 'Example: To synchronize a process of lifting a very heavy part by three cranes, it is proposed to use a rigid triangle synchronizing the cranes moving parts.', NULL, 'GROUP-3-1'),
	('STANDARD 3-1-3', 'Efficiency of bi- and polysystems can be improved by increasing the difference between system components. The following line of evolution is recommended: similar components (pencils of the same color) --> components with biased characteristics (pencils of different colors) --> different components (set of drawing instruments) --> combinations of the \'component + component with opposite function\' (pencil with rubber)', NULL, NULL, 'GROUP-3-1'),
	('STANDARD 3-1-4', 'Efficiency of bi- and polysystems can be improved by \'convolution\' (integration of several components into a single component) by reducing auxiliary components. Completely convoluted bi- and polysystems become monosystems again, and integration can be repeated at another level of the system.', 'Example: Instead of three separate indicators on a dashboard, a single indicator can be used in which indicating arrows are made of different colors.', NULL, 'GROUP-3-1'),
	('STANDARD 3-1-5', 'Efficiency of bi- and polysystems can be improved by distributing incompatible properties among the system and its parts. This is achieved by using a two-level structure in which all the system as a whole has a certain property A, while its parts (particles) have property anti-A.', 'Example: A working part of a vice is made of segmented plates capable of moving relatively each other. Parts of various shapes can be gripped quickly.', NULL, 'GROUP-3-1'),
	('STANDARD 3-2-1', 'Efficiency of a system at any stage of its evolution can be improved by transition from a macrolevel to a microlevel: the system or its part is replaced by a substance capable of delivering the required function when interacting with a field. NOTES: There is a multitude of microlevel states of a substance (crystal lattice, molecules, ions, domains, atoms, fundamental particles, fields, etc.). Therefore, various options of transition to a microlevel and various options of transition from one microlevel to another, lower one, should be considered when solving a problem.', 'Example: Instead of a microscrew, a microscopic table can be positioned by fixing it on a metal rod that is subjected to a thermal field. The rod expands and contracts relatively the value of the temperature due to the effect of thermal expansion.', NULL, 'GROUP-3-2'),
	('STANDARD 4-1-1', 'If a problem involves detection or measurement, it is proposed to change the problem in such a way, so that there should be no need to perform detection or measurement at all.', 'Example: To prevent a permanent electric motor from overheating, its temperature is measured by a temperature sensor. If to make the poles of the motor of an alloy with a Curie point equal to the critical value of the temperature, the motor will stop itself.', NULL, 'GROUP-4-1'),
	('STANDARD 4-1-2', 'If a problem involves detection or measurement, and it is impossible to change the problem to eliminate the need for detection or measurement, it is proposed to change/detect properties of a copy of the object (e.g. picture).', 'Example: It might be dangerous to measure the length of a snake. It is safe to measure its length on a photographic image of the snake, and then recalculate the obtained result.', NULL, 'GROUP-4-1'),
	('STANDARD 4-1-3', 'If a problem involves detection or measurement, and the problem cannot be changed to eliminate the need for measurement, and it is impossible to use copies or pictures, it is proposed to transform this problem into a problem of successive detection of changes. NOTES:	Any measurement is conducted with a certain degree of accuracy. Therefore, even if the problem deals with continuous measurement, one can always single out a simple act of measurement that involves two successive detections. This makes the problem much simpler.', 'Example: To measure a temperature, it is possible to use a material that changes its color depending on the current value of the temperature. Alternatively, several materials can be used to indicate different temperatures.', NULL, 'GROUP-4-1'),
	('STANDARD 4-2-1', 'If a non-SFM is not easy to detect or measure, the problem is solved by synthesizing a simple or dual SFM with a field at the output. Instead of direct measurement or detection of a parameter, another parameter identified with the field is measured or detected. The field to be introduced should have a parameter that we can easily detect or measure, and which can indicate the state of the parameter we need to detect or measure.', 'Example: To detect a moment when a liquid starts to boil, an electrical current is passed through the liquid. During boiling, air bubbles are formed - they dramatically reduce electrical resistance of the liquid.', 'TODO_IMG', 'GROUP-4-2'),
	('STANDARD 4-2-2', 'If a system (or its part) does not provide detection or measurement, the problem is solved by transition to an internal or external complex measuring SFM, introducing easily detectable additives.', 'Example: To detect leakage in a refrigerator, a cooling agent is mixed with a luminescent powder.', 'TODO_IMG', 'GROUP-4-2'),
	('STANDARD 4-2-3', 'If a system is difficult to detect or to measure at a given moment of time, and it is not allowed or not possible to introduce additives into the object, then additives that create an easily detectable and measurable field should be introduced in the external environment. Changing the state of the environment will indicate the state of the object.', 'Example: To detect wearing of a rotating metal disc contacting with another disk, it is proposed to introduce luminescent powder into the oil lubricant, which already exists in the system. Metal particles collecting in the oil will reduce luminosity of the oil.', NULL, 'GROUP-4-2'),
	('STANDARD 4-2-4', 'If it is impossible to introduce easily detectable additives in the external environment, these can be obtained in the environment itself, for instance, by decomposing the environment or by changing the aggregate state of the environment. NOTES:	In particular, gas or vapor bubbles produced by electrolysis, cavitation or by any other method may often be used as additives obtained by decomposing the external environment.', 'Example: The speed of a water flow in a pipe might be measured by amount of air bubbles resulting from cavitation.', NULL, 'GROUP-4-2'),
	('STANDARD 4-3-1', 'Efficiency of a measuring SFM can be improved by the use of physical effects.', 'Example: Temperature of liquid media can be measured by measuring a change of a coefficient of retraction, which depends on the value of the temperature.', NULL, 'GROUP-4-3'),
	('STANDARD 4-3-2', 'If it is impossible to detect or measure directly the changes in the system, and no field can be passed through the system, the problem can be solved by exciting resonance oscillations (of the entire system or of its part), whose frequency change is an indication of the changes taking place.', 'Example: To measure the mass of a substance in a container, the container is subjected to mechanically forced resonance oscillations. The frequency of the oscillations depends on the mass of the system.', NULL, 'GROUP-4-3'),
	('STANDARD 4-3-3', 'If resonance oscillations may not be excited in a system, its state can be determined by a change in the natural frequency of the object (external environment) connected with the system.', 'Example: The mass of boiling liquid can be measured by measuring the natural frequency of gas resulting from evaporation.', NULL, 'GROUP-4-3'),
	('STANDARD 4-4-1', 'Efficiency of a measuring SFM can be improved by using a ferromagnetic substance and a magnetic field. NOTES:	The standard indicates the use of a non-fragmented ferromagnetic object.', NULL, NULL, 'GROUP-4-4'),
	('STANDARD 4-4-2', 'Efficiency of detection or measurement can be improved by transition to ferromagnetic SFMs, replacing one of the substances with ferromagnetic particles (or adding ferromagnetic particles) and by detecting or measuring the magnetic field.', NULL, NULL, 'GROUP-4-4'),
	('STANDARD 4-4-3', 'If it is required to improve the efficiency of detection or measurement by transition to a ferromagnetic SFM, and replacement of the substance with ferromagnetic particles is not allowed, the transition to the F-SFM is performed by synthesizing a complex ferromagnetic SFM, introducing (or attaching) ferromagnetic additives in the substance.', NULL, NULL, 'GROUP-4-4'),
	('STANDARD 4-4-4', 'If it is required to improve efficiency of detection or measurement by transition to F-SFM, and introduction of ferromagnetic particles is not allowed, ferromagnetic particles are introduced in the external environment.', NULL, NULL, 'GROUP-4-4'),
	('STANDARD 4-4-5', 'Efficiency of a F-SFM measuring system can be improved by using physical effects, for instance, Curie point, Hopkins and Barkhausen effects, magnetoelastic effect, etc.', NULL, NULL, 'GROUP-4-4'),
	('STANDARD 4-5-1', 'Efficiency of a measuring system at any stage of its evolution can be improved by forming bi- or polysystem. NOTES:	To form bi- and polysystems, two or more components are combined. The components to be combined may be substances, fields, substance-field pairs and SFMs.', 'Example: It is difficult to accurately measure the temperature of a small beetle. However, if there are many beetles put together, the temperature can be measured easily.', NULL, 'GROUP-4-5'),
	('STANDARD 4-5-2', 'Measuring systems evolve towards measuring the derivatives of the function under control. The transition is performed along the following line: measurement of a function --> measurement of the first derivative of the function --> measurement of the second derivative of the function.', 'Example: Change of stress in the rack are defined by the speed of changing the electrical resistance of the rack.', NULL, 'GROUP-4-5'),
	('STANDARD 5-1-1-1', 'If it is necessary to introduce a substance in the system, and it is not allowed, a \'void\' can be used instead of the substance. NOTES: A \'void\' is usually gaseous substance, like air, or empty space formed in a solid object. In some cases a \'void\' may be formed by other substances, such as liquids (foam) or loose bodies.', NULL, NULL, 'GROUP-5-1'),
	('STANDARD 5-1-1-2', 'If it is necessary to introduce a substance in the system, and it is not allowed, a field can be introduced instead of the substance.', NULL, NULL, 'GROUP-5-1'),
	('STANDARD 5-1-1-3', 'If it is necessary to introduce a substance in the system, and it is not allowed, an external additive can be used instead of an internal one.', NULL, NULL, 'GROUP-5-1'),
	('STANDARD 5-1-1-4', 'If it is necessary to introduce a substance in the system, and it is not allowed, a very active additive can be introduced in very small quantities.', NULL, NULL, 'GROUP-5-1'),
	('STANDARD 5-1-1-5', 'If it is necessary to introduce a substance in the system, and it is not allowed, an additive can be introduced in very small quantities, and concentrated in certain parts of the object.', NULL, NULL, 'GROUP-5-1'),
	('STANDARD 5-1-1-6', 'If it is necessary to introduce a substance in the system, and it is not allowed, the substance can be introduced temporarily and then removed.', NULL, NULL, 'GROUP-5-1'),
	('STANDARD 5-1-1-7', 'If it is necessary to introduce a substance in the system, and it is not allowed, a copy of the object can be used instead of the object itself, where introduction of substances is allowed.', NULL, NULL, 'GROUP-5-1'),
	('STANDARD 5-1-1-8', 'If it is necessary to introduce a substance in the system, and it is not allowed by the system\'s operating conditions, the substance can be introduced in a form of a chemical compound which can be later decomposed.', NULL, NULL, 'GROUP-5-1'),
	('STANDARD 5-1-1-9', 'If it is necessary to introduce a substance in the system, and it is not allowed, the substance can be produced by decomposing the external environment or the object itself, for instance, by electrolysis, or by changing the aggregate state of a part of the object or external environment.', NULL, NULL, 'GROUP-5-1'),
	('STANDARD 5-1-2', 'If a system is not easy to change as required, and the conditions do not allow to replace the component acting as an instrument or introduce additives, the artifact has to be used instead of the instrument, dividing the artifact into parts interacting with each other.', NULL, NULL, 'GROUP-5-1'),
	('STANDARD 5-1-3', 'After the substance introduced in the system has fulfilled its function, it should either disappear or become indistinguishable from the substance that was in the system or in the external environment before. NOTE: The substance that has been introduced may disappear due to chemical reactions or change of phase.', NULL, NULL, 'GROUP-5-1'),
	('STANDARD 5-1-4', 'If it is necessary to introduce a large quantity of a substance, but this is not allowed, a \'void\' in the form of inflatable structures or foam should be used as the substance. NOTE:	Introduction of foam or inflatable structures resolves a contradiction \'much substance - little substance\'.', NULL, NULL, 'GROUP-5-1'),
	('STANDARD 5-2-1', 'If a field has to be introduced in a SFM, one should use first of all the present fields for whom the media are those substances that form the system or its part. NOTES:	The use of substances and fields which already present in the system improves the system\'s ideality: number of functions performed by the system increases without increasing the number of used components.', NULL, NULL, 'GROUP-5-2'),
	('STANDARD 5-2-2', 'If a field has to be introduced in a SFM and it is not possible to use the fields which already present in the system, one should use the fields of the external environment. NOTE: The use of external environment fields (gravitation, thermal field, pressure...) improves the system\'s ideality: the number of functions performed by the system increases without increasing the number of used components.', NULL, NULL, 'GROUP-5-2'),
	('STANDARD 5-2-3', 'If a field has to be introduced in a SFM but it is impossible to use the fields which already present in the system or in the external environment, one should use the fields for whom the substances present in. the system or external environment can act as media or sources. NOTES:	In particular, if there are ferromagnetic substances in a system and they are used for mechanical purposes, it is possible to use their magnetic properties in order to obtain additional effects: improve interactions between components, obtain information on the state of the system, etc.', NULL, NULL, 'GROUP-5-2'),
	('STANDARD 5-3-1', 'Efficiency of the use of a substance without introducing other substances can be improved by changing its phase.', NULL, NULL, 'GROUP-5-3'),
	('STANDARD 5-3-2', '\'Dual\' properties are provided by using substances capable of converting from one phase to another according to operating conditions.', NULL, NULL, 'GROUP-5-3'),
	('STANDARD 5-3-3', 'Efficiency of a system can be improved by the use of physical phenomena accompanying a phase transition. NOTES: Structure of a substance, density, thermal conductivity, etc. also change along with the change of aggregate state during all types of phase transitions. In addition, during phase transitions, energy may be released or absorbed.', NULL, NULL, 'GROUP-5-3'),
	('STANDARD 5-3-4', '\'Dual\' properties of a system are provided by replacing a single-phase state of the substance with a dual-phase state.', NULL, NULL, 'GROUP-5-3'),
	('STANDARD 5-3-5', 'Efficiency of systems obtained as a result of replacing a substance\'s single-phase state with a dual-phase state can be improved by introducing interaction .(physical or chemical) between parts (phases) of the system.', NULL, NULL, 'GROUP-5-3'),
	('STANDARD 5-4-1', 'If an object is to be alternating between different physical states, the transition is performed by the object itself using reversible physical transformations, e.g. phase transitions, ionization-recombination, dissociation-association, etc. NOTE: A dynamic balance providing for the process self-adjustment or stabilization may be maintained in the dual-phase state.', NULL, NULL, 'GROUP-5-4'),
	('STANDARD 5-4-2', 'If it is necessary to obtain a strong effect at the system\'s output, given a weak effect at the input, the transformer substance is placed to a condition close to critical. The energy is stored in the substance, and the input signal acts a \'trigger\'.', NULL, NULL, 'GROUP-5-4'),
	('STANDARD 5-5-1', 'If substance particles (e. g. ions) are required to solve a problem and they are not available according to the problem conditions, the required particles can be obtained by decomposing a substance of a higher structural level (e.g. molecules).', NULL, NULL, 'GROUP-5-5'),
	('STANDARD 5-5-2', 'If substance particles (e.g. molecules) are required to solve a problem and they can not be produced by decomposing a substance of a higher structural level, the required particles can be obtained by combining particles of a lower structural level (e.g. ions).', NULL, NULL, 'GROUP-5-5'),
	('STANDARD 5-5-3', 'If a substance of a higher structural level has to be decomposed, the easiest way is to decompose the nearest higher element. When combining particles of a lower structural level, the easiest way is to complete the nearest lower element.', NULL, NULL, 'GROUP-5-5');
/*!40000 ALTER TABLE `inventive_standard` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.inventive_standard_classification
DROP TABLE IF EXISTS `inventive_standard_classification`;
CREATE TABLE IF NOT EXISTS `inventive_standard_classification` (
  `class_number` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `class_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`class_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.inventive_standard_classification: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `inventive_standard_classification` DISABLE KEYS */;
INSERT INTO `inventive_standard_classification` (`class_number`, `class_name`) VALUES
	('CLASS-1', 'CLASS 1. COMPOSITION AND DESCOMPOSITION OF SFMS'),
	('CLASS-2', 'CLASS 2. EVOLUTION OF SFMS'),
	('CLASS-3', 'CLASS 3. TRANSITIONS TO SUPERSYSTEM AND MICROLEVEL'),
	('CLASS-4', 'CLASS 4. MEASUREMENT AND DETECTION STANDARDS'),
	('CLASS-5', 'CLASS 5. HELPERS');
/*!40000 ALTER TABLE `inventive_standard_classification` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.inventive_standard_group
DROP TABLE IF EXISTS `inventive_standard_group`;
CREATE TABLE IF NOT EXISTS `inventive_standard_group` (
  `group_number` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `group_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `class_number` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`group_number`),
  KEY `FKB32F7D9E26A23A3` (`class_number`),
  CONSTRAINT `FKB32F7D9E26A23A3` FOREIGN KEY (`class_number`) REFERENCES `inventive_standard_classification` (`class_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.inventive_standard_group: ~18 rows (aproximadamente)
/*!40000 ALTER TABLE `inventive_standard_group` DISABLE KEYS */;
INSERT INTO `inventive_standard_group` (`group_number`, `group_name`, `class_number`) VALUES
	('GROUP-1-1', 'GROUP 1-1: SYNTHESIS OF SFMS', 'CLASS-1'),
	('GROUP-1-2', 'GROUP 1-2: DECOMPOSITION OF SFMS', 'CLASS-1'),
	('GROUP-2-1', 'GROUP 2-1: TRANSITION TO COMPLEX SFMS', 'CLASS-2'),
	('GROUP-2-2', 'GROUP 2-2: EVOLUTION OF SFMS', 'CLASS-2'),
	('GROUP-2-3', 'GROUP 2-3: EVOLUTION BY COORDINATING RHYTHMS', 'CLASS-2'),
	('GROUP-2-4', 'GROUP 2-4: COMPLEX-FORCED SFMS (F-SFMS)', 'CLASS-2'),
	('GROUP-3-1', 'GROUP 3-1: TRANSITIONS TO BISYSTEM AND POLYSYSTEM', 'CLASS-3'),
	('GROUP-3-2', 'GROUP 3-2: TRANSITION TO MICROLEVEL', 'CLASS-3'),
	('GROUP-4-1', 'GROUP 4-1: CHANGE INSTEAD OF MEASUREMENT AND DETECTION', 'CLASS-4'),
	('GROUP-4-2', 'GROUP 4-2: SYNTHESIS OF MEASUREMENT SYSTEM', 'CLASS-4'),
	('GROUP-4-3', 'GROUP 4-3: IMPROVEMENT OF MEASUREMENT SYSTEMS', 'CLASS-4'),
	('GROUP-4-4', 'GROUP 4-4: TRANSITION TO FERROMAGNETIC MEASUREMENT SYSTEMS', 'CLASS-4'),
	('GROUP-4-5', 'GROUP 4-5: EVOLUTION OF MEASUREMENT SYSTEMS', 'CLASS-4'),
	('GROUP-5-1', 'GROUP 5-1: INTRODUCTION OF SUBSTANCES UNDER RESTRICTED CONDITIONS', 'CLASS-5'),
	('GROUP-5-2', 'GROUP 5-2: INTRODUCTION OF FIELDS UNDER RESTRICTED CONDITIONS', 'CLASS-5'),
	('GROUP-5-3', 'GROUP 5-3: USE OF PHASE TRANSITIONS', 'CLASS-5'),
	('GROUP-5-4', 'GROUP 5-4: USE OF PHYSICAL EFFECTS', 'CLASS-5'),
	('GROUP-5-5', 'GROUP 5-5: OBTAINING SUBSTANCE PARTICLES', 'CLASS-5');
/*!40000 ALTER TABLE `inventive_standard_group` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.invitation
DROP TABLE IF EXISTS `invitation`;
CREATE TABLE IF NOT EXISTS `invitation` (
  `invitation_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `invitation_date` datetime NOT NULL,
  `is_accepted` bit(1) DEFAULT NULL,
  `invitation_message` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `profile_host` bigint(20) NOT NULL,
  `profile_guest` bigint(20) NOT NULL,
  PRIMARY KEY (`invitation_id`),
  KEY `FK473F77992521D444` (`project_id`),
  KEY `FK473F77996F4AE695` (`profile_guest`),
  KEY `FK473F7799E3793551` (`profile_host`),
  CONSTRAINT `FK473F7799E3793551` FOREIGN KEY (`profile_host`) REFERENCES `profile` (`profile_id`),
  CONSTRAINT `FK473F77992521D444` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`),
  CONSTRAINT `FK473F77996F4AE695` FOREIGN KEY (`profile_guest`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.invitation: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `invitation` DISABLE KEYS */;
/*!40000 ALTER TABLE `invitation` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.invitation_mail
DROP TABLE IF EXISTS `invitation_mail`;
CREATE TABLE IF NOT EXISTS `invitation_mail` (
  `invitation_mail_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hilo_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_accepted` bit(1) DEFAULT NULL,
  `message` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `project_id` bigint(20) NOT NULL,
  `profile_host_id` bigint(20) NOT NULL,
  PRIMARY KEY (`invitation_mail_id`),
  UNIQUE KEY `hilo_id` (`hilo_id`),
  KEY `FK83561E9D2521D444` (`project_id`),
  KEY `FK83561E9D68BAA14F` (`profile_host_id`),
  CONSTRAINT `FK83561E9D68BAA14F` FOREIGN KEY (`profile_host_id`) REFERENCES `profile` (`profile_id`),
  CONSTRAINT `FK83561E9D2521D444` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.invitation_mail: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `invitation_mail` DISABLE KEYS */;
/*!40000 ALTER TABLE `invitation_mail` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.notification
DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `notification_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `notification_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `project_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`notification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.notification: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` (`notification_id`, `created_on`, `description`, `fullname`, `notification_type`, `project_name`) VALUES
	(4, '2015-01-22 23:40:20', 'Project creation', 'Antonino Salas', 'PROJECT_CREATED', 'nueva funda'),
	(5, '2015-01-22 23:40:45', 'Project edition', 'Antonino Salas', 'PROJECT_EDITED', 'nueva funda'),
	(6, '2015-01-22 23:41:24', 'Project edition', 'Antonino Salas', 'PROJECT_EDITED', 'nueva funda'),
	(8, '2015-01-22 23:55:46', 'Project edition', 'Antonino Salas', 'PROJECT_EDITED', 'nueva funda'),
	(9, '2015-01-22 23:56:26', 'Project edition', 'Antonino Salas', 'PROJECT_EDITED', 'nueva funda');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.no_standards_list
DROP TABLE IF EXISTS `no_standards_list`;
CREATE TABLE IF NOT EXISTS `no_standards_list` (
  `questionNumber` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inventiveStandardNumber` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  KEY `FK8EBC1C2510C980CE` (`questionNumber`),
  KEY `FK8EBC1C25BBA317C9` (`inventiveStandardNumber`),
  CONSTRAINT `FK8EBC1C25BBA317C9` FOREIGN KEY (`inventiveStandardNumber`) REFERENCES `inventive_standard` (`standard_number`),
  CONSTRAINT `FK8EBC1C2510C980CE` FOREIGN KEY (`questionNumber`) REFERENCES `algorithm_question` (`question_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.no_standards_list: ~93 rows (aproximadamente)
/*!40000 ALTER TABLE `no_standards_list` DISABLE KEYS */;
INSERT INTO `no_standards_list` (`questionNumber`, `inventiveStandardNumber`) VALUES
	('Q4.1.1', 'STANDARD 5-1-1-1'),
	('Q4.1.1', 'STANDARD 5-1-1-2'),
	('Q4.1.1', 'STANDARD 5-1-1-3'),
	('Q4.1.1', 'STANDARD 5-1-1-4'),
	('Q4.1.1', 'STANDARD 5-1-1-5'),
	('Q4.1.1', 'STANDARD 5-1-1-6'),
	('Q4.1.1', 'STANDARD 5-1-1-7'),
	('Q4.1.1', 'STANDARD 5-1-1-8'),
	('Q4.1.1', 'STANDARD 5-1-1-9'),
	('Q4.1.1', 'STANDARD 5-1-2'),
	('Q4.1.1', 'STANDARD 5-1-3'),
	('Q4.1.1', 'STANDARD 5-1-4'),
	('Q4.1.1', 'STANDARD 5-2-1'),
	('Q4.1.1', 'STANDARD 5-2-2'),
	('Q4.1.1', 'STANDARD 5-2-3'),
	('Q4.1.1', 'STANDARD 5-5-1'),
	('Q4.1.1', 'STANDARD 5-5-2'),
	('Q4.1.1', 'STANDARD 5-5-3'),
	('Q4.2', 'STANDARD 5-1-1-1'),
	('Q4.2', 'STANDARD 5-1-1-2'),
	('Q4.2', 'STANDARD 5-1-1-3'),
	('Q4.2', 'STANDARD 5-1-1-4'),
	('Q4.2', 'STANDARD 5-1-1-5'),
	('Q4.2', 'STANDARD 5-1-1-6'),
	('Q4.2', 'STANDARD 5-1-1-7'),
	('Q4.2', 'STANDARD 5-1-1-8'),
	('Q4.2', 'STANDARD 5-1-1-9'),
	('Q4.2', 'STANDARD 5-1-2'),
	('Q4.2', 'STANDARD 5-1-3'),
	('Q4.2', 'STANDARD 5-1-4'),
	('Q4.2', 'STANDARD 5-2-1'),
	('Q4.2', 'STANDARD 5-2-2'),
	('Q4.2', 'STANDARD 5-2-3'),
	('Q4.2', 'STANDARD 5-5-1'),
	('Q4.2', 'STANDARD 5-5-2'),
	('Q4.2', 'STANDARD 5-5-3'),
	('Q5.1', 'STANDARD 5-1-1-1'),
	('Q5.1', 'STANDARD 5-1-1-2'),
	('Q5.1', 'STANDARD 5-1-1-3'),
	('Q5.1', 'STANDARD 5-1-1-4'),
	('Q5.1', 'STANDARD 5-1-1-5'),
	('Q5.1', 'STANDARD 5-1-1-6'),
	('Q5.1', 'STANDARD 5-1-1-7'),
	('Q5.1', 'STANDARD 5-1-1-8'),
	('Q5.1', 'STANDARD 5-1-1-9'),
	('Q5.1', 'STANDARD 5-1-2'),
	('Q5.1', 'STANDARD 5-1-3'),
	('Q5.1', 'STANDARD 5-1-4'),
	('Q5.1', 'STANDARD 5-2-1'),
	('Q5.1', 'STANDARD 5-2-2'),
	('Q5.1', 'STANDARD 5-2-3'),
	('Q5.1', 'STANDARD 5-5-1'),
	('Q5.1', 'STANDARD 5-5-2'),
	('Q5.1', 'STANDARD 5-5-3'),
	('Q10', 'STANDARD 2-2-2'),
	('Q10', 'STANDARD 2-2-3'),
	('Q10', 'STANDARD 2-2-4'),
	('Q11', 'STANDARD 2-2-5'),
	('Q11', 'STANDARD 2-2-6'),
	('Q11', 'STANDARD 4-3-1'),
	('Q11', 'STANDARD 5-3-1'),
	('Q11', 'STANDARD 5-3-2'),
	('Q11', 'STANDARD 5-3-3'),
	('Q11', 'STANDARD 5-3-4'),
	('Q11', 'STANDARD 5-3-5'),
	('Q11', 'STANDARD 5-4-1'),
	('Q11', 'STANDARD 5-4-2'),
	('Q12', 'STANDARD 2-3-1'),
	('Q12', 'STANDARD 2-3-2'),
	('Q12', 'STANDARD 2-3-3'),
	('Q12', 'STANDARD 4-3-2'),
	('Q12', 'STANDARD 4-3-3'),
	('Q16', 'STANDARD 3-2-1'),
	('Q16', 'STANDARD 3-1-1'),
	('Q16', 'STANDARD 3-1-2'),
	('Q16', 'STANDARD 3-1-3'),
	('Q16', 'STANDARD 3-1-5'),
	('Q17', 'STANDARD 2-4-2'),
	('Q17', 'STANDARD 2-4-3'),
	('Q17', 'STANDARD 2-4-4'),
	('Q17', 'STANDARD 2-4-7'),
	('Q17', 'STANDARD 2-4-8'),
	('Q17', 'STANDARD 4-4-2'),
	('Q18', 'STANDARD 2-4-9'),
	('Q19', 'STANDARD 2-4-10'),
	('Q19', 'STANDARD 4-4-5'),
	('Q19', 'STANDARD 5-3-1'),
	('Q19', 'STANDARD 5-3-2'),
	('Q19', 'STANDARD 5-3-3'),
	('Q19', 'STANDARD 5-3-4'),
	('Q19', 'STANDARD 5-3-5'),
	('Q19', 'STANDARD 5-4-1'),
	('Q19', 'STANDARD 5-4-2');
/*!40000 ALTER TABLE `no_standards_list` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.picture_solution
DROP TABLE IF EXISTS `picture_solution`;
CREATE TABLE IF NOT EXISTS `picture_solution` (
  `picture_solution_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `picture` longblob,
  `project_solution_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`picture_solution_id`),
  KEY `FK8847DF9AE269C057` (`project_solution_id`),
  CONSTRAINT `FK8847DF9AE269C057` FOREIGN KEY (`project_solution_id`) REFERENCES `project_solution` (`project_solution_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.picture_solution: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `picture_solution` DISABLE KEYS */;
/*!40000 ALTER TABLE `picture_solution` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.problem_description
DROP TABLE IF EXISTS `problem_description`;
CREATE TABLE IF NOT EXISTS `problem_description` (
  `problem_desc_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brief_description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `estimated_relase` datetime DEFAULT NULL,
  `improvement_drawback` longtext COLLATE utf8_unicode_ci,
  `long_description` longtext COLLATE utf8_unicode_ci,
  `problem_nature` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`problem_desc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.problem_description: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `problem_description` DISABLE KEYS */;
INSERT INTO `problem_description` (`problem_desc_id`, `brief_description`, `estimated_relase`, `improvement_drawback`, `long_description`, `problem_nature`) VALUES
	(1, 'mi primer proyecto', '2015-03-23 00:00:00', 'mejora', 'la situación del proyecto', 'ADD_NEW_FUNCTION');
/*!40000 ALTER TABLE `problem_description` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.profile
DROP TABLE IF EXISTS `profile`;
CREATE TABLE IF NOT EXISTS `profile` (
  `profile_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dashboard_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`profile_id`),
  KEY `FKED8E89A980F114F0` (`user_id`),
  KEY `FKED8E89A9E7BCBBE4` (`dashboard_id`),
  CONSTRAINT `FKED8E89A9E7BCBBE4` FOREIGN KEY (`dashboard_id`) REFERENCES `dashboard` (`dashboard_id`),
  CONSTRAINT `FKED8E89A980F114F0` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.profile: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` (`profile_id`, `last_name`, `name`, `dashboard_id`, `user_id`) VALUES
	(1, 'Salas', 'Antonino', 1, 1);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.project
DROP TABLE IF EXISTS `project`;
CREATE TABLE IF NOT EXISTS `project` (
  `project_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime NOT NULL,
  `project_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `project_status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ifr_id` bigint(20) NOT NULL,
  `problem_desc_id` bigint(20) NOT NULL,
  `profile_id` bigint(20) NOT NULL,
  `project_solution_id` bigint(20) NOT NULL,
  `superSystemComponents_system_description_id` bigint(20) DEFAULT NULL,
  `systemComponents_system_description_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  KEY `FKED904B191ED1890B` (`systemComponents_system_description_id`),
  KEY `FKED904B19E3C88446` (`superSystemComponents_system_description_id`),
  KEY `FKED904B19E269C057` (`project_solution_id`),
  KEY `FKED904B19D58F45C` (`problem_desc_id`),
  KEY `FKED904B197C317C4` (`ifr_id`),
  KEY `FKED904B1958D28A44` (`profile_id`),
  CONSTRAINT `FKED904B1958D28A44` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`profile_id`),
  CONSTRAINT `FKED904B191ED1890B` FOREIGN KEY (`systemComponents_system_description_id`) REFERENCES `system_description` (`system_description_id`),
  CONSTRAINT `FKED904B197C317C4` FOREIGN KEY (`ifr_id`) REFERENCES `ifr` (`ifr_id`),
  CONSTRAINT `FKED904B19D58F45C` FOREIGN KEY (`problem_desc_id`) REFERENCES `problem_description` (`problem_desc_id`),
  CONSTRAINT `FKED904B19E269C057` FOREIGN KEY (`project_solution_id`) REFERENCES `project_solution` (`project_solution_id`),
  CONSTRAINT `FKED904B19E3C88446` FOREIGN KEY (`superSystemComponents_system_description_id`) REFERENCES `system_description` (`system_description_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.project: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` (`project_id`, `created_on`, `project_name`, `project_status`, `ifr_id`, `problem_desc_id`, `profile_id`, `project_solution_id`, `superSystemComponents_system_description_id`, `systemComponents_system_description_id`) VALUES
	(1, '2015-01-22 23:40:20', 'nueva funda', 'WORKING', 1, 1, 1, 1, 1, 2);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.project_contradiction
DROP TABLE IF EXISTS `project_contradiction`;
CREATE TABLE IF NOT EXISTS `project_contradiction` (
  `project_contradiction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `negative_description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `positive_description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `negativeCharacteristic_characteristic_id` bigint(20) DEFAULT NULL,
  `positiveCharacteristic_characteristic_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`project_contradiction_id`),
  KEY `FKD53B5DEB9F4CEFC5` (`positiveCharacteristic_characteristic_id`),
  KEY `FKD53B5DEBE9E97201` (`negativeCharacteristic_characteristic_id`),
  CONSTRAINT `FKD53B5DEBE9E97201` FOREIGN KEY (`negativeCharacteristic_characteristic_id`) REFERENCES `characteristic` (`characteristic_id`),
  CONSTRAINT `FKD53B5DEB9F4CEFC5` FOREIGN KEY (`positiveCharacteristic_characteristic_id`) REFERENCES `characteristic` (`characteristic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.project_contradiction: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `project_contradiction` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_contradiction` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.project_project_resource
DROP TABLE IF EXISTS `project_project_resource`;
CREATE TABLE IF NOT EXISTS `project_project_resource` (
  `project_project_id` bigint(20) NOT NULL,
  `projectResourceList_project_resource_id` bigint(20) NOT NULL,
  UNIQUE KEY `projectResourceList_project_resource_id` (`projectResourceList_project_resource_id`),
  KEY `FK67C8A2DACAFC203D` (`projectResourceList_project_resource_id`),
  KEY `FK67C8A2DA2A3C252A` (`project_project_id`),
  CONSTRAINT `FK67C8A2DA2A3C252A` FOREIGN KEY (`project_project_id`) REFERENCES `project` (`project_id`),
  CONSTRAINT `FK67C8A2DACAFC203D` FOREIGN KEY (`projectResourceList_project_resource_id`) REFERENCES `project_resource` (`project_resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.project_project_resource: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `project_project_resource` DISABLE KEYS */;
INSERT INTO `project_project_resource` (`project_project_id`, `projectResourceList_project_resource_id`) VALUES
	(1, 1);
/*!40000 ALTER TABLE `project_project_resource` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.project_resource
DROP TABLE IF EXISTS `project_resource`;
CREATE TABLE IF NOT EXISTS `project_resource` (
  `project_resource_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resource_category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`project_resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.project_resource: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `project_resource` DISABLE KEYS */;
INSERT INTO `project_resource` (`project_resource_id`, `resource_category_id`) VALUES
	(1, 5);
/*!40000 ALTER TABLE `project_resource` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.project_resource_selected_resource
DROP TABLE IF EXISTS `project_resource_selected_resource`;
CREATE TABLE IF NOT EXISTS `project_resource_selected_resource` (
  `project_resource_project_resource_id` bigint(20) NOT NULL,
  `selectedResourceList_selected_resource_id` bigint(20) NOT NULL,
  UNIQUE KEY `selectedResourceList_selected_resource_id` (`selectedResourceList_selected_resource_id`),
  KEY `FK4F618C2719599DEC` (`project_resource_project_resource_id`),
  KEY `FK4F618C27244B529F` (`selectedResourceList_selected_resource_id`),
  CONSTRAINT `FK4F618C27244B529F` FOREIGN KEY (`selectedResourceList_selected_resource_id`) REFERENCES `selected_resource` (`selected_resource_id`),
  CONSTRAINT `FK4F618C2719599DEC` FOREIGN KEY (`project_resource_project_resource_id`) REFERENCES `project_resource` (`project_resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.project_resource_selected_resource: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `project_resource_selected_resource` DISABLE KEYS */;
INSERT INTO `project_resource_selected_resource` (`project_resource_project_resource_id`, `selectedResourceList_selected_resource_id`) VALUES
	(1, 1);
/*!40000 ALTER TABLE `project_resource_selected_resource` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.project_restriction
DROP TABLE IF EXISTS `project_restriction`;
CREATE TABLE IF NOT EXISTS `project_restriction` (
  `project_project_id` bigint(20) NOT NULL,
  `restrictionList_restriction_id` bigint(20) NOT NULL,
  UNIQUE KEY `restrictionList_restriction_id` (`restrictionList_restriction_id`),
  KEY `FK4123DFA657CA1CD9` (`restrictionList_restriction_id`),
  KEY `FK4123DFA62A3C252A` (`project_project_id`),
  CONSTRAINT `FK4123DFA62A3C252A` FOREIGN KEY (`project_project_id`) REFERENCES `project` (`project_id`),
  CONSTRAINT `FK4123DFA657CA1CD9` FOREIGN KEY (`restrictionList_restriction_id`) REFERENCES `restriction` (`restriction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.project_restriction: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `project_restriction` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_restriction` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.project_solution
DROP TABLE IF EXISTS `project_solution`;
CREATE TABLE IF NOT EXISTS `project_solution` (
  `project_solution_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alternative_solution` longtext COLLATE utf8_unicode_ci,
  `comments_in_solution` longtext COLLATE utf8_unicode_ci,
  `expert_contact_detail` longtext COLLATE utf8_unicode_ci,
  `how_was_it_adapted` longtext COLLATE utf8_unicode_ci,
  `information_sources` longtext COLLATE utf8_unicode_ci,
  `problem_with_solution` longtext COLLATE utf8_unicode_ci,
  `resource_implementation` longtext COLLATE utf8_unicode_ci,
  `satisfaction_percent` int(11) DEFAULT NULL,
  PRIMARY KEY (`project_solution_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.project_solution: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `project_solution` DISABLE KEYS */;
INSERT INTO `project_solution` (`project_solution_id`, `alternative_solution`, `comments_in_solution`, `expert_contact_detail`, `how_was_it_adapted`, `information_sources`, `problem_with_solution`, `resource_implementation`, `satisfaction_percent`) VALUES
	(1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
/*!40000 ALTER TABLE `project_solution` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.project_solution_restriction
DROP TABLE IF EXISTS `project_solution_restriction`;
CREATE TABLE IF NOT EXISTS `project_solution_restriction` (
  `project_solution_project_solution_id` bigint(20) NOT NULL,
  `satisfiedConstrainList_restriction_id` bigint(20) NOT NULL,
  UNIQUE KEY `satisfiedConstrainList_restriction_id` (`satisfiedConstrainList_restriction_id`),
  KEY `FK2A1F3B6C264347F7` (`project_solution_project_solution_id`),
  KEY `FK2A1F3B6CAFB4DE80` (`satisfiedConstrainList_restriction_id`),
  CONSTRAINT `FK2A1F3B6CAFB4DE80` FOREIGN KEY (`satisfiedConstrainList_restriction_id`) REFERENCES `restriction` (`restriction_id`),
  CONSTRAINT `FK2A1F3B6C264347F7` FOREIGN KEY (`project_solution_project_solution_id`) REFERENCES `project_solution` (`project_solution_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.project_solution_restriction: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `project_solution_restriction` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_solution_restriction` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.project_solution_selected_resource
DROP TABLE IF EXISTS `project_solution_selected_resource`;
CREATE TABLE IF NOT EXISTS `project_solution_selected_resource` (
  `project_solution_project_solution_id` bigint(20) NOT NULL,
  `utilizedResourceList_selected_resource_id` bigint(20) NOT NULL,
  UNIQUE KEY `utilizedResourceList_selected_resource_id` (`utilizedResourceList_selected_resource_id`),
  KEY `FK72E9B0D2264347F7` (`project_solution_project_solution_id`),
  KEY `FK72E9B0D2D5F66F88` (`utilizedResourceList_selected_resource_id`),
  CONSTRAINT `FK72E9B0D2D5F66F88` FOREIGN KEY (`utilizedResourceList_selected_resource_id`) REFERENCES `selected_resource` (`selected_resource_id`),
  CONSTRAINT `FK72E9B0D2264347F7` FOREIGN KEY (`project_solution_project_solution_id`) REFERENCES `project_solution` (`project_solution_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.project_solution_selected_resource: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `project_solution_selected_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_solution_selected_resource` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.resource
DROP TABLE IF EXISTS `resource`;
CREATE TABLE IF NOT EXISTS `resource` (
  `resource_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.resource: ~10 rows (aproximadamente)
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` (`resource_id`, `resource_name`) VALUES
	(1, 'Stiffness of steel'),
	(2, 'Ferromagnetic of steel'),
	(3, 'Pressure Kg/Cm2 estimated 10 kg/cm2'),
	(4, 'Friction'),
	(5, 'Sonic'),
	(6, 'Stiffness of steel'),
	(7, 'Ferromagnetic of steel'),
	(8, 'Pressure Kg/Cm2 estimated 10 kg/cm2'),
	(9, 'Friction'),
	(10, 'Sonic');
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.resource_category
DROP TABLE IF EXISTS `resource_category`;
CREATE TABLE IF NOT EXISTS `resource_category` (
  `resource_category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `parent_category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`resource_category_id`),
  KEY `FKA3BCDA0F5AFAA529` (`parent_category_id`),
  CONSTRAINT `FKA3BCDA0F5AFAA529` FOREIGN KEY (`parent_category_id`) REFERENCES `resource_category` (`resource_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.resource_category: ~10 rows (aproximadamente)
/*!40000 ALTER TABLE `resource_category` DISABLE KEYS */;
INSERT INTO `resource_category` (`resource_category_id`, `category_name`, `parent_category_id`) VALUES
	(1, 'ResourceCategoryTree', NULL),
	(2, 'Substances', 1),
	(3, 'Properties of the substance', 2),
	(4, 'Fields', 1),
	(5, 'Mechanicals', 4),
	(6, 'Space', 1),
	(7, 'Empty', 6),
	(8, 'Internal placement', 6),
	(9, 'Time', 1),
	(10, 'Programming and sequencing', 9);
/*!40000 ALTER TABLE `resource_category` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.resource_category_resource
DROP TABLE IF EXISTS `resource_category_resource`;
CREATE TABLE IF NOT EXISTS `resource_category_resource` (
  `resource_category_resource_category_id` bigint(20) NOT NULL,
  `resourceList_resource_id` bigint(20) NOT NULL,
  UNIQUE KEY `resourceList_resource_id` (`resourceList_resource_id`),
  KEY `FK9B76FC3E3E4408FD` (`resourceList_resource_id`),
  KEY `FK9B76FC3E9C31DABD` (`resource_category_resource_category_id`),
  CONSTRAINT `FK9B76FC3E9C31DABD` FOREIGN KEY (`resource_category_resource_category_id`) REFERENCES `resource_category` (`resource_category_id`),
  CONSTRAINT `FK9B76FC3E3E4408FD` FOREIGN KEY (`resourceList_resource_id`) REFERENCES `resource` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.resource_category_resource: ~10 rows (aproximadamente)
/*!40000 ALTER TABLE `resource_category_resource` DISABLE KEYS */;
INSERT INTO `resource_category_resource` (`resource_category_resource_category_id`, `resourceList_resource_id`) VALUES
	(3, 1),
	(3, 2),
	(5, 3),
	(5, 4),
	(5, 5),
	(13, 6),
	(13, 7),
	(15, 8),
	(15, 9),
	(15, 10);
/*!40000 ALTER TABLE `resource_category_resource` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.restriction
DROP TABLE IF EXISTS `restriction`;
CREATE TABLE IF NOT EXISTS `restriction` (
  `restriction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `str_condition` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `final_value` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `initial_value` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_single_value` bit(1) DEFAULT NULL,
  `value_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `characteristic_characteristic_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`restriction_id`),
  KEY `FKA2F413CCE48879CC` (`characteristic_characteristic_id`),
  CONSTRAINT `FKA2F413CCE48879CC` FOREIGN KEY (`characteristic_characteristic_id`) REFERENCES `characteristic` (`characteristic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.restriction: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `restriction` DISABLE KEYS */;
/*!40000 ALTER TABLE `restriction` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.selected_resource
DROP TABLE IF EXISTS `selected_resource`;
CREATE TABLE IF NOT EXISTS `selected_resource` (
  `selected_resource_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `selected_value` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `resource_resource_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`selected_resource_id`),
  KEY `FK1512C132221D8C5F` (`resource_resource_id`),
  CONSTRAINT `FK1512C132221D8C5F` FOREIGN KEY (`resource_resource_id`) REFERENCES `resource` (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.selected_resource: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `selected_resource` DISABLE KEYS */;
INSERT INTO `selected_resource` (`selected_resource_id`, `selected_value`, `resource_resource_id`) VALUES
	(1, '2', 3);
/*!40000 ALTER TABLE `selected_resource` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.solution_contradiction
DROP TABLE IF EXISTS `solution_contradiction`;
CREATE TABLE IF NOT EXISTS `solution_contradiction` (
  `project_solution_id` bigint(20) NOT NULL,
  `solutionPrinciple_solution_principle_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`project_solution_id`),
  KEY `FK2F4B84BECAF0D55` (`solutionPrinciple_solution_principle_id`),
  KEY `FK2F4B84BE269C057` (`project_solution_id`),
  CONSTRAINT `FK2F4B84BE269C057` FOREIGN KEY (`project_solution_id`) REFERENCES `project_solution` (`project_solution_id`),
  CONSTRAINT `FK2F4B84BECAF0D55` FOREIGN KEY (`solutionPrinciple_solution_principle_id`) REFERENCES `solution_principle` (`solution_principle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.solution_contradiction: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `solution_contradiction` DISABLE KEYS */;
/*!40000 ALTER TABLE `solution_contradiction` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.solution_principle
DROP TABLE IF EXISTS `solution_principle`;
CREATE TABLE IF NOT EXISTS `solution_principle` (
  `solution_principle_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principle_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`solution_principle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.solution_principle: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `solution_principle` DISABLE KEYS */;
/*!40000 ALTER TABLE `solution_principle` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.solution_principle_subprinciple
DROP TABLE IF EXISTS `solution_principle_subprinciple`;
CREATE TABLE IF NOT EXISTS `solution_principle_subprinciple` (
  `solution_principle_solution_principle_id` bigint(20) NOT NULL,
  `subPrincipleList_subprinciple_id` bigint(20) NOT NULL,
  UNIQUE KEY `subPrincipleList_subprinciple_id` (`subPrincipleList_subprinciple_id`),
  KEY `FKCBCD76A5342E8CCB` (`subPrincipleList_subprinciple_id`),
  KEY `FKCBCD76A58E90A728` (`solution_principle_solution_principle_id`),
  CONSTRAINT `FKCBCD76A58E90A728` FOREIGN KEY (`solution_principle_solution_principle_id`) REFERENCES `solution_principle` (`solution_principle_id`),
  CONSTRAINT `FKCBCD76A5342E8CCB` FOREIGN KEY (`subPrincipleList_subprinciple_id`) REFERENCES `subprinciple` (`subprinciple_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.solution_principle_subprinciple: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `solution_principle_subprinciple` DISABLE KEYS */;
/*!40000 ALTER TABLE `solution_principle_subprinciple` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.solution_sufield
DROP TABLE IF EXISTS `solution_sufield`;
CREATE TABLE IF NOT EXISTS `solution_sufield` (
  `project_solution_id` bigint(20) NOT NULL,
  `inventiveStandard_standard_number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`project_solution_id`),
  KEY `FKE31AAB1289507F8C` (`inventiveStandard_standard_number`),
  KEY `FKE31AAB12E269C057` (`project_solution_id`),
  CONSTRAINT `FKE31AAB12E269C057` FOREIGN KEY (`project_solution_id`) REFERENCES `project_solution` (`project_solution_id`),
  CONSTRAINT `FKE31AAB1289507F8C` FOREIGN KEY (`inventiveStandard_standard_number`) REFERENCES `inventive_standard` (`standard_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.solution_sufield: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `solution_sufield` DISABLE KEYS */;
INSERT INTO `solution_sufield` (`project_solution_id`, `inventiveStandard_standard_number`) VALUES
	(1, 'STANDARD 1-1-1');
/*!40000 ALTER TABLE `solution_sufield` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.subprinciple
DROP TABLE IF EXISTS `subprinciple`;
CREATE TABLE IF NOT EXISTS `subprinciple` (
  `subprinciple_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subprinciple_description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`subprinciple_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.subprinciple: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `subprinciple` DISABLE KEYS */;
/*!40000 ALTER TABLE `subprinciple` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.sufield
DROP TABLE IF EXISTS `sufield`;
CREATE TABLE IF NOT EXISTS `sufield` (
  `project_id` bigint(20) NOT NULL,
  `standard_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sufield_graph_id` bigint(20) DEFAULT NULL,
  `sufield_model_id` bigint(20) NOT NULL,
  PRIMARY KEY (`project_id`),
  KEY `FK90E23FD85D8CDEA2` (`standard_id`),
  KEY `FK90E23FD82521D444` (`project_id`),
  KEY `FK90E23FD8962811DF` (`sufield_model_id`),
  KEY `FK90E23FD8D2B73815` (`sufield_graph_id`),
  CONSTRAINT `FK90E23FD8D2B73815` FOREIGN KEY (`sufield_graph_id`) REFERENCES `sufield_graph` (`sufield_graph_id`),
  CONSTRAINT `FK90E23FD82521D444` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`),
  CONSTRAINT `FK90E23FD85D8CDEA2` FOREIGN KEY (`standard_id`) REFERENCES `inventive_standard` (`standard_number`),
  CONSTRAINT `FK90E23FD8962811DF` FOREIGN KEY (`sufield_model_id`) REFERENCES `sufield_model` (`sufield_model_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.sufield: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `sufield` DISABLE KEYS */;
INSERT INTO `sufield` (`project_id`, `standard_id`, `sufield_graph_id`, `sufield_model_id`) VALUES
	(1, 'STANDARD 1-1-1', 6, 1);
/*!40000 ALTER TABLE `sufield` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.sufield_graph
DROP TABLE IF EXISTS `sufield_graph`;
CREATE TABLE IF NOT EXISTS `sufield_graph` (
  `sufield_graph_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`sufield_graph_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.sufield_graph: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `sufield_graph` DISABLE KEYS */;
INSERT INTO `sufield_graph` (`sufield_graph_id`) VALUES
	(6);
/*!40000 ALTER TABLE `sufield_graph` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.sufield_graph_edge
DROP TABLE IF EXISTS `sufield_graph_edge`;
CREATE TABLE IF NOT EXISTS `sufield_graph_edge` (
  `edge_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `edge_id_string` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `moved` bit(1) DEFAULT NULL,
  `source` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `target` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `x1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `x2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `y1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `y2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sufield_graph_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`edge_id`),
  KEY `FK6C4644D5D2B73815` (`sufield_graph_id`),
  CONSTRAINT `FK6C4644D5D2B73815` FOREIGN KEY (`sufield_graph_id`) REFERENCES `sufield_graph` (`sufield_graph_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.sufield_graph_edge: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `sufield_graph_edge` DISABLE KEYS */;
INSERT INTO `sufield_graph_edge` (`edge_id`, `description`, `edge_id_string`, `moved`, `source`, `target`, `type`, `x1`, `x2`, `y1`, `y2`, `sufield_graph_id`) VALUES
	(16, '', 'Edge9668', b'0', 'Node3910', 'Node8540', 'ApplicationConn', '427', '484', '169', '261', 6),
	(17, 'Primer%20Conector', 'Edge7834', b'0', 'Node3910', 'Node5648', 'ApplicationConn', '309', '247', '169', '264', 6),
	(18, '', 'Edge1023', b'0', 'Node5648', 'Node8540', 'InsufficientDesiredEffectConn', '247', '484', '301', '301', 6);
/*!40000 ALTER TABLE `sufield_graph_edge` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.sufield_graph_node
DROP TABLE IF EXISTS `sufield_graph_node`;
CREATE TABLE IF NOT EXISTS `sufield_graph_node` (
  `node_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `caption` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `node_id_string` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `x` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `y` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sufield_graph_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`node_id`),
  KEY `FK6C4A851AD2B73815` (`sufield_graph_id`),
  CONSTRAINT `FK6C4A851AD2B73815` FOREIGN KEY (`sufield_graph_id`) REFERENCES `sufield_graph` (`sufield_graph_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.sufield_graph_node: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `sufield_graph_node` DISABLE KEYS */;
INSERT INTO `sufield_graph_node` (`node_id`, `caption`, `description`, `node_id_string`, `type`, `x`, `y`, `sufield_graph_id`) VALUES
	(16, 'Vibration%20Force', 'Campo', 'Node3910', 'FieldNode', '309', '91', 6),
	(17, 'S1', 'Sustancia%201', 'Node8540', 'SubstanceNode', '484', '261', 6),
	(18, 'S2', 'Sustancia%202', 'Node5648', 'SubstanceNode', '129', '264', 6);
/*!40000 ALTER TABLE `sufield_graph_node` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.sufield_model
DROP TABLE IF EXISTS `sufield_model`;
CREATE TABLE IF NOT EXISTS `sufield_model` (
  `sufield_model_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `model_graph_ml` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`sufield_model_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.sufield_model: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `sufield_model` DISABLE KEYS */;
INSERT INTO `sufield_model` (`sufield_model_id`, `model_graph_ml`) VALUES
	(1, '<graph>\n  <node id="Node3910" type="FieldNode" x="309" y="91">\n    <caption>Vibration%20Force</caption>\n    <description>Campo</description>\n  </node>\n  <node id="Node8540" type="SubstanceNode" x="484" y="261">\n    <caption>S1</caption>\n    <description>Sustancia%201</description>\n  </node>\n  <node id="Node5648" type="SubstanceNode" x="129" y="264">\n    <caption>S2</caption>\n    <description>Sustancia%202</description>\n  </node>\n  <edge id="Edge9668" type="ApplicationConn" source="Node3910" target="Node8540" x1="427" y1="169" x2="484" y2="261" moved="false">\n    <description/>\n  </edge>\n  <edge id="Edge7834" type="ApplicationConn" source="Node3910" target="Node5648" x1="309" y1="169" x2="247" y2="264" moved="false">\n    <description>Primer%20Conector</description>\n  </edge>\n  <edge id="Edge1023" type="InsufficientDesiredEffectConn" source="Node5648" target="Node8540" x1="247" y1="301" x2="484" y2="301" moved="false">\n    <description/>\n  </edge>\n</graph>');
/*!40000 ALTER TABLE `sufield_model` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.system_description
DROP TABLE IF EXISTS `system_description`;
CREATE TABLE IF NOT EXISTS `system_description` (
  `system_description_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `control_unit` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `engine` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `power_source` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `transmition` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `working_unit` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`system_description_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.system_description: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `system_description` DISABLE KEYS */;
INSERT INTO `system_description` (`system_description_id`, `control_unit`, `engine`, `power_source`, `transmition`, `working_unit`) VALUES
	(1, 'super control', 'super engine', 'super power', 'super transmission', 'super working'),
	(2, 'control', 'engine', 'power', 'transmission', 'working');
/*!40000 ALTER TABLE `system_description` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.tree_field
DROP TABLE IF EXISTS `tree_field`;
CREATE TABLE IF NOT EXISTS `tree_field` (
  `tree_field_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `parent_field_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`tree_field_id`),
  KEY `FK3A57C919FFE3D11B` (`parent_field_id`),
  CONSTRAINT `FK3A57C919FFE3D11B` FOREIGN KEY (`parent_field_id`) REFERENCES `tree_field` (`tree_field_id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.tree_field: ~52 rows (aproximadamente)
/*!40000 ALTER TABLE `tree_field` DISABLE KEYS */;
INSERT INTO `tree_field` (`tree_field_id`, `name`, `parent_field_id`) VALUES
	(1, 'AllFields', NULL),
	(2, 'Mechanical', 1),
	(3, 'Vibration Force', 2),
	(4, 'Frictional Force', 2),
	(5, 'Centrifugal Force', 2),
	(6, 'Inertial Force', 2),
	(7, 'Gravitational Force', 2),
	(8, 'Coriolis Force', 2),
	(9, 'Tension Force', 2),
	(10, 'Compression Force', 2),
	(11, 'Elasticity Force', 2),
	(12, 'Reaction Force', 2),
	(13, 'Thermal', 1),
	(14, 'Thermal radiation', 13),
	(15, 'Convection', 13),
	(16, 'Heat conduction', 13),
	(17, 'Static temperature gradient', 13),
	(18, 'Total temperature gradient', 13),
	(19, 'Pressure', 1),
	(20, 'Buoyancy force', 19),
	(21, 'Static pressure', 19),
	(22, 'Dynamic pressure', 19),
	(23, 'Pressure gradient', 19),
	(24, 'Lift', 19),
	(25, 'Magnus force', 19),
	(26, 'Vaccum', 19),
	(27, 'Chemical', 1),
	(28, 'Diffusion', 27),
	(29, 'Combustion', 27),
	(30, 'Reduction', 27),
	(31, 'Oxidation', 27),
	(32, 'Electrical', 1),
	(33, 'Electrostatic', 32),
	(34, 'Electrodynamic', 32),
	(35, 'Alternating electric', 32),
	(36, 'Electrophoretic', 32),
	(37, 'Electromagnetic radiation', 32),
	(38, 'Magnetic', 1),
	(39, 'Alternating magnetic', 38),
	(40, 'Weak Nuclear Interaction', 1),
	(41, 'Strong Nuclear Interaction', 1),
	(42, 'Optical', 1),
	(43, 'Acoustic', 1),
	(44, 'Sound', 43),
	(45, 'Ultrasound', 43),
	(46, 'Odour', 1),
	(47, 'Biological', 1),
	(48, 'Osmotic', 47),
	(49, 'Catabolic', 47),
	(50, 'Anabolic', 47),
	(51, 'Photosynthesis', 47),
	(52, 'Enzyme action', 47);
/*!40000 ALTER TABLE `tree_field` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `profile_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK36EBCB58D28A44` (`profile_id`),
  CONSTRAINT `FK36EBCB58D28A44` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.user: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `email`, `password`, `user_name`, `profile_id`) VALUES
	(1, 'asalasmx@gmail.com', 'acbf157754bc921e70ab30b1e79c75f5', 'asalas', 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Volcando estructura para tabla itsolver.yes_standards_list
DROP TABLE IF EXISTS `yes_standards_list`;
CREATE TABLE IF NOT EXISTS `yes_standards_list` (
  `questionNumber` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inventiveStandardNumber` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  KEY `FK27FEA63F10C980CE` (`questionNumber`),
  KEY `FK27FEA63FBBA317C9` (`inventiveStandardNumber`),
  CONSTRAINT `FK27FEA63FBBA317C9` FOREIGN KEY (`inventiveStandardNumber`) REFERENCES `inventive_standard` (`standard_number`),
  CONSTRAINT `FK27FEA63F10C980CE` FOREIGN KEY (`questionNumber`) REFERENCES `algorithm_question` (`question_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Volcando datos para la tabla itsolver.yes_standards_list: ~34 rows (aproximadamente)
/*!40000 ALTER TABLE `yes_standards_list` DISABLE KEYS */;
INSERT INTO `yes_standards_list` (`questionNumber`, `inventiveStandardNumber`) VALUES
	('Q4.1.1', 'STANDARD 1-1-1'),
	('Q4.1.1', 'STANDARD 1-1-2'),
	('Q4.1.1', 'STANDARD 1-1-3'),
	('Q4.1.1', 'STANDARD 1-1-4'),
	('Q4.1.1', 'STANDARD 1-1-5'),
	('Q4.1.1', 'STANDARD 1-1-6'),
	('Q4.1.1', 'STANDARD 4-2-1'),
	('Q4.1.1', 'STANDARD 4-2-2'),
	('Q4.1.1', 'STANDARD 4-2-3'),
	('Q4.1.1', 'STANDARD 4-2-4'),
	('Q4.2', 'STANDARD 1-1-7'),
	('Q4.2', 'STANDARD 1-1-8-1'),
	('Q4.2', 'STANDARD 1-1-8-2'),
	('Q4.2', 'STANDARD 1-2-3'),
	('Q5.1', 'STANDARD 1-2-1'),
	('Q5.1', 'STANDARD 1-2-2'),
	('Q5.1', 'STANDARD 1-2-4'),
	('Q5.1', 'STANDARD 1-2-5'),
	('Q8', 'STANDARD 2-1-1'),
	('Q8', 'STANDARD 2-1-2'),
	('Q9', 'STANDARD 2-2-1'),
	('Q13', 'STANDARD 2-4-1'),
	('Q13', 'STANDARD 4-4-1'),
	('Q14', 'STANDARD 2-4-5'),
	('Q14', 'STANDARD 4-4-3'),
	('Q15', 'STANDARD 2-4-6'),
	('Q15', 'STANDARD 4-4-4'),
	('Q16', 'STANDARD 2-4-11'),
	('Q16', 'STANDARD 2-4-12'),
	('Q19', 'STANDARD 3-2-1'),
	('Q19', 'STANDARD 3-1-1'),
	('Q19', 'STANDARD 3-1-2'),
	('Q19', 'STANDARD 3-1-3'),
	('Q19', 'STANDARD 3-1-5');
/*!40000 ALTER TABLE `yes_standards_list` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
