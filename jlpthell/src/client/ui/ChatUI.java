//채팅 창 UI
package client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import client.uiManager.ChatUIManager;

public class ChatUI extends JFrame {
	private ChatUIManager cuim = new ChatUIManager(this);
	private JPanel contentPane;
	private JTextField inputTextField;
	private JTextArea outputTextArea;
	
	//객채 생성 시 UI 요소 생성
	public ChatUI(String id) {
		
		//JFrame의 contentPane
		setTitle("사용자 ID: " + id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				cuim.sendText(id);
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//사용자 입력 부분 panel
		JPanel inputPanel = new JPanel();
		inputPanel.setPreferredSize(new Dimension(0, 40));
		contentPane.add(inputPanel, BorderLayout.SOUTH);
		inputPanel.setLayout(new BorderLayout(0, 0));
		
		//사용자 입력 TextField
		inputTextField = new JTextField();
		inputTextField.setFont(new Font("카이겐고딕 KR Regular", Font.PLAIN, 20));
		inputPanel.add(inputTextField);
		inputTextField.setColumns(10);
		//엔터키 입력 시 서버에 전송
		inputTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//서버에 전송하는 메소드
					cuim.sendText(id);
				}
			}
		});
		
		//채팅 출력 부분 panel
		JPanel outputPanel = new JPanel();
		contentPane.add(outputPanel, BorderLayout.CENTER);
		outputPanel.setLayout(new BorderLayout(0, 0));
		
		//채팅 출력 부분 TextArea
		outputTextArea = new JTextArea();
		outputTextArea.setFont(new Font("카이겐고딕 KR Regular", Font.PLAIN, 20));
		outputTextArea.setEditable(false);
		outputPanel.add(outputTextArea);
		
		//채팅 창 생성시 초기화
		cuim.readyStart(id);
	}

	public JTextField getInputTextField() {
		return inputTextField;
	}
	
	public JTextArea getOutputTextArea() {
		return outputTextArea;
	}
}
