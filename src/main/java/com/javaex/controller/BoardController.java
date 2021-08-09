package com.javaex.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

  // 필드
  @Autowired
  private BoardService boardService;

  // 생성자

  // 메소드 - GS

  // 메소드 - 일반

  /*** 페이징 연습 리스트 ***/
  @RequestMapping(value = "/list2", method = {RequestMethod.GET, RequestMethod.POST})
  public String list2(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
    System.out.println("[현재 위치: BoardController.list2]");

    Map<String, Object> listMap = boardService.getList2(page, keyword);

    model.addAttribute("listMap", listMap);

    return "board/list2";
  }


  /*** 글 읽기 ***/
  @RequestMapping(value = "/read/{no}", method = {RequestMethod.GET, RequestMethod.POST})
  public String read(@PathVariable("no") int no, Model model) {
    System.out.println("[현재 위치: BoardController.read]");

    BoardVo boardVo = boardService.getBoard(no);
    model.addAttribute("boardVo", boardVo);

    return "board/read";
  }

  /*** 게시판 리스트 ***/
  @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
  public String list(Model model, @RequestParam(required = false, defaultValue = "") String keyword) {
    System.out.println("[현재 위치: BoardController.list]");

    List<BoardVo> bList = boardService.selectList(keyword);
    model.addAttribute("bList", bList);

    return "board/list";
  }

  /*** 게시글 작성 폼 ***/
  @RequestMapping(value = "/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
  public String writeForm(HttpSession session) {
    System.out.println("[현재 위치: BoardController.writeForm]");

    UserVo authUser = ((UserVo) session.getAttribute("authUser"));

    if (authUser != null) {
      return "board/writeForm";
    } else {
      return "redirect:/user/loginForm";
    }

  }

  /*** 게시글 작성 ***/
  @RequestMapping(value = "/write", method = {RequestMethod.GET, RequestMethod.POST})
  public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
    System.out.println("[현재 위치: BoardController.write]");

    UserVo authUser = ((UserVo) session.getAttribute("authUser"));

    if (authUser != null) {
      boardService.write(boardVo);

      return "redirect:/board/list";
    } else {

      return "redirect:/user/loginForm";
    }

  }

  /*** 게시글 수정 폼 ***/
  @RequestMapping(value = "/modifyForm/{no}", method = {RequestMethod.GET, RequestMethod.POST})
  public String modifyForm(@PathVariable("no") int no, Model model, HttpSession session) {

    UserVo authUser = ((UserVo) session.getAttribute("authUser"));
    BoardVo boardVo = boardService.getOneBoard(no);

    if (authUser == null) {
      return "redirect:/main";
    } else {

      if (authUser.getNo() == boardVo.getUser_no()) {
        model.addAttribute("boardVo", boardVo);

        return "board/modifyForm";

      } else {
        return "redirect:/board/list";

      }
    }

  }

  /*** 게시글 수정 ***/
  @RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
  public String modify(@ModelAttribute BoardVo boardVo) {

    boardService.modify(boardVo);

    return "redirect:/board/list";
  }

  /*** 게시글 삭제 ***/
  @RequestMapping(value = "/delete/{no}", method = {RequestMethod.GET, RequestMethod.POST})
  public String delete(@PathVariable("no") int no, HttpSession session) {

    UserVo authUser = ((UserVo) session.getAttribute("authUser"));
    BoardVo boardVo = boardService.getOneBoard(no);

    if (authUser == null) {
      return "redirect:/main";
    } else {

      if (authUser.getNo() == boardVo.getUser_no()) {
        boardService.delete(no);

      }
      return "redirect:/board/list";

    }

  }

}