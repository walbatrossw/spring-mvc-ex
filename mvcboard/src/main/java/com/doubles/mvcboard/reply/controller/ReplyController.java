package com.doubles.mvcboard.reply.controller;

import com.doubles.mvcboard.reply.domain.ReplyVO;
import com.doubles.mvcboard.reply.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;

    @Inject
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody ReplyVO replyVO) {
        ResponseEntity<String> entity = null;
        try {
            replyService.create(replyVO);
            entity = new ResponseEntity<>("regSuccess", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @RequestMapping(value = "/all/{articleNo}", method = RequestMethod.GET)
    public ResponseEntity<List<ReplyVO>> list(@PathVariable("articleNo") Integer articleNo) {
        ResponseEntity<List<ReplyVO>> entity = null;
        try {
            entity = new ResponseEntity<>(replyService.list(articleNo), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @RequestMapping(value = "/{replyNo}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> update(@PathVariable("replyNo") Integer replyNo, @RequestBody ReplyVO replyVO) {
        ResponseEntity<String> entity = null;
        try {
            replyVO.setReplyNo(replyNo);
            replyService.update(replyVO);
            entity = new ResponseEntity<>("modSuccess", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @RequestMapping(value = "/{replyNo}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("replyNo") Integer replyNo) {
        ResponseEntity<String> entity = null;
        try {
            replyService.delete(replyNo);
            entity = new ResponseEntity<>("delSuccess", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}
