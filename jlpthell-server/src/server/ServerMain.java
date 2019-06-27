//Server 실행  class
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {
	private int mainPort = 8888;
	private int chatPort = 8889;
	private ServerSocket mainSS;
	private ServerSocket chatSS;
	private ArrayList<ServerThread> stList = new ArrayList<>();

	public ServerMain() {
		try {
			//server 소켓 생성
			mainSS = new ServerSocket(mainPort);
			chatSS = new ServerSocket(chatPort);
			while(true) {
				//Client의 접속 대기
				System.out.println("[INFO] Client 접속 대기(Main)");
				Socket mainSocket = mainSS.accept();
				System.out.println("[INFO] Client 접속 성공(Main)");
				
				//Socket을 가지고 있는 Thread 생성
				ServerThread thread = new ServerThread(mainSocket, chatSS, stList);
				stList.add(thread);
				Thread t = new Thread(thread);
				t.start();
				System.out.println("[INFO] Thread 실행 완료(ServerThread)");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * server 실행을 위한 main method
	 * @param args
	 */
	public static void main(String[] args) {
		new ServerMain();
	}
}