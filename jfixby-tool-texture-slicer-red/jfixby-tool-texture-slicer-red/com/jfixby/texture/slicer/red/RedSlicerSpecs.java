package com.jfixby.texture.slicer.red;

import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.cmns.api.file.File;
import com.jfixby.texture.slicer.api.TextureSlicerSpecs;

public class RedSlicerSpecs implements TextureSlicerSpecs {

	@Override
	public String toString() {
		return "[inputFile=" + inputFile + ", tileWidth=" + tileWidth
				+ ", tileHeight=" + tileHeight + ", nameSpacePrefix="
				+ nameSpacePrefix + ", outputFolder=" + outputFolder
				+ ", compositionStructureOutputFileName="
				+ compositionStructureOutputFileName + ", margin=" + margin
				+ "]";
	}

	File inputFile;
	int tileWidth = 128;
	int tileHeight = 128;
	AssetID nameSpacePrefix;
	File outputFolder;
	String compositionStructureOutputFileName;
	int margin = 1;

	public File getInputFile() {
		return inputFile;
	}

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}

	public AssetID getNameSpacePrefix() {
		return nameSpacePrefix;
	}

	public void setNameSpacePrefix(AssetID nameSpacePrefix) {
		this.nameSpacePrefix = nameSpacePrefix;
	}

	public File getOutputFolder() {
		return outputFolder;
	}

	public void setOutputFolder(File outputFolder) {
		this.outputFolder = outputFolder;
	}

	public String getCompositionStructureOutputFileName() {
		return compositionStructureOutputFileName;
	}

	public void setCompositionStructureOutputFileName(
			String compositionStructureOutputFileName) {
		this.compositionStructureOutputFileName = compositionStructureOutputFileName;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

}
