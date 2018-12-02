package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import controller.historyController;
import controller.stationController;
import model.History;

import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class historyGui {

	private JFrame frmHistory;
	public DefaultTableModel model = new DefaultTableModel();
	public JSONArray jsnArr;
	public Vector<String> columns = new Vector<>();
	public Vector<String> rows = new Vector<>();
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnBack;
	private JButton btnTaken;
	private JButton btnWait;
	private String clickID="";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new historyGui();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public historyGui() {
		initialize();
	}
	
	public JSONArray getJsnArr() {
		return jsnArr;
	}

	public void setJsnArr(JSONArray jsnArr) {
		this.jsnArr = jsnArr;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHistory = new JFrame();
		frmHistory.setTitle("SOS History");
		frmHistory.setBounds(100, 100, 800, 545);
		frmHistory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHistory.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225));
		panel.setBounds(197, 6, 329, 38);
		frmHistory.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblHistory = new JLabel("SOS History");
		lblHistory.setBounds(85, 0, 151, 38);
		panel.add(lblHistory);
		lblHistory.setFont(new Font("Palatino Linotype", Font.PLAIN, 24));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 68, 640, 343);
		frmHistory.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (e.getClickCount()==1)
				{	
					
					// Retrieve the item id from certain row selected by user
					clickID = table.getModel().getValueAt(table.getSelectedRow(),0).toString();
					
					// Enable user click on delete button 
					// because system get the selected item id already
					btnTaken.setEnabled(true);
					btnWait.setEnabled(true);
				}	
			}
		});
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new adminGui();
				frmHistory.dispose();
			}
		});
		btnBack.setBounds(678, 184, 117, 46);
		frmHistory.getContentPane().add(btnBack);
		
		btnTaken = new JButton("Taken");
		btnTaken.setEnabled(false);
		btnTaken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Runnable run = new Runnable() {
				
					public void run()
					{
						historyController hc = new historyController();
						History history = new History();
						history.setSosId(clickID);
						
						try 
						{
							String success ="";
							success = hc.updateTaken(history);
							
							if(success.equals("1"))
							{
								JOptionPane.showMessageDialog(null, "SOS Status Updated");
								clickID="";
								btnTaken.setEnabled(false);
								btnWait.setEnabled(false);
								getAllHistory();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "SOS Status Fail to Updated");
							}
						}catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				};
				Thread tupdateTaken = new Thread(run);
				tupdateTaken.start();
			}
		});
		btnTaken.setBounds(678, 68, 117, 46);
		frmHistory.getContentPane().add(btnTaken);
		
		btnWait = new JButton("Waiting");
		btnWait.setEnabled(false);
		btnWait.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Runnable run = new Runnable() {
					
					public void run()
					{
						historyController hc = new historyController();
						History history = new History();
						history.setSosId(clickID);
						
						try 
						{
							String success ="";
							success = hc.updateWait(history);
							
							if(success.equals("1"))
							{
								JOptionPane.showMessageDialog(null, "SOS Status Updated");
								clickID="";
								btnTaken.setEnabled(false);
								btnWait.setEnabled(false);
								getAllHistory();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "SOS Status Fail to Updated");
							}
						}catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				};
				Thread tupdateWait = new Thread(run);
				tupdateWait.start();
			}
		});
		btnWait.setBounds(678, 126, 117, 46);
		frmHistory.getContentPane().add(btnWait);
		
		setColumnIdentifier();
		getAllHistory();
		frmHistory.setVisible(true);
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
				jsnArr= hc.getAllHistory();
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

