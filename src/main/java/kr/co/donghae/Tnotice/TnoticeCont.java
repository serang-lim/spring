package kr.co.donghae.Tnotice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class TnoticeCont {

	@Autowired
	TnoticeDAO dao;
	
	public TnoticeCont() {
		System.out.println("---TnoticeCont()객체 생성됨...---");
	}//TnoticeCont end
	
	@RequestMapping("/Tnotice/TnoticeList.do")
	public ModelAndView Tnoticelist(TnoticeDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Tnotice/TnoticeList");
		mav.addObject("root",Utility.getRoot());
		mav.addObject("list",dao.Tnoticelist());
		return mav;
	}//Tnoticelist end
	
	@RequestMapping(value="/Tnotice/TnoticeRead.do", method=RequestMethod.GET)
	public ModelAndView read(int nonum) {
		ModelAndView mav=new ModelAndView();
		TnoticeDTO dto = dao.read(nonum);
		mav.addObject("root",Utility.getRoot());
		mav.addObject("dto",dto);
		return mav;
		
	}//read end	
	
	@RequestMapping(value = "/Tnotice/TnoticeForm.do", method = RequestMethod.POST )
	public ModelAndView createForm(TnoticeDTO dto) {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("Tnotice/TnoticeForm");
		mav.addObject("root",Utility.getRoot());
		mav.addObject("nonum",dto.getNonum());
		return mav;
	}//createForm() end
	
	@RequestMapping(value = "/Tnotice/TnoticeInsert.do", method = RequestMethod.POST )
	public ModelAndView insertForm(TnoticeDTO dto) {
		ModelAndView mav= new ModelAndView();
		int cnt= dao.create(dto);
		mav.setViewName("Tnotice/TnoticeList");
		mav.addObject("root",Utility.getRoot());
		mav.addObject("list",dao.Tnoticelist());
		return mav;
	}//createForm() end	
	
	@RequestMapping(value = "/Tnotice/TnoticeUpdateForm.do")
	public ModelAndView updateForm(TnoticeDTO dto) {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("Tnotice/TnoticeUpdateForm");
		mav.addObject("root",Utility.getRoot());
		//수정하고자 하는 행 가져오기
		mav.addObject("dto",dao.read(dto.getNonum()));
		return mav;
	}//createForm() end
	
	
	@RequestMapping(value = "/Tnotice/TnoticeUpdateProc.do", method = RequestMethod.POST)
	public ModelAndView updateProc(TnoticeDTO dto, HttpServletRequest req) {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("Tnotice/msgView");
		mav.addObject("root",Utility.getRoot());
		//기존에 저장된 정보 가져오기 
		TnoticeDTO oldDTO=dao.read(dto.getNonum());
			int cnt=dao.update(dto);//테이블저장
			if(cnt==0) {
				mav.addObject("msg1","<p>공지사항 수정 실패!</p>");
				mav.addObject("img", "<p><img src='../images/k1.png'></p>");
				mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
				mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"./TnoticeList.do?nonum="+dto.getNonum()+"\"'>");
			}else {
				mav.addObject("msg1","<p>공지사항 수정 성공!</p>");
				mav.addObject("img", "<p><img src='../images/k2.png'></p>");
				mav.addObject("link1", "<input type='button' value='목록' onclick='location.href=\"./TnoticeList.do?nonum="+dto.getNonum()+"\"'>");
			}//if end
			
			
			
			return mav;
	}//createProc() end

	
	@RequestMapping(value = "/Tnotice/TnoticeDeleteForm.do", method = RequestMethod.GET )
	public ModelAndView deleteForm(TnoticeDTO dto) {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("Tnotice/TnoticeDeleteForm");
		mav.addObject("root",Utility.getRoot());
		//삭제하고자 하는 행 가져오기
		mav.addObject("dto",dao.read(dto.getNonum()));
		return mav;
	}//deleteForm() end
	
	
	@RequestMapping(value = "/Tnotice/TnoticeDeleteProc.do", method = RequestMethod.POST)
	public ModelAndView deleteProc(TnoticeDTO dto, HttpServletRequest req) {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("Tnotice/msgView");
		mav.addObject("root", Utility.getRoot());
		
		//삭제하고자 하는 정보 가져오기
		TnoticeDTO oldDTO= dao.read(dto.getNonum());
		int cnt=dao.delete(dto.getNonum());
		if(cnt==0) {
			mav.addObject("msg1","<p>공지사항 삭제 실패!</p>");
			mav.addObject("img","<img src='../images/k1.png'>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link1", "<input type='button' value='목록' onclick='location.href=\"./TnoticeList.do?nonum="+dto.getNonum()+"\"'>");
		}else {
			mav.addObject("msg1","<p>공지사항이 삭제 되었습니다</p>");
			mav.addObject("img","<img src='../images/k2.png'>");
			mav.addObject("link1", "<input type='button' value='목록' onclick='location.href=\"./TnoticeList.do?nonum="+dto.getNonum()+"\"'>");
		}//if end
		return mav;
	}//deleteProc end
	



	
}//class end
