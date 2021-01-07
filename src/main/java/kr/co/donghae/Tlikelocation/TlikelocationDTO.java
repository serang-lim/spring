package kr.co.donghae.Tlikelocation;

import java.sql.Timestamp;

public class TlikelocationDTO {

	   private int Lno;
	   private String Lsubject;
	   private String Lid;
	   private String Licon;
	   private Timestamp Ldate;
	   private String Lregion; 

	   
	
	 public TlikelocationDTO() {}



	/**
	 * @return the lno
	 */
	public int getLno() {
		return Lno;
	}



	/**
	 * @param lno the lno to set
	 */
	public void setLno(int lno) {
		Lno = lno;
	}



	/**
	 * @return the lsubject
	 */
	public String getLsubject() {
		return Lsubject;
	}



	/**
	 * @param lsubject the lsubject to set
	 */
	public void setLsubject(String lsubject) {
		Lsubject = lsubject;
	}



	/**
	 * @return the lid
	 */
	public String getLid() {
		return Lid;
	}



	/**
	 * @param lid the lid to set
	 */
	public void setLid(String lid) {
		Lid = lid;
	}



	/**
	 * @return the licon
	 */
	public String getLicon() {
		return Licon;
	}



	/**
	 * @param licon the licon to set
	 */
	public void setLicon(String licon) {
		Licon = licon;
	}



	/**
	 * @return the ldate
	 */
	public Timestamp getLdate() {
		return Ldate;
	}



	/**
	 * @param ldate the ldate to set
	 */
	public void setLdate(Timestamp ldate) {
		Ldate = ldate;
	}



	/**
	 * @return the lregion
	 */
	public String getLregion() {
		return Lregion;
	}



	/**
	 * @param lregion the lregion to set
	 */
	public void setLregion(String lregion) {
		Lregion = lregion;
	}



	@Override
	public String toString() {
		return "TlikelocationDTO [Lno=" + Lno + ", Lsubject=" + Lsubject + ", Lid=" + Lid + ", Licon=" + Licon
				+ ", Ldate=" + Ldate + ", Lregion=" + Lregion + "]";
	}
	 
	 
}//class end