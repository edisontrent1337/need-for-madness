package main.java;

import java.awt.Color;

//
// Decompiled by Procyon v0.5.36
//

public class Madness
{
    Medium medium;
    Record rpd;
    GraphicsPanel graphicsPanel;
    int cn;
    int im;
    float mxz;
    float cxz;
    float[][] acelf;
    int[][] swits;
    int[] handb;
    float[] airs;
    int[] airc;
    float[] drag;
    int[] turn;
    float[] grip;
    float[] bounce;
    float[] simag;
    float[] moment;
    float[] comprad;
    int[] push;
    int[] revpush;
    int[] lift;
    int[] revlift;
    int[] powerloss;
    int[] flipy;
    int[] msquash;
    int[] clrad;
    int[] maxDamage;
    float[] dammult;
    int[] outdam;
    boolean[] dominate;
    boolean[] caught;
    int pzy;
    int pxy;
    float speed;
    float forca;
    float[] scy;
    float[] scz;
    float[] scx;
    boolean mtouch;
    boolean wtouch;
    int cntouch;
    boolean capsized;
    int txz;
    int fxz;
    int pmlt;
    int nmlt;
    int dcnt;
    int skid;
    boolean pushed;
    boolean gtouch;
    boolean pl;
    boolean pr;
    boolean pd;
    boolean pu;
    int loop;
    float ucomp;
    float dcomp;
    float lcomp;
    float rcomp;
    int lxz;
    int travxy;
    int travzy;
    int travxz;
    int trcnt;
    int capcnt;
    int srfcnt;
    boolean rtab;
    boolean ftab;
    boolean btab;
    boolean surfer;
    float powerup;
    int xtpower;
    float tilt;
    int squash;
    int nbsq;
    int currentDamage;
    int cntdest;
    boolean dest;
    boolean newcar;
    int pan;
    int pcleared;
    int clear;
    int nlaps;
    int focus;
    float power;
    int missedCheckpoint;
    int lastcolido;
    int point;
    boolean nofocus;
    boolean colidim;

    public int py(final int n, final int n2, final int n3, final int n4) {
        return (n - n2) * (n - n2) + (n3 - n4) * (n3 - n4);
    }

    public void regy(final int n, float n2, final Geometry geometry) {
        n2 *= this.dammult[this.cn];
        if (n2 > 100.0f) {
            this.rpd.recy(n, n2, this.mtouch, this.im);
            n2 -= 100.0f;
            int n3 = 0;
            int n4 = 0;
            int i = geometry.zy;
            int j = geometry.xy;

            i = i % 360;

            if (i < 210 && i > 150) {
                n3 = -1;
            }
            if (i > 330 || i < 30) {
                n3 = 1;
            }

            j = j % 360;

            if (j < 210 && j > 150) {
                n4 = -1;
            }
            if (j > 330 || j < 30) {
                n4 = 1;
            }
            if (this.im == 0 || this.colidim) {
                this.graphicsPanel.crash(n2, n4 * n3);
            }
            if (n4 * n3 == 0 || this.mtouch) {
                for (int k = 0; k < geometry.numberOfPlanes; ++k) {
                    float ctmag = 0.0f;
                    for (int l = 0; l < geometry.planes[k].n; ++l) {
                        if (geometry.planes[k].wz == 0 && this.py(geometry.keyx[n], geometry.planes[k].ox[l], geometry.keyz[n], geometry.planes[k].oz[l]) < this.clrad[this.cn]) {
                            ctmag = n2 / 20.0f * this.medium.random();
                            final int[] oz = geometry.planes[k].oz;
                            oz[l] += (int)(ctmag * this.medium.sin(i));
                            final int[] ox = geometry.planes[k].ox;
                            ox[l] -= (int)(ctmag * this.medium.sin(j));
                            this.currentDamage += (int)Math.abs(ctmag);
                        }
                    }
                    if (ctmag != 0.0f) {
                        if (Math.abs(ctmag) >= 1.0f) {
                            geometry.planes[k].chip = 1;
                            geometry.planes[k].ctmag = ctmag;
                        }
                        if (!geometry.planes[k].nocol && !geometry.planes[k].glass) {
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
                            plane.bfase += (int)ctmag;
                            new Color(geometry.planes[k].c[0], geometry.planes[k].c[1], geometry.planes[k].c[2]);
                            final Color hsbColor = Color.getHSBColor(geometry.planes[k].hsb[0], geometry.planes[k].hsb[1], geometry.planes[k].hsb[2]);
                            geometry.planes[k].c[0] = hsbColor.getRed();
                            geometry.planes[k].c[1] = hsbColor.getGreen();
                            geometry.planes[k].c[2] = hsbColor.getBlue();
                        }
                        if (geometry.planes[k].glass) {
                            final Plane plane2 = geometry.planes[k];
                            plane2.gr -= (int)Math.abs(ctmag * 1.5);
                        }
                    }
                }
            }
            if (n4 * n3 == -1) {
                if (this.nbsq > 0) {
                    int n7 = 0;
                    int n8 = 1;
                    for (int n9 = 0; n9 < geometry.numberOfPlanes; ++n9) {
                        float ctmag2 = 0.0f;
                        for (int n10 = 0; n10 < geometry.planes[n9].n; ++n10) {
                            if (geometry.planes[n9].wz == 0) {
                                ctmag2 = n2 / 15.0f * this.medium.random();
                                if ((Math.abs(geometry.planes[n9].oy[n10] - this.flipy[this.cn] - this.squash) < this.msquash[this.cn] * 3 || geometry.planes[n9].oy[n10] < this.flipy[this.cn] + this.squash) && this.squash < this.msquash[this.cn]) {
                                    final int[] oy = geometry.planes[n9].oy;
                                    oy[n10] += (int)ctmag2;
                                    n7 += (int)ctmag2;
                                    ++n8;
                                    this.currentDamage += (int)Math.abs(ctmag2);
                                }
                            }
                        }
                        if (geometry.planes[n9].glass) {
                            final Plane plane3 = geometry.planes[n9];
                            plane3.gr -= 5;
                        }
                        else if (ctmag2 != 0.0f) {
                            final Plane plane4 = geometry.planes[n9];
                            plane4.bfase += (int)ctmag2;
                        }
                        if (Math.abs(ctmag2) >= 1.0f) {
                            geometry.planes[n9].chip = 1;
                            geometry.planes[n9].ctmag = ctmag2;
                        }
                    }
                    this.squash += n7 / n8;
                    this.nbsq = 0;
                }
                else {
                    ++this.nbsq;
                }
            }
        }
    }

    public Madness(final Medium medium, final Record rpd, final GraphicsPanel graphicsPanel, final int im) {
        this.cn = 0;
        this.im = 0;
        this.mxz = 0;
        this.cxz = 0;
        this.acelf = new float[][] { { 11.0f, 5.0f, 3.0f }, { 8.0f, 7.0f, 5.0f }, { 10.0f, 5.0f, 3.5f }, { 11.0f, 6.0f, 3.5f }, { 10.0f, 5.0f, 3.5f }, { 12.0f, 6.0f, 3.0f }, { 7.0f, 9.0f, 4.0f }, { 9.0f, 5.0f, 3.0f }, { 11.0f, 7.0f, 4.0f }, { 12.0f, 6.0f, 3.5f } };
        this.swits = new int[][] { { 50, 180, 280 }, { 50, 200, 310 }, { 60, 180, 275 }, { 70, 190, 295 }, { 70, 170, 275 }, { 60, 200, 290 }, { 60, 170, 280 }, { 50, 160, 270 }, { 80, 200, 300 }, { 70, 210, 290 } };
        this.handb = new int[] { 7, 10, 7, 15, 12, 8, 9, 10, 7, 7, 7 };
        this.airs = new float[] { 1.0f, 1.2f, 0.95f, 1.0f, 1.5f, 1.0f, 0.9f, 0.8f, 1.3f, 1.0f };
        this.airc = new int[] { 70, 30, 40, 40, 30, 50, 40, 10, 100, 60 };
        this.drag = new float[] { 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f };
        this.turn = new int[] { 6, 9, 5, 7, 6, 7, 5, 4, 7, 6 };
        this.grip = new float[] { 20.0f, 27.0f, 18.0f, 22.0f, 15.0f, 20.0f, 25.0f, 25.0f, 25.0f, 27.0f };
        this.bounce = new float[] { 1.2f, 1.05f, 1.3f, 1.15f, 1.3f, 1.2f, 1.15f, 0.8f, 1.1f, 1.15f };
        this.simag = new float[] { 0.9f, 0.85f, 1.05f, 0.9f, 0.85f, 0.9f, 1.05f, 1.1f, 0.9f, 1.15f };
        this.moment = new float[] { 1.2f, 0.75f, 1.4f, 1.0f, 0.85f, 1.25f, 1.4f, 2.0f, 1.5f, 2.0f };
        this.comprad = new float[] { 0.5f, 0.4f, 0.8f, 0.5f, 0.5f, 0.5f, 0.5f, 1.5f, 0.5f, 0.8f };
        this.push = new int[] { 2, 2, 3, 3, 2, 2, 2, 4, 2, 2 };
        this.revpush = new int[] { 2, 3, 2, 2, 2, 2, 2, 1, 2, 1 };
        this.lift = new int[] { 0, 30, 0, 20, 0, 30, 0, 0, 35, 0 };
        this.revlift = new int[] { 0, 0, 15, 0, 0, 0, 0, 0, 0, 32 };
        this.powerloss = new int[] { 2500000, 2500000, 3500000, 2500000, 2500000, 2500000, 3200000, 4500000, 3000000, 5500000 };
        this.flipy = new int[] { -50, -26, -90, -41, -55, -53, -54, -85, -60, -127 };
        this.msquash = new int[] { 7, 3, 7, 2, 3, 3, 6, 10, 10, 8 };
        this.clrad = new int[] { 3300, 1500, 4700, 3000, 1700, 2100, 3500, 7000, 4000, 4000 };
        this.maxDamage = new int[] { 3500, 1700, 7500, 5000, 3000, 4100, 6000, 9000, 4400, 9500 };
        this.dammult = new float[] { 1.0f, 2.028f, 0.9375f, 1.1791f, 1.0f, 0.9066f, 1.0f, 0.6969f, 0.8266f, 0.7667f };
        this.outdam = new int[] { 77, 35, 80, 67, 55, 75, 81, 100, 75, 90 };
        this.dominate = new boolean[5];
        this.caught = new boolean[5];
        this.pzy = 0;
        this.pxy = 0;
        this.speed = 0.0f;
        this.forca = 0.0f;
        this.scy = new float[4];
        this.scz = new float[4];
        this.scx = new float[4];
        this.mtouch = false;
        this.wtouch = false;
        this.cntouch = 0;
        this.capsized = false;
        this.txz = 0;
        this.fxz = 0;
        this.pmlt = 1;
        this.nmlt = 1;
        this.dcnt = 0;
        this.skid = 0;
        this.pushed = false;
        this.gtouch = false;
        this.pl = false;
        this.pr = false;
        this.pd = false;
        this.pu = false;
        this.loop = 0;
        this.ucomp = 0.0f;
        this.dcomp = 0.0f;
        this.lcomp = 0.0f;
        this.rcomp = 0.0f;
        this.lxz = 0;
        this.travxy = 0;
        this.travzy = 0;
        this.travxz = 0;
        this.trcnt = 0;
        this.capcnt = 0;
        this.srfcnt = 0;
        this.rtab = false;
        this.ftab = false;
        this.btab = false;
        this.surfer = false;
        this.powerup = 0.0f;
        this.xtpower = 0;
        this.tilt = 0.0f;
        this.squash = 0;
        this.nbsq = 0;
        this.currentDamage = 0;
        this.cntdest = 0;
        this.dest = false;
        this.newcar = false;
        this.pan = 0;
        this.pcleared = 0;
        this.clear = 0;
        this.nlaps = 0;
        this.focus = -1;
        this.power = 75.0f;
        this.missedCheckpoint = 0;
        this.lastcolido = 0;
        this.point = 0;
        this.nofocus = false;
        this.colidim = false;
        this.medium = medium;
        this.rpd = rpd;
        this.graphicsPanel = graphicsPanel;
        this.im = im;
    }

    public int rpy(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        return (int)((n - n2) * (n - n2) + (n3 - n4) * (n3 - n4) + (n5 - n6) * (n5 - n6));
    }

    public void regz(final int n, float a, final Geometry geometry) {
        a *= this.dammult[this.cn];
        if (Math.abs(a) > 100.0f) {
            this.rpd.recz(n, a, this.im);
            if (a > 100.0f) {
                a -= 100.0f;
            }
            if (a < -100.0f) {
                a += 100.0f;
            }
            if (this.im == 0 || this.colidim) {
                this.graphicsPanel.crash(a, 0);
            }
            for (int i = 0; i < geometry.numberOfPlanes; ++i) {
                float n2 = 0.0f;
                for (int j = 0; j < geometry.planes[i].n; ++j) {
                    if (geometry.planes[i].wz == 0 && this.py(geometry.keyx[n], geometry.planes[i].ox[j], geometry.keyz[n], geometry.planes[i].oz[j]) < this.clrad[this.cn]) {
                        n2 = a / 20.0f * this.medium.random();
                        final int[] oz = geometry.planes[i].oz;
                        oz[j] += (int)(n2 * this.medium.cos(geometry.xz) * this.medium.cos(geometry.zy));
                        final int[] ox = geometry.planes[i].ox;
                        ox[j] += (int)(n2 * this.medium.sin(geometry.xz) * this.medium.cos(geometry.xy));
                        this.currentDamage += (int)Math.abs(n2);
                    }
                }
                if (n2 != 0.0f) {
                    if (Math.abs(n2) >= 1.0f) {
                        geometry.planes[i].chip = 1;
                        geometry.planes[i].ctmag = n2;
                    }
                    if (!geometry.planes[i].nocol && !geometry.planes[i].glass) {
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
                        plane.bfase += (int)Math.abs(n2);
                        new Color(geometry.planes[i].c[0], geometry.planes[i].c[1], geometry.planes[i].c[2]);
                        final Color hsbColor = Color.getHSBColor(geometry.planes[i].hsb[0], geometry.planes[i].hsb[1], geometry.planes[i].hsb[2]);
                        geometry.planes[i].c[0] = hsbColor.getRed();
                        geometry.planes[i].c[1] = hsbColor.getGreen();
                        geometry.planes[i].c[2] = hsbColor.getBlue();
                    }
                    if (geometry.planes[i].glass) {
                        final Plane plane2 = geometry.planes[i];
                        plane2.gr -= (int)Math.abs(n2 * 1.5);
                    }
                }
            }
        }
    }

    public void rot(final float[] array, final float[] array2, final int n, final int n2, final int n3, final int n4) {
        if (n3 != 0) {
            for (int i = 0; i < n4; ++i) {
                final float n5 = array[i];
                final float n6 = array2[i];
                array[i] = n + ((n5 - n) * this.medium.cos(n3) - (n6 - n2) * this.medium.sin(n3));
                array2[i] = n2 + ((n5 - n) * this.medium.sin(n3) + (n6 - n2) * this.medium.cos(n3));
            }
        }
    }

    public void colide(final Geometry geometry, final Madness madness, final Geometry geometry2) {
        final float[] array = new float[4];
        final float[] array2 = new float[4];
        final float[] array3 = new float[4];
        final float[] array4 = new float[4];
        final float[] array5 = new float[4];
        final float[] array6 = new float[4];
        int n = 0;
        do {
            array[n] = (float)(geometry.x + geometry.keyx[n]);
            if (this.capsized) {
                array2[n] = (float)(geometry.y + this.flipy[this.cn] + this.squash);
            }
            else {
                array2[n] = (float)(geometry.y + geometry.grat);
            }
            array3[n] = (float)(geometry.z + geometry.keyz[n]);
            array4[n] = (float)(geometry2.x + geometry2.keyx[n]);
            if (this.capsized) {
                array5[n] = (float)(geometry2.y + madness.flipy[madness.cn] + madness.squash);
            }
            else {
                array5[n] = (float)(geometry2.y + geometry2.grat);
            }
            array6[n] = (float)(geometry2.z + geometry2.keyz[n]);
        } while (++n < 4);
        this.rot(array, array2, geometry.x, geometry.y, geometry.xy, 4);
        this.rot(array2, array3, geometry.y, geometry.z, geometry.zy, 4);
        this.rot(array, array3, geometry.x, geometry.z, geometry.xz, 4);
        this.rot(array4, array5, geometry2.x, geometry2.y, geometry2.xy, 4);
        this.rot(array5, array6, geometry2.y, geometry2.z, geometry2.zy, 4);
        this.rot(array4, array6, geometry2.x, geometry2.z, geometry2.xz, 4);
        if (this.rpy((float) geometry.x, (float) geometry2.x, (float) geometry.y, (float) geometry2.y, (float) geometry.z, (float) geometry2.z) < (geometry.maxR * geometry.maxR + geometry2.maxR * geometry2.maxR) * 1.5) {
            if (!this.caught[madness.im] && (this.speed != 0.0f || madness.speed != 0.0f)) {
                if (Math.abs(this.power * this.speed * this.moment[this.cn]) != Math.abs(madness.power * madness.speed * madness.moment[madness.cn])) {
                    this.dominate[madness.im] = Math.abs(this.power * this.speed * this.moment[this.cn]) > Math.abs(madness.power * madness.speed * madness.moment[madness.cn]);
                }
                else this.dominate[madness.im] = this.moment[this.cn] > madness.moment[madness.cn];
                this.caught[madness.im] = true;
            }
        }
        else if (this.caught[madness.im]) {
            this.caught[madness.im] = false;
        }
        if (this.dominate[madness.im]) {
            final int n2 = (int)(((this.scz[0] - madness.scz[0] + this.scz[1] - madness.scz[1] + this.scz[2] - madness.scz[2] + this.scz[3] - madness.scz[3]) * (this.scz[0] - madness.scz[0] + this.scz[1] - madness.scz[1] + this.scz[2] - madness.scz[2] + this.scz[3] - madness.scz[3]) + (this.scx[0] - madness.scx[0] + this.scx[1] - madness.scx[1] + this.scx[2] - madness.scx[2] + this.scx[3] - madness.scx[3]) * (this.scx[0] - madness.scx[0] + this.scx[1] - madness.scx[1] + this.scx[2] - madness.scx[2] + this.scx[3] - madness.scx[3])) / 16.0f);
            int n3 = 0;
            do {
                int n4 = 0;
                do {
                    if (this.rpy(array[n3], array4[n4], array2[n3], array5[n4], array3[n3], array6[n4]) < (n2 + 7000) * (this.comprad[madness.cn] + this.comprad[this.cn])) {
                        if (Math.abs(this.scx[n3] * this.moment[this.cn]) > Math.abs(madness.scx[n4] * madness.moment[madness.cn])) {
                            float n5 = madness.scx[n4] * this.revpush[this.cn];
                            if (n5 > 300.0f) {
                                n5 = 300.0f;
                            }
                            if (n5 < -300.0f) {
                                n5 = -300.0f;
                            }
                            float n6 = this.scx[n3] * this.push[this.cn];
                            if (n6 > 300.0f) {
                                n6 = 300.0f;
                            }
                            if (n6 < -300.0f) {
                                n6 = -300.0f;
                            }
                            final float[] scx = madness.scx;
                            scx[n4] += n6;
                            if (this.im == 0) {
                                madness.colidim = true;
                            }
                            madness.regx(n4, n6 * this.moment[this.cn], geometry2);
                            if (madness.colidim) {
                                madness.colidim = false;
                            }
                            final float[] scx2 = this.scx;
                            scx2[n3] -= n5;
                            this.regx(n3, -n5 * madness.moment[this.cn], geometry);
                            final float[] scy = this.scy;
                            scy[n3] -= this.revlift[this.cn];
                            if (this.im == 0) {
                                madness.colidim = true;
                            }
                            madness.regy(n4, (float)(this.revlift[this.cn] * 7), geometry2);
                            if (madness.colidim) {
                                madness.colidim = false;
                            }
                        }
                        if (Math.abs(this.scz[n3] * this.moment[this.cn]) > Math.abs(madness.scz[n4] * madness.moment[madness.cn])) {
                            float n10 = madness.scz[n4] * this.revpush[this.cn];
                            if (n10 > 300.0f) {
                                n10 = 300.0f;
                            }
                            if (n10 < -300.0f) {
                                n10 = -300.0f;
                            }
                            float n11 = this.scz[n3] * this.push[this.cn];
                            if (n11 > 300.0f) {
                                n11 = 300.0f;
                            }
                            if (n11 < -300.0f) {
                                n11 = -300.0f;
                            }
                            final float[] scz = madness.scz;
                            scz[n4] += n11;
                            if (this.im == 0) {
                                madness.colidim = true;
                            }
                            madness.regz(n4, n11 * this.moment[this.cn], geometry2);
                            if (madness.colidim) {
                                madness.colidim = false;
                            }
                            final float[] scz2 = this.scz;
                            scz2[n3] -= n10;
                            this.regz(n3, -n10 * madness.moment[this.cn], geometry);
                            final float[] scy2 = this.scy;
                            scy2[n3] -= this.revlift[this.cn];
                            if (this.im == 0) {
                                madness.colidim = true;
                            }
                            madness.regy(n4, (float)(this.revlift[this.cn] * 7), geometry2);
                            if (madness.colidim) {
                                madness.colidim = false;
                            }
                        }
                        if (this.im == 0) {
                            madness.lastcolido = 70;
                        }
                        if (madness.im == 0) {
                            this.lastcolido = 70;
                        }
                        final float[] scy3 = madness.scy;
                        scy3[n4] -= this.lift[this.cn];
                    }
                } while (++n4 < 4);
            } while (++n3 < 4);
        }
    }

    public void distruct(final Geometry geometry) {
        for (int i = 0; i < geometry.numberOfPlanes; ++i) {
            if (geometry.planes[i].wz == 0) {
                geometry.planes[i].embos = 1;
            }
        }
    }

    public void reseto(final int cn, final Geometry geometry, final CheckPoints checkPoints) {
        this.cn = cn;
        int n = 0;
        do {
            this.dominate[n] = false;
            this.caught[n] = false;
        } while (++n < 5);
        if (this.cn == 7 && this.im == 0) {
            if (checkPoints.stage == 10) {
                this.moment[this.cn] = 1.7f;
            }
            else {
                this.moment[this.cn] = 2.0f;
            }
        }
        this.mxz = 0;
        this.cxz = 0;
        this.pzy = 0;
        this.pxy = 0;
        this.speed = 0.0f;
        int n2 = 0;
        do {
            this.scy[n2] = 0.0f;
            this.scx[n2] = 0.0f;
            this.scz[n2] = 0.0f;
        } while (++n2 < 4);
        this.forca = ((float)Math.sqrt(geometry.keyz[0] * geometry.keyz[0] + geometry.keyx[0] * geometry.keyx[0]) + (float)Math.sqrt(geometry.keyz[1] * geometry.keyz[1] + geometry.keyx[1] * geometry.keyx[1]) + (float)Math.sqrt(geometry.keyz[2] * geometry.keyz[2] + geometry.keyx[2] * geometry.keyx[2]) + (float)Math.sqrt(geometry.keyz[3] * geometry.keyz[3] + geometry.keyx[3] * geometry.keyx[3])) / 10000.0f * (float)(this.bounce[this.cn] - 0.3);
        this.mtouch = false;
        this.wtouch = false;
        this.txz = 0;
        this.fxz = 0;
        this.pmlt = 1;
        this.nmlt = 1;
        this.dcnt = 0;
        this.skid = 0;
        this.pushed = false;
        this.gtouch = false;
        this.pl = false;
        this.pr = false;
        this.pd = false;
        this.pu = false;
        this.loop = 0;
        this.ucomp = 0.0f;
        this.dcomp = 0.0f;
        this.lcomp = 0.0f;
        this.rcomp = 0.0f;
        this.lxz = 0;
        this.travxy = 0;
        this.travzy = 0;
        this.travxz = 0;
        this.rtab = false;
        this.ftab = false;
        this.btab = false;
        this.powerup = 0.0f;
        this.xtpower = 0;
        this.trcnt = 0;
        this.capcnt = 0;
        this.tilt = 0.0f;
        this.pan = 0;
        this.pcleared = checkPoints.pcs;
        this.clear = 0;
        this.nlaps = 0;
        this.focus = -1;
        this.missedCheckpoint = 0;
        this.nofocus = false;
        this.power = 98.0f;
        this.lastcolido = 0;
        checkPoints.dested[this.im] = 0;
        this.squash = 0;
        this.nbsq = 0;
        this.currentDamage = 0;
        this.cntdest = 0;
        this.dest = false;
        this.newcar = false;
    }

    public void regx(final int n, float a, final Geometry geometry) {
        a *= this.dammult[this.cn];
        if (Math.abs(a) > 100.0f) {
            this.rpd.recx(n, a, this.im);
            if (a > 100.0f) {
                a -= 100.0f;
            }
            if (a < -100.0f) {
                a += 100.0f;
            }
            if (this.im == 0 || this.colidim) {
                this.graphicsPanel.crash(a, 0);
            }
            for (int i = 0; i < geometry.numberOfPlanes; ++i) {
                float n2 = 0.0f;
                for (int j = 0; j < geometry.planes[i].n; ++j) {
                    if (geometry.planes[i].wz == 0 && this.py(geometry.keyx[n], geometry.planes[i].ox[j], geometry.keyz[n], geometry.planes[i].oz[j]) < this.clrad[this.cn]) {
                        n2 = a / 20.0f * this.medium.random();
                        final int[] oz = geometry.planes[i].oz;
                        oz[j] -= (int)(n2 * this.medium.sin(geometry.xz) * this.medium.cos(geometry.zy));
                        final int[] ox = geometry.planes[i].ox;
                        ox[j] += (int)(n2 * this.medium.cos(geometry.xz) * this.medium.cos(geometry.xy));
                        this.currentDamage += (int)Math.abs(n2);
                    }
                }
                if (n2 != 0.0f) {
                    if (Math.abs(n2) >= 1.0f) {
                        geometry.planes[i].chip = 1;
                        geometry.planes[i].ctmag = n2;
                    }
                    if (!geometry.planes[i].nocol && !geometry.planes[i].glass) {
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
                        plane.bfase += (int)Math.abs(n2);
                        new Color(geometry.planes[i].c[0], geometry.planes[i].c[1], geometry.planes[i].c[2]);
                        final Color hsbColor = Color.getHSBColor(geometry.planes[i].hsb[0], geometry.planes[i].hsb[1], geometry.planes[i].hsb[2]);
                        geometry.planes[i].c[0] = hsbColor.getRed();
                        geometry.planes[i].c[1] = hsbColor.getGreen();
                        geometry.planes[i].c[2] = hsbColor.getBlue();
                    }
                    if (geometry.planes[i].glass) {
                        final Plane plane2 = geometry.planes[i];
                        plane2.gr -= (int)Math.abs(n2 * 1.5);
                    }
                }
            }
        }
    }

    public void drive(final Control control, final Geometry geometry, final Trackers trackers, final CheckPoints checkPoints) {
        //System.out.println(this.loop);
        int n = 1;
        int n2 = 1;
        boolean zyinv = false;
        boolean b = false;
        boolean b2 = false;
        this.capsized = false;
        int i;
        i = Math.abs(this.pzy);
        while (i > 270) {
            i -= 360;
        }
        if (Math.abs(i) > 90) {
            zyinv = true;
        }
        int n3 = 0;
        int j;
        j = Math.abs(this.pxy);
        while (j > 270) {
            j -= 360;
        }
        if (Math.abs(j) > 90) {
            n3 = 1;
            n2 = -1;
        }
        int grat = geometry.grat;
        if (zyinv) {
            if (n3 != 0) {
                n3 = 0;
                b = true;
            }
            else {
                n3 = 1;
                this.capsized = true;
            }
            n = -1;
        }
        else if (n3 != 0) {
            this.capsized = true;
        }
        if (this.capsized) {
            grat = this.flipy[this.cn] + this.squash;
        }
        control.zyinv = zyinv;
        float n4 = 0.0f;
        float n5 = 0.0f;
        float n6 = 0.0f;
        if (this.mtouch) {
            this.loop = 0;
        }
        if (this.wtouch) {
            if (this.loop == 2 || this.loop == -1) {
                this.loop = -1;
                if (control.left) {
                    this.pl = true;
                }
                if (control.right) {
                    this.pr = true;
                }
                if (control.up) {
                    this.pu = true;
                }
                if (control.down) {
                    this.pd = true;
                }
            }
            this.ucomp = 0.0f;
            this.dcomp = 0.0f;
            this.lcomp = 0.0f;
            this.rcomp = 0.0f;
        }
        if (control.handb) {
            if (!this.pushed) {
                if (!this.wtouch) {
                    if (this.loop == 0) {
                        this.loop = 1;
                    }
                }
                else if (this.gtouch) {
                    this.pushed = true;
                }
            }
        }
        else {
            this.pushed = false;
        }
        if (this.loop == 1) {
            final float n7 = (this.scy[0] + this.scy[1] + this.scy[2] + this.scy[3]) / 4.0f;
            int n8 = 0;
            do {
                this.scy[n8] = n7;
            } while (++n8 < 4);
            this.loop = 2;
        }
        if (!this.dest) {
            if (this.loop == 2) {
                if (control.up) {
                    if (this.ucomp == 0.0f) {
                        this.ucomp = 10.0f + (this.scy[0] + 50.0f) / 20.0f;
                        if (this.ucomp < 5.0f) {
                            this.ucomp = 5.0f;
                        }
                        if (this.ucomp > 10.0f) {
                            this.ucomp = 10.0f;
                        }
                        this.ucomp *= this.airs[this.cn];
                    }
                    if (this.ucomp < 20.0f) {
                        this.ucomp += (float)(0.5 * this.airs[this.cn]);
                    }
                    n4 = -this.airc[this.cn] * this.medium.sin(geometry.xz) * n2;
                    n5 = this.airc[this.cn] * this.medium.cos(geometry.xz) * n2;
                }
                else if (this.ucomp != 0.0f && this.ucomp > -2.0f) {
                    this.ucomp -= (float)(0.5 * this.airs[this.cn]);
                }
                if (control.down) {
                    if (this.dcomp == 0.0f) {
                        this.dcomp = 10.0f + (this.scy[0] + 50.0f) / 20.0f;
                        if (this.dcomp < 5.0f) {
                            this.dcomp = 5.0f;
                        }
                        if (this.dcomp > 10.0f) {
                            this.dcomp = 10.0f;
                        }
                        this.dcomp *= this.airs[this.cn];
                    }
                    if (this.dcomp < 20.0f) {
                        this.dcomp += (float)(0.5 * this.airs[this.cn]);
                    }
                    n6 = (float)(-this.airc[this.cn]);
                }
                else if (this.dcomp != 0.0f && this.ucomp > -2.0f) {
                    this.dcomp -= (float)(0.5 * this.airs[this.cn]);
                }
                if (control.left) {
                    if (this.lcomp == 0.0f) {
                        this.lcomp = 5.0f;
                    }
                    if (this.lcomp < 20.0f) {
                        this.lcomp += 2.0f * this.airs[this.cn];
                    }
                    n4 = -this.airc[this.cn] * this.medium.cos(geometry.xz) * n;
                    n5 = -this.airc[this.cn] * this.medium.sin(geometry.xz) * n;
                }
                else if (this.lcomp > 0.0f) {
                    this.lcomp -= 2.0f * this.airs[this.cn];
                }
                if (control.right) {
                    if (this.rcomp == 0.0f) {
                        this.rcomp = 5.0f;
                    }
                    if (this.rcomp < 20.0f) {
                        this.rcomp += 2.0f * this.airs[this.cn];
                    }
                    n4 = this.airc[this.cn] * this.medium.cos(geometry.xz) * n;
                    n5 = this.airc[this.cn] * this.medium.sin(geometry.xz) * n;
                }
                else if (this.rcomp > 0.0f) {
                    this.rcomp -= 2.0f * this.airs[this.cn];
                }
                this.pzy += (int)((this.dcomp - this.ucomp) * this.medium.cos(this.pxy));
                if (zyinv) {
                    geometry.xz += (int)((this.dcomp - this.ucomp) * this.medium.sin(this.pxy));
                }
                else {
                    geometry.xz -= (int)((this.dcomp - this.ucomp) * this.medium.sin(this.pxy));
                }
                this.pxy += (int)(this.rcomp - this.lcomp);
            }
            else {
                float power = this.power;
                if (power < 40.0f) {
                    power = 40.0f;
                }
                if (this.im == 0 && this.power != 98.0f) {
                    if (checkPoints.stage != 6 && checkPoints.stage != 8) {
                        power *= (float)0.76;
                    }
                    else if (checkPoints.stage != 6) {
                        power *= (float)0.9;
                    }
                }
                if (control.down) {
                    if (this.speed > 0.0f) {
                        this.speed -= this.handb[this.cn] / 2;
                    }
                    else {
                        int n9 = 0;
                        int n10 = 0;
                        do {
                            if (this.speed <= -(this.swits[this.cn][n10] / 2 + power * this.swits[this.cn][n10] / 196.0f)) {
                                ++n9;
                            }
                        } while (++n10 < 2);
                        if (n9 != 2) {
                            this.speed -= this.acelf[this.cn][n9] / 2.0f + power * this.acelf[this.cn][n9] / 196.0f;
                        }
                        else {
                            this.speed = -(this.swits[this.cn][1] / 2 + power * this.swits[this.cn][1] / 196.0f);
                        }
                    }
                }
                if (control.up) {
                    if (this.speed < 0.0f) {
                        this.speed += this.handb[this.cn];
                    }
                    else {
                        int n11 = 0;
                        int n12 = 0;
                        do {
                            if (this.speed >= this.swits[this.cn][n12] / 2 + power * this.swits[this.cn][n12] / 196.0f) {
                                ++n11;
                            }
                        } while (++n12 < 3);
                        if (n11 != 3) {
                            this.speed += this.acelf[this.cn][n11] / 2.0f + power * this.acelf[this.cn][n11] / 196.0f;
                        }
                        else {
                            this.speed = this.swits[this.cn][2] / 2 + power * this.swits[this.cn][2] / 196.0f;
                        }
                    }
                }
                if (control.handb && Math.abs(this.speed) > this.handb[this.cn]) {
                    if (this.speed < 0.0f) {
                        this.speed += this.handb[this.cn];
                    }
                    else {
                        this.speed -= this.handb[this.cn];
                    }
                }
                if (this.loop == -1 && geometry.y < 100) {
                    if (control.left) {
                        if (!this.pl) {
                            if (this.lcomp == 0.0f) {
                                this.lcomp = 5.0f * this.airs[this.cn];
                            }
                            if (this.lcomp < 20.0f) {
                                this.lcomp += 2.0f * this.airs[this.cn];
                            }
                        }
                    }
                    else {
                        if (this.lcomp > 0.0f) {
                            this.lcomp -= 2.0f * this.airs[this.cn];
                        }
                        this.pl = false;
                    }
                    if (control.right) {
                        if (!this.pr) {
                            if (this.rcomp == 0.0f) {
                                this.rcomp = 5.0f * this.airs[this.cn];
                            }
                            if (this.rcomp < 20.0f) {
                                this.rcomp += 2.0f * this.airs[this.cn];
                            }
                        }
                    }
                    else {
                        if (this.rcomp > 0.0f) {
                            this.rcomp -= 2.0f * this.airs[this.cn];
                        }
                        this.pr = false;
                    }
                    if (control.up) {
                        if (!this.pu) {
                            if (this.ucomp == 0.0f) {
                                this.ucomp = 5.0f * this.airs[this.cn];
                            }
                            if (this.ucomp < 20.0f) {
                                this.ucomp += 2.0f * this.airs[this.cn];
                            }
                        }
                    }
                    else {
                        if (this.ucomp > 0.0f) {
                            this.ucomp -= 2.0f * this.airs[this.cn];
                        }
                        this.pu = false;
                    }
                    if (control.down) {
                        if (!this.pd) {
                            if (this.dcomp == 0.0f) {
                                this.dcomp = 5.0f * this.airs[this.cn];
                            }
                            if (this.dcomp < 20.0f) {
                                this.dcomp += 2.0f * this.airs[this.cn];
                            }
                        }
                    }
                    else {
                        if (this.dcomp > 0.0f) {
                            this.dcomp -= 2.0f * this.airs[this.cn];
                        }
                        this.pd = false;
                    }
                    this.pzy += (int)((this.dcomp - this.ucomp) * this.medium.cos(this.pxy));
                    if (zyinv) {
                        geometry.xz += (int)((this.dcomp - this.ucomp) * this.medium.sin(this.pxy));
                    }
                    else {
                        geometry.xz -= (int)((this.dcomp - this.ucomp) * this.medium.sin(this.pxy));
                    }
                    this.pxy += (int)(this.rcomp - this.lcomp);
                }
            }
        }
        float n13 = 20.0f * this.speed / (154.0f * this.simag[this.cn]);
        if (n13 > 20.0f) {
            n13 = 20.0f;
        }
        geometry.wzy -= (int)n13;
        if (geometry.wzy < -45) {
            geometry.wzy += 45;
        }
        if (geometry.wzy > 45) {
            geometry.wzy -= 45;
        }
        if (control.right) {
            geometry.wxz -= this.turn[this.cn];
            if (geometry.wxz < -36) {
                geometry.wxz = -36;
            }
        }
        if (control.left) {
            geometry.wxz += this.turn[this.cn];
            if (geometry.wxz > 36) {
                geometry.wxz = 36;
            }
        }
        if (geometry.wxz != 0 && !control.left && !control.right) {
            if (Math.abs(this.speed) < 10.0f) {
                if (Math.abs(geometry.wxz) == 1) {
                    geometry.wxz = 0;
                }
                if (geometry.wxz > 0) {
                    --geometry.wxz;
                }
                if (geometry.wxz < 0) {
                    ++geometry.wxz;
                }
            }
            else {
                if (Math.abs(geometry.wxz) < this.turn[this.cn] * 2) {
                    geometry.wxz = 0;
                }
                if (geometry.wxz > 0) {
                    geometry.wxz -= this.turn[this.cn] * 2;
                }
                if (geometry.wxz < 0) {
                    geometry.wxz += this.turn[this.cn] * 2;
                }
            }
        }
        int n14 = (int)(3600.0f / (this.speed * this.speed));
        if (n14 < 5) {
            n14 = 5;
        }
        if (this.speed < 0.0f) {
            n14 = -n14;
        }
        if (this.wtouch) {
            if (!this.capsized) {
                if (!control.handb) {
                    this.fxz = geometry.wxz / (n14 * 3);
                }
                else {
                    this.fxz = geometry.wxz / n14;
                }
                geometry.xz += geometry.wxz / n14;
            }
            this.wtouch = false;
            this.gtouch = false;
        }
        else {
            geometry.xz += this.fxz;
        }
        if (this.speed > 30.0f || this.speed < -100.0f) {
            while (Math.abs(this.mxz - this.cxz) > 180) {
                if (this.cxz > this.mxz) {
                    this.cxz -= 360;
                }
                else {
                    if (this.cxz >= this.mxz) {
                        continue;
                    }
                    this.cxz += 360;
                }
            }
            if (Math.abs(this.mxz - this.cxz) < 30) {
                this.cxz += ((this.mxz - this.cxz) / 4.0f);
            }
            else {
                if (this.cxz > this.mxz) {
                    this.cxz -= 10;
                }
                if (this.cxz < this.mxz) {
                    this.cxz += 10;
                }
            }
        }
        final float[] array = new float[4];
        final float[] array2 = new float[4];
        final float[] array3 = new float[4];
        int n15 = 0;
        do {
            array[n15] = (float)(geometry.keyx[n15] + geometry.x);
            array3[n15] = (float)(grat + geometry.y);
            array2[n15] = (float)(geometry.z + geometry.keyz[n15]);
            this.scy[n15] += 7.0f;
        } while (++n15 < 4);
        this.rot(array, array3, geometry.x, geometry.y, this.pxy, 4);
        this.rot(array3, array2, geometry.y, geometry.z, this.pzy, 4);
        this.rot(array, array2, geometry.x, geometry.z, geometry.xz, 4);
        boolean b3 = false;
        final int n17 = (int)((this.scx[0] + this.scx[1] + this.scx[2] + this.scx[3]) / 4.0f);
        final int n18 = (int)((this.scz[0] + this.scz[1] + this.scz[2] + this.scz[3]) / 4.0f);
        int n19 = 0;
        do {
            if (this.scx[n19] - n17 > 200.0f) {
                this.scx[n19] = (float)(200 + n17);
            }
            if (this.scx[n19] - n17 < -200.0f) {
                this.scx[n19] = (float)(n17 - 200);
            }
            if (this.scz[n19] - n18 > 200.0f) {
                this.scz[n19] = (float)(200 + n18);
            }
            if (this.scz[n19] - n18 < -200.0f) {
                this.scz[n19] = (float)(n18 - 200);
            }
        } while (++n19 < 4);
        int n20 = 0;
        do {
            array3[n20] += this.scy[n20];
            array[n20] += (this.scx[0] + this.scx[1] + this.scx[2] + this.scx[3]) / 4.0f;
            array2[n20] += (this.scz[0] + this.scz[1] + this.scz[2] + this.scz[3]) / 4.0f;
        } while (++n20 < 4);
        int n24 = 1;
        for (int k = 0; k < trackers.nt; ++k) {
            if (Math.abs(trackers.zy[k]) != 90 && Math.abs(trackers.xy[k]) != 90 && Math.abs(geometry.x - trackers.x[k]) < trackers.radx[k] && Math.abs(geometry.z - trackers.z[k]) < trackers.radz[k]) {
                n24 = trackers.skd[k];
            }
        }
        if (this.mtouch) {
            float n25 = this.grip[this.cn] - Math.abs(this.txz - geometry.xz) * this.speed / 250.0f;
            if (control.handb) {
                n25 -= Math.abs(this.txz - geometry.xz) * 4;
            }
            if (n25 < this.grip[this.cn]) {
                if (this.skid != 2) {
                    this.skid = 1;
                }
                this.speed -= this.speed / 100.0f;
            }
            else if (this.skid == 1) {
                this.skid = 2;
            }
            if (n24 == 1) {
                n25 *= 0.75;
            }
            if (n24 == 2) {
                n25 *= (float)0.55;
            }
            int n26 = -(int)(this.speed * this.medium.sin(geometry.xz) * this.medium.cos(this.pzy));
            int n27 = (int)(this.speed * this.medium.cos(geometry.xz) * this.medium.cos(this.pzy));
            int n28 = -(int)(this.speed * this.medium.sin(this.pzy));
            if (this.capsized || this.dest || checkPoints.haltall) {
                n26 = 0;
                n27 = 0;
                n28 = 0;
                n25 = this.grip[this.cn] / 5.0f;
                if (this.speed > 0.0f) {
                    this.speed -= 2.0f;
                }
                else {
                    this.speed += 2.0f;
                }
            }
            if (Math.abs(this.speed) > this.drag[this.cn]) {
                if (this.speed > 0.0f) {
                    this.speed -= this.drag[this.cn];
                }
                else {
                    this.speed += this.drag[this.cn];
                }
            }
            else {
                this.speed = 0.0f;
            }
            if (n25 < 1.0f) {
                n25 = 1.0f;
            }
            float n29 = 0.0f;
            float n30 = 0.0f;
            int n31 = 0;
            do {
                if (Math.abs(this.scx[n31] - n26) > n25) {
                    if (this.scx[n31] < n26) {
                        this.scx[n31] += n25;
                    }
                    else {
                        this.scx[n31] -= n25;
                    }
                }
                else {
                    this.scx[n31] = (float)n26;
                }
                if (Math.abs(this.scz[n31] - n27) > n25) {
                    if (this.scz[n31] < n27) {
                        this.scz[n31] += n25;
                    }
                    else {
                        this.scz[n31] -= n25;
                    }
                }
                else {
                    this.scz[n31] = (float)n27;
                }
                if (Math.abs(this.scy[n31] - n28) > n25) {
                    if (this.scy[n31] < n28) {
                        final float[] scy2 = this.scy;
                        final int n36 = n31;
                        scy2[n36] += n25;
                    }
                    else {
                        final float[] scy3 = this.scy;
                        final int n37 = n31;
                        scy3[n37] -= n25;
                    }
                }
                else {
                    this.scy[n31] = (float)n28;
                }
                if (n25 < this.grip[this.cn]) {
                    if (this.txz != geometry.xz) {
                        ++this.dcnt;
                    }
                    else if (this.dcnt != 0) {
                        this.dcnt = 0;
                    }
                    if (this.dcnt > 40.0f * n25 / this.grip[this.cn] || this.capsized) {
                        float n38 = 1.0f;
                        if (n24 != 0) {
                            n38 = 1.2f;
                        }
                        if (this.medium.random() > 0.75) {
                            geometry.dust(n31, array[n31], array3[n31], array2[n31], this.scx[n31], this.scz[n31], n38 * this.simag[this.cn], true, (int)this.tilt);
                            if (this.im == 0 && !this.capsized) {
                                this.graphicsPanel.skid(n24, (float)Math.sqrt(this.scx[n31] * this.scx[n31] + this.scz[n31] * this.scz[n31]));
                            }
                        }
                    }
                    else {
                        if (n24 == 1 && this.medium.random() > 0.85) {
                            geometry.dust(n31, array[n31], array3[n31], array2[n31], this.scx[n31], this.scz[n31], 1.1f * this.simag[this.cn], false, (int)this.tilt);
                        }
                        if ((n24 == 2 || n24 == 3) && this.medium.random() > 0.7) {
                            geometry.dust(n31, array[n31], array3[n31], array2[n31], this.scx[n31], this.scz[n31], 1.15f * this.simag[this.cn], false, (int)this.tilt);
                        }
                    }
                }
                else if (this.dcnt != 0) {
                    this.dcnt -= 2;
                    if (this.dcnt < 0) {
                        this.dcnt = 0;
                    }
                }
                if (n24 == 3) {
                    this.scy[(int)(this.medium.random() * 4.0f)] = (float)(-100.0f * this.medium.random() * (this.speed / this.swits[this.cn][2]) * (this.bounce[this.cn] - 0.3));
                }
                n29 += this.scx[n31];
                n30 += this.scz[n31];
            } while (++n31 < 4);
            this.txz = geometry.xz;
            int n39;
            if (n29 > 0.0f) {
                n39 = -1;
            }
            else {
                n39 = 1;
            }
            this.mxz = (float)(Math.acos(n30 / Math.sqrt(n29 * n29 + n30 * n30)) / 0.017453292519943295 * n39);
            if (this.skid == 2) {
                if (!this.capsized) {
                    n29 /= 4.0f;
                    n30 /= 4.0f;
                    if (b) {
                        this.speed = -((float)Math.sqrt(n29 * n29 + n30 * n30) * this.medium.cos(this.mxz - geometry.xz));
                    }
                    else {
                        this.speed = (float)Math.sqrt(n29 * n29 + n30 * n30) * this.medium.cos(this.mxz - geometry.xz);
                    }
                }
                this.skid = 0;
            }
            if (this.capsized && n29 == 0.0f && n30 == 0.0f) {
                n24 = 0;
            }
            this.mtouch = false;
            b3 = true;
        }
        else if (this.skid != 2) {
            this.skid = 2;
        }
        int n40 = 0;
        final boolean[] array7 = new boolean[4];
        int n41 = 0;
        do {
            if (array3[n41] > 245.0f) {
                ++n40;
                this.wtouch = true;
                this.gtouch = true;
                if (!b3 && this.scy[n41] != 7.0f) {
                    float n42 = this.scy[n41] / 333.33f;
                    if (n42 > 0.3) {
                        n42 = 0.3f;
                    }
                    float n43;
                    if (n24 == 0) {
                        n43 = (float)(n42 + 1.1);
                    }
                    else {
                        n43 = (float)(n42 + 1.2);
                    }
                    geometry.dust(n41, array[n41], array3[n41], array2[n41], this.scx[n41], this.scz[n41], n43 * this.simag[this.cn], true, 0);
                }
                array3[n41] = 250.0f;
                int n44 = 0;
                do {
                    if (n41 != n44 && array3[n44] <= 245.0f) {
                        array3[n44] -= array3[n41] - 250.0f;
                    }
                } while (++n44 < 4);
                float n46 = (Math.abs(this.medium.sin(this.pxy)) + Math.abs(this.medium.sin(this.pzy))) / 3.0f;
                if (n46 > 0.4) {
                    n46 = 0.4f;
                }
                float n47 = n46 + this.bounce[this.cn];
                if (n47 < 1.1) {
                    n47 = 1.1f;
                }
                this.regy(n41, Math.abs(this.scy[n41] * n47), geometry);
                if (this.scy[n41] > 0.0f) {
                    this.scy[n41] -= Math.abs(this.scy[n41] * n47);
                }
            }
            array7[n41] = false;
        } while (++n41 < 4);
        int n49 = 0;
        for (int l = 0; l < trackers.nt; ++l) {
            int n50 = 0;
            int n51 = 0;
            int n52 = 0;
            do {
                if (!array7[n52] && array[n52] > trackers.x[l] - trackers.radx[l] && array[n52] < trackers.x[l] + trackers.radx[l] && array2[n52] > trackers.z[l] - trackers.radz[l] && array2[n52] < trackers.z[l] + trackers.radz[l] && array3[n52] > trackers.y[l] - trackers.rady[l] && array3[n52] < trackers.y[l] + trackers.rady[l]) {
                    if (trackers.xy[l] == 0 && trackers.zy[l] == 0 && trackers.y[l] != 250 && array3[n52] > trackers.y[l] - 5) {
                        ++n51;
                        this.wtouch = true;
                        this.gtouch = true;
                        if (!b3 && this.scy[n52] != 7.0f) {
                            float n53 = this.scy[n52] / 333.33f;
                            if (n53 > 0.3) {
                                n53 = 0.3f;
                            }
                            float n54;
                            if (n24 == 0) {
                                n54 = (float)(n53 + 1.1);
                            }
                            else {
                                n54 = (float)(n53 + 1.2);
                            }
                            geometry.dust(n52, array[n52], array3[n52], array2[n52], this.scx[n52], this.scz[n52], n54 * this.simag[this.cn], true, 0);
                        }
                        array3[n52] = (float)trackers.y[l];
                        int n55 = 0;
                        do {
                            if (n52 != n55 && array3[n55] <= trackers.y[l] - 5) {
                                array3[n55] -= array3[n52] - trackers.y[l];
                            }
                        } while (++n55 < 4);
                        float n57 = (Math.abs(this.medium.sin(this.pxy)) + Math.abs(this.medium.sin(this.pzy))) / 3.0f;
                        if (n57 > 0.4) {
                            n57 = 0.4f;
                        }
                        float n58 = n57 + this.bounce[this.cn];
                        if (n58 < 1.1) {
                            n58 = 1.1f;
                        }
                        this.regy(n52, Math.abs(this.scy[n52] * n58), geometry);
                        if (this.scy[n52] > 0.0f) {
                            this.scy[n52] -= Math.abs(this.scy[n52] * n58);
                        }
                        array7[n52] = true;
                    }
                    if (trackers.zy[l] == -90 && array2[n52] < trackers.z[l] + trackers.radz[l] && this.scz[n52] < 0.0f) {
                        array2[n52] = (float)(trackers.z[l] + trackers.radz[l]);
                        int n60 = 0;
                        do {
                            if (n52 != n60 && array2[n60] >= trackers.z[l] + trackers.radz[l]) {
                                array2[n60] -= array2[n52] - (trackers.z[l] + trackers.radz[l]);
                            }
                        } while (++n60 < 4);
                        float n62 = (Math.abs(this.medium.cos(this.pxy)) + Math.abs(this.medium.cos(this.pzy))) / 4.0f;
                        if (n62 > 0.3) {
                            n62 = 0.3f;
                        }
                        if (b3) {
                            n62 = 0.0f;
                        }
                        float n63 = (float)(n62 + (this.bounce[this.cn] - 0.2));
                        if (n63 < 1.1) {
                            n63 = 1.1f;
                        }
                        this.regz(n52, Math.abs(this.scz[n52] * n63 * trackers.dam[l]), geometry);
                        this.scz[n52] += Math.abs(this.scz[n52] * n63);
                        this.skid = 2;
                        b2 = true;
                        array7[n52] = true;
                        control.wall = l;
                    }
                    if (trackers.zy[l] == 90 && array2[n52] > trackers.z[l] - trackers.radz[l] && this.scz[n52] > 0.0f) {
                        array2[n52] = (float)(trackers.z[l] - trackers.radz[l]);
                        int n65 = 0;
                        do {
                            if (n52 != n65 && array2[n65] <= trackers.z[l] - trackers.radz[l]) {
                                array2[n65] -= array2[n52] - (trackers.z[l] - trackers.radz[l]);
                            }
                        } while (++n65 < 4);
                        float n67 = (Math.abs(this.medium.cos(this.pxy)) + Math.abs(this.medium.cos(this.pzy))) / 4.0f;
                        if (n67 > 0.3) {
                            n67 = 0.3f;
                        }
                        if (b3) {
                            n67 = 0.0f;
                        }
                        float n68 = (float)(n67 + (this.bounce[this.cn] - 0.2));
                        if (n68 < 1.1) {
                            n68 = 1.1f;
                        }
                        this.regz(n52, -Math.abs(this.scz[n52] * n68 * trackers.dam[l]), geometry);
                        this.scz[n52] -= Math.abs(this.scz[n52] * n68);
                        this.skid = 2;
                        b2 = true;
                        array7[n52] = true;
                        control.wall = l;
                    }
                    if (trackers.xy[l] == -90 && array[n52] < trackers.x[l] + trackers.radx[l] && this.scx[n52] < 0.0f) {
                        array[n52] = (float)(trackers.x[l] + trackers.radx[l]);
                        int n70 = 0;
                        do {
                            if (n52 != n70 && array[n70] >= trackers.x[l] + trackers.radx[l]) {
                                array[n70] -= array[n52] - (trackers.x[l] + trackers.radx[l]);
                            }
                        } while (++n70 < 4);
                        float n72 = (Math.abs(this.medium.cos(this.pxy)) + Math.abs(this.medium.cos(this.pzy))) / 4.0f;
                        if (n72 > 0.3) {
                            n72 = 0.3f;
                        }
                        if (b3) {
                            n72 = 0.0f;
                        }
                        float n73 = (float)(n72 + (this.bounce[this.cn] - 0.2));
                        if (n73 < 1.1) {
                            n73 = 1.1f;
                        }
                        this.regx(n52, Math.abs(this.scx[n52] * n73 * trackers.dam[l]), geometry);
                        this.scx[n52] += Math.abs(this.scx[n52] * n73);
                        this.skid = 2;
                        b2 = true;
                        array7[n52] = true;
                        control.wall = l;
                    }
                    if (trackers.xy[l] == 90 && array[n52] > trackers.x[l] - trackers.radx[l] && this.scx[n52] > 0.0f) {
                        array[n52] = (float)(trackers.x[l] - trackers.radx[l]);
                        int n75 = 0;
                        do {
                            if (n52 != n75 && array[n75] <= trackers.x[l] - trackers.radx[l]) {
                                array[n75] -= array[n52] - (trackers.x[l] - trackers.radx[l]);
                            }
                        } while (++n75 < 4);
                        float n77 = (Math.abs(this.medium.cos(this.pxy)) + Math.abs(this.medium.cos(this.pzy))) / 4.0f;
                        if (n77 > 0.3) {
                            n77 = 0.3f;
                        }
                        if (b3) {
                            n77 = 0.0f;
                        }
                        float n78 = (float)(n77 + (this.bounce[this.cn] - 0.2));
                        if (n78 < 1.1) {
                            n78 = 1.1f;
                        }
                        this.regx(n52, -Math.abs(this.scx[n52] * n78 * trackers.dam[l]), geometry);
                        final float[] scx4 = this.scx;
                        scx4[n52] -= Math.abs(this.scx[n52] * n78);
                        this.skid = 2;
                        b2 = true;
                        array7[n52] = true;
                        control.wall = l;
                    }
                    if (trackers.zy[l] != 0 && trackers.zy[l] != 90 && trackers.zy[l] != -90) {
                        final int n80 = 90 + trackers.zy[l];
                        float n81 = 1.0f + (50 - Math.abs(trackers.zy[l])) / 30.0f;
                        if (n81 < 1.0f) {
                            n81 = 1.0f;
                        }
                        final float n82 = trackers.y[l] + ((array3[n52] - trackers.y[l]) * this.medium.cos(n80) - (array2[n52] - trackers.z[l]) * this.medium.sin(n80));
                        float n83 = trackers.z[l] + ((array3[n52] - trackers.y[l]) * this.medium.sin(n80) + (array2[n52] - trackers.z[l]) * this.medium.cos(n80));
                        if (n83 > trackers.z[l] && n83 < trackers.z[l] + 200) {
                            final float[] scy6 = this.scy;
                            final int n84 = n52;
                            scy6[n84] -= (n83 - trackers.z[l]) / n81;
                            n83 = (float)trackers.z[l];
                        }
                        if (n83 > trackers.z[l] - 30) {
                            if (trackers.skd[l] == 2) {
                                ++n50;
                            }
                            else {
                                ++n49;
                            }
                            this.wtouch = true;
                            this.gtouch = false;
                            if (!b3 && n24 != 0) {
                                geometry.dust(n52, array[n52], array3[n52], array2[n52], this.scx[n52], this.scz[n52], 1.4f * this.simag[this.cn], true, 0);
                            }
                        }
                        array3[n52] = trackers.y[l] + ((n82 - trackers.y[l]) * this.medium.cos(-n80) - (n83 - trackers.z[l]) * this.medium.sin(-n80));
                        array2[n52] = trackers.z[l] + ((n82 - trackers.y[l]) * this.medium.sin(-n80) + (n83 - trackers.z[l]) * this.medium.cos(-n80));
                        array7[n52] = true;
                    }
                    if (trackers.xy[l] == 0 || trackers.xy[l] == 90 || trackers.xy[l] == -90) {
                        continue;
                    }
                    final int n85 = 90 + trackers.xy[l];
                    float n86 = 1.0f + (50 - Math.abs(trackers.xy[l])) / 30.0f;
                    if (n86 < 1.0f) {
                        n86 = 1.0f;
                    }
                    final float n87 = trackers.y[l] + ((array3[n52] - trackers.y[l]) * this.medium.cos(n85) - (array[n52] - trackers.x[l]) * this.medium.sin(n85));
                    float n88 = trackers.x[l] + ((array3[n52] - trackers.y[l]) * this.medium.sin(n85) + (array[n52] - trackers.x[l]) * this.medium.cos(n85));
                    if (n88 > trackers.x[l] && n88 < trackers.x[l] + 200) {
                        final float[] scy7 = this.scy;
                        final int n89 = n52;
                        scy7[n89] -= (n88 - trackers.x[l]) / n86;
                        n88 = (float)trackers.x[l];
                    }
                    if (n88 > trackers.x[l] - 30) {
                        if (trackers.skd[l] == 2) {
                            ++n50;
                        }
                        else {
                            ++n49;
                        }
                        this.wtouch = true;
                        this.gtouch = false;
                        if (!b3 && n24 != 0) {
                            geometry.dust(n52, array[n52], array3[n52], array2[n52], this.scx[n52], this.scz[n52], 1.4f * this.simag[this.cn], true, 0);
                        }
                    }
                    array3[n52] = trackers.y[l] + ((n87 - trackers.y[l]) * this.medium.cos(-n85) - (n88 - trackers.x[l]) * this.medium.sin(-n85));
                    array[n52] = trackers.x[l] + ((n87 - trackers.y[l]) * this.medium.sin(-n85) + (n88 - trackers.x[l]) * this.medium.cos(-n85));
                    array7[n52] = true;
                }
            } while (++n52 < 4);
            if (n50 == 4) {
                this.mtouch = true;
            }
            if (n51 == 4) {
                n40 = 4;
            }
        }
        if (n49 == 4) {
            this.mtouch = true;
        }
        int a = 0;
        int a2 = 0;
        int a3 = 0;
        int a4 = 0;
        if (this.scy[2] != this.scy[0]) {
            int n90;
            if (this.scy[2] < this.scy[0]) {
                n90 = -1;
            }
            else {
                n90 = 1;
            }
            final double a5 = Math.sqrt((array2[0] - array2[2]) * (array2[0] - array2[2]) + (array3[0] - array3[2]) * (array3[0] - array3[2]) + (array[0] - array[2]) * (array[0] - array[2])) / (Math.abs(geometry.keyz[0]) + Math.abs(geometry.keyz[2]));
            if (a5 >= 0.9998) {
                a = n90;
            }
            else {
                a = (int)(Math.acos(a5) / 0.017453292519943295 * n90);
            }
        }
        if (this.scy[3] != this.scy[1]) {
            int n91;
            if (this.scy[3] < this.scy[1]) {
                n91 = -1;
            }
            else {
                n91 = 1;
            }
            final double a6 = Math.sqrt((array2[1] - array2[3]) * (array2[1] - array2[3]) + (array3[1] - array3[3]) * (array3[1] - array3[3]) + (array[1] - array[3]) * (array[1] - array[3])) / (Math.abs(geometry.keyz[1]) + Math.abs(geometry.keyz[3]));
            if (a6 >= 0.9998) {
                a2 = n91;
            }
            else {
                a2 = (int)(Math.acos(a6) / 0.017453292519943295 * n91);
            }
        }
        if (this.scy[1] != this.scy[0]) {
            int n92;
            if (this.scy[1] < this.scy[0]) {
                n92 = -1;
            }
            else {
                n92 = 1;
            }
            final double a7 = Math.sqrt((array2[0] - array2[1]) * (array2[0] - array2[1]) + (array3[0] - array3[1]) * (array3[0] - array3[1]) + (array[0] - array[1]) * (array[0] - array[1])) / (Math.abs(geometry.keyx[0]) + Math.abs(geometry.keyx[1]));
            if (a7 >= 0.9998) {
                a3 = n92;
            }
            else {
                a3 = (int)(Math.acos(a7) / 0.017453292519943295 * n92);
            }
        }
        if (this.scy[3] != this.scy[2]) {
            int n93;
            if (this.scy[3] < this.scy[2]) {
                n93 = -1;
            }
            else {
                n93 = 1;
            }
            final double a8 = Math.sqrt((array2[2] - array2[3]) * (array2[2] - array2[3]) + (array3[2] - array3[3]) * (array3[2] - array3[3]) + (array[2] - array[3]) * (array[2] - array[3])) / (Math.abs(geometry.keyx[2]) + Math.abs(geometry.keyx[3]));
            if (a8 >= 0.9998) {
                a4 = n93;
            }
            else {
                a4 = (int)(Math.acos(a8) / 0.017453292519943295 * n93);
            }
        }
        if (b2) {
            int abs;
            abs = Math.abs(geometry.xz + 45);
            while (abs > 180) {
                abs -= 360;
            }
            if (Math.abs(abs) > 90) {
                this.pmlt = 1;
            }
            else {
                this.pmlt = -1;
            }
            int abs2;
            abs2 = Math.abs(geometry.xz - 45);
            while (abs2 > 180) {
                abs2 -= 360;
            }
            if (Math.abs(abs2) > 90) {
                this.nmlt = 1;
            }
            else {
                this.nmlt = -1;
            }
        }
        geometry.xz += (int)(this.forca * (this.scz[0] * this.nmlt - this.scz[1] * this.pmlt + this.scz[2] * this.pmlt - this.scz[3] * this.nmlt + this.scx[0] * this.pmlt + this.scx[1] * this.nmlt - this.scx[2] * this.nmlt - this.scx[3] * this.pmlt));
        if (Math.abs(a2) > Math.abs(a)) {
            a = a2;
        }
        if (Math.abs(a4) > Math.abs(a3)) {
            a3 = a4;
        }
        if (!zyinv) {
            this.pzy += a;
        }
        else {
            this.pzy -= a;
        }
        if (n3 == 0) {
            this.pxy += a3;
        }
        else {
            this.pxy -= a3;
        }
        if (n40 == 4) {
            int n94 = 0;
            while (this.pzy < 360) {
                this.pzy += 360;
                geometry.zy += 360;
            }
            while (this.pzy > 360) {
                this.pzy -= 360;
                geometry.zy -= 360;
            }
            if (this.pzy < 190 && this.pzy > 170) {
                this.pzy = 180;
                geometry.zy = 180;
                ++n94;
            }
            if (this.pzy > 350 || this.pzy < 10) {
                this.pzy = 0;
                geometry.zy = 0;
                ++n94;
            }
            while (this.pxy < 360) {
                this.pxy += 360;
                geometry.xy += 360;
            }
            while (this.pxy > 360) {
                this.pxy -= 360;
                geometry.xy -= 360;
            }
            if (this.pxy < 190 && this.pxy > 170) {
                this.pxy = 180;
                geometry.xy = 180;
                ++n94;
            }
            if (this.pxy > 350 || this.pxy < 10) {
                this.pxy = 0;
                geometry.xy = 0;
                ++n94;
            }
            if (n94 == 2) {
                this.mtouch = true;
            }
        }
        if (!this.mtouch && this.wtouch) {
            if (this.cntouch == 10) {
                this.mtouch = true;
            }
            else {
                ++this.cntouch;
            }
        }
        else {
            this.cntouch = 0;
        }
        geometry.y = (int)((array3[0] + array3[1] + array3[2] + array3[3]) / 4.0f - grat * this.medium.cos(this.pzy) * this.medium.cos(this.pxy) + n6);
        int n95;
        if (zyinv) {
            n95 = -1;
        }
        else {
            n95 = 1;
        }
        geometry.x = (int)((array[0] - geometry.keyx[0] * this.medium.cos(geometry.xz) + n95 * geometry.keyz[0] * this.medium.sin(geometry.xz) + array[1] - geometry.keyx[1] * this.medium.cos(geometry.xz) + n95 * geometry.keyz[1] * this.medium.sin(geometry.xz) + array[2] - geometry.keyx[2] * this.medium.cos(geometry.xz) + n95 * geometry.keyz[2] * this.medium.sin(geometry.xz) + array[3] - geometry.keyx[3] * this.medium.cos(geometry.xz) + n95 * geometry.keyz[3] * this.medium.sin(geometry.xz)) / 4.0f + grat * this.medium.sin(this.pxy) * this.medium.cos(geometry.xz) - grat * this.medium.sin(this.pzy) * this.medium.sin(geometry.xz) + n4);
        geometry.z = (int)((array2[0] - n95 * geometry.keyz[0] * this.medium.cos(geometry.xz) - geometry.keyx[0] * this.medium.sin(geometry.xz) + array2[1] - n95 * geometry.keyz[1] * this.medium.cos(geometry.xz) - geometry.keyx[1] * this.medium.sin(geometry.xz) + array2[2] - n95 * geometry.keyz[2] * this.medium.cos(geometry.xz) - geometry.keyx[2] * this.medium.sin(geometry.xz) + array2[3] - n95 * geometry.keyz[3] * this.medium.cos(geometry.xz) - geometry.keyx[3] * this.medium.sin(geometry.xz)) / 4.0f + grat * this.medium.sin(this.pxy) * this.medium.sin(geometry.xz) - grat * this.medium.sin(this.pzy) * this.medium.cos(geometry.xz) + n5);
        if (Math.abs(this.speed) > 10.0f || !this.mtouch) {
            if (Math.abs(this.pxy - geometry.xy) >= 4) {
                if (this.pxy > geometry.xy) {
                    geometry.xy += 2 + (this.pxy - geometry.xy) / 2;
                }
                else {
                    geometry.xy -= 2 + (geometry.xy - this.pxy) / 2;
                }
            }
            else {
                geometry.xy = this.pxy;
            }
            if (Math.abs(this.pzy - geometry.zy) >= 4) {
                if (this.pzy > geometry.zy) {
                    geometry.zy += 2 + (this.pzy - geometry.zy) / 2;
                }
                else {
                    geometry.zy -= 2 + (geometry.zy - this.pzy) / 2;
                }
            }
            else {
                geometry.zy = this.pzy;
            }
        }
        if (this.wtouch && !this.capsized) {
            final float n96 = (float)(this.speed / this.swits[this.cn][2] * 14.0f * (this.bounce[this.cn] - 0.4));
            if (control.left && this.tilt < n96 && this.tilt >= 0.0f) {
                this.tilt += (float)0.4;
            }
            else if (control.right && this.tilt > -n96 && this.tilt <= 0.0f) {
                this.tilt -= (float)0.4;
            }
            else if (Math.abs(this.tilt) > 3.0 * (this.bounce[this.cn] - 0.4)) {
                if (this.tilt > 0.0f) {
                    this.tilt -= (float)(3.0 * (this.bounce[this.cn] - 0.3));
                }
                else {
                    this.tilt += (float)(3.0 * (this.bounce[this.cn] - 0.3));
                }
            }
            else {
                this.tilt = 0.0f;
            }
            geometry.xy += (int)this.tilt;
            if (this.gtouch) {
                geometry.y -= (int)(this.tilt / 1.5);
            }
        }
        else if (this.tilt != 0.0f) {
            this.tilt = 0.0f;
        }
        if (this.wtouch && n24 == 2) {
            geometry.zy += (int)((this.medium.random() * 6.0f * this.speed / this.swits[this.cn][2] - 3.0f * this.speed / this.swits[this.cn][2]) * (this.bounce[this.cn] - 0.3));
            geometry.xy += (int)((this.medium.random() * 6.0f * this.speed / this.swits[this.cn][2] - 3.0f * this.speed / this.swits[this.cn][2]) * (this.bounce[this.cn] - 0.3));
        }
        if (this.wtouch && n24 == 1) {
            geometry.zy += (int)((this.medium.random() * 4.0f * this.speed / this.swits[this.cn][2] - 2.0f * this.speed / this.swits[this.cn][2]) * (this.bounce[this.cn] - 0.3));
            geometry.xy += (int)((this.medium.random() * 4.0f * this.speed / this.swits[this.cn][2] - 2.0f * this.speed / this.swits[this.cn][2]) * (this.bounce[this.cn] - 0.3));
        }
        if (this.currentDamage > this.maxDamage[this.cn] && !this.dest) {
            this.distruct(geometry);
            if (this.cntdest == 7) {
                this.dest = true;
            }
            else {
                ++this.cntdest;
            }
            if (this.cntdest == 1) {
                this.rpd.dest[this.im] = 300;
            }
        }
        if (geometry.dist == 0) {
            for (int n97 = 0; n97 < geometry.numberOfPlanes; ++n97) {
                if (geometry.planes[n97].chip != 0) {
                    geometry.planes[n97].chip = 0;
                }
                if (geometry.planes[n97].embos != 0) {
                    geometry.planes[n97].embos = 13;
                }
            }
        }
        int focus = 0;
        int n98 = 0;
        int n99 = 0;
        int n100;
        if (this.nofocus) {
            n100 = 1;
        }
        else {
            n100 = 7;
        }
        for (int n101 = 0; n101 < checkPoints.n; ++n101) {
            if (checkPoints.typ[n101] > 0) {
                ++n99;
                if (checkPoints.typ[n101] == 1) {
                    if (this.clear == n99 + this.nlaps * checkPoints.nsp) {
                        n100 = 1;
                    }
                    if (Math.abs(geometry.z - checkPoints.z[n101]) < 60.0f + Math.abs(this.scz[0] + this.scz[1] + this.scz[2] + this.scz[3]) / 4.0f && Math.abs(geometry.x - checkPoints.x[n101]) < 700 && Math.abs(geometry.y - checkPoints.y[n101]) < 800 && this.clear == n99 + this.nlaps * checkPoints.nsp - 1) {
                        this.clear = n99 + this.nlaps * checkPoints.nsp;
                        this.pcleared = n101;
                        this.focus = -1;
                    }
                }
                if (checkPoints.typ[n101] == 2) {
                    if (this.clear == n99 + this.nlaps * checkPoints.nsp) {
                        n100 = 1;
                    }
                    if (Math.abs(geometry.x - checkPoints.x[n101]) < 60.0f + Math.abs(this.scx[0] + this.scx[1] + this.scx[2] + this.scx[3]) / 4.0f && Math.abs(geometry.z - checkPoints.z[n101]) < 700 && Math.abs(geometry.y - checkPoints.y[n101]) < 800 && this.clear == n99 + this.nlaps * checkPoints.nsp - 1) {
                        this.clear = n99 + this.nlaps * checkPoints.nsp;
                        this.pcleared = n101;
                        this.focus = -1;
                    }
                }
            }
            if (this.py(geometry.x / 100, checkPoints.x[n101] / 100, geometry.z / 100, checkPoints.z[n101] / 100) * n100 < n98 || n98 == 0) {
                focus = n101;
                n98 = this.py(geometry.x / 100, checkPoints.x[n101] / 100, geometry.z / 100, checkPoints.z[n101] / 100) * n100;
            }
        }
        if (this.clear == n99 + this.nlaps * checkPoints.nsp) {
            ++this.nlaps;
        }
        if (this.focus == -1) {
            if (this.im == 0) {
                focus += 2;
            }
            else {
                ++focus;
            }
            if (!this.nofocus) {
                int n102;
                for (n102 = this.pcleared + 1; checkPoints.typ[n102] <= 0; ++n102) {
                    if (n102 == checkPoints.n) {
                    	n102 = 0;
					}
                }
                if (focus > n102 && (this.clear != this.nlaps * checkPoints.nsp || focus < this.pcleared)) {
                    focus = n102;
                    this.focus = focus;
                }
            }
            if (focus >= checkPoints.n) {
                focus -= checkPoints.n;
            }
            if (checkPoints.typ[focus] == -3) {
                focus = 0;
            }
            if (this.im == 0) {
                if (this.missedCheckpoint != -1) {
                    this.missedCheckpoint = -1;
                }
            }
            else if (this.missedCheckpoint != 0) {
                this.missedCheckpoint = 0;
            }
        }
        else {
            focus = this.focus;
            if (this.im == 0) {
                if (this.missedCheckpoint == 0 && this.mtouch && Math.sqrt(this.py(geometry.x / 10, checkPoints.x[this.focus] / 10, geometry.z / 10, checkPoints.z[this.focus] / 10)) > 800.0) {
                    this.missedCheckpoint = 1;
                }
                if (this.missedCheckpoint == -2 && Math.sqrt(this.py(geometry.x / 10, checkPoints.x[this.focus] / 10, geometry.z / 10, checkPoints.z[this.focus] / 10)) < 400.0) {
                    this.missedCheckpoint = 0;
                }
                if (this.missedCheckpoint != 0 && this.mtouch && Math.sqrt(this.py(geometry.x / 10, checkPoints.x[this.focus] / 10, geometry.z / 10, checkPoints.z[this.focus] / 10)) < 250.0) {
                    this.missedCheckpoint = 68;
                }
            }
            else {
                this.missedCheckpoint = 1;
            }
            if (this.nofocus) {
                this.focus = -1;
                this.missedCheckpoint = 0;
            }
        }
        if (this.nofocus) {
            this.nofocus = false;
        }
        this.point = focus;
        for (int n103 = 0; n103 < checkPoints.fn; ++n103) {
            if (!checkPoints.roted[n103]) {
                if (Math.abs(geometry.z - checkPoints.fz[n103]) < 200 && this.py(geometry.x / 100, checkPoints.fx[n103] / 100, geometry.y / 100, checkPoints.fy[n103] / 100) < 30) {
                    if (geometry.dist == 0) {
                        geometry.fcnt = 8;
                    }
                    else {
                        if (this.im == 0 && !geometry.fix && !this.graphicsPanel.isSoundMuted) {
                            this.graphicsPanel.carfixed.play();
                        }
                        geometry.fix = true;
                    }
                    this.rpd.fix[this.im] = 300;
                }
            }
            else if (Math.abs(geometry.x - checkPoints.fx[n103]) < 200 && this.py(geometry.z / 100, checkPoints.fz[n103] / 100, geometry.y / 100, checkPoints.fy[n103] / 100) < 30) {
                if (geometry.dist == 0) {
                    geometry.fcnt = 8;
                }
                else {
                    if (this.im == 0 && !geometry.fix && !this.graphicsPanel.isSoundMuted) {
                        this.graphicsPanel.carfixed.play();
                    }
                    geometry.fix = true;
                }
                this.rpd.fix[this.im] = 300;
            }
        }
        if (geometry.fcnt == 7 || geometry.fcnt == 8) {
            this.squash = 0;
            this.nbsq = 0;
            this.currentDamage = 0;
            this.cntdest = 0;
            this.dest = false;
            this.newcar = true;
        }
        if (!this.mtouch) {
            if (this.trcnt != 1) {
                this.trcnt = 1;
                this.lxz = geometry.xz;
            }
            if (this.loop == 2 || this.loop == -1) {
                this.travxy += (int)(this.rcomp - this.lcomp);
                if (Math.abs(this.travxy) > 135) {
                    this.rtab = true;
                }
                this.travzy += (int)(this.ucomp - this.dcomp);
                if (this.travzy > 135) {
                    this.ftab = true;
                }
                if (this.travzy < -135) {
                    this.btab = true;
                }
            }
            if (this.lxz != geometry.xz) {
                this.travxz += this.lxz - geometry.xz;
                this.lxz = geometry.xz;
            }
            if (this.srfcnt < 10) {
                if (control.wall != -1) {
                    this.surfer = true;
                }
                ++this.srfcnt;
            }
        }
        else if (!this.dest) {
            if (!this.capsized) {
                if (this.capcnt != 0) {
                    this.capcnt = 0;
                }
                if (this.gtouch && this.trcnt != 0) {
                    if (this.trcnt == 9) {
                        this.powerup = 0.0f;
                        if (Math.abs(this.travxy) > 90) {
                            this.powerup += Math.abs(this.travxy) / 24.0f;
                        }
                        else if (this.rtab) {
                            this.powerup += 30.0f;
                        }
                        if (Math.abs(this.travzy) > 90) {
                            this.powerup += Math.abs(this.travzy) / 18.0f;
                        }
                        else {
                            if (this.ftab) {
                                this.powerup += 40.0f;
                            }
                            if (this.btab) {
                                this.powerup += 40.0f;
                            }
                        }
                        if (Math.abs(this.travxz) > 90) {
                            this.powerup += Math.abs(this.travxz) / 18.0f;
                        }
                        if (this.surfer) {
                            this.powerup += 30.0f;
                        }
                        this.power += this.powerup;
                        if (this.im == 0 && (int)this.powerup > this.rpd.powered && this.rpd.wasted == 0 && this.powerup > 60.0f) {
                            this.rpd.cotchinow(0);
                            if (this.rpd.hcaught) {
                                this.rpd.whenwasted = 225;
                                this.rpd.powered = (int)this.powerup;
                            }
                        }
                        if (this.power > 98.0f) {
                            this.power = 98.0f;
                            if (this.powerup > 150.0f) {
                                this.xtpower = 200;
                            }
                            else {
                                this.xtpower = 100;
                            }
                        }
                    }
                    if (this.trcnt == 10) {
                        this.travxy = 0;
                        this.travzy = 0;
                        this.travxz = 0;
                        this.ftab = false;
                        this.rtab = false;
                        this.btab = false;
                        this.trcnt = 0;
                        this.srfcnt = 0;
                        this.surfer = false;
                    }
                    else {
                        ++this.trcnt;
                    }
                }
            }
            else {
                if (this.trcnt != 0) {
                    this.travxy = 0;
                    this.travzy = 0;
                    this.travxz = 0;
                    this.ftab = false;
                    this.rtab = false;
                    this.btab = false;
                    this.trcnt = 0;
                    this.srfcnt = 0;
                    this.surfer = false;
                }
                if (this.capcnt == 0) {
                    int n104 = 0;
                    int n105 = 0;
                    do {
                        if (Math.abs(this.scz[n105]) < 70.0f && Math.abs(this.scx[n105]) < 70.0f) {
                            ++n104;
                        }
                    } while (++n105 < 4);
                    if (n104 == 4) {
                        this.capcnt = 1;
                    }
                }
                else {
                    ++this.capcnt;
                    if (this.capcnt == 30) {
                        this.speed = 0.0f;
                        geometry.y += this.flipy[this.cn];
                        this.pxy += 180;
                        geometry.xy += 180;
                        this.capcnt = 0;
                    }
                }
            }
            if (this.trcnt == 0) {
                if (this.xtpower == 0) {
                    if (this.power > 0.0f) {
                        this.power -= this.power * this.power * this.power / this.powerloss[this.cn];
                    }
                    else {
                        this.power = 0.0f;
                    }
                }
                else {
                    --this.xtpower;
                }
            }
        }
        if (this.im == 0) {
            if (control.wall != -1) {
                control.wall = -1;
            }
        }
        else if (this.lastcolido != 0 && !this.dest) {
            --this.lastcolido;
        }
        if (this.dest) {
            if (checkPoints.dested[this.im] == 0) {
                if (this.lastcolido == 0) {
                    checkPoints.dested[this.im] = 1;
                }
                else {
                    checkPoints.dested[this.im] = 2;
                }
            }
        }
        else if (checkPoints.dested[this.im] != 0) {
            checkPoints.dested[this.im] = 0;
        }
    }
}
