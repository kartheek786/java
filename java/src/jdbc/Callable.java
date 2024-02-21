package jdbc;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Callable {
 private static final String INSERT_ART="{CALL First_pro(?,?,?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try (Scanner s= new Scanner(System.in)){
			
			
			System.out.println("Enter the  Empno : ");
			 int id=s.nextInt();
			
				
			
			// creating File input Stream object and adding files location to it

				//connecting to jdbc by using oracle 
				try(//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample1","root","Ckarthik1");
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM","SYSTEM","Ckarthik1");
						CallableStatement cs=con.prepareCall(INSERT_ART);
						){
					if(cs!=null)
					cs.registerOutParameter(2, Types.VARCHAR);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.FLOAT);
					if(cs!=null) {
						//set values to the query
						cs.setInt(1,id);
				
					
					}//if
					
					if(cs!=null)
						
					cs.execute();
				
			
					String name=cs.getString(2);
					String job=cs.getString(3);
					float sal =cs.getFloat(4);
					System.out.println("name :"+name+" job : "+job+"sal : "+sal);
					
		
						
					
					
					
				}//try3

			
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in the record insertion ");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}

