package com.simon.game;

import com.badlogic.gdx.graphics.Texture;

public abstract class Item extends Entity {

    private boolean isInInventory;
    private Texture texture;

    public Item(Texture texture, int x, int y, boolean inventory, String name) {
        super(x, y, name);
        isInInventory = inventory;
        this.texture = texture;
    }

    @Override
    public void interact(Player player) {
        player.addToInventory(this);
        isInInventory = true;
    }

    public abstract boolean use(Player player, Entity interaction);
}
