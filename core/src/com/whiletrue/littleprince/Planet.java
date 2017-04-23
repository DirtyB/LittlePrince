package com.whiletrue.littleprince;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public class Planet extends Actor {
    private Texture texture = new Texture("planet.png");
    private float rotationSpeed = 20f;

    private PlanetOutline planetOutline;
    PolygonRegion region;


    public Planet(float drawingRadius, float physicalRadius){
        planetOutline = new PlanetOutline(drawingRadius, physicalRadius);

        TextureRegion textureRegion = new TextureRegion(texture);

        float graphicRadius = 0.5f*textureRegion.getRegionWidth();

        float[] vertices = planetOutline.getVertices(graphicRadius,graphicRadius,graphicRadius);

        region = new PolygonRegion(textureRegion, vertices, PlanetOutline.TRIANGLES);

        setSize(drawingRadius*2,drawingRadius*2);
        setOrigin(drawingRadius,drawingRadius);
        setPosition(0-drawingRadius,0-drawingRadius);
    }

    @Override
    public void draw(Batch batch, float alpha){
        ((PolygonSpriteBatch)batch).draw(region,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
    }

    @Override
    public void act(float delta){
        setRotation(getRotation()+rotationSpeed*delta);
    }

    public PlanetOutline getPlanetOutline() {
        return planetOutline;
    }

    public float getXOfOrigin(){
        return getX()+getOriginX();
    }
    public float getYOfOrigin(){
        return getY()+getOriginY();
    }

}
