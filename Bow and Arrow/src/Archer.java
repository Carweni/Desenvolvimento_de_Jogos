package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Archer extends Sprite {
    private Texture archer;

    public Archer(float positionX, float positionY, Texture archer) {
        this.archer = archer;
        this.setTexture(archer);
        this.setPosition(positionX, positionY);
    }

    public Texture getArcher() {
        return archer;
    }

    public void setArcherTexture(Texture archer) {
        this.archer = archer;
        this.setTexture(archer);
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
}
