//Server와 socket통신하기 위한 class, 각 UI에 해당하는 Manager객체와 Server와의 매개를 담당
package client.manager;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientManager {
	private int port = 8888;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public ClientManager() {}
	
	/**
	 * 객체 생성시 소켓 생성 및 서버와 연결
	 */
	public void conStart() {
		try {
			//서버하고 socket 통신
			socket = new Socket("localhost", port);
			System.out.println("[INFO] Server 접속 성공 (Main)");
			
			//in, out Stream 연결
			is = socket.getInputStream();
			os = socket.getOutputStream();
			System.out.println("[INFO] Stream 생성 성공 (Main)");
			
			//in, out object stream 연결
			ois = new ObjectInputStream(is);
			oos = new ObjectOutputStream(os);
			System.out.println("[INFO] Object stream 생성 성공 (Main)");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 서버에 정보 요청시 사용하는 메소드
	 * @param 서버에 정보 요청 정보가 담긴 request
	 * @return 서버에서 받은 정보
	 */
	public Object sendRequest(Object[] request) {
		Object result = null;
		try {
			oos.writeObject(request);
			oos.reset();
			result = ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			close();
		}
		return result;
	}
	
	/**
	 * 예외 발생 시 소켓 닫기
	 */
	public void close() {
		try {
			if(oos != null)	oos.close();
			if(ois != null) ois.close();
			if(os != null) os.close();
			if(is != null) is.close();
			if(socket != null) socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}