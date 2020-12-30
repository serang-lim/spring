<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ include file="../header.jsp"%>
  
<!-- 본 문 시 작 TreviewcreateFrom -->
   <form name='createfrm' method="post" action="create.do"
   		 enctype="multipart/form-data" onsubmit="return pdsCheck()">
   		 
	<table class='table_write'>
	<tr>
	   <th>제목</th>
	   <td><input type="text" name="rsubject"></td>
	</tr>
	<tr>
	   <th>비밀번호</th>
	   <td><input type="password" name="rpasswd"></td>
	</tr>
	<tr>
	   <th>작성자</th>
	   <td><input type="text" name="rid"></td>
	</tr>
	<tr>
	   <th>첨부파일</th>
	   <td><input type="file" name="photonameMF" size='50'></td>
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
	   <td><input type="text" name="rcontent" rows="5" cols="30"></td>
	</tr>
	
	
	<td colspan='2'>
      <input type="submit"  value="등록">
      <input type="button" value="목록" onclick="location.href='Treview.do'">
      <input type="button" value="HOME" onclick="location.href=${root}/home.do'">
	</table>
</form>
	

<!-- 본 문 끝 !! -->
<%@ include file="../footer.jsp"%>