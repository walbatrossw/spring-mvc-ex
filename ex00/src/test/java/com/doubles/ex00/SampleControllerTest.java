package com.doubles.ex00;

import com.doubles.ex00.domain.ProductVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class SampleControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(SampleControllerTest.class);

    @Inject
    private WebApplicationContext wac;

    // 브라우저에서 요청과 응답을 의미하는 객체
    private MockMvc mockMvc;

    @Before // 테스트를 진행하기전 수행하는 작업
    public void setup() {
        // MockMvc 객체를 생성
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        logger.info("setup.....");
    }

    // 각각의 Controller 테스트 코드 작성

    @Test
    public void testDoA() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/doA"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDoB() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/doB"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDoC() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/doC?msg=Hello"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDoD() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/doD"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("vo"));
    }

    @Test
    public void testDoE() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/doE"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/doF"));
    }

    @Test
    public void testDoJson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/doJson"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=utf-8"));
    }
}
