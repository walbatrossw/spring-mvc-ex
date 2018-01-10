-- 추천테이블
CREATE TABLE tbl_likes (
  blno     INT         NOT NULL AUTO_INCREMENT,
  bno      INT         NOT NULL,
  uid      VARCHAR(50) NOT NULL,
  likedate TIMESTAMP   NOT NULL DEFAULT NOW(),
  PRIMARY KEY (blno)
);

-- 외래키 설정
ALTER TABLE tbl_likes ADD CONSTRAINT fk_board_likes
FOREIGN KEY (bno) REFERENCES tbl_board (bno);