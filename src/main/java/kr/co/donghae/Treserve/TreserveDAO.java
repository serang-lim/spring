package kr.co.donghae.Treserve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.donghae.Tpromotion.TpromotionDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class TreserveDAO {

	@Autowired
	DBOpen dbopen;
	
	@Autowired
	DBClose dbclose;
	
	Connection con=null;
	PreparedStatement pstmt=null;
	StringBuilder sql=null;
	ResultSet rs=null;
	ArrayList<TreserveDTO> list=null;
	
	public TreserveDAO() {
		System.out.println("---TreserveDAO() 객체 생성됨...");
	}//TreserveDAO() end
	
	
	
	public TpromotionDTO create(int Tbno) {
		TpromotionDTO prodto=null;
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			sql.append(" SELECT Tbno,Tsubject,Timage_name,Timage_size,Tregion,Ticon,Tnum  ");
			sql.append(" FROM Tpromotion ");
			sql.append(" WHERE Tbno=? ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, Tbno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				prodto=new TpromotionDTO();
				prodto.setTbno(rs.getInt("Tbno"));
				prodto.setTsubject(rs.getString("Tsubject"));
				prodto.setTimage_name(rs.getString("Timage_name"));
				prodto.setTimage_size(rs.getInt("Timage_size"));
				prodto.setTregion(rs.getString("Tregion"));
				prodto.setTicon(rs.getString("Ticon"));
				prodto.setTnum(rs.getString("Tnum"));
			}//if end
		}catch (Exception e) {
			System.out.println("예약내용 집어오기 실패:"+e);
		}finally {
			DBClose.close(con, pstmt, rs);
		}//end
		return prodto;
	}//create() end
	
	public int createproc(int Tbno, String id, String resdate) {
		int cnt=0;
			try {
				con=dbopen.getConnection();
				sql=new StringBuilder();
				sql.append(" SELECT Tbno,Tsubject,Timage_name,Timage_size,Tregion,Ticon,Tnum  ");
				sql.append(" FROM Tpromotion ");
				sql.append(" WHERE Tbno=? ");
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setInt(1, Tbno);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					String ressub=rs.getString("Tsubject");
					int resnum=rs.getInt("Tbno");
					String resimage_name=rs.getString("Timage_name");
					int resimage_size=rs.getInt("Timage_size");
					String resregion=rs.getString("Tregion");
					String resicon=rs.getString("Ticon");
					String resbno=rs.getString("Tnum");
			    	sql=new StringBuilder();
					sql.append(" INSERT INTO Treserve(resid,resdate,resbno,ressub,resnum,resregion,resicon,resimage_name,resimage_size ) ");
					sql.append(" VALUES(?,?,?,?,?,?,?,?,?) ");
					pstmt=con.prepareStatement(sql.toString());
					pstmt.setString(1, id);
					pstmt.setString(2, resdate);
					pstmt.setString(3, resbno);
					pstmt.setString(4, ressub);
					pstmt.setInt(5, resnum);
					pstmt.setString(6, resregion);
					pstmt.setString(7, resicon);
					pstmt.setString(8, resimage_name);
					pstmt.setInt(9, resimage_size);
					cnt=pstmt.executeUpdate();		
				}//if end
			}catch (Exception e) {
				System.out.println("예약 실패:"+e);
			}finally {
				DBClose.close(con, pstmt, rs);
			}//end
		
		return cnt;
	}//createproc() end
	
	public int duplecate(int Tbno, String id, String resdate) {
		int cnt=0;
		try {
			System.out.println(Tbno);
			System.out.println(id);
			System.out.println(resdate);
			con=dbopen.getConnection();
			sql=new StringBuilder();
		    sql.append(" Select count(resid) as cnt ");
		    sql.append(" FROM Treserve ");
		    sql.append(" Where resid=? AND resdate=? AND resnum=? ");
		    pstmt=con.prepareStatement(sql.toString());
		    pstmt.setString(1, id);
		    pstmt.setString(2, resdate);
		    pstmt.setInt(3, Tbno);
		    
		    rs=pstmt.executeQuery();
		    if(rs.next()){  
		    	//아이디 중복됨
		    	cnt=rs.getInt("cnt"); 
		    }
	    }catch (Exception e) {
	      System.out.println("예약중복확인실패:"+e);
	    }finally {
	      DBClose.close(con, pstmt, rs);
	    }//end 
	    
	    return cnt;

	}//duplicate()end
	
	
	ArrayList<TreserveDTO> listent(String resbno){ //목록
		ArrayList<TreserveDTO> list=null;
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			sql.append(" SELECT resid,resdate,resbno,ressub,result,resnum,resregion,resicon,resimage_name,resimage_size ");
			sql.append(" FROM Treserve ");
			sql.append(" WHERE resbno=? ");
			sql.append(" ORDER BY resdate DESC ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, resbno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<TreserveDTO>();
				do {
					TreserveDTO dto=new TreserveDTO();
					dto.setResid(rs.getString("resid"));
					dto.setResdate(rs.getString("resdate"));
					dto.setResbno(rs.getString("resbno"));
					dto.setRessub(rs.getString("ressub"));
					dto.setResult(rs.getString("result"));
					dto.setResnum(rs.getInt("resnum"));
					dto.setResregion(rs.getString("resregion"));
					dto.setResicon(rs.getString("resicon"));
					dto.setResimage_name(rs.getString("resimage_name"));
					dto.setResimage_size(rs.getInt("resimage_size"));
					list.add(dto);
				}while(rs.next());
			}else {
				list=null;
			}//if end
		}catch (Exception e) {
			System.out.println("예약 기업 목록실패:"+ e);
		}finally {
			DBClose.close(con, pstmt, rs);
		}//end
		return list;
	}//listent() end
	
	public int entUpdate(String resid, int resnum, String resdate) {
		int cnt=0;
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			sql.append(" UPDATE Treserve ");
			sql.append(" SET result='예약완료' ");
	        sql.append(" Where resid=? AND resdate=? AND resnum=? ");
	        pstmt=con.prepareStatement(sql.toString());
	        pstmt.setString(1, resid);
	        pstmt.setString(2, resdate);
	        pstmt.setInt(3, resnum);
	        cnt=pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("예약완료 수정 실패:"+e);
		}finally {
			DBClose.close(con,pstmt);
		}//end
		 return cnt;
	}//entUpdate() end
	
	public int entUpdated(String resid, int resnum, String resdate) {
		int cnt=0;
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			sql.append(" UPDATE Treserve ");
			sql.append(" SET result='예약실패' ");
	        sql.append(" Where resid=? AND resdate=? AND resnum=? ");
	        System.out.println(resid);
	        System.out.println(resnum);
	        System.out.println(resdate);
	        pstmt=con.prepareStatement(sql.toString());
	        pstmt.setString(1, resid);
	        pstmt.setString(2, resdate);
	        pstmt.setInt(3, resnum);
	        cnt=pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("예약실패 수정 실패:"+e);
		}finally {
			DBClose.close(con,pstmt);
		}//end
		 return cnt;
	}//entUpdated() end
	
	ArrayList<TreserveDTO> listper(String resid){ //목록
		ArrayList<TreserveDTO> list=null;
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			sql.append(" SELECT resid,resdate,resbno,ressub,result,resnum,resregion,resicon,resimage_name,resimage_size FROM Treserve ");
			sql.append(" WHERE resid=? ");
			sql.append(" ORDER BY resdate DESC ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, resid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<TreserveDTO>();
				do {
					TreserveDTO dto=new TreserveDTO();
					dto.setResid(rs.getString("resid"));
					dto.setResdate(rs.getString("resdate"));
					dto.setResbno(rs.getString("resbno"));
					dto.setRessub(rs.getString("ressub"));
					dto.setResult(rs.getString("result"));
					dto.setResnum(rs.getInt("resnum"));
					dto.setResregion(rs.getString("resregion"));
					dto.setResicon(rs.getString("resicon"));
					dto.setResimage_name(rs.getString("resimage_name"));
					dto.setResimage_size(rs.getInt("resimage_size"));
					list.add(dto);
				}while(rs.next());
			}else {
				list=null;
			}//if end
		}catch (Exception e) {
			System.out.println("예약목록실패:"+ e);
		}finally {
			DBClose.close(con, pstmt, rs);
		}//end
		return list;
	}//listper() end
	
	
	ArrayList<TreserveDTO> listperread(String dates, String resid){ //목록
		ArrayList<TreserveDTO> list=null;
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			sql.append(" SELECT resid,resdate,resbno,ressub,result,resnum,resregion,resicon,resimage_name,resimage_size FROM Treserve ");
			sql.append(" WHERE resid=? AND resdate=? ");
			sql.append(" ORDER BY resdate DESC ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, resid);
			pstmt.setString(2, dates);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<TreserveDTO>();
				do {
					TreserveDTO dto=new TreserveDTO();
					dto.setResid(rs.getString("resid"));
					dto.setResdate(rs.getString("resdate"));
					dto.setResbno(rs.getString("resbno"));
					dto.setRessub(rs.getString("ressub"));
					dto.setResult(rs.getString("result"));
					dto.setResnum(rs.getInt("resnum"));
					dto.setResregion(rs.getString("resregion"));
					dto.setResicon(rs.getString("resicon"));
					dto.setResimage_name(rs.getString("resimage_name"));
					dto.setResimage_size(rs.getInt("resimage_size"));
					list.add(dto);
				}while(rs.next());
			}else {
				list=null;
			}//if end
		}catch (Exception e) {
			System.out.println("예약목록실패:"+ e);
		}finally {
			DBClose.close(con, pstmt, rs);
		}//end
		return list;
	}//listperread() end
	
	public int listperup(String resdate,String resid,int resnum) {
		int cnt=0;
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			sql.append(" DELETE FROM Treserve ");
			sql.append(" WHERE resdate=? AND resid=? AND resnum=? ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, resdate);
			pstmt.setString(2, resid);
			pstmt.setInt(3, resnum);
			cnt=pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("개인 변경 실패:"+ e);
		}finally {
			DBClose.close(con, pstmt);
		}//end
		
		return cnt;
	}//listperup() end
	
	
}//class end

