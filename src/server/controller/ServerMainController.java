package server.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import server.view.ServerMainView;

public class ServerMainController extends WindowAdapter implements ActionListener,Runnable {
	
	private ServerMainView smv;
	private Thread threadServer;
	
	public ServerMainController(ServerMainView smv) {
		this.smv = smv;
		threadServer = new Thread(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == smv.getJbRunServer()) {
			threadServer.start();
		}
		
		if (e.getSource() == smv.getJbClose()) {
			smv.dispose();
		}
	}

	@Override
	public void run() {
		
		ServerSocket server = null;
		Socket client = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		
		String fileName = "";
		int fileSize = 0;
		try {
			server = new ServerSocket(8000);
			
			while(true) {
				client = server.accept();
				
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				
				// Ŭ���̾�Ʈ�� ��û�� ������?
				// �ϴ� ���ϸ��� �����װ�, ���Ϲ迭�� ũ�⸦ �����װ�, �����͸� ����ŭ ���� ��
				fileName = dis.readUTF();
				
				fileSize = dis.readInt();
				
				for(int i=0; i<fileSize; i++) {
					
				}
				// �ϴ� Ŭ���̾�Ʈ�� �̹����� ���, ����ϸ� �������� �����尡 �����ؼ�
				// ������ �о�鿩�� �����ϰ�, DB�� ���
				
				// Ŭ���̾�Ʈ�� ���⸦ �ϸ� �������� Ŭ���̾�Ʈ���� ������ ����
				// �̹� �������� �ʴ� ���ϸ� ����
				//
			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (server != null) { 
					server.close();
				}
				if (client != null) {
					client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
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
