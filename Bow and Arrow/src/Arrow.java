package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Arrow extends Sprite {
    private float speed = 5;
    private ArcherInputProcessor arrowInputProcessor;   // Separar e arrumar com o Input Multiplexer.

    public Arrow(float positionX, float positionY, Texture arrowTexture, Archer archer) {
        super(arrowTexture);
        this.setPosition(positionX, positionY);
        this.setSize(30, 50);
        this.arrowInputProcessor = archer.getInputProcessor();
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public boolean isOutOfScreen (){
        if((getY() > Gdx.graphics.getHeight())&&(getY()+getHeight()<0)){
            return true;
        }
        if((getX() > Gdx.graphics.getWidth())&&(getX()+getWidth()<0)){
            return true;
        }
        return false;
    }

    public void update(SpriteBatch batch) {
        if(!isOutOfScreen()) {
            setPosition(getX() + 5,getY());
            this.draw(batch);
        }
    }

}
