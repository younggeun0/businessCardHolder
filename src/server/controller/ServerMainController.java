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
				
				// 클라이언트가 요청을 뭘하지?
				// 일단 파일명을 보낼테고, 파일배열의 크기를 보낼테고, 데이터를 수만큼 보낼 것
				fileName = dis.readUTF();
				
				fileSize = dis.readInt();
				
				for(int i=0; i<fileSize; i++) {
					
				}
				// 일단 클라이언트가 이미지를 등록, 등록하면 서버에서 스레드가 연결해서
				// 파일을 읽어들여서 저장하고, DB에 등록
				
				// 클라이언트가 보기를 하면 서버에서 클라이언트에게 파일을 보냄
				// 이미 존재하지 않는 파일만 보냄
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
