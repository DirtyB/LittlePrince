package com.whiletrue.littleprince;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public class GameScreen implements Screen {

    public static final float WORLD_WIDTH = 10f;
    public static final float WORLD_HEIGHT = 10f;

    public static final int MIN_VISIBLE_WORLD_WIDTH = 5;
    public static final int MIN_VISIBLE_WORLD_HEIGHT = 5;

    public static final float PLANET_RADIUS = 2f;

    public final LittlePrinceGame game;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;

    private Planet planet;

    private boolean isPaused = false;

    public GameScreen(final LittlePrinceGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(MIN_VISIBLE_WORLD_WIDTH,MIN_VISIBLE_WORLD_HEIGHT,WORLD_WIDTH,WORLD_HEIGHT,camera);

        stage = new Stage(viewport,game.batch);
        stage.getCamera().position.set(0,0,0);
        Gdx.input.setInputProcessor(stage);

        planet = new Planet(PLANET_RADIUS);
        //myActor.setTouchable(Touchable.enabled);
        stage.addActor(planet);
    }

    @Override
    public void render(float deltaTime) {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
        isPaused = true;
    }

    @Override
    public void resume() {
        isPaused = false;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }


}