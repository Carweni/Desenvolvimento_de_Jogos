package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Balloon extends Sprite {
    private boolean exploded;
    private float explosionTime;

    Balloon(float PositionX, float PositionY) {
        super((Texture) MyGdxGame.assetManager.get("balloon.png"));
        this.setPosition(PositionX, PositionY);
        setSize(30, 60);
        exploded = false;
        explosionTime = 0;
    }

    public boolean hasCollided() {
        if (exploded){
            return false;
        }

        // Verifica se a flecha colidiu com o balão para a futura remoção:
        Vector2 arrowHitbox = new Vector2(0, 0);
        for (Arrow a : ArrowController.activeArrows) {
            arrowHitbox.set(a.getX() + a.getWidth(), a.getY() + a.getHeight());
            if (arrowHitbox.x >= this.getX() && arrowHitbox.x <= this.getX() + this.getWidth() &&
                    arrowHitbox.y >= this.getY() && arrowHitbox.y <= this.getY() + this.getHeight()) {
                Texture explodedBalloonTexture = MyGdxGame.assetManager.get("explodedBalloon.png", Texture.class);
                this.setTexture(explodedBalloonTexture);
                exploded = true;
                explosionTime = 0;
                return true;
            }
        }
        return false;
    }

    // Deixa um tempo para a "animação" de explosão:
    public boolean isReadyForRemoval(float delta) {
        if (!exploded) return false;
        explosionTime += delta;
        return explosionTime >= 0.2f;
    }

    public boolean isOutOfScreen() {
        return (getY() > Gdx.graphics.getHeight() || getY() + getHeight() < 0) ||
                (getX() > Gdx.graphics.getWidth() || getX() + getWidth() < 0);
    }

    public void update() {
        if (!exploded) {
            setPosition(getX(), getY() + 1);
        }
    }

    public void returnToFullTexture() {
        Texture balloonTexture = MyGdxGame.assetManager.get("balloon.png", Texture.class);
        this.setTexture(balloonTexture);
        exploded = false;
        explosionTime = 0;
    }
}
