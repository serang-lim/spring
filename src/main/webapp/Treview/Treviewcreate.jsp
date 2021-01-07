<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp"%>
<script>
   var cnt = 1;
   
   function addFile() {
   var addItem = "<input type='file' name='photonameMF"+cnt+"' /><br/>";
   document.getElementById("filebox").innerHTML += addItem;
   cnt++;
   }
   
   var formData = new FormData($('#fileForm')[0]); 
   $.ajax({ 
      type: "POST", 
      enctype: 'multipart/form-data', // 필수 
      url: '/multipartUpload.do', data: formData, // 필수 
      processData: false, // 필수 
      contentType: false, // 필수 
      cache: false, success: 
         function (result) { 
         
      }, 
      error: function (e) { 
         
      } });
</script>
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
      <th>지역</th>
      <td>
      <select name="rregion"  id="rregion">
          <option value="0" selected>선택하세요.</option>
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
   <tr>
      <th>파일첨부</th>
      <td>
      <input type="file" name="photonameMF" multiple size="50">
        <!-- <input type="button" id="button2" value="파일 추가" onclick="addFile()">   
        <div id="filebox"></div> -->
      </td>
   </tr>
   <tr>
   <td colspan='2'>
      <input type="submit"  value="등록">
      <input type="button" value="목록" onclick="location.href='Treview.do'">
      <input type="button" value="HOME" onclick="location.href=${root}/home.do'">
   </table>
   
</form>
   
<!-- 본 문 끝 !! -->
<%@ include file="../footer.jsp"%>