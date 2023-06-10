package com.simon.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends ApplicationAdapter {

	private SpriteBatch spriteBatch;
	private ExtendViewport viewport;

	private float viewportWidth,viewportHeight;
	private float delta;

	private int posXDelta, posYDelta;
	private float sprintFac;
	private Texture[] figureImg;
	private Rectangle figure;
	private int renderState;

	private Map[] maps;
	private int mapId;
	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();

		viewportWidth = 32 * 30;
		viewportHeight = 32 * 20;
		delta = 0;

		posXDelta = 0;
		posYDelta = 0;
		sprintFac = 0.5f;

		OrthographicCamera cam = new OrthographicCamera();
		viewport = new ExtendViewport(viewportWidth, viewportHeight, cam);

		// Setting up everything regarding the player-character

		figure = new Rectangle(64, 128, 16, 32);
		renderState = 0;

		figureImg = new Texture[2];
		figureImg[0] = new Texture(Gdx.files.internal("Sprites/char.png"));
		figureImg[1] = new Texture(Gdx.files.internal("Sprites/charWalk.png"));

		// Managing maps

		mapId = 0;
		maps = new Map[2];
		maps[0] = MapFactory.create(0, cam);
		maps[1] = MapFactory.create(1, cam);

		// Input Processor for managing the keyboard inputs
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {
				switch (keycode) {
					case Input.Keys.ESCAPE:
						Gdx.app.exit();
						return super.keyDown(keycode);
					case Input.Keys.W:
						posYDelta++;
						return super.keyDown(keycode);
					case Input.Keys.S:
						posYDelta--;
						return super.keyDown(keycode);
					case Input.Keys.D:
						posXDelta++;
						return super.keyDown(keycode);
					case Input.Keys.A:
						posXDelta--;
						return super.keyDown(keycode);
					case Input.Keys.CONTROL_LEFT:
						sprintFac = 1;
						return super.keyDown(keycode);
				}
				return super.keyDown(keycode);
			}

			@Override
			public boolean keyUp(int keycode) {
				switch (keycode) {
					case Input.Keys.W:
						posYDelta--;
						return super.keyUp(keycode);
					case Input.Keys.S:
						posYDelta++;
						return super.keyUp(keycode);
					case Input.Keys.D:
						posXDelta--;
						return super.keyUp(keycode);
					case Input.Keys.A:
						posXDelta++;
						return super.keyUp(keycode);
					case Input.Keys.CONTROL_LEFT:
						sprintFac = 0.5f;
						return super.keyUp(keycode);
				}
				return super.keyUp(keycode);
			}
		});
	}

	@Override
	public void render () {
		ScreenUtils.clear(Color.BLACK);
		viewport.apply();

		maps[mapId].draw(spriteBatch);

		spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
		spriteBatch.begin();

		spriteBatch.draw(figureImg[renderState], figure.x, figure.y);

		spriteBatch.end();

		act();
	}

	private void act() {
		delta += Gdx.graphics.getDeltaTime();
		figure.x += posXDelta * sprintFac;
		figure.y += posYDelta * sprintFac;
		mapId = maps[mapId].keepInBounds(figure);
		if (posXDelta != 0 || posYDelta != 0) {
			renderState = (int) (delta * 5 * sprintFac) % 2;
		} else if (renderState != 0) {
			renderState = 0;
		}
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, false);
		viewport.getCamera().position.set(viewportWidth/2f, viewportHeight/2f, 0);
		viewport.getCamera().update();
	}
	
	@Override
	public void dispose () {
		figureImg[0].dispose();
		figureImg[1].dispose();
		spriteBatch.dispose();
	}
}
