<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
    
<%@ include file="../header.jsp" %>

<div align="center">
<h3>* 로 그 인 *</h3>
<c:if test="${empty sessionScope.memid}">
<%
 //사용자 pc에 저장된 쿠키값 가져오기
 Cookie[] cookies=request.getCookies();
 String c_id="";
 if(cookies!=null){//쿠키가 존재한다면
    for(int idx=0; idx<cookies.length; idx++){
       Cookie item=cookies[idx];
       if(item.getName().equals("c_id")==true){
          c_id=item.getValue();
       }
    }//for end
 }//if end
%>

<form name="loginfrm"
         method="post"
         action="TloginPro.do"
          onsubmit="return loginCheck(this)">
      
       <table align="center" style="width:60%;">
      <tr>
         <td align="right" width="12px"><input type="text" name="mid" id="mid" placeholder="Username" required></td>
         <td align="left" width="10px" rowspan="2"><input type="image" src="../images/bt_login.gif" style="cursor:pointer"></td>
      </tr>
      <tr >
         <td align="right"  width="12px"><input type="password" name="mpasswd" placeholder="Password" required></td>
      </tr>
      <tr>
         <td align="center" colspan="2"><input type="checkbox" name="c_id" value="SAVE">아이디저장&nbsp;&nbsp;
               <a href="agreement.jsp">회원가입</a>&nbsp;&nbsp;
               <a href="findID.jsp">아이디/비번찾기</a>
            </td>
      </tr>   
   </table>
      </form>
      </div>

</c:if>

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
      </div>
  
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>    


    