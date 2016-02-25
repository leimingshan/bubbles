# clean data and insert test data
DELETE FROM `report` WHERE `id` BETWEEN 1 AND 5;
DELETE FROM `feedback` WHERE `id` BETWEEN 1 AND 5;
DELETE FROM `bubble` WHERE `id`='10001';
DELETE FROM `bubble` WHERE `id`='10000';
DELETE FROM `user` WHERE `id`='10000' OR `id`='10001';

INSERT INTO `user` VALUES ('10000', '000000000010000', '12345678912345678912', '10887322133', 'rayman', 'f', 'http://112.124.56.38/avatar/10000.jpg', '56', NOW(), NOW());
INSERT INTO `user` VALUES ('10001', '000000000010001', '00000000000000012345','18734920714', 'liu', 'm', 'http://112.124.56.38/avatar/10001.jpg', '60', NOW(), NOW());

INSERT INTO `bubble` VALUES ('10000', null, null, '10000', 'hellohello', '0', '1', '67', '2015-05-21 16:57:40', '2015-05-21 16:57:42', '50.11', '40.33', '1000');
INSERT INTO `bubble` VALUES ('10001', '10000', '10000', '10000', 'hello world', '0', '0', '0', '2015-05-24 15:16:45', '2015-05-24 15:16:48', '28.9', '38.9999999998', '1000');

INSERT INTO `report` (`id`, `bubble_id`, `category`, `content`, `created_date`, `modified_date`) VALUES ('1', '10000', '1', '内容不符合', NOW(), NOW());
INSERT INTO `report` (`id`, `bubble_id`, `category`, `content`, `created_date`, `modified_date`) VALUES ('2', '10000', '2', '内容质量低', NOW(), NOW());
INSERT INTO `report` (`id`, `bubble_id`, `category`, `content`, `created_date`, `modified_date`) VALUES ('3', '10001', '3', '内容违反规定', NOW(), NOW());
INSERT INTO `report` (`id`, `bubble_id`, `category`, `content`, `created_date`, `modified_date`) VALUES ('4', '10001', '1', '内容不符合', NOW(), NOW());
INSERT INTO `report` (`id`, `bubble_id`, `category`, `content`, `created_date`, `modified_date`) VALUES ('5', '10000', '4', '内容不符合规定', NOW(), NOW());

INSERT INTO `feedback` (`id`, `content`, `email`, `cellphone`, `created_date`, `modified_date`) VALUES ('1', '反馈和建议', 'abc@abc.com', '12345678912', NOW(), NOW());
INSERT INTO `feedback` (`id`, `content`, `email`, `cellphone`, `created_date`, `modified_date`) VALUES ('2', '反馈和建议', 'abc@abc.com', '12345678912', NOW(), NOW());
INSERT INTO `feedback` (`id`, `content`, `email`, `cellphone`, `created_date`, `modified_date`) VALUES ('3', '反馈和建议', 'abc@abc.com', '12345678912', NOW(), NOW());
INSERT INTO `feedback` (`id`, `content`, `email`, `cellphone`, `created_date`, `modified_date`) VALUES ('4', '反馈和建议', 'abc@abc.com', '12345678912', NOW(), NOW());
INSERT INTO `feedback` (`id`, `content`, `email`, `cellphone`, `created_date`, `modified_date`) VALUES ('5', '反馈和建议', 'abc@abc.com', '12345678912', NOW(), NOW());
