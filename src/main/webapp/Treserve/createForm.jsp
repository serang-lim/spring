<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%@ include file="../header.jsp"%>
<link rel="stylesheet" href="../css/jquery-ui-1.12.1/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="../js/jquery-ui-1.12.1/jquery-ui.js"></script>
<script src="../js/jquery-ui-1.12.1/datepicker-ko.js"></script>
<script src="../js/check/jquery.cookie.js"></script>
<!-- <a href="Treserve/create.do?Tbno=1">예약하기</a> -->
<!-- 본문 시작 CreateForm.jsp-->
<c:if test="${empty sessionScope.memid }">
<script>
alert("로그인 해주세요");
</script>
<meta http-equiv="refresh" content="0;url=../Tmember/Tlogin.do">

</c:if>
<c:if test="${sessionScope.memid !=null }">
<p><strong>예약페이지</strong>

<form name="createform" id="createform" method="post" 
		action="createproc.do?Tbno=${prodto.tbno }"
		onsubmit="return send()">
<table class="table" border="1">
<tr>
	<th>글제목</th>
	<td>${prodto.tsubject }</td>
</tr>	
<tr>
	<th>글내용</th>
	<td><p><img src="../storage/${prodto.timage_name }" style="height:50%; width:50%" ></p></td>

</tr>
<tr>
	<th>날짜지정</th>
	<td>
	<input type="text" name="resdate" id="resdate" size="12" readonly/>
	<input type="button" value="달력" onclick="$('#resdate').datepicker('show');" />
	&nbsp;&nbsp;
	<input type="button" value="예약중복확인" id="btn_reserve">
	</td>
</tr>
<tr>
	<td colspan="2">
	<input type="submit" value="예약하기">	
	<input type="button" value="돌아가기" onclick="javascript:history.back()">	
	</td>
</tr>
</table>
</form>

<script type="text/javascript">
	/* var img = document.getElementsByTagName("img");
    for (var x = 0; x < img.length; x++) {
      img.item(x).onclick=function() {window.open(this.src)}; 
    }//for end */
    
    $(function(){
	    $("#resdate").datepicker({
	    	onSelect:function(dateText, inst) {
	            console.log(dateText);
	            console.log(inst);
	        }
	    });
	});
	$( "#datepicker" ).datepicker();
	
	$(function() {
		$.removeCookie("check");
	});
	
	$("#btn_reserve").click(function(){
		//session=req.getSession();
		//var id	=(String)session.getAttribute("s_id");
		//int Tbno	=prodto.getTbno();
		var Tbno=${prodto.tbno}
		//var resdate= "resdate='" + $("#resdate").val().trim() +"'";
		var resdate= "resdate=" + $("#resdate").val().trim();
		var params="Tbno="+Tbno+"&"+resdate;
		//alert(params);
		$.post("duplecate.do", params , check, "json");
	});//click end
	
	function check(result) {
		var resdate= $("#resdate").val().trim();
		var count=eval(result.count);//형변환
		//alert(result.count);
		//alert(resdate);
		if(resdate==""){
			alert("예약 날짜를 선택해주세요");
		}else{
			if(count==0){
				$.cookie("check","PASS");
				alert("예약가능합니다");
			}else{
				$.removeCookie("check");
				alert("중복된 예약이 존재합니다. 마이페이지를 확인해주세요");
			}//if end
		}//if end		
	}//check()

	function send() {
		//쿠키변수값 가져오기
		var check=$.cookie("check");
		if(check=="PASS"){
			return true;
		}else{
			alert("중복확인 해주세요");
			return false;
		}//if end 
	}//send() end 
	
</script>
</c:if>
<!-- 본 문 끝 !! -->

<%@ include file="../footer.jsp"%>