import java.awt.Color;
import java.awt.Graphics;

//
// Decompiled by Procyon v0.5.36
//

public class Medium {
	int focus_point;
	float ground;
	int skyline;
	int[] fade;
	int[] csky;
	int[] cgrnd;
	int[] cfade;
	int[] snap;
	int origfade;
	int flex;
	boolean trk;
	boolean crs;
	float cx;
	float cy;
	float cz;
	float xz;
	float zy;
	int x;
	int y;
	int z;
	int w;
	int h;
	int nsp;
	int[] spx;
	int[] spz;
	int[] sprad;
	boolean td;
	int vxz;
	int adv;
	boolean vert;
	int trns;
	float[] tcos;
	float[] tsin;
	int cntrn;
	boolean[] diup;
	int[] rand;
	int trn;
	int hit;
	int ptr;
	int ptcnt;
	int nrnd;
	long trx;
	long trz;

	public float random() {
		if (this.cntrn == 0) {
			int n = 0;
			do {
				this.rand[n] = (int) (10.0 * Math.random());
				if (Math.random() > Math.random()) {
					this.diup[n] = false;
				} else {
					this.diup[n] = true;
				}
			} while (++n < 3);
			this.cntrn = 20;
		} else {
			--this.cntrn;
		}
		int n2 = 0;
		do {
			if (this.diup[n2]) {
				final int[] rand = this.rand;
				final int n3 = n2;
				++rand[n3];
				if (this.rand[n2] != 10) {
					continue;
				}
				this.rand[n2] = 0;
			} else {
				final int[] rand2 = this.rand;
				final int n4 = n2;
				--rand2[n4];
				if (this.rand[n2] != -1) {
					continue;
				}
				this.rand[n2] = 9;
			}
		} while (++n2 < 3);
		++this.trn;
		if (this.trn == 3) {
			this.trn = 0;
		}
		return this.rand[this.trn] / 10.0f;
	}

	public int ys(final int n, int n2) {
		if (n2 < 10) {
			n2 = 10;
		}
		return (n2 - this.focus_point) * ((int)this.cy - n) / n2 + n;
	}

	public float ys(final float n, float n2) {
		if (n2 < 10) {
			n2 = 10;
		}
		return (n2 - this.focus_point) * (this.cy - n) / n2 + n;
	}


	public Medium() {
		this.focus_point = Config.SCREEN_HEIGHT;
		this.ground = 250;
		this.skyline = -300;
		this.fade = new int[]{3000, 6000, 9000, 12000, 15000, 18000, 21000, 24000};
		this.csky = new int[]{217, 224, 255};
		this.cgrnd = new int[]{205, 200, 200};
		this.cfade = new int[]{255, 220, 220};
		this.snap = new int[3];
		this.origfade = 3000;
		this.flex = 0;
		this.trk = false;
		this.crs = false;
		this.cx = (int) Config.SCREEN_WIDTH / 2;
		this.cy = (int) Config.SCREEN_HEIGHT / 2;
		this.cz = 70;
		this.xz = 0;
		this.zy = 0;
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.w = Config.SCREEN_WIDTH;
		this.h = Config.SCREEN_HEIGHT;
		this.nsp = 0;
		this.spx = new int[5];
		this.spz = new int[5];
		this.sprad = new int[5];
		this.td = false;
		this.vxz = 180;
		this.adv = 500;
		this.vert = false;
		this.trns = 1;
		this.cntrn = 0;
		this.diup = new boolean[3];
		this.rand = new int[3];
		this.trn = 0;
		this.hit = 60000;
		this.ptr = 0;
		this.ptcnt = -10;
		this.nrnd = 0;
		this.trx = 0L;
		this.trz = 0L;
		int n = 0;
	}

	public void setfade(final int n, final int n2, final int n3) {
		this.cfade[0] = (int) (n + n * (this.snap[0] / 100.0f));
		if (this.cfade[0] > 255) {
			this.cfade[0] = 255;
		}
		if (this.cfade[0] < 0) {
			this.cfade[0] = 0;
		}
		this.cfade[1] = (int) (n2 + n2 * (this.snap[1] / 100.0f));
		if (this.cfade[1] > 255) {
			this.cfade[1] = 255;
		}
		if (this.cfade[1] < 0) {
			this.cfade[1] = 0;
		}
		this.cfade[2] = (int) (n3 + n3 * (this.snap[2] / 100.0f));
		if (this.cfade[2] > 255) {
			this.cfade[2] = 255;
		}
		if (this.cfade[2] < 0) {
			this.cfade[2] = 0;
		}
	}

	public void draw(final Graphics graphics) {
		this.nsp = 0;
		if (this.zy > 90) {
			this.zy = 90;
		}
		if (this.zy < -90) {
			this.zy = -90;
		}
		if (this.xz > 360) {
			this.xz -= 360;
		}
		if (this.xz < 0) {
			this.xz += 360;
		}
		if (this.y > 0) {
			this.y = 0;
		}
		this.ground = 250 - this.y;
		final int[] array = new int[4];
		final int[] array2 = new int[4];
		int r = this.cgrnd[0];
		int g = this.cgrnd[1];
		int b = this.cgrnd[2];
		int h = this.h;
		int n = 0;
		do {
			float n2 = this.fade[n];
			float ground = this.ground;
			if (this.zy != 0) {
				ground = this.cy + (int) ((this.ground - this.cy) * this.cos(this.zy) - (this.fade[n] - this.cz) * this.sin(this.zy));
				n2 = this.cz + (int) ((this.ground - this.cy) * this.sin(this.zy) + (this.fade[n] - this.cz) * this.cos(this.zy));
			}
			array2[array[0] = 0] = (int)this.ys(ground, n2);
			if (array2[0] < 0) {
				array2[0] = 0;
			}
			array[1] = 0;
			array2[1] = h;
			array[2] = this.w;
			array2[2] = h;
			array[3] = this.w;
			array2[3] = array2[0];
			h = array2[0];
			if (n > 0) {
				r = (r * 3 + this.cfade[0]) / 4;
				g = (g * 3 + this.cfade[1]) / 4;
				b = (b * 3 + this.cfade[2]) / 4;
			}
			if (array2[0] < this.h && array2[1] > 0) {
				graphics.setColor(new Color(r, g, b));
				graphics.fillPolygon(array, array2, 4);
			}
		} while (++n < 8);
		int n3 = this.csky[0];
		int n4 = this.csky[1];
		int n5 = this.csky[2];
		int n6 = 0;
		if (this.flex == 2) {
			array[0] = 200;
			array2[0] = 45;
			array[1] = 200;
			array2[1] = 0;
			array[2] = 350;
			array2[2] = 0;
			array[3] = 350;
			array2[3] = 45;
			graphics.setColor(new Color(n3, n4, n5));
			graphics.fillPolygon(array, array2, 4);
			n6 = 45;
		}
		int n7 = 0;
		do {
			int n8 = this.fade[n7];
			int skyline = this.skyline;
			if (this.zy != 0) {
				skyline = (int)this.cy + (int) ((this.skyline - this.cy) * this.cos(this.zy) - (this.fade[n7] - this.cz) * this.sin(this.zy));
				n8 = (int)this.cz + (int) ((this.skyline - this.cy) * this.sin(this.zy) + (this.fade[n7] - this.cz) * this.cos(this.zy));
			}
			array2[array[0] = 0] = this.ys(skyline, n8);
			if (array2[0] > this.h) {
				array2[0] = this.h;
			}
			array[1] = 0;
			array2[1] = n6;
			array[2] = this.w;
			array2[2] = n6;
			array[3] = this.w;
			array2[3] = array2[0];
			n6 = array2[0];
			if (n7 > 0) {
				n3 = (n3 * 3 + this.cfade[0]) / 4;
				n4 = (n4 * 3 + this.cfade[1]) / 4;
				n5 = (n5 * 3 + this.cfade[2]) / 4;
			}
			if (array2[0] > 0 && array2[1] < this.h) {
				graphics.setColor(new Color(n3, n4, n5));
				graphics.fillPolygon(array, array2, 4);
			}
		} while (++n7 < 8);
		array2[array[0] = 0] = n6;
		array[1] = 0;
		array2[1] = h;
		array[2] = this.w;
		array2[2] = h;
		array[3] = this.w;
		array2[3] = n6;
		if (array2[0] < this.h && array2[1] > 0) {
			graphics.setColor(new Color(this.cfade[0], this.cfade[1], this.cfade[2]));
			graphics.fillPolygon(array, array2, 4);
		}
	}

	public void watch(final Geometry geometry, final float n) {
		if (this.flex != 0) {
			this.flex = 0;
		}
		if (this.td) {
			this.y = (int) (geometry.y - 300 - 1100.0f * this.random());
			this.x = geometry.x + (int) ((geometry.x + 400 - geometry.x) * this.cos(n) - (geometry.z + 5000 - geometry.z) * this.sin(n));
			this.z = geometry.z + (int) ((geometry.x + 400 - geometry.x) * this.sin(n) + (geometry.z + 5000 - geometry.z) * this.cos(n));
			this.td = false;
		}
		int n2 = 0;
		if (geometry.x - this.x - this.cx > 0) {
			n2 = 180;
		}
		final float n3 = -(float) (90 + n2 + Math.atan((geometry.z - this.z) / (float) (geometry.x - this.x - this.cx)) / 0.017453292519943295);
		int n4 = 0;
		if (geometry.y - this.y - this.cy < 0) {
			n4 = -180;
		}
		final float n5 = (float) (90 + n4 - Math.atan((float) Math.sqrt((geometry.z - this.z) * (geometry.z - this.z) + (geometry.x - this.x - this.cx) * (geometry.x - this.x - this.cx)) / (float) (geometry.y - this.y - this.cy)) / 0.017453292519943295);
		this.xz += (n3 - this.xz) / this.trns;
		if (this.trns != 1) {
			--this.trns;
		}
		this.zy += (n5 - this.zy) / 5;
		System.out.println(this.zy);
		System.out.println((n5 - this.zy) / 5);
		if ((int) Math.sqrt((geometry.z - this.z) * (geometry.z - this.z) + (geometry.x - this.x - this.cx) * (geometry.x - this.x - this.cx) + (geometry.y - this.y - this.cy) * (geometry.y - this.y - this.cy)) > 6000) {
			this.td = true;
		}
	}

	public void setsnap(final int n, final int n2, final int n3) {
		this.snap[0] = n;
		this.snap[1] = n2;
		this.snap[2] = n3;
	}

	public void around(final Geometry geometry, final boolean b) {

		if (this.zy > 90) {
			this.zy = 90;
		}
		if (this.zy < -90) {
			this.zy = -90;
		}

		this.xz = xz % 360;

		if (this.flex != 0) {
			this.flex = 0;
		}
		if (!b) {
			if (!this.vert) {
				this.adv += 2;
			} else {
				this.adv -= 2;
			}
			if (this.adv > 900) {
				this.vert = true;
			}
			if (this.adv < -500) {
				this.vert = false;
			}
		} else {
			this.adv -= 20;
		}
		int n = 500 + this.adv;
		if (n < 1000) {
			n = 1000;
		}
		this.y = geometry.y - this.adv;
		if (this.y > 10) {
			this.vert = false;
		}
		this.x = geometry.x + (int) ((geometry.x - n - geometry.x) * this.cos(this.vxz));
		this.z = geometry.z + (int) ((geometry.x - n - geometry.x) * this.sin(this.vxz));
		if (!b) {
			this.vxz += 2;
		} else {
			this.vxz += 4;
		}
		int n2 = 0;
		int y = this.y;
		if (y > 0) {
			y = 0;
		}
		if (geometry.y - y - this.cy < 0) {
			n2 = -180;
		}
		int n3 = (int) (90 + n2 - Math.atan((int) Math.sqrt((geometry.z - this.z + this.cz) * (geometry.z - this.z + this.cz) + (geometry.x - this.x - this.cx) * (geometry.x - this.x - this.cx)) / (double) (geometry.y - y - this.cy)) / 0.017453292519943295);
		this.xz = -this.vxz + 90;
		if (b) {
			n3 -= 15;
		}
		this.zy += (n3 - this.zy) / 10;
		if (this.trns != 5) {
			this.trns = 5;
		}
		System.out.println("hit:" + this.hit + ", vxz:" + this.vxz + ", zy:" + this.zy + ", xz:" + this.xz);

	}

	public void setgrnd(final int n, final int n2, final int n3) {
		this.cgrnd[0] = (int) (n + n * (this.snap[0] / 100.0f));
		if (this.cgrnd[0] > 255) {
			this.cgrnd[0] = 255;
		}
		if (this.cgrnd[0] < 0) {
			this.cgrnd[0] = 0;
		}
		this.cgrnd[1] = (int) (n2 + n2 * (this.snap[1] / 100.0f));
		if (this.cgrnd[1] > 255) {
			this.cgrnd[1] = 255;
		}
		if (this.cgrnd[1] < 0) {
			this.cgrnd[1] = 0;
		}
		this.cgrnd[2] = (int) (n3 + n3 * (this.snap[2] / 100.0f));
		if (this.cgrnd[2] > 255) {
			this.cgrnd[2] = 255;
		}
		if (this.cgrnd[2] < 0) {
			this.cgrnd[2] = 0;
		}
	}

	public void adjstfade(final float n) {
		if (n < 15.0f) {
			this.fade[0] = (int) (this.origfade - 1000.0f * (15.0f - n));
			if (this.fade[0] < 3000) {
				this.fade[0] = 3000;
			}
			this.fadfrom(this.fade[0]);
		} else if (this.fade[0] != this.origfade) {
			final int[] fade = this.fade;
			final int n2 = 0;
			fade[n2] += 500;
			if (this.fade[0] > this.origfade) {
				this.fade[0] = this.origfade;
			}
			this.fadfrom(this.fade[0]);
		}
	}

	public void addsp(final int n, final int n2, final int n3) {
		if (this.nsp != 5) {
			this.spx[this.nsp] = n;
			this.spz[this.nsp] = n2;
			this.sprad[this.nsp] = n3;
			++this.nsp;
		}
	}

	public void aroundtrack(final CheckPoints checkPoints) {
		if (this.flex != 0) {
			this.flex = 0;
		}
		this.y = -this.hit;
		this.x = (int) this.cx + (int) this.trx + (int) (12000.0f * this.cos(this.vxz));
		this.z = (int) this.trz + (int) (12000.0f * this.sin(this.vxz));
		this.hit -= 3000;
		if (this.hit < 5000) {
			this.hit = 5000;
			this.trx -= (this.trx - checkPoints.x[this.ptr]) / 10L;
			this.trz -= (this.trz - checkPoints.z[this.ptr]) / 10L;
			if (this.ptcnt == 7) {
				++this.ptr;
				if (this.ptr == checkPoints.n) {
					this.ptr = 0;
					++this.nrnd;
				}
				this.ptcnt = 0;
			} else {
				++this.ptcnt;
			}
		}
		this.vxz += 2;
		if (this.vxz > 360) {
			this.vxz -= 360;
		}
		this.xz = -this.vxz - 90;
		int n = 0;
		if (-this.y - this.cy < 0) {
			n = -180;
		}
		final float n2 = (float)Math.sqrt((double) ((this.trz - this.z + this.cz) * (this.trz - this.z + this.cz) + (this.trx - this.x - this.cx) * (this.trx - this.x - this.cx)));
		if (this.zy < 30 && this.hit != 57000) {
			if (this.zy > 9) {
				--this.zy;
			} else {
				this.zy = 9;
			}
		} else {
			this.zy = (float)(90 + n - Math.atan(n2 / (double) (-this.y - this.cy)) / 0.017453292519943295);
		}
	}

	public void setsky(final int n, final int n2, final int n3) {
		this.csky[0] = (int) (n + n * (this.snap[0] / 100.0f));
		if (this.csky[0] > 255) {
			this.csky[0] = 255;
		}
		if (this.csky[0] < 0) {
			this.csky[0] = 0;
		}
		this.csky[1] = (int) (n2 + n2 * (this.snap[1] / 100.0f));
		if (this.csky[1] > 255) {
			this.csky[1] = 255;
		}
		if (this.csky[1] < 0) {
			this.csky[1] = 0;
		}
		this.csky[2] = (int) (n3 + n3 * (this.snap[2] / 100.0f));
		if (this.csky[2] > 255) {
			this.csky[2] = 255;
		}
		if (this.csky[2] < 0) {
			this.csky[2] = 0;
		}
	}

	public void fadfrom(final int n) {
		int n2 = 0;
		do {
			this.fade[n2] = n * (n2 + 1);
		} while (++n2 < 8);
	}

	public void follow(final Geometry geometry, final float n) {
		this.zy += 0.05f;
		this.xz = -n;
		this.x = geometry.x - (int) this.cx + (int) (-(geometry.z - 800 - geometry.z) * this.sin(n));
		this.z = geometry.z - (int) this.cz + (int) ((geometry.z - 800 - geometry.z) * this.cos(n));
		this.y = geometry.y - 250 - (int) this.cy;
		if (this.trns != 1) {
			this.trns = 1;
		}
	}

	public float cos(int i) {
		return (float) (Math.cos(i * 2 * Math.PI / 360));
	}

	public float cos(float i) {
		return (float) (Math.cos(i * 2 * Math.PI / 360));
	}

	public float sin(int i) {
		return (float) (Math.sin(i * 2 * Math.PI / 360));
	}

	public float sin(float i) {
		return (float) (Math.sin(i * 2 * Math.PI / 360));
	}
}
