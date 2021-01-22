<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp"%>
<!-- 본 문 시 작 -->
<br>
<h2 align="center"> 프로모션 상세보기 </h2>
	
	<form action="updateRes.do" name="readfrm">
	<input type="hidden" name="tbno" value="${dto.tbno}">
	<div style="margin: 0 10% 0 10%;"> 
	<table class="notice_tal">
	<c:if test="${sessionScope.s_mlevel=='A1'}">
	<tr>
		<td colspan="6" style="text-align: right;">
			<select name="tresult">
			<option ${dto.tresult.equals("Y") ? "selected" : ""} value="Y">Y</option>
			<option ${dto.tresult.equals("N") ? "selected" : ""} value="N">N</option>
			</select>
			<input type="submit" value="변경하기" style="background-color: white; color: black; font-size: 15px; width: 70px; height:22px;border-color: black; border-radius: 5px; font-size: 10pt;">
		</td>
	</tr>	
		</c:if>
	<tr>
		<th colspan="1">제목</th>
			<td colspan="5">${dto.tsubject }</td>
	</tr>
	<tr>
		<th>글번호</th>
			<td>${dto.tbno}</td>
		<th>분류</th>
			<td>
			<c:if test="${dto.ticon=='숙소'}"><img src="../images/zam.png" width="35px"></c:if>
			<c:if test="${dto.ticon=='주위식당'}"><img src="../images/bob.png" width="35px"></c:if>
			<c:if test="${dto.ticon=='주요행사'}"><img src="../images/fe.png" width="35px"></c:if>
			<c:if test="${dto.ticon=='액티비티'}"><img src="../images/act.png" width="35px"></c:if>
			<c:if test="${dto.ticon=='사진스팟'}"><img src="../images/sajin.png" width="35px"></c:if>
			</td>
		<th>지역</th>
			<td>
			<c:if test="${dto.tregion=='강릉'}"><img src="../images/강릉.png" width="35px"></c:if>
			<c:if test="${dto.tregion=='삼척'}"><img src="../images/삼척.png" width="35px"></c:if>
			<c:if test="${dto.tregion=='속초'}"><img src="../images/속초.png" width="35px"></c:if>
			<c:if test="${dto.tregion=='동해'}"><img src="../images/동해.png" width="35px"></c:if>
			</td>
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
	</div>		
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