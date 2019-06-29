//메인 창 UI Manager
package client.uiManager;

import java.util.ArrayList;

import client.manager.ClientManager;
import client.ui.ChatUI;
import client.ui.ConnectionUI;
import client.ui.MainUI;
import client.ui.MinigameUI;
import client.ui.SettingUI;
import client.ui.StudyUI;
import client.ui.UserlistUI;
import client.vo.UserForList;

public class MainUIManager {
	private MainUI mainUI;
	private ClientManager cm;
	private StudyUI studyUI;
	private MinigameUI minigameUI;
	private SettingUI settingUI;
	private UserlistUI userlistUI;
	private ChatUI chatUI;
	private String[] userInfo;
	
	//MainUI와 ClientManager 객체 주소를 받는 생성자
	public MainUIManager(MainUI mainUI, ClientManager cm) {
		this.mainUI = mainUI;
		this.cm = cm;
	}

	//StudyUI 창으로 이동
	public void studyUI() {
		studyUI = new StudyUI(mainUI, cm);
		mainUI.setVisible(false);
		studyUI.setVisible(true);
	}
	
	//MiniGameUI 창으로 이동
	public void miniGameUI() {
		mainUI.setVisible(false);
		minigameUI = new MinigameUI(mainUI, cm);
	}
	
	//SettingUI 창으로 이동
	public void settingUI() {
		settingUI = new SettingUI(mainUI, cm);
		mainUI.setVisible(false);
		settingUI.setVisible(true);
	}
	
	//UserListUI 창으로 이동
	@SuppressWarnings("unchecked")
	public void userListUI() {
		Object[] request = {"userlistui.userlist", null};
		ArrayList<UserForList> userList = (ArrayList<UserForList>) cm.sendRequest(request);
		userlistUI = new UserlistUI(mainUI, userList);
		mainUI.setVisible(false);
		userlistUI.setVisible(true);
	}
	
	//logout후 로그인 창으로 이동
	public void logout() {
		mainUI.dispose();
		ConnectionUI connectionUI = new ConnectionUI();
		connectionUI.setVisible(true);
	}
	
	//MainUI 실행 시 실행되는 메소드
	public void startMainUI() {
		userInfo = getUserInfo();
		mainUI.setTitle("ID: " + userInfo[0] + ", Grade: " + userInfo[1]);
	}
	
	//접속한 유저의 id와 grade를 서버에서 가져온다.
	private String[] getUserInfo() {
		String[] result = null;
		Object[] request = {"mainui.getuserinfo", null};
		result = (String[]) cm.sendRequest(request);
		return result;
	}
	
	//Chating 창을 열고, 채팅을 시작한다.
	public void chatStart() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Object[] request = {"mainui.chatstart", null};
				boolean result = (boolean)cm.sendRequest(request);
			}
		});
		thread.start();
		chatUI = new ChatUI(userInfo[0]);
		chatUI.setVisible(true);
	}
}
