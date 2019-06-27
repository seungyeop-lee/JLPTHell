//채팅 창 UI Manager
package client.uiManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JTextField;

import client.ui.ChatUI;

public class ChatUIManager {
	
	private int port = 8889;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	private ChatUI chatUI;
	
	//ChatUI 객체 주소를 받는 생성자
	public ChatUIManager(ChatUI chatUI) {
		this.chatUI = chatUI;
		
		//서버에 채팅용 소켓 연결
		try {
			socket = new Socket("localhost", port);
			System.out.println("[INFO] Server 접속 성공 (Chat)");
			
			is = socket.getInputStream();
			os = socket.getOutputStream();
			System.out.println("[INFO] Stream 생성 성공 (Chat)");
			
			dis = new DataInputStream(is);
			dos = new DataOutputStream(os);
			System.out.println("[INFO] Data stream 생성 성공 (Chat)");

		} catch (IOException e) {
			e.printStackTrace();
			close();
		}
	}
	
	//사용자의 입력 값을 서버에 전송
	public void sendText(String id) {
		JTextField inputTextField = chatUI.getInputTextField();
		String sendText = inputTextField.getText();
		try {
			if(chatUI.isActive()) {
				dos.writeUTF(id + ": " + sendText);
			} else {
				dos.writeUTF("[" + id + "]님이 퇴장하셨습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
			close();
		}
		inputTextField.setText("");
	}
	
	//서버에서 입력이 있을 때 받아와서 뿌려줌
	public void readyStart(String id) {
		Thread rThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					dos.writeUTF("[" + id + "]님이 입장하셨습니다.");
					while(true) {
						String recieve = dis.readUTF();
						chatUI.getOutputTextArea().insert(recieve + "\n", 0);
					}
				} catch (IOException e) {
					e.printStackTrace();
					close();
				}
			}
		});
		rThread.start();
	}
	
	//예외 발생 시 열려있는 스트림 및 소켓 반환
	public void close() {
		try {
			if(dos != null)	dos.close();
			if(dis != null) dis.close();
			if(os != null) os.close();
			if(is != null) is.close();
			if(socket != null) socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}