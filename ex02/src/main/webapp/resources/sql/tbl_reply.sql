
-- 댓글 테이블 생성 및 참조키 설정

CREATE TABLE tbl_reply (
  rno INT NOT NULL AUTO_INCREMENT,
  bno INT NOT NULL DEFAULT 0,
  replytext VARCHAR(1000) NOT NULL,
  replyer VARCHAR(50) NOT NULL,
  regdate TIMESTAMP NOT NULL DEFAULT NOW(),
  updatedate TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (rno)
);

ALTER TABLE tbl_reply ADD CONSTRAINT fk_board
FOREIGN KEY (bno) REFERENCES tbl_board (bno);