<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<link rel="stylesheet" type="text/css" media="all" href="../css/jquery.bxslider.css"/>
<link rel="stylesheet" type="text/css" media="all" href="../css/owl.carousel.min.css"/>
<link rel="stylesheet" type="text/css" media="all" href="../css/common.css"/>
<link rel="stylesheet" type="text/css" media="all" href="../css/default.css"/>
<link rel="stylesheet" type="text/css" media="all" href="../css/main.css"/>
<link rel="stylesheet" type="text/css" media="all" href="../css/sub.css"/>
<link rel="stylesheet" type="text/css" media="all" href="../css/media.css"/>
<link rel="stylesheet" type="text/css" media="all" href="../css/media_sub.css"/>
<link rel="stylesheet" type="text/css" media="all" href="../css/jquery-ui.min.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/owl.carousel.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<link rel=" shortcut icon" href="../images/common/favicon.ico">
<link rel="icon" href="../images/common/favicon.ico">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title> 동 해 지 니 </title>
 <link rel="shortcut icon" href=></link> 
</head>
<body>
<!--S:wrap-->
<div id="wrap">
   <!--S:header-->   <!--S:header-->
   <header>
      <!--S:header-->
      <div id="header">
        <div>
           <div style=float:right;>
           <c:if test="${empty sessionScope.memid}">  <a href="../Tmember/Tlogin.do">로그인</a></c:if>
            <c:if test="${sessionScope.memid!=null}"><a href="../Tmember/Tlogout.do">로그아웃</a></c:if>
              &nbsp;
              <a href="../Tmember/Tmy.do">| &nbsp; 마이페이지</a>
           </div> 
        </div>
         <!--S:lnb-->
         <nav id="lnb">
         <div id="h_logo">
         <a href="../index.do">
            <img src="../images/lamp.png" width="70px">
         </a>
        </div>
            <ul class="lnb">
            
               <li class="depth1"><a href="../Tnotice/TnoticeList.do">공지사항</a>
               </li>
               <li class="depth1"><a href="../Troute/Troute.do">여행루트</a>
               </li>
               <li class="depth1"><a href="../Treview/Treview.do">후기</a>
               </li>
               <li class="depth1"><a href="../Tpromotion/Tpromotion.do">프로모션</a>
               </li>
             <li class="depth1"><a href="../Tlikelocation/TlikelocationList.do">관심여행지</a></li>
            </ul>
         </nav>
         <!--//E:lnb-->

      </div>
      <!--//E:header-->
      <!--S:mobile_header-->
      <div class="m_head">
         <div class="menu_bar">
            <p class="logo"><a href="../index.do"><img src="../images/common/homeicon2.png" alt="홈"/></a></p>
            <p class="all_menu"><a href="#n"><img src="../images/common/mobile_allmenu.jpg" alt="전체메뉴"/> </a></p>
         </div>

         <nav class="lnb_wrap">
            <a href="#" class="close"><img src="../images/common/mobile_close.jpg" alt="메뉴닫기" /></a>
            &nbsp;
            <div class="lnb_login" align="right">
            <c:if test="${empty sessionScope.memid}">  <a href="../Tmember/Tlogin.do">로그인</a></c:if>
            <c:if test="${sessionScope.memid!=null}"><a href="../Tmember/Tlogout.do">로그아웃</a></c:if>
                 &nbsp;&nbsp;
                 <a href="../Tmember/Tmy.do">마이페이지</a>
              </div>
            <ul class="left_lnb">
               <li>
                  <a href="../Tnotice/TnoticeList.do">공지사항</a>
               </li>
               <li>
                  <a href="../Troute/Troute.do">여행루트</a>
               </li>
               <li>
                  <a href="../Treview/Treview.do">후기</a>
               </li>
               <li>
                  <a href="../Tpromotion/Tpromotion.do">프로모션</a>
               </li>
               <li>
                 <a href="../Tlikelocation/TlikelocationList.do">관심여행지</a>
               </li>
            </ul>
         </nav>
      </div>
      <!--//E:mobile_header-->
   </header>
   <div>
   <hr>