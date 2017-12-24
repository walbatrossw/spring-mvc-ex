package com.doubles.ex05.controller;

import com.doubles.ex05.commons.utils.MediaUtils;
import com.doubles.ex05.commons.utils.UploadFileUtils;
import com.doubles.ex05.service.UploadService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/file")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Inject
    private UploadService uploadService;

    // 파일 저장 기본 경로 bean 등록
    @Resource(name = "uploadPath")
    private String uploadPath;

    // 업로드 파일
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/pliain;charset=UTF-8")
    public ResponseEntity<String> uploadFile(MultipartFile file) throws Exception {
        logger.info("========================================= FILE UPLOAD =========================================");
        logger.info("ORIGINAL FILE NAME : " + file.getOriginalFilename());
        logger.info("FILE SIZE : " + file.getSize());
        logger.info("CONTENT TYPE : " + file.getContentType());
        logger.info("===============================================================================================");
        return new ResponseEntity<>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
    }

    // 게시글 첨부파일 조회
    @RequestMapping(value = "/list/{bno}")
    public ResponseEntity<List<String>> fileList(@PathVariable("bno") Integer bno) throws Exception {
        ResponseEntity<List<String>> entity = null;
        try {
            entity = new ResponseEntity<>(uploadService.getAttach(bno), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 업로드 파일 보여주기
    @RequestMapping(value = "/display")
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

    // 단일 파일 데이터 삭제1 : 게시글 작성화면
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> boardWriteRemoveFile(String fileName) throws Exception {
        // 파일 삭제
        UploadFileUtils.removeFile(uploadPath, fileName);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    // 단일 파일 데이터 삭제2 : 게시글 수정화면
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/delete/{bno}", method = RequestMethod.POST)
    public ResponseEntity<String> boardMofifyRemoveFile(@PathVariable("bno") Integer bno, String fileName) throws Exception {
        // DB에서 파일명 제거
        uploadService.deleteAttach(fileName);
        // 첨부파일 갯수 갱신
        uploadService.updateAttachCnt(bno);
        // 파일 삭제
        UploadFileUtils.removeFile(uploadPath, fileName);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    // 전체 파일 삭제 : 게시글 삭제 처리시
    @ResponseBody
    @RequestMapping(value = "/delete/all", method = RequestMethod.POST)
    public ResponseEntity<String> boardDeleteRemoveAllFiles(@RequestParam("files[]") String[] files) {
        // 파일이 없을 경우 메서드 종료
        if (files == null || files.length == 0) {
            return new ResponseEntity<>("DELETED", HttpStatus.OK);
        }
        // 파일이 존재할 경우 반복문 수행
        for (String fileName : files) {
            // 파일 삭제
            UploadFileUtils.removeFile(uploadPath, fileName);
        }
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

}
