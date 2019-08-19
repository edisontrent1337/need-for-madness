
import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//
// Decompiled by Procyon v0.5.36
//

public class GameSparker extends Applet implements Runnable, MouseListener {
	Graphics rd;
	Image offImage;
	Thread gamer;
	Control[] u;
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
			System.gc();
			this.gamer.stop();
			this.gamer = null;
		}
		this.exwist = true;
	}

	public boolean lostFocus(final Event event, final Object o) {
		if (!this.exwist) {
			this.lostfcs = true;
			this.mouses = 0;
			this.u[0].falseo();
		}
		return false;
	}

	public String getstring(final String s, final String s2, final int n) {
		int n2 = 0;
		String string = "";
		for (int i = s.length() + 1; i < s2.length(); ++i) {
			final String string2 = "" + s2.charAt(i);
			if (string2.equals(",") || string2.equals(")")) {
				++n2;
				++i;
			}
			if (n2 == n) {
				string += s2.charAt(i);
			}
		}
		return string;
	}

	public int getint(final String s, final String s2, final int n) {
		int n2 = 0;
		String string = "";
		for (int i = s.length() + 1; i < s2.length(); ++i) {
			final String string2 = "" + s2.charAt(i);
			if (string2.equals(",") || string2.equals(")")) {
				++n2;
				++i;
			}
			if (n2 == n) {
				string += s2.charAt(i);
			}
		}
		return Integer.valueOf(string);
	}

    /*public int readcookie(final String str) {
        int intValue = -1;
        try {
            final JSObject window = JSObject.getWindow(this);
            window.eval("s=GetCookie('" + str + "');");
            intValue = Integer.valueOf(String.valueOf(String.valueOf(window.getMember("s"))));
        }
        catch (Exception ex) {}
        return intValue;
    }*/

	public void paint(final Graphics graphics) {
		graphics.drawImage(this.offImage, 0, 0, this);
	}

	public GameSparker() {
		this.u = new Control[5];
		this.mouses = 0;
		this.xm = 0;
		this.ym = 0;
		this.lostfcs = false;
		this.exwist = true;
		this.nob = 0;
		this.notb = 0;
		this.view = 0;
		addMouseListener(this);
	}

	public void loadbase(final ContO[] array, final Medium medium, final Trackers trackers) {
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
				array[n] = new ContO(b, medium, trackers);
			}
			in.close();
			zipInputStream.close();
		} catch (Exception obj) {
			System.out.println("Error Reading Models: " + obj);
		}
		System.gc();
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

	public boolean keyUp(final Event event, final int n) {
		if (!this.exwist) {
			if (n == 1004) {
				this.u[0].up = false;
			}
			if (n == 1005) {
				this.u[0].down = false;
			}
			if (n == 1007) {
				this.u[0].right = false;
			}
			if (n == 1006) {
				this.u[0].left = false;
			}
			if (n == 32) {
				this.u[0].handb = false;
			}
		}
		return false;
	}

	public void start() {
		if (this.gamer == null) {
			this.gamer = new Thread(this);
		}
		this.gamer.start();
	}

	@Override
	public boolean mouseDown(final Event event, final int xm, final int ym) {
		if (!this.exwist && this.mouses == 0) {
			this.xm = xm;
			this.ym = ym;
			this.mouses = 1;
		}
		return false;
	}

	public void loadstage(final ContO[] array, final ContO[] array2, final Medium medium, final Trackers trackers, final CheckPoints checkPoints, final xtGraphics xtGraphics, final Madness[] array3, final Record record) {
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
					medium.setsnap(this.getint("snap", string, 0), this.getint("snap", string, 1), this.getint("snap", string, 2));
				}
				if (string.startsWith("sky")) {
					medium.setsky(this.getint("sky", string, 0), this.getint("sky", string, 1), this.getint("sky", string, 2));
					xtGraphics.snap(checkPoints.stage);
				}
				if (string.startsWith("ground")) {
					medium.setgrnd(this.getint("ground", string, 0), this.getint("ground", string, 1), this.getint("ground", string, 2));
				}
				if (string.startsWith("fog")) {
					medium.setfade(this.getint("fog", string, 0), this.getint("fog", string, 1), this.getint("fog", string, 2));
				}
				if (string.startsWith("fadefrom")) {
					medium.fadfrom(this.getint("fadefrom", string, 0));
					medium.origfade = medium.fade[0];
				}
				if (string.startsWith("set")) {
					final int getint = this.getint("set", string, 0);
					array[this.nob] = new ContO(array2[getint], this.getint("set", string, 1), medium.ground - array2[getint].grat, this.getint("set", string, 2), this.getint("set", string, 3));
					if (string.indexOf(")p") != -1) {
						checkPoints.x[checkPoints.n] = this.getint("chk", string, 1);
						checkPoints.z[checkPoints.n] = this.getint("chk", string, 2);
						checkPoints.y[checkPoints.n] = 0;
						checkPoints.typ[checkPoints.n] = 0;
						if (string.indexOf(")pt") != -1) {
							checkPoints.typ[checkPoints.n] = -1;
						}
						if (string.indexOf(")pr") != -1) {
							checkPoints.typ[checkPoints.n] = -2;
						}
						if (string.indexOf(")po") != -1) {
							checkPoints.typ[checkPoints.n] = -3;
						}
						if (string.indexOf(")ph") != -1) {
							checkPoints.typ[checkPoints.n] = -4;
						}
						++checkPoints.n;
						this.notb = this.nob + 1;
					}
					++this.nob;
				}
				if (string.startsWith("chk")) {
					final int getint2 = this.getint("chk", string, 0);
					array[this.nob] = new ContO(array2[getint2], this.getint("chk", string, 1), medium.ground - array2[getint2].grat, this.getint("chk", string, 2), this.getint("chk", string, 3));
					checkPoints.x[checkPoints.n] = this.getint("chk", string, 1);
					checkPoints.z[checkPoints.n] = this.getint("chk", string, 2);
					checkPoints.y[checkPoints.n] = medium.ground - array2[getint2].grat;
					if (this.getint("chk", string, 3) == 0) {
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
					array[this.nob] = new ContO(array2[this.getint("fix", string, 0)], this.getint("fix", string, 1), this.getint("fix", string, 3), this.getint("fix", string, 2), this.getint("fix", string, 4));
					checkPoints.fx[checkPoints.fn] = this.getint("fix", string, 1);
					checkPoints.fz[checkPoints.fn] = this.getint("fix", string, 2);
					checkPoints.fy[checkPoints.fn] = this.getint("fix", string, 3);
					array[this.nob].elec = true;
					if (this.getint("fix", string, 4) != 0) {
						checkPoints.roted[checkPoints.fn] = true;
						array[this.nob].roted = true;
					} else {
						checkPoints.roted[checkPoints.fn] = false;
					}
					if (string.indexOf(")s") != -1) {
						checkPoints.special[checkPoints.fn] = true;
					} else {
						checkPoints.special[checkPoints.fn] = false;
					}
					++checkPoints.fn;
					++this.nob;
					this.notb = this.nob;
				}
				if (string.startsWith("nlaps")) {
					checkPoints.nlaps = this.getint("nlaps", string, 0);
				}
				if (string.startsWith("name")) {
					checkPoints.name = this.getstring("name", string, 0).replace('|', ',');
				}
				if (string.startsWith("maxr")) {
					final int getint3 = this.getint("maxr", string, 0);
					final int getint4 = this.getint("maxr", string, 1);
					final int getint5 = this.getint("maxr", string, 2);
					for (int i = 0; i < getint3; ++i) {
						array[this.nob] = new ContO(array2[39], getint4, medium.ground - array2[39].grat, i * 4800 + getint5, 0);
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
					final int getint6 = this.getint("maxl", string, 0);
					final int getint7 = this.getint("maxl", string, 1);
					final int getint8 = this.getint("maxl", string, 2);
					for (int j = 0; j < getint6; ++j) {
						array[this.nob] = new ContO(array2[39], getint7, medium.ground - array2[39].grat, j * 4800 + getint8, 0);
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
					final int getint9 = this.getint("maxt", string, 0);
					final int getint10 = this.getint("maxt", string, 1);
					final int getint11 = this.getint("maxt", string, 2);
					for (int k = 0; k < getint9; ++k) {
						array[this.nob] = new ContO(array2[39], k * 4800 + getint11, medium.ground - array2[39].grat, getint10, 90);
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
					final int getint12 = this.getint("maxb", string, 0);
					final int getint13 = this.getint("maxb", string, 1);
					final int getint14 = this.getint("maxb", string, 2);
					for (int l = 0; l < getint12; ++l) {
						array[this.nob] = new ContO(array2[39], l * 4800 + getint14, medium.ground - array2[39].grat, getint13, 90);
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
			this.u[n2].reset(checkPoints);
		} while (++n2 < 5);
		xtGraphics.resetstat(checkPoints.stage);
		int n3 = 0;
		do {
			array[n3] = new ContO(array2[xtGraphics.sc[n3]], xtGraphics.xstart[n3], 250 - array2[xtGraphics.sc[n3]].grat, xtGraphics.zstart[n3], 0);
			array3[n3].reseto(xtGraphics.sc[n3], array[n3], checkPoints);
		} while (++n3 < 5);
		record.reset(array);
		System.gc();
	}

	public void run() {
		this.rd.setColor(new Color(0, 0, 0));
		this.rd.fillRect(0, 0, 550, 400);
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
		final xtGraphics xtGraphics = new xtGraphics(medium, this.rd, this, sunytyp);
		final Record record = new Record(medium);
		final ContO[] array = new ContO[43];
		this.loadbase(array, medium, trackers);
		final xtGraphics xtGraphics2 = xtGraphics;
		xtGraphics2.dnload += 29;
		xtGraphics.loading(this.rd, this);
		final ContO[] array2 = new ContO[210];
		final Madness[] array3 = new Madness[5];
		int n2 = 0;
		do {
			array3[n2] = new Madness(medium, record, xtGraphics, n2);
			this.u[n2] = new Control(medium);
		} while (++n2 < 5);
		float n3 = 35.0f;
		int n4 = 80;
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
		System.gc();
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
					xtGraphics.clicknow(this.rd);
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
					xtGraphics.rad(this.rd, n9);
					++n9;
				} else {
					n9 = 0;
					xtGraphics.fase = 10;
					this.mouses = 0;
					this.u[0].falseo();
				}
			}
			if (xtGraphics.fase == -9) {
				if (n9 < 5) {
					this.rd.setColor(new Color(255, 255, 255));
					this.rd.fillRect(0, 0, 550, 400);
					++n9;
				} else {
					n9 = 0;
					xtGraphics.fase = 7;
					this.mouses = 0;
				}
			}
			if (xtGraphics.fase == 8) {
				xtGraphics.credits(this.rd, this.u[0]);
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == 10) {
				xtGraphics.maini(this.rd, this.u[0]);
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == 11) {
				xtGraphics.inst(this.rd, this.u[0]);
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == -5) {
				xtGraphics.finish(checkPoints, array, this.u[0], this.rd);
				if (n5 == 1) {
					if (checkPoints.stage == xtGraphics.unlocked && xtGraphics.winner && xtGraphics.unlocked != 11) {
						this.savecookie("unlocked", "" + (xtGraphics.unlocked + 1));
					}
					this.savecookie("gameprfact", "" + (int) n3);
					this.savecookie("usercar", "" + xtGraphics.sc[0]);
					n5 = 0;
				}
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == 7) {
				xtGraphics.carselect(this.u[0], array, array3[0], this.rd);
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == 6) {
				xtGraphics.musicomp(checkPoints.stage, this.rd, this.u[0]);
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == 5) {
				xtGraphics.loadmusic(checkPoints.stage, n4, this.rd);
				if (n5 == 0) {
					this.savecookie("usercar", "" + xtGraphics.sc[0]);
					n5 = 1;
				}
			}
			if (xtGraphics.fase == 4) {
				xtGraphics.cantgo(this.rd, this.u[0]);
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == 3) {
				xtGraphics.loadingfailed(checkPoints.stage, this.u[0], this.rd);
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == 2) {
				xtGraphics.loadingstage(checkPoints.stage, this.rd);
				this.loadstage(array2, array, medium, trackers, checkPoints, xtGraphics, array3, record);
				this.u[0].falseo();
			}
			if (xtGraphics.fase == 1) {
				xtGraphics.trackbg(this.rd);
				medium.aroundtrack(checkPoints);
				int n11 = 0;
				final int[] array4 = new int[200];
				for (int i = 5; i < this.notb; ++i) {
					if (array2[i].dist != 0) {
						array4[n11] = i;
						++n11;
					} else {
						array2[i].d(this.rd);
					}
				}
				final int[] array5 = new int[n11];
				for (int j = 0; j < n11; ++j) {
					array5[j] = 0;
				}
				for (int k = 0; k < n11; ++k) {
					for (int l = k + 1; l < n11; ++l) {
						if (array2[array4[k]].dist != array2[array4[l]].dist) {
							if (array2[array4[k]].dist < array2[array4[l]].dist) {
								final int[] array6 = array5;
								final int n12 = k;
								++array6[n12];
							} else {
								final int[] array7 = array5;
								final int n13 = l;
								++array7[n13];
							}
						} else if (l > k) {
							final int[] array8 = array5;
							final int n14 = k;
							++array8[n14];
						} else {
							final int[] array9 = array5;
							final int n15 = l;
							++array9[n15];
						}
					}
				}
				for (int n16 = 0; n16 < n11; ++n16) {
					for (int n17 = 0; n17 < n11; ++n17) {
						if (array5[n17] == n16) {
							array2[array4[n17]].d(this.rd);
						}
					}
				}
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
				xtGraphics.stageselect(this.rd, checkPoints, this.u[0]);
			}
			if (xtGraphics.fase == 176) {
				medium.d(this.rd);
				int n18 = 0;
				final int[] array10 = new int[100];
				for (int n19 = 0; n19 < this.nob; ++n19) {
					if (array2[n19].dist != 0) {
						array10[n18] = n19;
						++n18;
					} else {
						array2[n19].d(this.rd);
					}
				}
				final int[] array11 = new int[n18];
				for (int n20 = 0; n20 < n18; ++n20) {
					array11[n20] = 0;
				}
				for (int n21 = 0; n21 < n18; ++n21) {
					for (int n22 = n21 + 1; n22 < n18; ++n22) {
						if (array2[array10[n21]].dist != array2[array10[n22]].dist) {
							if (array2[array10[n21]].dist < array2[array10[n22]].dist) {
								final int[] array12 = array11;
								final int n23 = n21;
								++array12[n23];
							} else {
								final int[] array13 = array11;
								final int n24 = n22;
								++array13[n24];
							}
						} else if (n22 > n21) {
							final int[] array14 = array11;
							final int n25 = n21;
							++array14[n25];
						} else {
							final int[] array15 = array11;
							final int n26 = n22;
							++array15[n26];
						}
					}
				}
				for (int n27 = 0; n27 < n18; ++n27) {
					for (int n28 = 0; n28 < n18; ++n28) {
						if (array11[n28] == n27) {
							array2[array10[n28]].d(this.rd);
						}
					}
				}
				medium.follow(array2[0], 0);
				xtGraphics.framer(checkPoints.stage, this.rd);
				if (n4 != 0) {
					--n4;
				} else {
					this.u[0].enter = false;
					this.u[0].handb = false;
					if (xtGraphics.loadedt[checkPoints.stage - 1]) {
						xtGraphics.stracks[checkPoints.stage - 1].play();
					}
					this.setCursor(new Cursor(0));
					xtGraphics.fase = 6;
				}
			}
			if (xtGraphics.fase == 0) {
				int n29 = 0;
				do {
					if (array3[n29].newcar) {
						final int xz = array2[n29].xz;
						final int xy = array2[n29].xy;
						final int zy = array2[n29].zy;
						array2[n29] = new ContO(array[array3[n29].cn], array2[n29].x, array2[n29].y, array2[n29].z, 0);
						array2[n29].xz = xz;
						array2[n29].xy = xy;
						array2[n29].zy = zy;
						array3[n29].newcar = false;
					}
				} while (++n29 < 5);
				medium.d(this.rd);
				int n30 = 0;
				final int[] array16 = new int[100];
				for (int n31 = 0; n31 < this.nob; ++n31) {
					if (array2[n31].dist != 0) {
						array16[n30] = n31;
						++n30;
					} else {
						array2[n31].d(this.rd);
					}
				}
				final int[] array17 = new int[n30];
				for (int n32 = 0; n32 < n30; ++n32) {
					array17[n32] = 0;
				}
				for (int n33 = 0; n33 < n30; ++n33) {
					for (int n34 = n33 + 1; n34 < n30; ++n34) {
						if (array2[array16[n33]].dist != array2[array16[n34]].dist) {
							if (array2[array16[n33]].dist < array2[array16[n34]].dist) {
								final int[] array18 = array17;
								final int n35 = n33;
								++array18[n35];
							} else {
								final int[] array19 = array17;
								final int n36 = n34;
								++array19[n36];
							}
						} else if (n34 > n33) {
							final int[] array20 = array17;
							final int n37 = n33;
							++array20[n37];
						} else {
							final int[] array21 = array17;
							final int n38 = n34;
							++array21[n38];
						}
					}
				}
				for (int n39 = 0; n39 < n30; ++n39) {
					for (int n40 = 0; n40 < n30; ++n40) {
						if (array17[n40] == n39) {
							array2[array16[n40]].d(this.rd);
						}
					}
				}
				if (xtGraphics.starcnt == 0) {
					int n41 = 0;
					do {
						int n42 = 0;
						do {
							if (n42 != n41) {
								array3[n41].colide(array2[n41], array3[n42], array2[n42]);
							}
						} while (++n42 < 5);
					} while (++n41 < 5);
					int n43 = 0;
					do {
						array3[n43].drive(this.u[n43], array2[n43], trackers, checkPoints);
					} while (++n43 < 5);
					int n44 = 0;
					do {
						record.rec(array2[n44], n44, array3[n44].squash, array3[n44].lastcolido, array3[n44].cntdest);
					} while (++n44 < 5);
					checkPoints.checkstat(array3, array2);
					int n45 = 1;
					do {
						this.u[n45].preform(array3[n45], array2[n45], checkPoints, trackers);
					} while (++n45 < 5);
				} else {
					if (xtGraphics.starcnt == 90) {
						medium.adv = 1900;
						medium.zy = 40;
						medium.vxz = 70;
						this.rd.setColor(new Color(255, 255, 255));
						this.rd.fillRect(0, 0, 550, 400);
					}
					if (xtGraphics.starcnt != 0) {
						final xtGraphics xtGraphics3 = xtGraphics;
						--xtGraphics3.starcnt;
					}
				}
				if (xtGraphics.starcnt < 35) {
					if (this.view == 0) {
						medium.follow(array2[0], array3[0].cxz);
						xtGraphics.stat(array3[0], checkPoints, this.u[0], true, this.rd);
					}
					if (this.view == 1) {
						medium.around(array2[0], false);
						xtGraphics.stat(array3[0], checkPoints, this.u[0], false, this.rd);
					}
					if (this.view == 2) {
						medium.watch(array2[0], array3[0].mxz);
						xtGraphics.stat(array3[0], checkPoints, this.u[0], false, this.rd);
					}
					if (this.mouses == 1) {
						this.u[0].enter = true;
						this.mouses = 0;
					}
				} else {
					medium.around(array2[3], true);
					if (this.u[0].enter || this.u[0].handb) {
						xtGraphics.starcnt = 35;
						this.u[0].enter = false;
						this.u[0].handb = false;
					}
					if (xtGraphics.starcnt == 35) {
						this.mouses = 0;
						medium.vert = false;
						medium.adv = 900;
						medium.vxz = 180;
						checkPoints.checkstat(array3, array2);
						medium.follow(array2[0], array3[0].cxz);
						xtGraphics.stat(array3[0], checkPoints, this.u[0], true, this.rd);
						this.rd.setColor(new Color(255, 255, 255));
						this.rd.fillRect(0, 0, 550, 400);
					}
				}
			}
			if (xtGraphics.fase == -1) {
				if (n8 == 0) {
					int n46 = 0;
					do {
						record.ocar[n46] = new ContO(array2[n46], 0, 0, 0, 0);
						array2[n46] = new ContO(record.car[0][n46], 0, 0, 0, 0);
					} while (++n46 < 5);
				}
				medium.d(this.rd);
				int n47 = 0;
				final int[] array22 = new int[100];
				for (int n48 = 0; n48 < this.nob; ++n48) {
					if (array2[n48].dist != 0) {
						array22[n47] = n48;
						++n47;
					} else {
						array2[n48].d(this.rd);
					}
				}
				final int[] array23 = new int[n47];
				for (int n49 = 0; n49 < n47; ++n49) {
					array23[n49] = 0;
				}
				for (int n50 = 0; n50 < n47; ++n50) {
					for (int n51 = n50 + 1; n51 < n47; ++n51) {
						if (array2[array22[n50]].dist != array2[array22[n51]].dist) {
							if (array2[array22[n50]].dist < array2[array22[n51]].dist) {
								final int[] array24 = array23;
								final int n52 = n50;
								++array24[n52];
							} else {
								final int[] array25 = array23;
								final int n53 = n51;
								++array25[n53];
							}
						} else if (n51 > n50) {
							final int[] array26 = array23;
							final int n54 = n50;
							++array26[n54];
						} else {
							final int[] array27 = array23;
							final int n55 = n51;
							++array27[n55];
						}
					}
				}
				for (int n56 = 0; n56 < n47; ++n56) {
					for (int n57 = 0; n57 < n47; ++n57) {
						if (array23[n57] == n56) {
							array2[array22[n57]].d(this.rd);
						}
					}
				}
				if (this.u[0].enter || this.u[0].handb || this.mouses == 1) {
					n8 = 299;
					this.u[0].enter = false;
					this.u[0].handb = false;
					this.mouses = 0;
				}
				int n58 = 0;
				do {
					if (record.fix[n58] == n8) {
						if (array2[n58].dist == 0) {
							array2[n58].fcnt = 8;
						} else {
							array2[n58].fix = true;
						}
					}
					if (array2[n58].fcnt == 7 || array2[n58].fcnt == 8) {
						array2[n58] = new ContO(array[array3[n58].cn], 0, 0, 0, 0);
						record.cntdest[n58] = 0;
					}
					if (n8 == 299) {
						array2[n58] = new ContO(record.ocar[n58], 0, 0, 0, 0);
					}
					record.play(array2[n58], array3[n58], n58, n8);
				} while (++n58 < 5);
				if (++n8 == 300) {
					n8 = 0;
					if (xtGraphics.loadedt[checkPoints.stage - 1]) {
						xtGraphics.stracks[checkPoints.stage - 1].stop();
					}
					xtGraphics.fase = -6;
				} else {
					xtGraphics.replyn(this.rd);
				}
				medium.around(array2[0], false);
			}
			if (xtGraphics.fase == -2) {
				if (record.hcaught) {
					if (medium.random() > 0.45) {
						medium.vert = false;
					} else {
						medium.vert = true;
					}
					medium.adv = (int) (900.0f * medium.random());
					medium.vxz = 180;
					n8 = 0;
					xtGraphics.fase = -3;
					n9 = 0;
					n10 = 0;
				} else {
					this.rd.drawImage(xtGraphics.trackbg, 0, 0, null);
					n8 = -2;
					xtGraphics.fase = -4;
				}
			}
			if (xtGraphics.fase == -3) {
				if (n8 == 0) {
					int n59 = 0;
					do {
						array2[n59] = new ContO(record.starcar[n59], 0, 0, 0, 0);
					} while (++n59 < 5);
				}
				medium.d(this.rd);
				int n60 = 0;
				final int[] array28 = new int[100];
				for (int n61 = 0; n61 < this.nob; ++n61) {
					if (array2[n61].dist != 0) {
						array28[n60] = n61;
						++n60;
					} else {
						array2[n61].d(this.rd);
					}
				}
				final int[] array29 = new int[n60];
				for (int n62 = 0; n62 < n60; ++n62) {
					array29[n62] = 0;
				}
				for (int n63 = 0; n63 < n60; ++n63) {
					for (int n64 = n63 + 1; n64 < n60; ++n64) {
						if (array2[array28[n63]].dist != array2[array28[n64]].dist) {
							if (array2[array28[n63]].dist < array2[array28[n64]].dist) {
								final int[] array30 = array29;
								final int n65 = n63;
								++array30[n65];
							} else {
								final int[] array31 = array29;
								final int n66 = n64;
								++array31[n66];
							}
						} else if (n64 > n63) {
							final int[] array32 = array29;
							final int n67 = n63;
							++array32[n67];
						} else {
							final int[] array33 = array29;
							final int n68 = n64;
							++array33[n68];
						}
					}
				}
				for (int n69 = 0; n69 < n60; ++n69) {
					for (int n70 = 0; n70 < n60; ++n70) {
						if (array29[n70] == n69) {
							array2[array28[n70]].d(this.rd);
						}
					}
				}
				int n71 = 0;
				do {
					if (record.hfix[n71] == n8) {
						if (array2[n71].dist == 0) {
							array2[n71].fcnt = 8;
						} else {
							array2[n71].fix = true;
						}
					}
					if (array2[n71].fcnt == 7 || array2[n71].fcnt == 8) {
						array2[n71] = new ContO(array[array3[n71].cn], 0, 0, 0, 0);
						record.cntdest[n71] = 0;
					}
					record.playh(array2[n71], array3[n71], n71, n8);
				} while (++n71 < 5);
				if (n10 == 2 && n8 == 299) {
					this.u[0].enter = true;
				}
				if (this.u[0].enter || this.u[0].handb) {
					xtGraphics.fase = -4;
					this.u[0].enter = false;
					this.u[0].handb = false;
					n8 = -7;
				} else {
					xtGraphics.levelhigh(this.rd, record.wasted, record.whenwasted);
					if (n8 == 0 || n8 == 1 || n8 == 2) {
						this.rd.setColor(new Color(0, 0, 0));
						this.rd.fillRect(0, 0, 550, 400);
					}
					if (record.wasted != 0) {
						if (n9 == 10) {
							this.rd.setColor(new Color(255, 255, 255));
							this.rd.fillRect(0, 0, 550, 400);
						}
						if (n9 >= 10) {
							medium.around(array2[record.wasted], false);
						} else {
							medium.around(array2[0], false);
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
							this.rd.setColor(new Color(255, 255, 255));
							this.rd.fillRect(0, 0, 550, 400);
						}
						if (n8 > record.whenwasted && n9 != 67) {
							++n9;
						}
						medium.around(array2[0], false);
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
					this.rd.drawImage(xtGraphics.mdness, 164, 330, null);
				}
				if (n8 >= 0) {
					xtGraphics.fleximage(this.offImage, this.rd, n8);
					if (n8 != 0) {
						if (xtGraphics.winner) {
							if (checkPoints.stage == xtGraphics.unlocked) {
								this.rd.drawImage(xtGraphics.congrd, 140, 30, null);
								if (this.u[0].enter || this.u[0].handb) {
									this.u[0].enter = false;
									this.u[0].handb = false;
								}
							} else {
								this.rd.drawImage(xtGraphics.congrd, 140, 117, null);
							}
						} else {
							this.rd.drawImage(xtGraphics.gameov, 190, 117, null);
						}
					}
				}
				if (++n8 == 6) {
					xtGraphics.fase = -5;
				}
			}
			if (xtGraphics.fase == -6) {
				xtGraphics.pauseimage(this.offImage, this.rd);
				xtGraphics.fase = -7;
				this.mouses = 0;
			}
			if (xtGraphics.fase == -7) {
				xtGraphics.pausedgame(this.rd, checkPoints.stage, this.u[0], record);
				if (n8 != 0) {
					n8 = 0;
				}
				xtGraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0]);
				if (this.mouses == 2) {
					this.mouses = 0;
				}
				if (this.mouses == 1) {
					this.mouses = 2;
				}
			}
			if (xtGraphics.fase == -8) {
				xtGraphics.cantreply(this.rd);
				if (++n8 == 150 || this.u[0].enter || this.u[0].handb || this.mouses == 1) {
					this.rd.drawImage(xtGraphics.fleximg, 0, 0, null);
					this.rd.drawImage(xtGraphics.paused, 156, 106, null);
					xtGraphics.fase = -7;
					this.mouses = 0;
					this.u[0].enter = false;
					this.u[0].handb = false;
				}
			}
			if (this.lostfcs && xtGraphics.fase != 176 && xtGraphics.fase != 111) {
				xtGraphics.nofocus(this.rd);
				if (xtGraphics.fase == 0) {
					this.u[0].enter = true;
				}
				if (this.mouses == 1 || this.mouses == 2) {
					this.lostfcs = false;
					if (xtGraphics.fase == 10) {
						xtGraphics.flipo = 0;
					}
					if (xtGraphics.fase == 11 && xtGraphics.flipo != 0) {
						final xtGraphics xtGraphics4 = xtGraphics;
						--xtGraphics4.flipo;
					}
					if (xtGraphics.fase == -7) {
						this.rd.drawImage(xtGraphics.fleximg, 0, 0, null);
					}
				}
			}
			this.repaint();
			xtGraphics.playsounds(array3[0], this.u[0], checkPoints.stage);
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
						medium.adjstfade(a);
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
				this.rd.dispose();
				xtGraphics.stopallnow();
				System.gc();
				this.gamer.stop();
				this.gamer = null;
			}
			long n72 = Math.round(a) - (time3 - time2);
			if (n72 < n) {
				n72 = n;
			}
			try {
				Thread.sleep(n72);
			} catch (InterruptedException ex) {
			}
		}
	}

	public void init() {
		this.offImage = this.createImage(550, 400);
		if (this.offImage != null) {
			this.rd = this.offImage.getGraphics();
		}
	}

	public void savecookie(final String str, final String str2) {
		try {
			//  JSObject.getWindow(this).eval("SetCookie('" + str + "','" + str2 + "');");
		} catch (Exception ex) {
		}
	}

	public boolean keyDown(final Event event, final int n) {
		if (!this.exwist) {
			if (n == 1004) {
				this.u[0].up = true;
			}
			if (n == 1005) {
				this.u[0].down = true;
			}
			if (n == 1007) {
				this.u[0].right = true;
			}
			if (n == 1006) {
				this.u[0].left = true;
			}
			if (n == 32) {
				this.u[0].handb = true;
			}
			if (n == 10 || n == 80 || n == 112 || n == 27) {
				this.u[0].enter = true;
			}
			if (n == 97 || n == 65) {
				if (this.u[0].arrace) {
					this.u[0].arrace = false;
				} else {
					this.u[0].arrace = true;
				}
			}
			if (n == 77 || n == 109) {
				if (this.u[0].mutem) {
					this.u[0].mutem = false;
				} else {
					this.u[0].mutem = true;
				}
			}
			if (n == 78 || n == 110) {
				if (this.u[0].mutes) {
					this.u[0].mutes = false;
				} else {
					this.u[0].mutes = true;
				}
			}
			if (n == 118 || n == 86) {
				++this.view;
				if (this.view == 3) {
					this.view = 0;
				}
			}
		}
		return false;
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
		this.mouses = 0;
		System.out.println("Mouse released.");
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {

	}
}
