package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import client.view.ClientAddBCView;
import client.view.ClientMainView;
import client.view.ClientShowBCView;

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
		if (e.getSource() == cmv.getJbAddBC()) { // 명함등록
			new ClientAddBCView(cmv);
		}
		
		if (e.getSource() == cmv.getJbShowBC()) { // 등록한 명함보기
			new ClientShowBCView(cmv);
		}
		
	}
}
