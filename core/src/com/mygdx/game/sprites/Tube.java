package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int FLUCTUATIONS = 200;
    public static final int TUBE_WIDTH = 50;

    public static int TUBE_GAP = 200;
    public static int LOWEST_OPENING = 50;

    private final Texture topTube, botTube;
    private final Vector2 posTopTube, posBotTube;
    private final Random random;
    private final Rectangle boundaryTop, boundaryBot;

    public Tube(float x) {
        topTube = new Texture("wall.png");
        botTube = new Texture("wall.png");
        random = new Random();

        posTopTube = new Vector2(x, random.nextInt(FLUCTUATIONS) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - botTube.getHeight());
        boundaryBot = new Rectangle(posBotTube.x, posBotTube.y, botTube.getWidth(), botTube.getHeight());
        boundaryTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
    }

    public static void resetDifficulty() {
        TUBE_GAP = 200;
        LOWEST_OPENING = 50;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBotTube() {
        return botTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public void reposition(float x) {
        posTopTube.set(x, random.nextInt(FLUCTUATIONS) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - botTube.getHeight());
        boundaryBot.setPosition(posBotTube.x, posBotTube.y);
        boundaryTop.setPosition(posTopTube.x, posTopTube.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundaryTop) || player.overlaps(boundaryBot);
    }

    public void dispose() {
        topTube.dispose();
        botTube.dispose();
    }
}