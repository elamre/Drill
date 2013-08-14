package com.drill.menu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.drill.main.Assets;
import com.drill.main.MyGame;

/**
 * User: elmar
 * Date: 8/14/13
 * Time: 5:24 PM
 * Packate: com.drill.menu
 * Project: Drill
 */
public class MainMenu implements Screen {
	private OrthographicCamera camera;
	SpriteBatch spriteBatch;
	Rectangle playRectangle;
	private MyGame game;

	public MainMenu(MyGame game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 288, 488);
		spriteBatch = new SpriteBatch();
		playRectangle = new Rectangle(288 / 2, 448 / 2, 150, 100);

	}

	public void update(float deltaTime) {
		camera.update();
		if (Gdx.input.isTouched()) {
			if (playRectangle.contains(Gdx.input.getX(), Gdx.input.getY())) {
				game.setScreen(null);
				return;
			}
		}
	}

	@Override
	public void render(float deltaT) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		spriteBatch.setProjectionMatrix(camera.combined);
		//gl.glEnable(GL10.GL_TEXTURE_2D);

		spriteBatch.begin();
		spriteBatch.draw(Assets.backgroundRegion, 0, 0);
		spriteBatch.draw(Assets.startButtonRegion, 100, 100);
		spriteBatch.end();
		update(deltaT);
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