package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Date_values {
	private static final String STUDENT_QUERY = "INSERT INTO XYZ(name,DOB,DOJ) VALUES ( ?, ?, ?)";

	public static void main(String args[]) {
		Scanner s= null;
		Connection con =null;
		PreparedStatement ps= null;
		ResultSet rs=null;
		String name=null; String DOB=null; String DOJ=null;
		try {
			s=new Scanner(System.in);
			if(s!=null) {
				System.out.println("ENTER THE NAME");
				name= s.next();
				System.out.println("Enter the Date of birth : ");
				DOB=s.next();
				System.out.println("Enter the Date of Join : ");
				DOJ =s.next();

			}
			// Converting the string values into  java.util.date class obj DOB
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date jud = sdf.parse(DOB);
			System.out.println(sdf);
			System.out.println(jud);


			//COVERTING java.util.date into java.sql.date class obj

			long ms=jud.getTime();
			java.sql.Date sqd=new java.sql.Date(ms);
			System.out.println("DOB : " +sqd);


			// Converting the string values into  java.util.date class obj DOJ
			SimpleDateFormat sdf1= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date jud1 = sdf1.parse(DOJ);
			System.out.println(sdf1);
			System.out.println(jud1);


			//COVERTING java.util.date into java.sql.date class obj

			long ms1=jud1.getTime();
			java.sql.Date sqd1=new java.sql.Date(ms1);
			System.out.println("DOJ  : "+sqd1);

			//Activating Driver
			//Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("oracle.jdbc.OracleDriver");

			//Connecting to jdbc by using url both mysql and oracle
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample1","root","Ckarthik1");// mysql
			//con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM","SYSTEM","Ckarthik1");
			if(con!=null)
				ps=con.prepareStatement(STUDENT_QUERY);
			if(ps!=null) {
				ps.setString(1,name);
				ps.setDate(2,sqd);
				ps.setDate(3, sqd1);
				
			}
			int result = ps.executeUpdate();
			if(result==0) {
				System.out.println("student details are not inserted");

			}
			else {
				System.out.println("student details are inserted");
			}

				




		}
		catch(SQLException se) {
			se.printStackTrace();//this used to print checked numbers
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

	}

}



