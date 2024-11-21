package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	int screenHeight, screenWidth;
	float balloonTime;
	Texture archerIdleTexture, archerShootingTexture, balloonTexture, arrowTexture, backgroundTexture;
	Archer archer;
	BalloonController balloonController;
	public static AssetManager assetManager;

	@Override
	public void create() {
		batch = new SpriteBatch();
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		balloonTime = 0;

		// Carregar as texturas com Asset Manager:
		assetManager = new AssetManager();
		assetManager.load("balloon.png", Texture.class);
		assetManager.load("archerIdle.png", Texture.class);
		assetManager.load("archerShooting.png", Texture.class);
		assetManager.load("arrow.png", Texture.class);
		assetManager.load("background.png", Texture.class);
		assetManager.finishLoading();

		archerIdleTexture = assetManager.get("archerIdle.png", Texture.class);
		archerShootingTexture = assetManager.get("archerShooting.png", Texture.class);

		archer = new Archer(0, 0, archerIdleTexture, archerShootingTexture);

		balloonController = new BalloonController(archer);
		ArrowController.init(archer);

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
		Texture arrowTexture = assetManager.get("arrow.png", Texture.class);

		batch.draw(backgroundTexture, 0, 0, screenWidth, screenHeight);
		archer.update(batch);

		// Controle de balões
		balloonTime += Gdx.graphics.getDeltaTime();
		if (balloonTime > 0.5) {
			balloonController.addBalloon(balloonTexture);
			balloonTime = 0;
		}
		balloonController.update(batch);

		ArrowController.draw(batch, arrowTexture);

		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		assetManager.dispose();
	}
}
