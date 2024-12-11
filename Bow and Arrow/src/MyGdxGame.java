package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.screens.GameScreen;

public class MyGdxGame extends Game {
	public static AssetManager assetManager;

	@Override
	public void create() {
		assetManager = new AssetManager();
		assetManager.load("balloon.png", Texture.class);
		assetManager.load("archerIdle.png", Texture.class);
		assetManager.load("archerShooting.png", Texture.class);
		assetManager.load("arrow.png", Texture.class);
		assetManager.load("background.png", Texture.class);
		assetManager.load("menuBackground.jpg", Texture.class);
		assetManager.load("explodedBalloon.png", Texture.class);
		assetManager.load("button.png", Texture.class);
		assetManager.load("arrow-hitting.mp3", Sound.class);
		assetManager.load("arrow-shooting.mp3", Sound.class);
		assetManager.finishLoading();

		this.setScreen(new MainMenuScreen(this));
	}

	public void startGame() {
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		assetManager.dispose();
	}
}