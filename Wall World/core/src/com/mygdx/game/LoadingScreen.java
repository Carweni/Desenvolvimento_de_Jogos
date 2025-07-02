package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LoadingScreen implements Screen {
    final float WORLD_HEIGHT = 1080;
    private int screenHeight, screenWidth;

    private OrthographicCamera cam;
    private Viewport viewport;

    private final WallWorld game;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture loadingTexture;
    private float elapsedTime;
    private static final float MIN_LOADING_TIME = 2f; // 2 segundos na tela de loading para dar tempo de ver.

    public LoadingScreen(WallWorld game) {
        // screen atributes
        this.screenHeight = com.badlogic.gdx.Gdx.graphics.getHeight();
        this.screenWidth = com.badlogic.gdx.Gdx.graphics.getWidth();
        float aspectRatio = (float) screenWidth / (float) screenHeight;

        // camera and viewport
        cam = new OrthographicCamera();
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
        viewport = new FitViewport(WORLD_HEIGHT*aspectRatio, WORLD_HEIGHT, cam);
        viewport.apply();

        this.game = game;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.loadingTexture = WallWorld.assetManager.get("loadingBackground.png", Texture.class);
        this.elapsedTime = 0f;
    }

    @Override
    public void show() {
        elapsedTime = 0f;
    }

    @Override
    public void render(float delta) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        float aspectRatio = (float) screenWidth / (float) screenHeight;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(loadingTexture, 0, 0, screenWidth, screenHeight);

        float progress = WallWorld.assetManager.getProgress() * 100;
                font.draw(batch, "Loading: " + (int) progress + "%",WORLD_HEIGHT*aspectRatio/2f-30, 100);
        batch.end();

        if (WallWorld.assetManager.update() && elapsedTime >= MIN_LOADING_TIME) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
