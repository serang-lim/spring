<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TpromotionDeleteForm</title>

</head>
<body>
<h3 align="center">프로모션 삭제</h3>
<form action="TpromotionDelete.do" method="post" name="frm">
<input type="hidden" name="tbno" value="${dto.tbno}">

	<div align="center">
		<p>프로모션을 삭제하시겠습니까?</p>
		<p>* 관련 파일 전부 삭제됩니다 </p>
	</div>
<div align="center">
	<input type="submit"  value="삭제하기">
	<input type="button" value="목록으로" onclick="location.href='Tpromotion.do'">
</div>

</form>


</body>
</html>