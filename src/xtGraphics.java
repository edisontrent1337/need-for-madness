import java.awt.Component;
import java.awt.Cursor;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.applet.AudioClip;
import java.awt.Image;
import java.applet.Applet;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.36
// 

public class xtGraphics extends Panel
{
    Medium m;
    FontMetrics ftm;
    ImageObserver ob;
    Applet app;
    int fase;
    int oldfase;
    int starcnt;
    int unlocked;
    int lockcnt;
    int opselect;
    boolean shaded;
    int flipo;
    boolean nextc;
    int gatey;
    int looped;
    int[] sc;
    int[] xstart;
    int[] zstart;
    float[] proba;
    float[] dishandle;
    int holdcnt;
    boolean winner;
    int[] flexpix;
    Image fleximg;
    Image odmg;
    Image opwr;
    Image opos;
    Image owas;
    Image olap;
    Image oyourwasted;
    Image oyoulost;
    Image oyouwon;
    Image oyouwastedem;
    Image ogameh;
    Image oloadingmusic;
    Image omdness;
    Image dmg;
    Image pwr;
    Image pos;
    Image was;
    Image lap;
    Image trackbg;
    Image bl;
    Image bt;
    Image br;
    Image bb;
    Image select;
    Image loadingmusic;
    Image yourwasted;
    Image youlost;
    Image youwon;
    Image youwastedem;
    Image gameh;
    Image congrd;
    Image gameov;
    Image carsbg;
    Image pgate;
    Image selectcar;
    Image statb;
    Image mdness;
    Image paused;
    Image radicalplay;
    Image maini;
    Image opti;
    Image bgmain;
    Image inst1;
    Image inst2;
    Image inst3;
    Image rpro;
    Image nfmcom;
    Image[] next;
    Image[] back;
    Image[] contin1;
    Image[] contin2;
    Image[] ostar;
    Image[] star;
    int pcontin;
    int pnext;
    int pback;
    int pstar;
    Image[] orank;
    Image[] rank;
    Image[] ocntdn;
    Image[] cntdn;
    int gocnt;
    AudioClip[][] engs;
    boolean[] pengs;
    int[][] enginsignature;
    AudioClip[] air;
    boolean aird;
    boolean grrd;
    AudioClip[] crash;
    AudioClip[] lowcrash;
    AudioClip tires;
    AudioClip checkpoint;
    AudioClip carfixed;
    AudioClip powerup;
    AudioClip three;
    AudioClip two;
    AudioClip one;
    AudioClip go;
    AudioClip wastd;
    AudioClip firewasted;
    boolean pwastd;
    AudioClip[] skid;
    AudioClip[] dustskid;
    boolean mutes;
    RadicalMod stages;
    RadicalMod cars;
    RadicalMod[] stracks;
    boolean[] loadedt;
    boolean mutem;
    boolean arrace;
    int ana;
    int cntan;
    int cntovn;
    boolean flk;
    int tcnt;
    boolean tflk;
    String say;
    boolean wasay;
    int clear;
    int posit;
    int wasted;
    int laps;
    int[] dested;
    String[] names;
    int dmcnt;
    boolean dmflk;
    int pwcnt;
    boolean pwflk;
    String[][] adj;
    String[] exlm;
    String loop;
    String spin;
    String asay;
    int auscnt;
    boolean aflk;
    int[] hipno;
    int[] sndsize;
    Image hello;
    Image sign;
    Image loadbar;
    int kbload;
    int dnload;
    int pin;
    int pwait;
    int stopcnt;
    int cntwis;
    int crshturn;
    int bfcrash;
    int bfskid;
    boolean crashup;
    int skflg;
    int dskflg;
    
    public void framer(final int n, final Graphics graphics) {
        int r = (int)(230.0f - 230.0f * (this.m.snap[0] / (float)(100 * this.hipno[n - 1])));
        if (r > 255) {
            r = 255;
        }
        if (r < 0) {
            r = 0;
        }
        int g = (int)(230.0f - 230.0f * (this.m.snap[1] / (float)(100 * this.hipno[n - 1])));
        if (g > 255) {
            g = 255;
        }
        if (g < 0) {
            g = 0;
        }
        int b = (int)(230.0f - 230.0f * (this.m.snap[2] / (float)(100 * this.hipno[n - 1])));
        if (b > 255) {
            b = 255;
        }
        if (b < 0) {
            b = 0;
        }
        if (this.hipno[n - 1] == 0) {
            r = 255;
            g = 230;
            b = 201;
        }
        graphics.setColor(new Color(r, g, b));
        graphics.fillRect(0, 0, 550, 400);
        graphics.drawImage(this.loadingmusic, 164, 90, null);
        this.drawcs(graphics, 225, "" + this.sndsize[n - 1] + " KB", 0, 0, 0, 3);
        this.drawcs(graphics, 260, " Please Wait...", 0, 0, 0, 3);
        if (n == 10) {
            this.drawcs(graphics, 300, "> Note: Guidance Arrow is disabled in this stage!", 100, 100, 100, 4);
        }
        if (n == this.unlocked) {
            if (n == 1) {
                this.drawcs(graphics, 300, "> Don't forget, you must pass in all checkpoints to complete a lap...", 100, 100, 100, 4);
            }
            if (n == 2) {
                this.drawcs(graphics, 300, "> Don't forget, you need power to be up to race faster...", 100, 100, 100, 4);
            }
            if (n == 3) {
                this.drawcs(graphics, 300, "> Hint: its easier to waste the other cars then to finish 1st in this stage...", 100, 100, 100, 4);
                this.drawcs(graphics, 320, "( Press [A] to make Guidance Arrow point to cars )", 100, 100, 100, 4);
            }
            if (n == 4) {
                this.drawcs(graphics, 300, "> Remember the better the stunt the better the power you get...", 100, 100, 100, 4);
            }
            if (n == 5) {
                this.drawcs(graphics, 300, "> Remember the more the power the more faster and powerful your car is...", 100, 100, 100, 4);
            }
        }
    }
    
    public void cantgo(final Graphics graphics, final Control control) {
        this.pnext = 0;
        graphics.drawImage(this.trackbg, 0, 0, null);
        graphics.setFont(new Font("SansSerif", 1, 13));
        this.ftm = graphics.getFontMetrics();
        this.drawcs(graphics, 110, "> You need to complete current stage " + this.unlocked + " before playing this one...", 0, 0, 0, 3);
        graphics.drawImage(this.pgate, 96, 150, null);
        if (this.aflk) {
            this.drawcs(graphics, 160, "[ Stage " + (this.unlocked + 1) + " Locked ]", 0, 0, 0, 3);
            this.aflk = false;
        }
        else {
            this.drawcs(graphics, 160, "[ Stage " + (this.unlocked + 1) + " Locked ]", 255, 0, 0, 3);
            this.aflk = true;
        }
        graphics.drawImage(this.select, 201, 45, null);
        graphics.drawImage(this.bl, 0, 0, null);
        graphics.drawImage(this.bt, 0, 0, null);
        graphics.drawImage(this.br, 509, 0, null);
        graphics.drawImage(this.bb, 0, 357, null);
        graphics.drawImage(this.back[this.pback], 245, 320, null);
        graphics.setFont(new Font("SansSerif", 1, 11));
        this.ftm = graphics.getFontMetrics();
        this.drawcs(graphics, 396, "Use keyboard Arrows and press Enter to continue", 0, 0, 0, 3);
        --this.lockcnt;
        if (this.lockcnt == 0 || control.enter || control.handb || control.left) {
            control.left = false;
            control.handb = false;
            control.enter = false;
            this.fase = 1;
        }
    }
    
    public boolean over(final Image image, final int n, final int n2, final int n3, final int n4) {
        final int height = image.getHeight(this.ob);
        final int width = image.getWidth(this.ob);
        return n > n3 - 5 && n < n3 + width + 5 && n2 > n4 - 5 && n2 < n4 + height + 5;
    }
    
    public void loadingstage(final int i, final Graphics graphics) {
        this.cars.stop();
        this.stages.stop();
        graphics.drawImage(this.trackbg, 0, 0, null);
        graphics.setFont(new Font("SansSerif", 1, 13));
        this.ftm = graphics.getFontMetrics();
        this.drawcs(graphics, 180, "Loading Stage " + i + ", please wait...", 0, 0, 0, 3);
        graphics.drawImage(this.select, 201, 45, null);
        graphics.drawImage(this.bl, 0, 0, null);
        graphics.drawImage(this.bt, 0, 0, null);
        graphics.drawImage(this.br, 509, 0, null);
        graphics.drawImage(this.bb, 0, 357, null);
        graphics.setFont(new Font("SansSerif", 1, 11));
        this.ftm = graphics.getFontMetrics();
        this.drawcs(graphics, 396, "Use keyboard Arrows and press Enter to continue", 0, 0, 0, 3);
        this.app.repaint();
    }
    
    public void inst(final Graphics graphics, final Control control) {
        if (this.flipo == 0) {
            graphics.setColor(new Color(214, 218, 252));
            graphics.fillRect(0, 0, 550, 400);
            graphics.drawImage(this.inst1, 0, 0, null);
            this.flipo = 1;
        }
        if (this.flipo == 2) {
            graphics.setColor(new Color(214, 218, 252));
            graphics.fillRect(0, 0, 550, 400);
            graphics.drawImage(this.inst2, 0, 0, null);
            this.flipo = 3;
        }
        if (this.flipo == 4) {
            graphics.setColor(new Color(214, 218, 252));
            graphics.fillRect(0, 0, 550, 400);
            graphics.drawImage(this.inst3, 0, 0, null);
            this.flipo = 5;
        }
        if (this.flipo == 1 || this.flipo == 3) {
            graphics.drawImage(this.next[this.pnext], 460, 370, null);
        }
        if (this.flipo == 5) {
            graphics.drawImage(this.contin2[this.pcontin], 230, 370, null);
        }
        if (control.enter) {
            if (this.flipo == 1) {
                this.flipo = 2;
            }
            if (this.flipo == 3) {
                this.flipo = 4;
            }
            if (this.flipo == 5) {
                this.flipo = 0;
                this.fase = this.oldfase;
                if (this.fase == -7) {
                    graphics.drawImage(this.fleximg, 0, 0, null);
                }
            }
            control.enter = false;
        }
    }
    
    public void fleximage(final Image img, final Graphics graphics, final int n) {
        if (n == 0) {
            final PixelGrabber pixelGrabber = new PixelGrabber(img, 0, 0, 550, 400, this.flexpix, 0, 550);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {}
            int n2 = 0;
            do {
                final Color color = new Color(this.flexpix[n2]);
                final float[] hsbvals = new float[3];
                Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsbvals);
                this.flexpix[n2] = Color.getHSBColor(0.7f, hsbvals[1], hsbvals[2]).getRGB();
            } while (++n2 < 220000);
        }
        int n3 = 0;
        do {
            if (this.m.random() > 0.2) {
                final Color color2 = new Color(this.flexpix[n3]);
                int r = (int)(color2.getRed() - color2.getRed() * 0.4);
                if (r > 225) {
                    r = 225;
                }
                if (r < 0) {
                    r = 0;
                }
                int g = (int)(color2.getGreen() - color2.getGreen() * 0.35);
                if (g > 225) {
                    g = 225;
                }
                if (g < 0) {
                    g = 0;
                }
                int b = (int)(color2.getBlue() - color2.getBlue() * (0.25 + n / 30.0f));
                if (b > 225) {
                    b = 225;
                }
                if (b < 0) {
                    b = 0;
                }
                this.flexpix[n3] = new Color(r, g, b).getRGB();
            }
        } while (++n3 < 220000);
        graphics.drawImage(this.fleximg = this.createImage(new MemoryImageSource(550, 400, this.flexpix, 0, 550)), 0, 0, null);
    }
    
    public void arrow(final Graphics graphics, final int n, final int n2, final CheckPoints checkPoints, final boolean b) {
        final int[] array = new int[7];
        final int[] array2 = new int[7];
        final int[] array3 = new int[7];
        final int n3 = 275;
        final int n4 = -90;
        final int n5 = 700;
        int n6 = 0;
        do {
            array2[n6] = n4;
        } while (++n6 < 7);
        array[0] = n3;
        array3[0] = n5 + 110;
        array[1] = n3 - 35;
        array3[1] = n5 + 50;
        array[2] = n3 - 15;
        array3[2] = n5 + 50;
        array[3] = n3 - 15;
        array3[3] = n5 - 50;
        array[4] = n3 + 15;
        array3[4] = n5 - 50;
        array[5] = n3 + 15;
        array3[5] = n5 + 50;
        array[6] = n3 + 35;
        array3[6] = n5 + 50;
        int n8;
        if (!b) {
            int n7 = 0;
            if (checkPoints.x[n] - checkPoints.opx[0] >= 0) {
                n7 = 180;
            }
            n8 = (int)(90 + n7 + Math.atan((checkPoints.z[n] - checkPoints.opz[0]) / (double)(checkPoints.x[n] - checkPoints.opx[0])) / 0.017453292519943295);
        }
        else {
            int n9 = 0;
            int py = -1;
            int n10 = 0;
            int n11 = 1;
            do {
                if ((this.py(checkPoints.opx[0] / 100, checkPoints.opx[n11] / 100, checkPoints.opz[0] / 100, checkPoints.opz[n11] / 100) < py || py == -1) && (n10 == 0 || checkPoints.onscreen[n11] != 0) && checkPoints.dested[n11] == 0) {
                    n9 = n11;
                    py = this.py(checkPoints.opx[0] / 100, checkPoints.opx[n11] / 100, checkPoints.opz[0] / 100, checkPoints.opz[n11] / 100);
                    if (checkPoints.onscreen[n11] == 0) {
                        continue;
                    }
                    n10 = 1;
                }
            } while (++n11 < 5);
            int n12 = 0;
            if (checkPoints.opx[n9] - checkPoints.opx[0] >= 0) {
                n12 = 180;
            }
            n8 = (int)(90 + n12 + Math.atan((checkPoints.opz[n9] - checkPoints.opz[0]) / (double)(checkPoints.opx[n9] - checkPoints.opx[0])) / 0.017453292519943295);
            this.drawcs(graphics, 13, "[                              ]", 76, 67, 240, 0);
            this.drawcs(graphics, 13, this.names[this.sc[n9]], 0, 0, 0, 0);
        }
        int i;
        for (i = n8 + this.m.xz; i < 0; i += 360) {}
        while (i > 180) {
            i -= 360;
        }
        if (!b) {
            if (i > 130) {
                i = 130;
            }
            if (i < -130) {
                i = -130;
            }
        }
        else {
            if (i > 100) {
                i = 100;
            }
            if (i < -100) {
                i = -100;
            }
        }
        if (Math.abs(this.ana - i) < 180) {
            if (Math.abs(this.ana - i) < 10) {
                this.ana = i;
            }
            else if (this.ana < i) {
                this.ana += 10;
            }
            else {
                this.ana -= 10;
            }
        }
        else {
            if (i < 0) {
                this.ana += 15;
                if (this.ana > 180) {
                    this.ana -= 360;
                }
            }
            if (i > 0) {
                this.ana -= 15;
                if (this.ana < -180) {
                    this.ana += 360;
                }
            }
        }
        this.rot(array, array3, n3, n5, this.ana, 7);
        final int abs = Math.abs(this.ana);
        if (!b) {
            if (abs > 7 || n2 > 0 || n2 == -2 || this.cntan != 0) {
                int n13 = 0;
                do {
                    array[n13] = this.xs(array[n13], array3[n13]);
                    array2[n13] = this.ys(array2[n13], array3[n13]);
                } while (++n13 < 7);
                int r = (int)(190.0f + 190.0f * (this.m.snap[0] / 100.0f));
                if (r > 255) {
                    r = 255;
                }
                if (r < 0) {
                    r = 0;
                }
                int g = (int)(255.0f + 255.0f * (this.m.snap[1] / 100.0f));
                if (g > 255) {
                    g = 255;
                }
                if (g < 0) {
                    g = 0;
                }
                int b2 = 0;
                if (n2 <= 0) {
                    if (abs <= 45 && n2 != -2 && this.cntan == 0) {
                        r = (r * abs + this.m.csky[0] * (45 - abs)) / 45;
                        g = (g * abs + this.m.csky[1] * (45 - abs)) / 45;
                        b2 = (b2 * abs + this.m.csky[2] * (45 - abs)) / 45;
                    }
                    if (abs >= 90) {
                        int n14 = (int)(255.0f + 255.0f * (this.m.snap[0] / 100.0f));
                        if (n14 > 255) {
                            n14 = 255;
                        }
                        if (n14 < 0) {
                            n14 = 0;
                        }
                        r = (r * (140 - abs) + n14 * (abs - 90)) / 50;
                        if (r > 255) {
                            r = 255;
                        }
                    }
                }
                else if (this.flk) {
                    r = (int)(255.0f + 255.0f * (this.m.snap[0] / 100.0f));
                    if (r > 255) {
                        r = 255;
                    }
                    if (r < 0) {
                        r = 0;
                    }
                    this.flk = false;
                }
                else {
                    r = (int)(255.0f + 255.0f * (this.m.snap[0] / 100.0f));
                    if (r > 255) {
                        r = 255;
                    }
                    if (r < 0) {
                        r = 0;
                    }
                    g = (int)(220.0f + 220.0f * (this.m.snap[1] / 100.0f));
                    if (g > 255) {
                        g = 255;
                    }
                    if (g < 0) {
                        g = 0;
                    }
                    this.flk = true;
                }
                graphics.setColor(new Color(r, g, b2));
                graphics.fillPolygon(array, array2, 7);
                int r2 = (int)(115.0f + 115.0f * (this.m.snap[0] / 100.0f));
                if (r2 > 255) {
                    r2 = 255;
                }
                if (r2 < 0) {
                    r2 = 0;
                }
                int g2 = (int)(170.0f + 170.0f * (this.m.snap[1] / 100.0f));
                if (g2 > 255) {
                    g2 = 255;
                }
                if (g2 < 0) {
                    g2 = 0;
                }
                int b3 = 0;
                if (n2 <= 0) {
                    if (abs <= 45 && n2 != -2 && this.cntan == 0) {
                        r2 = (r2 * abs + this.m.csky[0] * (45 - abs)) / 45;
                        g2 = (g2 * abs + this.m.csky[1] * (45 - abs)) / 45;
                        b3 = (b3 * abs + this.m.csky[2] * (45 - abs)) / 45;
                    }
                }
                else if (this.flk) {
                    r2 = (int)(255.0f + 255.0f * (this.m.snap[0] / 100.0f));
                    if (r2 > 255) {
                        r2 = 255;
                    }
                    if (r2 < 0) {
                        r2 = 0;
                    }
                    g2 = 0;
                }
                graphics.setColor(new Color(r2, g2, b3));
                graphics.drawPolygon(array, array2, 7);
            }
        }
        else {
            int n15 = 0;
            do {
                array[n15] = this.xs(array[n15], array3[n15]);
                array2[n15] = this.ys(array2[n15], array3[n15]);
            } while (++n15 < 7);
            int r3 = (int)(159.0f + 159.0f * (this.m.snap[0] / 100.0f));
            if (r3 > 255) {
                r3 = 255;
            }
            if (r3 < 0) {
                r3 = 0;
            }
            int g3 = (int)(207.0f + 207.0f * (this.m.snap[1] / 100.0f));
            if (g3 > 255) {
                g3 = 255;
            }
            if (g3 < 0) {
                g3 = 0;
            }
            int b4 = (int)(255.0f + 255.0f * (this.m.snap[2] / 100.0f));
            if (b4 > 255) {
                b4 = 255;
            }
            if (b4 < 0) {
                b4 = 0;
            }
            graphics.setColor(new Color(r3, g3, b4));
            graphics.fillPolygon(array, array2, 7);
            int r4 = (int)(120.0f + 120.0f * (this.m.snap[0] / 100.0f));
            if (r4 > 255) {
                r4 = 255;
            }
            if (r4 < 0) {
                r4 = 0;
            }
            int g4 = (int)(114.0f + 114.0f * (this.m.snap[1] / 100.0f));
            if (g4 > 255) {
                g4 = 255;
            }
            if (g4 < 0) {
                g4 = 0;
            }
            int b5 = (int)(255.0f + 255.0f * (this.m.snap[2] / 100.0f));
            if (b5 > 255) {
                b5 = 255;
            }
            if (b5 < 0) {
                b5 = 0;
            }
            graphics.setColor(new Color(r4, g4, b5));
            graphics.drawPolygon(array, array2, 7);
        }
    }
    
    public void levelhigh(final Graphics graphics, final int n, final int n2) {
        graphics.drawImage(this.gameh, 176, 20, null);
        if (n != 0) {
            this.drawcs(graphics, 60, "You wasted 'em!", 16, 48, 96, 0);
        }
        else if (n2 == 229) {
            this.drawcs(graphics, 60, "Wasted!", 16, 48, 96, 0);
        }
        else {
            this.drawcs(graphics, 60, "Stunts!", 16, 48, 96, 0);
        }
        this.drawcs(graphics, 380, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
    }
    
    public void playsounds(final Madness madness, final Control control, final int n) {
        if (this.fase == 0 && this.starcnt < 35 && this.cntwis != 8 && !this.mutes) {
            boolean b = (control.up && madness.speed > 0.0f) || (control.down && madness.speed < 10.0f);
            boolean b2 = (madness.skid == 1 && control.handb) || Math.abs(madness.scz[0] - (madness.scz[1] + madness.scz[0] + madness.scz[2] + madness.scz[3]) / 4.0f) > 1.0f || Math.abs(madness.scx[0] - (madness.scx[1] + madness.scx[0] + madness.scx[2] + madness.scx[3]) / 4.0f) > 1.0f;
            boolean b3 = false;
            if (control.up && madness.speed < 10.0f) {
                b2 = true;
                b = true;
                b3 = true;
            }
            if (b && madness.mtouch) {
                if (!madness.capsized) {
                    if (!b2) {
                        if (madness.power != 98.0f) {
                            if (Math.abs(madness.speed) > 0.0f && Math.abs(madness.speed) <= madness.swits[madness.cn][0]) {
                                int n2 = (int)(3.0f * Math.abs(madness.speed) / madness.swits[madness.cn][0]);
                                if (n2 == 2) {
                                    if (this.pwait == 0) {
                                        n2 = 0;
                                    }
                                    else {
                                        --this.pwait;
                                    }
                                }
                                else {
                                    this.pwait = 7;
                                }
                                this.sparkeng(n2);
                            }
                            if (Math.abs(madness.speed) > madness.swits[madness.cn][0] && Math.abs(madness.speed) <= madness.swits[madness.cn][1]) {
                                int n3 = (int)(3.0f * (Math.abs(madness.speed) - madness.swits[madness.cn][0]) / (madness.swits[madness.cn][1] - madness.swits[madness.cn][0]));
                                if (n3 == 2) {
                                    if (this.pwait == 0) {
                                        n3 = 0;
                                    }
                                    else {
                                        --this.pwait;
                                    }
                                }
                                else {
                                    this.pwait = 7;
                                }
                                this.sparkeng(n3);
                            }
                            if (Math.abs(madness.speed) > madness.swits[madness.cn][1] && Math.abs(madness.speed) <= madness.swits[madness.cn][2]) {
                                this.sparkeng((int)(3.0f * (Math.abs(madness.speed) - madness.swits[madness.cn][1]) / (madness.swits[madness.cn][2] - madness.swits[madness.cn][1])));
                            }
                        }
                        else {
                            int n4 = 2;
                            if (this.pwait == 0) {
                                if (Math.abs(madness.speed) > madness.swits[madness.cn][1]) {
                                    n4 = 3;
                                }
                            }
                            else {
                                --this.pwait;
                            }
                            this.sparkeng(n4);
                        }
                    }
                    else {
                        this.sparkeng(-1);
                        if (b3) {
                            if (this.stopcnt <= 0) {
                                this.air[5].loop();
                                this.stopcnt = 10;
                            }
                        }
                        else if (this.stopcnt <= -2) {
                            this.air[2 + (int)(this.m.random() * 3.0f)].loop();
                            this.stopcnt = 7;
                        }
                    }
                }
                else {
                    this.sparkeng(3);
                }
                this.grrd = false;
                this.aird = false;
            }
            else {
                this.pwait = 15;
                if (!madness.mtouch && !this.grrd && this.m.random() > 0.4) {
                    this.air[(int)(this.m.random() * 4.0f)].loop();
                    this.stopcnt = 5;
                    this.grrd = true;
                }
                if (!madness.wtouch && !this.aird) {
                    this.stopairs();
                    this.air[(int)(this.m.random() * 4.0f)].loop();
                    this.stopcnt = 10;
                    this.aird = true;
                }
                this.sparkeng(-1);
            }
            if (madness.cntdest != 0 && this.cntwis < 7) {
                if (!this.pwastd) {
                    this.wastd.loop();
                    this.pwastd = true;
                }
            }
            else {
                if (this.pwastd) {
                    this.wastd.stop();
                    this.pwastd = false;
                }
                if (this.cntwis == 7 && !this.mutes) {
                    this.firewasted.play();
                }
            }
        }
        else {
            this.sparkeng(-2);
            if (this.pwastd) {
                this.wastd.stop();
                this.pwastd = false;
            }
        }
        if (this.stopcnt != -20) {
            if (this.stopcnt == 1) {
                this.stopairs();
            }
            --this.stopcnt;
        }
        if (this.bfcrash != 0) {
            --this.bfcrash;
        }
        if (this.bfskid != 0) {
            --this.bfskid;
        }
        if (madness.newcar) {
            this.cntwis = 0;
        }
        if (this.fase == 0 || this.fase == 6 || this.fase == -1 || this.fase == -2 || this.fase == -3 || this.fase == -4 || this.fase == -5) {
            if (this.mutes != control.mutes) {
                this.mutes = control.mutes;
            }
            if (control.mutem != this.mutem) {
                this.mutem = control.mutem;
                if (this.mutem) {
                    if (this.loadedt[n - 1]) {
                        this.stracks[n - 1].stop();
                    }
                }
                else if (this.loadedt[n - 1]) {
                    this.stracks[n - 1].resume();
                }
            }
        }
        if (madness.cntdest != 0 && this.cntwis < 7) {
            if (madness.dest) {
                ++this.cntwis;
            }
        }
        else {
            if (madness.cntdest == 0) {
                this.cntwis = 0;
            }
            if (this.cntwis == 7) {
                this.cntwis = 8;
            }
        }
    }
    
    public void crash(final float a, final int n) {
        if (this.bfcrash == 0) {
            if (n == 0) {
                if (Math.abs(a) > 25.0f && Math.abs(a) < 170.0f) {
                    if (!this.mutes) {
                        this.lowcrash[this.crshturn].play();
                    }
                    this.bfcrash = 2;
                }
                if (Math.abs(a) > 170.0f) {
                    if (!this.mutes) {
                        this.crash[this.crshturn].play();
                    }
                    this.bfcrash = 2;
                }
                if (Math.abs(a) > 25.0f) {
                    if (this.crashup) {
                        --this.crshturn;
                    }
                    else {
                        ++this.crshturn;
                    }
                    if (this.crshturn == -1) {
                        this.crshturn = 2;
                    }
                    if (this.crshturn == 3) {
                        this.crshturn = 0;
                    }
                }
            }
            if (n == -1) {
                if (Math.abs(a) > 25.0f && Math.abs(a) < 170.0f) {
                    if (!this.mutes) {
                        this.lowcrash[2].play();
                    }
                    this.bfcrash = 2;
                }
                if (Math.abs(a) > 170.0f) {
                    if (!this.mutes) {
                        this.crash[2].play();
                    }
                    this.bfcrash = 2;
                }
            }
            if (n == 1) {
                if (!this.mutes) {
                    this.tires.play();
                }
                this.bfcrash = 3;
            }
        }
    }
    
    public int ys(final int n, int n2) {
        if (n2 < 50) {
            n2 = 50;
        }
        return (n2 - this.m.focus_point) * (this.m.cy - n) / n2 + n;
    }
    
    public void replyn(final Graphics graphics) {
        if (this.aflk) {
            this.drawcs(graphics, 30, "Replay  >", 0, 0, 0, 0);
            this.aflk = false;
        }
        else {
            this.drawcs(graphics, 30, "Replay  >", 0, 128, 255, 0);
            this.aflk = true;
        }
    }
    
    private Image pressed(final Image img) {
        final int height = img.getHeight(this.ob);
        final int width = img.getWidth(this.ob);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(img, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < width * height; ++i) {
            if (array[i] != array[width * height - 1]) {
                array[i] = -16777216;
            }
        }
        return this.createImage(new MemoryImageSource(width, height, array, 0, width));
    }
    
    public void loadpak1(final MediaTracker mediaTracker, final Toolkit toolkit) {
        try {
            final DataInputStream in = new DataInputStream(new URL(this.app.getCodeBase(), "graphics/images1.radq").openStream());
            final ZipInputStream zipInputStream = new ZipInputStream(in);
            for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
                int i = (int)zipEntry.getSize();
                final String name = zipEntry.getName();
                final byte[] b = new byte[i];
                int off = 0;
                while (i > 0) {
                    final int read = zipInputStream.read(b, off, i);
                    off += read;
                    i -= read;
                }
                if (name.equals("cars.gif")) {
                    this.carsbg = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("1.gif")) {
                    this.orank[0] = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("gameh.gif")) {
                    this.ogameh = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("gameov.gif")) {
                    this.gameov = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("lap.gif")) {
                    this.olap = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("paused.gif")) {
                    this.paused = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("select.gif")) {
                    this.select = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("yourwasted.gif")) {
                    this.oyourwasted = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("youwastedem.gif")) {
                    this.oyouwastedem = this.loadimage(b, mediaTracker, toolkit);
                }
            }
            in.close();
            zipInputStream.close();
        }
        catch (Exception obj) {
            System.out.println("Error Loading Images Pak 1: " + obj);
        }
        System.gc();
    }
    
    public void nofocus(final Graphics graphics) {
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(0, 0, 550, 20);
        graphics.fillRect(0, 0, 20, 400);
        graphics.fillRect(0, 380, 550, 20);
        graphics.fillRect(530, 0, 20, 400);
        graphics.setColor(new Color(192, 192, 192));
        graphics.drawRect(20, 20, 510, 360);
        graphics.setFont(new Font("SansSerif", 1, 11));
        this.ftm = graphics.getFontMetrics();
        this.drawcs(graphics, 14, "Game lost its focus -Click- screen with mouse to continue.", 100, 100, 100, 3);
        this.drawcs(graphics, 395, "Game lost its focus -Click- screen with mouse to continue.", 100, 100, 100, 3);
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
    
    public boolean overon(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return n5 > n && n5 < n + n3 && n6 > n2 && n6 < n2 + n4;
    }
    
    public void pauseimage(final Image img, final Graphics graphics) {
        final PixelGrabber pixelGrabber = new PixelGrabber(img, 0, 0, 550, 400, this.flexpix, 0, 550);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        do {
            final Color color = new Color(this.flexpix[n5]);
            int n6;
            if (n4 == 0) {
                n6 = (n3 = (color.getRed() + color.getGreen() + color.getBlue()) / 3);
            }
            else {
                n6 = (n3 = (color.getRed() + color.getGreen() + color.getBlue() + n3 * 30) / 33);
            }
            if (++n4 == 550) {
                n4 = 0;
            }
            if (n5 > 550 * (106 + n2) + 156 && n2 < 188) {
                final int r = (n6 + 60) / 3;
                final int g = (n6 + 135) / 3;
                final int b = (n6 + 220) / 3;
                if (++n == 237) {
                    ++n2;
                    n = 0;
                }
                this.flexpix[n5] = new Color(r, g, b).getRGB();
            }
            else {
                this.flexpix[n5] = new Color(n6, n6, n6).getRGB();
            }
        } while (++n5 < 220000);
        graphics.drawImage(this.fleximg = this.createImage(new MemoryImageSource(550, 400, this.flexpix, 0, 550)), 0, 0, null);
        this.m.flex = 0;
    }
    
    public void loadmusic(final int n, final int n2, final Graphics graphics) {
        int r = (int)(230.0f - 230.0f * (this.m.snap[0] / (float)(100 * this.hipno[n - 1])));
        if (r > 255) {
            r = 255;
        }
        if (r < 0) {
            r = 0;
        }
        int g = (int)(230.0f - 230.0f * (this.m.snap[1] / (float)(100 * this.hipno[n - 1])));
        if (g > 255) {
            g = 255;
        }
        if (g < 0) {
            g = 0;
        }
        int b = (int)(230.0f - 230.0f * (this.m.snap[2] / (float)(100 * this.hipno[n - 1])));
        if (b > 255) {
            b = 255;
        }
        if (b < 0) {
            b = 0;
        }
        if (this.hipno[n - 1] == 0) {
            r = 255;
            g = 230;
            b = 201;
        }
        graphics.setColor(new Color(r, g, b));
        graphics.fillRect(0, 0, 550, 400);
        graphics.drawImage(this.loadingmusic, 164, 90, null);
        this.drawcs(graphics, 225, "" + this.sndsize[n - 1] + " KB", 0, 0, 0, 3);
        this.drawcs(graphics, 260, " Please Wait...", 0, 0, 0, 3);
        if (n == 10) {
            this.drawcs(graphics, 300, "> Note: Guidance Arrow is disabled in this stage!", 100, 100, 100, 4);
        }
        if (n == this.unlocked) {
            if (n == 1) {
                this.drawcs(graphics, 300, "> Don't forget, you must pass in all checkpoints to complete a lap...", 100, 100, 100, 4);
            }
            if (n == 2) {
                this.drawcs(graphics, 300, "> Don't forget, you need power to be up to race faster...", 100, 100, 100, 4);
            }
            if (n == 3) {
                this.drawcs(graphics, 300, "> Hint: its easier to waste the other cars then to finish 1st in this stage...", 100, 100, 100, 4);
                this.drawcs(graphics, 320, "( Press [A] to make Guidance Arrow point to cars )", 100, 100, 100, 4);
            }
            if (n == 4) {
                this.drawcs(graphics, 300, "> Remember the better the stunt the better the power you get...", 100, 100, 100, 4);
            }
            if (n == 5) {
                this.drawcs(graphics, 300, "> Remember the more the power the more faster and powerful your car is...", 100, 100, 100, 4);
            }
        }
        this.app.setCursor(new Cursor(3));
        this.app.repaint();
        if (n == 1 && !this.loadedt[0]) {
            this.stracks[0] = new RadicalMod("music/stage1.radq", 350, 8400, 135, this.app);
            if (this.stracks[0].stream != null) {
                this.loadedt[0] = true;
            }
        }
        if (n == 2 && !this.loadedt[1]) {
            this.stracks[1] = new RadicalMod("music/stage2.radq", 370, 9000, 145, this.app);
            if (this.stracks[1].stream != null) {
                this.loadedt[1] = true;
            }
        }
        if (n == 3 && !this.loadedt[2]) {
            this.stracks[2] = new RadicalMod("music/stage3.radq", 350, 8500, 145, this.app);
            if (this.stracks[2].stream != null) {
                this.loadedt[2] = true;
            }
        }
        if (n == 4 && !this.loadedt[3]) {
            this.stracks[3] = new RadicalMod("music/stage4.radq", 300, 7500, 125, this.app);
            if (this.stracks[3].stream != null) {
                this.loadedt[3] = true;
            }
        }
        if (n == 5 && !this.loadedt[4]) {
            this.stracks[4] = new RadicalMod("music/stage5.radq", 250, 7900, 125, this.app);
            if (this.stracks[4].stream != null) {
                this.loadedt[4] = true;
            }
        }
        if (n == 6 && !this.loadedt[5]) {
            this.stracks[5] = new RadicalMod("music/stage6.radq", 760, 7900, 125, this.app);
            if (this.stracks[5].stream != null) {
                this.loadedt[5] = true;
            }
        }
        if (n == 7 && !this.loadedt[6]) {
            this.stracks[6] = new RadicalMod("music/stage7.radq", 300, 7500, 125, this.app);
            if (this.stracks[6].stream != null) {
                this.loadedt[6] = true;
            }
        }
        if (n == 8 && !this.loadedt[7]) {
            this.stracks[7] = new RadicalMod("music/stage8.radq", 400, 7900, 125, this.app);
            if (this.stracks[7].stream != null) {
                this.loadedt[7] = true;
            }
        }
        if (n == 9 && !this.loadedt[8]) {
            this.stracks[8] = new RadicalMod("music/stage9.radq", 300, 7900, 125, this.app);
            if (this.stracks[8].stream != null) {
                this.loadedt[8] = true;
            }
        }
        if (n == 10 && !this.loadedt[9]) {
            this.stracks[9] = new RadicalMod("music/stage10.radq", 550, 8100, 145, this.app);
            if (this.stracks[9].stream != null) {
                this.loadedt[9] = true;
            }
        }
        if (n == 11 && !this.loadedt[10]) {
            this.stracks[10] = new RadicalMod("music/stage11.radq", 550, 9000, 145, this.app);
            if (this.stracks[10].stream != null) {
                this.loadedt[10] = true;
            }
        }
        if (n2 == 0) {
            if (this.loadedt[n - 1]) {
                this.stracks[n - 1].play();
            }
            this.app.setCursor(new Cursor(0));
            this.fase = 6;
        }
        else {
            this.fase = 176;
        }
        this.pcontin = 0;
        this.mutem = false;
        this.mutes = false;
    }
    
    public void pausedgame(final Graphics graphics, final int n, final Control control, final Record record) {
        if (control.up) {
            graphics.drawImage(this.fleximg, 0, 0, null);
            --this.opselect;
            if (this.opselect == -1) {
                this.opselect = 3;
            }
            control.up = false;
        }
        if (control.down) {
            graphics.drawImage(this.fleximg, 0, 0, null);
            ++this.opselect;
            if (this.opselect == 4) {
                this.opselect = 0;
            }
            control.down = false;
        }
        if (this.shaded) {
            graphics.drawImage(this.fleximg, 0, 0, null);
        }
        if (this.opselect == 0) {
            graphics.setColor(new Color(64, 143, 223));
            graphics.fillRoundRect(204, 143, 137, 22, 7, 20);
            if (this.shaded) {
                graphics.setColor(new Color(225, 200, 255));
            }
            else {
                graphics.setColor(new Color(0, 89, 223));
            }
            graphics.drawRoundRect(204, 143, 137, 22, 7, 20);
        }
        if (this.opselect == 1) {
            graphics.setColor(new Color(64, 143, 223));
            graphics.fillRoundRect(195, 171, 155, 22, 7, 20);
            if (this.shaded) {
                graphics.setColor(new Color(225, 200, 255));
            }
            else {
                graphics.setColor(new Color(0, 89, 223));
            }
            graphics.drawRoundRect(195, 171, 155, 22, 7, 20);
        }
        if (this.opselect == 2) {
            graphics.setColor(new Color(64, 143, 223));
            graphics.fillRoundRect(178, 197, 190, 22, 7, 20);
            if (this.shaded) {
                graphics.setColor(new Color(225, 200, 255));
            }
            else {
                graphics.setColor(new Color(0, 89, 223));
            }
            graphics.drawRoundRect(178, 197, 190, 22, 7, 20);
        }
        if (this.opselect == 3) {
            graphics.setColor(new Color(64, 143, 223));
            graphics.fillRoundRect(216, 223, 109, 22, 7, 20);
            if (this.shaded) {
                graphics.setColor(new Color(225, 200, 255));
            }
            else {
                graphics.setColor(new Color(0, 89, 223));
            }
            graphics.drawRoundRect(216, 223, 109, 22, 7, 20);
        }
        graphics.drawImage(this.paused, 156, 106, null);
        if (control.enter || control.handb) {
            if (this.opselect == 0) {
                if (this.loadedt[n - 1] && !this.mutem) {
                    this.stracks[n - 1].resume();
                }
                this.fase = 0;
            }
            if (this.opselect == 1) {
                if (record.caught >= 300) {
                    if (this.loadedt[n - 1] && !this.mutem) {
                        this.stracks[n - 1].resume();
                    }
                    this.fase = -1;
                }
                else {
                    this.fase = -8;
                }
            }
            if (this.opselect == 2) {
                this.oldfase = -7;
                this.fase = 11;
            }
            if (this.opselect == 3) {
                this.fase = 10;
                this.opselect = 0;
            }
            control.enter = false;
            control.handb = false;
        }
    }
    
    public void credits(final Graphics graphics, final Control control) {
        if (this.flipo == 0) {
            this.powerup.play();
            this.flipo = 1;
        }
        if (this.flipo == 1) {
            graphics.setColor(new Color(0, 0, 0));
            graphics.fillRect(0, 0, 550, 400);
            graphics.drawImage(this.radicalplay, 87, 110, null);
            graphics.setFont(new Font("SansSerif", 1, 13));
            this.ftm = graphics.getFontMetrics();
            this.drawcs(graphics, 150 + (int)(10.0f * this.m.random()), "www.radicalplay.com", 112, 120, 143, 3);
            graphics.setFont(new Font("SansSerif", 1, 11));
            this.ftm = graphics.getFontMetrics();
            if (this.aflk) {
                this.drawcs(graphics, 190, "And we are never going to find the new unless we get a little crazy...", 112, 120, 143, 3);
                this.aflk = false;
            }
            else {
                this.drawcs(graphics, 192, "And we are never going to find the new unless we get a little crazy...", 150, 150, 150, 3);
                this.aflk = true;
            }
            graphics.drawImage(this.rpro, 150, 240, null);
        }
        if (this.flipo == 2) {
            graphics.drawImage(this.bgmain, 0, 0, null);
            graphics.drawImage(this.omdness, 158, 7, null);
            graphics.setFont(new Font("SansSerif", 1, 13));
            this.ftm = graphics.getFontMetrics();
            this.drawcs(graphics, 65, "At Radicalplay.com", 0, 0, 0, 3);
            this.drawcs(graphics, 100, "Cartoon 3D Engine, Game Programming, 3D Models, Graphics and Sound Effects", 0, 0, 0, 3);
            this.drawcs(graphics, 120, "By Omar Waly", 0, 0, 100, 3);
            this.drawcs(graphics, 180, "Thanks for Game Testing", 0, 0, 0, 3);
            this.drawcs(graphics, 200, "Khaled Helmy, Ismail Gorilaz,", 0, 0, 100, 3);
            this.drawcs(graphics, 215, "Karim AboSamra, Mahmoud Waly", 0, 0, 100, 3);
            this.drawcs(graphics, 230, "Karim Khadem, Ahmed Ismail", 0, 0, 100, 3);
            this.drawcs(graphics, 245, "and Mahmoud EzzElDien (Turbo)", 0, 0, 100, 3);
            this.drawcs(graphics, 305, "Music From", 0, 0, 0, 3);
            this.drawcs(graphics, 325, "www.ModArchive.com", 0, 0, 100, 3);
            this.drawcs(graphics, 385, "For any comments: Omar@radicalplay.com", 0, 0, 0, 3);
        }
        if (this.flipo == 3) {
            graphics.drawImage(this.bgmain, 0, 0, null);
            graphics.drawImage(this.nfmcom, 69, 170, null);
        }
        graphics.drawImage(this.next[this.pnext], 460, 370, null);
        if (control.enter || control.handb || control.right) {
            ++this.flipo;
            if (this.flipo == 4) {
                this.flipo = 0;
                this.fase = 10;
            }
            control.enter = false;
            control.handb = false;
            control.right = false;
        }
    }
    
    public void stat(final Madness madness, final CheckPoints checkPoints, final Control control, final boolean b, final Graphics graphics) {
        int n = 0;
        if (this.wasted == 4) {
            if (this.m.flex != 2) {
                graphics.setColor(new Color(this.m.csky[0], this.m.csky[1], this.m.csky[2]));
                graphics.fillRect(166, 70, this.youwastedem.getWidth(this.ob), this.youwastedem.getHeight(this.ob));
                graphics.setColor(new Color(this.m.cfade[0], this.m.cfade[1], this.m.cfade[2]));
                graphics.drawRect(166, 70, this.youwastedem.getWidth(this.ob), this.youwastedem.getHeight(this.ob));
            }
            graphics.drawImage(this.youwastedem, 166, 70, null);
            if (this.aflk) {
                this.drawcs(graphics, 120, "You Won, all cars have been wasted!", 0, 0, 0, 0);
                this.aflk = false;
            }
            else {
                this.drawcs(graphics, 120, "You Won, all cars have been wasted!", 0, 128, 255, 0);
                this.aflk = true;
            }
            this.drawcs(graphics, 350, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
            checkPoints.haltall = true;
            n = 1;
            this.winner = true;
        }
        if (n == 0 && madness.dest && this.cntwis == 8) {
            if (this.m.flex != 2) {
                graphics.setColor(new Color(this.m.csky[0], this.m.csky[1], this.m.csky[2]));
                graphics.fillRect(172, 70, this.yourwasted.getWidth(this.ob), this.yourwasted.getHeight(this.ob));
                graphics.setColor(new Color(this.m.cfade[0], this.m.cfade[1], this.m.cfade[2]));
                graphics.drawRect(172, 70, this.yourwasted.getWidth(this.ob), this.yourwasted.getHeight(this.ob));
            }
            graphics.drawImage(this.yourwasted, 172, 70, null);
            this.drawcs(graphics, 350, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
            n = 1;
            this.winner = false;
        }
        if (n == 0) {
            int n2 = 0;
            do {
                if (checkPoints.clear[n2] == checkPoints.nlaps * checkPoints.nsp && checkPoints.pos[n2] == 0) {
                    if (n2 == 0) {
                        if (this.m.flex != 2) {
                            graphics.setColor(new Color(this.m.csky[0], this.m.csky[1], this.m.csky[2]));
                            graphics.fillRect(208, 70, this.youwon.getWidth(this.ob), this.youwon.getHeight(this.ob));
                            graphics.setColor(new Color(this.m.cfade[0], this.m.cfade[1], this.m.cfade[2]));
                            graphics.drawRect(208, 70, this.youwon.getWidth(this.ob), this.youwon.getHeight(this.ob));
                        }
                        graphics.drawImage(this.youwon, 208, 70, null);
                        if (this.aflk) {
                            this.drawcs(graphics, 120, "You finished first, nice job!", 0, 0, 0, 0);
                            this.aflk = false;
                        }
                        else {
                            this.drawcs(graphics, 120, "You finished first, nice job!", 0, 128, 255, 0);
                            this.aflk = true;
                        }
                        this.winner = true;
                    }
                    else {
                        if (this.m.flex != 2) {
                            graphics.setColor(new Color(this.m.csky[0], this.m.csky[1], this.m.csky[2]));
                            graphics.fillRect(211, 70, this.youlost.getWidth(this.ob), this.youlost.getHeight(this.ob));
                            graphics.setColor(new Color(this.m.cfade[0], this.m.cfade[1], this.m.cfade[2]));
                            graphics.drawRect(211, 70, this.youlost.getWidth(this.ob), this.youlost.getHeight(this.ob));
                        }
                        graphics.drawImage(this.youlost, 211, 70, null);
                        if (this.aflk) {
                            this.drawcs(graphics, 120, "" + this.names[this.sc[n2]] + " finished first, race over!", 0, 0, 0, 0);
                            this.aflk = false;
                        }
                        else {
                            this.drawcs(graphics, 120, "" + this.names[this.sc[n2]] + " finished first, race over!", 0, 128, 255, 0);
                            this.aflk = true;
                        }
                        this.winner = false;
                    }
                    this.drawcs(graphics, 350, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
                    checkPoints.haltall = true;
                    n = 1;
                }
            } while (++n2 < 5);
        }
        if (n != 0) {
            ++this.holdcnt;
            if (control.enter || this.holdcnt > 250) {
                this.fase = -2;
                control.enter = false;
            }
        }
        else {
            if (this.holdcnt != 0) {
                this.holdcnt = 0;
            }
            if (control.enter) {
                if (this.loadedt[checkPoints.stage - 1]) {
                    this.stracks[checkPoints.stage - 1].stop();
                }
                this.fase = -6;
                control.enter = false;
            }
        }
        if (b) {
            if (checkPoints.stage != 10 && this.arrace != control.arrace) {
                this.arrace = control.arrace;
                if (this.arrace) {
                    this.wasay = true;
                    this.say = " Arrow now pointing at  Cars  <";
                    this.tcnt = -5;
                }
                if (!this.arrace) {
                    this.wasay = false;
                    this.say = " Arrow now pointing at  Track  <";
                    this.tcnt = -5;
                    this.cntan = 20;
                }
            }
            if (n == 0 && checkPoints.stage != 10 && this.starcnt == 0) {
                this.arrow(graphics, madness.point, madness.missedcp, checkPoints, this.arrace);
                if (!this.arrace && this.auscnt == 45 && madness.capcnt == 0) {
                    if (madness.missedcp > 0) {
                        if (madness.missedcp > 15 && madness.missedcp < 50) {
                            if (this.flk) {
                                this.drawcs(graphics, 70, "CheckPoint Missed!", 255, 150, 0, 2);
                            }
                            else {
                                this.drawcs(graphics, 70, "CheckPoint Missed!", 255, 0, 0, 2);
                            }
                        }
                        ++madness.missedcp;
                        if (madness.missedcp == 70) {
                            madness.missedcp = -2;
                        }
                    }
                    else if (madness.mtouch && this.cntovn < 70) {
                        if (Math.abs(this.ana) > 100) {
                            ++this.cntan;
                        }
                        else if (this.cntan != 0) {
                            --this.cntan;
                        }
                        if (this.cntan > 40) {
                            ++this.cntovn;
                            this.cntan = 40;
                            if (this.flk) {
                                this.drawcs(graphics, 70, "Wrong Way!", 255, 150, 0, 2);
                                this.flk = false;
                            }
                            else {
                                this.drawcs(graphics, 70, "Wrong Way!", 255, 0, 0, 2);
                                this.flk = true;
                            }
                        }
                    }
                }
            }
            if (this.m.flex != 2) {
                graphics.drawImage(this.dmg, 360, 7, null);
                graphics.drawImage(this.pwr, 360, 27, null);
                graphics.drawImage(this.lap, 19, 7, null);
                graphics.setColor(new Color(0, 0, 100));
                graphics.drawString("" + (madness.nlaps + 1) + " / " + checkPoints.nlaps + "", 51, 18);
                graphics.drawImage(this.was, 92, 7, null);
                graphics.setColor(new Color(0, 0, 100));
                graphics.drawString("" + checkPoints.wasted + " / 4", 150, 18);
                graphics.drawImage(this.pos, 42, 27, null);
                graphics.drawImage(this.rank[checkPoints.pos[madness.im]], 110, 28, null);
                final Medium m = this.m;
                ++m.flex;
            }
            else {
                if (this.posit != checkPoints.pos[madness.im]) {
                    graphics.drawImage(this.rank[checkPoints.pos[madness.im]], 110, 28, null);
                    this.posit = checkPoints.pos[madness.im];
                }
                if (this.wasted != checkPoints.wasted) {
                    graphics.setColor(new Color(this.m.csky[0], this.m.csky[1], this.m.csky[2]));
                    graphics.fillRect(150, 8, 30, 10);
                    graphics.setColor(new Color(0, 0, 100));
                    graphics.drawString("" + checkPoints.wasted + " / 4", 150, 18);
                    this.wasted = checkPoints.wasted;
                }
                if (this.laps != madness.nlaps) {
                    graphics.setColor(new Color(this.m.csky[0], this.m.csky[1], this.m.csky[2]));
                    graphics.fillRect(51, 8, 40, 10);
                    graphics.setColor(new Color(0, 0, 100));
                    graphics.drawString("" + (madness.nlaps + 1) + " / " + checkPoints.nlaps + "", 51, 18);
                    this.laps = madness.nlaps;
                }
            }
            this.drawstat(graphics, madness.maxmag[madness.cn], madness.hitmag, madness.newcar, madness.power);
        }
        if (n == 0) {
            if (this.starcnt != 0) {
                if (this.starcnt == 35 && !this.mutes) {
                    this.three.play();
                }
                if (this.starcnt == 24) {
                    this.gocnt = 2;
                    if (!this.mutes) {
                        this.two.play();
                    }
                }
                if (this.starcnt == 13) {
                    this.gocnt = 1;
                    if (!this.mutes) {
                        this.one.play();
                    }
                }
                if (this.starcnt == 2) {
                    this.gocnt = 0;
                    if (!this.mutes) {
                        this.go.play();
                    }
                }
                if (this.gocnt != 0) {
                    graphics.drawImage(this.cntdn[this.gocnt], 260, 50, null);
                }
                else {
                    graphics.drawImage(this.cntdn[this.gocnt], 238, 50, null);
                }
            }
            if (this.looped != 0 && madness.loop == 2) {
                this.looped = 0;
            }
            if (madness.power < 45.0f) {
                if (this.tcnt == 30 && this.auscnt == 45 && madness.mtouch && madness.capcnt == 0) {
                    if (this.pwcnt < 70) {
                        if (this.looped != 3) {
                            if (this.pwflk) {
                                this.drawcs(graphics, 110, "Power low, perform stunt!", 0, 0, 200, 0);
                                this.pwflk = false;
                            }
                            else {
                                this.drawcs(graphics, 110, "Power low, perform stunt!", 255, 100, 0, 0);
                                this.pwflk = true;
                            }
                        }
                        else if (this.pwflk) {
                            this.drawcs(graphics, 110, ">> Press Enter for game instructions! <<", 0, 0, 200, 0);
                            this.pwflk = false;
                        }
                        else {
                            this.drawcs(graphics, 110, ">> Press Enter for game instructions! <<", 255, 100, 0, 0);
                            this.pwflk = true;
                        }
                    }
                    ++this.pwcnt;
                    if (this.pwcnt == 300) {
                        this.pwcnt = 0;
                        if (this.looped != 0) {
                            ++this.looped;
                            if (this.looped == 4) {
                                this.looped = 2;
                            }
                        }
                    }
                }
            }
            else if (this.pwcnt != 0) {
                this.pwcnt = 0;
            }
            if (madness.capcnt == 0) {
                if (this.tcnt < 30) {
                    if (this.tflk) {
                        if (!this.wasay) {
                            this.drawcs(graphics, 105, this.say, 0, 0, 0, 0);
                        }
                        else {
                            this.drawcs(graphics, 105, this.say, 0, 0, 0, 0);
                        }
                        this.tflk = false;
                    }
                    else {
                        if (!this.wasay) {
                            this.drawcs(graphics, 105, this.say, 0, 128, 255, 0);
                        }
                        else {
                            this.drawcs(graphics, 105, this.say, 255, 128, 0, 0);
                        }
                        this.tflk = true;
                    }
                    ++this.tcnt;
                }
                else if (this.wasay) {
                    this.wasay = false;
                }
                if (this.auscnt < 45) {
                    if (this.aflk) {
                        this.drawcs(graphics, 85, this.asay, 98, 176, 255, 0);
                        this.aflk = false;
                    }
                    else {
                        this.drawcs(graphics, 85, this.asay, 0, 128, 255, 0);
                        this.aflk = true;
                    }
                    ++this.auscnt;
                }
            }
            else if (this.tflk) {
                this.drawcs(graphics, 110, "Bad Landing!", 0, 0, 200, 0);
                this.tflk = false;
            }
            else {
                this.drawcs(graphics, 110, "Bad Landing!", 255, 100, 0, 0);
                this.tflk = true;
            }
            if (madness.trcnt == 10) {
                this.loop = "";
                this.spin = "";
                this.asay = "";
                int n3 = 0;
                while (madness.travzy > 225) {
                    madness.travzy -= 360;
                    ++n3;
                }
                while (madness.travzy < -225) {
                    madness.travzy += 360;
                    --n3;
                }
                if (n3 == 1) {
                    this.loop = "Forward loop";
                }
                if (n3 == 2) {
                    this.loop = "double Forward";
                }
                if (n3 == 3) {
                    this.loop = "triple Forward";
                }
                if (n3 >= 4) {
                    this.loop = "massive Forward looping";
                }
                if (n3 == -1) {
                    this.loop = "Backloop";
                }
                if (n3 == -2) {
                    this.loop = "double Back";
                }
                if (n3 == -3) {
                    this.loop = "triple Back";
                }
                if (n3 <= -4) {
                    this.loop = "massive Back looping";
                }
                if (n3 == 0) {
                    if (madness.ftab && madness.btab) {
                        this.loop = "Tabletop and reversed Tabletop";
                    }
                    else if (madness.ftab || madness.btab) {
                        this.loop = "Tabletop";
                    }
                }
                if (n3 > 0 && madness.btab) {
                    this.loop = "Hanged " + this.loop;
                }
                if (n3 < 0 && madness.ftab) {
                    this.loop = "Hanged " + this.loop;
                }
                if (this.loop != "") {
                    this.asay = this.asay + " " + this.loop;
                }
                int n4 = 0;
                madness.travxy = Math.abs(madness.travxy);
                while (madness.travxy > 270) {
                    madness.travxy -= 360;
                    ++n4;
                }
                if (n4 == 0 && madness.rtab) {
                    if (this.loop == "") {
                        this.spin = "Tabletop";
                    }
                    else {
                        this.spin = "Flipside";
                    }
                }
                if (n4 == 1) {
                    this.spin = "Rollspin";
                }
                if (n4 == 2) {
                    this.spin = "double Rollspin";
                }
                if (n4 == 3) {
                    this.spin = "triple Rollspin";
                }
                if (n4 >= 4) {
                    this.spin = "massive Roll spinning";
                }
                int n5 = 0;
                boolean b2 = false;
                madness.travxz = Math.abs(madness.travxz);
                while (madness.travxz > 90) {
                    madness.travxz -= 180;
                    n5 += 180;
                    if (n5 > 900) {
                        n5 = 900;
                        b2 = true;
                    }
                }
                if (n5 != 0) {
                    if (this.loop == "" && this.spin == "") {
                        this.asay = this.asay + " " + n5;
                        if (b2) {
                            this.asay += " and beyond";
                        }
                    }
                    else {
                        if (this.spin != "") {
                            if (this.loop == "") {
                                this.asay = this.asay + " " + this.spin;
                            }
                            else {
                                this.asay = this.asay + " with " + this.spin;
                            }
                        }
                        this.asay = this.asay + " by " + n5;
                        if (b2) {
                            this.asay += " and beyond";
                        }
                    }
                }
                else if (this.spin != "") {
                    if (this.loop == "") {
                        this.asay = this.asay + " " + this.spin;
                    }
                    else {
                        this.asay = this.asay + " by " + this.spin;
                    }
                }
                if (this.asay != "") {
                    this.auscnt -= 15;
                }
                if (this.loop != "") {
                    this.auscnt -= 25;
                }
                if (this.spin != "") {
                    this.auscnt -= 25;
                }
                if (n5 != 0) {
                    this.auscnt -= 25;
                }
                if (this.auscnt < 45) {
                    if (!this.mutes) {
                        this.powerup.play();
                    }
                    if (this.auscnt < -20) {
                        this.auscnt = -20;
                    }
                    int n6 = 0;
                    if (madness.powerup > 20.0f) {
                        n6 = 1;
                    }
                    if (madness.powerup > 40.0f) {
                        n6 = 2;
                    }
                    if (madness.powerup > 150.0f) {
                        n6 = 3;
                    }
                    if (madness.surfer) {
                        this.asay = " " + this.adj[4][(int)(this.m.random() * 3.0f)] + this.asay;
                    }
                    if (n6 != 3) {
                        this.asay = this.adj[n6][(int)(this.m.random() * 3.0f)] + this.asay + this.exlm[n6];
                    }
                    else {
                        this.asay = this.adj[n6][(int)(this.m.random() * 3.0f)];
                    }
                    if (!this.wasay) {
                        this.tcnt = this.auscnt;
                        if (madness.power != 98.0f) {
                            this.say = "Power Up " + (int)(100.0f * madness.powerup / 98.0f) + "%";
                        }
                        else {
                            this.say = "Power To The MAX";
                        }
                    }
                }
            }
            if (madness.newcar) {
                if (!this.wasay) {
                    this.say = "Car Fixed";
                    this.tcnt = 0;
                }
                if (this.crashup) {
                    this.crashup = false;
                }
                else {
                    this.crashup = true;
                }
            }
            if (this.clear != madness.clear && madness.clear != 0) {
                if (!this.wasay) {
                    this.say = "CheckPoint";
                    this.tcnt = 15;
                }
                this.clear = madness.clear;
                if (!this.mutes) {
                    this.checkpoint.play();
                }
                this.cntovn = 0;
                if (this.cntan != 0) {
                    this.cntan = 0;
                }
            }
            int n7 = 1;
            do {
                if (this.dested[n7] != checkPoints.dested[n7]) {
                    this.dested[n7] = checkPoints.dested[n7];
                    if (this.dested[n7] == 1) {
                        this.wasay = true;
                        this.say = "" + this.names[this.sc[n7]] + " has been wasted!";
                        this.tcnt = -15;
                    }
                    if (this.dested[n7] != 2) {
                        continue;
                    }
                    this.wasay = true;
                    this.say = "You wasted " + this.names[this.sc[n7]] + "!";
                    this.tcnt = -15;
                }
            } while (++n7 < 5);
        }
    }
    
    public void finish(final CheckPoints checkPoints, final ContO[] array, final Control control, final Graphics graphics) {
        graphics.drawImage(this.fleximg, 0, 0, null);
        if (this.winner) {
            if (checkPoints.stage == this.unlocked) {
                if (checkPoints.stage != 11) {
                    graphics.drawImage(this.congrd, 140, 30, null);
                    this.drawcs(graphics, 80, "You completed stage " + checkPoints.stage + " : " + checkPoints.name + " !", 144, 167, 255, 3);
                }
                else {
                    graphics.drawImage(this.congrd, 135 + (int)(this.m.random() * 10.0f), 30, null);
                }
                int n = 0;
                int y = 0;
                this.pin = 60;
                if (checkPoints.stage == 2) {
                    n = 5;
                    y = 60;
                    this.pin = 0;
                    this.sc[0] = 5;
                }
                if (checkPoints.stage == 4) {
                    n = 6;
                    y = 40;
                    this.pin = 0;
                    this.sc[0] = 6;
                }
                if (checkPoints.stage == 6) {
                    n = 7;
                    y = 25;
                    this.pin = 0;
                    this.sc[0] = 7;
                }
                if (checkPoints.stage == 8) {
                    n = 8;
                    y = 70;
                    this.pin = 0;
                    this.sc[0] = 8;
                }
                if (checkPoints.stage == 10) {
                    n = 9;
                    y = 80;
                    this.pin = 0;
                    this.sc[0] = 9;
                }
                if (checkPoints.stage != 11) {
                    graphics.setFont(new Font("SansSerif", 1, 13));
                    this.ftm = graphics.getFontMetrics();
                    if (this.aflk) {
                        this.drawcs(graphics, 120 + this.pin, "Stage " + (checkPoints.stage + 1) + " unlocked!", 144, 167, 255, 3);
                    }
                    else {
                        this.drawcs(graphics, 120 + this.pin, "Stage " + (checkPoints.stage + 1) + " unlocked!", 208, 240, 255, 3);
                    }
                    if (n != 0) {
                        if (this.aflk) {
                            this.drawcs(graphics, 150, "And:", 144, 167, 255, 3);
                        }
                        else {
                            this.drawcs(graphics, 150, "And:", 208, 240, 255, 3);
                        }
                        graphics.setColor(new Color(208, 240, 255));
                        graphics.fillRect(200, 170, 150, 100);
                        graphics.setColor(new Color(176, 217, 240));
                        graphics.fillRect(201, 171, 148, 4);
                        graphics.fillRect(201, 171, 4, 98);
                        graphics.fillRect(201, 265, 148, 4);
                        graphics.fillRect(345, 171, 4, 98);
                        array[n].y = y;
                        this.m.crs = true;
                        this.m.x = -275;
                        this.m.y = -500;
                        this.m.z = -50;
                        this.m.xz = 0;
                        this.m.zy = 10;
                        this.m.ground = 2470;
                        array[n].z = 1500;
                        array[n].x = 0;
                        final ContO contO = array[n];
                        contO.xz += 5;
                        array[n].zy = 0;
                        final ContO contO2 = array[n];
                        contO2.wzy -= 10;
                        array[n].d(graphics);
                        if (this.aflk) {
                            graphics.setColor(new Color(0, 128, 255));
                        }
                        else {
                            graphics.setColor(new Color(255, 0, 0));
                        }
                        graphics.drawRect(197, 167, 155, 105);
                        if (this.aflk) {
                            this.drawcs(graphics, 300, "" + this.names[n] + " has been unlocked!", 144, 167, 255, 3);
                        }
                        else {
                            this.drawcs(graphics, 300, "" + this.names[n] + " has been unlocked!", 208, 240, 255, 3);
                        }
                    }
                    graphics.setFont(new Font("SansSerif", 1, 11));
                    this.ftm = graphics.getFontMetrics();
                    if (this.aflk) {
                        this.drawcs(graphics, 335 - this.pin, "( Game Saved )", 112, 120, 143, 3);
                    }
                    else {
                        this.drawcs(graphics, 335 - this.pin, "( Game Saved )", 150, 150, 150, 3);
                    }
                }
                else {
                    graphics.setFont(new Font("SansSerif", 1, 13));
                    this.ftm = graphics.getFontMetrics();
                    if (this.aflk) {
                        this.drawcs(graphics, 120, "Woohoooo you finished the game!!!", 144, 167, 255, 3);
                    }
                    else {
                        this.drawcs(graphics, 120, "Woohoooo you finished the game!!!", 208, 240, 255, 3);
                    }
                    if (this.aflk) {
                        this.drawcs(graphics, 150, "Your Awesome!", 144, 167, 255, 3);
                    }
                    else {
                        this.drawcs(graphics, 152, "Your Awesome!", 228, 240, 255, 3);
                    }
                    if (this.aflk) {
                        this.drawcs(graphics, 200, "Your Truely a RADICAL GAMER!", 144, 167, 255, 3);
                    }
                    else {
                        this.drawcs(graphics, 200, "Your Truely a RADICAL GAMER!", 228, 240, 255, 3);
                    }
                    graphics.drawImage(this.radicalplay, 95, 205, null);
                    graphics.setFont(new Font("SansSerif", 1, 11));
                    this.ftm = graphics.getFontMetrics();
                    if (this.aflk) {
                        this.drawcs(graphics, 270, "Be sure to check Radicalplay.com for more action!", 144, 167, 255, 3);
                    }
                    else {
                        this.drawcs(graphics, 270, "Be sure to check Radicalplay.com for more action!", 208, 240, 255, 3);
                    }
                    this.pin = 40;
                }
                if (this.aflk) {
                    this.aflk = false;
                }
                else {
                    this.aflk = true;
                }
            }
            else {
                this.pin = 100;
                graphics.drawImage(this.congrd, 140, 117, null);
                this.drawcs(graphics, 167, "You completed stage " + checkPoints.stage + " : " + checkPoints.name + " !", 144, 167, 255, 3);
            }
        }
        else {
            this.pin = 100;
            graphics.drawImage(this.gameov, 190, 117, null);
            this.drawcs(graphics, 167, "You lost at stage " + checkPoints.stage + " : " + checkPoints.name + " !", 144, 167, 255, 3);
            if (checkPoints.stage == this.unlocked) {
                if (checkPoints.stage == 1) {
                    this.drawcs(graphics, 205, "> Don't forget, you must pass in all checkpoints to complete a lap...", 128, 128, 128, 3);
                }
                if (checkPoints.stage == 2) {
                    this.drawcs(graphics, 205, "> Don't forget, you need power to be up to race faster...", 128, 128, 128, 3);
                }
                if (checkPoints.stage == 3) {
                    this.drawcs(graphics, 205, "> Hint: its easier to waste the other cars then to finish 1st in this stage...", 128, 128, 128, 3);
                    this.drawcs(graphics, 220, "( Press [A] to make Guidance Arrow point to cars )", 128, 128, 128, 3);
                }
                if (checkPoints.stage == 4) {
                    this.drawcs(graphics, 205, "> Remember the better the stunt the better the power you get...", 128, 128, 128, 3);
                }
                if (checkPoints.stage == 5) {
                    this.drawcs(graphics, 205, "> Remember the more the power the more faster and powerful your car is...", 128, 128, 128, 3);
                }
            }
        }
        graphics.drawImage(this.contin2[this.pcontin], 230, 350 - this.pin, null);
        if (control.enter || control.handb) {
            this.fase = 10;
            if (this.loadedt[checkPoints.stage - 1]) {
                this.stracks[checkPoints.stage - 1].stop();
            }
            if (checkPoints.stage == this.unlocked && this.winner && this.unlocked != 11) {
                ++checkPoints.stage;
                ++this.unlocked;
            }
            control.enter = false;
            control.handb = false;
        }
    }
    
    public void sortcars(final int n) {
        if (n != 0) {
            final boolean[] array = new boolean[5];
            if (this.unlocked == n && this.unlocked != 11) {
                this.sc[4] = 4 + (n + 1) / 2;
                int n2 = 1;
                do {
                    array[n2] = false;
                    while (!array[n2]) {
                        this.sc[n2] = (int)(Math.random() * (4 + (n + 1) / 2));
                        array[n2] = true;
                        int n3 = 0;
                        do {
                            if (n2 != n3 && this.sc[n2] == this.sc[n3]) {
                                array[n2] = false;
                            }
                        } while (++n3 < 5);
                        if (Math.random() < this.proba[this.sc[n2]]) {
                            array[n2] = false;
                        }
                        if (this.sc[n2] == 1 && n == 6) {
                            array[n2] = false;
                        }
                        if (this.sc[n2] == 7 && (n == 8 || n == 9 || n == 7)) {
                            array[n2] = false;
                        }
                    }
                } while (++n2 < 4);
                if (n == 10) {
                    if (this.sc[0] != 7) {
                        boolean b = false;
                        int n4 = 0;
                        do {
                            if (this.sc[n4] == 7) {
                                b = true;
                            }
                        } while (++n4 < 5);
                        if (!b) {
                            if (Math.random() > Math.random()) {
                                this.sc[1] = 7;
                            }
                            else {
                                this.sc[2] = 7;
                            }
                        }
                    }
                    else {
                        boolean b2 = false;
                        int n5 = 0;
                        do {
                            if (this.sc[n5] == 8) {
                                b2 = true;
                            }
                        } while (++n5 < 5);
                        if (!b2) {
                            if (Math.random() > Math.random()) {
                                this.sc[1] = 8;
                            }
                            this.sc[2] = 8;
                        }
                    }
                }
            }
            else {
                int n6 = 5;
                if (this.sc[0] != 4 + (n + 1) / 2 && n != 11) {
                    this.sc[4] = 4 + (n + 1) / 2;
                    n6 = 4;
                }
                for (int i = 1; i < n6; ++i) {
                    array[i] = false;
                    while (!array[i]) {
                        int unlocked = this.unlocked;
                        if (unlocked == 11) {
                            unlocked = 10;
                        }
                        this.sc[i] = (int)(Math.random() * (5 + (unlocked + 1) / 2));
                        array[i] = true;
                        int n7 = 0;
                        do {
                            if (i != n7 && this.sc[i] == this.sc[n7]) {
                                array[i] = false;
                            }
                        } while (++n7 < 5);
                        if (Math.random() < this.proba[this.sc[i]]) {
                            array[i] = false;
                        }
                    }
                }
            }
        }
    }
    
    public void sparkeng(int n) {
        ++n;
        int n2 = 0;
        do {
            if (n == n2) {
                if (this.pengs[n2]) {
                    continue;
                }
                this.engs[this.enginsignature[this.sc[0]][n2]][n2].loop();
                this.pengs[n2] = true;
            }
            else {
                if (!this.pengs[n2]) {
                    continue;
                }
                this.engs[this.enginsignature[this.sc[0]][n2]][n2].stop();
                this.pengs[n2] = false;
            }
        } while (++n2 < 5);
    }
    
    public void drawcs(final Graphics graphics, final int n, final String str, int r, int g, int b, final int n2) {
        if (n2 != 3 && n2 != 4) {
            r += (int)(r * (this.m.snap[0] / 100.0f));
            if (r > 255) {
                r = 255;
            }
            if (r < 0) {
                r = 0;
            }
            g += (int)(g * (this.m.snap[1] / 100.0f));
            if (g > 255) {
                g = 255;
            }
            if (g < 0) {
                g = 0;
            }
            b += (int)(b * (this.m.snap[2] / 100.0f));
            if (b > 255) {
                b = 255;
            }
            if (b < 0) {
                b = 0;
            }
        }
        if (n2 == 4) {
            r -= (int)(r * (this.m.snap[0] / 100.0f));
            if (r > 255) {
                r = 255;
            }
            if (r < 0) {
                r = 0;
            }
            g -= (int)(g * (this.m.snap[1] / 100.0f));
            if (g > 255) {
                g = 255;
            }
            if (g < 0) {
                g = 0;
            }
            b -= (int)(b * (this.m.snap[2] / 100.0f));
            if (b > 255) {
                b = 255;
            }
            if (b < 0) {
                b = 0;
            }
        }
        if (n2 == 1) {
            graphics.setColor(new Color(0, 0, 0));
            graphics.drawString(str, 275 - this.ftm.stringWidth(str) / 2 + 1, n + 1);
        }
        if (n2 == 2) {
            graphics.setColor(new Color((r + this.m.csky[0] * 2) / 3, (g + this.m.csky[1] * 2) / 3, (b + this.m.csky[2] * 2) / 3));
            graphics.drawString(str, 275 - this.ftm.stringWidth(str) / 2 + 1, n + 1);
        }
        graphics.setColor(new Color(r, g, b));
        graphics.drawString(str, 275 - this.ftm.stringWidth(str) / 2, n);
    }
    
    public int py(final int n, final int n2, final int n3, final int n4) {
        return (n - n2) * (n - n2) + (n3 - n4) * (n3 - n4);
    }
    
    public void trackbg(final Graphics graphics) {
        if (this.stages.posit() > 240000 || this.m.nrnd <= 1) {
            graphics.drawImage(this.trackbg, 0, 0, null);
        }
    }
    
    public void stageselect(final Graphics graphics, final CheckPoints checkPoints, final Control control) {
        this.stages.play();
        graphics.drawImage(this.bl, 0, 0, null);
        graphics.drawImage(this.bt, 0, 0, null);
        graphics.drawImage(this.br, 509, 0, null);
        graphics.drawImage(this.bb, 0, 357, null);
        graphics.drawImage(this.select, 201, 45, null);
        if (checkPoints.stage != 1) {
            graphics.drawImage(this.back[this.pback], 50, 110, null);
        }
        if (checkPoints.stage != 11) {
            graphics.drawImage(this.next[this.pnext], 440, 110, null);
        }
        graphics.drawImage(this.contin1[this.pcontin], 232, 170, null);
        graphics.setFont(new Font("SansSerif", 1, 13));
        this.ftm = graphics.getFontMetrics();
        if (checkPoints.stage != 11) {
            this.drawcs(graphics, 110, "Stage " + checkPoints.stage + "  >", 255, 255, 255, 3);
        }
        else {
            this.drawcs(graphics, 110, "Final Party Stage  >", 255, 255, 255, 3);
        }
        this.drawcs(graphics, 130, "| " + checkPoints.name + " |", 32, 48, 98, 3);
        graphics.setFont(new Font("SansSerif", 1, 11));
        this.ftm = graphics.getFontMetrics();
        this.drawcs(graphics, 396, "Use keyboard Arrows and press Enter to continue", 0, 0, 0, 3);
        if (control.handb || control.enter) {
            this.fase = 5;
            this.m.trk = false;
            control.handb = false;
            control.enter = false;
            this.stages.stop();
        }
        if (control.right && checkPoints.stage != 11) {
            if (checkPoints.stage != this.unlocked) {
                ++checkPoints.stage;
                this.fase = 2;
                control.right = false;
            }
            else {
                this.fase = 4;
                this.lockcnt = 70;
                control.right = false;
            }
        }
        if (control.left && checkPoints.stage != 1) {
            --checkPoints.stage;
            this.fase = 2;
            control.left = false;
        }
    }
    
    public void snap(final int n) {
        this.dmg = this.loadsnap(this.odmg);
        this.pwr = this.loadsnap(this.opwr);
        this.was = this.loadsnap(this.owas);
        this.lap = this.loadsnap(this.olap);
        this.pos = this.loadsnap(this.opos);
        int n2 = 0;
        do {
            this.rank[n2] = this.loadsnap(this.orank[n2]);
        } while (++n2 < 5);
        int n3 = 0;
        do {
            this.cntdn[n3] = this.loadsnap(this.ocntdn[n3]);
        } while (++n3 < 4);
        this.yourwasted = this.loadsnap(this.oyourwasted);
        this.youlost = this.loadsnap(this.oyoulost);
        this.youwon = this.loadsnap(this.oyouwon);
        this.youwastedem = this.loadsnap(this.oyouwastedem);
        this.gameh = this.loadsnap(this.ogameh);
        this.mdness = this.loadsnap(this.omdness);
        this.loadingmusic = this.loadopsnap(this.oloadingmusic, n);
        this.star[0] = this.loadopsnap(this.ostar[0], n);
        this.star[1] = this.loadopsnap(this.ostar[1], n);
    }
    
    private Image loadsnap(final Image img) {
        final int height = img.getHeight(this.ob);
        final int width = img.getWidth(this.ob);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(img, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < width * height; ++i) {
            if (array[i] != -4144960 && array[i] != array[width * height - 1]) {
                final Color color = new Color(array[i]);
                int r = (int)(color.getRed() + color.getRed() * (this.m.snap[0] / 100.0f));
                if (r > 225) {
                    r = 225;
                }
                if (r < 0) {
                    r = 0;
                }
                int g = (int)(color.getGreen() + color.getGreen() * (this.m.snap[1] / 100.0f));
                if (g > 225) {
                    g = 225;
                }
                if (g < 0) {
                    g = 0;
                }
                int b = (int)(color.getBlue() + color.getBlue() * (this.m.snap[2] / 100.0f));
                if (b > 225) {
                    b = 225;
                }
                if (b < 0) {
                    b = 0;
                }
                array[i] = new Color(r, g, b).getRGB();
            }
            else if (array[i] == -4144960) {
                array[i] = new Color(this.m.csky[0], this.m.csky[1], this.m.csky[2]).getRGB();
            }
        }
        return this.createImage(new MemoryImageSource(width, height, array, 0, width));
    }
    
    public void resetstat(final int n) {
        this.arrace = false;
        this.ana = 0;
        this.cntan = 0;
        this.cntovn = 0;
        this.tcnt = 30;
        this.wasay = false;
        this.clear = 0;
        this.dmcnt = 0;
        this.pwcnt = 0;
        this.auscnt = 45;
        this.pnext = 0;
        this.pback = 0;
        this.starcnt = 90;
        this.gocnt = 3;
        this.grrd = true;
        this.aird = true;
        this.bfcrash = 0;
        this.cntwis = 0;
        this.bfskid = 0;
        this.pwait = 7;
        this.holdcnt = 0;
        this.winner = false;
        this.wasted = 0;
        int n2 = 0;
        do {
            this.dested[n2] = 0;
        } while (++n2 < 5);
        this.sortcars(n);
    }
    
    public void loadpak2(final MediaTracker mediaTracker, final Toolkit toolkit) {
        try {
            final DataInputStream in = new DataInputStream(new URL(this.app.getCodeBase(), "graphics/images2.radq").openStream());
            final ZipInputStream zipInputStream = new ZipInputStream(in);
            for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
                int i = (int)zipEntry.getSize();
                final String name = zipEntry.getName();
                final byte[] b = new byte[i];
                int off = 0;
                while (i > 0) {
                    final int read = zipInputStream.read(b, off, i);
                    off += read;
                    i -= read;
                }
                if (name.equals("1c.gif")) {
                    this.ocntdn[1] = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("2c.gif")) {
                    this.ocntdn[2] = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("3c.gif")) {
                    this.ocntdn[3] = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("2.gif")) {
                    this.orank[1] = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("3.gif")) {
                    this.orank[2] = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("4.gif")) {
                    this.orank[3] = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("5.gif")) {
                    this.orank[4] = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("bgmain.jpg")) {
                    this.bgmain = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("br.gif")) {
                    this.br = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("loadingmusic.gif")) {
                    this.oloadingmusic = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("main.gif")) {
                    this.maini = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("radicalplay.gif")) {
                    this.radicalplay = this.loadimage(b, mediaTracker, toolkit);
                }
            }
            in.close();
            zipInputStream.close();
        }
        catch (Exception obj) {
            System.out.println("Error Reading Images Pak 2: " + obj);
        }
        System.gc();
    }
    
    public void drawstat(final Graphics graphics, final int n, int n2, final boolean b, final float n3) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        if (b) {
            array[0] = 423;
            array2[0] = 11;
            array[1] = 423;
            array2[1] = 19;
            array[2] = 520;
            array2[2] = 19;
            array[3] = 520;
            array2[3] = 11;
            graphics.setColor(new Color(this.m.csky[0], this.m.csky[1], this.m.csky[2]));
            graphics.fillPolygon(array, array2, 4);
        }
        if (n2 > n) {
            n2 = n;
        }
        final int n4 = (int)(98.0f * (n2 / (float)n));
        array[0] = 422;
        array2[0] = 11;
        array[1] = 422;
        array2[1] = 20;
        array[2] = 422 + n4;
        array2[2] = 20;
        array[3] = 422 + n4;
        array2[3] = 11;
        final int n5 = 244;
        int n6 = 244;
        final int n7 = 11;
        if (n4 > 33) {
            n6 = (int)(244.0f - 233.0f * ((n4 - 33) / 65.0f));
        }
        if (n4 > 70) {
            if (this.dmcnt < 10) {
                if (this.dmflk) {
                    n6 = 170;
                    this.dmflk = false;
                }
                else {
                    this.dmflk = true;
                }
            }
            ++this.dmcnt;
            if (this.dmcnt > 167.0 - n4 * 1.5) {
                this.dmcnt = 0;
            }
        }
        int r = (int)(n5 + n5 * (this.m.snap[0] / 100.0f));
        if (r > 255) {
            r = 255;
        }
        if (r < 0) {
            r = 0;
        }
        int g = (int)(n6 + n6 * (this.m.snap[1] / 100.0f));
        if (g > 255) {
            g = 255;
        }
        if (g < 0) {
            g = 0;
        }
        int b2 = (int)(n7 + n7 * (this.m.snap[2] / 100.0f));
        if (b2 > 255) {
            b2 = 255;
        }
        if (b2 < 0) {
            b2 = 0;
        }
        graphics.setColor(new Color(r, g, b2));
        graphics.fillPolygon(array, array2, 4);
        array[0] = 422;
        array2[0] = 31;
        array[1] = 422;
        array2[1] = 40;
        array[2] = (int)(422.0f + n3);
        array2[2] = 40;
        array[3] = (int)(422.0f + n3);
        array2[3] = 31;
        int n8 = 128;
        if (n3 == 98.0f) {
            n8 = 64;
        }
        int n9 = (int)(190.0 + n3 * 0.37);
        int n10 = 244;
        if (this.auscnt < 45 && this.aflk) {
            n8 = 128;
            n9 = 244;
            n10 = 244;
        }
        int r2 = (int)(n8 + n8 * (this.m.snap[0] / 100.0f));
        if (r2 > 255) {
            r2 = 255;
        }
        if (r2 < 0) {
            r2 = 0;
        }
        int g2 = (int)(n9 + n9 * (this.m.snap[1] / 100.0f));
        if (g2 > 255) {
            g2 = 255;
        }
        if (g2 < 0) {
            g2 = 0;
        }
        int b3 = (int)(n10 + n10 * (this.m.snap[2] / 100.0f));
        if (b3 > 255) {
            b3 = 255;
        }
        if (b3 < 0) {
            b3 = 0;
        }
        graphics.setColor(new Color(r2, g2, b3));
        graphics.fillPolygon(array, array2, 4);
        if (this.m.flex == 2 && n3 != 98.0f) {
            array[0] = (int)(422.0f + n3);
            array2[0] = 31;
            array[1] = (int)(422.0f + n3);
            array2[1] = 39;
            array[2] = 520;
            array2[2] = 39;
            array[3] = 520;
            array2[3] = 31;
            graphics.setColor(new Color(this.m.csky[0], this.m.csky[1], this.m.csky[2]));
            graphics.fillPolygon(array, array2, 4);
        }
    }
    
    private Image bressed(final Image img) {
        final int height = img.getHeight(this.ob);
        final int width = img.getWidth(this.ob);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(img, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        final Color color = new Color(206, 214, 255);
        for (int i = 0; i < width * height; ++i) {
            if (array[i] != array[width * height - 1]) {
                array[i] = color.getRGB();
            }
        }
        return this.createImage(new MemoryImageSource(width, height, array, 0, width));
    }
    
    public void loading(final Graphics graphics, final Applet applet) {
        graphics.setColor(new Color(0, 0, 0));
        graphics.fillRect(0, 0, 550, 400);
        graphics.drawImage(this.sign, 237, 10, this);
        graphics.drawImage(this.hello, 0, 80, this);
        graphics.setColor(new Color(198, 214, 255));
        graphics.fillRoundRect(125, 315, 300, 80, 30, 70);
        graphics.setColor(new Color(128, 167, 255));
        graphics.drawRoundRect(125, 315, 300, 80, 30, 70);
        graphics.drawImage(this.loadbar, 156, 340, this);
        graphics.setFont(new Font("SansSerif", 1, 11));
        this.ftm = graphics.getFontMetrics();
        this.drawcs(graphics, 333, "Loading game, please wait.", 0, 0, 0, 3);
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(170, 373, 210, 17);
        this.drawcs(graphics, 385, "" + (int)((26.0f + this.dnload / (float)this.kbload * 200.0f) / 226.0f * 100.0f) + " % loaded    |    " + (this.kbload - this.dnload) + " KB remaining", 32, 64, 128, 3);
        graphics.setColor(new Color(32, 64, 128));
        graphics.fillRect(162, 346, 26 + (int)(this.dnload / (float)this.kbload * 200.0f), 10);
        applet.repaint();
    }
    
    public xtGraphics(final Medium m, final Graphics graphics, final Applet app, final int n) {
        this.fase = 111;
        this.oldfase = 0;
        this.starcnt = 0;
        this.unlocked = 1;
        this.lockcnt = 0;
        this.opselect = 1;
        this.shaded = false;
        this.flipo = 0;
        this.nextc = false;
        this.gatey = 0;
        this.looped = 1;
        this.sc = new int[5];
        this.xstart = new int[] { 0, -350, 350, 0, 0 };
        this.zstart = new int[] { -100, 500, 500, 500, 1100 };
        this.proba = new float[] { 0.3f, 0.8f, 0.5f, 0.3f, 0.8f, 0.0f, 0.2f, 0.4f, 0.0f, 0.0f };
        this.dishandle = new float[] { 0.65f, 0.6f, 0.55f, 0.77f, 0.7f, 0.9f, 0.7f, 0.4f, 1.0f, 0.85f };
        this.holdcnt = 0;
        this.winner = false;
        this.flexpix = new int[220000];
        this.next = new Image[3];
        this.back = new Image[3];
        this.contin1 = new Image[2];
        this.contin2 = new Image[2];
        this.ostar = new Image[2];
        this.star = new Image[3];
        this.pcontin = 0;
        this.pnext = 0;
        this.pback = 0;
        this.pstar = 0;
        this.orank = new Image[5];
        this.rank = new Image[5];
        this.ocntdn = new Image[4];
        this.cntdn = new Image[4];
        this.gocnt = 0;
        this.engs = new AudioClip[2][5];
        this.pengs = new boolean[5];
        final int[][] enginsignature = { { 0, 0, 1, 1, 0 }, { 0, 1, 1, 0, 1 }, new int[5], { 0, 1, 1, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 1, 1, 1 }, { 0, 1, 0, 1, 0 }, null, null, null };
        final int n2 = 7;
        final int[] array = new int[5];
        array[0] = 1;
        enginsignature[n2] = array;
        enginsignature[8] = new int[] { 0, 1, 1, 1, 1 };
        enginsignature[9] = new int[] { 1, 1, 1, 1, 1 };
        this.enginsignature = enginsignature;
        this.air = new AudioClip[6];
        this.aird = false;
        this.grrd = false;
        this.crash = new AudioClip[3];
        this.lowcrash = new AudioClip[3];
        this.pwastd = false;
        this.skid = new AudioClip[2];
        this.dustskid = new AudioClip[2];
        this.mutes = false;
        this.stracks = new RadicalMod[11];
        this.loadedt = new boolean[11];
        this.mutem = false;
        this.arrace = false;
        this.ana = 0;
        this.cntan = 0;
        this.cntovn = 0;
        this.flk = false;
        this.tcnt = 30;
        this.tflk = false;
        this.say = "";
        this.wasay = false;
        this.clear = 0;
        this.posit = 0;
        this.wasted = 0;
        this.laps = 0;
        this.dested = new int[5];
        this.names = new String[] { "Tornado Shark", "Formula 7", "Wow Caninaro", "La vite Crab", "Nimi", "MAX Revenge", "Lead Oxide", "EL KING", "Radical One", "DR Monstaa" };
        this.dmcnt = 0;
        this.dmflk = false;
        this.pwcnt = 0;
        this.pwflk = false;
        this.adj = new String[][] { { "Cool", "Alright", "Nice" }, { "Wicked", "Amazing", "Super" }, { "Awesome", "Ripping", "Radical" }, { "What the...?", "Your a super star!!!!", "Who are you again...?" }, { "surf style", "off the lip", "bounce back" } };
        this.exlm = new String[] { "!", "!!", "!!!" };
        this.loop = "";
        this.spin = "";
        this.asay = "";
        this.auscnt = 45;
        this.aflk = false;
        this.hipno = new int[] { 0, 2, 2, 2, 2, 0, 2, 50, 2, 2, 6 };
        this.sndsize = new int[] { 39, 128, 23, 58, 106, 140, 81, 135, 38, 141, 80 };
        this.kbload = 0;
        this.dnload = 0;
        this.pin = 60;
        this.pwait = 7;
        this.stopcnt = 0;
        this.cntwis = 0;
        this.crshturn = 0;
        this.bfcrash = 0;
        this.bfskid = 0;
        this.crashup = false;
        this.skflg = 0;
        this.dskflg = 0;
        this.m = m;
        this.app = app;
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        final MediaTracker mediaTracker = new MediaTracker(this.app);
        mediaTracker.addImage(this.hello = this.app.getImage(this.app.getCodeBase(), "hello.gif"), 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (Exception ex) {}
        mediaTracker.addImage(this.sign = this.app.getImage(this.app.getCodeBase(), "sign.gif"), 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (Exception ex2) {}
        mediaTracker.addImage(this.loadbar = this.app.getImage(this.app.getCodeBase(), "loadbar.gif"), 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (Exception ex3) {}
        this.kbload = 416;
        if (n == 2) {
            this.kbload = 514;
        }
        this.loading(graphics, this.app);
        this.loadpak1(mediaTracker, defaultToolkit);
        this.dnload += 47;
        this.loading(graphics, this.app);
        this.loadpak2(mediaTracker, defaultToolkit);
        this.dnload += 44;
        this.loading(graphics, this.app);
        this.loadpak3(mediaTracker, defaultToolkit);
        this.dnload += 47;
        this.loading(graphics, this.app);
        this.loadpak4(mediaTracker, defaultToolkit);
        this.dnload += 44;
        this.loading(graphics, this.app);
        this.next[1] = this.pressed(this.next[0]);
        this.back[1] = this.pressed(this.back[0]);
        this.next[2] = this.bressed(this.next[0]);
        this.back[2] = this.bressed(this.back[0]);
        this.contin1[1] = this.pressed(this.contin1[0]);
        this.contin2[1] = this.bressed(this.contin2[0]);
        this.contin1[1] = this.pressed(this.contin1[0]);
        this.contin2[1] = this.bressed(this.contin2[0]);
        this.star[2] = this.pressed(this.ostar[1]);
        String str = "default/";
        if (n == 2) {
            str = "newsun/";
        }
        int n3 = 0;
        do {
            this.engs[0][n3] = this.getSound("sounds/" + str + "a" + n3 + ".au");
            this.dnload += 2;
            this.loading(graphics, this.app);
            this.engs[1][n3] = this.getSound("sounds/" + str + "b" + n3 + ".au");
            this.dnload += 3;
            this.loading(graphics, this.app);
            this.pengs[n3] = false;
        } while (++n3 < 5);
        int i = 0;
        do {
            this.air[i] = this.getSound("sounds/" + str + "air" + i + ".au");
            this.dnload += 2;
            this.loading(graphics, this.app);
        } while (++i < 6);
        int n4 = 0;
        do {
            this.crash[n4] = this.getSound("sounds/" + str + "crash" + (n4 + 1) + ".au");
            if (n == 2) {
                this.dnload += 12;
                this.loading(graphics, this.app);
            }
            else {
                this.dnload += 7;
                this.loading(graphics, this.app);
            }
        } while (++n4 < 3);
        int n5 = 0;
        do {
            this.lowcrash[n5] = this.getSound("sounds/" + str + "lowcrash" + (n5 + 1) + ".au");
            if (n == 2) {
                this.dnload += 8;
                this.loading(graphics, this.app);
            }
            else {
                this.dnload += 3;
                this.loading(graphics, this.app);
            }
        } while (++n5 < 3);
        this.tires = this.getSound("sounds/" + str + "tires.au");
        if (n == 2) {
            this.dnload += 12;
            this.loading(graphics, this.app);
        }
        else {
            this.dnload += 4;
            this.loading(graphics, this.app);
        }
        this.checkpoint = this.getSound("sounds/" + str + "checkpoint.au");
        if (n == 2) {
            this.dnload += 12;
            this.loading(graphics, this.app);
        }
        else {
            this.dnload += 7;
            this.loading(graphics, this.app);
        }
        this.carfixed = this.getSound("sounds/" + str + "carfixed.au");
        if (n == 2) {
            this.dnload += 16;
            this.loading(graphics, this.app);
        }
        else {
            this.dnload += 12;
            this.loading(graphics, this.app);
        }
        this.powerup = this.getSound("sounds/" + str + "powerup.au");
        if (n == 2) {
            this.dnload += 12;
            this.loading(graphics, this.app);
        }
        else {
            this.dnload += 9;
            this.loading(graphics, this.app);
        }
        this.three = this.getSound("sounds/" + str + "three.au");
        if (n == 2) {
            this.dnload += 12;
            this.loading(graphics, this.app);
        }
        else {
            this.dnload += 4;
            this.loading(graphics, this.app);
        }
        this.two = this.getSound("sounds/" + str + "two.au");
        if (n == 2) {
            this.dnload += 12;
            this.loading(graphics, this.app);
        }
        else {
            this.dnload += 3;
            this.loading(graphics, this.app);
        }
        this.one = this.getSound("sounds/" + str + "one.au");
        if (n == 2) {
            this.dnload += 12;
            this.loading(graphics, this.app);
        }
        else {
            this.dnload += 4;
            this.loading(graphics, this.app);
        }
        this.go = this.getSound("sounds/" + str + "go.au");
        if (n == 2) {
            this.dnload += 12;
            this.loading(graphics, this.app);
        }
        else {
            this.dnload += 4;
            this.loading(graphics, this.app);
        }
        int n6 = 0;
        do {
            this.skid[n6] = this.getSound("sounds/" + str + "skid" + (n6 + 1) + ".au");
            if (n == 2) {
                this.dnload += 9;
                this.loading(graphics, this.app);
            }
            else {
                this.dnload += 6;
                this.loading(graphics, this.app);
            }
        } while (++n6 < 2);
        int n7 = 0;
        do {
            this.dustskid[n7] = this.getSound("sounds/" + str + "dustskid" + (n7 + 1) + ".au");
            if (n == 2) {
                this.dnload += 11;
                this.loading(graphics, this.app);
            }
            else {
                this.dnload += 7;
                this.loading(graphics, this.app);
            }
        } while (++n7 < 2);
        this.wastd = this.getSound("sounds/" + str + "wasted.au");
        this.dnload += 5;
        this.loading(graphics, this.app);
        this.firewasted = this.getSound("sounds/" + str + "firewasted.au");
        if (n == 2) {
            this.dnload += 13;
            this.loading(graphics, this.app);
        }
        else {
            this.dnload += 12;
            this.loading(graphics, this.app);
        }
        this.cars = new RadicalMod("music/cars.radq", 500, 7900, 125, this.app);
        this.dnload += 26;
        this.loading(graphics, this.app);
        this.stages = new RadicalMod("music/stages.radq", 200, 9000, 145, this.app);
        this.dnload += 22;
        this.loading(graphics, this.app);
        int n8 = 0;
        do {
            this.loadedt[n8] = false;
        } while (++n8 < 10);
    }
    
    public void maini(final Graphics graphics, final Control control) {
        if (this.flipo == 0) {
            graphics.drawImage(this.bgmain, 0, 0, null);
            graphics.drawImage(this.maini, 0, 0, null);
            this.flipo = 1;
        }
        if (control.up) {
            --this.opselect;
            if (this.opselect == -1) {
                this.opselect = 2;
            }
            control.up = false;
        }
        if (control.down) {
            ++this.opselect;
            if (this.opselect == 3) {
                this.opselect = 0;
            }
            control.down = false;
        }
        if (this.opselect == 0) {
            if (this.shaded) {
                graphics.setColor(new Color(32, 48, 160));
                graphics.fillRect(218, 246, 110, 22);
                this.aflk = false;
            }
            if (this.aflk) {
                graphics.setColor(new Color(143, 159, 239));
                this.aflk = false;
            }
            else {
                graphics.setColor(new Color(0, 0, 255));
                this.aflk = true;
            }
            graphics.drawRoundRect(218, 246, 110, 22, 7, 20);
        }
        else {
            graphics.setColor(new Color(0, 0, 0));
            graphics.drawRoundRect(218, 246, 110, 22, 7, 20);
        }
        if (this.opselect == 1) {
            if (this.shaded) {
                graphics.setColor(new Color(32, 48, 160));
                graphics.fillRect(174, 275, 196, 22);
                this.aflk = false;
            }
            if (this.aflk) {
                graphics.setColor(new Color(143, 159, 239));
                this.aflk = false;
            }
            else {
                graphics.setColor(new Color(0, 0, 255));
                this.aflk = true;
            }
            graphics.drawRoundRect(174, 275, 196, 22, 7, 20);
        }
        else {
            graphics.setColor(new Color(0, 0, 0));
            graphics.drawRoundRect(174, 275, 196, 22, 7, 20);
        }
        if (this.opselect == 2) {
            if (this.shaded) {
                graphics.setColor(new Color(32, 48, 160));
                graphics.fillRect(230, 306, 85, 22);
                this.aflk = false;
            }
            if (this.aflk) {
                graphics.setColor(new Color(143, 159, 239));
                this.aflk = false;
            }
            else {
                graphics.setColor(new Color(0, 0, 255));
                this.aflk = true;
            }
            graphics.drawRoundRect(230, 306, 85, 22, 7, 20);
        }
        else {
            graphics.setColor(new Color(0, 0, 0));
            graphics.drawRoundRect(230, 306, 85, 22, 7, 20);
        }
        graphics.drawImage(this.opti, 181, 250, null);
        if (control.enter || control.handb) {
            if (this.opselect == 0) {
                if (this.unlocked == 1 && this.oldfase == 0) {
                    this.oldfase = 7;
                    this.fase = 11;
                }
                else {
                    this.fase = -9;
                }
            }
            if (this.opselect == 1) {
                this.oldfase = 10;
                this.fase = 11;
            }
            if (this.opselect == 2) {
                this.fase = 8;
            }
            this.flipo = 0;
            control.enter = false;
            control.handb = false;
        }
    }
    
    public void musicomp(final int n, final Graphics graphics, final Control control) {
        int r = (int)(230.0f - 230.0f * (this.m.snap[0] / (float)(100 * this.hipno[n - 1])));
        if (r > 255) {
            r = 255;
        }
        if (r < 0) {
            r = 0;
        }
        int g = (int)(230.0f - 230.0f * (this.m.snap[1] / (float)(100 * this.hipno[n - 1])));
        if (g > 255) {
            g = 255;
        }
        if (g < 0) {
            g = 0;
        }
        int b = (int)(230.0f - 230.0f * (this.m.snap[2] / (float)(100 * this.hipno[n - 1])));
        if (b > 255) {
            b = 255;
        }
        if (b < 0) {
            b = 0;
        }
        if (this.hipno[n - 1] == 0) {
            r = 255;
            g = 230;
            b = 201;
        }
        graphics.setColor(new Color(r, g, b));
        graphics.fillRect(0, 0, 550, 400);
        graphics.drawImage(this.loadingmusic, 164, 90, null);
        graphics.setFont(new Font("SansSerif", 1, 11));
        this.ftm = graphics.getFontMetrics();
        this.drawcs(graphics, 250, "Loading complete!  press start to begin...", 0, 0, 0, 3);
        graphics.drawImage(this.star[this.pstar], 234, 280, null);
        if (n == 10) {
            if (this.aflk) {
                this.drawcs(graphics, 340, "> Note: Guidance Arrow is disabled in this stage!", 200, 0, 0, 3);
                this.aflk = false;
            }
            else {
                this.drawcs(graphics, 340, "> Note: Guidance Arrow is disabled in this stage!", 0, 0, 0, 3);
                this.aflk = true;
            }
        }
        if (n == this.unlocked) {
            if (n == 1) {
                this.drawcs(graphics, 340, "> Don't forget, you must pass in all checkpoints to complete a lap...", 100, 100, 100, 4);
            }
            if (n == 2) {
                this.drawcs(graphics, 340, "> Don't forget, you need power to be up to race faster...", 100, 100, 100, 4);
            }
            if (n == 3) {
                this.drawcs(graphics, 340, "> Hint: its easier to waste the other cars then to finish 1st in this stage...", 100, 100, 100, 4);
                this.drawcs(graphics, 360, "( Press [A] to make Guidance Arrow point to cars )", 100, 100, 100, 4);
            }
            if (n == 4) {
                this.drawcs(graphics, 340, "> Remember the better the stunt the better the power you get...", 100, 100, 100, 4);
            }
            if (n == 5) {
                this.drawcs(graphics, 340, "> Remember the more the power the more faster and powerful your car is...", 100, 100, 100, 4);
            }
        }
        if (this.pstar != 2) {
            if (this.pstar == 0) {
                this.pstar = 1;
            }
            else {
                this.pstar = 0;
            }
        }
        if (control.handb || control.enter) {
            System.gc();
            this.fase = 0;
            control.handb = false;
            control.enter = false;
        }
    }
    
    public void clicknow(final Graphics graphics) {
        graphics.setColor(new Color(198, 214, 255));
        graphics.fillRoundRect(125, 315, 300, 80, 30, 70);
        graphics.setColor(new Color(128, 167, 255));
        graphics.drawRoundRect(125, 315, 300, 80, 30, 70);
        if (this.aflk) {
            this.drawcs(graphics, 355, "Click here to Start", 0, 0, 0, 3);
            this.aflk = false;
        }
        else {
            this.drawcs(graphics, 355, "Click here to Start", 0, 67, 200, 3);
            this.aflk = true;
        }
    }
    
    private Image loadimage(final byte[] imagedata, final MediaTracker mediaTracker, final Toolkit toolkit) {
        final Image image = toolkit.createImage(imagedata);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (Exception ex) {}
        return image;
    }
    
    public void rad(final Graphics graphics, final int n) {
        if (n == 0) {
            this.powerup.play();
        }
        graphics.setColor(new Color(0, 0, 0));
        graphics.fillRect(0, 0, 550, 400);
        graphics.drawImage(this.radicalplay, 87, 110, null);
        graphics.setFont(new Font("SansSerif", 1, 13));
        this.ftm = graphics.getFontMetrics();
        this.drawcs(graphics, 150 + (int)(10.0f * this.m.random()), "www.radicalplay.com", 112, 120, 143, 3);
        graphics.setFont(new Font("SansSerif", 1, 11));
        this.ftm = graphics.getFontMetrics();
        if (this.aflk) {
            this.drawcs(graphics, 190, "And we are never going to find the new unless we get a little crazy...", 112, 120, 143, 3);
            this.aflk = false;
        }
        else {
            this.drawcs(graphics, 192, "And we are never going to find the new unless we get a little crazy...", 150, 150, 150, 3);
            this.aflk = true;
        }
        graphics.drawImage(this.rpro, 150, 240, null);
    }
    
    public void skid(final int n, final float n2) {
        if (this.bfcrash == 0 && this.bfskid == 0 && n2 > 150.0f) {
            if (n == 0) {
                if (!this.mutes) {
                    this.skid[this.skflg].play();
                }
                if (this.skflg == 0) {
                    this.skflg = 1;
                }
                else {
                    this.skflg = 0;
                }
            }
            else {
                if (!this.mutes) {
                    this.dustskid[this.dskflg].play();
                }
                if (this.dskflg == 0) {
                    this.dskflg = 1;
                }
                else {
                    this.dskflg = 0;
                }
            }
            this.bfskid = 35;
        }
    }
    
    public int xs(final int n, int n2) {
        if (n2 < 50) {
            n2 = 50;
        }
        return (n2 - this.m.focus_point) * (this.m.cx - n) / n2 + n;
    }
    
    public void cantreply(final Graphics graphics) {
        graphics.setColor(new Color(64, 143, 223));
        graphics.fillRoundRect(75, 171, 400, 23, 7, 20);
        graphics.setColor(new Color(0, 89, 223));
        graphics.drawRoundRect(75, 171, 400, 23, 7, 20);
        this.drawcs(graphics, 187, "Sorry not enough replay data to play available, please try again later.", 255, 255, 255, 1);
    }
    
    public void loadpak3(final MediaTracker mediaTracker, final Toolkit toolkit) {
        try {
            final DataInputStream in = new DataInputStream(new URL(this.app.getCodeBase(), "graphics/images3.radq").openStream());
            final ZipInputStream zipInputStream = new ZipInputStream(in);
            for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
                int i = (int)zipEntry.getSize();
                final String name = zipEntry.getName();
                final byte[] b = new byte[i];
                int off = 0;
                while (i > 0) {
                    final int read = zipInputStream.read(b, off, i);
                    off += read;
                    i -= read;
                }
                if (name.equals("back.gif")) {
                    this.back[0] = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("continue1.gif")) {
                    this.contin1[0] = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("continue2.gif")) {
                    this.contin2[0] = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("inst3.gif")) {
                    this.inst3 = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("next.gif")) {
                    this.next[0] = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("pgate.gif")) {
                    this.pgate = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("rpro.gif")) {
                    this.rpro = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("selectcar.gif")) {
                    this.selectcar = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("stages.jpg")) {
                    this.trackbg = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("youlost.gif")) {
                    this.oyoulost = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("youwon.gif")) {
                    this.oyouwon = this.loadimage(b, mediaTracker, toolkit);
                }
            }
            in.close();
            zipInputStream.close();
        }
        catch (Exception obj) {
            System.out.println("Error Reading Images Pak 3: " + obj);
        }
        System.gc();
    }
    
    public void stopallnow() {
        int n = 0;
        do {
            if (this.loadedt[n]) {
                this.stracks[n].outwithit();
            }
        } while (++n < 11);
        int n2 = 0;
        do {
            this.engs[0][n2].stop();
            this.engs[1][n2].stop();
        } while (++n2 < 5);
        int n3 = 0;
        do {
            this.air[n3].stop();
        } while (++n3 < 6);
        this.wastd.stop();
        this.cars.outwithit();
        this.stages.outwithit();
    }
    
    public void carselect(final Control control, final ContO[] array, final Madness madness, final Graphics graphics) {
        this.cars.play();
        graphics.drawImage(this.carsbg, 0, 0, null);
        graphics.drawImage(this.selectcar, 184, 65, null);
        this.m.crs = true;
        this.m.x = -275;
        this.m.y = -500;
        this.m.z = -50;
        this.m.xz = 0;
        this.m.zy = 10;
        this.m.ground = 470;
        array[this.sc[0]].d(graphics);
        if (this.flipo == 0) {
            graphics.setFont(new Font("SansSerif", 1, 13));
            this.ftm = graphics.getFontMetrics();
            if (this.aflk) {
                this.drawcs(graphics, 130, this.names[this.sc[0]], 130, 130, 255, 3);
                this.aflk = false;
            }
            else {
                this.drawcs(graphics, 130, this.names[this.sc[0]], 130, 215, 255, 3);
                this.aflk = true;
            }
            array[this.sc[0]].z = 950;
            array[this.sc[0]].y = -34 - array[this.sc[0]].grat;
            array[this.sc[0]].x = 0;
            final ContO contO = array[this.sc[0]];
            contO.xz += 5;
            array[this.sc[0]].zy = 0;
            final ContO contO2 = array[this.sc[0]];
            contO2.wzy -= 10;
            if (array[this.sc[0]].wzy < -45) {
                final ContO contO3 = array[this.sc[0]];
                contO3.wzy += 45;
            }
            if (this.sc[0] != 0) {
                graphics.drawImage(this.back[this.pback], 23, 270, null);
            }
            if (this.sc[0] != 9) {
                graphics.drawImage(this.next[this.pnext], 467, 270, null);
            }
            if ((this.sc[0] - 4) * 2 >= this.unlocked) {
                graphics.drawImage(this.pgate, 96, 145 - this.gatey, null);
                if (this.gatey != 0) {
                    this.gatey -= 100;
                }
                this.drawcs(graphics, 320, "[ Car Locked ]", 224, 63, 63, 3);
                this.drawcs(graphics, 345, "This car unlocks when stage " + (this.sc[0] - 4) * 2 + " is completed...", 160, 176, 255, 3);
            }
            else {
                graphics.setFont(new Font("SansSerif", 1, 11));
                this.ftm = graphics.getFontMetrics();
                graphics.setColor(new Color(160, 176, 255));
                graphics.drawString("Top Speed:", 33, 313);
                graphics.drawImage(this.statb, 97, 307, null);
                graphics.drawString("Acceleration:", 23, 328);
                graphics.drawImage(this.statb, 97, 322, null);
                graphics.drawString("Handling:", 45, 343);
                graphics.drawImage(this.statb, 97, 337, null);
                graphics.drawString("Aerial Control:", 290, 313);
                graphics.drawImage(this.statb, 371, 307, null);
                graphics.drawString("Strength:", 318, 328);
                graphics.drawImage(this.statb, 371, 322, null);
                graphics.drawString("Power Save:", 300, 343);
                graphics.drawImage(this.statb, 371, 337, null);
                graphics.setColor(new Color(0, 0, 0));
                final float n = (madness.swits[this.sc[0]][2] - 220) / 90.0f;
                graphics.fillRect((int)(97.0f + 156.0f * n), 307, (int)(156.0f * (1.0f - n) + 1.0f), 7);
                final float n2 = madness.acelf[this.sc[0]][1] * madness.acelf[this.sc[0]][0] * madness.acelf[this.sc[0]][2] * madness.grip[this.sc[0]] / 7700.0f;
                graphics.fillRect((int)(97.0f + 156.0f * n2), 322, (int)(156.0f * (1.0f - n2) + 1.0f), 7);
                final float n3 = this.dishandle[this.sc[0]];
                graphics.fillRect((int)(97.0f + 156.0f * n3), 337, (int)(156.0f * (1.0f - n3) + 1.0f), 7);
                float n4 = (madness.airc[this.sc[0]] * madness.airs[this.sc[0]] + 25.0f) / 125.0f;
                if (n4 > 1.0f) {
                    n4 = 1.0f;
                }
                graphics.fillRect((int)(371.0f + 156.0f * n4), 307, (int)(156.0f * (1.0f - n4) + 1.0f), 7);
                float n5 = madness.outdam[this.sc[0]] * madness.moment[this.sc[0]] / 150.0f;
                if (n5 > 1.0f) {
                    n5 = 1.0f;
                }
                graphics.fillRect((int)(371.0f + 156.0f * n5), 322, (int)(156.0f * (1.0f - n5) + 1.0f), 7);
                final float n6 = madness.powerloss[this.sc[0]] / 5500000.0f;
                graphics.fillRect((int)(371.0f + 156.0f * n6), 337, (int)(156.0f * (1.0f - n6) + 1.0f), 7);
                graphics.drawImage(this.contin2[this.pcontin], 230, 357, null);
            }
        }
        else {
            this.pback = 0;
            this.pnext = 0;
            this.gatey = 300;
            if (this.flipo > 10) {
                final ContO contO4 = array[this.sc[0]];
                contO4.y -= 100;
                if (this.nextc) {
                    final ContO contO5 = array[this.sc[0]];
                    contO5.zy += 20;
                }
                else {
                    final ContO contO6 = array[this.sc[0]];
                    contO6.zy -= 20;
                }
            }
            else {
                if (this.flipo == 10) {
                    if (this.nextc) {
                        final int[] sc = this.sc;
                        final int n7 = 0;
                        ++sc[n7];
                    }
                    else {
                        final int[] sc2 = this.sc;
                        final int n8 = 0;
                        --sc2[n8];
                    }
                    array[this.sc[0]].z = 950;
                    array[this.sc[0]].y = -34 - array[this.sc[0]].grat - 1100;
                    array[this.sc[0]].x = 0;
                    array[this.sc[0]].zy = 0;
                }
                final ContO contO7 = array[this.sc[0]];
                contO7.y += 100;
            }
            --this.flipo;
        }
        graphics.setFont(new Font("SansSerif", 1, 11));
        this.ftm = graphics.getFontMetrics();
        this.drawcs(graphics, 396, "Use keyboard Arrows and press Enter to continue", 112, 151, 208, 3);
        if (control.right) {
            control.right = false;
            if (this.sc[0] != 9 && this.flipo == 0) {
                this.nextc = true;
                this.flipo = 20;
            }
        }
        if (control.left) {
            control.left = false;
            if (this.sc[0] != 0 && this.flipo == 0) {
                this.nextc = false;
                this.flipo = 20;
            }
        }
        if (control.handb || control.enter) {
            if (this.flipo == 0 && (this.sc[0] - 4) * 2 < this.unlocked) {
                this.m.crs = false;
                this.fase = 2;
            }
            control.handb = false;
            control.enter = false;
        }
    }
    
    public void ctachm(final int n, final int n2, final int n3, final Control control) {
        if (this.fase == 1) {
            if (n3 == 1) {
                if (this.over(this.next[0], n, n2, 440, 110)) {
                    this.pnext = 1;
                }
                if (this.over(this.back[0], n, n2, 50, 110)) {
                    this.pback = 1;
                }
                if (this.over(this.contin1[0], n, n2, 232, 170)) {
                    this.pcontin = 1;
                }
            }
            if (n3 == 2) {
                if (this.pnext == 1) {
                    control.right = true;
                }
                if (this.pback == 1) {
                    control.left = true;
                }
                if (this.pcontin == 1) {
                    control.enter = true;
                }
            }
        }
        if (this.fase == 3) {
            if (n3 == 1 && this.over(this.contin1[0], n, n2, 232, 270)) {
                this.pcontin = 1;
            }
            if (n3 == 2 && this.pcontin == 1) {
                control.enter = true;
                this.pcontin = 0;
            }
        }
        if (this.fase == 4) {
            if (n3 == 1 && this.over(this.back[0], n, n2, 245, 320)) {
                this.pback = 1;
            }
            if (n3 == 2 && this.pback == 1) {
                control.enter = true;
                this.pback = 0;
            }
        }
        if (this.fase == 6) {
            if (n3 == 1 && this.over(this.star[0], n, n2, 234, 280)) {
                this.pstar = 2;
            }
            if (n3 == 2 && this.pstar == 2) {
                control.enter = true;
                this.pstar = 1;
            }
        }
        if (this.fase == 7) {
            if (n3 == 1) {
                if (this.over(this.next[0], n, n2, 467, 276)) {
                    this.pnext = 2;
                }
                if (this.over(this.back[0], n, n2, 23, 276)) {
                    this.pback = 2;
                }
                if (this.over(this.contin2[0], n, n2, 232, 360)) {
                    this.pcontin = 1;
                }
            }
            if (n3 == 2) {
                if (this.pnext == 2) {
                    control.right = true;
                }
                if (this.pback == 2) {
                    control.left = true;
                }
                if (this.pcontin == 1) {
                    control.enter = true;
                    this.pcontin = 0;
                }
            }
        }
        if (this.fase == -5) {
            if (n3 == 1 && this.over(this.contin2[0], n, n2, 230, 350 - this.pin)) {
                this.pcontin = 1;
            }
            if (n3 == 2 && this.pcontin == 1) {
                control.enter = true;
                this.pcontin = 0;
            }
        }
        if (this.fase == -7) {
            if (n3 == 1) {
                if (this.overon(204, 143, 137, 22, n, n2)) {
                    this.opselect = 0;
                    this.shaded = true;
                }
                if (this.overon(195, 171, 155, 22, n, n2)) {
                    this.opselect = 1;
                    this.shaded = true;
                }
                if (this.overon(178, 197, 190, 22, n, n2)) {
                    this.opselect = 2;
                    this.shaded = true;
                }
                if (this.overon(216, 223, 109, 22, n, n2)) {
                    this.opselect = 3;
                    this.shaded = true;
                }
            }
            if (n3 == 2 && this.shaded) {
                control.enter = true;
                this.shaded = false;
            }
        }
        if (this.fase == 10) {
            if (n3 == 1) {
                if (this.overon(218, 246, 110, 22, n, n2)) {
                    this.opselect = 0;
                    this.shaded = true;
                }
                if (this.overon(174, 275, 196, 22, n, n2)) {
                    this.opselect = 1;
                    this.shaded = true;
                }
                if (this.overon(230, 306, 85, 22, n, n2)) {
                    this.opselect = 2;
                    this.shaded = true;
                }
            }
            if (n3 == 2 && this.shaded) {
                control.enter = true;
                this.shaded = false;
            }
        }
        if (this.fase == 11) {
            if (this.flipo == 1 || this.flipo == 3) {
                if (n3 == 1 && this.over(this.next[0], n, n2, 460, 370)) {
                    this.pnext = 1;
                }
                if (n3 == 2 && this.pnext == 1) {
                    control.enter = true;
                    this.pnext = 0;
                }
            }
            if (this.flipo == 5) {
                if (n3 == 1 && this.over(this.contin2[0], n, n2, 230, 370)) {
                    this.pcontin = 1;
                }
                if (n3 == 2 && this.pcontin == 1) {
                    control.enter = true;
                    this.pcontin = 0;
                }
            }
        }
        if (this.fase == 8) {
            if (n3 == 1 && this.over(this.next[0], n, n2, 460, 370)) {
                this.pnext = 1;
            }
            if (n3 == 2 && this.pnext == 1) {
                control.enter = true;
                this.pnext = 0;
            }
        }
    }
    
    public void stopairs() {
        int n = 0;
        do {
            this.air[n].stop();
        } while (++n < 6);
    }
    
    private Image loadopsnap(final Image img, final int n) {
        final int height = img.getHeight(this.ob);
        final int width = img.getWidth(this.ob);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(img, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < width * height; ++i) {
            if (array[i] != array[76]) {
                final Color color = new Color(array[i]);
                int r;
                int green;
                int b;
                if (this.hipno[n - 1] != 0) {
                    r = (int)(color.getRed() - color.getRed() * (this.m.snap[0] / (float)(50 * this.hipno[n - 1])));
                    if (r > 255) {
                        r = 255;
                    }
                    if (r < 0) {
                        r = 0;
                    }
                    green = (int)(color.getGreen() - color.getGreen() * (this.m.snap[1] / (float)(50 * this.hipno[n - 1])));
                    if (green > 255) {
                        green = 255;
                    }
                    if (green < 0) {
                        green = 0;
                    }
                    b = (int)(color.getBlue() - color.getBlue() * (this.m.snap[2] / (float)(50 * this.hipno[n - 1])));
                    if (b > 255) {
                        b = 255;
                    }
                    if (b < 0) {
                        b = 0;
                    }
                }
                else {
                    r = (int)(color.getRed() + color.getRed() * 0.25f);
                    if (r > 255) {
                        r = 255;
                    }
                    if (r < 0) {
                        r = 0;
                    }
                    green = color.getGreen();
                    b = (int)(color.getBlue() - color.getBlue() * 0.25);
                    if (b > 255) {
                        b = 255;
                    }
                    if (b < 0) {
                        b = 0;
                    }
                }
                array[i] = new Color(r, green, b).getRGB();
            }
        }
        return this.createImage(new MemoryImageSource(width, height, array, 0, width));
    }
    
    public void loadingfailed(final int i, final Control control, final Graphics graphics) {
        graphics.drawImage(this.trackbg, 0, 0, null);
        graphics.drawImage(this.select, 201, 45, null);
        graphics.setFont(new Font("SansSerif", 1, 13));
        this.ftm = graphics.getFontMetrics();
        this.drawcs(graphics, 140, "Error Loading Stage " + i, 200, 0, 70, 3);
        this.drawcs(graphics, 170, "Your internet connection may have been lost...", 0, 0, 0, 3);
        this.drawcs(graphics, 220, "Press Enter to try again.", 0, 0, 0, 3);
        graphics.drawImage(this.contin1[this.pcontin], 232, 270, null);
        graphics.drawImage(this.bl, 0, 0, null);
        graphics.drawImage(this.bt, 0, 0, null);
        graphics.drawImage(this.br, 509, 0, null);
        graphics.drawImage(this.bb, 0, 357, null);
        if (control.handb || control.enter) {
            this.fase = 2;
            control.handb = false;
            control.enter = false;
        }
    }
    
    private AudioClip getSound(final String name) {
        final AudioClip audioClip = this.app.getAudioClip(this.app.getCodeBase(), name);
        if (name.startsWith("sounds/default")) {
            audioClip.play();
            Thread.yield();
            audioClip.stop();
        }
        return audioClip;
    }
    
    public void loadpak4(final MediaTracker mediaTracker, final Toolkit toolkit) {
        try {
            final DataInputStream in = new DataInputStream(new URL(this.app.getCodeBase(), "graphics/images4.radq").openStream());
            final ZipInputStream zipInputStream = new ZipInputStream(in);
            for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
                int i = (int)zipEntry.getSize();
                final String name = zipEntry.getName();
                final byte[] b = new byte[i];
                int off = 0;
                while (i > 0) {
                    final int read = zipInputStream.read(b, off, i);
                    off += read;
                    i -= read;
                }
                if (name.equals("0c.gif")) {
                    this.ocntdn[0] = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("damage.gif")) {
                    this.odmg = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("power.gif")) {
                    this.opwr = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("position.gif")) {
                    this.opos = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("wasted.gif")) {
                    this.owas = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("bl.gif")) {
                    this.bl = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("bt.gif")) {
                    this.bt = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("bb.gif")) {
                    this.bb = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("start1.gif")) {
                    this.ostar[0] = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("start2.gif")) {
                    this.ostar[1] = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("congrad.gif")) {
                    this.congrd = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("statb.gif")) {
                    this.statb = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("madness.gif")) {
                    this.omdness = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("options.gif")) {
                    this.opti = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("inst1.gif")) {
                    this.inst1 = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("inst2.gif")) {
                    this.inst2 = this.loadimage(b, mediaTracker, toolkit);
                }
                if (name.equals("nfmcom.gif")) {
                    this.nfmcom = this.loadimage(b, mediaTracker, toolkit);
                }
            }
            in.close();
            zipInputStream.close();
        }
        catch (Exception obj) {
            System.out.println("Error Reading Images Pak 4: " + obj);
        }
        System.gc();
    }
}
