package com.doubles.ex05.controller;

import com.doubles.ex05.domain.Criteria;
import com.doubles.ex05.domain.PageMaker;
import com.doubles.ex05.domain.ReplyVO;
import com.doubles.ex05.service.ReplyService;
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

    // 댓글 목록
//    @RequestMapping(value = "/all/{bno}/", method = RequestMethod.GET)
//    public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Integer bno) {
//        ResponseEntity<List<ReplyVO>> entity = null;
//        try {
//            entity = new ResponseEntity<>(replyService.list(bno), HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return entity;
//    }

    // 댓글 목록 + 페이징
    @RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> list(@PathVariable("bno") Integer bno,
                                                    @PathVariable("page") Integer page) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            Criteria criteria = new Criteria();
            criteria.setPage(page);

            List<ReplyVO> list = replyService.list(bno, criteria);
            int totalCount = replyService.count(bno);

            PageMaker pageMaker = new PageMaker();
            pageMaker.setCriteria(criteria);
            pageMaker.setTotalCount(totalCount);

            Map<String, Object> map = new HashMap<>();
            map.put("list", list);
            map.put("pageMaker", pageMaker);

            entity = new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 댓글 입력
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> write(@RequestBody ReplyVO replyVO) {
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

    // 댓글 수정
    @RequestMapping(value = "/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> modify(@PathVariable("rno") Integer rno, @RequestBody ReplyVO replyVO) {
        ResponseEntity<String> entity = null;
        try {
            replyVO.setRno(rno);
            replyService.modifyReply(replyVO);
            entity = new ResponseEntity<>("UPDATED", HttpStatus.OK);
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
            entity = new ResponseEntity<>("DELETED", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}
