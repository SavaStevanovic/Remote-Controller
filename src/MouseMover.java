import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

public class MouseMover {

	public static int portNumber = 4321;
	public static ServerSocket serverSocket;

	public static void main(String[] args) {
		System.out.println("Start");
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Stop");
			return;
		}
		MouseHandler mouseHandler;
		try {
			mouseHandler = new MouseHandler();
		} catch (AWTException e1) {
			e1.printStackTrace();
			System.out.println("Stop");
			return;
		}
		try {
			MessageHandler messageHandler = new MessageHandler(
					serverSocket.accept(), mouseHandler);
			messageHandler.start();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Stop");
			return;
		}
	}
}
