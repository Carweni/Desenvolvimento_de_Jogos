package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class WallWorld extends Game {
	public static AssetManager assetManager;

	@Override
	public void create() {
		assetManager = new AssetManager();
		assetManager.load("ship.png", Texture.class);
		assetManager.load("background.jpg", Texture.class);
		assetManager.load("background2.jpg", Texture.class);
		assetManager.load("spiderOpen.png", Texture.class);
		assetManager.load("diamondOre.png", Texture.class);
		assetManager.load("calcoOre.png", Texture.class);
		assetManager.load("stone.png", Texture.class);
		assetManager.load("button.png", Texture.class);
		assetManager.load("menuBackground.png", Texture.class);
		assetManager.load("loadingBackground.png", Texture.class);
		assetManager.load("block.png", Texture.class);
		assetManager.load("miningLaser1.png", Texture.class);
		assetManager.load("miningLaser2.png", Texture.class);
		assetManager.load("minerioBlock2.png", Texture.class);
		assetManager.load("minerioBlock3.png", Texture.class);
		assetManager.load("menuBg.png", Texture.class);
		assetManager.load("sounds/stone-breaking.mp3", Sound.class);
		assetManager.load("honey_small.png", Texture.class);
		assetManager.load("coil_small.png", Texture.class);
		assetManager.load("diamond-small.png", Texture.class);
		assetManager.load("chalcopyrite-small.png", Texture.class);
		assetManager.finishLoading();

		this.setScreen(new LoadingScreen(this));
	}

	public void startGame() {
		this.setScreen(new GameScreen(null));
	}

	@Override
	public void dispose() {
		super.dispose();
		assetManager.dispose();
	}
}
