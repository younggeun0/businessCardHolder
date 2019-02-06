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
				smv.getDlmRequest().addElement("서버구동시작...");

				while (true) {
					client = server.accept();

					dis = new DataInputStream(client.getInputStream());
					dos = new DataOutputStream(client.getOutputStream());

					// 클라이언트가 요청을 뭘하지?
					// 일단 파일명을 보낼테고, 파일배열의 크기를 보낼테고, 데이터를 수만큼 보낼 것
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
							smv.getDlmRequest().addElement("새로운 명함데이터가 추가되었습니다.");
						} catch (SQLException e) {
							e.printStackTrace();
						}
						// 일단 클라이언트가 이미지를 등록, 등록하면 서버에서 스레드가 연결해서
						// 파일을 읽어들여서 저장하고, DB에 등록
						
						// 클라이언트가 보기를 하면 서버에서 클라이언트에게 파일을 보냄
						// 이미 존재하지 않는 파일만 보냄
						//
					}
					
					if (flag.equals("showBC")) {
						// 데이터 목록을 DB에서 가져와서 리스트에 저장
						// 클라이언트에게 객체화를 통해 전달
						List<TableDataVO> list;
						try {
							list = s_dao.selectAll();
							oos = new ObjectOutputStream(client.getOutputStream());
							
							oos.writeObject(list);
							oos.flush();
							
							smv.getDlmRequest().addElement("명함리스트를 전송했습니다.");
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					
					if (flag.equals("showDetail")) {
						ois = new ObjectInputStream(client.getInputStream());
						
						SelectedRowVO srvo = (SelectedRowVO)ois.readObject();
						
						DetailBCVO dbcvo = s_dao.selectDetail(srvo);
						System.out.println("클라가보낸 객체로 조회한 디테일VO내용"+dbcvo);
						
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
						
						// DB에서 시간과 메모가 동일한 데이터를 찾아서 파일명까지 클라이언트에 전달
						// 이미지 파일이 클라이언트 측에 존재하는지 우선 파악해야 함
						// 파악하고 전달..아오
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
