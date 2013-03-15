package nl.tdegroot.RunningGame.entity;

import nl.tdegroot.RunningGame.Level;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Player extends Entity {

	public Player(Level level, Image image, int width, int height) {
		super(level, image, width, height);
	}

	public void update(GameContainer gameContainer, int delta) {
		Input input = gameContainer.getInput();

		if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
			velocity.x += horizontalSpeed;
		} else if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
			velocity.x -= horizontalSpeed;
		}

		if (!isJumping && (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W))) {
			velocity.y -= jumpSpeed;
			isJumping = true;
			animType = ANIMATION_TYPE_JUMP;
			animIndex = 0;
		}
		move();
	}
}
