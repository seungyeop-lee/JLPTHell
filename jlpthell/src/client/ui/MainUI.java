//메인 창 UI
package client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import client.manager.ClientManager;
import client.uiManager.MainUIManager;

public class MainUI extends JFrame implements ActionListener {
	public MainUIManager muim;
	private ClientManager cm;
	private JPanel contentPane;
	
	//객채 생성 시 UI 요소 생성
	public MainUI(ClientManager cm) {
		//Server와 연결된 socket객체 받음
		this.cm = cm;
		//mainUIManager 객체 생성
		muim = new MainUIManager(this, cm);
		
		//JFrame의 contentPane
		setTitle("Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 0, 0, 0));
		
		
		//title panel
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(titlePanel);
		titlePanel.setLayout(new BorderLayout(0, 0));
		
		//title label
		JLabel titleLabel = new JLabel("JLPT 단어암기 지옥");
		titleLabel.setBorder(new LineBorder(Color.RED, 2, true));
		titleLabel.setForeground(Color.RED);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("굴림", Font.BOLD, 50));
		titlePanel.add(titleLabel, BorderLayout.CENTER);
		
		
		//main button panel (단어학습, 미니게임)
		JPanel mainButtonPanel = new JPanel();
		contentPane.add(mainButtonPanel);
		mainButtonPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		//학습 button panel
		JPanel studyBtnPanel = new JPanel();
		studyBtnPanel.setBackground(Color.WHITE);
		studyBtnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainButtonPanel.add(studyBtnPanel);
		studyBtnPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		//학습 button
		JButton studyButton = new JButton("단어학습");
		studyButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		studyButton.setBackground(Color.WHITE);
		studyButton.setFont(new Font("Dialog", Font.BOLD, 30));
		studyButton.addActionListener(this);
		studyBtnPanel.add(studyButton);
		
		//게임 button panel
		JPanel gameBtnPanel = new JPanel();
		gameBtnPanel.setBackground(Color.WHITE);
		gameBtnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainButtonPanel.add(gameBtnPanel);
		gameBtnPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		//게임 button
		JButton gameButton = new JButton("미니게임");
		gameButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		gameButton.setBackground(Color.WHITE);
		gameButton.setFont(new Font("Dialog", Font.BOLD, 30));
		gameButton.addActionListener(this);
		gameBtnPanel.add(gameButton);
		
		
		//sub button panel (환경설정, 회원학습현황, 로그아웃)
		JPanel subButtonPanel = new JPanel();
		contentPane.add(subButtonPanel);
		subButtonPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		//member list button panel
		JPanel listBtnPanel = new JPanel();
		listBtnPanel.setBackground(Color.WHITE);
		listBtnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		subButtonPanel.add(listBtnPanel);
		listBtnPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		//member list button
		JButton listButton = new JButton("회원학습현황");
		listButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		listButton.setBackground(Color.WHITE);
		listButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		listButton.addActionListener(this);
		listBtnPanel.add(listButton);
		
		//chat button panel
		JPanel chatBtnPanel = new JPanel();
		chatBtnPanel.setBackground(Color.WHITE);
		chatBtnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		subButtonPanel.add(chatBtnPanel);
		chatBtnPanel.setLayout(new BorderLayout(0, 0));
		
		//chat button
		JButton chatButton = new JButton("접속자 채팅");
		chatButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		chatButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		chatButton.setBackground(Color.WHITE);
		chatBtnPanel.add(chatButton);
		chatButton.addActionListener(this);
		
		//etc button panel(logout, setting)
		JPanel etcPanel = new JPanel();
		subButtonPanel.add(etcPanel);
		etcPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		//logout button panel
		JPanel logoutBtnPanel = new JPanel();
		etcPanel.add(logoutBtnPanel);
		logoutBtnPanel.setBackground(Color.WHITE);
		logoutBtnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		logoutBtnPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		//logout button
		JButton logoutButton = new JButton("로그아웃");
		logoutButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		logoutButton.setBackground(Color.WHITE);
		logoutButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		logoutButton.addActionListener(this);
		logoutBtnPanel.add(logoutButton);
		
		//setting button panel
		JPanel settingBtnPanel = new JPanel();
		etcPanel.add(settingBtnPanel);
		settingBtnPanel.setBackground(Color.WHITE);
		settingBtnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		settingBtnPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		//setting button 
		JButton settingButton = new JButton("환경설정");
		settingButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		settingButton.setBackground(Color.WHITE);
		settingButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		settingButton.addActionListener(this);
		settingBtnPanel.add(settingButton);
		
		//초기값 설정 메소드 실행
		muim.startMainUI();
	}
	
	/**
	 * 버튼 클릭에 따른 이벤트 처리
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		//단어학습 버튼 클릭
		case "단어학습":
			muim.studyUI();
			break;
		//미니게임 버튼 클릭
		case "미니게임":
			muim.miniGameUI();
			break;
		//환경설정 버튼 클릭
		case "환경설정":
			muim.settingUI();
			break;
		//회원학습현황 버튼 클릭
		case "회원학습현황":
			muim.userListUI();
			break;
		//로그아웃 버튼 클릭
		case "로그아웃":
			int logoutResult = JOptionPane.showConfirmDialog(this, "정말 로그아웃 하시겠습니까?");
			if(logoutResult == JOptionPane.YES_OPTION) {
				muim.logout();
			}
			break;
		//채팅 버튼 클릭
		case "접속자 채팅":
			muim.chatStart();
			break;
		}
	}
}
