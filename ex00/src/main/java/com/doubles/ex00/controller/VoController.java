package com.doubles.ex00.controller;

import com.doubles.ex00.domain.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VoController {

    private static final Logger logger = LoggerFactory.getLogger(VoController.class);

    @RequestMapping("/doD")
    public String doD(Model model) {
        ProductVO vo = new ProductVO("sample product", 100000);
        logger.info("doD : " + vo);
        model.addAttribute(vo);
        model.addAttribute("vo", vo);

        return "productDetail";
    }
}
