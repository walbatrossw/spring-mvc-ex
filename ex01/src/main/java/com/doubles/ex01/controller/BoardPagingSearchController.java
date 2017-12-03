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
@RequestMapping("/board/paging/search")
public class BoardPagingSearchController {

    private static final Logger logger = LoggerFactory.getLogger(BoardPagingSearchController.class);

    @Inject
    private BoardService boardService;

    // 게시글 입력페이지 : 기존과 동일
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGET(BoardVO boardVO, Model model) throws Exception {

        logger.info("================ registerGET() : called ================");

        return "/board/paging_search/register";
    }

    // 게시글 입력처리 : 기존과 동일
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPOST(BoardVO boardVO, RedirectAttributes rttr) throws Exception {

        logger.info("================ registerPOST() : called ================");
        logger.info("Inserted BoardVO : " + boardVO);
        boardService.register(boardVO);
        rttr.addFlashAttribute("msg", "INSERT");

        return "redirect:/board/paging/search/list";
    }

    // 게시글 조회 : 목록페이지 정보, 검색 정보 유지
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(@RequestParam("bno") int bno,
                       @ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {

        logger.info("================ read() : called ================");
        logger.info("Get boardVO : " + boardService.read(bno));
        model.addAttribute(boardService.read(bno));

        return "/board/paging_search/read";
    }

    // 게시글 수정 페이지 : 목록페이지 정보, 검색 정보 유지
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyGET(@RequestParam("bno") int bno,
                            @ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {

        logger.info("================ modifyGET() : called ================");
        logger.info("Get boardVO : " + boardService.read(bno));
        model.addAttribute("boardVO", boardService.read(bno));

        return "/board/paging_search/modify";
    }

    // 게시글 수정 처리 : 목록페이지 정보, 검색 정보 유지
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPOST(@ModelAttribute("boardVO") BoardVO boardVO,
                             @ModelAttribute("criteria") SearchCriteria criteria,
                             RedirectAttributes rttr) throws Exception {

        logger.info("================ modifyPOST() : called ================");
        logger.info("Modified boardVO : " + boardVO);
        logger.info(criteria.toString());
        boardService.modify(boardVO);

        rttr.addAttribute("bno", boardVO.getBno());
        rttr.addAttribute("page", criteria.getPage());
        rttr.addAttribute("perPageNum", criteria.getPerPageNum());
        rttr.addAttribute("searchType", criteria.getSearchType());
        rttr.addAttribute("keyword", criteria.getKeyword());

        rttr.addFlashAttribute("msg", "MODIFY");

        logger.info(rttr.toString());

        return "redirect:/board/paging/search/read";

    }

    // 게시글 삭제 : 목록페이지 정보, 검색 정보 유지
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("bno") int bno,
                         @ModelAttribute("criteria") SearchCriteria criteria,
                         RedirectAttributes rttr) throws Exception {

        logger.info("================ remove() : called ================");
        logger.info("bno : " + bno);
        boardService.remove(bno);

        rttr.addAttribute("page", criteria.getPage());
        rttr.addAttribute("perPageNum", criteria.getPerPageNum());
        rttr.addAttribute("searchType", criteria.getSearchType());
        rttr.addAttribute("keyword", criteria.getKeyword());

        rttr.addFlashAttribute("msg", "DELETE");

        return "redirect:/board/paging/search/list";
    }

    // 게시글 목록 : 목록페이지 정보, 검색 정보 유지
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {

        logger.info("================ list() : called ================");
        logger.info(criteria.toString());

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);
        pageMaker.setTotalCount(boardService.listSearchCount(criteria));

        model.addAttribute("list", boardService.listSearchCriteria(criteria)); // 게시글 목록
        model.addAttribute("boardCount", boardService.listSearchCount(criteria)); // 게시글 갯수
        model.addAttribute("pageMaker", pageMaker);

        return "/board/paging_search/list";
    }
}
