<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>

<h3>*아이디 찾기*</h3>
<form method="post" action="TfindIDPro.do" onsubmit="findIDCheck()">
<table class="table">
<tr>
	<th>이름</th>
<td>
	<input type="text" name="Mname" id="Mname"  placeholder="이름" maxlength="20" required>
</td>
</tr>
<tr>
	<th>이메일</th>
	<td>
	<input type="email" name="Memail" id="Memail" placeholder="이메일" maxlength="30" text-align="left" required>
	</td>	
</tr>
<tr>
	<td colspan="2">
		<input type="submit" value="아이디 /비번찾기"  class="btn btn-primary"/>
		<input type="reset"  value="취소"  class="btn btn-primary"/>
	</td>
</tr>
</table>
</form>