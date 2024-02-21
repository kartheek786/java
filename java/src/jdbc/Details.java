package jdbc;



import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Details {
 private static final String INSERT_ART="INSERT INTO DETAILS VALUES(id_seq.nextval,?,?,?,?,?,?)";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try (Scanner s= new Scanner(System.in)){
			String name=null; String DOB = null; String ads=null; String email=null; String song=null; String photo=null; 
			
			if(s!=null) {
				System.out.println("Enter name : ");
				 name=s.nextLine();
				System.out.println("Enter the  date of Birth : ");
				DOB =s.nextLine();
				System.out.println("Enter the address : ");
				ads=s.nextLine();
				System.out.println("Enter your email Id : ");
				email=s.nextLine();
				System.out.println("Add your Fav music : ");
				song=s.nextLine().replace("?","");
				System.out.println("Add photo : ");
				 photo=s.nextLine().replace("?","");// 
				
			}
			// creating File input Stream object and adding files location to it
			try(InputStream is= new FileInputStream(song);
					InputStream is1=new FileInputStream(photo);){
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date jud=sdf.parse(DOB);
					System.out.println("java.util.Date: "+jud);
					
					long ms=jud.getTime();
					java.sql.Date jsd=new java.sql.Date(ms);
					
				//connecting to jdbc by using oracle 
				try(//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample1","root","Ckarthik1");
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM","SYSTEM","Ckarthik1");
						PreparedStatement ps=con.prepareStatement(INSERT_ART);){
					if(ps!=null) {
						//set values to the query
						ps.setString(1,name);
						ps.setDate(2,jsd);
						ps.setString(3,ads);
						ps.setString(4,email);
						ps.setBlob(5,is);
						//ps.setBinaryStream(3,is); // we can use both 
						ps.setBlob(6,is1);
						
						
					
					}//if
					int count=0;
					if(ps!=null)
						
						count=ps.executeUpdate();
					if(count==0)
						System.out.println("Record Not Inserted");
					else 
						System.out.println("Record Inserted");
						
					
					
					
				}//try3
			}//try2
			
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
