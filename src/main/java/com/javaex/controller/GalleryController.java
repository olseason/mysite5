package com.javaex.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@RequestMapping(value = "/gallery/")
@Controller
public class GalleryController {

  @Autowired
  private GalleryService galleryService;

  /**** 갤러리 리스트 ****/
  @RequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
  public String list(Model model, HttpSession session) {
    System.out.println("[현재 위치: GalleryController.list]");

    session.getAttribute("authUser");

    model.addAttribute("galleryList", galleryService.galleryList());

    return "/gallery/list";
  }

  /**** 사진 등록 ****/
  @RequestMapping(value = "upload", method = {RequestMethod.GET, RequestMethod.POST})
  public String upload(HttpSession session, @ModelAttribute GalleryVo galleryVo) {
    System.out.println("[현재 위치: GalleryController.upload]");

    session.getAttribute("authUser");

    galleryService.restore(galleryVo);

    return "redirect:/gallery/list";
  }

}
