package com.tenio.gdx.c2engine.sprite;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.NumberUtils;

/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. vn.sunnet.daovangdoi.utils
 * 
 * import static com.badlogic.gdx.graphics.g2d.Animation.LOOP; import static
 * com.badlogic.gdx.graphics.g2d.Animation.LOOP_PINGPONG; import static
 * com.badlogic.gdx.graphics.g2d.Animation.LOOP_RANDOM; import static
 * com.badlogic.gdx.graphics.g2d.Animation.LOOP_REVERSED; import static
 * com.badlogic.gdx.graphics.g2d.Animation.NORMAL; import static
 * com.badlogic.gdx.graphics.g2d.Animation.REVERSED; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.C1; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.C2; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.C3; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.C4; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.U1; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.U2; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.U3; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.U4; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.V1; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.V2; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.V3; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.V4; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.X1; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.X2; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.X3; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.X4; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.Y1; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.Y2; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.Y3; import static
 * com.badlogic.gdx.graphics.g2d.SpriteBatch.Y4;
 * 
 * import com.badlogic.gdx.graphics.Color; import
 * com.badlogic.gdx.graphics.Texture; import
 * com.badlogic.gdx.graphics.g2d.SpriteBatch; import
 * com.badlogic.gdx.graphics.g2d.TextureRegion; import
 * com.badlogic.gdx.math.Circle; import com.badlogic.gdx.math.MathUtils; import
 * com.badlogic.gdx.math.Rectangle; import com.badlogic.gdx.utils.Array; import
 * com.badlogic.gdx.utils.NumberUtils;
 * 
 * /** Holds the geometry, color, and texture information for drawing 2D sprites
 * using {@link SpriteBatch}. A Sprite has a position and a size given as width
 * and height. The position is relative to the origin of the coordinate system
 * specified via {@link SpriteBatch#begin()} and the respective matrices. A
 * Sprite is always rectangular and its position (x, y) are located in the
 * bottom left corner of that rectangle. A Sprite also has an origin around
 * which rotations and scaling are performed (that is, the origin is not
 * modified by rotation and scaling). The origin is given relative to the bottom
 * left corner of the Sprite, its position.
 * 
 * @author mzechner
 * @author Nathan Sweet
 * @author Ngo Trong Trung
 */
public class SpriteAnimation implements Animator, SpriteBackend {
	TextureRegion[] keyFrames;

	float mFrameDuration;
	float mStateTime;
	float mAnimationDuration;
	private boolean isFlipX, isFlipY;

	private int frameNumber;

	private int mPlayMode = Animation.NORMAL;

	private Texture mCurrentTexture;

	protected boolean RUN = false;
	// ---------------------------------------------------------

	static final int VERTEX_SIZE = 2 + 1 + 2;
	static final int SPRITE_SIZE = 4 * VERTEX_SIZE;

	// ---------------------------------------------------------

	final float[] vertices = new float[SPRITE_SIZE];
	final float[] rect = new float[4];

	private final Color color = new Color(1, 1, 1, 1);
	private float x, y;
	private float adjX, adjY, scaleTransX, scaleTransY;
	float width, height;
	private float originX, originY;
	private float rotation;
	private float scaleX = 1, scaleY = 1;
	private boolean dirty = true;
	private final Rectangle bounds;
	static public final byte X1 = 0;
	static public final byte Y1 = 1;
	static public final byte C1 = 2;
	static public final byte U1 = 3;
	static public final byte V1 = 4;
	static public final byte X2 = 5;
	static public final byte Y2 = 6;
	static public final byte C2 = 7;
	static public final byte U2 = 8;
	static public final byte V2 = 9;
	static public final byte X3 = 10;
	static public final byte Y3 = 11;
	static public final byte C3 = 12;
	static public final byte U3 = 13;
	static public final byte V3 = 14;
	static public final byte X4 = 15;
	static public final byte Y4 = 16;
	static public final byte C4 = 17;
	static public final byte U4 = 18;
	static public final byte V4 = 19;

//	Array<Animator> animators = new Array<Animator>();
	
	// ---------------------------------------------------------

	/**
	 * Creates an uninitialized sprite. The sprite will need a texture, texture
	 * region, bounds, and color set before it can be drawn.
	 */
	public SpriteAnimation() {
		setColor(1, 1, 1, 1);
		setSize(100, 100);
		setOrigin(50, 50);

		bounds = new Rectangle();
	}
	
	public float getFrameDuration() {
		return mFrameDuration;
	}

	public SpriteAnimation(Array<?> region) {
		keyFrames = new TextureRegion[region.size];
		for (int i = 0; i < keyFrames.length; i++)
			keyFrames[i] = (TextureRegion) region.get(i);
		setRegion(keyFrames[0]);

		setColor(1, 1, 1, 1);
		setSize(keyFrames[0].getRegionWidth(), keyFrames[0].getRegionHeight());
		setOrigin(width / 2, height / 2);

		setTexture(keyFrames);

		bounds = new Rectangle();
	}

	// Note the region is copied.
	public SpriteAnimation(TextureRegion[] region) {
		keyFrames = region;
		setRegion(region[0]);

		setColor(1, 1, 1, 1);
		setSize(keyFrames[0].getRegionWidth(), keyFrames[0].getRegionHeight());
		setOrigin(width / 2, height / 2);

		setTexture(keyFrames);

		bounds = new Rectangle();
	}

	/** Creates a sprite that is a copy in every way of the specified sprite. */
	public SpriteAnimation(SpriteAnimation sprite) {
		set(sprite);

		bounds = new Rectangle();
	}

	public SpriteAnimation(TextureRegion region) {
		TextureRegion[] regions = new TextureRegion[1];
		regions[0] = region;

		keyFrames = regions;
		setRegion(regions[0]);

		setColor(1, 1, 1, 1);
		setSize(keyFrames[0].getRegionWidth(), keyFrames[0].getRegionHeight());
		setOrigin(width / 2, height / 2);

		setTexture(keyFrames);

		bounds = new Rectangle();
	}

	/***********************************************************
	 * 
	 ***********************************************************/

	public void set(SpriteAnimation sprite) {
		if (sprite == null)
			throw new IllegalArgumentException("sprite cannot be null.");
		System.arraycopy(sprite.vertices, 0, vertices, 0, SPRITE_SIZE);
		keyFrames = sprite.keyFrames;
		setTexture(keyFrames);
		x = sprite.x;
		y = sprite.y;
		width = sprite.width;
		height = sprite.height;
		originX = sprite.originX;
		originY = sprite.originY;
		rotation = sprite.rotation;
		scaleX = sprite.scaleX;
		scaleY = sprite.scaleY;
		color.set(sprite.color);
		dirty = sprite.dirty;
	}

	/**
	 * Sets the position and size of the sprite when drawn, before scaling and
	 * rotation are applied. If origin, rotation, or scale are changed, it is
	 * slightly more efficient to set the bounds after those operations.
	 */
	public void setBounds(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		if (dirty)
			return;

		float x2 = x + width;
		float y2 = y + height;
		final float[] vertices = this.vertices;
		vertices[X1] = x;
		vertices[Y1] = y;

		vertices[X2] = x;
		vertices[Y2] = y2;

		vertices[X3] = x2;
		vertices[Y3] = y2;

		vertices[X4] = x2;
		vertices[Y4] = y;

		if (rotation != 0 || scaleX != 1 || scaleY != 1)
			dirty = true;
	}

	/**
	 * Sets the size of the sprite when drawn, before scaling and rotation are
	 * applied. If origin, rotation, or scale are changed, it is slightly more
	 * efficient to set the size after those operations. If both position and
	 * size are to be changed, it is better to use
	 * {@link #setBounds(float, float, float, float)}.
	 */
	public void setSize(float width, float height) {
		this.width = width;
		this.height = height;

		if (dirty)
			return;

		float x2 = x + width;
		float y2 = y + height;
		final float[] vertices = this.vertices;
		vertices[X1] = x;
		vertices[Y1] = y;

		vertices[X2] = x;
		vertices[Y2] = y2;

		vertices[X3] = x2;
		vertices[Y3] = y2;

		vertices[X4] = x2;
		vertices[Y4] = y;

		if (rotation != 0 || scaleX != 1 || scaleY != 1)
			dirty = true;
	}

	/**
	 * Sets the position where the sprite will be drawn. If origin, rotation, or
	 * scale are changed, it is slightly more efficient to set the position
	 * after those operations. If both position and size are to be changed, it
	 * is better to use {@link #setBounds(float, float, float, float)}.
	 */
	public void setPosition(float x, float y) {
		translate(x - this.x, y - this.y);
	}

	/**
	 * Sets the x position where the sprite will be drawn. If origin, rotation,
	 * or scale are changed, it is slightly more efficient to set the position
	 * after those operations. If both position and size are to be changed, it
	 * is better to use {@link #setBounds(float, float, float, float)}.
	 */
	public void setX(float x) {
		translateX(x - this.x);
	}

	/**
	 * Sets the y position where the sprite will be drawn. If origin, rotation,
	 * or scale are changed, it is slightly more efficient to set the position
	 * after those operations. If both position and size are to be changed, it
	 * is better to use {@link #setBounds(float, float, float, float)}.
	 */
	public void setY(float y) {
		translateY(y - this.y);
	}

	/**
	 * Sets the x position relative to the current position where the sprite
	 * will be drawn. If origin, rotation, or scale are changed, it is slightly
	 * more efficient to translate after those operations.
	 */
	public void translateX(float xAmount) {
		this.x += xAmount;

		if (dirty)
			return;

		final float[] vertices = this.vertices;
		vertices[X1] += xAmount;
		vertices[X2] += xAmount;
		vertices[X3] += xAmount;
		vertices[X4] += xAmount;
	}

	/**
	 * Sets the y position relative to the current position where the sprite
	 * will be drawn. If origin, rotation, or scale are changed, it is slightly
	 * more efficient to translate after those operations.
	 */
	public void translateY(float yAmount) {
		y += yAmount;

		if (dirty)
			return;

		final float[] vertices = this.vertices;
		vertices[Y1] += yAmount;
		vertices[Y2] += yAmount;
		vertices[Y3] += yAmount;
		vertices[Y4] += yAmount;
	}

	/**
	 * Sets the position relative to the current position where the sprite will
	 * be drawn. If origin, rotation, or scale are changed, it is slightly more
	 * efficient to translate after those operations.
	 */
	public void translate(float xAmount, float yAmount) {
		x += xAmount;
		y += yAmount;

		if (dirty)
			return;

		final float[] vertices = this.vertices;
		vertices[X1] += xAmount;
		vertices[Y1] += yAmount;

		vertices[X2] += xAmount;
		vertices[Y2] += yAmount;

		vertices[X3] += xAmount;
		vertices[Y3] += yAmount;

		vertices[X4] += xAmount;
		vertices[Y4] += yAmount;
	}

	public void setColor(Color tint) {
		float color = tint.toFloatBits();
		final float[] vertices = this.vertices;
		vertices[C1] = color;
		vertices[C2] = color;
		vertices[C3] = color;
		vertices[C4] = color;
	}

	public void setColor(float r, float g, float b, float a) {
		final int intBits = ((int) (255 * a) << 24) | ((int) (255 * b) << 16)
				| ((int) (255 * g) << 8) | ((int) (255 * r));
		float color = NumberUtils.intToFloatColor(intBits);
		final float[] vertices = this.vertices;
		vertices[C1] = color;
		vertices[C2] = color;
		vertices[C3] = color;
		vertices[C4] = color;
	}

	/**
	 * Sets the origin in relation to the sprite's position for scaling and
	 * rotation.
	 */
	public void setOrigin(float originX, float originY) {
		this.originX = originX;
		this.originY = originY;
		dirty = true;
	}

	public void setRotation(float degrees) {
		this.rotation = degrees;
		dirty = true;
	}

	/** Sets the sprite's rotation relative to the current rotation. */
	public void rotate(float degrees) {
		rotation += degrees;
		dirty = true;
	}

	/**
	 * Rotates this sprite 90 degrees in-place by rotating the texture
	 * coordinates. This rotation is unaffected by {@link #setRotation(float)}
	 * and {@link #rotate(float)}.
	 */
	public void rotate90(boolean clockwise) {
		final float[] vertices = this.vertices;

		if (clockwise) {
			float temp = vertices[V1];
			vertices[V1] = vertices[V4];
			vertices[V4] = vertices[V3];
			vertices[V3] = vertices[V2];
			vertices[V2] = temp;

			temp = vertices[U1];
			vertices[U1] = vertices[U4];
			vertices[U4] = vertices[U3];
			vertices[U3] = vertices[U2];
			vertices[U2] = temp;
		} else {
			float temp = vertices[V1];
			vertices[V1] = vertices[V2];
			vertices[V2] = vertices[V3];
			vertices[V3] = vertices[V4];
			vertices[V4] = temp;

			temp = vertices[U1];
			vertices[U1] = vertices[U2];
			vertices[U2] = vertices[U3];
			vertices[U3] = vertices[U4];
			vertices[U4] = temp;
		}
	}

	public void setScale(float scaleXY) {
		this.scaleX = scaleXY;
		this.scaleY = scaleXY;
		dirty = true;
	}

	public void setScale(float scaleX, float scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		dirty = true;
	}

	/** Sets the sprite's scale relative to the current scale. */
	public void scale(float amount) {
		this.scaleX += amount;
		this.scaleY += amount;
		dirty = true;
	}

	/***********************************************************
	 * Animation Controller
	 ***********************************************************/

	public void setFrameDuration(float frameDuration) {
		this.mFrameDuration = frameDuration;
	}

	public void setPlayMode(int playMode) {
		mPlayMode = playMode;
	}

	public void setLooping(boolean looping) {
		if (looping
				&& (mPlayMode == Animation.NORMAL || mPlayMode == Animation.REVERSED)) {
			if (mPlayMode == Animation.NORMAL)
				mPlayMode = Animation.LOOP;
			else
				mPlayMode = Animation.LOOP_REVERSED;
		} else if (!looping
				&& !(mPlayMode == Animation.NORMAL || mPlayMode == Animation.REVERSED)) {
			if (mPlayMode == Animation.LOOP_REVERSED)
				mPlayMode = Animation.REVERSED;
			else
				mPlayMode = Animation.LOOP;
		}
	}

	private void setTexture(TextureRegion[] texture) {
		mCurrentTexture = texture[0].getTexture();
	}

	public void setKeyFrames(TextureRegion[] keyFrame) {
		this.keyFrames = keyFrame;
		setRegion(keyFrames[0]);
		setTexture(keyFrame);
		setColor(1, 1, 1, 1);
		setSize(keyFrames[0].getRegionWidth(), keyFrames[0].getRegionHeight());
		setOrigin(width / 2, height / 2);
	}

	public void setKeyFrames(Array<?> keyFrame) {
		keyFrames = new TextureRegion[keyFrame.size];
		for (int i = 0; i < keyFrames.length; i++)
			keyFrames[i] = (TextureRegion) keyFrame.get(i);
		setRegion(keyFrames[0]);
		setTexture(keyFrames);
	}

	public void setFlipX(boolean isFlipX) {
		this.isFlipX = isFlipX;
	}

	public void setFlipY(boolean isFlipY) {
		this.isFlipY = isFlipY;
	}

	public void start() {
		RUN = true;
	}

	public void start(float frameDuration) {
		RUN = true;
		mFrameDuration = frameDuration;
	}

	public void start(float frameDuration, int playMode) {
		RUN = true;
		mFrameDuration = frameDuration;
		mPlayMode = playMode;
	}

	public void stop() {
		RUN = false;
		mStateTime = 0;
		setRegion(keyFrames[0]);
	}

	public void pause() {
		RUN = false;
	}

	public void switchState() {
		RUN = !RUN;
	}

	public void resetFrame() {
		mStateTime = 0;
		setRegion(keyFrames[0]);
	}

	public void update(float delta) {
		if (!RUN)
			return;
		mStateTime += delta;
		frameNumber = (int) (mStateTime / mFrameDuration);
		switch (mPlayMode) {
		case Animation.NORMAL:
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
			break;
		case Animation.LOOP:
			frameNumber = frameNumber % keyFrames.length;
			break;
		case Animation.LOOP_PINGPONG:
			frameNumber = frameNumber % (keyFrames.length * 2);
			if (frameNumber >= keyFrames.length)
				frameNumber = keyFrames.length - 1
						- (frameNumber - keyFrames.length);
			break;
		case Animation.LOOP_RANDOM:
			frameNumber = MathUtils.random(keyFrames.length - 1);
			break;
		case Animation.REVERSED:
			frameNumber = Math.max(keyFrames.length - frameNumber - 1, 0);
			break;
		case Animation.LOOP_REVERSED:
			frameNumber = frameNumber % keyFrames.length;
			frameNumber = keyFrames.length - frameNumber - 1;
			break;

		default:
			// play normal otherwise
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
			break;
		}
		setRegion(keyFrames[frameNumber]);
		
		if (isFlipX && !getCurrentFrame().isFlipX()) {
			for(int i = 0;i< keyFrames.length;i++)
				if (!keyFrames[i].isFlipX())
					keyFrames[i].flip(true, false);
			adjX += scaleTransX;
		}
		
		if (!isFlipX && getCurrentFrame().isFlipX()) {
			for(int i = 0;i< keyFrames.length;i++)
				if (keyFrames[i].isFlipX())
					keyFrames[i].flip(true, false);
			adjX -= scaleTransX;
		}
		
		if (isFlipY && !getCurrentFrame().isFlipY()) {
			for(int i = 0;i< keyFrames.length;i++)
				if (!keyFrames[i].isFlipY())
					keyFrames[i].flip(false, true);
			adjY += scaleTransY;
		}
		
		if (!isFlipY && getCurrentFrame().isFlipY()) {
			for(int i = 0;i< keyFrames.length;i++)
				if (keyFrames[i].isFlipY())
					keyFrames[i].flip(false, true);
			adjY -= scaleTransY;
		}
	}

	/**
	 * Get current frame number ( unsafe method)
	 * 
	 * @return
	 */
	public int getFrameNumber() {
		return frameNumber;
	}

	public TextureRegion[] getFrames() {
		return this.keyFrames;
	}

	@Override
	public boolean isRunning() {
		return RUN;
	}

	public boolean isAnimationFinished() {
		int frameNumber = (int) (mStateTime / mFrameDuration);
		return keyFrames.length - 1 < frameNumber;
	}

	/***********************************************************
	 * 
	 ***********************************************************/

	/**
	 * Returns the packed vertices, colors, and texture coordinates for this
	 * sprite.
	 */
	public float[] getVertices() {
		if (dirty) {
			dirty = false;

			float[] vertices = this.vertices;
			float localX = -originX;
			float localY = -originY;
			float localX2 = localX + width;
			float localY2 = localY + height;
			float worldOriginX = this.x - localX;
			float worldOriginY = this.y - localY;
			if (scaleX != 1 || scaleY != 1) {
				localX *= scaleX;
				localY *= scaleY;
				localX2 *= scaleX;
				localY2 *= scaleY;
			}
			if (rotation != 0) {
				final float cos = MathUtils.cosDeg(rotation);
				final float sin = MathUtils.sinDeg(rotation);
				final float localXCos = localX * cos;
				final float localXSin = localX * sin;
				final float localYCos = localY * cos;
				final float localYSin = localY * sin;
				final float localX2Cos = localX2 * cos;
				final float localX2Sin = localX2 * sin;
				final float localY2Cos = localY2 * cos;
				final float localY2Sin = localY2 * sin;

				final float x1 = localXCos - localYSin + worldOriginX;
				final float y1 = localYCos + localXSin + worldOriginY;
				vertices[X1] = x1;
				vertices[Y1] = y1;

				final float x2 = localXCos - localY2Sin + worldOriginX;
				final float y2 = localY2Cos + localXSin + worldOriginY;
				vertices[X2] = x2;
				vertices[Y2] = y2;

				final float x3 = localX2Cos - localY2Sin + worldOriginX;
				final float y3 = localY2Cos + localX2Sin + worldOriginY;
				vertices[X3] = x3;
				vertices[Y3] = y3;

				vertices[X4] = x1 + (x3 - x2);
				vertices[Y4] = y3 - (y2 - y1);
			} else {
				final float x1 = localX + worldOriginX;
				final float y1 = localY + worldOriginY;
				final float x2 = localX2 + worldOriginX;
				final float y2 = localY2 + worldOriginY;

				vertices[X1] = x1;
				vertices[Y1] = y1;

				vertices[X2] = x1;
				vertices[Y2] = y2;

				vertices[X3] = x2;
				vertices[Y3] = y2;

				vertices[X4] = x2;
				vertices[Y4] = y1;
			}
		}
		return vertices;
	}

	/**
	 * Returns the bounding axis aligned {@link Rectangle} that bounds this
	 * sprite. The rectangles x and y coordinates describe its bottom left
	 * corner. If you change the position or size of the sprite, you have to
	 * fetch the triangle again for it to be recomputed.
	 * 
	 * @return the bounding Rectangle
	 */
	public Rectangle getBoundingRectangle() {
		final float[] vertices = getVertices();

		float minx = vertices[X1];
		float miny = vertices[Y1];
		float maxx = vertices[X1];
		float maxy = vertices[Y1];

		minx = minx > vertices[X2] ? vertices[X2] : minx;
		minx = minx > vertices[X3] ? vertices[X3] : minx;
		minx = minx > vertices[X4] ? vertices[X4] : minx;

		maxx = maxx < vertices[X2] ? vertices[X2] : maxx;
		maxx = maxx < vertices[X3] ? vertices[X3] : maxx;
		maxx = maxx < vertices[X4] ? vertices[X4] : maxx;

		miny = miny > vertices[Y2] ? vertices[Y2] : miny;
		miny = miny > vertices[Y3] ? vertices[Y3] : miny;
		miny = miny > vertices[Y4] ? vertices[Y4] : miny;

		maxy = maxy < vertices[Y2] ? vertices[Y2] : maxy;
		maxy = maxy < vertices[Y3] ? vertices[Y3] : maxy;
		maxy = maxy < vertices[Y4] ? vertices[Y4] : maxy;

		bounds.x = minx;
		bounds.y = miny;
		bounds.width = maxx - minx;
		bounds.height = maxy - miny;
		return bounds;
	}

	public float[] getBoundingFloatRect(float offset) {
		final float[] vertices = getVertices();

		float minx = vertices[X1];
		float miny = vertices[Y1];
		float maxx = vertices[X1];
		float maxy = vertices[Y1];

		minx = minx > vertices[X2] ? vertices[X2] : minx;
		minx = minx > vertices[X3] ? vertices[X3] : minx;
		minx = minx > vertices[X4] ? vertices[X4] : minx;

		maxx = maxx < vertices[X2] ? vertices[X2] : maxx;
		maxx = maxx < vertices[X3] ? vertices[X3] : maxx;
		maxx = maxx < vertices[X4] ? vertices[X4] : maxx;

		miny = miny > vertices[Y2] ? vertices[Y2] : miny;
		miny = miny > vertices[Y3] ? vertices[Y3] : miny;
		miny = miny > vertices[Y4] ? vertices[Y4] : miny;

		maxy = maxy < vertices[Y2] ? vertices[Y2] : maxy;
		maxy = maxy < vertices[Y3] ? vertices[Y3] : maxy;
		maxy = maxy < vertices[Y4] ? vertices[Y4] : maxy;

		rect[0] = minx + offset;
		rect[1] = miny + offset;
		rect[2] = maxx - minx - offset;
		rect[3] = maxy - miny - offset;

		return rect;
	}

	public Circle getBoundingCircle() {
		return null;
	}

	public void draw(SpriteBatch spriteBatch) {
		translate(adjX, adjY);
		spriteBatch.draw(mCurrentTexture, getVertices(), 0, SPRITE_SIZE);
		translate(-adjX, -adjY);
	}

	public void draw(SpriteBatch spriteBatch, float alphaModulation) {
		final Color color = getColor();
		float oldAlpha = color.a;
		color.a *= alphaModulation;
		setColor(color);
		draw(spriteBatch);
		color.a = oldAlpha;
		setColor(color);
	}

	/***********************************************************
	 * 
	 ***********************************************************/

	public float getX() {
		return x;
	}

	public float getCenterX() {
		final float[] vertices = getVertices();

		float minx = vertices[X1];
		float maxx = vertices[X1];

		minx = minx > vertices[X2] ? vertices[X2] : minx;
		minx = minx > vertices[X3] ? vertices[X3] : minx;
		minx = minx > vertices[X4] ? vertices[X4] : minx;

		maxx = maxx < vertices[X2] ? vertices[X2] : maxx;
		maxx = maxx < vertices[X3] ? vertices[X3] : maxx;
		maxx = maxx < vertices[X4] ? vertices[X4] : maxx;

		return (minx + maxx) / 2;
	}

	public float getY() {
		return y;
	}

	public float getCenterY() {
		final float[] vertices = getVertices();

		float miny = vertices[Y1];
		float maxy = vertices[Y1];

		miny = miny > vertices[Y2] ? vertices[Y2] : miny;
		miny = miny > vertices[Y3] ? vertices[Y3] : miny;
		miny = miny > vertices[Y4] ? vertices[Y4] : miny;

		maxy = maxy < vertices[Y2] ? vertices[Y2] : maxy;
		maxy = maxy < vertices[Y3] ? vertices[Y3] : maxy;
		maxy = maxy < vertices[Y4] ? vertices[Y4] : maxy;

		return (miny + maxy) / 2;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getOriginX() {
		return originX;
	}

	public float getOriginY() {
		return originY;
	}

	public float getRotation() {
		return rotation;
	}

	public float getScaleX() {
		return scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	/**
	 * Returns the color of this sprite. Changing the returned color will have
	 * no affect, {@link #setColor(Color)} or
	 * {@link #setColor(float, float, float, float)} must be used.
	 */
	public Color getColor() {
		final int intBits = NumberUtils.floatToIntColor(vertices[C1]);
		final Color color = this.color;
		color.r = (intBits & 0xff) / 255f;
		color.g = ((intBits >>> 8) & 0xff) / 255f;
		color.b = ((intBits >>> 16) & 0xff) / 255f;
		color.a = ((intBits >>> 24) & 0xff) / 255f;
		return color;
	}

	protected void setRegion(TextureRegion region) {
		this.mCurrentTexture = region.getTexture();

		final float u = region.getU();
		final float v = region.getV();
		final float u2 = region.getU2();
		final float v2 = region.getV2();

		final float[] vertices = SpriteAnimation.this.vertices;

		vertices[U1] = u;
		vertices[V1] = v2;

		vertices[U2] = u;
		vertices[V2] = v;

		vertices[U3] = u2;
		vertices[V3] = v;

		vertices[U4] = u2;
		vertices[V4] = v2;
	}

	public void flip(boolean x, boolean y) {
		for (int i = 0; i < keyFrames.length; i++)
			keyFrames[i].flip(x, y);

		float[] vertices = SpriteAnimation.this.vertices;
		if (x) {
			float temp = vertices[U1];
			vertices[U1] = vertices[U3];
			vertices[U3] = temp;
			temp = vertices[U2];
			vertices[U2] = vertices[U4];
			vertices[U4] = temp;
		}
		if (y) {
			float temp = vertices[V1];
			vertices[V1] = vertices[V3];
			vertices[V3] = temp;
			temp = vertices[V2];
			vertices[V2] = vertices[V4];
			vertices[V4] = temp;
		}
	}

	public void scroll(float xAmount, float yAmount) {

		final float[] vertices = SpriteAnimation.this.vertices;
		if (xAmount != 0) {
			final float u = (vertices[U1] + xAmount) % 1;
			final float u2 = u + width / mCurrentTexture.getWidth();
			vertices[U1] = u;
			vertices[U2] = u;
			vertices[U3] = u2;
			vertices[U4] = u2;
		}
		if (yAmount != 0) {
			final float v = (vertices[V2] + yAmount) % 1;
			final float v2 = v + height / mCurrentTexture.getHeight();
			vertices[V1] = v2;
			vertices[V2] = v;
			vertices[V3] = v;
			vertices[V4] = v2;
		}
	}

	public boolean hit(float x, float y) {
		if (x >= getX() && x <= (getX() + getWidth()) && y >= getY()
				&& y <= (getY() + getHeight())) {
			return true;
		}
		return false;
	}

	// @Override
	public void reset() {
		stop();
		setPosition(0, 0);
		setSize(0, 0);
		setOrigin(0, 0);
		rotation = 0;
		scaleX = 1;
		scaleY = 1;
		dirty = false;
		setColor(1, 1, 1, 1);
	}

	@SuppressWarnings("unused")
	private float[] getAdjustVertices() {
		float[] vertices = getVertices();
		vertices[X1] -= adjX;
		vertices[X2] -= adjX;
		vertices[X3] -= adjX;
		vertices[X4] -= adjX;

		vertices[Y1] -= adjY;
		vertices[Y2] -= adjY;
		vertices[Y3] -= adjY;
		vertices[Y4] -= adjY;
		return vertices;
	}
	
	public void setAdjustImage(float x, float y) {
		adjX = x;
		adjY = y;
	}
	
	public void setAdjX(float adjX) {
		this.adjX = adjX;
	}
	
	public void setAdjY(float adjY) {
		this.adjY = adjY;
	}

	public void setAdjustImage(float x, float y,float scaleTransX,float scaleTransY) {
		adjX = x;
		adjY = y;
		this.scaleTransX = scaleTransX;
		this.scaleTransY = scaleTransY;
	}

	public TextureRegion getCurrentFrame() {
		return keyFrames[frameNumber];
	}
	
	public float getAdjX() {
		return adjX;
	}
	
	public float getAdjY() {
		return adjY;
	}
	
	public void setCenterX(float x){
		translateX(x - getCenterX());
	}
	
	public void setCenterY(float y){
		translateY(y - getCenterY());
	}
	
	public void setCenterXY(float x,float y){
		setCenterX(x);
		setCenterY(y);
	}
	
	public void resize(float num){
		setSize(getWidth()*num, getHeight()*num);
	}

}
