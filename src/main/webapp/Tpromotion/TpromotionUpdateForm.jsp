<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp"%>
<!-- 본 문 시 작 -->
<h3 align="center"> 프 로 모 션 </h3>
<form name="TpromotionUpdate" enctype="multipart/form-data"
	action="TpromotionUpdate.do" method="post">
	<input type="hidden" name="tbno" id="tbno" value="${dto.tbno}">
	<table border="1" align="center">
	<tr>
		
		<th width="15%">분류</th>
			<td> <select name="ticon" id="ticon">
					<option value="주위식당"${dto.ticon.equals("주우식당") ?"selected" : ""}>주위식당</option>
					<option value="주요행사"${dto.ticon.equals("주요행사") ?"selected" : ""}>주요행사</option>
					<option value="숙소"${dto.ticon.equals("숙소") ?"selected" : ""}>숙소</option>
					<option value="액티비티"${dto.ticon.equals("액티비티") ?"selected" : ""}>액티비티</option>
				 </select></td>
	</tr>
	<tr>
		<th width="15%">지역</th>
				<td> <select name="tregion" id="tregion">
					<option value="강릉" ${dto.tregion.equals("강릉") ?"selected" : ""}>강릉</option>
					<option value="속초" ${dto.tregion.equals("속초") ?"selected" : ""}>속초</option>
					<option value="삼척" ${dto.tregion.equals("삼척") ?"selected" : ""}>삼척</option>
					<option value="동해" ${dto.tregion.equals("동해") ?"selected" : ""}>동해</option>
				 </select></td>
	</tr>
	<tr>	
		<th width="15%">사업자번호</th>
			<td><input type="text" name="tnum" id="tnum" value="${dto.tnum}"></td>
	</tr>
	<tr>	
		<th width="15%">제목</th>
			<td><input type="text" name="tsubject" id="tsubject" value="${dto.tsubject}"></td>
	</tr>
	<tr>
		<th width="15%">관리자아이디</th>
			<td><input type="text" name="twriter" id="twriter"  value="${dto.twriter}"></td>
	</tr>
	<tr>
		<th width="15%">메인 사진</th>
			<td><input type="file" name="timage_nameMF" id="timage_nameMF" value="main사진"></td>
	</tr>
	<tr>
		<th width="15%">프로모션 사진</th>
			<td><input type="file" name="timage_nameMF2" id="timage_nameMF2" value="read사진"></td>
	</tr>
	</table>
	<div align="center" style="padding: 1%  1%  1%  1%;">
		<input type="submit" value="프로모션수정">
		<input type="button" value="목록으로" onclick="location.href='Tpromotion.do'">
		<input type="button" value="HOME" onclick="location.href='../home.do'">
	</div>
</form>

<div id="num" onMouseover="this.style.color=\'#FF9900\'" onMouseout="this.style.color=\'black\'">
${dto.twriter != null?"널이아니야":"널이야"}
</div>
<!-- 본 문 끝 !! -->
<%@ include file="../footer.jsp"%>