package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class ArrowController{
    private final ArrayList<Arrow> activeArrows;
    private final Archer archer;

    public ArrowController(Archer archer) {
        this.activeArrows = new ArrayList<>();
        this.archer = archer;
    }

    public void addArrow(Texture arrowTexture) {
        int screenWidth = Gdx.graphics.getWidth();
        float archerX = archer.getX();
        activeArrows.add(new Arrow(archer.getX() + archer.getWidth(), archer.getY() + archer.getHeight() / 2, arrowTexture, archer));
    }

    public void update(SpriteBatch batch) {
        for (Arrow arrow : activeArrows) {
            arrow.update(batch);
        }
    }

}

