package main.java;

public class CheckPoints {
    public int[] x;
    public int[] z;
    public int[] y;
    public int[] typ;
    public int pcs;
    public int nsp;
    public int n;
    public int[] fx;
    public int[] fz;
    public int[] fy;
    public boolean[] rotated;
    public boolean[] special;
    public int fn;
    public int stage;
    public int nlaps;
    public String name;
    public int[] pos;
    public int[] clear;
    public int[] dested;
    public int wasted;
    public boolean haltall;
    public int[] opx;
    public int[] opz;
    public int[] onscreen;

    public CheckPoints() {
        this.x = new int[140];
        this.z = new int[140];
        this.y = new int[140];
        this.typ = new int[140];
        this.pcs = 0;
        this.nsp = 0;
        this.n = 0;
        this.fx = new int[5];
        this.fz = new int[5];
        this.fy = new int[5];
        this.rotated = new boolean[5];
        this.special = new boolean[5];
        this.fn = 0;
        this.stage = 1;
        this.nlaps = 0;
        this.name = "hogan rewish";
        this.pos = new int[]{4, 4, 4, 4, 4};
        this.clear = new int[5];
        this.dested = new int[5];
        this.wasted = 0;
        this.haltall = false;
        this.opx = new int[5];
        this.opz = new int[5];
        this.onscreen = new int[5];
    }

    public void checkstat(final Madness[] cars, final Geometry[] geometries) {
        if (!this.haltall) {
            for (int i = 0; i < 5; i++) {
                this.pos[i] = 0;
                this.onscreen[i] = geometries[i].distance;
                this.opx[i] = geometries[i].x;
                this.opz[i] = geometries[i].z;
                if (this.dested[i] == 0) {
                    this.clear[i] = cars[i].clear;
                } else {
                    this.clear[i] = -1;
                }
            }
            int n2 = 0;
            do {
                for (int i = n2 + 1; i < 5; ++i) {
                    if (this.clear[n2] != this.clear[i]) {
                        if (this.clear[n2] < this.clear[i]) {
                            ++this.pos[n2];
                        } else {
                            ++this.pos[i];
                        }
                    } else {
                        int n5;
                        for (n5 = cars[n2].pcleared + 1; this.typ[n5] <= 0; ++n5) {
                            if (n5 == this.n) {
                                n5 = 0;
                            }
                        }
                        if (this.py(geometries[n2].x / 100, this.x[n5] / 100, geometries[n2].z / 100, this.z[n5] / 100) > this.py(geometries[i].x / 100, this.x[n5] / 100, geometries[i].z / 100, this.z[n5] / 100)) {
                            ++this.pos[n2];
                        } else {
                            ++this.pos[i];
                        }
                    }
                }
            } while (++n2 < 5);
        }
        this.wasted = 0;
        int n8 = 1;
        do if (cars[n8].dest) {
            ++this.wasted;
        } while (++n8 < 5);
    }

    public int py(final int n, final int n2, final int n3, final int n4) {
        return (n - n2) * (n - n2) + (n3 - n4) * (n3 - n4);
    }
}
