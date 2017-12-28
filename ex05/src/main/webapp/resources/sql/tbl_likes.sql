-- 추천테이블
CREATE TABLE tbl_likes (
  blno     INT         NOT NULL AUTO_INCREMENT,
  bno      INT         NOT NULL,
  uid      VARCHAR(50) NOT NULL,
  likedate TIMESTAMP   NOT NULL DEFAULT NOW(),
  PRIMARY KEY (blno)
);