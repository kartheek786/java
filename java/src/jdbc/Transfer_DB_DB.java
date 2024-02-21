package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Transfer_DB_DB{

	private static final String SELECT ="SELECT * FROM SAMPLE1.student";
	private static final String INSERT="INSERT INTO student VALUES(?,?,?,?)";

	public static void main (String[] args) {
		Scanner s= null;
		Connection Oracon =null,Sqlcon=null;
		PreparedStatement ps= null;
		ResultSet rs=null;
		Statement st=null;

  
		try {

			//Activating Driver
			//Class.forName("oracle.jdbc.OracleDriver");
			//Class.forName("com.mysql.jdbc.Driver");

			//connecting jdbc to mysql and oracle 
			Oracon =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM","SYSTEM","Ckarthik1");

			Sqlcon=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample1","root","Ckarthik1");

			//Creating statement OBJ
			if(Oracon!=null)
				st=Oracon.createStatement();

			if(Sqlcon!=null)
				//prepared Statement object
				ps=Sqlcon.prepareStatement(INSERT);
			if(st!=null)
				rs=st.executeQuery(SELECT);

			if (rs!=null && ps!=null){


				while(rs.next()) {

					int sno =rs.getInt(1);

					String name =rs.getString(2);
					String sadd= rs.getString(3);
					float avg= rs.getFloat(4);


                    ps.setInt(1, sno);
					ps.setString(2,name);
					ps.setString(3, sadd);
					ps.setFloat(4, avg);
					ps.executeUpdate();
					




				}
				System.out.println("Successfully transfered");
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
					if( Oracon!=null) {Oracon.close();// close connection

					}}

				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if( Sqlcon!=null) {Sqlcon.close();// close connection

					}}

				catch(SQLException se) {
					se.printStackTrace();
				}

				try {
					if( s!=null) {s.close();}}//close scanner

				catch(Exception e) {
					e.printStackTrace();
				}


			
		}
	}
}//finally




