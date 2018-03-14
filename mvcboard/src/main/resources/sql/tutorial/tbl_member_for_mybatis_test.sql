-- MyBatis 테스트 예제 SQL

-- 회원테이블
CREATE TABLE tbl_member (
  userid     VARCHAR(50) NOT NULL, -- 회원아이디
  userpw     VARCHAR(50) NOT NULL, -- 회원비밀번호
  username   VARCHAR(50) NOT NULL, -- 회원이름
  email      VARCHAR(100), -- 회원이메일
  regdate    TIMESTAMP DEFAULT now(), -- 가입일자
  updatedate TIMESTAMP DEFAULT now(), -- 수정일자
  PRIMARY KEY (userid)  -- 기본키 : userid
)