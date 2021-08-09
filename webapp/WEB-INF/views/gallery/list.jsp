<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
  <div id="wrap">

    <!-- 해더 네비 -->
    <c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
    <!-- //해더 네비 -->


    <div id="container" class="clearfix">
      <!-- 게시판 aside -->
      <c:import url="/WEB-INF/views/includes/asideGallery.jsp"></c:import>
      <!-- //게시판 aside -->

      <div id="content">

        <div id="content-head">
          <h3>갤러리</h3>
          <div id="location">
            <ul>
              <li>홈</li>
              <li>갤러리</li>
              <li class="last">갤러리</li>
            </ul>
          </div>
          <div class="clear"></div>
        </div>
        <!-- //content-head -->

        <div id="gallery">
          <div id="list">

            <c:if test="${authUser.no != null}">
              <button id="btnImgUpload">이미지 올리기</button>
            </c:if>
            <div class="clear"></div>

            <ul id="viewArea">
              <c:forEach items="${galleryList}" var="galleryList">
                <!-- 이미지반복영역 -->
                <li id="t-${galleryList.no}">
                  <div class="view">
                    <img class="imgItem" data-no="${galleryList.no}" id="imgItem" src="${pageContext.request.contextPath}/upload/${galleryList.saveName}">
                    <div class="imgWriter">
                      작성자: <strong>${galleryList.name}</strong>
                    </div>
                  </div>
                </li>
                <!-- 이미지반복영역 -->
              </c:forEach>
            </ul>
          </div>
          <!-- //list -->
        </div>
        <!-- //gallery -->


      </div>
      <!-- //content  -->
    </div>
    <!-- //container  -->


    <!-- 푸터 -->
    <c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
    <!-- //푸터 -->
  </div>
  <!-- //wrap -->









  <!-- 이미지등록 팝업(모달)창 -->
  <div class="modal fade" id="addModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
          <h4 class="modal-title">이미지 등록</h4>
        </div>

        <form action="${pageContext.request.contextPath}/gallery/upload" method="post" enctype="multipart/form-data">
          <div class="modal-body">
            <div class="form-group">
              <label class="form-text">글 작성</label>
              <input id="addModalContent" type="text" name="content" value="">
            </div>
            <div class="form-group">
              <label class="form-text">이미지 선택</label>
              <input id="file" type="file" name="imgFile">
            </div>
          </div>
          <div class="modal-footer">
            <button type="submit" class="btn" id="btnUpload">등록</button>
          </div>
          <input type="hidden" name="user_no" id="authUser_no" value="${authUser.no}">
        </form>


      </div>
      <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
  </div>
  <!-- /.modal -->



  <!-- 이미지보기 팝업(모달)창 -->
  <div class="modal fade" id="viewModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
          <h4 class="modal-title">이미지 보기</h4>
        </div>
        <div class="modal-body">

          <div class="formgroup">
            <img id="viewModelImg" src="">
            <!-- ajax로 처리 : 이미지출력 위치-->
          </div>

          <div class="formgroup">
            <p id="viewModelContent"></p>
          </div>

        </div>
        <form method="get" action="" enctype="multipart/form-data">
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
            <button type="button" class="btn btn-danger" id="btnDel">삭제</button>

            <input type="hidden" id="galleryNo" value="">

          </div>


        </form>

      </div>
      <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
  </div>
  <!-- /.modal -->

</body>
<script type="text/javascript">
	// 사진 올리기 버튼을 클릭했을 때
	$("#btnImgUpload").on("click", function() {
		$("#addModal").modal();
	});
	// 삭제 버튼을 클릭했을 때
	$("#viewModal").on("click", "#btnDel", function() {
		$.ajax({
			// 컨트롤러에서 대기중인 URL 주소이다.
			url : "${pageContext.request.contextPath}/api/gallery/delete",
			// HTTP method type(GET, POST) 형식이다.
			type : "get",
			// Json 형태의 데이터로 보낸다.
			contentType : "application/json",
			// Json 형식의 데이터를 받는다.
			dataType : "json",
			data : {
				no : $("#galleryNo").val()
			},
			// 성공일 경우 success로 들어오며, 'result'는 응답받은 데이터이다.
			success : function(count) {
				/*성공시 처리해야될 코드 작성*/
				$("#viewModal").modal("hide");
				$("#t-" + $("#galleryNo").val()).remove();
			},
			// 실패할경우 error로 들어온다.
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	// 사진을 클릭했을 때
	$("#viewArea").on("click", "#imgItem", function() {
				$("#viewModal").modal();
				$("#viewModelImg").attr({
					src : $(this).attr("src")
				});
				$("#galleryNo").val($(this).data("no"));
				$.ajax({
					// 컨트롤러에서 대기중인 URL 주소이다.
					url : "${pageContext.request.contextPath}/api/gallery/src",
					// HTTP method type(GET, POST) 형식이다.
					type : "get",
					// Json 형태의 데이터로 보낸다.
					contentType : "application/json",
					// Json 형식의 데이터를 받는다.
					dataType : "json",
					data : {
						no : $(this).data("no")
					},
					// 성공일 경우 success로 들어오며, 'result'는 응답받은 데이터이다.
					success : function(galleryVo) {
						/*성공시 처리해야될 코드 작성*/
						$("#viewModelContent").html(
								galleryVo[Object.keys(galleryVo)[2]]);
						var userNo = galleryVo[Object.keys(galleryVo)[1]];
						var authuserNo = $("#authUser_no").val();
						if (authuserNo == userNo) {
							$("#btnDel").show();
						} else {
							$("#btnDel").hide();
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