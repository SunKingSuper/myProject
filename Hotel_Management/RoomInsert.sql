INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (101, '豪华大床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (102, '豪华双床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (103, '豪华大床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (104, '豪华双床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (105, '豪华大床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (106, '豪华双床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (107, '豪华大床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (108, '豪华双床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (201, '豪华双床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (202, '豪华大床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (203, '豪华双床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (204, '豪华大床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (205, '豪华双床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (206, '豪华大床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (207, '豪华双床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (208, '豪华大床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (301, '豪华大床套房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (302, '超豪华大床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (303, '豪华大床套房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (304, '超豪华大床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (305, '豪华大床套房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (306, '超豪华大床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (307, '豪华大床套房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (308, '超豪华大床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (401, '超豪华双床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (402, '超豪华套房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (403, '超豪华大床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (404, '超豪华套房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (405, '超豪华大床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (406, '超豪华套房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (407, '超豪华大床房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (408, '超豪华套房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (504, '行政房', 1001);
INSERT INTO `KingSun_Hotel`.`room` (`idRoom`, `roomType`, `status`) VALUES (505, '行政房', 1001);


INSERT INTO `KingSun_Hotel`.`User` (`idUser`, `password`, `role`) VALUES (1, '1', 'server');
INSERT INTO `KingSun_Hotel`.`User` (`idUser`, `password`, `role`) VALUES (2, '2', 'server');
INSERT INTO `KingSun_Hotel`.`User` (`idUser`, `password`, `role`) VALUES (3, '3', 'cleaner');
SELECT * FROM KingSun_Hotel.User;

INSERT INTO `KingSun_Hotel`.`roleSale` (`guestRole`, `dismiss`) VALUES ('普通客人', 1);
INSERT INTO `KingSun_Hotel`.`roleSale` (`guestRole`, `dismiss`) VALUES ('普通会员', 0.9);
INSERT INTO `KingSun_Hotel`.`roleSale` (`guestRole`, `dismiss`) VALUES ('白金会员', 0.7);

INSERT INTO `KingSun_Hotel`.`Guest` (`idGuest`, `name`, `idCard`, `phoneNumber`, `guestRole`) VALUES ('1', '小明', '44180019970666abcd', '12357790923', '白金会员');
INSERT INTO `KingSun_Hotel`.`Guest` (`idGuest`, `name`, `idCard`, `phoneNumber`, `guestRole`) VALUES ('2', '小王', '441600199203668ecf', '18326487262', '普通会员');
INSERT INTO `KingSun_Hotel`.`Guest` (`idGuest`, `name`, `idCard`, `phoneNumber`, `guestRole`) VALUES ('3', '小红', '23280019570122dth4', '18328746832', '普通客人');
INSERT INTO `KingSun_Hotel`.`Guest` (`idGuest`, `name`, `idCard`, `phoneNumber`, `guestRole`) VALUES ('4', '小李', '11220019971223fgr3', '18734982933', '普通客人');
INSERT INTO `KingSun_Hotel`.`Guest` (`idGuest`, `name`, `idCard`, `phoneNumber`, `guestRole`) VALUES ('5', '小莫', '12180019920516rert', '18597987332', '普通客人');
INSERT INTO `KingSun_Hotel`.`Guest` (`idGuest`, `name`, `idCard`, `phoneNumber`, `guestRole`) VALUES ('6', '小许', '43120019950162ae3d', '19382749820', '普通客人');
INSERT INTO `KingSun_Hotel`.`Guest` (`idGuest`, `name`, `idCard`, `phoneNumber`, `guestRole`) VALUES ('7', '小刘', '63150019670664f13d', '13874982794', '普通客人');
INSERT INTO `KingSun_Hotel`.`Guest` (`name`, `idCard`, `phoneNumber`, `guestRole`) VALUES ('小小', '24540019650213ds32', '17193579373', '普通客人');
INSERT INTO `KingSun_Hotel`.`Guest` (`name`, `idCard`, `phoneNumber`, `guestRole`) VALUES ('小叶', '347700197802113483', '13487329593', '普通客人');
INSERT INTO `KingSun_Hotel`.`Guest` (`name`, `idCard`, `phoneNumber`, `guestRole`) VALUES ('小曾', '2345001997111123l2', '19823240923', '普通客人');
SELECT * FROM KingSun_Hotel.Guest;


