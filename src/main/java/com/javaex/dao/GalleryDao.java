package com.javaex.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

  @Autowired
  private SqlSession sqlSession;

  /*** 파일 업로드 처리 ***/
  public void restore(GalleryVo galleryVo) {
    System.out.println("[사용 메소드: GalleryDao.restore()]");

    sqlSession.insert("gallery.insert", galleryVo);
  }

  /*** 사진 리스트 ***/
  public List<GalleryVo> galleryList() {

    return sqlSession.selectList("gallery.select");
  }

  /*** 사진 정보 가져오기 ***/
  public GalleryVo galleryOneList(int no) {

    return sqlSession.selectOne("gallery.selectOnt", no);
  }

  /*** 사진 삭제 ***/
  public int delete(int no) {

    int count = sqlSession.delete("gallery.delete", no);

    return count;
  }

}