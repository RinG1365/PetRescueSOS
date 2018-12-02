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

import controller.rescuerController;
import controller.stationController;
import model.Station;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class stationGui {

	private JFrame frmStation;
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
					new stationGui();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public stationGui() {
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
		frmStation = new JFrame();
		frmStation.setTitle("Station");
		frmStation.setBounds(100, 100, 534, 351);
		frmStation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStation.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225));
		panel.setBounds(115, 14, 240, 33);
		frmStation.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblStationList = new JLabel("Station List");
		lblStationList.setBounds(46, 0, 154, 33);
		panel.add(lblStationList);
		lblStationList.setFont(new Font("Palatino Linotype", Font.PLAIN, 24));
		
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new addStationGui();
				frmStation.dispose();
			
			}
		});
		btnAdd.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		btnAdd.setBounds(370, 59, 110, 38);
		frmStation.getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Runnable run = new Runnable() {
					
				public void run()
				{
					stationController sc = new stationController();
					Station station = new Station();
					station.setStationID(clickID);
						
						try {
							String success;
							success = sc.deleteStation(station);
							if(success.equals("1"))
							{
								JOptionPane.showMessageDialog(null, "Station deleted");
								clickID="";
								btnDelete.setEnabled(false);
								getAllStation();
							}
							else 
							{
								JOptionPane.showMessageDialog(null, "Station failed to delete(involved in SOS History)");
							}
							
						}catch(Exception t) {
							t.printStackTrace();
						}
				}	
				};
				Thread tdeleteStation = new Thread(run);
				tdeleteStation.start();
						
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		btnDelete.setBounds(370, 109, 110, 38);
		btnDelete.setEnabled(false);
		frmStation.getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 59, 335, 240);
		frmStation.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new adminGui();
				frmStation.dispose();
			}
		});
		btnBack.setBounds(370, 158, 110, 38);
		frmStation.getContentPane().add(btnBack);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// If user click the mouse twice at certain row in table_1
				if (e.getClickCount()==1)
				{	
					
					// Retrieve the item id from certain row selected by user
					clickID = table.getModel().getValueAt(table.getSelectedRow(),0).toString();
					
					// Enable user click on delete button 
					// because system get the selected item id already
					btnDelete.setEnabled(true);
				}	
			}
		});
		setColumnIdentifier();
		getAllStation();
		frmStation.setVisible(true);
	}
	
	public void getAllStation()
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
		stationController sc = new stationController();
		
		while (model.getRowCount()>0) {
			model.removeRow(0);
		}
		
		try {
				jsnArr= sc.getAllStation();
				int i = 0;
				
				for(i=0;i<jsnArr.length();i++)
				{
					rows = new Vector<>();
					JSONObject result = jsnArr.getJSONObject(i);
					rows.addElement(result.optString("SID"));
					rows.addElement(result.optString("SAREA"));
					rows.addElement(result.optString("ADDRESS"));
					
					
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

		columns.add("ID");
		columns.add("AREA");
		columns.add("ADDRESS");

		
		model.setColumnIdentifiers(columns);
	}

}
