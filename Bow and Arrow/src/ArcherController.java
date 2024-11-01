package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;

public class ArcherController {
    private Archer archer;
    private GameInputProcessor gameInputProcessor;

    public ArcherController(Archer archer) {
        this.archer = archer;
        this.gameInputProcessor = new GameInputProcessor();
    }

    public void update() {
        if (gameInputProcessor.getKeyUpPressed()) {
            archer.setY(archer.getY() + 10);
        }

        if (gameInputProcessor.getKeyDownPressed()) {
            archer.setY(archer.getY() - 10);
        }
    }

    public InputProcessor getInputProcessor() {
        return gameInputProcessor;
    }
}
