-- 게시판 테이블 생성
CREATE TABLE tbl_board (
  bno     INT          NOT NULL AUTO_INCREMENT,
  title   VARCHAR(200) NOT NULL,
  content TEXT         NOT NULL,
  writer  VARCHAR(50)  NOT NULL,
  regdate TIMESTAMP    NOT NULL DEFAULT NOW(),
  viewcnt INT                   DEFAULT 0,
  PRIMARY KEY (bno)
);

-- 게시물 등록
INSERT INTO tbl_board (
  title
  , content
  , writer
) VALUES (
  '제목입니다...'
  , '내용입니다...'
  , 'user00'
);

-- 게시물 조회
SELECT * FROM tbl_board WHERE bno = 1;

-- 게시물 전체 목록
SELECT * FROM tbl_board WHERE bno > 0 ORDER BY bno DESC;

-- 게시물 수정 (제목)
UPDATE tbl_board SET title='수정된 제목...' WHERE bno = 1;

-- 게시물 삭제
DELETE FROM tbl_board WHERE bno = 1;