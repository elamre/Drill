package com.drill.game;

import com.drill.game.entity.Block;
import com.drill.game.entity.EntityManager;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 8/17/13
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Map {
    private Block[][] map;
    private EntityManager entityManager;
    private int blockTypes;
    private int width;
    private int height;

    public Map(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setBlock(int x, int y, Block block) {
        if (y > map[0].length - 1 || x < 0 || y < 0 || x > map.length - 1) {
            //System.out.println("Illegal modifier");
            return;
        }
        map[x][y] = block;
        System.out.println(toString());
    }

    public void generateNewMap(int width,int height,int blockTypes) {
        this.blockTypes = blockTypes;
        map = new Block[Globals.MAX_WIDTH][Globals.MAX_HEIGHT];
        Random rand = new Random();
        int type;
        for (int y = 5; y < height; y++) {
            for (int x = 0; x < width; x++) {
                type = rand.nextInt(blockTypes);
                map[x][y] = (Block) entityManager.addEntity(new Block(entityManager, type, x * Globals.BLOCK_SIZE, y * Globals.BLOCK_SIZE));
            }
        }
    }

    public String toString() {
        String string = "";
        for (int y = 0; y < Globals.MAX_HEIGHT; y++) {
            for (int x = 0; x < Globals.MAX_WIDTH; x++) {
                if (getBlock(x, y) == null)
                    string += " - ";
                else
                    string += " " + getBlock(x, y).getType() + " ";
            }
            string += "\n";
        }
        return string;
    }

    public void shiftMap() {
        Random rand = new Random();
        for (int y = 1; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                if (getBlock(x, y) != null)
                    map[x][y].setPosition(x * Globals.BLOCK_SIZE, (y - 1) * Globals.BLOCK_SIZE);
                map[x][y - 1] = map[x][y];
            }
        }
        for (int x = 0; x < map.length; x++) {
            map[x][map[0].length - 1] = (Block) entityManager.addEntity(new Block(entityManager, rand.nextInt(blockTypes), x * 32, (map[0].length - 1) * 32));
        }
        System.out.println(toString());

    }

    public Block getBlock(int x, int y) {
        if (y > map[0].length - 1 || x < 0 || y < 0 || x > map.length - 1)
            return null;
        return map[x][y];
    }

    public int getGridCord(int cord) {
        return (cord - (cord % Globals.BLOCK_SIZE)) / Globals.BLOCK_SIZE;
    }

}
