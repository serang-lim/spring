package kr.co.donghae.Tpromotion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class TpromotionDAO {
	
	@Autowired
	DBOpen dbopen;
	@Autowired
	DBClose close;
	
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	StringBuilder sql=null;
	ArrayList<TpromotionDTO> list=null;
	
	public TpromotionDAO() {
		System.out.println("---TpromotionDAO()객체 생성됨...");
	}//생성자함수 end
	
	
	public ArrayList<TpromotionDTO> list(TpromotionDTO dto){
		ArrayList<TpromotionDTO> list=null;
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
	        sql.append(" SELECT Ticon, Tbno,Timage_name,Tregion, Tsubject, Twdate, twriter, tresult");
	        sql.append(" FROM tpromotion ORDER BY tbno desc");

			pstmt = con.prepareStatement(sql.toString());
	      rs=pstmt.executeQuery();
	      if(rs.next()) {
	        list=new ArrayList<TpromotionDTO>(); 
	        do {
	        	dto=new TpromotionDTO();
		        dto.setTicon(rs.getString("Ticon"));
				dto.setTbno(rs.getInt("Tbno"));
				dto.setTimage_name(rs.getString("Timage_name"));
				dto.setTregion(rs.getString("Tregion"));
				dto.setTsubject(rs.getString("Tsubject"));
				dto.setTwdate(rs.getString("Twdate"));
				dto.setTwriter(rs.getString("Twriter"));
				dto.setTresult(rs.getString("tresult"));
				
				list.add(dto);
	        }while(rs.next());
	      }else {        
	        list=null;        
	      }//if end   
		}catch (Exception e) {
			System.out.println("목록실패:"+e);
		}finally {
			DBClose.close(con, pstmt, rs);
		}//end
		return list;
	}//list() end


public ArrayList<TpromotionDTO> list2(String col1, String col2) {
	ArrayList<TpromotionDTO> list=null;
	try {
		con=dbopen.getConnection();
		sql=new StringBuilder();
		sql.append(" Select Ticon, Tbno,Timage_name,Tregion, Tsubject, Twdate, Twriter, tresult");
		sql.append(" From Tpromotion");
		//검색어가 있다면
		if(col1.equals("x")){
			sql.append(" Where ticon=?");
			sql.append(" Order by tbno desc");

			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, col2);
			
		}else if(col2.equals("x")){
			sql.append(" Where tregion=?");
			sql.append(" Order by tbno desc");

			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, col1);
		
		}else if(col1!="x"&&col2!="x") {
			sql.append(" Where ticon=? and tregion=?");
			sql.append(" Order by tbno desc");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, col2);
			pstmt.setString(2, col1);

		}else {
			sql.append(" Order by tbno desc");
			pstmt=con.prepareStatement(sql.toString());
			
		}
		
      rs=pstmt.executeQuery();
      if(rs.next()) {
        list=new ArrayList<TpromotionDTO>(); //전체 행 저장
        do {
        	TpromotionDTO dto=new TpromotionDTO(); //한줄 저장
          dto.setTbno(rs.getInt("tbno"));
          dto.setTicon(rs.getString("ticon"));
          dto.setTsubject(rs.getString("tsubject"));
          dto.setTregion(rs.getString("tregion"));
          dto.setTwdate(rs.getString("twdate"));
          dto.setTimage_name(rs.getString("timage_name")); 
          dto.setTwriter(rs.getString("twriter"));
          dto.setTresult(rs.getString("tresult"));
          list.add(dto);
        }while(rs.next());
      }else {        
        list=null;        
      }//if end   
		
	}catch (Exception e) {
		
		System.out.println("목록실패:"+e);
	}finally {
		DBClose.close(con, pstmt, rs);
	}//end
	
	return list;
}//list2() end

	

	public String tnumselect(String mid){
		String tnum=null;
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" SELECT mnum");
	        sql.append(" FROM Tmember ");
	        sql.append(" WHERE mid=? ");
	        
	        pstmt=con.prepareStatement(sql.toString());
	        pstmt.setString(1, mid);
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				tnum=rs.getString("mnum");
				
			}else{
				tnum=null;
			}//if end
			
		}catch(Exception e) {
			System.out.println("프로모션 상세보기실패:"+e);
		}finally {
			DBClose.close(con,pstmt,rs);
		}//end
		
		return tnum;
		
	}//read() end	
			
	public TpromotionDTO pwcheck(TpromotionDTO dto){
		
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" SELECT tbno, tpasswd, timage_name, tnum, timage_size,timage_name2, timage_size2, tregion, tsubject, twdate, ttime, twriter, ticon, tresult ");
	        sql.append(" FROM Tpromotion ");
	        sql.append(" WHERE tbno=? and tpasswd=? ");
	        
	        pstmt=con.prepareStatement(sql.toString());
	        pstmt.setInt(1, dto.getTbno());
	        pstmt.setInt(2, dto.getTpasswd());
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				dto.setTbno(rs.getInt("tbno"));
				dto.setTpasswd(rs.getInt("tpasswd"));
				dto.setTimage_name(rs.getString("timage_name"));
				dto.setTimage_size(rs.getInt("timage_size"));
				dto.setTimage_name2(rs.getString("timage_name"));
				dto.setTimage_size2(rs.getInt("timage_size"));
				dto.setTregion(rs.getString("tregion"));
				dto.setTsubject(rs.getString("tsubject"));
				dto.setTwdate(rs.getString("twdate"));
				dto.setTtime(rs.getString("ttime"));
				dto.setTwriter(rs.getString("twriter"));
				dto.setTicon(rs.getString("ticon"));
				dto.setTresult(rs.getString("tresult"));
				dto.setTnum(rs.getString("tnum"));
				
			}else{
				dto=null;
			}//if end
			
		}catch(Exception e) {
			System.out.println("상세보기 수정 실패:"+e);
		}finally {
			DBClose.close(con,pstmt,rs);
		}//end
		
		return dto;
		
	}//read() end	
			
	public int insert(TpromotionDTO dto) {
	    int cnt=0;
	    try {
	      con =dbopen.getConnection();
	      sql =new StringBuilder();
	      sql.append(" INSERT INTO Tpromotion(Tbno,Ticon,Timage_name,Timage_size, Tregion, Tsubject, Twdate, Ttime, Tnum, Twriter,Timage_name2,Timage_size2, tpasswd)");
	      sql.append(" VALUES( tbno_seq.nextval, ?, ?,?,?,?,sysdate,ADD_MONTHS(SYSDATE, 1),?,?,?,?,?)");
	      pstmt = con.prepareStatement(sql.toString());
	      pstmt.setString(1, dto.getTicon());
	      pstmt.setString(2, dto.getTimage_name());
	      pstmt.setInt(3, dto.getTimage_size());
	      pstmt.setString(4, dto.getTregion());
	      pstmt.setString(5, dto.getTsubject());
	      pstmt.setString(6, dto.getTnum());
	      pstmt.setString(7, dto.getTwriter());
	      pstmt.setString(8, dto.getTimage_name2());
	      pstmt.setInt(9, dto.getTimage_size2());
	      pstmt.setInt(10, dto.getTpasswd());
	      cnt = pstmt.executeUpdate();
	   } catch (Exception e) {
	      System.out.println("프로모션 등록실패"+e);
	   }finally {
	      DBClose.close(con,pstmt);
	   }
	    return cnt;

	}//create() end
	

public TpromotionDTO read(int tbno){
	TpromotionDTO dto=null;
	try {
		con=dbopen.getConnection();
		
		sql=new StringBuilder();
		sql.append(" SELECT Tbno,Ticon,Timage_name,Timage_size,Timage_name2,Timage_size2, Tregion, Tsubject, Twdate, Ttime, Tnum, Twriter, tresult");
        sql.append(" FROM Tpromotion ");
        sql.append(" WHERE tbno=? ");
        
        pstmt=con.prepareStatement(sql.toString());
        pstmt.setInt(1, tbno);
		
		rs=pstmt.executeQuery();
		if(rs.next()){
			dto=new TpromotionDTO();
			dto.setTbno(tbno);
			dto.setTicon(rs.getString("Ticon"));
			dto.setTimage_name(rs.getString("Timage_name"));
			dto.setTimage_name2(rs.getString("Timage_name2"));
			dto.setTregion(rs.getString("Tregion"));
			dto.setTsubject(rs.getString("Tsubject"));
			dto.setTwdate(rs.getString("Twdate"));
			dto.setTtime(rs.getString("Ttime"));
			dto.setTnum(rs.getString("Tnum"));
			dto.setTwriter(rs.getString("Twriter"));
			dto.setTresult(rs.getString("tresult"));
			
		}else{
			dto=null;
		}//if end
		
	}catch(Exception e) {
		System.out.println("프로모션 상세보기실패:"+e);
	}finally {
		DBClose.close(con,pstmt,rs);
	}//end
	
	return dto;
	
}//read() end	
	
	


public int update(TpromotionDTO dto) {
	int cnt=0;
	try {
		con=dbopen.getConnection();
		sql=new StringBuilder();
		sql.append(" Update Tpromotion");
		sql.append(" set Ticon=?,Timage_name=?,Timage_size=?,Timage_name2=?,Timage_size2=?, Tregion=?, Tsubject=?,  Twriter=?, tresult=?, tpasswd=?");
		sql.append(" Where tbno=?");
		pstmt=con.prepareStatement(sql.toString());
		pstmt.setString(1, dto.getTicon());
		pstmt.setString(2, dto.getTimage_name());
		pstmt.setInt(3, dto.getTimage_size());
		pstmt.setString(4, dto.getTimage_name2());
		pstmt.setInt(5, dto.getTimage_size2());
		pstmt.setString(6, dto.getTregion());
		pstmt.setString(7, dto.getTsubject());
		pstmt.setString(8, dto.getTwriter());
		pstmt.setString(9, dto.getTresult());
		pstmt.setInt(10, dto.getTpasswd());
		pstmt.setInt(11, dto.getTbno());
		
		
		cnt=pstmt.executeUpdate();
	} catch (Exception e) {
		System.out.println("수정실패"+e);
	}finally {
		DBClose.close(con,pstmt);
	}//end
	return cnt;
}//update() end


public int delete(int tbno) {
	int cnt=0;
	try {
		con=dbopen.getConnection();
		sql=new StringBuilder();
		sql.append(" Delete From Tpromotion");
		sql.append(" Where tbno=?");
		pstmt=con.prepareStatement(sql.toString());
		pstmt.setInt(1, tbno);
		cnt=pstmt.executeUpdate();
	} catch (Exception e) {
		System.out.println("삭제실패"+e);
	}finally {
		DBClose.close(con,pstmt);
	}//end
	return cnt;
}//delete() end

	

public int updateRes(TpromotionDTO dto) {
	int cnt=0;
	
	try {
		con=dbopen.getConnection();
		sql=new StringBuilder();
		sql.append(" Update Tpromotion");
		sql.append(" set tresult=?");
		sql.append(" Where tbno=?");
		pstmt=con.prepareStatement(sql.toString());
		pstmt.setString(1, dto.getTresult());
		pstmt.setInt(2, dto.getTbno());
		
		
		cnt=pstmt.executeUpdate();
	} catch (Exception e) {
		System.out.println("Y/N수정실패"+e);
	}finally {
		DBClose.close(con,pstmt);
	}//end
	return cnt;
}//update() end


public ArrayList<TpromotionDTO> mylist(String twriter){
	ArrayList<TpromotionDTO> list=null;
	TpromotionDTO dto= new TpromotionDTO();
	try {
		con=dbopen.getConnection();
		
		sql=new StringBuilder();
		sql.append(" SELECT tbno,ticon,timage_name,timage_size,timage_name2,timage_size2, tregion, tsubject, twdate, ttime, tnum, twriter, tresult, tpasswd");
        sql.append(" FROM Tpromotion ");
        sql.append(" WHERE twriter=? Order by tbno desc");
        
        pstmt=con.prepareStatement(sql.toString());
        pstmt.setString(1, twriter);
		
		rs=pstmt.executeQuery();

	    
		if(rs.next()){
		    list=new ArrayList<TpromotionDTO>(); 
	        do {
				dto=new TpromotionDTO();
				dto.setTbno(rs.getInt("tbno"));
				dto.setTicon(rs.getString("Ticon"));
				dto.setTimage_name(rs.getString("Timage_name"));
				dto.setTimage_name2(rs.getString("Timage_name2"));
				dto.setTregion(rs.getString("Tregion"));
				dto.setTsubject(rs.getString("Tsubject"));
				dto.setTwdate(rs.getString("Twdate"));
				dto.setTtime(rs.getString("Ttime"));
				dto.setTnum(rs.getString("Tnum"));
				dto.setTwriter(twriter);
				dto.setTresult(rs.getString("tresult"));
				dto.setTpasswd(rs.getInt("tpasswd"));
				list.add(dto);
	        }while(rs.next());
			
		}else{
			dto=null;
		}//if end
		
	}catch(Exception e) {
		System.out.println("내 프로모션 상세보기실패:"+e);
	}finally {
		DBClose.close(con,pstmt,rs);
	}//end
	
	return list;
	
}//read() end	
	
	
	
}//class end
