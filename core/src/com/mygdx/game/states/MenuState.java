package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Background;

public class MenuState extends State {

    private final Background bg;
    private final Texture playBtn;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        bg = new Background();
        playBtn = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg.getBackground(), 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(playBtn, (MyGdxGame.WIDTH / 2f) - (playBtn.getWidth() / 2f), MyGdxGame.HEIGHT / 2f);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        playBtn.dispose();
        System.out.println("MenuState disposed");
    }
}