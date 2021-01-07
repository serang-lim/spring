/**
 *  myscript.js
 */

function bbsCheck(f){
	//게시판 유효성 검사
	//this 자기자신
	//f -><form name=bbsfrm></form>

	//1) 작성자 2글자이상 입력
	var wname=f.wname.value; //작성자 가져오기
	wname=wname.trim();  //좌우 공백 제거하기
	if(wname.length<2){ 
		alert("작성자 2글자 이상 입력해 주세요")
		f.wname.focus(); //작성자 칸에 커서 생성
		return false; //전송하지 않음
	}//if end

	//2) 제목 2글자 이상 입력
	var subject=f.subject.value; //제목 가져오기
	subject=subject.trim();  //좌우 공백 제거하기
	if(subject.length<2){ 
		alert("제목 2글자 이상 입력해 주세요")
		f.subject.focus(); //제목 칸에 커서 생성
		return false; //전송하지 않음
	}//if end

	//3) 내용 2글자 이상 입력
	var content=f.content.value; //내용 가져오기
	content=content.trim();  //좌우 공백 제거하기
	if(content.length<2){ 
		alert("내용 2글자 이상 입력해 주세요")
		f.content.focus(); //내용 칸에 커서 생성
		return false; //전송하지 않음
	}//if end

	//4) 비밀번호 4글자 이상 입력
	var passwd=f.passwd.value; //비밀번호 가져오기
	passwd=passwd.trim();  //좌우 공백 제거하기
	if(passwd.length<4){ 
		alert("비밀번호 4글자 이상 입력해 주세요")
		f.passwd.focus(); //비밀번호 칸에 커서 생성
		return false; //전송하지 않음
	}//if end

	return true; //onsubmit이벤트에서 서버로 전송

} //bbsCheck() end
	
function pwCheck(){
	var passwd=document.getElementById("passwd").value;
	passwd=passwd.trim();
	if(passwd.length<4){
		alert("비밀번호 4글자 이상 입력해 주세요")
		document.getElementById("passwd").focus();
		return false;
	} //if end

	var message="진행된 내용은 복구되지 않습니다\n계속 진행할까요?";
	if(confirm(message)){ //확인 true, 취소 false
		return true; //서버로 전송됨
	}else{
		return false;
	}//if end

}//pwCheck() end

function pwCheck2(){
      //비밀번호가 4글자 이상입력되었는지 검사
     var passwd=document.getElementById("passwd").value;
     passwd=passwd.trim();
     if(passwd.length<4){
        alert("비밀번호 4글자 이상 입력해 주세요");
        document.getElementById("passwd").focus();
        return false;    
      }//if end

      var message="정말 수정하시겠습니까?";
      if(confirm(message)){//확인 true, 취소 false
         return true;//서버전송
      }else{
         return false;
      }//if end
   }//pwCheck2 end

	function searchCheck(f){
		//검색어를 입력했는지 검사
		var word=f.word.value;
		word=word.trim()
		if(word.length==0){
			alert("검색어를 입력해 주세요");
			return false;
		}//if end
		return true;
	}//searchCheck() end

function noticeCheck(f){
   //공지사항 유효성 검사
   //this:자기자신
   var subject=f.subject.value;
   subject=subject.trim();
   if(subject.length<=2){
      alert("제목 2글자 이상 입력해 주세요!!");
      f.subject.focus();
      return false;
   }//if end

   //3)내용 2글자 이상 입력
   var content=f.content.value;
   content=content.trim();
   if(content.length<=2){
      alert("내용 2글자 이상 입력해 주세요!!");
      f.content.focus();
      return false;
   }//if end


   return true;      //onsubmit이벤트에서 서버로 전송할수 있게 리턴값 전달
}//noticeCheck() end

function loginCheck(){
	//로그인 유효성 검사
	//1) 아이디 5~10글자 이내 인지 검사
	var id=document.getElementById("id").value;
	  id=id.trim();
	  if(!(id.length>=5 && id.length<=10)){
		 alert("아이디 5~10글자이내 입력해 주세요");
		 document.getElementById("id").focus();
		 return false;    
	   }//if end


	//2) 비밀번호 5~10글자 이내 인지 검사
      var passwd=document.getElementById("passwd").value;
	  passwd=passwd.trim();
	  if(!(passwd.length>=5 && passwd.length<=10)){
		 alert("비밀번호 5~10글자이내 입력해 주세요");
		 document.getElementById("passwd").focus();
		 return false;    
	   }//if end

	   return true;
}//loginCheck() end

function memberCheck(){
	//회원가입 유효성 검사
	//1) 아이디 5~10글자 인지?
	var id=document.getElementById("id").value;
	  id=id.trim();
	  if(!(id.length>=5 && id.length<=10)){
		 alert("아이디 5~10글자이내 입력해 주세요");
		 document.getElementById("id").focus();
		 return false;    
	   }//if end

	//2) 비밀번호 5~10글자 인지?
	var passwd=document.getElementById("passwd").value;
	passwd=passwd.trim();
	if(!(passwd.length>=5 && passwd.length<=10)){
	   alert("비밀번호 5~10글자이내 입력해 주세요");
	   document.getElementById("passwd").focus();
	   return false;    
	 }//if end

	//3) 비밀번호와 비밀번호 확인이 서로 일치하는지?
	var repasswd=document.getElementById("repasswd").value;
    repasswd=repasswd.trim();
	if(passwd!=repasswd){
	alert("비밀번호와 비밀번호 확인이 같지 않습니다.");
	document.getElementById("passwd").focus();      
	return false;
	}//if end

	//4) 이름 1~20글자 인지?
	var mname=document.getElementById("mname").value;
	mname=mname.trim();
	if(!(mname.length>=1 && mname.length<=20)){
	   alert("이름 1~20글자이내 입력해 주세요");
	   document.getElementById("mname").focus();
	   return false;    
	 }//if end

	//5) 이메일 5글자이상 인지?
	var email=document.getElementById("email").value;
	email=email.trim();
	if(!(email.length>=5)){
	   alert("이메일 5글자이상 입력해 주세요");
	   document.getElementById("email").focus();
	   return false;    
	 }//if end
	
	//6) 직업을 선택했는지?
	var job=document.getElementById("job").value;
	if(job=="0"){
	   alert("직업을 선택해 주세요");
	   return false;    
	 }//if end


	return true;

}//memberCheck() end


function idCheck(){
	//아이디 중복 확인

	//bootstrap 모달창
	//https://www.w3schools.com/bootstrap/bootstrap_modal.asp 참조
	// > 부모창과 자식창이 한 몸으로 구성되어 있음

	//새창 만들기
	// > 부모창과 자식창이 별개로 구성되어 있음
	// > 모바일에 기반을 둔 frontend단에서는 사용하지 말 것!
	//window.open("파일명","새창이름","다양한 옵션들")
	window.open("idCheckForm.jsp"
				, "idwin"
				, "width=400m height=350");
}//idCheck() end

function emailCheck(){
	//이메일 중복 확인
	window.open("emailCheckForm.jsp"
				, "idwin"
				, "width=400m height=350");
}//emailCheck() end

function findIDCheck(){
   //아이디/비번 찾기  유효성 검사
   
   //이름 1~20글자 인지?
   var mname=document.getElementById("mname").value;
      mname=mname.trim();
      if(!(mname.length>=1 && mname.length<=20)){
      alert("이름을 1~10글자 이내 입력해 주세요");
      document.getElementById("mname").focus();      
      return false;
      }//if end
   //이메일 5글자 인지?
   var email=document.getElementById("email").value;
      email=email.trim();
      if(!(email.length>=5 && email.length<=50)){
      alert("이메일을 5~30글자 이내 입력해 주세요");
      document.getElementById("email").focus();      
      return false;
      }//if end
      return true;
   }//findIDCheck() end

function pdsCheck(){
   //포토갤러리 유효성 검사
   
   //이름
   var wname=document.getElementById("wname").value;
  	 wname=wname.trim();
      if(!(wname.length>=1 && wname.length<=20)){
      alert("이름을 1~10글자 이내 입력해 주세요");
      document.getElementById("wname").focus();      
      return false;
      }//if end
   //제목
   var subject=document.getElementById("subject").value;
   subject=subject.trim();
      if(subject.length<=2){
      alert("제목 2글자 이상 입력해 주세요!!");
      document.getElementById("subject").focus();      
      return false;
	  }//if end
	  
	//비밀번호
	var passwd=document.getElementById("passwd").value;
	passwd=passwd.trim();
	   if(passwd.length<4){
	   alert("비밀번호 4글자 이상 입력해 주세요");
	   document.getElementById("passwd").focus();      
	   return false;
	   }//if end

	//4) 첨부파일
	//첨부파일 확장명이 이미지 파일(png, jpg, gif)인지 확인하시오
	var filename=document.getElementById("filename").value;
	filename=filename.trim();
	if(filename.length==0){
		alert("첨부 파일 선택하세요");
		return false;
	}else{
		//마지막 . 의 순서 값
		//alert(filename.lastIndexOf("."));
		//문제) 파일 타입(확장명)을 출력하시오
		var dot=filename.lastIndexOf(".");
		//확장명 : 마지막 . 이후 문자열만 자르기
		var ext=filename.substring(dot+1);
		//확장명을 전부 소환자로 치환
		ext=ext.toLowerCase();
		if(ext=="png" || ext=="jpg" || ext=="gif"){
			return true;
		}else{
			alert("이미지 파일만 가능합니다!");
			return false;
		}//if end
	}//if end

   }//pdsCheck() end