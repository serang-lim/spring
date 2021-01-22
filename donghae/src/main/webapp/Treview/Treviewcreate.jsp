<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp"%>
<%-- <%@ include file="script.jsp"%> --%>
<script src="../js/treviewscript.js"></script>

<!-- 본 문 시 작 TreviewcreateFrom -->
   <br>

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
   <input type="hidden" id="tno" name="tno" value="${tno}">
   <input type="hidden" id="rregion" name="rregion" value="${rregion}">
   
   <div style="margin: 0 10% 0 10%">
   <table class="notice_tal">
   <tr>
      <th width="30%">제목</th>
      <td align="left"><input type="text" name="rsubject" id="rsubject" size="50%"></td>
   </tr>
   <tr>
      <th>비밀번호</th>
      <td align="left"><input type="password" name="rpasswd" id="rpasswd" size="50%" required></td>
   </tr>

   <tr>
      <th>내용</th>
      <td align="left"><textarea rows="15%" cols="60%" name="rcontent"></textarea></td>
      <!-- <textarea col="5" style="resize: none;" name="rcontent" ></textarea> -->
   </tr>
   <tr>
      <th>파일첨부</th>
      <td align="left">
      <input type="file" name="photonameMF" multiple size="50">
      <br>※ 올릴 사진은 모두 선택해주세요
        <!-- <input type="button" id="button2" value="파일 추가" onclick="addFile()">   
        <div id="filebox"></div> -->
      </td>
   </tr>
   <tr>
   </table>
   </div>
   <br><p align="center">
      <input type="submit"  value=" 등록 " style="background-color: white; color: black; font-size: 15px; width: 100px; height:40px;border-color: black; border-radius: 5px; font-size: 10pt;">
      <input type="button" value=" 목록 " onclick="location.href='Treview.do'">
      <%-- <input type="button" value=" HOME " onclick="location.href=${root}/home.do'"> --%>
   </p><br>
</form>
</c:if>   
<!-- 본 문 끝 !! -->
<%@ include file="../footer.jsp"%>