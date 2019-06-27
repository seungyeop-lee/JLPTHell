//가입 회원 현황창 UI
package client.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import vo.UserWord;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import java.awt.Font;

public class HistoryUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -8974421923484843744L;

	private StudyUI studyUI;
	
	private JPanel contentPane;
	private JTable listTable;
	private JButton backButton;
	private JPanel backBtnPanel;
	private JPanel lowerPanel;
	
	//객채 생성 시 UI 요소 생성
	public HistoryUI(ArrayList<UserWord> userWordList, StudyUI studyUI) {
		setBackground(Color.WHITE);
		this.studyUI = studyUI;
		
		int size = userWordList.size();
		int count = 0;
		
		//JFrame의 contentPane
		setTitle("History Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//table panel
		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		tablePanel.setBackground(Color.WHITE);
		contentPane.add(tablePanel);
		tablePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		//list table
		//컬럼 이름 setting
		String[] columnNames = {"단어", "뜻", "학습진도"};
		
		//자동 입력 폼
		Object[][] data = new Object[size][3];
		for(UserWord w : userWordList) {
			data[count][0] = w.getWord();
			data[count][1] = w.getMean();
			data[count][2] = w.getCount();
			count++;
		}
		
		//list table 생성 및 setting
		listTable = new JTable(data, columnNames);
		listTable.setRowHeight(25);
		listTable.setOpaque(false);
		listTable.setFont(new Font("카이겐고딕 KR Regular", Font.PLAIN, 15));
		listTable.setGridColor(Color.WHITE);
		listTable.setBorder(null);
		listTable.setBackground(Color.WHITE);
		listTable.setShowHorizontalLines(false);
		listTable.setAutoCreateRowSorter(true);
		listTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//list table scrollpane
		JScrollPane tableScrollPane = new JScrollPane(listTable);
		tableScrollPane.setBackground(Color.WHITE);
		tableScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tablePanel.add(tableScrollPane);
		tableScrollPane.getViewport().setBackground(Color.white);
		
		//lower panel(back button panel 부분)
		lowerPanel = new JPanel();
		lowerPanel.setBackground(Color.WHITE);
		contentPane.add(lowerPanel, BorderLayout.SOUTH);
		lowerPanel.setLayout(new BorderLayout(0, 0));
		
		//back button panel
		backBtnPanel = new JPanel();
		backBtnPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		backBtnPanel.setBackground(Color.WHITE);
		lowerPanel.add(backBtnPanel, BorderLayout.EAST);
		backBtnPanel.setLayout(new BorderLayout(0, 0));
		backBtnPanel.setPreferredSize(new Dimension(100, 50));
		
		//back button
		backButton = new JButton("돌아가기");
		backButton.setFont(new Font("굴림", Font.PLAIN, 20));
		backButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		backBtnPanel.add(backButton);
		backButton.setBackground(Color.WHITE);
		backButton.addActionListener(this);
	}

	/**
	 * 버튼 클릭에 따른 이벤트 처리
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			dispose();
			studyUI.setVisible(true);
		}
	}
}
