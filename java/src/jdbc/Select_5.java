package jdbc;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;


public class Select_5 {
	public static void main (String args[]) {

		Scanner s= null;
		Connection con=null;
		Statement st=null;
		ResultSet rs =null;


		try {
			int dept_Num1=0;
			s=new Scanner(System.in);
			if(s!=null) {
				System.out.println("Enter the dept number  1 :");
				dept_Num1=s.nextInt();

			}
			// Connection between the java application and jdbc by using oracle and mysql
			//con =DriverManager.getConnection("jdbc:mysql://localhost:3306/sample1","root","Ckarthik1");//mysql 

			con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM","system","Ckarthik1");// Oracle

			if(con!=null)
				//CREATING STATEMENT OBJECT
				st=con.createStatement();
			//String query=("SELECT ENAME FROM EMP WHERE ENAME LIKE 'A%'");
			String query ="SELECT ENAME,JOB,SAL FROM SAMPLE1.EMP WHERE DEPTNO="+dept_Num1;
			// creating resultset object

			if(st!=null)
				rs=st.executeQuery(query);


			if(rs!=null) {

				boolean flag=false;
				while(rs.next()) {
					System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)); 

					flag=true;
				}
				if(flag==false) {			System.out.println("NO records found");
				}
			}




		}
		catch(SQLException se) {
			if(se.getErrorCode()>=900&&se.getErrorCode()<=999) {
				System.out.println("wrong keys wrong statements,wrong query");
			}
		}catch(Exception e) {
			e.printStackTrace();

		}
		finally {
			try {
				if( rs!=null) {
					rs.close();
				}}

			catch(SQLException se) {
				se.printStackTrace();
			}


			try {
				if( st!=null) {st.close();}}

			catch(SQLException se) {
				se.printStackTrace();
			}

			try {
				if( con!=null) {con.close();

				}}

			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if( s!=null) {s.close();}}

			catch(Exception e) {
				e.printStackTrace();
			}
		}


	}

}
