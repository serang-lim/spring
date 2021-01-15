package net.utility;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Component;

//스프링컨테이너(웹서버)가 자동으로 객체 생성함
@Component
public class DBOpen {
	
	public DBOpen() {
		System.out.println("---DBOpen() 객체 생성됨");
	}//DBOpen ()end
	
	
  //오라클 데이터베이스 연결 메소드
  public Connection getConnection() {
    
    //오라클 DB 연결 정보
    String url     ="jdbc:oracle:thin:@localhost:1521:xe";      
    String user    ="system";
    String password="1234";
    String driver  ="oracle.jdbc.driver.OracleDriver";
    
    Connection con=null;
    
    try {
      
      Class.forName(driver);  
      con=DriverManager.getConnection(url, user, password);
      
    }catch(Exception e){
      System.out.println("DB실패:" + e);
    }//end
    
    return con;
    
  }//getConnection() end
  
}//class end





