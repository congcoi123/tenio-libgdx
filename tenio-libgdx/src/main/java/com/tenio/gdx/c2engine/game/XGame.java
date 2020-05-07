package com.tenio.gdx.c2engine.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.tenio.gdx.c2engine.screen.LoadingScreenAbstract;
import com.tenio.gdx.c2engine.screen.XScreen;

/**
 * A wrapper class for game handler.
 * 
 * @author mytv
 *
 */
public abstract class XGame extends Game {
	private LoadingScreenAbstract loadingScreenAbstract;
	private static int WIDTH = 0, HEIGHT = 0;

	@Override
	public void create() {
		configure();
		onCreate();
		setStartScreen();
	}

	private void setStartScreen() {
		setLoadingScreen(createStartedScreen());
	}

	private void configure() {
		Gdx.input.setCatchBackKey(true);
		loadingScreenAbstract = createLoadingScreen();
		loadingScreenAbstract.createGame(this);
		XScreen.setCoreGame(this);
	}

	public abstract LoadingScreenAbstract createLoadingScreen();

	public abstract Screen createStartedScreen();

	public abstract void onCreate();

	public void setLoadingScreen(Screen screen) {
		loadingScreenAbstract.setNextScreen(screen);
		setScreen(loadingScreenAbstract);
	}

	public void setResolution(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}
	
}
