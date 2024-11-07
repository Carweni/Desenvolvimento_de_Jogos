package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	int screenHeight, screenWidth;
	float balloonTime;
	Texture archerTexture, balloonTexture, arrowTexture, backgroundTexture;
	Archer archer;
	BalloonController balloonController;
	ArrowController arrowController;  

	@Override
	public void create() {
		batch = new SpriteBatch();
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		balloonTime = 0;

		archerTexture = new Texture("archerIdle.png");
		balloonTexture = new Texture("balloon.png");
		arrowTexture = new Texture("arrow.png");
		backgroundTexture = new Texture("background.png");

		archer = new Archer(0, 0, archerTexture);
		balloonController = new BalloonController(archer);
		arrowController = new ArrowController(archer);
	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();

		batch.draw(backgroundTexture, 0, 0, screenWidth, screenHeight);
		archer.update(batch);

		// Controle de balões
		balloonTime += Gdx.graphics.getDeltaTime();
		if (balloonTime > 0.5) {
			balloonController.addBalloon(balloonTexture);
			balloonTime = 0;
		}
		balloonController.update(batch);

		if (archer.getEnterPressed()) {
			arrowController.addArrow(arrowTexture);
			archer.resetEnter();
		}

		arrowController.update(batch);

		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		archerTexture.dispose();
		arrowTexture.dispose();
		balloonTexture.dispose();
		backgroundTexture.dispose();
	}
}
