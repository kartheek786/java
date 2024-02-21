package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BAtch_Update {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try(Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM","SYSTEM","Ckarthik1");
				Statement st=con.createStatement();
				
				){
			st.addBatch("insert into student values(10,'narendra','hyd',45.55)");
			st.addBatch("update student set name ='bittu' where sno =7");
			st.addBatch("delete from student where sno=10s");
			
			int[] result =st.executeBatch();
			int sum =0;
			for(int i=0;i<result.length;++i) {
				
				sum += result[i];
				
			}
			System.out.println("succesful"+sum);
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e ){
			e.printStackTrace();
		}

	}

}
