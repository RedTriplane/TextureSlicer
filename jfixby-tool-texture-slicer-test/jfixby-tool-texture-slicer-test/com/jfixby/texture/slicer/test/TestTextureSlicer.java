
package com.jfixby.texture.slicer.test;

import java.io.IOException;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.texture.slicer.api.SlicesCompositionInfo;
import com.jfixby.texture.slicer.api.SlicesCompositionsContainer;
import com.jfixby.texture.slicer.api.TextureSlicer;
import com.jfixby.texture.slicer.api.TextureSlicerSpecs;
import com.jfixby.texture.slicer.api.TextureSlicingResult;
import com.jfixby.texture.slicer.red.RedTextureSlicer;

public class TestTextureSlicer {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();

		TextureSlicer.installComponent(new RedTextureSlicer());

		final File input_folder = LocalFileSystem.ApplicationHome().child("input");
		final File output_folder = LocalFileSystem.ApplicationHome().child("output");

		final File input_file = input_folder.child("example.jpg");

		output_folder.makeFolder();
		output_folder.clearFolder();

		final TextureSlicerSpecs specs = TextureSlicer.newDecompositionSpecs();
		specs.setInputFile(input_file);
		final int tile_size = 256 * 2;
		final int margin = TextureSlicerSpecs.MIN_TILE_SIZE / 2;
		specs.setTileWidth(tile_size - 2 * margin);
		specs.setTileHeight(tile_size - 2 * margin);
		specs.setMargin(margin);

		final ID package_name = Names.newID("com.jfixby.tool.texture.slicer.example");

		specs.setNameSpacePrefix(package_name);

		specs.setOutputFolder(output_folder);

		final TextureSlicingResult result = TextureSlicer.decompose(specs);
		{// Save resulting structure info
			L.d("output", result.getRasterID());
			final SlicesCompositionInfo composition = result.getTilesComposition();
			final SlicesCompositionsContainer container = new SlicesCompositionsContainer();
			container.content.add(composition);
			final String data = Json.serializeToString(container).toString();

			final ID sctruct_package_name = package_name.child(TextureSlicerSpecs.TILE_MAP_FILE_EXTENSION);
			final String struct_pkg_name = sctruct_package_name.toString();
			final File container_file = output_folder.child(struct_pkg_name);
			container_file.writeString(data);

			// com.badlogic.gdx.utils.Json j = new
			// com.badlogic.gdx.utils.Json();
			final JsonValue gdx_json = new JsonReader().parse(data);
			L.d("structure:");
			L.d(gdx_json.toString());

		}
	}
}
