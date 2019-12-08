package com.tenio.gdx.c2engine.asset;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * 
 * @author mytv
 * 
 */
public class ResourceManager {
	private static AssetManager assetManager = new AssetManager();

	public static AssetManager getAssetManager() {
		return assetManager;
	}

	/**
	 * = assetManager.get
	 * 
	 * @param asset
	 * @param clazz
	 * @return
	 */
	public static <T> T get(Asset asset, Class<T> clazz) {
		return assetManager.get(asset.pathName, clazz);
	}

	/**
	 * = assetManager.get(asset,Texture.class)
	 * 
	 * @param asset
	 * @param clazz
	 * @return
	 */
	public static Texture getTexture(Asset asset) {
		// get texture
		Texture texture = assetManager.get(asset.pathName, Texture.class);
		// filter
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return texture;
	}

	public static Skin getSkin(Asset asset) {
		return assetManager.get(asset.pathName, Skin.class);
	}

	public static float getProgress() {
		return assetManager.getProgress();
	}

	public static void preloadAssets(Asset... assets) {
		for (Asset asset : assets) {
			loadAsset(asset);
		}
		assetManager.finishLoading();
	}

	public static void loadAssets(Iterable<Asset> assets) {
		for (Asset asset : assets) {
			loadAsset(asset);
		}
	}

	public static void loadAssets(Asset... assets) {
		for (Asset asset : assets) {
			loadAsset(asset);
		}
	}

	public static void unloadAssets(Iterable<Asset> assets) {
		for (Asset asset : assets) {
			unLoadAsset(asset);
		}
	}

	public static void unloadAssets(Asset... assets) {
		for (Asset asset : assets) {
			unLoadAsset(asset);
		}
	}

	public static boolean isLoadDone() {
		return assetManager.update();
	}

	public static void unLoadAsset(Asset asset) {
		assetManager.unload(asset.pathName);
	}

	public static void loadAsset(Asset asset) {
		assetManager.load(asset.pathName, asset.clazz);
	}
	
}
