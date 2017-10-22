import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MessageHandler extends Thread {

	protected Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private JsonHandler jsonHandler;

	public MessageHandler(Socket socket, JsonHandler jsonHandler) {
		this.socket = socket;
		this.jsonHandler = jsonHandler;
	}

	@Override
	public void run() {
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				String jsonMessage = in.readUTF();
				System.out.println(jsonMessage.toString());
				jsonHandler.handle(jsonMessage);
			} catch (IOException e) {
				e.printStackTrace();
				jsonHandler.disconect();
				try {
					socket.close();
					return;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	protected void sendMessage(String jsonMessage) {
		try {
			System.out.println(jsonMessage);
			out.writeUTF(jsonMessage);
		} catch (IOException e) {
			e.printStackTrace();
			jsonHandler.disconect();
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
