package kr.co.donghae.Treview;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class TreviewCont {	

   @Autowired
   TreviewDAO dao;

   public TreviewCont() {
      System.out.println("---TreviewCont()객체 생성됨");
   }// MediaCont() end

//----------------------------------------------------------------------------------------   
   @RequestMapping("Treview/Treview.do")
   public ModelAndView list(TreviewDTO dto) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("Treview/Treviewlist");
      mav.addObject("root", Utility.getRoot());
      mav.addObject("list", dao.list());
      return mav;
   }// list() end

//----------------------------------------------------------------------------------------   
   @RequestMapping(value = "/Treview/read.do", method = RequestMethod.GET)
   public ModelAndView read(int rnum, HttpServletRequest req) {
      ModelAndView mav=new ModelAndView();
      TreviewDTO dto=dao.read(rnum);
      ArrayList<TreviewFileDTO> fdto=dao.fread(rnum);
      if(dto!=null) {
          mav.setViewName("Treview/Treviewread");
          mav.addObject("root", Utility.getRoot());
          mav.addObject("dto",dto);  
          mav.addObject("list",fdto);  
          }//if end
      
      return mav;
   }//read() end
 
//----------------------------------------------------------------------------------------
   @RequestMapping(value = "Treview/createrform.do", method = RequestMethod.GET)
   public String createForm() {
      return "Treview/Treviewcreate";
   }// createform() end

 //------------------------------------------------------------------------------------------  
   
   @RequestMapping(value = "Treview/create.do", method = RequestMethod.POST)
   public ModelAndView createProc(@ModelAttribute TreviewDTO dto , TreviewFileDTO fdto
                           ,MultipartHttpServletRequest req
                           ,HttpServletResponse resp
                           ,HttpSession session
                           ,@RequestParam("photonameMF")MultipartFile file
                           ) throws IOException {
	   //String msg="";
	   ModelAndView mav = new ModelAndView();
       mav.setViewName("Treview/msg");

	   if(file.getSize()==0) {
		  int cnt=dao.create(dto);
		  if(cnt==0) {
		  	  mav.addObject("msg","게시물 등록에 실패하였습니다");
		  	  mav.addObject("back","history.back()");
          }else {
        	  mav.addObject("msg","등록되었습니다.");
        	  mav.addObject("url","../Treview/Treview.do");
          }//if end
	   }else {
		   mav.addObject("root", Utility.getRoot());
		   int cnt=dao.create(dto);
		   int seq=dao.seqselect();

		   System.out.println(seq);
		   if(cnt==0) {
			   mav.addObject("msg","게시물 등록에 실패하였습니다");
			   mav.addObject("back","history.back()");
		   }else {
			   mav.addObject("msg","등록되었습니다.");
			   mav.addObject("url","../Treview/Treview.do");
			   System.out.println("후기 등록 성공");
	     	     
	         List<MultipartFile> fileList = req.getFiles("photonameMF");
	         String path=req.getRealPath("./storage");
	         File fileDir = new File(path);
	         
	         if(!fileDir.exists()) {
	            fileDir.mkdir();
	         }//if end
	         
	         long time = System.currentTimeMillis();
	         int cnt1=0;
	         for(MultipartFile mf : fileList) {
	           String orgFileName=mf.getOriginalFilename();//원본파일명
	           String photonameMF=String.format("%d_%s", time,orgFileName);
	           fdto.setFileName(photonameMF);
	           fdto.setFilesize(mf.getSize());
	           cnt1=dao.fcreate(fdto, seq);
	           try {
	              //파일생성
	              mf.transferTo(new File(path, photonameMF));
	              //mf.getInputStream();
	           }catch (Exception e) {
	              System.out.println("파일생성 실패"+e);
	           }//try end

	          System.out.println(fdto.toString());

	         }//for end
	         if(cnt1==0) {
	        	 System.out.println("사진 등록 실패");
	          }else {
	        	 System.out.println("사진 등록 성공");
	          }//if end
	     }//if end
		      	 System.out.println(dto.toString());
	   }//if end
      return mav;
   
   }//createProc() end
   
 //------------------------------------------------------------------------------------------

   @RequestMapping(value = "Treview/upcheckform.do", method = RequestMethod.GET)
   public ModelAndView upcheckform(@ModelAttribute TreviewDTO dto
									 ,HttpServletRequest req
									 ,HttpServletResponse resp
									 ,HttpSession session) {
	   ModelAndView mav = new ModelAndView();
	   
	   mav.setViewName("Treview/upcheck");
	   mav.addObject("root", Utility.getRoot());
	   mav.addObject("dto", dao.read(dto.getRnum()));
       return mav;
      
   }//upcheckform() end

 //--------------------------------------------------------------------------------------------------------------------------
   
   @RequestMapping(value = "Treview/upcheck.do", method = {RequestMethod.POST, RequestMethod.GET})
   public ModelAndView upcheck(TreviewDTO dto
								  ,HttpServletRequest req
								  ,HttpServletResponse resp
								  ,HttpSession session) throws IOException{
	   int rnum = Integer.parseInt(req.getParameter("rnum"));
	   ModelAndView mav= new ModelAndView();
	   
	   String rpasswd=req.getParameter("rpasswd").trim(); 
	   mav.addObject("root", Utility.getRoot());
	   
	   int cnt=0;
	   dto.setRpasswd(rpasswd);
	   cnt=dao.pwcheck(dto);
	   req.setAttribute("rpasswd", rpasswd);
	   System.out.println(rpasswd);
	   System.out.println(cnt);
	   
	    if(cnt==0) {
	    	resp.setContentType("text/html; charset=UTf-8;");
	    	PrintWriter out = resp.getWriter();
	    	out.println("<script> alert('비밀번호가 일치하지 않습니다');");
	    	out.println("history.back(); </script>");
	    	//out.println("location.href='../Treview/Treview.do'; </script>");
	    	out.flush();
	    }else {
	    	mav.setViewName("Treview/upalert");
	    	mav.addObject("dto",dto);
	        System.out.println(dto);
	    }
      return mav;
      
   }//upcheck() end
//-----------------------------------------------------------------------
   
   @RequestMapping(value="Treview/updateform.do", method = RequestMethod.GET)
      public ModelAndView updateForm(TreviewDTO dto, TreviewFileDTO fdto) {
         ModelAndView mav = new ModelAndView();
         mav.setViewName("Treview/Treviewupd");
         mav.addObject("root", Utility.getRoot());
         //수정하고자 하는 행 가져오기
         mav.addObject("dto", dao.read(dto.getRnum()));
         return mav;
      }//updateForm() end
 //--------------------------------------------------------------------------------------------------------------------------   
   @RequestMapping(value="Treview/update.do", method = RequestMethod.POST)
   public ModelAndView updateProc(TreviewDTO dto
		   						,TreviewFileDTO fdto
		   						,MultipartHttpServletRequest req
		   						,HttpServletResponse resp
		   						,@RequestParam("photonameMF")MultipartFile file) throws IOException{
	   ModelAndView mav = new ModelAndView();
	   mav.addObject("root", Utility.getRoot());
	   mav.setViewName("Treview/msg");

		 String basePath=req.getRealPath("./storage"); //삭제하고자 하는 정보 가져오기 
		 TreviewDTO oldDTO=dao.read(dto.getRnum()); 
		 ArrayList<TreviewFileDTO> oldfDTO=dao.fread(dto.getRnum());
		 
		 if(file.getSize()==0) {//사진 안올릴때
			 int cnt=dao.update(dto);
			 dao.fdelete(dto.getRnum());
			 if(oldfDTO!=null) {
			     int cnt1=dao.fdelete(dto.getRnum());
				 for(int i=0; i<oldfDTO.size(); i++) {
		        	 UploadSaveManager.deleteFile(basePath, oldfDTO.get(i).getFileName());
		         }//for end
				 if(cnt1==0) { //삭제되었는지?
					 mav.addObject("msg","수정 실패하였습니다");
				  	 mav.addObject("back","history.back()");
				     System.out.println("수정 실패하였습니다.1");
			      }else {
			    	 mav.addObject("msg","수정되었습니다.");
					 mav.addObject("url","../Treview/Treview.do");
					 System.out.println("수정되었습니다.1");
			      }//if end
			 }//if end
			 if(cnt==0) { //삭제되었는지?
				 mav.addObject("msg","수정 실패하였습니다.");
			  	 mav.addObject("back","history.back()");
			     System.out.println("수정 실패하였습니다.2");
		      }else {
		      	 mav.addObject("msg","수정되었습니다.");
				 mav.addObject("url","../Treview/Treview.do");
				 System.out.println("수정되었습니다.2");
		      }//if end
		 }else {
			 //파일수정할때
			 if(oldfDTO==null) {//기존 사진 없을때
				 //파일테이블create
				 List<MultipartFile> fileList = req.getFiles("photonameMF");
		         String path=req.getRealPath("./storage");
		         File fileDir = new File(path);
		         
		         if(!fileDir.exists()) {
		            fileDir.mkdir();
		         }//if end
		         
		         long time = System.currentTimeMillis();
		         int cnt1=0;
		         for(MultipartFile mf : fileList) {//
		           String orgFileName=mf.getOriginalFilename();//원본파일명
		           String photonameMF=String.format("%d_%s", time,orgFileName);
		           fdto.setFileName(photonameMF);
		           fdto.setFilesize(mf.getSize());
		           cnt1=dao.fcreate(fdto, dto.getRnum());
		           try {
		              //파일생성
		              mf.transferTo(new File(path, photonameMF));
		              //mf.getInputStream();
		           }catch (Exception e) {
		              System.out.println("파일생성 실패"+e);
		           }//try end
		           if(cnt1==0) { //수정 되었는지?
				    	 System.out.println("사진 수정 실패");
				      }else {
				         System.out.println("사진 수정 되었습니다");
				      }//if end
		         }//for end
			 }else {//기존 사진 존재할때
				 
		          dao.fdelete(dto.getRnum());
	
				 List<MultipartFile> fileList = req.getFiles("photonameMF");
		         String path=req.getRealPath("./storage");
		         File fileDir = new File(path);
		         
		         if(!fileDir.exists()) {
		            fileDir.mkdir();
		         }//if end
		         
		         long time = System.currentTimeMillis();
		         int cnt1=0;
		         for(MultipartFile mf : fileList) {//
		           String orgFileName=mf.getOriginalFilename();//원본파일명
		           String photonameMF=String.format("%d_%s", time,orgFileName);
		           fdto.setFileName(photonameMF);
		           fdto.setFilesize(mf.getSize());
		           cnt1=dao.fcreate(fdto, dto.getRnum());
		           try {
		              //파일생성
		              mf.transferTo(new File(path, photonameMF));
		              //mf.getInputStream();
		           }catch (Exception e) {
		              System.out.println("파일생성 실패"+e);
		           }//try end
		           if(cnt1==0) { //수정 되었는지?
				    	 System.out.println("사진 수정 실패");
				      }else {
				         System.out.println("사진 수정 되었습니다");
				      }//if end
		         }//for end
			 }//if end
		 }//if end
		 int cnt=dao.update(dto);
		 if(cnt==0) { //삭제되었는지?
	    	 mav.addObject("msg","후기 수정 실패");
		  	 mav.addObject("back","history.back()");
		     System.out.println("수정 실패하였습니다.3");
		 }else {
			 mav.addObject("msg","수정되었습니다.");
			 mav.addObject("url","../Treview/Treview.do");
			 System.out.println("수정되었습니다.3");
		 }//if end
		 
	   return mav;
   }//updateproc()end
 //------------------------------------------------------------------------------------------

   @RequestMapping(value = "Treview/delcheckform.do", method = RequestMethod.GET)
   public ModelAndView delcheckform(@ModelAttribute TreviewDTO dto
									 ,HttpServletRequest req
									 ,HttpServletResponse resp
									 ,HttpSession session) {
	   ModelAndView mav = new ModelAndView();
	   
	   mav.setViewName("Treview/delcheck");
	   mav.addObject("root", Utility.getRoot());
	   mav.addObject("dto", dao.read(dto.getRnum()));
       return mav;
      
   }//upcheckform() end

   @RequestMapping(value = "Treview/delcheck.do", method = {RequestMethod.POST, RequestMethod.GET})
   public ModelAndView delcheck(TreviewDTO dto, Model model
								  ,HttpServletRequest req
								  ,HttpServletResponse resp
								  ,HttpSession session) throws IOException{
	   int rnum = Integer.parseInt(req.getParameter("rnum"));
	   ModelAndView mav= new ModelAndView();
	   
	   String rpasswd=req.getParameter("rpasswd").trim(); 
	   mav.addObject("root", Utility.getRoot());
	   
	   int cnt=0;
	   dto.setRpasswd(rpasswd);
	   cnt=dao.pwcheck(dto);
	   req.setAttribute("rpasswd", rpasswd);
	   System.out.println(rpasswd);
	   System.out.println(cnt);
	   
	    if(cnt==0) {
	    	resp.setContentType("text/html; charset=UTf-8;");
	    	PrintWriter out = resp.getWriter();
	    	out.println("<script> alert('비밀번호가 일치하지 않습니다');");
	    	out.println("history.back(); </script>");
	    	//out.println("location.href='../Treview/Treview.do'; </script>");
	    	out.flush();
	    }else {
	    	mav.setViewName("Treview/delalert");
	    	mav.addObject("root", Utility.getRoot());
	        mav.addObject("dto",dto);
	    }
      return mav;
      
   }//upcheck() end
   
/*----------------------------------------------------------------------------------------   
   @RequestMapping(value="Treview/delete.do", method = RequestMethod.GET)
   public ModelAndView deleteForm(TreviewDTO dto) {
      ModelAndView mav = new ModelAndView();
     //mav.setViewName("Treview/Treviewdel");
      mav.addObject("root", Utility.getRoot());
      //삭제하고자 하는 행 가져오기
      mav.addObject("dto", dao.read(dto.getRnum()));
      return mav;
   }//deleteForm() end
*/
//----------------------------------------------------------------------------------------   
   @RequestMapping(value="Treview/delete.do", method = {RequestMethod.GET, RequestMethod.POST} )
   public ModelAndView deleteProc(TreviewDTO dto, int rnum
		   						, HttpServletRequest req, HttpServletResponse resp) throws IOException{
	  rnum=Integer.parseInt(req.getParameter("rnum"));
      ModelAndView mav = new ModelAndView();
      mav.addObject("root", Utility.getRoot());
      mav.addObject("dto", dao.read(dto.getRnum()));
      mav.setViewName("Treview/msg");
      
      //삭제하고자 하는 정보 가져오기
      TreviewDTO oldDTO=dao.read(rnum);
      ArrayList<TreviewFileDTO> oldfDTO=dao.fread(rnum);
      int cnt=dao.delete(rnum);
      int cnt1=dao.fdelete(rnum);
      //System.out.println(cnt1);
      //System.out.println(oldfDTO.get(1));
      
      if(cnt==0||cnt1==0) { //삭제되었는지?
    	  mav.addObject("msg","삭제 되었습니다.");
    	  mav.addObject("url","../Treview/Treview.do");
		  System.out.println("삭제성공");
      }else {
         //관련 파일 삭제
         String basePath=req.getRealPath("./storage");
         for(int i=0; i<=oldfDTO.size()-1; i++) {
        	 UploadSaveManager.deleteFile(basePath, oldfDTO.get(i).getFileName());
         }//for end
         mav.addObject("msg","삭제 되었습니다.");
         mav.addObject("url","../Treview/Treview.do");
		 
		 System.out.println("삭제실패");
      }//if end
      
      return mav;
      
   }//deleteProc() end
//--------------------------------------------------------------------------------------------------------------------------   
   @RequestMapping("Treview/Treview2.do")
      public ModelAndView list2(TreviewDTO dto
                            , String col
                            , String word,HttpServletRequest req) {
         col=req.getParameter("col");
         word=req.getParameter("word");
         
         ModelAndView mav = new ModelAndView();
         mav.setViewName("Treview/Treviewlist");
         mav.addObject("root", Utility.getRoot());
         mav.addObject("list", dao.list2(col, word));
         return mav;
      }// list2() end

//----------------------------------------------------------------------------------------   

   
}// class end