package com.whiletrue.littleprince;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public class GameScreen implements Screen {

    public static final float WORLD_WIDTH = 30f;
    public static final float WORLD_HEIGHT = 30f;

    public static final int MIN_VISIBLE_WORLD_WIDTH = 9;
    public static final int MIN_VISIBLE_WORLD_HEIGHT = 9;

    public static final float PLANET_DRAWING_RADIUS = 2f;
    public static final float PLANET_PHYSICAL_RADIUS = 1.6f;


    public final LittlePrinceGame game;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;
    private Music music;
    private AssetManager assetManager;

    private Planet planet;
    private Prince prince;
    private Star star;
    private Sprite mapSprite;
    private BigStar bigStar;



    private boolean isPaused = false;

    public GameScreen(final LittlePrinceGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(MIN_VISIBLE_WORLD_WIDTH,MIN_VISIBLE_WORLD_HEIGHT,WORLD_WIDTH,WORLD_HEIGHT,camera);

        stage = new Stage(viewport,game.batch);
        stage.getCamera().position.set(0,0,0);
        Gdx.input.setInputProcessor(stage);

        planet = new Planet(PLANET_DRAWING_RADIUS, PLANET_PHYSICAL_RADIUS);
        prince = new Prince(planet,(float)(Math.PI*0.5));
        star= new Star(PLANET_DRAWING_RADIUS , PLANET_PHYSICAL_RADIUS);
        bigStar= new BigStar(PLANET_DRAWING_RADIUS , PLANET_PHYSICAL_RADIUS);


        assetManager = new AssetManager();
        assetManager.load("music.mp3", Music.class);
        assetManager.finishLoading();

        music = assetManager.get("music.mp3");
        music.play();

        stage.addActor(star);
        stage.addActor(planet);
        stage.addActor(prince);
        stage.addActor(bigStar);
       // stage.addActor(star);

    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0.14f, 0.16f, 0.34f, 1);
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
        music.stop();
        stage.dispose();
    }


}