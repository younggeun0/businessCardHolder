package client.controller;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import client.view.ClientAddBCView;

public class ClientAddBCController extends WindowAdapter implements ActionListener {

	private ClientAddBCView cabcv;
	private boolean fileSelectFlag;
	private String path;
	
	public ClientAddBCController(ClientAddBCView cabcv) {
		this.cabcv = cabcv;
	}
	
	private void msgCenter(String msg) {
		JOptionPane.showMessageDialog(cabcv, msg);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cabcv.getJbAdd()) {
			JTextField jtf = cabcv.getJtfMemo();
			String memo = "";
			if (jtf.getText().trim().equals("")) {
				msgCenter("memo는 필수입력 항목입니다.");
				return;
			}
			
			memo = jtf.getText().trim();
			
			if (!fileSelectFlag) {
				msgCenter("명함 이미지를 선택해주세요.");
				return;
			}
			
			Socket client = null;
			DataOutputStream dos = null;
			FileInputStream fis = null;
			File file = null;
			int fileSize = 0;
			int fileLen = 0;
			
			try {
				try {
					client = new Socket("localhost", 8000);
					dos = new DataOutputStream(client.getOutputStream());
					file = new File(path);
					
					dos.writeUTF("addBC");
					dos.flush();

					dos.writeUTF(memo);
					System.out.println("file이름? : "+file.getName());
					dos.writeUTF(file.getName());
					
					fis = new FileInputStream(path);
					byte[] readData = new byte[512];
					while((fileLen = fis.read(readData)) != -1) {
						fileSize++;
					}
					
					fis.close();
					
					dos.writeInt(fileSize);
					
					fis = new FileInputStream(path);
					
					while((fileLen = fis.read(readData)) != -1) {
						dos.write(readData, 0, fileLen);
					}
					dos.flush();
					
					msgCenter("서버에 명함데이터가 추가되었습니다.");
					
					cabcv.dispose();
				} finally {
					if (fis != null) { fis.close(); }
					if (dos != null) { dos.close(); }
					if (client != null) { client.close(); }
				}
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == cabcv.getJbChooseImg()) {
			chooseFile();
		}
		
		if(e.getSource() == cabcv.getJbClose()) {
			cabcv.dispose();
		}
	}
	
	private void chooseFile() {
		FileDialog fd = new FileDialog(cabcv, "명함 선택");
		fd.setVisible(true);
		
		String dir = fd.getDirectory();
		String fileName = fd.getFile();
		path = dir+fileName;
		
		cabcv.getJlNameCardImg().setIcon(new ImageIcon(path));

		fileSelectFlag = true;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		cabcv.dispose();
	}
}
