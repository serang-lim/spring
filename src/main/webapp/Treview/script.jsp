<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script>
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
		
		function ErrorMessage(){
	            alert("비밀번호가 틀렸습니다.");
	            history.back(-1);
	      }//ErrorMessage end
	      
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

</script>