<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 본 문 시 작 -->
<br>
<h3 align="center"> 프로모션 상세보기 </h3>
	<table border="1" style="margin:5% 20% 5% 0%;">
	<tr>
		<th>분류</th>
		<th>글번호</th>
		<th>사진</th>
		<th>지역</th>
		<th>제목</th>
		<th>작성일</th>
		
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>	
	</table>			
	
	<div class="bottom">
		<input type="button" value="프로모션수정" onclick="location.href='TpromotionModify.do'">
		<input type="button" value="프로모션삭제" onclick="location.href='TpromotionDelete.do'">
		<input type="button" value="HOME" onclick="location.href='${root}/home.do'">
	</div>
		



<!-- 본 문 끝 !! -->
<%@ include file="../footer.jsp"%>