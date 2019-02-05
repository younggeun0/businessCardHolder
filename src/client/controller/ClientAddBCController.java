package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import client.view.ClientAddBCView;

public class ClientAddBCController extends WindowAdapter implements ActionListener {

	private ClientAddBCView cabcv;
	
	public ClientAddBCController(ClientAddBCView cabcv) {
		this.cabcv = cabcv;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cabcv.getJbChooseImg()) {
			
		}
		
		if(e.getSource() == cabcv.getJbClose()) {
			cabcv.dispose();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		cabcv.dispose();
	}
}
