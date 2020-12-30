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
import org.springframework.web.servlet.ModelAndView;

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
	
	System.out.println(mid);
    System.out.println(mpasswd);
	
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
	
	
	
	
	@RequestMapping(value="Tmember/TmemberForm.do")
	public ModelAndView TmemberForm(TmemberDTO dto) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Tmember/TmemberForm");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dto);
		return mav;
	}//
	
	

	
	
	
}//class end
