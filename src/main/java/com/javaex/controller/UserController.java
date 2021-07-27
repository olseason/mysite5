package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {
	// 필드
	@Autowired
	private UserService userService;

	// 생성자

	// 메소드 - GS

	// 메소드 - 일반

	/*** 로그인 폼 ***/
	@RequestMapping(value = "/user/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("[현재 위치: UserController.loginForm]");

		return "user/loginForm";
	}

	/*** 로그인 ***/
	@RequestMapping(value = "/user/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[현재 위치: UserController.login]");

		UserVo authUser = userService.getUserLogin(userVo);

		if (authUser != null) { // 로그인 성공(authUser 있을 때)
			System.out.println("[" + authUser.getName() + "] 님이 로그인 하셨습니다.");
			session.setAttribute("authUser", authUser);

			return "redirect:/main";

		} else { // 로그인 실패(authUser 없을 때)
			System.out.println("로그인 실패");

			return "redirect:/user/loginForm?result=fail";

		}
	}

	/*** 회원가입 폼 ***/
	@RequestMapping(value = "/user/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[현재 위치: UserController.joinForm]");

		return "user/joinForm";
	}

	/*** 회원가입 ***/
	@RequestMapping(value = "/user/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {

		userService.insert(userVo);

		return "user/joinOk";
	}

	/*** 회원수정 폼 ***/
	@RequestMapping(value = "/user/modifyForm/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(Model model, HttpSession session) {

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser != null) {
			UserVo userVo = userService.getUserModify(authUser);
			model.addAttribute("userVo", userVo);

			return "user/modifyForm";
		} else {
			return "redirect:/main";
		}
	}

	/*** 회원수정 폼 2 ***/
	@RequestMapping(value = "/user/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm2() {

		return "redirect:/main";

	}

	/*** 회원수정 ***/
	@RequestMapping(value = "/user/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {

		userService.modify(userVo);

		((UserVo) session.getAttribute("authUser")).setName(userVo.getName());

		return "redirect:/main";
	}

	/*** 로그아웃 ***/
	@RequestMapping(value = "/user/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {

		session.removeAttribute("authUser");
		session.invalidate();

		System.out.println("[");

		return "redirect:/main";
	}

}