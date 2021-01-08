<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<%@ include file="../header.jsp" %>
<!-- 본문 시작 read.jsp -->
<c:if test="${empty sessionScope.rnum !=null }">
<!-- dao.incrementCnt(rnum); -->
</c:if>

   <h3 align="center">* 게시물 상세보기 *</h3>
   <br>
	<p align="center">
	<a href="createrform.do">[새로 작성하기]</a>
	&nbsp;&nbsp;
	<a href="Treview.do">[글목록]</a>
	</p>
	<br><br>
   <table class="table_view">
	<form role="form" method="get">
   	<input type="hidden" name="rid" value="${dto.rid}">
   	<input type="hidden" name="rnum" value="${dto.rnum}">
   	<input type="hidden" name="size" value="${dto.rphoto_size}">
 	
	</form>
	<tr height="30">   
      <th>작성일</th>
      <td>${dto.rdate}</td>
      <th>조회수</th>
      <td>${dto.rreadcnt}</td>
   </tr>
   <tr height="30">  
      <th>제목</th>
      <td>${dto.rsubject}</td>
   </tr>
   <tr height="30">   
      <th>지역</th>
      <td>${dto.rregion}</td>
   </tr>
<c:forEach var="dto" items="${list}"> 
	<tr height="30">
	<th>사진</th>
	<td>
        <img src="../storage/${dto.fileName}" width="500">
		<script type="text/javascript">
			var img = document.getElementsByTagName("img");
		    for (var x = 0; x < img.length; x++) {
		      img.item(x).onclick=function() {window.open(this.src)}; 
		    }//for end
		</script>
	 </td>
	 </tr>	    
</c:forEach>
   <tr height="30">   
      <th>내용</th>
      <td>${dto.rcontent}</td>
   </tr>
   
   </table>
	<br>
	<p align="center">
	<input type="button" value=" 수정 " onclick="checkpawdform.do?rnum=${dto.rnum}">
	<!-- location.href='updateform.do?rnum=${dto.rnum}' -->
    <input type="button" value=" 삭제 " onclick="location.href='delete.do?rnum=${dto.rnum}'">
	</p><br><br>
   
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %> 