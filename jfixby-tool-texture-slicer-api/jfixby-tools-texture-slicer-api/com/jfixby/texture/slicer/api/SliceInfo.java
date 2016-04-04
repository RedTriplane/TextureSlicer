package com.jfixby.texture.slicer.api;

import java.io.Serializable;

public class SliceInfo implements Serializable{

	public double tile_width;
	public double tile_height;
	public int index_x;
	public int index_y;
	public String postfix;
	public boolean is_empty = false;
}