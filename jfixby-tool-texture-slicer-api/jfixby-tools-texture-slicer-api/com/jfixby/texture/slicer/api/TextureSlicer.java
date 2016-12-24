
package com.jfixby.texture.slicer.api;

import java.io.IOException;

import com.jfixby.scarabei.api.ComponentInstaller;

public class TextureSlicer {

	static private ComponentInstaller<TextureSlicerComponent> componentInstaller = new ComponentInstaller<TextureSlicerComponent>(
		"TextureSlicer");

	public static final void installComponent (final TextureSlicerComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final TextureSlicerComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final TextureSlicerComponent component () {
		return componentInstaller.getComponent();
	}

	public static TextureSlicerSpecs newDecompositionSpecs () {
		return invoke().newDecompositionSpecs();
	}

	public static TextureSlicingResult decompose (final TextureSlicerSpecs specs) throws IOException {
		return invoke().decompose(specs);
	}
}
