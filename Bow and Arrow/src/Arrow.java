package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Arrow {
    private final Texture arrowTexture;
    private float positionX, positionY;
    private boolean active;

    public Arrow(Texture arrowTexture, float positionX, float positionY) {
        this.arrowTexture = arrowTexture;
        this.positionX = positionX;
        this.positionY = positionY;
        this.active = true;
    }

    public void updatePosition() {
        positionX += 10;
    }

    public Texture getTexture() {
        return arrowTexture;
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }
}
