package kr.co.donghae.Treserve;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.donghae.Tpromotion.TpromotionDTO;
import net.utility.Utility;

@Controller
public class TreserveCont {

	@Inject
	TreserveDAO dao;
	
	public TreserveCont() {
		System.out.println("---TreserveCont()객체 생성됨");
	}//TreserveCont()
	
	@RequestMapping(value = "/Treserve/create.do")
	public ModelAndView create(int Tbno) {
		ModelAndView mav=new ModelAndView();
		TpromotionDTO prodto=dao.create(Tbno);
		if(prodto==null) {
			mav.addObject("msg", "<div>정보를 읽어오는데 실패하였습니다</div>");
			mav.setViewName("Treserve/msgView");
		}else {
			mav.setViewName("Treserve/createForm");
			mav.addObject("root", Utility.getRoot());
			mav.addObject("prodto", prodto);
		}//if end
		return mav;
	}//create() end
	
	@RequestMapping(value = "Treserve/createproc.do")
	public String createproc(@ModelAttribute TreserveDTO dto,
								   @ModelAttribute TpromotionDTO prodto,
								    HttpServletRequest req,
								    HttpServletResponse resp,
								    HttpSession session) {
		session=req.getSession();
		String id	=(String)session.getAttribute("s_id");
		int Tbno	=prodto.getTbno();
		String resdate=req.getParameter("resdate");
		int cnt=dao.createproc(Tbno, id, resdate);
		String msg="";
		String root=Utility.getRoot();
		
		if(cnt==0) {
			msg+="<script>";
			msg+="	alert('예약실패했습니다');";
			msg+="</script>";
			msg+="<meta http-equiv='refresh' content='0;url=" + root + "/Tpromotion/Tpromotion.do'>";
		}else {
			msg+="<script>";
			msg+="	alert('예약성공했습니다 마이페이지에서 확인해주세요');";
			msg+="</script>";
			msg+="<meta http-equiv='refresh' content='0;url=" + root + "/Tpromotion/Tpromotion.do'>";
		
		}//if end
		req.setAttribute("msg", msg);
		return "Treserve/msgView";
	}//createproc() end
	
	@RequestMapping(value = "Treserve/duplecate.do")
	public void duplecate(HttpServletRequest req, HttpServletResponse resp,
							@ModelAttribute TpromotionDTO prodto,
							HttpSession session) {
		try {
			session=req.getSession();
			String id	=(String)session.getAttribute("s_id");
			int Tbno	=prodto.getTbno();
			String resdate=req.getParameter("resdate");
			int cnt=dao.duplecate(Tbno, id, resdate);
			JSONObject json=new JSONObject();
			json.put("count", cnt);
			resp.setContentType("text/plain; charset=UTF-8");
	        PrintWriter out=resp.getWriter();
	        out.println(json.toString());
	        out.flush();
	        out.close();
		}catch (Exception e) {
			System.out.println("예약중복확인 쿠키 실패:"+e);
		}
	}//duplecate() end
	
	@RequestMapping(value = "Treserve/listent.do")
	public ModelAndView listent(TreserveDTO dto,
								HttpServletRequest req,
							    HttpServletResponse resp,
							    HttpSession session) {
		ModelAndView mav=new ModelAndView();
		session=req.getSession();
		String resbno	=(String)session.getAttribute("s_mnum");
		List<TreserveDTO> list=dao.listent(resbno);
		if(list==null) {
			String msg="";
			String root=Utility.getRoot();
			msg+="<script>";
			msg+="	alert('예약 신청내역이 없습니다');";
			msg+="</script>";
			msg+="<meta http-equiv='refresh' content='0;url="+root+"/home.do'>";
			req.setAttribute("msg", msg);
			mav.setViewName("Treserve/msgView");
		}else {
			mav.addObject("list", dao.listent(resbno));
			mav.addObject("id", dto.getResid());
			mav.setViewName("Treserve/listent");
			mav.addObject("root", Utility.getRoot());
		}
		return mav;
	}//listent() end
	
	@RequestMapping(value = "Treserve/entUpdate.do")
	public String entUpdate(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mav=new ModelAndView();
		String resid	=req.getParameter("resid");
		int resnum  =Integer.parseInt(req.getParameter("resnum"));
		String resdate=req.getParameter("resdate");
		System.out.println(resid);
		System.out.println(resnum);
		System.out.println(resdate);
		
		int cnt=dao.entUpdate(resid, resnum, resdate);
		String root=Utility.getRoot();
		String msg="";
		if(cnt==0) {
			msg+="<script>";
			msg+="	alert('실패했습니다');";
			msg+="</script>";
			msg+="<meta http-equiv='refresh' content='0;url="+root+"/Treserve/listent.do'>";
		}else {
			msg+="<script>";
			msg+="	alert('예약완료 처리되었습니다');";
			msg+="</script>";
			msg+="<meta http-equiv='refresh' content='0;url="+root+"/Treserve/listent.do'>";
		}
		req.setAttribute("msg", msg);
		return "Treserve/msgView";
	}//entUpdate() end
	
	@RequestMapping(value = "Treserve/entUpdated.do")
	public String entUpdated(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mav=new ModelAndView();
		String resid	=req.getParameter("resid");
		int resnum  =Integer.parseInt(req.getParameter("resnum"));
		String resdate=req.getParameter("resdate");
		int cnt=dao.entUpdated(resid, resnum, resdate);
		String root=Utility.getRoot();
		String msg="";
		if(cnt==0) {
			msg+="<script>";
			msg+="	alert('실패했습니다');";
			msg+="</script>";
			msg+="<meta http-equiv='refresh' content='0;url="+root+"/Treserve/listent.do'>";
		}else {
			msg+="<script>";
			msg+="	alert('예약실패 처리되었습니다');";
			msg+="</script>";
			msg+="<meta http-equiv='refresh' content='0;url="+root+"/Treserve/listent.do'>";
		}
		req.setAttribute("msg", msg);
		return "Treserve/msgView";
	}//entUpdated() end
	
	@RequestMapping(value = "Treserve/listper.do")
	public ModelAndView listper(TreserveDTO dto, HttpSession session, HttpServletRequest req) {
		session=req.getSession();
		String resid	=(String)session.getAttribute("s_id");
		ModelAndView mav=new ModelAndView();
		
		String root=Utility.getRoot();
		String msg="";
		System.out.println(resid);
		List<TreserveDTO> list=dao.listper(resid);
		//System.out.println(list.size());
		if(list==null) {
			msg+="<script>";
			msg+="	alert('예약 내역이 없습니다');";
			msg+="</script>";
			msg+="<meta http-equiv='refresh' content='0;url="+root+"/home.do'>";
			req.setAttribute("msg", msg);
			mav.setViewName("Treserve/msgView");
		}else {
		
		mav.setViewName("Treserve/listper");
		mav.addObject("root", Utility.getRoot());
		//mav.addObject("list", dao.listper(resid));	
//------------------------------------------------------------------------------------
		//달력 작성하기
		Calendar cal=Calendar.getInstance();
		int cYear=0;
		int cMonth=0;

		if(req.getParameter("cYear")==null || req.getParameter("cMonth")==null) {
			//전달된 년+월값이 없으면 현재 년+월도 달력구성하기
			cYear  =cal.get(Calendar.YEAR);
			cMonth =cal.get(Calendar.MONTH);
		}else {
			//전단된 년+월값이 있으면...그 값으로 달력 구성하기
			cYear=Integer.parseInt(req.getParameter("cYear"));
			cMonth=Integer.parseInt(req.getParameter("cMonth"));			
		}//if end
		
		GregorianCalendar dal=new GregorianCalendar(cYear, cMonth, 1); //현재 달력 구성하기
		System.out.println("------" + dal.get(Calendar.YEAR));
		System.out.println("------" + dal.get(Calendar.MONTH));
		
		int startday=dal.get(Calendar.DAY_OF_WEEK);
		System.out.println(startday);
		
		//1달 더하기(NEXT)
		dal.add(Calendar.MONTH, 1); //2021-02-01
		int nextYear =dal.get(Calendar.YEAR);
		int nextMonth=dal.get(Calendar.MONTH);		
		
		//전월 PREV
		//해당월의 마지막날 구하기
		dal.add(Calendar.DATE, -1); //2021-02-01에서 하루 빼기(그러면...전월의 마지막 날을 알수 있음) 2021-1-31
		int lastday=dal.get(Calendar.DATE);
		System.out.println("------" + dal.get(Calendar.DATE));
		
		dal.add(Calendar.MONTH, -1);
		int prevYear=dal.get(Calendar.YEAR);
		int prevMonth=dal.get(Calendar.MONTH);
//------------------------------------------------------------------------------------		
		int cnt=0;
		
		String today=Utility.getDate();
		
		String str="";
		String yearmonth=cYear+"년"+(cMonth+1)+"월";
		
		for(int i=1; i<startday; i++) {
			str+="<td></td>";
			cnt++;
		}//for end 빈곳
		
		for(int i=1; i<=lastday; i++) {
			str+="<td><span id=num"+i+ " onclick='checkdate(" + cYear + ","+(cMonth+1) + ","+i + ")'>"+i;
			
			String dates=cYear+"-";
			if((cMonth+1)<10) {
				dates+="0"+(cMonth+1)+"-";
			}else {
				dates+=(cMonth+1)+"-";
			}
			if(i<10) {
				dates+="0"+i;
			}else {
				dates+=i;
			}
			
			//list=dao.listper(resid);
			
			for(int j=0; j<list.size(); j++) {
				TreserveDTO vo=list.get(j);
				String resdate=vo.getResdate().substring(0,10);
				
				if(dates.equals(resdate)) {
					str+=vo.getRessub()+ "</span><img src='../images/common/mobile_close.jpg'/>";					
					break;
				}//if end
			}//for end 달력날짜
			
			str+="</span></td>";
			cnt++;
			
			if(cnt%7==0) { 
				str+="</tr><tr>"; 
			}
		}//for end

		mav.addObject("yearmonth",yearmonth);
		mav.addObject("str", str);
		mav.addObject("cYear", cYear);
		mav.addObject("cMonth", cMonth);
		mav.addObject("nextYear", nextYear);
		mav.addObject("nextMonth", nextMonth);
		mav.addObject("prevYear", prevYear);
		mav.addObject("prevMonth", prevMonth);
		}
		return mav;
		
	}//listper() end
	
	@RequestMapping(value = "Treserve/listperread.do")
	public ModelAndView listperread(String dates, HttpSession session, HttpServletRequest req) {
		session=req.getSession();
		String resid	=(String)session.getAttribute("s_id");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Treserve/listperread");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("list", dao.listperread(dates,resid));
		return mav;
	}//listperread() end
	
	@RequestMapping(value = "Treserve/listperup.do")
	public String listperup(String resdate, int resnum, HttpSession session, HttpServletRequest req,TreserveDTO dto) {
		session=req.getSession();
		String resid	=(String)session.getAttribute("s_id");
		System.out.println(resdate);
		System.out.println(resid);
		System.out.println(resnum);
		int cnt=dao.listperup(resdate,resid,resnum);
		String root=Utility.getRoot();
		String msg="";
		if(cnt==0) {
			msg+="<script>";
			msg+="	alert('실패했습니다');";
			msg+="</script>";
			msg+="<meta http-equiv='refresh' content='0;url="+root+"/Treserve/listper.do'>";
		}else {
			msg+="<script>";
			msg+="	alert('예약취소되었습니다');";
			msg+="</script>";
			msg+="<meta http-equiv='refresh' content='0;url="+root+"/Treserve/listper.do'>";
		}
		req.setAttribute("msg", msg);
		return "Treserve/msgView";
	}//listperup() end
	
}//class end

