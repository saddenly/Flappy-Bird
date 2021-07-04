package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {
    private final BgPicture[] backs;
    private Texture background = new Texture("back.png");

    public Background() {
        backs = new BgPicture[4];
        backs[0] = new BgPicture(new Vector2(0, 0));
        backs[1] = new BgPicture(new Vector2(800, 0));
        backs[2] = new BgPicture(new Vector2(0, 0));
        backs[3] = new BgPicture(new Vector2(0, 0));
    }

    public Texture getBackground() {
        return background;
    }

    public void changeLevel() {
        background = new Texture("back2.png");
        backs[2] = new BgPicture(new Vector2(backs[0].pos.x + 1600, 0));
        backs[3] = new BgPicture(new Vector2(backs[1].pos.x + 1600, 0));
    }

    public void render(SpriteBatch batch) {
        for (BgPicture back : backs) {
                batch.draw(back.tx, back.pos.x, back.pos.y);
        }
    }

    public void bgUpdate(Bird bird) {
        if (backs[1].pos.x + 150 < bird.getPosition().x) {
            backs[0].pos.x = backs[1].pos.x + 800;
        }

        if (bird.getPosition().x > backs[1].pos.x && backs[0].pos.x + 150 < bird.getPosition().x) {
            backs[1].pos.x = backs[0].pos.x + 800;
        }

        if (backs[3].pos.x + 150 < bird.getPosition().x) {
            backs[2].pos.x = backs[3].pos.x + 800;
        }

        if (bird.getPosition().x > backs[3].pos.x && backs[2].pos.x + 150 < bird.getPosition().x) {
            backs[3].pos.x = backs[2].pos.x + 800;
        }
    }

    public void dispose() {
        background.dispose();
    }


    class BgPicture {
        private final Texture tx;
        private final Vector2 pos;

        public BgPicture(Vector2 pos) {
            tx = background;
            this.pos = pos;
        }
    }
}