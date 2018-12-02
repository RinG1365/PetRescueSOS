package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class adminGui {

	private JFrame frmAdminPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new adminGui();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public adminGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdminPage = new JFrame();
		frmAdminPage.setTitle("Admin Page");
		frmAdminPage.setBounds(100, 100, 557, 340);
		frmAdminPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdminPage.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225));
		panel.setBounds(111, 19, 333, 33);
		frmAdminPage.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAdminHomepage = new JLabel("Admin Homepage");
		lblAdminHomepage.setBounds(62, 0, 241, 33);
		panel.add(lblAdminHomepage);
		lblAdminHomepage.setFont(new Font("Palatino Linotype", Font.PLAIN, 24));
		
		JButton btnRescuer = new JButton("Rescuer");
		btnRescuer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new rescuerGui();
				frmAdminPage.dispose();
			}
		});
		btnRescuer.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		btnRescuer.setBounds(165, 64, 206, 33);
		frmAdminPage.getContentPane().add(btnRescuer);
		
		JButton btnReceiveSos = new JButton("Receive SOS");
		btnReceiveSos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new receiveSosGui();
				frmAdminPage.dispose();
			}
		});
		btnReceiveSos.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		btnReceiveSos.setBounds(165, 154, 206, 33);
		frmAdminPage.getContentPane().add(btnReceiveSos);
		
		JButton btnHistory = new JButton("SOS History");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new historyGui();
				frmAdminPage.dispose();
			}
		});
		btnHistory.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		btnHistory.setBounds(165, 199, 206, 33);
		frmAdminPage.getContentPane().add(btnHistory);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new loginGui();
				frmAdminPage.dispose();
			}
		});
		btnLogOut.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		btnLogOut.setBounds(165, 244, 206, 33);
		frmAdminPage.getContentPane().add(btnLogOut);
		
		JButton btnAddStation = new JButton("Station");
		btnAddStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new stationGui();
				frmAdminPage.dispose();
			}
		});
		btnAddStation.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		btnAddStation.setBounds(165, 109, 206, 33);
		frmAdminPage.getContentPane().add(btnAddStation);
		frmAdminPage.setVisible(true);
	}
}
