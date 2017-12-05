package com.doubles.ex02.controller;

import com.doubles.ex02.domain.BoardVO;
import com.doubles.ex02.domain.Criteria;
import com.doubles.ex02.domain.PageMaker;
import com.doubles.ex02.domain.SearchCriteria;
import com.doubles.ex02.service.BoardService;
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
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Inject
    private BoardService boardService;

    // 입력페이지
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGET() {

        logger.info("registerGET() : called...");

        return "board/register";
    }

    // 입력처리
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPOST(@ModelAttribute("boardVO") BoardVO boardVO, RedirectAttributes rttr) throws Exception {

        logger.info("registerPOST() : called...");
        boardService.register(boardVO);
        rttr.addFlashAttribute("msg", "INSERTED");

        return "redirect:/board/list";
    }

    // 목록 : 목록페이지 정보 + 검색옵션, 키워드 정보 유지
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {

        logger.info("list() : called...");
        logger.info(criteria.toString());

        List<BoardVO> list = boardService.list(criteria);
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);
        pageMaker.setTotalCount(boardService.listCount(criteria));

        model.addAttribute("list", list);
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("totalCount", boardService.listCount(criteria));

        return "board/list";
    }

    // 조회 : 목록페이지 정보 + 검색옵션, 키워드 정보 유지
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(@RequestParam Integer bno,
                       @ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {

        logger.info("read() : called...");
        model.addAttribute("boardVO", boardService.read(bno));

        return "board/read";
    }

    // 수정 페이지 : 목록페이지 정보 + 검색옵션, 키워드 정보 유지
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyGET(@RequestParam("bno") Integer bno,
                            @ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {

        logger.info("modifyGET() : called...");
        model.addAttribute("boardVO", boardService.read(bno));

        return "board/modify";
    }

    // 수정 처리 : 목록페이지 정보 + 검색옵션, 키워드 정보 유지
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPOST(@ModelAttribute("boardVO") BoardVO boardVO,
                             @ModelAttribute("criteria") SearchCriteria criteria,
                             RedirectAttributes rttr) throws Exception {

        logger.info("modifyPOST() : called...");
        boardService.modify(boardVO);

        rttr.addAttribute("bno", boardVO.getBno());
        rttr.addAttribute("page", criteria.getPage());
        rttr.addAttribute("perPageNum", criteria.getPerPageNum());
        rttr.addAttribute("searchType", criteria.getSearchType());
        rttr.addAttribute("keyword", criteria.getKeyword());

        rttr.addFlashAttribute("msg", "MODIFIED");

        return "redirect:/board/read";
    }

    // 삭제 : 목록페이지 정보 + 검색옵션, 키워드 정보 유지
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("bno") Integer bno,
                         @ModelAttribute("criteria") SearchCriteria criteria, RedirectAttributes rttr) throws Exception {

        logger.info("remove() : called...");
        boardService.remove(bno);
        rttr.addAttribute("page", criteria.getPage());
        rttr.addAttribute("perPageNum", criteria.getPerPageNum());
        rttr.addAttribute("searchType", criteria.getSearchType());
        rttr.addAttribute("keyword", criteria.getKeyword());
        rttr.addFlashAttribute("msg", "REMOVED");

        return "redirect:/board/list";
    }
}
