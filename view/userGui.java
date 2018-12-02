package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import view.loginGui;
import view.SOSSender;

public class userGui {

	static String currentUser;
	private JFrame frmMainPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					new userGui(currentUser);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public userGui(String username) {
		currentUser = username;
		System.out.println(currentUser);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainPage = new JFrame();
		frmMainPage.setTitle("Main Page");
		frmMainPage.setBounds(100, 100, 651, 430);
		frmMainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainPage.getContentPane().setLayout(null);
		
		JButton btnSendSos = new JButton("SEND SOS");
		btnSendSos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new SOSSender(currentUser);
				frmMainPage.dispose();
			}
		});
		btnSendSos.setFont(new Font("Palatino", Font.BOLD, 15));
		btnSendSos.setBounds(248, 113, 139, 51);
		frmMainPage.getContentPane().add(btnSendSos);
		
		JButton btnSosHistory = new JButton("SOS HISTORY");
		btnSosHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new userSosHistory(currentUser);
				frmMainPage.dispose();
			}
		});
		btnSosHistory.setFont(new Font("Palatino", Font.BOLD, 15));
		btnSosHistory.setBounds(248, 196, 139, 51);
		frmMainPage.getContentPane().add(btnSosHistory);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225));
		panel.setBounds(138, 18, 378, 39);
		frmMainPage.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblWelcomeUsingPet = new JLabel("Welcome Using Pet Rescue SOS");
		lblWelcomeUsingPet.setBounds(54, 0, 279, 39);
		panel.add(lblWelcomeUsingPet);
		lblWelcomeUsingPet.setFont(new Font("Palatino", Font.BOLD, 18));
		
		JButton btnSignOut = new JButton("SIGN OUT");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new loginGui();
				JOptionPane.showMessageDialog(null, "Thanks for using Pet Rescue SOS");
				frmMainPage.dispose();
			}
		});
		btnSignOut.setFont(new Font("Palatino", Font.BOLD, 15));
		btnSignOut.setBounds(248, 275, 139, 51);
		frmMainPage.getContentPane().add(btnSignOut);
		
		frmMainPage.setVisible(true);
	}
}
