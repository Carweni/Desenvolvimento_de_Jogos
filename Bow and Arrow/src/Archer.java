package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Padrão singleton (sem Controller):
public class Archer extends Sprite {
    private final ArcherInputProcessor gameInputProcessor;
    private final Texture archerIdleTexture;
    private final Texture archerShootingTexture;
    private boolean isShooting;
    private float shootingTimer;

    public Archer(float positionX, float positionY, Texture archerIdleTexture, Texture archerShootingTexture) {
        super((Texture) MyGdxGame.assetManager.get("archerIdle.png"));
        this.gameInputProcessor = new ArcherInputProcessor();
        this.setPosition(positionX, positionY);
        this.setSize(100, 100);
        this.archerIdleTexture = archerIdleTexture;
        this.archerShootingTexture = archerShootingTexture;
        this.isShooting = false;
        this.shootingTimer = 0;  // Inicializa o temporizador
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void update(SpriteBatch batch) {
        if (ArrowController.getArrowKeyDownPressed() && !isShooting) {
            this.setTexture(archerShootingTexture);
            isShooting = true;
            shootingTimer = 0.2f;
        }

        if (isShooting) {
            shootingTimer -= Gdx.graphics.getDeltaTime();
            if (shootingTimer <= 0) {
                this.setTexture(archerIdleTexture);
                isShooting = false;
            }
        }

        if (gameInputProcessor.getKeyUpPressed() && this.getY() + this.getHeight() < Gdx.graphics.getHeight()) {
            this.setY(this.getY() + 3);
        }

        if (gameInputProcessor.getKeyDownPressed() && this.getY() > 0) {
            this.setY(this.getY() - 3);
        }

        this.draw(batch);
    }

    public ArcherInputProcessor getInputProcessor() {
        return gameInputProcessor;
    }

}
