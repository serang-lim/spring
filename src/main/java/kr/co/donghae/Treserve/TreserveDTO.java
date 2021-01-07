package kr.co.donghae.Treserve;

public class TreserveDTO {

    public String resid;
    public String resdate;
    public String resbno;
    public String ressub;
    public String result;
    public int resnum;
    public String resregion;
    public String resicon;
    public String resimage_name;
    public int resimage_size;
    
    public TreserveDTO() {}

	public String getResid() {
		return resid;
	}

	public void setResid(String resid) {
		this.resid = resid;
	}

	public String getResdate() {
		return resdate;
	}

	public void setResdate(String resdate) {
		this.resdate = resdate;
	}

	public String getResbno() {
		return resbno;
	}

	public void setResbno(String resbno) {
		this.resbno = resbno;
	}

	public String getRessub() {
		return ressub;
	}

	public void setRessub(String ressub) {
		this.ressub = ressub;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getResnum() {
		return resnum;
	}

	public void setResnum(int resnum) {
		this.resnum = resnum;
	}

	public String getResregion() {
		return resregion;
	}

	public void setResregion(String resregion) {
		this.resregion = resregion;
	}

	public String getResicon() {
		return resicon;
	}

	public void setResicon(String resicon) {
		this.resicon = resicon;
	}

	public String getResimage_name() {
		return resimage_name;
	}

	public void setResimage_name(String resimage_name) {
		this.resimage_name = resimage_name;
	}

	public int getResimage_size() {
		return resimage_size;
	}

	public void setResimage_size(int resimage_size) {
		this.resimage_size = resimage_size;
	}

	@Override
	public String toString() {
		return "TreserveDTO [resid=" + resid + ", resdate=" + resdate + ", resbno=" + resbno + ", ressub=" + ressub
				+ ", result=" + result + ", resnum=" + resnum + ", resregion=" + resregion + ", resicon=" + resicon
				+ ", resimage_name=" + resimage_name + ", resimage_size=" + resimage_size + "]";
	}

	
    
    
}//class end
