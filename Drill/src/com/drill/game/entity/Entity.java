package com.drill.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 8/17/13
 * Time: 1:15 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Entity {
    protected boolean alive = true;
    protected int x = 0;
    protected int y = 0;
    private Sprite sprite;

    public Entity(Sprite sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    protected void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public abstract void update(float deltaT);

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(sprite, x, y);
    }

    public void destroy() {
        if (alive) {
            alive = false;
            onDestroy();
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void onDestroy() {
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
