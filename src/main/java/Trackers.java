package main.java;

public class Trackers
{
    public int[] x;
    public int[] y;
    public int[] z;
    public int[] xy;
    public int[] zy;
    public int[] skd;
    public int[] dam;
    public int[][] objectColor;
    public int[] radx;
    public int[] radz;
    public int[] rady;
    public int nt;

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
