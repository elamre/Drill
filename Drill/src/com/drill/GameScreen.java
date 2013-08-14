package com.drill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.drill.game.World;
import com.drill.game.WorldRenderer;
import com.drill.game.entities.Block;
import com.drill.game.entities.Player;
import com.drill.main.MyGame;

import java.util.List;


public class GameScreen implements Screen {

	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_OVER = 3;
	static final int GAME_LEVEL_END = 4;

	int state;
	OrthographicCamera guiCam;
	Vector2 touchPoint;
	SpriteBatch spriteBatch;
	World world;
	World.WorldListener worldListener;
	WorldRenderer renderer;
	Rectangle pauseBounds;
	Rectangle resumeBounds;
	Rectangle quitBounds;
	Rectangle downArrowRectangle;
	Rectangle leftArrowRectangle;
	Rectangle rightArrowRectangle;
	int lastFrameTime;
	String timeString;
	Rectangle digArrowRectangle;
	float sec;

	public GameScreen(MyGame game) {
		state = GAME_READY;
		guiCam = new OrthographicCamera();
		guiCam.setToOrtho(true, 288, 488);
		spriteBatch = new SpriteBatch();
		touchPoint = new Vector2();
		spriteBatch = new SpriteBatch();
		worldListener = new World.WorldListener() {
			public void jump() {

			}

			public void highJump() {

			}

			public void hit() {

			}

			public void coin() {

			}
		};
		world = new World(worldListener);
		renderer = new WorldRenderer(spriteBatch, world);
		pauseBounds = new Rectangle(0, 448 - 64, 64, 64);
		resumeBounds = new Rectangle(144 - 64, 224, 64, 64);
		quitBounds = new Rectangle(144 - 96, 224 - 64, 64, 64);
		downArrowRectangle = new Rectangle(64, 0, 64, 64);
		leftArrowRectangle = new Rectangle(0, 0, 64, 64);
		rightArrowRectangle = new Rectangle(64 + 64, 0, 64, 64);
		digArrowRectangle = new Rectangle(288 - 64, 0, 64, 64);
		lastFrameTime = 0;
		timeString = "90000";
		sec = 0;
	}

	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		if (deltaTime > 0.1f) {
			deltaTime = 0.1f;
		}
		switch (state) {
			case GAME_READY:
				updateReady();
				break;
			case GAME_RUNNING:
				updateRunning(deltaTime);
				break;
			case GAME_PAUSED:
				updatePaused();
				break;
			case GAME_OVER:
				updateGameOver();
				break;
		}

	}

	private void updateGameOver() {
		// TODO Auto-generated method stub

	}

	private void updatePaused() {
		// TODO Auto-generated method stub

	}

	private void updateRunning(float deltaTime) {
		if (Gdx.input.isTouched()) {
			if (downArrowRectangle.contains(Gdx.input.getX(), Gdx.input.getY())) {
				world.player.setLooking(Player.LOOKING_DOWN);
			}
			if (leftArrowRectangle.contains(Gdx.input.getX(), Gdx.input.getY())) {
				world.player.setLooking(Player.LOOKING_LEFT);
				for (int j = 0; j < world.blocks.size; j++) {
					Block block = world.blocks.get(j);
					if (world.player.isBlockLeft(block)) {
						break;
					} else {
						if (j == world.blocks.size - 1) {
							world.player.state = Player.PLAYER_MOVING_LEFT;
						}
					}
				}
			}
			if (rightArrowRectangle.contains(Gdx.input.getX(), Gdx.input.getY())) {
				world.player.setLooking(Player.LOOKING_RIGHT);
				for (int j = 0; j < world.blocks.size; j++) {
					Block block = world.blocks.get(j);
					if (world.player.isBlockRight(block)) {
						break;
					} else {
						if (j == world.blocks.size - 1) {
							world.player.state = Player.PLAYER_MOVING_RIGHT;
						}
					}
				}
			}

			if (digArrowRectangle.contains(Gdx.input.getX(), Gdx.input.getY())) {
				switch (world.player.getLooking()) {
					case 0:
						for (int j = 0; j < world.blocks.size; j++) {
							Block block = world.blocks.get(j);
							world.player.digDown(block);
						}
						break;
					case 1:
						for (int j = 0; j < world.blocks.size; j++) {
							Block block = world.blocks.get(j);
							world.player.digLeft(block);
						}
						break;
					case 2:
						for (int j = 0; j < world.blocks.size; j++) {
							Block block = world.blocks.get(j);
							world.player.digRight(block);
						}
						break;
				}
			}
		}
		world.update(deltaTime);

	}

	private void updateReady() {
		// TODO Auto-generated method stub
		if(Gdx.input.isTouched()){
			state = GAME_RUNNING;
		}
	}

	private void presentRunning(float deltaTime) {
		// TODO Auto-generated method stub
		// C�digo que va restando el tiempo de juego restante
		String milisecs = Float.toString(deltaTime);
		int milisecsInt = milisecs.charAt(2) + milisecs.charAt(3);
		int remainingTime = Integer.parseInt(timeString);
		String timeRemainingString = String.valueOf(remainingTime - milisecsInt);
		//Assets.font.drawText(batcher, timeRemainingString, 15, 448 - 10);
		if (remainingTime < 00000) {
			state = GAME_OVER;
		}
	}

	private void presentPaused() {
		// TODO Auto-generated method stub

	}

	private void presentLevelEnd() {
		// TODO Auto-generated method stub

	}

	private void presentGameOver() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		renderer.render();
		// TODO Auto-generated method stub
/*		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);

		renderer.render();

		guiCam.setViewportAndMatrices();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
//		batcher.beginBatch(Assets.items);
		switch (state) {
			case GAME_READY:
				presentReady();
				break;
			case GAME_RUNNING:
				presentRunning(deltaTime);
				break;
			case GAME_PAUSED:
				presentPaused();
				break;
			case GAME_LEVEL_END:
				presentLevelEnd();
				break;
			case GAME_OVER:
				presentGameOver();
				break;
		}
		spriteBatch.end();
		gl.glDisable(GL10.GL_BLEND);*/
		update(delta);
	}

	@Override
	public void resize(int i, int i2) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void show() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void hide() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
