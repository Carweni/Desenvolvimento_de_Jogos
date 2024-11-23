package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

// Padrão abstract:
public abstract class BalloonController {
    private static ConcurrentLinkedQueue<Balloon> activeBalloons;
    private static ConcurrentLinkedQueue<Balloon> deadBalloons;

    public static void init() {
        activeBalloons = new ConcurrentLinkedQueue<>();
        deadBalloons = new ConcurrentLinkedQueue<>();
        Random random = new Random();
        int randomX = random.nextInt(Gdx.graphics.getWidth()) + 100;
        Balloon b = new Balloon(randomX, 0);
        activeBalloons.add(b);
    }

    public static void set(float x, float y) {
        Balloon b;
        if (!deadBalloons.isEmpty()) {
            b = deadBalloons.remove();
            b.returnToFullTexture();
        } else {
            b = new Balloon(x, y);
        }
        activeBalloons.add(b);
        b.setX(x);
        b.setY(y);
    }

    public static void draw(SpriteBatch batch) {
        for (Balloon b : activeBalloons) {
            b.draw(batch);
        }
    }

    public static void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        for (Balloon b : activeBalloons) {
            b.update();

            if (b.isOutOfScreen() || b.isReadyForRemoval(deltaTime)) {
                activeBalloons.remove(b);
                deadBalloons.add(b);
            }

            b.hasCollided();
        }
    }
}
