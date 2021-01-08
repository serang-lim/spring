package kr.co.donghae.Tmember;

public class TmemberDTO {
	private String Mid;
	private String Mpasswd;
	private String Mname;
	private String Mtel;
	private String Memail;
	private String Mzipcode;
	private String Maddress1;
	private String Maddress2;
	private String Mlevel;
	private String Mnum;
	private String Mdate;
	
	public TmemberDTO() {
		System.out.println("---DTO객체생성---");
	}
	
	public String getMid() {
		return Mid;
	}
	public void setMid(String mid) {
		Mid = mid;
	}
	public String getMpasswd() {
		return Mpasswd;
	}
	public void setMpasswd(String mpasswd) {
		Mpasswd = mpasswd;
	}
	public String getMname() {
		return Mname;
	}
	public void setMname(String mname) {
		Mname = mname;
	}
	public String getMtel() {
		return Mtel;
	}
	public void setMtel(String mtel) {
		Mtel = mtel;
	}
	public String getMemail() {
		return Memail;
	}
	public void setMemail(String memail) {
		Memail = memail;
	}
	public String getMzipcode() {
		return Mzipcode;
	}
	public void setMzipcode(String mzipcode) {
		Mzipcode = mzipcode;
	}
	public String getMaddress1() {
		return Maddress1;
	}
	public void setMaddress1(String maddress1) {
		Maddress1 = maddress1;
	}
	public String getMaddress2() {
		return Maddress2;
	}
	public void setMaddress2(String maddress2) {
		Maddress2 = maddress2;
	}
	public String getMlevel() {
		return Mlevel;
	}
	public void setMlevel(String mlevel) {
		Mlevel = mlevel;
	}
	public String getMnum() {
		return Mnum;
	}
	public void setMnum(String mnum) {
		Mnum = mnum;
	}
	public String getMdate() {
		return Mdate;
	}
	public void setMdate(String mdate) {
		Mdate = mdate;
	}
	
	
}
