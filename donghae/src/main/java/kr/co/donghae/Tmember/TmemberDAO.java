package kr.co.donghae.Tmember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.donghae.Tnotice.TnoticeDTO;
import net.utility.DBClose;
import net.utility.DBOpen;
import net.utility.MyAuthenticator;
import net.utility.Utility;
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
         sql.append(" WHERE Mid=? AND Mpasswd=? AND Mlevel IN('A1','B1','C1','D1')");
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
		DBClose.close(con,pstmt,rs);
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
        pstmt.setString(9, dto.getMnum());
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
		         dto.setMnum(rs.getString("mnum"));  
		         dto.setMpasswd(rs.getString("mpasswd"));
		         dto.setMtel(rs.getString("mtel"));
		         dto.setMzipcode(rs.getString("mzipcode"));
		      }
		   }catch(Exception e) {
		      e.printStackTrace();
		   }finally {
		      DBClose.close(con,pstmt,rs);
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
			        dto.setMnum(rs.getString("Mnum"));
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
			       pstmt.setString(8,   dto.getMnum());
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
	  public int Tdelcheck(TmemberDTO dto) {
		  int cnt=0;
		  try {
				 con=dbopen.getConnection();
			      sql=new StringBuilder();
			      sql.append(" Update Tmember");
			      sql.append(" Set Mlevel='F1'");
			      sql.append(" WHERE Mid=? AND Mpasswd=?");
			      
			      pstmt=con.prepareStatement(sql.toString());
			      pstmt.setString(1, dto.getMid());
			      pstmt.setString(2, dto.getMpasswd());
	
			      cnt=pstmt.executeUpdate();
			      
			    }catch (Exception e) {
			      System.out.println("실패:"+e);
			    }finally {
			      DBClose.close(con, pstmt);
			    }//end 
		  return cnt;
	  }//Tdelcheck()end
	  
//------------------------------------------------------------------------------------------------------	  
	public boolean TfindID(TmemberDTO dto) {
		  boolean flag=false;
		   try {
		      con=dbopen.getConnection();
		       sql=new StringBuilder();
		       sql.append(" SELECT Mid,Mpasswd ");
		       sql.append(" FROM Tmember ");
		       sql.append(" WHERE Mname=? AND Memail=? ");
		       
		       pstmt=con.prepareStatement(sql.toString());
		       pstmt.setString(1, dto.getMname());
		       pstmt.setString(2, dto.getMemail());
		       rs = pstmt.executeQuery();
		       
		       if(rs.next()){ 
		           
		           //이름과 이메일이 일차한다면
		               //임시비밀번호 발급
		               String[] ch= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
		                           ,"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
		                          ,"0","1","2","3","4","5","6","7","8","9"};              
		               //임시비밀번호 랜덤하게 10글자 발생
		               StringBuffer imsiPW=new StringBuffer();
		               for(int i=0; i<10; i++) {
		                  int num=(int)(Math.random()*ch.length);
		                  imsiPW.append(ch[num]);
		               }//for end
		               //System.out.println(imsiPW.toString());
		               
		               //임시비밀번호 업데이트하기
		               sql=new StringBuilder();
		               sql.append(" Update Tmember Set Mpasswd=?");
		               sql.append(" Where Mname=? and Memail=?");
		               pstmt=con.prepareStatement(sql.toString());
		               pstmt.setString(1, imsiPW.toString());
		               pstmt.setString(2, dto.getMname());
		               pstmt.setString(3, dto.getMemail());
		            
		               int cnt=pstmt.executeUpdate();
		               if(cnt==1) {
		                 String id=rs.getString("Mid");
		                 String content="";
		                 content += " 아이디 : "+ id+ "\n";
		                 
		                 content += " 비밀번호 : "+imsiPW;
		                    //임시비밀번호를 이메일로 전송하기
		                String mailServer="mw-002.cafe24.com";
		               Properties props=new Properties(); 
		               props.put("mail.smtp.host", mailServer);
		               props.put("mail.smtp.auth", "true");
		               Authenticator myAuth=new MyAuthenticator();    //다형성
		               Session sess=Session.getInstance(props, myAuth);

		                   //엔터및 특수문자 변환
		                   content=Utility.convertChar(content);
		                   
		                   //받는사람 이메일 주소(to)
		                   InternetAddress[] address={new InternetAddress(dto.getMemail())};
		                   //메일관련 메세지 작성
		                   Message msg=new MimeMessage(sess);
		                   //받는사람
		                   msg.setRecipients(Message.RecipientType.TO, address);
		                   //참조         RecipientType.
		                   //숨은참조         RecipientType.BCC
		                   
		                //보내는 사람
		                msg.setFrom(new InternetAddress("gksmf4095@gmail.com"));

		                   //메일제목
		                   msg.setSubject("myweb 비밀번호 입니다");
		                   //메일내용
		                   msg.setContent(content,"text/html; charset=UTF-8");
		                   //보낸날짜
		                   msg.setSentDate(new Date());
		                   //메일전송
		                   Transport.send(msg);
		                   
		                   
		               }//if end
		               flag=true;
		               
		            }else {
		               flag=false ;
		        }//if end
		   }catch(Exception e) {
			      System.out.println("아이디/비밀번호 찾기 실패:" +e);
			   }finally {
			      DBClose.close(con, pstmt, rs);
			   }//end
			   return flag;
	}
//-------------------------------------------------------------------------------------------------------------		 
	public ArrayList<TmemberDTO> Tlist() {
		ArrayList<TmemberDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" Select Mdate, Mlevel, Mname,Mid,Mpasswd,Mtel");
			sql.append(" From Tmember");
			sql.append(" Order by Mdate");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<TmemberDTO>(); // 전체 행 저장
				do {
					TmemberDTO dto = new TmemberDTO(); // 한줄 저장
					dto.setMdate(rs.getString("Mdate"));
					dto.setMlevel(rs.getString("Mlevel"));
					dto.setMname(rs.getString("Mname"));
					dto.setMid(rs.getString("Mid"));
					dto.setMpasswd(rs.getString("Mpasswd"));
					dto.setMtel(rs.getString("Mtel"));
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
//----------------------------------------------------------------------------------------
	public int duplecateMnum(String Mnum) {
		int cnt=0;
		try {
			con=dbopen.getConnection();
			sql= new StringBuilder();
			sql.append(" SELECT count(Mnum) as cnt ");
	        sql.append(" FROM Tmember ");
	        sql.append(" WHERE Mnum=?");  
	        
	        pstmt=con.prepareStatement(sql.toString());
	        pstmt.setString(1, Mnum);
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
//--------------------------------------------------------------------------------------
	
}//class end
