//학습 창 UI
package client.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.manager.ClientManager;
import client.uiManager.StudyUIManager;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;

public class StudyUI extends JFrame implements ActionListener{
	private StudyUIManager suim;
	private MainUI mainUI;
	private ClientManager cm;
	
	private JPanel contentPane;
	private JButton backButton;
	private JButton memButton;
	private JButton reviewButton;
	private JButton historyButton;
	
	//객채 생성 시 UI 요소 생성
	public StudyUI(MainUI mainUI, ClientManager cm) {
		//MainUI 객체와 Server와 연결된 socket객체 받음
		this.mainUI = mainUI;
		this.cm = cm;
		
		suim = new StudyUIManager(this, cm);
		//JFrame의 contentPane
		setTitle("Study Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		//bottomPanel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.WHITE);
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		//back button panel
		JPanel backPanel = new JPanel();
		backPanel.setPreferredSize(new Dimension(100, 30));
		bottomPanel.add(backPanel, BorderLayout.EAST);
		
		//back button
		backButton = new JButton("돌아가기");
		backButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		backButton.setBackground(Color.WHITE);
		backButton.addActionListener(this);
		backPanel.setLayout(new BorderLayout(0, 0));
		backPanel.add(backButton);
		
		
		//centerPanel
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		//학습 button panel
		JPanel memBtnPanel = new JPanel();
		memBtnPanel.setBackground(Color.WHITE);
		memBtnPanel.setBorder(new EmptyBorder(20, 5, 20, 5));
		centerPanel.add(memBtnPanel);
		memBtnPanel.setLayout(new BorderLayout(0, 0));
		
		//학습 button
		memButton = new JButton("처음학습");
		memButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		memButton.setBackground(Color.WHITE);
		memButton.setFont(new Font("굴림", Font.BOLD, 25));
		memButton.addActionListener(this);
		memBtnPanel.add(memButton, BorderLayout.CENTER);
		
		
		//복습 button panel
		JPanel reviewBtnPanel = new JPanel();
		reviewBtnPanel.setBackground(Color.WHITE);
		reviewBtnPanel.setBorder(new EmptyBorder(20, 5, 20, 5));
		centerPanel.add(reviewBtnPanel);
		reviewBtnPanel.setLayout(new BorderLayout(0, 0));
		
		//복습 button
		reviewButton = new JButton("복습하기");
		reviewButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		reviewButton.setBackground(Color.WHITE);
		reviewButton.setFont(new Font("굴림", Font.BOLD, 25));
		reviewButton.addActionListener(this);
		reviewBtnPanel.add(reviewButton, BorderLayout.CENTER);
		
		
		//학습이력 button panel
		JPanel historyBtnPanel = new JPanel();
		historyBtnPanel.setBackground(Color.WHITE);
		historyBtnPanel.setBorder(new EmptyBorder(20, 5, 20, 5));
		centerPanel.add(historyBtnPanel);
		historyBtnPanel.setLayout(new BorderLayout(0, 0));
		
		//학습이력 button
		historyButton = new JButton("학습이력");
		historyButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		historyButton.setBackground(Color.WHITE);
		historyButton.setFont(new Font("굴림", Font.BOLD, 25));
		historyButton.addActionListener(this);
		historyBtnPanel.add(historyButton, BorderLayout.CENTER);
	}
	
	/**
	 * 버튼 클릭에 따른 이벤트 처리
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object input = e.getSource();
		//암기 버튼 클릭
		if(input == memButton) {
			suim.memUI();
		//복습 버튼 클릭
		} else if(input == reviewButton) {
			suim.reviewUI();
		//학습이력 버튼 클릭
		} else if(input == historyButton) {
			suim.historyUI();
		//돌아가기 버튼 클릭
		} else if(input == backButton) {
			mainUI.setVisible(true);
			dispose();
		}
	}

}
