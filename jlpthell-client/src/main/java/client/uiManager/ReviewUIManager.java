//복습 창 UI Manager
package client.uiManager;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JOptionPane;

import client.manager.ClientManager;
import client.ui.ReviewUI;
import vo.UserWord;

public class ReviewUIManager {
	private int reviewCount;
	private int nowCount;
	private int maxCount;
	private ArrayList<UserWord> reviewWordList;
	private UserWord wordSet;
	private boolean meanVisible;
	
	private ReviewUI reviewUI;
	private ClientManager cm;
	
	//ReviewUI와 ClientManager의 객체 주소를 받는 생성자
	public ReviewUIManager(ReviewUI reviewUI, ClientManager cm) {
		this.reviewUI = reviewUI;
		this.cm = cm;
	}
	
	//ReviewUI 객체 생성 시 실행되는 초기화 기능
	public void startReview() {
		reviewWordList = getReviewWordList();
		maxCount = reviewWordList.size();
		if(maxCount == 0) {
			JOptionPane.showMessageDialog(reviewUI, "더 이상 복습 할 단어가 없습니다.");
			reviewUI.dispose();
			reviewUI.studyUI.setVisible(true);
			return;
		}
		wordSet = reviewWordList.get(nowCount);
		reviewUI.wordButton.setText(wordSet.getWord());
		reviewUI.orderTextArea.setText(nowCount+1 + "/" + maxCount);
		reviewUI.countTextArea.setText(String.valueOf(wordSet.getCount()));
		reviewUI.setVisible(true);
	}
	
	/**
	 * 단어 버튼을 누를 때 단어였으면 뜻으로, 뜻이였으면 단어 변환
	 */
	public void wordChange() {
		if(meanVisible) {
			reviewUI.wordButton.setText(wordSet.getWord());
			meanVisible = !meanVisible;
		} else {
			reviewUI.wordButton.setText(wordSet.getMean());
			meanVisible = !meanVisible;
		}
	}
	
	/**
	 * 서버에서 학습 할 문장을 받는다.
	 * @return 학습 할 문장이 담겨있는 ArrayList<UserWord>
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<UserWord> getReviewWordList() {
		ArrayList<UserWord> result = null;
		Object[] request = {"reviewui.getreviewword", null};
		result = (ArrayList<UserWord>) cm.sendRequest(request);
		return result;
	}
	
	//현재까지 복습 상태를 서버에 저장
	public void saveNow() {
		HashSet<Integer> reviewWordNo = new HashSet<>();
		for(int i = 0; i <= reviewCount; ++i) {
			reviewWordNo.add(reviewWordList.get(i).getWordNo());
		}
		Object[] request = {"reviewui.sendreviewword", reviewWordNo};
		cm.sendRequest(request);
	}
	
	//prev 버튼 누를 때 작동
	public void prevButton() {
		if(nowCount <= 0) {
			return;
		}
		--nowCount;
		reviewUI.orderTextArea.setText(nowCount+1 + "/" + maxCount);
		wordSet = reviewWordList.get(nowCount);
		reviewUI.wordButton.setText(wordSet.getWord());
		reviewUI.countTextArea.setText(String.valueOf(wordSet.getCount()));
	}
	
	//next 버튼 누를 때 작동
	public void nextButton() {
		if(nowCount+1 == maxCount) {
			int finishResult = JOptionPane.showConfirmDialog(reviewUI, maxCount + "개 학습이 끝났습니다. 암기를 종료하시겠습니까?");
			if(finishResult == JOptionPane.YES_NO_OPTION) {
				saveNow();
				reviewUI.studyUI.setVisible(true);
				reviewUI.dispose();
			}
			return;
		}
		
		if(reviewCount < ++nowCount) {
			++reviewCount;
		}
		
		reviewUI.orderTextArea.setText(nowCount+1 + "/" + maxCount);
		wordSet = reviewWordList.get(nowCount);
		reviewUI.wordButton.setText(wordSet.getWord());
		reviewUI.countTextArea.setText(String.valueOf(wordSet.getCount()));
	}
}