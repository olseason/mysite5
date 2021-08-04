package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	// 필드
	@Autowired
	private BoardDao boardDao;

	// 생성자

	// 메소드 - GS

	// 메소드 - 일반

	/*** Read ***/
	public BoardVo getBoard(int no) {

		// 조회수
		boardDao.updateHit(no);

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
