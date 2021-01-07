package kr.co.donghae.Troute;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.utility.Utility;


@Controller
public class TrouteCont {

	@Autowired
	TrouteDAO dao;
	
	public TrouteCont() {
		System.out.println("---TrouteCont() 객체 생성");
	}
	
	
	@RequestMapping("Troute/Troute.do")
	public ModelAndView list(@ModelAttribute TrouteDTO dto, String tregion, HttpServletRequest req) {
		 ModelAndView mav=new ModelAndView();
		  mav.setViewName("Troute/TrouteList"); 
		  tregion=req.getParameter("tregion");
		  mav.addObject("tregion", tregion);

		  mav.addObject("list", dao.list(dto));
	  
		return mav;	
	}//loginForm end
	
	@RequestMapping("Troute/TrouteRead.do")
	public ModelAndView read(@ModelAttribute String tregion, HttpServletRequest req) {
		 ModelAndView mav=new ModelAndView();
		  mav.setViewName("Troute/TrouteRead");
		  tregion=req.getParameter("tregion");
		  mav.addObject("tregion", tregion);
		
		return mav;	
	}//loginForm end
	
	
	
}//class end
