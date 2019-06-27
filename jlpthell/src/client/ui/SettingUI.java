//Setting 창 UI
package client.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.manager.ClientManager;
import client.uiManager.SettingUIManager;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Dimension;

public class SettingUI extends JFrame implements ActionListener {
	private SettingUIManager suim;
	private MainUI mainUI;
	private ClientManager cm;
	
	private JPanel contentPane;
	private JComboBox<String> changeComboBox;
	
	//객채 생성 시 UI 요소 생성
	public SettingUI(MainUI mainUI, ClientManager cm) {
		//MainUI 객체와 Server와 연결된 socket객체 받음
		this.mainUI = mainUI;
		this.cm = cm;
		
		//SettingUIManager 객체 생성
		suim = new SettingUIManager(mainUI, cm);
		
		//JFrame의 contentPane
		setTitle("Setting Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 316);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		//upper main panel (button)
		JPanel upperPanel = new JPanel();
		upperPanel.setBackground(Color.WHITE);
		contentPane.add(upperPanel);
		upperPanel.setLayout(new GridLayout(3, 2, 0, 0));
		
		
		//change label panel
		JPanel changeLabelPanel = new JPanel();
		changeLabelPanel.setBackground(Color.WHITE);
		upperPanel.add(changeLabelPanel);
		changeLabelPanel.setBorder(new EmptyBorder(5, 20, 5, 20));
		changeLabelPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		//change label
		JLabel changeLabel = new JLabel("급수 변경");
		changeLabel.setBackground(Color.WHITE);
		changeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		changeLabel.setFont(new Font("굴림", Font.BOLD, 25));
		changeLabelPanel.add(changeLabel);
		
		
		//change setting panel
		JPanel changeSettingPanel = new JPanel();
		changeSettingPanel.setBackground(Color.WHITE);
		upperPanel.add(changeSettingPanel);
		changeSettingPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		changeSettingPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		//change combobox panel
		JPanel changeComboPanel = new JPanel();
		changeComboPanel.setBackground(Color.WHITE);
		changeComboPanel.setBorder(new EmptyBorder(15, 10, 15, 10));
		changeSettingPanel.add(changeComboPanel);
		changeComboPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		//change combobox
		changeComboBox = new JComboBox<>();
		changeComboBox.setBorder(new LineBorder(new Color(171, 173, 179), 1, true));
		changeComboBox.setBackground(Color.WHITE);
		changeComboBox.setFont(new Font("굴림", Font.PLAIN, 20));
		changeComboBox.setMaximumRowCount(5);
		changeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"N1", "N2", "N3", "N4", "N5"}));
		changeComboBox.addActionListener(this);
		changeComboPanel.add(changeComboBox);
		
		//change button panel
		JPanel changeButtonPanel = new JPanel();
		changeButtonPanel.setBackground(Color.WHITE);
		changeButtonPanel.setBorder(new EmptyBorder(15, 10, 15, 10));
		changeSettingPanel.add(changeButtonPanel);
		changeButtonPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		//change button
		JButton changeButton = new JButton("확인");
		changeButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		changeButton.setBackground(Color.WHITE);
		changeButton.setFont(new Font("굴림", Font.PLAIN, 20));
		changeButton.addActionListener(this);
		changeButtonPanel.add(changeButton);
		
		
		//initialization label panel
		JPanel iniLabelPanel = new JPanel();
		iniLabelPanel.setBackground(Color.WHITE);
		upperPanel.add(iniLabelPanel);
		iniLabelPanel.setBorder(new EmptyBorder(5, 20, 5, 20));
		iniLabelPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		//initialization label
		JLabel iniLabel = new JLabel("학습이력 초기화");
		iniLabel.setBackground(Color.WHITE);
		iniLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iniLabel.setFont(new Font("굴림", Font.BOLD, 25));
		iniLabelPanel.add(iniLabel);
		
		//initialization button panel
		JPanel iniButtonPanel = new JPanel();
		iniButtonPanel.setBackground(Color.WHITE);
		upperPanel.add(iniButtonPanel);
		iniButtonPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
		iniButtonPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		//initialization button
		JButton iniButton = new JButton("초기화실행");
		iniButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		iniButton.setBackground(Color.WHITE);
		iniButton.setFont(new Font("굴림", Font.PLAIN, 20));
		iniButton.addActionListener(this);
		iniButtonPanel.add(iniButton);
		
		//delete label panel
		JPanel deleteLabelPanel = new JPanel();
		deleteLabelPanel.setBackground(Color.WHITE);
		upperPanel.add(deleteLabelPanel);
		deleteLabelPanel.setBorder(new EmptyBorder(5, 20, 5, 20));
		deleteLabelPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		//delete label
		JLabel deleteLabel = new JLabel("아이디 삭제");
		deleteLabel.setBackground(Color.WHITE);
		deleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		deleteLabel.setFont(new Font("굴림", Font.BOLD, 25));
		deleteLabelPanel.add(deleteLabel);
		
		
		//delete button panel
		JPanel deleteButtonPanel = new JPanel();
		deleteButtonPanel.setBackground(Color.WHITE);
		upperPanel.add(deleteButtonPanel);
		deleteButtonPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
		deleteButtonPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		//delete button
		JButton deleteUserButton = new JButton("삭제실행");
		deleteUserButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		deleteUserButton.setBackground(Color.WHITE);
		deleteUserButton.setFont(new Font("굴림", Font.PLAIN, 20));
		deleteUserButton.addActionListener(this);
		deleteButtonPanel.add(deleteUserButton);
		
		
		//lower panel (back button)
		JPanel lowerPanel = new JPanel();
		lowerPanel.setBackground(Color.WHITE);
		contentPane.add(lowerPanel, BorderLayout.SOUTH);
		lowerPanel.setLayout(new BorderLayout(0, 0));
		
		//back button panel
		JPanel backBtnPanel = new JPanel();
		backBtnPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		backBtnPanel.setBackground(Color.WHITE);
		lowerPanel.add(backBtnPanel, BorderLayout.EAST);
		
		//back button
		JButton backButton = new JButton("돌아가기");
		backButton.setPreferredSize(new Dimension(100, 30));
		backButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		backButton.setBackground(Color.WHITE);
		backButton.addActionListener(this);
		backBtnPanel.setLayout(new BorderLayout(0, 0));
		backBtnPanel.add(backButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()) {
		//급수 변경 확인 버튼
		case "확인":
			int changeGradeConfirmResult = JOptionPane.showConfirmDialog(this, "급수를 변경하시겠습니까?");
			if(changeGradeConfirmResult == JOptionPane.YES_OPTION) {
				int changeGradeResult = suim.changeGrade((String) changeComboBox.getSelectedItem());
				switch(changeGradeResult) {
				case -1:
					JOptionPane.showMessageDialog(this, "급수 변경에 실패하였습니다.");
					break;
				case 0:
					JOptionPane.showMessageDialog(this, "현재 급수입니다.");
					break;
				case 1:
					JOptionPane.showMessageDialog(this, "급수 변경에 성공하였습니다.");
					mainUI.muim.startMainUI();
					break;
				}
			}
			break;
		//학습이력 초기화 버튼
		case "초기화실행":
			int iniConfirmResult = JOptionPane.showConfirmDialog(this, "초기화 하시겠습니까?");
			if(iniConfirmResult == JOptionPane.YES_OPTION) {
				boolean iniResult = suim.initialize();
				if(iniResult) {
					JOptionPane.showMessageDialog(this, "초기화에 성공하였습니다.");
				} else {
					JOptionPane.showMessageDialog(this, "초기화에 실패하였습니다.");
				}
			}
			break;
		//아이디 삭제 버튼
		case "삭제실행":
			int deleteUserConfirmResult = JOptionPane.showConfirmDialog(this, "아이디를 하시겠습니까?");
			if(deleteUserConfirmResult == JOptionPane.YES_OPTION) {
				boolean deleteUserResult = suim.deleteUser();
				if(deleteUserResult) {
					JOptionPane.showMessageDialog(this, "아이디 삭제에 성공하였습니다.");
					dispose();
					suim.logout();
				} else {
					JOptionPane.showMessageDialog(this, "아이디 삭제에 실패하였습니다.");
				}
			}
			break;
		//돌아가기 버튼
		case "돌아가기":
			mainUI.setVisible(true);
			dispose();
			break;
		}
	}
}
