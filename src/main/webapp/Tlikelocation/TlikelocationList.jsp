<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본 문 시 작 -->
<form action="TlikelocationDeleteForm.do">
<c:if test="${sessionScope.s_id!=null}">
   
   <div class="title">관심여행지 목록</div>
 
	<table class="table" border="1">
		<tr>
		<td>리스트 총갯수:${count }</td>
		</tr>
		
		
		<tr>
			<th>체크란</th>
			<th>아이콘</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성시간</th>
			<th>지역</th>
		</tr>
	<c:forEach var="dto" items="${list}">
		<tr>
			<td><input type="checkbox" name="check" value="${dto.lno}"> </td>
			<td>${dto.licon }</td>
			<td><a href="<%=request.getContextPath() %>../Troute/TrouteRead.do?Tno=${dto.lno}">${dto.lsubject}</td>
			<td>${dto.lid }</td>
			<td>${fn:substring(dto.ldate,0,10)}</td>
			<td>${dto.lregion }</td>	
		</tr>
	</c:forEach>
	</table>
		
		
		<div class='bottom'>
			<input type="submit" value="글삭제"> 
			<input type="button" value="되돌아가기" onclick="location.href='${root}/home.do'">
		</div>
		
</c:if>
</form>		
<c:if test="${sessionScope.s_id==null}">
	로그인을 해주십시오
</c:if>

<!-- 본 문 끝 !! -->

<%@ include file="../footer.jsp"%>