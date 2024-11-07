package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class ArrowController{
    private final ArrayList<Arrow> activeArrows;
    private final Archer archer;
    private final ArrowInputProcessor arrowInputProcessor;

    public ArrowController(Archer archer) {
        this.activeArrows = new ArrayList<>();
        this.archer = archer;
        this. arrowInputProcessor = new ArrowInputProcessor();
    }

    public void addArrow(Texture arrowTexture) {
        int screenWidth = Gdx.graphics.getWidth();
        float archerX = archer.getX();
        activeArrows.add(new Arrow(archer.getX() + archer.getWidth() / 2, archer.getY() + 5, arrowTexture, archer));
    }

    public boolean getArrowKeyDownPressed(){
        return arrowInputProcessor.getEnterPressed();
    }

    public void update(SpriteBatch batch) {
        for (Arrow arrow : activeArrows) {
            arrow.update(batch);
        }
    }

    public boolean getEnterPressed(){
        return arrowInputProcessor.getEnterPressed();
    }

    public void resetEnter(){
        arrowInputProcessor.resetEnterPressed();
    }

    public ArrowInputProcessor getArrowInputProcessor() {
        return arrowInputProcessor;
    }

}

