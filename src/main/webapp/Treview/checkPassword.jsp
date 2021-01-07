<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<script src="../js/treviewscript.js"></script>
<!-- 본 문 시 작 -->
<div>
   <h1 align="center">비밀번호 확인</h1><br>
   <form name="checkfrm" method="post" onsubmit="return pwCheck2()" >
   <input type="hidden" name="rnum" value="${dto.rnum}">
       <table>
           <tr>
               <th> 비밀번호 </th>
               <td>
                   <input type="password" name="rpasswd" size="20" required>
                   <input type="submit" value="확인">
                   <input type="button" value="뒤로가기" onclick="location.href=back"/>
               </td>
           </tr>
       </table><br>
   </form>
</div>
<!-- 본 문 끝 !! -->

<%@ include file="../footer.jsp"%>