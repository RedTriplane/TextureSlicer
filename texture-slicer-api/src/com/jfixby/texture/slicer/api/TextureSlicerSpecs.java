
package com.jfixby.texture.slicer.api;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.file.File;

public interface TextureSlicerSpecs {

	public static final int MIN_TILE_SIZE = 32;

	public static final String TILE_MAP_FILE_EXTENSION = "r3-trs";

	void setInputFile (File input_file);

	void setTileWidth (int tile_width);

	void setTileHeight (int tile_height);

	void setNameSpacePrefix (ID newAssetID);

	void setOutputFolder (File parent);

	void setMargin (int margin);

	public File getInputFile ();

	public int getTileWidth ();

	public int getTileHeight ();

	public ID getNameSpacePrefix ();

	public File getOutputFolder ();

	public int getMargin ();

	float getImageQuaity ();

	void setImageQuality (float d);

}
