package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Background;

public class RestartState extends State {

    private final Background bg;
    private final Texture restart;


    public RestartState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, MyGdxGame.WIDTH / 2f, MyGdxGame.HEIGHT / 2f);
        bg = new Background();
        restart = new Texture("RestartBtn.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg.getBackground(), 0, 0);
        sb.draw(restart, 160, 180);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        restart.dispose();
        System.out.println("Restart State disposed");
    }
}