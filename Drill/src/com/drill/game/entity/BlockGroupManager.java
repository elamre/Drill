package com.drill.game.entity;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 8/22/13
 * Time: 9:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class BlockGroupManager {
    private static int id = 0;
    private static ArrayList<BlockGroup> blockGroups = new ArrayList<BlockGroup>();

    public static BlockGroup newGroup(EntityManager entityManager, int type) {
        BlockGroup blockGroup = new BlockGroup(entityManager, type, id++);
        blockGroups.add(blockGroup);
        return blockGroup;
    }

    public static void update() {
        ArrayList<BlockGroup> removeList = new ArrayList<BlockGroup>();
        for (int i = 0, l = blockGroups.size(); i < l; i++) {
            blockGroups.get(i).update();
       //     if (blockGroups.get(i).currentSize == 0)
       //         removeList.add(blockGroups.get(i));
        }
        for (int i = 0, l = removeList.size(); i < l; i++) {
            blockGroups.remove(removeList.get(i));
        }
        removeList.clear();
    }
}
