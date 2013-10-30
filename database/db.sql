#
#
#
#

USE nheengare;

# words
CREATE TABLE IF NOT EXISTS `words` (
  `word_id`     INT NOT NULL AUTO_INCREMENT,
  `word`        VARCHAR(120) NOT NULL,
  `language_id` INT NOT NULL,
  `inter_alpha` VARCHAR(120) NOT NULL,
  KEY `index_word_on_word` (`word`),
  KEY `index_language_id_on_word` (`language_id`),
  PRIMARY KEY (`word_id`)
) ENGINE=InnoDB;

# wordeable
CREATE TABLE IF NOT EXISTS `wordeable` (
  `word_id`             INT NOT NULL,
  `meaning_id`          INT NOT NULL,
  `meaning_language_id` INT NOT NULL,
  `weight`              INT NOT NULL,
  KEY `index_meaning_language_id` (`meaning_language_id`)
) ENGINE=InnoDB;

# usageable
CREATE TABLE IF NOT EXISTS `usageable` (
  `word_id`  INT NOT NULL,
  `usage_id` INT NOT NULL
) ENGINE=InnoDB;

# publicable
CREATE TABLE IF NOT EXISTS `publicable` (
  `publication_id` INT NOT NULL,
  `word_id`        INT NOT NULL
) ENGINE=InnoDB;

# likely
CREATE TABLE IF NOT EXISTS `likely` (
  `word_id`   INT NOT NULL,
  `likely_id` INT NOT NULL,
  `weight`    INT
) ENGINE=InnoDB;

# same_as
CREATE TABLE IF NOT EXISTS `same_as` (
  `word_id`    INT NOT NULL,
  `same_as_id` INT NOT NULL
) ENGINE=InnoDB;

# languages
CREATE TABLE IF NOT EXISTS `languages` (
  `language_id` INT NOT NULL AUTO_INCREMENT,
  `language`    VARCHAR(20) NOT NULL,
  `code`        VARCHAR(6) NOT NULL,
  KEY `index_code_on_languages`(`code`),
  PRIMARY KEY (`language_id`)
) ENGINE=InnoDB;

# usages
CREATE TABLE IF NOT EXISTS `usages` (
  `usage_id`       INT NOT NULL AUTO_INCREMENT,
  `usage`          VARCHAR(255) NOT NULL,
  `publication_id` INT NOT NULL,
  PRIMARY KEY (`usage_id`)
) ENGINE=InnoDB;

# usages usage
CREATE TABLE IF NOT EXISTS `usage_usage` (
  `usage_id`          INT NOT NULL,
  `usage_usage_id`    INT NOT NULL,
  `usage_language_id` INT NOT NULL,
  KEY `usage_language_index` (`usage_language_id`)
) ENGINE=InnoDB;

# publications
CREATE TABLE IF NOT EXISTS `publications` (
  `publication_id` INT NOT NULL AUTO_INCREMENT,
  `author`         VARCHAR(60) NOT NULL,
  `title`          VARCHAR(80) NOT NULL,
  `date`           DATETIME DEFAULT NULL,
  `isbn`           VARCHAR(15),
  `url`            VARCHAR(200),
  PRIMARY KEY(`publication_id`)
) ENGINE=InnoDB;

# classes
CREATE TABLE IF NOT EXISTS `classes` (
  `class_id`       INT NOT NULL AUTO_INCREMENT,
  `class`          VARCHAR(40) NOT NULL,
  PRIMARY KEY(`class_id`)
) ENGINE=InnoDB;

# classeable
CREATE TABLE IF NOT EXISTS `classeable` (
  `class_id` INT NOT NULL,
  `word_id`  INT NOT NULL
) ENGINE=InnoDB;

# select all words of an language
DELIMITER //
DROP PROCEDURE IF EXISTS list_words //
CREATE PROCEDURE list_words ( IN word_id INT, IN language_code VARCHAR(6), IN start_letter VARCHAR(2) )
main_list_words:BEGIN
  
  # check if it have word id
  IF NOT IFNULL(word_id, '')='' THEN
    SET @sql_statement="foo";
  END IF;

  # check if it have language code
  IF NOT IFNULL(language_code, '')='' THEN
    SET @sql_statement="bar";
  END IF;

  # check if it have start letter
  IF NOT IFNULL(start_letter, '')='' THEN
    SET @sql_statement="manner";
  END IF;

    #SET @sql_statement = CONCAT( "SELECT * FROM busLines LEFT JOIN paths ON ( busLines.lineId=paths.lineId ) ORDER BY lineCode LIMIT ", offset, ",", _limit );
    #PREPARE stmt FROM @sqlString;
    #EXECUTE stmt;
    #DEALLOCATE PREPARE stmt;
END //
DELIMITER ;

# associate word with many same significant word
DELIMITER //
DROP PROCEDURE IF EXISTS associate_same_as //
CREATE PROCEDURE associate_same_as (
  IN word_id INT NOT NULL,
  IN same_ids VARCHAR(80)
)
main_associate_same_as:BEGIN

END //
DELIMITER ;

# associate word with many likely significant word
DELIMITER //
DROP PROCEDURE IF EXISTS associate_likely //
CREATE PROCEDURE associate_likely (
  IN word_id INT NOT NULL,
  IN likely_ids VARCHAR(80)
)
main_associate_likely:BEGIN

END //
DELIMITER ;

# associate word with many usages
DELIMITER //
DROP PROCEDURE IF EXISTS associate_usages //
CREATE PROCEDURE associate_usages (
  IN word_id INT NOT NULL,
  IN usage_ids VARCHAR(80)
)
main_associate_usages:BEGIN

END //
DELIMITER ;

# associate word with many words
DELIMITER //
DROP PROCEDURE IF EXISTS associate_words //
CREATE PROCEDURE associate_words (
  IN word_id INT NOT NULL,
  IN word_ids VARCHAR(80)     # ( ,1,3), ( ,2,3)  = ($current_word_id$, $meaning_word_id$, $weight$), ...
)
main_associate_words:BEGIN

END //
DELIMITER ;

# create word with many associations
DELIMITER //
DROP PROCEDURE IF EXISTS add_word //
CREATE PROCEDURE add_word ( 
  IN word VARCHAR(120),
  IN language_code VARCHAR(6),
  IN usage_ids VARCHAR(80),     # ( ,1), ( ,3), ( ,4), ( ,10) =  
  IN word_ids VARCHAR(80),      # ( ,1,3), ( ,2,3), ( ,2,4)   = ($current_word_id$, $meaning_word_id$, $weight$), ...
  IN same_ids VARCHAR(80),      # 
)
main_add_word:BEGIN
  
  
END //
DELIMITER ;

# associate usage with many usages


# create usage 
DELIMITER //
DROP PROCEDURE IF EXISTS add_usage //
CREATE PROCEDURE add_usage (

# 


#
# validate isbn -> JavaScript
#function isValidISBN13(ISBNumber) {
#  var check, i;
#  ISBNumber = ISBNumber.replace(/-\s/g, '');
#  check = 0;
#  for (i=0; i<13; i+=2) {check += +ISBNumber[i];}
#  for (i=1; i<12; i+=2) {check += 3 * +ISBNumber[i];}
#  return check % 10 === 0;
#}