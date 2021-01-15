<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp"%>
<!-- 본 문 시 작 -->
<br>
<h3 align="center"> 프로모션 상세보기 </h3>
	<form action="updateRes.do" name="readfrm">
	<input type="hidden" name="tbno" value="${dto.tbno}">
	<table class="notice_tal">
	<c:if test="${sessionScope.s_mlevel=='A1'}">
	<tr>
		<td colspan="6" style="text-align: right;">
			<select name="tresult">
			<option ${dto.tresult.equals("Y") ? "selected" : ""} value="Y">Y</option>
			<option ${dto.tresult.equals("N") ? "selected" : ""} value="N">N</option>
			</select>
			<input type="submit" value="변경하기">
		</td>
	</tr>	
		</c:if>
	<tr>
		<th colspan="2">제목</th>
			<td colspan="4">${dto.tsubject }</td>
	</tr>
	<tr>
		<th>글번호</th>
			<td>${dto.tbno}</td>
		<th>분류</th>
			<td>${dto.ticon}</td>
		<th>지역</th>
			<td>${dto.tregion }</td>
	</tr>
	<tr>
		<th>작성자</th>
			<td>${dto.twriter}</td>
		<th>작성일</th>
			<td>${fn:substring(dto.twdate,0,10)}</td>
		<th>게시기간</th>
			<td>${fn:substring(dto.ttime,0,10)}</td>
	</tr>
	<tr>
		<th colspan="1">사진</th>
			<td colspan="5"><img src="../storage/${dto.timage_name2}" width="80%" height="auto" align="middle"></td>
	</tr>	
	</table>			
	</form>
	<div align="center" style="padding: 1%  1%  1%  1%;">
	 
		<c:if test="${sessionScope.s_mlevel=='B1'}"><!-- 멤버레벨이 관리자면 보여지도록 바꿔야함 -->
			<input type="button" value="프로모션수정" onclick="location.href='TpromotionUpdate.do?tbno=${dto.tbno}'">
			<input type="button" value="프로모션삭제" onclick="location.href='TpromotionDelete.do?tbno=${dto.tbno}'">
		</c:if>

		<c:if test="${sessionScope.memid !=null }">
      		<input type="button" value="예약하기" onclick="location.href='../Treserve/create.do?Tbno=${dto.tbno}'">
      	</c:if>
  		<input type="button" value="목록으로" onclick="location.href='Tpromotion.do'">
		<input type="button" value="HOME" onclick="location.href='../home.do'">
	</div>
		



<!-- 본 문 끝 !! -->
<%@ include file="../footer.jsp"%>