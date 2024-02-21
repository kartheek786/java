package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;



public class Prepared_statement {
	private static final String STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		Scanner s=null;
		Connection con =null;
		PreparedStatement ps=null;
		ResultSet rs= null;




		try {
			s=new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			// used to connect with java application and jdbc using my sql
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample1","root","Ckarthik1");

			//creating  prepared statement object 
			if(con!=null)
				ps=con.prepareStatement(STUDENT_QUERY);

			if(ps!=null && s!=null) {
				System.out.println("Enter the number of users ");
				int count=s.nextInt();
				for(int i=1;i<count;++i) {
					System.out.println("Enter the details of "+i+ "user :");
					System.out.println("Enter the sno number : ");// getting details from user 
					int sno=s.nextInt();
					System.out.println("Enter the name of : ");
					String name=s.next();
					System.out.println("Enter the address of : ");
					String add=s.next();
					System.out.println("Enter the avg : ");
					float avg=s.nextFloat();
					// giving input values to the pre-compiled object 
					//inserting values into the query
					ps.setInt(1,sno);   // 
					ps.setString(2,name);
					ps.setString(3,add);
					ps.setFloat(4,avg);
					// here execute update query used to execute and send results 
					int result = ps.executeUpdate();
					if(result==0) {
						System.out.println("student details are not inserted");

					}
					else {
						System.out.println("student details are inserted");
					}






				}


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
				if( ps!=null) {ps.close();}}// close statement 

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
		// TODO Auto-generated method stub

	}

}
