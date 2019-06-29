//학습 창 UI Manager
package client.uiManager;

import java.util.ArrayList;

import client.manager.ClientManager;
import client.ui.HistoryUI;
import client.ui.MemUI;
import client.ui.ReviewUI;
import client.ui.StudyUI;
import client.vo.UserWord;

public class StudyUIManager {

	private StudyUI studyUI;
	private ClientManager cm;
	private MemUI memUI;
	private ReviewUI reviewUI;
	private HistoryUI historyUI;
	
	//StudyUI와 ClientManager의 객체 주소를 받는 생성자
	public StudyUIManager(StudyUI studyUI, ClientManager cm) {
		this.studyUI = studyUI;
		this.cm = cm;
	}
	
	//암기 창 이동
	public void memUI() {
		memUI = new MemUI(studyUI, cm);
		studyUI.setVisible(false);
		memUI.setVisible(true);
	}
	
	//복습 창 이동
	public void reviewUI() {
		studyUI.setVisible(false);
		reviewUI = new ReviewUI(studyUI, cm);
	}
	
	//학습 이력 창 이동
	@SuppressWarnings("unchecked")
	public void historyUI() {
		Object[] request = {"historyui.userwordlist", null};
		ArrayList<UserWord> userWordList = (ArrayList<UserWord>) cm.sendRequest(request);
		historyUI = new HistoryUI(userWordList, studyUI);
		studyUI.setVisible(false);
		historyUI.setVisible(true);
	}
}
