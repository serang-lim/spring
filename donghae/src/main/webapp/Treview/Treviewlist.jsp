<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%@ include file="../header.jsp" %>

<!-- 본문 시작 Treview.jsp -->

   <br><p><a href="createrform.do">[작성하기]</a></p>
   <table border="1" align="center" width='70%' >
   <tr height="20">
      <!-- <th>글번호</th>  -->
      <th>지역</th>
      <th>제목</th>
      <th>작성자</th>
      <th>작성일</th>
      <th>조회수</th>
   </tr>
   <c:forEach var="dto" items="${list}">
   <tr height="30">
      <!--  <td align="center">${dto.rnum}</td> -->
      <td align="center">${dto.rregion}</td>
      <td align="center"><a href="read.do?rnum=${dto.rnum}">${dto.rsubject}</a></td>
      <td align="center">${dto.rid}</td>
      <td align="center">${dto.rdate}</td>
      <td align="center">${dto.rreadcnt}</td>
   </tr>
   </c:forEach>
   </table>
	<br>
   
   <%-- <div class='bottom'>
   <input type='button' value=' HOME ' onclick="location.href='${root}/index.do'">
   </div> --%>
   <div align="center">
   <form action="Treview2.do">
         <select name="col">
            <option value="0">지역선택
            <option value="강릉">강릉
            <option value="속초">속초
            <option value="삼척">삼척
            <option value="동해">동해      
         </select>
         <input type="text" name="word" placeholder="작성자검색">
         <input type="submit" value="검색">
     </form>
     </div><br>
     
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %> 