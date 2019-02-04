package client.view;

import javax.swing.JFrame;

import client.controller.ClientMainController;

public class ClientMainView extends JFrame {

	public ClientMainView() {

		
		
		ClientMainController cmc = new ClientMainController(this);
		addWindowListener(cmc);
		
		setBounds(300, 400, 500, 300);
		setVisible(true);
	}
}
