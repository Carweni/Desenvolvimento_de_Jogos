package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public abstract class ArrowController {
    private static ArrayList<Arrow> activeArrows;
    private static ArrayList<Arrow> deadArrows;
    private static Archer archer;
    private static ArrowInputProcessor arrowInputProcessor;

    public static void init(Archer archer1) {
        activeArrows = new ArrayList<>();
        deadArrows = new ArrayList<>();
        archer = archer1;
        arrowInputProcessor = new ArrowInputProcessor();
    }

    public void addArrow(Texture arrowTexture) {
        float archerX = archer.getX();
        Arrow currentArrow;

        if (!deadArrows.isEmpty()) {
            currentArrow = deadArrows.remove(deadArrows.size() - 1);
        } else {
            currentArrow = new Arrow(
                    archerX + archer.getWidth() / 2,
                    archer.getY() + 5,
                    archer
            );
        }
        activeArrows.add(currentArrow);
    }

    public static void draw(SpriteBatch batch, Texture arrowTexture) {
        handleArrowCreation(arrowTexture);

        for (Arrow a : activeArrows) {
            a.draw(batch);
            a.updateArrow(batch);

            if (a.isOutOfScreen()) {
                activeArrows.remove(a);
                deadArrows.add(a);
            }
        }
    }

    private static void handleArrowCreation(Texture arrowTexture) {
        if (arrowInputProcessor.getEnterPressed()) {
            float archerX = archer.getX();
            Arrow currentArrow;

            if (!deadArrows.isEmpty()) {
                currentArrow = deadArrows.remove(deadArrows.size() - 1); 
            } else {
                currentArrow = new Arrow(
                        archerX + archer.getWidth() / 2,
                        archer.getY() + 5,
                        archer
                );
            }
            activeArrows.add(currentArrow);

            arrowInputProcessor.resetEnterPressed();
        }
    }

    public static boolean getArrowKeyDownPressed() {
        return arrowInputProcessor.getEnterPressed();
    }

    public void update(SpriteBatch batch) {
        for (Arrow arrow : activeArrows) {
            arrow.updateArrow(batch);
        }
    }

    public boolean getEnterPressed() {
        return arrowInputProcessor.getEnterPressed();
    }

    public void resetEnter() {
        arrowInputProcessor.resetEnterPressed();
    }

    public static ArrowInputProcessor getArrowInputProcessor() {
        return arrowInputProcessor;
    }
}
