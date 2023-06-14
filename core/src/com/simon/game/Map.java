package com.simon.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

public abstract class Map {
    private final int width;
    private final int height;
    private final TiledMap map;
    private final Array<Entity> entities;
    private final TiledMapRenderer renderer;
    private final Camera cam;

    public Map(int width, int height, TiledMap map, Camera cam, Entity... entities) {
        this.width = width;
        this.height = height;
        this.map = map;
        this.entities = new Array<>(entities);
        this.renderer = new OrthogonalTiledMapRenderer(map);
        this.cam = cam;
    }

    public TiledMap getMap() {
        return map;
    }

    public abstract byte keepInBounds(Player player);
    public abstract void enter();

    public void draw(SpriteBatch batch) {
        renderer.setView((OrthographicCamera) cam);
        renderer.render();

        for(int i = 0; i < entities.size; i++) {
            if (entities.get(i) != null) {
                entities.get(i).draw(batch);
            }
        }
    }
}
