package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Random;

public class BalloonController {
    private final ArrayList<Balloon> activeBalloons;
    private final Random random;
    private final Archer archer;

    public BalloonController(Archer archer) {
        this.activeBalloons = new ArrayList<>();
        this.random = new Random();
        this.archer = archer;
    }

    public void addBalloon(Texture balloonTexture) {
        int screenWidth = Gdx.graphics.getWidth();
        float archerX = archer.getX();
        int randomX = random.nextInt(screenWidth - (int) archerX - 100) + 100;

        activeBalloons.add(new Balloon(randomX, -40, balloonTexture));
    }

    public void update(SpriteBatch batch) {
        for (Balloon balloon : activeBalloons) {
            balloon.update(batch);
        }
    }
}
