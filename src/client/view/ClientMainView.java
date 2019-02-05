package client.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import client.controller.ClientMainController;

public class ClientMainView extends JFrame {

	JButton jbAddBC, jbShowBC;
	
	public ClientMainView() {
		super("명함첩");

		jbAddBC = new JButton("명함등록");
		jbShowBC = new JButton("등록한 명함보기");
		
		setLayout(null);
		
		jbAddBC.setBounds(20, 20, 260, 60);
		jbShowBC.setBounds(20, 100, 260, 60);
		
		add(jbAddBC);
		add(jbShowBC);
		
		ClientMainController cmc = new ClientMainController(this);

		addWindowListener(cmc);
		
		setBounds(300, 400, 320, 230);
		setResizable(false);
		setVisible(true);
	}
	public JButton getJbAddBC() {
		return jbAddBC;
	}
	public JButton getJbShowBC() {
		return jbShowBC;
	}
}
