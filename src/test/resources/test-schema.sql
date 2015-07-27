CREATE TABLE
IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_id` varchar(64) NOT NULL,
  `cellphone` char(11) DEFAULT NULL,
  `nickname` varchar(32) NOT NULL,
  `gender` char(1) NOT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `get_up_num` int(11) DEFAULT NULL,
  `give_up_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `device_id_idx` (`device_id`)
);

CREATE TABLE
IF NOT EXISTS `bubble` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_bubble_id` bigint(20) DEFAULT NULL,
  `author_id` bigint(20) NOT NULL,
  `content` varchar(255) NOT NULL,
  `reply_num` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `last_reply_time` datetime DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `distance` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_parent_bubble` (`parent_bubble_id`),
  KEY `fk_parent` (`parent_id`),
  KEY `fk_user` (`author_id`),
  CONSTRAINT `fk_user` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_parent` FOREIGN KEY (`parent_id`) REFERENCES `bubble` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_parent_bubble` FOREIGN KEY (`parent_bubble_id`) REFERENCES `bubble` (`id`) ON UPDATE CASCADE
);
