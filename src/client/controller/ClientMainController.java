package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import client.view.ClientMainView;

public class ClientMainController extends WindowAdapter implements ActionListener {

	private ClientMainView cmv;
	
	public ClientMainController(ClientMainView cmv) {
		this.cmv = cmv;
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		System.exit(0);
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		cmv.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
