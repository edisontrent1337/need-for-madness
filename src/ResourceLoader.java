import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.List;

public class ResourceLoader {

	private static final String GRAPHICS_PATH = "resources/graphics";

	private MediaTracker mediaTracker;
	Toolkit defaultToolKit = Toolkit.getDefaultToolkit();
	GraphicsPanel graphicsPanel;

/*    Image[] ocntdn = new Image[4];
    Image[] orank = new Image[5];
    Image[] back = new Image[3];
    Image bb;
    Image bgmain;
    Image bl, br, bt;
    Image carsbg;
    Image congrd;
    Image[] contin1 = new Image[2];
    Image[] contin2 = new Image[2];
    Image odmg;
    Image ogameh;
    Image gameov;
    Image inst1, inst2, inst3;
    Image olap;
    Image oloadingmusic;
    Image omdness;
    Image maini;
    Image[] next = new Image[3];
    Image nfmcom;
    Image opti;
    Image paused;
    Image pgate;
    Image opos, opwr;
    Image radicalplay;
    Image rpro;
    Image select;
    Image selectcar;
    Image trackbg;
    Image[] ostar = new Image[2];
    Image statb;
    Image owas;
    Image oyoulost;
    Image oyourwasted;
    Image oyouwastedem;
    Image oyouwon;*/


	private Map<String, Image> textureMap = new HashMap<>();

	ResourceLoader(Component app, GraphicsPanel graphicsPanel) {
		this.mediaTracker = new MediaTracker(app);
		this.graphicsPanel = graphicsPanel;
	}


	Image getImage(String textureName) {

		if (!textureMap.containsKey(textureName)) {
			System.err.println("Error: The image by the name " + textureName + " was not found.");
		}

		return textureMap.get(textureName);
	}

	void loadResources() {
		Path graphicsDirectory = Paths.get(ResourceLoader.class.getResource(GRAPHICS_PATH).getPath());
		try (Stream<Path> walk = Files.walk(graphicsDirectory)) {
			List<Path> result = walk.filter(Files::isRegularFile).collect(Collectors.toList());
			result.forEach(System.out::println);
			for (Path p : result) {
				loadTexture(p);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void loadTexture(Path path) {
		BufferedImage img;
		try {
			img = ImageIO.read(path.toFile());
			String resourceName = path.getFileName().toString().split("\\.")[0].toUpperCase();
			System.out.println(resourceName);
			textureMap.put(resourceName, img);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void loadTextures() {
		try {
			String path = "resources/graphics/images.zipo";
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
					graphicsPanel.ocntdn[0] = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("1.gif")) {
					graphicsPanel.orank[0] = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("1c.gif")) {
					graphicsPanel.ocntdn[1] = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("2.gif")) {
					graphicsPanel.orank[1] = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("2c.gif")) {
					graphicsPanel.ocntdn[2] = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("3.gif")) {
					graphicsPanel.orank[2] = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("3c.gif")) {
					graphicsPanel.ocntdn[3] = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("4.gif")) {
					graphicsPanel.orank[3] = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("5.gif")) {
					graphicsPanel.orank[4] = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("back.gif")) {
					graphicsPanel.back[0] = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("bb.gif")) {
					graphicsPanel.bb = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("bgmain.jpg")) {
					graphicsPanel.bgmain = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("bl.gif")) {
					graphicsPanel.bl = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("br.gif")) {
					graphicsPanel.br = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("bt.gif")) {
					graphicsPanel.bt = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("cars.gif")) {
					graphicsPanel.carsbg = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("congrad.gif")) {
					graphicsPanel.congrd = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("continue1.gif")) {
					graphicsPanel.contin1[0] = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("continue2.gif")) {
					graphicsPanel.contin2[0] = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("damage.gif")) {
					graphicsPanel.odmg = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("gameh.gif")) {
					graphicsPanel.ogameh = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("gameov.gif")) {
					graphicsPanel.gameov = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("inst1.gif")) {
					graphicsPanel.inst1 = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("inst2.gif")) {
					graphicsPanel.inst2 = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("inst3.gif")) {
					graphicsPanel.inst3 = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("lap.gif")) {
					graphicsPanel.olap = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("loadingmusic.gif")) {
					graphicsPanel.oloadingmusic = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("madness.gif")) {
					graphicsPanel.omdness = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("main.gif")) {
					graphicsPanel.maini = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("next.gif")) {
					graphicsPanel.next[0] = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("nfmcom.gif")) {
					graphicsPanel.nfmcom = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("options.gif")) {
					graphicsPanel.opti = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("paused.gif")) {
					graphicsPanel.paused = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("pgate.gif")) {
					graphicsPanel.pgate = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("position.gif")) {
					graphicsPanel.opos = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("power.gif")) {
					graphicsPanel.opwr = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("radicalplay.gif")) {
					graphicsPanel.radicalplay = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("rpro.gif")) {
					graphicsPanel.rpro = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("select.gif")) {
					graphicsPanel.select = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("selectcar.gif")) {
					graphicsPanel.selectcar = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("stages.jpg")) {
					graphicsPanel.trackbg = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("start1.gif")) {
					graphicsPanel.ostar[0] = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("start2.gif")) {
					graphicsPanel.ostar[1] = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("statb.gif")) {
					graphicsPanel.statb = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("wasted.gif")) {
					graphicsPanel.owas = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("youlost.gif")) {
					graphicsPanel.oyoulost = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("yourwasted.gif")) {
					graphicsPanel.oyourwasted = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("youwastedem.gif")) {
					graphicsPanel.oyouwastedem = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
				if (name.equals("youwon.gif")) {
					graphicsPanel.oyouwon = this.loadImage(bytes, mediaTracker, defaultToolKit);
				}
			}
			zipInputStream.close();
		} catch (Exception e) {
			System.out.println("Error Loading Images");
		}
	}

	private Image loadImage(final byte[] imagedata, final MediaTracker mediaTracker, final Toolkit toolkit) {
		final Image image = toolkit.createImage(imagedata);
		mediaTracker.addImage(image, 0);
		try {
			mediaTracker.waitForID(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}


		return image;
	}

}
