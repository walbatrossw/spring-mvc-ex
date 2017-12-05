package com.doubles.ex02.controller;

import com.doubles.ex02.domain.ReplyVO;
import com.doubles.ex02.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Inject
    private ReplyService replyService;

    // 댓글 등록
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody ReplyVO replyVO) {

        ResponseEntity<String> entity = null;

        try {
            replyService.addReply(replyVO);
            entity = new ResponseEntity<String>("INSERTED", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    // 댓글 목록
    @RequestMapping(value = "/all/{bno}", method = RequestMethod.GET)
    public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Integer bno) {

        ResponseEntity<List<ReplyVO>> entity = null;
        try {
            entity = new ResponseEntity<>(replyService.listReply(bno), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    // 댓글 수정
    @RequestMapping(value = "/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> update(@PathVariable("rno") Integer rno, @RequestBody ReplyVO replyVO) {

        ResponseEntity<String> entity = null;
        try {
            replyVO.setBno(rno);
            replyService.modifyReply(replyVO);
            entity = new ResponseEntity<String>("MODIFIED",HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }


    // 댓글 삭제
    @RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("rno") Integer rno) {
        ResponseEntity<String> entity = null;
        try {
            replyService.removeReply(rno);
            entity = new ResponseEntity<>("DELETED", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

}
