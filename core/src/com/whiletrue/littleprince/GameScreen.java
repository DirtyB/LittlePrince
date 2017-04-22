package com.whiletrue.littleprince;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

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
    private Prince prince;

    private boolean isPaused = false;

    public GameScreen(final LittlePrinceGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(MIN_VISIBLE_WORLD_WIDTH,MIN_VISIBLE_WORLD_HEIGHT,WORLD_WIDTH,WORLD_HEIGHT,camera);

        stage = new Stage(viewport,game.batch);
        stage.getCamera().position.set(0,0,0);
        Gdx.input.setInputProcessor(stage);

        planet = new Planet(PLANET_RADIUS);
        prince = new Prince(planet,(float)(Math.PI*0.5));

        //myActor.setTouchable(Touchable.enabled);
        stage.addActor(planet);
        stage.addActor(prince);
    }

    @Override
    public void render(float deltaTime) {
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