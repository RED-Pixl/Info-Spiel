package com.simon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

public class MapFactory{
    public static Map create (int mapId, Camera cam, Map[] maps) {
        switch (mapId) {
            case 0:
                // SMV-Zimmer
                return new Map(new TmxMapLoader().load("00.tmx"), cam,
                        new Door(0, 0, maps[0], 0, false)) {
                    @Override
                    public byte keepInBounds(Player player) {
                        if (player.getX() < 11 * 32) {
                            player.getRectangle().x = 11 * 23;
                        } else if (player.getX() + player.getRectangle().width > 19 * 32) {
                            player.getRectangle().x = 19 * 32 - player.getRectangle().width;
                        }
                        if (player.getY() < 8 * 32) {
                            player.getRectangle().y = 8 * 32;
                        } else if (player.getY() + player.getRectangle().height > 12 * 32 + 16) {
                            player.getRectangle().y = 12 * 32 + 16 - player.getRectangle().height;
                        }
                        return 0;
                    }

                    @Override
                    public void enter() {

                    }
                };
            case 1:

        }
        return null;
    }

    private static void checkPos(Player player, int lowerBoundX, int upperBoundX, int lowerBoundY, int upperBoundY) {
        if (player.getRectangle().x < lowerBoundX) {
            player.getRectangle().x = lowerBoundX;
        } else if (player.getRectangle().x > upperBoundX) {
            player.getRectangle().x = upperBoundX;
        }

        if (player.getRectangle().y < lowerBoundY) {
            player.getRectangle().y = lowerBoundY;
        } else if (player.getRectangle().y > upperBoundY) {
            player.getRectangle().y = upperBoundY;
        }
    }
}
