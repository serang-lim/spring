<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 본 문 시 작 -->

<h3 align="center"> 프 로 모 션 </h3>
<form name="TpromotionWrite" enctype="multipart/form-data"
	action="TpromotionInsert.do" method="post">
	
	<input type="hidden" name="twriter" id="twriter" value="${sessionScope.s_id}">

	<table border="1" align="center">
	<tr>
		<th width="15%">분류</th>
			<td> <select name="ticon" id="ticon">
					<option disabled selected>분류선택</option>
					<option value="주위식당">주위식당</option>
					<option value="주요행사">주요행사</option>
					<option value="숙소">숙소</option>
					<option value="액티비티">액티비티</option>
				 </select></td>
	</tr>
	<tr>
		<th width="15%">지역</th>
				<td> <select name="tregion" id="tregion">
					<option disabled selected>지역선택</option>
					<option value="강릉">강릉</option>
					<option value="속초">속초</option>
					<option value="삼척">삼척</option>
					<option value="동해">동해</option>
				 </select></td>
	</tr>
	<tr>	
		<th width="15%">제목</th>
			<td><input type="text" name="tsubject" id="tsubject" required></td>
	</tr>
	<tr>
		<th width="15%">메인 사진</th>
			<td><input type="file" name="timage_nameMF" id="timage_nameMF" value="main사진" required></td>
	</tr>
	<tr>
		<th width="15%">프로모션 사진</th>
			<td><input type="file" name="timage_nameMF2" id="timage_nameMF2" value="read사진" required></td>
	</tr>
	<tr>
		<th>비밀번호</th>
			<td style="font-size:8pt; color:red;"><input type="password" name="tpasswd" id="tpasswd" required>*숫자로 입력 하세요</td>
	</tr>
	</table>
	<div align="center" style="padding: 1%  1%  1%  1%;">
		<input type="submit" value="프로모션등록">
		<input type="button" value="목록으로" onclick="location.href='Tpromotion.do'">
		<input type="button" value="HOME" onclick="location.href='../home.do'">
	</div>
</form>


<!-- 본 문 끝 !! -->
<%@ include file="../footer.jsp"%>