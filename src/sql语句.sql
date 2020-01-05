#删除库
drop database myblog;

#建库
create database myblog;

#创建article表
use myblog;
CREATE TABLE `article` (
                         `article_id` INT(11) NOT NULL AUTO_INCREMENT,
                         `title` VARCHAR(80) NOT NULL,
                         `author` VARCHAR(30) NOT NULL,
                         `sort` VARCHAR(30) NOT NULL,
                         `time` DATETIME DEFAULT '2020-01-2 00:00:00',
                         `star`  INT(11) DEFAULT '0',
                         `comment` INT(11) DEFAULT '0',
                         `visit` INT(11) DEFAULT '0',
                         `content` TEXT,
                         PRIMARY KEY (`article_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `article_delet` (
                               `id` INT(11) NOT NULL AUTO_INCREMENT,
                               `title` VARCHAR(80) NOT NULL,
                               `author` VARCHAR(30) NOT NULL,
                               `sort` VARCHAR(30) NOT NULL,
                               `time` DATETIME DEFAULT '2020-01-2 00:00:00',
                               `star` INT(11) DEFAULT '0',
                               `comment` VARCHAR(300) DEFAULT '0',
                               `visit` INT(11) DEFAULT '0',
                               `content` TEXT,
                               PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `comment` (
                         `c_id` INT(11) NOT NULL AUTO_INCREMENT,
                         `article_id` INT(11) DEFAULT NULL,
                         `nickname` VARCHAR(30) DEFAULT NULL,
                         `content` TEXT,
                         `time` DATETIME DEFAULT '2020-01-2 00:00:00',
                         `star` INT(11) DEFAULT '0',
                         `diss` INT(11) DEFAULT '0',
                         PRIMARY KEY (`c_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `tag` (
                     `t_id` INT(11) NOT NULL AUTO_INCREMENT,
                     `article_id` INT(11),
                     `tag` VARCHAR(30) DEFAULT NULL,
                     PRIMARY KEY (`t_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user` (
                      `user_id` INT(11) NOT NULL AUTO_INCREMENT,
                      `user_name` VARCHAR(20) NOT NULL,
                      `user_password` VARCHAR(20) NOT NULL,
                      PRIMARY KEY (`user_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `visitor` (
                         `v_id` INT(11) NOT NULL AUTO_INCREMENT,
                         `ip` VARCHAR(50) DEFAULT NULL,
                         `time` VARCHAR(50) DEFAULT NULL,
                         `web_ip` VARCHAR(50) DEFAULT NULL,
                         `host` VARCHAR(50) DEFAULT NULL,
                         PRIMARY KEY (`v_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
