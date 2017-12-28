package com.doubles.ex05.controller;

import com.doubles.ex05.domain.*;
import com.doubles.ex05.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/board")
public class BoardController {

    @Inject
    private BoardService boardService;

    // 게시글 입력 페이지
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String writeGET() {

        return "board/write";
    }

    // 게시글 입력 처리
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writePOST(BoardVO boardVO, RedirectAttributes redirectAttributes) throws Exception {

        boardService.write(boardVO);
        redirectAttributes.addFlashAttribute("msg", "INSERTED");

        return "redirect:/board/list";
    }

    // 게시글 조회
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(@RequestParam("bno") Integer bno,
                       @ModelAttribute("criteria") SearchCriteria criteria, Model model,
                       HttpSession session) throws Exception {

        BoardVO boardVO = boardService.read(bno, session);
        model.addAttribute("boardVO", boardVO);

        return "board/read";
    }

    // 게시글 수정 페이지
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyGET(@RequestParam("bno") Integer bno,
                            @ModelAttribute("criteria") SearchCriteria criteria, Model model,
                            HttpSession session) throws Exception {

        BoardVO boardVO = boardService.read(bno, session);
        model.addAttribute("boardVO", boardVO);

        return "board/modify";
    }

    // 게시글 수정 처리
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPOST(BoardVO boardVO,
                             SearchCriteria criteria, RedirectAttributes redirectAttributes) throws Exception {

        boardService.modify(boardVO);

        redirectAttributes.addAttribute("bno", boardVO.getBno());
        redirectAttributes.addAttribute("page", criteria.getPage());
        redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
        redirectAttributes.addAttribute("searchType", criteria.getSearchType());
        redirectAttributes.addAttribute("keyword", criteria.getKeyword());

        redirectAttributes.addFlashAttribute("msg", "UPDATED");

        return "redirect:/board/read";
    }

    // 게시글 삭제
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("bno") Integer bno,
                         SearchCriteria criteria, RedirectAttributes redirectAttributes) throws Exception {

        boardService.remove(bno);
        redirectAttributes.addAttribute("page", criteria.getPage());
        redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
        redirectAttributes.addAttribute("searchType", criteria.getSearchType());
        redirectAttributes.addAttribute("keyword", criteria.getKeyword());
        redirectAttributes.addFlashAttribute("msg", "DELETED");

        return "redirect:/board/list";
    }

    // 게시글 목록
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public String list(Model model) throws Exception {
//        model.addAttribute("list", boardService.list());
//        return "board/list";
//    }

    // 게시글 목록 + 페이징
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public String list(Criteria criteria, Model model) throws Exception {
//
//        PageMaker pageMaker = new PageMaker();
//        pageMaker.setCriteria(criteria);
//        pageMaker.setTotalCount(boardService.countList(criteria));
//
//        model.addAttribute("pageMaker", pageMaker);
//        model.addAttribute("list", boardService.list(criteria));
//
//        return "board/list";
//    }

    // 게시글 목록 + 페이징 + 검색
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);
        pageMaker.setTotalCount(boardService.searchedListCount(criteria));

        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("list", boardService.list(criteria));
        model.addAttribute("totalCount", boardService.searchedListCount(criteria));

        return "board/list";
    }

}
