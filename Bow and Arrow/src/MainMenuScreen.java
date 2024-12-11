package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.MyGdxGame;

public class MainMenuScreen implements Screen {

    private final MyGdxGame game;
    private final SpriteBatch batch;
    private final Texture backgroundTexture;
    private final Stage stage;

    public MainMenuScreen(MyGdxGame game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.backgroundTexture = MyGdxGame.assetManager.get("menuBackground.jpg", Texture.class);
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Texture buttonTexture = MyGdxGame.assetManager.get("button.png", Texture.class);
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(buttonTexture));

        ImageButton startButton = new ImageButton(style);

        startButton.setSize(buttonTexture.getWidth()/10, buttonTexture.getHeight()/10);

        startButton.setPosition(
                Gdx.graphics.getWidth() / 2f - startButton.getWidth() / 2,
                Gdx.graphics.getHeight() / 2f - startButton.getHeight() / 2
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
    public void show() {}

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.draw();
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
        stage.dispose();
    }
}

