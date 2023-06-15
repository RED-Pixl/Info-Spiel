package com.simon.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {

    public int x;
    public int y;
    private final String name;

    protected Map map;

    public Entity(int x, int y, String name, Map map) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public abstract void draw(SpriteBatch batch);

    public abstract void interact(Player player);

    public void removeFromMap() {
        map.removeEntity(this);
    }
}