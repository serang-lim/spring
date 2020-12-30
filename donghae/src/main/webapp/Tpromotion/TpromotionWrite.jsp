<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 본 문 시 작 -->
<div> 프 로 모 션 </div>
<form name="TpromotionWrite" enctype="multipart/form-data"
	action="TpromotionInsert.do" method="post">
	<table border="1" align="center">
	<tr>
		<th width="15%">분류</th>
			<td> <select name="ticon" id="ticon">
					<option value="주위식당">주위식당</option>
					<option value="주요행사">주요행사</option>
					<option value="숙소">숙소</option>
					<option value="액티비티">액티비티</option>
				 </select></td>
	</tr>
	<tr>
		<th width="15%">지역</th>
				<td> <select name="tregion" id="tregion">
					<option value="gang">강릉</option>
					<option value="sock">속초</option>
					<option value="sam">삼척</option>
					<option value="dong">동해</option>
				 </select></td>
	</tr>
	<tr>	
		<th width="15%">사업자번호</th>
			<td><input type="number" name="tnum" id="tnum"></td>
	</tr>
	<tr>	
		<th width="15%">제목</th>
			<td><input type="text" name="tsubject" id="tsubject"></td>
	</tr>
	<tr>
		<th width="15%">관리자아이디</th>
			<td><input type="text" name="twriter" id="twriter"></td>
	</tr>
	<tr>
		<th width="15%">사진</th>
			<td><input type="file" name="timage_nameMF" id="timage_nameMF" value="관리자"></td>
	</tr>

	</table>
	<div class="bottom">
		<input type="submit" value="프로모션등록">
		<input type="button" value="HOME" onclick="location.href='${root}/home.do'">
	</div>
</form>

<!-- 본 문 끝 !! -->
<%@ include file="../footer.jsp"%>