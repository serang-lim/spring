<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TemailcheckPro</title>
</head>
<body>
<div style="text-align: center;">
   <h3>* 이메일 중복확인 결과 *</h3>      
   <c:if test="${requestScope.memail!=null}">
   입력ID: <strong> ${requestScope.memail } </strong>
  
   <a href="javascript:apply('${requestScope.memail}')">[적용]</a>
   </c:if>
   &nbsp;&nbsp;
   <a href="javascript:history.back()">[다시검색]</a>
   &nbsp;&nbsp;
   <a href="javascript:window.close()">[창닫기]</a>   
   </div>
   
   <script>
      function apply(memail){
         //중복이 확인된 id를 부모창(opener)에 적용
         opener.Tmemfrm.Memail.value=memail ;
         window.close();
      }//apply() end
   </script>
</body>
</html>