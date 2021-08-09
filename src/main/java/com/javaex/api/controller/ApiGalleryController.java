package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@RequestMapping(value = "/api/gallery/")
@Controller
public class ApiGalleryController {

  // 필드
  @Autowired
  private GalleryService galleryService;

  /*** ajax 정보 가져오기 ***/
  @ResponseBody
  @RequestMapping(value = "src", method = {RequestMethod.GET, RequestMethod.POST})
  public GalleryVo src(Model model, @RequestParam("no") int no) {
    System.out.println("[현재 위치: ApiGalleryController.src]");

    GalleryVo galleryVo = galleryService.galleryOneList(no);
    model.addAttribute("galleryOne", galleryVo);

    return galleryVo;
  }

  /*** ajax 사진 삭제 ***/
  @ResponseBody
  @RequestMapping(value = "delete", method = {RequestMethod.GET, RequestMethod.POST})
  public int delete(@RequestParam("no") int no) {
    System.out.println("[현재 위치: ApiGalleryController.delete]");

    int count = galleryService.delete(no);


    return count;
  }



}