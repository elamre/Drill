package com.drill.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.drill.game.entities.Block;
import com.drill.game.entities.Player;
import org.lwjgl.util.Rectangle;

public class World {



	public interface WorldListener {
		public void jump();

		public void highJump();

		public void hit();

		public void coin();
	}

	public static final float WORLD_WIDTH = 9;
	public static final float WORLD_HEIGHT = 10;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_GAMEOVER = 1;
	public static final Vector2 gravity = new Vector2(0, -5);

	Rectangle downDrillRectangle;

	public final Player player;
	public final List<Block> blocks;
	public final List<Block> chainBlocks;
	public final WorldListener listener;
	public final Random rand;
	public boolean updatePlayer;
	public int scrore = 0;

	public World(WorldListener listener) {
		this.player = new Player(this, 4.5f, WORLD_HEIGHT - 3.5f);
		this.listener = listener;
		this.blocks = new ArrayList<Block>();
		this.chainBlocks = new ArrayList<Block>();
		rand = new Random();
		generateLevel();
		downDrillRectangle = new Rectangle(0, 0, 288, 448);

	}

	private void generateLevel() {
		// TODO Auto-generated method stub

		// generating first line of blocks and leaving a space in the middle for
		// the player
		int typex = Block.BLOCK_TYPE_X;
		Block block0 = new Block(typex, 0.5f, WORLD_HEIGHT - 3.5f);
		Block block1 = new Block(typex, 1.5f, WORLD_HEIGHT - 3.5f);
		Block block2 = new Block(typex, 2.5f, WORLD_HEIGHT - 3.5f);
		Block block3 = new Block(typex, 3.5f, WORLD_HEIGHT - 3.5f);
		Block block4 = new Block(typex, 5.5f, WORLD_HEIGHT - 3.5f);
		Block block5 = new Block(typex, 6.5f, WORLD_HEIGHT - 3.5f);
		Block block6 = new Block(typex, 7.5f, WORLD_HEIGHT - 3.5f);
		Block block7 = new Block(typex, 8.5f, WORLD_HEIGHT - 3.5f);
		blocks.add(block0);
		blocks.add(block1);
		blocks.add(block2);
		blocks.add(block3);
		blocks.add(block4);
		blocks.add(block5);
		blocks.add(block6);
		blocks.add(block7);

		// number of blocks x
		int numBlocksX = 9;
		// number of blocks y
		int numBlocksY = 50;

		// generating blocks
		float x = 0.5f;
		float y = WORLD_HEIGHT - 4.5f;
		for (int i = 0; i < numBlocksY; i++) {
			for (int j = 0; j < numBlocksX; j++) {
				int type = 0;

				float floatType = rand.nextFloat();
				if (floatType < 0.25f) {
					type = Block.BLOCK_TYPE_X;
				} else if (floatType >= 0.25f && floatType < 0.5f) {
					type = Block.BLOCK_TYPE_Y;
				} else if (floatType >= 0.5f && floatType < 0.75f) {
					type = Block.BLOCK_TYPE_J;
				} else if (floatType >= 0.75f) {
					type = Block.BLOCK_TYPE_Z;
				}

				Block block = new Block(type, x, y);
				blocks.add(block);

				x += 1.0f;
			}
			x = 0.5f;
			y -= 1.0f;
		}

	}

	public void update(float deltaTime) {
		updatePlayer(deltaTime);
		updateBlocks(deltaTime);
		checkCollisions();
	}

	private void updateBlocks(float deltaTime) {
		// TODO Auto-generated method stub
		for (int i = 0; i < blocks.size(); i++) {
			Block block = blocks.get(i);
			block.update(deltaTime);
		}
	}

	private void updatePlayer(float deltaTime) {
		// TODO Auto-generated method stub
		player.update(deltaTime);
	}

	private void checkCollisions() {

		checkPlayerCollision();
		checkBlocksCollision();
	}

	private void checkBlocksCollision() {
		// TODO Auto-generated method stub
		// for (int i = 0; i < blocks.size(); i++) {
		// Block block = blocks.get(i);
		// block.state = Block.BLOCK_FALLING;
		// if (block.type == Block.BLOCK_TYPE_Y) {
		// for (int j = 0; j < blocks.size(); j++) {
		// Block blockUnder = blocks.get(j);
		// if (blockUnder.position.x == block.position.x
		// && blockUnder.position.y == block.position.y - 1) {
		// block.state = Block.BLOCK_STILL;
		// continue;
		// } else if (blockUnder.position.y < block.position.y
		// && blockUnder.position.x == block.position.x) {
		// if (OverlapTester.overlapRectangles(block.bounds,
		// blockUnder.bounds)) {
		// block.state = Block.BLOCK_STILL;
		// block.position.set(blockUnder.position.x,
		// blockUnder.position.y + 1);
		// }
		// }
		// }
		// }
		// }
		// for (int i = 0; i < blocks.size(); i++) {
		// Block block = blocks.get(i);
		// for (int j = 0; j < blocks.size(); j++) {
		// Block blockAtSide = blocks.get(j);
		//
		// // check if block is top
		// if(block.position.x == blockAtSide.position.x && block.position.y ==
		// blockAtSide.position.y - 1) {
		// if(block.type == blockAtSide.type) {
		// block.state = Block.BLOCK_STILL;
		// blockAtSide.state = Block.BLOCK_STILL;
		// continue;
		// } else if(block.position.x == blockAtSide.position.x &&
		// block.position.y == blockAtSide.position.y + 1) {
		// block.state = Block.BLOCK_STILL;
		// } else {
		// block.state = Block.BLOCK_FALLING;
		// }
		//
		// }
		// // check if block is right
		// if(block.position.x == blockAtSide.position.x + 1 && block.position.y
		// == blockAtSide.position.y) {
		// if(block.type == blockAtSide.type) {
		// block.state = Block.BLOCK_STILL;
		// blockAtSide.state = Block.BLOCK_STILL;
		// continue;
		// } else if(block.position.x == blockAtSide.position.x &&
		// block.position.y == blockAtSide.position.y + 1) {
		// block.state = Block.BLOCK_STILL;
		// } else {
		// block.state = Block.BLOCK_FALLING;
		// }
		//
		// }
		// // check if block is left
		// if(block.position.x == blockAtSide.position.x - 1 && block.position.y
		// == blockAtSide.position.y) {
		// if(block.type == blockAtSide.type) {
		// block.state = Block.BLOCK_STILL;
		// blockAtSide.state = Block.BLOCK_STILL;
		// continue;
		// } else if(block.position.x == blockAtSide.position.x &&
		// block.position.y == blockAtSide.position.y + 1) {
		// block.state = Block.BLOCK_STILL;
		// } else {
		// block.state = Block.BLOCK_FALLING;
		// }
		//
		// }
		// }
		// }
	}

	private void checkPlayerCollision() {
		// TODO Auto-generated method stub
		int len = blocks.size();
		for (int i = 0; i < len; i++) {
			Block block = blocks.get(i);
			if (player.position.y > block.position.y
					&& player.position.x == block.position.x) {
				if (OverlapTester
						.overlapRectangles(player.bounds, block.bounds)) {
					player.state = Player.PLAYER_STANDING;
					player.position.set(block.position.x, block.position.y + 1);
					player.velocity.x = 0;
					player.velocity.y = 0;
				} else {
					player.state = Player.PLAYER_FALLING;
				}
			}
			if (player.state == Player.PLAYER_MOVING_LEFT) {
				if (player.position.x < block.position.x
						&& player.position.y == block.position.y) {
					if (OverlapTester.overlapRectangles(player.bounds,
							block.bounds)) {
						player.state = Player.PLAYER_STANDING;
						player.position.set(block.position.x + 1,
								block.position.y);
						player.velocity.x = 0;
						player.velocity.y = 0;
					}
				}
			}
			if (player.state == Player.PLAYER_MOVING_RIGHT) {
				if (player.position.x > block.position.x
						&& player.position.y == block.position.y) {
					if (OverlapTester.overlapRectangles(player.bounds,
							block.bounds)) {
						player.state = Player.PLAYER_STANDING;
						player.position.set(block.position.x - 1,
								block.position.y);
						player.velocity.x = 0;
						player.velocity.y = 0;
					}
				}
			}
		}
	}
}
