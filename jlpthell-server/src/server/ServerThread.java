//서버 Thread, MainFunction과 ChatFunction을 이여주는 객체
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable {

	private MainFunction mf;
	private ArrayList<ServerThread> stList;
	private String id;
	private String grade;
	private Socket mainSocket;
	private ServerSocket chatSS;
	private ChatFunction cf;
	private Socket chatSocket;
	
	//Main용 socket과, 채팅용 Serversocket, 실행된 Thread의 객체가 저장되어 있는 ArrayList를 매개변수로 받는 생성자
	public ServerThread(Socket mainSocket, ServerSocket chatSS, ArrayList<ServerThread> stList) {
		this.stList = stList;
		this.chatSS = chatSS;
		this.mainSocket = mainSocket;
	}
	
	//MainFunction을 Thread로 실행
	@Override
	public void run() {
		mf = new MainFunction(mainSocket, this);
		Thread mft = new Thread(mf);
		mft.start();
		System.out.println("[INFO] Thread 실행 완료(MainFunction)");
	}
	
	//MainFunction에서 채팅 시작 명령이 들어오면 접속시작
	public boolean chatStart() {
		boolean result = false;
		try {
			//클라이언트와 채팅용 소켓 접속
			System.out.println("[INFO] Client 접속 대기(Chat)");
			chatSocket = chatSS.accept();
			System.out.println("[INFO] Client 접속 성공(Chat)");
			
			//채팅용 객체 생성 및 Thread로 실행
			cf = new ChatFunction(chatSocket, this);
			Thread cft = new Thread(cf);
			cft.start();
		} catch (IOException e) {
			e.printStackTrace();
			close();
		}
		return result;
	}
	
	//예외발생시 자원 반환
	private void close() {
		try {
			if(chatSS != null) chatSS.close();
			if(chatSocket != null) chatSocket.close();
			if(mainSocket != null) mainSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket getChatSocket() {
		return chatSocket;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public ArrayList<ServerThread> getStList() {
		return stList;
	}
	
	public ChatFunction getCf() {
		return cf;
	}
}
