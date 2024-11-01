package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Balloon{
    private final Texture balloon;
    private float PositionX, PositionY;
    private boolean visible;

    Balloon(Texture Balloon, float PositionX, float PositionY){
        this.balloon = Balloon;
        this.PositionX = PositionX;
        this.PositionY = PositionY;
        this.visible = true;
    }

    public Texture getTexture() {
        return balloon;
    }

    public float getPositionX() {
        return PositionX;
    }

    public float getPositionY() {
        return PositionY;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void updatePosition(){
        PositionY += 10;
    }
}