package com.drill.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.drill.game.Assets;
import com.drill.game.Globals;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 8/17/13
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Block extends Entity {
    private BlockGroup blockGroup = null;
    private EntityManager entityManager;
    private int type = 0;
    private int oldY = 0;

    public Block(EntityManager entityManager, int type, int x, int y) {
        super(Assets.getAssets().getSprite(type * 32, 0, 32, 32), x, y);
        this.entityManager = entityManager;
        this.type = type;
        checkBlockGroup();
        blockGroup.setGroup(this);
        //TODO calculate blockgroup
    }

    private void checkBlockGroup() {
        BlockGroup tempGroup = null;
        if (entityManager.getBlock(getGridX() + 1, getGridY()) != null) {
            if (entityManager.getBlock(getGridX() + 1, getGridY()).getType() == type) {
                tempGroup = entityManager.getBlock(getGridX() + 1, getGridY()).getBlockGroup();
                if (tempGroup != null) {
                    blockGroup = tempGroup;
                    return;
                }
            }
        }
        if (entityManager.getBlock(getGridX(), getGridY() + 1) != null) {
            if (entityManager.getBlock(getGridX(), getGridY() + 1).getType() == type) {
                tempGroup = entityManager.getBlock(getGridX(), getGridY() + 1).getBlockGroup();
                if (tempGroup != null) {
                    blockGroup = tempGroup;
                    return;
                }
            }
        }
        if (entityManager.getBlock(getGridX(), getGridY() - 1) != null) {
            if (entityManager.getBlock(getGridX(), getGridY() - 1).getType() == type) {
                tempGroup = entityManager.getBlock(getGridX(), getGridY() - 1).getBlockGroup();
                if (tempGroup != null) {
                    blockGroup = tempGroup;
                    return;
                }
            }
        }
        if (entityManager.getBlock(getGridX() - 1, getGridY()) != null) {
            if (entityManager.getBlock(getGridX() - 1, getGridY()).getType() == type) {
                tempGroup = entityManager.getBlock(getGridX() - 1, getGridY()).getBlockGroup();
                if (tempGroup != null) {
                    blockGroup = tempGroup;
                    return;
                }
            }
        }
        if (tempGroup == null)
            this.blockGroup = BlockGroupManager.newGroup(entityManager, type);
    }

    @Override
    public void preUpdate(float deltaT) {

    }

    @Override
    public void update(float deltaT) {
        if(blockGroup.canMove())
            moveDown();
    }

    @Override
    public void pastUpdate(float deltaT) {

    }

    public void moveDown() {
        oldY = y;
        y += Globals.BLOCK_SIZE;
        entityManager.moveBlock(this, getGridX(), Globals.getGridCord(oldY), getGridX(), getGridY());
    }

    public void onDestroy() {
        System.out.println("Destroying block with type: " + getType());
        blockGroup.destroyGroup();
        //Destroy the whole group
    }

    public boolean isBlockChecked() {
        return blockGroup != null;
    }

    public BlockGroup getBlockGroup() {
        return blockGroup;
    }

    public int getType() {
        return type;
    }

    enum State {
        FALLING, CHECK, IDLE
    }
}
