<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp"%>

<!-- 본 문 시 작 -->
<c:if test="${empty sessionScope.memid }">
<script>
alert("로그인 해주세요");
</script>
<meta http-equiv="refresh" content="0;url=Tlogin.do">
</c:if>
<br><br>

<h3 align="center">${s_id }님 즐거운 여행 하세요~</h3>
<c:if test="${sessionScope.memid !=null }">
   <br>
   <c:if test="${sessionScope.s_mlevel =='A1' }">
   <div align="center">
   		<input type="button" value="회원정보수정"
       	onclick="location.href='./Tmodifycheck.do'">
       	&nbsp;&nbsp;
      	<input type="button" value="회원정보리스트"
       	onclick="location.href='./Tlist.do'">   
       	&nbsp;&nbsp;
   		<input type="button" value="나의 예약"
       	onclick="location.href='../Treserve/listper.do'">
   </div>
   </c:if>
   <c:if test="${sessionScope.s_mlevel=='B1' }">
   <div align="center">
   		<input type="button" value="회원정보수정"
      	onclick="location.href='./Tmodifycheck.do'">
      	&nbsp;&nbsp;
      	<input type="button" value="예약접수현황"
       	onclick="location.href='../Treserve/listent.do'"> 
   </div>
   <br>
   <div align="center">       	
       <input type="button" value="나의 예약"
       onclick="location.href='../Treserve/listper.do'">
       &nbsp;&nbsp;
       <input type="button" value="회원탈퇴" 
       onclick="location.href='./Tdelcheck.do'">
   </div>
   </c:if>
      <c:if test="${sessionScope.s_mlevel=='C1' }">
   <div align="center">
   		<input type="button" value="회원정보수정"
      	onclick="location.href='./Tmodifycheck.do'">
      	&nbsp;&nbsp;
       	<input type="button" value="나의 예약"
       	onclick="location.href='../Treserve/listper.do'">
       	&nbsp;&nbsp;
        <input type="button" value="회원탈퇴" 
       	onclick="location.href='./Tdelcheck.do'">
   </div>
   </c:if>
   
</c:if>
<br><br>
<!-- 본 문 끝 !! -->

<%@ include file="../footer.jsp"%>