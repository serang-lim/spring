<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
	<div style="margin: 10% 10% 10% 10%">
	<div align="center">[ 알 림 ]</div>
	<br>
	<div align="center">
	<dl>
		<dd>${msg1 != null ? img : ""}${msg1}</dd>
		<br>
		<dd>${msg2 != null ? img : ""}${msg2}</dd>
		<br>
		<dd>${msg3 != null ? img : ""}${msg3}</dd>	
		<br>
	</dl>
	<div>
		${link1} ${link2} ${link3}
	</div>
	</div>
	</div>
<%@ include file="../footer.jsp"%>