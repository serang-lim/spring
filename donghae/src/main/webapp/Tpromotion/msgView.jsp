<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<title>Tpromotion/msgView.jsp</title>
</head>
<body>
	<div align="center">알림</div>
	<div align="center">
	<dl>
		<dd>${msg1 != null ? img : ""}${msg1}</dd>
		<dd>${msg2 != null ? img : ""}${msg2}</dd>
		<dd>${msg3 != null ? img : ""}${msg3}</dd>	
	</dl>
	<p>
		${link1} ${link2} ${link3}
	</p>
	</div>
</body>
</html>