-- 회원 테이블 생성
CREATE TABLE tbl_member (
  userid VARCHAR(50) NOT NULL,
  userpw VARCHAR(50) NOT NULL,
  username VARCHAR(50) NOT NULL,
  email VARCHAR(100),
  regdate TIMESTAMP DEFAULT now(),
  updatedate TIMESTAMP DEFAULT now(),
  PRIMARY KEY (userid)
)