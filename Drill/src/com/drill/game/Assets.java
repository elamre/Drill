package com.drill.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 8/17/13
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Assets {
    private static Assets assets;
    private Texture spriteSheet;

    public Assets() {
        spriteSheet = new Texture("Spritesheet.png");
        spriteSheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public synchronized static Assets getAssets() {
        if (assets == null)
            assets = new Assets();
        return assets;
    }

    public Sprite getSprite(int posX, int posY, int width, int height) {
        TextureRegion textureRegion = new TextureRegion(spriteSheet,posX,posY,width,height);
        textureRegion.flip(false,true);
        return new Sprite(textureRegion);
    }
}
