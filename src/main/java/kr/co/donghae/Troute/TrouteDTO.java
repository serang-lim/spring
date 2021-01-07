package kr.co.donghae.Troute;

import java.sql.Timestamp;

public class TrouteDTO {

	private int tno;
	private String tsubject;
	private String tid;
	private Timestamp tdate;
	private int treadcnt;
	private int tlike;
	private String tmapsub;
	private String tmapcont;
	private String ticon;
	private double taddrW;
	private double taddrG;
	private String tregion;
	
	
	public TrouteDTO() {
	}//생성자함수


	public int getTno() {
		return tno;
	}


	public void setTno(int tno) {
		this.tno = tno;
	}


	public String getTsubject() {
		return tsubject;
	}


	public void setTsubject(String tsubject) {
		this.tsubject = tsubject;
	}


	public String getTid() {
		return tid;
	}


	public void setTid(String tid) {
		this.tid = tid;
	}


	public Timestamp getTdate() {
		return tdate;
	}


	public void setTdate(Timestamp tdate) {
		this.tdate = tdate;
	}


	public int getTreadcnt() {
		return treadcnt;
	}


	public void setTreadcnt(int treadcnt) {
		this.treadcnt = treadcnt;
	}


	public int getTlike() {
		return tlike;
	}


	public void setTlike(int tlike) {
		this.tlike = tlike;
	}


	public String getTmapsub() {
		return tmapsub;
	}


	public void setTmapsub(String tmapsub) {
		this.tmapsub = tmapsub;
	}


	public String getTmapcont() {
		return tmapcont;
	}


	public void setTmapcont(String tmapcont) {
		this.tmapcont = tmapcont;
	}


	public String getTicon() {
		return ticon;
	}


	public void setTicon(String ticon) {
		this.ticon = ticon;
	}


	public double getTaddrW() {
		return taddrW;
	}


	public void setTaddrW(double taddrW) {
		this.taddrW = taddrW;
	}


	public double getTaddrG() {
		return taddrG;
	}


	public void setTaddrG(double taddrG) {
		this.taddrG = taddrG;
	}


	public String getTregion() {
		return tregion;
	}


	public void setTregion(String tregion) {
		this.tregion = tregion;
	}


	@Override
	public String toString() {
		return "TrouteDTO [tno=" + tno + ", tsubject=" + tsubject + ", tid=" + tid + ", tdate=" + tdate + ", treadcnt="
				+ treadcnt + ", tlike=" + tlike + ", tmapsub=" + tmapsub + ", tmapcont=" + tmapcont + ", ticon=" + ticon
				+ ", taddrW=" + taddrW + ", taddrG=" + taddrG + ", tregion=" + tregion + "]";
	}
	
	
	
}//class end
