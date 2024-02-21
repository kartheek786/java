package jdbc;
import java.sql.*;
public class Connection1
{
 public static void main(String[] args) throws Exception
	{
		oracle.jdbc.driver.OracleDriver obj=new oracle.jdbc.driver.OracleDriver();
		
		DriverManager.registerDriver(obj);
		// using class.forname
		
		 //Class.forName("com.mysql.jdbc.Driver");
		//Class.forName("oracle.jdbc.OracleDriver");
		//Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sample1","root","karthik1");//mysql
        Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM","SYSTEM","karthik1");//oracle
		if(con1==null)
         System.out.println("connection is not established");
		else
			System.out.println("connection is established");

	}
}