//회원가입 창 UI
package client.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import client.manager.ClientManager;
import client.uiManager.JoinUIManager;
import vo.User;
import java.awt.Color;

public class JoinUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -9076766543303495839L;
	
	private ConnectionUI conUI;
	private ClientManager cm;
	private JoinUIManager juim;
	
	private JPanel contentPane;
	private JTextField idTextField;
	private JPasswordField pwPasswordField;
	private JPasswordField pwrPasswordField;
	private JComboBox<String> gradeComboBox;
	private JTextField pwhTextField;
	
	//객채 생성 시 UI 요소 생성
	public JoinUI(ConnectionUI conUI, ClientManager cm) {
		//접속 화면  UI, ClientManager의 주소 저장
		this.conUI = conUI;
		this.cm = cm;
		
		//JoinUIManager 객체 생성
		juim = new JoinUIManager(cm);
		
		//JFrame의 contentPane
		setTitle("\uD68C\uC6D0\uAC00\uC785");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(6, 0, 0, 0));
		
		//ID Panel
		JPanel idPanel = new JPanel();
		idPanel.setBackground(Color.WHITE);
		idPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(idPanel);
		idPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		//ID Lable
		JLabel idLabel = new JLabel("ID");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		idPanel.add(idLabel);
		
		//ID TextField
		idTextField = new JTextField();
		idTextField.setHorizontalAlignment(SwingConstants.CENTER);
		idTextField.setFont(new Font("굴림", Font.PLAIN, 20));
		idPanel.add(idTextField);
		idTextField.setColumns(10);
		idTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField temp = (JTextField) e.getSource();
				if(temp.getText().length() >= 12) {
					e.consume();
				}
			}
		});
		
		
		//password Panel
		JPanel pwPanel = new JPanel();
		pwPanel.setBackground(Color.WHITE);
		pwPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(pwPanel);
		pwPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		//password Label
		JLabel pwLabel = new JLabel("Password");
		pwLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		pwPanel.add(pwLabel);
		
		//password passwordfield
		pwPasswordField = new JPasswordField();
		pwPasswordField.setHorizontalAlignment(SwingConstants.CENTER);
		pwPasswordField.setFont(new Font("굴림", Font.PLAIN, 20));
		pwPanel.add(pwPasswordField);
		pwPasswordField.setColumns(10);
		pwPasswordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField temp = (JTextField) e.getSource();
				if(temp.getText().length() >= 12) {
					e.consume();
				}
			}
		});
		
		
		//password 확인 panel
		JPanel pwrPanel = new JPanel();
		pwrPanel.setBackground(Color.WHITE);
		pwrPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(pwrPanel);
		pwrPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		//password 확인 label
		JLabel pwrLabel = new JLabel("Password(re)");
		pwrLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwrLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		pwrPanel.add(pwrLabel);
		
		//password 확인 passwordfield
		pwrPasswordField = new JPasswordField();
		pwrPasswordField.setHorizontalAlignment(SwingConstants.CENTER);
		pwrPasswordField.setFont(new Font("굴림", Font.PLAIN, 20));
		pwrPanel.add(pwrPasswordField);
		pwrPasswordField.setColumns(10);
		pwrPasswordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField temp = (JTextField) e.getSource();
				if(temp.getText().length() >= 12) {
					e.consume();
				}
			}
		});
		
		
		//grade panel
		JPanel gradePanel = new JPanel();
		gradePanel.setBackground(Color.WHITE);
		gradePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(gradePanel);
		gradePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		//grade label
		JLabel gradeLabel = new JLabel("Grade");
		gradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gradeLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		gradePanel.add(gradeLabel);
		
		//grade combobox
		gradeComboBox = new JComboBox<String>();
		gradeComboBox.setBackground(Color.WHITE);
		gradeComboBox.setMaximumRowCount(5);
		gradeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"N1", "N2", "N3", "N4", "N5"}));
		gradeComboBox.setFont(new Font("굴림", Font.BOLD, 20));
		gradeComboBox.addActionListener(this);
		gradePanel.add(gradeComboBox);
		
		//password hint panel
		JPanel pwhPanel = new JPanel();
		pwhPanel.setBackground(Color.WHITE);
		pwhPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(pwhPanel);
		pwhPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		//password hint label
		JLabel pwhLabel = new JLabel("Password Hint");
		pwhLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwhLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		pwhPanel.add(pwhLabel);
		
		//password hint textfield
		pwhTextField = new JTextField();
		pwhTextField.setHorizontalAlignment(SwingConstants.CENTER);
		pwhTextField.setFont(new Font("굴림", Font.PLAIN, 20));
		pwhPanel.add(pwhTextField);
		pwhTextField.setColumns(10);
		pwhTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField temp = (JTextField) e.getSource();
				if(temp.getText().length() >= 20) {
					e.consume();
				}
			}
		});
		
		
		//join button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setBorder(new EmptyBorder(5, 50, 5, 50));
		contentPane.add(buttonPanel);
		buttonPanel.setLayout(new BorderLayout(0, 0));
		
		//join button 
		JButton joinButton = new JButton("JOIN");
		joinButton.setBackground(Color.WHITE);
		joinButton.setFont(new Font("Dialog", Font.BOLD, 20));
		joinButton.addActionListener(this);
		buttonPanel.add(joinButton, BorderLayout.CENTER);
		
	}
	
	/**
	 * 버튼 클릭에 따른 이벤트 처리
	 */
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()) {
		//JOIN 버튼 클릭 이벤트
		case "JOIN":
			//가입 최종 확인
			int confirmResult = JOptionPane.showConfirmDialog(this, "정말 가입하시겠습니까?");
			if(confirmResult == JOptionPane.NO_OPTION) {
				dispose();
				conUI.setVisible(true);
				return;
			} else if(confirmResult == JOptionPane.CANCEL_OPTION) {
				return;
			}
			
			//사용자가 입력한 id를 저장
			String id = idTextField.getText();
			
			//사용자가 입력한 pw를 저장
			String pw = "";
			char[] tpw = pwPasswordField.getPassword();
			for(char s : tpw) {
				pw = pw + s;
			}
			
			//사용자가 입력한 pw 재확인을 저장
			String pwr = "";
			char[] tpwr = pwrPasswordField.getPassword();
			for(char s : tpwr) {
				pwr = pwr + s;
			}
			
			//pw와 pw 재확인이 같은지 확인 후 다르면 error 발생!
			if(!pw.equals(pwr)) {
				JOptionPane.showMessageDialog(this, "비밀번호 서로 다릅니다.");
				return;
			}
			
			//사용자가 입력한 pw의 힌트를 저장 
			String pwHint = pwhTextField.getText();
			
			//사용자가 입력한 값들을 vo로 생성
			User u = new User(id, pw, (String)gradeComboBox.getSelectedItem(), pwHint);
			
			//manager의 join 메소드를 실행
			if(juim.join(u)) {
				JOptionPane.showMessageDialog(this, "회원가입에 성공 하였습니다.");
			} else {
				JOptionPane.showMessageDialog(this, "회원가입에 실패 하였습니다.");
			}
			
			//결과와 상관없이 회원가입창을 닫고, 로그인창으로 돌아간다.
			conUI.setVisible(true);
			dispose();
			break;
		}
	}
}
