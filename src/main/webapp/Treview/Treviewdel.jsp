<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>        
<%@ include file="../header.jsp"%>

<!-- 본 문 시 작 Treviewdel -->
   <form name='delfrm' method="post" action="delete.do" onsubmit="return pdsCheck()">
   	<input type="hidden" name="rnum" value="${dto.rnum}"> 	 
	<div class="content">
    <p>후기글을 삭제하시겠습니까?</p>
    <p>※ 관련 사진도 전부 삭제됩니다</p>
	</div>
	<div class='bottom'>
      <input type="submit" value="확인">
      <input type="button" value="목록" onclick="location.href='Treview.do'">
</form>
	

<!-- 본 문 끝 !! -->
<%@ include file="../footer.jsp"%>