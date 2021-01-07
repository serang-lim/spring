package kr.co.donghae.Tlikelocation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class TlikelocationDAO {
	
	@Autowired
	DBOpen dbopen;
	
	@Autowired
	DBClose close;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuilder sql = null;

	public TlikelocationDAO() {
	System.out.println("---TlikelocationDAO()객체 생성됨...");
	}
	
	public ArrayList<TlikelocationDTO> Tlikelocationlist() {
		ArrayList<TlikelocationDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" Select Lno, Licon, Lsubject, Lid, Ldate, Lregion");
			sql.append(" From likelocation");
			sql.append(" Order by Lno Desc");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<TlikelocationDTO>(); // 전체 행 저장
				do {
					TlikelocationDTO dto = new TlikelocationDTO(); // 한줄 저장
					dto.setLno(rs.getInt("Lno"));
					dto.setLicon(rs.getString("Licon"));
					dto.setLsubject(rs.getString("Lsubject"));
					dto.setLid(rs.getString("Lid"));
					dto.setLdate(rs.getTimestamp("Ldate"));
					dto.setLregion(rs.getString("Lregion"));
					list.add(dto);
				} while (rs.next());
			} else {
				list = null;
			} // if end
		} catch (Exception e) {
			System.out.println("목록실패:" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// list() end
	
	 public int count() {
		 int x=0;
		 try {
				con = dbopen.getConnection();
				sql = new StringBuilder();
				sql.append("SELECT count(*) FROM likelocation");
				pstmt = con.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					x=rs.getInt(1);
				}
			} catch (Exception e) {
				System.out.println("목록실패:" + e);
			} finally {
				DBClose.close(con, pstmt, rs);
			} // end
		
		 return x;
		 
	 }//Count end
	 
	 public int delete(int Lno) {
			int cnt=0;
			try {
				con=dbopen.getConnection();
				sql= new StringBuilder();
				sql.append(" DELETE FROM likelocation");
				sql.append(" WHERE Lno=?");
				pstmt =con.prepareStatement(sql.toString());
				pstmt.setInt(1, Lno);
				cnt=pstmt.executeUpdate();
			} catch (Exception e) {
			 System.out.println("삭제실패:"+e);
			}finally {
				DBClose.close(con,pstmt);
			}//end
			return cnt;
		}//delete end
	
}//class end
