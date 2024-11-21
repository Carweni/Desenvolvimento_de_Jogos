package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class ArcherInputProcessor implements InputProcessor {
    public boolean isKeyUpPressed = false;
    public boolean isKeyDownPressed = false;
    // private boolean isArrowKeyDownPressed = false;

    ArcherInputProcessor(){
        Gdx.input.setInputProcessor(this);
    }

    public boolean getKeyUpPressed() {
        return isKeyUpPressed;
    }

    public boolean getKeyDownPressed() {
        return isKeyDownPressed;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W) {
            this.isKeyUpPressed = true;
        } else if (keycode == Input.Keys.S) {
            this.isKeyDownPressed = true;
        } else if (keycode == Input.Keys.ENTER) {
            return false;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W) {
            this.isKeyUpPressed = false;
        } else if (keycode == Input.Keys.S) {
            this.isKeyDownPressed = false;
        }/* else if (keycode == Input.Keys.ENTER) {
            this.isArrowKeyDownPressed = false;
        }*/
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
