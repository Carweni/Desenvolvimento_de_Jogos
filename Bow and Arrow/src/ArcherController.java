package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;

public class ArcherController {
    private Archer archer;
    private GameInputProcessor gameInputProcessor;

    public ArcherController(Archer archer) {
        this.archer = archer;
        this.gameInputProcessor = new GameInputProcessor();
    }

    public void draw(SpriteBatch batch) {
        archer.draw(batch);
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
