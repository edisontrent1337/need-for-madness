package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.zip.ZipInputStream;

class Util {

	static int clamp(int value, int min, int max) {
		return Math.max(Math.min(value, max), min);
	}
	static ZipInputStream getInputStream(String path, Class callingClass) throws URISyntaxException, FileNotFoundException {
		URL resource = callingClass.getResource(path);
		File file = new File(resource.toURI());
		final FileInputStream in = new FileInputStream(file);
		return new ZipInputStream(in);
	}
}
