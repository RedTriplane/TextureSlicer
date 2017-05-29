
package com.jfixby.texture.slicer.red;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.color.Color;
import com.jfixby.scarabei.api.color.Colors;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.desktop.ImageAWT;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.image.ArrayColorMapSpecs;
import com.jfixby.scarabei.api.image.ColorMap;
import com.jfixby.scarabei.api.image.EditableColorMap;
import com.jfixby.scarabei.api.image.ImageProcessing;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.IntegerMath;
import com.jfixby.scarabei.api.sys.Sys;
import com.jfixby.texture.slicer.api.SlicesCompositionInfo;
import com.jfixby.texture.slicer.api.TextureSlicerComponent;
import com.jfixby.texture.slicer.api.TextureSlicerSpecs;
import com.jfixby.texture.slicer.api.TextureSlicingResult;

public class RedTextureSlicer implements TextureSlicerComponent {

	@Override
	public TextureSlicerSpecs newDecompositionSpecs () {
		return new RedSlicerSpecs();
	}

	@Override
	public TextureSlicingResult decompose (final TextureSlicerSpecs specs) throws IOException {

		final RedSlicerResult result = new RedSlicerResult();

		L.d(specs);
		final float qualityReductionValue = specs.getImageQuaity();

		final File input_file = specs.getInputFile();
		if (!input_file.isFile()) {
			Err.reportError("Bad file: " + input_file);
		}

		final int margin = (int)IntegerMath.max(specs.getMargin(), 0);
		final int tile_width = (int)IntegerMath.max(specs.getTileWidth(), TextureSlicerSpecs.MIN_TILE_SIZE);
		final int tile_height = (int)IntegerMath.max(specs.getTileHeight(), TextureSlicerSpecs.MIN_TILE_SIZE);

		final ID namespace = specs.getNameSpacePrefix();
		Debug.checkNull("NameSpacePrefix", namespace);

		File output_folder = specs.getOutputFolder();
		if (output_folder == null) {
			output_folder = input_file.parent();
		}
		output_folder.makeFolder();

		final SlicesCompositionInfo structure = new SlicesCompositionInfo();

		structure.cell_margin = (margin);
		structure.cell_width = (tile_width);
		structure.cell_height = (tile_height);
		final String namespace_string = namespace.toString();
		structure.composition_asset_id_string = (namespace_string);

		L.d("reading", input_file);
		final BufferedImage java_image = ImageAWT.readFromFile(input_file);

		final int image_height = java_image.getHeight();
		final int image_width = java_image.getWidth();

		structure.width = image_width;
		structure.height = image_height;

		L.d("  input", "[" + image_width + " x " + image_height + "]");
		if (image_width == 0 && image_height == 0) {
			Err.reportError("Empty image");
		}

		final int full_rows = image_height / tile_height;
		final int full_columns = image_width / tile_width;

		// L.d("full_rows", full_rows);
		// L.d("full_columns", full_columns);

		final int rest_height = image_height % tile_height;
		final int rest_width = image_width % tile_width;

		final int rest_rows = IntegerMath.index(rest_height > 0);
		final int rest_columns = IntegerMath.index(rest_width > 0);

		// L.d("rest_rows", rest_rows);
		// L.d("rest_columns", rest_columns);

		// L.d("rest_height", rest_height);
		// L.d("rest_width", rest_width);

		final int k = 0;
		for (int j = 0; j < full_rows; j++) {
			for (int i = 0; i < full_columns; i++) {
				final int tile_actual_width = tile_width + margin * 2;
				final int tile_actual_height = tile_height + margin * 2;
				this.process(i, j, tile_width, tile_height, margin, java_image, namespace_string, output_folder, k, structure,
					tile_actual_width, tile_actual_height, result, qualityReductionValue);

			}
		}

		if (rest_rows > 0) {
			final int j = full_rows;
			for (int i = 0; i < full_columns; i++) {
				final int tile_actual_width = tile_width + margin * 2;
				final int tile_actual_height = rest_height + margin * 2;
				this.process(i, j, tile_width, tile_height, margin, java_image, namespace_string, output_folder, k, structure,
					tile_actual_width, tile_actual_height, result, qualityReductionValue);

			}
		}

		if (rest_columns > 0) {
			for (int j = 0; j < full_rows; j++) {
				final int i = full_columns;
				final int tile_actual_width = rest_width + margin * 2;
				final int tile_actual_height = tile_height + margin * 2;
				this.process(i, j, tile_width, tile_height, margin, java_image, namespace_string, output_folder, k, structure,
					tile_actual_width, tile_actual_height, result, qualityReductionValue);
			}
		}
		if (rest_columns > 0 && rest_rows > 0) {
			final int j = full_rows;
			final int i = full_columns;
			final int tile_actual_width = rest_width + margin * 2;
			final int tile_actual_height = rest_height + margin * 2;
			this.process(i, j, tile_width, tile_height, margin, java_image, namespace_string, output_folder, k, structure,
				tile_actual_width, tile_actual_height, result, qualityReductionValue);
		}

		result.setAssetID(namespace);
		result.setStructure(structure);

		return result;
	}

	private void process (final int i, final int j, final int tile_width, final int tile_height, final int margin,
		final BufferedImage java_image, final String namespace, final File output_folder, int k,
		final SlicesCompositionInfo structure, final int tile_actual_width, final int tile_actual_height,
		final RedSlicerResult result, final float qualityReductionValue) throws IOException {
		final int index_top_left_x = i * tile_width;
		final int index_top_left_y = j * tile_height;
		// L.d("dot", "(" + index_top_left_x + ";" + index_top_left_y
		// + ")");
		final int index_bottom_right_x = (1 + i) * tile_width - 1;
		final int index_bottom_right_y = (1 + j) * tile_height - 1;

		final ArrayColorMapSpecs cf_specs = ImageProcessing.newArrayColorMapSpecs();
		cf_specs.setWidth(tile_actual_width);
		cf_specs.setHeight(tile_actual_height);

		final EditableColorMap cf = ImageProcessing.newArrayColorMap(cf_specs);
		final boolean is_empty = this.copy(index_top_left_x, index_top_left_y, index_bottom_right_x, index_bottom_right_y, cf,
			java_image, margin);

		final String signature = this.signTile();
// final BufferedImage java_tile = ImageAWT.toAWTImage(cf);
		final String postfix = "tile-" + signature + "-" + i + "-" + j;
		final String tile_name = namespace + "." + postfix;
		result.addTile(Names.newID(tile_name));

		k++;
		if (!is_empty) {
			final File tile_path = output_folder.child(tile_name + ".png");
			L.d("writing", tile_path);

			this.writeToFile(cf, tile_path, qualityReductionValue);
		} else {
			L.d("dropping empty_raster", postfix);
		}
		structure.addTile(i, j, postfix, is_empty, tile_actual_width, tile_actual_height);

	}

	private String signTile () {
		final long time = Sys.SystemTime().currentTimeMillis();
		final long cut = (time / 1000) * 1000;
		final long last_digits = -cut + time;

		return last_digits + "";
	}

	private void writeToFile (ColorMap image, final File outputFile, final float imageQuality) throws IOException {

// ColorMap image = ImageAWT.readAWTColorMap(file_to_copy);
		if (imageQuality != 1) {
			image = ImageProcessing.scale(image, imageQuality);
		}
// final File outputFile = tiling_folder.child(file_to_copy.getName());
		ImageAWT.writeToFile(image, outputFile, "PNG");

// BufferedImage out;
// if (qualityReductionValue != 1) {
// final Image tmp = ImageAWT.awtScale(java_image, qualityReductionValue);
// out = ImageAWT.toBufferedImage(tmp);
// } else {
// out = java_image;
// }
//
// ImageAWT.writeToFile(out, tile_path, "PNG");
	}

	private boolean copy (final int index_top_left_x, final int index_top_left_y, final int index_bottom_right_x,
		final int index_bottom_right_y, final EditableColorMap cf, final BufferedImage java_image, final int margin) {
		boolean is_empty = true;
		final int offset_x = index_top_left_x - margin;
		final int offset_y = index_top_left_y - margin;
		for (int image_y = offset_y; image_y <= index_bottom_right_y + margin; image_y++) {
			for (int image_x = offset_x; image_x <= index_bottom_right_x + margin; image_x++) {
				// L.d(">> " + x + "," + y);
				Color color_value;
				if (this.within(image_x, image_y, java_image)) {
					final int rgb = java_image.getRGB(image_x, image_y);
					color_value = Colors.newColor().setARGB(rgb);
					if (color_value.alpha() > 0) {
						is_empty = false;
					}
				} else {
					color_value = Colors.newColor(0f, 1f, 0f, 0f);
				}

				final int a = image_x - offset_x;
				final int b = image_y - offset_y;
				cf.setValue(a, b, color_value);
			}
		}
		return is_empty;
	}

	private boolean within (final int image_x, final int image_y, final BufferedImage java_image) {
		if (image_x < 0) {
			return false;
		}
		if (image_y < 0) {
			return false;
		}

		if (image_x >= java_image.getWidth()) {
			return false;
		}
		if (image_y >= java_image.getHeight()) {
			return false;
		}

		return true;
	}
}
