package main.java;

import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import static main.java.Util.snapRGBs;



public class Geometry {
	Medium medium;
	Trackers trackers;
	Plane[] planes;
	public int numberOfPlanes;
	public int x;
	public int y;
	public int z;
	public float xz;
	public float xy;
	public float zy;
	public float wxz;
	public float wzy;
	public int distance;
	public int maxR;
	public int disp;
	public int disline;
	public boolean shadow;
	public boolean drawLines;
	public float grounded;
	public int grat;
	public int[] keyx;
	public int[] keyz;
	public int[] txy;
	public int[] tzy;
	public int[][] tc;
	public int[] tradx;
	public int[] tradz;
	public int[] trady;
	public int[] tx;
	public int[] ty;
	public int[] tz;
	public int[] skd;
	public int[] dam;
	public int tnt;
	public int[] sx;
	public int[] sy;
	public int[] sz;
	public int[] stg;
	public int[] dov;
	public float[] smag;
	public int[] scx;
	public int[] scz;
	public boolean[] fulls;
	public boolean elec;
	public boolean roted;
	public int[] edl;
	public int[] edr;
	public int[] elc;
	public boolean fix;
	public int fcnt;

	public void pdust(final int n, final Graphics graphics, final int n2) {
		if (n2 * this.dov[n] > 0) {
			int n3;
			if (this.fulls[n]) {
				n3 = this.stg[n] * this.stg[n];
			} else {
				n3 = this.stg[n] * this.stg[n] * this.stg[n] + 1;
			}
			int r = (this.medium.groundColor[0] * n3 + this.medium.fadeColor[0] * 2 + this.medium.skyColor[0]) / (3 + n3);
			int g = (this.medium.groundColor[1] * n3 + this.medium.fadeColor[0] * 2 + this.medium.skyColor[1]) / (3 + n3);
			int b = (this.medium.groundColor[2] * n3 + this.medium.fadeColor[0] * 2 + this.medium.skyColor[2]) / (3 + n3);
			for (int i = 0; i < this.trackers.numberOfTracks; ++i) {
				if (Math.abs(this.trackers.zy[i]) != 90 && Math.abs(this.trackers.xy[i]) != 90 && Math.abs(this.sx[n] - this.trackers.x[i]) < this.trackers.radx[i] && Math.abs(this.sz[n] - this.trackers.z[i]) < this.trackers.radz[i]) {
					if (this.trackers.skd[i] == 0) {
						n3 = this.stg[n] * this.stg[n] * this.stg[n] + 2;
					}
					r = (int) ((this.trackers.objectColor[i][0] * 0.87 * n3 + this.medium.fadeColor[0] * 2 + this.medium.skyColor[0]) / (3 + n3));
					g = (int) ((this.trackers.objectColor[i][1] * 0.87 * n3 + this.medium.fadeColor[0] * 2 + this.medium.skyColor[1]) / (3 + n3));
					b = (int) ((this.trackers.objectColor[i][2] * 0.87 * n3 + this.medium.fadeColor[0] * 2 + this.medium.skyColor[2]) / (3 + n3));
				}
			}
			if (this.sy[n] > 250) {
				this.sy[n] = 250;
			}
			final float n5 = this.medium.centerX + (int) ((this.sx[n] - this.medium.positionX - this.medium.centerX) * Util.cos(this.medium.xz) - (this.sz[n] - this.medium.positionZ - this.medium.centerZ) * Util.sin(this.medium.xz));
			final float n6 = this.medium.centerZ + (int) ((this.sx[n] - this.medium.positionX - this.medium.centerX) * Util.sin(this.medium.xz) + (this.sz[n] - this.medium.positionZ - this.medium.centerZ) * Util.cos(this.medium.xz));
			final float n7 = this.medium.centerY + (int) ((this.sy[n] - this.medium.positionY - this.medium.centerY) * Util.cos(this.medium.zy) - (n6 - this.medium.centerZ) * Util.sin(this.medium.zy));
			final float n8 = this.medium.centerZ + (int) ((this.sy[n] - this.medium.positionY - this.medium.centerY) * Util.sin(this.medium.zy) + (n6 - this.medium.centerZ) * Util.cos(this.medium.zy));
			final float n9 = (int) Math.sqrt((this.medium.centerY - n7) * (this.medium.centerY - n7) + (this.medium.centerX - n5) * (this.medium.centerX - n5) + n8 * n8);
			int n10 = 0;
			do {
				if (n9 > this.medium.fade[n10]) {
					r = (r * 3 + this.medium.fadeColor[0]) / 4;
					g = (g * 3 + this.medium.fadeColor[1]) / 4;
					b = (b * 3 + this.medium.fadeColor[2]) / 4;
				}
			} while (++n10 < 8);
			if (Math.abs(this.scx[n]) + Math.abs(this.scz[n]) > 150) {
				final int[] sy = this.sy;
				sy[n] -= (int) (3.0f + 27.0f * this.smag[n]);
			} else {
				final int[] sy2 = this.sy;
				sy2[n] -= (int) (23.0f + 7.0f * this.smag[n]);
			}
			final int[] sx = this.sx;
			sx[n] += (int) (this.scx[n] / ((this.stg[n] + 1) * this.smag[n]));
			final int[] sz = this.sz;
			sz[n] += (int) (this.scz[n] / ((this.stg[n] + 1) * this.smag[n]));
			final int[] array = new int[8];
			final int[] array2 = new int[8];
			final int n11 = this.stg[n] - 3;
			array[0] = (int) this.xs((float)(n5 - (18.0f + this.medium.random() * 18.0f + n11 * 6) * this.smag[n]), n8);
			array2[0] = (int) this.ys((float)(n7 - (7.5 + this.medium.random() * 7.5 + n11 * 2.5) * this.smag[n]), n8);
			if (array2[0] < 45 && this.medium.flex != 0) {
				this.medium.flex = 0;
			}
			array[1] = this.xs((int) (n5 - (18.0f + this.medium.random() * 18.0f + n11 * 6) * this.smag[n]), (int) n8);
			array2[1] = this.ys((int) (n7 + (7.5 + this.medium.random() * 7.5 + n11 * 2.5) * this.smag[n]), (int) n8);
			array[2] = this.xs((int) (n5 - (7.5 + this.medium.random() * 7.5 + n11 * 2.5) * this.smag[n]), (int) n8);
			array2[2] = this.ys((int) (n7 + (18.0f + this.medium.random() * 18.0f + n11 * 6) * this.smag[n]), (int) n8);
			array[3] = this.xs((int) (n5 + (7.5 + this.medium.random() * 7.5 + n11 * 2.5) * this.smag[n]), (int) n8);
			array2[3] = this.ys((int) (n7 + (18.0f + this.medium.random() * 18.0f + n11 * 6) * this.smag[n]), (int) n8);
			array[4] = this.xs((int) (n5 + (18.0f + this.medium.random() * 18.0f + n11 * 6) * this.smag[n]), (int) n8);
			array2[4] = this.ys((int) (n7 + (7.5 + this.medium.random() * 7.5 + n11 * 2.5) * this.smag[n]), (int) n8);
			array[5] = this.xs((int) (n5 + (18.0f + this.medium.random() * 18.0f + n11 * 6) * this.smag[n]), (int) n8);
			array2[5] = this.ys((int) (n7 - (7.5 + this.medium.random() * 7.5 + n11 * 2.5) * this.smag[n]), (int) n8);
			array[6] = this.xs((int) (n5 + (7.5 + this.medium.random() * 7.5 + n11 * 2.5) * this.smag[n]), (int) n8);
			array2[6] = this.ys((int) (n7 - (18.0f + this.medium.random() * 18.0f + n11 * 6) * this.smag[n]), (int) n8);
			array[7] = this.xs((int) (n5 - (7.5 + this.medium.random() * 7.5 + n11 * 2.5) * this.smag[n]), (int) n8);
			array2[7] = this.ys((int) (n7 - (18.0f + this.medium.random() * 18.0f + n11 * 6) * this.smag[n]), (int) n8);
			boolean b2 = true;
			int n12 = 0;
			int n13 = 0;
			int n14 = 0;
			int n15 = 0;
			int n16 = 0;
			do {
				if (array2[n16] < 0 || n8 < 10) {
					++n12;
				}
				if (array2[n16] > this.medium.height || n8 < 10) {
					++n13;
				}
				if (array[n16] < 0 || n8 < 10) {
					++n14;
				}
				if (array[n16] > this.medium.width || n8 < 10) {
					++n15;
				}
				if (array2[n16] < 45 && this.medium.flex != 0) {
					this.medium.flex = 0;
				}
			} while (++n16 < 8);
			if (n14 == 4 || n12 == 4 || n13 == 4 || n15 == 4) {
				b2 = false;
			}
			if (b2) {
				graphics.setColor(new Color(r, g, b));
				graphics.fillPolygon(array, array2, 8);
			}
			if (this.dov[n] == 1) {
				this.dov[n] = -1;
			}
			if (this.stg[n] == 4) {
				this.stg[n] = 0;
			} else {
				final int[] stg = this.stg;
				++stg[n];
				if (this.stg[n] == 2 && this.fulls[n]) {
					this.dov[n] = 0;
				}
			}
		} else if (this.dov[n] == 0) {
			this.dov[n] = 1;
		}
	}

	public int ys(final int n, int n2) {
		n2 = Math.max(50, n2);
		return (int) ((n2 - this.medium.focusPoint) * (this.medium.centerY - n) / n2 + n);
	}

	public float ys(final float n, float n2) {
		n2 = Math.max(50, n2);
		return ((n2 - this.medium.focusPoint) * (this.medium.centerY - n) / n2 + n);
	}

	public Geometry(final byte[] modelBytes, final Medium medium, final Trackers trackers) {
		this.numberOfPlanes = 0;
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.xz = 0;
		this.xy = 0;
		this.zy = 0;
		this.wxz = 0;
		this.wzy = 0;
		this.distance = 0;
		this.maxR = 0;
		this.disp = 0;
		this.disline = 7;
		this.shadow = false;
		this.drawLines = true;
		this.grounded = 1.0f;
		this.grat = 0;
		this.keyx = new int[4];
		this.keyz = new int[4];
		this.tnt = 0;
		this.sx = new int[4];
		this.sy = new int[4];
		this.sz = new int[4];
		this.stg = new int[4];
		this.dov = new int[4];
		this.smag = new float[4];
		this.scx = new int[4];
		this.scz = new int[4];
		this.fulls = new boolean[4];
		this.elec = false;
		this.roted = false;
		this.edl = new int[2];
		this.edr = new int[2];
		this.elc = new int[2];
		this.fix = false;
		this.fcnt = 0;
		this.medium = medium;
		this.trackers = trackers;
		this.planes = new Plane[120];
		int n = 0;
		int n2 = 0;
		int n3 = 0;
		float n4 = 1.0f;
		final int[] array2 = new int[100];
		final int[] array3 = new int[100];
		final int[] array4 = new int[100];
		final int[] color = new int[3];
		boolean isGlass = false;
		boolean b2 = false;
		final Wheels wheels = new Wheels();
		int n5 = 0;
		int getvalue = 1;

		String string = "";
		try {
			final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(modelBytes));
			String line;
			while ((line = dataInputStream.readLine()) != null) {
				string = "" + line.trim();
				if (string.startsWith("<p>")) {
					n = 1;
					n3 = 0;
					getvalue = 0;
				}
				if (n != 0) {
					if (string.startsWith("gr")) {
						getvalue = this.getvalue("gr", string, 0);
					}
					if (string.startsWith("c")) {
						isGlass = false;
						color[0] = this.getvalue("c", string, 0);
						color[1] = this.getvalue("c", string, 1);
						color[2] = this.getvalue("c", string, 2);
					}
					if (string.startsWith("glass")) {
						isGlass = true;
					}
					if (string.startsWith("p")) {
						array2[n3] = (int) (this.getvalue("p", string, 0) * n4);
						array3[n3] = (int) (this.getvalue("p", string, 1) * n4);
						array4[n3] = (int) (this.getvalue("p", string, 2) * n4);
						final int maxR = (int) Math.sqrt(array2[n3] * array2[n3] + array3[n3] * array3[n3] + array4[n3] * array4[n3]);
						if (maxR > this.maxR) {
							this.maxR = maxR;
						}
						++n3;
					}
				}
				if (string.startsWith("</p>")) {
					this.planes[this.numberOfPlanes] = new Plane(this.medium, this.trackers, array2, array4, array3, n3, color, isGlass, getvalue, 0, 0, 0, this.disline, 0, b2);
					++this.numberOfPlanes;
					n = 0;
				}
				if (string.startsWith("w")) {
					this.keyx[n5] = (int) (this.getvalue("w", string, 0) * n4);
					this.keyz[n5] = (int) (this.getvalue("w", string, 2) * n4);
					++n5;
					wheels.make(this.medium, this.trackers, this.planes, this.numberOfPlanes, (int) (this.getvalue("w", string, 0) * n4), (int) (this.getvalue("w", string, 1) * n4), (int) (this.getvalue("w", string, 2) * n4), this.getvalue("w", string, 3), (int) (this.getvalue("w", string, 4) * n4), (int) (this.getvalue("w", string, 5) * n4));
					this.numberOfPlanes += 9;
				}
				if (string.startsWith("tracks")) {
					final int getvalue2 = this.getvalue("tracks", string, 0);
					this.txy = new int[getvalue2];
					this.tzy = new int[getvalue2];
					this.tc = new int[getvalue2][3];
					this.tradx = new int[getvalue2];
					this.tradz = new int[getvalue2];
					this.trady = new int[getvalue2];
					this.tx = new int[getvalue2];
					this.ty = new int[getvalue2];
					this.tz = new int[getvalue2];
					this.skd = new int[getvalue2];
					this.dam = new int[getvalue2];
				}
				if (string.startsWith("<track>")) {
					n2 = 1;
					this.dam[this.tnt] = 1;
					this.skd[this.tnt] = 0;
					this.ty[this.tnt] = 0;
					this.tx[this.tnt] = 0;
					this.tz[this.tnt] = 0;
					this.txy[this.tnt] = 0;
					this.tzy[this.tnt] = 0;
					this.trady[this.tnt] = 0;
					this.tradx[this.tnt] = 0;
					this.tradz[this.tnt] = 0;
					this.tc[this.tnt][0] = 0;
					this.tc[this.tnt][1] = 0;
					this.tc[this.tnt][2] = 0;
				}
				if (n2 != 0) {
					if (string.startsWith("c")) {
						this.tc[this.tnt][0] = this.getvalue("c", string, 0);
						this.tc[this.tnt][1] = this.getvalue("c", string, 1);
						this.tc[this.tnt][2] = this.getvalue("c", string, 2);
					}
					if (string.startsWith("xy")) {
						this.txy[this.tnt] = this.getvalue("xy", string, 0);
					}
					if (string.startsWith("zy")) {
						this.tzy[this.tnt] = this.getvalue("zy", string, 0);
					}
					if (string.startsWith("radx")) {
						this.tradx[this.tnt] = (int) (this.getvalue("radx", string, 0) * n4);
					}
					if (string.startsWith("rady")) {
						this.trady[this.tnt] = (int) (this.getvalue("rady", string, 0) * n4);
					}
					if (string.startsWith("radz")) {
						this.tradz[this.tnt] = (int) (this.getvalue("radz", string, 0) * n4);
					}
					if (string.startsWith("ty")) {
						this.ty[this.tnt] = (int) (this.getvalue("ty", string, 0) * n4);
					}
					if (string.startsWith("tx")) {
						this.tx[this.tnt] = (int) (this.getvalue("tx", string, 0) * n4);
					}
					if (string.startsWith("tz")) {
						this.tz[this.tnt] = (int) (this.getvalue("tz", string, 0) * n4);
					}
					if (string.startsWith("skid")) {
						this.skd[this.tnt] = this.getvalue("skid", string, 0);
					}
					if (string.startsWith("dam")) {
						this.dam[this.tnt] = 3;
					}
				}
				if (string.startsWith("</track>")) {
					n2 = 0;
					++this.tnt;
				}
				if (string.startsWith("disp")) {
					this.disp = this.getvalue("disp", string, 0);
				}
				if (string.startsWith("disline")) {
					this.disline = this.getvalue("disline", string, 0);
				}
				if (string.startsWith("shadow")) {
					this.shadow = true;
				}
				if (string.startsWith("stonecold")) {
					this.drawLines = false;
				}
				if (string.startsWith("road")) {
					b2 = true;
				}
				if (string.startsWith("notroad")) {
					b2 = false;
				}
				if (string.startsWith("grounded")) {
					this.grounded = this.getvalue("grounded", string, 0) / 100.0f;
				}
				if (string.startsWith("div")) {
					n4 = this.getvalue("div", string, 0) / 10.0f;
				}
			}
			dataInputStream.close();
		} catch (Exception obj) {
			System.out.println("ContO Loading Error: " + obj);
			System.out.println("At File: " + Arrays.toString(modelBytes) + ".rad");
			System.out.println("At Line: " + string);
			System.out.println("--------------------");
		}
		this.grat = wheels.ground;
	}

	public Geometry(final Geometry geometry, final int x, final int y, final int z, final int a) {
		this.numberOfPlanes = 0;
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.xz = 0;
		this.xy = 0;
		this.zy = 0;
		this.wxz = 0;
		this.wzy = 0;
		this.distance = 0;
		this.maxR = 0;
		this.disp = 0;
		this.disline = 7;
		this.shadow = false;
		this.drawLines = true;
		this.grounded = 1.0f;
		this.grat = 0;
		this.keyx = new int[4];
		this.keyz = new int[4];
		this.tnt = 0;
		this.sx = new int[4];
		this.sy = new int[4];
		this.sz = new int[4];
		this.stg = new int[4];
		this.dov = new int[4];
		this.smag = new float[4];
		this.scx = new int[4];
		this.scz = new int[4];
		this.fulls = new boolean[4];
		this.elec = false;
		this.roted = false;
		this.edl = new int[2];
		this.edr = new int[2];
		this.elc = new int[2];
		this.fix = false;
		this.fcnt = 0;
		this.medium = geometry.medium;
		this.trackers = geometry.trackers;
		this.numberOfPlanes = geometry.numberOfPlanes;
		this.maxR = geometry.maxR;
		this.disp = geometry.disp;
		this.disline = geometry.disline;
		this.drawLines = geometry.drawLines;
		this.shadow = geometry.shadow;
		this.grounded = geometry.grounded;
		this.grat = geometry.grat;
		this.planes = new Plane[geometry.numberOfPlanes];
		for (int i = 0; i < this.numberOfPlanes; ++i) {
			this.planes[i] = new Plane(this.medium, this.trackers, geometry.planes[i].ox, geometry.planes[i].oz, geometry.planes[i].oy, geometry.planes[i].numberOfPoints, geometry.planes[i].oc, geometry.planes[i].isGlass, geometry.planes[i].gr, geometry.planes[i].wx, geometry.planes[i].wy, geometry.planes[i].wz, geometry.disline, geometry.planes[i].bfase, geometry.planes[i].isRoad);
		}
		this.x = x;
		this.y = y;
		this.z = z;
		this.xz = 0;
		this.xy = 0;
		this.zy = 0;
		for (int j = 0; j < this.numberOfPlanes; ++j) {
			this.planes[j].master = geometry.planes[j].master;
			this.planes[j].rot(this.planes[j].ox, this.planes[j].oz, 0, 0, a, this.planes[j].numberOfPoints);
			this.planes[j].loadprojf();
		}
		if (geometry.tnt != 0) {
			for (int k = 0; k < geometry.tnt; ++k) {
				this.trackers.xy[this.trackers.numberOfTracks] = (geometry.txy[k] * Util.cos(a) - geometry.tzy[k] * Util.sin(a));
				this.trackers.zy[this.trackers.numberOfTracks] = (geometry.tzy[k] * Util.cos(a) + geometry.txy[k] * Util.sin(a));
				int n = 0;
				do {
					this.trackers.objectColor[this.trackers.numberOfTracks][n] = (int) (geometry.tc[k][n] + geometry.tc[k][n] * (this.medium.snapColor[n] / 100.0f));
					if (this.trackers.objectColor[this.trackers.numberOfTracks][n] > 255) {
						this.trackers.objectColor[this.trackers.numberOfTracks][n] = 255;
					}
					if (this.trackers.objectColor[this.trackers.numberOfTracks][n] < 0) {
						this.trackers.objectColor[this.trackers.numberOfTracks][n] = 0;
					}
				} while (++n < 3);
				this.trackers.x[this.trackers.numberOfTracks] = (int) (this.x + geometry.tx[k] * Util.cos(a) - geometry.tz[k] * Util.sin(a));
				this.trackers.z[this.trackers.numberOfTracks] = (int) (this.z + geometry.tz[k] * Util.cos(a) + geometry.tx[k] * Util.sin(a));
				this.trackers.y[this.trackers.numberOfTracks] = this.y + geometry.ty[k];
				this.trackers.skd[this.trackers.numberOfTracks] = geometry.skd[k];
				this.trackers.dam[this.trackers.numberOfTracks] = geometry.dam[k];
				int abs = Math.abs(a);
				if (abs == 180) {
					abs = 0;
				}
				this.trackers.radx[this.trackers.numberOfTracks] = (int) (geometry.tradx[k] * Util.cos(abs) + geometry.tradz[k] * Util.sin(abs));
				this.trackers.radz[this.trackers.numberOfTracks] = (int) (geometry.tradx[k] * Util.sin(abs) + geometry.tradz[k] * Util.cos(abs));
				this.trackers.rady[this.trackers.numberOfTracks] = geometry.trady[k];
				final Trackers t = this.trackers;
				++t.numberOfTracks;
			}
		}
		int n2 = 0;
		do {
			this.stg[n2] = 0;
			this.keyx[n2] = geometry.keyx[n2];
			this.keyz[n2] = geometry.keyz[n2];
		} while (++n2 < 4);
	}

	public void draw(final Graphics graphics) {
		if (this.distance != 0) {
			this.distance = 0;
		}
		final float n = this.medium.centerX +  ((this.x - this.medium.positionX - this.medium.centerX) * Util.cos(this.medium.xz) - (this.z - this.medium.positionZ - this.medium.centerZ) * Util.sin(this.medium.xz));
		final float n2 = this.medium.centerZ + ((this.x - this.medium.positionX - this.medium.centerX) * Util.sin(this.medium.xz) + (this.z - this.medium.positionZ - this.medium.centerZ) * Util.cos(this.medium.xz));
		final float n3 = this.medium.centerZ + ((this.y - this.medium.positionY - this.medium.centerY) * Util.sin(this.medium.zy) + (n2 - this.medium.centerZ) * Util.cos(this.medium.zy));
		if (this.xs(n + this.maxR, n3) > 0 && this.xs(n - this.maxR, n3) < this.medium.width && n3 > -this.maxR && (n3 < this.medium.fade[this.disline] + this.maxR || this.medium.trk) && (this.xs(n + this.maxR, n3) - this.xs(n - this.maxR, n3) > this.disp || this.medium.trk)) {
			if (this.shadow) {
				if (!this.medium.crs) {
					if (n3 < 5000) {
						boolean b = false;
						for (int i = this.trackers.numberOfTracks - 1; i >= 0; --i) {
							if (Math.abs(this.trackers.zy[i]) != 90 && Math.abs(this.trackers.xy[i]) != 90 && Math.abs(this.x - this.trackers.x[i]) < this.trackers.radx[i] + this.maxR && Math.abs(this.z - this.trackers.z[i]) < this.trackers.radz[i] + this.maxR) {
								b = true;
								break;
							}
						}
						if (b) {
							for (int j = 0; j < this.numberOfPlanes; ++j) {
								this.planes[j].drawShadow(graphics, this.x - this.medium.positionX, this.y - this.medium.positionY, this.z - this.medium.positionZ, this.xz, this.xy, this.zy, 0);
							}
						} else {
							final float n4 = this.medium.centerY + ((this.medium.ground - this.medium.centerY) * Util.cos(this.medium.zy) - (n2 - this.medium.centerZ) * Util.sin(this.medium.zy));
							final float n5 = this.medium.centerZ + ((this.medium.ground - this.medium.centerY) * Util.sin(this.medium.zy) + (n2 - this.medium.centerZ) * Util.cos(this.medium.zy));
							if (this.ys(n4 + this.maxR, n5) > 0 && this.ys(n4 - this.maxR, n5) < this.medium.height) {
								for (int k = 0; k < this.numberOfPlanes; ++k) {
									this.planes[k].drawShadow(graphics, this.x - this.medium.positionX, this.y - this.medium.positionY, this.z - this.medium.positionZ, this.xz, this.xy, this.zy, 1);
								}
							}
						}
						this.medium.addsp(this.x - this.medium.positionX, this.z - this.medium.positionZ, (int) (this.maxR * 0.8));
					} else {
						this.lowPolyShadow(graphics, (int) n3);
					}
				} else {
					for (int l = 0; l < this.numberOfPlanes; ++l) {
						this.planes[l].drawShadow(graphics, this.x - this.medium.positionX, this.y - this.medium.positionY, this.z - this.medium.positionZ, this.xz, this.xy, this.zy, 2);
					}
				}
			}
			final float n6 = this.medium.centerY + ((this.y - this.medium.positionY - this.medium.centerY) * Util.cos(this.medium.zy) - (n2 - this.medium.centerZ) * Util.sin(this.medium.zy));
			if (this.ys(n6 + this.maxR, n3) > 0 && this.ys(n6 - this.maxR, n3) < this.medium.height) {
				if (this.elec) {
					this.electrify(graphics);
				}
				if (this.fix) {
					this.fixIt(graphics);
				}
				final int[] array = new int[this.numberOfPlanes];
				int n7 = 0;
				do {
					if (this.stg[n7] != 0) {
						this.pdust(n7, graphics, -1);
					}
				} while (++n7 < 4);
				for (int n8 = 0; n8 < this.numberOfPlanes; ++n8) {
					array[n8] = 0;
				}
				for (int n9 = 0; n9 < this.numberOfPlanes; ++n9) {
					for (int n10 = n9 + 1; n10 < this.numberOfPlanes; ++n10) {
						if (this.planes[n9].av != this.planes[n10].av) {
							if (this.planes[n9].av < this.planes[n10].av) {
								++array[n9];
							} else {
								++array[n10];
							}
						} else if (n9 > n10) {
							++array[n9];
						} else {
							++array[n10];
						}
					}
				}
				for (int i = 0; i < this.numberOfPlanes; ++i) {
					for (int j = 0; j < this.numberOfPlanes; ++j) {
						if (array[j] == i) {
							this.planes[j].draw(graphics, this.x - this.medium.positionX, this.y - this.medium.positionY, this.z - this.medium.positionZ, this.xz, this.xy, this.zy, this.wxz, this.wzy, this.drawLines);
							if (this.planes[j].master != 0 && this.stg[this.planes[j].master - 1] != 0) {
								this.pdust(this.planes[j].master - 1, graphics, 1);
							}
						}
					}
				}
				this.distance = (int) (Math.sqrt((int) Math.sqrt((this.medium.positionX + this.medium.centerX - this.x) * (this.medium.positionX + this.medium.centerX - this.x) + (this.medium.positionZ - this.z) * (this.medium.positionZ - this.z) + (this.medium.positionY + this.medium.centerY - this.y) * (this.medium.positionY + this.medium.centerY - this.y))) * this.grounded);
			}
		}
		if (this.distance == 0) {
			int n17 = 0;
			do {
				if (this.stg[n17] != 0) {
					if (this.stg[n17] == 4) {
						this.stg[n17] = 0;
					} else {
						this.stg[n17]++;
					}
				}
			} while (++n17 < 4);
		}
	}

	public void rot(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
		if (n3 != 0) {
			for (int i = 0; i < n4; ++i) {
				final int n5 = array[i];
				final int n6 = array2[i];
				array[i] = n + (int) ((n5 - n) * Util.cos(n3) - (n6 - n2) * Util.sin(n3));
				array2[i] = n2 + (int) ((n5 - n) * Util.sin(n3) + (n6 - n2) * Util.cos(n3));
			}
		}
	}

	public void rot(final int[] array, final int[] array2, final float n, final float n2, final float n3, final int n4) {
		if (n3 != 0) {
			for (int i = 0; i < n4; ++i) {
				final int n5 = array[i];
				final int n6 = array2[i];
				array[i] = (int) (n + ((n5 - n) * Util.cos(n3) - (n6 - n2) * Util.sin(n3)));
				array2[i] = (int) (n2 + ((n5 - n) * Util.sin(n3) + (n6 - n2) * Util.cos(n3)));
			}
		}
	}

	public void dust(final int n, final float n2, final float n3, final float n4, final float a, final float a2, final float n5, final boolean b, final int n6) {
		boolean b2 = false;
		if (n6 > 5 && (n == 0 || n == 2)) {
			b2 = true;
		}
		if (n6 < -5 && (n == 1 || n == 3)) {
			b2 = true;
		}
		if (this.stg[n] == 0 && Math.abs(a) + Math.abs(a2) > 100.0f && !b2) {
			this.sx[n] = (int) n2;
			this.sy[n] = (int) n3;
			this.sz[n] = (int) n4;
			this.stg[n] = 1;
			this.dov[n] = -1;
			this.smag[n] = n5;
			this.scx[n] = (int) a;
			this.scz[n] = (int) a2;
			this.fulls[n] = b;
		}
	}

	public int getvalue(final String s, final String s2, final int n) {
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
		return Integer.parseInt(string.toString());
	}

	public int xs(final int n, int n2) {
		if (n2 < 50) {
			n2 = 50;
		}
		return (int) ((n2 - this.medium.focusPoint) * (this.medium.centerX - n) / n2 + n);
	}

	public float xs(final float n, float n2) {
		if (n2 < 50) {
			n2 = 50;
		}
		return (n2 - this.medium.focusPoint) * (this.medium.centerX - n) / n2 + n;
	}

	public void lowPolyShadow(final Graphics graphics, final int n) {
		final int[] xPoints = new int[4];
		final int[] yPoints = new int[4];
		final int[] zPoints = new int[4];
		int n2 = 1;
		float i = Math.abs(this.zy) % 360;
		if (Math.abs(i) > 90) {
			n2 = -1;
		}
		xPoints[0] = (int) (this.keyx[0] * 1.2 + this.x - this.medium.positionX);
		zPoints[0] = (int) (this.keyz[0] * n2 * 1.4 + this.z - this.medium.positionZ);
		xPoints[1] = (int) (this.keyx[1] * 1.2 + this.x - this.medium.positionX);
		zPoints[1] = (int) (this.keyz[1] * n2 * 1.4 + this.z - this.medium.positionZ);
		xPoints[2] = (int) (this.keyx[3] * 1.2 + this.x - this.medium.positionX);
		zPoints[2] = (int) (this.keyz[3] * n2 * 1.4 + this.z - this.medium.positionZ);
		xPoints[3] = (int) (this.keyx[2] * 1.2 + this.x - this.medium.positionX);
		zPoints[3] = (int) (this.keyz[2] * n2 * 1.4 + this.z - this.medium.positionZ);
		this.rot(xPoints, zPoints, this.x - this.medium.positionX, this.z - this.medium.positionZ, this.xz, 4);
		int r = (int) (this.medium.groundColor[0] / 1.5);
		int g = (int) (this.medium.groundColor[1] / 1.5);
		int b = (int) (this.medium.groundColor[2] / 1.5);
		int n3 = 0;
		do {
			yPoints[n3] = (int) this.medium.ground;
		} while (++n3 < 4);
		for (int j = this.trackers.numberOfTracks - 1; j >= 0; --j) {
			int n4 = 0;
			int n5 = 0;
			do {
				if (Math.abs(this.trackers.zy[j]) != 90 && Math.abs(this.trackers.xy[j]) != 90 && Math.abs(xPoints[n5] - (this.trackers.x[j] - this.medium.positionX)) < this.trackers.radx[j] && Math.abs(zPoints[n5] - (this.trackers.z[j] - this.medium.positionZ)) < this.trackers.radz[j]) {
					++n4;
				}
			} while (++n5 < 4);
			if (n4 > 2) {
				int n6 = 0;
				do {
					yPoints[n6] = this.trackers.y[j] - this.medium.positionY;
					if (this.trackers.zy[j] != 0) {
						yPoints[n6] += (int) ((zPoints[n6] - (this.trackers.z[j] - this.medium.positionZ - this.trackers.radz[j])) * Util.sin(this.trackers.zy[j]) / Util.sin(90 - this.trackers.zy[j]) - this.trackers.radz[j] * Util.sin(this.trackers.zy[j]) / Util.sin(90 - this.trackers.zy[j]));
					}
					if (this.trackers.xy[j] != 0) {
						yPoints[n6] += (int) ((xPoints[n6] - (this.trackers.x[j] - this.medium.positionX - this.trackers.radx[j])) * Util.sin(this.trackers.xy[j]) / Util.sin(90 - this.trackers.xy[j]) - this.trackers.radx[j] * Util.sin(this.trackers.xy[j]) / Util.sin(90 - this.trackers.xy[j]));
					}
				} while (++n6 < 4);
				r = (int) ((float) this.trackers.objectColor[j][0] / 1.5);
				g = (int) ((float) this.trackers.objectColor[j][1] / 1.5);
				b = (int) ((float) this.trackers.objectColor[j][2] / 1.5);
				break;
			}
		}
		this.rot(xPoints, zPoints, this.medium.centerX, this.medium.centerZ, this.medium.xz, 4);
		this.rot(yPoints, zPoints, this.medium.centerY, this.medium.centerZ, this.medium.zy, 4);
		boolean b2 = true;
		int n9 = 0;
		int n10 = 0;
		int n11 = 0;
		int n12 = 0;
		int n13 = 0;
		do {
			xPoints[n13] = this.xs(xPoints[n13], zPoints[n13]);
			yPoints[n13] = this.ys(yPoints[n13], zPoints[n13]);
			if (yPoints[n13] < 0 || zPoints[n13] < 10) {
				++n9;
			}
			if (yPoints[n13] > this.medium.height || zPoints[n13] < 10) {
				++n10;
			}
			if (xPoints[n13] < 0 || zPoints[n13] < 10) {
				++n11;
			}
			if (xPoints[n13] > this.medium.width || zPoints[n13] < 10) {
				++n12;
			}
		} while (++n13 < 4);
		if (n11 == 4 || n9 == 4 || n10 == 4 || n12 == 4) {
			b2 = false;
		}
		if (b2) {
			for(int j = 0; j < 8; j++) {
				if (n > this.medium.fade[j]) {
					r = (r * 3 + this.medium.fadeColor[0]) / 4;
					g = (g * 3 + this.medium.fadeColor[1]) / 4;
					b = (b * 3 + this.medium.fadeColor[2]) / 4;
				}
			}
			graphics.setColor(new Color(r, g, b));
			graphics.fillPolygon(xPoints, yPoints, 4);
		}
	}

	// FIXME: not sure if this is the car fixed ring
	public void fixIt(final Graphics graphics) {


		if (this.fcnt == 1) {
			for (int i = 0; i < this.numberOfPlanes; ++i) {
				Plane currentPlane = this.planes[i];
				currentPlane.hsb[0] = 0.8f;
				currentPlane.hsb[1] = 0.8f;
				currentPlane.hsb[2] = 0.8f;
				final Color hsbColor = Color.getHSBColor(currentPlane.hsb[0], currentPlane.hsb[1], currentPlane.hsb[2]);

				int[] RGBs = snapRGBs(hsbColor.getRGB(), this.medium.snapColor);

				Color.RGBtoHSB(RGBs[0], RGBs[1], RGBs[2], currentPlane.hsb);
				currentPlane.flx = 1;
			}
		}
		if (this.fcnt == 2) {
			for (int j = 0; j < this.numberOfPlanes; ++j) {
				this.planes[j].flx = 1;
			}
		}
		if (this.fcnt == 4) {
			for (int k = 0; k < this.numberOfPlanes; ++k) {
				this.planes[k].flx = 3;
			}
		}
		if (this.fcnt == 1 || this.fcnt > 2) {
			final int[] xCoordinates = new int[8];
			final int[] yCoordinates = new int[8];
			final int[] array3 = new int[4];
			int n = 0;
			do {
				xCoordinates[n] = this.keyx[n] + this.x - this.medium.positionX;
				yCoordinates[n] = this.grat + this.y - this.medium.positionY;
				array3[n] = this.keyz[n] + this.z - this.medium.positionZ;
			} while (++n < 4);
			this.rot(xCoordinates, yCoordinates, this.x - this.medium.positionX, this.y - this.medium.positionY, this.xy, 4);
			this.rot(yCoordinates, array3, this.y - this.medium.positionY, this.z - this.medium.positionY, this.zy, 4);
			this.rot(xCoordinates, array3, this.x - this.medium.positionX, this.z - this.medium.positionZ, this.xz, 4);
			this.rot(xCoordinates, array3, this.medium.centerX, this.medium.centerZ, this.medium.xz, 4);
			this.rot(yCoordinates, array3, this.medium.centerY, this.medium.centerZ, this.medium.zy, 4);
			int abs = 0;
			int abs2 = 0;
			int py = 0;
			int n2 = 0;
			do {
				int n3 = 0;
				do {
					if (Math.abs(xCoordinates[n2] - xCoordinates[n3]) > abs) {
						abs = Math.abs(xCoordinates[n2] - xCoordinates[n3]);
					}
					if (Math.abs(yCoordinates[n2] - yCoordinates[n3]) > abs2) {
						abs2 = Math.abs(yCoordinates[n2] - yCoordinates[n3]);
					}
					if (this.py(xCoordinates[n2], xCoordinates[n3], yCoordinates[n2], yCoordinates[n3]) > py) {
						py = this.py(xCoordinates[n2], xCoordinates[n3], yCoordinates[n2], yCoordinates[n3]);
					}
				} while (++n3 < 4);
			} while (++n2 < 4);
			final int n4 = (int) (Math.sqrt(py) / 1.5);
			if (abs < n4) {
				abs = n4;
			}
			if (abs2 < n4) {
				abs2 = n4;
			}
			final float n5 = this.medium.centerX + (int) ((this.x - this.medium.positionX - this.medium.centerX) * Util.cos(this.medium.xz) - (this.z - this.medium.positionZ - this.medium.centerZ) * Util.sin(this.medium.xz));
			final float n6 = this.medium.centerZ + (int) ((this.x - this.medium.positionX - this.medium.centerX) * Util.sin(this.medium.xz) + (this.z - this.medium.positionZ - this.medium.centerZ) * Util.cos(this.medium.xz));
			final float n7 = this.medium.centerY + (int) ((this.y - this.medium.positionY - this.medium.centerY) * Util.cos(this.medium.zy) - (n6 - this.medium.centerZ) * Util.sin(this.medium.zy));
			final float n8 = this.medium.centerZ + (int) ((this.y - this.medium.positionY - this.medium.centerY) * Util.sin(this.medium.zy) + (n6 - this.medium.centerZ) * Util.cos(this.medium.zy));
			xCoordinates[0] = (int) this.xs((float)(n5 - abs / 0.8 - this.medium.random() * (abs / 2.4)), n8);
			yCoordinates[0] = (int) this.ys((float) (n7 - abs2 / 1.92 - this.medium.random() * (abs2 / 5.67)), n8);
			xCoordinates[1] = (int) this.xs((float) (n5 - abs / 0.8 - this.medium.random() * (abs / 2.4)), n8);
			yCoordinates[1] = (int) this.ys((float) (n7 + abs2 / 1.92 + this.medium.random() * (abs2 / 5.67)), n8);
			xCoordinates[2] = (int) this.xs((float) (n5 - abs / 1.92 - this.medium.random() * (abs / 5.67)), n8);
			yCoordinates[2] = (int) this.ys((float) (n7 + abs2 / 0.8 + this.medium.random() * (abs2 / 2.4)), n8);
			xCoordinates[3] = (int) this.xs((float) (n5 + abs / 1.92 + this.medium.random() * (abs / 5.67)), n8);
			yCoordinates[3] = (int) this.ys((float) (n7 + abs2 / 0.8 + this.medium.random() * (abs2 / 2.4)), n8);
			xCoordinates[4] = (int) this.xs((float) (n5 + abs / 0.8 + this.medium.random() * (abs / 2.4)), n8);
			yCoordinates[4] = (int) this.ys((float) (n7 + abs2 / 1.92 + this.medium.random() * (abs2 / 5.67)), n8);
			xCoordinates[5] = (int) this.xs((float) (n5 + abs / 0.8 + this.medium.random() * (abs / 2.4)), n8);
			yCoordinates[5] = (int) this.ys((float) (n7 - abs2 / 1.92 - this.medium.random() * (abs2 / 5.67)), n8);
			xCoordinates[6] = (int) this.xs((float) (n5 + abs / 1.92 + this.medium.random() * (abs / 5.67)), n8);
			yCoordinates[6] = (int) this.ys((float) (n7 - abs2 / 0.8 - this.medium.random() * (abs2 / 2.4)), n8);
			xCoordinates[7] = (int) this.xs((float) (n5 - abs / 1.92 - this.medium.random() * (abs / 5.67)), n8);
			yCoordinates[7] = (int) this.ys((float) (n7 - abs2 / 0.8 - this.medium.random() * (abs2 / 2.4)), n8);
			if (this.fcnt == 3) {
				this.rot(xCoordinates, yCoordinates, this.xs(n5, n8), this.ys(n7, n8), 22, 8);
			}
			if (this.fcnt == 4) {
				this.rot(xCoordinates, yCoordinates, this.xs(n5, n8), this.ys(n7, n8), 22, 8);
			}
			if (this.fcnt == 5) {
				this.rot(xCoordinates, yCoordinates, this.xs(n5, n8), this.ys(n7, n8), 0, 8);
			}
			if (this.fcnt == 6) {
				this.rot(xCoordinates, yCoordinates, this.xs(n5, n8), this.ys(n7, n8), -22, 8);
			}
			if (this.fcnt == 7) {
				this.rot(xCoordinates, yCoordinates, this.xs(n5, n8), this.ys(n7, n8), -22, 8);
			}
			int r2 = (int) (191.0f + 191.0f * (this.medium.snapColor[0] / 200.0f));
			r2 = Util.clamp(r2, 0, 255);

			int g2 = (int) (232.0f + 232.0f * (this.medium.snapColor[1] / 200.0f));
			g2 = Util.clamp(g2, 0, 255);

			int b2 = (int) (255.0f + 255.0f * (this.medium.snapColor[2] / 200.0f));
			b2 = Util.clamp(b2, 0, 255);

			graphics.setColor(new Color(r2, g2, b2));
			graphics.fillPolygon(xCoordinates, yCoordinates, 8);
			xCoordinates[0] = (int) this.xs(n5 - abs - this.medium.random() * (abs / 4), n8);
			yCoordinates[0] = (int) this.ys((float) (n7 - abs2 / 2.4 - this.medium.random() * (abs2 / 9.6)), n8);
			xCoordinates[1] = (int) this.xs(n5 - abs - this.medium.random() * (abs / 4), n8);
			yCoordinates[1] = (int) this.ys((float) (n7 + abs2 / 2.4 + this.medium.random() * (abs2 / 9.6)), n8);
			xCoordinates[2] = (int) this.xs((float) (n5 - abs / 2.4 - this.medium.random() * (abs / 9.6)), n8);
			yCoordinates[2] = (int) this.ys(n7 + abs2 + this.medium.random() * (abs2 / 4), n8);
			xCoordinates[3] = (int) this.xs((float) (n5 + abs / 2.4 + this.medium.random() * (abs / 9.6)), n8);
			yCoordinates[3] = (int) this.ys(n7 + abs2 + this.medium.random() * (abs2 / 4), n8);
			xCoordinates[4] = (int) this.xs(n5 + abs + this.medium.random() * (abs / 4), n8);
			yCoordinates[4] = (int) this.ys((float) (n7 + abs2 / 2.4 + this.medium.random() * (abs2 / 9.6)), n8);
			xCoordinates[5] = (int) this.xs(n5 + abs + this.medium.random() * (abs / 4), n8);
			yCoordinates[5] = (int) this.ys((float) (n7 - abs2 / 2.4 - this.medium.random() * (abs2 / 9.6)), n8);
			xCoordinates[6] = (int) this.xs((float) (n5 + abs / 2.4 + this.medium.random() * (abs / 9.6)), n8);
			yCoordinates[6] = (int) this.ys(n7 - abs2 - this.medium.random() * (abs2 / 4), n8);
			xCoordinates[7] = (int) this.xs((float) (n5 - abs / 2.4 - this.medium.random() * (abs / 9.6)), n8);
			yCoordinates[7] = (int) this.ys(n7 - abs2 - this.medium.random() * (abs2 / 4), n8);

			int r3 = (int) (213.0f + 213.0f * (this.medium.snapColor[0] / 200.0f));
			r3 = Util.clamp(r3, 0, 255);

			int g3 = (int) (239.0f + 239.0f * (this.medium.snapColor[1] / 200.0f));
			g3 = Util.clamp(g3, 0, 255);

			int b3 = (int) (255.0f + 255.0f * (this.medium.snapColor[2] / 200.0f));
			b3 = Util.clamp(b3, 0, 255);

			graphics.setColor(new Color(r3, g3, b3));
			graphics.fillPolygon(xCoordinates, yCoordinates, 8);
		}
		if (this.fcnt > 7) {
			this.fcnt = 0;
			this.fix = false;
		} else {
			++this.fcnt;
		}
	}

	public void electrify(final Graphics graphics) {
		int n = 0;
		do {
			if (this.elc[n] == 0) {
				this.edl[n] = (int) (380.0f - this.medium.random() * 760.0f);
				this.edr[n] = (int) (380.0f - this.medium.random() * 760.0f);
				this.elc[n] = 1;
			}
			final int n2 = (int) (this.edl[n] + (190.0f - this.medium.random() * 380.0f));
			final int n3 = (int) (this.edr[n] + (190.0f - this.medium.random() * 380.0f));
			final int n4 = (int) (this.medium.random() * 126.0f);
			final int n5 = (int) (this.medium.random() * 126.0f);
			if (this.elc[n] > this.medium.random() * 7.0f) {
				this.elc[n] = 0;
			} else {
				final int[] elc = this.elc;
				++elc[n];
			}
			final int[] array = new int[8];
			final int[] array2 = new int[8];
			final int[] array3 = new int[8];
			int n7 = 0;
			do {
				array3[n7] = this.z - this.medium.positionZ;
			} while (++n7 < 8);
			array[0] = this.x - this.medium.positionX - 504;
			array2[0] = this.y - this.medium.positionY - this.edl[n] - 10 - (int) (this.medium.random() * 10.0f);
			array[1] = this.x - this.medium.positionX - 252 + n5;
			array2[1] = this.y - this.medium.positionY - n2 - 10 - (int) (this.medium.random() * 10.0f);
			array[2] = this.x - this.medium.positionX + 252 - n4;
			array2[2] = this.y - this.medium.positionY - n3 - 10 - (int) (this.medium.random() * 10.0f);
			array[3] = this.x - this.medium.positionX + 504;
			array2[3] = this.y - this.medium.positionY - this.edr[n] - 10 - (int) (this.medium.random() * 10.0f);
			array[4] = this.x - this.medium.positionX + 504;
			array2[4] = this.y - this.medium.positionY - this.edr[n] + 10 + (int) (this.medium.random() * 10.0f);
			array[5] = this.x - this.medium.positionX + 252 - n4;
			array2[5] = this.y - this.medium.positionY - n3 + 10 + (int) (this.medium.random() * 10.0f);
			array[6] = this.x - this.medium.positionX - 252 + n5;
			array2[6] = this.y - this.medium.positionY - n2 + 10 + (int) (this.medium.random() * 10.0f);
			array[7] = this.x - this.medium.positionX - 504;
			array2[7] = this.y - this.medium.positionY - this.edl[n] + 10 + (int) (this.medium.random() * 10.0f);
			if (this.roted) {
				this.rot(array, array3, this.x - this.medium.positionX, this.z - this.medium.positionZ, 90, 8);
			}
			this.rot(array, array3, this.medium.centerX, this.medium.centerZ, this.medium.xz, 8);
			this.rot(array2, array3, this.medium.centerY, this.medium.centerZ, this.medium.zy, 8);
			boolean b = true;
			int n8 = 0;
			int n9 = 0;
			int n10 = 0;
			int n11 = 0;
			final int[] array4 = new int[8];
			final int[] array5 = new int[8];
			int n12 = 0;
			do {
				array4[n12] = this.xs(array[n12], array3[n12]);
				array5[n12] = this.ys(array2[n12], array3[n12]);
				if (array5[n12] < 0 || array3[n12] < 10) {
					++n8;
				}
				if (array5[n12] > this.medium.height || array3[n12] < 10) {
					++n9;
				}
				if (array4[n12] < 0 || array3[n12] < 10) {
					++n10;
				}
				if (array4[n12] > this.medium.width || array3[n12] < 10) {
					++n11;
				}
			} while (++n12 < 8);
			if (n10 == 8 || n8 == 8 || n9 == 8 || n11 == 8) {
				b = false;
			}
			if (b) {
				int n13 = 0;
				int g = (int) (255.0f + 255.0f * (this.medium.snapColor[1] / 250.0f));
				g = Util.clamp(g, 0, 255);

				int b2 = (int) (223.0f + 223.0f * (this.medium.snapColor[2] / 250.0f));
				b2 = Util.clamp(b2, 0, 255);

				if (this.medium.trk) {
					n13 = 255;
					g = 128;
					b2 = 0;
				}
				graphics.setColor(new Color(n13, g, b2));
				graphics.fillPolygon(array4, array5, 8);

				if (array3[0] >= 4000) {
					continue;
				}
				int g2 = (int) (223.0f + 223.0f * (this.medium.snapColor[1] / 250.0f));
				g2 = Util.clamp(g2, 0, 255);

				int b3 = (int) (255.0f + 255.0f * (this.medium.snapColor[2] / 250.0f));
				b3 = Util.clamp(b3, 0, 255);

				graphics.setColor(new Color(n13, g2, b3));
				graphics.drawPolygon(array4, array5, 8);
			}
		} while (++n < 2);
		if (!this.roted) {
			this.xy += 11;
		} else {
			this.zy += 11;
		}
	}

	public int py(final int n, final int n2, final int n3, final int n4) {
		return (n - n2) * (n - n2) + (n3 - n4) * (n3 - n4);
	}
}
