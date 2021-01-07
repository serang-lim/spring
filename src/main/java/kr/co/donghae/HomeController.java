package kr.co.donghae;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
   
   public HomeController() {
      System.out.println("---HomeController() 객체 생성됨...");
   }//생성자함수 end
      
   //donghae프로젝트의 첫페이지 호출
   //http://localhost:9090/donghae/home.do
   //redirect: 등록한 명령어를 호출할 수 있다.
   @RequestMapping("/home.do")
   public ModelAndView home() {
      ModelAndView mav=new ModelAndView();
      mav.setViewName("redirect:./index.do");
      return mav;
   }//home() end
   
   @RequestMapping("/index.do")
   public ModelAndView index() {
      ModelAndView mav=new ModelAndView();
      mav.setViewName("index");
      return mav;
   }//home() end
   
}//class end