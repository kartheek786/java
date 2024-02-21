package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Non_select_Create_command {

	public static void main(String[] args) {//main
		// TODO Auto-generated method stub
		Scanner s=null;
		Connection con=null;
		Statement st=null;	
		ResultSet rs=null;
		try {//try
			s=new Scanner(System.in);


			int sno=0;//hike 
			String name=null;//salary Starting range
			String loc=null;//salary ending range
			int avg=0;
			if( s!=null) {
				System.out.println( "Enter the sno : ");
				sno=s.nextInt();
				System.out.println( "Enter the name : ");
				name=s.next();
				System.out.println( "Enter the loc : ");
				loc=s.next();
				System.out.println("Enter the avg : ");
				avg=s.nextInt();
			}
			name="'"+name+"'";
			loc="'"+loc+"'";		

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
			String query = " CREATE TABLE Exp(id int primary key,name varchar2(20)" ;//mysql this query written in my sql

			int count=	st.executeUpdate(query);//used to execute query and update the data


			if (count != 0) {
				System.out.println("Table 'Exp' created successfully");
			} else {
				System.out.println("Table 'Exp' creation failed");
			}
			//Non select query which is used to  update,insert alter,drop,create commands






			String query2="insert into exp values ("+sno+","+name+")";

			count=st.executeUpdate(query2);

			if(count !=0) {
				System.out.println("Records add "+ count);
			}
			else {
				System.out.println("No Records"+ count);
			}
			String query3= "select count(*) from emp where id="+avg+"and name="+loc;
			rs=st.executeQuery(query3);

			if(rs.next()) {
				int count3=rs.getInt(1);
				if(count3!=0) {

					System.out.println("Records add "+ count3);}

				else {
					System.out.println("No Records"+ count3);
				}


			}}//try close
		catch(SQLException se) {
			if(se.getErrorCode()>=900&&se.getErrorCode()<=999) {// this used to give known or checked exceptions 
				System.out.println(" your enteries was not Correct check if their is any mistakes in query,statements,");	
			}
			else if(se.getErrorCode()==1) {
				System.out.println("");
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
	}

}
