<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
  <div id="wrap">

    <!-- Header -->
    <c:import url="/WEB-INF/views/includes/header.jsp"></c:import>


    <div id="container" class="clearfix">

      <!-- aside -->
      <c:import url="/WEB-INF/views/includes/asideBoard.jsp"></c:import>

      <div id="content">

        <div id="content-head">
          <h3>게시판</h3>
          <div id="location">
            <ul>
              <li>홈</li>
              <li>게시판</li>
              <li class="last">일반게시판</li>
            </ul>
          </div>
          <div class="clear"></div>
        </div>
        <!-- //content-head -->

        <div id="board">
          <div id="list">
            <form action="${pageContext.request.contextPath}/board/list" method="get">
              <div class="form-group text-right">
                <input type="text" name="keyword" value="">
                <button type="submit" id=btn_search>검색</button>
              </div>
            </form>
            <table>
              <thead>
                <tr>
                  <th>번호</th>
                  <th>제목</th>
                  <th>글쓴이</th>
                  <th>조회수</th>
                  <th>작성일</th>
                  <th>관리</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${bList}" var="bList">
                  <tr>
                    <td>${bList.rn}</td>
                    <td class="text-left"><a href="${pageContext.request.contextPath}/board/read/${bList.no}">${bList.title}</a></td>
                    <td>${bList.name}</td>
                    <td>${bList.hit}</td>
                    <td>${bList.reg_date}</td>
                    <c:if test="${authUser.no == bList.user_no}">
                      <td><a href="${pageContext.request.contextPath}/board/delete/${bList.no}">[삭제]</a></td>
                    </c:if>
                  </tr>
                </c:forEach>

              </tbody>
            </table>

            <div id="paging">
              <ul>
                <li><a href="">◀</a></li>
                <li><a href="">1</a></li>
                <li><a href="">2</a></li>
                <li><a href="">3</a></li>
                <li><a href="">4</a></li>
                <li class="active"><a href="">5</a></li>
                <li><a href="">6</a></li>
                <li><a href="">7</a></li>
                <li><a href="">8</a></li>
                <li><a href="">9</a></li>
                <li><a href="">10</a></li>
                <li><a href="">▶</a></li>
              </ul>


              <div class="clear"></div>
            </div>
            <c:if test="${authUser.no != null}">
              <a id="btn_write" href="${pageContext.request.contextPath}/board/writeForm">글쓰기</a>
            </c:if>


          </div>
          <!-- //list -->
        </div>
        <!-- //board -->
      </div>
      <!-- //content  -->

    </div>
    <!-- //container  -->

    <!-- footer -->
    <c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

  </div>
  <!-- //wrap -->

</body>

</html>