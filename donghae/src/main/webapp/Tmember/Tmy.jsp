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
<c:if test="${sessionScope.memid !=null }">
   <div align="center">
   <input type="button" value="회원정보수정"
       onclick="location.href='./Tmodifycheck.do'">
   </div>
   <br>
   <c:if test="${sessionScope.s_mlevel =='A1' }">
   <div align="center">
      <input type="button" value="회원정보리스트"
       onclick="location.href='./Tlist.do'">   
   </div>
   <br>
   </c:if>
   <c:if test="${sessionScope.s_mlevel=='B1' }">
   <div align="center">
      <input type="button" value="예약신청내역확인"
       onclick="location.href='../Treserve/listent.do'">   
   </div>
   </c:if>
   <div align="center">
      <input type="button" value="나의예약내역확인"
       onclick="location.href='../Treserve/listper.do'">   
   </div>
   <br>
   <div align="center">
   <input type="button" value="회원탈퇴" 
       onclick="location.href='./Tdelcheck.do'">
   </div>    
</c:if>
<!-- 본 문 끝 !! -->

<%@ include file="../footer.jsp"%>>