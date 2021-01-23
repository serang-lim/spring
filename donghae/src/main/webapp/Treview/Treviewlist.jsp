<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%@ include file="../header.jsp" %>

<!-- 본문 시작 Treview.jsp -->
   <br>
   <h1 align="left" style="margin-left: 10%">여행 후기</h1>
   <hr width="80%">
   <section id="cont">
   <article>
   <div class="wrap" align="center" style="margin:0% 10% 5% 10%" >

   <br>
   <table class="notice_tal" style="padding:0% 20% 0% 20%">
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
      <td><c:set var="rregion" value="${fn:split(dto.rregion,',')}" />
      <c:forEach var="reg" items="${rregion}" varStatus="g">
            <c:if test="${fn:split(dto.rregion,',')[g.count-1]=='속초'}"><img src="../images/속초.png" width="30px"></c:if>
            <c:if test="${fn:split(dto.rregion,',')[g.count-1]=='강릉'}"><img src="../images/강릉.png" width="30px"></c:if>
            <c:if test="${fn:split(dto.rregion,',')[g.count-1]=='삼척'}"><img src="../images/삼척.png" width="30px"></c:if>
            <c:if test="${fn:split(dto.rregion,',')[g.count-1]=='동해'}"><img src="../images/동해.png" width="30px"></c:if>
      </c:forEach></td>
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
         <input type="submit" value="검색" style="background-color: white; color: black; font-size: 15px; width: 70px; height:22px;border-color: black; border-radius: 5px; font-size: 10pt;">
     </form>
     </div>
     <br>
     </div>
</article>
</section>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %> 