package com.drill;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.drill.game.Globals;
import com.drill.main.MyGame;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Drill";
        cfg.useGL20 = true;
        cfg.width = 8 * Globals.BLOCK_SIZE;
        cfg.height = 12 * Globals.BLOCK_SIZE;

        new LwjglApplication(new MyGame(), cfg);
    }
}
