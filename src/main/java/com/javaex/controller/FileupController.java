package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.javaex.service.FileupService;

@RequestMapping(value = "/fileup/")
@Controller
public class FileupController {

  @Autowired
  private FileupService fileupService;

  /*** 업로드 화면 ***/
  @RequestMapping(value = "form", method = {RequestMethod.GET, RequestMethod.POST})
  public String form() {
    System.out.println("[현재 위치: FileupController.form]");

    return "fileup/form";
  }

  /*** 파일 업로드 ***/
  @RequestMapping(value = "upload", method = {RequestMethod.GET, RequestMethod.POST})
  public String upload(@RequestParam("file") MultipartFile file, Model model) {
    System.out.println("[현재 위치: FileupController.upload]");

    String saveName = fileupService.restore(file);

    model.addAttribute("saveName", saveName);

    return "fileup/result";
  }

}