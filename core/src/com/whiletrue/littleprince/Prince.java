package com.whiletrue.littleprince;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public class Prince extends AbstractObjectOnPlanet {

    private static String PRINCE_TEXTURE_FILE_NAME = "prince.png";
    private static float PRINCE_WIDTH = 0.5f;
    private static float PRINCE_HEIGHT = PRINCE_WIDTH*2;
    private static float PRINCE_ORIGIN_RELATIVE_X = 0.5f;
    private static float PRINCE_ORIGIN_RELATIVE_Y = 0.05f;

    Prince(Planet planet, float angle) {
        super(planet, angle);

        setPosition(1,1);
    }

    protected String getTextureFileName(){
        return PRINCE_TEXTURE_FILE_NAME;
    }

    @Override
    protected void configureSizeAndOrigin() {
        setSize(PRINCE_WIDTH,PRINCE_HEIGHT);
        setOrigin(PRINCE_WIDTH*PRINCE_ORIGIN_RELATIVE_X, PRINCE_HEIGHT*PRINCE_ORIGIN_RELATIVE_Y);
    }


    @Override
    public void act(float delta){
    }


}
