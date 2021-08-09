<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
  <div id="wrap">


    <!-- Header -->
    <c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

    <div id="container" class="clearfix">

      <!-- aside -->
      <c:import url="/WEB-INF/views/includes/asideGuestBook.jsp"></c:import>

      <div id="content">

        <div id="content-head" class="clearfix">
          <h3>ajax방명록</h3>
          <div id="location">
            <ul>
              <li>홈</li>
              <li>방명록</li>
              <li class="last">ajax방명록</li>
            </ul>
          </div>
        </div>
        <!-- //content-head -->

        <div id="guestbook">
          <form action="" method="get">
            <table id="guestAdd">
              <colgroup>
                <col style="width: 70px;">
                <col>
                <col style="width: 70px;">
                <col>
              </colgroup>
              <tbody>
                <tr>
                  <th><label class="form-text" for="input-uname">이름</label></th>
                  <td><input id="input-uname" type="text" name="name" value=""></td>
                  <th><label class="form-text" for="input-pass">패스워드</label></th>
                  <td><input id="input-pass" type="password" name="password" value=""></td>
                </tr>
                <tr>
                  <td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
                </tr>
                <tr class="button-area">
                  <td colspan="4" class="text-center"><button type="submit" id="btnSubmit">등록</button></td>
                </tr>
              </tbody>
            </table>
          </form>

          <div id="listArea"></div>

        </div>
        <!-- //guestbook -->

      </div>
      <!-- //content  -->
    </div>
    <!-- //container  -->

    <!-- footer -->
    <c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

  </div>
  <!-- //wrap -->

  <!-- 모달창 -->
  <div id="delModal" class="modal fade">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
          <h4 class="modal-title">방명록 삭제</h4>
        </div>
        <div class="modal-body">

          <label for="modalPassword">비밀번호</label>
          <input id="modalPassword" type="password" name="password" value="">
          <input type="text" name="no" value="">


        </div>
        <div class="modal-footer">
          <button id="modalBtnDel" type="button" class="btn btn-primary">삭제</button>
        </div>
      </div>
      <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
  </div>
  <!-- /.modal -->





</body>
<script type="text/javascript">
	// 페이지가 로딩되기 전일 때(이벤트)
	$(document).ready(function() { // 문서가 준비되면 매개변수로 넣은 함수를 실행하라는 의미
		// ajax 요청
		fetchlist();
	});
	// 등록 버튼 클릭할 때(이벤트)
	$("#btnSubmit").on("click", function() {
		event.preventDefault(); // href를 통해 이동하거나, 창이 새로고침하여 실행되는 걸 막아준다.
		<!--
		/*** 방법 1 ***/
		// name 값 읽어오기
		var name = $("[name='name']").val();
		console.log(name);
		// password 값 읽어오기
		var password = $("[name='password']").val();
		console.log(password);
		// content 값 읽어오기
		var content = $("[name='content']").val();
		console.log(content);
		// 읽은 값을 객체에 넣기
		var guestbookVo = {
			name : name,
			password : password,
			content : content
		};
		-->
		/*** 방법 2 ***/
		// 읽은 값을 객체에 넣기
		var guestbookVo = {
			name : $("[name='name']").val(),
			password : $("[name='password']").val(),
			content : $("[name='content']").val()
		};
		$.ajax({
			url : "${pageContext.request.contextPath }/api/guestbook/write",
			type : "get",
			// contentType : "application/json",
			/* 	*** 방법 1 ***
			// 사용자가 입력한 데이터를 객체에 넣음
			 data : {
			 name : name,
			 password : password,
			 content : content,
			 }, */
			/*** 방법 2 ***/
			// 사용자가 입력한 데이터를 객체에 넣음
			data : guestbookVo,
			// dataType : "json",
			success : function(guestbookVo) {
				/*성공시 처리해야될 코드 작성*/
				render(guestbookVo, "up");
				// 입력 폼 초기화
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	// 리스트에서 삭제 버튼을 클릭했을 때
	$("#listArea").on("click", ".btnDel", function() {
		// 모달창에 no 값 전송
		$("[name=no]").val($(this).data("no"));
		// 비밀번호 초기화
		$("#modalPassword").val("");
		// 모달창 보이기
		$("#delModal").modal();
	});
	// 모달창에서 삭제 버튼을 클릭했을 때
	$("#modalBtnDel").on("click", function() {
		console.log("모달창 삭제 버튼");
		var no = $("[name='no']").val();
		var guestbookVo = {
			no : no,
			password : $("#modalPassword").val()
		};
		console.log(guestbookVo);
		// 서버에 삭제 요청(no, password 전달)
		$.ajax({
			url : "${pageContext.request.contextPath }/api/guestbook/remove",
			type : "post",
			// contentType : "application/json",
			data : guestbookVo,
			// dataType : "json",
			success : function(count) {
				/*성공시 처리해야될 코드 작성*/
				if (count === 1) {
					// 모달창 닫기
					$("#delModal").modal("hide");
					// 방명록 지우기
					$("#t-" + no).remove();
				} else {
					// 모달창 닫기
					$("#delModal").modal("hide");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	// 리스트 가져오기
	function fetchlist() {
		$.ajax({
			url : "${pageContext.request.contextPath}/api/guestbook/list",
			type : "post",
			/*
			contentType : "application/json",
			data : {
				name : "홍길동"
			},
			dataType : "json",
			 */
			success : function(guestbookList) {
				// 성공 시 처리해야될 코드 작성
				console.log(guestbookList);
				// 화면에 그리기
				for (var i = 0; i < guestbookList.length; i++) {
					render(guestbookList[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	};
	// 방명록 1개씩 가져와 출력
	function render(guestbookVo, type) {
		var str = '';
		str += '<table id="t-' + guestbookVo.no + '" class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>' + guestbookVo.no + '</td>';
		str += '		<td>' + guestbookVo.name + '</td>';
		str += '		<td>' + guestbookVo.reg_date + '</td>';
		// data-no=" 값 " ==> no이라는 data에 값을 넣은 것.
		str += '		<td><div class="btnDel" data-no="'+ guestbookVo.no +'">[삭제]</div></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan="4">' + guestbookVo.content + '</td>';
		str += '	</tr>';
		str += '</table>';
		if (type === "up") {
			$("#listArea").prepend(str);
		} else if (type === "down") {
			$("#listArea").append(str);
		} else {
			console.log("방향을 지정해주세요.")
		}
	};
</script>

</html>