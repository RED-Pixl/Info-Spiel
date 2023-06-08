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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends ApplicationAdapter {

	SpriteBatch spriteBatch;
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera cam;
	ExtendViewport viewport;

	float viewportWidth,viewportHeight;
	float delta;

	int posXDelta, posYDelta;
	int sprintFac;
	Texture figureImg;
	Rectangle figure;
	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();

		viewportWidth = 32 * 30;
		viewportHeight = 32 * 20;
		delta = 0;

		posXDelta = 0;
		posYDelta = 0;
		sprintFac = 1;

		cam = new OrthographicCamera();
		viewport = new ExtendViewport(viewportWidth, viewportHeight, cam);

		TmxMapLoader loader = new TmxMapLoader();
		TiledMap map = loader.load("map.tmx");

		renderer = new OrthogonalTiledMapRenderer(map);
		renderer.setView(cam);

		// Setting up everything regarding the player-character

		figure = new Rectangle(64, 128, 16, 32);
		figureImg = new Texture(Gdx.files.internal("Sprites/char.png"));

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
						sprintFac = 2;
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
						sprintFac = 1;
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

		renderer.setView(cam);
		renderer.render();

		spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
		spriteBatch.begin();

		spriteBatch.draw(figureImg, figure.x, figure.y);

		spriteBatch.end();

		act();
	}

	private void act() {
		delta += Gdx.graphics.getDeltaTime();
		if (delta >= 0.04) {
			delta %= 0.04;
			figure.x += posXDelta * sprintFac;
			figure.y += posYDelta * sprintFac;
			Map.keepInBounds(figure);
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
		renderer.dispose();
		figureImg.dispose();
		spriteBatch.dispose();
	}
}
