
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//
// Decompiled by Procyon v0.5.36
//

public class GameSparker extends Applet implements Runnable, MouseListener, KeyListener {
	Graphics graphics;
	Image offImage;
	Thread gamer;
	Control[] controls;
	int mouses;
	int xm;
	int ym;
	boolean lostfcs;
	boolean exwist;
	int nob;
	int notb;
	int view;


   /* public void testlocation() {
        try {
            final JSObject window = JSObject.getWindow(this);
            boolean b = false;
            window.eval("var sti=''+top.location;");
            final String value = String.valueOf(String.valueOf(window.getMember("sti")));
            int i = 0;
            if (value.startsWith("http:/")) {
                while (i < value.length() - 1) {
                    if (value.startsWith("freearcade.com", i)) {
                        b = true;
                    }
                    ++i;
                }
            }
            else {
                b = true;
            }
            if (!b) {
                this.rd.setColor(new Color(0, 0, 0));
                this.rd.fillRect(0, 0, 550, 400);
                this.rd.setColor(new Color(255, 255, 255));
                this.rd.drawString("Access Denied !", 30, 50);
                this.rd.drawString("This game will not run under this loaction:", 30, 100);
                this.rd.drawString("" + value, 30, 120);
                this.rd.drawString("Please contact Radicalplay.com for details.", 30, 200);
                this.repaint();
                window.eval("window.open('http://www.radicalplay.com/madness/','olen','menubar=1,toolbar=1,location=1,resizable=1,status=1,scrollbars=1');");
                this.gamer.stop();
            }
        }
        catch (Exception ex) {
            boolean b2 = false;
            final String string = "" + this.getDocumentBase();
            int j = 0;
            if (string.startsWith("http:/")) {
                while (j < string.length() - 1) {
                    if (string.startsWith("freearcade.com", j)) {
                        b2 = true;
                    }
                    ++j;
                }
            }
            else {
                b2 = true;
            }
            if (!b2) {
                this.rd.setColor(new Color(0, 0, 0));
                this.rd.fillRect(0, 0, 550, 400);
                this.rd.setColor(new Color(255, 255, 255));
                this.rd.drawString("Access Denied !", 30, 50);
                this.rd.drawString("This game will not run under this loaction:", 30, 100);
                this.rd.drawString("" + string, 30, 120);
                this.rd.drawString("Please contact Radicalplay.com for details.", 30, 200);
                this.repaint();
                try {
                    this.getAppletContext().showDocument(new URL("http://www.radicalplay.com/madness/"), "olen");
                }
                catch (Exception ex2) {}
                this.gamer.stop();
            }
        }
    }*/

	public void stop() {
		if (this.exwist && this.gamer != null) {
			this.gamer.stop();
			this.gamer = null;
		}
		this.exwist = true;
	}

	public boolean lostFocus(final Event event, final Object o) {
		if (!this.exwist) {
			this.lostfcs = true;
			this.mouses = 0;
			this.controls[0].falseo();
		}
		return false;
	}

	public String getString(final String s, final String s2, final int n) {
		int n2 = 0;
		StringBuilder string = new StringBuilder();
		for (int i = s.length() + 1; i < s2.length(); ++i) {
			final String string2 = "" + s2.charAt(i);
			if (string2.equals(",") || string2.equals(")")) {
				++n2;
				++i;
			}
			if (n2 == n) {
				string.append(s2.charAt(i));
			}
		}
		return string.toString();
	}

	public int getInt(final String s, final String s2, final int n) {
		return Integer.valueOf(getString(s, s2, n));
/*		int n2 = 0;
		String string = "";
		for (int i = drawShadow.length() + 1; i < s2.length(); ++i) {
			final String string2 = "" + s2.charAt(i);
			if (string2.equals(",") || string2.equals(")")) {
				++n2;
				++i;
			}
			if (n2 == n) {
				string += s2.charAt(i);
			}
		}
		return Integer.valueOf(string);*/
	}

    /*public int readcookie(final String str) {
        int intValue = -1;
        try {
            final JSObject window = JSObject.getWindow(this);
            window.eval("drawShadow=GetCookie('" + str + "');");
            intValue = Integer.valueOf(String.valueOf(String.valueOf(window.getMember("drawShadow"))));
        }
        catch (Exception ex) {}
        return intValue;
    }*/

	public void paint(final Graphics graphics) {
		graphics.drawImage(this.offImage, 0, 0, this);
	}

	public GameSparker() {
		this.controls = new Control[5];
		this.mouses = 0;
		this.xm = 0;
		this.ym = 0;
		this.lostfcs = false;
		this.exwist = true;
		this.nob = 0;
		this.notb = 0;
		this.view = 0;
		addMouseListener(this);
		addKeyListener(this);
	}

	public void loadBase(final Geometry[] array, final Medium medium, final Trackers trackers) {
		final String[] array2 = {"2000tornados", "formula7", "canyenaro", "lescrab", "nimi", "maxrevenge", "leadoxide", "king", "radicalone", "drmonster", "road", "froad", "twister2", "twister1", "turn", "offroad", "bumproad", "offturn", "nroad", "nturn", "roblend", "noblend", "rnblend", "roadend", "offroadend", "hpground", "ramp30", "cramp35", "dramp15", "dhilo15", "slide10", "takeoff", "sramp22", "offbump", "offramp", "sofframp", "halfpipe", "spikes", "rail", "thewall", "checkpoint", "fixpoint", "offcheckpoint"};
		try {
			//final DataInputStream in = new DataInputStream(new URL(this.getCodeBase(), "graphics/models.radq").openStream());
			//final ZipInputStream zipInputStream = new ZipInputStream(in);

			URL resource = this.getClass().getResource("resources/graphics/models.zipo");
			File file = new File(resource.toURI());
			final FileInputStream in = new FileInputStream(file);
			final ZipInputStream zipInputStream = new ZipInputStream(in);

			for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
				int n = 0;
				int n2 = 0;
				do {
					if (zipEntry.getName().startsWith(array2[n2])) {
						n = n2;
					}
				} while (++n2 < 43);
				int i = (int) zipEntry.getSize();
				final byte[] b = new byte[i];
				int off = 0;
				while (i > 0) {
					final int read = zipInputStream.read(b, off, i);
					off += read;
					i -= read;
				}
				array[n] = new Geometry(b, medium, trackers);
			}
			in.close();
			zipInputStream.close();
		} catch (Exception obj) {
			System.out.println("Error Reading Models: " + obj);
		}
	}

	public void update(final Graphics graphics) {
		this.paint(graphics);
	}

	public int sunytyp() {
		final String property = System.getProperty("java.version");
		if (!("" + this.getAppletContext()).startsWith("sun.")) {
			return 0;
		}
		if (property.startsWith("1.3")) {
			return 1;
		}
		return 2;
	}

	public void start() {
		if (this.gamer == null) {
			this.gamer = new Thread(this);
		}
		this.gamer.start();
	}

	public void loadStage(final Geometry[] array, final Geometry[] array2, final Medium medium, final Trackers trackers, final CheckPoints checkPoints, final xtGraphics xtGraphics, final Madness[] array3, final Record record) {
		trackers.nt = 0;
		this.nob = 5;
		this.notb = 0;
		checkPoints.n = 0;
		checkPoints.nsp = 0;
		checkPoints.fn = 0;
		checkPoints.haltall = false;
		checkPoints.wasted = 0;
		medium.ground = 250;
		this.view = 0;
		String string = "";
		try {
			URL resource = this.getClass().getResource("resources/stages/" + checkPoints.stage + ".txt");
			File file = new File(resource.toURI());
			final DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));

			String line;
			while ((line = dataInputStream.readLine()) != null) {
				string = "" + line.trim();
				if (string.startsWith("snap")) {
					medium.setSnap(this.getInt("snap", string, 0), this.getInt("snap", string, 1), this.getInt("snap", string, 2));
				}
				if (string.startsWith("sky")) {
					medium.setSky(this.getInt("sky", string, 0), this.getInt("sky", string, 1), this.getInt("sky", string, 2));
					xtGraphics.snap(checkPoints.stage);
				}
				if (string.startsWith("ground")) {
					medium.setGround(this.getInt("ground", string, 0), this.getInt("ground", string, 1), this.getInt("ground", string, 2));
				}
				if (string.startsWith("fog")) {
					medium.setFade(this.getInt("fog", string, 0), this.getInt("fog", string, 1), this.getInt("fog", string, 2));
				}
				if (string.startsWith("fadefrom")) {
					medium.fadeFrom(this.getInt("fadefrom", string, 0));
					medium.origfade = medium.fade[0];
				}
				if (string.startsWith("set")) {
					final int getint = this.getInt("set", string, 0);
					array[this.nob] = new Geometry(array2[getint], this.getInt("set", string, 1), (int) medium.ground - array2[getint].grat, this.getInt("set", string, 2), this.getInt("set", string, 3));
					if (string.contains(")p")) {
						checkPoints.x[checkPoints.n] = this.getInt("chk", string, 1);
						checkPoints.z[checkPoints.n] = this.getInt("chk", string, 2);
						checkPoints.y[checkPoints.n] = 0;
						checkPoints.typ[checkPoints.n] = 0;
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
						this.notb = this.nob + 1;
					}
					++this.nob;
				}
				if (string.startsWith("chk")) {
					final int getint2 = this.getInt("chk", string, 0);
					array[this.nob] = new Geometry(array2[getint2], this.getInt("chk", string, 1), (int) medium.ground - array2[getint2].grat, this.getInt("chk", string, 2), this.getInt("chk", string, 3));
					checkPoints.x[checkPoints.n] = this.getInt("chk", string, 1);
					checkPoints.z[checkPoints.n] = this.getInt("chk", string, 2);
					checkPoints.y[checkPoints.n] = (int) medium.ground - array2[getint2].grat;
					if (this.getInt("chk", string, 3) == 0) {
						checkPoints.typ[checkPoints.n] = 1;
					} else {
						checkPoints.typ[checkPoints.n] = 2;
					}
					checkPoints.pcs = checkPoints.n;
					++checkPoints.n;
					++checkPoints.nsp;
					++this.nob;
					this.notb = this.nob;
				}
				if (string.startsWith("fix")) {
					array[this.nob] = new Geometry(array2[this.getInt("fix", string, 0)], this.getInt("fix", string, 1), this.getInt("fix", string, 3), this.getInt("fix", string, 2), this.getInt("fix", string, 4));
					checkPoints.fx[checkPoints.fn] = this.getInt("fix", string, 1);
					checkPoints.fz[checkPoints.fn] = this.getInt("fix", string, 2);
					checkPoints.fy[checkPoints.fn] = this.getInt("fix", string, 3);
					array[this.nob].elec = true;
					if (this.getInt("fix", string, 4) != 0) {
						checkPoints.roted[checkPoints.fn] = true;
						array[this.nob].roted = true;
					} else {
						checkPoints.roted[checkPoints.fn] = false;
					}
					if (string.contains(")drawShadow")) {
						checkPoints.special[checkPoints.fn] = true;
					} else {
						checkPoints.special[checkPoints.fn] = false;
					}
					++checkPoints.fn;
					++this.nob;
					this.notb = this.nob;
				}
				if (string.startsWith("nlaps")) {
					checkPoints.nlaps = this.getInt("nlaps", string, 0);
				}
				if (string.startsWith("name")) {
					checkPoints.name = this.getString("name", string, 0).replace('|', ',');
				}
				if (string.startsWith("maxr")) {
					final int getint3 = this.getInt("maxr", string, 0);
					final int getint4 = this.getInt("maxr", string, 1);
					final int getint5 = this.getInt("maxr", string, 2);
					for (int i = 0; i < getint3; ++i) {
						array[this.nob] = new Geometry(array2[39], getint4, (int) medium.ground - array2[39].grat, i * 4800 + getint5, 0);
						++this.nob;
					}
					trackers.y[trackers.nt] = -5000;
					trackers.rady[trackers.nt] = 7100;
					trackers.x[trackers.nt] = getint4 + 500;
					trackers.radx[trackers.nt] = 600;
					trackers.z[trackers.nt] = getint3 * 4800 / 2 + getint5 - 2400;
					trackers.radz[trackers.nt] = getint3 * 4800 / 2;
					trackers.xy[trackers.nt] = 90;
					trackers.zy[trackers.nt] = 0;
					trackers.dam[trackers.nt] = 1;
					++trackers.nt;
				}
				if (string.startsWith("maxl")) {
					final int getint6 = this.getInt("maxl", string, 0);
					final int getint7 = this.getInt("maxl", string, 1);
					final int getint8 = this.getInt("maxl", string, 2);
					for (int j = 0; j < getint6; ++j) {
						array[this.nob] = new Geometry(array2[39], getint7, (int) medium.ground - array2[39].grat, j * 4800 + getint8, 0);
						++this.nob;
					}
					trackers.y[trackers.nt] = -5000;
					trackers.rady[trackers.nt] = 7100;
					trackers.x[trackers.nt] = getint7 - 500;
					trackers.radx[trackers.nt] = 600;
					trackers.z[trackers.nt] = getint6 * 4800 / 2 + getint8 - 2400;
					trackers.radz[trackers.nt] = getint6 * 4800 / 2;
					trackers.xy[trackers.nt] = -90;
					trackers.zy[trackers.nt] = 0;
					trackers.dam[trackers.nt] = 1;
					++trackers.nt;
				}
				if (string.startsWith("maxt")) {
					final int getint9 = this.getInt("maxt", string, 0);
					final int getint10 = this.getInt("maxt", string, 1);
					final int getint11 = this.getInt("maxt", string, 2);
					for (int k = 0; k < getint9; ++k) {
						array[this.nob] = new Geometry(array2[39], k * 4800 + getint11, (int) medium.ground - array2[39].grat, getint10, 90);
						++this.nob;
					}
					trackers.y[trackers.nt] = -5000;
					trackers.rady[trackers.nt] = 7100;
					trackers.z[trackers.nt] = getint10 + 500;
					trackers.radz[trackers.nt] = 600;
					trackers.x[trackers.nt] = getint9 * 4800 / 2 + getint11 - 2400;
					trackers.radx[trackers.nt] = getint9 * 4800 / 2;
					trackers.zy[trackers.nt] = 90;
					trackers.xy[trackers.nt] = 0;
					trackers.dam[trackers.nt] = 1;
					++trackers.nt;
				}
				if (string.startsWith("maxb")) {
					final int getint12 = this.getInt("maxb", string, 0);
					final int getint13 = this.getInt("maxb", string, 1);
					final int getint14 = this.getInt("maxb", string, 2);
					for (int l = 0; l < getint12; ++l) {
						array[this.nob] = new Geometry(array2[39], l * 4800 + getint14, (int) medium.ground - array2[39].grat, getint13, 90);
						++this.nob;
					}
					trackers.y[trackers.nt] = -5000;
					trackers.rady[trackers.nt] = 7100;
					trackers.z[trackers.nt] = getint13 - 500;
					trackers.radz[trackers.nt] = 600;
					trackers.x[trackers.nt] = getint12 * 4800 / 2 + getint14 - 2400;
					trackers.radx[trackers.nt] = getint12 * 4800 / 2;
					trackers.zy[trackers.nt] = -90;
					trackers.xy[trackers.nt] = 0;
					trackers.dam[trackers.nt] = 1;
					++trackers.nt;
				}
			}
			dataInputStream.close();
		} catch (Exception obj) {
			xtGraphics.fase = 3;
			System.out.println("Error in stage " + checkPoints.stage);
			System.out.println("" + obj);
			System.out.println("At line: " + string);
		}
		if (xtGraphics.fase == 2) {
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
			xtGraphics.fase = 1;
			this.mouses = 0;
		}
		int n2 = 0;
		do {
			this.controls[n2].reset(checkPoints);
		} while (++n2 < 5);
		xtGraphics.resetstat(checkPoints.stage);
		int n3 = 0;
		do {
			array[n3] = new Geometry(array2[xtGraphics.sc[n3]], xtGraphics.xstart[n3], 250 - array2[xtGraphics.sc[n3]].grat, xtGraphics.zstart[n3], 0);
			array3[n3].reseto(xtGraphics.sc[n3], array[n3], checkPoints);
		} while (++n3 < 5);
		record.reset(array);
	}

	public void run() {
		this.graphics.setColor(new Color(0, 0, 0));
		this.graphics.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		this.repaint();
		// this.testlocation();
		final Medium medium = new Medium();
		int n = 5;
		final int sunytyp = this.sunytyp();
		if (sunytyp != 0) {
			n = 15;
		}
		final Trackers trackers = new Trackers();
		final CheckPoints checkPoints = new CheckPoints();
		xtGraphics xtGraphics = null;
		try {
			xtGraphics = new xtGraphics(medium, this.graphics, this, sunytyp);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		final Record record = new Record(medium);
		final Geometry[] array = new Geometry[43];
		this.loadBase(array, medium, trackers);
		final xtGraphics xtGraphics2 = xtGraphics;
		xtGraphics2.dnload += 29;
		xtGraphics.loading(this.graphics, this);
		final Geometry[] geometries = new Geometry[210];
		final Madness[] cars = new Madness[5];
		int n2 = 0;
		do {
			cars[n2] = new Madness(medium, record, xtGraphics, n2);
			this.controls[n2] = new Control(medium);
		} while (++n2 < 5);
		float n3 = 35.0f;
		int n4 = 80;
		xtGraphics.unlocked = 11;
		// final int readcookie = this.readcookie("unlocked");
        /*if (readcookie >= 1 && readcookie <= 11) {
            xtGraphics.unlocked = readcookie;
            if (xtGraphics.unlocked != 11) {
                checkPoints.stage = xtGraphics.unlocked;
            }
            else {
                checkPoints.stage = (int)(Math.random() * 11.0) + 1;
            }
            xtGraphics.opselect = 0;
        }
        final int readcookie2 = this.readcookie("usercar");
        if (readcookie2 >= 0 && readcookie2 <= 9) {
            xtGraphics.sc[0] = readcookie2;
        }
        if (this.readcookie("gameprfact") != -1) {
            n3 = (float)this.readcookie("gameprfact");
            n4 = 0;
        }*/
		int n5 = 0;
		long time = new Date().getTime();
		float a = 30.0f;
		int n6 = 0;
		int n7 = 0;
		int n8 = 0;
		int n9 = 0;
		int n10 = 0;
		this.exwist = false;
		while (true) {
			final long time2 = new Date().getTime();
			if (xtGraphics.fase == 111) {
				if (this.mouses == 1) {
					n9 = 800;
				}
				if (n9 < 800) {
					xtGraphics.clicknow(this.graphics);
					++n9;
				} else {
					n9 = 0;
					xtGraphics.fase = 9;
					this.mouses = 0;
					this.lostfcs = false;
				}
			}
			if (xtGraphics.fase == 9) {
				if (n9 < 150) {
					xtGraphics.rad(this.graphics, n9);
					++n9;
				} else {
					n9 = 0;
					xtGraphics.fase = 10;
					this.mouses = 0;
					this.controls[0].falseo();
				}
			}
			if (xtGraphics.fase == -9) {
				if (n9 < 5) {
					this.graphics.setColor(new Color(255, 255, 255));
					this.graphics.fillRect(0, 0, 550, 400);
					++n9;
				} else {
					n9 = 0;
					xtGraphics.fase = 7;
					this.mouses = 0;
				}
			}
			if (xtGraphics.fase == 8) {
				xtGraphics.credits(this.graphics, this.controls[0]);
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.controls[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == 10) {
				xtGraphics.maini(this.graphics, this.controls[0]);
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.controls[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == 11) {
				xtGraphics.inst(this.graphics, this.controls[0]);
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.controls[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == -5) {
				xtGraphics.finish(checkPoints, array, this.controls[0], this.graphics);
				if (n5 == 1) {
					if (checkPoints.stage == xtGraphics.unlocked && xtGraphics.winner && xtGraphics.unlocked != 11) {
						this.savecookie("unlocked", "" + (xtGraphics.unlocked + 1));
					}
					this.savecookie("gameprfact", "" + (int) n3);
					this.savecookie("usercar", "" + xtGraphics.sc[0]);
					n5 = 0;
				}
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.controls[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == 7) {
				xtGraphics.carselect(this.controls[0], array, cars[0], this.graphics);
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.controls[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == 6) {
				xtGraphics.musicomp(checkPoints.stage, this.graphics, this.controls[0]);
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.controls[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == 5) {
				xtGraphics.loadmusic(checkPoints.stage, n4, this.graphics);
				if (n5 == 0) {
					this.savecookie("usercar", "" + xtGraphics.sc[0]);
					n5 = 1;
				}
			}
			if (xtGraphics.fase == 4) {
				xtGraphics.cantgo(this.graphics, this.controls[0]);
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.controls[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == 3) {
				xtGraphics.loadingfailed(checkPoints.stage, this.controls[0], this.graphics);
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.controls[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == 2) {
				xtGraphics.loadingstage(checkPoints.stage, this.graphics);
				this.loadStage(geometries, array, medium, trackers, checkPoints, xtGraphics, cars, record);
				this.controls[0].falseo();
			}
			if (xtGraphics.fase == 1) {
				xtGraphics.trackbg(this.graphics);
				medium.aroundTrackAtStart(checkPoints);
				int n11 = 0;
				final int[] array4 = new int[200];
				for (int i = 5; i < this.notb; ++i) {
					if (geometries[i].dist != 0) {
						array4[n11] = i;
						++n11;
					} else {
						geometries[i].draw(this.graphics);
					}
				}
				final int[] array5 = new int[n11];
				for (int j = 0; j < n11; ++j) {
					array5[j] = 0;
				}
				for (int k = 0; k < n11; ++k) {
					for (int l = k + 1; l < n11; ++l) {
						if (geometries[array4[k]].dist != geometries[array4[l]].dist) {
							if (geometries[array4[k]].dist < geometries[array4[l]].dist) {
								++array5[k];
							} else {
								++array5[l];
							}
						} else if (l > k) {
							++array5[k];
						} else {
							++array5[l];
						}
					}
				}
				for (int n16 = 0; n16 < n11; ++n16) {
					for (int n17 = 0; n17 < n11; ++n17) {
						if (array5[n17] == n16) {
							geometries[array4[n17]].draw(this.graphics);
						}
					}
				}
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.controls[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
				xtGraphics.stageselect(this.graphics, checkPoints, this.controls[0]);
			}
			if (xtGraphics.fase == 176) {
				medium.draw(this.graphics);
				int n18 = 0;
				final int[] array10 = new int[100];
				for (int n19 = 0; n19 < this.nob; ++n19) {
					if (geometries[n19].dist != 0) {
						array10[n18] = n19;
						++n18;
					} else {
						geometries[n19].draw(this.graphics);
					}
				}
				final int[] array11 = new int[n18];
				for (int n20 = 0; n20 < n18; ++n20) {
					array11[n20] = 0;
				}
				for (int n21 = 0; n21 < n18; ++n21) {
					for (int n22 = n21 + 1; n22 < n18; ++n22) {
						if (geometries[array10[n21]].dist != geometries[array10[n22]].dist) {
							if (geometries[array10[n21]].dist < geometries[array10[n22]].dist) {
								++array11[n21];
							} else {
								++array11[n22];
							}
						} else if (n22 > n21) {
							++array11[n21];
						} else {
							++array11[n22];
						}
					}
				}
				for (int n27 = 0; n27 < n18; ++n27) {
					for (int n28 = 0; n28 < n18; ++n28) {
						if (array11[n28] == n27) {
							geometries[array10[n28]].draw(this.graphics);
						}
					}
				}
				medium.follow(geometries[0], 0);
				xtGraphics.framer(checkPoints.stage, this.graphics);
				if (n4 != 0) {
					--n4;
				} else {
					this.controls[0].enter = false;
					this.controls[0].handb = false;
					if (xtGraphics.loadedt[checkPoints.stage - 1]) {
						xtGraphics.stracks[checkPoints.stage - 1].play();
					}
					this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					xtGraphics.fase = 6;
				}
			}
			if (xtGraphics.fase == 0) {
				for (int i = 0; i < 5; i++) {
					if (cars[i].newcar) {
						final int xz = geometries[i].xz;
						final int xy = geometries[i].xy;
						final int zy = geometries[i].zy;
						geometries[i] = new Geometry(array[cars[i].cn], geometries[i].x, geometries[i].y, geometries[i].z, 0);
						geometries[i].xz = xz;
						geometries[i].xy = xy;
						geometries[i].zy = zy;
						cars[i].newcar = false;
					}
				}
				medium.draw(this.graphics);
				int n30 = 0;
				final int[] array16 = new int[100];
				for (int n31 = 0; n31 < this.nob; ++n31) {
					if (geometries[n31].dist != 0) {
						array16[n30] = n31;
						++n30;
					} else {
						geometries[n31].draw(this.graphics);
					}
				}
				final int[] array17 = new int[n30];
				for (int n32 = 0; n32 < n30; ++n32) {
					array17[n32] = 0;
				}
				for (int n33 = 0; n33 < n30; ++n33) {
					for (int n34 = n33 + 1; n34 < n30; ++n34) {
						if (geometries[array16[n33]].dist != geometries[array16[n34]].dist) {
							if (geometries[array16[n33]].dist < geometries[array16[n34]].dist) {
								++array17[n33];
							} else {
								++array17[n34];
							}
						} else if (n34 > n33) {
							++array17[n33];
						} else {
							++array17[n34];
						}
					}
				}
				for (int n39 = 0; n39 < n30; ++n39) {
					for (int n40 = 0; n40 < n30; ++n40) {
						if (array17[n40] == n39) {
							geometries[array16[n40]].draw(this.graphics);
						}
					}
				}
				if (xtGraphics.starcnt == 0) {
					int n41 = 0;
					do {
						int n42 = 0;
						do {
							if (n42 != n41) {
								cars[n41].colide(geometries[n41], cars[n42], geometries[n42]);
							}
						} while (++n42 < 5);
					} while (++n41 < 5);
					int n43 = 0;
					do {
						cars[n43].drive(this.controls[n43], geometries[n43], trackers, checkPoints);
					} while (++n43 < 5);
					int n44 = 0;
					do {
						record.rec(geometries[n44], n44, cars[n44].squash, cars[n44].lastcolido, cars[n44].cntdest);
					} while (++n44 < 5);
					checkPoints.checkstat(cars, geometries);
					int n45 = 1;
					do {
						this.controls[n45].preform(cars[n45], geometries[n45], checkPoints, trackers);
					} while (++n45 < 5);
				} else {
					if (xtGraphics.starcnt == 90) {
						medium.adv = 1900;
						medium.zy = 40;
						medium.vxz = 70;
						this.graphics.setColor(new Color(255, 255, 255));
						this.graphics.fillRect(0, 0, 550, 400);
					}
					if (xtGraphics.starcnt != 0) {
						--xtGraphics.starcnt;
					}
				}
				if (xtGraphics.starcnt < 35) {
					if (this.view == 0) {
						medium.follow(geometries[0], cars[0].cxz);
						xtGraphics.stat(cars[0], checkPoints, this.controls[0], true, this.graphics);
					}
					if (this.view == 1) {
						medium.circleAround(geometries[0], false);
						xtGraphics.stat(cars[0], checkPoints, this.controls[0], false, this.graphics);
					}
					if (this.view == 2) {
						medium.watchFromStationaryPoint(geometries[0], cars[0].mxz);
						xtGraphics.stat(cars[0], checkPoints, this.controls[0], false, this.graphics);
					}
					if (this.mouses == 1) {
						this.controls[0].enter = true;
						this.mouses = 0;
					}
				} else {
					medium.circleAround(geometries[3], true);
					if (this.controls[0].enter || this.controls[0].handb) {
						xtGraphics.starcnt = 35;
						this.controls[0].enter = false;
						this.controls[0].handb = false;
					}
					if (xtGraphics.starcnt == 35) {
						this.mouses = 0;
						medium.vert = false;
						medium.adv = 900;
						medium.vxz = 180;
						checkPoints.checkstat(cars, geometries);
						medium.follow(geometries[0], cars[0].cxz);
						xtGraphics.stat(cars[0], checkPoints, this.controls[0], true, this.graphics);
						this.graphics.setColor(new Color(255, 255, 255));
						this.graphics.fillRect(0, 0, 550, 400);
					}
				}
			}
			if (xtGraphics.fase == -1) {
				if (n8 == 0) {
					int n46 = 0;
					do {
						record.ocar[n46] = new Geometry(geometries[n46], 0, 0, 0, 0);
						geometries[n46] = new Geometry(record.car[0][n46], 0, 0, 0, 0);
					} while (++n46 < 5);
				}
				medium.draw(this.graphics);
				int n47 = 0;
				final int[] array22 = new int[100];
				for (int n48 = 0; n48 < this.nob; ++n48) {
					if (geometries[n48].dist != 0) {
						array22[n47] = n48;
						++n47;
					} else {
						geometries[n48].draw(this.graphics);
					}
				}
				final int[] array23 = new int[n47];
				for (int n49 = 0; n49 < n47; ++n49) {
					array23[n49] = 0;
				}
				for (int n50 = 0; n50 < n47; ++n50) {
					for (int n51 = n50 + 1; n51 < n47; ++n51) {
						if (geometries[array22[n50]].dist != geometries[array22[n51]].dist) {
							if (geometries[array22[n50]].dist < geometries[array22[n51]].dist) {
								++array23[n50];
							} else {
								++array23[n51];
							}
						} else if (n51 > n50) {
							++array23[n50];
						} else {
							++array23[n51];
						}
					}
				}
				for (int n56 = 0; n56 < n47; ++n56) {
					for (int n57 = 0; n57 < n47; ++n57) {
						if (array23[n57] == n56) {
							geometries[array22[n57]].draw(this.graphics);
						}
					}
				}
				if (this.controls[0].enter || this.controls[0].handb || this.mouses == 1) {
					n8 = 299;
					this.controls[0].enter = false;
					this.controls[0].handb = false;
					this.mouses = 0;
				}
				int n58 = 0;
				do {
					if (record.fix[n58] == n8) {
						if (geometries[n58].dist == 0) {
							geometries[n58].fcnt = 8;
						} else {
							geometries[n58].fix = true;
						}
					}
					if (geometries[n58].fcnt == 7 || geometries[n58].fcnt == 8) {
						geometries[n58] = new Geometry(array[cars[n58].cn], 0, 0, 0, 0);
						record.cntdest[n58] = 0;
					}
					if (n8 == 299) {
						geometries[n58] = new Geometry(record.ocar[n58], 0, 0, 0, 0);
					}
					record.play(geometries[n58], cars[n58], n58, n8);
				} while (++n58 < 5);
				if (++n8 == 300) {
					n8 = 0;
					if (xtGraphics.loadedt[checkPoints.stage - 1]) {
						xtGraphics.stracks[checkPoints.stage - 1].stop();
					}
					xtGraphics.fase = -6;
				} else {
					xtGraphics.replyn(this.graphics);
				}
				medium.circleAround(geometries[0], false);
			}
			if (xtGraphics.fase == -2) {
				if (record.hcaught) {
					medium.vert = !(medium.random() > 0.45);
					medium.adv = (int) (900.0f * medium.random());
					medium.vxz = 180;
					n8 = 0;
					xtGraphics.fase = -3;
					n9 = 0;
					n10 = 0;
				} else {
					this.graphics.drawImage(xtGraphics.trackbg, 0, 0, null);
					n8 = -2;
					xtGraphics.fase = -4;
				}
			}
			if (xtGraphics.fase == -3) {
				if (n8 == 0) {
					int n59 = 0;
					do {
						geometries[n59] = new Geometry(record.starcar[n59], 0, 0, 0, 0);
					} while (++n59 < 5);
				}
				medium.draw(this.graphics);
				int n60 = 0;
				final int[] array28 = new int[100];
				for (int n61 = 0; n61 < this.nob; ++n61) {
					if (geometries[n61].dist != 0) {
						array28[n60] = n61;
						++n60;
					} else {
						geometries[n61].draw(this.graphics);
					}
				}
				final int[] array29 = new int[n60];
				for (int n62 = 0; n62 < n60; ++n62) {
					array29[n62] = 0;
				}
				for (int n63 = 0; n63 < n60; ++n63) {
					for (int n64 = n63 + 1; n64 < n60; ++n64) {
						if (geometries[array28[n63]].dist != geometries[array28[n64]].dist) {
							if (geometries[array28[n63]].dist < geometries[array28[n64]].dist) {
								++array29[n63];
							} else {
								++array29[n64];
							}
						} else if (n64 > n63) {
							++array29[n63];
						} else {
							++array29[n64];
						}
					}
				}
				for (int n69 = 0; n69 < n60; ++n69) {
					for (int n70 = 0; n70 < n60; ++n70) {
						if (array29[n70] == n69) {
							geometries[array28[n70]].draw(this.graphics);
						}
					}
				}
				int n71 = 0;
				do {
					if (record.hfix[n71] == n8) {
						if (geometries[n71].dist == 0) {
							geometries[n71].fcnt = 8;
						} else {
							geometries[n71].fix = true;
						}
					}
					if (geometries[n71].fcnt == 7 || geometries[n71].fcnt == 8) {
						geometries[n71] = new Geometry(array[cars[n71].cn], 0, 0, 0, 0);
						record.cntdest[n71] = 0;
					}
					record.playh(geometries[n71], cars[n71], n71, n8);
				} while (++n71 < 5);
				if (n10 == 2 && n8 == 299) {
					this.controls[0].enter = true;
				}
				if (this.controls[0].enter || this.controls[0].handb) {
					xtGraphics.fase = -4;
					this.controls[0].enter = false;
					this.controls[0].handb = false;
					n8 = -7;
				} else {
					xtGraphics.levelhigh(this.graphics, record.wasted, record.whenwasted);
					if (n8 == 0 || n8 == 1 || n8 == 2) {
						this.graphics.setColor(new Color(0, 0, 0));
						this.graphics.fillRect(0, 0, 550, 400);
					}
					if (record.wasted != 0) {
						if (n9 == 10) {
							this.graphics.setColor(new Color(255, 255, 255));
							this.graphics.fillRect(0, 0, 550, 400);
						}
						if (n9 >= 10) {
							medium.circleAround(geometries[record.wasted], false);
						} else {
							medium.circleAround(geometries[0], false);
						}
						if (n8 > record.whenwasted && n9 != 20) {
							++n9;
						}
						if ((n9 == 0 || n9 == 20) && ++n8 == 300) {
							n8 = 0;
							n9 = 0;
							++n10;
						}
					} else {
						if (n9 == 3 || n9 == 31 || n9 == 66) {
							this.graphics.setColor(new Color(255, 255, 255));
							this.graphics.fillRect(0, 0, 550, 400);
						}
						if (n8 > record.whenwasted && n9 != 67) {
							++n9;
						}
						medium.circleAround(geometries[0], false);
						if ((n9 == 0 || n9 == 67) && ++n8 == 300) {
							n8 = 0;
							n9 = 0;
							++n10;
						}
					}
				}
			}
			if (xtGraphics.fase == -4) {
				if (n8 <= 0) {
					this.graphics.drawImage(xtGraphics.mdness, 164, 330, null);
				}
				if (n8 >= 0) {
					xtGraphics.fleximage(this.offImage, this.graphics, n8);
					if (n8 != 0) {
						if (xtGraphics.winner) {
							if (checkPoints.stage == xtGraphics.unlocked) {
								this.graphics.drawImage(xtGraphics.congrd, 140, 30, null);
								if (this.controls[0].enter || this.controls[0].handb) {
									this.controls[0].enter = false;
									this.controls[0].handb = false;
								}
							} else {
								this.graphics.drawImage(xtGraphics.congrd, 140, 117, null);
							}
						} else {
							this.graphics.drawImage(xtGraphics.gameov, 190, 117, null);
						}
					}
				}
				if (++n8 == 6) {
					xtGraphics.fase = -5;
				}
			}
			if (xtGraphics.fase == -6) {
				xtGraphics.pauseimage(this.offImage, this.graphics);
				xtGraphics.fase = -7;
				this.mouses = 0;
			}
			if (xtGraphics.fase == -7) {
				xtGraphics.pausedgame(this.graphics, checkPoints.stage, this.controls[0], record);
				if (n8 != 0) {
					n8 = 0;
				}
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.controls[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == -8) {
				xtGraphics.cantreply(this.graphics);
				if (++n8 == 150 || this.controls[0].enter || this.controls[0].handb || this.mouses == 1) {
					this.graphics.drawImage(xtGraphics.fleximg, 0, 0, null);
					this.graphics.drawImage(xtGraphics.paused, 156, 106, null);
					xtGraphics.fase = -7;
					this.mouses = 0;
					this.controls[0].enter = false;
					this.controls[0].handb = false;
				}
			}
			if (this.lostfcs && xtGraphics.fase != 176 && xtGraphics.fase != 111) {
				xtGraphics.nofocus(this.graphics);
				if (xtGraphics.fase == 0) {
					this.controls[0].enter = true;
				}
				if (this.mouses == 1 || this.mouses == 2) {
					this.lostfcs = false;
					if (xtGraphics.fase == 10) {
						xtGraphics.flipo = 0;
					}
					if (xtGraphics.fase == 11 && xtGraphics.flipo != 0) {
						--xtGraphics.flipo;
					}
					if (xtGraphics.fase == -7) {
						this.graphics.drawImage(xtGraphics.fleximg, 0, 0, null);
					}
				}
			}
			this.repaint();
			xtGraphics.playsounds(cars[0], this.controls[0], checkPoints.stage);
			final long time3 = new Date().getTime();
			if (xtGraphics.fase == 0 || xtGraphics.fase == -1 || xtGraphics.fase == -3) {
				if (n6 == 0) {
					a = n3;
					n6 = 1;
					n7 = 0;
				}
				if (n7 == 10) {
					if (time3 - time < 530L) {
						a += 0.5;
					} else {
						a -= 0.5;
						if (a < 5.0f) {
							a = 5.0f;
						}
					}
					if (xtGraphics.starcnt == 0) {
						medium.adjustFade(a);
					}
					time = time3;
					n7 = 0;
				} else {
					++n7;
				}
			} else {
				if (n6 != 0) {
					n3 = a;
					n6 = 0;
					n7 = 0;
				}
				if (n4 == 0 || xtGraphics.fase != 176) {
					if (n7 == 10) {
						if (time3 - time < 400L) {
							a += 3.5;
						} else {
							a -= 3.5;
							if (a < 5.0f) {
								a = 5.0f;
							}
						}
						time = time3;
						n7 = 0;
					} else {
						++n7;
					}
				} else {
					if (n4 == 79) {
						a = n3;
						time = time3;
						n7 = 0;
					}
					if (n7 == 10) {
						if (time3 - time < 530L) {
							a += 5.0f;
						} else {
							a -= 5.0f;
							if (a < 5.0f) {
								a = 5.0f;
							}
						}
						time = time3;
						n7 = 0;
					} else {
						++n7;
					}
					if (n4 == 1) {
						n3 = a;
					}
				}
			}
			if (this.exwist) {
				this.graphics.dispose();
				xtGraphics.stopallnow();
				this.gamer.stop();
				this.gamer = null;
			}
			long frametime = Math.round(a) - (time3 - time2);
			if (frametime < n) {
				frametime = n;
			}
			try {
				Thread.sleep((long) (1000.0 / 30.0));
			} catch (InterruptedException ex) {
			}
			//this.graphics.drawString("FPS:" + String.valueOf(1000 / frametime), 100, 100);
			//System.out.println(xtGraphics.fase);
		}
	}

	public void init() {
		this.offImage = this.createImage(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		if (this.offImage != null) {
			this.graphics = this.offImage.getGraphics();
		}
	}

	public void savecookie(final String str, final String str2) {
		try {
			//  JSObject.getWindow(this).eval("SetCookie('" + str + "','" + str2 + "');");
		} catch (Exception ex) {
		}
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		System.out.println("Mouse pressed.");
		if (!this.exwist && this.mouses == 0) {
			this.xm = mouseEvent.getX();
			this.ym = mouseEvent.getY();
			this.mouses = 1;
		}
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		Control controls = this.controls[0];
		switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_UP:
				controls.up = true;
				break;
			case KeyEvent.VK_LEFT:
				controls.left = true;
				break;
			case KeyEvent.VK_DOWN:
				controls.down = true;
				break;
			case KeyEvent.VK_RIGHT:
				controls.right = true;
				break;
			case KeyEvent.VK_SPACE:
				controls.handb = true;
				break;
			case KeyEvent.VK_ENTER:
				controls.enter = true;
				break;
			case KeyEvent.VK_A:
				this.controls[0].arrace = !this.controls[0].arrace;
				break;
			case KeyEvent.VK_N:
				this.controls[0].sound_muted = !this.controls[0].sound_muted;
				break;
			case KeyEvent.VK_M:
				this.controls[0].music_muted = !this.controls[0].music_muted;
				break;

		}
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		Control controls = this.controls[0];
		switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_UP:
				controls.up = false;
				break;
			case KeyEvent.VK_LEFT:
				controls.left = false;
				break;
			case KeyEvent.VK_DOWN:
				controls.down = false;
				break;
			case KeyEvent.VK_RIGHT:
				controls.right = false;
				break;
			case KeyEvent.VK_SPACE:
				controls.handb = false;
				break;
			case KeyEvent.VK_ENTER:
				controls.enter = false;
				break;
			case KeyEvent.VK_V:
				++this.view;
				if (this.view == 3) {
					this.view = 0;
				}
				break;
		}
	}
}
