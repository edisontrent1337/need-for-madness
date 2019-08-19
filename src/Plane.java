import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.36
// 

public class Plane
{
    Medium m;
    Trackers t;
    int[] ox;
    int[] oy;
    int[] oz;
    int n;
    int[] c;
    int[] oc;
    float[] hsb;
    boolean glass;
    int gr;
    int disline;
    boolean road;
    int master;
    int wx;
    int wz;
    int wy;
    float deltaf;
    float projf;
    int av;
    int bfase;
    boolean nocol;
    int chip;
    float ctmag;
    int cxz;
    int cxy;
    int czy;
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
                    this.projf *= (float)(Math.sqrt((this.ox[n] - this.ox[n2]) * (this.ox[n] - this.ox[n2]) + (this.oz[n] - this.oz[n2]) * (this.oz[n] - this.oz[n2])) / 100.0);
                }
            } while (++n2 < 3);
        } while (++n < 3);
        this.projf /= 3.0f;
    }
    
    public int ys(final int n, int cz) {
        if (cz < this.m.cz) {
            cz = this.m.cz;
        }
        return (cz - this.m.focus_point) * (this.m.cy - n) / cz + n;
    }
    
    public Plane(final Medium m, final Trackers t, final int[] array, final int[] array2, final int[] array3, final int n, final int[] array4, final boolean glass, final int gr, final int wx, final int wy, final int wz, final int disline, final int bfase, final boolean road) {
        this.c = new int[3];
        this.oc = new int[3];
        this.hsb = new float[3];
        this.glass = false;
        this.gr = 0;
        this.disline = 7;
        this.road = false;
        this.master = 0;
        this.wx = 0;
        this.wz = 0;
        this.wy = 0;
        this.deltaf = 1.0f;
        this.projf = 1.0f;
        this.av = 0;
        this.bfase = 0;
        this.nocol = false;
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
        this.m = m;
        this.t = t;
        this.n = n;
        this.ox = new int[this.n];
        this.oz = new int[this.n];
        this.oy = new int[this.n];
        for (int i = 0; i < this.n; ++i) {
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
            this.nocol = true;
        }
        if (!glass) {
            int n3 = 0;
            do {
                this.c[n3] = (int)(array4[n3] + array4[n3] * (this.m.snap[n3] / 100.0f));
                if (this.c[n3] > 255) {
                    this.c[n3] = 255;
                }
                if (this.c[n3] < 0) {
                    this.c[n3] = 0;
                }
            } while (++n3 < 3);
        }
        if (glass) {
            int n4 = 0;
            do {
                this.c[n4] = (this.m.csky[n4] * this.m.fade[0] * 2 + this.m.cfade[n4] * 3000) / (this.m.fade[0] * 2 + 3000);
            } while (++n4 < 3);
        }
        this.disline = disline;
        this.bfase = bfase;
        this.glass = glass;
        Color.RGBtoHSB(this.c[0], this.c[1], this.c[2], this.hsb);
        if (!this.nocol && !this.glass) {
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
                this.hsb[1] = 0.05f;
            }
            if (this.bfase > 60) {
                this.hsb[0] = 0.05f;
            }
        }
        this.road = road;
        this.gr = gr;
        this.wx = wx;
        this.wy = wy;
        this.wz = wz;
        int n5 = 0;
        do {
            int n6 = 0;
            do {
                if (n6 != n5) {
                    this.deltaf *= (float)(Math.sqrt((this.ox[n6] - this.ox[n5]) * (this.ox[n6] - this.ox[n5]) + (this.oy[n6] - this.oy[n5]) * (this.oy[n6] - this.oy[n5]) + (this.oz[n6] - this.oz[n5]) * (this.oz[n6] - this.oz[n5])) / 100.0);
                }
            } while (++n6 < 3);
        } while (++n5 < 3);
        this.deltaf /= 3.0f;
    }
    
    public void d(final Graphics graphics, final int n, final int n2, final int n3, final int cxz, final int n4, final int n5, final int n6, final int n7, boolean b) {
        final int[] array = new int[this.n];
        final int[] array2 = new int[this.n];
        final int[] array3 = new int[this.n];
        if (this.embos == 0) {
            for (int i = 0; i < this.n; ++i) {
                array[i] = this.ox[i] + n;
                array3[i] = this.oy[i] + n2;
                array2[i] = this.oz[i] + n3;
            }
        }
        else {
            if (this.embos <= 11 && this.m.random() > 0.5 && !this.glass) {
                for (int j = 0; j < this.n; ++j) {
                    array[j] = (int)(this.ox[j] + n + (15.0f - this.m.random() * 30.0f));
                    array3[j] = (int)(this.oy[j] + n2 + (15.0f - this.m.random() * 30.0f));
                    array2[j] = (int)(this.oz[j] + n3 + (15.0f - this.m.random() * 30.0f));
                }
                this.rot(array, array3, n, n2, n4, this.n);
                this.rot(array3, array2, n2, n3, n5, this.n);
                this.rot(array, array2, n, n3, cxz, this.n);
                this.rot(array, array2, this.m.cx, this.m.cz, this.m.xz, this.n);
                this.rot(array3, array2, this.m.cy, this.m.cz, this.m.zy, this.n);
                final int[] array4 = new int[this.n];
                final int[] array5 = new int[this.n];
                for (int k = 0; k < this.n; ++k) {
                    array4[k] = this.xs(array[k], array2[k]);
                    array5[k] = this.ys(array3[k], array2[k]);
                }
                graphics.setColor(new Color(230, 230, 230));
                graphics.fillPolygon(array4, array5, this.n);
            }
            float n8 = 1.0f;
            if (this.embos <= 4) {
                n8 = 1.0f + this.m.random() / 5.0f;
            }
            if (this.embos > 4 && this.embos <= 7) {
                n8 = 1.0f + this.m.random() / 4.0f;
            }
            if (this.embos > 7 && this.embos <= 9) {
                n8 = 1.0f + this.m.random() / 3.0f;
                if (this.hsb[2] > 0.7) {
                    this.hsb[2] = 0.7f;
                }
            }
            if (this.embos > 9 && this.embos <= 10) {
                n8 = 1.0f + this.m.random() / 2.0f;
                if (this.hsb[2] > 0.6) {
                    this.hsb[2] = 0.6f;
                }
            }
            if (this.embos > 10 && this.embos <= 12) {
                n8 = 1.0f + this.m.random() / 1.0f;
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
                this.pa = (int)(this.m.random() * this.n);
                this.pb = (int)(this.m.random() * this.n);
                while (this.pa == this.pb) {
                    this.pb = (int)(this.m.random() * this.n);
                }
            }
            if (this.embos >= 16) {
                int n9 = 1;
                int n10 = 1;
                int l;
                for (l = Math.abs(n5); l > 270; l -= 360) {}
                if (Math.abs(l) > 90) {
                    n9 = -1;
                }
                int abs;
                for (abs = Math.abs(n4); abs > 270; abs -= 360) {}
                if (Math.abs(abs) > 90) {
                    n10 = -1;
                }
                final int[] array6 = new int[3];
                final int[] array7 = new int[3];
                array[0] = this.ox[this.pa] + n;
                array3[0] = this.oy[this.pa] + n2;
                array2[0] = this.oz[this.pa] + n3;
                array[1] = this.ox[this.pb] + n;
                array3[1] = this.oy[this.pb] + n2;
                array2[1] = this.oz[this.pb] + n3;
                while (Math.abs(array[0] - array[1]) > 100) {
                    if (array[1] > array[0]) {
                        final int[] array8 = array;
                        final int n11 = 1;
                        array8[n11] -= 30;
                    }
                    else {
                        final int[] array9 = array;
                        final int n12 = 1;
                        array9[n12] += 30;
                    }
                }
                while (Math.abs(array2[0] - array2[1]) > 100) {
                    if (array2[1] > array2[0]) {
                        final int[] array10 = array2;
                        final int n13 = 1;
                        array10[n13] -= 30;
                    }
                    else {
                        final int[] array11 = array2;
                        final int n14 = 1;
                        array11[n14] += 30;
                    }
                }
                final int n15 = (int)(Math.abs(array[0] - array[1]) / 3 * (0.5 - this.m.random()));
                final int n16 = (int)(Math.abs(array2[0] - array2[1]) / 3 * (0.5 - this.m.random()));
                array[2] = (array[0] + array[1]) / 2 + n15;
                array2[2] = (array2[0] + array2[1]) / 2 + n16;
                final int n17 = (int)((Math.abs(array[0] - array[1]) + Math.abs(array2[0] - array2[1])) / 1.5 * (this.m.random() / 2.0f + 0.5));
                array3[2] = (array3[0] + array3[1]) / 2 - n9 * n10 * n17;
                this.rot(array, array3, n, n2, n4, 3);
                this.rot(array3, array2, n2, n3, n5, 3);
                this.rot(array, array2, n, n3, cxz, 3);
                this.rot(array, array2, this.m.cx, this.m.cz, this.m.xz, 3);
                this.rot(array3, array2, this.m.cy, this.m.cz, this.m.zy, 3);
                int n18 = 0;
                do {
                    array6[n18] = this.xs(array[n18], array2[n18]);
                    array7[n18] = this.ys(array3[n18], array2[n18]);
                } while (++n18 < 3);
                int r = (int)(255.0f + 255.0f * (this.m.snap[0] / 400.0f));
                if (r > 255) {
                    r = 255;
                }
                if (r < 0) {
                    r = 0;
                }
                int g = (int)(169.0f + 169.0f * (this.m.snap[1] / 300.0f));
                if (g > 255) {
                    g = 255;
                }
                if (g < 0) {
                    g = 0;
                }
                int b2 = (int)(89.0f + 89.0f * (this.m.snap[2] / 200.0f));
                if (b2 > 255) {
                    b2 = 255;
                }
                if (b2 < 0) {
                    b2 = 0;
                }
                graphics.setColor(new Color(r, g, b2));
                graphics.fillPolygon(array6, array7, 3);
                array[0] = this.ox[this.pa] + n;
                array3[0] = this.oy[this.pa] + n2;
                array2[0] = this.oz[this.pa] + n3;
                array[1] = this.ox[this.pb] + n;
                array3[1] = this.oy[this.pb] + n2;
                array2[1] = this.oz[this.pb] + n3;
                while (Math.abs(array[0] - array[1]) > 100) {
                    if (array[1] > array[0]) {
                        final int[] array12 = array;
                        final int n19 = 1;
                        array12[n19] -= 30;
                    }
                    else {
                        final int[] array13 = array;
                        final int n20 = 1;
                        array13[n20] += 30;
                    }
                }
                while (Math.abs(array2[0] - array2[1]) > 100) {
                    if (array2[1] > array2[0]) {
                        final int[] array14 = array2;
                        final int n21 = 1;
                        array14[n21] -= 30;
                    }
                    else {
                        final int[] array15 = array2;
                        final int n22 = 1;
                        array15[n22] += 30;
                    }
                }
                array[2] = (array[0] + array[1]) / 2 + n15;
                array2[2] = (array2[0] + array2[1]) / 2 + n16;
                array3[2] = (array3[0] + array3[1]) / 2 - n9 * n10 * (int)(n17 * 0.8);
                this.rot(array, array3, n, n2, n4, 3);
                this.rot(array3, array2, n2, n3, n5, 3);
                this.rot(array, array2, n, n3, cxz, 3);
                this.rot(array, array2, this.m.cx, this.m.cz, this.m.xz, 3);
                this.rot(array3, array2, this.m.cy, this.m.cz, this.m.zy, 3);
                int n23 = 0;
                do {
                    array6[n23] = this.xs(array[n23], array2[n23]);
                    array7[n23] = this.ys(array3[n23], array2[n23]);
                } while (++n23 < 3);
                int r2 = (int)(255.0f + 255.0f * (this.m.snap[0] / 400.0f));
                if (r2 > 255) {
                    r2 = 255;
                }
                if (r2 < 0) {
                    r2 = 0;
                }
                int g2 = (int)(207.0f + 207.0f * (this.m.snap[1] / 300.0f));
                if (g2 > 255) {
                    g2 = 255;
                }
                if (g2 < 0) {
                    g2 = 0;
                }
                int b3 = (int)(136.0f + 136.0f * (this.m.snap[2] / 200.0f));
                if (b3 > 255) {
                    b3 = 255;
                }
                if (b3 < 0) {
                    b3 = 0;
                }
                graphics.setColor(new Color(r2, g2, b3));
                graphics.fillPolygon(array6, array7, 3);
            }
            for (int n24 = 0; n24 < this.n; ++n24) {
                if (this.typ == 1) {
                    array[n24] = (int)(this.ox[n24] * n8 + n);
                }
                else {
                    array[n24] = this.ox[n24] + n;
                }
                if (this.typ == 2) {
                    array3[n24] = (int)(this.oy[n24] * n8 + n2);
                }
                else {
                    array3[n24] = this.oy[n24] + n2;
                }
                if (this.typ == 3) {
                    array2[n24] = (int)(this.oz[n24] * n8 + n3);
                }
                else {
                    array2[n24] = this.oz[n24] + n3;
                }
            }
            if (this.embos != 70) {
                ++this.embos;
            }
            else {
                this.embos = 16;
            }
        }
        if (this.wz != 0) {
            this.rot(array3, array2, this.wy + n2, this.wz + n3, n7, this.n);
        }
        if (this.wx != 0) {
            this.rot(array, array2, this.wx + n, this.wz + n3, n6, this.n);
        }
        if (this.chip == 1 && (this.m.random() > 0.6 || this.bfase == 0)) {
            this.chip = 0;
            if (this.bfase == 0 && this.nocol) {
                this.bfase = 1;
            }
        }
        if (this.chip != 0) {
            if (this.chip == 1) {
                this.cxz = cxz;
                this.cxy = n4;
                this.czy = n5;
                final int n25 = (int)(this.m.random() * this.n);
                this.cox[0] = this.ox[n25];
                this.coz[0] = this.oz[n25];
                this.coy[0] = this.oy[n25];
                if (this.ctmag > 3.0f) {
                    this.ctmag = 3.0f;
                }
                if (this.ctmag < -3.0f) {
                    this.ctmag = -3.0f;
                }
                this.cox[1] = (int)(this.cox[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.cox[2] = (int)(this.cox[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.coy[1] = (int)(this.coy[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.coy[2] = (int)(this.coy[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.coz[1] = (int)(this.coz[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.coz[2] = (int)(this.coz[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.dx = 0;
                this.dy = 0;
                this.dz = 0;
                if (this.bfase != -7) {
                    this.vx = (int)(this.ctmag * (30.0f - this.m.random() * 60.0f));
                    this.vz = (int)(this.ctmag * (30.0f - this.m.random() * 60.0f));
                    this.vy = (int)(this.ctmag * (30.0f - this.m.random() * 60.0f));
                }
                else {
                    this.vx = (int)(this.ctmag * (10.0f - this.m.random() * 20.0f));
                    this.vz = (int)(this.ctmag * (10.0f - this.m.random() * 20.0f));
                    this.vy = (int)(this.ctmag * (10.0f - this.m.random() * 20.0f));
                }
                this.chip = 2;
            }
            final int[] array16 = new int[3];
            final int[] array17 = new int[3];
            final int[] array18 = new int[3];
            int n26 = 0;
            do {
                array16[n26] = this.cox[n26] + n;
                array18[n26] = this.coy[n26] + n2;
                array17[n26] = this.coz[n26] + n3;
            } while (++n26 < 3);
            this.rot(array16, array18, n, n2, this.cxy, 3);
            this.rot(array18, array17, n2, n3, this.czy, 3);
            this.rot(array16, array17, n, n3, this.cxz, 3);
            int n27 = 0;
            do {
                final int[] array19 = array16;
                final int n28 = n27;
                array19[n28] += this.dx;
                final int[] array20 = array18;
                final int n29 = n27;
                array20[n29] += this.dy;
                final int[] array21 = array17;
                final int n30 = n27;
                array21[n30] += this.dz;
            } while (++n27 < 3);
            this.dx += this.vx;
            this.dz += this.vz;
            this.dy += this.vy;
            this.vy += 7;
            if (array18[0] > this.m.ground) {
                this.chip = 19;
            }
            this.rot(array16, array17, this.m.cx, this.m.cz, this.m.xz, 3);
            this.rot(array18, array17, this.m.cy, this.m.cz, this.m.zy, 3);
            final int[] array22 = new int[3];
            final int[] array23 = new int[3];
            int n31 = 0;
            do {
                array22[n31] = this.xs(array16[n31], array17[n31]);
                array23[n31] = this.ys(array18[n31], array17[n31]);
                if (array23[n31] < 45 && this.m.flex != 0) {
                    this.m.flex = 0;
                }
            } while (++n31 < 3);
            final int n32 = (int)(this.m.random() * 3.0f);
            if (this.bfase != -7) {
                if (n32 == 0) {
                    graphics.setColor(new Color(this.c[0], this.c[1], this.c[2]).darker());
                }
                if (n32 == 1) {
                    graphics.setColor(new Color(this.c[0], this.c[1], this.c[2]));
                }
                if (n32 == 2) {
                    graphics.setColor(new Color(this.c[0], this.c[1], this.c[2]).brighter());
                }
            }
            else {
                graphics.setColor(Color.getHSBColor(this.hsb[0], this.hsb[1], this.hsb[2]));
            }
            graphics.fillPolygon(array22, array23, 3);
            ++this.chip;
            if (this.chip == 20) {
                this.chip = 0;
            }
        }
        this.rot(array, array3, n, n2, n4, this.n);
        this.rot(array3, array2, n2, n3, n5, this.n);
        this.rot(array, array2, n, n3, cxz, this.n);
        if (n4 != 0 || n5 != 0 || cxz != 0) {
            this.projf = 1.0f;
            int n33 = 0;
            do {
                int n34 = 0;
                do {
                    if (n34 != n33) {
                        this.projf *= (float)(Math.sqrt((array[n33] - array[n34]) * (array[n33] - array[n34]) + (array2[n33] - array2[n34]) * (array2[n33] - array2[n34])) / 100.0);
                    }
                } while (++n34 < 3);
            } while (++n33 < 3);
            this.projf /= 3.0f;
        }
        this.rot(array, array2, this.m.cx, this.m.cz, this.m.xz, this.n);
        boolean b4 = false;
        final int[] array24 = new int[this.n];
        final int[] array25 = new int[this.n];
        int n35 = 500;
        for (int n36 = 0; n36 < this.n; ++n36) {
            array24[n36] = this.xs(array[n36], array2[n36]);
            array25[n36] = this.ys(array3[n36], array2[n36]);
        }
        int n37 = 0;
        int n38 = 1;
        for (int n39 = 0; n39 < this.n; ++n39) {
            for (int n40 = 0; n40 < this.n; ++n40) {
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
        if (this.spy(array[n37], array2[n37]) > this.spy(array[n38], array2[n38])) {
            b4 = true;
            int n42 = 0;
            for (int n43 = 0; n43 < this.n; ++n43) {
                if (array2[n43] < 50 && array3[n43] > this.m.cy) {
                    b4 = false;
                }
                else if (array3[n43] == array3[0]) {
                    ++n42;
                }
            }
            if (n42 == this.n && array3[0] > this.m.cy) {
                b4 = false;
            }
        }
        this.rot(array3, array2, this.m.cy, this.m.cz, this.m.zy, this.n);
        int n44 = 1;
        final int[] array26 = new int[this.n];
        final int[] array27 = new int[this.n];
        int n45 = 0;
        int n46 = 0;
        int n47 = 0;
        int n48 = 0;
        int n49 = 0;
        for (int n50 = 0; n50 < this.n; ++n50) {
            array26[n50] = this.xs(array[n50], array2[n50]);
            array27[n50] = this.ys(array3[n50], array2[n50]);
            if (array27[n50] < 0 || array2[n50] < 10) {
                ++n45;
            }
            if (array27[n50] > this.m.h || array2[n50] < 10) {
                ++n46;
            }
            if (array26[n50] < 0 || array2[n50] < 10) {
                ++n47;
            }
            if (array26[n50] > this.m.w || array2[n50] < 10) {
                ++n48;
            }
            if (array27[n50] < 45 && this.m.flex != 0) {
                this.m.flex = 0;
            }
            if (array2[n50] < 10) {
                ++n49;
            }
        }
        if (n47 == this.n || n45 == this.n || n46 == this.n || n48 == this.n) {
            n44 = 0;
        }
        if (this.m.trk && (n47 != 0 || n45 != 0 || n46 != 0 || n48 != 0)) {
            n44 = 0;
        }
        if (n49 != 0) {
            b = true;
        }
        if (n44 != 0) {
            int n51 = 0;
            int n52 = 0;
            int n53 = 0;
            int n54 = 0;
            int n55 = 0;
            int n56 = 0;
            for (int n57 = 0; n57 < this.n; ++n57) {
                int n58 = 0;
                int n59 = 0;
                int n60 = 0;
                int n61 = 0;
                int n62 = 0;
                int n63 = 0;
                for (int n64 = 0; n64 < this.n; ++n64) {
                    if (array3[n57] >= array3[n64]) {
                        ++n58;
                    }
                    if (array3[n57] <= array3[n64]) {
                        ++n59;
                    }
                    if (array[n57] >= array[n64]) {
                        ++n60;
                    }
                    if (array[n57] <= array[n64]) {
                        ++n61;
                    }
                    if (array2[n57] >= array2[n64]) {
                        ++n62;
                    }
                    if (array2[n57] <= array2[n64]) {
                        ++n63;
                    }
                }
                if (n58 == this.n) {
                    n51 = array3[n57];
                }
                if (n59 == this.n) {
                    n52 = array3[n57];
                }
                if (n60 == this.n) {
                    n53 = array[n57];
                }
                if (n61 == this.n) {
                    n54 = array[n57];
                }
                if (n62 == this.n) {
                    n55 = array2[n57];
                }
                if (n63 == this.n) {
                    n56 = array2[n57];
                }
            }
            final int n65 = (n51 + n52) / 2;
            final int n66 = (n53 + n54) / 2;
            final int n67 = (n55 + n56) / 2;
            this.av = (int)Math.sqrt((this.m.cy - n65) * (this.m.cy - n65) + (this.m.cx - n66) * (this.m.cx - n66) + n67 * n67 + Math.abs(this.gr * this.gr * this.gr));
            if (!this.m.trk && (this.av > this.m.fade[this.disline] || this.av == 0)) {
                n44 = 0;
            }
            if (this.gr > 0 && this.av > 3500) {
                n44 = 0;
            }
            if (this.av > 3000 && this.m.adv <= 900) {
                b = true;
            }
            if (this.flx != 0 && this.m.random() > 0.3) {
                n44 = 0;
            }
        }
        if (n44 != 0) {
            float n68 = (float)(this.projf / this.deltaf + 0.3);
            if (b) {
                boolean b5 = false;
                if (n68 > 1.0f) {
                    if (n68 >= 1.27) {
                        b5 = true;
                    }
                    n68 = 1.0f;
                }
                if (b5) {
                    n68 *= (float)0.89;
                }
                else {
                    n68 *= (float)0.86;
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
            }
            else {
                if (n68 > 1.0f) {
                    n68 = 1.0f;
                }
                if (n68 < 0.6 || b4) {
                    n68 = 0.6f;
                }
            }
            Color color;
            if (!this.m.trk) {
                color = Color.getHSBColor(this.hsb[0], this.hsb[1], this.hsb[2] * n68);
            }
            else {
                final float[] hsbvals = new float[3];
                Color.RGBtoHSB(this.oc[0], this.oc[1], this.oc[2], hsbvals);
                color = Color.getHSBColor(0.105f, 0.95f, hsbvals[2] * n68 + 0.1f);
            }
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            if (!this.m.trk) {
                int n69 = 0;
                do {
                    if (this.av > this.m.fade[n69]) {
                        red = (red * 3 + this.m.cfade[0]) / 4;
                        green = (green * 3 + this.m.cfade[1]) / 4;
                        blue = (blue * 3 + this.m.cfade[2]) / 4;
                    }
                } while (++n69 < 8);
            }
            graphics.setColor(new Color(red, green, blue));
            graphics.fillPolygon(array26, array27, this.n);
            if (!b) {
                if (this.flx == 0) {
                    graphics.setColor(new Color(0, 0, 0));
                    graphics.drawPolygon(array26, array27, this.n);
                }
                else {
                    if (this.flx == 2) {
                        graphics.setColor(new Color(0, 0, 0));
                        graphics.drawPolygon(array26, array27, this.n);
                    }
                    if (this.flx == 1) {
                        final int r3 = 0;
                        int g3 = (int)(223.0f + 223.0f * (this.m.snap[1] / 100.0f));
                        if (g3 > 255) {
                            g3 = 255;
                        }
                        if (g3 < 0) {
                            g3 = 0;
                        }
                        int b6 = (int)(255.0f + 255.0f * (this.m.snap[2] / 100.0f));
                        if (b6 > 255) {
                            b6 = 255;
                        }
                        if (b6 < 0) {
                            b6 = 0;
                        }
                        graphics.setColor(new Color(r3, g3, b6));
                        graphics.drawPolygon(array26, array27, this.n);
                        this.flx = 2;
                    }
                    if (this.flx == 3) {
                        final int r4 = 0;
                        int g4 = (int)(255.0f + 255.0f * (this.m.snap[1] / 100.0f));
                        if (g4 > 255) {
                            g4 = 255;
                        }
                        if (g4 < 0) {
                            g4 = 0;
                        }
                        int b7 = (int)(223.0f + 223.0f * (this.m.snap[2] / 100.0f));
                        if (b7 > 255) {
                            b7 = 255;
                        }
                        if (b7 < 0) {
                            b7 = 0;
                        }
                        graphics.setColor(new Color(r4, g4, b7));
                        graphics.drawPolygon(array26, array27, this.n);
                        this.flx = 2;
                    }
                }
            }
            else if (this.av <= 3000 && !this.m.trk && this.road && this.m.fade[0] > 4000) {
                red -= 10;
                if (red < 0) {
                    red = 0;
                }
                green -= 10;
                if (green < 0) {
                    green = 0;
                }
                blue -= 10;
                if (blue < 0) {
                    blue = 0;
                }
                graphics.setColor(new Color(red, green, blue));
                graphics.drawPolygon(array26, array27, this.n);
            }
            if (!this.glass && this.gr == -10 && !this.m.trk) {
                int r5 = this.c[0];
                int g5 = this.c[1];
                int b8 = this.c[2];
                int n70 = 0;
                do {
                    if (this.av > this.m.fade[n70]) {
                        r5 = (r5 * 3 + this.m.cfade[0]) / 4;
                        g5 = (g5 * 3 + this.m.cfade[1]) / 4;
                        b8 = (b8 * 3 + this.m.cfade[2]) / 4;
                    }
                } while (++n70 < 8);
                graphics.setColor(new Color(r5, g5, b8));
                graphics.drawPolygon(array26, array27, this.n);
            }
        }
    }
    
    public void rot(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        if (n3 != 0) {
            for (int i = 0; i < n4; ++i) {
                final int n5 = array[i];
                final int n6 = array2[i];
                array[i] = n + (int)((n5 - n) * this.m.cos(n3) - (n6 - n2) * this.m.sin(n3));
                array2[i] = n2 + (int)((n5 - n) * this.m.sin(n3) + (n6 - n2) * this.m.cos(n3));
            }
        }
    }
    
    public int xs(final int n, int cz) {
        if (cz < this.m.cz) {
            cz = this.m.cz;
        }
        return (cz - this.m.focus_point) * (this.m.cx - n) / cz + n;
    }
    
    public void s(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        if (this.gr <= 0) {
            final int[] array = new int[this.n];
            final int[] array2 = new int[this.n];
            final int[] array3 = new int[this.n];
            for (int i = 0; i < this.n; ++i) {
                array[i] = this.ox[i] + n;
                array3[i] = this.oy[i] + n2;
                array2[i] = this.oz[i] + n3;
            }
            this.rot(array, array3, n, n2, n5, this.n);
            this.rot(array3, array2, n2, n3, n6, this.n);
            this.rot(array, array2, n, n3, n4, this.n);
            int r = (int)((float)this.m.cgrnd[0] / 1.5);
            int g = (int)((float)this.m.cgrnd[1] / 1.5);
            int b = (int)((float)this.m.cgrnd[2] / 1.5);
            for (int j = 0; j < this.n; ++j) {
                array3[j] = this.m.ground;
            }
            if (n7 == 0) {
                for (int k = this.t.nt - 1; k >= 0; --k) {
                    int n8 = 0;
                    for (int l = 0; l < this.n; ++l) {
                        if (Math.abs(this.t.zy[k]) != 90 && Math.abs(this.t.xy[k]) != 90 && Math.abs(array[l] - (this.t.x[k] - this.m.x)) < this.t.radx[k] && Math.abs(array2[l] - (this.t.z[k] - this.m.z)) < this.t.radz[k]) {
                            ++n8;
                        }
                    }
                    if (n8 > this.n / 2) {
                        for (int n9 = 0; n9 < this.n; ++n9) {
                            array3[n9] = this.t.y[k] - this.m.y;
                            if (this.t.zy[k] != 0) {
                                final int[] array4 = array3;
                                final int n10 = n9;
                                array4[n10] += (int)((array2[n9] - (this.t.z[k] - this.m.z - this.t.radz[k])) * this.m.sin(this.t.zy[k]) / this.m.sin(90 - this.t.zy[k]) - this.t.radz[k] * this.m.sin(this.t.zy[k]) / this.m.sin(90 - this.t.zy[k]));
                            }
                            if (this.t.xy[k] != 0) {
                                final int[] array5 = array3;
                                final int n11 = n9;
                                array5[n11] += (int)((array[n9] - (this.t.x[k] - this.m.x - this.t.radx[k])) * this.m.sin(this.t.xy[k]) / this.m.sin(90 - this.t.xy[k]) - this.t.radx[k] * this.m.sin(this.t.xy[k]) / this.m.sin(90 - this.t.xy[k]));
                            }
                        }
                        r = (int)((float)this.t.c[k][0] / 1.5);
                        g = (int)((float)this.t.c[k][1] / 1.5);
                        b = (int)((float)this.t.c[k][2] / 1.5);
                        break;
                    }
                }
            }
            int n12 = 1;
            final int[] array6 = new int[this.n];
            final int[] array7 = new int[this.n];
            if (n7 == 2) {
                r = 76;
                g = 98;
                b = 142;
            }
            else {
                for (int n13 = 0; n13 < this.m.nsp; ++n13) {
                    for (int n14 = 0; n14 < this.n; ++n14) {
                        if (Math.abs(array[n14] - this.m.spx[n13]) < this.m.sprad[n13] && Math.abs(array2[n14] - this.m.spz[n13]) < this.m.sprad[n13]) {
                            n12 = 0;
                        }
                    }
                }
            }
            if (n12 != 0) {
                this.rot(array, array2, this.m.cx, this.m.cz, this.m.xz, this.n);
                this.rot(array3, array2, this.m.cy, this.m.cz, this.m.zy, this.n);
                int n15 = 0;
                int n16 = 0;
                int n17 = 0;
                int n18 = 0;
                for (int n19 = 0; n19 < this.n; ++n19) {
                    array6[n19] = this.xs(array[n19], array2[n19]);
                    array7[n19] = this.ys(array3[n19], array2[n19]);
                    if (array7[n19] < 0 || array2[n19] < 10) {
                        ++n15;
                    }
                    if (array7[n19] > this.m.h || array2[n19] < 10) {
                        ++n16;
                    }
                    if (array6[n19] < 0 || array2[n19] < 10) {
                        ++n17;
                    }
                    if (array6[n19] > this.m.w || array2[n19] < 10) {
                        ++n18;
                    }
                }
                if (n17 == this.n || n15 == this.n || n16 == this.n || n18 == this.n) {
                    n12 = 0;
                }
            }
            if (n12 != 0) {
                int n20 = 0;
                do {
                    if (this.av > this.m.fade[n20]) {
                        r = (r * 3 + this.m.cfade[0]) / 4;
                        g = (g * 3 + this.m.cfade[1]) / 4;
                        b = (b * 3 + this.m.cfade[2]) / 4;
                    }
                } while (++n20 < 8);
                graphics.setColor(new Color(r, g, b));
                graphics.fillPolygon(array6, array7, this.n);
            }
        }
    }
    
    public int spy(final int n, final int n2) {
        return (int)Math.sqrt((n - this.m.cx) * (n - this.m.cx) + n2 * n2);
    }
}
