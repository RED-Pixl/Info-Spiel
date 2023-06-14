package com.simon.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Game extends ApplicationAdapter {

	private SpriteBatch spriteBatch;
	private ExtendViewport viewport;
	private OrthographicCamera cam;
	private float viewportWidth,viewportHeight;
	Player player;
	private Map[] maps;
	private byte mapId;
	
	@Override
	public void create () {
		viewportWidth = 32 * 30;
		viewportHeight = 32 * 20;

		spriteBatch = new SpriteBatch();
		cam = new OrthographicCamera();
		viewport = new ExtendViewport(viewportWidth, viewportHeight, cam);

		// Setting up everything regarding the player-character
		player = new Player();

		// Managing maps
		mapId = 0;
		maps = new Map[2];
		maps[0] = MapFactory.create(0, cam);
		maps[0].enter();

		// Input Processor for managing the keyboard inputs
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {
				switch (keycode) {
					case Input.Keys.ESCAPE:
						Gdx.app.exit();
						return super.keyDown(keycode);
					case Input.Keys.W:
						player.posYDelta++;
						return super.keyDown(keycode);
					case Input.Keys.S:
						player.posYDelta--;
						return super.keyDown(keycode);
					case Input.Keys.D:
						player.posXDelta++;
						return super.keyDown(keycode);
					case Input.Keys.A:
						player.posXDelta--;
						return super.keyDown(keycode);
					case Input.Keys.CONTROL_LEFT:
						player.startSprint();
						return super.keyDown(keycode);
				}
				return super.keyDown(keycode);
			}

			@Override
			public boolean keyUp(int keycode) {
				switch (keycode) {
					case Input.Keys.W:
						player.posYDelta--;
						return super.keyUp(keycode);
					case Input.Keys.S:
						player.posYDelta++;
						return super.keyUp(keycode);
					case Input.Keys.D:
						player.posXDelta--;
						return super.keyUp(keycode);
					case Input.Keys.A:
						player.posXDelta++;
						return super.keyUp(keycode);
					case Input.Keys.CONTROL_LEFT:
						player.endSprint();
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
		player.draw(spriteBatch);
		act();
	}

	private void act() {
		player.act();

		// updating the map
		if (mapId != (mapId = maps[mapId].keepInBounds(player))) {
			if (maps[mapId] == null) {
				maps[mapId] = MapFactory.create(mapId, cam);
			}
			maps[mapId].enter();
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
		player.dispose();
		spriteBatch.dispose();
	}
}
