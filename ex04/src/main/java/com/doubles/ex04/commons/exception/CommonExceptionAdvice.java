package com.doubles.ex04.commons.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView commonException(Exception e) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.setViewName("commons/common_errors");
        logger.info(e.toString());

        return modelAndView;
    }

}
