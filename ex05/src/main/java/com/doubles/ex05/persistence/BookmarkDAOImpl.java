package com.doubles.ex05.persistence;

import com.doubles.ex05.domain.BookmarkVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookmarkDAOImpl implements BookmarkDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.doubles.ex05.mapper.BookmarkMapper";

    @Override
    public void create(BookmarkVO bookmarkVO) throws Exception {

        sqlSession.insert(NAMESPACE + ".create", bookmarkVO);

    }

    @Override
    public List<BookmarkVO> list(String uid) throws Exception {

        return sqlSession.selectList(NAMESPACE + ".list", uid);

    }

    @Override
    public boolean checkBookmark(Integer bno, String uid) throws Exception {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("bno", bno);
        paramMap.put("uid", uid);

        return sqlSession.selectOne(NAMESPACE + ".checkBookmark", paramMap);
    }

    @Override
    public void delete(Integer bno, String uid) throws Exception {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("bno", bno);
        paramMap.put("uid", uid);

        sqlSession.delete(NAMESPACE + ".delete",paramMap);
    }
}
