package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;

	int screenHeight;
	int screenWidth;

	Random random;
	boolean isShooting;
	float shootTime;
	float balloonTime;
	int contBalloon;

	Texture archerTexture, balloonTexture, arrowTexture, backgroundTexture;
	Archer archer;
	BalloonController balloonController;
	ArrayList<Arrow> arrows;
	Pool<Balloon> balloonPool;

	@Override
	public void create () {
		batch = new SpriteBatch();
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		balloonTime = 0;
		contBalloon = 0;

		archerTexture= new Texture("archer.png");
		balloonTexture= new Texture("balloon.png");
		arrowTexture = new Texture("arrow.png");
		backgroundTexture = new Texture("background.png");

		archer = new Archer(0,0, archerTexture);
		balloonController = new BalloonController();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();

		batch.draw(backgroundTexture, 0, 0, screenWidth, screenHeight);
		archer.update(batch);

		balloonTime += Gdx.graphics.getDeltaTime();

		if(balloonTime > 1 && contBalloon <= 5){
			contBalloon++;
			balloonController.addBalloon(200, -40, balloonTexture);
			balloonTime = 0;
		}

		balloonController.update(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		arrowTexture.dispose();
		balloonTexture.dispose();
		backgroundTexture.dispose();
	}
}
