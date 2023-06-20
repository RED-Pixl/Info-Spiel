package com.simon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Objects;

public class Door extends Entity{

    private String keyCode;
    private Texture texture;
    private byte facing;

    public Door(int x, int y, Map map, byte facing, String key) {
        super(x - 16, y - 16, map, (byte) 32, (byte) 32, true);
        keyCode = key;
        this.facing = facing;
        switch (facing) {
            case 0:
                texture = new Texture(Gdx.files.internal("Sprites/doorNorthClosed.png"));
            case 1:
                texture = new Texture(Gdx.files.internal("Sprites/doorEastClosed.png"));
            case 2:
                texture = new Texture(Gdx.files.internal("Sprites/doorSouthClosed.png"));
            case 3:
                texture = new Texture(Gdx.files.internal("Sprites/doorWestClosed.png"));
            default:
                throw new IllegalArgumentException();
        }
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

    public boolean open(String code) {
        if (Objects.equals(code, keyCode)) {
            collide = false;
            texture.dispose();
            switch (facing) {
                case 0:
                    texture = new Texture(Gdx.files.internal("Sprites/doorNorthOpen.png"));
                case 1:
                    texture = new Texture(Gdx.files.internal("Sprites/doorEastOpen.png"));
                case 2:
                    texture = new Texture(Gdx.files.internal("Sprites/doorSouthOpen.png"));
                case 3:
                    texture = new Texture(Gdx.files.internal("Sprites/doorWestOpen.png"));
            }
            return true;
        }
        return false;
    }
}
