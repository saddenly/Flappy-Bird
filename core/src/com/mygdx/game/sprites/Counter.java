package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Counter extends Bird {
    private final BitmapFont font;
    private int counter = 0;

    public Counter() {
        super(700, 600);
        font = new BitmapFont(Gdx.files.internal("myFont.fnt"));
    }

    public void render(SpriteBatch batch, Bird bird) {
        font.getData().setScale(2, 2);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(Color.BLACK);
        font.draw(batch, String.valueOf(getCounter()), bird.getPosition().x + 240, 400);

        if (getCounter() > 20 && getCounter() < 24) {
            font.getData().setScale(2.5f, 2.5f);
            font.setColor(Color.RED);
            font.draw(batch, "TRY NOT TO DIE:)", bird.getPosition().x + 25, 100);
        }
    }

    public void setCounter() {
        this.counter--;
    }

    public void update() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}