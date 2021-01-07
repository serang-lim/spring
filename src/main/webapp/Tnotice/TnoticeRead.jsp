<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본 문 시 작 -->
<p><strong>* 글 상세보기 *</strong></p>
<table border="1">
<tr>
	<td bgcolor="${value_c }">글번호</td>
	<td>${dto.nonum }</td>
	<td bgcolor="${value_c }">조회수</td>
	<td>${dto.noreadcnt }</td>
</tr>
<tr>
	<td bgcolor="${value_c }">작성자</td>
	<td>${dto.nowriter }</td>
	<td bgcolor="${value_c }">작성일</td>
	<td>${fn:substring(dto.nodate,0,10)}</td>
</tr>
<tr>
	<td bgcolor="${value_c }">글제목</td>
	<td colspan=3>${dto.nosubject }</td>
</tr>
<tr>
	<td bgcolor="${value_c }">글내용</td>
	<td colspan=3>
    <!-- 
	//pageContext.setAttribute("cr","\r");  	//스페이스
	//pageContext.setAttribute("crcn","\r\n");	//스페이스,엔터
	//pageContext.setAttribute("cn","\n");		//엔터 -->

	<%--${article.content} --%>
	<!-- \n값이 <br/>값으로 바꿔서 출력하게함 -->
	${fn:replace(dto.nocontent,cn,'<br/>') }
	</td>
</tr>
<tr>
	<td colspan=4 bgcolor="${value_c }">
		<input type="button" value="글수정"
			onclick="location.href='./TnoticeUpdateForm.do?nonum=${dto.nonum}&pageNum=${pageNum }'">
	
		<input type="button" value="글삭제" 
		    onclick="location.href='./TnoticeDeleteForm.do?nonum=${dto.nonum}&pageNum=${pageNum }'"> 
		
													
		<input type="button" value="목록"
			onclick="location.href='./TnoticeList.do?pageNum=${pageNum}'">
		</td>
</tr>

</table>

<!-- 본 문 끝 !! -->

<%@ include file="../footer.jsp"%>