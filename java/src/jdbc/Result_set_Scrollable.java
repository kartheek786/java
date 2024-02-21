package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Result_set_Scrollable {
  private static final String EMP_Q="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM","SYSTEM","Ckarthik1");
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
						ResultSet rs=st.executeQuery(EMP_Q)){
		if(rs!=null)
			while(rs.next()) {
				//System.out.println( rs.getRow()+ "------------------" + "Emp :  "+rs.getInt(1)+" Ename : "+rs.getString(2)+"    Job : "+rs.getString(3)+"    Sal : "+rs.getFloat(4)+"   Deptno : "+rs.getFloat(5));
				//System.out.println("_________________________");
				rs.afterLast();
			}
		if(rs!=null)
		while(rs.previous()) {
			//System.out.println( rs.getRow()+ "------------------" + "Emp :  "+rs.getInt(1)+" Ename : "+rs.getString(2)+"    Job : "+rs.getString(3)+"    Sal : "+rs.getFloat(4)+"   Deptno : "+rs.getFloat(5));
			
		}
		rs.first();
		System.out.println( rs.getRow()+ "------------------" + "Emp :  "+rs.getInt(1)+" Ename : "+rs.getString(2)+"    Job : "+rs.getString(3)+"    Sal : "+rs.getFloat(4)+"   Deptno : "+rs.getFloat(5));
		rs.absolute(3);
		System.out.println( rs.getRow()+ "------------------" + "Emp :  "+rs.getInt(1)+" Ename : "+rs.getString(2)+"    Job : "+rs.getString(3)+"    Sal : "+rs.getFloat(4)+"   Deptno : "+rs.getFloat(5));
		rs.relative(9);
		System.out.println( rs.getRow()+ "------------------" + "Emp :  "+rs.getInt(1)+" Ename : "+rs.getString(2)+"    Job : "+rs.getString(3)+"    Sal : "+rs.getFloat(4)+"   Deptno : "+rs.getFloat(5));
		}
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("problem in injection ");
		}

	}

}
