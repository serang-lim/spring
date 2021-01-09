<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ include file="../header.jsp"%>
<%-- <script type="text/javaScript" src="../js/treviewscript.js"></script>
<script type="text/javaScript"><%@ include file="../js/treviewscript.js" %></script> --%>

<!-- 본 문 시 작 Treviewup -->
<c:if test="${res==0}">
   <script>
   alert("비밀번호가 일치하지 않습니다")<br/>
   <a href='history.go(-1)''>[돌아가기]</a>
   </script>
</c:if>

<c:if test="${res==1 }">
	<script>
	var message="정말 수정하시겠습니까? 수정 시 기존 파일은 삭제됩니다";
    if(confirm(message)){//확인 true, 취소 false
       return true;//서버전송
    }else{
       return false;
    }//if end
   </script>
   <form name='updatefrm' method="post" action="update.do"
   		 enctype="multipart/form-data" onsubmit="return pdsCheck()">
   	<input type="hidden" name="rnum" id="rnum" value="${dto.rnum}" />	 
   	<input type="hidden" name="rid" id="rid" value="${dto.rid}" />	 
 	<input type="hidden" name="rdate" id="rdate" value="${dto.rdate}" />	
	<table class='table_write'>
	<tr>
	   <th>제목</th>
	   <td><input type="text" name="rsubject" value="${dto.rsubject }"></td>
	</tr>
	<tr>
	   <th>비밀번호</th>
	   <td><input type="password" name="rpasswd" required></td>
	</tr>
	<tr>
	   <th>지역</th>
	   <td>
	   <select name="rregion"  id="rregion">
          <option value="없음" selected>선택하세요.</option>
          <option value="동해">동해</option>
          <option value="삼척">삼척</option>
          <option value="속초">속초</option>
          <option value="강릉">강릉</option>
        </select>
        </td>
	</tr>
	<tr>
	   <th>내용</th>
	   <td><input type="text" name="rcontent" rows="5" cols="30" value="${dto.rcontent }"></td>
	</tr>
	<tr>
	   <th>첨부파일</th>
	   <td><input type="file" name="photonameMF" multiple size='50'></td>
	</tr>
	</table>
	<br>
	<p align="center">
	<td colspan='2'>
      <input type="submit"  value="등록">
      <input type="button" value="목록" onclick="location.href='Treview.do'">
      </p><br>
	</form>
</c:if>
<!-- 본 문 끝 !! -->
<%@ include file="../footer.jsp"%>