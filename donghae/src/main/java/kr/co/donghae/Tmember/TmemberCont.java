package kr.co.donghae.Tmember;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;import kr.co.donghae.Tnotice.TnoticeDTO;
import kr.co.donghae.Tpromotion.TpromotionDTO;
import net.utility.Utility;

@Controller
public class TmemberCont {

	@Autowired
	TmemberDAO dao;
	
	public TmemberCont() {
		System.out.println("---TmemberCont()객체 생성");
	}//TmemberCont end
	
	@RequestMapping(value="Tmember/Tlogin.do")
	public ModelAndView TloginForm( @ModelAttribute TmemberDTO dto
			  ,HttpServletRequest req
			  ,HttpServletResponse resp
			  ,HttpSession session) {
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("root",Utility.getRoot());
		mav.addObject("Mid",dto.getMid());
		mav.addObject("Mpasswd",dto.getMpasswd());

	    mav.setViewName("Tmember/TloginForm");
	        
		return mav;	
	}//loginForm end
//-------------------------------------------------------------------------------------	
	
	@RequestMapping(value="Tmember/TloginPro.do" ,method=RequestMethod.POST)
	public ModelAndView TloginPro(@ModelAttribute TmemberDTO dto
								  ,HttpServletRequest req
								  ,HttpServletResponse resp
								  ,HttpSession session) {
	ModelAndView mav=new ModelAndView();
	 
	 String mid=req.getParameter("mid").trim();
	 String mpasswd=req.getParameter("mpasswd").trim();
	 
	mav.setViewName("Tmember/TloginPro");
	mav.addObject("root",Utility.getRoot());
	mav.addObject("Mid", dto.getMid());
	mav.addObject("Mpasswd",dto.getMpasswd());
	
	dto.setMid(mid);
	dto.setMpasswd(mpasswd);
	 
	  
    int res=0;
    res=dao.login(dto);
    System.out.println("res:" +res);
    
  
    if(res==1) {
       req.setAttribute("id", dto.getMid());
       req.setAttribute("passwd", dto.getMpasswd()); 
       
     	session.setAttribute("s_id", dto.getMid());
     	session.setAttribute("memid", dto.getMid());
     	session.setAttribute("s_passwd", dto.getMpasswd());
     	session.setAttribute("s_mlevel", dto.getMlevel());
     	
     	String id=dto.getMid();
        dto=dao.read(id);
        session.setAttribute("s_mlevel", dto.getMlevel());	
        session.setAttribute("s_mnum", dto.getMnum());
//-----------------------------------------쿠키 아이디저장
       String c_id=req.getParameter("c_id");
          if(c_id==null){ 
             c_id="";
          }
       Cookie cookie=null;
       if(c_id.equals("SAVE")) {
          cookie=new Cookie("c_id", dto.getMid());
          cookie.setMaxAge(60*60*24*31);//1달동안 쿠키저장
       }else {
          cookie=new Cookie("c_id", "");
          cookie.setMaxAge(0);
       }
       resp.addCookie(cookie);//사용자 pc에 쿠키값저장
//----------------------------------------------
       mav.setViewName("Tmember/TloginForm");
       
    }// if end
    
    req.setAttribute("res", new Integer(res));
	
	
	return mav;
	}
//---------------------------------------------------------------------------------------	
	@RequestMapping(value="Tmember/Tlogout.do" ,method=RequestMethod.POST)
	public ModelAndView Tlogout(TmemberDTO dto) {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("Tmember/Tlogout");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dto);
		return mav;
	}
//-------------------------------------------------------------------------------------------	
	
	@RequestMapping(value="Tmember/TmemberForm.do")
	public ModelAndView TmemberForm(TmemberDTO dto) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Tmember/TmemberForm");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dto);
		return mav;
	}//

//---------------------------------------------------------------------------------------------
	@RequestMapping(value="Tmember/TidcheckForm.do")
	public ModelAndView TidcheckForm(String Mid) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Tmember/TidcheckForm");
		mav.addObject("root",Utility.getRoot());
	
		return mav;
	}// TidcheckForm
//----------------------------------------------------------------------------------------------	
	@RequestMapping(value="Tmember/TidcheckPro.do" ,method=RequestMethod.POST)
	   public ModelAndView TidcheckPro(@ModelAttribute TmemberDTO dto
	                          ,HttpServletRequest req
	                          ,HttpServletResponse resp
	                          ,HttpSession session) {
	      
	      ModelAndView mav= new ModelAndView();
	      String mid=req.getParameter("Mid").trim();
	      mav.setViewName("Tmember/TidcheckPro");
	      mav.addObject("root", Utility.getRoot());
	      int cnt=dao.duplecateID(mid);
	      
	      String msg="";
	      if(cnt==0) {
	         req.setAttribute("mid", mid);
	         msg+="사용가능한 아이디입니다.";
	      }else {
	         msg+="이미 사용하고 있는 아이디입니다.";
	      }
	   mav.addObject("msg",msg);
	   return mav;   
	   }

	
	
//-----------------------------------------------------------------------------------------------
	@RequestMapping(value="Tmember/TemailcheckForm.do")
	public ModelAndView TemailcheckForm(String Memail) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Tmember/TemailcheckForm");
		mav.addObject("root",Utility.getRoot());
		mav.addObject("Mid", dao.duplecateEMAIL(Memail));
		return mav;
	}// TidcheckForm
	
//-------------------------------------------------------------------------------------------------
	@RequestMapping(value="Tmember/TemailcheckPro.do" ,method=RequestMethod.POST)
	   public ModelAndView TemailcheckPro(@ModelAttribute TmemberDTO dto
	                          ,HttpServletRequest req
	                          ,HttpServletResponse resp
	                          ,HttpSession session) {
	      
	      ModelAndView mav= new ModelAndView();
	      String memail=req.getParameter("Memail").trim();
	      mav.setViewName("Tmember/TemailcheckPro");
	      mav.addObject("root", Utility.getRoot());
	      
	      int cnt=0;
	      cnt=dao.duplecateEMAIL(memail);
	      
	      String msg=" ";
	      if(cnt==0) {
	         msg+="사용 가능한 이메일입니다";
	         req.setAttribute("memail", memail);

	      }else {
	         msg+="이미 사용하고 있는 이메일 입니다.";
	      }
	      mav.addObject("msg", msg);
	   return mav;   
	   }
//------------------------------------------------------------------------------
	@RequestMapping(value="Tmember/TmemberPro.do", method=RequestMethod.POST )
	public ModelAndView TmemberPro(@ModelAttribute TmemberDTO dto
								  ,HttpServletRequest req
								  ,HttpServletResponse resp
								  ,HttpSession session) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Tmember/TmemberPro");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("Mid",dto.getMid());
		mav.addObject("Mpasswd", dto.getMpasswd());
		mav.addObject("Mname", dto.getMname());
		mav.addObject("Mtel",dto.getMtel());
		mav.addObject("Memail",dto.getMemail());
		mav.addObject("Mzipcode", dto.getMzipcode());
		mav.addObject("Maddress1", dto.getMaddress1());
		mav.addObject("Maddress2",dto.getMaddress2());
		mav.addObject("Mnum", dto.getMnum());
		mav.addObject("Mlevel", dto.getMlevel());
		
		String Mid = req.getParameter("Mid").trim();
		String Mpasswd = req.getParameter("Mpasswd").trim();
		String Mname = req.getParameter("Mname").trim();
		String Mtel = req.getParameter("Mtel").trim();
		String Memail = req.getParameter("Memail").trim();
		String Mzipcode = req.getParameter("Mzipcode").trim();
		String Maddress1 = req.getParameter("Maddress1").trim();
		String Maddress2 = req.getParameter("Maddress2").trim();
		int Mnum = Integer.parseInt(req.getParameter("Mnum"));
	    String Mlevel=req.getParameter("Mlevel").trim();
		
	    dto.setMid(Mid);
		dto.setMpasswd(Mpasswd);
		dto.setMname(Mname);
		dto.setMtel(Mtel);
		dto.setMemail(Memail);
		dto.setMzipcode(Mzipcode);
		dto.setMaddress1(Maddress1);
		dto.setMaddress2(Maddress2);
		dto.setMnum(Mnum);
		dto.setMlevel(Mlevel);
		
		int cnt=dao.insert(dto);
		
		String msg=" ";
		
		if(cnt==1) {
			
			msg+="회원가입에 완료되었습니다.";
		}else {
			msg+="회원가입에 실패하였습니다.";
		}//if end
		return mav;
	}//insert end
//-------------------------------------------------------------------------------------------------------
	@RequestMapping(value="Tmember/Tmodifycheck.do")
	public ModelAndView Tmodifycheck(@ModelAttribute TmemberDTO dto
									 ,HttpServletRequest req) {	
	ModelAndView mav = new ModelAndView();
	mav.setViewName("Tmember/Tmodifycheck");
	mav.addObject("root", Utility.getRoot());
	mav.addObject("Mid",dto.getMid());
	mav.addObject("Mpasswd",dto.getMpasswd());
	return mav;
	}//modifycheck end
	
	
	
//------------------------------------------------------------------------	
	@RequestMapping(value="Tmember/TmodifyForm.do")
	public ModelAndView TmodifyForm(@ModelAttribute TmemberDTO dto
									 ,HttpServletRequest req) {
	ModelAndView mav = new ModelAndView();
	mav.setViewName("Tmember/TmodifyForm");
	mav.addObject("root", Utility.getRoot());
	mav.addObject("dto", dao.modify(dto));
	mav.addObject("Mid", dto.getMid());
	mav.addObject("Mpasswd",dto.getMpasswd());
	mav.addObject("Mnum", dto.getMnum());
	mav.addObject("Mlevel",dto.getMlevel());
	mav.addObject("Mtel", dto.getMtel());
	
	
	
	
	if(dto==null) {
	
		
		 
	}else {
		
 		 
	
}
	return mav;
	}
//------------------------------------------------------------------------------------------------------
	  @RequestMapping(value="/Tmember/TmodifyPro.do",method=RequestMethod.POST)
	    public ModelAndView TmodifyPro(TmemberDTO dto, HttpServletRequest req) {
	      	ModelAndView mav=new ModelAndView();
	  	  	mav.setViewName("Tmember/msgView");
	      	mav.addObject("root", Utility.getRoot());
	  
	      	return mav;
	  }
	  
//-------------------------------------------------------------------------------------------------------
	  @RequestMapping(value = "Tmember/Tmy.do")
	   public ModelAndView Tmy() {
	      ModelAndView mav=new ModelAndView();
	      mav.setViewName("Tmember/Tmy");
	      return mav;
	   }//Tmy() end
}//class end
