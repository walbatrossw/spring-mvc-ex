package com.doubles.mvcboard.tutorial.controller;

import com.doubles.mvcboard.tutorial.domain.SampleVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ajax/test")
public class AjaxController {

    @RequestMapping("/hello")
    public String sayHello() {

        return "Hello World";

    }


    @RequestMapping("/sendVO")
    public SampleVO sendVO() {

        SampleVO sample = new SampleVO();
        sample.setSampleNo(1);
        sample.setFirstName("더블");
        sample.setLastName("에스");

        return sample;
    }

    @RequestMapping("/sendList")
    public List<SampleVO> sendList() {

        List<SampleVO> samples = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SampleVO sample = new SampleVO();
            sample.setSampleNo(i);
            sample.setFirstName("더블");
            sample.setLastName("에스" + i);
            samples.add(sample);
        }

        return samples;
    }

    @RequestMapping("/sendMap")
    public Map<Integer, SampleVO> sendMap() {

        Map<Integer, SampleVO> sampleMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            SampleVO sample = new SampleVO();
            sample.setFirstName("더블");
            sample.setLastName("에스"+i);
            sample.setSampleNo(i);
            sampleMap.put(i, sample);
        }

        return sampleMap;
    }

    @RequestMapping("/sendErrorAuth")
    public ResponseEntity<Void> sendListAuth() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/sendErrorNot")
    public ResponseEntity<List<SampleVO>> sendListNot() {

        List<SampleVO> samples = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SampleVO sample = new SampleVO();
            sample.setSampleNo(i);
            sample.setFirstName("더블");
            sample.setLastName("에스" + i);
            samples.add(sample);
        }

        return new ResponseEntity<>(samples, HttpStatus.NOT_FOUND);
    }

}
