USE `forum` ;

-- Table `forum`.`users`
CREATE TABLE IF NOT EXISTS `forum`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `mail` VARCHAR(45) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `enabled` boolean NOT NULL default true,
  `role` varchar(16) NOT NULL default 'USER',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- Table `forum`.`themes`
CREATE TABLE IF NOT EXISTS `forum`.`themes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  `text` VARCHAR(255) NOT NULL,
  `ldt` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `qty` INT NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_themes_users_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_themes_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `forum`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- Table `forum`.`comments`
CREATE TABLE IF NOT EXISTS `forum`.`comments` (
  `id` INT NOT NULL,
  `text` VARCHAR(255) NOT NULL,
  `ldt` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `users_id` INT NOT NULL,
  `themes_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comments_users1_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_comments_themes1_idx` (`themes_id` ASC) VISIBLE,
  CONSTRAINT `fk_comments_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `forum`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_themes1`
    FOREIGN KEY (`themes_id`)
    REFERENCES `forum`.`themes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;