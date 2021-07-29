package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestController {

	// 필드
	@Autowired
	private GuestService guestService;

	// 생성자

	// 메소드 - GS

	// 메소드 - 일반

	/*** addList ***/
	@RequestMapping(value = "/guestbook/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model) {

		// 리스트 가져오기
		List<GuestbookVo> gList = guestService.getGuestbookList();

		// model에 담기
		model.addAttribute("gList", gList);

		// addList.jsp 포워드
		return "guestbook/addList";
	}

	/*** add ***/
	@RequestMapping(value = "/guestbook/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestbookVo guestbookVo) {

		// insert() 메소드 사용
		guestService.insert(guestbookVo);

		// 리다이렉트
		return "redirect:/guestbook/addList";
	}

	/*** deleteForm ***/
	@RequestMapping(value = "/guestbook/deleteForm/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm(@PathVariable("no") int no, Model model) {

		// model에 담기
		model.addAttribute("no", no);

		// deleteForm.jsp 포워드
		return "guestbook/deleteForm";
	}

	/*** delete ***/
	@RequestMapping(value = "/guestbook/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {

		// delete() 메소드 사용
		guestService.delete(guestbookVo);

		// 리다이렉트
		return "redirect:/guestbook/addList";
	}

}