package com.mygdx.game;

import java.util.ArrayList;

public class BalloonController {
    private ArrayList<Balloon> balloons;

    public BalloonController(){
        balloons = new ArrayList<>();
    }

    public void addBalloon(Balloon b){
        balloons.add(b);
    }

    public Balloon getNextBalloon(){
        Balloon b = balloons.get(0);
        balloons.remove(b);
        return b;
    }
}