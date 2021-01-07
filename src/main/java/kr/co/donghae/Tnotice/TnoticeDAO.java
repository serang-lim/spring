package kr.co.donghae.Tnotice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class TnoticeDAO {
   
   @Autowired
   DBOpen dbopen;
   
   @Autowired
   DBClose close;
   
   Connection con = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   StringBuilder sql = null;

      
   public TnoticeDAO() {
     System.out.println("---TnoticeDAO()객체 생성됨...");
   }//default constructor 기본생성자
      

   
   public ArrayList<TnoticeDTO> Tnoticelist() {
      ArrayList<TnoticeDTO> list = null;
      try {
         con = dbopen.getConnection();
         sql = new StringBuilder();
         sql.append(" Select nonum, nosubject, nowriter, nodate, noreadcnt");
         sql.append(" From Tnotice");
         sql.append(" Order by nonum Desc");
         pstmt = con.prepareStatement(sql.toString());
         rs = pstmt.executeQuery();
         if (rs.next()) {
            list = new ArrayList<TnoticeDTO>(); // 전체 행 저장
            do {
               TnoticeDTO dto = new TnoticeDTO(); // 한줄 저장
               dto.setNonum(rs.getInt("nonum"));
               dto.setNosubject(rs.getString("nosubject"));
               dto.setNowriter(rs.getString("nowriter"));
               dto.setNodate(rs.getTimestamp("nodate"));
               dto.setNoreadcnt(rs.getInt("noreadcnt"));
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
   
   
   

   public TnoticeDTO read(int nonum) {
         TnoticeDTO dto=null;
      try {
         con=dbopen.getConnection();
         sql=new StringBuilder();
         sql.append(" UPDATE Tnotice SET noreadcnt=noreadcnt+1 WHERE nonum=?   ");
         pstmt=con.prepareStatement(sql.toString());
         pstmt.setInt(1, nonum);
         pstmt.executeUpdate();
         
         sql=new StringBuilder();
         sql.append(" SELECT nonum, nosubject, nocontent, nowriter, nodate, noreadcnt");
         sql.append(" FROM Tnotice");
         sql.append(" WHERE nonum=?");
         pstmt= con.prepareStatement(sql.toString());
         pstmt.setInt(1, nonum);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            dto = new TnoticeDTO();
            dto.setNonum(rs.getInt("nonum"));
            dto.setNosubject(rs.getString("nosubject"));
            dto.setNocontent(rs.getString("nocontent"));
            dto.setNowriter(rs.getString("nowriter"));
            dto.setNodate(rs.getTimestamp("nodate"));
            dto.setNoreadcnt(rs.getInt("noreadcnt"));

         }else {
            dto=null;
         }//if end
      } catch (Exception e) {
          System.out.println("읽기 실패:"+e);
      }finally {
         DBClose.close(con,pstmt,rs);
      }//end
      return dto;
   }//read end
   
   
      
   public int create(TnoticeDTO dto) {
       int cnt=0;
          try {
            con =dbopen.getConnection();
            sql =new StringBuilder();
            sql.append(" INSERT INTO Tnotice(nonum, nosubject, nocontent, nodate, noreadcnt)");
            sql.append(" VALUES(nonum_seq.nextval,?,?,sysdate,?)");
            pstmt = con.prepareStatement(sql.toString());
             pstmt.setString(1, dto.getNosubject());
             pstmt.setString(2, dto.getNocontent());
             pstmt.setInt(3, dto.getNoreadcnt());

            cnt = pstmt.executeUpdate();
         } catch (Exception e) {
            System.out.println("공지사항 등록 실패"+e);
         }finally {
            DBClose.close(con,pstmt);
         }
          return cnt;
   }//create end
      
   public int update(TnoticeDTO dto) {
      int cnt=0;
      try {
         con= dbopen.getConnection();
         sql=new StringBuilder();
         sql.append(" UPDATE Tnotice");
         sql.append(" SET nosubject=?, nocontent=?");
         sql.append(" WHERE nonum=?");
         pstmt=con.prepareStatement(sql.toString());
         pstmt.setString(1, dto.getNosubject());
         pstmt.setString(2, dto.getNocontent());
         pstmt.setInt(3, dto.getNonum());   
         cnt=pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("수정실패"+e);
      }finally {
         DBClose.close(con,pstmt);      
      }//end
      return cnt;
   }//update end
   
   public int delete(int nonum) {
      int cnt=0;
      try {
         con=dbopen.getConnection();
         sql= new StringBuilder();
         sql.append(" DELETE FROM Tnotice");
         sql.append(" WHERE nonum=?");
         pstmt =con.prepareStatement(sql.toString());
         pstmt.setInt(1, nonum);
         cnt=pstmt.executeUpdate();
      } catch (Exception e) {
       System.out.println("삭제실패:"+e);
      }finally {
         DBClose.close(con,pstmt);
      }//end
      return cnt;
   }//delete end
   
   
}//class end