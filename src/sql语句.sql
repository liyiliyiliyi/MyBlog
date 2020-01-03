CREATE database myblog
use myblog;
CREATE TABLE `article` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(80) NOT NULL,
  `author` VARCHAR(30) NOT NULL,
  `sort` VARCHAR(30) NOT NULL,
  `time` DATETIME DEFAULT '2020-01-2 00:00:00',
  `star` INT(11) DEFAULT '0',
  `comment` INT(11) DEFAULT '0',
  `visit` INT(11) DEFAULT '0',
  `content` TEXT,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `article_delet` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(80) NOT NULL,
  `author` VARCHAR(30) NOT NULL,
  `sort` VARCHAR(30) NOT NULL,
  `time` DATETIME DEFAULT '2020-01-2 00:00:00',
  `star` INT(11) DEFAULT '0',
  `comment` INT(11) DEFAULT '0',
  `visit` INT(11) DEFAULT '0',
  `content` TEXT,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `comment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `article_id` INT(11) DEFAULT NULL,
  `nickname` VARCHAR(30) DEFAULT NULL,
  `content` TEXT,
  `time` DATETIME DEFAULT '2020-01-2 00:00:00',
  `star` INT(11) DEFAULT '0',
  `diss` INT(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `article_id` (`article_id`),
  CONSTRAINT `article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `tag` (
  `id` INT(11) DEFAULT NULL,
  `tag` VARCHAR(30) DEFAULT NULL,
  KEY `id` (`id`),
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

create database myblog;

CREATE TABLE `user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `visitor` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `ip` VARCHAR(50) DEFAULT NULL,
  `time` VARCHAR(50) DEFAULT NULL,
  `web_ip` VARCHAR(50) DEFAULT NULL,
  `host` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

insert into user (user_name, user_password) values ('Lemon', '23456');
insert into user (user_name, user_password) values ('Purple', '88896');
insert into user (user_name, user_password) values ('Pear', '55564');
