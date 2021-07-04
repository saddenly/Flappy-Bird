package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Background;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Counter;
import com.mygdx.game.sprites.Tube;

public class PlayState extends State {
    public static final int TUBE_SPACING = 220;
    public static final int TUBE_COUNT = 4;

    private final Counter counter;
    private final Bird bird;
    private final Background bg;

    private final Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(100, 380);
        bg = new Background();
        counter = new Counter();
        camera.setToOrtho(false, MyGdxGame.WIDTH / 2f, MyGdxGame.HEIGHT / 2f);

        tubes = new Array<>();

        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH) + 500));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        camera.position.x = bird.getPosition().x + 260;
        boolean firstTubePassed = false;

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            float birdPosition = camera.position.x - (camera.viewportWidth / 2);
            float tubesPosition = tube.getPosTopTube().x
                    + tube.getBotTube().getWidth() * 2;

            if (birdPosition > tubesPosition + 220) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                if (firstTubePassed) {
                    counter.update();
                }
            }

            if ((birdPosition > tubesPosition && birdPosition <= tubesPosition + 4.2f) && !firstTubePassed) {
                counter.update();
                firstTubePassed = true;
            }

            if (tube.collides(bird.getBounds())) {
                Tube.resetDifficulty();
                gsm.set(new RestartState(gsm));
            }
        }

        switch (counter.getCounter()) {
            case 10:
                Tube.TUBE_GAP = 170;
                Tube.LOWEST_OPENING = 80;
                break;
            case 17:
                bg.changeLevel();
                Tube.TUBE_GAP = 145;
                Tube.LOWEST_OPENING = 100;
                break;
            case 37:
                Tube.TUBE_GAP = 120;
                break;
        }

        if (bird.getPosition().y > 500 | bird.getPosition().y < 0) {
            gsm.set(new RestartState(gsm));
            Tube.resetDifficulty();
        }
        camera.update();
        bg.bgUpdate(bird);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        bg.render(sb);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosBotTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBotTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        counter.render(sb, bird);
        sb.end();
    }

    @Override
    public void dispose() {
        bird.dispose();
        bg.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("PlayState Disposed");
    }
}