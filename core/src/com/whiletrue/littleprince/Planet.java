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
    private float rotationSpeed = 5f;
    private PlanetOutline planetOutline;
    PolygonRegion region;


    public Planet(float radius){
        planetOutline = new PlanetOutline(radius);

        TextureRegion textureRegion = new TextureRegion(texture);

        float graphicRadius = 0.5f*textureRegion.getRegionWidth();

        PlanetOutline planetOutline = new PlanetOutline(1);

        //planetOutline.cutLine(0.5f,-0.5f);
        //planetOutline.cutLine(-0.5f,-0.5f);

        float[] vertices = planetOutline.getVertices(graphicRadius,graphicRadius,graphicRadius);

        region = new PolygonRegion(textureRegion, vertices, PlanetOutline.TRIANGLES);

        setSize(radius*2,radius*2);
        setOrigin(radius,radius);
        setPosition(0-radius,0-radius);
    }

    @Override
    public void draw(Batch batch, float alpha){
        ((PolygonSpriteBatch)batch).draw(region,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
    }

    @Override
    public void act(float delta){
        setRotation(getRotation()+rotationSpeed*delta);
    }
}
