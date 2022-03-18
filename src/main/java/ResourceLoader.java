package main.java;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static main.java.Config.modelNames;

public class ResourceLoader {

	private static final String GRAPHICS_PATH = "../../resources/graphics";

	private GraphicsPanel graphicsPanel;
	private Map<String, Image> images = new HashMap<>();

	// FIXME: Remove reference to graphics panel
	ResourceLoader(GraphicsPanel graphicsPanel) {
		this.graphicsPanel = graphicsPanel;
	}

	Image getImage(String imageName) {
		if (!images.containsKey(imageName)) {
			System.err.println("Error: The image by the name " + imageName + " was not found.");
			return null;
		}
		return images.get(imageName);
	}

	public Map<String, Image> getImages() {
		return images;
	}

	Map<String, Image> loadImages() {
		URI uri = null;
		try {
			uri = ResourceLoader.class.getResource(GRAPHICS_PATH).toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String graphicsDirectoryPath = Paths.get(uri).toString();
		Path graphicsDirectory = Paths.get(graphicsDirectoryPath);
		try (Stream<Path> fileStream = Files.walk(graphicsDirectory)) {
			List<Path> imageFilePaths = fileStream
					.filter(Files::isRegularFile)
					.collect(Collectors.toList());
			for (Path path : imageFilePaths) {
				loadImage(path);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Loaded images.");
		return images;
	}

	/**
	 * Loads an image that is located on the given path and adds it to the textureMap.
	 *
	 * @param path path
	 */

	private void loadImage(Path path) {
		BufferedImage image;
		try {
			image = ImageIO.read(path.toFile());
			String pathName = path.getFileName().toString();
			if (pathName.contains(".")) {
				String resourceName = pathName.split("\\.")[0].toUpperCase().replace("-", "_");
				images.put(resourceName, image);
			} else {
				System.err.println("The file " + pathName + " has no extension and was therefore skipped.");
			}
		} catch (IOException e) {
			System.err.println("Error loading images: " + e);
			e.printStackTrace();
		}
	}

	public Geometry[] loadModels(Medium medium, Trackers trackers) {
		Geometry[] availableGeometry = new Geometry[modelNames.length];
		try {
			URL resource = this.getClass().getResource("../../resources/graphics/models.zip");
			File file = new File(resource.toURI());
			final FileInputStream inputStream = new FileInputStream(file);
			final ZipInputStream zipStream = new ZipInputStream(inputStream);

			for (ZipEntry entry = zipStream.getNextEntry(); entry != null; entry = zipStream.getNextEntry()) {
				int geometryIndex = Util.getGeometryIndex(entry);
				final byte[] modelBytes = Util.readZipEntryBytes(entry, zipStream);
				availableGeometry[geometryIndex] = new Geometry(modelBytes, medium, trackers);
			}

			inputStream.close();
			zipStream.close();
		} catch (Exception e) {
			System.out.println("Error Reading Models: " + e);
		}
		return availableGeometry;
	}

	void loadTextures() {
		try {
			String path = "../../resources/graphics/images.zipo";
			ZipInputStream zipInputStream = Util.getInputStream(path, this.getClass());
			for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
				int i = (int) zipEntry.getSize();
				final String name = zipEntry.getName();
				final byte[] bytes = new byte[i];
				int off = 0;
				while (i > 0) {
					final int read = zipInputStream.read(bytes, off, i);
					off += read;
					i -= read;
				}
				if (name.equals("0c.gif")) {
					graphicsPanel.countDownImages[0] = this.getImage("0C");
				}
				if (name.equals("1.gif")) {
					graphicsPanel.orank[0] = this.getImage("1");
				}
				if (name.equals("1c.gif")) {
					graphicsPanel.countDownImages[1] = this.getImage("1C");
				}
				if (name.equals("2.gif")) {
					graphicsPanel.orank[1] = this.getImage("2");
				}
				if (name.equals("2c.gif")) {
					graphicsPanel.countDownImages[2] = this.getImage("2C");
				}
				if (name.equals("3.gif")) {
					graphicsPanel.orank[2] = this.getImage("3");
				}
				if (name.equals("3c.gif")) {
					graphicsPanel.countDownImages[3] = this.getImage("3C");
				}
				if (name.equals("4.gif")) {
					graphicsPanel.orank[3] = this.getImage("4");
				}
				if (name.equals("5.gif")) {
					graphicsPanel.orank[4] = this.getImage("5");
				}
				if (name.equals("back.gif")) {
					graphicsPanel.back[0] = this.getImage("BACK");
				}
				if (name.equals("bb.gif")) {
					graphicsPanel.bb = this.getImage("BB");
				}
				if (name.equals("bgmain.jpg")) {
					graphicsPanel.bgmain = this.getImage("BGMAIN");
				}
				if (name.equals("bl.gif")) {
					graphicsPanel.bl = this.getImage("BL");
				}
				if (name.equals("br.gif")) {
					graphicsPanel.br = this.getImage("BR");
				}
				if (name.equals("bt.gif")) {
					graphicsPanel.bt = this.getImage("BT");
				}
				if (name.equals("cars.gif")) {
					// FIXME This is an example of how the new API might be used in the future.
					// FIXME Of course, you should NOT do the assignment here as graphicsPanel has to be removed from
					// FIXME this class altogether.
					graphicsPanel.carsbg = this.getImage("CARS");
				}
				if (name.equals("congrad.gif")) {
					graphicsPanel.congrd = this.getImage("CONGRAD");
				}
				if (name.equals("continue1.gif")) {
					graphicsPanel.contin1[0] = this.getImage("CONTINUE1");
				}
				if (name.equals("continue2.gif")) {
					graphicsPanel.contin2[0] = this.getImage("CONTINUE2");
				}
				if (name.equals("damage.gif")) {
					graphicsPanel.odmg = this.getImage("DAMAGE");
				}
				if (name.equals("gameh.gif")) {
					graphicsPanel.ogameh = this.getImage("GAMEH");
				}
				if (name.equals("gameov.gif")) {
					graphicsPanel.gameov = this.getImage("GAMEOV");
				}
				if (name.equals("inst1.gif")) {
					graphicsPanel.inst1 = this.getImage("INST1");
				}
				if (name.equals("inst2.gif")) {
					graphicsPanel.inst2 = this.getImage("INST2");
				}
				if (name.equals("inst3.gif")) {
					graphicsPanel.inst3 = this.getImage("INST3");
				}
				if (name.equals("lap.gif")) {
					graphicsPanel.olap = this.getImage("LAP");
				}
				if (name.equals("loadingmusic.gif")) {
					graphicsPanel.oloadingmusic = this.getImage("LOADINGMUSIC");
				}
				if (name.equals("madness.gif")) {
					graphicsPanel.omdness = this.getImage("MADNESS");
				}
				if (name.equals("main.gif")) {
					graphicsPanel.maini = this.getImage("MAIN");
				}
				if (name.equals("next.gif")) {
					graphicsPanel.next[0] = this.getImage("NEXT");
				}
				if (name.equals("nfmcom.gif")) {
					graphicsPanel.nfmcom = this.getImage("NFMCOM");
				}
				if (name.equals("options.gif")) {
					graphicsPanel.opti = this.getImage("OPTIONS");
				}
				if (name.equals("paused.gif")) {
					graphicsPanel.paused = this.getImage("PAUSED");
				}
				if (name.equals("pgate.gif")) {
					graphicsPanel.pgate = this.getImage("PGATE");
				}
				if (name.equals("position.gif")) {
					graphicsPanel.opos = this.getImage("POSITION");
				}
				if (name.equals("power.gif")) {
					graphicsPanel.opwr = this.getImage("POWER");
				}
				if (name.equals("radicalplay.gif")) {
					graphicsPanel.radicalplay = this.getImage("RADICALPLAY");
				}
				if (name.equals("rpro.gif")) {
					graphicsPanel.rpro = this.getImage("RPRO");
				}
				if (name.equals("select.gif")) {
					graphicsPanel.select = this.getImage("SELECT");
				}
				if (name.equals("selectcar.gif")) {
					graphicsPanel.selectcar = this.getImage("SELECTCAR");
				}
				if (name.equals("stages.jpg")) {
					graphicsPanel.trackbg = this.getImage("STAGES");
				}
				if (name.equals("start1.gif")) {
					graphicsPanel.ostar[0] = this.getImage("START1");
				}
				if (name.equals("start2.gif")) {
					graphicsPanel.ostar[1] = this.getImage("START2");
				}
				if (name.equals("statb.gif")) {
					graphicsPanel.statb = this.getImage("STATB");
				}
				if (name.equals("wasted.gif")) {
					graphicsPanel.owas = this.getImage("WASTED");
				}
				if (name.equals("youlost.gif")) {
					graphicsPanel.oyoulost = this.getImage("YOULOST");
				}
				if (name.equals("yourwasted.gif")) {
					graphicsPanel.oyourwasted = this.getImage("YOURWASTED");
				}
				if (name.equals("youwastedem.gif")) {
					graphicsPanel.oyouwastedem = this.getImage("YOUWASTEDEM");
				}
				if (name.equals("youwon.gif")) {
					graphicsPanel.oyouwon = this.getImage("YOUWON");
				}
			}
			zipInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Loading Images");
		}
	}

	public void loadStage() {

	}

}
