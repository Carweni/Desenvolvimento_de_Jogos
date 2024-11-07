package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;

public class Balloon extends Sprite implements Pool.Poolable {
    private boolean alive;

    Balloon(float PositionX, float PositionY, Texture Balloon){
        super(Balloon);
        this.setPosition(PositionX, PositionY);
        setSize(20, 40);
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void init(float posX, float posY) {
        setPosition(posX, posY);
        alive = true;
    }

    @Override
    public void reset() {
        setPosition(0,0);
        alive = false;
    }

    public boolean isOutOfScreen (){
        if((getY()> Gdx.graphics.getHeight())&&(getY()+getHeight()<0)){
            return true;
        }
        if((getX()>Gdx.graphics.getWidth())&&(getX()+getWidth()<0)){
            return true;
        }
        return false;
    }

    public void update (SpriteBatch batch) {
        if (this.isOutOfScreen()) {
            alive = false;
        } else {
            setPosition(getX(),getY()+1);
            this.draw(batch);
        }
    }

}