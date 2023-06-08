package com.simon.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
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

    public void keepInBounds() {

    }

    public void draw(SpriteBatch batch) {
        for(int i = 0; i < entities.size; i++) {
            if (entities.get(i) != null) {
                entities.get(i).draw(batch);
            }
        }
    }
}
