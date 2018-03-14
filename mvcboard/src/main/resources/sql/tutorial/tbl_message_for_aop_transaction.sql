
-- AOP, Transaction을 위한 SQL

-- 메시지 테이블
CREATE TABLE tbl_message (
  message_no INT NOT NULL AUTO_INCREMENT,
  receiver VARCHAR(50) NOT NULL,
  sender VARCHAR(50) NOT NULL,
  message TEXT NOT NULL,
  open_date TIMESTAMP,
  send_date TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (message_no)
);

-- 회원 테이블
CREATE TABLE tbl_user (
  user_id VARCHAR(50) NOT NULL,
  user_pw VARCHAR(50) NOT NULL,
  user_name VARCHAR(100) NOT NULL,
  user_point INT NOT NULL DEFAULT 0,
  PRIMARY KEY (user_id)
);

-- 외래키 설정 : 회원테이블_수신자
alter table tbl_message add constraint fk_user_receiver
foreign key (receiver) references tbl_user (user_id);

-- 외래키 설정 : 회원테이블_발송자
alter table tbl_message add constraint fk_user_sender
foreign key (sender) references tbl_user (user_id);

-- 회원 추가
INSERT INTO tbl_user (user_id, user_pw, user_name) VALUE ('user01', '1234', 'user01');
INSERT INTO tbl_user (user_id, user_pw, user_name) VALUE ('user02', '1234', 'user02');
INSERT INTO tbl_user (user_id, user_pw, user_name) VALUE ('user03', '1234', 'user03');
INSERT INTO tbl_user (user_id, user_pw, user_name) VALUE ('user04', '1234', 'user04');
INSERT INTO tbl_user (user_id, user_pw, user_name) VALUE ('user05', '1234', 'user05');
INSERT INTO tbl_user (user_id, user_pw, user_name) VALUE ('user06', '1234', 'user06');