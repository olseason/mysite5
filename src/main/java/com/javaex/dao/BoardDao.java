package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

  // 필드
  @Autowired
  private SqlSession sqlSession;

  /*** 페이징 연습용(전체 게시물 갯수 구하기) ***/
  public int selectTotalCnt(String keyword) {
    System.out.println("[사용 메소드: BoardDao.selectTotalCnt()]");

    return sqlSession.selectOne("board.selectTotalCnt", keyword);
  }


  /*** 페이징 연습용(리스트) ***/
  public List<BoardVo> selectList2(int startNum, int endNum, String keyword) {
    System.out.println("[사용 메소드: BoardDao.selectList2()]");

    Map<String, Object> pageMap = new HashMap<String, Object>();
    pageMap.put("startNum", startNum);
    pageMap.put("endNum", endNum);
    pageMap.put("keyword", keyword);

    return sqlSession.selectList("board.selectList2", pageMap);
  }


  /*** 조회수 ***/
  public int updateHit(int no) {
    System.out.println("[사용 메소드: BoardDao.updateHit()]");

    int count = sqlSession.update("board.updateHit", no);

    return count;
  }

  /*** 게시판 정보 가져오기 ***/
  public BoardVo selectBoard(int no) {
    System.out.println("[사용 메소드: BoardDao.selectBoard()]");

    return sqlSession.selectOne("board.selectBoard", no);
  }

  /*** 게시판 리스트(검색) ***/
  public List<BoardVo> selectList(String keyword) {
    System.out.println("[사용 메소드: BoardDao.selectList(" + keyword + ")]");

    Map<String, Object> keywordMap = new HashMap<String, Object>();
    keywordMap.put("keyword", keyword);

    return sqlSession.selectList("board.selectList", keywordMap);
  }

  /*** 게시글 작성 ***/
  public void write(BoardVo boardVo) {
    System.out.println("[사용 메소드: BoardDao.write()]");

    sqlSession.insert("board.write", boardVo);
  }

  /*** 게시글 수정 ***/
  public void modify(BoardVo boardVo) {
    System.out.println("[사용 메소드: BoardDao.modify()]");

    sqlSession.update("board.modify", boardVo);
  }

  /*** 게시글 삭제 ***/
  public void delete(int no) {
    System.out.println("[사용 메소드: BoardDao.modify()]");

    sqlSession.delete("board.delete", no);
  }
}