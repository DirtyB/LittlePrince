package com.whiletrue.littleprince;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public class Prince extends AbstractObjectOnPlanet {

    private static String PRINCE_TEXTURE_FILE_NAME = "prince.png";
    private static float PRINCE_HEIGHT = 1;
    private static float PRINCE_WIDTH = PRINCE_HEIGHT*2/3;
    private static float PRINCE_ORIGIN_RELATIVE_X = 0.5f;
    private static float PRINCE_ORIGIN_RELATIVE_Y = 0.05f;
    private static final float FRAME_DURATION = 0.1f;

    private TextureRegion stillTextureRegion;
    private Animation<TextureRegion> walkAnimation;


    Prince(GameScreen gameScreen, float angle) {
        super(gameScreen, angle);

        AssetManager assetManager = gameScreen.getGame().assetManager;

        stillTextureRegion = new TextureRegion((Texture)assetManager.get(PRINCE_TEXTURE_FILE_NAME));

        TextureAtlas atlas = assetManager.get("walk/walk.atlas");
        walkAnimation =  new Animation<TextureRegion>(FRAME_DURATION, atlas.findRegions("walk"), Animation.PlayMode.LOOP);
    }

    protected TextureRegion getCurrentTextureRegion(){
        //return stillTextureRegion;
        return walkAnimation.getKeyFrame(stateTime, true);
    }

    @Override
    protected void configureSizeAndOrigin() {
        setSize(PRINCE_WIDTH,PRINCE_HEIGHT);
        setOrigin(PRINCE_WIDTH*PRINCE_ORIGIN_RELATIVE_X, PRINCE_HEIGHT*PRINCE_ORIGIN_RELATIVE_Y);
    }


    @Override
    public void act(float delta){
        super.act(delta);
        currentAngle-=0.01f;
    }


}
