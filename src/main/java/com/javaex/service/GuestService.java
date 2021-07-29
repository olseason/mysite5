package com.javaex.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestService {

	// 필드
	@Autowired
	private GuestbookDao guestbookDao;

	/**** 리스트 출력 ****/
	public List<GuestbookVo> getGuestbookList() {

		return guestbookDao.getGuestbookList();
	}

	/**** 방명록 등록 ****/
	public void insert(GuestbookVo guestbookVo) {

		guestbookDao.insert(guestbookVo);
	}

	/**** 방명록 삭제 ****/
	public void delete(GuestbookVo guestbookVo) {

		guestbookDao.delete(guestbookVo);

	}
}