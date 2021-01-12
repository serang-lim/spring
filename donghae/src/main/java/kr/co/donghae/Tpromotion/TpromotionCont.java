package kr.co.donghae.Tpromotion;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class TpromotionCont {
    @Autowired
    TpromotionDAO dao;
 
	public TpromotionCont() {
		System.out.println("---TpromotionCont() 객체 생성됨...");
	}//생성자함수 end
	

	  @RequestMapping(value = "Tpromotion/Tpromotion.do", method = RequestMethod.GET)
	  public ModelAndView list(TpromotionDTO dto,HttpServletRequest req) { 
		  ModelAndView mav=new ModelAndView();
		  mav.setViewName("Tpromotion/TpromotionList"); 
		  mav.addObject("root",Utility.getRoot());
		  ArrayList<TpromotionDTO> list=dao.list(dto);
		  mav.addObject("list", list);
		  //System.out.println(list);
	  return mav; }//home() end
	  
	 
	/*
	 * @RequestMapping("Tpromotion/Tpromotion2.do") public ModelAndView
	 * list2(TpromotionDTO dto, String col1, String col2, HttpServletRequest req) {
	 * ModelAndView mav=new ModelAndView(); col1=req.getParameter("col1");
	 * col2=req.getParameter("col2");
	 * 
	 * mav.setViewName("Tpromotion/TpromotionList"); mav.addObject("root",
	 * Utility.getRoot()); mav.addObject("list", dao.list2(col1, col2));
	 * req.setAttribute("cnt",dao.count(col1, col2) ); return mav; }//home() end
	 */
	@RequestMapping("Tpromotion/Tpromotion2.do")
	public ModelAndView list2(TpromotionDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		String col1=req.getParameter("col1");
		String col2=req.getParameter("col2");
		mav.setViewName("Tpromotion/TpromotionList");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("list", dao.list2(col1, col2));


		return mav;
	}//home() end
	/*
	 * @RequestMapping(value = "Tpromotion/Tpromotion2.do",method=RequestMethod.GET)
	 * public ModelAndView list3(TpromotionDTO dto, HttpServletRequest req) {
	 * ModelAndView mav=new ModelAndView(); String col1=req.getParameter("col1");
	 * String col2=req.getParameter("col2");
	 * 
	 * mav.setViewName("Tpromotion/TpromotionList"); mav.addObject("root",
	 * Utility.getRoot()); mav.addObject("list", dao.list2(col1, col2));
	 * 
	 * 
	 * return mav; }//home() end
	 * 
	 */
	
	
	@RequestMapping("Tpromotion/TpromotionWrite.do")
	public ModelAndView Write() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Tpromotion/TpromotionWrite");
		return mav;
	}//write() end
	
	
	
    @RequestMapping(value="Tpromotion/TpromotionInsert.do",method=RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute TpromotionDTO dto, HttpServletRequest req, HttpSession session) {
    	ModelAndView mav=new ModelAndView();
  	  mav.addObject("root", Utility.getRoot());
  	  
  	  String tnum=dao.tnumselect(dto.getTwriter());
  	  dto.setTnum(tnum);
//--------------------------------------------------
  	  //파일 저장 폴더의 실제 물리적인 경로 가져오기
  	  String basePath=req.getRealPath("./storage");
  	  //<input type="file" name"filenameMF"> 파일 가져오기
  	  MultipartFile imagenameMF=dto.getTimage_nameMF();
  	  MultipartFile imagenameMF2=dto.getTimage_nameMF2();
	  
  	  String imagename=UploadSaveManager.saveFileSpring30(imagenameMF, basePath);
  	  String imagename2=UploadSaveManager.saveFileSpring30(imagenameMF2, basePath);

  	  dto.setTimage_name(imagename);
  	  dto.setTimage_name2(imagename2);

  	  dto.setTimage_size((int)imagenameMF.getSize());
  	  dto.setTimage_size2((int)imagenameMF2.getSize());

//--------------------------------------------------
  	  
  	  
  	  int cnt=dao.insert(dto);
  	  if(cnt==0) {
  		mav.setViewName("Tpromotion/msg");
  		String msg="<script>";
    	msg+="alert('프로모션 등록 실패 지역 또는 분류를 반드시 선택해주세요 ');";	
    	msg+="</script>";
    	msg+="<script language=\"JavaScript\" type=\"text/javascript\">\r\n" + 
    			"        setTimeout(\"window.history.go(-1)\",0);\r\n" + 
    			"</script>";
    	req.setAttribute("msg",msg);
      	
  	  }else {

  	  	mav.setViewName("Tpromotion/msg");
		String msg="<script>";
		msg+="alert('프로모션 등록 성공 목록으로 돌아갑니다');";	
		msg+="</script>";
		msg+="<meta http-equiv='refresh' content='0;url=Tpromotion.do'>";
		req.setAttribute("msg",msg);
  	
	    
  	  }//if end
		return mav;
    }//createProc() end
    
    
    @RequestMapping(value="/Tpromotion/TpromotionRead.do",method=RequestMethod.GET)
    public ModelAndView read(int tbno, HttpServletRequest req) {
  	  ModelAndView mav=new ModelAndView();
  	  
  	  TpromotionDTO dto=dao.read(tbno);
  	  if(dto!=null) {
  		  mav.setViewName("Tpromotion/TpromotionRead");
  		  mav.addObject("root", Utility.getRoot());
    	  mav.addObject("dto",dto);    	
   	  }//if end
  	  return mav;
    }//read() end
    

    @RequestMapping(value="/Tpromotion/TpromotionUpdate.do",method=RequestMethod.GET)
    public ModelAndView updateForm(TpromotionDTO dto, HttpServletRequest req) {
      	ModelAndView mav=new ModelAndView();
  	  	mav.setViewName("Tpromotion/passwdUpd");
      	mav.addObject("root", Utility.getRoot());
      	//수행하고자하는 행 가져오기
      	mav.addObject("dto", dao.read(dto.getTbno()));
  	  
  	  return mav;
  	  
    }//updateForm() end
    
    @RequestMapping(value="/Tpromotion/TpromotionUpdateCheck.do",method=RequestMethod.POST)
    public ModelAndView updateCheck(TpromotionDTO dto, HttpServletRequest req) {
      	ModelAndView mav=new ModelAndView();
      	dto.setTbno(Integer.parseInt(req.getParameter("tbno")));
      	dto.setTpasswd(Integer.parseInt(req.getParameter("passwd")));
       	dto=dao.pwcheck(dto);

      	if(dto==null) {
      		mav.setViewName("Tpromotion/msg");
      		String msg="<script>";
	    	msg+="alert('비밀번호가 틀립니다');";	
	    	msg+="</script>";
	    	msg+="<meta http-equiv='refresh' content='0;url=TpromotionUpdate.do?tbno="+req.getParameter("tbno")+"'>";
	    	req.setAttribute("msg",msg);

      	}else {
      	  	mav.setViewName("Tpromotion/TpromotionUpdateForm");
      	  	mav.addObject("dto", dto);
          	
      	}//if end
      	return mav;
    }//updateCheck() end
    @RequestMapping(value="/Tpromotion/TpromotionDeleteCheck.do",method=RequestMethod.POST)
    public ModelAndView deleteCheck(TpromotionDTO dto, HttpServletRequest req) {
      	ModelAndView mav=new ModelAndView();
      	dto.setTbno(Integer.parseInt(req.getParameter("tbno")));
      	dto.setTpasswd(Integer.parseInt(req.getParameter("passwd")));
       	dto=dao.pwcheck(dto);

      	if(dto==null) {
      		mav.setViewName("Tpromotion/msg");
      		String msg="<script>";
	    	msg+="alert('비밀번호가 틀립니다');";	
	    	msg+="</script>";
	    	msg+="<meta http-equiv='refresh' content='0;url=TpromotionDelete.do?tbno="+req.getParameter("tbno")+"'>";
	    	req.setAttribute("msg",msg);
	    	

      	}else {
      	  	mav.setViewName("Tpromotion/TpromotionDeleteForm");
      	  	mav.addObject("dto", dto);
          	
      	}//if end
      	return mav;
    }//updateCheck() end
    @RequestMapping(value="/Tpromotion/TpromotionUpdate.do",method=RequestMethod.POST)
    public ModelAndView updateproc(TpromotionDTO dto, HttpServletRequest req) {
      	ModelAndView mav=new ModelAndView();
      	String msg="";
  		
      	  	mav.addObject("dto", dto);
        
          	String basePath=req.getRealPath("./storage");
          	//기존에 저장된 정보 가져오기
          	TpromotionDTO oldDTO=dao.read(dto.getTbno());
          	
          	//파일을 수정할것인지?
          	//1)
          	MultipartFile imagenameMF=dto.getTimage_nameMF();          	MultipartFile imagenameMF2=dto.getTimage_nameMF2();
          	
          	if(imagenameMF.getSize()>0 && imagenameMF.getSize()>0) {
          		//기존파일 삭제
          		UploadSaveManager.deleteFile(basePath, oldDTO.getTimage_name());
          		UploadSaveManager.deleteFile(basePath, oldDTO.getTimage_name2());
          		
          		//신규파일 저장
          		String imagename=UploadSaveManager.saveFileSpring30(imagenameMF, basePath);
          		dto.setTimage_name(imagename);
          		String imagename2=UploadSaveManager.saveFileSpring30(imagenameMF2, basePath);
          		dto.setTimage_name2(imagename2);

          	}else {
          		//사진를 수정하지 않은 경우
          		dto.setTimage_name(oldDTO.getTimage_name());
          		dto.setTimage_name2(oldDTO.getTimage_name2());
          		
          	}//if end
              	 int cnt=dao.update(dto);
         	  if(cnt==0) {
            	  mav.setViewName("Tpromotion/msgView");

         		  msg="<p>프로모션 수정 실패</p>";
         		  String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
         		  String link2="<input type='button' value='목록으로' onclick='location.href=\"./Tpromotion.do\"'>";
         		  mav.addObject("msg",msg);
         		  mav.addObject("link1", link1);
         		  mav.addObject("link2", link2);
         	  }else {
            	  mav.setViewName("Tpromotion/msg");
              		msg="<script>";
        	    	msg+="alert('프로모션 수정 성공 목록으로 돌아갑니다');";	
        	    	msg+="</script>";
        	    	msg+="<meta http-equiv='refresh' content='0;url=Tpromotion.do'>";
        	    	req.setAttribute("msg",msg);
        	    	
         	  }//if end
     
      	
      	return mav;
    }//updateProc() end
    
    
    @RequestMapping(value="/Tpromotion/TpromotionDelete.do",method=RequestMethod.GET)
    public ModelAndView deleteForm(TpromotionDTO dto, HttpServletRequest req) {
  	  ModelAndView mav= new ModelAndView();
        mav.setViewName("/Tpromotion/passwdDel");
        mav.addObject("root",Utility.getRoot());
        mav.addObject("dto", dao.read(dto.getTbno()));
  	  return mav;
    }//deleteForm() end
    
    
    
    @RequestMapping(value="/Tpromotion/TpromotionDelete.do",method=RequestMethod.POST)
    public ModelAndView deleteProc(TpromotionDTO dto, HttpServletRequest req) {
  	  ModelAndView mav= new ModelAndView();
        mav.addObject("root",Utility.getRoot());
    	String basePath=req.getRealPath("./storage");

        //삭제할정보가져오기
        TpromotionDTO oldDTO  = dao.read(dto.getTbno());
        int cnt=dao.delete(dto.getTbno());
     	  if(cnt==0) {
      		UploadSaveManager.deleteFile(basePath, oldDTO.getTimage_name());
      		UploadSaveManager.deleteFile(basePath, oldDTO.getTimage_name2());
            mav.setViewName("/Tpromotion/msgView");

				String msg="<p>프로모션 삭제 실패</p>";
				String img="<img src='../images/k1.png'>";
				String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
	     		String link2="<input type='button' value='목록으로' onclick='location.href=\"./Tpromotion.do?tbno="+dto.getTbno()+"\"'>";
				mav.addObject("msg",msg);
				mav.addObject("link1", link1);
				mav.addObject("link2", link2);
     	  }else {
     		  	String basepath=req.getRealPath("./storage");
     		  	UploadSaveManager.deleteFile(basepath, oldDTO.getTimage_name());
     		  	UploadSaveManager.deleteFile(basepath, oldDTO.getTimage_name2());
				
     		  	mav.setViewName("Tpromotion/msg");
          		String msg="<script>";
    	    	msg+="alert('프로모션 삭제 성공 !! 목록으로 돌아갑니다');";	
    	    	msg+="</script>";
    	    	msg+="<meta http-equiv='refresh' content='0;url=Tpromotion.do'>";
    	    	req.setAttribute("msg",msg);

     	  }//if end
		return mav; 
    }//deleteForm() end
    
    
    @RequestMapping(value="/Tpromotion/updateRes.do",method=RequestMethod.GET)
    public ModelAndView updateRes(TpromotionDTO dto, HttpServletRequest req) {
  	  ModelAndView mav= new ModelAndView();
	  //String tresult=req.getParameter("tresult");
	  int tbno=dto.getTbno();
	  int cnt=dao.updateRes(dto);
	  //System.out.println(dto);
	  if(cnt==0) {
		  mav.setViewName("Tpromotion/msgView"); 

		  String msg1="<p>보기 설정 변경 실패</p>";
		  String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
		  String link2="<input type='button' value='목록으로' onclick='location.href=\"Tpromotion.do?tbno="+dto.getTbno()+"\"'>";
		  mav.addObject("msg1",msg1);
		  mav.addObject("link1", link1);
		  mav.addObject("link2", link2);
	  }else {
		  
			
		  	mav.setViewName("Tpromotion/msg");
      		String msg="<script>";
	    	msg+="alert('보기 설정 변경 완료!! 목록으로 돌아갑니다');";	
	    	msg+="</script>";
	    	msg+="<meta http-equiv='refresh' content='0;url=Tpromotion.do'>";
	    	req.setAttribute("msg",msg);

	  }
  	  return mav;
    }//deleteForm() end
    
    
    @RequestMapping(value="/Tpromotion/TproDel.do",method=RequestMethod.GET)
    public ModelAndView deleteTpro(TpromotionDTO dto, HttpServletRequest req) {
  	  ModelAndView mav= new ModelAndView();
        mav.setViewName("/Tpromotion/msgView");
        mav.addObject("root",Utility.getRoot());
        String msg1="<p>이 프로모션을 삭제하시겠습니까?</p>";
		String link1="<input type='button' value='삭제하기' onclick='location.href=\"TproDelete.do?tbno="+dto.getTbno()+"\"'>";
		String link2="<input type='button' value='목록으로' onclick='location.href=\"Tpromotion.do?tbno="+dto.getTbno()+"\"'>";
		mav.addObject("msg1",msg1);
		mav.addObject("link1", link1);
		mav.addObject("link2", link2);
		mav.addObject("dto",dto);
  	  return mav;
    }//deleteForm() end
    
    
    @RequestMapping(value="/Tpromotion/TproDelete.do",method=RequestMethod.GET)
    public ModelAndView deleteTpromotion(TpromotionDTO dto, HttpServletRequest req) {
  	  ModelAndView mav= new ModelAndView();
        mav.setViewName("/Tpromotion/msgView");
        mav.addObject("root",Utility.getRoot());
    	String basePath=req.getRealPath("./storage");

        //삭제할정보가져오기
        TpromotionDTO oldDTO  = dao.read(dto.getTbno());
        int cnt=dao.delete(dto.getTbno());
     	  if(cnt==0) {
      		UploadSaveManager.deleteFile(basePath, oldDTO.getTimage_name());
      		UploadSaveManager.deleteFile(basePath, oldDTO.getTimage_name2());

				String msg1="<p>프로모션 삭제 실패</p>";
				String img="<img src='../images/k1.png'>";
				String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
	     		String link2="<input type='button' value='목록으로' onclick='location.href=\"./Tpromotion.do?tbno="+dto.getTbno()+"\"'>";
				mav.addObject("msg1",msg1);
				mav.addObject("link1", link1);
				mav.addObject("link2", link2);
     	  }else {
     		  	String basepath=req.getRealPath("./storage");
     		  	UploadSaveManager.deleteFile(basepath, oldDTO.getTimage_name());
     		  	UploadSaveManager.deleteFile(basepath, oldDTO.getTimage_name2());
    		  	mav.setViewName("Tpromotion/msg");
          		String msg="<script>";
    	    	msg+="alert('프로모션 삭제 성공 목록으로 돌아갑니다');";	
    	    	msg+="</script>";
    	    	msg+="<meta http-equiv='refresh' content='0;url=Tpromotion.do'>";
    	    	req.setAttribute("msg",msg);

     	  }//if end
		return mav; 
    }//deleteForm() end
    
    
    
    @RequestMapping(value="/Tpromotion/TpromotionMy.do",method=RequestMethod.GET)
    public ModelAndView myPromotion(String twriter, HttpServletRequest req) {
  	  ModelAndView mav=new ModelAndView();
  	  twriter=req.getParameter("twriter");
	  ArrayList<TpromotionDTO> list=dao.mylist(twriter);
  	  //System.out.println(dto.toString());
  	  if(list==null) {
  		mav.setViewName("Tpromotion/msg");
  		String msg="<script>";
    	msg+="alert('등록한 프로모션이 없습니다');";	
    	msg+="</script>";
    	msg+="<meta http-equiv='refresh' content='0;url=Tpromotion.do'>";
    	req.setAttribute("msg",msg);

  		  
   	  }else{
   	
   		mav.setViewName("Tpromotion/TpromotionMyList");
  	 	mav.addObject("list",list);
   	  }
  	  return mav;
    }//read() end
    
	
}//class end
