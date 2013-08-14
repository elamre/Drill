package com.drill;

import java.util.List;

public class MainMenuScreen extends GLScreen {

	Camera2D camera;
	SpriteBatcher batcher;
	Rectangle playRectangle;
	Vector2 touchPoint;

	public MainMenuScreen(Game game) {
		super(game);
		camera = new Camera2D(glGraphics, 288, 448);
		batcher = new SpriteBatcher(glGraphics, 2);
		playRectangle = new Rectangle(288 / 2, 448 / 2, 150, 100);
		touchPoint = new Vector2();

	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				touchPoint.set(event.x, event.y);
				camera.touchToWorld(touchPoint);

				if (OverlapTester.pointInRectangle(playRectangle, touchPoint)) {
					game.setScreen(new GameScreen(game));
					return;
				}

			}
		}

	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);

		batcher.beginBatch(Assets.background);
		batcher.drawSprite(288 / 2, 448 / 2, 288, 448, Assets.backgroundRegion);
		batcher.endBatch();

		batcher.beginBatch(Assets.startButton);
		batcher.drawSprite(288/2, 448/2, 150, 100, Assets.startButtonRegion);
		batcher.endBatch();

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
