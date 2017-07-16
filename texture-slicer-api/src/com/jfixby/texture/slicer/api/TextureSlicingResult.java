package com.jfixby.texture.slicer.api;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.texture.slicer.api.io.SlicesCompositionInfo;

public interface TextureSlicingResult {

	ID getRasterID();

	Collection<ID> listProducedTiles();

	SlicesCompositionInfo getTilesComposition();

}
