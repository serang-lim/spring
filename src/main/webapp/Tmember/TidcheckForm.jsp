<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TidcheckForm.jsp </title>
</head>
<body>
<div style="text-align: center">

	<h3>* 아이디 중복확인 *</h3>

	<form 	name="memfrm"
	
			method="post"

			action="TidcheckPro.do"

			id="memfrm"
			
			onsubmit="return blankCheck()">

	아이디 : 	<input type="text" name="Mid" id="Mid" maxlength="10" autofocus>

		   	<input type="submit" value="중복확인">

	</form>

</div>

<script>

	function blankCheck(){

		var id=document.getElementById("Mid").value;

		id=id.trim();

		if(id.length<5){

			alert("아이디는 5글자 이상 입력해 주세요");

			return false;

		}//if end

		return true;

	}//blankCheck() end

</script>

</body>
</html>