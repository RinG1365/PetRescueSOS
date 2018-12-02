package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import view.userGui;
import controller.sosController;
import model.Station;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SOSSender {

	static String currentUser;
	private JFrame frmSosSender;
	private JTextField textField;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	public JSONArray jsnArr;
	private String id ="";
	
	public JSONArray getJsnArr() {
		return jsnArr;
	}

	public void setJsnArr(JSONArray jsnArr) {
		this.jsnArr = jsnArr;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new SOSSender(currentUser);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SOSSender(String username) {
		currentUser = username;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSosSender = new JFrame();
		frmSosSender.setTitle("SOS SENDER");
		frmSosSender.setBounds(100, 100, 620, 454);
		frmSosSender.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSosSender.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225));
		panel.setBounds(58, 19, 136, 36);
		frmSosSender.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SOS Info");
		lblNewLabel.setBounds(27, 6, 85, 27);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Palatino", Font.BOLD, 18));
		
		JLabel lblArea = new JLabel("Station ID:");
		lblArea.setFont(new Font("Palatino", Font.BOLD, 15));
		lblArea.setBounds(27, 76, 99, 27);
		frmSosSender.getContentPane().add(lblArea);
		
		JLabel lblPetType = new JLabel("Pet Type:");
		lblPetType.setFont(new Font("Palatino", Font.BOLD, 15));
		lblPetType.setBounds(27, 115, 85, 27);
		frmSosSender.getContentPane().add(lblPetType);
		
		JLabel lblPetQuantity = new JLabel("Pet Quantity:");
		lblPetQuantity.setFont(new Font("Palatino", Font.BOLD, 15));
		lblPetQuantity.setBounds(27, 164, 99, 27);
		frmSosSender.getContentPane().add(lblPetQuantity);
		
	
		
		comboBox = new JComboBox();	
		comboBox.setBounds(160, 76, 386, 27);
		frmSosSender.getContentPane().add(comboBox);
		
		fillStationChoice();
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Dog", "Cat"}));
		comboBox_1.setBounds(160, 115, 227, 27);
		frmSosSender.getContentPane().add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(160, 163, 221, 26);
		frmSosSender.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(SystemColor.textText);
		textArea.setFont(new Font("Palatino", Font.PLAIN, 15));
		textArea.setBackground(SystemColor.textHighlight);
		textArea.setBounds(27, 263, 553, 149);
		
		JButton btnNewButton = new JButton("SEND");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String temp =String.valueOf(comboBox.getSelectedItem());
				String[] integers = temp.split("-");
				for(String integer: integers)
				{
					id = integer;
					break;
				}
				
				Date date = new Date();				
				SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
				String realDate = String.valueOf(ft.format(date));
				
				SimpleDateFormat ft1 = new SimpleDateFormat("hh:mm:ss");
				String realTime = String.valueOf(ft1.format(date));
				
				String petType = String.valueOf(comboBox_1.getSelectedItem());
				String petNumber = textField.getText();
				char []  validNumbers =petNumber.toCharArray();
				Boolean valid = true;
				

				try 
				{
					if(!(id.isEmpty() || petType.isEmpty() || petNumber.isEmpty()))
					{
						for(char validNumber : validNumbers )
						{
							if(!(Character.isDigit(validNumber)))
							{
								valid = false;
								break;
							}
							
								
						}
						if(valid)
						{
							Runnable run = new Runnable() {
								
								public void run()
								{					
				 					try 
									{
				 						JSONObject sosObj = new JSONObject();
				 						sosObj.put("sosDate", realDate);
				 						sosObj.put("sosTime", realTime);
				 						sosObj.put("petType", petType);
				 						sosObj.put("petNumber", petNumber);
				 						sosObj.put("stationID", id);
				 						sosObj.put("sender", currentUser);
				 						
				 						
				 						String strToSender = String.valueOf(sosObj);
				 						Socket socket = new Socket("127.0.0.1",8081);
				 						DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
				 						dataOutputStream.writeUTF(strToSender);
				 						
				 						DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
				 						String strFromReceiver = dataInputStream.readUTF();
				 						textArea.setText(strFromReceiver);
				 						btnNewButton.setEnabled(false);
				 						
				 						socket.close();
									}catch (JSONException e)
									{
										e.printStackTrace();
									}
				 					catch(IOException ex)
				 					{
				 						textArea.setText("The Server is Closed @ Another SOS arranging now. \n Please click resend until success message showed ");
				 					}
									
								}
								
								};
								Thread thread = new Thread(run);
								thread.start();
							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please key in the correct pet quantity");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please fill in all the blank");
					}
				}
				catch(Exception ex) 
				{
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Palatino", Font.BOLD, 15));
		btnNewButton.setBounds(341, 204, 116, 36);
		frmSosSender.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new userGui(currentUser);
				frmSosSender.dispose();
				
			}
		});
		btnNewButton_1.setFont(new Font("Palatino", Font.BOLD, 15));
		btnNewButton_1.setBounds(469, 204, 122, 36);
		frmSosSender.getContentPane().add(btnNewButton_1);
		

		frmSosSender.getContentPane().add(textArea);
		frmSosSender.setVisible(true);
	}
	
	public void fillStationChoice()
	{
		sosController sc = new sosController();
		
		try 
		{
			jsnArr =sc.getStation();
			
			for(int i=0;i<jsnArr.length();i++)
			{
				JSONObject stationObj = jsnArr.getJSONObject(i);
				System.out.println(stationObj.optString("SID"));
				comboBox.addItem(stationObj.optString("SID") + "-"+stationObj.optString("SAREA")+":"+stationObj.optString("ADDRESS"));	
			}		
			
		}catch(Exception e1)
		{
			e1.printStackTrace();
		}
	
	}
	

}
