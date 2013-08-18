package com.drill.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 8/17/13
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    private ArrayList<RegisteredKey> registeredKeys = new ArrayList<RegisteredKey>();

    public Controller() {

    }

    public void update() {
        for (int i = 0, l = registeredKeys.size(); i < l; i++) {
            registeredKeys.get(i).update();
        }
    }

    public void registerKey(int key, InputReactListener inputReactListener, InputReactListener.Event event) {
        registeredKeys.add(new RegisteredKey(key, inputReactListener, event));
    }

    class RegisteredKey {
        boolean pressed = false;
        int key;
        InputReactListener inputReactListener;
        InputReactListener.Event triggerEvent;

        RegisteredKey(int key, InputReactListener inputReactListener, InputReactListener.Event event) {
            this.key = key;
            this.inputReactListener = inputReactListener;
            this.triggerEvent = event;
        }

        public void update() {
            if (Gdx.input.isKeyPressed(key) && !pressed) {
                if (triggerEvent == InputReactListener.Event.PRESSED) {
                    inputReactListener.inputReact();
                }
                pressed = true;
            } else if (!Gdx.input.isKeyPressed(key) && pressed) {
                if (triggerEvent == InputReactListener.Event.RELEASED) {
                    inputReactListener.inputReact();
                }
                pressed = false;
            }

        }
    }
}
