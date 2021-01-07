package kr.co.donghae.Tnotice;

import java.sql.Timestamp;

public class TnoticeDTO {
	
	   private int nonum;
	   private String nosubject;
	   private String nocontent;
	   private String nowriter;
	   private int noreadcnt; 
	   private Timestamp nodate;
	   
	
	 public TnoticeDTO() {}


	public int getNonum() {
		return nonum;
	}


	public void setNonum(int nonum) {
		this.nonum = nonum;
	}


	public String getNosubject() {
		return nosubject;
	}


	public void setNosubject(String nosubject) {
		this.nosubject = nosubject;
	}


	public String getNocontent() {
		return nocontent;
	}


	public void setNocontent(String nocontent) {
		this.nocontent = nocontent;
	}


	public String getNowriter() {
		return nowriter;
	}


	public void setNowriter(String nowriter) {
		this.nowriter = nowriter;
	}


	public int getNoreadcnt() {
		return noreadcnt;
	}


	public void setNoreadcnt(int noreadcnt) {
		this.noreadcnt = noreadcnt;
	}




	/**
	 * @return the nodate
	 */
	public Timestamp getNodate() {
		return nodate;
	}


	/**
	 * @param nodate the nodate to set
	 */
	public void setNodate(Timestamp nodate) {
		this.nodate = nodate;
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}


	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}


	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	 
	
	 

}//class end
