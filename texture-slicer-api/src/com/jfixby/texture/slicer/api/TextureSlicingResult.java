package com.jfixby.texture.slicer.api;

import com.jfixby.r3.io.texture.slicer.SlicesCompositionInfo;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.names.ID;

public interface TextureSlicingResult {

	ID getRasterID();

	Collection<ID> listProducedTiles();

	SlicesCompositionInfo getTilesComposition();

}
