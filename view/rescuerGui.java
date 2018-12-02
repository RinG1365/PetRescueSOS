package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import controller.rescuerController;
import controller.stationController;
import model.Rescuer;
import model.Station;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class rescuerGui {

	private JFrame frmRescuer;
	public DefaultTableModel model = new DefaultTableModel();
	public JSONArray jsnArr;
	public Vector<String> columns = new Vector<>();
	public Vector<String> rows = new Vector<>();
	private String clickID="";
	private JTable table;

	public void getAllRescuer()
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
		rescuerController rc = new rescuerController();
		
		while (model.getRowCount()>0) {
			model.removeRow(0);
		}
		
		try 
		{
			jsnArr= rc.getAllRescuer();
		
		
			for(int i=0;i<jsnArr.length();i++)
			{
				rows = new Vector<>();
				JSONObject result = jsnArr.getJSONObject(i);
				rows.addElement(result.optString("RID"));
				rows.addElement(result.optString("RNAME"));
				rows.addElement(result.optString("GENDER"));
				rows.addElement(result.optString("PHONENO"));
				
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
		columns.add("NAME");
		columns.add("GENDER");
		columns.add("PHONE NO");

		
		model.setColumnIdentifiers(columns);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new rescuerGui();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public rescuerGui() {
		initialize();
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRescuer = new JFrame();
		frmRescuer.setTitle("Rescuer");
		frmRescuer.setBounds(100, 100, 541, 418);
		frmRescuer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRescuer.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225));
		panel.setBounds(131, 11, 160, 33);
		frmRescuer.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRescuer = new JLabel("Rescuer");
		lblRescuer.setBounds(37, 0, 94, 33);
		panel.add(lblRescuer);
		lblRescuer.setFont(new Font("Palatino Linotype", Font.PLAIN, 24));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new addRescuer();
				frmRescuer.dispose();
			
			}
		});
		btnAdd.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		btnAdd.setBounds(432, 60, 103, 29);
		frmRescuer.getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Runnable run = new Runnable() {
					
				public void run() 
				{
							rescuerController rc = new rescuerController();
							Rescuer rescuer = new Rescuer();
							rescuer.setRid(clickID);
							System.out.println(clickID);
								
								try {
									String success;
									success = rc.deleteRescuer(rescuer);
									if(success.equals("1"))
									{
										JOptionPane.showMessageDialog(null, "Station deleted");
										clickID="";
										btnDelete.setEnabled(false);
										getAllRescuer();
									}
									else 
									{
										JOptionPane.showMessageDialog(null, "Item failed to delete(involved in transaction)");
									}
									
								}catch(Exception t) {
									t.printStackTrace();
								}
				}
				};
				Thread tdeleteRescuer = new Thread(run);
				tdeleteRescuer.start();
												
			}
		});
		btnDelete.setFont(new Font("Palatino Linotype", Font.PLAIN, 13));
		btnDelete.setBounds(432, 101, 103, 33);
		frmRescuer.getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 66, 401, 307);
		frmRescuer.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
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
					btnDelete.setEnabled(true);
				}	
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new adminGui();
				frmRescuer.dispose();
			}
		});
		btnBack.setBounds(424, 146, 117, 29);
		frmRescuer.getContentPane().add(btnBack);
		setColumnIdentifier();
		getAllRescuer();
		frmRescuer.setVisible(true);
	}
	

}
