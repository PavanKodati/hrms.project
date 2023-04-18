-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema project_healthcard
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema project_healthcard
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `project_healthcard` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `project_healthcard` ;

-- -----------------------------------------------------
-- Table `project_healthcard`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_healthcard`.`client` (
  `id` VARCHAR(255) NOT NULL,
  `client_name` VARCHAR(255) NULL DEFAULT NULL,
  `customer_value` INT NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project_healthcard`.`component`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_healthcard`.`component` (
  `id` VARCHAR(255) NOT NULL,
  `component_name` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `question_type` VARCHAR(255) NULL DEFAULT NULL,
  `component_question` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project_healthcard`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_healthcard`.`project` (
  `id` VARCHAR(255) NOT NULL,
  `end_date` DATETIME(6) NULL DEFAULT NULL,
  `logo` VARCHAR(255) NULL DEFAULT NULL,
  `project_name` VARCHAR(255) NULL DEFAULT NULL,
  `start_date` DATETIME(6) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `client_id` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK8nw995uro0115f1go0dmrtn2d` (`client_id` ASC) VISIBLE,
  CONSTRAINT `FK8nw995uro0115f1go0dmrtn2d`
    FOREIGN KEY (`client_id`)
    REFERENCES `project_healthcard`.`client` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project_healthcard`.`project_rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_healthcard`.`project_rating` (
  `id` VARCHAR(255) NOT NULL,
  `comment` VARCHAR(255) NULL DEFAULT NULL,
  `rating` INT NULL DEFAULT NULL,
  `rating_month` INT NULL DEFAULT NULL,
  `rating_year` INT NULL DEFAULT NULL,
  `project_id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK8ta2rnk0hgfuctu4wdoh0dvoe` (`project_id` ASC) VISIBLE,
  CONSTRAINT `FK8ta2rnk0hgfuctu4wdoh0dvoe`
    FOREIGN KEY (`project_id`)
    REFERENCES `project_healthcard`.`project` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project_healthcard`.`component_rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_healthcard`.`component_rating` (
  `id` VARCHAR(255) NOT NULL,
  `component_rating` INT NULL DEFAULT NULL,
  `notes` VARCHAR(255) NULL DEFAULT NULL,
  `component_id` VARCHAR(255) NULL DEFAULT NULL,
  `project_rating_id` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKbjas6c8pmhonfouj44j1b5dl8` (`component_id` ASC) VISIBLE,
  INDEX `FKdtqk5ghsmvl5v20kl258isaay` (`project_rating_id` ASC) VISIBLE,
  CONSTRAINT `FKbjas6c8pmhonfouj44j1b5dl8`
    FOREIGN KEY (`component_id`)
    REFERENCES `project_healthcard`.`component` (`id`),
  CONSTRAINT `FKdtqk5ghsmvl5v20kl258isaay`
    FOREIGN KEY (`project_rating_id`)
    REFERENCES `project_healthcard`.`project_rating` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project_healthcard`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_healthcard`.`employee` (
  `id` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `fl_id` VARCHAR(4) NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `role` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_fn32l5fj7l2lxir0xj53i7t7t` (`fl_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project_healthcard`.`project_component`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_healthcard`.`project_component` (
  `project_id` VARCHAR(255) NOT NULL,
  `component_id` VARCHAR(255) NOT NULL,
  INDEX `FKqqtn5h58qbneycw6fwu19hvdx` (`component_id` ASC) VISIBLE,
  INDEX `FKf570qadmpk4np8ioj4efoqwk` (`project_id` ASC) VISIBLE,
  CONSTRAINT `FKf570qadmpk4np8ioj4efoqwk`
    FOREIGN KEY (`project_id`)
    REFERENCES `project_healthcard`.`project` (`id`),
  CONSTRAINT `FKqqtn5h58qbneycw6fwu19hvdx`
    FOREIGN KEY (`component_id`)
    REFERENCES `project_healthcard`.`component` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project_healthcard`.`project_employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_healthcard`.`project_employees` (
  `id` VARCHAR(255) NOT NULL,
  `role` VARCHAR(255) NULL DEFAULT NULL,
  `employee_id` VARCHAR(255) NULL DEFAULT NULL,
  `project_id` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKcl7k41fte3cq0lykr31y46ts2` (`employee_id` ASC) VISIBLE,
  INDEX `FK7o5tsarf50drwfmrimkgkp4ds` (`project_id` ASC) VISIBLE,
  CONSTRAINT `FK7o5tsarf50drwfmrimkgkp4ds`
    FOREIGN KEY (`project_id`)
    REFERENCES `project_healthcard`.`project` (`id`),
  CONSTRAINT `FKcl7k41fte3cq0lykr31y46ts2`
    FOREIGN KEY (`employee_id`)
    REFERENCES `project_healthcard`.`employee` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;