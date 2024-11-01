package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;

	Texture archerTexture, balloonTexture, arrowTexture, backgroundTexture;
	Archer archer;
	
	int screenHeight;
	int screenWidth;
	Random random;

	@Override
	public void create() {
		batch = new SpriteBatch();

		archerTexture = new Texture("archer.png");
		arrowTexture = new Texture("arrow.png");
		balloonTexture = new Texture("balloon.png");
		backgroundTexture = new Texture("background.png");

		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		random = new Random();

		archer = new Archer(0,0, archerTexture);
	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);

		batch.begin();

		batch.draw(backgroundTexture, 0, 0, screenWidth, screenHeight);
		archer.update(batch);
	
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		archerIdleTexture.dispose();
		archerShootingTexture.dispose();
		arrowTexture.dispose();
		balloonTexture.dispose();
	}
}