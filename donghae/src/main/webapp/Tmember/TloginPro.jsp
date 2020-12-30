<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>


<c:if test="${sessionScope.memid!=null }">
   <table width=500 cellpaddion="0" cellspacing="0" align="center" border="1">
      <tr>
         <td rowspan="3" align="center">
         ${sessionScope.memid} 님이 방문하셨습니다.
         <form method="post" action="./logout.do">
            <input type="submit" value="로그아웃">
         </form>
         
         <form method="post" action="./ModifyCheck.do">
            <input type="hidden" name="id" value="${sessionScope.memid}">
            <input type="submit" value="회원정보변경">
         </form>
         
         <form method="post" action="./IdDelForm.do">
            <input type="submit" value="회원탈퇴">
         </form>
      </tr>
   </table>
</c:if>
      
<!-- 본문시작 loginForm.jsp -->
<div align="center">
<c:if test="${res==1 }">
   <c:set var="memid" value="${sessionScope.s_id}" scope="session"/>
   <meta http-equiv="Refresh" content="0;url=../TloginForm.do">
</c:if>

<c:if test="${res==0}">
   아이디 또는 비밀번호를 확인해주세요<br/>
   <a href="javascript:history.go(-1)">[돌아가기]</a>
</c:if>
</div>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>    

