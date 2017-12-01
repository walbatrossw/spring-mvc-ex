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
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Inject
    private BoardService boardService;

    // 게시글 입력페이지
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerGET(BoardVO boardVO, Model model) throws Exception {

        logger.info("registerGET() : called... ");

    }

    // 게시글 입력처리
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPOST(BoardVO boardVO, Model model, RedirectAttributes rttr) throws Exception {

        logger.info("registerPOST() : called...");
        logger.info("Inserted BoardVO : " + boardVO);
        boardService.register(boardVO);
        //model.addAttribute("result", "success");
        rttr.addFlashAttribute("msg", "INSERT"); // redirect되는 시점에 한번만 사용되는 데이터 전송

        //return "/board/success";
        return "redirect:/board/listAll";
    }

    // 게시글 조회 1
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(@RequestParam("bno") int bno, Model model) throws Exception {

        logger.info("read() : called...");
        logger.info("Get boardVO : " + boardService.read(bno));
        model.addAttribute("boardVO",boardService.read(bno));

        return "/board/read";
    }

    // 게시글 조회 2 : 목록페이지 정보 유지
    @RequestMapping(value = "/readPage", method = RequestMethod.GET)
    public String readPage(@RequestParam("bno") int bno,
                           @ModelAttribute("criteria") Criteria criteria,
                           Model model) throws Exception {

        logger.info("readPage() : called...");
        logger.info("Get boardVO : " + boardService.read(bno));
        model.addAttribute("boardVO", boardService.read(bno));

        return "/board/read_page";
    }

    // 게시글 수정페이지
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyGET(@RequestParam("bno") int bno, Model model) throws Exception {

        logger.info("modifyGET() : called...");
        logger.info("Get boardVO : " + boardService.read(bno));
        model.addAttribute("boardVO", boardService.read(bno));

        return "/board/modify";
    }

    // 게시글 수정페이지 2 : 목록페이지 정보 유지
    @RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
    public String modifyPagingGET(@RequestParam("bno") int bno,
                                  @ModelAttribute("criteria") Criteria criteria,
                                  Model model) throws Exception {

        logger.info("modifyPagingGET() : called...");
        logger.info("Get boardVO : " + boardService.read(bno));
        model.addAttribute("boardVO", boardService.read(bno));

        return "/board/modify_page";
    }


    // 게시글 수정처리 1
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPOST(BoardVO boardVO, RedirectAttributes rttr) throws Exception {

        logger.info("modifyPOST() : called...");
        logger.info("Modified boardVO : " + boardVO);
        boardService.modify(boardVO);
        int bno = boardVO.getBno();
        rttr.addFlashAttribute("msg", "MODIFY");

        return "redirect:/board/read?bno="+bno;

    }

    // 게시글 수정처리 2 : 목록페이지 정보 유지
    @RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
    public String modifyPagingPOST(BoardVO boardVO, Criteria criteria, RedirectAttributes rttr) throws Exception {

        logger.info("modifyPagingPOST() : called...");
        logger.info("Modified boardVO : " + boardVO);
        boardService.modify(boardVO);
        rttr.addAttribute("page", criteria.getPage());
        rttr.addAttribute("perPageNum", criteria.getPerPageNum());
        rttr.addFlashAttribute("msg", "MODIFY");

        return "redirect:/board/listPage";

    }

    // 게시글 삭제처리 1
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {

        logger.info("remove() : called...");
        logger.info("bno : " + bno);
        boardService.delete(bno);
        rttr.addFlashAttribute("msg", "DELETE");

        return "redirect:/board/listAll";

    }

    // 게시글 삭제처리 2 : 목록페이지 정보 유지
    @RequestMapping(value = "/removePage", method = RequestMethod.POST)
    public String removePage(@RequestParam("bno") int bno,
                             Criteria criteria,
                             RedirectAttributes rttr) throws Exception {

        logger.info("removePage() : called...");
        logger.info("bno : " + bno);
        boardService.delete(bno);
        rttr.addAttribute("page", criteria.getPage());
        rttr.addAttribute("perPageNum", criteria.getPerPageNum());
        rttr.addFlashAttribute("msg", "DELETE");

        return "redirect:/board/listPage";

    }

    // 게시글 목록 : 전체
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public String listAll(Model model) throws Exception {

        logger.info("listAll() : called...");
        logger.info("GET list : " + boardService.listAll());
        model.addAttribute("list", boardService.listAll());

        return "board/list_all";
    }

    // 게시글 목록 : 페이징 1 : 하단 페이징 처리 X
    @RequestMapping(value = "/listCriteria", method = RequestMethod.GET)
    public String listAll(Criteria criteria, Model model) throws Exception {

        logger.info("Show list page with Criteria ...");
        model.addAttribute("list", boardService.listCriteria(criteria));

        return "/board/list_criteria";
    }

    // 게시글 목록 : 페이징 2 : 하단 페이징 처리 O
    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public String listPage(Criteria criteria, Model model) throws Exception {

        logger.info(criteria.toString());
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);
        //pageMaker.setTotalCount(131);
        pageMaker.setTotalCount(boardService.listCountCriteria(criteria));

        model.addAttribute("list", boardService.listCriteria(criteria));
        model.addAttribute("pageMaker", pageMaker);

        return "/board/list_page";
    }


}
