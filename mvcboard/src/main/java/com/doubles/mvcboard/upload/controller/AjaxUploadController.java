package com.doubles.mvcboard.upload.controller;

import com.doubles.mvcboard.commons.util.MediaUtils;
import com.doubles.mvcboard.commons.util.UploadFileUtils;
import com.doubles.mvcboard.commons.util.UploadPathUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/file/ajax")
public class AjaxUploadController {

    private static final Logger logger = LoggerFactory.getLogger(AjaxUploadController.class);

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView uploadAjaxGET() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tutorial/upload_ajax");
        return modelAndView;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> uploadAjaxPOST(MultipartFile file, HttpServletRequest request) throws Exception {

        logger.info("==================== FILE UPLOAD ===========================");
        logger.info("original file name : " + file.getOriginalFilename());
        logger.info("file size : " + file.getSize());
        logger.info("contentType : " + file.getContentType());
        logger.info("============================================================");

        return new ResponseEntity<>(UploadFileUtils.uploadFile(file.getOriginalFilename(), file.getBytes(), request), HttpStatus.CREATED);
    }


}
