DELETE FROM `user` WHERE `id`='10000' OR `id`='10001';

INSERT INTO `user` VALUES ('10000', '000000000010000', '10887322133', 'rayman', 'f', 'http://112.124.56.38/avatar/10000.jpg', '56', '10', '12', '22', '31', '65');
INSERT INTO `user` VALUES ('10001', '000000000010001', '18734920714', 'liu', 'm', 'http://112.124.56.38/avatar/10001.jpg', '60', '13', '15', '28', '18', '77');


DELETE FROM `bubble` WHERE `id`='2';
DELETE FROM `bubble` WHERE `id`='1';

INSERT INTO `bubble` VALUES ('1', null, null, '1', 'hellohello', '1', '67', '2015-05-21 16:57:40', '2015-05-21 16:57:42', '50.11', '40.33', '1000');
INSERT INTO `bubble` VALUES ('2', '1', '1', '1', 'hello world', '0', '0', '2015-05-24 15:16:45', '2015-05-24 15:16:48', '28.9', '38.9999999998', '1000');
