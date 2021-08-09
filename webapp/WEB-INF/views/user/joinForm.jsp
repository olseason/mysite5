<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
  <div id="wrap">

    <!-- Header -->
    <c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

    <div id="container" class="clearfix">
      <div id="aside">
        <h2>회원</h2>
        <ul>
          <li>회원정보</li>
          <li>로그인</li>
          <li>회원가입</li>
        </ul>
      </div>
      <!-- //aside -->

      <div id="content">

        <div id="content-head">
          <h3>회원가입</h3>
          <div id="location">
            <ul>
              <li>홈</li>
              <li>회원</li>
              <li class="last">회원가입</li>
            </ul>
          </div>
          <div class="clear"></div>
        </div>
        <!-- //content-head -->

        <div id="user">
          <div>
            <form id="joinForm" action="${pageContext.request.contextPath}/user/join" method="get">

              <!-- 아이디 -->
              <div class="form-group">
                <label class="form-text" for="input-uid">아이디</label>
                <input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
                <button type="button" id="idCheck">중복체크</button>
              </div>
              <p align="center" id="idChk"></p>

              <!-- 비밀번호 -->
              <div class="form-group">
                <label class="form-text" for="input-pass">패스워드</label>
                <input type="text" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요">
              </div>

              <!-- 이메일 -->
              <div class="form-group">
                <label class="form-text" for="input-name">이름</label>
                <input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
              </div>

              <!-- //나이 -->
              <div class="form-group">
                <span class="form-text">성별</span>

                <label for="rdo-male">남</label>
                <input type="radio" id="rdo-male" name="gender" value="male">

                <label for="rdo-female">여</label>
                <input type="radio" id="rdo-female" name="gender" value="female">

              </div>

              <!-- 약관동의 -->
              <div class="form-group">
                <span class="form-text">약관동의</span>

                <input type="checkbox" id="chk-agree" value="" name="">
                <label for="chk-agree">서비스 약관에 동의합니다.</label>
              </div>

              <!-- 버튼영역 -->
              <div class="button-area">
                <button type="submit" id="btn-submit">회원가입</button>
              </div>

            </form>
          </div>
          <!-- //joinForm -->
        </div>
        <!-- //user -->
      </div>
      <!-- //content  -->
    </div>
    <!-- //container  -->

    <!-- footer -->
    <c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

  </div>
  <!-- //wrap -->

</body>

<script type="text/javascript">
	// 회원가입 버튼을 눌렀을 때(데이터를 json 형식으로 보내기 [form 사용 X])
	$("#btn-submit").on("click", function() {
		event.preventDefault();
		// 데이터 묶기
		var userVo = {
			id : $("[name=id]").val(),
			password : $("[name=password]").val(),
			name : $("[name=name]").val(),
			gender : $("[name=gender]").val()
		};
		$.ajax({
			// 컨트롤러에서 대기중인 URL 주소이다.
			url : "${pageContext.request.contextPath}/user/join2",
			// HTTP method type(GET, POST) 형식이다.
			type : "post",
			// Json 형태의 데이터로 보낸다.
			contentType : "application/json",
			// Json 형식의 데이터를 받는다.
			dataType : "json",
			data : JSON.stringify(userVo),
			// 성공일 경우 success로 들어오며, 'result'는 응답받은 데이터이다.
			success : function(result) {
				/*성공시 처리해야될 코드 작성*/
			},
			// 실패할경우 error로 들어온다.
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	/*
	// 회원가입 버튼을 눌렀을 때(값이 없거나 몇 글자 이상일 때)
	$("#joinForm").on("submit", function() {
		console.log("서브밋");
		// 비밀번호 8글자 이상 체크
		if ($("#input-pass").val().length < 8) {
			alert("비밀번호를 8글자 이상 입력해 주세요.");
			return false;
		}
		// 이름 체크
		if ($("#input-name").val().length < 1) {
			alert("이름을 입력해 주세요.");
			return false;
		}
		// 약관 동의
		if ($("#chk-agree").is(":checked") === false) {
			alert("약관에 동의해주세요.");
			return false;
		}
		return true;
	});
	 */
	// id 중복체크
	$("#idCheck").on("click", function() {
		$.ajax({
			// 컨트롤러에서 대기중인 URL 주소이다.
			url : "${pageContext.request.contextPath}/api/user/idcheck",
			// HTTP method type(GET, POST) 형식이다.
			type : "get",
			// Json 형태의 데이터로 보낸다.
			contentType : "application/json",
			// Json 형식의 데이터를 받는다.
			dataType : "json",
			data : {
				id : $("[name=id]").val()
			},
			// 성공일 경우 success로 들어오며, 'result'는 응답받은 데이터이다.
			success : function(idcheck) {
				/*성공시 처리해야될 코드 작성*/
				if (idcheck === true) {
					$("#idChk").html("사용 가능한 아이디 입니다.");
				} else if (idcheck === false) {
					$("#idChk").html("중복된 아이디 입니다.");
				} else if (idcheck === "") {
					$("#idChk").html("관리자에게 문의");
				}
			},
			// 실패할경우 error로 들어온다.
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
</script>

</html>