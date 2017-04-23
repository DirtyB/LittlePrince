package com.whiletrue.littleprince;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public class GameScreen implements Screen {

    public static final float WORLD_WIDTH = 10f;
    public static final float WORLD_HEIGHT = 10f;

    public static final int MIN_VISIBLE_WORLD_WIDTH = 5;
    public static final int MIN_VISIBLE_WORLD_HEIGHT = 5;

    public static final float PLANET_DRAWING_RADIUS = 2f;
    public static final float PLANET_PHYSICAL_RADIUS = 1.6f;

    public final LittlePrinceGame game;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;

    private Planet planet;
    private Prince prince;

    private boolean isPaused = false;

    public GameScreen(final LittlePrinceGame game) {
        this.game = game;

        loadAssets();

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(MIN_VISIBLE_WORLD_WIDTH,MIN_VISIBLE_WORLD_HEIGHT,WORLD_WIDTH,WORLD_HEIGHT,camera);

        stage = new Stage(viewport,game.batch);
        stage.getCamera().position.set(0,0,0);
        Gdx.input.setInputProcessor(stage);

        planet = new Planet(game, PLANET_DRAWING_RADIUS, PLANET_PHYSICAL_RADIUS);
        prince = new Prince(this,(float)(Math.PI*0.5));

        //myActor.setTouchable(Touchable.enabled);
        stage.addActor(planet);
        stage.addActor(prince);
    }

    private void loadAssets(){
        game.assetManager.load(Planet.PLANET_TEXTURE_FILE_NAME, Texture.class);
        game.assetManager.load(Prince.PRINCE_STILL_TEXTURE_FILE_NAME, Texture.class);
        game.assetManager.load(Prince.PRINCE_WALK_ANIMATION_ATLAS_NAME, TextureAtlas.class);

        game.assetManager.finishLoading();
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

    public Planet getPlanet() {
        return planet;
    }

    public LittlePrinceGame getGame() {
        return game;
    }
}