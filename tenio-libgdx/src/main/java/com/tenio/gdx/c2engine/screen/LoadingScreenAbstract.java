package com.tenio.gdx.c2engine.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Interpolation;
import com.tenio.gdx.c2engine.asset.Asset;
import com.tenio.gdx.c2engine.asset.AssetManageable;
import com.tenio.gdx.c2engine.asset.ResourceManager;

/**
 * 
 * @author mytv
 *
 */
public abstract class LoadingScreenAbstract implements Screen {

	protected float progress;
	// private attribute should be added "_" at
	// last, it's simple mark and a standard of code
	private Screen nextScreen_;
	private Game game_;

	public void createGame(Game game) {
		// create game
		game_ = game;
		// preload assets
		preload();
		// init process
		progress = 0;
	}

	public void setNextScreen(Screen screen) {
		// unload last screen resource if need
		if (nextScreen_ != null && nextScreen_ instanceof AssetManageable) {
			Iterable<Asset> unloadAssets = ((AssetManageable) nextScreen_).unloadAssets();
			if (unloadAssets != null)
				ResourceManager.unloadAssets(unloadAssets);
		}

		// load next screen resource if need
		if (screen instanceof AssetManageable) {
			Iterable<Asset> loadAssets = ((AssetManageable) screen).loadNeedAssets();
			if (loadAssets != null)
				ResourceManager.loadAssets(loadAssets);
		}

		nextScreen_ = screen;
	}

	public void render(float delta) {
		onRender(delta);

		// update process
		progress = Interpolation.linear.apply(progress, ResourceManager.getProgress(), 0.02f);

		// if unload and load is done, automatically set to next screen && progress >
		// 0.99f
		if (ResourceManager.isLoadDone()) {
			game_.setScreen(nextScreen_);
		}
	}

	public abstract void preload();

	// draw background, animation, anything else let code in below. It is
	// abstract because it's up to ideal of design
	public abstract void onRender(float render);
	
}
