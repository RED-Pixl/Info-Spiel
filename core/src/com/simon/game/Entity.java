package com.simon.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {

    protected Rectangle rectangle;
    private final String name;
    protected Map map;

    public Entity(int x, int y, String name, Map map, byte width, byte height) {
        rectangle = new Rectangle(x, y, width, height);
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

    public void collision(Player player) {
        while (player.getRectangle().overlaps(rectangle)) {
            player.revertMovement();
        }
    }

    public int getX() {
        return (int) rectangle.x;
    }

    public int getY() {
        return (int) rectangle.y;
    }
}