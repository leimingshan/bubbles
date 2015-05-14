CREATE TABLE
IF NOT EXISTS user (
	id BIGINT NOT NULL,
	device_id VARCHAR (64) NOT NULL,
	cellphone CHAR (11),
	nickname VARCHAR (32) NOT NULL,
	gender CHAR (1) NOT NULL,
	avatar LONGBLOB NULL,
	score INT NULL,
	pub_num INT NULL,
	give_reply_num INT NULL,
	get_reply_num INT NULL,
	get_up_num INT NULL,
	give_up_num INT NULL,
  PRIMARY KEY (id)
);

DELETE FROM `bubbles`.`user` WHERE `id`='10000' OR `id`='10001';

INSERT INTO `bubbles`.`user` (
	`id`,
	`device_id`,
	`cellphone`,
	`nickname`,
	`gender`,
	`avatar`,
	`score`,
	`pub_num`,
	`give_reply_num`,
	`get_reply_num`,
	`get_up_num`,
	`give_up_num`
)
VALUES
	(
		'10000',
		'000000000000010',
		'12266552212',
		'lilili',
		'm',
		'145241',
		'58',
		'22',
		'35',
		'123',
		'33',
		'77'
	);

INSERT INTO `bubbles`.`user` (
	`id`,
	`device_id`,
	`cellphone`,
	`nickname`,
	`gender`,
	`avatar`,
	`score`,
	`pub_num`,
	`give_reply_num`,
	`get_reply_num`,
	`get_up_num`,
	`give_up_num`
)
VALUES
	(
		'10001',
		'000000000000011',
		'12266552212',
		'rayman',
		'm',
		'14211',
		'321',
		'220',
		'55',
		'823',
		'43',
		'67'
	);

