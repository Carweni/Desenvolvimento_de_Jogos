package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class ArrowInputProcessor implements InputProcessor {
    private boolean isArrowKeyDownPressed = false;

    public boolean getEnterPressed() {
        return isArrowKeyDownPressed;
    }

    public void resetEnterPressed(){
        this.isArrowKeyDownPressed = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ENTER) {
            this.isArrowKeyDownPressed = true;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.ENTER) {
            this.isArrowKeyDownPressed = false;
        }
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
