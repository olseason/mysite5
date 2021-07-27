package com.javaex.vo;

public class UserVo {
	// 필드
	private int no;
	private String name;
	private String id;
	private String password;
	private String gender;

	// 생성자
	public UserVo() {

	}

	public UserVo(String id, String password) {
		this.id = id;
		this.password = password;
	}

	// 메소드 - GS
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	// 메소드 - 일반
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", id=" + id + ", password=" + password + ", gender=" + gender
				+ "]";
	}

}