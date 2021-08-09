package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

  @Autowired
  private GalleryDao galleryDao;

  /*** 파일 업로드 처리 ***/
  public void restore(GalleryVo galleryVo) {
    System.out.println("[사용 메소드: GalleryService.restore()]");

    MultipartFile file = galleryVo.getImgFile();

    // 원본 파일명
    String orgName = file.getOriginalFilename();

    // 확장자
    String exName = orgName.substring(orgName.lastIndexOf("."));

    // 저장 파일명
    String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

    // 파일 저장 위치
    String filePath = "E:\\javaStudy\\upload\\" + saveName;

    // 파일 사이즈
    long fileSize = file.getSize();

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

    // 2. 파일 정보를 DB에 저장
    GalleryVo galleryData = new GalleryVo(galleryVo.getUser_no(), galleryVo.getContent(), filePath, orgName, saveName, fileSize);

    galleryDao.restore(galleryData);
  }

  /*** 사진 리스트 ***/
  public List<GalleryVo> galleryList() {

    return galleryDao.galleryList();
  }

  /*** 사진 정보 가져오기 ***/
  public GalleryVo galleryOneList(int no) {

    return galleryDao.galleryOneList(no);
  }

  /*** 사진 삭제 ***/
  public int delete(int no) {

    int count = galleryDao.delete(no);

    return count;
  }

}
