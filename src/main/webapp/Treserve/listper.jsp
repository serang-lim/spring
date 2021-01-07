<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" href="../css/calendar/style.css">
<!-- 본문 시작 listper.jsp-->
	<style>
	th{
		height: 50px;
		background-color: purple;
	}
	
	td{
		height: 50px;
	}
	
	td img{
		height: 50%;
		width: 50%;
	}
	
	</style>

	<div style="text-align: center; "><h1>${sessionScope.s_id }님의 예약현황</h1></div>
	<input type="button" value="prev" onclick="location.href='listper.do?cYear=${prevYear}&cMonth=${prevMonth}'">
	<input type="button" value="next" onclick="location.href='listper.do?cYear=${nextYear}&cMonth=${nextMonth}'">
	<h2>${yearmonth }</h2>
	<table>
	<tr>
		<th>일</th>
		<th>월</th>
		<th>화</th>
		<th>수</th>
		<th>목</th>
		<th>금</th>
		<th>토</th>
	</tr>
	<tr>
	${str}
	</tr>
	</table>
	
	<script>
	function checkdate(year, month, i) {
		const dates=year+"-"+month+"-"+i;
		location.href="listperread.do?dates="+dates;		
	}//checkdate() end	
	</script>
	
<!-- 본 문 끝 !! -->

<%@ include file="../footer.jsp"%>