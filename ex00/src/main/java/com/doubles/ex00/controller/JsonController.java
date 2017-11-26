package com.doubles.ex00.controller;

import com.doubles.ex00.domain.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JsonController {

    private static final Logger logger = LoggerFactory.getLogger(JsonController.class);

    @RequestMapping("doJson")
    @ResponseBody
    public ProductVO doJson() {
        ProductVO vo = new ProductVO("sample product", 300000);
        logger.info(String.valueOf(vo));
        return vo;
    }
}
