-- 첨부파일 테이블
CREATE TABLE tbl_attach (
  fullname VARCHAR(150) NOT NULL,
  bno INT NOT NULL,
  regdate TIMESTAMP DEFAULT NOW(),
  PRIMARY KEY (fullname)
);

-- 외래키
ALTER TABLE tbl_attach ADD CONSTRAINT fk_board_attach
FOREIGN KEY (bno) REFERENCES tbl_board (bno);