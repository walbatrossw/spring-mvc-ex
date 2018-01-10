-- 북마크 테이블
CREATE TABLE tbl_bookmark (
  bmno    INT         NOT NULL AUTO_INCREMENT,
  bno     INT         NOT NULL,
  uid     VARCHAR(50) NOT NULL,
  regdate TIMESTAMP   NOT NULL DEFAULT NOW(),
  PRIMARY KEY (bmno)
);

-- 외래키 설정
ALTER TABLE tbl_bookmark ADD CONSTRAINT fk_board_bookmark
FOREIGN KEY (bno) REFERENCES tbl_board (bno);