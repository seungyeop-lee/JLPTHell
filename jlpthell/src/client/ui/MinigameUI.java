//미니게임  창 UI
package client.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.manager.ClientManager;
import client.uiManager.MinigameUIManager;

import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Dimension;

public class MinigameUI extends JFrame implements ActionListener {
	private MinigameUIManager miniuim;
	public MainUI mainUI;
	private ClientManager cm;
	
	private JPanel contentPane;
	private JButton backButton;
	public JButton[] btnList;
	
	//객채 생성 시 UI 요소 생성
	public MinigameUI(MainUI mainUI, ClientManager cm) {
		//MainUI 객체와 Server와 연결된 socket객체 받음
		this.mainUI = mainUI;
		this.cm = cm;
		
		//MinigameUIManager 객체 생성
		miniuim = new MinigameUIManager(this, cm);
		
		//JFrame의 contentPane
		setTitle("Minigame Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		//lower panel (back button)
		JPanel lowerPanel = new JPanel();
		lowerPanel.setBackground(Color.WHITE);
		contentPane.add(lowerPanel, BorderLayout.SOUTH);
		lowerPanel.setLayout(new BorderLayout(0, 0));
		
		//back button panel
		JPanel backBtnPanel = new JPanel();
		backBtnPanel.setPreferredSize(new Dimension(100, 50));
		backBtnPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		backBtnPanel.setBackground(Color.WHITE);
		lowerPanel.add(backBtnPanel, BorderLayout.EAST);
		
		//back button
		backButton = new JButton("돌아가기");
		backButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		backButton.setBackground(Color.WHITE);
		backButton.setFont(new Font("굴림", Font.PLAIN, 20));
		backButton.addActionListener(this);
		backBtnPanel.setLayout(new BorderLayout(0, 0));
		backBtnPanel.add(backButton);
		
		//upper panel (game button)
		JPanel upperPanel = new JPanel();
		upperPanel.setBackground(Color.WHITE);
		contentPane.add(upperPanel, BorderLayout.CENTER);
		upperPanel.setLayout(new GridLayout(4, 5, 0, 0));
		
		//game button (array)
		btnList = new JButton[20];  
		
		//game button 생성
		for(int i = 0; i < 20; i++) {
			btnList[i] = new JButton();
			btnList[i].addActionListener(this);
			btnList[i].setFont(new Font("카이겐고딕 KR Regular", Font.BOLD, 20));
			btnList[i].setBackground(Color.white);
			btnList[i].setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			upperPanel.add(btnList[i]);
		}
		
		//초기값 설정 메소드 실행
		miniuim.miniGameStart();
	}
	
	/**
	 * 버튼 클릭에 따른 이벤트 처리
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object input = e.getSource();
		//돌아가기 버튼 클릭
		if(input == backButton) {
			dispose();
			mainUI.setVisible(true);
		//각 버튼을 클릭
		} else if(input == btnList[0]) {
			miniuim.clickbutton(0);
		} else if(input == btnList[1]) {
			miniuim.clickbutton(1);
		} else if(input == btnList[2]) {
			miniuim.clickbutton(2);
		} else if(input == btnList[3]) {
			miniuim.clickbutton(3);
		} else if(input == btnList[4]) {
			miniuim.clickbutton(4);
		} else if(input == btnList[5]) {
			miniuim.clickbutton(5);
		} else if(input == btnList[6]) {
			miniuim.clickbutton(6);
		} else if(input == btnList[7]) {
			miniuim.clickbutton(7);
		} else if(input == btnList[8]) {
			miniuim.clickbutton(8);
		} else if(input == btnList[9]) {
			miniuim.clickbutton(9);
		} else if(input == btnList[10]) {
			miniuim.clickbutton(10);
		} else if(input == btnList[11]) {
			miniuim.clickbutton(11);
		} else if(input == btnList[12]) {
			miniuim.clickbutton(12);
		} else if(input == btnList[13]) {
			miniuim.clickbutton(13);
		} else if(input == btnList[14]) {
			miniuim.clickbutton(14);
		} else if(input == btnList[15]) {
			miniuim.clickbutton(15);
		} else if(input == btnList[16]) {
			miniuim.clickbutton(16);
		} else if(input == btnList[17]) {
			miniuim.clickbutton(17);
		} else if(input == btnList[18]) {
			miniuim.clickbutton(18);
		} else if(input == btnList[19]) {
			miniuim.clickbutton(19);
		}
	}
}
