-- 북마크 테이블
CREATE TABLE tbl_bookmark (
  bmno    INT         NOT NULL AUTO_INCREMENT,
  bno     INT         NOT NULL,
  uid     VARCHAR(50) NOT NULL,
  regdate TIMESTAMP   NOT NULL DEFAULT NOW(),
  PRIMARY KEY (bmno)
);
