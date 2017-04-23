package com.whiletrue.littleprince;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class LittlePrinceGame extends Game {
	Batch batch;
	AssetManager assetManager;

	@Override
	public void create () {

		assetManager = new AssetManager();

		batch = new PolygonSpriteBatch();
		this.setScreen(new StartScreen(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
	}

	public void startGame(){
		this.setScreen(new GameScreen(this));
	}

	public void endGame(){
		this.setScreen(new StartScreen(this));
	}
}
