package main.java;

import java.awt.Graphics;
import java.awt.Color;


public class Plane {
    Medium medium;
    Trackers trackers;

    int[] ox;
    int[] oy;
    int[] oz;
    int numberOfPoints;
    int[] color;
    int[] oc;
    float[] hsb;
    boolean isGlass;
    int gr;
    int disline;
    boolean isRoad;
    int master;
    int wx;
    int wz;
    int wy;
    float deltaf;
    float projf;
    int av;
    int bfase;
    boolean isNotColored;
    int chip;
    float ctmag;
    float cxz;
    float cxy;
    float czy;
    int[] cox;
    int[] coz;
    int[] coy;
    int dx;
    int dy;
    int dz;
    int vx;
    int vy;
    int vz;
    int embos;
    int typ;
    int pa;
    int pb;
    int flx;

    public void loadprojf() {
        this.projf = 1.0f;
        int n = 0;
        do {
            int n2 = 0;
            do {
                if (n2 != n) {
                    this.projf *= (float) (Math.sqrt((this.ox[n] - this.ox[n2]) * (this.ox[n] - this.ox[n2]) + (this.oz[n] - this.oz[n2]) * (this.oz[n] - this.oz[n2])) / 100.0);
                }
            } while (++n2 < 3);
        } while (++n < 3);
        this.projf /= 3.0f;
    }

    public float ys(float n, float cz) {
        if (cz < this.medium.centerZ) {
            cz = this.medium.centerZ;
        }
        return ((cz - this.medium.focusPoint) * (this.medium.centerY - n) / cz + n);
    }

    public Plane(final Medium medium, final Trackers trackers, final int[] array, final int[] array2, final int[] array3, final int numberOfPoints, final int[] array4, final boolean isGlass, final int gr, final int wx, final int wy, final int wz, final int disline, final int bfase, final boolean isRoad) {
        this.color = new int[3];
        this.oc = new int[3];
        this.hsb = new float[3];
        this.isGlass = false;
        this.gr = 0;
        this.disline = 7;
        this.isRoad = false;
        this.master = 0;
        this.wx = 0;
        this.wz = 0;
        this.wy = 0;
        this.deltaf = 1.0f;
        this.projf = 1.0f;
        this.av = 0;
        this.bfase = 0;
        this.isNotColored = false;
        this.chip = 0;
        this.ctmag = 0.0f;
        this.cxz = 0;
        this.cxy = 0;
        this.czy = 0;
        this.cox = new int[3];
        this.coz = new int[3];
        this.coy = new int[3];
        this.dx = 0;
        this.dy = 0;
        this.dz = 0;
        this.vx = 0;
        this.vy = 0;
        this.vz = 0;
        this.embos = 0;
        this.typ = 0;
        this.pa = 0;
        this.pb = 0;
        this.flx = 0;
        this.medium = medium;
        this.trackers = trackers;
        this.numberOfPoints = numberOfPoints;
        this.ox = new int[this.numberOfPoints];
        this.oz = new int[this.numberOfPoints];
        this.oy = new int[this.numberOfPoints];
        for (int i = 0; i < this.numberOfPoints; ++i) {
            this.ox[i] = array[i];
            this.oy[i] = array3[i];
            this.oz[i] = array2[i];
        }
        final int abs = Math.abs(this.ox[2] - this.ox[1]);
        final int abs2 = Math.abs(this.oy[2] - this.oy[1]);
        final int abs3 = Math.abs(this.oz[2] - this.oz[1]);
        if (abs2 <= abs && abs2 <= abs3) {
            this.typ = 2;
        }
        if (abs <= abs2 && abs <= abs3) {
            this.typ = 1;
        }
        if (abs3 <= abs && abs3 <= abs2) {
            this.typ = 3;
        }
        int n2 = 0;
        do {
            this.oc[n2] = array4[n2];
        } while (++n2 < 3);
        if (array4[0] == array4[1] && array4[1] == array4[2]) {
            this.isNotColored = true;
        }
        if (!isGlass) {
            for (int i = 0; i < 3; i++) {
                this.color[i] = Util.clampCol((int) (array4[i] + array4[i] * (this.medium.snapColor[i] / 100.0f)));
            }
        }
        if (isGlass) {
            for (int i = 0; i < 3; i++) {
                this.color[i] = (this.medium.skyColor[i] * this.medium.fade[0] * 2 + this.medium.fadeColor[i] * 3000) / (this.medium.fade[0] * 2 + 3000);
            }
        }
        this.disline = disline;
        this.bfase = bfase;
        this.isGlass = isGlass;
        Color.RGBtoHSB(this.color[0], this.color[1], this.color[2], this.hsb);
        if (!this.isNotColored && !this.isGlass) {
            if (this.bfase > 20 && this.hsb[1] > 0.2) {
                this.hsb[1] = 0.2f;
            }
            if (this.bfase > 30) {
                if (this.hsb[2] < 0.5) {
                    this.hsb[2] = 0.5f;
                }
                if (this.hsb[1] > 0.1) {
                    this.hsb[1] = 0.1f;
                }
            }
            if (this.bfase > 40) {
                this.hsb[1] = 0.05f;
            }
            if (this.bfase > 50) {
                if (this.hsb[2] > 0.8) {
                    this.hsb[2] = 0.8f;
                }
                this.hsb[0] = 0.075f;
            }
            if (this.bfase > 60) {
                this.hsb[0] = 0.05f;
            }
        }
        this.isRoad = isRoad;
        this.gr = gr;
        this.wx = wx;
        this.wy = wy;
        this.wz = wz;
        int n5 = 0;
        do {
            int n6 = 0;
            do {
                if (n6 != n5) {
                    this.deltaf *= (float) (Math.sqrt((this.ox[n6] - this.ox[n5]) * (this.ox[n6] - this.ox[n5]) + (this.oy[n6] - this.oy[n5]) * (this.oy[n6] - this.oy[n5]) + (this.oz[n6] - this.oz[n5]) * (this.oz[n6] - this.oz[n5])) / 100.0);
                }
            } while (++n6 < 3);
        } while (++n5 < 3);
        this.deltaf /= 3.0f;
    }

    public void draw(final Graphics graphics, final int x, final int y, final int z, final float cxz, final float cxy, final float czy, final float wxz, final float wzy, boolean drawLines) {
        final int[] xPoints = new int[this.numberOfPoints];
        final int[] yPoints = new int[this.numberOfPoints];
        final int[] zPoints = new int[this.numberOfPoints];
        if (this.embos == 0) {
            for (int i = 0; i < this.numberOfPoints; ++i) {
                xPoints[i] = this.ox[i] + x;
                zPoints[i] = this.oy[i] + y;
                yPoints[i] = this.oz[i] + z;
            }
        } else {
            if (this.embos <= 11 && this.medium.random() > 0.5 && !this.isGlass) {
                for (int j = 0; j < this.numberOfPoints; ++j) {
                    xPoints[j] = (int) (this.ox[j] + x + (15.0f - this.medium.random() * 30.0f));
                    zPoints[j] = (int) (this.oy[j] + y + (15.0f - this.medium.random() * 30.0f));
                    yPoints[j] = (int) (this.oz[j] + z + (15.0f - this.medium.random() * 30.0f));
                }
                this.rot(xPoints, zPoints, x, y, cxy, this.numberOfPoints);
                this.rot(zPoints, yPoints, y, z, czy, this.numberOfPoints);
                this.rot(xPoints, yPoints, x, z, cxz, this.numberOfPoints);
                this.rot(xPoints, yPoints, this.medium.centerX, this.medium.centerZ, this.medium.xz, this.numberOfPoints);
                this.rot(zPoints, yPoints, this.medium.centerY, this.medium.centerZ, this.medium.zy, this.numberOfPoints);
                final int[] xPointsS = new int[this.numberOfPoints];
                final int[] yPointsS = new int[this.numberOfPoints];
                for (int k = 0; k < this.numberOfPoints; ++k) {
                    xPointsS[k] = (int) this.xs(xPoints[k], yPoints[k]);
                    yPointsS[k] = (int) this.ys(zPoints[k], yPoints[k]);
                }
                graphics.setColor(new Color(230, 230, 230));
                graphics.fillPolygon(xPointsS, yPointsS, this.numberOfPoints);
            }
            float n8 = 1.0f;
            if (this.embos <= 4) {
                n8 = 1.0f + this.medium.random() / 5.0f;
            }
            if (this.embos > 4 && this.embos <= 7) {
                n8 = 1.0f + this.medium.random() / 4.0f;
            }
            if (this.embos > 7 && this.embos <= 9) {
                n8 = 1.0f + this.medium.random() / 3.0f;
                if (this.hsb[2] > 0.7) {
                    this.hsb[2] = 0.7f;
                }
            }
            if (this.embos == 10) {
                n8 = 1.0f + this.medium.random() / 2.0f;
                if (this.hsb[2] > 0.6) {
                    this.hsb[2] = 0.6f;
                }
            }
            if (this.embos > 10 && this.embos <= 12) {
                n8 = 1.0f + this.medium.random() / 1.0f;
                if (this.hsb[2] > 0.5) {
                    this.hsb[2] = 0.5f;
                }
            }
            if (this.embos == 12) {
                this.chip = 1;
                this.ctmag = 2.0f;
                this.bfase = -7;
            }
            if (this.embos == 13) {
                this.hsb[1] = 0.2f;
                this.hsb[2] = 0.4f;
            }
            if (this.embos == 16) {
                this.pa = (int) (this.medium.random() * this.numberOfPoints);
                this.pb = (int) (this.medium.random() * this.numberOfPoints);
                while (this.pa == this.pb) {
                    this.pb = (int) (this.medium.random() * this.numberOfPoints);
                }
            }
            if (this.embos >= 16) {
                int n9 = 1;
                int n10 = 1;
                float l;
                l = Math.abs(czy);
                while (l > 270) {
                    l -= 360;
                }
                if (Math.abs(l) > 90) {
                    n9 = -1;
                }
                float abs;
                abs = Math.abs(cxy);
                while (abs > 270) {
                    abs -= 360;
                }
                if (Math.abs(abs) > 90) {
                    n10 = -1;
                }
                final int[] array6 = new int[3];
                final int[] array7 = new int[3];
                xPoints[0] = this.ox[this.pa] + x;
                zPoints[0] = this.oy[this.pa] + y;
                yPoints[0] = this.oz[this.pa] + z;
                xPoints[1] = this.ox[this.pb] + x;
                zPoints[1] = this.oy[this.pb] + y;
                yPoints[1] = this.oz[this.pb] + z;
                while (Math.abs(xPoints[0] - xPoints[1]) > 100) {
                    if (xPoints[1] > xPoints[0]) {
                        xPoints[1] -= 30;
                    } else {
                        xPoints[1] += 30;
                    }
                }
                while (Math.abs(yPoints[0] - yPoints[1]) > 100) {
                    if (yPoints[1] > yPoints[0]) {
                        yPoints[1] -= 30;
                    } else {
                        yPoints[1] += 30;
                    }
                }
                final int n15 = (int) (Math.abs(xPoints[0] - xPoints[1]) / 3 * (0.5 - this.medium.random()));
                final int n16 = (int) (Math.abs(yPoints[0] - yPoints[1]) / 3 * (0.5 - this.medium.random()));
                xPoints[2] = (xPoints[0] + xPoints[1]) / 2 + n15;
                yPoints[2] = (yPoints[0] + yPoints[1]) / 2 + n16;
                final int n17 = (int) ((Math.abs(xPoints[0] - xPoints[1]) + Math.abs(yPoints[0] - yPoints[1])) / 1.5 * (this.medium.random() / 2.0f + 0.5));
                zPoints[2] = (zPoints[0] + zPoints[1]) / 2 - n9 * n10 * n17;
                this.rot(xPoints, zPoints, x, y, cxy, 3);
                this.rot(zPoints, yPoints, y, z, czy, 3);
                this.rot(xPoints, yPoints, x, z, cxz, 3);
                this.rot(xPoints, yPoints, this.medium.centerX, this.medium.centerZ, this.medium.xz, 3);
                this.rot(zPoints, yPoints, this.medium.centerY, this.medium.centerZ, this.medium.zy, 3);
                int n18 = 0;
                do {
                    array6[n18] = (int) this.xs(xPoints[n18], yPoints[n18]);
                    array7[n18] = (int) this.ys(zPoints[n18], yPoints[n18]);
                } while (++n18 < 3);
                int r = (int) (255.0f + 255.0f * (this.medium.snapColor[0] / 400.0f));
                r = Util.clamp(r, 0, 255);
                int g = (int) (169.0f + 169.0f * (this.medium.snapColor[1] / 300.0f));
                g = Util.clamp(g, 0, 255);
                int b2 = (int) (89.0f + 89.0f * (this.medium.snapColor[2] / 200.0f));
                b2 = Util.clamp(b2, 0, 255);
                graphics.setColor(new Color(r, g, b2));
                graphics.fillPolygon(array6, array7, 3);
                xPoints[0] = this.ox[this.pa] + x;
                zPoints[0] = this.oy[this.pa] + y;
                yPoints[0] = this.oz[this.pa] + z;
                xPoints[1] = this.ox[this.pb] + x;
                zPoints[1] = this.oy[this.pb] + y;
                yPoints[1] = this.oz[this.pb] + z;
                while (Math.abs(xPoints[0] - xPoints[1]) > 100) {
                    if (xPoints[1] > xPoints[0]) {
                        xPoints[1] -= 30;
                    } else {
                        xPoints[1] += 30;
                    }
                }
                while (Math.abs(yPoints[0] - yPoints[1]) > 100) {
                    if (yPoints[1] > yPoints[0]) {
                        yPoints[1] -= 30;
                    } else {
                        yPoints[1] += 30;
                    }
                }
                xPoints[2] = (xPoints[0] + xPoints[1]) / 2 + n15;
                yPoints[2] = (yPoints[0] + yPoints[1]) / 2 + n16;
                zPoints[2] = (zPoints[0] + zPoints[1]) / 2 - n9 * n10 * (int) (n17 * 0.8);
                this.rot(xPoints, zPoints, x, y, cxy, 3);
                this.rot(zPoints, yPoints, y, z, czy, 3);
                this.rot(xPoints, yPoints, x, z, cxz, 3);
                this.rot(xPoints, yPoints, this.medium.centerX, this.medium.centerZ, this.medium.xz, 3);
                this.rot(zPoints, yPoints, this.medium.centerY, this.medium.centerZ, this.medium.zy, 3);
                int n23 = 0;
                do {
                    array6[n23] = (int) this.xs(xPoints[n23], yPoints[n23]);
                    array7[n23] = (int) this.ys(zPoints[n23], yPoints[n23]);
                } while (++n23 < 3);
                int r2 = (int) (255.0f + 255.0f * (this.medium.snapColor[0] / 400.0f));
                r2 = Util.clamp(r2, 0, 255);
                int g2 = (int) (207.0f + 207.0f * (this.medium.snapColor[1] / 300.0f));
                g2 = Util.clamp(g2, 0, 255);
                int b3 = (int) (136.0f + 136.0f * (this.medium.snapColor[2] / 200.0f));
                b3 = Util.clamp(b3, 0, 255);

                graphics.setColor(new Color(r2, g2, b3));
                graphics.fillPolygon(array6, array7, 3);
            }
            for (int i = 0; i < this.numberOfPoints; ++i) {
                if (this.typ == 1) {
                    xPoints[i] = (int) (this.ox[i] * n8 + x);
                } else {
                    xPoints[i] = this.ox[i] + x;
                }
                if (this.typ == 2) {
                    zPoints[i] = (int) (this.oy[i] * n8 + y);
                } else {
                    zPoints[i] = this.oy[i] + y;
                }
                if (this.typ == 3) {
                    yPoints[i] = (int) (this.oz[i] * n8 + z);
                } else {
                    yPoints[i] = this.oz[i] + z;
                }
            }
            if (this.embos != 70) {
                ++this.embos;
            } else {
                this.embos = 16;
            }
        }
        if (this.wz != 0) {
            this.rot(zPoints, yPoints, this.wy + y, this.wz + z, wzy, this.numberOfPoints);
        }
        if (this.wx != 0) {
            this.rot(xPoints, yPoints, this.wx + x, this.wz + z, wxz, this.numberOfPoints);
        }
        if (this.chip == 1 && (this.medium.random() > 0.6 || this.bfase == 0)) {
            this.chip = 0;
            if (this.bfase == 0 && this.isNotColored) {
                this.bfase = 1;
            }
        }
        if (this.chip != 0) {
            if (this.chip == 1) {
                this.cxz = cxz;
                this.cxy = cxy;
                this.czy = czy;
                final int n25 = (int) (this.medium.random() * this.numberOfPoints);
                this.cox[0] = this.ox[n25];
                this.coz[0] = this.oz[n25];
                this.coy[0] = this.oy[n25];
                if (this.ctmag > 3.0f) {
                    this.ctmag = 3.0f;
                }
                if (this.ctmag < -3.0f) {
                    this.ctmag = -3.0f;
                }
                this.cox[1] = (int) (this.cox[0] + this.ctmag * (10.0f - this.medium.random() * 20.0f));
                this.cox[2] = (int) (this.cox[0] + this.ctmag * (10.0f - this.medium.random() * 20.0f));
                this.coy[1] = (int) (this.coy[0] + this.ctmag * (10.0f - this.medium.random() * 20.0f));
                this.coy[2] = (int) (this.coy[0] + this.ctmag * (10.0f - this.medium.random() * 20.0f));
                this.coz[1] = (int) (this.coz[0] + this.ctmag * (10.0f - this.medium.random() * 20.0f));
                this.coz[2] = (int) (this.coz[0] + this.ctmag * (10.0f - this.medium.random() * 20.0f));
                this.dx = 0;
                this.dy = 0;
                this.dz = 0;
                if (this.bfase != -7) {
                    this.vx = (int) (this.ctmag * (30.0f - this.medium.random() * 60.0f));
                    this.vz = (int) (this.ctmag * (30.0f - this.medium.random() * 60.0f));
                    this.vy = (int) (this.ctmag * (30.0f - this.medium.random() * 60.0f));
                } else {
                    this.vx = (int) (this.ctmag * (10.0f - this.medium.random() * 20.0f));
                    this.vz = (int) (this.ctmag * (10.0f - this.medium.random() * 20.0f));
                    this.vy = (int) (this.ctmag * (10.0f - this.medium.random() * 20.0f));
                }
                this.chip = 2;
            }
            final int[] array16 = new int[3];
            final int[] array17 = new int[3];
            final int[] array18 = new int[3];
            int n26 = 0;
            do {
                array16[n26] = this.cox[n26] + x;
                array18[n26] = this.coy[n26] + y;
                array17[n26] = this.coz[n26] + z;
            } while (++n26 < 3);
            this.rot(array16, array18, x, y, this.cxy, 3);
            this.rot(array18, array17, y, z, this.czy, 3);
            this.rot(array16, array17, x, z, this.cxz, 3);
            int n27 = 0;
            do {
                array16[n27] += this.dx;
                array18[n27] += this.dy;
                array17[n27] += this.dz;
            } while (++n27 < 3);
            this.dx += this.vx;
            this.dz += this.vz;
            this.dy += this.vy;
            this.vy += 7;
            if (array18[0] > this.medium.ground) {
                this.chip = 19;
            }
            this.rot(array16, array17, this.medium.centerX, this.medium.centerZ, this.medium.xz, 3);
            this.rot(array18, array17, this.medium.centerY, this.medium.centerZ, this.medium.zy, 3);
            final int[] array22 = new int[3];
            final int[] array23 = new int[3];
            int n31 = 0;
            do {
                array22[n31] = (int) this.xs(array16[n31], array17[n31]);
                array23[n31] = (int) this.ys(array18[n31], array17[n31]);
                if (array23[n31] < 45 && this.medium.flex != 0) {
                    this.medium.flex = 0;
                }
            } while (++n31 < 3);
            final int n32 = (int) (this.medium.random() * 3.0f);
            if (this.bfase != -7) {
                if (n32 == 0) {
                    graphics.setColor(new Color(this.color[0], this.color[1], this.color[2]).darker());
                }
                if (n32 == 1) {
                    graphics.setColor(new Color(this.color[0], this.color[1], this.color[2]));
                }
                if (n32 == 2) {
                    graphics.setColor(new Color(this.color[0], this.color[1], this.color[2]).brighter());
                }
            } else {
                graphics.setColor(Color.getHSBColor(this.hsb[0], this.hsb[1], this.hsb[2]));
            }
            graphics.fillPolygon(array22, array23, 3);
            ++this.chip;
            if (this.chip == 20) {
                this.chip = 0;
            }
        }
        this.rot(xPoints, zPoints, x, y, cxy, this.numberOfPoints);
        this.rot(zPoints, yPoints, y, z, czy, this.numberOfPoints);
        this.rot(xPoints, yPoints, x, z, cxz, this.numberOfPoints);
        if (cxy != 0 || czy != 0 || cxz != 0) {
            this.projf = 1.0f;
            int n33 = 0;
            do {
                int n34 = 0;
                do {
                    if (n34 != n33) {
                        this.projf *= (float) (Math.sqrt((xPoints[n33] - xPoints[n34]) * (xPoints[n33] - xPoints[n34]) + (yPoints[n33] - yPoints[n34]) * (yPoints[n33] - yPoints[n34])) / 100.0);
                    }
                } while (++n34 < 3);
            } while (++n33 < 3);
            this.projf /= 3.0f;
        }
        this.rot(xPoints, yPoints, this.medium.centerX, this.medium.centerZ, this.medium.xz, this.numberOfPoints);
        boolean b4 = false;
        final int[] array24 = new int[this.numberOfPoints];
        final int[] array25 = new int[this.numberOfPoints];
        int n35 = 500;
        for (int i = 0; i < this.numberOfPoints; ++i) {
            array24[i] = (int) this.xs(xPoints[i], yPoints[i]);
            array25[i] = (int) this.ys(zPoints[i], yPoints[i]);
        }
        int n37 = 0;
        int n38 = 1;
        for (int n39 = 0; n39 < this.numberOfPoints; ++n39) {
            for (int n40 = 0; n40 < this.numberOfPoints; ++n40) {
                if (n39 != n40 && Math.abs(array24[n39] - array24[n40]) - Math.abs(array25[n39] - array25[n40]) < n35) {
                    n38 = n39;
                    n37 = n40;
                    n35 = Math.abs(array24[n39] - array24[n40]) - Math.abs(array25[n39] - array25[n40]);
                }
            }
        }
        if (array25[n37] < array25[n38]) {
            final int n41 = n37;
            n37 = n38;
            n38 = n41;
        }
        if (this.spy(xPoints[n37], yPoints[n37]) > this.spy(xPoints[n38], yPoints[n38])) {
            b4 = true;
            int n42 = 0;
            for (int n43 = 0; n43 < this.numberOfPoints; ++n43) {
                if (yPoints[n43] < 50 && zPoints[n43] > this.medium.centerY) {
                    b4 = false;
                } else if (zPoints[n43] == zPoints[0]) {
                    ++n42;
                }
            }
            if (n42 == this.numberOfPoints && zPoints[0] > this.medium.centerY) {
                b4 = false;
            }
        }
        this.rot(zPoints, yPoints, this.medium.centerY, this.medium.centerZ, (int) this.medium.zy, this.numberOfPoints);
        int n44 = 1;
        final int[] array26 = new int[this.numberOfPoints];
        final int[] array27 = new int[this.numberOfPoints];
        int n45 = 0;
        int n46 = 0;
        int n47 = 0;
        int n48 = 0;
        int n49 = 0;
        for (int n50 = 0; n50 < this.numberOfPoints; ++n50) {
            array26[n50] = (int) this.xs(xPoints[n50], yPoints[n50]);
            array27[n50] = (int) this.ys(zPoints[n50], yPoints[n50]);
            if (array27[n50] < 0 || yPoints[n50] < 10) {
                ++n45;
            }
            if (array27[n50] > this.medium.height || yPoints[n50] < 10) {
                ++n46;
            }
            if (array26[n50] < 0 || yPoints[n50] < 10) {
                ++n47;
            }
            if (array26[n50] > this.medium.width || yPoints[n50] < 10) {
                ++n48;
            }
            if (array27[n50] < 45 && this.medium.flex != 0) {
                this.medium.flex = 0;
            }
            if (yPoints[n50] < 10) {
                ++n49;
            }
        }
        if (n47 == this.numberOfPoints || n45 == this.numberOfPoints || n46 == this.numberOfPoints || n48 == this.numberOfPoints) {
            n44 = 0;
        }
        if (this.medium.trk && (n47 != 0 || n45 != 0 || n46 != 0 || n48 != 0)) {
            n44 = 0;
        }
        if (n49 != 0) {
            drawLines = false;
        }
        if (n44 != 0) {
            int n51 = 0;
            int n52 = 0;
            int n53 = 0;
            int n54 = 0;
            int n55 = 0;
            int n56 = 0;
            for (int n57 = 0; n57 < this.numberOfPoints; ++n57) {
                int n58 = 0;
                int n59 = 0;
                int n60 = 0;
                int n61 = 0;
                int n62 = 0;
                int n63 = 0;
                for (int n64 = 0; n64 < this.numberOfPoints; ++n64) {
                    if (zPoints[n57] >= zPoints[n64]) {
                        ++n58;
                    }
                    if (zPoints[n57] <= zPoints[n64]) {
                        ++n59;
                    }
                    if (xPoints[n57] >= xPoints[n64]) {
                        ++n60;
                    }
                    if (xPoints[n57] <= xPoints[n64]) {
                        ++n61;
                    }
                    if (yPoints[n57] >= yPoints[n64]) {
                        ++n62;
                    }
                    if (yPoints[n57] <= yPoints[n64]) {
                        ++n63;
                    }
                }
                if (n58 == this.numberOfPoints) {
                    n51 = zPoints[n57];
                }
                if (n59 == this.numberOfPoints) {
                    n52 = zPoints[n57];
                }
                if (n60 == this.numberOfPoints) {
                    n53 = xPoints[n57];
                }
                if (n61 == this.numberOfPoints) {
                    n54 = xPoints[n57];
                }
                if (n62 == this.numberOfPoints) {
                    n55 = yPoints[n57];
                }
                if (n63 == this.numberOfPoints) {
                    n56 = yPoints[n57];
                }
            }
            final int n65 = (n51 + n52) / 2;
            final int n66 = (n53 + n54) / 2;
            final int n67 = (n55 + n56) / 2;
            this.av = (int) Math.sqrt((this.medium.centerY - n65) * (this.medium.centerY - n65) + (this.medium.centerX - n66) * (this.medium.centerX - n66) + n67 * n67 + Math.abs(this.gr * this.gr * this.gr));
            if (!this.medium.trk && (this.av > this.medium.fade[this.disline] || this.av == 0)) {
                n44 = 0;
            }
            if (this.gr > 0 && this.av > 3500) {
                n44 = 0;
            }
            if (this.av > 3000 && this.medium.adv <= 900) {
                drawLines = false;
            }
            if (this.flx != 0 && this.medium.random() > 0.3) {
                n44 = 0;
            }
        }
        if (n44 != 0) {
            float n68 = this.projf / this.deltaf + 0.3f;
            if (!drawLines) {
                boolean b5 = false;
                if (n68 > 1.0f) {
                    if (n68 >= 1.27) {
                        b5 = true;
                    }
                    n68 = 1.0f;
                }
                if (b5) {
                    n68 *= 0.89f;
                } else {
                    n68 *= 0.86f;
                }
                if (n68 < 0.37) {
                    n68 = 0.37f;
                }
                if (this.gr == -9) {
                    n68 = 0.7f;
                }
                if (this.gr != -7 && b4) {
                    n68 = 0.32f;
                }
                if (this.gr == -8) {
                    n68 = 1.0f;
                }
            } else {
                if (n68 > 1.0f) {
                    n68 = 1.0f;
                }
                if (n68 < 0.6 || b4) {
                    n68 = 0.6f;
                }
            }
            Color color;
            if (!this.medium.trk) {
                color = Color.getHSBColor(this.hsb[0], this.hsb[1], this.hsb[2] * n68);
            } else {
                final float[] hsbValues = new float[3];
                Color.RGBtoHSB(this.oc[0], this.oc[1], this.oc[2], hsbValues);
                color = Color.getHSBColor(0.105f, 0.95f, hsbValues[2] * n68 + 0.1f);
            }
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            if (!this.medium.trk) {
                for (int i = 0; i < this.medium.fade.length; i++) {
                    if (this.av > this.medium.fade[i]) {
                        red = (red * 3 + this.medium.fadeColor[0]) / 4;
                        green = (green * 3 + this.medium.fadeColor[1]) / 4;
                        blue = (blue * 3 + this.medium.fadeColor[2]) / 4;
                    }
                }
            }
            graphics.setColor(new Color(red, green, blue));
            graphics.fillPolygon(array26, array27, this.numberOfPoints);
            if (drawLines) {
                if (this.flx == 0) {
                    graphics.setColor(new Color(0, 0, 0));
                    graphics.drawPolygon(array26, array27, this.numberOfPoints);
                } else {
                    if (this.flx == 2) {
                        graphics.setColor(new Color(0, 0, 0));
                        graphics.drawPolygon(array26, array27, this.numberOfPoints);
                    }
                    if (this.flx == 1) {
                        int g = (int) (223.0f + 223.0f * (this.medium.snapColor[1] / 100.0f));
                        g = Util.clampCol(g);
                        int b = (int) (255.0f + 255.0f * (this.medium.snapColor[2] / 100.0f));
                        b = Util.clampCol(b);
                        graphics.setColor(new Color(0, g, b));
                        graphics.drawPolygon(array26, array27, this.numberOfPoints);
                        this.flx = 2;
                    }
                    if (this.flx == 3) {
                        int g = (int) (255.0f + 255.0f * (this.medium.snapColor[1] / 100.0f));
                        g = Util.clampCol(g);

                        int b = (int) (223.0f + 223.0f * (this.medium.snapColor[2] / 100.0f));
                        b = Util.clampCol(b);

                        graphics.setColor(new Color(0, g, b));
                        graphics.drawPolygon(array26, array27, this.numberOfPoints);
                        this.flx = 2;
                    }
                }
            } else if (this.av <= 3000 && !this.medium.trk && this.isRoad && this.medium.fade[0] > 4000) {
                red -= 10;
                red = Math.max(red, 0);
                green -= 10;
                green = Math.max(green, 0);
                blue -= 10;
                blue = Math.max(blue, 0);
                graphics.setColor(new Color(red, green, blue));
                graphics.drawPolygon(array26, array27, this.numberOfPoints);
            }
            if (!this.isGlass && this.gr == -10 && !this.medium.trk) {
                int r = this.color[0];
                int g = this.color[1];
                int b = this.color[2];
                for(int i = 0; i < this.medium.fade.length; i++) {
                    if (this.av > this.medium.fade[i]) {
                        r = (r * 3 + this.medium.fadeColor[0]) / 4;
                        g = (g * 3 + this.medium.fadeColor[1]) / 4;
                        b = (b * 3 + this.medium.fadeColor[2]) / 4;
                    }
                }
                graphics.setColor(new Color(r, g, b));
                graphics.drawPolygon(array26, array27, this.numberOfPoints);
            }
        }
    }

    public void rot(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        if (n3 != 0) {
            for (int i = 0; i < n4; ++i) {
                final float n5 = array[i];
                final float n6 = array2[i];
                array[i] = n + (int) ((n5 - n) * Util.cos(n3) - (n6 - n2) * Util.sin(n3));
                array2[i] = n2 + (int) ((n5 - n) * Util.sin(n3) + (n6 - n2) * Util.cos(n3));
            }
        }
    }

    public void rot(final int[] array, final int[] array2, final float n, final float n2, final float n3, final int numberOfPoints) {
        if (n3 != 0) {
            for (int i = 0; i < numberOfPoints; ++i) {
                final float n5 = array[i];
                final float n6 = array2[i];
                array[i] = (int) (n + (n5 - n) * Util.cos(n3) - (n6 - n2) * Util.sin(n3));
                array2[i] = (int) (n2 + (n5 - n) * Util.sin(n3) + (n6 - n2) * Util.cos(n3));
            }
        }
    }


    public float xs(final float n, float cz) {
        if (cz < this.medium.centerZ) cz = this.medium.centerZ;
        return ((cz - this.medium.focusPoint) * (this.medium.centerX - n) / cz + n);
    }

    public void drawShadow(final Graphics graphics, final int n, final int n2, final int n3, final float n4, final float n5, final float n6, final int n7) {
        if (this.gr <= 0) {
            final int[] xCoords = new int[this.numberOfPoints];
            final int[] zCoords = new int[this.numberOfPoints];
            final int[] yCoords = new int[this.numberOfPoints];
            for (int i = 0; i < this.numberOfPoints; ++i) {
                xCoords[i] = this.ox[i] + n;
                yCoords[i] = this.oy[i] + n2;
                zCoords[i] = this.oz[i] + n3;
            }
            this.rot(xCoords, yCoords, n, n2, n5, this.numberOfPoints);
            this.rot(yCoords, zCoords, n2, n3, n6, this.numberOfPoints);
            this.rot(xCoords, zCoords, n, n3, n4, this.numberOfPoints);
            int r = (int) (this.medium.groundColor[0] / 1.5);
            int g = (int) (this.medium.groundColor[1] / 1.5);
            int b = (int) (this.medium.groundColor[2] / 1.5);
            for (int j = 0; j < this.numberOfPoints; ++j) {
                yCoords[j] = (int) this.medium.ground;
            }
            if (n7 == 0) {
                for (int k = this.trackers.numberOfTracks - 1; k >= 0; --k) {
                    int n8 = 0;
                    for (int l = 0; l < this.numberOfPoints; ++l) {
                        if (Math.abs(this.trackers.zy[k]) != 90 && Math.abs(this.trackers.xy[k]) != 90 && Math.abs(xCoords[l] - (this.trackers.x[k] - this.medium.positionX)) < this.trackers.radx[k] && Math.abs(zCoords[l] - (this.trackers.z[k] - this.medium.positionZ)) < this.trackers.radz[k]) {
                            ++n8;
                        }
                    }
                    if (n8 > this.numberOfPoints / 2) {
                        for (int n9 = 0; n9 < this.numberOfPoints; ++n9) {
                            yCoords[n9] = this.trackers.y[k] - this.medium.positionY;
                            if (this.trackers.zy[k] != 0) {
                                yCoords[n9] += (int) ((zCoords[n9] - (this.trackers.z[k] - this.medium.positionZ - this.trackers.radz[k])) * Util.sin(this.trackers.zy[k]) / Util.sin(90 - this.trackers.zy[k]) - this.trackers.radz[k] * Util.sin(this.trackers.zy[k]) / Util.sin(90 - this.trackers.zy[k]));
                            }
                            if (this.trackers.xy[k] != 0) {
                                yCoords[n9] += (int) ((xCoords[n9] - (this.trackers.x[k] - this.medium.positionX - this.trackers.radx[k])) * Util.sin(this.trackers.xy[k]) / Util.sin(90 - this.trackers.xy[k]) - this.trackers.radx[k] * Util.sin(this.trackers.xy[k]) / Util.sin(90 - this.trackers.xy[k]));
                            }
                        }
                        r = (int) ((float) this.trackers.objectColor[k][0] / 1.5);
                        g = (int) ((float) this.trackers.objectColor[k][1] / 1.5);
                        b = (int) ((float) this.trackers.objectColor[k][2] / 1.5);
                        break;
                    }
                }
            }
            int n12 = 1;
            final int[] array6 = new int[this.numberOfPoints];
            final int[] array7 = new int[this.numberOfPoints];
            if (n7 == 2) {
                r = 76;
                g = 98;
                b = 142;
            } else {
                for (int n13 = 0; n13 < this.medium.nsp; ++n13) {
                    for (int n14 = 0; n14 < this.numberOfPoints; ++n14) {
                        if (Math.abs(xCoords[n14] - this.medium.spx[n13]) < this.medium.sprad[n13] && Math.abs(zCoords[n14] - this.medium.spz[n13]) < this.medium.sprad[n13]) {
                            n12 = 0;
                            break;
                        }
                    }
                }
            }
            if (n12 != 0) {
                this.rot(xCoords, zCoords, this.medium.centerX, this.medium.centerZ, this.medium.xz, this.numberOfPoints);
                this.rot(yCoords, zCoords, this.medium.centerY, this.medium.centerZ, this.medium.zy, this.numberOfPoints);
                int n15 = 0;
                int n16 = 0;
                int n17 = 0;
                int n18 = 0;
                for (int i = 0; i < this.numberOfPoints; ++i) {
                    array6[i] = (int) this.xs(xCoords[i], zCoords[i]);
                    array7[i] = (int) this.ys(yCoords[i], zCoords[i]);
                    if (array7[i] < 0 || zCoords[i] < 10) {
                        ++n15;
                    }
                    if (array7[i] > this.medium.height || zCoords[i] < 10) {
                        ++n16;
                    }
                    if (array6[i] < 0 || zCoords[i] < 10) {
                        ++n17;
                    }
                    if (array6[i] > this.medium.width || zCoords[i] < 10) {
                        ++n18;
                    }
                }
                if (n17 == this.numberOfPoints || n15 == this.numberOfPoints || n16 == this.numberOfPoints || n18 == this.numberOfPoints) {
                    n12 = 0;
                }
            }
            if (n12 != 0) {
                int n20 = 0;
                do {
                    if (this.av > this.medium.fade[n20]) {
                        r = (r * 3 + this.medium.fadeColor[0]) / 4;
                        g = (g * 3 + this.medium.fadeColor[1]) / 4;
                        b = (b * 3 + this.medium.fadeColor[2]) / 4;
                    }
                } while (++n20 < 8);
                graphics.setColor(new Color(r, g, b));
                graphics.fillPolygon(array6, array7, this.numberOfPoints);
            }
        }
    }

    public int spy(final int n, final int n2) {
        return (int) Math.sqrt((n - this.medium.centerX) * (n - this.medium.centerX) + n2 * n2);
    }
}
