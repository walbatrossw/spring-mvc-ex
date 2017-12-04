package com.doubles.ex02;

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
        logger.info("SETUP...");

    }

    @Test
    public void testRegisterGET() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/board/register"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("board/register"));

    }

    @Test
    public void testRegisterPOST() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
                .param("title", "새글 제목...")
                .param("content", "새글 내용...")
                .param("writer", "새글 작성자...")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("msg", "INSERTED"))
                .andExpect(redirectedUrl("board/list"));

    }

    @Test
    public void testList() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("list"))
                .andExpect(view().name("board/list"));

    }

    @Test
    public void testRead() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/board/read")
                .param("bno", "25")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("boardVO"))
                .andExpect(view().name("board/read"));

    }

    @Test
    public void testModifyGET() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/board/modify")
                .param("bno", "1000")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("board/modify"));

    }

    @Test
    public void testModifyPOST() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
                .param("bno", "1000")
                .param("title", "수정된 글 제목입니다...")
                .param("content", "수정된 글 내용입니다...")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("msg", "MODIFIED"))
                .andExpect(model().attribute("bno", "1000"))
                .andExpect(redirectedUrl("board/read?bno=1000"));

    }

    @Test
    public void testRemove() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
                .param("bno", "998")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("msg", "REMOVED"))
                .andExpect(redirectedUrl("board/list"));

    }

    @Test
    public void testReadWithCriteria() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/board/read")
                .param("bno", "25")
                .param("page", "6")
                .param("perPageNum", "20")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("boardVO"))
                .andExpect(view().name("board/read"));

    }

    @Test
    public void testListWithCriteria() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/board/list?page=6&perPageNum=20"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("list"))
                .andExpect(model().attributeExists("pageMaker"))
                .andExpect(view().name("board/list"));

    }

    @Test
    public void testRemoveWithCriteria() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
                .param("bno", "991")
                .param("page", "6")
                .param("perPageNum", "20")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("msg", "REMOVED"))
                .andExpect(model().attributeExists("page"))
                .andExpect(model().attributeExists("perPageNum"))
                .andExpect(redirectedUrl("/board/list?page=6&perPageNum=20"));

    }

    @Test
    public void testModifyPOSTWithCriteria() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
                .param("bno", "1000")
                .param("title", "수정된 글 제목입니다. --> testModifyPOSTWithCriteria()")
                .param("content", "수정된 글 내용입니다...")
                .param("page", "6")
                .param("perPageNum", "20")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("msg"))
                .andExpect(model().attributeExists("bno"))
                .andExpect(model().attributeExists("page"))
                .andExpect(model().attributeExists("perPageNum"))
                .andExpect(redirectedUrl("/board/read?page=6&perPageNum=20&bno=1000"));

    }

}
