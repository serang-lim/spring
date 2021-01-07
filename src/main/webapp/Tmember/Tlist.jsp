<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ include file="../header.jsp" %>  
<table> 
	<tr>
		<th>체크란</th>
		<th>회원가입일</th>
		<th>회원레벨</th>
		<th>회원명</th>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>연락처</th>
	</tr>

<c:forEach var="dto" items="${list }">
	<tr>
		<td><input type="checkbox" value="회원리스트" name="Tlist" id="Tlist"/></td>
		<td>${dto.mdate }</td>
		<td>${dto.mlevel}</td>
		<td>${dto.mname }</td>
   		<td>${dto.mid}</td>
   		<td>${dto.mpasswd }</td>
   		<td>${dto.mtel }</td>
   			
   	
	</tr>
	</c:forEach>
</table>




</html>