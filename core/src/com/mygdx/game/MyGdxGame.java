package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class MyGdxGame extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	public static final String TITLE = "Flappy bird";

	private GameStateManager gsm;
	private SpriteBatch batch;

	private Music soundtrack;

	@Override
	public void create() {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		ScreenUtils.clear(1, 0, 0, 1);
		soundtrack = Gdx.audio.newMusic(Gdx.files.internal("soundtrack.mp3"));
		soundtrack.setLooping(true);
		soundtrack.setVolume(1);
		soundtrack.play();
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose() {
		batch.dispose();
		soundtrack.dispose();
	}
}