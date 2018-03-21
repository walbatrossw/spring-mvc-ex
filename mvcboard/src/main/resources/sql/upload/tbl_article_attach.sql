
-- 게시글 첨부파일 테이블 생성
CREATE TABLE tbl_article_attach (
  file_name VARCHAR(150) NOT NULL ,
  article_no INT NOT NULL ,
  reg_date TIMESTAMP DEFAULT NOW(),
  PRIMARY KEY (file_name)
);

-- 게시글 첨부파일 테이블 참조키 설정
ALTER TABLE tbl_article_attach ADD CONSTRAINT fk_article_attach
FOREIGN KEY (article_no) REFERENCES tbl_article (article_no);