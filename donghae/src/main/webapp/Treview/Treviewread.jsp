<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<%@ include file="../header.jsp" %>
<%--<script type="text/javaScript" src="../js/treviewscript.js"></script>
<script type="text/javaScript"><%@ include file="../js/treviewscript.js" %></script> --%>
   
<!-- 본문 시작 read.jsp -->
   <h3 align="center">* 게시물 상세보기 *</h3>
   <br>
	<p align="center">
	<a href="createrform.do">[새로 작성하기]</a>
	&nbsp;&nbsp;
	<a href="Treview.do">[글목록]</a>
	</p>
	<br><br>
   <table class="table_view">
	<form role="form" method="get">
   	<input type="hidden" name="rid" id="rid" value="${dto.rid}">
   	<input type="hidden" name="rnum" id="rnum" value="${dto.rnum}">
   	<input type="hidden" name="rpasswd" id="uppw" value="${dto.rpasswd}">
 	
	</form>
	<tr height="30">   
      <th>작성일</th>
      <td>${dto.rdate}</td>
      <th>조회수</th>
      <td>${dto.rreadcnt}</td>
   </tr>
   <tr height="30">  
      <th>제목</th>
      <td>${dto.rsubject}</td>
   </tr>
   <tr height="30">   
      <th>지역</th>
      <td>${dto.rregion}</td>
   </tr>
	<c:forEach var="dto" items="${list}"> 
	<tr height="30">
	<th>사진</th>
	<td>
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
      <td>${dto.rcontent}</td>
   </tr>
   
   </table>
	<br>
	<p align="center">
	<input type="button" id="button1" value=" 수정 " onclick="updatepw()"/>
	<!-- location.href='updateform.do?rnum=${dto.rnum}' -->
    <input type="button" value=" 삭제 " onclick="location.href='checkpawdform.do?rnum=${dto.rnum}'"/>
    <!-- location.href='delete.do?rnum=${dto.rnum}' -->
	</p><br><br>
	
<script type="text/javaScript">
function updatepw(){
	//url저장;
	//var url = "location.href='updateform.do?rnum='${dto.rnum}";
	//기존 패스워드;
	var pwd = document.getElementById('uppw').value;
	//$('#uppw').val()
	//document.getElementById('uppw').value
	//패스워드 확인 창 
	//alert(pwd);
	var getpwd = prompt('비밀번호를 입력하세요');
	if(getpwd != pwd){
		alert("비밀번호가 틀렸습니다.");
	}else{
		var result = confirm('정말 수정하시겠습니까? 수정 시 기존 파일은 삭제됩니다');
	     if(true){
	     alert("비밀번호 확인되었습니다.")
	     //location.href ='updateform.do?rnum='${dto.rnum}';
	 	}else{
	    	history.back(-1);
		 }//if end
		 //alert("확인되었습니다.");	
	}//if end
	}//updatepw() end
</script>
   
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %> 