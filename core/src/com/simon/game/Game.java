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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends ApplicationAdapter {

	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera cam;
	ExtendViewport viewport;

	float viewportWidth,viewportHeight;
	
	@Override
	public void create () {
		viewportWidth = 32 * 30;
		viewportHeight = 32 * 20;

		cam = new OrthographicCamera();
		viewport = new ExtendViewport(viewportWidth, viewportHeight, cam);

		TmxMapLoader loader = new TmxMapLoader();
		TiledMap map = loader.load("map.tmx");

		renderer = new OrthogonalTiledMapRenderer(map);
		renderer.setView(cam);

		// Input Processor for managing the keyboard inputs
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {
				switch (keycode) {
					case Input.Keys.ESCAPE:
						Gdx.app.exit();
				}
				return super.keyDown(keycode);
			}

			@Override
			public boolean keyUp(int keycode) {
				switch (keycode) {

				}
				return super.keyUp(keycode);
			}
		});
	}

	@Override
	public void render () {
		ScreenUtils.clear(Color.BLACK);

		renderer.setView(cam);
		renderer.render();
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
	}
}
