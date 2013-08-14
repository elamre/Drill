package com.drill.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.drill.game.entities.Block;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.drill.main.Assets;

public class WorldRenderer {

	static final float FRUSTRUM_WIDTH = 9;
	static final float FRUSTRUM_HEIGHT = 14;

	OrthographicCamera cam;
	SpriteBatch spriteBatch;
	World world;
	Random rand;

	public WorldRenderer(SpriteBatch batcher,
	                     World world) {
		this.spriteBatch = batcher;
		this.world = world;
		this.cam = new OrthographicCamera();
		cam.setToOrtho(false, FRUSTRUM_WIDTH, FRUSTRUM_HEIGHT);
		this.cam.position.y = this.world.player.position.y;
		rand = new Random();
	}

	public void render() {
		// TODO Auto-generated method stub
		if (world.player.position.y < cam.position.y) {
			cam.position.y = world.player.position.y;
		}
		spriteBatch.setProjectionMatrix(cam.combined);
		renderBackground();
		renderObjects();
	}

	public void renderBackground() {
		spriteBatch.begin();
		spriteBatch.draw(Assets.backgroundRegion, cam.position.x, cam.position.y);
		spriteBatch.end();
	}

	public void renderObjects() {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		renderPlayer();
		renderBlocks();
		renderButtons();
		spriteBatch.end();

	}

	public void renderButtons() {
		// TODO Auto-generated method stub
		spriteBatch.draw(Assets.arrowButton, 1, world.player.position.y - 6);
		spriteBatch.draw(Assets.arrowButton, 3, world.player.position.y - 6);
		spriteBatch.draw(Assets.arrowButton, 5, world.player.position.y - 6);
		spriteBatch.draw(Assets.arrowButton, 3, world.player.position.y - 6);
/*		batcher.drawSprite(3, world.player.position.y - 6, 2, 2, Assets.arrowButton);
		batcher.drawSprite(1, world.player.position.y - 6, 2, 2, 180 + 90, Assets.arrowButton);
		batcher.drawSprite(5, world.player.position.y - 6, 2, 2, 90, Assets.arrowButton);
		batcher.drawSprite(8, world.player.position.y - 6, 2, 2, Assets.digButton);*/
	}

	private void renderBlocks() {
		// TODO Auto-generated method stub
		int len = world.blocks.size;
		for (int i = 0; i < len; i++) {
			Block block = world.blocks.get(i);

			if (block.type == Block.BLOCK_TYPE_X) {
				spriteBatch.draw(Assets.xBlockRegion, block.position.x, block.position.y);
				//batcher.drawSprite(block.position.x, block.position.y, Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT, Assets.xBlockRegion);
			} else if (block.type == Block.BLOCK_TYPE_Y) {
				spriteBatch.draw(Assets.yBlockRegion, block.position.x, block.position.y);
				//batcher.drawSprite(block.position.x, block.position.y,Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT,Assets.yBlockRegion);
			} else if (block.type == Block.BLOCK_TYPE_J) {
				spriteBatch.draw(Assets.jBlockRegion, block.position.x, block.position.y);
				//batcher.drawSprite(block.position.x, block.position.y,	Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT,Assets.jBlockRegion);
			} else if (block.type == Block.BLOCK_TYPE_Z) {
				spriteBatch.draw(Assets.zBlockRegion, block.position.x, block.position.y);
				//batcher.drawSprite(block.position.x, block.position.y,Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT,Assets.zBlockRegion);
			}
		}

	}

	private void renderPlayer() {
		// TODO Auto-generated method stub
		spriteBatch.draw(Assets.playerRegion,world.player.position.x, world.player.position.y);
		//batcher.drawSprite(world.player.position.x, world.player.position.y, 1,	1, Assets.playerRegion);
	}

}
