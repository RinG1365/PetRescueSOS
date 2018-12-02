package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

import view.loginGui;
import controller.loginController;
import model.User;

public class signUp {

	private JFrame frmRegisterAccount;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private JPasswordField rptPasswordField;
	private JTextField textMobile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
						new signUp();				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public signUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegisterAccount = new JFrame();
		frmRegisterAccount.setTitle("Register Account");
		frmRegisterAccount.setBounds(100, 100, 572, 485);
		frmRegisterAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegisterAccount.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225));
		panel.setBounds(107, 21, 337, 39);
		frmRegisterAccount.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRegister = new JLabel("Register to Rescue Pet");
		lblRegister.setBounds(57, 6, 247, 33);
		panel.add(lblRegister);
		lblRegister.setFont(new Font("Palatino", Font.BOLD, 23));
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Palatino", Font.PLAIN, 18));
		lblUsername.setBounds(76, 104, 128, 16);
		frmRegisterAccount.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Palatino", Font.PLAIN, 18));
		lblPassword.setBounds(76, 154, 134, 16);
		frmRegisterAccount.getContentPane().add(lblPassword);
		
		JLabel lblRepeatPassword = new JLabel("Repeat Password:");
		lblRepeatPassword.setFont(new Font("Palatino", Font.PLAIN, 18));
		lblRepeatPassword.setBounds(76, 206, 159, 16);
		frmRegisterAccount.getContentPane().add(lblRepeatPassword);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Palatino", Font.PLAIN, 18));
		lblGender.setBounds(76, 257, 128, 16);
		frmRegisterAccount.getContentPane().add(lblGender);
		
		JLabel lblMobileNo = new JLabel("Mobile No.:");
		lblMobileNo.setFont(new Font("Palatino", Font.PLAIN, 18));
		lblMobileNo.setBounds(76, 304, 128, 16);
		frmRegisterAccount.getContentPane().add(lblMobileNo);
		
		textUsername = new JTextField();
		textUsername.setBounds(286, 99, 191, 26);
		frmRegisterAccount.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(286, 149, 191, 26);
		frmRegisterAccount.getContentPane().add(passwordField);
		
		rptPasswordField = new JPasswordField();
		rptPasswordField.setBounds(286, 201, 191, 26);
		frmRegisterAccount.getContentPane().add(rptPasswordField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		comboBox.setBounds(286, 253, 81, 27);
		frmRegisterAccount.getContentPane().add(comboBox);
		
		textMobile = new JTextField();
		textMobile.setBounds(286, 299, 191, 26);
		frmRegisterAccount.getContentPane().add(textMobile);
		textMobile.setColumns(10);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent e) 
			{
				String username = textUsername.getText().trim();
				char[] password = passwordField.getPassword();
				char[] rptPassword = rptPasswordField.getPassword();
				String gender = String.valueOf(comboBox.getSelectedItem()) ;
				String mobileNo = textMobile.getText();
				String psw = String.valueOf(password);
				String rptpsw = String.valueOf(rptPassword);
				
				if(!(username.isEmpty() || psw.isEmpty() || rptpsw.isEmpty() || gender.isEmpty() || mobileNo.isEmpty()))
				{	
					if(Arrays.equals(password, rptPassword))
					{
						User user1 = new User();
						user1.setUsername(username);
						user1.setPassword(psw);
						user1.setGender(gender);
						user1.setMobile(mobileNo);
						
						loginController lg =new loginController();
						
						try 
						{
							String success = lg.addUser(user1);
							if(success.equals("1"))
							{
								JOptionPane.showMessageDialog(null, "Register Successfully!");
								new loginGui();
								frmRegisterAccount.dispose();
							}
							else if(success.equals("-1"))
							{
								JOptionPane.showMessageDialog(null, "Username already been used.Please use another username");
							}
							else 
							{
								JOptionPane.showMessageDialog(null, "Fail to Register.Please Try Again");
							}
							
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
					
						
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Password Not Match");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please fill in all the blank");
				}
				
			
			}
		});
		
		btnSubmit.setBounds(163, 377, 117, 33);
		frmRegisterAccount.getContentPane().add(btnSubmit);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new loginGui();
				frmRegisterAccount.dispose();
			}
		});
		btnBack.setBounds(309, 377, 117, 33);
		frmRegisterAccount.getContentPane().add(btnBack);
		
		frmRegisterAccount.setVisible(true);
	}

}
