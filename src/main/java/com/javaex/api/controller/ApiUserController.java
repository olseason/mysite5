package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.javaex.service.UserService;

@RequestMapping(value = "/api/user/")
@Controller
public class ApiUserController {

  @Autowired
  UserService userService;

  @ResponseBody
  @RequestMapping(value = "idcheck", method = {RequestMethod.GET, RequestMethod.POST})
  public boolean idcheck(@RequestParam("id") String id) {
    System.out.println("[현재 위치: ApiUserController.idcheck]");

    return userService.idCheck(id);
  }

}