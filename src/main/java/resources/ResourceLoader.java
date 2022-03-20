package main.java.resources;

import main.java.*;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
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

	private static final String GRAPHICS_PATH = "../../../resources/graphics";

	private GraphicsPanel graphicsPanel;
	private Map<String, Image> images = new HashMap<>();

	// FIXME: Remove reference to graphics panel
    public ResourceLoader(GraphicsPanel graphicsPanel) {
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

	public Map<String, Image> loadImages() {
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
			URL resource = this.getClass().getResource("../../../resources/graphics/models.zip");
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

	public void loadTextures() {
		try {
			String path = "../../../resources/graphics/images.zipo";
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
					graphicsPanel.barBottom = this.getImage("BB");
				}
				if (name.equals("bgmain.jpg")) {
					graphicsPanel.bgmain = this.getImage("BGMAIN");
				}
				if (name.equals("bl.gif")) {
					graphicsPanel.barLeft = this.getImage("BL");
				}
				if (name.equals("br.gif")) {
					graphicsPanel.barRight = this.getImage("BR");
				}
				if (name.equals("bt.gif")) {
					graphicsPanel.barTop = this.getImage("BT");
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
					graphicsPanel.selectStage = this.getImage("SELECT");
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

	public void loadStage(GameSparker sparker, Geometry[] stageGeometry, Geometry[] availableGeometry, Medium medium, Trackers trackers, CheckPoints checkPoints, GraphicsPanel graphicsPanel, Madness[] cars, Record record) {
		trackers.nt = 0;
		sparker.nob = 5;
		sparker.notb = 0;
		checkPoints.n = 0;
		checkPoints.nsp = 0;
		checkPoints.fn = 0;
		checkPoints.haltall = false;
		checkPoints.wasted = 0;
		medium.ground = 250;
		sparker.view = 0;
		String string = "";
		try {
			URL resource = this.getClass().getResource("../../../resources/stages/" + checkPoints.stage + ".txt");
			File file = new File(resource.toURI());
			final DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
			String line;
			while ((line = dataInputStream.readLine()) != null) {
				string = line.trim();
				final List<Integer> integerList = Util.getInts(string);
				if (string.startsWith("snap")) {
					medium.setSnapColor(integerList);
				}
				if (string.startsWith("sky")) {
					medium.setSky(integerList);
					graphicsPanel.snap(checkPoints.stage);
				}
				if (string.startsWith("ground")) {
					medium.setGround(integerList);
				}
				if (string.startsWith("fog")) {
					medium.setFade(integerList);
				}
				if (string.startsWith("fadefrom")) {
					medium.fadeFrom(integerList);
					medium.origfade = medium.fade[0];
				}
				if (string.startsWith("set")) {
					final int geometryIndex = integerList.get(0);
					stageGeometry[sparker.nob] = new Geometry(availableGeometry[geometryIndex], integerList.get(1), (int) medium.ground - availableGeometry[geometryIndex].grat, integerList.get(2), integerList.get(3));
					if (string.contains(")p")) {
						checkPoints.x[checkPoints.n] = integerList.get(1);
						checkPoints.z[checkPoints.n] = integerList.get(2);
						checkPoints.y[checkPoints.n] = 0;
						checkPoints.typ[checkPoints.n] = 0;

						//TODO: Checkpoint Types for Bots? Maybe smth better mayking anna
						if (string.contains(")pt")) {
							checkPoints.typ[checkPoints.n] = -1;
						}
						if (string.contains(")pr")) {
							checkPoints.typ[checkPoints.n] = -2;
						}
						if (string.contains(")po")) {
							checkPoints.typ[checkPoints.n] = -3;
						}
						if (string.contains(")ph")) {
							checkPoints.typ[checkPoints.n] = -4;
						}
						++checkPoints.n;
						sparker.notb = sparker.nob + 1;
					}
					++sparker.nob;
				}
				if (string.startsWith("chk")) {
					final int checkpointIndex = integerList.get(0);
					stageGeometry[sparker.nob] = new Geometry(availableGeometry[checkpointIndex], integerList.get(1), (int) medium.ground - availableGeometry[checkpointIndex].grat, integerList.get(2), integerList.get(3));
					checkPoints.x[checkPoints.n] = integerList.get(1);
					checkPoints.z[checkPoints.n] = integerList.get(2);
					checkPoints.y[checkPoints.n] = (int) medium.ground - availableGeometry[checkpointIndex].grat;

					checkPoints.typ[checkPoints.n] = integerList.get(3) == 0 ? 1 : 2;
					checkPoints.pcs = checkPoints.n;

					checkPoints.n++;
					checkPoints.nsp++;
					sparker.nob++;
					sparker.notb = sparker.nob;
				}
				if (string.startsWith("fix")) {
					stageGeometry[sparker.nob] = new Geometry(availableGeometry[integerList.get(0)], integerList.get(1), integerList.get(3), integerList.get(2), integerList.get(4));
					checkPoints.fx[checkPoints.fn] = integerList.get(1);
					checkPoints.fz[checkPoints.fn] = integerList.get(2);
					checkPoints.fy[checkPoints.fn] = integerList.get(3);

					stageGeometry[sparker.nob].elec = true;
					checkPoints.rotated[checkPoints.fn] = integerList.get(4) != 0;
					stageGeometry[sparker.nob].roted = checkPoints.rotated[checkPoints.fn];

					checkPoints.special[checkPoints.fn] = string.contains(")drawShadow");
					++checkPoints.fn;
					++sparker.nob;
					sparker.notb = sparker.nob;
				}
				if (string.startsWith("nlaps")) {
					checkPoints.nlaps = integerList.get(0);
				}
				if (string.startsWith("name")) {
					checkPoints.name = Util.getName(string).replace('|', ',');
				}
				if (string.startsWith("max")) {
					final int angle, sign, xy, zy;
					angle = (string.startsWith("maxr") || string.startsWith("maxl")) ? 0 : 90;
					sign = (string.startsWith("maxr") || string.startsWith("maxt")) ? 1 : -1;

					for (int i = 0; i < integerList.get(0); ++i) {
						int xPos = (string.startsWith("maxr") || string.startsWith("maxl")) ? integerList.get(1) : integerList.get(2) + i * 4800;
						int zPos = (string.startsWith("maxr") || string.startsWith("maxl")) ? integerList.get(2) + i * 4800 : integerList.get(1);
						stageGeometry[sparker.nob] = new Geometry(availableGeometry[39], xPos, (int) medium.ground - availableGeometry[39].grat, zPos, angle);
						++sparker.nob;
					}

					trackers.y[trackers.nt] = -5000;
					trackers.rady[trackers.nt] = 7100;
					trackers.dam[trackers.nt] = 1;

					if (string.startsWith("maxr") || string.contains("maxl")) {
						trackers.x[trackers.nt] = integerList.get(1) + (sign * 500);
						trackers.z[trackers.nt] = integerList.get(0) * 4800 / 2 + integerList.get(2) - 2400;
						trackers.radx[trackers.nt] = 600;
						trackers.radz[trackers.nt] = integerList.get(0) * 4800 / 2;
						trackers.xy[trackers.nt] = sign * 90;
						trackers.zy[trackers.nt] = 0;
					} else {
						trackers.x[trackers.nt] = integerList.get(0) * 4800 / 2 + integerList.get(2) - 2400;
						trackers.z[trackers.nt] = integerList.get(1) + (sign * 500);
						trackers.radx[trackers.nt] = integerList.get(0) * 4800 / 2;
						trackers.radz[trackers.nt] = 600;
						trackers.xy[trackers.nt] = 0;
						trackers.zy[trackers.nt] = sign * 90;
					}

					trackers.y[trackers.nt] = -5000;
					trackers.rady[trackers.nt] = 7100;
					trackers.dam[trackers.nt] = 1;
					++trackers.nt;
				}
			}
			dataInputStream.close();
		} catch (Exception e) {
			graphicsPanel.state = GameState.LOADING_STAGE_FAILED;
			System.out.println("Error in stage " + checkPoints.stage);
			System.out.println("" + e);
			e.printStackTrace();
			System.out.println("At line: " + string);
		}
		if (graphicsPanel.state == GameState.LOADING_STAGE) {
			medium.trx = 0L;
			medium.trz = 0L;
			if (trackers.nt >= 4) {
				int n = 4;
				do {
					medium.trx += trackers.x[trackers.nt - n];
					medium.trz += trackers.z[trackers.nt - n];
				} while (--n > 0);
			}
			medium.trx /= 4L;
			medium.trz /= 4L;
			medium.ptr = 0;
			medium.ptcnt = -10;
			medium.hit = 60000;
			medium.nrnd = 0;
			medium.trk = true;
			graphicsPanel.state = GameState.STAGE_PREVIEW;
			sparker.mouseClick = 0;
		}
		int n2 = 0;
		do {
			sparker.controls[n2].reset(checkPoints);
		} while (++n2 < 5);
		graphicsPanel.resetstat(checkPoints.stage);
		int n3 = 0;
		do {
			stageGeometry[n3] = new Geometry(availableGeometry[graphicsPanel.selectedCar[n3]], graphicsPanel.xstart[n3], 250 - availableGeometry[graphicsPanel.selectedCar[n3]].grat, graphicsPanel.zstart[n3], 0);
			cars[n3].reseto(graphicsPanel.selectedCar[n3], stageGeometry[n3], checkPoints);
		} while (++n3 < 5);
		record.reset(stageGeometry);
	}

	public void loadSoundEffects(GraphicsPanel graphicsPanel) {
		for (int i = 0; i < 5; i++) {
			graphicsPanel.engineSounds[0][i] = getSound("a" + i);
			graphicsPanel.engineSounds[1][i] = getSound("b" + i);
			graphicsPanel.playingEngineSound[i] = false;
			graphicsPanel.airSoundEffects[i] = getSound("air" + i);
		}
		graphicsPanel.airSoundEffects[5] = getSound("air5");

		for (int i = 1; i < 3; i++) {
			graphicsPanel.crashSoundEffects[i] = getSound("crash" + i);
			graphicsPanel.lowCrashSoundEffects[i] = getSound("lowcrash" + i);
		}
		graphicsPanel.tires = getSound("tires");
		graphicsPanel.checkpoint = getSound("checkpoint");
		graphicsPanel.carfixed = getSound("carfixed");
		graphicsPanel.powerup = getSound("powerup");
		graphicsPanel.three = getSound("three");
		graphicsPanel.two = getSound("two");
		graphicsPanel.one = getSound("one");
		graphicsPanel.go = getSound("go");
		graphicsPanel.skidSoundEffects[0] = getSound("skid1");
		graphicsPanel.skidSoundEffects[1] = getSound("skid2");
		graphicsPanel.dustSkidSoundEffects[0] = getSound("dustskid1");
		graphicsPanel.dustSkidSoundEffects[1] = getSound("dustskid2");
		graphicsPanel.wastd = getSound("wasted");
		graphicsPanel.firewasted = getSound("firewasted");

		System.out.println("Loaded Sound Effects");
	}

	AudioClip getSound(final String name) {
		String location = "../../../resources/sounds/default/";
		AudioClip audioClip;
        String fileName = location + name + ".au";
        URL resource = this.getClass().getResource(fileName);
		if (resource == null) {
			System.out.println(fileName);

		}
		audioClip = Applet.newAudioClip(resource);
		return audioClip;
	}

}
