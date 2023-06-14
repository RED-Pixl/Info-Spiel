package com.simon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Player {
    private final Rectangle rectangle;
    public byte posXDelta, posYDelta;
    private byte sprintFac;
    private final Texture[] playerTexture;
    private byte renderState;
    private final Array<Item> inventory;

    public Player() {
        rectangle = new Rectangle(472, 304, 16, 32);
        posXDelta = 0;
        posYDelta = 0;

        sprintFac = 35;
        renderState = 0;

        playerTexture = new Texture[2];
        playerTexture[0] = new Texture(Gdx.files.internal("Sprites/char.png"));
        playerTexture[1] = new Texture(Gdx.files.internal("Sprites/charWalk.png"));

        inventory = new Array<>();
    }

    public void startSprint() {
        sprintFac = 60;
    }

    public void endSprint() {
        sprintFac = 35;
    }

    public byte getSprintFac() {
        return sprintFac;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        batch.draw(playerTexture[(renderState / 10) % playerTexture.length], rectangle.x, rectangle.y);
        batch.end();
    }

    public void act() {
        if (renderState != -1) {
            if (posXDelta != 0 || posYDelta != 0) {
                renderState++;
                float frameTime = Gdx.graphics.getDeltaTime();
                rectangle.x += posXDelta * getSprintFac() * frameTime;
                rectangle.y += posYDelta * getSprintFac() * frameTime;
            }
        } else {
            // render the inventory
        }
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public void dispose() {
        for (Texture texture : playerTexture) {
            texture.dispose();
        }
    }
}
