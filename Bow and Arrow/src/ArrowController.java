package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.concurrent.ConcurrentLinkedQueue;

// Padrão abstract:
public abstract class ArrowController {
    public static ConcurrentLinkedQueue<Arrow> activeArrows;
    private static ConcurrentLinkedQueue<Arrow> deadArrows;
    private static Archer archer;
    private static ArrowInputProcessor arrowInputProcessor;

    public static void init(Archer archer1) {
        activeArrows = new ConcurrentLinkedQueue<>();
        deadArrows = new ConcurrentLinkedQueue<>();
        archer = archer1;
        arrowInputProcessor = new ArrowInputProcessor();
    }

    public static void draw(SpriteBatch batch) {
        handleArrowCreation();

        for (Arrow a : activeArrows) {
            a.draw(batch);
            a.updateArrow(batch);

            if (a.isOutOfScreen()) {
                activeArrows.remove(a);
                deadArrows.add(a);
            }
        }
    }

    private static void handleArrowCreation() {
        if (arrowInputProcessor.getEnterPressed()) {
            float archerX = archer.getX();
            Arrow currentArrow;

            if (!deadArrows.isEmpty()) {
                currentArrow = deadArrows.remove();
                currentArrow.setPosition(
                        archerX + archer.getWidth() / 2,
                        archer.getY() + 5
                );
            } else {
                currentArrow = new Arrow(
                        archerX + archer.getWidth() / 2,
                        archer.getY() + 5
                );
            }
            activeArrows.add(currentArrow);

            arrowInputProcessor.resetEnterPressed();
        }
    }

    public static boolean getArrowKeyDownPressed() {
        return arrowInputProcessor.getEnterPressed();
    }

    public static ArrowInputProcessor getArrowInputProcessor() {
        return arrowInputProcessor;
    }
}
