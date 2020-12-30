<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp"%>
<!-- 본 문 시 작 -->
<br>
<h3 align="center"> 프 로 모 션 </h3>
	<table border="1" align="center">
	<tr align="center">
		<th width="20%">분류</th>
		<th width="10%">글번호</th>
		<th width="10%">사진</th>
		<th width="10%">지역</th>
		<th width="30%">제목</th>
		<th width="15%">작성일</th>
	</tr>
	<c:forEach var="dto" items="${list}">
	<tr align="center">
		<td>${dto.ticon}</td>
		<td>${dto.tbno}</td>
		<td>${dto.timage_name}</td>
		<td>${dto.tregion}</td>
		<td><a href="TpromotionRead.do">${dto.tsubject}</a></td>
		<td style="font-size:5pt;">${dto.twdate}</td>
	</tr>	
	</c:forEach>
	
	</table>			
	
	<br><br><br>
	
	<div class="bottom" align="center">
		<!-- 등록버튼 관리자만 보이게 설정 -->
		<input type="button" value="프로모션 등록하기" onclick="location.href='TpromotionWrite.do'">
		<input type="button" value="메인으로" onclick="location.href='${root}/home.do'">
	</div>
	<br><br><br>	



<!-- 본 문 끝 !! -->
<%@ include file="../footer.jsp"%>