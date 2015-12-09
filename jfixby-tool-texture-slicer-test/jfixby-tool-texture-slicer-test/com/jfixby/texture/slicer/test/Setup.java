package com.jfixby.texture.slicer.test;

import com.jfixby.cmns.adopted.gdx.json.GdxJson;
import com.jfixby.cmns.api.assets.Names;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.image.ImageProcessing;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.math.FloatMath;
import com.jfixby.cmns.api.math.IntegerMath;
import com.jfixby.cmns.api.md5.MD5;
import com.jfixby.cmns.api.net.http.Http;
import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.cmns.jutils.desktop.DesktopUtils;
import com.jfixby.red.color.RedColors;
import com.jfixby.red.desktop.filesystem.win.WinFileSystem;
import com.jfixby.red.desktop.img.processing.DesktopImageProcessing;
import com.jfixby.red.desktop.log.DesktopLogger;
import com.jfixby.red.desktop.math.DesktopFloatMath;
import com.jfixby.red.desktop.math.RedIntegerMath;
import com.jfixby.red.desktop.net.HttpDesktopComponent;
import com.jfixby.red.desktop.sys.DesktopSystem;
import com.jfixby.red.io.RedIO;
import com.jfixby.red.name.RedAssetsNamespace;
import com.jfixby.red.util.md5.AlpaeroMD5;

public class Setup {

	public static void setup() {
		L.installComponent(new DesktopLogger());
		JUtils.installComponent(new DesktopUtils());
		IO.installComponent(new RedIO());
		IntegerMath.installComponent(new RedIntegerMath());
		MD5.installComponent(new AlpaeroMD5());
		Sys.installComponent(new DesktopSystem());
		LocalFileSystem.installComponent(new WinFileSystem());
		Http.installComponent(new HttpDesktopComponent());
		FloatMath.installComponent(new DesktopFloatMath());
		Names.installComponent(new RedAssetsNamespace());
		ImageProcessing.installComponent(new DesktopImageProcessing());
		Colors.installComponent(new RedColors());
		Json.installComponent(new GdxJson());
	}

}
