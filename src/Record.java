import java.awt.Color;

// 
// Decompiled by Procyon v0.5.36
// 

public class Record
{
    Medium m;
    int caught;
    boolean hcaught;
    boolean prepit;
    ContO[] ocar;
    int cntf;
    ContO[][] car;
    int[][] squash;
    int[] fix;
    int[] dest;
    int[][] x;
    int[][] y;
    int[][] z;
    int[][] xy;
    int[][] zy;
    int[][] xz;
    int[][] wxz;
    int[][] wzy;
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
    ContO[] starcar;
    int[] hsquash;
    int[] hfix;
    int[] hdest;
    int[][] hx;
    int[][] hy;
    int[][] hz;
    int[][] hxy;
    int[][] hzy;
    int[][] hxz;
    int[][] hwxz;
    int[][] hwzy;
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
    
    public void regy(final int n, float n2, final boolean b, final ContO contO, final Madness madness) {
        if (n2 > 100.0f) {
            n2 -= 100.0f;
            int n3 = 0;
            int n4 = 0;
            int i = contO.zy;
            int j = contO.xy;
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
                for (int k = 0; k < contO.npl; ++k) {
                    float n5 = 0.0f;
                    for (int l = 0; l < contO.p[k].n; ++l) {
                        if (contO.p[k].wz == 0 && this.py(contO.keyx[n], contO.p[k].ox[l], contO.keyz[n], contO.p[k].oz[l]) < madness.clrad[madness.cn]) {
                            n5 = n2 / 20.0f * this.m.random();
                            final int[] oz = contO.p[k].oz;
                            final int n6 = l;
                            oz[n6] += (int)(n5 * this.m.sin(i));
                            final int[] ox = contO.p[k].ox;
                            final int n7 = l;
                            ox[n7] -= (int)(n5 * this.m.sin(j));
                        }
                    }
                    if (n5 != 0.0f) {
                        if (Math.abs(n5) >= 1.0f) {
                            contO.p[k].chip = 1;
                            contO.p[k].ctmag = n5;
                        }
                        if (!contO.p[k].nocol && !contO.p[k].glass) {
                            if (contO.p[k].bfase > 20 && contO.p[k].hsb[1] > 0.2) {
                                contO.p[k].hsb[1] = 0.2f;
                            }
                            if (contO.p[k].bfase > 30) {
                                if (contO.p[k].hsb[2] < 0.5) {
                                    contO.p[k].hsb[2] = 0.5f;
                                }
                                if (contO.p[k].hsb[1] > 0.1) {
                                    contO.p[k].hsb[1] = 0.1f;
                                }
                            }
                            if (contO.p[k].bfase > 40) {
                                contO.p[k].hsb[1] = 0.05f;
                            }
                            if (contO.p[k].bfase > 50) {
                                if (contO.p[k].hsb[2] > 0.8) {
                                    contO.p[k].hsb[2] = 0.8f;
                                }
                                contO.p[k].hsb[0] = 0.075f;
                                contO.p[k].hsb[1] = 0.05f;
                            }
                            if (contO.p[k].bfase > 60) {
                                contO.p[k].hsb[0] = 0.05f;
                            }
                            final Plane plane = contO.p[k];
                            plane.bfase += (int)n5;
                            new Color(contO.p[k].c[0], contO.p[k].c[1], contO.p[k].c[2]);
                            final Color hsbColor = Color.getHSBColor(contO.p[k].hsb[0], contO.p[k].hsb[1], contO.p[k].hsb[2]);
                            contO.p[k].c[0] = hsbColor.getRed();
                            contO.p[k].c[1] = hsbColor.getGreen();
                            contO.p[k].c[2] = hsbColor.getBlue();
                        }
                        if (contO.p[k].glass) {
                            final Plane plane2 = contO.p[k];
                            plane2.gr -= (int)Math.abs(n5 * 1.5);
                        }
                    }
                }
            }
            if (n4 * n3 == -1) {
                int n8 = 0;
                int n9 = 1;
                for (int n10 = 0; n10 < contO.npl; ++n10) {
                    float n11 = 0.0f;
                    for (int n12 = 0; n12 < contO.p[n10].n; ++n12) {
                        if (contO.p[n10].wz == 0) {
                            n11 = n2 / 15.0f * this.m.random();
                            if ((Math.abs(contO.p[n10].oy[n12] - madness.flipy[madness.cn] - this.squash[0][madness.im]) < madness.msquash[madness.cn] * 3 || contO.p[n10].oy[n12] < madness.flipy[madness.cn] + this.squash[0][madness.im]) && this.squash[0][madness.im] < madness.msquash[madness.cn]) {
                                final int[] oy = contO.p[n10].oy;
                                final int n13 = n12;
                                oy[n13] += (int)n11;
                                n8 += (int)n11;
                                ++n9;
                            }
                        }
                    }
                    if (contO.p[n10].glass) {
                        final Plane plane3 = contO.p[n10];
                        plane3.gr -= 5;
                    }
                    else if (n11 != 0.0f) {
                        final Plane plane4 = contO.p[n10];
                        plane4.bfase += (int)n11;
                    }
                    if (Math.abs(n11) >= 1.0f) {
                        contO.p[n10].chip = 1;
                        contO.p[n10].ctmag = n11;
                    }
                }
                final int[] array = this.squash[0];
                final int im = madness.im;
                array[im] += n8 / n9;
            }
        }
    }
    
    public void reset(final ContO[] array) {
        this.caught = 0;
        this.hcaught = false;
        this.wasted = 0;
        this.whenwasted = 0;
        this.powered = 0;
        int n = 0;
        do {
            if (this.prepit) {
                this.starcar[n] = new ContO(array[n], 0, 0, 0, 0);
            }
            this.fix[n] = -1;
            this.dest[n] = -1;
            this.cntdest[n] = 0;
        } while (++n < 5);
        int n2 = 0;
        do {
            int n3 = 0;
            do {
                this.car[n2][n3] = new ContO(array[n3], 0, 0, 0, 0);
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
        this.hcaught = false;
        this.prepit = true;
        this.ocar = new ContO[5];
        this.cntf = 50;
        this.car = new ContO[6][5];
        this.squash = new int[6][5];
        this.fix = new int[5];
        this.dest = new int[5];
        this.x = new int[300][5];
        this.y = new int[300][5];
        this.z = new int[300][5];
        this.xy = new int[300][5];
        this.zy = new int[300][5];
        this.xz = new int[300][5];
        this.wxz = new int[300][5];
        this.wzy = new int[300][5];
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
        this.starcar = new ContO[5];
        this.hsquash = new int[5];
        this.hfix = new int[] { -1, -1, -1, -1, -1 };
        this.hdest = new int[] { -1, -1, -1, -1, -1 };
        this.hx = new int[300][5];
        this.hy = new int[300][5];
        this.hz = new int[300][5];
        this.hxy = new int[300][5];
        this.hzy = new int[300][5];
        this.hxz = new int[300][5];
        this.hwxz = new int[300][5];
        this.hwzy = new int[300][5];
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
    
    public void playh(final ContO contO, final Madness madness, final int n, final int lastfr) {
        contO.x = this.hx[lastfr][n];
        contO.y = this.hy[lastfr][n];
        contO.z = this.hz[lastfr][n];
        contO.zy = this.hzy[lastfr][n];
        contO.xy = this.hxy[lastfr][n];
        contO.xz = this.hxz[lastfr][n];
        contO.wxz = this.hwxz[lastfr][n];
        contO.wzy = this.hwzy[lastfr][n];
        if (lastfr == 0) {
            this.cntdest[n] = 0;
        }
        if (this.hdest[n] == lastfr) {
            this.cntdest[n] = 7;
        }
        if (lastfr == 0 && this.hdest[n] < -1) {
            for (int i = 0; i < contO.npl; ++i) {
                if (contO.p[i].wz == 0) {
                    contO.p[i].embos = 13;
                }
            }
        }
        if (this.cntdest[n] != 0) {
            for (int j = 0; j < contO.npl; ++j) {
                if (contO.p[j].wz == 0) {
                    contO.p[j].embos = 1;
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
                    contO.stg[n2] = 1;
                    contO.dov[n2] = -1;
                    contO.sx[n2] = this.hsx[n][n2][n3];
                    contO.sy[n2] = this.hsy[n][n2][n3];
                    contO.sz[n2] = this.hsz[n][n2][n3];
                    contO.smag[n2] = this.hsmag[n][n2][n3];
                    contO.scx[n2] = this.hscx[n][n2][n3];
                    contO.scz[n2] = this.hscz[n][n2][n3];
                    contO.fulls[n2] = this.hfulls[n][n2][n3];
                }
            } while (++n3 < 30);
            int n4 = 0;
            do {
                if (this.hry[n][n2][n4] == lastfr && this.lastfr != lastfr) {
                    this.regy(n2, (float)this.hmagy[n][n2][n4], this.hmtouch[n][n4], contO, madness);
                }
                if (this.hrx[n][n2][n4] == lastfr) {
                    if (this.lastfr != lastfr) {
                        this.regx(n2, (float)this.hmagx[n][n2][n4], contO, madness);
                    }
                    else {
                        this.chipx(n2, (float)this.hmagx[n][n2][n4], contO, madness);
                    }
                }
                if (this.hrz[n][n2][n4] == lastfr) {
                    if (this.lastfr != lastfr) {
                        this.regz(n2, (float)this.hmagz[n][n2][n4], contO, madness);
                    }
                    else {
                        this.chipz(n2, (float)this.hmagz[n][n2][n4], contO, madness);
                    }
                }
            } while (++n4 < 7);
        } while (++n2 < 4);
        this.lastfr = lastfr;
    }
    
    public void chipz(final int n, float a, final ContO contO, final Madness madness) {
        if (Math.abs(a) > 100.0f) {
            if (a > 100.0f) {
                a -= 100.0f;
            }
            if (a < -100.0f) {
                a += 100.0f;
            }
            for (int i = 0; i < contO.npl; ++i) {
                float n2 = 0.0f;
                for (int j = 0; j < contO.p[i].n; ++j) {
                    if (contO.p[i].wz == 0 && this.py(contO.keyx[n], contO.p[i].ox[j], contO.keyz[n], contO.p[i].oz[j]) < madness.clrad[madness.cn]) {
                        n2 = a / 20.0f * this.m.random();
                    }
                }
                if (n2 != 0.0f && Math.abs(n2) >= 1.0f) {
                    contO.p[i].chip = 1;
                    contO.p[i].ctmag = n2;
                }
            }
        }
    }
    
    public void regz(final int n, float a, final ContO contO, final Madness madness) {
        if (Math.abs(a) > 100.0f) {
            if (a > 100.0f) {
                a -= 100.0f;
            }
            if (a < -100.0f) {
                a += 100.0f;
            }
            for (int i = 0; i < contO.npl; ++i) {
                float a2 = 0.0f;
                for (int j = 0; j < contO.p[i].n; ++j) {
                    if (contO.p[i].wz == 0 && this.py(contO.keyx[n], contO.p[i].ox[j], contO.keyz[n], contO.p[i].oz[j]) < madness.clrad[madness.cn]) {
                        a2 = a / 20.0f * this.m.random();
                        final int[] oz = contO.p[i].oz;
                        final int n2 = j;
                        oz[n2] += (int)(a2 * this.m.cos(contO.xz) * this.m.cos(contO.zy));
                        final int[] ox = contO.p[i].ox;
                        final int n3 = j;
                        ox[n3] += (int)(a2 * this.m.sin(contO.xz) * this.m.cos(contO.xy));
                    }
                }
                if (a2 != 0.0f) {
                    if (Math.abs(a2) >= 1.0f) {
                        contO.p[i].chip = 1;
                        contO.p[i].ctmag = a2;
                    }
                    if (!contO.p[i].nocol && !contO.p[i].glass) {
                        if (contO.p[i].bfase > 20 && contO.p[i].hsb[1] > 0.2) {
                            contO.p[i].hsb[1] = 0.2f;
                        }
                        if (contO.p[i].bfase > 30) {
                            if (contO.p[i].hsb[2] < 0.5) {
                                contO.p[i].hsb[2] = 0.5f;
                            }
                            if (contO.p[i].hsb[1] > 0.1) {
                                contO.p[i].hsb[1] = 0.1f;
                            }
                        }
                        if (contO.p[i].bfase > 40) {
                            contO.p[i].hsb[1] = 0.05f;
                        }
                        if (contO.p[i].bfase > 50) {
                            if (contO.p[i].hsb[2] > 0.8) {
                                contO.p[i].hsb[2] = 0.8f;
                            }
                            contO.p[i].hsb[0] = 0.075f;
                            contO.p[i].hsb[1] = 0.05f;
                        }
                        if (contO.p[i].bfase > 60) {
                            contO.p[i].hsb[0] = 0.05f;
                        }
                        final Plane plane = contO.p[i];
                        plane.bfase += (int)Math.abs(a2);
                        new Color(contO.p[i].c[0], contO.p[i].c[1], contO.p[i].c[2]);
                        final Color hsbColor = Color.getHSBColor(contO.p[i].hsb[0], contO.p[i].hsb[1], contO.p[i].hsb[2]);
                        contO.p[i].c[0] = hsbColor.getRed();
                        contO.p[i].c[1] = hsbColor.getGreen();
                        contO.p[i].c[2] = hsbColor.getBlue();
                    }
                    if (contO.p[i].glass) {
                        final Plane plane2 = contO.p[i];
                        plane2.gr -= (int)Math.abs(a2 * 1.5);
                    }
                }
            }
        }
    }
    
    public void play(final ContO contO, final Madness madness, final int n, final int n2) {
        contO.x = this.x[n2][n];
        contO.y = this.y[n2][n];
        contO.z = this.z[n2][n];
        contO.zy = this.zy[n2][n];
        contO.xy = this.xy[n2][n];
        contO.xz = this.xz[n2][n];
        contO.wxz = this.wxz[n2][n];
        contO.wzy = this.wzy[n2][n];
        if (n2 == 0) {
            this.cntdest[n] = 0;
        }
        if (this.dest[n] == n2) {
            this.cntdest[n] = 7;
        }
        if (n2 == 0 && this.dest[n] < -1) {
            for (int i = 0; i < contO.npl; ++i) {
                if (contO.p[i].wz == 0) {
                    contO.p[i].embos = 13;
                }
            }
        }
        if (this.cntdest[n] != 0) {
            for (int j = 0; j < contO.npl; ++j) {
                if (contO.p[j].wz == 0) {
                    contO.p[j].embos = 1;
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
                    contO.stg[n3] = 1;
                    contO.dov[n3] = -1;
                    contO.sx[n3] = this.sx[n][n3][n4];
                    contO.sy[n3] = this.sy[n][n3][n4];
                    contO.sz[n3] = this.sz[n][n3][n4];
                    contO.smag[n3] = this.smag[n][n3][n4];
                    contO.scx[n3] = this.scx[n][n3][n4];
                    contO.scz[n3] = this.scz[n][n3][n4];
                    contO.fulls[n3] = this.fulls[n][n3][n4];
                }
            } while (++n4 < 30);
            int n5 = 0;
            do {
                if (this.ry[n][n3][n5] == n2) {
                    this.regy(n3, (float)this.magy[n][n3][n5], this.mtouch[n][n5], contO, madness);
                }
                if (this.rx[n][n3][n5] == n2) {
                    this.regx(n3, (float)this.magx[n][n3][n5], contO, madness);
                }
                if (this.rz[n][n3][n5] == n2) {
                    this.regz(n3, (float)this.magz[n][n3][n5], contO, madness);
                }
            } while (++n5 < 7);
        } while (++n3 < 4);
    }
    
    public void rec(final ContO contO, final int n, final int n2, final int n3, final int n4) {
        if (n == 0) {
            ++this.caught;
        }
        if (this.cntf == 50) {
            int n5 = 0;
            do {
                this.car[n5][n] = new ContO(this.car[n5 + 1][n], 0, 0, 0, 0);
                this.squash[n5][n] = this.squash[n5 + 1][n];
            } while (++n5 < 5);
            this.car[5][n] = new ContO(contO, 0, 0, 0, 0);
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
        this.x[299][n] = contO.x;
        this.y[299][n] = contO.y;
        this.z[299][n] = contO.z;
        this.xy[299][n] = contO.xy;
        this.zy[299][n] = contO.zy;
        this.xz[299][n] = contO.xz;
        this.wxz[299][n] = contO.wxz;
        this.wzy[299][n] = contO.wzy;
        int n7 = 0;
        do {
            if (contO.stg[n7] == 1) {
                this.sspark[n][n7][this.ns[n][n7]] = 300;
                this.sx[n][n7][this.ns[n][n7]] = contO.sx[n7];
                this.sy[n][n7][this.ns[n][n7]] = contO.sy[n7];
                this.sz[n][n7][this.ns[n][n7]] = contO.sz[n7];
                this.smag[n][n7][this.ns[n][n7]] = contO.smag[n7];
                this.scx[n][n7][this.ns[n][n7]] = contO.scx[n7];
                this.scz[n][n7][this.ns[n][n7]] = contO.scz[n7];
                this.fulls[n][n7][this.ns[n][n7]] = contO.fulls[n7];
                final int[] array = this.ns[n];
                final int n8 = n7;
                ++array[n8];
                if (this.ns[n][n7] == 30) {
                    this.ns[n][n7] = 0;
                }
            }
            int n9 = 0;
            do {
                final int[] array2 = this.sspark[n][n7];
                final int n10 = n9;
                --array2[n10];
            } while (++n9 < 30);
            int n11 = 0;
            do {
                final int[] array3 = this.ry[n][n7];
                final int n12 = n11;
                --array3[n12];
                final int[] array4 = this.rx[n][n7];
                final int n13 = n11;
                --array4[n13];
                final int[] array5 = this.rz[n][n7];
                final int n14 = n11;
                --array5[n14];
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
                this.starcar[n] = new ContO(this.car[0][n], 0, 0, 0, 0);
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
            this.hcaught = true;
        }
    }
    
    public void chipx(final int n, float a, final ContO contO, final Madness madness) {
        if (Math.abs(a) > 100.0f) {
            if (a > 100.0f) {
                a -= 100.0f;
            }
            if (a < -100.0f) {
                a += 100.0f;
            }
            for (int i = 0; i < contO.npl; ++i) {
                float n2 = 0.0f;
                for (int j = 0; j < contO.p[i].n; ++j) {
                    if (contO.p[i].wz == 0 && this.py(contO.keyx[n], contO.p[i].ox[j], contO.keyz[n], contO.p[i].oz[j]) < madness.clrad[madness.cn]) {
                        n2 = a / 20.0f * this.m.random();
                    }
                }
                if (n2 != 0.0f && Math.abs(n2) >= 1.0f) {
                    contO.p[i].chip = 1;
                    contO.p[i].ctmag = n2;
                }
            }
        }
    }
    
    public void regx(final int n, float a, final ContO contO, final Madness madness) {
        if (Math.abs(a) > 100.0f) {
            if (a > 100.0f) {
                a -= 100.0f;
            }
            if (a < -100.0f) {
                a += 100.0f;
            }
            for (int i = 0; i < contO.npl; ++i) {
                float a2 = 0.0f;
                for (int j = 0; j < contO.p[i].n; ++j) {
                    if (contO.p[i].wz == 0 && this.py(contO.keyx[n], contO.p[i].ox[j], contO.keyz[n], contO.p[i].oz[j]) < madness.clrad[madness.cn]) {
                        a2 = a / 20.0f * this.m.random();
                        final int[] oz = contO.p[i].oz;
                        final int n2 = j;
                        oz[n2] -= (int)(a2 * this.m.sin(contO.xz) * this.m.cos(contO.zy));
                        final int[] ox = contO.p[i].ox;
                        final int n3 = j;
                        ox[n3] += (int)(a2 * this.m.cos(contO.xz) * this.m.cos(contO.xy));
                    }
                }
                if (a2 != 0.0f) {
                    if (Math.abs(a2) >= 1.0f) {
                        contO.p[i].chip = 1;
                        contO.p[i].ctmag = a2;
                    }
                    if (!contO.p[i].nocol && !contO.p[i].glass) {
                        if (contO.p[i].bfase > 20 && contO.p[i].hsb[1] > 0.2) {
                            contO.p[i].hsb[1] = 0.2f;
                        }
                        if (contO.p[i].bfase > 30) {
                            if (contO.p[i].hsb[2] < 0.5) {
                                contO.p[i].hsb[2] = 0.5f;
                            }
                            if (contO.p[i].hsb[1] > 0.1) {
                                contO.p[i].hsb[1] = 0.1f;
                            }
                        }
                        if (contO.p[i].bfase > 40) {
                            contO.p[i].hsb[1] = 0.05f;
                        }
                        if (contO.p[i].bfase > 50) {
                            if (contO.p[i].hsb[2] > 0.8) {
                                contO.p[i].hsb[2] = 0.8f;
                            }
                            contO.p[i].hsb[0] = 0.075f;
                            contO.p[i].hsb[1] = 0.05f;
                        }
                        if (contO.p[i].bfase > 60) {
                            contO.p[i].hsb[0] = 0.05f;
                        }
                        final Plane plane = contO.p[i];
                        plane.bfase += (int)Math.abs(a2);
                        new Color(contO.p[i].c[0], contO.p[i].c[1], contO.p[i].c[2]);
                        final Color hsbColor = Color.getHSBColor(contO.p[i].hsb[0], contO.p[i].hsb[1], contO.p[i].hsb[2]);
                        contO.p[i].c[0] = hsbColor.getRed();
                        contO.p[i].c[1] = hsbColor.getGreen();
                        contO.p[i].c[2] = hsbColor.getBlue();
                    }
                    if (contO.p[i].glass) {
                        final Plane plane2 = contO.p[i];
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
