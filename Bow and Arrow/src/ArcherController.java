package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ArcherController{
    private Archer archer;
    private ArcherInputProcessor gameInputProcessor;

    public ArcherController(Archer archer) {
        this.archer = archer;
        this.gameInputProcessor = new ArcherInputProcessor();
    }

    public void draw(SpriteBatch batch) {
        archer.draw(batch);
    }
}