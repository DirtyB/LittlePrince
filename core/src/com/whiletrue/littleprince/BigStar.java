package com.whiletrue.littleprince;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.concurrent.TimeUnit;

import static java.lang.StrictMath.sin;

/**
 * Created by dshevchenkoo on 23.04.17.
 */
public class BigStar extends Actor {

    private Texture texture = new Texture("star.png");
    private Texture textureSecond = new Texture("starOpacity.png");
    private Texture textureBack = new Texture("back.png");
    private Texture textureBackSecond = new Texture("littlestar.png");
    private Texture textureBackTherd = new Texture("littlestar2.png");
    private Texture textureBackForth = new Texture("littlestar3.png");
    private Texture textureRegionEffect = new Texture("backEffect.png");
    private Texture textureRegionEffect2 = new Texture("start.png");



    private float rotationSpeed = 2.5f;

    private PlanetOutline planetOutline;
    PolygonRegion region;
    PolygonRegion regionSecond;
    Sprite sprite;
    Sprite spriteEffect;
    Sprite spriteEffect2;

    Sprite spriteLittleStar;
    Sprite spriteLittleStar2;
    Sprite spriteLittleStar3;



    public BigStar(float drawingRadius, float physicalRadius){
        planetOutline = new PlanetOutline(drawingRadius, physicalRadius);

        TextureRegion textureRegion = new TextureRegion(texture);
        TextureRegion textureRegionSecond = new TextureRegion(textureSecond);
        TextureRegion textureRegionBack = new TextureRegion(textureBack);
        TextureRegion textureRegionEffects = new TextureRegion(textureRegionEffect);
        TextureRegion textureRegionEffects2 = new TextureRegion(textureRegionEffect2);


        float graphicRadius = 0.5f*textureRegion.getRegionWidth();

        float[] vertices = planetOutline.getVertices(graphicRadius,graphicRadius,graphicRadius);

        region = new PolygonRegion(textureRegion, vertices, PlanetOutline.TRIANGLES);
        regionSecond = new PolygonRegion(textureRegionSecond, vertices, PlanetOutline.TRIANGLES);
        sprite = new Sprite(textureRegionBack);
        spriteLittleStar = new Sprite(textureBackSecond);
        spriteLittleStar2 = new Sprite(textureBackTherd);
        spriteLittleStar3 = new Sprite(textureBackForth);
        spriteEffect = new Sprite(textureRegionEffects);
        spriteEffect2 = new Sprite(textureRegionEffects2);





        setSize(1f,1f);
        setOrigin(0,0);
        setPosition(0-drawingRadius,0-drawingRadius);
    }

    @Override
    public void draw(Batch batch, float alpha){
        ((PolygonSpriteBatch) batch).draw(region,2,2, getOriginX(), getOriginY(), 0.6f, 0.6f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,-2,2, getOriginX(), getOriginY(), 0.3f, 0.3f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,0,3.3f, getOriginX(), getOriginY(), 0.75f, 0.75f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,-5.1f,3f, getOriginX(), getOriginY(), 0.3f, 0.3f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,-6.8f,2.2f, getOriginX(), getOriginY(), 0.3f, 0.5f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,-7.5f,2.7f, getOriginX(), getOriginY(), 0.7f, 0.7f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,3,3, getOriginX(), getOriginY(), 0.3f, 0.3f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,4.5f,4, getOriginX(), getOriginY(), 0.3f, 0.3f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,4.8f,3.25f, getOriginX(), getOriginY(), 0.6f, 0.6f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,6f,2.8f, getOriginX(), getOriginY(), 0.9f, 0.9f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,6.6f,2.4f, getOriginX(), getOriginY(), 0.2f, 0.2f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,5.6f,2f, getOriginX(), getOriginY(), 0.2f, 0.2f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,2,-2, getOriginX(), getOriginY(), 0.6f, 0.6f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,-2,-2, getOriginX(), getOriginY(), 0.3f, 0.3f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,0,-3.3f, getOriginX(), getOriginY(), 0.75f, 0.75f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,-5.1f,-3f, getOriginX(), getOriginY(), 0.3f, 0.3f, getScaleX(), getScaleY(), getRotation());

        batch.draw(sprite,-9f, -4.5f,18,9);
        batch.draw(spriteEffect,-9f, -4.5f,18,9);
        batch.draw(spriteEffect2,-9f, -4.5f,18,9);




        batch.draw(spriteLittleStar,-9f, -4.5f,18,9);
        batch.draw(spriteLittleStar2, -9f, -4.5f, 18, 9);
        batch.draw(spriteLittleStar3, -9f, -4.5f, 18, 9);


        ((PolygonSpriteBatch) batch).draw(region,-3.6f,4f, getOriginX(), getOriginY(), 1f, 1f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,-7.9f,3.7f, getOriginX(), getOriginY(), 1f, 1f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,7.4f,4f, getOriginX(), getOriginY(), 1f, 1f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,-3.1f,-4f, getOriginX(), getOriginY(), 1f, 1f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,-6.8f,-2.2f, getOriginX(), getOriginY(), 0.3f, 0.5f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,-7.5f,-2.4f, getOriginX(), getOriginY(), 0.7f, 0.7f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,-7.2f,-3.7f, getOriginX(), getOriginY(), 1f, 1f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,3.1f,-2.5f, getOriginX(), getOriginY(), 0.3f, 0.3f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,4.2f,-4, getOriginX(), getOriginY(), 0.3f, 0.3f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,4.8f,-3.f, getOriginX(), getOriginY(), 0.6f, 0.6f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,6f,-2.8f, getOriginX(), getOriginY(), 1.5f, 1.5f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,6.6f,-2.1f, getOriginX(), getOriginY(), 0.2f, 0.2f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,7.f,-4f, getOriginX(), getOriginY(), 1.3f, 1.3f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,5.6f,-2f, getOriginX(), getOriginY(), 0.2f, 0.2f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,-4f,-0.5f, getOriginX(), getOriginY(), 0.2f, 0.2f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(region,4f,-0.5f, getOriginX(), getOriginY(), 0.2f, 0.2f, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(regionSecond,3.5f,3f, getOriginX(), getOriginY(), 2.3f, 2, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(regionSecond,5f,-5f, getOriginX(), getOriginY(), 2.3f, 2, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(regionSecond,-5f,-5f, getOriginX(), getOriginY(), 2.3f, 2, getScaleX(), getScaleY(), getRotation());
        ((PolygonSpriteBatch) batch).draw(regionSecond,-5.5f,1f, getOriginX(), getOriginY(), 2.3f, 2, getScaleX(), getScaleY(), getRotation());

    }



    @Override
    public void act(float delta){
        setRotation(getRotation()+rotationSpeed*delta);

    }

}


