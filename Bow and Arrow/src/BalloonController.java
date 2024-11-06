package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class BalloonController {
    private final ArrayList<Balloon> ActiveBalloons;

    BalloonController() {
        ActiveBalloons = new ArrayList<>();
    }

    public void addBalloon(int x, int y, Texture balloonTexture){
        ActiveBalloons.add(new Balloon(x, y, balloonTexture));
    }

    public void update(SpriteBatch batch){
        if(!ActiveBalloons.isEmpty()) {
            for (Balloon balloon : ActiveBalloons) {
                balloon.update(batch);
            }
        }
    }
}
