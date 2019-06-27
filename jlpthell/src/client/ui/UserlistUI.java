//유저 현황 창 UI
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

import vo.UserForList;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Dimension;

public class UserlistUI extends JFrame implements ActionListener {
	private MainUI mainUI;
	
	private JPanel contentPane;
	private JTable listTable;
	
	//객채 생성 시 UI 요소 생성
	public UserlistUI(MainUI mainUI, ArrayList<UserForList> userList) {
		//MainUI 객체 받음
		this.mainUI = mainUI;
		
		int size = userList.size();
		int count = 0;
		
		//JFrame의 contentPane
		setTitle("UserList Window");
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
		String[] columnNames = {"ID", "학습급수", "학습 중 단어", "학습완료단어"};
		
		//자동 입력 폼
		Object[][] data = new Object[size][4];
		for(UserForList u : userList) {
			data[count][0] = u.getId();
			data[count][1] = u.getGrade();
			data[count][2] = u.getStudyingCount();
			data[count][3] = u.getStudiedCount();
			count++;
		}
		
		//list table 생성 및 setting
		listTable = new JTable(data, columnNames);
		listTable.setBorder(new EmptyBorder(0, 0, 0, 0));
		listTable.setGridColor(Color.WHITE);
		listTable.setRowHeight(25);
		listTable.setFont(new Font("굴림", Font.PLAIN, 15));
		listTable.setBackground(Color.WHITE);
		listTable.setShowHorizontalLines(false);
		listTable.setAutoCreateRowSorter(true);
		listTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		//list table scrollpane
		JScrollPane tableScrollPane = new JScrollPane(listTable);
		tableScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tablePanel.add(tableScrollPane);
		tableScrollPane.getViewport().setBackground(Color.white);
		
		//lower panel (back button panel 부분)
		JPanel lowerPanel = new JPanel();
		lowerPanel.setBackground(Color.WHITE);
		contentPane.add(lowerPanel, BorderLayout.SOUTH);
		lowerPanel.setLayout(new BorderLayout(0, 0));
		
		//back button panel
		JPanel backBtnPanel = new JPanel();
		backBtnPanel.setPreferredSize(new Dimension(100, 50));
		backBtnPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		lowerPanel.add(backBtnPanel, BorderLayout.EAST);
		backBtnPanel.setLayout(new BorderLayout(0, 0));
		
		//back button
		JButton backButton = new JButton("돌아가기");
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
		//돌아가기 버튼 클릭
		switch(e.getActionCommand()) {
		case "돌아가기":
			mainUI.setVisible(true);
			dispose();
			break;
		}
	}
}
