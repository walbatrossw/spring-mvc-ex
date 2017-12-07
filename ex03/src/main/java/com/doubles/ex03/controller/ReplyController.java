package com.doubles.ex03.controller;

import com.doubles.ex03.domain.ReplyVO;
import com.doubles.ex03.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Inject
    private ReplyService replyService;

    // 댓글 입력
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody ReplyVO replyVO) {

        ResponseEntity<String> entity = null;

        try {
            replyService.addReply(replyVO);
            entity = new ResponseEntity<>("INSERTED", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;

    }

    // 특정 게시물의 댓글목록
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
    @RequestMapping(value = "/{rno}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<String> update(@PathVariable("rno") Integer rno, @RequestBody ReplyVO replyVO) {

        ResponseEntity<String> entity = null;

        try {
            replyVO.setRno(rno);
            replyService.modifyReply(replyVO);
            entity = new ResponseEntity<>("MODIFIED", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;

    }

    // 댓글 삭제
    @RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
    public ResponseEntity<String> remove(@PathVariable("rno") Integer rno) {

        ResponseEntity<String> entity = null;

        try {
            replyService.removeReply(rno);
            entity = new ResponseEntity<>("REMOVED", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;

    }
}
