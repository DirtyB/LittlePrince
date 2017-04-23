package com.whiletrue.littleprince;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;

import javax.swing.plaf.nimbus.State;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public class Prince extends AbstractObjectOnPlanet {

    public static String PRINCE_STILL_TEXTURE_FILE_NAME = "prince.png";
    public static String PRINCE_ATTACK_TEXTURE_FILE_NAME = "prince_sword.png";
    public static String PRINCE_WALK_ANIMATION_ATLAS_NAME = "walk/walk.atlas";
    public static String PRINCE_WALK_ANIMATION_REGIONS_NAME = "walk";
    private static float PRINCE_HEIGHT = 1.7f;
    private static float PRINCE_WIDTH = PRINCE_HEIGHT*2/3;
    private static float PRINCE_ORIGIN_RELATIVE_X = 0.5f;
    private static float PRINCE_ORIGIN_RELATIVE_Y = 0.05f;
    private static final float FRAME_DURATION = 0.1f;
    private static final float ATTACK_DURATION = 0.2f;

    private float speedValue = 1f;

    private float speed = 0;
    private int direction = -1;
    private State state = State.STILL;
    private float attackDuration = 0;

    private TextureRegion stillTextureRegion;
    private TextureRegion attackTextureRegion;
    private Animation<TextureRegion> walkAnimation;


    Prince(GameScreen gameScreen, float angle) {
        super(gameScreen, angle);

        AssetManager assetManager = gameScreen.getGame().assetManager;

        stillTextureRegion = new TextureRegion((Texture)assetManager.get(PRINCE_STILL_TEXTURE_FILE_NAME));
        attackTextureRegion = new TextureRegion((Texture)assetManager.get(PRINCE_ATTACK_TEXTURE_FILE_NAME));

        TextureAtlas atlas = assetManager.get(PRINCE_WALK_ANIMATION_ATLAS_NAME);
        walkAnimation =  new Animation<TextureRegion>(FRAME_DURATION, atlas.findRegions(PRINCE_WALK_ANIMATION_REGIONS_NAME), Animation.PlayMode.LOOP);
    }

    @Override
    protected void setParent(Group parent) {
        super.setParent(parent);
        if(parent!=null){
            setZIndex(30);
        }
    }

    protected TextureRegion getCurrentTextureRegion(){
        TextureRegion textureRegion;

        switch (state){
            case WALK:
                textureRegion = walkAnimation.getKeyFrame(stateTime, true);
                break;
            case ATTACK:
                textureRegion = attackTextureRegion;
                break;
            case STILL:
            default:
                textureRegion = stillTextureRegion;
        }

        textureRegion.flip((direction>0) != textureRegion.isFlipX(),false);
        return textureRegion;
    }

    @Override
    protected void configureSizeAndOrigin() {
        setSize(PRINCE_WIDTH,PRINCE_HEIGHT);
        setOrigin(PRINCE_WIDTH*PRINCE_ORIGIN_RELATIVE_X, PRINCE_HEIGHT*PRINCE_ORIGIN_RELATIVE_Y);
    }


    @Override
    public void act(float delta){
        super.act(delta);

        if(state == State.ATTACK) {
            attackDuration +=delta;
            if(attackDuration >= ATTACK_DURATION){
                endAttack();
            }
        }

        if(state != State.ATTACK) {

            speed = 0;

            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                attack();
            }
            else {

                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    speed = speedValue;
                    direction = 1;
                } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    speed = -speedValue;
                    direction = -1;
                }

                state = (speed == 0) ? State.STILL : State.WALK;
            }

        }

        currentAngle+= speed *delta;

    }

    public void attack(){
        state = State.ATTACK;
        attackDuration = 0;
    }

    public void endAttack(){
        state = State.STILL;
        attackDuration = 0;
    }

    public enum State{
        STILL,
        WALK,
        ATTACK,
    }

}
