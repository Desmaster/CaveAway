package nl.tdegroot.RunningGame;

import nl.tdegroot.RunningGame.entity.Entity;
import org.newdawn.slick.BigImage;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMapPlus;

import java.util.ArrayList;
import java.util.List;

public class Level {

	private final BigImage backGround;
	private Vector2f size;
	private TiledMapPlus map;

	private List<Entity> entities = new ArrayList<Entity>();

	public Level(BigImage backGround, String mapUrl, Vector2f size) {
		this.backGround = backGround;
		this.size = size;
		try {
			map = new TiledMapPlus(mapUrl);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void update(GameContainer gameContainer, int delta) {
		for (Entity entity : entities) {
			entity.update(gameContainer, delta);
		}
	}

	public void render(Camera camera, GameContainer gameContainer, Graphics g) {
		backGround.draw(-camera.getX(), 0, size.getX(), size.getY());
		map.render((int) -camera.getX(), 0, 0, 0, (int) size.getX(), (int) size.getY());
		for (Entity entity : entities) {
			entity.render(camera, gameContainer, g);
		}
	}

	public Vector2f getSize() {
		return size;
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

}
