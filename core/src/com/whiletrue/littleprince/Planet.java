package com.whiletrue.littleprince;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public class Planet extends Actor {

    private static String TEXTURE_DESCRIPTOR = "planet.png";

    private float rotationSpeed = 20f;

    public LittlePrinceGame game;

    private PlanetOutline planetOutline;
    PolygonRegion region;


    public Planet(LittlePrinceGame game, float drawingRadius, float physicalRadius){
        this.game = game;

        planetOutline = new PlanetOutline(drawingRadius, physicalRadius);

        Texture texture = game.assetManager.get(TEXTURE_DESCRIPTOR);
        TextureRegion textureRegion = new TextureRegion(texture);

        float graphicRadius = 0.5f*textureRegion.getRegionWidth();

        //planetOutline.cutLine(0.5f,-0.5f);
        //planetOutline.cutLine(-0.5f,-0.5f);

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
