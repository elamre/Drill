package com.drill.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.drill.game.Controller;
import com.drill.game.Globals;
import com.drill.game.InputReactListener;
import com.drill.game.Map;
import com.drill.main.MyGame;

import javax.naming.ldap.Control;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 8/17/13
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntityManager {
    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private ArrayList<Entity> destroyEntities = new ArrayList<Entity>();
    private MyGame myGame;
    private Map map;
    private Controller controller;
    private Player player;

    public EntityManager(MyGame game) {
        this.myGame = game;
        controller = new Controller();
        map = new Map(this);
        map.generateNewMap(Globals.MAX_WIDTH, Globals.MAX_HEIGHT, 3);
        player = (Player) addEntity(new Player(this, 4 * Globals.BLOCK_SIZE, 4 * Globals.BLOCK_SIZE));
        controller.registerKey(Input.Keys.LEFT, new InputReactListener() {
            @Override
            public void inputReact() {
                player.move(Player.Direction.LEFT);
            }
        }, InputReactListener.Event.PRESSED);
        controller.registerKey(Input.Keys.RIGHT, new InputReactListener() {
            @Override
            public void inputReact() {
                player.move(Player.Direction.RIGHT);
            }
        }, InputReactListener.Event.PRESSED);
        controller.registerKey(Input.Keys.DOWN, new InputReactListener() {
            @Override
            public void inputReact() {
                player.move(Player.Direction.DOWN);
            }
        }, InputReactListener.Event.PRESSED);
        controller.registerKey(Input.Keys.UP, new InputReactListener() {
            @Override
            public void inputReact() {
                player.move(Player.Direction.UP);
            }
        }, InputReactListener.Event.PRESSED);
        controller.registerKey(Input.Keys.SPACE, new InputReactListener() {
            @Override
            public void inputReact() {
                player.dig();
            }
        }, InputReactListener.Event.PRESSED);
    }

    public Entity addEntity(Entity entity) {
        entities.add(entity);
        return entity;
    }

    public void shiftMap() {
        map.shiftMap();
    }

    public void update(float deltaT) {
        if (Gdx.input.isTouched()) {
            map.shiftMap();
            // removeBlock(map.getGridCord(Gdx.input.getX()), map.getGridCord(Gdx.input.getY()));
        }
        controller.update();

        for (int i = 0, l = entities.size(); i < l; i++) {
            entities.get(i).update(deltaT);
            if (!entities.get(i).isAlive())
                destroyEntities.add(entities.get(i));
        }
        for (int i = 0, l = destroyEntities.size(); i < l; i++) {
            if (map.getBlock(map.getGridCord(destroyEntities.get(i).x), map.getGridCord(destroyEntities.get(i).y)) != null) {
                map.setBlock(map.getGridCord(destroyEntities.get(i).x), map.getGridCord(destroyEntities.get(i).y), null);
            }
            entities.remove(destroyEntities.get(i));
        }
        destroyEntities.clear();
    }

    public void removeBlock(int x, int y) {
        if (map.getBlock(x, y) != null) {
            removeEntity(map.getBlock(x, y));
        }
    }

    public Block getBlock(int x, int y) {
        return map.getBlock(x, y);
    }

    public void setBlock(Block block, int x, int y) {
        map.setBlock(x, y, block);
    }

    public void moveBlock(Block block, int oldX, int oldY, int x, int y) {
        map.setBlock(x, y, block);
        map.setBlock(oldX, oldY, null);
    }

    public void removeEntity(Entity entity) {
        if (entity != null) {
            entity.destroy();
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        for (int i = 0, l = entities.size(); i < l; i++) {
            entities.get(i).draw(spriteBatch);
        }
    }
}
