<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>TemailCheckForm.jsp</title>

</head>

<body>

<div style="text-align: center">

	<h3>* 이메일 중복확인 *</h3>

	<form 	name="memfrm"
			
			id="memfrm"
	
			method="post"

			action="TemailcheckPro.do"

			onsubmit="return blankCheck()">

	이메일 : 	<input type="text" name="Memail" id="Memail" maxlength="30" autofocus>

		   	<input type="submit" value="중복확인">

	</form>

</div>

 

<script>

	function blankCheck(){

		var email=document.getElementById("Memail").value;

		email=email.trim();

		if(email.length<5){

			alert("email은 5글자 이상 입력해 주세요");

			return false;

		}//if end

		return true;

	}//blankCheck() end

</script>

</body>

</html>