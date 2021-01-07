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
         <td align="right" width="12px"><input type="text" name="Mid" id="Mid" value="<%=c_id %>" placeholder="Username" required></td>
         <td align="left" width="10px" rowspan="2"><input type="image" src="../images/bt_login.gif" style="cursor:pointer"></td>
      </tr>
      <tr >
         <td align="right"  width="12px"><input type="password" name="Mpasswd" placeholder="Password" required></td>
      </tr>
      <tr>
         <td align="center" colspan="2"><input type="checkbox" name="c_id" id="c_id" value="SAVE">아이디저장&nbsp;&nbsp;
               <a href="agreement.jsp">회원가입</a>&nbsp;&nbsp;
               <a href="TfindID.do">아이디/비번찾기</a>
            </td>
      </tr>   
   </table>
      </form>
      </div>

</c:if>


    
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>    


    