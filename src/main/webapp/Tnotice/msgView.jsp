<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본 문 시 작 -->
	<div class="title">알림</div>
	<div class="content">
		<dl>
			<dd>${msg1 != null ? img : "" }${msg1}</dd>
			<dd>${msg2 != null ? img : "" }${msg2}</dd>
			<dd>${msg3 != null ? img : "" }${msg3}</dd>
		</dl>
		<p>
			${link1 } ${link2 } ${link3 }
		</p>
	</div>

<!-- 본 문 끝 !! -->

<%@ include file="../footer.jsp"%>