package kr.co.donghae.Tlikelocation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.donghae.Tnotice.TnoticeDTO;
import net.utility.Utility;

@Controller
public class TlikelocationCont {
	
	@Autowired
	TlikelocationDAO dao;

	public TlikelocationCont() {
		System.out.println("---Tlikelocation()객체 생성됨...---");
	}
	
	@RequestMapping("/Tlikelocation/TlikelocationList.do")
	public ModelAndView Tlikelocationlist(TlikelocationDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Tlikelocation/TlikelocationList");
		mav.addObject("root",Utility.getRoot());
		mav.addObject("list",dao.Tlikelocationlist());
		mav.addObject("count",dao.count());
		return mav;
	}//Tnoticelist end
	
	
	@RequestMapping(value = "/Tlikelocation/TlikelocationDeleteForm.do", method = RequestMethod.GET )
	public ModelAndView deleteForm(String check ,HttpServletRequest req) {
		ModelAndView mav= new ModelAndView();
		int lno=Integer.parseInt(check);
		mav.setViewName("Tlikelocation/TlikelocationDeleteForm");
		mav.addObject("root",Utility.getRoot());
		//삭제하고자 하는 행 가져오기
		mav.addObject("lno",lno);
		return mav;
	}//deleteForm() end
	
	@RequestMapping(value = "/Tlikelocation/TlikelocationDeleteProc.do", method = RequestMethod.POST)
	public ModelAndView deleteProc(TlikelocationDTO dto, HttpServletRequest req) {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("Tlikelocation/msgView");
		mav.addObject("root", Utility.getRoot());
		
		//삭제하고자 하는 정보 가져오기
		int cnt=dao.delete(dto.getLno());
		if(cnt==0) {
			mav.addObject("msg1","<p>공지사항 삭제 실패!</p>");
			mav.addObject("img","<img src='../images/k1.png'>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link1", "<input type='button' value='목록' onclick='location.href=\"./TlikelocationList.do?nonum="+dto.getLno()+"\"'>");
		}else {
			mav.addObject("msg1","<p>공지사항이 삭제 되었습니다</p>");
			mav.addObject("img","<img src='../images/k2.png'>");
			mav.addObject("link1", "<input type='button' value='목록' onclick='location.href=\"./TlikelocationList.do?nonum="+dto.getLno()+"\"'>");
		}//if end
		return mav;
	}//deleteProc end
	
}//class end
