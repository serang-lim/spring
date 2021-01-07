<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../header.jsp"%>

<!-- 본 문 시 작 -->
<body>
	<div style="text-align: center;"><h1>예약신청 상세내역</h1></div>
	<br>
	<table>
	<tr>
		<th>글제목</th>
		<th>예약날짜</th>
		<th>지역</th>
		<th>분류</th>
		<th>상태</th>
		<th></th>
	</tr>
	<c:forEach var="dto" items="${list }">
	<tr>
		<td>${dto.ressub }</td>
		<td>${fn:substring(dto.resdate,0,10) }</td>
		<td>${dto.resregion }</td>
		<td>${dto.resicon }</td>
		<td>${dto.result }</td>
		<td><input type="button" value="예약취소" onclick="location.href='listperup.do?resdate=${fn:substring(dto.resdate,0,10) }&resnum=${dto.resnum }'"></td>
	</tr>
	</c:forEach>
	</table>
	
	
<!-- 본 문 끝 !! -->

<%@ include file="../footer.jsp"%>