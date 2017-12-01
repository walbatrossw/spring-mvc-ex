package com.doubles.ex01.controller;

import com.doubles.ex01.domain.BoardVO;
import com.doubles.ex01.domain.PageMaker;
import com.doubles.ex01.domain.SearchCriteria;
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

@Controller
@RequestMapping("/sboard")
public class SearchBoardController {

    private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);

    @Inject
    private BoardService boardService;

    // 게시글 입력페이지
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerGET(BoardVO boardVO, Model model) throws Exception {

        logger.info("registerGET() : called... ");

    }

    // 게시글 입력처리
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPOST(BoardVO boardVO, RedirectAttributes rttr) throws Exception {

        logger.info("registerPOST() : called...");
        logger.info("Inserted BoardVO : " + boardVO);
        boardService.register(boardVO);
        rttr.addFlashAttribute("msg", "INSERT"); // redirect되는 시점에 한번만 사용되는 데이터 전송

        return "redirect:/sboard/list";
    }

    // 게시글 목록
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage(@ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {

        logger.info(criteria.toString());
        //model.addAttribute("list", boardService.listCriteria(criteria));
        model.addAttribute("list", boardService.listSearchCriteria(criteria));

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);
        //pageMaker.setTotalCount(boardService.listCountCriteria(criteria));
        pageMaker.setTotalCount(boardService.listSearchCount(criteria));
        model.addAttribute("boardCount", boardService.listSearchCount(criteria));
        model.addAttribute("pageMaker", pageMaker);

        return "/sboard/list";
    }

    // 게시글 조회
    @RequestMapping(value = "/readPage", method = RequestMethod.GET)
    public String readPage(@RequestParam("bno") int bno,
                           @ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {
        model.addAttribute(boardService.read(bno));
        return "/sboard/read_page";
    }

    // 게시글 수정 페이지
    @RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
    public String modifyPagingGET(@RequestParam("bno") int bno,
                                  @ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {

        model.addAttribute("boardVO", boardService.read(bno));

        return "/sboard/modify_page";
    }

    // 게시글 수정 처리
    @RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
    public String modifyPagingPOST(@ModelAttribute("boardVO") BoardVO boardVO,
                                   @ModelAttribute("criteria") SearchCriteria criteria,
                                   RedirectAttributes rttr) throws Exception {
        logger.info(criteria.toString());
        boardService.modify(boardVO);

        rttr.addAttribute("bno", boardVO.getBno());
        rttr.addAttribute("page", criteria.getPage());
        rttr.addAttribute("perPageNum", criteria.getPerPageNum());
        rttr.addAttribute("searchType", criteria.getSearchType());
        rttr.addAttribute("keyword", criteria.getKeyword());

        rttr.addFlashAttribute("msg", "MODIFY");

        logger.info(rttr.toString());

        return "redirect:/sboard/readPage";

    }

    // 게시글 삭제
    @RequestMapping(value = "/deletePage", method = RequestMethod.POST)
    public String deletePage(@RequestParam("bno") int bno,
                             @ModelAttribute("criteria") SearchCriteria criteria,
                             RedirectAttributes rttr) throws Exception {

        boardService.delete(bno);

        rttr.addAttribute("page", criteria.getPage());
        rttr.addAttribute("perPageNum", criteria.getPerPageNum());
        rttr.addAttribute("searchType", criteria.getSearchType());
        rttr.addAttribute("keyword", criteria.getKeyword());

        rttr.addFlashAttribute("msg", "DELETE");

        return "redirect:/sboard/list";
    }

}
