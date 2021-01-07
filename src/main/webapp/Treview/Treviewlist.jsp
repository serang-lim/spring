<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%@ include file="../header.jsp" %>
<c:url var="getBoardListURL" value="/Treview/Treviewlist"></c:url><script>
   // 생략	
	$(document).on('click', '#btnSearch', function(e){
		e.preventDefault();
		var url = "${getBoardList}";
		url = url + "?searchType=" + $('#searchType').val();
		url = url + "&keyword=" + $('#keyword').val();
		location.href = url;
		console.log(url);
	});	

</script>
 <%--
 <%
	// 저장된 쿠키 불러오기
	Cookie[] cookieFromRequest = request.getCookies();
	String cookieValue = null;
	for(int i = 0 ; i<cookieFromRequest.length; i++) {
		// 요청정보로부터 쿠키를 가져온다.
		cookieValue = cookieFromRequest[0].getValue();	// 테스트라서 추가 데이터나 보안사항은 고려하지 않으므로 1번째 쿠키만 가져옴	
	}

	// 글 목록 -> 글 상세 : 글번호(seq)
 	String seq = request.getParameter("seq");
 	
 	// 쿠키 세션 입력
	if (session.getAttribute(seq+":cookie") == null) {
	 	session.setAttribute(seq+":cookie", seq + ":" + cookieValue);
	} else {
		session.setAttribute(seq+":cookie ex", session.getAttribute(seq+":cookie"));
		if (!session.getAttribute(seq+":cookie").equals(seq + ":" + cookieValue)) {
		 	session.setAttribute(seq+":cookie", seq + ":" + cookieValue);
		}
	}
 
	TreviewDTO dto = new TreviewDTO();
 	dto.setSeq(Integer.parseInt(seq));
 	
 	TreviewDAO boardDAO = new TreviewDAO();
 	// 글 상세 조회
 	TreviewDTO  board    = TreviewDAO.getBoard(dao);

 	// 조회수 카운트
 	if (!session.getAttribute(seq+":cookie").equals(session.getAttribute(seq+":cookie ex"))) {
 		TreviewDAO.updateBoardCnt(dao);
	 	// 가시적으로  조회수 1 추가해줌
	 	board.setCnt(board.getCnt() + 1);
 	}
 	
 	//System.out.println("중복방지 111 = " + session.getAttribute(seq+":cookie") );
 	//System.out.println("중복방지 222 = " + session.getAttribute(seq+":cookie ex") );
 	//System.out.println("중복방지 333 = " + session.toString() );
 	//for(int i = 0; i < session.getValueNames().length; i++){
 	//	System.out.println("중복방지 444 = " + session.getValueNames()[i].toString() );
 	//}
 	
 %>  
 --%>
 <style>
 .table_list{
  width: 80%
  margin-left: 500px;
  margin-right: 500px;
}
</style>
<!-- 본문 시작 Treview.jsp -->
   <h3 align="center">후기</h3>
   <p><a href="Treviewcreate.jsp">[작성하기]</a></p>
   <table border='1' align="center">
   <tr>
      <th>글번호</th>
      <th>지역</th>
      <th>제목</th>
      <th>작성자</th>
      <th>작성일</th>
      <th>조회수</th>
   </tr>
   <c:forEach var="dto" items="${list}">
   <tr>
      <td>${dto.rnum}</td>
      <td>${dto.rregion}</td>
      <td><a href="read.do?rnum=${dto.rnum}">${dto.rsubject}</a></td>
      <td>${dto.rid}</td>
      <td>${dto.rdate}</td>
      <td>${dto.rreadcnt}</td>
   </tr>
   </c:forEach>
   </table>

   <div class='bottom'>
   <input type='button' value='HOME' onclick="location.href='${root}/index.do'">
   </div>   
	<form action="Treview2.do" align="center">
         <select name="col">
            <option value="0">지역선택
            <option value="강릉">강릉
            <option value="속초">속초
            <option value="삼척">삼척
            <option value="동해">동해      
         </select>
         <input type="text" name="word" placeholder="아이디검색">
         <input type="submit" value="검색">
      </form>

		
<!-- 페이지 리스트 --> 
 <c:if test="${count>0 }"> 
    <c:set var="pageCount" value="${totalPage }"/> 
    <c:set var="startPage" value="${startPage }"/> 
    <c:set var="endPage" value="${endPage }"/> 
  
    <c:if test="${endPage>pageCount }"> 
    <c:set var="endPage" value="${pageCount+1 }"/> 
    </c:if> 
  
    <c:if test="${startPage>0 }"> 
       <a href="./Treview.do?rnum=${startPage }">[이전]</a>  
    </c:if> 
  
    <c:forEach var="i" begin="${startPage+1 }" end="${endPage-1 }"> 
       <a href="./Treview.do?rnum=${i }">[${i }]</a> 
    </c:forEach> 
  
    <c:if test="${endPage<pageCount }"> 
       <a href="./Treview.do?rnum=${startPage+11 }">[다음]</a> 
    </c:if>   
 </c:if> 
 		
	 
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %> 