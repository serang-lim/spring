<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>


<!-- 본문 시작 modifyForm.jsp-->

* 회원 정보 수정 *<br><br>

<form name="Tmemfrm" id="Tmemfrm" 

	  method="post"

	  action="./TmodifyPro.do" 

	  onsubmit="return membercheck()"><!-- myscript.js -->

<span style="color:red; font-weight: bold">* 필수입력</span>

<br>

<table class="table">
<tr>
   <th>*아이디</th>
   <td>
      <input type="text" name="Mid" id="Mid" value="${Mid }" size="15"  readonly>
   </td>
</tr>
<tr>
   <th>*비밀번호</th>
   <td><input type="password" name="Mpasswd" value="${Mpasswd }" id="Mpasswd" size="15" required></td>
</tr>
<tr>
   <th>*비밀번호 확인</th>
   <td><input type="password" name="repasswd" id="repasswd" value="${Mpasswd }" size="15" required></td>
</tr>
<tr>
   <th>*이름</th>
   <td><input type="text" name="Mname" id="Mname" value="${Mname }" size="15" required></td>
</tr>
<tr>
   <th>*이메일</th>
   <td>
      <input type="email" name="Memail" id="Memail" value="${Memail }" size="30" readonly>
      <input type="button" value="Email 중복확인" onclick="emailCheck()">   
   </td>
</tr>
<tr>
   <th>전화번호</th>
   <td><input type="text" name="Mtel" id="Mtel" value="${Mtel }" size="15"></td>
</tr>
<tr>
   <th>우편번호</th>
   <td>
      <input type="text" name="Mzipcode" id="Mzipcode" value="${Mzipcode }" size="7"  readonly>
      <input type="button" value="주소찾기"  onclick="DaumPostcode()">   
   </td>
</tr>
<tr>  
  <th>주소</th>
  <td><input type="text" name="Maddress1" id="Maddress1" value="${Maddress1}" size="45" readonly></td>
</tr>
<tr>  
  <th>나머지주소</th>
  <td><input type="text" name="Maddress2" id="Maddress2" value="${Maddress2}" size="45"></td>
</tr>

<tr>  

  <th>사업자번호</th>
  <td><input type="text" name="Mnum" id="Mnum" value="${Mnum }" size="45"></td>

</tr>

<c:if test="${sessionScope.s_mlevel != 'A1' }">
<tr>  
  <th>회원분류</th>
  <td><select name="Mlevel"  id="Mlevel">  
          <option value="E"   ${Mlevel.equals("0") ?"selected" : ""}>선택하세요.</option>
          <option value="B1" ${Mlevel.equals("B1") ?"selected" : ""}>기업</option>
          <option value="C1" ${Mlevel.equals("C1") ?"selected" : ""}>회원</option> 
        </select>
  </td>
</tr>
</c:if>
<tr>
   <td colspan="2">
      <input type="submit" value="수정하기"  class="btn btn-primary"/>
      <input type="button"  value="취소"  class="btn btn-primary" onclick="location.href='../home.do'"/>
   </td>
</tr>
</table>

<!-- ----- DAUM 우편번호 API 시작 ----- -->
<div id="Mwrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 110px;position:relative">
  <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
</div>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 화면을 넣을 element
    var element_wrap = document.getElementById('Mwrap');

    function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap.style.display = 'none';
    }

    function DaumPostcode() {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('Mzipcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('Maddress1').value = fullAddr;

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap.style.display = 'none';

                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
                
                $('#Maddress2').focus();
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
        }).embed(element_wrap);

        // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
    }
    
    
    function membercheck(){
    	//회원가입 유효성검사
    	//1)아이디 5~10글자 인지
    	var Mid=document.getElementById("Mid").value; //작성자 가져오기
    	Mid=Mid.trim();		//좌우 공백 제거하기
    	if(!(Mid.length>=5 && Mid.length<=10)){
    		alert("id 5~10글자 이내");
    		document.getElementById("Mid").focus();
    		return false;		//전송하지 않음
    	}//if end

    	//2)비밀번호 5~10글자 인지
    	var Mpasswd=document.getElementById("Mpasswd").value;
    	Mpasswd=Mpasswd.trim();
    	if(!(Mpasswd.length>=5 && Mpasswd.length<=10)){
    		alert("비밀번호 5~10글자이내");
    		document.getElementById("Mpasswd").focus();
    		return false;
    	}//if end

    	//3)비밀번호와 비밀번호 확인이 서로 일치하는지
    	var repasswd=document.getElementById("repasswd").value;
        repasswd=repasswd.trim();
       if(Mpasswd!=repasswd){
       alert("비밀번호와 비밀번호 확인이 같지 않습니다.");
       document.getElementById("repasswd").focus();      
       return false;
       }//if end
       
    	//4)이름 1~20글자 인지?
    	var Mname=document.getElementById("Mname").value;
    	Mname=Mname.trim();
    	if(!(Mname.length>=1 && Mname.length <=20)){
    		alert("이름을 정확히 써주세요");
    		document.getElementById("Mname").focus();
    		return false;
    	}
    	//5)이메일 5글자 이상인지
    	var Memail=document.getElementById("Memail").value;
    	Memail=Memail.trim();
    	if(Memail.length <=4){
    		alert("이메일 5글자 이상 써주세요")
    		document.getElementById("Memail").focus();
    		return false;
    	}
    	//6)회원등급선택
    	var Mlevel=document.getElementById("Mlevel").value;
    	Mlevel=Mlevel.trim();
    	if(Mlevel=="E"){
    		alert("회원등급 선택 요망");
 
    		return false;
    	}
    	//7) 기업선택시 사업자번호 넣게하기
    	var Mnum=document.getElementById("Mnum").value;
    	Mnum=Mnum.trim();
    	if(Mlevel=="B1"){
    		if(Mnum==""){
    			alert("사업자번호를 작성해주세요");
    			return false;
    		}
     	}
    	//8) 회원선택 시 사업자번호 생략시키기
    	
    	if(Mlevel=="C1"){
    		if(Mnum!=""){
    			alert("기업만 사업자번호 작성바랍니다.");
    			return false;
    		}
     	}
    	return true;
    }//memberCheck end

    function idCheck(){
    	//id 중복확인
    	
    	//부트스트랩 모달창
    	//https://www.w3schools.com/bootstrap/bootstrap.asp참조
    	//->부모창과 자식창이 한몸으로 구성되어 있음

    	//새창만들기
    	//-> 부모창과 자식창이 별개로 구성되어있음
    	//-> 모바일에 기반을 둔 frontend단에서는 사용하지 말것!!
    	//window.open("파일명","새창이름","다양한옵션들")
    	window.open("TidcheckForm.jsp"
    				,"idwin"
    				,"width=400, height=350");


    }//idCheck end

    function emailCheck(){
    	
    	window.open("TemailcheckForm.jsp"
    				,"emailwin"
    				,"width=400, height=350");
    	
    }//emailCheck end

</script>
<!-- ----- DAUM 우편번호 API 종료----- -->

</form>



<!-- 본문 끝 -->

<%@ include file="../footer.jsp" %>