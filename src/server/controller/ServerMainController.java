package server.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.vo.SelectedRowVO;
import server.dao.ServerDAO;
import server.view.ServerMainView;
import server.vo.DetailBCVO;
import server.vo.InsertVO;
import server.vo.TableDataVO;

public class ServerMainController extends WindowAdapter implements ActionListener, Runnable {

	private ServerMainView smv;
	private Thread threadServer;
	private ServerDAO s_dao;

	public ServerMainController(ServerMainView smv) {
		this.smv = smv;
		threadServer = new Thread(this);
		s_dao = ServerDAO.getInstance();
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
		FileOutputStream fos = null;
		FileInputStream fis = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		String memo = "";
		String flag = "";
		String fileName = "";
		int fileLen = 0;
		int fileSize = 0;
		byte[] readData = null;
		
		InsertVO ivo = null;
		
		try {

			try {
				server = new ServerSocket(8000);
				smv.getDlmRequest().addElement("������������...");

				while (true) {
					client = server.accept();

					dis = new DataInputStream(client.getInputStream());
					dos = new DataOutputStream(client.getOutputStream());

					// Ŭ���̾�Ʈ�� ��û�� ������?
					// �ϴ� ���ϸ��� �����װ�, ���Ϲ迭�� ũ�⸦ �����װ�, �����͸� ����ŭ ���� ��
					flag = dis.readUTF();

					if (flag.equals("addBC")) {
						
						memo = dis.readUTF();
						fileName = dis.readUTF();

						fileSize = dis.readInt();
						
						fos = new FileOutputStream("D:/git/repositories/businessCardHolder/src/server/img/"+fileName);
						
						readData = new byte[512];
						while(fileSize > 0) {
							fileLen = dis.read(readData);
							fos.write(readData, 0, fileLen);
							fos.flush();
							fileSize--;
						}
						
						ivo = new InsertVO(memo, fileName);
						try {
							s_dao.insertBC(ivo);
							smv.getDlmRequest().addElement("���ο� ���Ե����Ͱ� �߰��Ǿ����ϴ�.");
						} catch (SQLException e) {
							e.printStackTrace();
						}
						// �ϴ� Ŭ���̾�Ʈ�� �̹����� ���, ����ϸ� �������� �����尡 �����ؼ�
						// ������ �о�鿩�� �����ϰ�, DB�� ���
						
						// Ŭ���̾�Ʈ�� ���⸦ �ϸ� �������� Ŭ���̾�Ʈ���� ������ ����
						// �̹� �������� �ʴ� ���ϸ� ����
						//
					}
					
					if (flag.equals("showBC")) {
						// ������ ����� DB���� �����ͼ� ����Ʈ�� ����
						// Ŭ���̾�Ʈ���� ��üȭ�� ���� ����
						List<TableDataVO> list;
						try {
							list = s_dao.selectAll();
							oos = new ObjectOutputStream(client.getOutputStream());
							
							oos.writeObject(list);
							oos.flush();
							
							smv.getDlmRequest().addElement("���Ը���Ʈ�� �����߽��ϴ�.");
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					
					if (flag.equals("showDetail")) {
						ois = new ObjectInputStream(client.getInputStream());
						
						SelectedRowVO srvo = (SelectedRowVO)ois.readObject();
						
						DetailBCVO dbcvo = s_dao.selectDetail(srvo);
						System.out.println("Ŭ�󰡺��� ��ü�� ��ȸ�� ������VO����"+dbcvo);
						
						fis = new FileInputStream(new File("D:/git/repositories/businessCardHolder/src/server/img/"+dbcvo.getFileName()));
						
						dos.writeUTF(dbcvo.getFileName());
						dos.flush();
						
						String existFlag = dis.readUTF();
						
						if (!existFlag.equals("exist")) {
							readData = new byte[512];
							fileSize = 0;
							fileLen = 0;
							
							while((fileLen = fis.read(readData)) != -1) {
								fileSize++;
							}
							
							fis.close();
							
							dos.writeInt(fileSize);
							dos.flush();
							
							
							fis = new FileInputStream(new File("D:/git/repositories/businessCardHolder/src/server/img/"+dbcvo.getFileName()));
							
							while((fileLen = fis.read(readData)) != -1) {
								dos.write(readData, 0, fileLen);
							}
							dos.flush();
						}
						
						// DB���� �ð��� �޸� ������ �����͸� ã�Ƽ� ���ϸ���� Ŭ���̾�Ʈ�� ����
						// �̹��� ������ Ŭ���̾�Ʈ ���� �����ϴ��� �켱 �ľ��ؾ� ��
						// �ľ��ϰ� ����..�ƿ�
					}

				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (ois != null) { ois.close(); }
				if (oos != null) { oos.close(); }
				if (dis != null) { dis.close(); }
				if (dos != null) { dos.close(); }
				if (fos != null) { fos.close(); }
				if (client != null) { client.close(); }
				if (server != null) { server.close(); }
			}
		} catch (IOException e) {
			e.printStackTrace();
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
