//client에서 받은 명령을 해석하여 각 ServerManager에 명령을 전달
package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import server.manager.HistoryUIServerManager;
import server.manager.MiniGameUIServerManager;
import server.service.*;
import vo.User;
import vo.UserForList;
import vo.UserWord;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MainFunction implements Runnable {
	private ServerThread st;
	private Socket s;
	private InputStream nis;
	private OutputStream nos;
	private ObjectInputStream nois;
	private ObjectOutputStream noos;
	@Autowired private JoinUIService juis;
	@Autowired private ConnectionUIService cuis;
	@Autowired private UserlistUIService uuis;
	@Autowired private SettingUIService suis;
	@Autowired private HistoryUIServerManager huism;
	@Autowired private MemUIService muis;
	@Autowired private ReviewUIService ruis;
	@Autowired private MiniGameUIServerManager miniuism;
	
	
	public MainFunction(Socket s, ServerThread st) {
		this.st = st;
		this.s = s;
		
		//Stream 생성
		try {
			//in, out Stream 생성
			nos = s.getOutputStream();
			nis = s.getInputStream();
			System.out.println("[INFO] Stream 생성(MainFunction)");
			
			//in, out ObjectStream 생성
			noos = new ObjectOutputStream(nos);
			nois = new ObjectInputStream(nis);
			System.out.println("[INFO]  Object stream 생성(MainFunction)");
		} catch (IOException e) {
			close();
			e.printStackTrace();
		}
	}
	
	//사용자에게 받은 명령셋을 받고, 그에 맞는 명령을 실행
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		try {
			while(true) {
				Object[] readObjects = (Object[]) nois.readObject();
				String command = (String)readObjects[0];
				Object content = readObjects[1];
				switch(command) {
				case "joinui.join":
					boolean joinResult = juis.join((User)content);
					noos.writeObject(joinResult);
					break;
				case "conui.pwHint":
					String pwHintResult = cuis.pwHint((String)content);
					noos.writeObject(pwHintResult);
					break;
				case "conui.login":
					boolean loginResult = false;
					String[] loginInfo = (String[])content;
					String userGrade = cuis.login(loginInfo);
					if(userGrade != null) {
						st.setId(loginInfo[0]);
						st.setGrade(userGrade);
						loginResult = true;
					}
					noos.writeObject(loginResult);
					break;
				case "mainui.getuserinfo":
					String[] getUserInfoResult = {st.getId(), st.getGrade()};
					noos.writeObject(getUserInfoResult);
					break;
				case "mainui.chatstart":
					boolean chatStartResult = st.chatStart();
					noos.writeObject(chatStartResult);
					break;
				case "userlistui.userlist":
					ArrayList<UserForList> userListResult = uuis.userList();
					noos.writeObject(userListResult);
					break;
				case "settingui.changegrade":
					int changeGradeResult = suis.changeGrade(st.getId(), (String)content);
					if(changeGradeResult == 1) {
						st.setGrade((String)content);
					}
					noos.writeObject(changeGradeResult);
					break;
				case "settingui.initialize":
					boolean iniResult = suis.initialize(st.getId(), st.getGrade());
					noos.writeObject(iniResult);
					break;
				case "settingui.deleteuser":
					boolean deleteUserResult = suis.deleteUser(st.getId());
					noos.writeObject(deleteUserResult);
					break;
				case "historyui.userwordlist":
					ArrayList<UserWord> userWordListResult = huism.userWordList(st.getId());
					noos.writeObject(userWordListResult);
					break;
				case "memui.getstudyword":
					List<UserWord> getStudyWordResult = muis.getStudyWord(st.getId(), st.getGrade());
					noos.writeObject(getStudyWordResult);
					break;
				case "memui.sendstudyword":
					muis.saveStudyWord((HashSet<Integer>)content, st.getId());
					noos.writeObject(null);
					break;
				case "reviewui.getreviewword":
					List<UserWord> getReviewWordResult = ruis.getReviewWord(st.getId(), st.getGrade());
					noos.writeObject(getReviewWordResult);
					break;
				case "reviewui.sendreviewword":
					ruis.saveReviewWord((HashSet<Integer>)content, st.getId());
					noos.writeObject(null);
					break;
				case "minigameui.getgamewordlist":
					ArrayList<UserWord> getGameWordListResult = miniuism.getGameWordList(st.getId(), st.getGrade());
					noos.writeObject(getGameWordListResult);
					break;
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			close();
		}
	}
	
	//예외 발생 시 스레드 및 소켓 반환
	private void close() {
		try {
			if(nois != null) nois.close();
			if(noos != null) noos.close();
			if(nis != null) nis.close();
			if(nos != null) nos.close();
			if(s != null) s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
