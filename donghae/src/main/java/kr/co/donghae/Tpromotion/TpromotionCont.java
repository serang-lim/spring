package kr.co.donghae.Tpromotion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping("Tpromotion/Tpromotion.do")
	public ModelAndView list(TpromotionDTO dto) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Tpromotion/TpromotionList");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("list", dao.list(dto));



		return mav;
	}//home() end
	
	@RequestMapping("Tpromotion/TpromotionRead.do")
	public ModelAndView Read() {
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Tpromotion/TpromotionRead");
		return mav;
	}//home() end
	
	@RequestMapping("Tpromotion/TpromotionWrite.do")
	public ModelAndView Write() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Tpromotion/TpromotionWrite");
		return mav;
	}//write() end
	
	
	
    @RequestMapping(value="Tpromotion/TpromotionInsert.do",method=RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute TpromotionDTO dto, HttpServletRequest req, HttpSession session) {
    	ModelAndView mav=new ModelAndView();
  	  mav.setViewName("Tpromotion/msgView");
  	  mav.addObject("root", Utility.getRoot());


//--------------------------------------------------
  	  //파일 저장 폴더의 실제 물리적인 경로 가져오기
  	  String basePath=req.getRealPath("./storage");
  	  //<input type="file" name"filenameMF"> 파일 가져오기
  	  MultipartFile imagenameMF=dto.getTimage_nameMF();
  	  String imagename=UploadSaveManager.saveFileSpring30(imagenameMF, basePath);
  	  dto.setTimage_name(imagename);
  	  dto.setTimage_size((int)imagenameMF.getSize());
  	  
//--------------------------------------------------
  	  
  	  
  	  int cnt=dao.insert(dto);
  	  if(cnt==0) {
  		  String msg1="<p>프로모션 등록 실패</p>";
  		  String img="<img src='../images/1.png'>"+dto.toString();
  		  String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
  		  String link2="<input type='button' value='목록으로' onclick='location.href=\"/Tpromotion.do\"'>";
  		  mav.addObject("msg1",msg1);
  		  mav.addObject("img", img);
  		  mav.addObject("link1", link1);
  		  mav.addObject("link2", link2);
      	
  	  }else {
  		  String msg1="<p>프로모션 등록 성공</p>";
  		  String img="<img src='../images/2.png'>";
  		  String link1="<input type='button' value='계속등록' onclick='location.href=\"TpromotionWrite.do\"'>";
  		  String link2="<input type='button' value='목록으로' onclick='location.href=\"Tpromotion.do\"'>";
  		  mav.addObject("msg1",msg1);
  		  mav.addObject("img", img);
  		  mav.addObject("link1", link1);
  		  mav.addObject("link2", link2);
      	  
 
  	  }//if end
		return mav;
  	  
    }//createProc() end
	
}//class end
