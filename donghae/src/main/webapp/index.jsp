<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<link rel="stylesheet" type="text/css" media="all" href="css/jquery.bxslider.css"/>
<link rel="stylesheet" type="text/css" media="all" href="css/owl.carousel.min.css"/>
<link rel="stylesheet" type="text/css" media="all" href="css/common.css"/>
<link rel="stylesheet" type="text/css" media="all" href="css/default.css"/>
<link rel="stylesheet" type="text/css" media="all" href="css/main.css"/>
<link rel="stylesheet" type="text/css" media="all" href="css/sub.css"/>
<link rel="stylesheet" type="text/css" media="all" href="css/media.css"/>
<link rel="stylesheet" type="text/css" media="all" href="css/media_sub.css"/>
<link rel="stylesheet" type="text/css" media="all" href="css/jquery-ui.min.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.bxslider.js"></script>
<script type="text/javascript" src="js/owl.carousel.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<link rel=" shortcut icon" href="images/common/favicon.ico">
<link rel="icon" href="images/common/favicon.ico">


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<title> 동 해 지 니 </title>
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
            <c:if test="${empty sessionScope.memid}">  <a href="Tmember/Tlogin.do">로그인</a></c:if>
            <c:if test="${sessionScope.memid!=null}"><a href="Tmember/Tlogout.do">로그아웃</a></c:if>
              &nbsp;
              <a href="Tmember/Tmy.do">| &nbsp; 마이페이지</a>
           </div> 
         </div>
         <!--S:lnb-->
         <nav id="lnb">
         <div id="h_logo">
         <a href="index.do">
            <img src="./images/lamp.png" width="70px">
         </a>
          </div>
            <ul class="lnb">
               <li class="depth1"><a href="Tnotice/TnoticeList.do">공지사항</a>
               </li>
               <li class="depth1"><a href="Troute/Troute.do">여행루트</a>
               </li>
               <li class="depth1"><a href="Treview/Treview.do">후기</a>
               </li>
               <li class="depth1"><a href="Tpromotion/Tpromotion.do">프로모션</a>
               </li>
               <li class="depth1"><a href="Tlikelocation/TlikelocationList.do">관심여행지</a>
               </li>
            </ul>
         </nav>
         <!--//E:lnb-->

      </div>
      <!--//E:header-->
      <!--S:mobile_header-->
      <div class="m_head">
         <div class="menu_bar">
            <p class="logo"><a href="index.do"><img src="images/common/homeicon2.png" alt="홈"/></a></p>
            <p class="all_menu"><a href="#n"><img src="images/common/mobile_allmenu.jpg" alt="전체메뉴"/> </a></p>
         </div>

         <nav class="lnb_wrap">
            <a href="#" class="close"><img src="images/common/mobile_close.jpg" alt="메뉴닫기" /></a>
            &nbsp;
            <div class="lnb_login" align="right">
            <c:if test="${empty sessionScope.memid}">  <a href="Tmember/Tlogin.do">로그인</a></c:if>
            <c:if test="${sessionScope.memid!=null}"><a href="Tmember/Tlogout.do">로그아웃</a></c:if>
                 &nbsp;&nbsp;
                 <a href="Tmember/Tmy.do">마이페이지</a>
              </div>
            <ul class="left_lnb">
               <li>
                  <a href="Tnotice/TnoticeList.do">공지사항</a>
               </li>
               <li>
                  <a href="Troute/Troute.do">여행루트</a>
               </li>
               <li>
                  <a href="Treview/Treview.do">후기</a>
               </li>
               <li>
                  <a href="Tpromotion/Tpromotion.do">프로모션</a>
               </li>
               <li>
                  <a href="Tlikelocation/TlikelocationList.do">관심여행지</a>
               </li>
            </ul>
         </nav>
      </div>
      <!--//E:mobile_header-->
   </header>
   <!--//E:header--><!--//E:header-->
   <!--S:mainvisual-->
   <section id="main">
      <ul class="mvisual">
         <li class="mv1">
            <div class="main_wrap">
               <div class="txt_box">
                  <p class="tite"></p>
               </div>
            </div>
         </li>
         <li class="mv2">
            <div class="main_wrap">
               <div class="txt_box">
                  <p class="tit_thin"></p>
                  <p class="bt_ico"></p>
               </div>
            </div>
         </li>
      </ul>
   </section>
   <!--//E:mainvisual-->

    <!--S:Content
   S:짐캐리 간편이용방법
   <section id="cont01">
       <article class="cont_wrap">
        <h2>짐캐리 <span>간편 이용방법</span></h2>
        <dl>
         <dd>
            <div class="ico"><img src="images/ico/m_ico3.png" alt="온라인예약 아이콘" class="m_ico3"/></div>
            <div class="txt">
               <h4>온라인 예약</h4>
               <p>원하는 서비스를 선택 하고<span>온라인 결제 완료!</span></p>
            </div>
         </dd>
         <dd>
            <div class="ico"><img src="images/ico/m_ico4.png" alt="짐맡기기 아이콘"/></div>
            <div class="txt">
               <h4>짐 맡기기</h4>
               <p>예약일정에 맞추어 <span>짐을 맡겨주세요.</span></p>
            </div>
         </dd>
         <dd>
            <div class="ico"><img src="images/ico/m_ico5.png" alt="짐찾기 아이콘" class="m_ico5"/></div>
            <div class="txt">
               <h4>짐 찾기</h4>
               <p>예약한 장소에 배송된 <span>짐을 찾아주세요.</span></p>
            </div>
         </dd>
        </dl>
       </article>
   </section>
   //E:짐캐리 간편이용방법
   
   S:짐캐리 이벤트
   <section id="cont02">
      <article class="cont_wrap">
         <h2>짐캐리 이벤트</h2>
         S:owl-carousel
         <dl class="event owl-carousel">
                        <dd class="item" onclick="location.href='#n'">
               <div class="thumb" style="background-image:url('/html/images/img/test_img1.jpg');"></div>
               <div class="box">
                  <h4>부산타워 입장권</h4>
                  <p class="data">2019-01-30 ~ 2019-03-31</p>
                  <p class="btn">자세히보기</p>
               </div>
               <div class="event_ico">진행중</div>
            </dd>
            <dd class="item" onclick="location.href='#n'">
               <div class="thumb" style="background-image:url('/html/images/img/test_img2.jpg');"></div>
               <div class="box">
                  <h4>부산시티투어</h4>
                  <p class="data">2019-01-30 ~ 2019-03-31</p>
                  <p class="btn">자세히보기</p>
               </div>
               <div class="event_ico">진행중</div>
            </dd>         </dl>
         //E:owl-carousel
         <div class="more_btn"><a href="/html/sub/03/01.html" class="btn_color">더보기</a></div>
      </article>
   </section>
 -->
   <!--//E:짐캐리 이벤트-->
   <!--//E:Content-->
   <!--S:footer-->   <!--S:footer-->
    
   <footer>

      <section id="address">
         <div class="wrap">
            <!-- <dl class="social">
               <dd>
                  <a href="https://www.facebook.com/zimcarry/" target="_blank">
                     <img src="/html/images/common/facebook.png" alt="페이스북 아이콘"/>
                  </a>
               </dd>
               <dd>
                  <a href="https://www.instagram.com/zim_carry/" target="_blank">
                     <img src="/html/images/common/instra.png" alt="인스타 아이콘"/>
                  </a>
               </dd>
               <dd>
                  <a href="http://blog.naver.com/zimcarry12" target="_blank">
                     <img src="/html/images/common/blog.png" alt="블로그 아이콘"/>
                  </a>
               </dd>
            </dl>
            <p class="f_logo"><img src="/html/images/common/f_logo.jpg" alt="하단로고"/></p> -->
            <div class="adr">
               <p>
               <div align="center">
                  <span> 주식회사 동해지니 /</span>
                  <span> 02-333-4455</span>
               </p>
               <p><span> 서울시 종로구 관철동 코아빌딩 솔데스크 801호<span></p>
               <p class="copyright">Copyright &copy; DongHaeGenie All rights reserved.</p>
               </div>
            </div>
         </div>
      </section>
      <div class="back_top"><a href="#n"><img src="images/common/back_top.png"  alt="위로버튼"/></a></div>

   </footer>
   <!--//E:footer--><!--//E:footer-->
</div>
<!--//E:wrap-->
</body>
</html>