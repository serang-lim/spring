<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>


<h3>* 회원정보수정*</h3>
<form method="post" action="TmodifyForm.do"
	  onsubmit="return pwCheck()">

   
<table class='table'>
<tr>
	<!-- <th>아이디</th>
	<td><input type="text" name="Mid" id="Mid"  required></td>  -->
<tr>
	<th>비밀번호</th>
	<td><input type="password" name="Mpasswd" id="Mpasswd"  required></td>
</tr>
<tr>
	<td colspan="2">
	<input type="submit" value="수정" class="btn btn-danger">
	</td>
</tr>
</table>
</form>

	

<!-- 본문 끝 -->

<%@ include file="../footer.jsp" %>
    