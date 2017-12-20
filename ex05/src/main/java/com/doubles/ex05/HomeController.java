package com.doubles.ex05;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/doA", method = RequestMethod.GET)
    public String doA(Locale locale, Model model) {
        System.out.println("doA...................");
        return "interceptor/ex01";
    }

    @RequestMapping(value = "/doB", method = RequestMethod.GET)
    public String doB(Locale locale, Model model) {
        System.out.println("doB...................");
        model.addAttribute("result", "doB result");
        return "interceptor/ex01";
    }

}
