package com.doubles.ex04.controller;

import com.doubles.ex04.commons.util.MediaUtils;
import com.doubles.ex04.commons.util.UploadFileUtils;
import com.doubles.ex04.service.BoardService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

@Controller
@RequestMapping("/fileupload")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    // 파일 저장 경로
    @Resource(name = "uploadPath")
    private String uploadPath;

    @Inject
    private BoardService boardService;

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

        return "/fileupload/upload_result";
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

    // Ajax 파일 업로드 페이지
    @RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
    public String uploadAjaxGET() {

        return "/fileupload/upload_ajax";
    }

    // 파일 업로드 처리 Ajax 방식
    @ResponseBody
    @RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> uploadAjaxPOST(MultipartFile file) throws Exception {

        logger.info("----------- FILE AJAX UPLOAD -----------");
        logger.info("original file name : " + file.getOriginalFilename());
        logger.info("file size : " + file.getSize());
        logger.info("contentType : " + file.getContentType());
        logger.info("-----------------------------------------");

        return new ResponseEntity<>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
    }

    // 파일 데이터 브라우저 전송
    @ResponseBody
    @RequestMapping("/displayFile")
    public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {

        InputStream inputStream = null;
        ResponseEntity<byte[]> entity = null;
        logger.info("file name : " + fileName);
        try {
            // 파일 확장자 추출
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

            // 이미지 파일 여부 확인
            MediaType mediaType = MediaUtils.getMediaType(formatName);
            HttpHeaders httpHeaders = new HttpHeaders();
            inputStream = new FileInputStream(uploadPath + fileName);

            // MineType 지정
            if (mediaType != null) {
                httpHeaders.setContentType(mediaType);
            } else {
                fileName = fileName.substring(fileName.indexOf("_") + 1);
                httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                httpHeaders.add("Content-Disposition", "attachment; filename=\"" +
                        new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
            }

            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(inputStream), httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            inputStream.close();
        }
        return entity;
    }

    // 파일 데이터 삭제
    @ResponseBody
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(String fileName) throws Exception {
        boardService.deleteAttach(fileName);
        logger.info("delete file : " + fileName);
        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
        MediaType mediaType = MediaUtils.getMediaType(formatName);
        if (mediaType != null) {
            String front = fileName.substring(0, 12);
            String end = fileName.substring(14);
            new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
        }
        new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    // 파일 전체 삭제
    @ResponseBody
    @RequestMapping(value = "/deleteAllFiles", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(@RequestParam("files[]") String[] files) {
        logger.info("delete all files : " + files);
        if (files == null || files.length == 0) {
            return new ResponseEntity<>("DELETED", HttpStatus.OK);
        }
        for (String fileName : files) {
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            MediaType mediaType = MediaUtils.getMediaType(formatName);

            if (mediaType != null) {
                String front = fileName.substring(0, 12);
                String end = fileName.substring(14);
                new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
            }
            new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
        }
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
