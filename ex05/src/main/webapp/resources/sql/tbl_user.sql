
-- 회원 테이블
CREATE TABLE tbl_user (
  uid VARCHAR(50) NOT NULL,
  upw VARCHAR(50) NOT NULL,
  uname VARCHAR(100) NOT NULL,
  upoint INT NOT NULL DEFAULT 0,
  PRIMARY KEY (uid)
);

-- 세션 아이디
ALTER TABLE tbl_user ADD COLUMN sessionkey VARCHAR(50) NOT NULL DEFAULT 'none';

-- 세션 유효시간 : 유효한 기간에 다시 접속을 했는지 판단
ALTER TABLE tbl_user ADD COLUMN sessionlimit TIMESTAMP;

-- email (이메일) 추가
ALTER TABLE tbl_user ADD COLUMN uemail VARCHAR(50) NOT NULL;

-- regdate  (가입일자) 추가
ALTER TABLE tbl_user ADD COLUMN regdate TIMESTAMP NOT NULL DEFAULT NOW();

-- logdate (로그인일자) 추가
ALTER TABLE tbl_user ADD COLUMN logdate TIMESTAMP NOT NULL DEFAULT NOW();

