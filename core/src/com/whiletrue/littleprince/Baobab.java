package com.whiletrue.littleprince;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public class Baobab extends AbstractObjectOnPlanet {

    public static String BAOBAB_ANIMATION_ATLAS_NAME = "baobab/baobab.atlas";
    public static String BAOBAB_ANIMATION_REGIONS_NAME = "baobab";
    private static float BAOBAB_HEIGHT = 2f;
    private static float BAOBAB_WIDTH = BAOBAB_HEIGHT;
    private static float BAOBAB_ORIGIN_RELATIVE_X = 0.5f;
    private static float BAOBAB_ORIGIN_RELATIVE_Y = 0.05f;
    private static final float FRAME_DURATION = 1;//5f;

    private Animation<TextureRegion> baobabAnimation;


    Baobab(GameScreen gameScreen, float angle) {
        super(gameScreen, angle);

        AssetManager assetManager = gameScreen.getGame().assetManager;

        TextureAtlas atlas = assetManager.get(BAOBAB_ANIMATION_ATLAS_NAME);
        baobabAnimation =  new Animation<TextureRegion>(FRAME_DURATION, atlas.findRegions(BAOBAB_ANIMATION_REGIONS_NAME), Animation.PlayMode.NORMAL);
    }

    @Override
    protected void setParent(Group parent) {
        super.setParent(parent);
        if(parent!=null){
            setZIndex(20);
        }
    }

    protected TextureRegion getCurrentTextureRegion(){
        return baobabAnimation.getKeyFrame(stateTime, false);
    }

    @Override
    protected void configureSizeAndOrigin() {
        setSize(BAOBAB_WIDTH, BAOBAB_HEIGHT);
        setOrigin(BAOBAB_WIDTH * BAOBAB_ORIGIN_RELATIVE_X, BAOBAB_HEIGHT * BAOBAB_ORIGIN_RELATIVE_Y);
    }


    @Override
    public void act(float delta){
        super.act(delta);
    }

}
