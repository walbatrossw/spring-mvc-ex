package com.doubles.ex05;

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
public class BoardControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(BoardControllerTest.class);

    @Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        logger.info("BoardControllerTest Setup......");
    }

    @Test
    public void testWriteGET() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/board/write"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testWritePOST() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/board/write")
                .param("title", "새로운 글 제목")
                .param("content", "새로운 글 내용")
                .param("writer", "작성자")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("msg"))
                .andExpect(redirectedUrl("/board/list"));
    }

    @Test
    public void testRead() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/board/read").param("bno", "999"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("boardVO"));
    }

    @Test
    public void testModifyGET() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/board/modify").param("bno", "999"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("boardVO"));
    }

    @Test
    public void testModifyPOST() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
                .param("bno", "999")
                .param("title", "수정된 글 제목")
                .param("content", "수정된 글 내용")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("msg"))
                .andExpect(redirectedUrl("/board/read?bno=999"));
    }

    @Test
    public void testRemove() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
                .param("bno", "999")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("msg"))
                .andExpect(redirectedUrl("/board/list"));
    }

    @Test
    public void testList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
                .andDo(print())
                .andExpect(model().attributeExists("list"))
                .andExpect(status().isOk());
    }

    @Test
    public void testListPaging() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
                .param("page", "10")
                .param("perPageNum", "5")
        )
                .andDo(print())
                .andExpect(model().attributeExists("list"))
                .andExpect(status().isOk());
    }

}
