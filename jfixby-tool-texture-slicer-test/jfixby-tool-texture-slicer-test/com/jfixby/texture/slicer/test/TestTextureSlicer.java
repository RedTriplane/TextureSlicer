package com.jfixby.texture.slicer.test;

import java.io.IOException;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.cmns.api.assets.Names;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.desktop.DesktopAssembler;
import com.jfixby.texture.slicer.api.SlicesCompositionInfo;
import com.jfixby.texture.slicer.api.SlicesCompositionsContainer;
import com.jfixby.texture.slicer.api.TextureSlicer;
import com.jfixby.texture.slicer.api.TextureSlicerSpecs;
import com.jfixby.texture.slicer.api.TextureSlicingResult;
import com.jfixby.texture.slicer.red.RedTextureSlicer;

public class TestTextureSlicer {

	public static void main(String[] args) throws IOException {
		DesktopAssembler.setup();

		TextureSlicer.installComponent(new RedTextureSlicer());

		File input_folder = LocalFileSystem.ApplicationHome().child("input");
		File output_folder = LocalFileSystem.ApplicationHome().child("output");

		File input_file = input_folder.child("example.jpg");

		output_folder.makeFolder();
		output_folder.clearFolder();

		TextureSlicerSpecs specs = TextureSlicer.newDecompositionSpecs();
		specs.setInputFile(input_file);
		int tile_size = 256 * 2;
		int margin = TextureSlicerSpecs.MIN_TILE_SIZE / 2;
		specs.setTileWidth(tile_size - 2 * margin);
		specs.setTileHeight(tile_size - 2 * margin);
		specs.setMargin(margin);

		AssetID package_name = Names.newAssetID("com.jfixby.tool.texture.slicer.example");

		specs.setNameSpacePrefix(package_name);

		specs.setOutputFolder(output_folder);

		TextureSlicingResult result = TextureSlicer.decompose(specs);
		{// Save resulting structure info
			L.d("output", result.getRasterID());
			SlicesCompositionInfo composition = result.getTilesComposition();
			SlicesCompositionsContainer container = new SlicesCompositionsContainer();
			container.content.addElement(composition);
			String data = Json.serializeToString(container);

			AssetID sctruct_package_name = package_name.child(TextureSlicerSpecs.TILE_MAP_FILE_EXTENSION);
			String struct_pkg_name = sctruct_package_name.toString();
			File container_file = output_folder.child(struct_pkg_name);
			container_file.writeString(data);

			// com.badlogic.gdx.utils.Json j = new
			// com.badlogic.gdx.utils.Json();
			JsonValue gdx_json = new JsonReader().parse(data);
			L.d("structure:");
			L.d(gdx_json.toString());

		}
	}
}
