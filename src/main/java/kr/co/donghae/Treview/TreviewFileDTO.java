package kr.co.donghae.Treview;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class TreviewFileDTO {
	private Integer fileNum;
    private String fileName; //파일 이름
    private long filesize;
    private int Rnum;
     
    private MultipartFile photonameMF;//최초 사진
    private MultipartFile photonameMF1;//수정 사진
    
	private List<MultipartFile> files;
	  
    /**
     * 파일 크기를 정형화하기.
     */
    public String size2String() {
        Integer unit = 1024;
        if (filesize < unit) {
            return String.format("(%d B)", filesize);
        }
        int exp = (int) (Math.log(filesize) / Math.log(unit));
 
        return String.format("(%.0f %s)", filesize / Math.pow(unit, exp), "KMGTPE".charAt(exp - 1));
    }
        /*getters & setters*/

    
    
    
	public Integer getFileNum() {
		return fileNum;
	}

	public MultipartFile getPhotonameMF1() {
		return photonameMF1;
	}




	public void setPhotonameMF1(MultipartFile photonameMF1) {
		this.photonameMF1 = photonameMF1;
	}




	public void setFileNum(Integer fileNum) {
		this.fileNum = fileNum;
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
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

	public int getRnum() {
		return Rnum;
	}

	public void setRnum(int rnum) {
		Rnum = rnum;
	}




	@Override
	public String toString() {
		return "TreviewFileDTO [fileNum=" + fileNum + ", fileName=" + fileName + ", filesize=" + filesize + ", Rnum="
				+ Rnum + ", photonameMF=" + photonameMF + ", photonameMF1=" + photonameMF1 + ", files=" + files + "]";
	}

	
	
	
    
}//class end