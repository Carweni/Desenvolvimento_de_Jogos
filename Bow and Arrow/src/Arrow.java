package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Arrow extends Sprite {

    public Arrow(float positionX, float positionY) {
        super((Texture) MyGdxGame.assetManager.get("arrow.png"));
        this.setPosition(positionX, positionY);
        this.setSize(30, 50);
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public boolean isOutOfScreen() {
        return (getY() > Gdx.graphics.getHeight() || getY() + getHeight() < 0) ||
                (getX() > Gdx.graphics.getWidth() || getX() + getWidth() < 0);
    }

    public void updateArrow(SpriteBatch batch) {

        if(!isOutOfScreen()) {
            setPosition(getX() + 5, getY());
            this.draw(batch);
        }
    }

}
