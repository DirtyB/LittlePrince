package com.whiletrue.littleprince;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by boris_0mrym3f on 23.04.2017.
 */
public class BaobabGenerator extends Actor{

    private static final float BAOBAB_GENERATION_FREQUENCY = 1f;
    private static final float BAOBAB_GENERATION_PROBABILITY = 0.3f;

    private GameScreen screen;
    private float time = 0;

    BaobabGenerator(GameScreen screen){
        this.screen = screen;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        time += delta;
        if(time > BAOBAB_GENERATION_FREQUENCY){
            time = time%BAOBAB_GENERATION_FREQUENCY;
            double chance = Math.random();
            if(chance<=BAOBAB_GENERATION_PROBABILITY){
                double angle = Math.random()*Math.PI*2;
                screen.addBaobab((float)angle);
            }
        }
    }
}
