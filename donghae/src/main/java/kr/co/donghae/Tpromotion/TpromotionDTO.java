package kr.co.donghae.Tpromotion;

import org.springframework.web.multipart.MultipartFile;

public class TpromotionDTO {

	public TpromotionDTO() {}//기본생성자함수
	
	private int Tbno;
	private String Timage_name;
	private int Timage_size;
	private String Timage_name2;
	private int Timage_size2;
	private String Tregion;
	private String Tsubject;
	private String Twdate;
	private String Ttime;
	private String Tnum;
	private String Twriter;
	private String Ticon;

	
	private MultipartFile Timage_nameMF;
	private MultipartFile Timage_nameMF2;
	
	
	public int getTbno() {
		return Tbno;
	}
	
	public void setTbno(int tbno) {
		Tbno = tbno;
	}
	public String getTimage_name() {
		return Timage_name;
	}
	public void setTimage_name(String timage_name) {
		Timage_name = timage_name;
	}
	public int getTimage_size() {
		return Timage_size;
	}
	public void setTimage_size(int timage_size) {
		Timage_size = timage_size;
	}
	public String getTregion() {
		return Tregion;
	}
	public void setTregion(String tregion) {
		Tregion = tregion;
	}
	public String getTsubject() {
		return Tsubject;
	}
	public void setTsubject(String tsubject) {
		Tsubject = tsubject;
	}
	public String getTwdate() {
		return Twdate;
	}
	public void setTwdate(String twdate) {
		Twdate = twdate;
	}
	public String getTtime() {
		return Ttime;
	}
	public void setTtime(String ttime) {
		Ttime = ttime;
	}
	public String getTnum() {
		return Tnum;
	}
	public void setTnum(String tnum) {
		Tnum = tnum;
	}
	
	public String getTicon() {
		return Ticon;
	}
	public void setTicon(String ticon) {
		Ticon = ticon;
	}
	public MultipartFile getTimage_nameMF() {
		return Timage_nameMF;
	}
	public void setTimage_nameMF(MultipartFile timage_nameMF) {
		Timage_nameMF = timage_nameMF;
	}	
	public String getTwriter() {
		return Twriter;
	}
	public void setTwriter(String twriter) {
		Twriter = twriter;
	}
	
	
	
	public String getTimage_name2() {
		return Timage_name2;
	}
	public void setTimage_name2(String timage_name2) {
		Timage_name2 = timage_name2;
	}
	public int getTimage_size2() {
		return Timage_size2;
	}
	public void setTimage_size2(int timage_size2) {
		Timage_size2 = timage_size2;
	}
	public MultipartFile getTimage_nameMF2() {
		return Timage_nameMF2;
	}
	public void setTimage_nameMF2(MultipartFile timage_nameMF2) {
		Timage_nameMF2 = timage_nameMF2;
	}
	@Override
	public String toString() {
		return "TpromotionDTO [Tbno=" + Tbno + ", Timage_name=" + Timage_name + ", Timage_size=" + Timage_size
				+ ", Timage_name2=" + Timage_name2 + ", Timage_size2=" + Timage_size2 + ", Tregion=" + Tregion
				+ ", Tsubject=" + Tsubject + ", Twdate=" + Twdate + ", Ttime=" + Ttime + ", Tnum=" + Tnum + ", Twriter="
				+ Twriter + ", Ticon=" + Ticon + ", Timage_nameMF=" + Timage_nameMF + ", Timage_nameMF2="
				+ Timage_nameMF2 + "]";
	}
	
	
	
	
	
}//class end
