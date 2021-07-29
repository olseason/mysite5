package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	// 필드
	@Autowired
	private SqlSession sqlSession;

	/**** 리스트 출력 ****/
	public List<GuestbookVo> getGuestbookList() {

		List<GuestbookVo> gList = sqlSession.selectList("guestbook.getList");

		return gList;
	}

	/**** 방명록 등록 ****/
	public void insert(GuestbookVo guestbookVo) {

		sqlSession.insert("guestbook.insert", guestbookVo);

		System.out.println("[" + guestbookVo.getName() + "님이 등록되었습니다.]");

	}

	/**** 방명록 삭제 ****/
	public int delete(GuestbookVo guestbookVo) {

		int count = -1;

		count = sqlSession.delete("guestbook.delete", guestbookVo);

		if (count == 1) {
			System.out.println("[" + guestbookVo.getNo() + "번 글이 삭제되었습니다.]");

		} else if (count == 0) {
			System.out.println("비밀번호가 틀렸습니다.");
		}

		return count;

	}

}