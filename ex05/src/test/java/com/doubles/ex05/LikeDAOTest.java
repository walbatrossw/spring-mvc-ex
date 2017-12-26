package com.doubles.ex05;

import com.doubles.ex05.persistence.LikeDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"})
public class LikeDAOTest {

    @Inject
    private LikeDAO likeDAO;

    private static Logger logger = LoggerFactory.getLogger(LikeDAOTest.class);

    @Test
    public void testCheckBoardLike() throws Exception {
        boolean result = likeDAO.checkBoardLike(1028, "doubles");
        logger.info("================================================>>>>>>" + result);
    }

}
