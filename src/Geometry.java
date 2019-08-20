import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.awt.Color;
import java.awt.Graphics;

//
// Decompiled by Procyon v0.5.36
//

public class Geometry
{
    Medium m;
    Trackers t;
    Plane[] p;
    int npl;
    int x;
    int y;
    int z;
    int xz;
    int xy;
    int zy;
    int wxz;
    int wzy;
    int dist;
    int maxR;
    int disp;
    int disline;
    boolean shadow;
    boolean noline;
    float grounded;
    int grat;
    int[] keyx;
    int[] keyz;
    int[] txy;
    int[] tzy;
    int[][] tc;
    int[] tradx;
    int[] tradz;
    int[] trady;
    int[] tx;
    int[] ty;
    int[] tz;
    int[] skd;
    int[] dam;
    int tnt;
    int[] sx;
    int[] sy;
    int[] sz;
    int[] stg;
    int[] dov;
    float[] smag;
    int[] scx;
    int[] scz;
    boolean[] fulls;
    boolean elec;
    boolean roted;
    int[] edl;
    int[] edr;
    int[] elc;
    boolean fix;
    int fcnt;

    public void pdust(final int n, final Graphics graphics, final int n2) {
        if (n2 * this.dov[n] > 0) {
            int n3;
            if (this.fulls[n]) {
                n3 = this.stg[n] * this.stg[n];
            }
            else {
                n3 = this.stg[n] * this.stg[n] * this.stg[n] + 1;
            }
            int r = (this.m.cgrnd[0] * n3 + this.m.cfade[0] * 2 + this.m.csky[0]) / (3 + n3);
            int g = (this.m.cgrnd[1] * n3 + this.m.cfade[0] * 2 + this.m.csky[1]) / (3 + n3);
            int b = (this.m.cgrnd[2] * n3 + this.m.cfade[0] * 2 + this.m.csky[2]) / (3 + n3);
            for (int i = 0; i < this.t.nt; ++i) {
                if (Math.abs(this.t.zy[i]) != 90 && Math.abs(this.t.xy[i]) != 90 && Math.abs(this.sx[n] - this.t.x[i]) < this.t.radx[i] && Math.abs(this.sz[n] - this.t.z[i]) < this.t.radz[i]) {
                    if (this.t.skd[i] == 0) {
                        n3 = this.stg[n] * this.stg[n] * this.stg[n] + 2;
                    }
                    r = (int)((this.t.c[i][0] * 0.87 * n3 + this.m.cfade[0] * 2 + this.m.csky[0]) / (3 + n3));
                    g = (int)((this.t.c[i][1] * 0.87 * n3 + this.m.cfade[0] * 2 + this.m.csky[1]) / (3 + n3));
                    b = (int)((this.t.c[i][2] * 0.87 * n3 + this.m.cfade[0] * 2 + this.m.csky[2]) / (3 + n3));
                }
            }
            if (this.sy[n] > 250) {
                this.sy[n] = 250;
            }
            final int n4 = this.sy[n];
            final float n5 = this.m.cx + (int)((this.sx[n] - this.m.x - this.m.cx) * this.m.cos(this.m.xz) - (this.sz[n] - this.m.z - this.m.cz) * this.m.sin(this.m.xz));
            final float n6 = this.m.cz + (int)((this.sx[n] - this.m.x - this.m.cx) * this.m.sin(this.m.xz) + (this.sz[n] - this.m.z - this.m.cz) * this.m.cos(this.m.xz));
            final float n7 = this.m.cy + (int)((this.sy[n] - this.m.y - this.m.cy) * this.m.cos(this.m.zy) - (n6 - this.m.cz) * this.m.sin(this.m.zy));
            final float n8 = this.m.cz + (int)((this.sy[n] - this.m.y - this.m.cy) * this.m.sin(this.m.zy) + (n6 - this.m.cz) * this.m.cos(this.m.zy));
            final float n9 = (int)Math.sqrt((this.m.cy - n7) * (this.m.cy - n7) + (this.m.cx - n5) * (this.m.cx - n5) + n8 * n8);
            int n10 = 0;
            do {
                if (n9 > this.m.fade[n10]) {
                    r = (r * 3 + this.m.cfade[0]) / 4;
                    g = (g * 3 + this.m.cfade[1]) / 4;
                    b = (b * 3 + this.m.cfade[2]) / 4;
                }
            } while (++n10 < 8);
            if (Math.abs(this.scx[n]) + Math.abs(this.scz[n]) > 150) {
                final int[] sy = this.sy;
                sy[n] -= (int)(3.0f + 27.0f * this.smag[n]);
            }
            else {
                final int[] sy2 = this.sy;
                sy2[n] -= (int)(23.0f + 7.0f * this.smag[n]);
            }
            final int[] sx = this.sx;
            sx[n] += (int)(this.scx[n] / ((this.stg[n] + 1) * this.smag[n]));
            final int[] sz = this.sz;
            sz[n] += (int)(this.scz[n] / ((this.stg[n] + 1) * this.smag[n]));
            final int[] array = new int[8];
            final int[] array2 = new int[8];
            final int n11 = this.stg[n] - 3;
            array[0] = this.xs((int)(n5 - (18.0f + this.m.random() * 18.0f + n11 * 6) * this.smag[n]), (int)n8);
            array2[0] = this.ys((int)(n7 - (7.5 + this.m.random() * 7.5 + n11 * 2.5) * this.smag[n]), (int)n8);
            if (array2[0] < 45 && this.m.flex != 0) {
                this.m.flex = 0;
            }
            array[1] = this.xs((int)(n5 - (18.0f + this.m.random() * 18.0f + n11 * 6) * this.smag[n]), (int)n8);
            array2[1] = this.ys((int)(n7 + (7.5 + this.m.random() * 7.5 + n11 * 2.5) * this.smag[n]), (int)n8);
            array[2] = this.xs((int)(n5 - (7.5 + this.m.random() * 7.5 + n11 * 2.5) * this.smag[n]), (int)n8);
            array2[2] = this.ys((int)(n7 + (18.0f + this.m.random() * 18.0f + n11 * 6) * this.smag[n]), (int)n8);
            array[3] = this.xs((int)(n5 + (7.5 + this.m.random() * 7.5 + n11 * 2.5) * this.smag[n]), (int)n8);
            array2[3] = this.ys((int)(n7 + (18.0f + this.m.random() * 18.0f + n11 * 6) * this.smag[n]), (int)n8);
            array[4] = this.xs((int)(n5 + (18.0f + this.m.random() * 18.0f + n11 * 6) * this.smag[n]), (int)n8);
            array2[4] = this.ys((int)(n7 + (7.5 + this.m.random() * 7.5 + n11 * 2.5) * this.smag[n]), (int)n8);
            array[5] = this.xs((int)(n5 + (18.0f + this.m.random() * 18.0f + n11 * 6) * this.smag[n]), (int)n8);
            array2[5] = this.ys((int)(n7 - (7.5 + this.m.random() * 7.5 + n11 * 2.5) * this.smag[n]), (int)n8);
            array[6] = this.xs((int)(n5 + (7.5 + this.m.random() * 7.5 + n11 * 2.5) * this.smag[n]), (int)n8);
            array2[6] = this.ys((int)(n7 - (18.0f + this.m.random() * 18.0f + n11 * 6) * this.smag[n]), (int)n8);
            array[7] = this.xs((int)(n5 - (7.5 + this.m.random() * 7.5 + n11 * 2.5) * this.smag[n]), (int)n8);
            array2[7] = this.ys((int)(n7 - (18.0f + this.m.random() * 18.0f + n11 * 6) * this.smag[n]), (int)n8);
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
                if (array2[n16] > this.m.h || n8 < 10) {
                    ++n13;
                }
                if (array[n16] < 0 || n8 < 10) {
                    ++n14;
                }
                if (array[n16] > this.m.w || n8 < 10) {
                    ++n15;
                }
                if (array2[n16] < 45 && this.m.flex != 0) {
                    this.m.flex = 0;
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
            }
            else {
                final int[] stg = this.stg;
                ++stg[n];
                if (this.stg[n] == 2 && this.fulls[n]) {
                    this.dov[n] = 0;
                }
            }
        }
        else if (this.dov[n] == 0) {
            this.dov[n] = 1;
        }
    }

    public int ys(final int n, int n2) {
        if (n2 < 50) {
            n2 = 50;
        }
        return (int)((n2 - this.m.focus_point) * (this.m.cy - n) / n2 + n);
    }

    public float ys(final float n, float n2) {
        if (n2 < 50) {
            n2 = 50;
        }
        return ((n2 - this.m.focus_point) * (this.m.cy - n) / n2 + n);
    }

    public Geometry(final byte[] array, final Medium m, final Trackers t) {
        this.npl = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.xz = 0;
        this.xy = 0;
        this.zy = 0;
        this.wxz = 0;
        this.wzy = 0;
        this.dist = 0;
        this.maxR = 0;
        this.disp = 0;
        this.disline = 7;
        this.shadow = false;
        this.noline = false;
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
        this.m = m;
        this.t = t;
        this.p = new Plane[120];
        String string = "";
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        float n4 = 1.0f;
        final int[] array2 = new int[100];
        final int[] array3 = new int[100];
        final int[] array4 = new int[100];
        final int[] array5 = new int[3];
        boolean b = false;
        boolean b2 = false;
        final Wheels wheels = new Wheels();
        int n5 = 0;
        int getvalue = 1;
        try {
            final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(array));
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
                        b = false;
                        array5[0] = this.getvalue("c", string, 0);
                        array5[1] = this.getvalue("c", string, 1);
                        array5[2] = this.getvalue("c", string, 2);
                    }
                    if (string.startsWith("glass")) {
                        b = true;
                    }
                    if (string.startsWith("p")) {
                        array2[n3] = (int)(this.getvalue("p", string, 0) * n4);
                        array3[n3] = (int)(this.getvalue("p", string, 1) * n4);
                        array4[n3] = (int)(this.getvalue("p", string, 2) * n4);
                        final int maxR = (int)Math.sqrt(array2[n3] * array2[n3] + array3[n3] * array3[n3] + array4[n3] * array4[n3]);
                        if (maxR > this.maxR) {
                            this.maxR = maxR;
                        }
                        ++n3;
                    }
                }
                if (string.startsWith("</p>")) {
                    this.p[this.npl] = new Plane(this.m, this.t, array2, array4, array3, n3, array5, b, getvalue, 0, 0, 0, this.disline, 0, b2);
                    ++this.npl;
                    n = 0;
                }
                if (string.startsWith("w")) {
                    this.keyx[n5] = (int)(this.getvalue("w", string, 0) * n4);
                    this.keyz[n5] = (int)(this.getvalue("w", string, 2) * n4);
                    ++n5;
                    wheels.make(this.m, this.t, this.p, this.npl, (int)(this.getvalue("w", string, 0) * n4), (int)(this.getvalue("w", string, 1) * n4), (int)(this.getvalue("w", string, 2) * n4), this.getvalue("w", string, 3), (int)(this.getvalue("w", string, 4) * n4), (int)(this.getvalue("w", string, 5) * n4));
                    this.npl += 9;
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
                        this.tradx[this.tnt] = (int)(this.getvalue("radx", string, 0) * n4);
                    }
                    if (string.startsWith("rady")) {
                        this.trady[this.tnt] = (int)(this.getvalue("rady", string, 0) * n4);
                    }
                    if (string.startsWith("radz")) {
                        this.tradz[this.tnt] = (int)(this.getvalue("radz", string, 0) * n4);
                    }
                    if (string.startsWith("ty")) {
                        this.ty[this.tnt] = (int)(this.getvalue("ty", string, 0) * n4);
                    }
                    if (string.startsWith("tx")) {
                        this.tx[this.tnt] = (int)(this.getvalue("tx", string, 0) * n4);
                    }
                    if (string.startsWith("tz")) {
                        this.tz[this.tnt] = (int)(this.getvalue("tz", string, 0) * n4);
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
                    this.noline = true;
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
        }
        catch (Exception obj) {
            System.out.println("ContO Loading Error: " + obj);
            System.out.println("At File: " + array + ".rad");
            System.out.println("At Line: " + string);
            System.out.println("--------------------");
        }
        this.grat = wheels.ground;
    }

    public Geometry(final Geometry geometry, final int x, final int y, final int z, final int a) {
        this.npl = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.xz = 0;
        this.xy = 0;
        this.zy = 0;
        this.wxz = 0;
        this.wzy = 0;
        this.dist = 0;
        this.maxR = 0;
        this.disp = 0;
        this.disline = 7;
        this.shadow = false;
        this.noline = false;
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
        this.m = geometry.m;
        this.t = geometry.t;
        this.npl = geometry.npl;
        this.maxR = geometry.maxR;
        this.disp = geometry.disp;
        this.disline = geometry.disline;
        this.noline = geometry.noline;
        this.shadow = geometry.shadow;
        this.grounded = geometry.grounded;
        this.grat = geometry.grat;
        this.p = new Plane[geometry.npl];
        for (int i = 0; i < this.npl; ++i) {
            this.p[i] = new Plane(this.m, this.t, geometry.p[i].ox, geometry.p[i].oz, geometry.p[i].oy, geometry.p[i].n, geometry.p[i].oc, geometry.p[i].glass, geometry.p[i].gr, geometry.p[i].wx, geometry.p[i].wy, geometry.p[i].wz, geometry.disline, geometry.p[i].bfase, geometry.p[i].road);
        }
        this.x = x;
        this.y = y;
        this.z = z;
        this.xz = 0;
        this.xy = 0;
        this.zy = 0;
        for (int j = 0; j < this.npl; ++j) {
            this.p[j].master = geometry.p[j].master;
            this.p[j].rot(this.p[j].ox, this.p[j].oz, 0, 0, a, this.p[j].n);
            this.p[j].loadprojf();
        }
        if (geometry.tnt != 0) {
            for (int k = 0; k < geometry.tnt; ++k) {
                this.t.xy[this.t.nt] = (int)(geometry.txy[k] * this.m.cos(a) - geometry.tzy[k] * this.m.sin(a));
                this.t.zy[this.t.nt] = (int)(geometry.tzy[k] * this.m.cos(a) + geometry.txy[k] * this.m.sin(a));
                int n = 0;
                do {
                    this.t.c[this.t.nt][n] = (int)(geometry.tc[k][n] + geometry.tc[k][n] * (this.m.snap[n] / 100.0f));
                    if (this.t.c[this.t.nt][n] > 255) {
                        this.t.c[this.t.nt][n] = 255;
                    }
                    if (this.t.c[this.t.nt][n] < 0) {
                        this.t.c[this.t.nt][n] = 0;
                    }
                } while (++n < 3);
                this.t.x[this.t.nt] = (int)(this.x + geometry.tx[k] * this.m.cos(a) - geometry.tz[k] * this.m.sin(a));
                this.t.z[this.t.nt] = (int)(this.z + geometry.tz[k] * this.m.cos(a) + geometry.tx[k] * this.m.sin(a));
                this.t.y[this.t.nt] = this.y + geometry.ty[k];
                this.t.skd[this.t.nt] = geometry.skd[k];
                this.t.dam[this.t.nt] = geometry.dam[k];
                int abs = Math.abs(a);
                if (abs == 180) {
                    abs = 0;
                }
                this.t.radx[this.t.nt] = (int)(geometry.tradx[k] * this.m.cos(abs) + geometry.tradz[k] * this.m.sin(abs));
                this.t.radz[this.t.nt] = (int)(geometry.tradx[k] * this.m.sin(abs) + geometry.tradz[k] * this.m.cos(abs));
                this.t.rady[this.t.nt] = geometry.trady[k];
                final Trackers t = this.t;
                ++t.nt;
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
        if (this.dist != 0) {
            this.dist = 0;
        }
        final float n = this.m.cx + (int)((this.x - this.m.x - this.m.cx) * this.m.cos(this.m.xz) - (this.z - this.m.z - this.m.cz) * this.m.sin(this.m.xz));
        final float n2 = this.m.cz + (int)((this.x - this.m.x - this.m.cx) * this.m.sin(this.m.xz) + (this.z - this.m.z - this.m.cz) * this.m.cos(this.m.xz));
        final float n3 = this.m.cz + (int)((this.y - this.m.y - this.m.cy) * this.m.sin(this.m.zy) + (n2 - this.m.cz) * this.m.cos(this.m.zy));
        if (this.xs(n + this.maxR, n3) > 0 && this.xs(n - this.maxR, n3) < this.m.w && n3 > -this.maxR && (n3 < this.m.fade[this.disline] + this.maxR || this.m.trk) && (this.xs(n + this.maxR, n3) - this.xs(n - this.maxR, n3) > this.disp || this.m.trk)) {
            if (this.shadow) {
                if (!this.m.crs) {
                    if (n3 < 2000) {
                        boolean b = false;
                        for (int i = this.t.nt - 1; i >= 0; --i) {
                            if (Math.abs(this.t.zy[i]) != 90 && Math.abs(this.t.xy[i]) != 90 && Math.abs(this.x - this.t.x[i]) < this.t.radx[i] + this.maxR && Math.abs(this.z - this.t.z[i]) < this.t.radz[i] + this.maxR) {
                                b = true;
                                break;
                            }
                        }
                        if (b) {
                            for (int j = 0; j < this.npl; ++j) {
                                this.p[j].s(graphics, this.x - this.m.x, this.y - this.m.y, this.z - this.m.z, this.xz, this.xy, this.zy, 0);
                            }
                        }
                        else {
                            final float n4 = this.m.cy + (int)((this.m.ground - this.m.cy) * this.m.cos(this.m.zy) - (n2 - this.m.cz) * this.m.sin(this.m.zy));
                            final float n5 = this.m.cz + (int)((this.m.ground - this.m.cy) * this.m.sin(this.m.zy) + (n2 - this.m.cz) * this.m.cos(this.m.zy));
                            if (this.ys(n4 + this.maxR, n5) > 0 && this.ys(n4 - this.maxR, n5) < this.m.h) {
                                for (int k = 0; k < this.npl; ++k) {
                                    this.p[k].s(graphics, this.x - this.m.x, this.y - this.m.y, this.z - this.m.z, this.xz, this.xy, this.zy, 1);
                                }
                            }
                        }
                        this.m.addsp(this.x - this.m.x, this.z - this.m.z, (int)(this.maxR * 0.8));
                    }
                    else {
                        this.lowshadow(graphics, (int)n3);
                    }
                }
                else {
                    for (int l = 0; l < this.npl; ++l) {
                        this.p[l].s(graphics, this.x - this.m.x, this.y - this.m.y, this.z - this.m.z, this.xz, this.xy, this.zy, 2);
                    }
                }
            }
            final float n6 = this.m.cy + (int)((this.y - this.m.y - this.m.cy) * this.m.cos(this.m.zy) - (n2 - this.m.cz) * this.m.sin(this.m.zy));
            if (this.ys(n6 + this.maxR, n3) > 0 && this.ys(n6 - this.maxR, n3) < this.m.h) {
                if (this.elec) {
                    this.electrify(graphics);
                }
                if (this.fix) {
                    this.fixit(graphics);
                }
                final int[] array = new int[this.npl];
                int n7 = 0;
                do {
                    if (this.stg[n7] != 0) {
                        this.pdust(n7, graphics, -1);
                    }
                } while (++n7 < 4);
                for (int n8 = 0; n8 < this.npl; ++n8) {
                    array[n8] = 0;
                }
                for (int n9 = 0; n9 < this.npl; ++n9) {
                    for (int n10 = n9 + 1; n10 < this.npl; ++n10) {
                        if (this.p[n9].av != this.p[n10].av) {
                            if (this.p[n9].av < this.p[n10].av) {
                                final int[] array2 = array;
                                final int n11 = n9;
                                ++array2[n11];
                            }
                            else {
                                final int[] array3 = array;
                                final int n12 = n10;
                                ++array3[n12];
                            }
                        }
                        else if (n9 > n10) {
                            final int[] array4 = array;
                            final int n13 = n9;
                            ++array4[n13];
                        }
                        else {
                            final int[] array5 = array;
                            final int n14 = n10;
                            ++array5[n14];
                        }
                    }
                }
                for (int n15 = 0; n15 < this.npl; ++n15) {
                    for (int n16 = 0; n16 < this.npl; ++n16) {
                        if (array[n16] == n15) {
                            this.p[n16].d(graphics, this.x - this.m.x, this.y - this.m.y, this.z - this.m.z, this.xz, this.xy, this.zy, this.wxz, this.wzy, this.noline);
                            if (this.p[n16].master != 0 && this.stg[this.p[n16].master - 1] != 0) {
                                this.pdust(this.p[n16].master - 1, graphics, 1);
                            }
                        }
                    }
                }
                this.dist = (int)(Math.sqrt((int)Math.sqrt((this.m.x + this.m.cx - this.x) * (this.m.x + this.m.cx - this.x) + (this.m.z - this.z) * (this.m.z - this.z) + (this.m.y + this.m.cy - this.y) * (this.m.y + this.m.cy - this.y))) * this.grounded);
            }
        }
        if (this.dist == 0) {
            int n17 = 0;
            do {
                if (this.stg[n17] != 0) {
                    if (this.stg[n17] == 4) {
                        this.stg[n17] = 0;
                    }
                    else {
                        final int[] stg = this.stg;
                        final int n18 = n17;
                        ++stg[n18];
                    }
                }
            } while (++n17 < 4);
        }
    }

    public int getpy(final int n, final int n2, final int n3) {
        return (n - this.x) / 10 * ((n - this.x) / 10) + (n2 - this.y) / 10 * ((n2 - this.y) / 10) + (n3 - this.z) / 10 * ((n3 - this.z) / 10);
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

    public void rot(final int[] array, final int[] array2, final int n, final int n2, final float n3, final int n4) {
        if (n3 != 0) {
            for (int i = 0; i < n4; ++i) {
                final int n5 = array[i];
                final int n6 = array2[i];
                array[i] = n + (int)((n5 - n) * this.m.cos(n3) - (n6 - n2) * this.m.sin(n3));
                array2[i] = n2 + (int)((n5 - n) * this.m.sin(n3) + (n6 - n2) * this.m.cos(n3));
            }
        }
    }

    public void rot(final int[] array, final int[] array2, final float n, final float n2, final float n3, final int n4) {
        if (n3 != 0) {
            for (int i = 0; i < n4; ++i) {
                final int n5 = array[i];
                final int n6 = array2[i];
                array[i] = (int)(n + (n5 - n) * this.m.cos(n3) - (n6 - n2) * this.m.sin(n3));
                array2[i] = (int)(n2 + (n5 - n) * this.m.sin(n3) + (n6 - n2) * this.m.cos(n3));
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
            this.sx[n] = (int)n2;
            this.sy[n] = (int)n3;
            this.sz[n] = (int)n4;
            this.stg[n] = 1;
            this.dov[n] = -1;
            this.smag[n] = n5;
            this.scx[n] = (int)a;
            this.scz[n] = (int)a2;
            this.fulls[n] = b;
        }
    }

    public int getvalue(final String s, final String s2, final int n) {
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

    public int xs(final int n, int n2) {
        if (n2 < 50) {
            n2 = 50;
        }
        return (int)((n2 - this.m.focus_point) * (this.m.cx - n) / n2 + n);
    }

    public float xs(final float n, float n2) {
        if (n2 < 50) {
            n2 = 50;
        }
        return (n2 - this.m.focus_point) * (this.m.cx - n) / n2 + n;
    }

    public void lowshadow(final Graphics graphics, final int n) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        final int[] array3 = new int[4];
        int n2 = 1;
        int i;
        for (i = Math.abs(this.zy); i > 270; i -= 360) {}
        if (Math.abs(i) > 90) {
            n2 = -1;
        }
        array[0] = (int)(this.keyx[0] * 1.2 + this.x - this.m.x);
        array3[0] = (int)(this.keyz[0] * n2 * 1.4 + this.z - this.m.z);
        array[1] = (int)(this.keyx[1] * 1.2 + this.x - this.m.x);
        array3[1] = (int)(this.keyz[1] * n2 * 1.4 + this.z - this.m.z);
        array[2] = (int)(this.keyx[3] * 1.2 + this.x - this.m.x);
        array3[2] = (int)(this.keyz[3] * n2 * 1.4 + this.z - this.m.z);
        array[3] = (int)(this.keyx[2] * 1.2 + this.x - this.m.x);
        array3[3] = (int)(this.keyz[2] * n2 * 1.4 + this.z - this.m.z);
        this.rot(array, array3, this.x - this.m.x, this.z - this.m.z, this.xz, 4);
        int r = (int)((float)this.m.cgrnd[0] / 1.5);
        int g = (int)((float)this.m.cgrnd[1] / 1.5);
        int b = (int)((float)this.m.cgrnd[2] / 1.5);
        int n3 = 0;
        do {
            array2[n3] = (int)this.m.ground;
        } while (++n3 < 4);
        for (int j = this.t.nt - 1; j >= 0; --j) {
            int n4 = 0;
            int n5 = 0;
            do {
                if (Math.abs(this.t.zy[j]) != 90 && Math.abs(this.t.xy[j]) != 90 && Math.abs(array[n5] - (this.t.x[j] - this.m.x)) < this.t.radx[j] && Math.abs(array3[n5] - (this.t.z[j] - this.m.z)) < this.t.radz[j]) {
                    ++n4;
                }
            } while (++n5 < 4);
            if (n4 > 2) {
                int n6 = 0;
                do {
                    array2[n6] = this.t.y[j] - this.m.y;
                    if (this.t.zy[j] != 0) {
                        final int[] array4 = array2;
                        final int n7 = n6;
                        array4[n7] += (int)((array3[n6] - (this.t.z[j] - this.m.z - this.t.radz[j])) * this.m.sin(this.t.zy[j]) / this.m.sin(90 - this.t.zy[j]) - this.t.radz[j] * this.m.sin(this.t.zy[j]) / this.m.sin(90 - this.t.zy[j]));
                    }
                    if (this.t.xy[j] != 0) {
                        final int[] array5 = array2;
                        final int n8 = n6;
                        array5[n8] += (int)((array[n6] - (this.t.x[j] - this.m.x - this.t.radx[j])) * this.m.sin(this.t.xy[j]) / this.m.sin(90 - this.t.xy[j]) - this.t.radx[j] * this.m.sin(this.t.xy[j]) / this.m.sin(90 - this.t.xy[j]));
                    }
                } while (++n6 < 4);
                r = (int)((float)this.t.c[j][0] / 1.5);
                g = (int)((float)this.t.c[j][1] / 1.5);
                b = (int)((float)this.t.c[j][2] / 1.5);
                break;
            }
        }
        this.rot(array, array3, this.m.cx, this.m.cz, this.m.xz, 4);
        this.rot(array2, array3, this.m.cy, this.m.cz, this.m.zy, 4);
        boolean b2 = true;
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        int n13 = 0;
        do {
            array[n13] = this.xs(array[n13], array3[n13]);
            array2[n13] = this.ys(array2[n13], array3[n13]);
            if (array2[n13] < 0 || array3[n13] < 10) {
                ++n9;
            }
            if (array2[n13] > this.m.h || array3[n13] < 10) {
                ++n10;
            }
            if (array[n13] < 0 || array3[n13] < 10) {
                ++n11;
            }
            if (array[n13] > this.m.w || array3[n13] < 10) {
                ++n12;
            }
        } while (++n13 < 4);
        if (n11 == 4 || n9 == 4 || n10 == 4 || n12 == 4) {
            b2 = false;
        }
        if (b2) {
            int n14 = 0;
            do {
                if (n > this.m.fade[n14]) {
                    r = (r * 3 + this.m.cfade[0]) / 4;
                    g = (g * 3 + this.m.cfade[1]) / 4;
                    b = (b * 3 + this.m.cfade[2]) / 4;
                }
            } while (++n14 < 8);
            graphics.setColor(new Color(r, g, b));
            graphics.fillPolygon(array, array2, 4);
        }
    }

    public void fixit(final Graphics graphics) {
        if (this.fcnt == 1) {
            for (int i = 0; i < this.npl; ++i) {
                this.p[i].hsb[0] = 0.57f;
                this.p[i].hsb[2] = 0.8f;
                this.p[i].hsb[1] = 0.8f;
                final Color hsbColor = Color.getHSBColor(this.p[i].hsb[0], this.p[i].hsb[1], this.p[i].hsb[2]);
                int r = (int)(hsbColor.getRed() + hsbColor.getRed() * (this.m.snap[0] / 100.0f));
                if (r > 255) {
                    r = 255;
                }
                if (r < 0) {
                    r = 0;
                }
                int g = (int)(hsbColor.getGreen() + hsbColor.getGreen() * (this.m.snap[1] / 100.0f));
                if (g > 255) {
                    g = 255;
                }
                if (g < 0) {
                    g = 0;
                }
                int b = (int)(hsbColor.getBlue() + hsbColor.getBlue() * (this.m.snap[2] / 100.0f));
                if (b > 255) {
                    b = 255;
                }
                if (b < 0) {
                    b = 0;
                }
                Color.RGBtoHSB(r, g, b, this.p[i].hsb);
                this.p[i].flx = 1;
            }
        }
        if (this.fcnt == 2) {
            for (int j = 0; j < this.npl; ++j) {
                this.p[j].flx = 1;
            }
        }
        if (this.fcnt == 4) {
            for (int k = 0; k < this.npl; ++k) {
                this.p[k].flx = 3;
            }
        }
        if (this.fcnt == 1 || this.fcnt > 2) {
            final int[] array = new int[8];
            final int[] array2 = new int[8];
            final int[] array3 = new int[4];
            int n = 0;
            do {
                array[n] = this.keyx[n] + this.x - this.m.x;
                array2[n] = this.grat + this.y - this.m.y;
                array3[n] = this.keyz[n] + this.z - this.m.z;
            } while (++n < 4);
            this.rot(array, array2, this.x - this.m.x, this.y - this.m.y, this.xy, 4);
            this.rot(array2, array3, this.y - this.m.y, this.z - this.m.y, this.zy, 4);
            this.rot(array, array3, this.x - this.m.x, this.z - this.m.z, this.xz, 4);
            this.rot(array, array3, this.m.cx, this.m.cz, this.m.xz, 4);
            this.rot(array2, array3, this.m.cy, this.m.cz, this.m.zy, 4);
            int abs = 0;
            int abs2 = 0;
            int py = 0;
            int n2 = 0;
            do {
                int n3 = 0;
                do {
                    if (Math.abs(array[n2] - array[n3]) > abs) {
                        abs = Math.abs(array[n2] - array[n3]);
                    }
                    if (Math.abs(array2[n2] - array2[n3]) > abs2) {
                        abs2 = Math.abs(array2[n2] - array2[n3]);
                    }
                    if (this.py(array[n2], array[n3], array2[n2], array2[n3]) > py) {
                        py = this.py(array[n2], array[n3], array2[n2], array2[n3]);
                    }
                } while (++n3 < 4);
            } while (++n2 < 4);
            final int n4 = (int)(Math.sqrt(py) / 1.5);
            if (abs < n4) {
                abs = n4;
            }
            if (abs2 < n4) {
                abs2 = n4;
            }
            final int n5 = (int)this.m.cx + (int)((this.x - this.m.x - this.m.cx) * this.m.cos(this.m.xz) - (this.z - this.m.z - this.m.cz) * this.m.sin(this.m.xz));
            final int n6 = (int)this.m.cz + (int)((this.x - this.m.x - this.m.cx) * this.m.sin(this.m.xz) + (this.z - this.m.z - this.m.cz) * this.m.cos(this.m.xz));
            final int n7 = (int)this.m.cy + (int)((this.y - this.m.y - this.m.cy) * this.m.cos(this.m.zy) - (n6 - this.m.cz) * this.m.sin(this.m.zy));
            final int n8 = (int)this.m.cz + (int)((this.y - this.m.y - this.m.cy) * this.m.sin(this.m.zy) + (n6 - this.m.cz) * this.m.cos(this.m.zy));
            array[0] = this.xs((int)(n5 - abs / 0.8 - this.m.random() * (abs / 2.4)), n8);
            array2[0] = this.ys((int)(n7 - abs2 / 1.92 - this.m.random() * (abs2 / 5.67)), n8);
            array[1] = this.xs((int)(n5 - abs / 0.8 - this.m.random() * (abs / 2.4)), n8);
            array2[1] = this.ys((int)(n7 + abs2 / 1.92 + this.m.random() * (abs2 / 5.67)), n8);
            array[2] = this.xs((int)(n5 - abs / 1.92 - this.m.random() * (abs / 5.67)), n8);
            array2[2] = this.ys((int)(n7 + abs2 / 0.8 + this.m.random() * (abs2 / 2.4)), n8);
            array[3] = this.xs((int)(n5 + abs / 1.92 + this.m.random() * (abs / 5.67)), n8);
            array2[3] = this.ys((int)(n7 + abs2 / 0.8 + this.m.random() * (abs2 / 2.4)), n8);
            array[4] = this.xs((int)(n5 + abs / 0.8 + this.m.random() * (abs / 2.4)), n8);
            array2[4] = this.ys((int)(n7 + abs2 / 1.92 + this.m.random() * (abs2 / 5.67)), n8);
            array[5] = this.xs((int)(n5 + abs / 0.8 + this.m.random() * (abs / 2.4)), n8);
            array2[5] = this.ys((int)(n7 - abs2 / 1.92 - this.m.random() * (abs2 / 5.67)), n8);
            array[6] = this.xs((int)(n5 + abs / 1.92 + this.m.random() * (abs / 5.67)), n8);
            array2[6] = this.ys((int)(n7 - abs2 / 0.8 - this.m.random() * (abs2 / 2.4)), n8);
            array[7] = this.xs((int)(n5 - abs / 1.92 - this.m.random() * (abs / 5.67)), n8);
            array2[7] = this.ys((int)(n7 - abs2 / 0.8 - this.m.random() * (abs2 / 2.4)), n8);
            if (this.fcnt == 3) {
                this.rot(array, array2, this.xs(n5, n8), this.ys(n7, n8), 22, 8);
            }
            if (this.fcnt == 4) {
                this.rot(array, array2, this.xs(n5, n8), this.ys(n7, n8), 22, 8);
            }
            if (this.fcnt == 5) {
                this.rot(array, array2, this.xs(n5, n8), this.ys(n7, n8), 0, 8);
            }
            if (this.fcnt == 6) {
                this.rot(array, array2, this.xs(n5, n8), this.ys(n7, n8), -22, 8);
            }
            if (this.fcnt == 7) {
                this.rot(array, array2, this.xs(n5, n8), this.ys(n7, n8), -22, 8);
            }
            int r2 = (int)(191.0f + 191.0f * (this.m.snap[0] / 200.0f));
            if (r2 > 255) {
                r2 = 255;
            }
            if (r2 < 0) {
                r2 = 0;
            }
            int g2 = (int)(232.0f + 232.0f * (this.m.snap[1] / 200.0f));
            if (g2 > 255) {
                g2 = 255;
            }
            if (g2 < 0) {
                g2 = 0;
            }
            int b2 = (int)(255.0f + 255.0f * (this.m.snap[2] / 200.0f));
            if (b2 > 255) {
                b2 = 255;
            }
            if (b2 < 0) {
                b2 = 0;
            }
            graphics.setColor(new Color(r2, g2, b2));
            graphics.fillPolygon(array, array2, 8);
            array[0] = this.xs((int)(n5 - abs - this.m.random() * (abs / 4)), n8);
            array2[0] = this.ys((int)(n7 - abs2 / 2.4 - this.m.random() * (abs2 / 9.6)), n8);
            array[1] = this.xs((int)(n5 - abs - this.m.random() * (abs / 4)), n8);
            array2[1] = this.ys((int)(n7 + abs2 / 2.4 + this.m.random() * (abs2 / 9.6)), n8);
            array[2] = this.xs((int)(n5 - abs / 2.4 - this.m.random() * (abs / 9.6)), n8);
            array2[2] = this.ys((int)(n7 + abs2 + this.m.random() * (abs2 / 4)), n8);
            array[3] = this.xs((int)(n5 + abs / 2.4 + this.m.random() * (abs / 9.6)), n8);
            array2[3] = this.ys((int)(n7 + abs2 + this.m.random() * (abs2 / 4)), n8);
            array[4] = this.xs((int)(n5 + abs + this.m.random() * (abs / 4)), n8);
            array2[4] = this.ys((int)(n7 + abs2 / 2.4 + this.m.random() * (abs2 / 9.6)), n8);
            array[5] = this.xs((int)(n5 + abs + this.m.random() * (abs / 4)), n8);
            array2[5] = this.ys((int)(n7 - abs2 / 2.4 - this.m.random() * (abs2 / 9.6)), n8);
            array[6] = this.xs((int)(n5 + abs / 2.4 + this.m.random() * (abs / 9.6)), n8);
            array2[6] = this.ys((int)(n7 - abs2 - this.m.random() * (abs2 / 4)), n8);
            array[7] = this.xs((int)(n5 - abs / 2.4 - this.m.random() * (abs / 9.6)), n8);
            array2[7] = this.ys((int)(n7 - abs2 - this.m.random() * (abs2 / 4)), n8);
            int r3 = (int)(213.0f + 213.0f * (this.m.snap[0] / 200.0f));
            if (r3 > 255) {
                r3 = 255;
            }
            if (r3 < 0) {
                r3 = 0;
            }
            int g3 = (int)(239.0f + 239.0f * (this.m.snap[1] / 200.0f));
            if (g3 > 255) {
                g3 = 255;
            }
            if (g3 < 0) {
                g3 = 0;
            }
            int b3 = (int)(255.0f + 255.0f * (this.m.snap[2] / 200.0f));
            if (b3 > 255) {
                b3 = 255;
            }
            if (b3 < 0) {
                b3 = 0;
            }
            graphics.setColor(new Color(r3, g3, b3));
            graphics.fillPolygon(array, array2, 8);
        }
        if (this.fcnt > 7) {
            this.fcnt = 0;
            this.fix = false;
        }
        else {
            ++this.fcnt;
        }
    }

    public void electrify(final Graphics graphics) {
        int n = 0;
        do {
            if (this.elc[n] == 0) {
                this.edl[n] = (int)(380.0f - this.m.random() * 760.0f);
                this.edr[n] = (int)(380.0f - this.m.random() * 760.0f);
                this.elc[n] = 1;
            }
            final int n2 = (int)(this.edl[n] + (190.0f - this.m.random() * 380.0f));
            final int n3 = (int)(this.edr[n] + (190.0f - this.m.random() * 380.0f));
            final int n4 = (int)(this.m.random() * 126.0f);
            final int n5 = (int)(this.m.random() * 126.0f);
            if (this.elc[n] > this.m.random() * 7.0f) {
                this.elc[n] = 0;
            }
            else {
                final int[] elc = this.elc;
                final int n6 = n;
                ++elc[n6];
            }
            final int[] array = new int[8];
            final int[] array2 = new int[8];
            final int[] array3 = new int[8];
            int n7 = 0;
            do {
                array3[n7] = this.z - this.m.z;
            } while (++n7 < 8);
            array[0] = this.x - this.m.x - 504;
            array2[0] = this.y - this.m.y - this.edl[n] - 10 - (int)(this.m.random() * 10.0f);
            array[1] = this.x - this.m.x - 252 + n5;
            array2[1] = this.y - this.m.y - n2 - 10 - (int)(this.m.random() * 10.0f);
            array[2] = this.x - this.m.x + 252 - n4;
            array2[2] = this.y - this.m.y - n3 - 10 - (int)(this.m.random() * 10.0f);
            array[3] = this.x - this.m.x + 504;
            array2[3] = this.y - this.m.y - this.edr[n] - 10 - (int)(this.m.random() * 10.0f);
            array[4] = this.x - this.m.x + 504;
            array2[4] = this.y - this.m.y - this.edr[n] + 10 + (int)(this.m.random() * 10.0f);
            array[5] = this.x - this.m.x + 252 - n4;
            array2[5] = this.y - this.m.y - n3 + 10 + (int)(this.m.random() * 10.0f);
            array[6] = this.x - this.m.x - 252 + n5;
            array2[6] = this.y - this.m.y - n2 + 10 + (int)(this.m.random() * 10.0f);
            array[7] = this.x - this.m.x - 504;
            array2[7] = this.y - this.m.y - this.edl[n] + 10 + (int)(this.m.random() * 10.0f);
            if (this.roted) {
                this.rot(array, array3, this.x - this.m.x, this.z - this.m.z, 90, 8);
            }
            this.rot(array, array3, this.m.cx, this.m.cz, this.m.xz, 8);
            this.rot(array2, array3, this.m.cy, this.m.cz, this.m.zy, 8);
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
                if (array5[n12] > this.m.h || array3[n12] < 10) {
                    ++n9;
                }
                if (array4[n12] < 0 || array3[n12] < 10) {
                    ++n10;
                }
                if (array4[n12] > this.m.w || array3[n12] < 10) {
                    ++n11;
                }
            } while (++n12 < 8);
            if (n10 == 8 || n8 == 8 || n9 == 8 || n11 == 8) {
                b = false;
            }
            if (b) {
                int n13 = 0;
                int g = (int)(255.0f + 255.0f * (this.m.snap[1] / 250.0f));
                if (g > 255) {
                    g = 255;
                }
                if (g < 0) {
                    g = 0;
                }
                int b2 = (int)(223.0f + 223.0f * (this.m.snap[2] / 250.0f));
                if (b2 > 255) {
                    b2 = 255;
                }
                if (b2 < 0) {
                    b2 = 0;
                }
                if (this.m.trk) {
                    n13 = 255;
                    g = 128;
                    b2 = 0;
                }
                graphics.setColor(new Color(n13, g, b2));
                graphics.fillPolygon(array4, array5, 8);
                if (array3[0] >= 4000) {
                    continue;
                }
                int g2 = (int)(223.0f + 223.0f * (this.m.snap[1] / 250.0f));
                if (g2 > 255) {
                    g2 = 255;
                }
                if (g2 < 0) {
                    g2 = 0;
                }
                int b3 = (int)(255.0f + 255.0f * (this.m.snap[2] / 250.0f));
                if (b3 > 255) {
                    b3 = 255;
                }
                if (b3 < 0) {
                    b3 = 0;
                }
                graphics.setColor(new Color(n13, g2, b3));
                graphics.drawPolygon(array4, array5, 8);
            }
        } while (++n < 2);
        if (!this.roted) {
            this.xy += 11;
        }
        else {
            this.zy += 11;
        }
    }

    public int py(final int n, final int n2, final int n3, final int n4) {
        return (n - n2) * (n - n2) + (n3 - n4) * (n3 - n4);
    }
}
