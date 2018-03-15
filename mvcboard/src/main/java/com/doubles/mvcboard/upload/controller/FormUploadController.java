package com.doubles.mvcboard.upload.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/file/form")
public class FormUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FormUploadController.class);

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadGET() {

        return "tutorial/upload_form";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadPOST(MultipartFile file, Model model, HttpServletRequest request) throws Exception {

        logger.info("==================== FILE UPLOAD ===========================");
        logger.info("original file name : " + file.getOriginalFilename());
        logger.info("file size : " + file.getSize());
        logger.info("contentType : " + file.getContentType());
        logger.info("============================================================");

        //String uploadPath = getRealUploadPath(request);
        String uploadPath = getLocalUploadPath();

        String savedFileName = uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
        model.addAttribute("savedFileName", savedFileName);

        return "tutorial/upload_result";

    }

    // 로컬 업로드 디렉토리
    private String getLocalUploadPath() {
        return "/home/doubles/workspace/intellij-workspace/spring-mvc-ex/mvcboard/src/main/webapp/resources/upload";
    }

    // 서버업로드 디렉토리
    private String getRealUploadPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/resources/upload/");
    }

    // 파일 업로드 처리
    private String uploadFile(String uploadPath,
                              String originalFileName,
                              byte[] fileData) throws Exception {
        UUID uuid = UUID.randomUUID();
        String savedFileName = uuid.toString() + "_" + originalFileName;
        File target = new File(uploadPath, savedFileName);
        FileCopyUtils.copy(fileData, target);

        return savedFileName;
    }

}
