package kr.co.donghae.Treview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.donghae.Treview.TreviewDTO;
import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class TreviewCont {

	@Autowired
	TreviewDAO dao;
	FileUploadService fileUploadService;

	public TreviewCont() {
		System.out.println("---TreviewCont()객체 생성됨");
	}// MediaCont() end

	@RequestMapping("Treview/Treview.do")
	public ModelAndView list(TreviewDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Treview/Treviewlist");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("list", dao.list());
		return mav;
	}// list() end
	
	@RequestMapping(value = "/Treview/read.do", method = RequestMethod.GET)
	public ModelAndView read(int rnum, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		TreviewDTO dto=dao.read(rnum);
		if(dto!=null) {
          mav.setViewName("Treview/Treviewread");
          mav.addObject("root", Utility.getRoot());
          mav.addObject("dto",dto);       
        }//if end
		
		return mav;
	}//read() end

	@RequestMapping(value = "Treview/createrform.do", method = RequestMethod.GET)
	public String createForm() {
		return "Treview/Treviewcreate";
		
	}// createform() end

	@RequestMapping(value = "Treview/create.do", method = RequestMethod.POST)
	public ModelAndView createProc(@ModelAttribute TreviewDTO dto
									,MultipartHttpServletRequest req
									,HttpServletResponse resp
									,HttpSession session
									,List<MultipartFile> files) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("Treview/msgView");
		mav.addObject("root", Utility.getRoot());
		
		//-------------------------------------
	
		String uploadPath=req.getRealPath("./storage");
		
		//1)<input type='file' name='photonameMF'> 파일 가져오기
		MultipartFile photonameMF=dto.getPhotonameMF();
		String photoname=UploadSaveManager.saveFileSpring30(photonameMF, uploadPath);
		dto.setRphoto_name(photoname);
		dto.setRphoto_size((long)photonameMF.getSize());
		
		/*/-------------------------------------x
		
		List<MultipartFile> files = uploadForm.getFiles();

		// success.jsp 로 보낼 파일 이름 저장
		List<String> fileNames = new ArrayList<String>();

		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				String fileName = multipartFile.getOriginalFilename();

				String path = uploadForm.getUpDir() + fileName;

				File f = new File(path);

				multipartFile.transferTo(f);

				fileNames.add(fileName);

			}//for end
		}//if end
		rep.addAttribute("files", fileNames);
		*/
		
		int cnt=dao.create(dto);
		
		if(cnt==0) {
			String msg  ="<p>후기 등록 실패</p>";
			String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2="<input type='button' value='목록' onclick='location.href=\"Treview/Treview.do\"'>";
			mav.addObject("msg1",  msg);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
         
		}else {
			String msg  ="<p>후기 등록 성공</p>";
			String link1="<input type='button' value='계속등록' onclick='location.href=\"createrform.do\"'>";
			String link2="<input type='button' value='그룹목록' onclick='location.href=\"Treview.do\"'>";
			mav.addObject("msg1",  msg);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		}//if end
		return mav;
		
	}//createProc() end
	
	
	@RequestMapping(value="Treview/updateform.do", method = RequestMethod.GET)
	   public ModelAndView updateForm(TreviewDTO dto) {
	      ModelAndView mav = new ModelAndView();
	      mav.setViewName("Treview/Treviewupd");
	      mav.addObject("root", Utility.getRoot());
	      //수정하고자 하는 행 가져오기
	      mav.addObject("dto", dao.read(dto.getRnum()));
	      return mav;
	   }//updateForm() end
	
	@RequestMapping(value="Treview/update.do", method = RequestMethod.POST)
	public ModelAndView updateProc(TreviewDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Treview/msgView");
		mav.addObject("root", Utility.getRoot());
		
		//-------------------------------------
		String basePath=req.getRealPath("./storage");
		
		//기존에 저장된 정보 가져오기
		TreviewDTO oldDTO=dao.read(dto.getRnum());
		
//------------------------------------------------------------------
		//파일을 수정할 것인지?
		//1)
		MultipartFile photonameMF=dto.getPhotonameMF();
		if((long)photonameMF.getSize()>0) { //새로운 포스터 파일이 전송되었는지?
			//기존 파일 삭제
			UploadSaveManager.deleteFile(basePath, oldDTO.getRphoto_name());
			//신규 파일 저장
			String photoname=UploadSaveManager.saveFileSpring30(photonameMF, basePath);
			dto.setRphoto_name(photoname);
			dto.setRphoto_size((long)photonameMF.getSize());
		}else {
			//포스터 파일을 수정하지 않는 경우
			dto.setRphoto_name(oldDTO.getRphoto_name());
			dto.setRphoto_size(oldDTO.getRphoto_size());
		}//if end
		
//------------------------------------------------------------------		
		int cnt=dao.update(dto);//테이블저장
        if(cnt==0) {
           mav.addObject("msg1","<p>수정 실패</p>");
           mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
           mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"Treview.do\"'>");
        }else {
           mav.addObject("msg1","<p>수정 완료</p>");
           mav.addObject("link1", "<input type='button' value='목록' onclick='location.href=\"Treview.do\"'>");
        }//if end
		
		return mav;
	}//updateProc() end
	
	@RequestMapping(value="Treview/delete.do", method = RequestMethod.GET)
	public ModelAndView deleteForm(TreviewDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Treview/Treviewdel");
		mav.addObject("root", Utility.getRoot());
		//삭제하고자 하는 행 가져오기
		mav.addObject("dto", dao.read(dto.getRnum()));
		return mav;
	}//deleteForm() end
	
	@RequestMapping(value="Treview/delete.do", method = RequestMethod.POST)
	public ModelAndView deleteProc(TreviewDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Treview/msgView");
		mav.addObject("root", Utility.getRoot());
		
		//삭제하고자 하는 정보 가져오기
		TreviewDTO oldDTO=dao.read(dto.getRnum());
		int cnt=dao.delete(dto.getRnum());
		
		if(cnt==0) { //새로운 포스터 파일이 전송되었는지?
			mav.addObject("msg1","<p>삭제 실패</p>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"Treview.do\"'>");
		}else {
			//관련 파일 삭제
			String basePath=req.getRealPath("/Treview/storage");
			UploadSaveManager.deleteFile(basePath, oldDTO.getRphoto_name());
			mav.addObject("msg1","<p>삭제 되었습니다</p>");
			mav.addObject("link1", "<input type='button' value='목록' onclick='location.href=\"Treview.do\"'>");
		}//if end
		return mav;
	}//deleteProc() end
	
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
	
	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "Treview/listpage.do", method = RequestMethod.GET)
	public void getListPage(Model model) throws Exception {
	  
	 List list = null; 
	 list = dao.list();
	 model.addAttribute("list", list);   
	}//getListPage end
	
}// class end
