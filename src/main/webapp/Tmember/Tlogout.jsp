<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>

<c:remove var="memid" scope="session"/>
<c:remove var="s_id" scope="session"/>
<c:remove var="s_mlevel" scope="session"/>
<c:remove var="s_passwd" scope="session"/>
<c:remove var="s_mnum" scope="session"/>
<meta http-equiv="refresh" content="0;url=../home.do">