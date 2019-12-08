package com.tenio.gdx.c2engine.asset;

/**
 * The object which other can get asset from that
 * 
 * @author mytv
 *
 */
// "able" should be add in last of interface name, it's reminiscent that this is
// a interface
public interface AssetManageable {

	public Iterable<Asset> loadNeedAssets();

	public Iterable<Asset> unloadAssets();

}
