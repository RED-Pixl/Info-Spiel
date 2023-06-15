package com.simon.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

public class MapFactory{
    public static Map create (int mapId, Camera cam) {
        switch (mapId) {
            case 0:
                return new Map(320, 192, new TmxMapLoader().load("00.tmx"), cam) {

                    @Override
                    public byte keepInBounds(Player player) {
                        checkPos(player, 320, 624, 224, 384);

                        if (player.getRectangle().contains(new Vector2(480, 416)) && player.posYDelta == 1) {
                            player.getRectangle().x = 15 * 32 - player.getRectangle().width / 2;
                            player.getRectangle().y = 5 * 32 + 1;
                            return 1;
                        }

                        return 0;
                    }

                    @Override
                    public void enter() {

                    }
                };
            case 1:
                return new Map(256, 192, new TmxMapLoader().load("01.tmx"), cam, new Item(new Texture("Sprites/item.png"), 480, 256, "Test-Item") {
                    @Override
                    public boolean use(Player player, Entity interaction) {
                        return false;
                    }
                }) {

                    @Override
                    public byte keepInBounds(Player player) {
                        checkPos(player, 352, 592, 160, 448);

                        if (player.getRectangle().contains(new Vector2(480, 160)) && player.posYDelta == -1) {
                            player.getRectangle().x = 15 * 32 - player.getRectangle().width / 2;
                            player.getRectangle().y = 13 * 32 - player.getRectangle().height - 1;
                            return 0;
                        }

                        return 1;
                    }

                    @Override
                    public void enter() {

                    }
                };
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
