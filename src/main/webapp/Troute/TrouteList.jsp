<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<%@ include file="/header.jsp"%>

<!-- 본 문 시 작 -->
	<div align="center">
	<c:if test="${tregion==null}">
	<img src="../images/지도5.jpg" usemap="#zido">
	</c:if>
	<c:if test="${tregion!=null}">
	<img src="../images/지도_${tregion}.jpg" usemap="#zido">
	</c:if>
	</div>
	<map name="zido" id="zido">
	<area shape="poly" coords="376,169,384,160,437,161,437,181,419,181,415,188,414,188,393,188,376,169" href="Troute.do?tregion=속초">
	<area shape="poly" coords="418,303,419,286,425,284,432,277,451,278,453,273,486,273,486,289,553,359,544,375,553,386,544,386,542,404,529,404,523,410,518,406,501,405,501,388,488,389,486,411,478,417,451,401,443,404,434,397,437,392,440,385,447,386,455,377,456,366,460,359,461,334,448,321,423,320,418,303" href="Troute.do?tregion=강릉">
	<area shape="poly" coords="521,413,526,412,530,407,544,406,547,389,553,389,564,398,564,430,568,435,565,440,535,440,532,446,515,446,514,438,522,431,521,413" href="Troute.do?tregion=동해">
	<area shape="poly" coords="497,446,514,449,534,449,538,442,567,442,570,438,597,464,598,492,619,514,618,568,588,569,585,589,570,588,555,570,545,568,545,545,541,542,528,541,526,526,524,484,504,483,503,510,494,514,486,507,486,456,495,455,497,446" href="Troute.do?tregion=삼척">
	
	</map>



<table  border="1" align="center" style="font-size:15pt;">
	
	<tr>
	    <th>분류</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<c:forEach var="dto" items="${list}">
	<tr align="center">
		<td>${dto.ticon}</td>
		<td>${dto.tsubject}</td>
		<td>${dto.tid}</td>
		<td>${dto.tdate}</td>
		<td>${dto.treadcnt}</td>		
		
	</tr>	
	</c:forEach>
	
  <tr>
  
  
</table>

 
 
 
 
 
 

<!-- 본 문 끝 !! -->

<%@ include file="/footer.jsp"%>