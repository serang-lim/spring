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
			
			sql.append(" Select Ticon, Tbno,Timage_name,Tregion, Tsubject, Twdate");
			sql.append(" From Tpromotion");
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
	
	public int insert(TpromotionDTO dto) {
	    int cnt=0;
	    try {
	      con =dbopen.getConnection();
	      sql =new StringBuilder();
	      sql.append(" INSERT INTO Tpromotion(Tbno,Ticon,Timage_name,Timage_size, Tregion, Tsubject, Twdate, Ttime, Tnum, Twriter)");
	      sql.append(" VALUES( tbno_seq.nextval, ?, ?,?,?,?,sysdate,'2021-01-29',?,?)");
	      pstmt = con.prepareStatement(sql.toString());
	      pstmt.setString(1, dto.getTicon());
	      pstmt.setString(2, dto.getTimage_name());
	      pstmt.setInt(3, dto.getTimage_size());
	      pstmt.setString(4, dto.getTregion());
	      pstmt.setString(5, dto.getTsubject());
	      pstmt.setInt(6, dto.getTnum());
	      pstmt.setString(7, dto.getTwriter());
	      cnt = pstmt.executeUpdate();
	   } catch (Exception e) {
	      System.out.println("미디어그룹등록실패"+e);
	   }finally {
	      DBClose.close(con,pstmt);
	   }
	    return cnt;

	}//create() end
	
	
	
	
	
	
	
}//class end
