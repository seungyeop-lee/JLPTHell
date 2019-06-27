//로그인 창 UI
package client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import client.uiManager.ConnectionUIManager;

public class ConnectionUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -3738548882712169663L;
	
	private ConnectionUIManager cuim = new ConnectionUIManager(this);
	private JPanel contentPane;
	private JTextField idTextField;
	private JPasswordField pwPasswordField;
	
	//객체 생성 시 UI 요소 생성
	public ConnectionUI() {
		//JFrame의 contentPane
		setBackground(UIManager.getColor("window"));
		setTitle("Connection Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("window"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 0, 0, 0));
		
		//상위 Panel(제목 표시 )
		JPanel upperPanel = new JPanel();
		upperPanel.setBackground(UIManager.getColor("window"));
		upperPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(upperPanel);
		upperPanel.setLayout(new BorderLayout(0, 0));
		
		//제목 표시 라벨
		JLabel subjectLable = new JLabel("JLPT 단어암기 지옥");
		subjectLable.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		subjectLable.setFont(new Font("굴림", Font.BOLD, 35));
		subjectLable.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(subjectLable);
		
		
		//하위 Panel(ID, PW, Button )
		JPanel lowerPanel = new JPanel();
		contentPane.add(lowerPanel);
		lowerPanel.setLayout(new GridLayout(3, 0, 0, 0));
		
		//ID Panel
		JPanel idPanel = new JPanel();
		idPanel.setBackground(UIManager.getColor("window"));
		lowerPanel.add(idPanel);
		idPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		//ID 표시 라벨
		JLabel idLabel = new JLabel("ID");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		idPanel.add(idLabel);
		
		JPanel idTextFieldPanel = new JPanel();
		idTextFieldPanel.setBackground(Color.WHITE);
		idTextFieldPanel.setBorder(new EmptyBorder(0, 0, 3, 5));
		idPanel.add(idTextFieldPanel);
		idTextFieldPanel.setLayout(new BorderLayout(0, 0));
		
		//사용자 ID 입력 
		idTextField = new JTextField();
		idTextField.setBorder(new LineBorder(Color.BLACK, 1, true));
		idTextFieldPanel.add(idTextField);
		idTextField.setHorizontalAlignment(SwingConstants.CENTER);
		idTextField.setFont(new Font("굴림", Font.PLAIN, 25));
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
		
		//PW Panel
		JPanel pwPanel = new JPanel();
		pwPanel.setBackground(UIManager.getColor("window"));
		lowerPanel.add(pwPanel);
		pwPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		//PW 표시 라벨
		JLabel pwLabel = new JLabel("Password");
		pwLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		pwPanel.add(pwLabel);
		
		JPanel pwPasswordFieldPanel = new JPanel();
		pwPasswordFieldPanel.setBorder(new EmptyBorder(3, 0, 0, 5));
		pwPasswordFieldPanel.setBackground(Color.WHITE);
		pwPanel.add(pwPasswordFieldPanel);
		pwPasswordFieldPanel.setLayout(new BorderLayout(0, 0));
		
		//사용자 PW 입력 
		pwPasswordField = new JPasswordField();
		pwPasswordField.setBorder(new LineBorder(Color.BLACK, 1, true));
		pwPasswordFieldPanel.add(pwPasswordField);
		pwPasswordField.setHorizontalAlignment(SwingConstants.CENTER);
		pwPasswordField.setFont(new Font("굴림", Font.PLAIN, 25));
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
		
		//Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(UIManager.getColor("window"));
		lowerPanel.add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		//Login Button
		JButton loginButton = new JButton("Login");
		loginButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		loginButton.setBackground(Color.WHITE);
		loginButton.setFont(new Font("굴림", Font.BOLD, 25));
		loginButton.addActionListener(this);
		buttonPanel.add(loginButton);
		
		//PW Button
		JButton pwButton = new JButton("PW");
		pwButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		pwButton.setBackground(Color.WHITE);
		pwButton.setFont(new Font("굴림", Font.BOLD, 25));
		pwButton.addActionListener(this);
		buttonPanel.add(pwButton);
		
		//join Button
		JButton joinButton = new JButton("Join");
		joinButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		joinButton.setBackground(Color.WHITE);
		joinButton.setFont(new Font("굴림", Font.BOLD, 25));
		joinButton.addActionListener(this);
		buttonPanel.add(joinButton);
	}
	
	/**
	 * 버튼 클릭에 따른 이벤트 처리
	 */
	public void actionPerformed(ActionEvent e) {
		String id = "";
		String pw = "";
		switch(e.getActionCommand()) {
		
		//login 버튼 클릭 이벤트
		case "Login":
			id = idTextField.getText();
			char[] tpw = pwPasswordField.getPassword();
			for(char s : tpw) {
				pw = pw + s;
			}
			
			boolean login = cuim.login(new String[]{id,pw});
			
			if(login) {
				JOptionPane.showMessageDialog(this, id + "님 환영합니다 ^^");
				cuim.mainUI();
			} else {
				JOptionPane.showMessageDialog(this, "로그인 정보를 확인해주세요.");
			}
			break;
			
		//pw 버튼 클릭 이벤트
		case "PW":
			id = idTextField.getText();
			String pwHint = cuim.pwHint(id);
			if(pwHint != null) {
				JOptionPane.showMessageDialog(this, "암호 힌트는 " + pwHint);
			} else {
				JOptionPane.showMessageDialog(this, "없는 ID입니다.");
			}
			break;
			
		//join 버튼 클릭 이벤트
		case "Join":
			cuim.joinUI();
			break;
		}
	}
}
