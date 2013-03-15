package nl.tdegroot.RunningGame;

import nl.tdegroot.RunningGame.entity.Player;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Camera {

	private final Player player;
	private final Vector2f size;
	private final Level level;
	private final Rectangle bounds;

	public Camera(Player player, Level level, Vector2f size, Rectangle bounds) {
		this.player = player;
		this.level = level;
		this.size = size;
		this.bounds = bounds;
	}

	public float getX() {
		float x = player.getPosition().getCenterX() - size.getX() / 2;

		if (x < bounds.getX())
			x = bounds.getX();
		if (x > bounds.getWidth() / 2)
			x = bounds.getWidth() / 2;

		return x;
	}

}
