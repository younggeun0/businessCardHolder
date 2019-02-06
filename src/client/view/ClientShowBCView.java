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
		super(cmv, "���Ժ���", true);
		
		String[] columnNames = {"���Թ�ȣ","�����", "���Ը޸�"};
		dtmBC = new DefaultTableModel(columnNames, 3);
		jtBC = new JTable(dtmBC) {
			private static final long serialVersionUID = 1L;

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
