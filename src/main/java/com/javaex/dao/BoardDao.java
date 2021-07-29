package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	//조회수 올리기
	public int updateHit(int no) {
		System.out.println("[BoardDao.updateHit()]");
		
		return sqlSession.update("board.updateHit", no);
	}
	
	//게시판1개 정보 가져오기
	public BoardVo selectBoard(int no) {
		System.out.println("[BoardDao.selectBoard()]");
		
		return sqlSession.selectOne("board.selectBoard", no);
	}
	
	
	
	
}
