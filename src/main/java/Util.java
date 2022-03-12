package main.java;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;


class Util {

	static double clamp(double value, double min, double max) {
		return Math.max(Math.min(value, max), min);
	}

	static int clamp(int value, int min, int max) {
		return Math.max(Math.min(value, max), min);
	}

	static float clamp(float value, float min, float max) {
		return Math.max(Math.min(value, max), min);
	}

	static float clampCol(float value) {
		return clamp(value, 0, 255);
	}

	static int clampCol(int value) {
		return clamp(value, 0, 255);
	}

	static double clampCol(double value) {
		return clamp(value, 0, 255);
	}

	static ZipInputStream getInputStream(String path, Class callingClass) throws URISyntaxException, FileNotFoundException {
		URL resource = callingClass.getResource(path);
		File file = new File(resource.toURI());
		final FileInputStream in = new FileInputStream(file);
		return new ZipInputStream(in);
	}

	static int[] snapRGBs(int rgba, int[] snap) {
		final Color c = new Color(rgba, true);
		int[] snappedRGBs = new int[3];
		snappedRGBs[0] = Util.clampCol(c.getRed() + c.getRed() * (snap[0] / 100));
		snappedRGBs[1] = Util.clampCol(c.getGreen() + c.getGreen() * (snap[1] / 100));
		snappedRGBs[2] = Util.clampCol(c.getBlue() + c.getBlue() * (snap[2] / 100));
		return snappedRGBs;
	}

	static String getName(String lineString) {
		return lineString.substring(5, lineString.length() - 1);
	}

	static List<Integer> getInts(String lineString) {
		if(lineString.contains("(") && lineString.contains(")") && !lineString.contains("name")) {
			String[] strings = lineString.split("[()]");
			return Arrays.stream(strings[1].split(",")).map(Integer::parseInt).collect(Collectors.toList());
		} else {
			return new ArrayList<>();
		}
	}

	static int[] getPixelArray(Image image, ImageObserver imageObserver) {
		final int height = image.getHeight(imageObserver);
		final int width = image.getWidth(imageObserver);
		final int[] array = new int[width * height];
		final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
		try {
			pixelGrabber.grabPixels();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return array;
	}
}
