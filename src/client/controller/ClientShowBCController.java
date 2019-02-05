package client.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import client.view.ClientShowBCView;

public class ClientShowBCController extends WindowAdapter implements MouseListener {

	private ClientShowBCView csbcv;
	
	public ClientShowBCController(ClientShowBCView csbcv) {
		this.csbcv = csbcv; 
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		csbcv.dispose();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
