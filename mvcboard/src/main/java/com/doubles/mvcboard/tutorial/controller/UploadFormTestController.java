package com.doubles.mvcboard.tutorial.controller;

import com.doubles.mvcboard.commons.util.UploadFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/file/form")
public class UploadFormTestController {

    private static final Logger logger = LoggerFactory.getLogger(UploadFormTestController.class);

    @RequestMapping(value = "/uploadPage", method = RequestMethod.GET)
    public String uploadPage() {

        return "tutorial/upload_form";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(MultipartFile file, Model model, HttpServletRequest request) throws Exception {

        logger.info("==================== FILE UPLOAD ===========================");
        logger.info("original file name : " + file.getOriginalFilename());
        logger.info("file size : " + file.getSize());
        logger.info("contentType : " + file.getContentType());
        logger.info("============================================================");

//        String uploadPath = getRealUploadPath(request);
//        String savedFileName = uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());

        String savedFileName = UploadFileUtils.uploadFile(file, request);
        model.addAttribute("savedFileName", savedFileName);

        return "tutorial/upload_result";

    }

//    // 업로드 디렉토리
//    private String getRealUploadPath(HttpServletRequest request) {
//        return request.getSession().getServletContext().getRealPath("/resources/upload/");
//    }
//
//    // 파일 업로드 처리
//    private String uploadFile(String uploadPath,
//                              String originalFileName,
//                              byte[] fileData) throws Exception {
//        // 중복파일 저장을 방지하기 위해
//        UUID uuid = UUID.randomUUID();
//        String savedFileName = uuid.toString() + "_" + originalFileName;
//
//        // 파일 객체 생성
//        File target = new File(uploadPath, savedFileName);
//
//        // 파일 데이터를 파일로 처리
//        // 데이터가 담긴 바이트 배열을 파일객체에 기록
//        FileCopyUtils.copy(fileData, target);
//
//        return savedFileName;
//    }

}
