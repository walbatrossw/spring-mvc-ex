package com.doubles.ex03.controller;

import com.doubles.ex03.domain.Criteria;
import com.doubles.ex03.domain.PageMaker;
import com.doubles.ex03.domain.ReplyVO;
import com.doubles.ex03.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // 특정 게시물의 댓글목록 + 페이징
    @RequestMapping(value = "/all/{bno}/{page}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> list(@PathVariable("bno") Integer bno,
                                                    @PathVariable("page") Integer page) {

        ResponseEntity<Map<String, Object>> entity = null;

        try {
            Criteria criteria = new Criteria();
            criteria.setPage(page);

            PageMaker pageMaker = new PageMaker();
            pageMaker.setCriteria(criteria);

            Map<String, Object> map = new HashMap<>();
            List<ReplyVO> list = replyService.listReply(bno, criteria);

            map.put("list", list);

            int replyCount = replyService.replyCount(bno);
            pageMaker.setTotalCount(replyCount);

            map.put("pageMaker", pageMaker);
            entity = new ResponseEntity<>(map, HttpStatus.OK);

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
