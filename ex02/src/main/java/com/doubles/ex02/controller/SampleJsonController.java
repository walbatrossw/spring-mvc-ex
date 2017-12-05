package com.doubles.ex02.controller;

import com.doubles.ex02.domain.SampleVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// RestController 연습
@RestController
@RequestMapping("/sample")
public class SampleJsonController {

    // 단순 문자열 반환
    @RequestMapping("/hello")
    public String sayHello() {

        return "HELLO WORLD";

    }

    // 객체를 JSON으로 반환
    @RequestMapping("/sendVo")
    private SampleVO sendVO() {

        SampleVO sampleVO = new SampleVO();
        sampleVO.setMno(1);
        sampleVO.setFirstName("이");
        sampleVO.setLastName("순신");

        return sampleVO;
    }

    // List 타입으로 반환
    @RequestMapping("/sendList")
    private List<SampleVO> sendList() {

        List<SampleVO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SampleVO sampleVO = new SampleVO();
            sampleVO.setMno(i);
            sampleVO.setFirstName("이");
            sampleVO.setLastName("순신");
            list.add(sampleVO);
        }

        return list;
    }

    // Map 타입으로 반환
    @RequestMapping("/sendMap")
    private Map<Integer, SampleVO> sendMap() {

        Map<Integer, SampleVO> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            SampleVO sampleVO = new SampleVO();
            sampleVO.setMno(i);
            sampleVO.setFirstName("이");
            sampleVO.setLastName("순신");
            map.put(i, sampleVO);
        }
        return map;
    }

    // ResponseEntity : 결과데이터 + HTTP 상태코드를 제어할 수 있는 클래스

    // ResponseEntity 타입 반환 1
    @RequestMapping("/sendErrorAuth")
    public ResponseEntity<Void> sendListAuth() {

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    // ResponseEntity 타입 반환 2
    @RequestMapping("/sendErrorNot")
    public ResponseEntity<List<SampleVO>> sendListNot() {

        List<SampleVO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SampleVO sampleVO = new SampleVO();
            sampleVO.setMno(i);
            sampleVO.setFirstName("홍");
            sampleVO.setLastName("길동");
            list.add(sampleVO);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
