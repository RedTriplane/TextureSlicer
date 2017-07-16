
package com.jfixby.texture.slicer.api.io;

import java.io.Serializable;
import java.util.ArrayList;

public class SlicesCompositionsContainer implements Serializable {

	public final String PACKAGE_FORMAT = "RedTriplane.TiledRaster";

	private static final long serialVersionUID = 6944710849959772300L;
	public ArrayList<SlicesCompositionInfo> content = new ArrayList<SlicesCompositionInfo>();

}
