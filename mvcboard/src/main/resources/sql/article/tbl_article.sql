-- 게시글 테이블
CREATE TABLE tbl_article (
  article_no INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  content TEXT NULL,
  writer VARCHAR(50) NOT NULL,
  regdate TIMESTAMP NOT NULL DEFAULT NOW(),
  viewcnt INT DEFAULT 0,
  PRIMARY KEY (article_no)
)