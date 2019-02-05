package client.view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import client.controller.ClientShowBCController;

public class ClientShowBCView extends JDialog {
	
	private JTable jtBC;
	private DefaultTableModel dtmBC;
	private JScrollPane jspBC;
	
	public ClientShowBCView() {
		
		String[] columnNames = {"등록일", "명함메모"};
		dtmBC = new DefaultTableModel(columnNames, 3);
		jtBC = new JTable(dtmBC);
		jspBC = new JScrollPane(jtBC);
		
		add(jspBC);
		
		ClientShowBCController csbcc = new ClientShowBCController(this);
		jtBC.addMouseListener(csbcc);
		addWindowListener(csbcc);
		
		setBounds(400, 300, 500, 400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ClientShowBCView();
	}
}
