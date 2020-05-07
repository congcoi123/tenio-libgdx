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
package com.tenio.gdx.c2engine.asset;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * This class is used to divide a big frame images into smaller parts.
 * 
 * @author kong
 *
 */
public class FramesGenerator {

	/**
	 * Get frames from image (from asset) that contain a list of frame, arranged in
	 * line
	 */
	public static TextureRegion[] getFramesFromTexture(Asset asset, int rows, int cols) {
		Texture texture = ResourceManager.get(asset, Texture.class);
		return frameSplit(texture, rows, cols);
	}

	/**
	 * Split TextureRegion @see {@link TextureRegion} for Animation @see
	 * {@link Animation}
	 * 
	 * @param textureRegion
	 * @param rows
	 * @param cols
	 * @return Returns an array of TextureRegion @see {@link TextureRegion} objects
	 */
	private static TextureRegion[] frameSplit(Texture texture, int rows, int cols) {
		TextureRegion[] result;
		int tileWidth = texture.getWidth() / cols;
		int tileHeight = texture.getHeight() / rows;

		TextureRegion[][] temp = new TextureRegion(texture).split(tileWidth, tileHeight);
		result = new TextureRegion[cols * rows];

		int index = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[index++] = temp[i][j];
			}
		}

		return result;
	}

	/**
	 * Get the key frames in range
	 * 
	 * @param textureAtlas
	 * @param keyFrame
	 * @param startFrame
	 * @param endFrame
	 * @return Returns an array of TextureRegion @see {@link TextureRegion} objects
	 */
	public static TextureRegion[] getKeyFrames(TextureAtlas textureAtlas, String keyFrame, int startFrame,
			int endFrame) {
		int size = endFrame - startFrame + 1;

		TextureRegion[] trRegions = new TextureRegion[size];

		for (int i = 0; i < size; i++) {
			trRegions[i] = textureAtlas.findRegion(keyFrame + (i + startFrame));
		}

		return trRegions;
	}

	/**
	 * Get the key frames by desired array of key frames
	 * 
	 * @param keyFrames
	 * @return Returns an array of TextureRegion @see {@link TextureRegion} objects 
	 */
	public static TextureRegion[] getKeyFrames(TextureRegion[]... keyFrames) {
		int size = 0;
		for (int i = 0; i < keyFrames.length; i++) {
			size += keyFrames[i].length;
		}

		int k = 0;
		TextureRegion[] trRegions = new TextureRegion[size];

		for (int i = 0; i < keyFrames.length; i++) {
			for (int j = 0; j < keyFrames[i].length; j++) {
				trRegions[k] = keyFrames[i][j];
				k++;
			}
		}

		return trRegions;
	}

}
