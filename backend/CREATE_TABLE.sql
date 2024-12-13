use ricefriend;

CREATE TABLE `menucategory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `foodcategory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `menuid` int NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`menuid`) REFERENCES `menucategory` (`id`)
);

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  `profileimg` longblob,
  `favfood_id1` int NOT NULL,
  `favfood_id2` int DEFAULT NULL,
  `favfood_id3` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`favfood_id1`) REFERENCES `foodcategory` (`id`),
  FOREIGN KEY (`favfood_id2`) REFERENCES `foodcategory` (`id`),
  FOREIGN KEY (`favfood_id3`) REFERENCES `foodcategory` (`id`)
);

CREATE TABLE `chatroom` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `limitednum` int NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `chatpart` (
  `userid` int NOT NULL,
  `roomid` int NOT NULL,
  PRIMARY KEY (`userid`,`roomid`),
  FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  FOREIGN KEY (`roomid`) REFERENCES `chatroom` (`id`)
);

CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int NOT NULL,
  `roomid` int NOT NULL,
  `type` enum('CHAT','JOIN','LEAVE') DEFAULT NULL,
  `detail` text NOT NULL,
  `senttime` timestamp NOT NULL,
  PRIMARY KEY (`id`,`roomid`),
  FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  FOREIGN KEY (`roomid`) REFERENCES `chatroom` (`id`)
);

INSERT INTO menucategory(name) VALUES("분식");
INSERT INTO menucategory(name) VALUES("한식");
INSERT INTO menucategory(name) VALUES("양식");
INSERT INTO menucategory(name) VALUES("중식");
INSERT INTO menucategory(name) VALUES("일식");
INSERT INTO menucategory(name) VALUES("야식");
INSERT INTO menucategory(name) VALUES("카페·디저트");

INSERT INTO foodcategory(menuid, name) VALUES(1, "떡볶이");
INSERT INTO foodcategory(menuid, name) VALUES(1, "순대");
INSERT INTO foodcategory(menuid, name) VALUES(1, "튀김");
INSERT INTO foodcategory(menuid, name) VALUES(1, "김밥");
INSERT INTO foodcategory(menuid, name) VALUES(1, "라면");
INSERT INTO foodcategory(menuid, name) VALUES(1, "어묵");
INSERT INTO foodcategory(menuid, name) VALUES(2, "비빔밥");
INSERT INTO foodcategory(menuid, name) VALUES(2, "국밥");
INSERT INTO foodcategory(menuid, name) VALUES(2, "제육볶음");
INSERT INTO foodcategory(menuid, name) VALUES(2, "삼겹살");
INSERT INTO foodcategory(menuid, name) VALUES(2, "찜닭");
INSERT INTO foodcategory(menuid, name) VALUES(2, "김치찌개");
INSERT INTO foodcategory(menuid, name) VALUES(3, "파스타");
INSERT INTO foodcategory(menuid, name) VALUES(3, "피자");
INSERT INTO foodcategory(menuid, name) VALUES(3, "스테이크");
INSERT INTO foodcategory(menuid, name) VALUES(3, "햄버거");
INSERT INTO foodcategory(menuid, name) VALUES(3, "핫도그");
INSERT INTO foodcategory(menuid, name) VALUES(4, "짜장면");
INSERT INTO foodcategory(menuid, name) VALUES(4, "볶음밥");
INSERT INTO foodcategory(menuid, name) VALUES(4, "마라탕");
INSERT INTO foodcategory(menuid, name) VALUES(4, "군만두");
INSERT INTO foodcategory(menuid, name) VALUES(5, "초밥");
INSERT INTO foodcategory(menuid, name) VALUES(5, "사시미");
INSERT INTO foodcategory(menuid, name) VALUES(5, "돈까스");
INSERT INTO foodcategory(menuid, name) VALUES(5, "라멘");
INSERT INTO foodcategory(menuid, name) VALUES(5, "우동");
INSERT INTO foodcategory(menuid, name) VALUES(5, "카레");
INSERT INTO foodcategory(menuid, name) VALUES(5, "소바");
INSERT INTO foodcategory(menuid, name) VALUES(6, "족발");
INSERT INTO foodcategory(menuid, name) VALUES(6, "보쌈");
INSERT INTO foodcategory(menuid, name) VALUES(6, "곱창");
INSERT INTO foodcategory(menuid, name) VALUES(6, "닭발");
INSERT INTO foodcategory(menuid, name) VALUES(6, "파전");
INSERT INTO foodcategory(menuid, name) VALUES(7, "커피");
INSERT INTO foodcategory(menuid, name) VALUES(7, "케이크");
INSERT INTO foodcategory(menuid, name) VALUES(7, "와플");
INSERT INTO foodcategory(menuid, name) VALUES(7, "베이글");
INSERT INTO foodcategory(menuid, name) VALUES(7, "샐러드");

INSERT INTO user (email, password, name, favfood_id1)
VALUES ("admin@boss.com", "123", "정준혁", 1);

INSERT INTO chatroom(name, state, limitednum) VALUES("떡볶이 먹을 사람~~", "ACT", 4);
INSERT INTO chatpart(userid, roomid) VALUES(1, 1);