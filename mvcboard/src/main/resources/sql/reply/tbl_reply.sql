
-- 댓글 테이블 생성
CREATE TABLE tbl_reply (
  reply_no INT NOT NULL AUTO_INCREMENT,
  article_no INT NOT NULL DEFAULT 0,
  reply_text VARCHAR(1000) NOT NULL,
  reply_writer VARCHAR(50) NOT NULL,
  reg_date TIMESTAMP NOT NULL DEFAULT NOW(),
  update_date TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (reply_no)
);

-- 댓글 참조키 설정
ALTER TABLE tbl_reply ADD CONSTRAINT FK_ARTICLE
FOREIGN KEY (article_no) REFERENCES tbl_article (article_no);

