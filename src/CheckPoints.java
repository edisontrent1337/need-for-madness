// 
// Decompiled by Procyon v0.5.36
// 

public class CheckPoints
{
    int[] x;
    int[] z;
    int[] y;
    int[] typ;
    int pcs;
    int nsp;
    int n;
    int[] fx;
    int[] fz;
    int[] fy;
    boolean[] roted;
    boolean[] special;
    int fn;
    int stage;
    int nlaps;
    String name;
    int[] pos;
    int[] clear;
    int[] dested;
    int wasted;
    boolean haltall;
    int[] opx;
    int[] opz;
    int[] onscreen;
    
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
        this.roted = new boolean[5];
        this.special = new boolean[5];
        this.fn = 0;
        this.stage = 1;
        this.nlaps = 0;
        this.name = "hogan rewish";
        this.pos = new int[] { 4, 4, 4, 4, 4 };
        this.clear = new int[5];
        this.dested = new int[5];
        this.wasted = 0;
        this.haltall = false;
        this.opx = new int[5];
        this.opz = new int[5];
        this.onscreen = new int[5];
    }
    
    public void checkstat(final Madness[] array, final ContO[] array2) {
        if (!this.haltall) {
            int n = 0;
            do {
                this.pos[n] = 0;
                this.onscreen[n] = array2[n].dist;
                this.opx[n] = array2[n].x;
                this.opz[n] = array2[n].z;
                if (this.dested[n] == 0) {
                    this.clear[n] = array[n].clear;
                }
                else {
                    this.clear[n] = -1;
                }
            } while (++n < 5);
            int n2 = 0;
            do {
                for (int i = n2 + 1; i < 5; ++i) {
                    if (this.clear[n2] != this.clear[i]) {
                        if (this.clear[n2] < this.clear[i]) {
                            final int[] pos = this.pos;
                            final int n3 = n2;
                            ++pos[n3];
                        }
                        else {
                            final int[] pos2 = this.pos;
                            final int n4 = i;
                            ++pos2[n4];
                        }
                    }
                    else {
                        int n5;
                        for (n5 = array[n2].pcleared + 1; this.typ[n5] <= 0; n5 = 0) {
                            if (++n5 == this.n) {}
                        }
                        if (this.py(array2[n2].x / 100, this.x[n5] / 100, array2[n2].z / 100, this.z[n5] / 100) > this.py(array2[i].x / 100, this.x[n5] / 100, array2[i].z / 100, this.z[n5] / 100)) {
                            final int[] pos3 = this.pos;
                            final int n6 = n2;
                            ++pos3[n6];
                        }
                        else {
                            final int[] pos4 = this.pos;
                            final int n7 = i;
                            ++pos4[n7];
                        }
                    }
                }
            } while (++n2 < 5);
        }
        this.wasted = 0;
        int n8 = 1;
        do {
            if (array[n8].dest) {
                ++this.wasted;
            }
        } while (++n8 < 5);
    }
    
    public int py(final int n, final int n2, final int n3, final int n4) {
        return (n - n2) * (n - n2) + (n3 - n4) * (n3 - n4);
    }
}
