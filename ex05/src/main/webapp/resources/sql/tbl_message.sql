
-- 메시지 테이블
CREATE TABLE tbl_message (
  mno INT NOT NULL AUTO_INCREMENT,
  targetid VARCHAR(50) NOT NULL,
  sender VARCHAR(50) NOT NULL,
  message TEXT NOT NULL,
  opendate TIMESTAMP,
  senddate TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (mno)
);

-- 회원 테이블
CREATE TABLE tbl_user (
  uid VARCHAR(50) NOT NULL,
  upw VARCHAR(50) NOT NULL,
  uname VARCHAR(100) NOT NULL,
  upoint INT NOT NULL DEFAULT 0,
  PRIMARY KEY (uid)
);

-- 외래키 설정
alter table tbl_message add constraint fk_usertarget
foreign key (targetid) references tbl_user (uid);

-- 외래키 설정
alter table tbl_message add constraint fk_usersender
foreign key (sender) references tbl_user (uid);
