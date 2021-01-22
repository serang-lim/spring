<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<%@ include file="../header.jsp" %>
<script type="text/javaScript" src="../js/treviewscript.js"></script>
<%--<script type="text/javaScript"><%@ include file="../js/treviewscript.js" %></script> --%>
   
<!-- 본문 시작 read.jsp -->
   <h2 align="center">* 게시물 상세보기 *</h2>
   <br>
	<p align="right" style="margin-right:10% ">
	<%-- <a href="../Troute/Read.do?cno=${dto.tno }" style="background-color: white; color: black; font-size: 15px; width: 100px; height:40px;border-color: black; border-radius: 5px; font-size: 10pt;"> 원글 가기</a>--%>
	<input type="button" value="원글 가기" onclick="location.href='../Troute/Read.do?cno=${dto.tno }'"/>
	</p>
  
	<form role="form" method="get">
   	<input type="hidden" name="rid" id="rid" value="${dto.rid}">
   	<input type="hidden" name="rnum" id="rnum" value="${dto.rnum}">
   	<input type="hidden" name="rpasswd" id="uppw" value="${dto.rpasswd}">
 	<div style="margin: 0 10% 0 10%;">
 	<table class="notice_tal">
	
	<tr height="30">   
      <th>작성일</th>
      <td>${dto.rdate}</td>
      <th>조회수</th>
      <td>${dto.rreadcnt}</td>
   </tr>
   <tr height="30">   
      <th>지역</th>
      <td  align="left"><c:set var="rregion" value="${fn:split(dto.rregion,',')}" />
		<c:forEach var="reg" items="${rregion}" varStatus="g">
		   	<c:if test="${fn:split(dto.rregion,',')[g.count-1]=='속초'}"><img src="../images/속초.png" width="30px"></c:if>
		   	<c:if test="${fn:split(dto.rregion,',')[g.count-1]=='강릉'}"><img src="../images/강릉.png" width="30px"></c:if>
		   	<c:if test="${fn:split(dto.rregion,',')[g.count-1]=='삼척'}"><img src="../images/삼척.png" width="30px"></c:if>
		   	<c:if test="${fn:split(dto.rregion,',')[g.count-1]=='동해'}"><img src="../images/동해.png" width="30px"></c:if>
		</c:forEach></td>
   </tr>
   <tr height="30">  
      <th>제목</th>
      <td colspan="3" align="left">${dto.rsubject}</td>
   </tr>
  
	<c:forEach var="dto" items="${list}"> 
	<tr height="30">
	<th>사진</th>
	<td colspan="3" align="left">
        <img src="../storage/${dto.fileName}" width="500">
		<script type="text/javascript">
			var img = document.getElementsByTagName("img");
		    for (var x = 0; x < img.length; x++) {
		      img.item(x).onclick=function() {window.open(this.src)}; 
		    }//for end
		</script>
	 </td>
	 </tr>	    
	</c:forEach>
   <tr height="30">   
      <th>내용</th>
      <td colspan="3" align="left">${dto.rcontent}</td>
   </tr>
  
   	</table>
   	</div>
    </form>
	<br>
	<p align="center">
	<input type="button" value=" 수정 " onclick="location.href='upcheckform.do?rnum=${dto.rnum}'"/>
	<!-- location.href='updateform.do?rnum=${dto.rnum}' -->
	<input type="button" value=" 목록 " onclick="location.href='Treview.do'"/>
    <input type="button" value=" 삭제 " onclick="location.href='delcheckform.do?rnum=${dto.rnum}'"/>
    <!--  location.href='delete.do?rnum=${dto.rnum}'
    -->
	</p><br><br>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %> 