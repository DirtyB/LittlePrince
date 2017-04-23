package com.whiletrue.littleprince;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.whiletrue.littleprince.MathUtils.*;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public abstract class AbstractObjectOnPlanet extends Actor{

    protected GameScreen gameScreen;
    protected Planet planet;
    protected float currentAngle;
    protected float stateTime;

    AbstractObjectOnPlanet(GameScreen gameScreen, float angle){
        this.gameScreen = gameScreen;
        this.planet = gameScreen.getPlanet();

        currentAngle = angle;

        configureSizeAndOrigin();

        calculatePositionAndRotation();
    }

    protected abstract TextureRegion getCurrentTextureRegion();

    protected abstract void configureSizeAndOrigin();

    @Override
    public void draw(Batch batch, float alpha){
        TextureRegion textureRegion = getCurrentTextureRegion();
        calculatePositionAndRotation();
        batch.draw(textureRegion,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
    }


    protected void calculatePositionAndRotation(){
        currentAngle = normaliseAngle(currentAngle);
        float angle = currentAngle + degreesToRadians(planet.getRotation());
        float radius = planet.getPlanetOutline().getPhysicalRadiusForAngle(currentAngle);
        float planetCenterX = planet.getXOfOrigin();
        float planetCenterY = planet.getYOfOrigin();
        float xOfOrigin = planetCenterX + (float)(radius*Math.cos(angle));
        float yOfOrigin = planetCenterY + (float)(radius*Math.sin(angle));
        setPosition(xOfOrigin-getOriginX(), yOfOrigin-getOriginY());
        setRotation(radiansToDegrees(angle-(float)(0.5 * Math.PI)));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime+=delta;
    }
}
