package com.whiletrue.littleprince;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;

public class LittlePrinceGame extends Game {
	Batch batch;

	@Override
	public void create () {
		batch = new PolygonSpriteBatch();
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
