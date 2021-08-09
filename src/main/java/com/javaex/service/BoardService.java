package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

  // 필드
  @Autowired
  private BoardDao boardDao;


  /*** 페이징 연습용 ***/
  public Map<String, Object> getList2(int page, String keyword) {

    // 페이징 리스트 변수
    int listCount;
    int startNum;
    int endNum;

    // 페이징 버튼 변수
    int totalCount;
    int pageBtnCount;
    int startPageBtnNo;
    int endPageBtnNo;
    boolean next = false;
    boolean prev = false;

    ///////////////////////////////////////////////////////////////////////////////////////////
    ////////// 리스트 출력
    //////////////////////////////////////////////////////////////////////////////////////////

    // 한 페이지에 출력할 게시글 숫자
    listCount = 10;

    // 현재 페이지 계산(삼항연산자)
    page = (page > 0) ? page : (page = 1);


    // 시작 번호 계산
    startNum = (page - 1) * listCount + 1;

    // 끝 번호 계산
    endNum = (startNum + listCount) - 1;

    // 시작 번호, 끝 번호를 보내야 한다.
    List<BoardVo> boardList = boardDao.selectList2(startNum, endNum, keyword);

    ///////////////////////////////////////////////////////////////////////////////////////////
    ////////// 페이징 계산
    //////////////////////////////////////////////////////////////////////////////////////////

    // 전체 게시글 갯수
    totalCount = boardDao.selectTotalCnt(keyword);

    // 페이지당 버튼 갯수
    pageBtnCount = 5;

    // 마지막 버튼 번호
    endPageBtnNo = (int) Math.ceil((page / (double) pageBtnCount)) * pageBtnCount;

    // 시작 버튼 번호
    startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);

    // 다음 화살표 표현 유무
    if ((endPageBtnNo * listCount) < totalCount) {
      next = true;

    } else { // 다음 화살표가 없을 때 (마지막 페이지)
      endPageBtnNo = (int) Math.ceil(totalCount / (double) listCount);

    }

    // 이전 화살표 표현 유무
    if (startPageBtnNo != 1) {
      prev = true;

    }

    // Map으로 감싸 return
    Map<String, Object> listMap = new HashMap<String, Object>();
    listMap.put("boardList", boardList);
    listMap.put("prev", prev);
    listMap.put("startPageBtnNo", startPageBtnNo);
    listMap.put("endPageBtnNo", endPageBtnNo);
    listMap.put("next", next);

    // 시작 번호, 끝 번호를 보내야 한다.
    return listMap;
  }

  /*** Read ***/
  public BoardVo getBoard(int no) {

    // 조회수
    boardDao.updateHit(no);

    // 게시판 정보 가져오기
    return boardDao.selectBoard(no);
  }

  /*** 게시판 1명 정보 ***/
  public BoardVo getOneBoard(int no) {

    // 게시판 정보 가져오기
    return boardDao.selectBoard(no);
  }

  /*** 게시판 리스트(검색) ***/
  public List<BoardVo> selectList(String keyword) {

    return boardDao.selectList(keyword);
  }

  /*** 게시글 작성 ***/
  public void write(BoardVo boardVo) {

    boardDao.write(boardVo);

  }

  /*** 게시글 수정 ***/
  public void modify(BoardVo boardVo) {

    boardDao.modify(boardVo);
  }

  /*** 게시글 삭제 ***/
  public void delete(int no) {

    boardDao.delete(no);
  }
  
}
