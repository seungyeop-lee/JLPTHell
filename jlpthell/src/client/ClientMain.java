//Client 실행 class
package client;

import client.ui.ConnectionUI;
import client.ui.MainUI;

public class ClientMain {
	/**
	 * Client 실행을 위한 main method
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectionUI connectionUI = new ConnectionUI();
		connectionUI.setVisible(true);
	}
}