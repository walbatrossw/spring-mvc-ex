-- 회원 테이블
CREATE TABLE tbl_user (
  user_id VARCHAR(50) NOT NULL,
  user_pw VARCHAR(50) NOT NULL,
  user_name VARCHAR(100) NOT NULL,
  user_point INT NOT NULL DEFAULT 0,
  PRIMARY KEY (user_id)
);

-- 회원 세션아이디
ALTER TABLE tbl_user ADD COLUMN session_key VARCHAR(50) NOT NULL DEFAULT 'none';

-- 유효기간
ALTER TABLE tbl_user ADD COLUMN session_limit TIMESTAMP;