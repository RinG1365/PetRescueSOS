package view;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import controller.sosController;
import model.SOS;

import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.Font;

public class receiveSosGui {

	private JFrame frmSosReceiver;
	public String strFromSender = "";
	static public ServerSocket serverSocket;
	private JButton btnBack;
	private JTextArea textArea;
	private JLabel lblSosDate;
	private JLabel lblSosTime;
	private JLabel lblPetType;
	private JLabel lblPetQuantity;
	private JLabel lblRescuer;
	private JTextField textDate;
	private JTextField textTime;
	private JTextField textSID;
	private JTextField textField_3;
	private JTextField textpQuan;
	private JTextField textpType;
	private JComboBox comboBox;
	private JButton btnAccept;

	public JSONObject jObj;
	public JSONArray jsnArr;
	private JButton btnArrange;
	private JLabel lblNewLabel;
	private JLabel lblSender;
	private JTextField textSender;
	private String id ="";
	public JSONObject getJsnObj() {
		return jObj;
	}

	public void setJsnObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					 new receiveSosGui();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public receiveSosGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSosReceiver = new JFrame();
		frmSosReceiver.setTitle("SOS Receiver");
		frmSosReceiver.setBounds(100, 100, 602, 459);
		frmSosReceiver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSosReceiver.getContentPane().setLayout(null);
		
		textDate = new JTextField();
		textDate.setBounds(128, 180, 163, 26);
		textDate.setEditable(false);
		frmSosReceiver.getContentPane().add(textDate);
		textDate.setColumns(10);
		
		textTime = new JTextField();
		textTime.setBounds(417, 180, 163, 26);
		textTime.setEditable(false);
		frmSosReceiver.getContentPane().add(textTime);
		textTime.setColumns(10);
		
		textSID = new JTextField();
		textSID.setBounds(128, 228, 163, 26);
		textSID.setEditable(false);
		frmSosReceiver.getContentPane().add(textSID);
		textSID.setColumns(10);
		
		textpQuan = new JTextField();
		textpQuan.setBounds(128, 315, 163, 26);
		textpQuan.setEditable(false);
		frmSosReceiver.getContentPane().add(textpQuan);
		textpQuan.setColumns(10);
		
		JButton btnNewButton = new JButton("Open");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				textArea.setText("Wait for SOS...");
				 try
				 {
					 ServerSocket serverSocket = new ServerSocket(8081);
					 
						while(true)
						{
							
							Socket socket = serverSocket.accept();
							DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
							strFromSender = dataInputStream.readUTF();
							
							
							System.out.println(strFromSender);
						
							textArea.setText("SOS WANTED!!Click Accept to arrange the SOS");
						
							JSONObject jGet = new JSONObject(strFromSender);
							setJsnObj(jGet);
							
							btnNewButton.setEnabled(false);
							frmSosReceiver.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
							btnBack.setEnabled(false);
							btnAccept.setEnabled(true);
							
							String strToSender = "SOS Accept";
							DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
							dataOutputStream.writeUTF(strToSender);
								
							serverSocket.close();
						}		
					
						
					}
						catch(UnknownHostException ue)
						{
							ue.printStackTrace();
						}
				 		catch(JSONException je)
				 		{
				 			je.printStackTrace();
				 		}
						catch(IOException ex)
						{
							ex.printStackTrace();
						}
			}
		});
		btnNewButton.setBounds(334, 11, 117, 29);
		frmSosReceiver.getContentPane().add(btnNewButton);
		
		btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new adminGui();
				frmSosReceiver.dispose();
			}
		});
		btnBack.setBounds(463, 11, 117, 29);
		frmSosReceiver.getContentPane().add(btnBack);
		
		textArea = new JTextArea();
		textArea.setBackground(SystemColor.textHighlight);
		textArea.setBounds(18, 45, 564, 84);
		frmSosReceiver.getContentPane().add(textArea);
		
		lblSosDate = new JLabel("SOS Date:");
		lblSosDate.setFont(new Font("Palatino", Font.BOLD, 15));
		lblSosDate.setBounds(18, 186, 78, 16);
		frmSosReceiver.getContentPane().add(lblSosDate);
		
		lblSosTime = new JLabel("SOS Time:");
		lblSosTime.setFont(new Font("Palatino", Font.BOLD, 15));
		lblSosTime.setBounds(327, 186, 78, 16);
		frmSosReceiver.getContentPane().add(lblSosTime);
		
		lblPetType = new JLabel("Pet Type:");
		lblPetType.setFont(new Font("Palatino", Font.BOLD, 15));
		lblPetType.setBounds(18, 279, 78, 16);
		frmSosReceiver.getContentPane().add(lblPetType);
		
		lblPetQuantity = new JLabel("Pet Quantity:");
		lblPetQuantity.setFont(new Font("Palatino", Font.BOLD, 15));
		lblPetQuantity.setBounds(18, 321, 103, 16);
		frmSosReceiver.getContentPane().add(lblPetQuantity);
		
		lblRescuer = new JLabel("Rescuer:");
		lblRescuer.setFont(new Font("Palatino", Font.BOLD, 15));
		lblRescuer.setBounds(18, 364, 78, 16);
		frmSosReceiver.getContentPane().add(lblRescuer);
		
		textpType = new JTextField();
		textpType.setBounds(128, 273, 163, 26);
		textpType.setEditable(false);
		frmSosReceiver.getContentPane().add(textpType);
		textpType.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(128, 359, 163, 27);
		frmSosReceiver.getContentPane().add(comboBox);
		
		fillRescuer();
		
		btnAccept = new JButton("ACCEPT");
		btnAccept.setEnabled(false);
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println(jObj.optString("sosDate"));
				textDate.setText(jObj.optString("sosDate"));
				textTime.setText(jObj.optString("sosTime"));
				textSID.setText(jObj.optString("stationID"));
				textSender.setText(jObj.optString("sender"));
				textpType.setText(jObj.optString("petType"));
				textpQuan.setText(jObj.optString("petNumber"));
				
				
				btnAccept.setEnabled(false);
				btnArrange.setEnabled(true);
			}
		});
		btnAccept.setBounds(436, 141, 144, 28);
		frmSosReceiver.getContentPane().add(btnAccept);
		
		btnArrange = new JButton("ARRANGE");
		btnArrange.setEnabled(false);
		btnArrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Runnable run = new Runnable() {
				
				public void run()
				{
					String temp =String.valueOf(comboBox.getSelectedItem());
					String[] integers = temp.split("-");
					for(String integer: integers)
					{
						id = integer;
						break;
					}
					String addStatus = "WAIT";
					String addDate = textDate.getText();
					String addTime = textTime.getText();
					String addSid = textSID.getText();
					String addSender = textSender.getText();
					String addpType = textpType.getText();
					String addpQuan = textpQuan.getText();
					
					SOS sos = new SOS();
					sos.setSOSDate(addDate);
					sos.setSOSTime(addTime);
					sos.setSid(addSid);
					sos.setSender(addSender);
					sos.setpType(addpType);
					sos.setpQuan(addpQuan);
					sos.setRid(id);
					sos.setStatus(addStatus);
					
					sosController sc = new sosController();
					try 
					{
						String success ="";
						success = sc.addSOS(sos);
						
						if(success.equals("1"))
						{
							JOptionPane.showMessageDialog(null, "SOS Arranged");
							textDate.setText("");
							textTime.setText("");
							textSID.setText("");
							textSender.setText("");
							textpType.setText("");
							textpQuan.setText("");
							textArea.setText("Click Open To Receive SOS");
							
							btnArrange.setEnabled(false);
							btnAccept.setEnabled(false);
							btnNewButton.setEnabled(true);
							btnBack.setEnabled(true);
							frmSosReceiver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Invalid Username or Password");
						}
						
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
					
				};
				
				Thread tAddSOS = new Thread (run);
				tAddSOS.start();
					
			
			}
		});
		btnArrange.setBounds(436, 358, 144, 29);
		frmSosReceiver.getContentPane().add(btnArrange);
		
		lblNewLabel = new JLabel("Station ID:");
		lblNewLabel.setFont(new Font("Palatino", Font.BOLD, 15));
		lblNewLabel.setBounds(18, 234, 103, 16);
		frmSosReceiver.getContentPane().add(lblNewLabel);
		
		lblSender = new JLabel("Sender:");
		lblSender.setFont(new Font("Palatino", Font.BOLD, 15));
		lblSender.setBounds(327, 234, 68, 16);
		frmSosReceiver.getContentPane().add(lblSender);
		
		textSender = new JTextField();
		textSender.setBounds(417, 228, 163, 26);
		textSender.setEditable(false);
		frmSosReceiver.getContentPane().add(textSender);
		textSender.setColumns(10);
		frmSosReceiver.setVisible(true);
		
		}
	
	public void fillRescuer()
	{
		sosController sc = new sosController();
		
		try 
		{
			jsnArr =sc.getRescuer();
			
			for(int i=0;i<jsnArr.length();i++)
			{
				JSONObject rescuerObj = jsnArr.getJSONObject(i);
				comboBox.addItem(rescuerObj.optString("RID") + "-" +rescuerObj.optString("RNAME"));	
			}		
			
		}catch(Exception e1)
		{
			e1.printStackTrace();
		}
	
	}
	
}
