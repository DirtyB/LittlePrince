package com.whiletrue.littleprince;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public abstract class AbstractObjectOnPlanet extends Actor{

    protected String textureFile;
    protected Planet planet;
    protected float currentAngle;

    protected TextureRegion textureRegion;

    AbstractObjectOnPlanet(Planet planet, float angle){
        this.planet = planet;

        Texture texture = new Texture(getTextureFileName());
        textureRegion = new TextureRegion(texture);

        currentAngle = angle;

        configureSizeAndOrigin();

        calculatePositionAndRotation();
    }

    protected abstract String getTextureFileName();

    protected abstract void configureSizeAndOrigin();

    @Override
    public void draw(Batch batch, float alpha){
        calculatePositionAndRotation();
        batch.draw(textureRegion,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
    }


    protected void calculatePositionAndRotation(){
        float angle = currentAngle + degreesToRadians(planet.getRotation());
        float radius = planet.getPlanetOutline().getPhysicalRadiusForAngle(currentAngle);
        float planetCenterX = planet.getXOfOrigin();
        float planetCenterY = planet.getYOfOrigin();
        float xOfOrigin = planetCenterX + (float)(radius*Math.cos(angle));
        float yOfOrigin = planetCenterY + (float)(radius*Math.sin(angle));
        setPosition(xOfOrigin-getOriginX(), yOfOrigin-getOriginY());
        setRotation(radiansToDegrees(angle-(float)(0.5 * Math.PI)));
    }

    float degreesToRadians(float degrees){
        return (float)(degrees*Math.PI/180f);
    }

    float radiansToDegrees(float degrees){
        return (float)(degrees*180f/Math.PI);
    }


}
