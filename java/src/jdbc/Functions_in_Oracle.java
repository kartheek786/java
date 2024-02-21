package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Types;
import oracle.jdbc.internal.OracleTypes;

public class Functions_in_Oracle {
	private static final String STU_F="{?=call STU_F(?,?,?)}";
	public static void main (String args[]) {
   
		try(Scanner s= new Scanner(System.in)){
          int I=0;

			if(s!=null) {
				System.out.println("Enter the user Sno : ");
				I=s.nextInt();
				
			}
			try(Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM","SYSTEM","Ckarthik1");
					CallableStatement cs=con.prepareCall(STU_F);){
		     if(cs!=null) {
		    	 cs.registerOutParameter(1, Types.FLOAT);
				cs.registerOutParameter(3,Types.VARCHAR);
				cs.registerOutParameter(4, Types.VARCHAR);
				
               
		     }
		     if(cs!=null) {
		    	 cs.setInt(2,I);
		   
		     } 
		     if(cs!=null) {
		    	 cs.execute();
		    	
		    	if(cs!=null) {
		    		
		    		System.out.println(cs.getString(3));
		    		System.out.println(cs.getString(4));
		    		System.out.println(cs.getFloat(1));
		    	}
		    		
		    		
		    		 
		    		 

		     
		     
		     
		   
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
