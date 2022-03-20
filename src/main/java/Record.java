package main.java;

import java.awt.Color;



public class Record
{
    Medium m;
    int caught;
    boolean highlightExists;
    boolean prepit;
    Geometry[] ocar;
    int cntf;
    Geometry[][] car;
    int[][] squash;
    int[] fix;
    int[] dest;
    int[][] x;
    int[][] y;
    int[][] z;
    float[][] xy;
    float[][] zy;
    float[][] xz;
    float[][] wxz;
    float[][] wzy;
    int[][] ns;
    int[][][] sspark;
    int[][][] sx;
    int[][][] sy;
    int[][][] sz;
    float[][][] smag;
    int[][][] scx;
    int[][][] scz;
    boolean[][][] fulls;
    int[][] nry;
    int[][][] ry;
    int[][][] magy;
    boolean[][] mtouch;
    int[][] nrx;
    int[][][] rx;
    int[][][] magx;
    int[][] nrz;
    int[][][] rz;
    int[][][] magz;
    int wasted;
    int whenwasted;
    int powered;
    Geometry[] starcar;
    int[] hsquash;
    int[] hfix;
    int[] hdest;
    int[][] hx;
    int[][] hy;
    int[][] hz;
    float[][] hxy;
    float[][] hzy;
    float[][] hxz;
    float[][] hwxz;
    float[][] hwzy;
    int[][] hns;
    int[][][] hsspark;
    int[][][] hsx;
    int[][][] hsy;
    int[][][] hsz;
    float[][][] hsmag;
    int[][][] hscx;
    int[][][] hscz;
    boolean[][][] hfulls;
    int[][] hnry;
    int[][][] hry;
    int[][][] hmagy;
    int[][] hnrx;
    int[][][] hrx;
    int[][][] hmagx;
    int[][] hnrz;
    int[][][] hrz;
    int[][][] hmagz;
    boolean[][] hmtouch;
    int[] cntdest;
    int lastfr;

    public void regy(final int n, float n2, final boolean b, final Geometry geometry, final Madness madness) {
        if (n2 > 100.0f) {
            n2 -= 100.0f;
            int n3 = 0;
            int n4 = 0;
            float i = geometry.zy;
            float j = geometry.xy;
            while (i < 360) {
                i += 360;
            }
            while (i > 360) {
                i -= 360;
            }
            if (i < 210 && i > 150) {
                n3 = -1;
            }
            if (i > 330 || i < 30) {
                n3 = 1;
            }
            while (j < 360) {
                j += 360;
            }
            while (j > 360) {
                j -= 360;
            }
            if (j < 210 && j > 150) {
                n4 = -1;
            }
            if (j > 330 || j < 30) {
                n4 = 1;
            }
            if (n4 * n3 == 0 || b) {
                for (int k = 0; k < geometry.numberOfPlanes; ++k) {
                    float n5 = 0.0f;
                    for (int l = 0; l < geometry.planes[k].numberOfPoints; ++l) {
                        if (geometry.planes[k].wz == 0 && this.py(geometry.keyx[n], geometry.planes[k].ox[l], geometry.keyz[n], geometry.planes[k].oz[l]) < madness.clrad[madness.carIndex]) {
                            n5 = n2 / 20.0f * this.m.random();
                            final int[] oz = geometry.planes[k].oz;
                            oz[l] += (int)(n5 * Util.sin(i));
                            final int[] ox = geometry.planes[k].ox;
                            ox[l] -= (int)(n5 * Util.sin(j));
                        }
                    }
                    if (n5 != 0.0f) {
                        if (Math.abs(n5) >= 1.0f) {
                            geometry.planes[k].chip = 1;
                            geometry.planes[k].ctmag = n5;
                        }
                        if (!geometry.planes[k].isNotColored && !geometry.planes[k].isGlass) {
                            if (geometry.planes[k].bfase > 20 && geometry.planes[k].hsb[1] > 0.2) {
                                geometry.planes[k].hsb[1] = 0.2f;
                            }
                            if (geometry.planes[k].bfase > 30) {
                                if (geometry.planes[k].hsb[2] < 0.5) {
                                    geometry.planes[k].hsb[2] = 0.5f;
                                }
                                if (geometry.planes[k].hsb[1] > 0.1) {
                                    geometry.planes[k].hsb[1] = 0.1f;
                                }
                            }
                            if (geometry.planes[k].bfase > 40) {
                                geometry.planes[k].hsb[1] = 0.05f;
                            }
                            if (geometry.planes[k].bfase > 50) {
                                if (geometry.planes[k].hsb[2] > 0.8) {
                                    geometry.planes[k].hsb[2] = 0.8f;
                                }
                                geometry.planes[k].hsb[0] = 0.075f;
                                geometry.planes[k].hsb[1] = 0.05f;
                            }
                            if (geometry.planes[k].bfase > 60) {
                                geometry.planes[k].hsb[0] = 0.05f;
                            }
                            final Plane plane = geometry.planes[k];
                            plane.bfase += (int)n5;
                            new Color(geometry.planes[k].color[0], geometry.planes[k].color[1], geometry.planes[k].color[2]);
                            final Color hsbColor = Color.getHSBColor(geometry.planes[k].hsb[0], geometry.planes[k].hsb[1], geometry.planes[k].hsb[2]);
                            geometry.planes[k].color[0] = hsbColor.getRed();
                            geometry.planes[k].color[1] = hsbColor.getGreen();
                            geometry.planes[k].color[2] = hsbColor.getBlue();
                        }
                        if (geometry.planes[k].isGlass) {
                            final Plane plane2 = geometry.planes[k];
                            plane2.gr -= (int)Math.abs(n5 * 1.5);
                        }
                    }
                }
            }
            if (n4 * n3 == -1) {
                int n8 = 0;
                int n9 = 1;
                for (int n10 = 0; n10 < geometry.numberOfPlanes; ++n10) {
                    float n11 = 0.0f;
                    for (int n12 = 0; n12 < geometry.planes[n10].numberOfPoints; ++n12) {
                        if (geometry.planes[n10].wz == 0) {
                            n11 = n2 / 15.0f * this.m.random();
                            if ((Math.abs(geometry.planes[n10].oy[n12] - madness.flipy[madness.carIndex] - this.squash[0][madness.im]) < madness.msquash[madness.carIndex] * 3 || geometry.planes[n10].oy[n12] < madness.flipy[madness.carIndex] + this.squash[0][madness.im]) && this.squash[0][madness.im] < madness.msquash[madness.carIndex]) {
                                final int[] oy = geometry.planes[n10].oy;
                                oy[n12] += (int)n11;
                                n8 += (int)n11;
                                ++n9;
                            }
                        }
                    }
                    if (geometry.planes[n10].isGlass) {
                        final Plane plane3 = geometry.planes[n10];
                        plane3.gr -= 5;
                    }
                    else if (n11 != 0.0f) {
                        final Plane plane4 = geometry.planes[n10];
                        plane4.bfase += (int)n11;
                    }
                    if (Math.abs(n11) >= 1.0f) {
                        geometry.planes[n10].chip = 1;
                        geometry.planes[n10].ctmag = n11;
                    }
                }
                final int[] array = this.squash[0];
                final int im = madness.im;
                array[im] += n8 / n9;
            }
        }
    }

    public void reset(final Geometry[] array) {
        this.caught = 0;
        this.highlightExists = false;
        this.wasted = 0;
        this.whenwasted = 0;
        this.powered = 0;
        int n = 0;
        do {
            if (this.prepit) {
                this.starcar[n] = new Geometry(array[n], 0, 0, 0, 0);
            }
            this.fix[n] = -1;
            this.dest[n] = -1;
            this.cntdest[n] = 0;
        } while (++n < 5);
        int n2 = 0;
        do {
            int n3 = 0;
            do {
                this.car[n2][n3] = new Geometry(array[n3], 0, 0, 0, 0);
                this.squash[n2][n3] = 0;
            } while (++n3 < 5);
        } while (++n2 < 6);
        int n4 = 0;
        do {
            int n5 = 0;
            do {
                int n6 = 0;
                do {
                    this.sspark[n4][n5][n6] = -1;
                    this.ns[n4][n5] = 0;
                } while (++n6 < 30);
                int n7 = 0;
                do {
                    this.ry[n4][n5][n7] = -1;
                    this.nry[n4][n5] = 0;
                    this.rx[n4][n5][n7] = -1;
                    this.nrx[n4][n5] = 0;
                    this.rz[n4][n5][n7] = -1;
                    this.nrz[n4][n5] = 0;
                } while (++n7 < 7);
            } while (++n5 < 4);
        } while (++n4 < 4);
        this.prepit = false;
    }

    public Record(final Medium m) {
        this.caught = 0;
        this.highlightExists = false;
        this.prepit = true;
        this.ocar = new Geometry[5];
        this.cntf = 50;
        this.car = new Geometry[6][5];
        this.squash = new int[6][5];
        this.fix = new int[5];
        this.dest = new int[5];
        this.x = new int[300][5];
        this.y = new int[300][5];
        this.z = new int[300][5];
        this.xy = new float[300][5];
        this.zy = new float[300][5];
        this.xz = new float[300][5];
        this.wxz = new float[300][5];
        this.wzy = new float[300][5];
        this.ns = new int[5][4];
        this.sspark = new int[5][4][30];
        this.sx = new int[5][4][30];
        this.sy = new int[5][4][30];
        this.sz = new int[5][4][30];
        this.smag = new float[5][4][30];
        this.scx = new int[5][4][30];
        this.scz = new int[5][4][30];
        this.fulls = new boolean[5][4][30];
        this.nry = new int[5][4];
        this.ry = new int[5][4][7];
        this.magy = new int[5][4][7];
        this.mtouch = new boolean[5][7];
        this.nrx = new int[5][4];
        this.rx = new int[5][4][7];
        this.magx = new int[5][4][7];
        this.nrz = new int[5][4];
        this.rz = new int[5][4][7];
        this.magz = new int[5][4][7];
        this.wasted = 0;
        this.whenwasted = 0;
        this.powered = 0;
        this.starcar = new Geometry[5];
        this.hsquash = new int[5];
        this.hfix = new int[] { -1, -1, -1, -1, -1 };
        this.hdest = new int[] { -1, -1, -1, -1, -1 };
        this.hx = new int[300][5];
        this.hy = new int[300][5];
        this.hz = new int[300][5];
        this.hxy = new float[300][5];
        this.hzy = new float[300][5];
        this.hxz = new float[300][5];
        this.hwxz = new float[300][5];
        this.hwzy = new float[300][5];
        this.hns = new int[5][4];
        this.hsspark = new int[5][4][30];
        this.hsx = new int[5][4][30];
        this.hsy = new int[5][4][30];
        this.hsz = new int[5][4][30];
        this.hsmag = new float[5][4][30];
        this.hscx = new int[5][4][30];
        this.hscz = new int[5][4][30];
        this.hfulls = new boolean[5][4][30];
        this.hnry = new int[5][4];
        this.hry = new int[5][4][7];
        this.hmagy = new int[5][4][7];
        this.hnrx = new int[5][4];
        this.hrx = new int[5][4][7];
        this.hmagx = new int[5][4][7];
        this.hnrz = new int[5][4];
        this.hrz = new int[5][4][7];
        this.hmagz = new int[5][4][7];
        this.hmtouch = new boolean[5][7];
        this.cntdest = new int[5];
        this.lastfr = 0;
        this.m = m;
        this.cotchinow(this.caught = 0);
    }

    public void playh(final Geometry geometry, final Madness madness, final int n, final int lastfr) {
        geometry.x = this.hx[lastfr][n];
        geometry.y = this.hy[lastfr][n];
        geometry.z = this.hz[lastfr][n];
        geometry.zy = this.hzy[lastfr][n];
        geometry.xy = this.hxy[lastfr][n];
        geometry.xz = this.hxz[lastfr][n];
        geometry.wxz = this.hwxz[lastfr][n];
        geometry.wzy = this.hwzy[lastfr][n];
        if (lastfr == 0) {
            this.cntdest[n] = 0;
        }
        if (this.hdest[n] == lastfr) {
            this.cntdest[n] = 7;
        }
        if (lastfr == 0 && this.hdest[n] < -1) {
            for (int i = 0; i < geometry.numberOfPlanes; ++i) {
                if (geometry.planes[i].wz == 0) {
                    geometry.planes[i].embos = 13;
                }
            }
        }
        if (this.cntdest[n] != 0) {
            for (int j = 0; j < geometry.numberOfPlanes; ++j) {
                if (geometry.planes[j].wz == 0) {
                    geometry.planes[j].embos = 1;
                }
            }
            final int[] cntdest = this.cntdest;
            --cntdest[n];
        }
        int n2 = 0;
        do {
            int n3 = 0;
            do {
                if (this.hsspark[n][n2][n3] == lastfr) {
                    geometry.stg[n2] = 1;
                    geometry.dov[n2] = -1;
                    geometry.sx[n2] = this.hsx[n][n2][n3];
                    geometry.sy[n2] = this.hsy[n][n2][n3];
                    geometry.sz[n2] = this.hsz[n][n2][n3];
                    geometry.smag[n2] = this.hsmag[n][n2][n3];
                    geometry.scx[n2] = this.hscx[n][n2][n3];
                    geometry.scz[n2] = this.hscz[n][n2][n3];
                    geometry.fulls[n2] = this.hfulls[n][n2][n3];
                }
            } while (++n3 < 30);
            int n4 = 0;
            do {
                if (this.hry[n][n2][n4] == lastfr && this.lastfr != lastfr) {
                    this.regy(n2, (float)this.hmagy[n][n2][n4], this.hmtouch[n][n4], geometry, madness);
                }
                if (this.hrx[n][n2][n4] == lastfr) {
                    if (this.lastfr != lastfr) {
                        this.regx(n2, (float)this.hmagx[n][n2][n4], geometry, madness);
                    }
                    else {
                        this.chipx(n2, (float)this.hmagx[n][n2][n4], geometry, madness);
                    }
                }
                if (this.hrz[n][n2][n4] == lastfr) {
                    if (this.lastfr != lastfr) {
                        this.regz(n2, (float)this.hmagz[n][n2][n4], geometry, madness);
                    }
                    else {
                        this.chipz(n2, (float)this.hmagz[n][n2][n4], geometry, madness);
                    }
                }
            } while (++n4 < 7);
        } while (++n2 < 4);
        this.lastfr = lastfr;
    }

    public void chipz(final int n, float a, final Geometry geometry, final Madness madness) {
        if (Math.abs(a) > 100.0f) {
            if (a > 100.0f) {
                a -= 100.0f;
            }
            if (a < -100.0f) {
                a += 100.0f;
            }
            for (int i = 0; i < geometry.numberOfPlanes; ++i) {
                float n2 = 0.0f;
                for (int j = 0; j < geometry.planes[i].numberOfPoints; ++j) {
                    if (geometry.planes[i].wz == 0 && this.py(geometry.keyx[n], geometry.planes[i].ox[j], geometry.keyz[n], geometry.planes[i].oz[j]) < madness.clrad[madness.carIndex]) {
                        n2 = a / 20.0f * this.m.random();
                    }
                }
                if (n2 != 0.0f && Math.abs(n2) >= 1.0f) {
                    geometry.planes[i].chip = 1;
                    geometry.planes[i].ctmag = n2;
                }
            }
        }
    }

    public void regz(final int n, float a, final Geometry geometry, final Madness madness) {
        if (Math.abs(a) > 100.0f) {
            if (a > 100.0f) {
                a -= 100.0f;
            }
            if (a < -100.0f) {
                a += 100.0f;
            }
            for (int i = 0; i < geometry.numberOfPlanes; ++i) {
                float a2 = 0.0f;
                for (int j = 0; j < geometry.planes[i].numberOfPoints; ++j) {
                    if (geometry.planes[i].wz == 0 && this.py(geometry.keyx[n], geometry.planes[i].ox[j], geometry.keyz[n], geometry.planes[i].oz[j]) < madness.clrad[madness.carIndex]) {
                        a2 = a / 20.0f * this.m.random();
                        final int[] oz = geometry.planes[i].oz;
                        oz[j] += (int)(a2 * Util.cos(geometry.xz) * Util.cos(geometry.zy));
                        final int[] ox = geometry.planes[i].ox;
                        ox[j] += (int)(a2 * Util.sin(geometry.xz) * Util.cos(geometry.xy));
                    }
                }
                if (a2 != 0.0f) {
                    if (Math.abs(a2) >= 1.0f) {
                        geometry.planes[i].chip = 1;
                        geometry.planes[i].ctmag = a2;
                    }
                    if (!geometry.planes[i].isNotColored && !geometry.planes[i].isGlass) {
                        if (geometry.planes[i].bfase > 20 && geometry.planes[i].hsb[1] > 0.2) {
                            geometry.planes[i].hsb[1] = 0.2f;
                        }
                        if (geometry.planes[i].bfase > 30) {
                            if (geometry.planes[i].hsb[2] < 0.5) {
                                geometry.planes[i].hsb[2] = 0.5f;
                            }
                            if (geometry.planes[i].hsb[1] > 0.1) {
                                geometry.planes[i].hsb[1] = 0.1f;
                            }
                        }
                        if (geometry.planes[i].bfase > 40) {
                            geometry.planes[i].hsb[1] = 0.05f;
                        }
                        if (geometry.planes[i].bfase > 50) {
                            if (geometry.planes[i].hsb[2] > 0.8) {
                                geometry.planes[i].hsb[2] = 0.8f;
                            }
                            geometry.planes[i].hsb[0] = 0.075f;
                            geometry.planes[i].hsb[1] = 0.05f;
                        }
                        if (geometry.planes[i].bfase > 60) {
                            geometry.planes[i].hsb[0] = 0.05f;
                        }
                        final Plane plane = geometry.planes[i];
                        plane.bfase += (int)Math.abs(a2);
                        new Color(geometry.planes[i].color[0], geometry.planes[i].color[1], geometry.planes[i].color[2]);
                        final Color hsbColor = Color.getHSBColor(geometry.planes[i].hsb[0], geometry.planes[i].hsb[1], geometry.planes[i].hsb[2]);
                        geometry.planes[i].color[0] = hsbColor.getRed();
                        geometry.planes[i].color[1] = hsbColor.getGreen();
                        geometry.planes[i].color[2] = hsbColor.getBlue();
                    }
                    if (geometry.planes[i].isGlass) {
                        final Plane plane2 = geometry.planes[i];
                        plane2.gr -= (int)Math.abs(a2 * 1.5);
                    }
                }
            }
        }
    }

    public void play(final Geometry geometry, final Madness madness, final int n, final int n2) {
        geometry.x = this.x[n2][n];
        geometry.y = this.y[n2][n];
        geometry.z = this.z[n2][n];
        geometry.zy = this.zy[n2][n];
        geometry.xy = this.xy[n2][n];
        geometry.xz = this.xz[n2][n];
        geometry.wxz = this.wxz[n2][n];
        geometry.wzy = this.wzy[n2][n];
        if (n2 == 0) {
            this.cntdest[n] = 0;
        }
        if (this.dest[n] == n2) {
            this.cntdest[n] = 7;
        }
        if (n2 == 0 && this.dest[n] < -1) {
            for (int i = 0; i < geometry.numberOfPlanes; ++i) {
                if (geometry.planes[i].wz == 0) {
                    geometry.planes[i].embos = 13;
                }
            }
        }
        if (this.cntdest[n] != 0) {
            for (int j = 0; j < geometry.numberOfPlanes; ++j) {
                if (geometry.planes[j].wz == 0) {
                    geometry.planes[j].embos = 1;
                }
            }
            final int[] cntdest = this.cntdest;
            --cntdest[n];
        }
        int n3 = 0;
        do {
            int n4 = 0;
            do {
                if (this.sspark[n][n3][n4] == n2) {
                    geometry.stg[n3] = 1;
                    geometry.dov[n3] = -1;
                    geometry.sx[n3] = this.sx[n][n3][n4];
                    geometry.sy[n3] = this.sy[n][n3][n4];
                    geometry.sz[n3] = this.sz[n][n3][n4];
                    geometry.smag[n3] = this.smag[n][n3][n4];
                    geometry.scx[n3] = this.scx[n][n3][n4];
                    geometry.scz[n3] = this.scz[n][n3][n4];
                    geometry.fulls[n3] = this.fulls[n][n3][n4];
                }
            } while (++n4 < 30);
            int n5 = 0;
            do {
                if (this.ry[n][n3][n5] == n2) {
                    this.regy(n3, (float)this.magy[n][n3][n5], this.mtouch[n][n5], geometry, madness);
                }
                if (this.rx[n][n3][n5] == n2) {
                    this.regx(n3, (float)this.magx[n][n3][n5], geometry, madness);
                }
                if (this.rz[n][n3][n5] == n2) {
                    this.regz(n3, (float)this.magz[n][n3][n5], geometry, madness);
                }
            } while (++n5 < 7);
        } while (++n3 < 4);
    }

    public void rec(final Geometry geometry, final int n, final int n2, final int n3, final int n4) {
        if (n == 0) {
            ++this.caught;
        }
        if (this.cntf == 50) {
            int n5 = 0;
            do {
                this.car[n5][n] = new Geometry(this.car[n5 + 1][n], 0, 0, 0, 0);
                this.squash[n5][n] = this.squash[n5 + 1][n];
            } while (++n5 < 5);
            this.car[5][n] = new Geometry(geometry, 0, 0, 0, 0);
            this.squash[5][n] = n2;
            this.cntf = 0;
        }
        else {
            ++this.cntf;
        }
        final int[] fix = this.fix;
        --fix[n];
        if (n4 != 0) {
            final int[] dest = this.dest;
            --dest[n];
        }
        if (this.dest[n] == 230) {
            if (n == 0) {
                this.cotchinow(0);
                this.whenwasted = 229;
            }
            else if (n3 != 0) {
                this.cotchinow(n);
                this.whenwasted = 165 + n3;
            }
        }
        int n6 = 0;
        do {
            this.x[n6][n] = this.x[n6 + 1][n];
            this.y[n6][n] = this.y[n6 + 1][n];
            this.z[n6][n] = this.z[n6 + 1][n];
            this.zy[n6][n] = this.zy[n6 + 1][n];
            this.xy[n6][n] = this.xy[n6 + 1][n];
            this.xz[n6][n] = this.xz[n6 + 1][n];
            this.wxz[n6][n] = this.wxz[n6 + 1][n];
            this.wzy[n6][n] = this.wzy[n6 + 1][n];
        } while (++n6 < 299);
        this.x[299][n] = geometry.x;
        this.y[299][n] = geometry.y;
        this.z[299][n] = geometry.z;
        this.xy[299][n] = geometry.xy;
        this.zy[299][n] = geometry.zy;
        this.xz[299][n] = geometry.xz;
        this.wxz[299][n] = geometry.wxz;
        this.wzy[299][n] = geometry.wzy;
        int n7 = 0;
        do {
            if (geometry.stg[n7] == 1) {
                this.sspark[n][n7][this.ns[n][n7]] = 300;
                this.sx[n][n7][this.ns[n][n7]] = geometry.sx[n7];
                this.sy[n][n7][this.ns[n][n7]] = geometry.sy[n7];
                this.sz[n][n7][this.ns[n][n7]] = geometry.sz[n7];
                this.smag[n][n7][this.ns[n][n7]] = geometry.smag[n7];
                this.scx[n][n7][this.ns[n][n7]] = geometry.scx[n7];
                this.scz[n][n7][this.ns[n][n7]] = geometry.scz[n7];
                this.fulls[n][n7][this.ns[n][n7]] = geometry.fulls[n7];
                final int[] array = this.ns[n];
                ++array[n7];
                if (this.ns[n][n7] == 30) {
                    this.ns[n][n7] = 0;
                }
            }
            int n9 = 0;
            do {
                final int[] array2 = this.sspark[n][n7];
                --array2[n9];
            } while (++n9 < 30);
            int n11 = 0;
            do {
                final int[] array3 = this.ry[n][n7];
                --array3[n11];
                final int[] array4 = this.rx[n][n7];
                --array4[n11];
                final int[] array5 = this.rz[n][n7];
                --array5[n11];
            } while (++n11 < 7);
        } while (++n7 < 4);
    }

    public void recx(final int n, final float n2, final int n3) {
        this.rx[n3][n][this.nry[n3][n]] = 300;
        this.magx[n3][n][this.nry[n3][n]] = (int)n2;
        final int[] array = this.nrx[n3];
        ++array[n];
        if (this.nrx[n3][n] == 7) {
            this.nrx[n3][n] = 0;
        }
    }

    public void recy(final int n, final float n2, final boolean b, final int n3) {
        this.ry[n3][n][this.nry[n3][n]] = 300;
        this.magy[n3][n][this.nry[n3][n]] = (int)n2;
        this.mtouch[n3][this.nry[n3][n]] = b;
        final int[] array = this.nry[n3];
        ++array[n];
        if (this.nry[n3][n] == 7) {
            this.nry[n3][n] = 0;
        }
    }

    public void cotchinow(final int wasted) {
        if (this.caught >= 300) {
            this.wasted = wasted;
            int n = 0;
            do {
                this.starcar[n] = new Geometry(this.car[0][n], 0, 0, 0, 0);
                this.hsquash[n] = this.squash[0][n];
                this.hfix[n] = this.fix[n];
                this.hdest[n] = this.dest[n];
            } while (++n < 5);
            int n2 = 0;
            do {
                int n3 = 0;
                do {
                    this.hx[n2][n3] = this.x[n2][n3];
                    this.hy[n2][n3] = this.y[n2][n3];
                    this.hz[n2][n3] = this.z[n2][n3];
                    this.hxy[n2][n3] = this.xy[n2][n3];
                    this.hzy[n2][n3] = this.zy[n2][n3];
                    this.hxz[n2][n3] = this.xz[n2][n3];
                    this.hwxz[n2][n3] = this.wxz[n2][n3];
                    this.hwzy[n2][n3] = this.wzy[n2][n3];
                } while (++n3 < 5);
            } while (++n2 < 300);
            int n4 = 0;
            do {
                int n5 = 0;
                do {
                    this.hns[n4][n5] = this.ns[n4][n5];
                    int n6 = 0;
                    do {
                        this.hsspark[n4][n5][n6] = this.sspark[n4][n5][n6];
                        this.hsx[n4][n5][n6] = this.sx[n4][n5][n6];
                        this.hsy[n4][n5][n6] = this.sy[n4][n5][n6];
                        this.hsz[n4][n5][n6] = this.sz[n4][n5][n6];
                        this.hsmag[n4][n5][n6] = this.smag[n4][n5][n6];
                        this.hscx[n4][n5][n6] = this.scx[n4][n5][n6];
                        this.hscz[n4][n5][n6] = this.scz[n4][n5][n6];
                        this.hfulls[n4][n5][n6] = this.fulls[n4][n5][n6];
                    } while (++n6 < 30);
                } while (++n5 < 4);
            } while (++n4 < 5);
            int n7 = 0;
            do {
                int n8 = 0;
                do {
                    this.hnry[n7][n8] = this.nry[n7][n8];
                    this.hnrx[n7][n8] = this.nrx[n7][n8];
                    this.hnrz[n7][n8] = this.nrz[n7][n8];
                    int n9 = 0;
                    do {
                        this.hry[n7][n8][n9] = this.ry[n7][n8][n9];
                        this.hmagy[n7][n8][n9] = this.magy[n7][n8][n9];
                        this.hrx[n7][n8][n9] = this.rx[n7][n8][n9];
                        this.hmagx[n7][n8][n9] = this.magx[n7][n8][n9];
                        this.hrz[n7][n8][n9] = this.rz[n7][n8][n9];
                        this.hmagz[n7][n8][n9] = this.magz[n7][n8][n9];
                    } while (++n9 < 7);
                } while (++n8 < 4);
            } while (++n7 < 5);
            int n10 = 0;
            do {
                int n11 = 0;
                do {
                    this.hmtouch[n10][n11] = this.mtouch[n10][n11];
                } while (++n11 < 7);
            } while (++n10 < 5);
            this.highlightExists = true;
        }
    }

    public void chipx(final int n, float a, final Geometry geometry, final Madness madness) {
        if (Math.abs(a) > 100.0f) {
            if (a > 100.0f) {
                a -= 100.0f;
            }
            if (a < -100.0f) {
                a += 100.0f;
            }
            for (int i = 0; i < geometry.numberOfPlanes; ++i) {
                float n2 = 0.0f;
                for (int j = 0; j < geometry.planes[i].numberOfPoints; ++j) {
                    if (geometry.planes[i].wz == 0 && this.py(geometry.keyx[n], geometry.planes[i].ox[j], geometry.keyz[n], geometry.planes[i].oz[j]) < madness.clrad[madness.carIndex]) {
                        n2 = a / 20.0f * this.m.random();
                    }
                }
                if (n2 != 0.0f && Math.abs(n2) >= 1.0f) {
                    geometry.planes[i].chip = 1;
                    geometry.planes[i].ctmag = n2;
                }
            }
        }
    }

    public void regx(final int n, float a, final Geometry geometry, final Madness madness) {
        if (Math.abs(a) > 100.0f) {
            if (a > 100.0f) {
                a -= 100.0f;
            }
            if (a < -100.0f) {
                a += 100.0f;
            }
            for (int i = 0; i < geometry.numberOfPlanes; ++i) {
                float a2 = 0.0f;
                for (int j = 0; j < geometry.planes[i].numberOfPoints; ++j) {
                    if (geometry.planes[i].wz == 0 && this.py(geometry.keyx[n], geometry.planes[i].ox[j], geometry.keyz[n], geometry.planes[i].oz[j]) < madness.clrad[madness.carIndex]) {
                        a2 = a / 20.0f * this.m.random();
                        final int[] oz = geometry.planes[i].oz;
                        oz[j] -= (int)(a2 * Util.sin(geometry.xz) * Util.cos(geometry.zy));
                        final int[] ox = geometry.planes[i].ox;
                        ox[j] += (int)(a2 * Util.cos(geometry.xz) * Util.cos(geometry.xy));
                    }
                }
                if (a2 != 0.0f) {
                    if (Math.abs(a2) >= 1.0f) {
                        geometry.planes[i].chip = 1;
                        geometry.planes[i].ctmag = a2;
                    }
                    if (!geometry.planes[i].isNotColored && !geometry.planes[i].isGlass) {
                        if (geometry.planes[i].bfase > 20 && geometry.planes[i].hsb[1] > 0.2) {
                            geometry.planes[i].hsb[1] = 0.2f;
                        }
                        if (geometry.planes[i].bfase > 30) {
                            if (geometry.planes[i].hsb[2] < 0.5) {
                                geometry.planes[i].hsb[2] = 0.5f;
                            }
                            if (geometry.planes[i].hsb[1] > 0.1) {
                                geometry.planes[i].hsb[1] = 0.1f;
                            }
                        }
                        if (geometry.planes[i].bfase > 40) {
                            geometry.planes[i].hsb[1] = 0.05f;
                        }
                        if (geometry.planes[i].bfase > 50) {
                            if (geometry.planes[i].hsb[2] > 0.8) {
                                geometry.planes[i].hsb[2] = 0.8f;
                            }
                            geometry.planes[i].hsb[0] = 0.075f;
                            geometry.planes[i].hsb[1] = 0.05f;
                        }
                        if (geometry.planes[i].bfase > 60) {
                            geometry.planes[i].hsb[0] = 0.05f;
                        }
                        final Plane plane = geometry.planes[i];
                        plane.bfase += (int)Math.abs(a2);
                        new Color(geometry.planes[i].color[0], geometry.planes[i].color[1], geometry.planes[i].color[2]);
                        final Color hsbColor = Color.getHSBColor(geometry.planes[i].hsb[0], geometry.planes[i].hsb[1], geometry.planes[i].hsb[2]);
                        geometry.planes[i].color[0] = hsbColor.getRed();
                        geometry.planes[i].color[1] = hsbColor.getGreen();
                        geometry.planes[i].color[2] = hsbColor.getBlue();
                    }
                    if (geometry.planes[i].isGlass) {
                        final Plane plane2 = geometry.planes[i];
                        plane2.gr -= (int)Math.abs(a2 * 1.5);
                    }
                }
            }
        }
    }

    public void recz(final int n, final float n2, final int n3) {
        this.rz[n3][n][this.nry[n3][n]] = 300;
        this.magz[n3][n][this.nry[n3][n]] = (int)n2;
        final int[] array = this.nrz[n3];
        ++array[n];
        if (this.nrz[n3][n] == 7) {
            this.nrz[n3][n] = 0;
        }
    }

    public int py(final int n, final int n2, final int n3, final int n4) {
        return (n - n2) * (n - n2) + (n3 - n4) * (n3 - n4);
    }
}
