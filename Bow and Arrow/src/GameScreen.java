package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Archer;
import com.mygdx.game.BalloonController;
import com.mygdx.game.ArrowController;
import java.util.Random;

public class GameScreen implements Screen {

    private final MyGdxGame game;
    private SpriteBatch batch;
    private int screenHeight, screenWidth;
    private float balloonTime;
    private Archer archer;

    public GameScreen(MyGdxGame game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.screenHeight = com.badlogic.gdx.Gdx.graphics.getHeight();
        this.screenWidth = com.badlogic.gdx.Gdx.graphics.getWidth();
        this.balloonTime = 0;

        Texture archerIdleTexture = MyGdxGame.assetManager.get("archerIdle.png", Texture.class);
        Texture archerShootingTexture = MyGdxGame.assetManager.get("archerShooting.png", Texture.class);
        this.archer = new Archer(0, 0, archerIdleTexture, archerShootingTexture);

        BalloonController.init();
        ArrowController.init(archer);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(archer.getInputProcessor());
        multiplexer.addProcessor(ArrowController.getArrowInputProcessor());
        Gdx.input.setInputProcessor(multiplexer);

    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        Texture backgroundTexture = MyGdxGame.assetManager.get("background.png", Texture.class);
        batch.draw(backgroundTexture, 0, 0, screenWidth, screenHeight);
        archer.update(batch);
        ArrowController.draw(batch);
        BalloonController.draw(batch);
        batch.end();

        balloonTime += com.badlogic.gdx.Gdx.graphics.getDeltaTime();
        if (balloonTime > 0.5) {
            Random random = new Random();
            int randomX = random.nextInt(screenWidth - (int) archer.getX() - 100) + 100;
            BalloonController.set((float) randomX, 0);
            balloonTime = 0;
        }
        BalloonController.update();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
    }
}