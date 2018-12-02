package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;

import controller.stationController;
import model.Station;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addStationGui {

	private JFrame frmAddStation;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new addStationGui();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public addStationGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddStation = new JFrame();
		frmAddStation.setTitle("Add Station");
		frmAddStation.setBounds(100, 100, 450, 300);
		frmAddStation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddStation.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225));
		panel.setBounds(138, 22, 161, 43);
		frmAddStation.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAddStation = new JLabel("Add Station");
		lblAddStation.setBounds(10, 0, 136, 43);
		panel.add(lblAddStation);
		lblAddStation.setFont(new Font("Palatino Linotype", Font.PLAIN, 24));
		
		JLabel lblStationArea = new JLabel("Station Area :");
		lblStationArea.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		lblStationArea.setBounds(25, 78, 118, 14);
		frmAddStation.getContentPane().add(lblStationArea);
		
		JLabel lblStationLocation = new JLabel("Station Location:");
		lblStationLocation.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		lblStationLocation.setBounds(25, 141, 107, 14);
		frmAddStation.getContentPane().add(lblStationLocation);
		
		textField = new JTextField();
		textField.setBounds(25, 103, 369, 20);
		frmAddStation.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(25, 166, 369, 20);
		frmAddStation.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Runnable run = new Runnable() {
					
				public void run() 
				{
					String area = textField.getText();
					String location = textField_1.getText();
					
					Station station = new Station();
					station.setArea(area);
					station.setAddress(location);
					
					try
					{
						stationController sc = new stationController();
						String success = sc.addStation(station);
						
						if(success.equals("1"))
						{
							JOptionPane.showMessageDialog(null, "Add Station Successfully");
							new stationGui();
							frmAddStation.dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Fail to Add.Please Try Again");
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}	
				};
				Thread taddStation = new Thread(run);
				taddStation.start();
				
				
			}
		});
		btnAdd.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		btnAdd.setBounds(158, 213, 107, 29);
		frmAddStation.getContentPane().add(btnAdd);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new stationGui();
				frmAddStation.dispose();
			}
		});
		btnBack.setBounds(277, 213, 117, 29);
		frmAddStation.getContentPane().add(btnBack);
		frmAddStation.setVisible(true);
	}

}
