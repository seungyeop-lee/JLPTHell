//회원가입 창 UI Manager
package client.uiManager;

import client.manager.ClientManager;
import client.vo.User;

public class JoinUIManager {
	private ClientManager cm;
	
	//ClientManager 객체를 받는 생성자
	public JoinUIManager(ClientManager cm) {
		this.cm = cm;
	}
	
	//회원가입 기능
	public boolean join(User user) {
		boolean result = false;
		Object[] request = {"joinui.join", user};
		result = (Boolean)cm.sendRequest(request);
		return result;
	}
}