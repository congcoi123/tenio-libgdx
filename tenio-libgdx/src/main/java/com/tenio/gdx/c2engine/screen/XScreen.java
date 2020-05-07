package com.tenio.gdx.c2engine.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.tenio.gdx.c2engine.game.XGame;

/**
 * 
 * @author mytv
 *
 */
public abstract class XScreen implements Screen {
	protected static Camera camera;
	protected static SpriteBatch batch;
	protected static XGame coreGame;
	protected static InputMultiplexer inputMultiplexer = new InputMultiplexer();
	protected static Stage stage;

	public static void setCoreGame(XGame myGame) {
		XScreen.coreGame = myGame;
	}

	public void setScreen(Screen screen) {
		coreGame.setLoadingScreen(screen);
	}

	@Override
	public void render(float delta) {
		update(delta);
		clear();
		draw(delta);
	}

	private void clear() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearDepthf(1);
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
	}

	public abstract void draw(float delta);

	public abstract void update(float delta);
	
}
