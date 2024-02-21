package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Login {
	private static final String THRID_PRP="{CALL THRID_PRO(?,?,?)}";
	public static void main (String args[]) {
   
		try(Scanner s= new Scanner(System.in)){
          String us=null,ps=null;

			if(s!=null) {
				System.out.println("Enter the user name : ");
				us=s.next();
				System.out.println("Enter the password :");
				ps=s.next();
			}
			try(Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM","SYSTEM","Ckarthik1");
					CallableStatement cs=con.prepareCall(THRID_PRP);){
		     if(cs!=null) {
				cs.registerOutParameter(3, Types.VARCHAR);

		     }
		     if(cs!=null) {
		    	 cs.setString(1,us);
		    	 cs.setString(2, ps);
		     }
		     String result=null;
		     if(cs!=null) {
		    	 cs.execute();
		    result=cs.getString(3);
		    		System.out.println(result);
		    
		     }
			
		}
			}

		catch(SQLException se ){
           se.printStackTrace();
           System.out.println("problem in injection");
		}
		catch(Exception e) {
			e.printStackTrace();
		
		}
		}

}
