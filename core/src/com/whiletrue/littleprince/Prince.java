package com.whiletrue.littleprince;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public class Prince extends AbstractObjectOnPlanet {

    private static String PRINCE_TEXTURE_FILE_NAME = "prince.png";
    private static float PRINCE_WIDTH = 0.5f;
    private static float PRINCE_HEIGHT = PRINCE_WIDTH*2;
    private static float PRINCE_ORIGIN_RELATIVE_X = 0.5f;
    private static float PRINCE_ORIGIN_RELATIVE_Y = 0.05f;

    private TextureRegion stillTextureRegion;

    Prince(GameScreen gameScreen, float angle) {
        super(gameScreen, angle);
        stillTextureRegion = new TextureRegion((Texture) gameScreen.getGame().assetManager.get(PRINCE_TEXTURE_FILE_NAME));
    }

    protected TextureRegion getCurrentTextureRegion(){
        return stillTextureRegion;
    }

    @Override
    protected void configureSizeAndOrigin() {
        setSize(PRINCE_WIDTH,PRINCE_HEIGHT);
        setOrigin(PRINCE_WIDTH*PRINCE_ORIGIN_RELATIVE_X, PRINCE_HEIGHT*PRINCE_ORIGIN_RELATIVE_Y);
    }


    @Override
    public void act(float delta){
        currentAngle-=0.01f;
    }


}
