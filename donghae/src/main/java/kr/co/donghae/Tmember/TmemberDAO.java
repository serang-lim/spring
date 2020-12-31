package kr.co.donghae.Tmember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.utility.DBClose;
import net.utility.DBOpen;
@Component
public class TmemberDAO {

	@Autowired
	DBOpen dbopen;
	
	@Autowired
	DBClose dbclose;
	
	Connection con=null;
	PreparedStatement pstmt= null;
	ResultSet rs=null;
	StringBuilder sql =null;
	ArrayList<TmemberDTO> list =null;
	
public TmemberDAO() {
	System.out.println("---TmemberDAO()객체 생성됨---");
}//TmemberDAO end
//---------------------------------------------------------------------------------------------
public int login(TmemberDTO dto) {
	int res=0;
	try {
		con=dbopen.getConnection();
		sql =new StringBuilder();
		 sql.append(" SELECT COUNT(Mid) as cnt ");  
         sql.append(" FROM Tmember ");
         sql.append(" WHERE Mid=? AND Mpasswd=? AND Mlevel IN('A1','B1','C1','D1', 'F1')");
		pstmt=con.prepareStatement(sql.toString());
		pstmt.setString(1,dto.getMid());
		pstmt.setString(2,dto.getMpasswd());
		rs=pstmt.executeQuery();
		if(rs.next()==true) {
			res=rs.getInt("cnt");
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		dbclose.close(con,pstmt,rs);
	}
	System.out.println(res);
	return res;
}//login end
	
//-------------------------------------------------------------------------------------

	public int insert(TmemberDTO dto) {
		int cnt=0;
		try {  
		   con=dbopen.getConnection();
	       sql=new StringBuilder();
	       sql.append(" INSERT INTO Tmember(Mid, Mpasswd, Mname, Mtel,Memail,Mzipcode,Maddress1,Maddress2,Mnum ,Mlevel,Mdate) ");
	       sql.append(" VALUES (?,?,?,?,?,?,?,?,? ");
	       sql.append(" ,?,sysdate ");
	       sql.append(" )");
	       pstmt=con.prepareStatement(sql.toString());
	       pstmt.setString(1, dto.getMid());
	       pstmt.setString(2, dto.getMpasswd());
	       pstmt.setString(3, dto.getMname());
	       pstmt.setString(4, dto.getMtel());
	       pstmt.setString(5, dto.getMemail());
	       pstmt.setString(6, dto.getMzipcode());
	       pstmt.setString(7, dto.getMaddress1());
	       pstmt.setString(8, dto.getMaddress2());
	       pstmt.setInt(9, dto.getMnum());
	       pstmt.setString(10,dto.getMlevel());
	       
	       cnt= pstmt.executeUpdate();
	      
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBClose.close(con,pstmt);
		}
		return cnt;
	}// insert end
//------------------------------------------------------------------------------------	
	public int duplecateID(String Mid) {
		int cnt=0;
		try {
			con=dbopen.getConnection();
			sql= new StringBuilder();
			sql.append(" SELECT count(Mid) as cnt ");
	        sql.append(" FROM Tmember ");
	        sql.append(" WHERE Mid=?");  
	        
	        pstmt=con.prepareStatement(sql.toString());
	        pstmt.setString(1, Mid);
	        rs=pstmt.executeQuery();
	        
	        if(rs.next()) {
	        	cnt=rs.getInt("cnt");
	        }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBClose.close(con,pstmt,rs);
		}
		return cnt;
	}//id end
//-----------------------------------------------------------------------	
	  public int duplecateEMAIL(String Memail) {
		   int cnt=0;
		   try {
			      con=dbopen.getConnection();
			       sql=new StringBuilder();
			       sql.append(" SELECT count(Memail) as cnt ");
			       sql.append(" FROM Tmember ");
			       sql.append(" WHERE Memail=?");
			       pstmt=con.prepareStatement(sql.toString());
			       pstmt.setString(1, Memail);
			       rs = pstmt.executeQuery();
			       if(rs.next()){ 
			          cnt=rs.getInt("cnt"); 
			       }//if end
			   }catch(Exception e) {
				   	e.printStackTrace();
			   }finally {
			      DBClose.close(con, pstmt, rs);
			   }//end
			   return cnt;
			}//duplecateEMAIL
//-----------------------------------------------------------------------------------
	  public TmemberDTO read(String Mid) {
		   TmemberDTO dto=new TmemberDTO();
		   try {
		      con=dbopen.getConnection();
		      sql =new StringBuilder();
		       sql.append(" SELECT Mid,Mpasswd,Mname,Mtel,Memail,Mzipcode,Maddress1,Maddress2,Mlevel,Mnum,Mdate ");  
		         sql.append(" FROM Tmember ");
		         sql.append(" WHERE Mid=? ");
		      pstmt=con.prepareStatement(sql.toString());
		      pstmt.setString(1,Mid);
		      rs=pstmt.executeQuery();
		      if(rs.next()==true) {
		         dto.setMaddress1(rs.getString("maddress1"));
		         dto.setMaddress2(rs.getString("maddress2"));
		         dto.setMdate(rs.getString("mdate"));
		         dto.setMemail(rs.getString("memail"));
		         dto.setMid(rs.getString("mid"));
		         dto.setMlevel(rs.getString("mlevel"));
		         dto.setMname(rs.getString("mname"));
		         dto.setMnum(rs.getInt("mnum"));
		         dto.setMpasswd(rs.getString("mpasswd"));
		         dto.setMtel(rs.getString("mtel"));
		         dto.setMzipcode(rs.getString("mzipcode"));
		      }
		   }catch(Exception e) {
		      e.printStackTrace();
		   }finally {
		      dbclose.close(con,pstmt,rs);
		   }
		   return dto;
		}//read end
//-------------------------------------------------------------------------------------------
	  public TmemberDTO modify(TmemberDTO dto) {
		  try {
				 con=dbopen.getConnection();
			      sql=new StringBuilder();
			      sql.append(" SELECT Mid, Mpasswd, Mname, Mtel,Memail,Mzipcode,Maddress1,Maddress2,Mnum ,Mlevel");
			      sql.append(" FROM Tmember ");
			      sql.append(" WHERE Mid=? and Mpasswd=?");
			      
			      pstmt=con.prepareStatement(sql.toString());
			      pstmt.setString(1, dto.getMid());
			      pstmt.setString(2, dto.getMpasswd());
			      
			      rs=pstmt.executeQuery();
			      if(rs.next()){  
			        
			        dto.setMid(rs.getString("Mid"));
			        dto.setMname(rs.getString("Mname"));
			        dto.setMpasswd(rs.getString("Mpasswd"));
			        dto.setMemail(rs.getString("Memail"));
			        dto.setMtel(rs.getString("Mtel"));
			        dto.setMzipcode(rs.getString("Mzipcode"));
			        dto.setMaddress1(rs.getString("Maddress1"));
			        dto.setMaddress2(rs.getString("Maddress2"));
			        dto.setMnum(rs.getInt("Mnum"));
			        dto.setMlevel(rs.getString("Mlevel"));
			    
			      }else {
			        dto=null; 
			      }//if end
			      
			    }catch (Exception e) {
			      System.out.println("수정실패:"+e);
			    }finally {
			      DBClose.close(con, pstmt, rs);
			    }//end 
		  return dto;
	  }//modify
//-------------------------------------------------------------------------------------------------------
	  public int modifyPro(TmemberDTO dto) {
		  int cnt=0;
			 try {
			      con=dbopen.getConnection();
			       sql=new StringBuilder(); 
			       sql.append(" Update Tmember");
			       sql.append(" Set Mpasswd=?, Mname=?, Memail=?, Mtel=?, Mzipcode=?, Maddress1=?,Maddress2=?,Mnum=?,Mlevel=?");
			       sql.append(" WHERE Mid=?");
			       
			       pstmt=con.prepareStatement(sql.toString()); 
			       pstmt.setString(1,   dto.getMpasswd()); 
			       pstmt.setString(2,   dto.getMname()); 
			       pstmt.setString(3,   dto.getMemail()); 
			       pstmt.setString(4,   dto.getMtel()); 
			       pstmt.setString(5,   dto.getMzipcode()); 
			       pstmt.setString(6,   dto.getMaddress1()); 
			       pstmt.setString(7,   dto.getMaddress2()); 
			       pstmt.setInt(8,   dto.getMnum());
			       pstmt.setString(9, dto.getMlevel());
			       pstmt.setString(10,   dto.getMid()); 

			       cnt=pstmt.executeUpdate();
			        
			   } catch (Exception e) {
			      System.out.println("수정실패:"+e);
			      
			   }finally {
			      DBClose.close(con, pstmt);
			   }//end
			   
			   return cnt;
			   
			}//modifyPro() end
//------------------------------------------------------------------------------------------------------
	  
		 
	  
}//class end
