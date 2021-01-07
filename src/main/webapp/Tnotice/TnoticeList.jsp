<%@page import="java.sql.Timestamp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본 문 시 작 -->
<div class="title">공지사항 목록</div>
	<table class="table" border="1">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>작성시간</th>
		</tr>
	<c:forEach var="dto" items="${list}">
		<tr>
			<td>${dto.nonum}</td>
			<td><a href="<%=request.getContextPath() %>/Tnotice/TnoticeRead.do?nonum=${dto.nonum}">${dto.nosubject}</td>
			<td>${dto.nowriter }</td>
			<td>${dto.noreadcnt }</td>
			<td>${fn:substring(dto.nodate,0,10)}</td>	
		</tr>
	</c:forEach>
	</table>
		<!-- 페이지 리스트 -->
	<c:if test="${count>0 }">
		<c:set var="pageCount" value="${totalPage }"/>
		<c:set var="startPage" value="${startPage }"/>
		<c:set var="endPage" value="${endPage }"/>
		
		<c:if test="${endPage>pageCount }">
			<c:set var="engPage" value="${pageCount+1 }"/>
		</c:if>
		
		<c:if test="${startPage>0 }">
			<a href="./TnoticeList.do?pageNum=${startPage }">[이전]</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage+1 }" end="${endPage-1 }">
			<a href="./TnoticeList.do?pageNum=${i }">[${i }]</a>
		</c:forEach>
		
		<c:if test="${endPage<pageCount }">
			<a href="./TnoticeList.do?pageNum=${startPage+11 }">[다음]</a>
		</c:if>
		
	</c:if>
		
		<c:if test="${sessionScope.s_mlevel=='A1' }"><div class='bottom'>
			<input type="submit" value="공지사항 작성" onclick="location.href='TnoticeForm.jsp'">
			<input type="button" value="되돌아가기" onclick="location.href='${root}/home.do'">
		</div>
		</c:if>

<!-- 본 문 끝 !! -->

<%@ include file="../footer.jsp"%>