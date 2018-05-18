-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema siomari_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema siomari_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `siomari_db` DEFAULT CHARACTER SET utf8 ;
USE `siomari_db` ;

-- -----------------------------------------------------
-- Table `siomari_db`.`canal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`canal` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(100) NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `caudal_disenio` DOUBLE NOT NULL,
  `longitud` DOUBLE NOT NULL,
  `seccion_tipica` VARCHAR(100) NOT NULL,
  `clase` VARCHAR(100) NOT NULL,
  `tipo` VARCHAR(100) NOT NULL,
  `categoria` VARCHAR(100) NOT NULL,
  `estado` VARCHAR(100) NOT NULL,
  `estado_descripcion` TEXT NOT NULL,
  `canal_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC),
  INDEX `fk_canal_canal1_idx` (`canal_id` ASC),
  CONSTRAINT `fk_canal_canal1`
    FOREIGN KEY (`canal_id`)
    REFERENCES `siomari_db`.`canal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`obra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`obra` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`canal_obra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`canal_obra` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `canal_id` INT(11) NOT NULL,
  `obra_id` INT(11) NOT NULL,
  `descripcion` TEXT NOT NULL,
  `x` DOUBLE NULL DEFAULT NULL,
  `y` DOUBLE NULL DEFAULT NULL,
  `altitud` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_canal_has_obra_obra1_idx` (`obra_id` ASC),
  INDEX `fk_canal_has_obra_canal1_idx` (`canal_id` ASC),
  CONSTRAINT `fk_canal_has_obra_canal1`
    FOREIGN KEY (`canal_id`)
    REFERENCES `siomari_db`.`canal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_canal_has_obra_obra1`
    FOREIGN KEY (`obra_id`)
    REFERENCES `siomari_db`.`obra` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`climatologia_datos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`climatologia_datos` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `evaporacion` FLOAT NOT NULL,
  `precipitacion` FLOAT NOT NULL,
  `q_precipitacion` FLOAT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 706
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`decada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`decada` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `decada_1` INT(11) NOT NULL,
  `decada_2` INT(11) NOT NULL,
  `decada_3` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_decada_climatologia_datos_idx` (`decada_1` ASC),
  INDEX `fk_decada_climatologia_datos1_idx` (`decada_2` ASC),
  INDEX `fk_decada_climatologia_datos2_idx` (`decada_3` ASC),
  CONSTRAINT `fk_decada_climatologia_datos`
    FOREIGN KEY (`decada_1`)
    REFERENCES `siomari_db`.`climatologia_datos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_decada_climatologia_datos1`
    FOREIGN KEY (`decada_2`)
    REFERENCES `siomari_db`.`climatologia_datos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_decada_climatologia_datos2`
    FOREIGN KEY (`decada_3`)
    REFERENCES `siomari_db`.`climatologia_datos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 232
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`climatologia_year`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`climatologia_year` (
  `year` INT(11) NOT NULL,
  `enero` INT(11) NULL DEFAULT NULL,
  `febrero` INT(11) NULL DEFAULT NULL,
  `marzo` INT(11) NULL DEFAULT NULL,
  `abril` INT(11) NULL DEFAULT NULL,
  `mayo` INT(11) NULL DEFAULT NULL,
  `junio` INT(11) NULL DEFAULT NULL,
  `julio` INT(11) NULL DEFAULT NULL,
  `agosto` INT(11) NULL DEFAULT NULL,
  `septiembre` INT(11) NULL DEFAULT NULL,
  `octubre` INT(11) NULL DEFAULT NULL,
  `noviembre` INT(11) NULL DEFAULT NULL,
  `diciembre` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`year`),
  INDEX `fk_climatologia_year_decada1_idx` (`enero` ASC),
  INDEX `fk_climatologia_year_decada2_idx` (`febrero` ASC),
  INDEX `fk_climatologia_year_decada3_idx` (`marzo` ASC),
  INDEX `fk_climatologia_year_decada4_idx` (`abril` ASC),
  INDEX `fk_climatologia_year_decada5_idx` (`mayo` ASC),
  INDEX `fk_climatologia_year_decada6_idx` (`junio` ASC),
  INDEX `fk_climatologia_year_decada7_idx` (`julio` ASC),
  INDEX `fk_climatologia_year_decada8_idx` (`agosto` ASC),
  INDEX `fk_climatologia_year_decada9_idx` (`septiembre` ASC),
  INDEX `fk_climatologia_year_decada10_idx` (`octubre` ASC),
  INDEX `fk_climatologia_year_decada11_idx` (`noviembre` ASC),
  INDEX `fk_climatologia_year_decada12_idx` (`diciembre` ASC),
  CONSTRAINT `fk_climatologia_year_decada1`
    FOREIGN KEY (`enero`)
    REFERENCES `siomari_db`.`decada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_climatologia_year_decada10`
    FOREIGN KEY (`octubre`)
    REFERENCES `siomari_db`.`decada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_climatologia_year_decada11`
    FOREIGN KEY (`noviembre`)
    REFERENCES `siomari_db`.`decada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_climatologia_year_decada12`
    FOREIGN KEY (`diciembre`)
    REFERENCES `siomari_db`.`decada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_climatologia_year_decada2`
    FOREIGN KEY (`febrero`)
    REFERENCES `siomari_db`.`decada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_climatologia_year_decada3`
    FOREIGN KEY (`marzo`)
    REFERENCES `siomari_db`.`decada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_climatologia_year_decada4`
    FOREIGN KEY (`abril`)
    REFERENCES `siomari_db`.`decada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_climatologia_year_decada5`
    FOREIGN KEY (`mayo`)
    REFERENCES `siomari_db`.`decada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_climatologia_year_decada6`
    FOREIGN KEY (`junio`)
    REFERENCES `siomari_db`.`decada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_climatologia_year_decada7`
    FOREIGN KEY (`julio`)
    REFERENCES `siomari_db`.`decada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_climatologia_year_decada8`
    FOREIGN KEY (`agosto`)
    REFERENCES `siomari_db`.`decada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_climatologia_year_decada9`
    FOREIGN KEY (`septiembre`)
    REFERENCES `siomari_db`.`decada` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`config`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`config` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `lamina` DOUBLE NOT NULL,
  `eficiencia` DOUBLE NOT NULL,
  `caudal` DOUBLE NOT NULL,
  `horas` INT(11) NOT NULL,
  `costo` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`cultivo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`cultivo` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`plan_siembra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`plan_siembra` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `year` INT(11) NOT NULL,
  `mes` TINYINT(4) NOT NULL,
  `periodo` TINYINT(4) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`predio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`predio` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(200) NOT NULL,
  `nombre_propietario` VARCHAR(100) NOT NULL,
  `area_total` DOUBLE NOT NULL,
  `area_potencial_riego` DOUBLE NOT NULL,
  `area_bajo_riego` DOUBLE NOT NULL,
  `modulo_riego` DOUBLE NOT NULL,
  `numero_tomas` DOUBLE NOT NULL,
  `tipo_suelo` VARCHAR(100) NOT NULL,
  `canal_id` INT(11) NOT NULL,
  `x` DOUBLE NULL DEFAULT NULL,
  `y` DOUBLE NULL DEFAULT NULL,
  `altitud` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC),
  INDEX `fk_predio_canal1_idx` (`canal_id` ASC),
  CONSTRAINT `fk_predio_canal1`
    FOREIGN KEY (`canal_id`)
    REFERENCES `siomari_db`.`canal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`cultivo_predio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`cultivo_predio` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `predio_id` INT(11) NOT NULL,
  `cultivo_id` INT(11) NOT NULL,
  `plan_siembra_id` INT(11) NOT NULL,
  `hectareas` FLOAT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_predio_has_cultivo_cultivo1_idx` (`cultivo_id` ASC),
  INDEX `fk_predio_has_cultivo_predio1_idx` (`predio_id` ASC),
  INDEX `fk_predio_has_cultivo_plan_siembra1_idx` (`plan_siembra_id` ASC),
  CONSTRAINT `fk_predio_has_cultivo_cultivo1`
    FOREIGN KEY (`cultivo_id`)
    REFERENCES `siomari_db`.`cultivo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_predio_has_cultivo_plan_siembra1`
    FOREIGN KEY (`plan_siembra_id`)
    REFERENCES `siomari_db`.`plan_siembra` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_predio_has_cultivo_predio1`
    FOREIGN KEY (`predio_id`)
    REFERENCES `siomari_db`.`predio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 37
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`entrega`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`entrega` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `entrega` DATETIME NOT NULL,
  `suspension` DATETIME NOT NULL,
  `predio_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_entregas_predio1_idx` (`predio_id` ASC),
  CONSTRAINT `fk_entregas_predio1`
    FOREIGN KEY (`predio_id`)
    REFERENCES `siomari_db`.`predio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`estructura_control`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`estructura_control` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NOT NULL,
  `coeficiente` DOUBLE NULL DEFAULT NULL,
  `exponente` DOUBLE NULL DEFAULT NULL,
  `canal_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC),
  INDEX `fk_estructura_control_canal_idx` (`canal_id` ASC),
  CONSTRAINT `fk_estructura_control_canal`
    FOREIGN KEY (`canal_id`)
    REFERENCES `siomari_db`.`canal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`kc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`kc` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `kc` FLOAT NOT NULL,
  `cultivo_id` INT(11) NOT NULL,
  `decada` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_kc_cultivo1_idx` (`cultivo_id` ASC),
  CONSTRAINT `fk_kc_cultivo1`
    FOREIGN KEY (`cultivo_id`)
    REFERENCES `siomari_db`.`cultivo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 82
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`manejo_agua`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`manejo_agua` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `q_solicitado` DOUBLE NOT NULL,
  `q_extraido` DOUBLE NOT NULL,
  `q_servido` DOUBLE NOT NULL,
  `area` DOUBLE NOT NULL,
  `canal_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_manejo_agua_canal1_idx` (`canal_id` ASC),
  CONSTRAINT `fk_manejo_agua_canal1`
    FOREIGN KEY (`canal_id`)
    REFERENCES `siomari_db`.`canal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 47
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`unidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`unidad` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`zona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`zona` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `unidad_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_zona_unidad_idx` (`unidad_id` ASC),
  CONSTRAINT `fk_zona_unidad`
    FOREIGN KEY (`unidad_id`)
    REFERENCES `siomari_db`.`unidad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`seccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`seccion` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `zona_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_seccion_zona1_idx` (`zona_id` ASC),
  CONSTRAINT `fk_seccion_zona1`
    FOREIGN KEY (`zona_id`)
    REFERENCES `siomari_db`.`zona` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`seccion_canal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`seccion_canal` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `canal_id` INT(11) NOT NULL,
  `seccion_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_canal_has_seccion_seccion1_idx` (`seccion_id` ASC),
  INDEX `fk_canal_has_seccion_canal1_idx` (`canal_id` ASC),
  CONSTRAINT `fk_canal_has_seccion_canal1`
    FOREIGN KEY (`canal_id`)
    REFERENCES `siomari_db`.`canal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_canal_has_seccion_seccion1`
    FOREIGN KEY (`seccion_id`)
    REFERENCES `siomari_db`.`seccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `siomari_db`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siomari_db`.`usuario` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `cedula` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `apellidos` VARCHAR(100) NOT NULL,
  `direccion` VARCHAR(100) NOT NULL,
  `ciudad` VARCHAR(100) NOT NULL,
  `telefono` VARCHAR(20) NULL DEFAULT NULL,
  `celular` VARCHAR(20) NULL DEFAULT NULL,
  `correo` VARCHAR(200) NULL DEFAULT NULL,
  `predio_id` INT(11) NOT NULL,
  `propietario` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usuario_predio1_idx` (`predio_id` ASC),
  CONSTRAINT `fk_usuario_predio1`
    FOREIGN KEY (`predio_id`)
    REFERENCES `siomari_db`.`predio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
