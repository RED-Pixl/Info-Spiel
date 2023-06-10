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
                    @Override
                    public int keepInBounds(Rectangle rect) {
                        int lowerBoundX = 320;
                        int upperBoundX = (int) (640 - rect.width);

                        int lowerBoundY = 224;
                        int upperBoundY = (int) (416 - rect.height);

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

                        if (rect.contains(new Vector2(15 * 32, 12 * 32))) {
                            rect.x = 15 * 32;
                            rect.y = 6 * 32 - rect.width / 2;
                            System.out.println("hi");
                            return 1;
                        }

                        return 0;
                    }
                };
            case 1:
                return new Map(256, 192, new TmxMapLoader().load("01.tmx"), cam) {
                    @Override
                    public int keepInBounds(Rectangle rect) {
                        int lowerBoundX = 352;
                        int upperBoundX = (int) (608 - rect.width);

                        int lowerBoundY = 160;
                        int upperBoundY = (int) (480 - rect.height);

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

                        if (rect.contains(new Vector2(15 * 32, 5 * 32))) {
                            rect.x = 15 * 32;
                            rect.y = 5 * 32;
                            return 0;
                        }

                        return 1;
                    }
                };
        }
        return null;
    }
}
