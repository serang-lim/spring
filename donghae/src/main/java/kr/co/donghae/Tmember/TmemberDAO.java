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
	


	public int insert(TmemberDTO dto) {
		int cnt=0;
		try {  
		   con=dbopen.getConnection();
	       sql=new StringBuilder();
	       sql.append(" INSERT INTO Tmember(Mid, Mpasswd, mMame,Mtel,Memail,Mzipcode,Maddress1,Maddress2,Mnum ,Mlevel,Mdate) ");
	       sql.append(" VALUES (?,?,?,?,?,?,?,?,? ");
	       sql.append(" ,'C1',sysdate ");
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
	       
	       cnt= pstmt.executeUpdate();
	      
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBClose.close(con,pstmt);
		}
		return cnt;
	}
	
}//class end
