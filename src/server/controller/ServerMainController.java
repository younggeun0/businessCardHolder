package server.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import server.view.ServerMainView;

public class ServerMainController extends WindowAdapter implements ActionListener {
	
	private ServerMainView smv;
	
	public ServerMainController(ServerMainView smv) {
		this.smv = smv;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		smv.dispose();
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		System.exit(0);
	}
}
