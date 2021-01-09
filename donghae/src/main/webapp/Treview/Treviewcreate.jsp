<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp"%>
<%-- <%@ include file="script.jsp"%> --%>
<script src="../js/treviewscript.js"></script>

<!-- 본 문 시 작 TreviewcreateFrom -->
<c:if test="${empty sessionScope.memid }">
<script>
	alert("로그인 해주세요");
	document.location.href="../Tmember/Tlogin.do";
</script>
</c:if>
<c:if test="${sessionScope.memid !=null }">

   <form name='createfrm' method="post" action="create.do"
          enctype="multipart/form-data" onsubmit="return pdsCheck()">
   <input type="hidden" name="rid" value="${sessionScope.memid}">       
   <table class='table_write'>
   <tr>
      <th>제목</th>
      <td><input type="text" name="rsubject" id="rsubject"></td>
   </tr>
   <tr>
      <th>비밀번호</th>
      <td><input type="password" name="rpasswd" id="rpasswd" required></td>
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
      <td><input type="text" name="rcontent"></td>
      <!-- <textarea col="5" style="resize: none;" name="rcontent" ></textarea> -->
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
   </table>
   <br><p align="center">
      <input type="submit"  value=" 등록 ">
      <input type="button" value=" 목록 " onclick="location.href='Treview.do'">
      <%-- <input type="button" value=" HOME " onclick="location.href=${root}/home.do'"> --%>
   </p><br>
</form>
</c:if>   
<!-- 본 문 끝 !! -->
<%@ include file="../footer.jsp"%>