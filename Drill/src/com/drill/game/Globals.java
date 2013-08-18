package com.drill.game;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 8/17/13
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class Globals {
    public static final int BLOCK_SIZE = 32;
    public static final int MAX_HEIGHT = 12;
    public static final int MAX_WIDTH = 8;

    public static int getGridCord(int cord) {
        return (cord - (cord % Globals.BLOCK_SIZE)) / Globals.BLOCK_SIZE;
    }

}
