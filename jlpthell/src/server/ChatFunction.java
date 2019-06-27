//채팅 담당 객체
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ChatFunction implements Runnable {

	private Socket socket;
	private ServerThread st;
	private InputStream is;
	private DataInputStream dis;
	private OutputStream os;
	private DataOutputStream dos;
	
	public ChatFunction(Socket socket, ServerThread st) {
		this.socket = socket;
		this.st = st;
		
		try {
			is = socket.getInputStream();
			dis = new DataInputStream(is);
			os = socket.getOutputStream();
			dos = new DataOutputStream(os);
		} catch (IOException e) {
			e.printStackTrace();
			close();
		}
	}
	
	//클라이언트에서 보내는 메시지를 항시 받는 Thread
	@Override
	public void run() {
		Thread rThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(socket.isConnected()) {
						System.out.println("[INFO] 클라이언트의 입력을 기다립니다.");
						String recievedMessage = dis.readUTF();
						//접속되어있는 Client에 매시지 전달 지시
						for(ServerThread st : st.getStList()) {
							if(st.getChatSocket() != null && !st.getChatSocket().isClosed()) {
								st.getCf().sendMessage(recievedMessage);
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
					close();
				}
			}
		});
		rThread.start();
	}
	
	//client에 메시지를 보내는 메소드
	public void sendMessage(String recievedMessage) {
		try {
			dos.writeUTF(recievedMessage);
		} catch (IOException e) {
			e.printStackTrace();
			close();
		}
	}
	
	//예외 발생 시 자원 반환
	private void close() {
		try {
			if(dis != null) dis.close();
			if(dos != null) dos.close();
			if(is != null) is.close();
			if(os != null) os.close();
			if(socket != null) socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
