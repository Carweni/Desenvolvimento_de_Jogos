package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Archer archer;
	Texture archerIdleTexture;
	Texture archerShootingTexture;
	Texture balloonTexture;
	Texture arrowTexture, backgroundTexture;
	BalloonController balloonController;
	ArrayList<Arrow> arrows;
	int screenHeight;
	int screenWidth;
	Random random;
	boolean isShooting;
	float shootTime;

	@Override
	public void create() {
		batch = new SpriteBatch();
		archerIdleTexture = new Texture("archerIdle.png");
		archerShootingTexture = new Texture("archerShooting.png");
		arrowTexture = new Texture("arrow.png");
		balloonTexture = new Texture("balloon.png");
		backgroundTexture = new Texture("background.png");
		archer = new Archer(0, 0, archerIdleTexture);
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		balloonController = new BalloonController();
		arrows = new ArrayList<>();
		random = new Random();

	}

	public void shootArrow() {
		// Cria uma nova flecha na posição atual do arqueiro
		Arrow arrow = new Arrow(arrowTexture, archer.getPositionX() + 50, archer.getPositionY() + 50);
		arrows.add(arrow);

		// Troca o sprite do arqueiro para o de atirando
		archer.setArcherTexture(archerShootingTexture);
		isShooting = true;
		shootTime = 0; // Reset do tempo para controlar duração da animação
	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);

		batch.begin();
		batch.draw(backgroundTexture, 0, 0, screenWidth, screenHeight);

		// Atualiza e desenha o arqueiro
		shootTime += Gdx.graphics.getDeltaTime();
		if (isShooting && shootTime > 0.2f) { // 0.2 segundos de "atirando"
			archer.setArcherTexture(archerIdleTexture);
			isShooting = false;
		}

		archer.draw();
		// Atualiza e desenha cada flecha
		for (int i = 0; i < arrows.size(); i++) {
			Arrow arrow = arrows.get(i);
			if (arrow.isActive()) {
				arrow.updatePosition();
				batch.draw(arrow.getTexture(), arrow.getPositionX(), arrow.getPositionY() - 45, 20, 50);

				// Desativa a flecha se sair da tela
				if (arrow.getPositionX() > screenWidth) {
					arrow.deactivate();
				}
			}
		}

		// Remove flechas inativas da lista
		arrows.removeIf(arrow -> !arrow.isActive());

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

