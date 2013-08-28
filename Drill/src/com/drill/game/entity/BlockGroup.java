package com.drill.game.entity;

import com.drill.game.Globals;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 8/22/13
 * Time: 9:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class BlockGroup {
    final int sizeThreshold = 3;
    final int groupId;
    final int blockType;
    boolean changed = false;
    int previousSize = 0;
    int currentSize = 0;
    private boolean move = false;
    private ArrayList<Block> blocks;
    private EntityManager entityManager;

    public BlockGroup(EntityManager entityManager, int blockType, int groupId) {
        this.groupId = groupId;
        this.entityManager = entityManager;
        this.blocks = new ArrayList<Block>();
        this.blockType = blockType;
    }

    public void setGroup(Block block) {
        blocks.add(block);
    }

    public boolean isLargeEnough() {
        return currentSize >= sizeThreshold;
    }

    public void increment() {
        currentSize++;
    }

    public void update() {
        checkMove();
        if (previousSize != currentSize)
            changed = true;
        previousSize = currentSize;
    }

    private void checkMove() {
        move = true;
        for (int i = 0, l = blocks.size(); i < l; i++) {
            Block compareBlock;
            int checkX = blocks.get(i).getGridX();
            int checkY = blocks.get(i).getGridY() + 1;
            if (checkY > Globals.MAX_HEIGHT-2){
                move = false;
                return;
            }
            compareBlock = entityManager.getBlock(checkX, checkY);
            if (compareBlock == null || compareBlock.getType() == blockType) {
                continue;
            } else {
                move = false;
                break;
            }
        }
    }

    public boolean hasChanged() {
        boolean temp = changed;
        changed = false;
        return temp;
    }

    public boolean canMove() {
        return move;
    }

    public void destroyGroup() {
        System.out.println("Blocks size: " + blocks.size());
        for (int i = 0, l = blocks.size(); i < l; i++) {
            blocks.get(i).destroy();
        }
        //blocks.clear();
        //currentSize = 0;
    }

    public int getBlockType() {
        return blockType;
    }

    public int getId() {
        return groupId;
    }
}
