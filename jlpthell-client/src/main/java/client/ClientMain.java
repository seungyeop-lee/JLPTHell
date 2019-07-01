//Client 실행 class
package client;

import client.ui.ConnectionUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ClientMain {
	/**
	 * Client 실행을 위한 main method
	 * @param args
	 */
	public static void main(String[] args) {
		installFont();
		ConnectionUI connectionUI = new ConnectionUI();
		connectionUI.setVisible(true);
	}

	private static void installFont() {
		String classPath = System.getProperty("java.class.path");
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File(classPath + "/KaiGenGothicKR-Regular.ttf"));
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}