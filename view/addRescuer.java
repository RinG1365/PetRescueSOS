package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;

import controller.rescuerController;
import model.Rescuer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addRescuer {

	private JFrame frmAddRescuer;
	private JTextField textMobile;
	private JTextField textName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new addRescuer();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public addRescuer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddRescuer = new JFrame();
		frmAddRescuer.setTitle("Add Rescuer");
		frmAddRescuer.setBounds(100, 100, 450, 300);
		frmAddRescuer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddRescuer.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225));
		panel.setBounds(120, 11, 194, 26);
		frmAddRescuer.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAddRescuer = new JLabel("Add Rescuer");
		lblAddRescuer.setBounds(27, 0, 146, 26);
		panel.add(lblAddRescuer);
		lblAddRescuer.setFont(new Font("Palatino Linotype", Font.PLAIN, 24));
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		lblNewLabel.setBounds(31, 78, 46, 14);
		frmAddRescuer.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Gender:");
		lblNewLabel_1.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(31, 118, 60, 14);
		frmAddRescuer.getContentPane().add(lblNewLabel_1);
		
		JLabel lblPhoneNo = new JLabel("Phone no:");
		lblPhoneNo.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		lblPhoneNo.setBounds(31, 156, 71, 14);
		frmAddRescuer.getContentPane().add(lblPhoneNo);
		
		textMobile = new JTextField();
		textMobile.setBounds(103, 153, 245, 20);
		frmAddRescuer.getContentPane().add(textMobile);
		textMobile.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		comboBox.setBounds(103, 113, 241, 27);
		frmAddRescuer.getContentPane().add(comboBox);
		frmAddRescuer.setVisible(true);
		
		textName = new JTextField();
		textName.setBounds(99, 74, 245, 20);
		frmAddRescuer.getContentPane().add(textName);
		textName.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Runnable run = new Runnable() {
				
				public void run()
				{
					String rName = textName.getText();
					String mobile = textMobile.getText();
					String rGender = String.valueOf(comboBox.getSelectedItem());
					
					if(!(rName.isEmpty() || mobile.isEmpty() || rGender.isEmpty() )) 
					{
						Rescuer rescuer = new Rescuer();
						rescuer.setrName(rName);
						rescuer.setGender(rGender);
						rescuer.setPhoneNo(mobile);
						rescuerController rc = new rescuerController();
						try 
						{
							String success ="";
							success =  rc.addRescuer(rescuer);
							
							if(success.equals("1"))
							{
								JOptionPane.showMessageDialog(null, "Rescuer added Successfully");
								new rescuerGui();
								frmAddRescuer.dispose();
							}

							else
							{
								JOptionPane.showMessageDialog(null, "Fail to add.Please Try Again");
							}
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please fill in all the blank");
					}
				}
				};
				
				
				Thread taddRescuer = new Thread(run);
				taddRescuer.start();
			}
		});
		btnAdd.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		btnAdd.setBounds(214, 206, 100, 38);
		frmAddRescuer.getContentPane().add(btnAdd);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new rescuerGui();
				frmAddRescuer.dispose();
			}
		});
		btnBack.setBounds(332, 206, 100, 38);
		frmAddRescuer.getContentPane().add(btnBack);
		
	}

}
