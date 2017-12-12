package com.doubles.ex04.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/fileupload")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    // 파일 저장 경로
    @Resource(name = "uploadPath")
    private String uploadPath;


    // 파일 업로드 페이지
    @RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
    public String uploadForm() {
        return "/fileupload/upload_form";
    }

    // 파일 업로드 처리
    @RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
    public String uploadForm(MultipartFile file, Model model) throws Exception {
        logger.info("==================== FILE UPLOAD ===========================");
        logger.info("original file name : " + file.getOriginalFilename());
        logger.info("file size : " + file.getSize());
        logger.info("contentType : " + file.getContentType());
        logger.info("============================================================");

        String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
        model.addAttribute("savedName", savedName);

        return "/fileupload/upload_form";
    }

    // 파일 업로드 처리
    private String uploadFile(String originalFileName, byte[] fileData) throws Exception {

        // 고유한 파일명을 위해 UUID 키 값 생성
        UUID uuid = UUID.randomUUID();
        // UUID + _ + 원본파일명
        String savedName = uuid.toString() + "_" + originalFileName;

        // 업로드 경로, 파일명 : 파일 객체 생성
        File target = new File(uploadPath, savedName);

        // 파일을 경로에 전송
        FileCopyUtils.copy(fileData, target);

        return savedName;
    }

}
