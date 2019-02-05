package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import client.view.ClientDetailBCView;

public class ClientDetailBCController extends WindowAdapter implements ActionListener {
	
	private ClientDetailBCView cdbcv;
	
	public ClientDetailBCController(ClientDetailBCView cdbcv) {
		this.cdbcv = cdbcv;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		cdbcv.dispose();
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		cdbcv.dispose();
	}
}
