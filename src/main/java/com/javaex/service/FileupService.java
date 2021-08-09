package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileupService {

  /*** 파일 업로드 처리 ***/
  public String restore(MultipartFile file) {

    // 원본 파일명
    String orgName = file.getOriginalFilename();
    System.out.println("orgName: " + orgName);

    // 확장자
    String exName = orgName.substring(orgName.lastIndexOf("."));
    System.out.println("exName: " + exName);

    // 저장 파일명
    String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
    System.out.println("saveName: " + saveName);

    // 파일 저장 위치
    String filePath = "E:\\javaStudy\\upload\\" + saveName;
    System.out.println("filePath: " + filePath);

    // 파일 사이즈
    long fileSize = file.getSize();
    System.out.println("fileSize: " + fileSize + " byte");

    // 1. 파일을 서버의 하드디스크에 저장
    try {
      byte[] fileData = file.getBytes();
      OutputStream out = new FileOutputStream(filePath);
      BufferedOutputStream bos = new BufferedOutputStream(out);

      bos.write(fileData);
      bos.close();

    } catch (IOException e) {

      e.printStackTrace();
    }

    // 2. 파일 정보를 DB에 저장 -- 과제

    return saveName;

  }
}