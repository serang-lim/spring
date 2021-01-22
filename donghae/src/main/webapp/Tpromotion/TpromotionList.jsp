<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp"%>
<!-- 본 문 시 작 -->
<!-- 페이지 리스트 -->

<br>
<c:choose>
<c:when test="${s_mlevel=='A1'}">
<h3 align="center"> 프 로 모 션 </h3>
<form name="listfrm">
			
	<div align="right"><input type="button" value=" 삭 제 " onclick="return del()"> </div>
	
	<table class="notice_tal">
	
	<tr>
		<th width="10%">체크</th>
		<th width="20%">분류</th>
		<th width="10%">글번호</th>
		<th width="20%">사진</th>
		<th width="10%">지역</th>
		<th width="30%">제목</th>
		<th width="15%">작성자</th>
		<th width="15%">작성일</th>
		<th width="15%">Y/N</th>
		
	</tr>
	
	<c:forEach var="dto" items="${list}" varStatus="status">
	<tr align="center">
		<td><input type="checkbox" name="tbno${status.count}" value="${dto.tbno}" ></td>
		<td>
			<c:if test="${dto.ticon=='숙소'}"><img src="../images/zam.png" width="30%"></c:if>
			<c:if test="${dto.ticon=='주위식당'}"><img src="../images/bob.png" width="30%"></c:if>
			<c:if test="${dto.ticon=='주요행사'}"><img src="../images/fe.png" width="30%"></c:if>
			<c:if test="${dto.ticon=='액티비티'}"><img src="../images/act.png" width="30%"></c:if>
		 	<c:if test="${dto.ticon=='사진스팟'}"><img src="../images/sajin.png" width="30%"></c:if>
			
		</td>
		<td>${dto.tbno}</td>
		<td><img src="../storage/${dto.timage_name}" width="60%" height="auto"></td>
		<td>${dto.tregion}</td>
		<td><a href="TpromotionRead.do?tbno=${dto.tbno}">${dto.tsubject}</a></td>
		<td>${dto.twriter}</td>		
		<td style="font-size:12pt;">${fn:substring(dto.twdate,0,10)}</td>
		<td>${dto.tresult }</td>
	</tr>	
	<c:if test="${status.last=='true'}">
    <input type="hidden" id="number" value="${status.count}">
    </c:if>
	</c:forEach>
	
	</table>	
</form>
	<br><br><br>
<script>
function del() {
	var number=document.getElementById('number').value;
	var tbnos="";
	var indextbno = false;
	for(i=1; i<=number; i++){
		//alert(document.getElementsByName('tbno'+i+'')[0].checked);
		//alert(document.getElementsByName('tbno'+i+'')[0].value);
		if(document.getElementsByName('tbno'+i+'')[0].checked==true){
		 	if(indextbno){
		 		tbnos = tbnos + '-';
		   	}
		 	tbnos = tbnos + document.getElementsByName('tbno'+i+'')[0].value;
		 	indextbno = true;
		}
	}
	location.href="TproDel.do?tbnos="+tbnos;
}
</script>	
	
		<!-- 검색 시작  -->

			<form action="Tpromotion2.do" align="center" method="post">
			
				<select name="col1">
					<option value="x" >지역선택
					<option value="강릉" ${dto.tregion.equals("강릉") ?"selected" : ""}>강릉
					<option value="속초" ${dto.tregion.equals("속초") ?"selected" : ""}>속초
					<option value="삼척" ${dto.tregion.equals("삼척") ?"selected" : ""}>삼척
					<option value="동해" ${dto.tregion.equals("동해") ?"selected" : ""}>동해		
				</select>
				<select name="col2">
					<option value="x">분류선택
					<option value="숙소">숙소
					<option value="주요행사">주요행사
					<option value="주위식당">주위식당
					<option value="액티비티">액티비티
				</select>
				<input  type="submit" value="검색" style="background-color: white; color: black; font-size: 15px; width: 70px; height:22px;border-color: black; border-radius: 5px; font-size: 10pt;">
			</form>
	
	<div align="center" style="padding: 1%  1%  1%  1%;">
		<!-- 등록버튼 기업만 보이게 설정 -->
		<c:if test="${sessionScope.s_mlevel =='B1'||sessionScope.s_mlevel =='A1'}">
		<!-- <input type="button" value="프로모션 등록하기" onclick="location.href='TpromotionWrite.do'"> -->
		</c:if>
		<input type="button" value="메인으로" onclick="location.href='${root}/home.do'">
	</div>
	<br><br><br>	
	
</c:when>



<c:otherwise>
<h1 style="margin-left: 6.55%">프로모션</h1>
<hr width="85%" style="padding: 0 0 0 2%">
<section id="cont">
<article class="sub_wrap">

<div class="wrap" align="center">
   <dl class="event event_list" style="padding: 0% 0% 0% 3%;">
   <c:forEach var="dto" items="${list}">

   <dd class="item" style="margin: 3% 3% 0% 3%">
      <div align="center" class="thumb" style="background-image:url('../storage/${dto.timage_name}');"></div><!-- 사진경로주기 -->
      <div class="box"><h3>${dto.tsubject}</h3>
      <p class="btn" onclick="location.href='TpromotionRead.do?tbno=${dto.tbno}'">자세히보기</p>
      </div>
   </dd>
   </c:forEach>
   </dl>
      <!-- 등록버튼 기업만 보이게 설정 -->
      <c:if test="${sessionScope.s_mlevel =='B1'}">
      <input type="button" value="나의 프로모션" onclick="location.href='TpromotionMy.do?twriter=${s_id}'">
      <input type="button" value="프로모션 등록" onclick="location.href='TpromotionWrite.do'">
      </c:if>
      <input type="button" value="메인으로" onclick="location.href='${root}/home.do'">

</div>
</article>
</section>
</c:otherwise>
</c:choose>
<br>
	
<!-- 본 문 끝 !! -->
<%@ include file="../footer.jsp"%>