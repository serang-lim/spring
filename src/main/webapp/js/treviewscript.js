/**
 * treviewscript.js
 */

	function pdsCheck(){
   //게시판 유효성 검사
   
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

   }//pdsCheck() end
   
	var cnt = 1;
		
	function addFile() {
	var addItem = "<input type='file' name='photonameMF"+cnt+"' /><br/>";
	document.getElementById("filebox").innerHTML += addItem;
	cnt++;
	}//addFile end
	
	var formData = new FormData($('#fileForm')[0]); 
	$.ajax({ 
	   type: "POST", 
	   enctype: 'multipart/form-data', // 필수 
	   url: '/multipartUpload.do', data: formData, // 필수 
	   processData: false, // 필수 
	   contentType: false, // 필수 
	   cache: false, success: 
	      function (result) { 
	      
	   }, 
	   error: function (e) { 
	      
	   } });
	   
	function pwCheck(){
	//비밀번호 확인

	//새창 만들기
	// > 부모창과 자식창이 별개로 구성되어 있음
	// > 모바일에 기반을 둔 frontend단에서는 사용하지 말 것!
	//window.open("파일명","새창이름","다양한 옵션들")
	window.open("checkPassword.jsp"
				, "pwcheck"
				, "width=400m height=350");
	}//pwCheck() end
      
   function pwCheck2(){
   	//비밀번호가 4글자 이상입력되었는지 검사
       var rpasswd=document.getElementById("rpasswd").value;
       rpasswd=rpasswd.trim();
       if(rpasswd.length<4){
          alert("비밀번호 4글자 이상 입력해 주세요");
          document.getElementById("rpasswd").focus();
          return false;    
        }//if end

        var message="정말 수정하시겠습니까?";
        if(confirm(message)){//확인 true, 취소 false
           return true;//서버전송
        }else{
           return false;
        }//if end
     }//pwCheck2 end
	
	function ErrorMessage(){
            alert("비밀번호가 틀렸습니다.");
            history.back(-1);
      }//ErrorMessage end
    
    /*  
    function locationURL(){
      if(window.name == 'update')
            location.href='updateform.do?rnum='${dto.rnum}';

      else if(window.name == 'delete'){
            alert('삭제되었습니다.');
      location.href='deleteAction.action?no='+
                  '<s:property value = "no" />&currentPage='+
                  '<s:property value = "currentPage" />';
      }//if end

      window.close();
      }//locationURL end  
      */
      
      