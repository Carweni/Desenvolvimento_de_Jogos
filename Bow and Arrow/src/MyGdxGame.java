package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	int screenHeight, screenWidth;
	float balloonTime;
	Texture archerIdleTexture, archerShootingTexture;
	Archer archer;
	public static AssetManager assetManager;

	@Override
	public void create() {
		batch = new SpriteBatch();
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		balloonTime = 0;

		// Carregar as texturas
		assetManager = new AssetManager();
		assetManager.load("balloon.png", Texture.class);
		assetManager.load("archerIdle.png", Texture.class);
		assetManager.load("archerShooting.png", Texture.class);
		assetManager.load("arrow.png", Texture.class);
		assetManager.load("background.png", Texture.class);
		assetManager.load("explodedBalloon.png", Texture.class);
		assetManager.finishLoading();

		archerIdleTexture = assetManager.get("archerIdle.png", Texture.class);
		archerShootingTexture = assetManager.get("archerShooting.png", Texture.class);

		// Passa as texturas do Asset Manager para o arqueiro:
		archer = new Archer(0, 0, archerIdleTexture, archerShootingTexture);

		BalloonController.init();
		ArrowController.init(archer);

		// Multiplexador:
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(archer.getInputProcessor());
		multiplexer.addProcessor(ArrowController.getArrowInputProcessor());
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();

		Texture backgroundTexture = assetManager.get("background.png", Texture.class);

		batch.draw(backgroundTexture, 0, 0, screenWidth, screenHeight);
		archer.update(batch);

		ArrowController.draw(batch);
		BalloonController.draw(batch);

		batch.end();

		// Controle de balões:
		balloonTime += Gdx.graphics.getDeltaTime();
		if (balloonTime > 0.5) {
			Random random = new Random();
			int randomX = random.nextInt(screenWidth - (int) archer.getX() - 100) + 100;
			BalloonController.set((float)(randomX), 0);
			balloonTime = 0;
		}
		BalloonController.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		assetManager.dispose();
	}
}
