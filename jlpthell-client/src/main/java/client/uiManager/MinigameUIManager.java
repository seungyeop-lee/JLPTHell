//미니게임 창 UI Manager
package client.uiManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JOptionPane;

import client.manager.ClientManager;
import client.ui.MinigameUI;
import client.vo.UserWord;

public class MinigameUIManager {
	private UserWord[] buttonWordList = new UserWord[20];
	private ArrayList<UserWord> meanInButtonList = new ArrayList<>(); 
	private MinigameUI miniUI;
	private ClientManager cm;
	private int selected = -1;
	private int correctCount;
	
	//MinigameUI와 ClientManager의 객체 주소를 받는 생성자
	public MinigameUIManager(MinigameUI miniUI, ClientManager cm) {
		this.miniUI = miniUI;
		this.cm = cm;
	}
	
	//MinigameUI 객체 생성 시 실행되는 초기화 기능
	public void miniGameStart() {
		ArrayList<UserWord> gameWordList = getGameWordList();
		if(gameWordList.size() < 10) {
			JOptionPane.showMessageDialog(miniUI, "학습한 단어의 갯수가 10개 미만입니다.");
			miniUI.dispose();
			miniUI.mainUI.setVisible(true);
			return;
		} else {
			randomSet(gameWordList);
			miniUI.setVisible(true);
		}
	}
	
	//서버에서 받은 단어를 랜덤하게 버튼에 뿌린다
	public void randomSet(ArrayList<UserWord> gameWordList) {
		HashSet<Integer> tempHashSet = new HashSet<>();
		Random r = new Random();
		int temp = -1;
		
		for(UserWord u : gameWordList) {
			for(int i = 0 ; i < 2; ++i) {
				do {
					temp = r.nextInt(20);
				} while(!tempHashSet.add(temp));
				buttonWordList[temp] = u;
				if(i == 0) miniUI.btnList[temp].setText(u.getWord());
				else miniUI.btnList[temp].setText(u.getMean());
			}
		}
	}
	
	/**
	 * 서버에서 게임에 이용할 단어를 받아온다.
	 * @return 게임에 이용 할 단어가 들어가 있는  ArrayList
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<UserWord> getGameWordList() {
		ArrayList<UserWord> result = new ArrayList<>();
		Object[] request = {"minigameui.getgamewordlist", null};
		result = (ArrayList<UserWord>) cm.sendRequest(request);
		return result;
	}
	
	//사용자가 버튼을 누르면 현 상태에 따라서 정답 유무를 판단
	public void clickbutton(int clickButton) {
		miniUI.btnList[clickButton].setEnabled(false);
		if(selected == -1) {
			selected = clickButton;
		} else if (selected != -1) {
			if(buttonWordList[selected] == buttonWordList[clickButton]) {
				correctCount++;
				selected = -1;
				if(correctCount == 10) {
					JOptionPane.showMessageDialog(miniUI, "모두 맞추셨습니다!");
					miniUI.dispose();
					miniUI.mainUI.setVisible(true);
				}
				return;
			} else {
				JOptionPane.showMessageDialog(miniUI, "틀렸습니다!!!");
				miniUI.btnList[clickButton].setEnabled(true);
				miniUI.btnList[selected].setEnabled(true);
				selected = -1;
				return;
			}
		}
	}
}