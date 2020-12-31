<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<%@ include file="../header.jsp" %>
<!-- 본문 시작 read.jsp -->
   <h3 align="center">* 게시물 상세보기 *</h3>
	<p>
	<a href="Treviewcreate.jsp">[새로 작성하기]</a>
	&nbsp;&nbsp;
	<a href="Treview.do">[글목록]</a>
	</p>
   <table border='1' class="table_view">
	<form role="form" method="get">
   	<input type="hidden" name="writer" value="${dto.rid}">
   	<input type="hidden" name="num" value="${dto.rnum}">
   	<input type="hidden" name="size" value="${dto.rphoto_size}">
	</form>
   <tr>  
      <th>제목</th>
      <td>${dto.rsubject}</td>
   </tr>
   <tr>   
      <th>지역</th>
      <td>${dto.rregion}</td>
   </tr>
   <tr>   
      <th>작성일</th>
      <td>${dto.rdate}</td>
   </tr>
   <tr>   
      <th>사진</th>
      <td>
      <img src="../storage/${dto.rphoto_name}" style="size:30%">
      <!-- img src="${pageContext.request.contextPath}${url}" style="size:30%"> -->
		<script type="text/javascript">
		var img = document.getElementsByTagName("img");
		    for (var x = 0; x < img.length; x++) {
		      img.item(x).onclick=function() {window.open(this.src)}; 
		    }//for end
		</script>
      </td>
   </tr>
   <tr>   
      <th>내용</th>
      <td>${dto.rcontent}</td>
   </tr>
   <tr>   
      <th>조회수</th>
      <td>${dto.rreadcnt}</td>
   </tr>
   </table>
	<br>
	<input type="button" value="수정"
   onclick="location.href='updateform.do?rnum=${dto.rnum}'">
	<input type="button" value="삭제"
   onclick="location.href='delete.do?rnum=${dto.rnum}'">
   <input type='button' value='HOME' onclick="location.href='${root}/index.do'">
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %> 