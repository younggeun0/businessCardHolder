package client.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import client.controller.ClientAddBCController;

public class ClientAddBCView extends JDialog {

	JLabel jlNameCardImg;
	JTextField jtfMemo;
	JButton jbChooseImg, jbAdd, jbClose;
	
	public ClientAddBCView(ClientMainView cmv) {
		super(cmv,"상세명함정보",true);
		
		jlNameCardImg = new JLabel(new ImageIcon("D:/git/repositories/businessCardHolder/src/client/img/no_img.jpg"));
		jtfMemo = new JTextField();
		jbChooseImg = new JButton("사진선택");
		jbClose = new JButton("닫기");
		jbAdd = new JButton("등록");

		JLabel jlMemo = new JLabel("명함메모");
		
		setLayout(null);
		
		jlNameCardImg.setBounds(20, 20, 460, 260);
		jlMemo.setBounds(70, 300, 60, 30);
		jtfMemo.setBounds(130, 300, 240, 30);
		jbChooseImg.setBounds(380, 300, 100, 30);
		jbAdd.setBounds(160, 350, 100, 30);
		jbClose.setBounds(270, 350, 100, 30);
		
		add(jbAdd);
		add(jlNameCardImg);
		add(jlMemo);
		add(jtfMemo);
		add(jbChooseImg);
		add(jbClose);
		
		ClientAddBCController cabcc = new ClientAddBCController(this);
		jbAdd.addActionListener(cabcc);
		jbChooseImg.addActionListener(cabcc);
		jbClose.addActionListener(cabcc);
		
		addWindowListener(cabcc);
		
//		setBounds(400, 300, 520, 450);
		setBounds(cmv.getY()+50, cmv.getY()+50, 520, 450);
		setVisible(true);
	}
	public JButton getJbAdd() {
		return jbAdd;
	}
	public JLabel getJlNameCardImg() {
		return jlNameCardImg;
	}
	public JTextField getJtfMemo() {
		return jtfMemo;
	}
	public JButton getJbClose() {
		return jbClose;
	}
	public JButton getJbChooseImg() {
		return jbChooseImg;
	}
	/*public static void main(String[] args) {
		new ClientAddBCView(null);
	}*/
}

