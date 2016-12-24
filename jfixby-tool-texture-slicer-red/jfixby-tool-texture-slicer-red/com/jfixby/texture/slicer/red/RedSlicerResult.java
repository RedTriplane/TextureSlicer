package com.jfixby.texture.slicer.red;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.texture.slicer.api.SlicesCompositionInfo;
import com.jfixby.texture.slicer.api.TextureSlicingResult;

public class RedSlicerResult implements TextureSlicingResult {

	private ID namespace;
	private List<ID> list = Collections.newList();
	private SlicesCompositionInfo structure;

	public void setAssetID(ID namespace) {
		this.namespace = namespace;
	}

	@Override
	public ID getRasterID() {
		return namespace;
	}

	@Override
	public Collection<ID> listProducedTiles() {
		return list;
	}

	public void addTile(ID newAssetID) {
		list.add(newAssetID);
	}

	public void setStructure(SlicesCompositionInfo structure) {
		this.structure = structure;
	}

	@Override
	public SlicesCompositionInfo getTilesComposition() {
		return structure;
	}

}
