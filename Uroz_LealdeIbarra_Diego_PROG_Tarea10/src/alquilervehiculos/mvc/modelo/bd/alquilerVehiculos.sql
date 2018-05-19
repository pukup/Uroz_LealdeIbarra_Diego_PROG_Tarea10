DROP USER 'alquilerVehiculos'@'localhost';
CREATE USER 'alquilerVehiculos'@'localhost' IDENTIFIED BY 'alquilerVehiculos';
GRANT ALL PRIVILEGES ON alquilerVehiculos.* TO 'alquilerVehiculos'@'localhost';

-- -----------------------------------------------------
-- Schema alquilerVehiculos
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `alquilerVehiculos`;
CREATE SCHEMA `alquilerVehiculos` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;
USE `alquilerVehiculos` ;

-- -----------------------------------------------------
-- Table `vehiculos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vehiculos` (
  `identificador` INT NOT NULL AUTO_INCREMENT,
  `matricula` VARCHAR(7) NOT NULL,
  `marca` VARCHAR(45) NOT NULL,
  `modelo` VARCHAR(45) NOT NULL,
  `cilindrada` INT NULL,
  `numeroPlazas` INT NULL,
  `pma` INT NOT NULL,
  `tipo` ENUM('AUTOBUS', 'DE_CARGA', 'TURISMO') NOT NULL,
  `disponible` TINYINT(1) NOT NULL,
  PRIMARY KEY (`identificador`),
  UNIQUE INDEX `identificador_UNIQUE` (`identificador` ASC),
  UNIQUE INDEX `matricula_UNIQUE` (`matricula` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clientes` (
  `identificador` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `dni` VARCHAR(9) NOT NULL,
  `calle` VARCHAR(45) NOT NULL,
  `localidad` VARCHAR(45) NOT NULL,
  `codigoPostal` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`identificador`),
  UNIQUE INDEX `dni_UNIQUE` (`dni` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alquileres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alquileres` (
  `idCliente` INT NOT NULL,
  `idVehiculo` INT NOT NULL,
  `fecha` DATETIME NOT NULL,
  `dias` INT NOT NULL,
  INDEX `fk_alquileres_clientes_idx` (`idCliente` ASC),
  INDEX `fk_alquileres_vehiculos1_idx` (`idVehiculo` ASC),
  PRIMARY KEY (`idCliente`, `idVehiculo`, `fecha`),
  CONSTRAINT `fk_alquileres_clientes`
    FOREIGN KEY (`idCliente`)
    REFERENCES `clientes` (`identificador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_alquileres_vehiculos1`
    FOREIGN KEY (`idVehiculo`)
    REFERENCES `vehiculos` (`identificador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Datos de prueba
-- -----------------------------------------------------
insert into clientes values (null, 'Bob Esponja', '11111111A', 'C/ Primera, nº 1', 'Fondo del mar', '04001');
insert into clientes values (null, 'Calamardo', '22222222B', 'C/ Segunda, nº 2', 'Fondo del mar', '04002');
insert into clientes values (null, 'Patricio', '33333333C', 'C/ Tercera, nº 3', 'Fondo del mar', '04003');

insert into vehiculos values (null, '1111BBB', 'Seat', 'Ibiza', 1500, 5, 1000, 'TURISMO', true);
insert into vehiculos values (null, '2222CCC', 'Opel', 'Corsa', 1900, 5, 1000, 'TURISMO', true);
insert into vehiculos values (null, '3333DDD', 'Mercedes', 'Benz', 2100, 5, 2000, 'DE_CARGA', true);

