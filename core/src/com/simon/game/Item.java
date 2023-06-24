package com.simon.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Item extends Entity {

    private final Texture texture;

    public Item(Texture texture, int x, int y, Map map) {
        super(x, y, map, (byte) 32, (byte) 32, false);
        this.texture = texture;
    }

    public Item(Texture texture, int x, int y) {
        super(x, y, null, (byte) 32, (byte) 32, false);
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
        batch.draw(texture, rectangle.x, rectangle.y);
        batch.end();
    }

    public void draw(SpriteBatch batch, int x, int y) {
        batch.begin();
        batch.draw(texture, x, y);
        batch.end();
    }

    public void dispose() {
        texture.dispose();
    }
}
