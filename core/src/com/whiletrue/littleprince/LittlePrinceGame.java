package com.whiletrue.littleprince;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

public class LittlePrinceGame extends ApplicationAdapter {
	PolygonSpriteBatch batch;
	Texture texture;
	PolygonRegion region;
	
	@Override
	public void create () {
		texture = new Texture("planet.jpg");
		TextureRegion textureRegion = new TextureRegion(texture);

		float radius = 0.5f*textureRegion.getRegionWidth();

		PlanetOutline planetOutline = new PlanetOutline(1);

		planetOutline.cutLine(0.5f,-0.5f);
		planetOutline.cutLine(-0.5f,-0.5f);

		float[] vertices = planetOutline.getVertices(radius,radius,radius);

		region = new PolygonRegion(textureRegion, vertices, PlanetOutline.TRIANGLES);
		batch = new PolygonSpriteBatch();
		//debugRenderer = new PolygonRegionDebugRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(region, 128, 128, 256, 256);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		texture.dispose();
	}
}
