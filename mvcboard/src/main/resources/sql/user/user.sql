-- 회원 테이블
CREATE TABLE tbl_user (
  user_id VARCHAR(50) NOT NULL,
  user_pw VARCHAR(100) NOT NULL,
  user_name VARCHAR(100) NOT NULL,
  user_point INT NOT NULL DEFAULT 0,
  session_key VARCHAR(50) NOT NULL DEFAULT 'none',
  session_limit TIMESTAMP,
  user_img VARCHAR(100) NOT NULL DEFAULT 'user/default-user.png',
  user_email VARCHAR(50) NOT NULL,
  user_join_date TIMESTAMP NOT NULL DEFAULT NOW(),
  user_login_date TIMESTAMP NOT NULL DEFAULT NOW(),
  user_signature VARCHAR(200) NOT NULL DEFAULT '안녕하세요 ^^',
  PRIMARY KEY (user_id)
);

-- 회원 세션아이디
ALTER TABLE tbl_user ADD COLUMN session_key VARCHAR(50) NOT NULL DEFAULT 'none';

-- 유효기간
ALTER TABLE tbl_user ADD COLUMN session_limit TIMESTAMP;

-- 회원 프로필 이미지 칼럼 추가
ALTER TABLE tbl_user ADD COLUMN user_img VARCHAR(100) NOT NULL DEFAULT 'user/default-user.png';

-- 회원이메일 추가
ALTER TABLE tbl_user ADD COLUMN user_email VARCHAR(50) NOT NULL;

-- 회원가입일자 추가
ALTER TABLE tbl_user ADD COLUMN user_join_date TIMESTAMP NOT NULL DEFAULT NOW();

-- 회원로그인일자 추가
ALTER TABLE tbl_user ADD COLUMN user_login_date TIMESTAMP NOT NULL DEFAULT NOW();

-- 회원 서명 추가
ALTER TABLE tbl_user ADD COLUMN user_signature VARCHAR(200) NOT NULL DEFAULT '안녕하세요 ^^';

-- 회원 추가
INSERT INTO tbl_user (user_id, user_pw, user_name, user_email) VALUES ('user00', '1234', 'user00', 'user00@mail.com');
INSERT INTO tbl_user (user_id, user_pw, user_name, user_email) VALUES ('user01', '1234', 'user01', 'user01@mail.com');
INSERT INTO tbl_user (user_id, user_pw, user_name, user_email) VALUES ('user02', '1234', 'user02', 'user02@mail.com');
INSERT INTO tbl_user (user_id, user_pw, user_name, user_email) VALUES ('user03', '1234', 'user03', 'user03@mail.com');
INSERT INTO tbl_user (user_id, user_pw, user_name, user_email) VALUES ('user04', '1234', 'user04', 'user04@mail.com');
INSERT INTO tbl_user (user_id, user_pw, user_name, user_email) VALUES ('user05', '1234', 'user05', 'user05@mail.com');
INSERT INTO tbl_user (user_id, user_pw, user_name, user_email) VALUES ('user06', '1234', 'user06', 'user06@mail.com');
INSERT INTO tbl_user (user_id, user_pw, user_name, user_email) VALUES ('user07', '1234', 'user07', 'user07@mail.com');
INSERT INTO tbl_user (user_id, user_pw, user_name, user_email) VALUES ('user08', '1234', 'user08', 'user08@mail.com');
INSERT INTO tbl_user (user_id, user_pw, user_name, user_email) VALUES ('user09', '1234', 'user09', 'user09@mail.com');