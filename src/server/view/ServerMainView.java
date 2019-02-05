package server.view;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import server.controller.ServerMainController;

public class ServerMainView extends JFrame {

	JList<String> jlRequest;
	JScrollPane jspRequest;
	DefaultListModel<String> dlmRequest;
	JButton jbRunServer, jbClose;
	
	public ServerMainView() {
		super("명함첩-서버");

		dlmRequest = new DefaultListModel<String>();
		jlRequest = new JList<String>(dlmRequest);
		jbRunServer = new JButton("서버구동");
		jbClose = new JButton("닫기");
		
		jspRequest = new JScrollPane(jlRequest);
		jspRequest.setBorder(new TitledBorder("서버로그"));
		
		JPanel panel = new JPanel();
		panel.add(jbRunServer);
		panel.add(jbClose);
		
		add(BorderLayout.CENTER, jspRequest);
		add(BorderLayout.SOUTH, panel);
		
		ServerMainController smc = new ServerMainController(this);
		jbRunServer.addActionListener(smc);
		jbClose.addActionListener(smc);
		addWindowListener(smc);
		
		setBounds(400, 300, 600, 300);
		setVisible(true);
	}
	public JList<String> getJlRequest() {
		return jlRequest;
	}
	public JScrollPane getJspRequest() {
		return jspRequest;
	}
	public DefaultListModel<String> getDlmRequest() {
		return dlmRequest;
	}
	public JButton getJbRunServer() {
		return jbRunServer;
	}
	public JButton getJbClose() {
		return jbClose;
	}
}
