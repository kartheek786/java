package jdbc;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIBulder_ {
	//Query
	private static final String SEL_VAL="SELECT SNO,NAME,ADS,AVG FROM STUDENT";

	private JFrame frame;
	private JTextField txtSno;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	//JDBC
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIBulder_ window = new GUIBulder_();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIBulder_() {
		initialize();
		initialize_JDBC();
	}

	private void initialize_JDBC() {
		// TODO Auto-generated method stub
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SYSTEM","SYSTEM","Ckarthik1");
			ps=con.prepareStatement(SEL_VAL, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		    rs=ps.executeQuery();
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("sno");
		lblNewLabel.setBounds(69, 33, 45, 13);
		frame.getContentPane().add(lblNewLabel);
		
		txtSno = new JTextField();
		txtSno.setBounds(186, 30, 96, 19);
		frame.getContentPane().add(txtSno);
		txtSno.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("sname");
		lblNewLabel_1.setBounds(69, 68, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(186, 65, 96, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Add");
		lblNewLabel_2.setBounds(69, 101, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(186, 94, 96, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Avg");
		lblNewLabel_3.setBounds(69, 124, 45, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(186, 123, 96, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		Button button = new Button("First");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.first();
					txtSno.setText(rs.getString(1));
					textField.setText(rs.getString(2));
					textField_1.setText(rs.getString(3));
					textField_2.setText(rs.getString(4));
					
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		button.setBounds(38, 167, 66, 21);
		frame.getContentPane().add(button);
		
		Button button_1 = new Button("Next");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!rs.isLast())
					rs.next();
					txtSno.setText(rs.getString(1));
					textField.setText(rs.getString(2));
					textField_1.setText(rs.getString(3));
					textField_2.setText(rs.getString(4));
					
				}catch(SQLException se) {
					se.printStackTrace();
				}
				
			}
		});
		button_1.setBounds(128, 167, 66, 21);
		frame.getContentPane().add(button_1);
		
		Button button_2 = new Button("Previous");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!rs.isFirst())
					rs.previous();
					txtSno.setText(rs.getString(1));
					textField.setText(rs.getString(2));
					textField_1.setText(rs.getString(3));
					textField_2.setText(rs.getString(4));
					
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		button_2.setBounds(216, 167, 66, 21);
		frame.getContentPane().add(button_2);
		
		Button button_3 = new Button("Last");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { 
					
					rs.last();
					txtSno.setText(rs.getString(1));
					textField.setText(rs.getString(2));
					textField_1.setText(rs.getString(3));
					textField_2.setText(rs.getString(4));
					
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		button_3.setBounds(317, 167, 66, 21);
		frame.getContentPane().add(button_3);
	}
}
