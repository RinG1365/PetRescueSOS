package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

import view.userGui;
import controller.historyController;

public class userSosHistory {

	private JFrame frmSosHistory;
	static String currentUser;
	private JTable table;
	public DefaultTableModel model = new DefaultTableModel();
	public JSONArray jsnArr;
	public Vector<String> columns = new Vector<>();
	public Vector<String> rows = new Vector<>();
	private String clickID="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new userSosHistory(currentUser);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public userSosHistory(String username) {
		currentUser = username;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSosHistory = new JFrame();
		frmSosHistory.setTitle("SOS History");
		frmSosHistory.setBounds(100, 100, 755, 499);
		frmSosHistory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSosHistory.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225));
		panel.setBounds(255, 24, 247, 33);
		frmSosHistory.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblSosHistory = new JLabel("SOS History");
		lblSosHistory.setBounds(70, 0, 113, 33);
		panel.add(lblSosHistory);
		lblSosHistory.setFont(new Font("Palatino", Font.BOLD, 18));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 99, 707, 333);
		frmSosHistory.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new userGui(currentUser);
				frmSosHistory.dispose();
			}
		});
		btnBack.setBounds(586, 57, 128, 33);
		frmSosHistory.getContentPane().add(btnBack);
		setColumnIdentifier();
		getAllHistory();
		frmSosHistory.setVisible(true);
	}

	public void getAllHistory()
	{
		try {
			
		model = viewTable();
		table.setModel(model);
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	public DefaultTableModel viewTable() throws Exception
	{	
		historyController hc = new historyController();
		
		while (model.getRowCount()>0) {
			model.removeRow(0);
		}
		
		try {
				jsnArr= hc.getUserHistory(currentUser);
				int i = 0;
				
				for(i=0;i<jsnArr.length();i++)
				{
					rows = new Vector<>();
					JSONObject result = jsnArr.getJSONObject(i);
					rows.addElement(result.optString("SOSID"));
					rows.addElement(result.optString("SOSDATE"));
					rows.addElement(result.optString("SOSTIME"));
					rows.addElement(result.optString("PTYPE"));
					rows.addElement(result.optString("PQUAN"));
					rows.addElement(result.optString("USERNAME"));
					rows.addElement(result.optString("RID"));
					rows.addElement(result.optString("SID"));
					rows.addElement(result.optString("SOSSTATUS"));
					
					
					
					
					System.out.println(rows);
					model.addRow(rows);
				}
				
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	public void setColumnIdentifier()
	{

		columns.add("SOS ID");
		columns.add("DATE");
		columns.add("TIME");
		columns.add("PET TYPE");
		columns.add("QUANTITY");
		columns.add("USERNAME");
		columns.add("RESCUER ID");
		columns.add("STATION ID");
		columns.add("STATUS");

		
		model.setColumnIdentifiers(columns);
	}
}
