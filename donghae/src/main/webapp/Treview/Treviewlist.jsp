<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%@ include file="../header.jsp" %>

<!-- 본문 시작 Treview.jsp -->
   <h3 align="center">후기</h3>
   <p><a href="TreviewcreateForm.jsp">[작성하기]</a></p>
   <table border='1' class="table_list">
   <tr>
      <th>조회수</th>
      <th>글번호</th>
      <th>제목</th>
      <th>지역</th>
      <th>작성자</th>
      <th>작성일</th>
   </tr>
   <c:forEach var="dto" items="${list}">
   <tr>
   	  <td>${dto.rreadcnt}</td>
      <td>${dto.rnum}</td>
      <td><a href="read.do?rnum=${dto.rnum}">${dto.rsubject}</a></td>
      <td>${dto.rregion}</td>
      <td>${dto.rid}</td>
      <td>${dto.rdate}</td>
   </tr>
   </c:forEach>
   </table>

   <div class='bottom'>
   <input type='button' value='HOME' onclick="location.href='${root}/index.do'">
   </div>   
   
   <form action="Treview2.do" align="center">
       <select name="col1">
          <option value="0">지역선택
          <option value="강릉">강릉
          <option value="속초">속초
          <option value="삼척">삼척
          <option value="동해">동해      
       </select>
       <input type="text" name="rid">
       <input type="submit" value="검색">
    </form>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %> 