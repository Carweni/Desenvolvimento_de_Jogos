package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.audio.Music;

public class MainMenuScreen implements Screen{
    final float WORLD_WIDTH = 1000;
    final float WORLD_HEIGHT = 1080;
    private int screenHeight, screenWidth;

    private final OrthographicCamera cam;
    private final Viewport viewport;

    private final SpriteBatch batch;
    private final Texture backgroundTexture;
    private final Stage stage;

    private Music music;

    public MainMenuScreen(WallWorld game) {
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

        music = Gdx.audio.newMusic(Gdx.files.internal("music/mainMenuMusic.mp3"));
        music.setLooping(true);
        music.setVolume(0.2f);

        this.batch = new SpriteBatch();
        this.backgroundTexture = WallWorld.assetManager.get("menuBackground.png", Texture.class);
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Texture buttonTexture = WallWorld.assetManager.get("button.png", Texture.class);
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(buttonTexture));

        ImageButton startButton = new ImageButton(style);

        startButton.setSize((float)buttonTexture.getWidth()/10, (float)buttonTexture.getHeight()/10);

        startButton.setPosition(
                WORLD_HEIGHT*aspectRatio / 2f - startButton.getWidth()/2,
                WORLD_HEIGHT / 5f - startButton.getHeight()
        );

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.startGame();
            }
        });

        stage.addActor(startButton);

    }

    @Override
    public void show() {
        music.play();
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, screenWidth, screenHeight);
        batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        music.stop();
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        music.dispose();
    }
}