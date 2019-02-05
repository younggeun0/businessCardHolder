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
	
	public ClientShowBCView(ClientMainView cmv) {
		super(cmv, "명함보기", true);
		
		String[] columnNames = {"등록일", "명함메모"};
		dtmBC = new DefaultTableModel(columnNames, 3);
		jtBC = new JTable(dtmBC) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jspBC = new JScrollPane(jtBC);
		
		add(jspBC);
		
		ClientShowBCController csbcc = new ClientShowBCController(this);
		jtBC.addMouseListener(csbcc);
		addWindowListener(csbcc);
		
		setBounds(400, 300, 500, 400);
		setVisible(true);
	}
	public JTable getJtBC() {
		return jtBC;
	}
	public DefaultTableModel getDtmBC() {
		return dtmBC;
	}
	public JScrollPane getJspBC() {
		return jspBC;
	}
}
