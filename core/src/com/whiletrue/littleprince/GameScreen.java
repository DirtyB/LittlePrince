package com.whiletrue.littleprince;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public class GameScreen implements Screen {

    public static final float WORLD_WIDTH = 30f;
    public static final float WORLD_HEIGHT = 30f;

    public static final int MIN_VISIBLE_WORLD_WIDTH = 9;
    public static final int MIN_VISIBLE_WORLD_HEIGHT = 9;

    public static final float PLANET_DRAWING_RADIUS = 3.0f;
    public static final float PLANET_PHYSICAL_RADIUS = 2.4f;

    public static final int MAX_BAOBAB_COUNT = 100;
    public static final float MIN_ATTACK_DIST = 0.5f;

    public static final String MUSIC_FILE_NAME = "music.mp3";

    public final LittlePrinceGame game;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;

    private Star star;
    private BigStar bigStar;
    private Music music;

    private Planet planet;
    private Prince prince;
    private BaobabGenerator baobabGenerator;
    private Set<Baobab> baobabs = new HashSet<Baobab>();

    private boolean isPaused = false;

    public GameScreen(final LittlePrinceGame game) {
        this.game = game;

        loadAssets();

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(MIN_VISIBLE_WORLD_WIDTH,MIN_VISIBLE_WORLD_HEIGHT,WORLD_WIDTH,WORLD_HEIGHT,camera);

        stage = new Stage(viewport,game.batch);
        stage.getCamera().position.set(0,0,0);
        Gdx.input.setInputProcessor(stage);

        star= new Star(PLANET_DRAWING_RADIUS , PLANET_PHYSICAL_RADIUS);
        bigStar= new BigStar(PLANET_DRAWING_RADIUS , PLANET_PHYSICAL_RADIUS);

        stage.addActor(bigStar);
        stage.addActor(star);

        planet = new Planet(game, PLANET_DRAWING_RADIUS, PLANET_PHYSICAL_RADIUS);
        prince = new Prince(this,(float)(Math.PI*0.5));
        baobabGenerator = new BaobabGenerator(this);

        prince.toFront();

        stage.addActor(planet);
        stage.addActor(prince);
        stage.addActor(baobabGenerator);

        music = game.assetManager.get(MUSIC_FILE_NAME);
        music.play();

    }

    private void loadAssets(){
        game.assetManager.load(Planet.PLANET_TEXTURE_FILE_NAME, Texture.class);
        game.assetManager.load(Prince.PRINCE_STILL_TEXTURE_FILE_NAME, Texture.class);
        game.assetManager.load(Prince.PRINCE_WALK_ANIMATION_ATLAS_NAME, TextureAtlas.class);
        game.assetManager.load(Prince.PRINCE_ATTACK_TEXTURE_FILE_NAME, Texture.class);
        game.assetManager.load(Baobab.BAOBAB_ANIMATION_ATLAS_NAME, TextureAtlas.class);
        game.assetManager.load(MUSIC_FILE_NAME, Music.class);

        game.assetManager.finishLoading();
    }

    public void addBaobab(float angle){
        if(baobabs.size()>=MAX_BAOBAB_COUNT) {
            return;
        }
        Baobab baobab = new Baobab(this,angle);
        baobabs.add(baobab);
        stage.addActor(baobab);
    }

    public void removeBaobab(Baobab baobab){
        baobab.remove();
        baobabs.remove(baobab);
    }

    public void handleAttack(float damage){
        Baobab damagedBaobab = null;

        float minDist = WORLD_WIDTH*WORLD_HEIGHT;
        Baobab closestBaobab = null;
        for (Baobab baobab: baobabs){
            float dist = prince.distance(baobab);
            if (dist < minDist){
                closestBaobab = baobab;
                minDist = dist;
            }
        }
        System.out.println("closest Baobab^ " + minDist);
        if(closestBaobab != null && minDist <= MIN_ATTACK_DIST){
            damagedBaobab = closestBaobab;
        }

        if(damagedBaobab != null){
            damagedBaobab.damage(damage);
        }
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

    public Planet getPlanet() {
        return planet;
    }

    public LittlePrinceGame getGame() {
        return game;
    }
}