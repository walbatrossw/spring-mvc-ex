package com.doubles.mvcboard.tutorial;

import com.doubles.mvcboard.tutorial.domain.MemberVO;
import com.doubles.mvcboard.tutorial.persistence.MemberDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class MemberDAOTest {

    @Inject
    private MemberDAO memberDAO;

    // 현재시간 출력 테스트
    @Test
    public void testTime() throws Exception {
        System.out.println(memberDAO.getTime());
    }

    // 회원 가입 테스트
    @Test
    public void testInsertMember() throws Exception {
        MemberVO memberVO = new MemberVO();
        memberVO.setUserid("user00");
        memberVO.setUserpw("user00");
        memberVO.setUsername("user00");
        memberVO.setEmail("user00@mail.com");

        memberDAO.insertMember(memberVO);
    }

    // 회원 조회 테스트 1 : 아이디만
    @Test
    public void testReadMember() throws Exception {
        memberDAO.readMember("user00");
    }

    // 회원조회 테스트 2 : 아이디 + 비밀번호
    @Test
    public void testReadWithPW() throws Exception {
        memberDAO.readWithPW("user00", "user00");
    }
}
