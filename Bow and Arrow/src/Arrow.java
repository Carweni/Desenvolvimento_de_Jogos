package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Arrow extends Sprite {
    public boolean alive;

    public Arrow(float positionX, float positionY, Archer archer) {
        super((Texture) MyGdxGame.assetManager.get("arrow.png"));
        this.setPosition(positionX, positionY);
        this.setSize(30, 50);
        this.alive = false;
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void arrowInit (float posX, float posY){
        this.setPosition(posX, posY);
        this.alive = true;
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

    public void updateArrow(SpriteBatch batch) {
        if(!isOutOfScreen()) {
            setPosition(getX() + 5, getY());
            this.draw(batch);
        }
    }

}
