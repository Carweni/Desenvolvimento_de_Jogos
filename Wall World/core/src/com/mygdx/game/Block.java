package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Block extends Sprite {
    private Rectangle hitbox;
    private float collisionTime;
    private float breakTime;
    private boolean isActive;
    private String tipo;
    private ParticleEffect particleEffect;
    private boolean particleStarted; // Para garantir que o efeito só seja usado 1 vez.
    private Sound blockBreakSound;
    private boolean oreState;

    public Block(int x, int y, Texture t, String tipo, float time, ParticleEffect particleEffect) {
        super(t);
        this.setPosition(x, y);
        this.setSize(72, 72);
        createHitbox();
        this.isActive = true;
        this.collisionTime = 0;
        this.tipo = tipo;
        this.breakTime = time;
        this.particleEffect = particleEffect;
        this.particleStarted = false;
        this.oreState = false;
        blockBreakSound = WallWorld.assetManager.get("sounds/stone-breaking.mp3", Sound.class);
    }

    private void createHitbox() {
        hitbox = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void update(float deltaTime, Laser laser) {
        if (isActive && laser.checkCollision(hitbox)) {
            collisionTime += deltaTime;
            if (collisionTime >= breakTime) {
                isActive = false;
                if(tipo!="bloco"){
                    oreState = true;
                }
                hitbox.set(0, 0, 0, 0); // Desativa a hitbox
                blockBreakSound.play();

                if (!particleStarted) {
                    particleEffect.setPosition(getX() + getWidth() / 2, getY() + getHeight() / 2) ; // Centraliza o efeito no bloco
                    particleEffect.start();
                    particleStarted = true;
                }
            }
        } else {
            collisionTime = 0;
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isInOreState() {
        return oreState; }

    public String getTipo() {
        return tipo;
    }

    public ParticleEffect getParticleEffect() {
        return particleEffect;
    }

    public boolean isParticleFinished() {
        return particleEffect.isComplete();
    }

    public void makeOre (Texture t){
        super.setTexture(t);
        hitbox.set(getX()+ getWidth()/2 - 10, getY() + getHeight()/2 - 10, 20, 20);
    }

    public void deactivateHitbox(){
        hitbox.set(0,0,0,0);
        this.oreState = false;
    }
}