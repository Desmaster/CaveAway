package nl.tdegroot.RunningGame.entity;

import nl.tdegroot.RunningGame.Camera;
import nl.tdegroot.RunningGame.Level;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMapPlus;

public class Entity {

	public static final int ANIMATION_TYPE_IDLE = 0;
	public static final int ANIMATION_TYPE_JUMP = 1;
	public static final int ANIMATION_TYPE_RUN = 2;

	protected SpriteSheet sheet;
	protected Rectangle frameSize;
	protected final Level level;
	protected Rectangle position;
	protected Vector2f velocity = new Vector2f();
	protected float horizontalSpeed = 5f;
	protected float gravity = 0.75f;
	protected float jumpSpeed = 30f * gravity;
	protected float friction = 0.45f;
	protected boolean isJumping = false;
	protected boolean wasJumping = false;
	protected boolean isMoving = false;
	protected boolean wasMoving = false;
	protected int animIndex = 0;
	protected int animCount = 0;
	protected int animType = 0;
	protected int frame = 0;

	public Entity(Level level, Image image, int width, int height) {
		position = new Rectangle(0, 0, width, height);
		frameSize = new Rectangle(0, 0, width, height);
		animCount = (int) (image.getWidth() / frameSize.getWidth());
		sheet = new SpriteSheet(image, width, height);
		this.level = level;
	}

	public void update(GameContainer gameContainer, int delta) {
		velocity.x += horizontalSpeed * 0.85f;
		move();
		if (frame % (102 / delta) == 0) {
			animIndex = ((animIndex + 1) % animCount);
		}
	}

	public void move() {
		velocity.x *= friction;
		if (Math.abs(velocity.x) < friction)
			velocity.x = 0;

		if (isJumping) {
			velocity.y += gravity;
		} else {
			velocity.y = 0;
		}

		float newX = position.getX() + velocity.x;
		float newY = position.getY() + velocity.y;

		if (newY > 720 - position.getHeight() - 16)
			newY = 720 - position.getHeight() - 16;

		Rectangle newPos = collision(newX, newY);

		position = newPos;

		wasJumping = isJumping;
		wasMoving = isMoving;

		isJumping = newY < 720 - getPosition().getHeight() - 16;
		isMoving = Math.abs(velocity.x) > 0;

		if (wasJumping && ! isJumping) {
			animType = isMoving ? ANIMATION_TYPE_RUN : ANIMATION_TYPE_IDLE;
			animIndex = 0;
		} else if (wasMoving && ! isMoving) {
			animType = isJumping ? ANIMATION_TYPE_JUMP : ANIMATION_TYPE_IDLE;
			animIndex = 0;
		} else if (! wasMoving && isMoving) {
			animType = ANIMATION_TYPE_RUN;
			animIndex = 0;
		}
		frame++;
	}

	public Rectangle collision(float x, float y) {
		float xx = x / 16;
		float yy = y / 16;
		float ya = yy + (getPosition().getHeight() / 16);
		int i = 0;
		TiledMapPlus map = level.map;
		if (!(xx < 0 || xx > map.getWidth()))
		i = map.getTileId((int) xx, (int) ya, 0);
		if (i == 1) {
			System.out.println("Surface!");
		}
		return new Rectangle(xx * 16, yy * 16, position.getWidth(), position.getHeight());
	}

	public void render(Camera camera, GameContainer gameContainer, Graphics g) {
		sheet.startUse();
		sheet.renderInUse((int) (position.getX() - camera.getX()), (int) position.getY(), animIndex, animType);
		sheet.endUse();
	}

	public Rectangle getPosition() {
		return position;
	}

}
