package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Laser extends Sprite {
    private boolean active;
    public LaserInputProcessor laserInputProcessor;
    private final Rectangle hitbox;
    private float timeElapsed;
    private final Texture texture1;
    private final Texture texture2;
    private boolean isTexture1; // Controla qual textura está sendo usada

    public Laser(float startX, float startY) {
        super((Texture) WallWorld.assetManager.get("miningLaser1.png"));
        texture1 = new Texture("miningLaser1.png");
        texture2 = new Texture("miningLaser2.png");
        this.setSize(32, 64);
        this.setPosition(startX, startY);
        this.active = false;
        this.laserInputProcessor = new LaserInputProcessor();

        this.hitbox = new Rectangle(startX, startY, 32, 64);
        this.timeElapsed = 0;
        this.isTexture1 = true;
    }

    public void draw(SpriteBatch batch) {
        if (active) {
            super.draw(batch);
        }
    }

    public void update(float deltaTime, boolean isFacingRight) {
        if (active) {
            timeElapsed += deltaTime;

            if (timeElapsed >= 0.2f) {
                timeElapsed = 0;

                if (isTexture1) {
                    isTexture1 = false;
                    setRegion(texture2);
                } else {
                    isTexture1 = true;
                    setRegion(texture1);
                }
            }

            setFlip(!isFacingRight, false);
        }

    }

    public void updatePosition(float x, float y) {
        setPosition(x, y);
        hitbox.set(x, y, 32, 64);
    }

    public void activate() {
        active = true;
    }

    public void deactivate() {
        hitbox.set(0,0,0,0);
        active = false;
    }

    public LaserInputProcessor getLaserInputProcessor() {
        return this.laserInputProcessor;
    }

    public Rectangle getHitbox() {
        return this.hitbox;
    }

    public boolean checkCollision(Rectangle other) {
        return hitbox.overlaps(other);
    }

    public boolean isActive(){
        return active;
    }

}
