package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Archer extends Sprite {
    private final GameInputProcessor gameInputProcessor;

    public Archer(float positionX, float positionY, Texture archer) {
        super(archer);
        this.gameInputProcessor = new GameInputProcessor();
        this.setPosition(positionX, positionY);
        this.setSize(100, 100);
    }

    public void setArcherTexture(Texture archer) {
        this.setTexture(archer);
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void update(SpriteBatch batch) {
        if (gameInputProcessor.getKeyUpPressed() && this.getY()+this.getHeight()<Gdx.graphics.getHeight()) {
            this.setY(this.getY() + 3);
        }

        if (gameInputProcessor.getKeyDownPressed() && this.getY()>0) {
            this.setY(this.getY() - 3);
        }

        this.draw(batch);
    }

    public InputProcessor getInputProcessor() {
        return gameInputProcessor;
    }
}
