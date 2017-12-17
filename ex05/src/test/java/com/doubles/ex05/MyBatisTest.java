package com.doubles.ex05;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"})
public class MyBatisTest {

    @Inject
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testFactory() {
        System.out.println("SqlSessionFactory : " + sqlSessionFactory);
    }

    @Test
    public void testSession() throws Exception {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            System.out.println("SqlSession : " + sqlSession);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
