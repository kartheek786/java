package jdbc;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui_login extends JFrame implements ActionListener,WindowListener{

	//Query
	private static final String LOG_IN="SELECT * FROM IRCTC WHERE UNAME =? AND PWD = ?";

	// creating label and text boxes

	private JLabel jlogin,jpassword;
	private JTextField tlogin,tpassword;
	private JButton bsubmit;

	// JDBC
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;


	public Gui_login() {
		setTitle("Login app ");
		setSize(400,400);
		
		setLayout(new FlowLayout());
        // Center the frame on the screen
     





		// ADDING COMPONENTS

		jlogin =new JLabel(" LOGIN ");
		add(jlogin);


		tlogin = new JTextField(10);
		add(tlogin);

		jpassword =new JLabel(" Password ");
		add(jpassword);


		tpassword = new JTextField(10);
		add(tpassword);


		bsubmit =new JButton("SUBMIT");
		add(bsubmit);

		bsubmit.addActionListener(this);







		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}
	private static void showError(String message) {
		final Dialog dialog = new Dialog(new Frame(), "Error", true);
		dialog.setLayout(new FlowLayout());
		dialog.add(new Label(message));
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		dialog.add(closeButton);
		dialog.setSize(200, 100);
		dialog.setVisible(true);
	}
	public boolean initalize_JDBC( String uname,String pwd) {
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM","system","Ckarthik1");
			ps=con.prepareStatement(LOG_IN);
			ps.setString(1,uname);
			ps.setString(2, pwd);

			rs=ps.executeQuery();

			return rs.next();

		}
		catch(SQLException se) {
			se.printStackTrace();
			return false;
		}

	}




	public static void main(String[] args) {

		// TODO Auto-generated method stub
		new Gui_login();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub





		String uname= tlogin.getText();
		String psw= tpassword.getText();


		if(uname.isEmpty()  ) {
			showError("Username mandatory");



		}
		else if(psw.isEmpty()) {
			showError("Password mandatory");

		}

		else {
			if (initalize_JDBC(uname, psw)) {
				JOptionPane.showMessageDialog(this, "Login successful");
				// Add code to open new window or perform other actions upon successful login
			} else {
				showError("Invalid username or password");
			}
		}


	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		try {
			if(rs!=null) {
				rs.close();
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}

		try {
			if(ps!=null) {
				ps.close();
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		try {
			if(con!=null) {
				con.close();
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}


	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
