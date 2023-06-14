package com.simon.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {

    public int x;
    public int y;
    private String name;

    public Entity(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void draw(SpriteBatch batch) {

    }

    public abstract void interact(Player player);
}