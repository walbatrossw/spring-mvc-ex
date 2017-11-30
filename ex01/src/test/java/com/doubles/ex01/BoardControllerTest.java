package com.doubles.ex01;

import com.doubles.ex01.domain.BoardVO;
import com.doubles.ex01.service.BoardService;
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
        logger.info("setup.....");
    }

    @Test
    public void testRegisterGET() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/board/register"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testRegisterPOST() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
                .param("title", "new title...")
                .param("content", "new content...")
                .param("writer", "writer01")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("msg", "INSERT"))
                .andExpect(redirectedUrl("/board/listAll"));

    }

    @Test
    public void testListAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/board/listAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("list"));
    }

    @Test
    public void testRead() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/board/read?bno=25"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("boardVO"));
    }

    @Test
    public void testModifyGET() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/board/modify?bno=25"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("boardVO"));
    }

    @Test
    public void testModifyPOST() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
                .param("bno", "22")
                .param("title", "modified title....")
                .param("content", "modified content....")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("msg", "MODIFY"))
                .andExpect(redirectedUrl("/board/read?bno=22"));
    }

    @Test
    public void testRemove() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
                .param("bno", "22")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("msg", "DELETE"))
                .andExpect(redirectedUrl("/board/listAll"));
    }
}
