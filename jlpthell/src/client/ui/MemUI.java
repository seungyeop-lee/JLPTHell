//암기 창 UI
package client.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import client.manager.ClientManager;
import client.uiManager.MemUIManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class MemUI extends JFrame implements ActionListener{
	private MemUIManager muim;
	public StudyUI studyUI;
	private ClientManager cm;
	
	private JPanel contentPane;
	private JButton backButton;
	public JTextArea orderTextArea;
	public JButton wordButton;
	private JButton prevButton;
	private JButton nextButton;
	
	//객채 생성 시 UI 요소 생성
	public MemUI(StudyUI studyUI, ClientManager cm) {
		//StudyUI와 ClientManager 객채의 주소를 저장
		this.studyUI = studyUI;
		this.cm = cm;
		
		//MemUIManager 객체 생성
		muim = new MemUIManager(this, cm);
		
		//JFrame의 contentPane
		setTitle("Memorization Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//upper panel (순번, 돌아가기 버튼)
		JPanel upperPanel = new JPanel();
		upperPanel.setBackground(Color.WHITE);
		contentPane.add(upperPanel, BorderLayout.NORTH);
		upperPanel.setLayout(new BorderLayout(0, 0));
		
		//order textarea panel
		JPanel orderTextAreaPanel = new JPanel();
		orderTextAreaPanel.setBackground(Color.WHITE);
		orderTextAreaPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "\uC21C\uBC88", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		upperPanel.add(orderTextAreaPanel, BorderLayout.WEST);
		orderTextAreaPanel.setLayout(new BorderLayout(0, 0));
		
		//order textarea
		orderTextArea = new JTextArea();
		orderTextArea.setFont(new Font("Monospaced", Font.PLAIN, 25));
		orderTextAreaPanel.add(orderTextArea, BorderLayout.SOUTH);
		
		
		//back button panel
		JPanel backBtnPanel = new JPanel();
		backBtnPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		backBtnPanel.setBackground(Color.WHITE);
		upperPanel.add(backBtnPanel, BorderLayout.EAST);
		backBtnPanel.setLayout(new GridLayout(1, 0, 0, 0));
		backBtnPanel.setPreferredSize(new Dimension(100, 0));
		
		//back button
		backButton = new JButton("돌아가기");
		backButton.setFont(new Font("굴림", Font.PLAIN, 20));
		backButton.setBackground(Color.WHITE);
		backButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		backButton.addActionListener(this);
		backBtnPanel.add(backButton);
		
		
		//mid panel(단어 or 뜻 버튼)
		JPanel midPanel = new JPanel();
		midPanel.setBackground(Color.WHITE);
		contentPane.add(midPanel, BorderLayout.CENTER);
		midPanel.setLayout(new BorderLayout(0, 0));
		
		//word button panel
		JPanel wordBtnPanel = new JPanel();
		wordBtnPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		wordBtnPanel.setBackground(Color.WHITE);
		midPanel.add(wordBtnPanel, BorderLayout.CENTER);
		wordBtnPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		//word button
		wordButton = new JButton("단어 or 뜻");
		wordButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		wordButton.setBackground(Color.WHITE);
		wordButton.setFont(new Font("카이겐고딕 KR Regular", Font.PLAIN, 50));
		wordButton.addActionListener(this);
		wordBtnPanel.add(wordButton);
		
		
		//lower panel (prev., next 버튼)
		JPanel lowerPanel = new JPanel();
		lowerPanel.setBackground(Color.WHITE);
		contentPane.add(lowerPanel, BorderLayout.SOUTH);
		lowerPanel.setLayout(new GridLayout(0, 5, 0, 0));
		lowerPanel.setPreferredSize(new Dimension(0, 50));
		
		//empty panel_1
		JPanel emptyPanel_1 = new JPanel();
		emptyPanel_1.setBackground(Color.WHITE);
		lowerPanel.add(emptyPanel_1);
		
		//empty panel_2 (prev button panel 위치)
		JPanel emptyPanel_2 = new JPanel();
		emptyPanel_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		emptyPanel_2.setBackground(Color.WHITE);
		lowerPanel.add(emptyPanel_2);
		emptyPanel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		//prev button
		prevButton = new JButton("Prev.");
		prevButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		emptyPanel_2.add(prevButton);
		prevButton.setFont(new Font("굴림", Font.PLAIN, 25));
		prevButton.setBackground(Color.WHITE);
		prevButton.addActionListener(this);
		
		//empty panel_3
		JPanel emptyPanel_3 = new JPanel();
		emptyPanel_3.setBackground(Color.WHITE);
		lowerPanel.add(emptyPanel_3);
		
		//empty panel_4 (next button panel 위치)
		JPanel emptyPanel_4 = new JPanel();
		emptyPanel_4.setBorder(new EmptyBorder(5, 5, 5, 5));
		emptyPanel_4.setBackground(Color.WHITE);
		lowerPanel.add(emptyPanel_4);
		emptyPanel_4.setLayout(new GridLayout(1, 0, 0, 0));
		
		//next button
		nextButton = new JButton("Next");
		nextButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		emptyPanel_4.add(nextButton);
		nextButton.setFont(new Font("굴림", Font.PLAIN, 25));
		nextButton.setBackground(Color.WHITE);
		nextButton.addActionListener(this);
		
		//empty panel_5
		JPanel emptyPanel_5 = new JPanel();
		emptyPanel_5.setBackground(Color.WHITE);
		lowerPanel.add(emptyPanel_5);
		
		//초기값 설정 메소드 실행
		muim.startMem();
	}
	
	/**
	 * 버튼 클릭에 따른 이벤트 처리
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object input = e.getSource();
		//돌아가기 버튼 클릭
		if(input == backButton) {
			int backResult = JOptionPane.showConfirmDialog(this, "현재 학습 현황을 저장 후 뒤로 돌아가시겠습니까?");
			if(backResult == JOptionPane.YES_OPTION) {
				muim.saveNow();
				dispose();
				studyUI.setVisible(true);
			} else if(backResult == JOptionPane.NO_OPTION) {
				dispose();
				studyUI.setVisible(true);
			}
		//단어 버튼 클릭
		} else if(input == wordButton) {
			muim.wordChange();
		//prev. 버튼 클릭
		} else if(input == prevButton) {
			muim.prevButton();
		//next 버튼 클릭
		} else if(input == nextButton) {
			muim.nextButton();
		}
	}
}