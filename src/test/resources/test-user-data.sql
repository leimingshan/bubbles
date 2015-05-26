CREATE TABLE
IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_id` varchar(64) NOT NULL,
  `cellphone` char(11) DEFAULT NULL,
  `nickname` varchar(32) NOT NULL,
  `gender` char(1) NOT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `pub_num` int(11) DEFAULT NULL,
  `give_reply_num` int(11) DEFAULT NULL,
  `get_reply_num` int(11) DEFAULT NULL,
  `get_up_num` int(11) DEFAULT NULL,
  `give_up_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `device_id_idx` (`device_id`)
);

DELETE FROM `user` WHERE `id`='10000' OR `id`='10001';

INSERT INTO `user` VALUES ('10000', '000000000010000', '10887322133', 'rayman', 'f', 'http://112.124.56.38/avatar/10000.jpg', '56', '10', '12', '22', '31', '65');
INSERT INTO `user` VALUES ('10001', '000000000010001', '18734920714', 'liu', 'm', 'http://112.124.56.38/avatar/10001.jpg', '60', '13', '15', '28', '18', '77');

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

DELETE FROM `bubble` WHERE `id`='2';
DELETE FROM `bubble` WHERE `id`='1';

INSERT INTO `bubble` VALUES ('1', null, null, '1', 'hellohello', '1', '67', '2015-05-21 16:57:40', '2015-05-21 16:57:42', '50.11', '40.33', '1000');
INSERT INTO `bubble` VALUES ('2', '1', '1', '1', 'hello world', '0', '0', '2015-05-24 15:16:45', '2015-05-24 15:16:48', '28.9', '38.9999999998', '1000');
