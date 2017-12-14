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
import org.springframework.transaction.annotation.Transactional;
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

    // 파일 업로드 예제 페이지
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

    // 파일 업로드 처리 메서드
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

    // Ajax 파일 업로드 예제 페이지
    @RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
    public String uploadAjaxGET() {
        return "/fileupload/upload_ajax";
    }

    // 파일 업로드 처리 Ajax 방식 : UploadFileUtils 클래스 사용
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

    // 업로드 된 파일을 화면에 보여주기
    @ResponseBody
    @RequestMapping("/displayFile")
    public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
        // InputStream : 바이트 단위로 데이터를 읽는다. 외부로부터 읽어 들이는 기능관련 클래스
        InputStream inputStream = null;
        ResponseEntity<byte[]> entity = null;
        logger.info("file name : " + fileName);
        try {
            // 파일 확장자 추출
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 이미지 파일 여부 확인, 이미지 파일일 경우 이미지 MINEType 지정
            MediaType mediaType = MediaUtils.getMediaType(formatName);
            // HttpHeaders 객체 생성
            HttpHeaders httpHeaders = new HttpHeaders();
            // 실제 경로의 파일을 읽어들이고 InputStream 객체 생성
            inputStream = new FileInputStream(uploadPath + fileName);
            // 이미지 파일일 경우
            if (mediaType != null) {
                httpHeaders.setContentType(mediaType);
            // 이미지 파일이 아닐 경우
            } else {
                // 디렉토리 + UUID 제거
                fileName = fileName.substring(fileName.indexOf("_") + 1);
                // 다운로드 MIME Type 지정
                httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                // 한글이름의 파일 인코딩처리
                httpHeaders.add("Content-Disposition", "attachment; filename=\"" +
                        new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
            }
            entity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            inputStream.close();
        }
        return entity;
    }

    // 단일 파일 데이터 삭제1 : 게시글 작성시
    @ResponseBody
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(String fileName) throws Exception {
        logger.info("delete file : " + fileName);
        // 파일 삭제
        removeFile(fileName);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    // 단일 파일 데이터 삭제2 : 게시글 수정시
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/deleteFile/{bno}", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(@PathVariable("bno") Integer bno, String fileName) throws Exception {
        // DB에서 파일명 제거
        boardService.deleteAttach(fileName);
        // 첨부파일 갯수 갱신
        boardService.updateAttachCnt(bno);
        logger.info("delete file : " + fileName);
        // 파일 삭제
        removeFile(fileName);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    // 전체 파일 삭제
    @ResponseBody
    @RequestMapping(value = "/deleteAllFiles", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(@RequestParam("files[]") String[] files) {
        logger.info("delete all files : " + files);
        // 파일이 없을 경우 메서드 종료
        if (files == null || files.length == 0) {
            return new ResponseEntity<>("DELETED", HttpStatus.OK);
        }
        // 파일이 존재할 경우 반복문 수행
        for (String fileName : files) {
            // 파일 삭제
            removeFile(fileName);
        }
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    // 파일 삭제 메서드
    private void removeFile(String fileName) {
        // 파일 확장자 추출
        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 파일 확장자를 통해 이미지 파일인지 판별
        MediaType mediaType = MediaUtils.getMediaType(formatName);
        // 이미지 파일일 경우, 원본파일 삭제
        if (mediaType != null) {
            // 원본 이미지의 경로 + 파일명 추출
            // 날짜 경로 추출
            String front = fileName.substring(0, 12);
            // UUID + 파일명 추출
            String end = fileName.substring(14);
            // 원본 이미지 파일 삭제(구분자 변환)
            new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
        }
        // 파일 삭제(일반 파일 or 썸네일 이미지 파일 삭제)
        new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
    }
}
