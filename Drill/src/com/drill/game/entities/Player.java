package com.drill.game.entities;


import com.drill.game.World;

public class Player extends DynamicGameObject {

	public static final float PLAYER_WIDTH = 1;
	public static final float PLAYER_HEIGHT = 1;
	public static final int PLAYER_STANDING = 0;
	public static final int PLAYER_FALLING = 1;
	public static final int PLAYER_MOVING_LEFT = 2;
	public static final int PLAYER_MOVING_RIGHT = 3;
	public static final int LOOKING_DOWN = 0;
	public static final int LOOKING_LEFT = 1;
	public static final int LOOKING_RIGHT = 2;

	public int state;
	int looking;
	World world;

	public Player(World world, float x, float y) {
		super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
		this.world = world;
		looking = LOOKING_DOWN;
	}

	public void update(float deltaTime) {
		if (state == PLAYER_FALLING) {

			// THE IF BLOCK CHECKS IF THE PLAYER IS AT THE SAME/OR BELOW Y THAT
			// THE LAST BLOCKS AND STOPS THE UPDATE METHOD FOR KEEP DROPING.
			// ONLY FOR THE PROTOTIPE
			if (position.y >= -43.5f) {
				velocity.add(World.gravity.x * deltaTime, World.gravity.y
						* deltaTime);
				position.add(velocity.x * deltaTime, velocity.y * deltaTime);
				bounds.setX(position.x - bounds.getWidth() / 2);
				bounds.setY(position.y - bounds.getHeight() / 2);
				//bounds.lowerLeft.set(position).sub(bounds.getWidth() / 2, bounds.getHeight() / 2);
			}
		}
		if (state == PLAYER_MOVING_LEFT) {
			velocity.y = 0;
			velocity.add(-5 * deltaTime, 0);
			position.add(velocity.x * deltaTime, velocity.y * deltaTime);
			bounds.setX(position.x - bounds.getWidth() / 2);
			bounds.setY(position.y - bounds.getHeight() / 2);
			//bounds.lowerLeft.set(position).sub(bounds.getWidth() / 2, bounds.getHeight() / 2);
		}
		if (state == PLAYER_MOVING_RIGHT) {
			velocity.y = 0;
			velocity.add(5 * deltaTime, 0);
			position.add(velocity.x * deltaTime, velocity.y * deltaTime);
			bounds.setX(position.x - bounds.getWidth() / 2);
			bounds.setY(position.y - bounds.getHeight() / 2);
			//bounds.lowerLeft.set(position).sub(bounds.getWidth() / 2, bounds.getHeight() / 2);
		}
	}

	public void digDown(Block block) {
		if (block.position.y + 1 == position.y
				&& position.x == block.position.x) {
//			world.blocks.remove(block);
			cadena(block);
		}
	}

	public void digLeft(Block block) {
		if (block.position.x + 1 == position.x
				&& block.position.y == position.y) {
			world.blocks.removeValue(block, false);
		}
	}

	public int getLooking() {
		return looking;
	}

	public void setLooking(int looking) {
		this.looking = looking;
	}

	public void digRight(Block block) {
		if (block.position.x - 1 == position.x
				&& block.position.y == position.y) {
			world.blocks.removeValue(block,false);
		}
	}

	public boolean isBlockLeft(Block block) {
		// TODO Auto-generated method stub
		if (position.x - 1 == block.position.x
				&& position.y == block.position.y) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isBlockRight(Block block) {
		if (position.x + 1 == block.position.x
				&& position.y == block.position.y) {
			return true;
		} else {
			return false;
		}
	}

	public void cadena(Block block) {
		if (!world.chainBlocks.contains(block,true)) {
			world.chainBlocks.add(block);
		}
		for (int i = 0; i < world.blocks.size; i++) {
			Block blockAtSide = world.blocks.get(i);
			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					if (x != 0 || y != 0) {
						if (x == -1) {
							// block down
							if (block.position.x == blockAtSide.position.x && block.position.y - 1 == blockAtSide.position.y) {
								if (blockAtSide.type == block.type) {
									if (!world.chainBlocks.contains(blockAtSide,false)) {
										world.chainBlocks.add(blockAtSide);
										cadena(blockAtSide);
									} else {
										for (int j = 0; j < world.chainBlocks.size; j++) {
											world.chainBlocks.removeIndex(j);
										}    // LOS BLOQUES NO SE BORRAN PORQUE TAMBIEN ESTAN EN EL ARRAY WORLD.BLOCKS
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
