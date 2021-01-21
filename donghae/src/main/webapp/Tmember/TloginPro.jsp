<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>



      

<div align="center">
<c:if test="${res==1 }">
   <c:set var="memid" value="${sessionScope.s_id}" scope="session"/>
   <meta http-equiv="Refresh" content="0;url=../home.do">
</c:if>

<c:if test="${res==0}">
   <script>
   alert("아이디 또는 비밀번호를 확인해주세요");
   history.back();
   </script>
</c:if>
</div>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>    

