DROP TABLE `idms_db`.`users`;
CREATE TABLE `idms_db`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  `institutionid` VARCHAR(45) NULL,
  PRIMARY KEY (`username`))
COMMENT = 'Users Information';

INSERT INTO `idms_db`.`users` (`username`, `password`, `institutionid`)
VALUES ('testerAPI@drivesoft.tech', 'GiclVIaDST3st@@Main', '107007');


CREATE TABLE `idms_db`.`accounts` (
  `AccountNumber` INT NOT NULL,
   `AccountStatus` VARCHAR(1) NULL,
  `FirstName` VARCHAR(45) NULL,
  `LastName` VARCHAR(45) NULL,
  PRIMARY KEY (`AccountNumber`))
COMMENT = 'Accounts Information';


INSERT INTO `idms_db`.`ACCOUNTS` (`accountnumber`, `firstname`, `lastname`) VALUES ('81987', 'TestFirst', 'TestLast');
INSERT INTO `idms_db`.`ACCOUNTS` (`accountnumber`, `firstname`, `lastname`) VALUES ('81988', 'TestSecond', 'TestSecond');
