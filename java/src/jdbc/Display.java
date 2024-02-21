package jdbc;



import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class Display {
 private static final String SELECT_DETAILS="Select id_sqe,name,ads,photo from art where id_sqe=? ";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try (Scanner s= new Scanner(System.in)){
			int num=0;
			if(s!=null) {
				System.out.println("Enter ID : ");
				 num=s.nextInt();
				
			}
			// creating File input Stream object and adding files location to it
		
				//connecting to jdbc by using oracle 
				try(//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample1","root","Ckarthik1");
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM","SYSTEM","Ckarthik1");
						PreparedStatement ps=con.prepareStatement(SELECT_DETAILS);
						){
					if(ps!=null) {
						//set values to the query
				ps.setInt(1,num);
					}//if
					
					if(ps!=null)
						try(ResultSet rs=ps.executeQuery()){
						if(rs.next()) {
							num=rs.getInt(1);
							String name = rs.getString(2);
							String ads=rs.getString(3);
							System.out.println(num+" "+name+" "+ads);
							try(InputStream is = rs.getBinaryStream(4);
									//create output stream 
								OutputStream os=new FileOutputStream("retive.jpg")){
								 IOUtils.copy(is,os);
								 System.out.println("BLOB files copied into the file ");
								
								
								
							}
							
								
							
						}
						else {
							System.out.println("Record not found");
						}
						
		

						
					
						}//try4
					
				}//try3
			}//try2
			
		//try1
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in the record insertion ");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
