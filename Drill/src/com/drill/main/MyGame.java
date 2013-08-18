package com.drill.main;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.drill.game.Assets;
import com.drill.game.entity.Block;
import com.drill.game.entity.EntityManager;

public class MyGame extends Game {

    public SpriteBatch batch;
    private OrthographicCamera camera;
    private EntityManager entityManager;

    @Override
    public void create() {
        Assets.getAssets();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        entityManager = new EntityManager(this);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glDisable(GL10.GL_CULL_FACE);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        draw();
        update(Gdx.graphics.getDeltaTime());
    }

    public void draw() {
        batch.begin();
        entityManager.draw(batch);
        batch.end();
    }

    public void update(float deltaT) {
        camera.update();
        entityManager.update(deltaT);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
