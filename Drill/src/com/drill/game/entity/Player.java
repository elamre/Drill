package com.drill.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.drill.game.Assets;
import com.drill.game.Globals;
import com.drill.game.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 8/17/13
 * Time: 2:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player extends Entity {
    private final int targetGridY = 5;
    private Sprite sprites[];
    private Direction direction;
    private Direction previousDirection;
    private EntityManager entityManager;
    private int gridY = 0;


    public Player(EntityManager entityManager, int x, int y) {
        super(null, x, y);
        this.entityManager = entityManager;
        this.direction = Direction.DOWN;
        this.previousDirection = direction;
        sprites = new Sprite[4];
        loadSprites();
    }

    private void loadSprites() {
        sprites[Direction.UP.getNumber()] = Assets.getAssets().getSprite(0, 32, 32, 32);
        sprites[Direction.LEFT.getNumber()] = Assets.getAssets().getSprite(32, 32, 32, 32);
        sprites[Direction.RIGHT.getNumber()] = Assets.getAssets().getSprite(64, 32, 32, 32);
        sprites[Direction.DOWN.getNumber()] = Assets.getAssets().getSprite(0, 32, 32, 32);
    }

    @Override
    public void update(float deltaT) {
        if (entityManager.getBlock(Globals.getGridCord(x), Globals.getGridCord(y) + 1) == null) {
            if (Globals.getGridCord(y) > targetGridY) {
                entityManager.shiftMap();
            } else {
                y += Globals.BLOCK_SIZE;
            }
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(sprites[direction.getNumber()], x, y);
    }

    public void move(Direction direction) {
        this.previousDirection = direction;
        if (direction == Direction.LEFT) {
            if (this.direction == Direction.UP) {
                if (entityManager.getBlock(Globals.getGridCord(x) - 1, Globals.getGridCord(y)) != null && Globals.getGridCord(x) > 0) {
                    if (entityManager.getBlock(Globals.getGridCord(x) - 1, Globals.getGridCord(y) - 1) == null) {
                        x -= Globals.BLOCK_SIZE;
                        y -= Globals.BLOCK_SIZE;
                    }
                }
            } else if (entityManager.getBlock(Globals.getGridCord(x) - 1, Globals.getGridCord(y)) == null && Globals.getGridCord(x) > 0)
                x -= Globals.BLOCK_SIZE;
            this.direction = Direction.LEFT;
        } else if (direction == Direction.RIGHT) {
            if (this.direction == Direction.UP) {
                if (entityManager.getBlock(Globals.getGridCord(x) + 1, Globals.getGridCord(y)) != null && Globals.getGridCord(x) < 7) {
                    if (entityManager.getBlock(Globals.getGridCord(x) + 1, Globals.getGridCord(y) - 1) == null) {
                        x += Globals.BLOCK_SIZE;
                        y -= Globals.BLOCK_SIZE;
                    }
                }
            } else if (entityManager.getBlock(Globals.getGridCord(x) + 1, Globals.getGridCord(y)) == null && Globals.getGridCord(x) < 7)
                x += Globals.BLOCK_SIZE;
            this.direction = Direction.RIGHT;
        } else if (direction == Direction.DOWN) {
            this.direction = Direction.DOWN;
        } else if (direction == Direction.UP) {
            this.direction = Direction.UP;
        }
    }

    public void dig() {
        switch (direction) {

            case UP:
                // Implement jump
                break;
            case DOWN:
                entityManager.removeBlock(Globals.getGridCord(x), Globals.getGridCord(y) + 1);
                break;
            case LEFT:
                entityManager.removeBlock(Globals.getGridCord(x) - 1, Globals.getGridCord(y));
                break;
            case RIGHT:
                entityManager.removeBlock(Globals.getGridCord(x) + 1, Globals.getGridCord(y));
                break;
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public enum Direction {
        UP(0), DOWN(1), LEFT(2), RIGHT(3);
        int number;

        Direction(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }
    }
}
