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
package com.tenio.gdx.c2engine.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * 
 * @author kong
 *
 */
public class Utils {
	private static final String VNI_CHAR = "()Ã¡Â»ï¿½!?Ã¡Â»ï¿½Ã¡ÂºÂ§ÃƒÂ³ÃƒÂ£:1234567890$AÃ„â€šÃƒâ€šBCDÃ„ï¿½EÃƒÅ GHIKLMNOÃƒâ€�Ã†Â PQRSTUÃ†Â¯VXYaÃ„Æ’ÃƒÂ¢bcdÃ„â€˜eÃƒÂªghiklmnoÃƒÂ´Ã†Â¡pqrstuÃ†Â°vxyÃ¡Â»â„¢Ã¡Â»Â¥Ã¡Â»â€˜Ã¡Â»Æ’ÃƒÂ []Ã¡ÂºÂ­ÃƒÂ¡ÃƒÂºÃ¡ÂºÂ»Ã¡Â»â€ºÃ¡Â»ï¿½Ã¡Â»Â©Ã¡ÂºÂ¯Ã¡ÂºÂ¥Ã¡Â»Å¸Ã¡Â»Â£Ã¡ÂºÂ©Ã¡Â»â€¹ÃƒÂ¬ÃƒÂ­Ã¡Â»Â±ÃƒÂ©Ã¡Â»â€œÃ¡ÂºÂ¡Ã¡Â»Â­Ã¡ÂºÂ¿Ã¡ÂºÂ£Ã¡ÂºÂ¢Ã¡Â»â€¢Ã¡Â»â€�";

	/**
	 * make bitmap font from true type font
	 * 
	 * @param file
	 * @param size
	 * @param isVNIFont
	 * @return
	 */
	protected static BitmapFont getFontGenerate(String file, int size, boolean isVNIFont) {
		BitmapFont font;
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(file));
		if (isVNIFont)
			font = generator.generateFont(size, VNI_CHAR, false);
		else
			font = generator.generateFont(size);
		generator.dispose();
		return font;
	}

	/**
	 * Load Particle Effect
	 * 
	 * @param folder
	 * @param fileName
	 * @return
	 */
	public static ParticleEffect loadParticleEffect(String folder, String fileName) {
		ParticleEffect effect;

		effect = new ParticleEffect();
		effect.load(Gdx.files.internal(folder + "/" + fileName), Gdx.files.internal(folder));

		return effect;
	}

	/**
	 * Load Particle Effect
	 * 
	 * @param folder       : path + file name
	 * @param TextureAtlas
	 * @return
	 */
	public static ParticleEffect loadParticleEffect(String folder, TextureAtlas atlas) {
		ParticleEffect effect;

		effect = new ParticleEffect();
		effect.load(Gdx.files.internal(folder), atlas);

		return effect;
	}

	public static void printTouchPosition(float width, float height) {
		System.out.println("X : " + Gdx.input.getX() * width / Gdx.graphics.getWidth() + " Y : "
				+ (Gdx.graphics.getHeight() - Gdx.input.getY()) * height / Gdx.graphics.getHeight());
	}

}
