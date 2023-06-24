package com.simon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Door extends Entity{

    private final int keyCode;
    private Texture texture;

    public Door(int x, int y, Map map, int key, boolean open) {
        super(x, y, map, (byte) 32, (byte) 32, true);
        keyCode = key;
        if (!open) {
            texture = new Texture(Gdx.files.internal("Sprites/doorClosed.png"));
        } else {
            texture = new Texture(Gdx.files.internal("Sprites/doorOpen.png"));
        }
        collide = !open;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.begin();
        batch.draw(texture, rectangle.x, rectangle.y);
        batch.end();
    }

    @Override
    public void interact(Player player) {

    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    public void open(int code) {
        if (code == keyCode) {
            collide = false;
            texture.dispose();
            texture = new Texture(Gdx.files.internal("Sprites/doorOpen.png"));
        }
    }
}
