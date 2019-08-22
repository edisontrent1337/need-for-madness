//
// Decompiled by Procyon v0.5.36
//

public class Trackers
{
    int[] x;
    int[] y;
    int[] z;
    int[] xy;
    int[] zy;
    int[] skd;
    int[] dam;
    int[][] objectColor;
    int[] radx;
    int[] radz;
    int[] rady;
    int nt;

    public Trackers() {
        this.x = new int[500];
        this.y = new int[500];
        this.z = new int[500];
        this.xy = new int[500];
        this.zy = new int[500];
        this.skd = new int[500];
        this.dam = new int[500];
        this.objectColor = new int[500][3];
        this.radx = new int[500];
        this.radz = new int[500];
        this.rady = new int[500];
        this.nt = 0;
    }
}
