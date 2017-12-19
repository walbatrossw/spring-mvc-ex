package com.doubles.ex05.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UploadDAOImpl implements UploadDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.doubles.ex05.mapper.UploadMapper";

    // 게시글 첨부파일 추가
    @Override
    public void addAttach(String fullName, Integer bno) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fullName", fullName);
        paramMap.put("bno", bno);
        sqlSession.insert(NAMESPACE + ".addAttach", paramMap);
    }

    // 게시글 첨부파일 조회
    @Override
    public List<String> getAttach(Integer bno) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".getAttach", bno);
    }

    // 게시글 첨부파일 수정
    @Override
    public void replaceAttach(String fullName, Integer bno) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fullName", fullName);
        paramMap.put("bno", bno);
        sqlSession.insert(NAMESPACE + ".replaceAttach", paramMap);
    }

    // 게시글 첨부파일 삭제
    @Override
    public void deleteAttach(String fullName) throws Exception {
        sqlSession.delete(NAMESPACE + ".deleteAttach", fullName);
    }

    // 게시글 첨부파일 일괄 삭제
    @Override
    public void deleteAllAttach(Integer bno) throws Exception {
        sqlSession.delete(NAMESPACE + ".deleteAllAttach", bno);
    }

    // 특정 게시글의 첨부파일 갯수 갱신
    @Override
    public void updateAttachCnt(Integer bno) throws Exception {
        sqlSession.update(NAMESPACE + ".updateAttachCnt", bno);
    }

}
