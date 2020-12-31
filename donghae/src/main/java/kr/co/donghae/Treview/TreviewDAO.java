package kr.co.donghae.Treview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class TreviewDAO {
		
		@Autowired
		DBOpen dbopen;
		
		@Autowired
		DBClose dbclose;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuilder sql=null;
		ArrayList<TreviewDTO> list=null;

		public TreviewDAO() {
			System.out.println("---TreviewDAO() 객체 생성됨...");
		}//MediaDAO() end
		
		public int create(TreviewDTO dto) { //쓰기
			int cnt=0;
		       try {
		    	 con = dbopen.getConnection();
		         sql =new StringBuilder();
		         sql.append(" INSERT INTO review(Rnum, Rsubject, Rcontent, Rpasswd, Rphoto_name, Rphoto_size, Rregion, Rid, Rdate)");
		         sql.append(" VALUES( Rnum_seq.nextval, ?, ?, ?, ?, ?, ?, ?, sysdate)");
		         pstmt = con.prepareStatement(sql.toString());
		         pstmt.setString(1, dto.getRsubject());
	             pstmt.setString(2, dto.getRcontent());
	             pstmt.setString(3, dto.getRpasswd());
	             pstmt.setString(4, dto.getRphoto_name());
	             pstmt.setLong(5, dto.getRphoto_size());
	             pstmt.setString(6, dto.getRregion());
	             pstmt.setString(7, dto.getRid());

		         cnt = pstmt.executeUpdate();
		      } catch (Exception e) {
		         System.out.println("게시물 등록실패"+e);
		      }finally {
		         DBClose.close(con,pstmt);
		      }
		       return cnt;
		}//create() end
		
		public ArrayList<TreviewDTO> list(){ //목록
			ArrayList<TreviewDTO> list = null;
			try {
				con = dbopen.getConnection();
				sql = new StringBuilder();
				sql.append(" SELECT Rreadcnt, Rnum, Rsubject, Rregion, Rid, Rdate");
				sql.append(" FROM review");
				sql.append(" ORDER BY Rnum DESC");
				
				pstmt = con.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					list = new ArrayList<TreviewDTO>(); // 전체 행 저장
					do {
						TreviewDTO dto = new TreviewDTO(); // 한줄 저장
						dto.setRreadcnt(rs.getInt("rreadcnt"));
						dto.setRnum(rs.getInt("rnum"));
						dto.setRsubject(rs.getString("rsubject"));
						dto.setRregion(rs.getString("rregion"));
						dto.setRid(rs.getString("rid"));
						dto.setRdate(rs.getString("rdate"));
						list.add(dto);
					} while (rs.next());
				} else {
					list = null;
				} // if end
			} catch (Exception e) {
				System.out.println("게시물목록실패:" + e);
			} finally {
				DBClose.close(con, pstmt, rs);
			} // end
			return list;
		}//list() end
		
		public TreviewDTO read(int rnum) { //상세보기
			TreviewDTO dto = null;
			try {
		           con=dbopen.getConnection();
		           sql=new StringBuilder();
		           sql.append(" SELECT Rnum, Rsubject, Rcontent, Rregion, Rid, Rdate, Rreadcnt, Rphoto_name, Rphoto_size");
		           sql.append(" FROM review");
		           sql.append(" WHERE Rnum=?");
		           pstmt=con.prepareStatement(sql.toString());
		           pstmt.setInt(1, rnum);
		           rs=pstmt.executeQuery();
		           
		           if(rs.next()) {
		              dto=new TreviewDTO();
		              dto.setRnum(rs.getInt("rnum"));
		              dto.setRsubject(rs.getString("rsubject"));
		              dto.setRcontent(rs.getString("rcontent"));
		              dto.setRregion(rs.getString("rregion"));
		              dto.setRid(rs.getString("rid"));
		              dto.setRdate(rs.getString("rdate"));
		              dto.setRreadcnt(rs.getInt("rreadcnt"));
		              dto.setRphoto_name(rs.getString("rphoto_name"));
		              dto.setRphoto_size(rs.getLong("rphoto_size"));
		           }else {
		              dto=null;
		           }//if end
		        }catch(Exception e) {
		           System.out.println("게시물 상세보기 실패" + e);
		        }finally {
		           DBClose.close(con,pstmt,rs);
		        }//end
		        return dto;
		}//read() end
		
		public int update(TreviewDTO dto) { 
			int cnt=0;
			try {
		           con=dbopen.getConnection();
		           sql=new StringBuilder();
		           sql.append(" UPDATE review");
		           sql.append(" SET Rsubject=?, Rcontent=?, Rphoto_name=?, Rphoto_size=?, Rregion=?, Rpasswd=? ");
		           sql.append(" WHERE Rnum=?");
		           pstmt=con.prepareStatement(sql.toString());
		           pstmt.setString(1, dto.getRsubject());
		           pstmt.setString(2, dto.getRcontent());
		           pstmt.setString(3, dto.getRphoto_name());
		           pstmt.setLong(4, dto.getRphoto_size());
		           pstmt.setString(5, dto.getRregion());
		           pstmt.setString(6, dto.getRpasswd());
		           pstmt.setInt(7, dto.getRnum());
		           cnt = pstmt.executeUpdate();
			      } catch (Exception e) {
			         System.out.println("수정 실패" + e);
			      }finally {
			         DBClose.close(con,pstmt);
			      }//end
					return cnt;
		}//update() end
		
		public int delete(int Rnum) {
			int cnt=0;
			try {
		           con=dbopen.getConnection();
		           sql=new StringBuilder();
		           sql.append(" DELETE FROM review");
		           sql.append(" WHERE Rnum=?");
		           pstmt=con.prepareStatement(sql.toString());
		           pstmt.setInt(1, Rnum);
		           cnt = pstmt.executeUpdate();
			      } catch (Exception e) {
			         System.out.println("삭제 실패" + e);
			      }finally {
			         DBClose.close(con,pstmt);
			      }//end
					return cnt;
		}//delete() end
		

		public ArrayList<TreviewDTO> list2(String col, String word) {
	         ArrayList<TreviewDTO> list=null;
	         try {
	            con=dbopen.getConnection();
	            sql=new StringBuilder();
	            
	            sql.append(" SELECT Rreadcnt, Rnum, Rsubject, Rregion, Rid, Rdate");
	            sql.append(" From review");

	            //검색어가 있다면
	            //" Where wname like '%"+ word + "%'";
	               
	            if(col.equals("0")){
	               sql.append(" Where Rid like '%"+word+"%'");
	               sql.append(" Order by rnum desc");
	               pstmt=con.prepareStatement(sql.toString());
	            }else if(word.equals(null)) {
	               sql.append(" Where Rregion=?");
	               sql.append(" Order by rnum desc");
	               pstmt=con.prepareStatement(sql.toString());
	               pstmt.setString(1, col);
	               
	            }else {
	               sql.append(" Where Rregion=? and Rid like '%"+word+"%'");
	               sql.append(" Order by rnum desc");
	               pstmt=con.prepareStatement(sql.toString());
	               pstmt.setString(1, col);
	               
	            }
	            
	            rs=pstmt.executeQuery();
	            if(rs.next()) {
	              list=new ArrayList<TreviewDTO>(); //전체 행 저장
	              do {
	                 TreviewDTO dto=new TreviewDTO(); //한줄 저장
	                dto.setRreadcnt(rs.getInt("rreadcnt"));
	                dto.setRnum(Integer.parseInt(rs.getString("rnum")));
	                dto.setRsubject(rs.getString("rsubject"));
	                dto.setRregion(rs.getString("rregion"));
	                dto.setRid(rs.getString("rid"));
	                dto.setRdate(rs.getString("rdate")); 
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
   
	//글갯수 구하기 
		public int getArticleCount() {
		      int x=0;
		      try {
		         con=dbopen.getConnection();
		         pstmt=con.prepareStatement(" SELECT count(*) FROM review");
		         rs=pstmt.executeQuery();
		         if(rs.next()) {
		            x=rs.getInt(1);
		         }//if end
		         
		      } catch (Exception e) {
		         e.printStackTrace();
		      
		      }finally {
		         DBClose.close(con, pstmt, rs);
		         
		      }//end
		      
		      return x;
		      
		   }//getArticleCount() end
		
		
		//글목록 구하기 
		public List<TreviewDTO> getArticles(int start, int end) throws Exception{
		      List articleList=null;
		      sql=new StringBuilder();
		      sql.append(" Select AA.* ");
		      sql.append(" From( ");
		      sql.append("    Select Rownum as ronum, BB.* ");
		      sql.append("     From (");
		      sql.append("       Select rnum, rid, rcontent, rsubject, rpasswd, rdate ");
		      sql.append("             rregion, rreadcnt ");
		      sql.append("      From review Order byrnum desc");
		      sql.append("       ) BB ");
		      sql.append("    )AA ");
		      sql.append(" Where AA.ronum>=? and AA.ronum<=? ");
		      
		      try {
		         con=dbopen.getConnection();
		         pstmt=con.prepareStatement(sql.toString());
		         pstmt.setInt(1, start);
		         pstmt.setInt(2, end);
		         rs=pstmt.executeQuery();
		         
		         if(rs.next()) {
		            articleList=new ArrayList(end);
		            do {
		            	TreviewDTO article=new TreviewDTO();
		            	article.setRnum(rs.getInt("rnum"));
		                article.setRid(rs.getString("rid"));
		                article.setRreadcnt(rs.getInt("rreadcnt"));
		                article.setRsubject(rs.getString("rsubject"));
		                article.setRpasswd(rs.getString("rpasswd"));
		                article.setRdate(rs.getString("rdate"));
		                article.setRregion(rs.getString("rregion"));
		                article.setRcontent(rs.getString("rcontent"));
		              
		               articleList.add(article);
		               
		            }while(rs.next());
		         }//if end
		         
		      }catch (Exception e) {
		         e.printStackTrace();
		      }finally {
		         DBClose.close(con, pstmt, rs);
		      }//end
		      return articleList;
		   }//getArticles() end	
		
		public TreviewDTO getArticle(int rnum) throws Exception{
			TreviewDTO article=null;
			try{
				con=dbopen.getConnection();
				sql=new StringBuilder();
				sql.append(" UPDATE review SET rreadcnt=rreadcnt+1 WHERE rnum=? "); 
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setInt(1, rnum);
				pstmt.executeUpdate();
				
				sql.delete(0, sql.length());
				sql.append(" SELECT rnum, rid, rcontent, rsubject, rpasswd, rdate, rregion, rreadcnt ");
			 	sql.append(" FROM board WHERE rnum=? ");
	 			pstmt=con.prepareStatement(sql.toString());
				pstmt.setInt(1, rnum);
				rs=pstmt.executeQuery();
		         
		        if(rs.next()) {
		        	article=new TreviewDTO(); 
		        	article.setRnum(rs.getInt("rnum"));
	                article.setRid(rs.getString("rid"));
	                article.setRreadcnt(rs.getInt("rreadcnt"));
	                article.setRsubject(rs.getString("rsubject"));
	                article.setRpasswd(rs.getString("rpasswd"));
	                article.setRdate(rs.getString("rdate"));
	                article.setRregion(rs.getString("rregion"));
	                article.setRcontent(rs.getString("rcontent"));
		        	
		        }//if end
				
			}catch (Exception e) {
		         e.printStackTrace();
		      }finally {
		         DBClose.close(con, pstmt, rs);
		      }//end
			
			return article;
			
		}//getArticle() end
	
}//class end
