<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 본 문 시 작 -->

<h3 align="center"> 프 로 모 션 </h3>
<form name="TpromotionWrite" enctype="multipart/form-data"
	action="TpromotionInsert.do" method="post">
	
	<input type="hidden" name="twriter" id="twriter" value="${sessionScope.s_id}">
	<div style="margin: 0 10% 0 10%;">
	<table class="notice_tal">
	<tr>
		<th width="30%">분류</th>
			<td align="left"> <select name="ticon" id="ticon" style="width:30%;">
					<option disabled selected>분류선택</option>
					<option value="주위식당">주위식당</option>
					<option value="주요행사">주요행사</option>
					<option value="숙소">숙소</option>
					<option value="액티비티">액티비티</option>
					<option value="사진스팟">사진스팟</option>
				 </select></td>
	</tr>
	<tr>
		<th>지역</th>
				<td align="left"> <select name="tregion" id="tregion" style="width:30%;">
					<option disabled selected>지역선택</option>
					<option value="강릉">강릉</option>
					<option value="속초">속초</option>
					<option value="삼척">삼척</option>
					<option value="동해">동해</option>
				 </select></td>
	</tr>
	<tr>	
		<th>제목</th>
			<td align="left"><input type="text" name="tsubject" id="tsubject" required size="30%"></td>
	</tr>
	<tr>
		<th>메인 사진</th>
			<td align="left"><input type="file" name="timage_nameMF" id="timage_nameMF" value="main사진" required></td>
	</tr>
	<tr>
		<th>프로모션 사진</th>
			<td align="left"><input type="file" name="timage_nameMF2" id="timage_nameMF2" value="read사진" required></td>
	</tr>
	<tr>
		<th>비밀번호</th>
			<td align="left" style="font-size:8pt; color:red;"><input type="password" name="tpasswd" id="tpasswd" required> &nbsp; *숫자로 입력 하세요</td>
	</tr>
	</table>
	</div>
	<div align="center" style="padding: 1%  1%  1%  1%;">
		<input type="submit" value="프로모션등록" style="background-color: white; color: black; font-size: 15px; width: 100px; height:40px;border-color: black; border-radius: 5px; font-size: 10pt;">
		<input type="button" value="목록으로" onclick="location.href='Tpromotion.do'">
		<input type="button" value="HOME" onclick="location.href='../home.do'">
	</div>
</form>


<!-- 본 문 끝 !! -->
<%@ include file="../footer.jsp"%>