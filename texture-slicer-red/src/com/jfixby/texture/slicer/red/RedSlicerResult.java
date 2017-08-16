
package com.jfixby.texture.slicer.red;

import com.jfixby.r3.io.texture.slicer.SlicesCompositionInfo;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.texture.slicer.api.TextureSlicingResult;

public class RedSlicerResult implements TextureSlicingResult {

	private ID namespace;
	private final List<ID> list = Collections.newList();
	private SlicesCompositionInfo structure;

	public void setAssetID (final ID namespace) {
		this.namespace = namespace;
	}

	@Override
	public ID getRasterID () {
		return this.namespace;
	}

	@Override
	public Collection<ID> listProducedTiles () {
		return this.list;
	}

	public void addTile (final ID newAssetID) {
		this.list.add(newAssetID);
	}

	public void setStructure (final SlicesCompositionInfo structure) {
		this.structure = structure;
	}

	@Override
	public SlicesCompositionInfo getTilesComposition () {
		return this.structure;
	}

}
