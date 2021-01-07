<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본 문 시 작 -->
<div class="title">음원 삭제</div>
	<form name='frm' method="POST" action='TnoticeDeleteProc.do'>
	<input type="hidden" name="nonum" value="${dto.nonum }">
	<div class='content'>
		<p>공지사항을 삭제하시겠습니까?</p>
	</div>
	<div class='bottom'>
		<input type='submit' value='삭제진행'>
		<input type='button' value='음원목록' onclick="location.href='TnoticeList.do?nonum=${dto.nonum}'">
	</div>
	</form>

<!-- 본 문 끝 !! -->

<%@ include file="../footer.jsp"%>