<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본 문 시 작 -->
<strong>* 글수정 *</strong>
<br><br>
<form method="post" name="updateform" action="./TnoticeUpdateProc.do?nonum=${dto.nonum}">
<input type="hidden" name="Tnum" value="${requestScope.num}">
<input type="hidden" name="Mname" value="${requestScope.Mname}">
<input type="hidden" name="Mlevel" value="${requestScope.Mlevel}">

<table class="table" width="450" border="1">
<tr>
	<td align="right" colspan=2 bgcolor="${value_c}">
		<a href="<%=request.getContextPath() %>/Tnotice/Tnoticelist.do">글목록</a></td>
</tr>
<tr>
	<td bgcolor="${value_c }">제목</td>
	<td><input type="text" name="nosubject" value="${dto.nosubject}"></td>
</tr>
<tr>
	<td bgcolor="${value_c }">내용</td>
	<td><textarea name="nocontent" row="5" cols="40"> ${dto.nocontent}</textarea></td>
</tr>
<tr>
	<td bgcolor="${value_c }">비밀번호</td>
	<td><input type="password" name="Mpasswd"></td>
</tr>
<tr>
	<td colspan=2 bgcolor="${value_c }" align="center">
		<input type="submit" value="수정">
		<input type="reset" value="취소">
		<input type="button" value="목록보기" onclick="location.href='./Tnoticelist.do'">
</tr>
</table>
</form>

<!-- 본 문 끝 !! -->

<%@ include file="../footer.jsp"%>