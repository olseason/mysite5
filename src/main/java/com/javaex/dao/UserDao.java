package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	// 필드
	@Autowired
	private SqlSession sqlSession;

	// 생성자

	// 메소드 - GS

	// 메소드 - 일반

	/*** 유저 정보 조회(Login) ***/
	public UserVo getUserLogin(UserVo userVo) {
		System.out.println("[현재 사용 메소드: UserDao.selectUser()]");

		return sqlSession.selectOne("user.selectUserLogin", userVo);
	}

	/*** 회원가입 ***/
	public void insert(UserVo userVo) {
		System.out.println("[현재 사용 메소드: UserDao.insert()]");

		System.out.println("[" + userVo.getName() + "] 님이 회원가입 하셨습니다.");

		sqlSession.insert("user.insert", userVo);
	}

	/*** 회원 정보 수정 ***/
	public void modify(UserVo userVo) {

		System.out.println("[현재 사용 메소드: UserDao.insert()]");

		System.out.println("[" + userVo.getNo() + "번] 님이 정보를 수정 하셨습니다.");

		sqlSession.update("user.update", userVo);
	}

	/*** 유저 정보 조회(Modify) ***/
	public UserVo getUserModify(UserVo userVo) {
		System.out.println("[현재 사용 메소드: UserDao.getUserModify()]");

		return sqlSession.selectOne("user.selectUserModify", userVo);
	}

}
