package kr.co.donghae.Treview;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class TreviewDTO {
	  private int Rnum;
	  private String Rsubject;
	  private String Rcontent;
	  private String Rpasswd;
	  private String Rphoto_name;
	  private long Rphoto_size;
	  private String Rregion;
	  private String Rid;
	  private String Rdate;
	  private int Rreadcnt;
	  
  //-------------------------------------
	  //createForm.jsp에서 복사
	  
	  //1)스프링 파일 객체 멤버 변수 선언
	  
	  //<input type='file' name='photonameMF'> 
	  private MultipartFile photonameMF;
	  private List<MultipartFile> files;
	  
  //-------------------------------------
	
	public TreviewDTO() {}

	public int getRnum() {
		return Rnum;
	}

	public void setRnum(int rnum) {
		Rnum = rnum;
	}

	public String getRsubject() {
		return Rsubject;
	}

	public void setRsubject(String rsubject) {
		Rsubject = rsubject;
	}

	public String getRcontent() {
		return Rcontent;
	}

	public void setRcontent(String rcontent) {
		Rcontent = rcontent;
	}

	public String getRpasswd() {
		return Rpasswd;
	}

	public void setRpasswd(String rpasswd) {
		Rpasswd = rpasswd;
	}

	public String getRphoto_name() {
		return Rphoto_name;
	}

	public void setRphoto_name(String rphoto_name) {
		Rphoto_name = rphoto_name;
	}

	public long getRphoto_size() {
		return Rphoto_size;
	}

	public void setRphoto_size(long rphoto_size) {
		Rphoto_size = rphoto_size;
	}

	public String getRregion() {
		return Rregion;
	}

	public void setRregion(String rregion) {
		Rregion = rregion;
	}

	public String getRid() {
		return Rid;
	}

	public void setRid(String rid) {
		Rid = rid;
	}

	public String getRdate() {
		return Rdate;
	}

	public void setRdate(String rdate) {
		Rdate = rdate;
	}

	public int getRreadcnt() {
		return Rreadcnt;
	}

	public void setRreadcnt(int rreadcnt) {
		Rreadcnt = rreadcnt;
	}

	public MultipartFile getPhotonameMF() {
		return photonameMF;
	}

	public void setPhotonameMF(MultipartFile photonameMF) {
		this.photonameMF = photonameMF;
	}
	
	

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "TreviewDTO [Rnum=" + Rnum + ", Rsubject=" + Rsubject + ", Rcontent=" + Rcontent + ", Rpasswd=" + Rpasswd
				+ ", Rphoto_name=" + Rphoto_name + ", Rphoto_size=" + Rphoto_size + ", Rregion=" + Rregion + ", Rid="
				+ Rid + ", Rdate=" + Rdate + ", Rreadcnt=" + Rreadcnt + ", photonameMF=" + photonameMF + ", files="
				+ files + "]";
	}
	
	

	
}//class end
