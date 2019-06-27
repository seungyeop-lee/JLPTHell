//Setting UI Manager
package client.uiManager;

import client.manager.ClientManager;
import client.ui.ConnectionUI;
import client.ui.MainUI;

public class SettingUIManager {

	private MainUI mainUI;
	private ClientManager cm;
	
	//mainUI와 ClientManager의 객체 주소를 받는 생성자
	public SettingUIManager(MainUI mainUI, ClientManager cm) {
		this.mainUI = mainUI;
		this.cm = cm;
	}
	
	//급수 변경 기능
	public int changeGrade(String grade) {
		int result = -1;
		Object[] request = {"settingui.changegrade", grade};
		result = (Integer)cm.sendRequest(request);
		return result;
	}
	
	//학습 이력 초기화 기능
	public boolean initialize() {
		boolean result = false;
		Object[] request = {"settingui.initialize", null};
		result = (Boolean)cm.sendRequest(request);
		return result;
	}
	
	//사용자 삭제 기능
	public boolean deleteUser() {
		boolean result = false;
		Object[] request = {"settingui.deleteuser", null};
		result = (Boolean)cm.sendRequest(request);
		return result;
	}
	
	//로그아웃 기능
	public void logout() {
		mainUI.dispose();
		ConnectionUI connectionUI = new ConnectionUI();
		connectionUI.setVisible(true);
	}
}
