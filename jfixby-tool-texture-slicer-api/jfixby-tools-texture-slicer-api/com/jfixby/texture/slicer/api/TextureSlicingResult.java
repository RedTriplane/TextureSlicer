package com.jfixby.texture.slicer.api;

import com.jfixby.cmns.api.assets.ID;
import com.jfixby.cmns.api.collections.Collection;

public interface TextureSlicingResult {

	ID getRasterID();

	Collection<ID> listProducedTiles();

	SlicesCompositionInfo getTilesComposition();

}
