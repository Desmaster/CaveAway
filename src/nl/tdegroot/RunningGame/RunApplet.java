package nl.tdegroot.RunningGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class RunApplet {

	public static void main(String[] args) throws SlickException {
		AppGameContainer game = new AppGameContainer(new CaveGame("Running Game"));

		game.setDisplayMode(1280, 720, false);
		game.setTargetFrameRate(60);
		game.setMaximumLogicUpdateInterval(17);
		game.setMinimumLogicUpdateInterval(17);
		game.start();
	}
}
