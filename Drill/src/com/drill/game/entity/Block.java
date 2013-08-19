package com.drill.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.drill.game.Assets;
import com.drill.game.Globals;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 8/17/13
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Block extends Entity {
    State state = State.IDLE;
    private int type = 0;
    private EntityManager entityManager;
    private int oldY = 0;

    public Block(EntityManager entityManager, int type, int x, int y) {
        super(Assets.getAssets().getSprite(type * 32, 0, 32, 32), x, y);
        this.entityManager = entityManager;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @Override
    public void update(float deltaT) {
        if (entityManager.getBlock(Globals.getGridCord(x), Globals.getGridCord(y) + 1) == null && Globals.getGridCord(y) <= Globals.MAX_HEIGHT - 2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            oldY = y;
            y += Globals.BLOCK_SIZE;
            entityManager.moveBlock(this, Globals.getGridCord(x), Globals.getGridCord(oldY), Globals.getGridCord(x), Globals.getGridCord(y));
            state = State.FALLING;
        } else {
            if (state == State.FALLING) {
                state = State.IDLE;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                if(entityManager.getBlock(Globals.getGridCord(x), Globals.getGridCord(y)+1).getType() == type){
                    entityManager.removeEntity(this);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        int cordX = Globals.getGridCord(x);
        int cordY = Globals.getGridCord(y);
        removeBlock(cordX - 1, cordY);
        removeBlock(cordX + 1, cordY);
        removeBlock(cordX, cordY - 1);
        removeBlock(cordX, cordY + 1);
    }

    private void removeBlock(int x, int y) {
        if (entityManager.getBlock(x, y) != null) {
            if (entityManager.getBlock(x, y).getType() == type) {
                entityManager.removeBlock(x, y);
            }
        }
    }

    enum State {
        FALLING, CHECK, IDLE;
    }
}
