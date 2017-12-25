-- 추천테이블
CREATE TABLE tbl_likes (
  blno     INT       NOT NULL AUTO_INCREMENT,
  bno      INT       NOT NULL,
  uid      INT       NOT NULL,
  likedate TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (blno)
);