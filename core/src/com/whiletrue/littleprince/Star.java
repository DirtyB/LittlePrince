package com.whiletrue.littleprince;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.StrictMath.sin;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public class Star extends Actor {
    private Texture texture = new Texture("star.png");
    private float rotationSpeed = 0.1f;

    private PlanetOutline planetOutline;
    PolygonRegion region;
    Sprite sprite;


    public Star(float drawingRadius, float physicalRadius) {
        planetOutline = new PlanetOutline(drawingRadius, physicalRadius);

        TextureRegion textureRegion = new TextureRegion(texture);

        float graphicRadius = 0.5f * textureRegion.getRegionWidth();

        float[] vertices = planetOutline.getVertices(graphicRadius, graphicRadius, graphicRadius);

        region = new PolygonRegion(textureRegion, vertices, PlanetOutline.TRIANGLES);

        setSize(0.2f, 0.2f);
        setOrigin(drawingRadius, drawingRadius);

        setPosition(0, 0);
    }

    @Override
    public void draw(Batch batch, float alpha) {

        //((PolygonSpriteBatch) batch).draw(region, (float) (-6 + Math.random() * 100), (float) (-4 + Math.random() * 100), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region, (float) (-6 + Math.random() * 50), (float) (-4 + Math.random() * 50), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region, (float) (-6 + Math.random() * 150), (float) (-4 + Math.random() * 150), getOriginX(), getOriginY(), (float) (3.f + Math.random() * 5.f), 0.1f, getScaleX(), getScaleY(), getRotation());

    }
    @Override
    public void act(float delta){
        setRotation(getRotation()+rotationSpeed*delta);
    }


}
