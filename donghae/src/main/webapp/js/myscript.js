/*
*myscript.js
 */

function bbsCheck(f){
	//게시판 유효성 검사
	//this 자기자신
	//f -> <form name=bbsfrm></form>
	
	//1)작성자 2글자 이상 입력
	var wname=f.wname.value; //작성자 가져오기
	wname=wname.trim();		//좌우 공백 제거하기
	if(wname.length<2){
		alert("작성자 2글자 이상 입력해주세요");
		f.wname.focus();	//작성자 칸에 커서 생성
		return false;		//전송하지 않음
	}//if end

	//2)제목 2글자 이상 입력
	var subject=f.subject.value;
	subject=subject.trim();
	if(subject.length<2){
		alert("제목 2글자 이상!")
		f.subject.focus();
		return false;
	}
	//3)내용 2글자 이상 입력
	var content=f.content.value;
	content=content.trim();
	if(content.length<2){
		alert("내용 2글자 이상이다")
		f.content.focus();
		return false;
	}
	//4)비밀번호 4글자 이상 입력
	var passwd=f.passwd.value;
	passwd=passwd.trim();
	if(passwd.length<4){
		alert("비밀번호 4개 이상")
		f.passwd.focus();
		return false;
	}

	return true;//onsubmit 이벤트에서 서버로 전송
	
}//bbsCheck

function pwCheck(){
	var passwd=document.getElementById("passwd").value;
	passwd=passwd.trim();
	if(passwd.length<4){
		alert("비밀번호 4글자 이상");
		document.getElementById("passwd").focus();
		return false;
	}//if end
var message="복구되지않아요\n 그래도 해?"
if(confirm(message)){ //확인 true, 취소 false
	return true; //서버로 전송됨
}else{
	return false;
}//if end

}//pwcheck end

function pwCheckU(){
	var passwd=document.getElementById("passwd").value;
	passwd=passwd.trim();
	if(passwd.length<4){
		alert("비밀번호 4글자 이상");
		document.getElementById("passwd").focus();
		return false;
	}//if end
var message="수정하실거?\n 진짜 할거에요?"
if(confirm(message)){ //확인 true, 취소 false
	return true; //서버로 전송됨
}else{
	return false;
}//if end

}//pwcheck end
 

function searchCheck(f){
	//검색어를 입력했는지 검사
	var word=f.word.value;
	word=word.trim();
	if(word.length==0){
		alert("검색어를 입력해 주세요");
		return false;
	}//if end
}// searchCheck end


function noticeCheck(f){
	var subject=f.subject.value;
	subject=subject.trim();
	if(subject.length<2){
		alert("제목 2글자 이상!")
		f.subject.focus();
		return false;
	}
	//3)내용 2글자 이상 입력
	var content=f.content.value;
	content=content.trim();
	if(content.length<2){
		alert("내용 2글자 이상이다")
		f.content.focus();
		return false;
	}
return true;//onsubmit 이벤트에서 서버로 전송
}//notice check end


function loginCheck(){
	//로그인 유효성 검서
	//1)아이디 5이상 10글자 이하
	var id=document.getElementById("id").value; //작성자 가져오기
	id=id.trim();		//좌우 공백 제거하기
	if(!(id.length>=5 && id.length<=10)){
		alert("id 5~10글자 이내");
		document.getElementById("id").focus();
		return false;		//전송하지 않음
	}//if end

	//2)비밀번호 5이상 10글자 이하인지 검사
	var passwd=document.getElementById("passwd").value;
	passwd=passwd.trim();
	if(!(passwd.length>=5 && passwd.length<=10)){
		alert("비밀번호 5~10글자이내");
		document.getElementById("passwd").focus();
		return false;
	}//if end

	return true;
}//login check


function memberCheck(){
	//회원가입 유효성검사
	//1)아이디 5~10글자 인지
	var id=document.getElementById("Mid").value; //작성자 가져오기
	id=id.trim();		//좌우 공백 제거하기
	if(!(mid.length>=5 && mid.length<=10)){
		alert("id 5~10글자 이내");
		document.getElementById("id").focus();
		return false;		//전송하지 않음
	}//if end

	//2)비밀번호 5~10글자 인지
	var passwd=document.getElementById("passwd").value;
	passwd=passwd.trim();
	if(!(passwd.length>=5 && passwd.length<=10)){
		alert("비밀번호 5~10글자이내");
		document.getElementById("passwd").focus();
		return false;
	}//if end

	//3)비밀번호와 비밀번호 확인이 서로 일치하는지
	var repasswd=document.getElementById("repasswd").value;
    repasswd=repasswd.trim();
   if(passwd!=repasswd){
   alert("비밀번호와 비밀번호 확인이 같지 않습니다.");
   document.getElementById("passwd").focus();      
   return false;
   }//if end
   
	//4)이름 1~20글자 인지?
	var mname=document.getElementById("mname").value;
	mname=mname.trim();
	if(!(mname.length>=1 && mname.length <=20)){
		alert("이름을 정확히 써주세요");
		document.getElementById("mname").focus();
		return false;
	}
	//5)이메일 5글자 이상인지
	var email=document.getElementById("email").value;
	email=email.trim();
	if(email.length <=4){
		alert("5글자 이상 써주세요")
		document.getElementById("email").focus();
		return false;
	}
	//6)직업을 선택했는지?
	var job=document.getElementById("job").value;
	if(job=="0"){
		alert("직업 선택 요망");
		return false;
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
	
	window.open("emailCheckForm.jsp"
				,"emailwin"
				,"width=400, height=350");
	
}//emailCheck end



function findIDCheck(){
	//아이디 비번 찾기 유효성검사
	
	//이름 1~20글자 인지?
	var mname=document.getElementById("mname").value;
	mname=mname.trim();
	if(!(mname.length>=1 && mname.length <=20)){
		alert("이름을 정확히 써주세요");
		document.getElementById("mname").focus();
		return false;
	}
	//이메일 5글자 이상인지
	var email=document.getElementById("email").value;
	email=email.trim();
	if(email.length <=4){
		alert("5글자 이상 써주세요")
		document.getElementById("email").focus();
		return false;
	}
	};

	function pdsCheck(){
		//포토갤러리  유효성검사(확장자확인)
	 
		//1)이름
		var wname=document.getElementById("wname").value;   //작성자 가져오기
		wname=wname.trim();         //공백없애기(좌우공백)
		if(wname.length<2){         
		   alert("작성자 2글자 이상 입력해 주세요!!");
		   document.getElementById("wname").focus();      //작성할 칸에 커서 생성
		   return false;         //전송하지 않음
		}//if end
	 
		//2)제목 2글자이상입력
		var subject=document.getElementById("subject").value;
		subject=subject.trim();
		if(subject.length<2){
		   alert("제목 2글자 이상 입력해 주세요!!");
		   document.getElementById("subject").focus();
		   return false;
		}//if end
	 
		//3)비밀번호 4글자 이상입력
		var passwd=document.getElementById("passwd").value;
		passwd=passwd.trim();
		if(passwd.length<4){
		   alert("비밀번호 4글자 이상 입력해 주세요!!");
		   document.getElementById("passwd").focus();
		   return false;
		}//if end
		//4)첨부파일
		//확장명이 이미지파일인지 확인하시오
		var filename=document.getElementById("filename").value;
		   filename=filename.trim();
		   if(filename.length==0){
			  alert("첨부 파일 선택하세요");
			  return false;
		   }else{
			  
			 var  dot=filename.lastIndexOf(".");
			  //문제) 파일의 확장명을 출력하시오
			  var exname=filename.substring(dot+1);
			  //alert(exname);
			  var exname=exname.toLowerCase();
			  if(exname=="png" || exname=="gif" || exname=="jpg") {
				 alert("사진이 전송되었습니다.");
				 return true;
			  }else {
				 alert("이미지 파일만 가능합니다.");
				 return false;
			  }//if end
			  
			  
		
		   }//if end
	 }//pdsCheck() end

	 function fnImgPop(url){
		var img=new Image();
		img.src=url;
		var img_width=img.width;
		var win_width=img.width+25;
		var img_height=img.height;
		var win=img.height+30;
		var OpenWindow=window.open('','_blank', 'width='+img_width+', height='+img_height+', menubars=no, scrollbars=auto');
		OpenWindow.document.write("<style>body{margin:0px;}</style><img src='"+url+"' width='"+win_width+"'>");
	   }