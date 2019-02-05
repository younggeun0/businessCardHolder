package client.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import client.controller.ClientDetailBCController;

public class ClientDetailBCView extends JDialog {

	JLabel jlNameCardImg;
	JTextField jtfMemo;
	JButton jbClose;
	
	public ClientDetailBCView(ClientMainView cmv) {
		super(cmv,"상세명함정보",true);
		
		jlNameCardImg = new JLabel(new ImageIcon("D:/git/repositories/businessCardHolder/src/client/img/no_img.jpg"));
		jtfMemo = new JTextField();
		jbClose = new JButton("닫기");

		JLabel jlMemo = new JLabel("명함메모");

		setLayout(null);
		
		jlNameCardImg.setBounds(20, 20, 460, 260);
		jlMemo.setBounds(70, 300, 60, 30);
		jtfMemo.setBounds(130, 300, 300, 30);
		jbClose.setBounds(210, 350, 100, 30);
		
		add(jlNameCardImg);
		add(jlMemo);
		add(jtfMemo);
		add(jbClose);
		
		ClientDetailBCController cdbcc = new ClientDetailBCController(this);
		jbClose.addActionListener(cdbcc);

		addWindowListener(cdbcc);
		
		setBounds(cmv.getY()+50, cmv.getY()+50, 520, 450);
		setVisible(true);
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
}
