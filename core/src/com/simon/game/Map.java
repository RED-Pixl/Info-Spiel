package com.simon.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Map {
    private int width;
    private int height;
    private TiledMap map;
    private Array<Entity> entities;

    public Map(int width, int height, TiledMap map, Entity... entities) {
        this.width = width;
        this.height = height;
        this.map = map;
        this.entities = new Array<>(entities);
    }

    public TiledMap getMap() {
        return map;
    }

    public static void keepInBounds(Rectangle rect) {
        int lowerBoundX = 0;
        int upperBoundX = (int) (32 * 30 - rect.width);

        int lowerBoundY = 0;
        int upperBoundY = (int) (32 * 20 - rect.height);

        if (rect.x < lowerBoundX) {
            rect.x = lowerBoundX;
        } else if (rect.x > upperBoundX) {
            rect.x = upperBoundX;
        }

        if (rect.y < lowerBoundY) {
            rect.y = lowerBoundY;
        } else if (rect.y > upperBoundY) {
            rect.y = upperBoundY;
        }
    }

    public void draw(SpriteBatch batch) {
        for(int i = 0; i < entities.size; i++) {
            if (entities.get(i) != null) {
                entities.get(i).draw(batch);
            }
        }
    }
}
