<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ include file="../header.jsp"%>
<%-- <script type="text/javaScript" src="../js/treviewscript.js"></script>
<script type="text/javaScript"><%@ include file="../js/treviewscript.js" %></script> --%>
<script>
	function add(){
	var message="정말 수정하시겠습니까? 수정 시 기존 파일은 삭제됩니다";
    if(confirm(message)){//확인 true, 취소 false
       //return true;//서버전송
    }else{
    	alert('취소하였습니다');
    	history.back();
       //return false;
    }//if end
	}//add end
</script>

<!-- 본 문 시 작 Treviewup -->
<div onload="add()">

   <form name='updatefrm' method="post" action="update.do"
   		 enctype="multipart/form-data" onsubmit="return pdsCheck()">
   	<input type="hidden" name="rnum" id="rnum" value="${dto.rnum}" />	 
   	<input type="hidden" name="rid" id="rid" value="${dto.rid}" />	 
 	<input type="hidden" name="rdate" id="rdate" value="${dto.rdate}" />
 	<input type="hidden" id="rregion" name="rregion" value="${dto.rregion}">	
	<div style="margin: 0 10% 0 10%;">
	<br>
	<table class="notice_tal">
	<tr>
	   <th width="30%">제목</th>
	   <td align="left"><input type="text" name="rsubject" value="${dto.rsubject }"></td>
	</tr>
	<tr>
	   <th>비밀번호</th>
	   <td align="left"><input type="password" name="rpasswd" required></td>
	</tr>
	<tr>
	   <th>내용</th>
	   <td align="left"><textarea rows="15%" cols="60%" name="rcontent" >${dto.rcontent}</textarea></td>
	</tr>
	<tr>
	   <th>첨부파일</th>
	   <td align="left">
	   <input type="file" name="photonameMF" multiple size='50'>
	   <br>※ 올릴 사진은 모두 선택해주세요
	   </td>
	</tr>
	</table>
	</div>
	<br>
	<p align="center">
	
      <input type="submit"  value="수정" style="background-color: white; color: black; font-size: 15px; width: 100px; height:40px;border-color: black; border-radius: 5px; font-size: 10pt;">
      &nbsp;
      <input type="button" value="목록" onclick="location.href='Treview.do'">
      </p><br>
	</form>
</div>

<!-- 본 문 끝 !! -->
<%@ include file="../footer.jsp"%>