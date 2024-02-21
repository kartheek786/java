package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Sql_injection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s =null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
	
		try {
			String user =null;
			String password=null;
			s =new Scanner(System.in);
			if (s!=null) {
				System.out.println("Enter the user name : ");
				user=s.nextLine();
				System.out.println("Enter the Password : ");
				password=s.nextLine();
				
				
			}
			user="'"+user+"'";
			password="'"+password+"'";
			

			//loading jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");	//oracle to activate the Driver Manager of the data base
			//Class.forName("com.mysql.jdbc.Driver");// my sql  

			//Connecting to  javaapplication to jdbc this works as link or url that which connect to the data base
			//con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM","system","Ckarthik1");
		
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample1","root","Ckarthik1");

			if(con!=null) 
				st=con.createStatement();// creating statement object which act like vehicle between the java application and Data base
			// Writing query to get max sal from emp table

			//String query="SELECT EMPNO,ENAME,JOB,DEPTNO FROM sample1.EMP WHERE SAL =(SELECT MAX(SAL)FROM sample1.EMP)";//oracle written in oracle
			String query="SELECT COUNT(*) FROM IRCTC WHERE USNAME="+user+"AND PASW="+password;//mysql this query written in my sql
			

			if(st!=null)
				rs=st.executeQuery(query);//select which used to retrieving data from Data base
             if(rs!=null) {
            	 rs.next();
            	 int count =rs.getInt(1);
            	 if(count==0) 
            		 System.out.println("INVALID CREDENTIALS ");
            	 
            	 else 
            		 System.out.println("VALID CREDENTIALS ");
            	 
            	 
             }
			





		}
		catch(SQLException se) {
			if(se.getErrorCode()>=900&&se.getErrorCode()<=999) {// this used to give known or checked exceptions 
				System.out.println(" your enteries was not Correct check if their is any mistakes in query,statements,");	
			}
			else if(se.getErrorCode()==1) {
				System.out.println("-");
			}
		}
		catch(Exception e) {
			e.printStackTrace();		}// this used to give the unknown or unchecked exception in the program




		finally {// finally this is used to close all the connection that have been used
			try {
				if( rs!=null) {
					rs.close();
				}}

			catch(SQLException se) {
				se.printStackTrace();//close result set
			}


			try {
				if( st!=null) {st.close();}}// close statement 

			catch(SQLException se) {
				se.printStackTrace();
			}

			try {
				if( con!=null) {con.close();// close connection

				}}

			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if( s!=null) {s.close();}}//close scanner

			catch(Exception e) {
				e.printStackTrace();
			}

		}//finally

	}}
