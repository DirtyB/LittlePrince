package com.whiletrue.littleprince;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LittlePrinceGame extends ApplicationAdapter {
	private Stage stage;
	private Planet planet;
	private Batch batch;

	@Override
	public void create() {

		//todo: viewport
		Viewport viewport = new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
		Batch batch = new PolygonSpriteBatch();

		stage = new Stage(viewport,batch);
		Gdx.input.setInputProcessor(stage);

		planet = new Planet(1);
		//myActor.setTouchable(Touchable.enabled);
		stage.addActor(planet);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

}
