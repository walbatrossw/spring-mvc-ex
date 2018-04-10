-- 회원 테이블
CREATE TABLE tbl_user (
  user_id VARCHAR(50) NOT NULL,
  user_pw VARCHAR(100) NOT NULL,
  user_name VARCHAR(100) NOT NULL,
  user_point INT NOT NULL DEFAULT 0,
  PRIMARY KEY (user_id)
);

-- 회원 세션아이디
ALTER TABLE tbl_user ADD COLUMN session_key VARCHAR(50) NOT NULL DEFAULT 'none';

-- 유효기간
ALTER TABLE tbl_user ADD COLUMN session_limit TIMESTAMP;

-- 회원 프로필 이미지 칼럼 추가
ALTER TABLE tbl_user ADD COLUMN user_img VARCHAR(100) NOT NULL DEFAULT '/upload/user/default-user.png';

-- 회원이메일 추가
ALTER TABLE tbl_user ADD COLUMN user_email VARCHAR(50) NOT NULL;

-- 회원가입일자 추가
ALTER TABLE tbl_user ADD COLUMN user_join_date TIMESTAMP NOT NULL DEFAULT NOW();

-- 회원로그인일자 추가
ALTER TABLE tbl_user ADD COLUMN user_login_date TIMESTAMP NOT NULL DEFAULT NOW();

-- 회원 서명 추가
ALTER TABLE tbl_user ADD COLUMN user_signature VARCHAR(200) NOT NULL DEFAULT '안녕하세요 ^^';