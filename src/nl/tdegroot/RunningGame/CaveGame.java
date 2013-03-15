package nl.tdegroot.RunningGame;

import nl.tdegroot.RunningGame.entity.Player;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.BigImage;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class CaveGame extends BasicGame {

	Player player;
	Camera camera;
	Level level;

	public CaveGame(String title) {
		super(title);
	}

	public void init(GameContainer gameContainer) throws SlickException {
		level = new Level(new BigImage("/res/images/level.png"), "res/maps/level.tmx", new Vector2f(2560.0F, 720.0F));
		player = new Player(level, new Image("/res/images/playerAnim.png"), 49, 117);
		camera = new Camera(player, level, new Vector2f(1280f, 720f), new Rectangle(0, 0, 2560, 720));
		level.addEntity(player);
	}

	public void update(GameContainer gameContainer, int delta) throws SlickException {
		level.update(gameContainer, delta);
		logic(gameContainer);
	}

	public void logic(GameContainer gameContainer) {
		if (gameContainer.getInput().isKeyDown(Input.KEY_ESCAPE))
			stop();
		Display.setTitle("Running Game");
	}

	public void render(GameContainer gameContainer, Graphics g) throws SlickException {
		level.render(camera, gameContainer, g);
	}

	public void stop() {
		System.exit(0);
	}

	public Player getPlayer() {
		return player;
	}

	public Camera getCamera() {
		return camera;
	}

	public Level getLevel() {
		return level;
	}

}
