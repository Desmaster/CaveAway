package nl.tdegroot.RunningGame;

import java.util.ArrayList;
import java.util.List;

import nl.tdegroot.RunningGame.entity.Entity;

import org.newdawn.slick.BigImage;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.Base64;
import org.newdawn.slick.tiled.TiledMap;

public class Level {

	private final BigImage background;
	private Vector2f size;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Rectangle> collidables = new ArrayList<Rectangle>();

	private TiledMap map;

	public Level(BigImage background, Vector2f size) {
		this.background = background;
		this.size = size;
		try {
			map = new TiledMap("res/maps/level.tmx");
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
//		background.draw(-camera.getX(), 0, size.getX(), size.getY());
		map.render(0, 50);
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

	public boolean collides(Rectangle rect) {
		for (Rectangle collidable : collidables) {
			if (rect.intersects(collidable)) {
				if (rect.getMaxX() > collidable.getMinX()) {
					// rect.setX(rect.getX() - (collidable.getMinX() -
					// rect.getMaxX()));
				}
				if (rect.getMinX() < collidable.getMaxX()) {
					// rect.setX(rect.getX() + (rect.getMinX() -
					// collidable.getMaxX()));
				}
				if (rect.getMaxY() > collidable.getMinY()) {
					rect.setY(collidable.getMinY() - 1 - rect.getHeight());
				}
				if (rect.getMinX() < collidable.getMaxX()) {
					// rect.setY(rect.getY() + (rect.getMinY() -
					// collidable.getMaxY()));
				}
				return true;
			}

		}
		return false;
	}

	public boolean collides(float x, float y) {
		for (Rectangle collidable : collidables) {
			if (collidable.contains(x, y))
				return true;
		}
		return false;
	}

}
