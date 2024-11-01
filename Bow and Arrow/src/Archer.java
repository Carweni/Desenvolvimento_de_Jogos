package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Archer extends Sprite {
    public Archer(float positionX, float positionY, Texture archer) {
        super(archer);
        this.setPosition(positionX, positionY);
        this.setSize(100, 100);
    }

    public void setArcherTexture(Texture archer) {
        this.setTexture(archer);
    }
}
