package com.whiletrue.littleprince;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public class Planet extends Actor {
    private Texture texture = new Texture("planet.png");
    private float rotationSpeed = 10f;
    private PlanetOutline planetOutline;
    PolygonSprite sprite;


    public Planet(float radius){
        planetOutline = new PlanetOutline(radius);

        TextureRegion textureRegion = new TextureRegion(texture);

        float graphicRadius = 0.5f*textureRegion.getRegionWidth();

        PlanetOutline planetOutline = new PlanetOutline(1);

        planetOutline.cutLine(0.5f,-0.5f);
        planetOutline.cutLine(-0.5f,-0.5f);

        float[] vertices = planetOutline.getVertices(graphicRadius,graphicRadius,graphicRadius);

        PolygonRegion region = new PolygonRegion(textureRegion, vertices, PlanetOutline.TRIANGLES);


        sprite = new PolygonSprite(region);
        sprite.setOrigin(graphicRadius,graphicRadius);
        sprite.setPosition(0,0);
    }

    @Override
    public void draw(Batch batch, float alpha){
        sprite.draw((PolygonSpriteBatch)batch);
    }

    @Override
    public void act(float delta){
        sprite.setRotation(sprite.getRotation()+rotationSpeed*delta);
    }
}
