//암기 창 UI Manager
package client.uiManager;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JOptionPane;

import client.manager.ClientManager;
import client.ui.MemUI;
import client.vo.UserWord;

public class MemUIManager {
	private int studyCount;
	private int nowCount;
	private ArrayList<UserWord> studyWordList;
	private UserWord wordSet;
	private boolean meanVisible;
	
	private MemUI memUI;
	private ClientManager cm;
	
	//MemUI와 ClientManager의 객체 주소를 받는 생성자
	public MemUIManager(MemUI memUI, ClientManager cm) {
		this.memUI = memUI;
		this.cm = cm;
	}
	
	//MemUI를 시작 할 때 시작
	public void startMem() {
		studyWordList = getStudyWordList();
		wordSet = studyWordList.get(nowCount);
		memUI.wordButton.setText(wordSet.getWord());
		memUI.orderTextArea.setText(nowCount+1 + "/20");
	}
	
	/**
	 * 단어 버튼을 누를 때 단어였으면 뜻으로, 뜻이였으면 단어로 변환
	 */
	public void wordChange() {
		if(meanVisible) {
			memUI.wordButton.setText(wordSet.getWord());
			meanVisible = !meanVisible;
		} else {
			memUI.wordButton.setText(wordSet.getMean());
			meanVisible = !meanVisible;
		}
	}
	
	/**
	 * next 버튼을 누르면 다음 단어로 넘어 감
	 */
	public void nextButton() {
		if(nowCount >= 19) {
			int finishResult = JOptionPane.showConfirmDialog(memUI, "20개 학습이 끝났습니다. 암기를 종료하시겠습니까?");
			if(finishResult == JOptionPane.YES_NO_OPTION) {
				saveNow();
				memUI.studyUI.setVisible(true);
				memUI.dispose();
			}
			return;
		}
		if(studyCount < ++nowCount) {
			++studyCount;
		}
		memUI.orderTextArea.setText(nowCount+1 + "/20");
		wordSet = studyWordList.get(nowCount);
		memUI.wordButton.setText(wordSet.getWord());
	}
	
	/**
	 * prev 버튼을 누르면 그전 단어로 돌아 감
	 */
	public void prevButton() {
		if(nowCount <= 0) {
			return;
		}
		--nowCount;
		memUI.orderTextArea.setText(nowCount+1 + "/20");
		wordSet = studyWordList.get(nowCount);
		memUI.wordButton.setText(wordSet.getWord());
	}
	
	
	public void saveNow() {
		HashSet<Integer> studyWordNo = new HashSet<>();
		for(int i = 0; i <= studyCount; ++i) {
			studyWordNo.add(studyWordList.get(i).getWordNo());
		}
		Object[] request = {"memui.sendstudyword", studyWordNo};
		cm.sendRequest(request);
	}
	
	/**
	 * 서버에서 학습 할 문장을 받는다.
	 * @return 학습 할 문장이 담겨있는 ArrayList<UserWord>
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<UserWord> getStudyWordList() {
		ArrayList<UserWord> result = null;
		Object[] request = {"memui.getstudyword", null};
		result = (ArrayList<UserWord>) cm.sendRequest(request);
		return result;
	}
}
