//로그인 창 UI Manager
package client.uiManager;

import client.manager.ClientManager;
import client.ui.ConnectionUI;
import client.ui.JoinUI;
import client.ui.MainUI;

public class ConnectionUIManager {
	private ClientManager cm = new ClientManager();
	private ConnectionUI conUI;
	private MainUI mainUI;
	private JoinUI joinUI;
	
	//ConnctionUI 객체 주소를 받는 생성자
	public ConnectionUIManager(ConnectionUI conUI) {
		this.conUI = conUI;
		cm.conStart();
	}
	
	//로그인 기능
	public boolean login(String[] loginInfo) {
		boolean result = false;
		//서버에 id와 pw를 확인하여 로그인 정보 확인
		Object[] request = {"conui.login", loginInfo}; 
		result = (Boolean)cm.sendRequest(request);
		if(result) result = true;
		return result;
	}

	//password 힌트 출력하는 이벤트창 출현 기능
	public String pwHint(String id) {
		String result = null;
		Object[] request = {"conui.pwHint", id};
		result = (String)cm.sendRequest(request);
		return result;
	}
	
	//회원가입 창으로 이동
	public void joinUI() {
		//JoinUI생성 후 JoinUI 출현, conUI는 감춤
		joinUI = new JoinUI(conUI, cm);
		joinUI.setVisible(true);
		conUI.setVisible(false);
	}
	
	//MainUI 창으로 이동
	public void mainUI() {
		//MainUI생성 후 MainUI 출현, conUI는 소멸
		mainUI = new MainUI(cm);
		mainUI.setVisible(true);
		conUI.dispose();
	}
}
