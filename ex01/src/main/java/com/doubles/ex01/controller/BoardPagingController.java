package com.doubles.ex01.controller;

import com.doubles.ex01.domain.BoardVO;
import com.doubles.ex01.domain.Criteria;
import com.doubles.ex01.domain.PageMaker;
import com.doubles.ex01.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

// 페이징 처리
@Controller
@RequestMapping("/board/paging")
public class BoardPagingController {

    private static final Logger logger = LoggerFactory.getLogger(BoardPagingController.class);

    @Inject
    private BoardService boardService;

    // 게시글 입력 페이지 : 일반과 동일
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGET() {

        logger.info("================ registerGET() : called ================");

        return "/board/paging/register";
    }

    // 게시글 입력 처리 : 일반과 동일
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPOST(@ModelAttribute("boardVO") BoardVO boardVO, RedirectAttributes rttr) throws Exception {

        logger.info("================ registerPOST() : called ================");
        logger.info("Inserted BoardVO : " + boardVO);
        boardService.register(boardVO);
        rttr.addFlashAttribute("msg", "INSERT");

        return "redirect:/board/paging/list";
    }

    // 게시글 조회 : 목록페이지 정보 유지
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(@RequestParam("bno") int bno,
                       @ModelAttribute("criteria") Criteria criteria, Model model) throws Exception {

        logger.info("================ read() : called ================");
        logger.info("Get boardVO : " + boardService.read(bno));
        model.addAttribute(boardService.read(bno));

        return "/board/paging/read";
    }

    // 게시글 수정 페이지 : 목록페이지 정보 유지
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyGET(@RequestParam("bno") int bno,
                            @ModelAttribute("criteria") Criteria criteria,
                            Model model) throws Exception {

        logger.info("================ modifyGET() : called ================");
        logger.info("Get boardVO : " + boardService.read(bno));
        model.addAttribute("boardVO", boardService.read(bno));

        return "/board/paging/modify";
    }

    // 게시글 수정 처리 : 목록페이지 정보 유지
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPOST(@ModelAttribute("boardVO") BoardVO boardVO,
                             @ModelAttribute("criteria") Criteria criteria, RedirectAttributes rttr) throws Exception {

        logger.info("================ modifyPOST() : called ================");
        logger.info("Modified boardVO : " + boardVO);
        boardService.modify(boardVO);
        rttr.addAttribute("bno", boardVO.getBno());
        rttr.addAttribute("page", criteria.getPage());
        rttr.addAttribute("perPageNum", criteria.getPerPageNum());
        rttr.addFlashAttribute("msg", "MODIFY");

        return "redirect:/board/paging/read";
    }

    // 게시글 삭제 처리 : 목록페이지 정보 유지
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("bno") int bno,
                         @ModelAttribute("criteria") Criteria criteria,
                         RedirectAttributes rttr) throws Exception {

        logger.info("================ remove() : called ================");
        logger.info("bno : " + bno);
        boardService.remove(bno);
        rttr.addAttribute("page", criteria.getPage());
        rttr.addAttribute("perPageNum", criteria.getPerPageNum());
        rttr.addFlashAttribute("msg", "DELETE");

        return "redirect:/board/paging/list";
    }

    // 게시글 목록 : 하단 페이징 처리 X
    @RequestMapping(value = "/listCriteria", method = RequestMethod.GET)
    public String listCriteria(@ModelAttribute("criteria") Criteria criteria, Model model) throws Exception {

        logger.info("================ listCriteria() : called ================");
        logger.info("================ Show list page with Criteria ================");
        model.addAttribute("list", boardService.listCriteria(criteria));

        return "/board/paging/list_criteria";
    }

    // 게시글 목록 : 하단 페이징 처리 O
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listWithPaging(@ModelAttribute("criteria") Criteria criteria, Model model) throws Exception {

        logger.info("================ listPaging() : called ================");
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);
        //pageMaker.setTotalCount(131);
        pageMaker.setTotalCount(boardService.listCountCriteria(criteria));

        model.addAttribute("list", boardService.listCriteria(criteria));
        model.addAttribute("pageMaker", pageMaker);

        return "/board/paging/list";
    }

}
