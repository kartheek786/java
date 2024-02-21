package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TCL_Bank {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         
		try(Scanner s= new Scanner(System.in);){
			long ACN_1=0;
			long ACN_2=0;
			double AMT=0.0;
			if(s!=null) {
				System.out.println("Enter the Account Number : ");
				ACN_1=s.nextLong();
				System.out.println("Enter the Acount Number :");
				ACN_2=s.nextLong();
				System.out.println("Enter the Amount TO Send :");
				AMT= s.nextDouble();
				
				
				
				
			}
			try(Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM","System","Ckarthik1");
					Statement st = con.createStatement();){
				
				if(con!=null)
					con.setAutoCommit(false);
					
				st.addBatch("UPDATE BANK_TCL SET AMOUNT = AMOUNT-"+AMT+" WHERE ACNO="+ACN_1+"");
				st.addBatch("UPDATE BANK_TCL SET AMOUNT = AMOUNT-"+AMT+" WHERE ACNO="+ACN_2+"");
				
				int[] result =st.executeBatch();
				boolean flag =true;
				
				for(int i=0;i<result.length;++i) {
					 if(result[i]==0){
						 flag=false;
						 break;
						 
						 
					 }
					 if(flag==true) {
						 con.commit();
						 System.out.println("succesfull commited");
						 
					 }
					 else {
						 con.rollback();
						 System.out.println("roll back");
						 
					 }
					 
				}
				
			}
			
			
			
		}
		
		
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
