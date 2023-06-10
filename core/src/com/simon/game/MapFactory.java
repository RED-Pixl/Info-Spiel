package com.simon.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MapFactory{
    public static Map create (int mapId, Camera cam) {
        switch (mapId) {
            case 0:
                return new Map(320, 192, new TmxMapLoader().load("00.tmx"), cam) {
                    private final int lowerBoundX = 320;
                    private final int upperBoundX = 624;
                    private final int lowerBoundY = 224;
                    private final int upperBoundY = 384;

                    @Override
                    public byte keepInBounds(Rectangle rect) {

                        checkPos(rect, lowerBoundX, upperBoundX, lowerBoundY, upperBoundY);

                        if (rect.contains(new Vector2(480, 416))) {
                            rect.x = 15 * 32 - rect.width / 2;
                            rect.y = 5 * 32 + 1;
                            return 1;
                        }

                        return 0;
                    }
                };
            case 1:
                return new Map(256, 192, new TmxMapLoader().load("01.tmx"), cam) {
                    private final int lowerBoundX = 352;
                    private final int upperBoundX = 592;
                    private final int lowerBoundY = 160;
                    private final int upperBoundY = 448;

                    @Override
                    public byte keepInBounds(Rectangle rect) {
                        checkPos(rect, lowerBoundX, upperBoundX, lowerBoundY, upperBoundY);

                        if (rect.contains(new Vector2(480, 160))) {
                            rect.x = 15 * 32 - rect.width / 2;
                            rect.y = 13 * 32 - rect.height - 1;
                            return 0;
                        }

                        return 1;
                    }
                };
        }
        return null;
    }

    private static void checkPos(Rectangle rect, int lowerBoundX, int upperBoundX, int lowerBoundY, int upperBoundY) {
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
}
