package client.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import client.view.ClientShowBCView;
import client.vo.SelectedRowVO;
import server.vo.TableDataVO;

public class ClientShowBCController extends WindowAdapter implements MouseListener {

	private ClientShowBCView csbcv;

	public ClientShowBCController(ClientShowBCView csbcv) {
		this.csbcv = csbcv;
		try {
			setList();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void setList() throws ClassNotFoundException {

		Socket client = null;
		DataOutputStream dos = null;
		ObjectInputStream ois = null;
		try {
			try {
				client = new Socket("localhost", 8000);
				dos = new DataOutputStream(client.getOutputStream());

				dos.writeUTF("showBC");
				dos.flush();

				ois = new ObjectInputStream(client.getInputStream());
				List<TableDataVO> list = (List<TableDataVO>) ois.readObject();

				DefaultTableModel dtm = csbcv.getDtmBC();
				dtm.setRowCount(0);

				String[] rowData = new String[2];
				TableDataVO tdvo = null;
				for (int i = 0; i < list.size(); i++) {
					tdvo = list.get(i);
					rowData[0] = tdvo.getInputDate();
					rowData[1] = tdvo.getMemo();

					dtm.addRow(rowData);
				}
			} finally {
				if (ois != null) {
					ois.close();
				}
				if (dos != null) {
					dos.close();
				}
				if (client != null) {
					client.close();
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		csbcv.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == csbcv.getJtBC()) {
			if (e.getClickCount() == 2) {
				// detailVO에 담아서 서버에 전송, 서버가 판별한 후 결과를 VO로 전송, 보여줌
				int selectedRow = csbcv.getJtBC().getSelectedRow();

				SelectedRowVO srvo = new SelectedRowVO((String) csbcv.getJtBC().getValueAt(selectedRow, 0),
						(String) csbcv.getJtBC().getValueAt(selectedRow, 1));

				Socket client = null;
				DataOutputStream dos = null;
				ObjectOutputStream oos = null;
				try {
					try {
						
						client = new Socket("localhost", 8000);
						dos = new DataOutputStream(client.getOutputStream());
						
						dos.writeUTF("showDetail");
						dos.flush();
						
						oos = new ObjectOutputStream(client.getOutputStream());
						oos.writeObject(srvo);
						oos.flush();
						

					} finally {
						if (client != null) {
							client.close();
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
