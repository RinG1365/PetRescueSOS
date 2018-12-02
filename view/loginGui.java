package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.loginController;
import view.adminGui;
import view.userGui;
import view.signUp;
import model.User;
import javax.swing.UIManager; 

public class loginGui {

	private JFrame frmPetRescueSos;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new loginGui();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loginGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPetRescueSos = new JFrame();
		frmPetRescueSos.getContentPane().setBackground(SystemColor.window);
		frmPetRescueSos.setTitle("Pet Rescue SOS");
		frmPetRescueSos.setBackground(SystemColor.window);
		frmPetRescueSos.setBounds(100, 100, 636, 363);
		frmPetRescueSos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPetRescueSos.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(SystemColor.window);
		panel.setBackground(new Color(255, 228, 225));
		panel.setBounds(100, 27, 447, 54);
		frmPetRescueSos.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblStrayPetFound = new JLabel("Stray Pet Found?! Sign In & Help");
		lblStrayPetFound.setBounds(36, 0, 375, 54);
		panel.add(lblStrayPetFound);
		lblStrayPetFound.setFont(new Font("Palatino", Font.BOLD, 24));
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Palatino", Font.BOLD, 18));
		lblUsername.setBounds(122, 128, 123, 35);
		frmPetRescueSos.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Palatino", Font.BOLD, 18));
		lblPassword.setBounds(122, 189, 102, 28);
		frmPetRescueSos.getContentPane().add(lblPassword);
		
		username = new JTextField();
		username.setBounds(257, 130, 267, 31);
		frmPetRescueSos.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(257, 189, 267, 28);
		frmPetRescueSos.getContentPane().add(password);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBackground(Color.green);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = username.getText();
				char[] pass = password.getPassword();
				String psw = String.valueOf(pass);
				
				loginController lc = new loginController();
				
				if(!(user.isEmpty() || psw.isEmpty()))
				{
					try 
					{
						User user1 = new User();
						user1.setUsername(user);
						user1.setPassword(psw);
						
						String level =lc.doLogin(user1);
						if(level.equals("1"))
						{
							new adminGui();
							JOptionPane.showMessageDialog(null, "Hi Admin");
							frmPetRescueSos.dispose();
						}
						else if(level.equals("2"))
						{
							new userGui(user1.getUsername());
							JOptionPane.showMessageDialog(null, "Welcome "+user1.getUsername());
							frmPetRescueSos.dispose();
						}
						else if(level.equals("-1"))
						{
							JOptionPane.showMessageDialog(null, "Invalid Username or Password");
						}
						
						
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please fill in all the blank");
				}
				
			}
		});
		btnLogin.setBounds(358, 264, 117, 35);
		frmPetRescueSos.getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setToolTipText("");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new signUp();
				frmPetRescueSos.dispose();
			}
		});
		btnRegister.setForeground(SystemColor.activeCaptionText);
		btnRegister.setBackground(SystemColor.textHighlight);
		btnRegister.setBounds(206, 264, 117, 35);
		frmPetRescueSos.getContentPane().add(btnRegister);
	
		frmPetRescueSos.setVisible(true);
	}
}
