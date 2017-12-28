package com.doubles.ex05.controller;

import com.doubles.ex05.domain.BookmarkVO;
import com.doubles.ex05.service.BookmarkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

    @Inject
    private BookmarkService bookmarkService;

    // 북마크 등록
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody BookmarkVO bookMarkVO) {
        ResponseEntity<String> entity = null;
        try {
            bookmarkService.create(bookMarkVO);
            entity = new ResponseEntity<>("BOOKMARK INSERTED", HttpStatus.OK);
        } catch (Exception e) {
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 북마크 목록
    @RequestMapping(value = "/list/{uid}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> list(@PathVariable("uid") String uid) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            List<BookmarkVO> bookmarkList = bookmarkService.list(uid);
            Map<String, Object> map = new HashMap<>();
            map.put("bookmarkList", bookmarkList);
            entity = new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 북마크 확인
    @RequestMapping(value = "/check/{bno}/{uid}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> checkBookMark(@PathVariable("bno") Integer bno,
                                                @PathVariable("uid") String uid) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            boolean isBookmarkCheck = bookmarkService.checkBookmark(bno, uid);
            Map<String, Object> map = new HashMap<>();
            map.put("isBookmarkCheck", isBookmarkCheck);
            entity = new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 북마크 삭제
    @RequestMapping(value = "/remove/{bno}/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity<String> remove(@PathVariable("bno") Integer bno,
                                         @PathVariable("uid") String uid) {
        ResponseEntity<String> entity = null;
        try {
            bookmarkService.remove(bno, uid);
            entity = new ResponseEntity<>("BOOKMARK DELETED", HttpStatus.OK);
        } catch (Exception e) {
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

}
