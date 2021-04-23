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

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;
import com.tenio.gdx.c2engine.asset.Asset;
import com.tenio.gdx.c2engine.asset.AssetManageable;
import com.tenio.gdx.c2engine.asset.FramesGenerator;
import com.tenio.gdx.c2engine.screen.XScreen;
import com.tenio.gdx.c2engine.sprite.SpriteAnimation;
import com.tenio.gdx.network.IDatagramListener;
import com.tenio.gdx.network.ISocketListener;
import com.tenio.gdx.network.TCP;
import com.tenio.gdx.network.UDP;
import com.tenio.gdx.network.entity.TArray;
import com.tenio.gdx.network.entity.TObject;

/**
 * 
 * @author kong
 *
 */
public class GameScreen extends XScreen implements AssetManageable, ISocketListener, IDatagramListener {
	/**
	 * @see TCP
	 */
	private TCP __tcp;
	/**
	 * @see UDP
	 */
	private UDP __udp;

	/**
	 * An array of all entities
	 */
	private List<SpriteAnimation> __spriteAnimations = new ArrayList<SpriteAnimation>();

	public GameScreen() {
		// listen to ports
		__tcp = new TCP(8032);
		__tcp.receive(this);

		__udp = new UDP(8031);
		__udp.receive(this);

		// send a login request
		TObject message = new TObject();
		message.put("u", "kong");
		__tcp.send(message);
		System.out.println("Login Request -> " + message);

	}

	// networking
	@Override
	public void onReceivedTCP(TObject message) {
		System.err.println("[RECV FROM SERVER TCP] -> " + message);

		switch ((String) message.get("c")) {
		case "udp": {
			// now you are allowed to send a request for UDP connection
			TObject request = new TObject();
			request.put("u", "kong");
			__udp.send(request);
			System.out.println("Request UDP Connection -> " + request);
		}
			break;

		case "udp-done": {
			// UDP connection is established successful, now you're in conversation
			System.out.println("Conversation ...");
		}
			break;

		}
	}

	// networking
	@Override
	public void onReceivedUDP(TObject message) {
		// System.err.println("Received UDP message -> " + message);
		
		TArray transform = message.getTArray("p");
		int index = transform.getInt(0);
		int positionX = transform.getInt(1);
		int positionY = transform.getInt(2);

		// a naive synchronous for testing ...
		__spriteAnimations.get(index).setCenterXY(positionX, 500 - positionY);

	}

	// rendering
	@Override
	public void show() {
		__initSpriteAnimation();
	}

	private void __initSpriteAnimation() {
		for (int i = 0; i < 100; i++) {
			SpriteAnimation spriteAnimationSimple = new SpriteAnimation(
					FramesGenerator.getFramesFromTexture(Assets.TX_TEST_DRAGON, 1, 6));
			if (i == 99) {
				spriteAnimationSimple.resize(0.5f);
				spriteAnimationSimple.start(0.1f, Animation.LOOP);
			} else {
				spriteAnimationSimple.resize(0.1f);
			}
			__spriteAnimations.add(spriteAnimationSimple);
		}
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
	public Iterable<Asset> loadNeedAssets() {
		Array<Asset> assets = new Array<Asset>();
		assets.add(Assets.TX_TEST_BACKGROUND);
		assets.addAll(Assets.PACK_TEST_DRAGON);
		return assets;
	}

	@Override
	public Iterable<Asset> unloadAssets() {
		return null;
	}

	@Override
	public void draw(float delta) {
		// bring all to the screen
		batch.begin();
		__spriteAnimations.forEach(spriteAnimation -> {
			spriteAnimation.draw(batch);
		});
		batch.end();
	}

	@Override
	public void update(float delta) {
		// just simple updating new position for all entities
		__spriteAnimations.forEach(spriteAnimation -> {
			spriteAnimation.update(delta);
		});
	}

}
