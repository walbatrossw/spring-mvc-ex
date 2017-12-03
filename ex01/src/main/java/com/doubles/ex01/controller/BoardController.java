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

@Controller
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Inject
    private BoardService boardService;

    // 게시글 입력페이지 : 일반
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerGET() {

        logger.info("registerGET() : called... ");

    }

    // 게시글 입력처리 : 일반
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPOST(BoardVO boardVO, Model model, RedirectAttributes rttr) throws Exception {

        logger.info("registerPOST() : called...");
        logger.info("Inserted BoardVO : " + boardVO);
        boardService.register(boardVO);
        //model.addAttribute("result", "success");
        rttr.addFlashAttribute("msg", "INSERT"); // redirect되는 시점에 한번만 사용되는 데이터 전송

        //return "/board/success";
        return "redirect:/board/list";
    }

    // 게시글 조회 : 일반
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(@RequestParam("bno") int bno, Model model) throws Exception {

        logger.info("read() : called...");
        logger.info("Get boardVO : " + boardService.read(bno));
        model.addAttribute("boardVO",boardService.read(bno));

        return "/board/general/read";
    }

    // 게시글 수정페이지 : 일반
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyGET(@RequestParam("bno") int bno, Model model) throws Exception {

        logger.info("modifyGET() : called...");
        logger.info("Get boardVO : " + boardService.read(bno));
        model.addAttribute("boardVO", boardService.read(bno));

        return "/board/general/modify";
    }

    // 게시글 수정처리 : 일반
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPOST(BoardVO boardVO, RedirectAttributes rttr) throws Exception {

        logger.info("modifyPOST() : called...");
        logger.info("Modified boardVO : " + boardVO);
        boardService.modify(boardVO);
        int bno = boardVO.getBno();
        rttr.addFlashAttribute("msg", "MODIFY");

        return "redirect:/board/read?bno="+bno;

    }

    // 게시글 삭제처리 : 일반
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {

        logger.info("remove() : called...");
        logger.info("bno : " + bno);
        boardService.remove(bno);
        rttr.addFlashAttribute("msg", "DELETE");

        return "redirect:/board/list";

    }

    // 게시글 목록 : 일반
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception {

        logger.info("list() : called...");
        logger.info("GET list : " + boardService.listAll());
        model.addAttribute("list", boardService.listAll());

        return "/board/general/list";
    }

}
