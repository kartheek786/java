package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Result_Pre_Scrollable {
	private static final String EMP_Q="SELECT empno,ename,job,sal,deptno FROM emp";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props=null;
		try(InputStream is= new FileInputStream("src/jdbc/commons/jdbc.properties")){
			props =new Properties();
			props.load(is);
		}
		catch(FileNotFoundException fe ) {
			fe.printStackTrace();
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		try(Connection con =DriverManager.getConnection(props.getProperty("jdbc.url"),props.getProperty("jdbc.user"),props.getProperty("jdbc.pswd"));
				PreparedStatement ps=con.prepareStatement(EMP_Q,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=ps.executeQuery()){
			if(rs!=null)
				while(rs.next()) {
					
				
					System.out.println( rs.getRow()+ "------------------" + "Emp :  "+rs.getInt(1)+" Ename : "+rs.getString(2)+"    Job : "+rs.getString(3)+"    Sal : "+rs.getFloat(4)+"   Deptno : "+rs.getFloat(5));
					//System.out.println("_________________________");
					rs.afterLast();
				}
			// code for inserting rows
					/*rs.moveToInsertRow();
					rs.updateInt(1,7);
					rs.updateString(2,"kar");
					rs.updateString(3,"hyd");
					
					rs.updateFloat(4,34.00f);
					rs.insertRow();
					System.out.println("inserted ");*/
			
			// for deleting
			/*
			rs.absolute(6);
			rs.deleteRow();
			System.out.println("deleted");*/
			//for updating
			/*
			rs.absolute(4);
			rs.updateString(6,"ds");
			rs.updateRow();*/
					
					
				
			
		}

		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("problem in injection ");
		}


	}

}
