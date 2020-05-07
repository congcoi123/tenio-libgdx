/*
The MIT License

Copyright (c) 2016-2020 kong <congcoi123@gmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
package com.tenio.gdx.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.tenio.gdx.c2engine.asset.Asset;
import com.tenio.gdx.c2engine.asset.AssetManageable;
import com.tenio.gdx.c2engine.asset.ResourceManager;
import com.tenio.gdx.c2engine.screen.XScreen;
import com.tenio.gdx.constant.Constants;

/**
 * 
 * @author kong
 *
 */
public class MainScreen extends XScreen implements AssetManageable {

	private Texture __bgTexture;

	@Override
	public void show() {
		configure();
		__bgTexture = ResourceManager.getTexture(Assets.TX_TEST_BACKGROUND);
	}

	/**
	 * Configure your screen
	 */
	private void configure() {
		camera = new OrthographicCamera(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
		camera.position.set(Constants.GAME_WIDTH / 2, Constants.GAME_HEIGHT / 2, 0);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void draw(float delta) {
		batch.begin();
		batch.draw(__bgTexture, 0, 0);
		batch.end();
	}

	@Override
	public void update(float delta) {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		// touch the screen to log in
		if (Gdx.input.justTouched())
			setScreen(new GameScreen());
	}

	@Override
	public Iterable<Asset> loadNeedAssets() {
		Array<Asset> assets = new Array<Asset>();
		assets.add(Assets.TX_TEST_BACKGROUND);
		return assets;
	}

	@Override
	public Iterable<Asset> unloadAssets() {
		return null;
	}

}
