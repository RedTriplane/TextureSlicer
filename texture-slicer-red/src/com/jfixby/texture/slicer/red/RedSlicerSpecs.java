
package com.jfixby.texture.slicer.red;

import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.texture.slicer.api.TextureSlicerSpecs;

public class RedSlicerSpecs implements TextureSlicerSpecs {

	@Override
	public String toString () {
		return "RedSlicerSpecs [inputFile=" + this.inputFile + ", tileWidth=" + this.tileWidth + ", tileHeight=" + this.tileHeight
			+ ", nameSpacePrefix=" + this.nameSpacePrefix + ", outputFolder=" + this.outputFolder
			+ ", compositionStructureOutputFileName=" + this.compositionStructureOutputFileName + ", margin=" + this.margin
			+ ", qualityReductionValue=" + this.qualityReductionValue + "]";
	}

	File inputFile;
	int tileWidth = 128;
	int tileHeight = 128;
	ID nameSpacePrefix;
	File outputFolder;
	String compositionStructureOutputFileName;
	int margin = 1;
	private float qualityReductionValue = 1;

	@Override
	public File getInputFile () {
		return this.inputFile;
	}

	@Override
	public void setInputFile (final File inputFile) {
		this.inputFile = inputFile;
	}

	@Override
	public int getTileWidth () {
		return this.tileWidth;
	}

	@Override
	public void setTileWidth (final int tileWidth) {
		this.tileWidth = tileWidth;
	}

	@Override
	public int getTileHeight () {
		return this.tileHeight;
	}

	@Override
	public void setTileHeight (final int tileHeight) {
		this.tileHeight = tileHeight;
	}

	@Override
	public ID getNameSpacePrefix () {
		return this.nameSpacePrefix;
	}

	@Override
	public void setNameSpacePrefix (final ID nameSpacePrefix) {
		this.nameSpacePrefix = nameSpacePrefix;
	}

	@Override
	public File getOutputFolder () {
		return this.outputFolder;
	}

	@Override
	public void setOutputFolder (final File outputFolder) {
		this.outputFolder = outputFolder;
	}

	public String getCompositionStructureOutputFileName () {
		return this.compositionStructureOutputFileName;
	}

	public void setCompositionStructureOutputFileName (final String compositionStructureOutputFileName) {
		this.compositionStructureOutputFileName = compositionStructureOutputFileName;
	}

	@Override
	public int getMargin () {
		return this.margin;
	}

	@Override
	public void setMargin (final int margin) {
		this.margin = margin;
	}

	@Override
	public float getImageQuaity () {
		return this.qualityReductionValue;
	}

	@Override
	public void setImageQuality (final float qualityReductionValue) {
		this.qualityReductionValue = qualityReductionValue;
	}

}
