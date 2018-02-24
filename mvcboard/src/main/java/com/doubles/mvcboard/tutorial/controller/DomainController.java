package com.doubles.mvcboard.tutorial.controller;

import com.doubles.mvcboard.tutorial.domain.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DomainController {

    private static final Logger logger = LoggerFactory.getLogger(DomainController.class);

    @RequestMapping("/doD")
    public String doD(Model model) {

        ProductVO product = new ProductVO("desktop", 10000);
        logger.info("/doD called");
        model.addAttribute(product);

        return "/tutorial/product_detail";
    }

}
