package com.doubles.ex03.controller;

import com.doubles.ex03.domain.BoardVO;
import com.doubles.ex03.domain.Criteria;
import com.doubles.ex03.domain.PageMaker;
import com.doubles.ex03.domain.SearchCriteria;
import com.doubles.ex03.service.BoardService;
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
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Inject
    private BoardService boardService;

    // 입력 페이지
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGET() throws Exception {

        return "board/register";
    }

    // 입력 처리
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPOST(@ModelAttribute("boardVO") BoardVO boardVO,
                               RedirectAttributes redirectAttributes) throws Exception {

        boardService.register(boardVO);
        redirectAttributes.addFlashAttribute("msg", "INSERTED");

        return "redirect:/board/list";
    }

    // 조회
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(@RequestParam("bno") Integer bno,
                       @ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {

        model.addAttribute("boardVO", boardService.read(bno));

        return "board/read";
    }

    // 수정 페이지
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyGET(@RequestParam("bno") Integer bno,
                            @ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {

        model.addAttribute("boardVO", boardService.read(bno));

        return "board/modify";
    }

    // 수정 처리
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPOST(@ModelAttribute("BoardVO") BoardVO boardVO,
                             SearchCriteria criteria, RedirectAttributes redirectAttributes) throws Exception {

        boardService.modify(boardVO);
        redirectAttributes.addFlashAttribute("msg", "MODIFIED");
        redirectAttributes.addAttribute("bno", boardVO.getBno());
        redirectAttributes.addAttribute("page", criteria.getPage());
        redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
        redirectAttributes.addAttribute("searchType", criteria.getSearchType());
        redirectAttributes.addAttribute("keyword", criteria.getKeyword());

        return "redirect:/board/read";
    }

    // 삭제 처리
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("bno") Integer bno,
                         SearchCriteria criteria, RedirectAttributes redirectAttributes) throws Exception {

        boardService.remove(bno);
        redirectAttributes.addAttribute("page", criteria.getPage());
        redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
        redirectAttributes.addAttribute("searchType", criteria.getSearchType());
        redirectAttributes.addAttribute("keyword", criteria.getKeyword());
        redirectAttributes.addFlashAttribute("msg", "REMOVED");

        return "redirect:/board/list";
    }

    // 목록 : 기본 + 페이징 + 검색
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {

        model.addAttribute("list", boardService.list(criteria));
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);
        pageMaker.setTotalCount(boardService.listCount(criteria));
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("totalCount", boardService.listCount(criteria));

        return "board/list";
    }
}
