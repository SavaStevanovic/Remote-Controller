import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;

import com.google.gson.Gson;

public class MouseHandler implements JsonHandler {
	private int MAX_Y;
	private int MAX_X;
	public Robot robot;
	public static final Gson gson = new Gson();

	public MouseHandler() throws AWTException {
		robot = new Robot();
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		MAX_X = gd.getDisplayMode().getWidth();
		MAX_Y = gd.getDisplayMode().getHeight();
	}

	@Override
	public void disconect() {
		// TODO Auto-generated method stub
	}

	@Override
	public void handle(String jsonMessage) {
		MouseAction mauseAction = gson.fromJson(jsonMessage, MouseAction.class);
		if (mauseAction.action == "move") {
			moveCursor(mauseAction);
		}

	}

	private void moveCursor(MouseAction mauseAction) {
		robot.mouseMove(mauseAction.x, mauseAction.y);
	}
}
