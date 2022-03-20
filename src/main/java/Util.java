package main.java;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static main.java.Config.*;


public class Util {

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

    public static ZipInputStream getInputStream(String path, Class callingClass) throws URISyntaxException, FileNotFoundException {
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

    public static String getName(String lineString) {
        return lineString.substring(5, lineString.length() - 1);
    }

    public static List<Integer> getInts(String lineString) {
        if (lineString.contains("(") && lineString.contains(")") && !lineString.contains("name")) {
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

    public static byte[] readZipEntryBytes(ZipEntry zipEntry, ZipInputStream stream) throws IOException {
        int size = (int) zipEntry.getSize();
        final byte[] bytes = new byte[size];
        int offset = 0;
        while (size > 0) {
            int read = stream.read(bytes, offset, size);
            offset += read;
            size -= read;
        }
        return bytes;
    }

    public static int getGeometryIndex(ZipEntry zipEntry) {
        for (int i = 0; i < modelNames.length; i++) {
            if (zipEntry.getName().startsWith(modelNames[i] + ".rad")) return i;
        }
        return -1;
    }
}
