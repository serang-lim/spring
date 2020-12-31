package kr.co.donghae.Treview;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

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
	FileUploadService fileUploadService;

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
		if(dto!=null) {
          mav.setViewName("Treview/Treviewread");
          mav.addObject("root", Utility.getRoot());
          mav.addObject("dto",dto);       
        }//if end
		
		return mav;
	}//read() end
	
//----------------------------------------------------------------------------------------
	@RequestMapping(value = "Treview/createrform.do", method = RequestMethod.GET)
	public String createForm() {
		return "Treview/Treviewcreate";
		
	}// createform() end

	@RequestMapping(value = "Treview/create.do", method = RequestMethod.POST)
	public ModelAndView createProc(@ModelAttribute TreviewDTO dto
									,MultipartHttpServletRequest req
									,HttpServletResponse resp
									,HttpSession session
									,@RequestParam("photonameMF")MultipartFile file
									) {
		
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
		
		/*/-------------------------------------
		,List<MultipartFile> files
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
		
		System.out.println(dto);
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
	private FileOutputStream fos;
	
	public void writeFile(MultipartFile file, String path, String fileName){
        
        try{
            byte fileData[] = file.getBytes();
            fos = new FileOutputStream(path + "\\" + fileName);
            fos.write(fileData);
         
        }catch(Exception e){
            e.printStackTrace();
         finally{
            if(fos != null){
                try{
                    fos.close();
                }catch(Exception e){}
                }
        }// try end;
     }// wirteFile() end;
	
	
//----------------------------------------------------------------------------------------	
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

//----------------------------------------------------------------------------------------	
	@RequestMapping(value="Treview/delete.do", method = RequestMethod.GET)
	public ModelAndView deleteForm(TreviewDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Treview/Treviewdel");
		mav.addObject("root", Utility.getRoot());
		//삭제하고자 하는 행 가져오기
		mav.addObject("dto", dao.read(dto.getRnum()));
		return mav;
	}//deleteForm() end

//----------------------------------------------------------------------------------------	
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

//----------------------------------------------------------------------------------------	
	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "Treview/listpage.do", method = RequestMethod.GET)
	public void getListPage(Model model) throws Exception {
	  
	 List list = null; 
	 list = dao.list();
	 model.addAttribute("list", list);   
	}//getListPage end
	

//----------------------------------------------------------------------------------------		
	@RequestMapping("/file_uploader")
	 public String file_uploader(HttpServletRequest request, HttpServletResponse response, Editor editor){
		 String return1=request.getParameter("callback");
		 String return2="?callback_func=" + request.getParameter("callback_func");
		 String return3="";
		 String name = "";
		 try {
			if(editor.getFiledata() != null && editor.getFiledata().getOriginalFilename() != null && !editor.getFiledata().getOriginalFilename().equals("")) {
	             // 기존 상단 코드를 막고 하단코드를 이용
	            name = editor.getFiledata().getOriginalFilename().substring(editor.getFiledata().getOriginalFilename().lastIndexOf(File.separator)+1);
				String filename_ext = name.substring(name.lastIndexOf(".")+1);
				filename_ext = filename_ext.toLowerCase();
			   	String[] allow_file = {"jpg","png","bmp","gif"};
			   	int cnt = 0;
			   	for(int i=0; i<allow_file.length; i++) {
			   		if(filename_ext.equals(allow_file[i])){
			   			cnt++;
			   		}
			   	}
			   	if(cnt == 0) {
			   		return3 = "&errstr="+name;
			   	} else {
			   		//파일 기본경로
		    		String dftFilePath = request.getSession().getServletContext().getRealPath("/");
		    		//파일 기본경로 _ 상세경로
		    		String filePath = dftFilePath + "resources"+ File.separator + "editor" + File.separator +"upload" + File.separator;
		    		File file = new File(filePath);
		    		if(!file.exists()) {
		    			file.mkdirs();
		    		}
		    		String realFileNm = "";
		    		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
					String today= formatter.format(new java.util.Date());
					realFileNm = today+UUID.randomUUID().toString() + name.substring(name.lastIndexOf("."));
					String rlFileNm = filePath + realFileNm;
					///////////////// 서버에 파일쓰기 /////////////////
					editor.getFiledata().transferTo(new File(rlFileNm));
					///////////////// 서버에 파일쓰기 /////////////////
		    		return3 += "&bNewLine=true";
		    		return3 += "&sFileName="+ name;
		    		return3 += "&sFileURL=/resources/editor/upload/"+realFileNm;
			   	}
			}else {
				  return3 += "&errstr=error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "redirect:"+return1+return2+return3;
}	
//----------------------------------------------------------------------------------------	
	@RequestMapping("/file_uploader_html5")
	 public void file_uploader_html5(HttpServletRequest request, HttpServletResponse response){
		try {
			 //파일정보
			 String sFileInfo = "";
			 //파일명을 받는다 - 일반 원본파일명
			 String filename = request.getHeader("file-name");
			 //파일 확장자
			 String filename_ext = filename.substring(filename.lastIndexOf(".")+1);
			 //확장자를소문자로 변경
			 filename_ext = filename_ext.toLowerCase();
			 	
			 //이미지 검증 배열변수
			 String[] allow_file = {"jpg","png","bmp","gif"};

			 //돌리면서 확장자가 이미지인지 
			 int cnt = 0;
			 for(int i=0; i<allow_file.length; i++) {
			 	if(filename_ext.equals(allow_file[i])){
			 		cnt++;
			 	}
			 }

			 //이미지가 아님
			 if(cnt == 0) {
				 PrintWriter print = response.getWriter();
				 print.print("NOTALLOW_"+filename);
				 print.flush();
				 print.close();
			 } else {
			 //이미지이므로 신규 파일로 디렉토리 설정 및 업로드	
			 //파일 기본경로
			 String dftFilePath = request.getSession().getServletContext().getRealPath("/");
			 //파일 기본경로 _ 상세경로
			 String filePath = dftFilePath + "resources" + File.separator + "editor" + File.separator +"multiupload" + File.separator;
			 File file = new File(filePath);
			 if(!file.exists()) {
			 	file.mkdirs();
			 }
			 String realFileNm = "";
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			 String today= formatter.format(new java.util.Date());
			 realFileNm = today+UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
			 String rlFileNm = filePath + realFileNm;
			 ///////////////// 서버에 파일쓰기 ///////////////// 
			 InputStream is = request.getInputStream();
			 OutputStream os=new FileOutputStream(rlFileNm);
			 int numRead;
			 byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
			 while((numRead = is.read(b,0,b.length)) != -1){
			 	os.write(b,0,numRead);
			 }
			 if(is != null) {
			 	is.close();
			 }
			 os.flush();
			 os.close();
			 ///////////////// 서버에 파일쓰기 /////////////////

			 // 정보 출력
			 sFileInfo += "&bNewLine=true";
			 // img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
			 sFileInfo += "&sFileName="+ filename;;
			 sFileInfo += "&sFileURL="+"/resources/editor/multiupload/"+realFileNm;
			 PrintWriter print = response.getWriter();
			 print.print(sFileInfo);
			 print.flush();
			 print.close();
			 }	
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}// class end
