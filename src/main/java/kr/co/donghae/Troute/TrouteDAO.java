package kr.co.donghae.Troute;

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
public class TrouteDAO {

	@Autowired
	DBOpen dbopen;
	
	@Autowired
	DBClose close;
	
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	StringBuilder sql=null;
	ArrayList<TrouteDTO> list=null;
	
	
	public TrouteDAO() {
		System.out.println("---TrouteDAO 객체 생성");
	}
	
	
	public ArrayList<TrouteDTO> list(TrouteDTO dto){
		ArrayList<TrouteDTO> list=null;
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
	        sql.append(" SELECT tno, tsubject,tid,tdate, treadcnt, tlike, tmapsub, tmapcont, ticon, taddrw, taddrg, tregion");
	        sql.append(" FROM troute");
	        
	        if(dto.getTregion()!=null) {
	        	sql.append(" Where tregion=? ");
	        	sql.append(" ORDER BY tno desc ");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1,  dto.getTregion());
			    rs=pstmt.executeQuery();

	        }else {
	        	sql.append(" ORDER BY tno desc ");
				pstmt = con.prepareStatement(sql.toString());
			    rs=pstmt.executeQuery();

	        }        	

        	

	      if(rs.next()) {
	        list=new ArrayList<TrouteDTO>(); 
	        do {
	        	dto=new TrouteDTO();
		        dto.setTno(rs.getInt("tno"));
		        dto.setTsubject(rs.getString("tsubject"));
		        dto.setTid(rs.getString("tid"));
		        dto.setTdate(rs.getTimestamp("tdate"));
		        dto.setTreadcnt(rs.getInt("treadcnt"));
		        dto.setTlike(rs.getInt("tlike"));
		        dto.setTmapsub(rs.getString("tmapsub"));
		        dto.setTmapcont(rs.getString("tmapcont"));
		        dto.setTicon(rs.getString("ticon"));
		        dto.setTaddrW(rs.getDouble("taddrw"));
		        dto.setTaddrG(rs.getDouble("taddrg"));
		        dto.setTregion(rs.getString("tregion"));
		        
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

	public TrouteDTO read(String tregion){
		TrouteDTO dto=null;
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" SELECT tno, tsubject,tid,tdate, treadcnt, tlike, tmapsub, tmapcont, ticon, taddrw, taddrg, tregion");
	        sql.append(" FROM Troute ");
	        sql.append(" WHERE tregion=? ");
	        
	        pstmt=con.prepareStatement(sql.toString());
	        pstmt.setString(1, tregion);
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				dto=new TrouteDTO();
		        dto.setTno(rs.getInt("tno"));
		        dto.setTsubject(rs.getString("tsubject"));
		        dto.setTid(rs.getString("tid"));
		        dto.setTdate(rs.getTimestamp("tdate"));
		        dto.setTreadcnt(rs.getInt("treadcnt"));
		        dto.setTlike(rs.getInt("tlike"));
		        dto.setTmapsub(rs.getString("tmapsub"));
		        dto.setTmapcont(rs.getString("tmapcont"));
		        dto.setTicon(rs.getString("ticon"));
		        dto.setTaddrW(rs.getDouble("taddrw"));
		        dto.setTaddrG(rs.getDouble("taddrg"));
		        dto.setTregion(rs.getString("tregion"));
			}else{
				dto=null;
			}//if end
			
		}catch(Exception e) {
			System.out.println("미디어그룹 상세보기실패:"+e);
		}finally {
			DBClose.close(con,pstmt,rs);
		}//end
		
		return dto;
		
	}//read() end	
		
	
	
}//class end
