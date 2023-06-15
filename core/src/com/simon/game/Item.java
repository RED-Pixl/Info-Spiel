package com.simon.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Item extends Entity {

    private final Texture texture;

    public Item(Texture texture, int x, int y, String name, Map map) {
        super(x, y, name, map);
        this.texture = texture;
    }

    public Item(Texture texture, int x, int y, String name) {
        super(x, y, name, null);
        this.texture = texture;
    }

    @Override
    public void interact(Player player) {
        player.addToInventory(this);
        removeFromMap();
        map = null;
    }

    public abstract boolean use(Player player, Entity interaction);

    public void draw(SpriteBatch batch) {
        batch.begin();
        batch.draw(texture, x - 16, y - 16);
        batch.end();
    }

    public void draw(SpriteBatch batch, int x, int y) {
        batch.begin();
        batch.draw(texture, x - 16, y - 16);
        batch.end();
    }
}
