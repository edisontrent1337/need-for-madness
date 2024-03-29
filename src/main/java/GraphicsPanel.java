package main.java;

import main.java.resources.ResourceLoader;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.HashMap;
import java.util.Map;

import static main.java.Util.getPixelArray;
import static main.java.Util.snapRGBs;


public class GraphicsPanel extends Panel {
    Medium medium;
    FontMetrics fontMetrics;
    ImageObserver imageObserver;
    Applet app;
    public GameState state;
    GameState lastState;
    public int startCount;
    public int unlocked;
    public int lockcnt;
    public int opselect;
    public boolean shaded;
    public int flipo;
    public boolean nextc;
    public int gatey;
    public int looped;
    public int[] selectedCar;
    public int[] xstart;
    public int[] zstart;
    public float[] proba;
    public float[] dishandle;
    public int holdcnt;
    public boolean winner;
    public int[] flexpix;
    public Image fleximg;
    public Image odmg;
    public Image opwr;
    public Image opos;
    public Image owas;
    public Image olap;
    public Image oyourwasted;
    public Image oyoulost;
    public Image oyouwon;
    public Image oyouwastedem;
    public Image ogameh;
    public Image oloadingmusic;
    public Image omdness;
    public Image damageMeter;
    public Image powerMeter;
    public Image pos;
    public Image was;
    public Image lap;
    public Image trackbg;
    public Image barLeft;
    public Image barTop;
    public Image barRight;
    public Image barBottom;
    public Image selectStage;
    public Image loadingmusic;
    public Image yourwasted;
    public Image youlost;
    public Image youwon;
    public Image youwastedem;
    public Image gameh;
    public Image congrd;
    public Image gameov;
    public Image carsbg;
    public Image pgate;
    public Image selectcar;
    public Image statb;
    public Image mdness;
    public Image paused;
    public Image radicalplay;
    public Image maini;
    public Image opti;
    public Image bgmain;
    public Image inst1;
    public Image inst2;
    public Image inst3;
    public Image rpro;
    public Image nfmcom;
    public Image[] next;
    public Image[] back;
    public Image[] contin1;
    public Image[] contin2;
    public Image[] ostar;
    public Image[] star;
    int pcontin;
    int pnext;
    int pback;
    int pstar;
    public Image[] rankImages;
    public Image[] rankImagesSnap;
    public Image[] countDownImages;
    public Image[] countdownImagesSnap;
    public int gocnt;
    public AudioClip[][] engineSounds;
    public boolean[] playingEngineSound;
    public int[][] enginsignature;
    public AudioClip[] airSoundEffects;
    public boolean aird;
    public boolean grrd;
    public AudioClip[] crashSoundEffects;
    public AudioClip[] lowCrashSoundEffects;
    public AudioClip tires;
    public AudioClip checkpoint;
    public AudioClip carfixed;
    public AudioClip powerup;
    public AudioClip three;
    public AudioClip two;
    public AudioClip one;
    public AudioClip go;
    public AudioClip wastd;
    public AudioClip firewasted;
    public boolean pwastd;
    public AudioClip[] skidSoundEffects;
    public AudioClip[] dustSkidSoundEffects;
    public boolean isSoundMuted;
    public RadicalMod stages;
    public RadicalMod cars;
    public RadicalMod[] soundTracks;
    public boolean[] loadedSoundTracks;
    public boolean isMusicMuted;
    public boolean arrace;
    public float ana;
    public int cntan;
    public int cntovn;
    public boolean flk;
    public int tcnt;
    public boolean tflk;
    public String say;
    public boolean wasay;
    public int clear;
    public int posit;
    public int wasted;
    public int laps;
    public int[] dested;
    public String[] carNames;
    public int dmcnt;
    public boolean dmflk;
    public int pwcnt;
    public boolean powerBlink;
    public String[][] adj;
    public String[] exlm;
    public String loop;
    public String spin;
    public String asay;
    public int auscnt;
    public boolean aflk;
    public int[] hipno;
    public int[] sndsize;
    public Image hello;
    public Image sign;
    public Image loadbar;
    public int kbload;
    public int pin;
    public int pwait;
    public int stopcnt;
    public int cntwis;
    public int crshturn;
    public int bfcrash;
    public int bfskid;
    public boolean crashup;
    public int skflg;
    public int dskflg;

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    private ResourceLoader resourceLoader;
    private Map<String, Image> images = new HashMap<>();

    public void setImages(Map<String, Image> images) {
        this.images = images;
    }

    public GraphicsPanel(final Medium medium, final Graphics graphics, final Applet app) {
        this.state = GameState.MAIN_MENU;
        this.lastState = GameState.GAMEPLAY;
        this.startCount = 0;
        this.unlocked = 1;
        this.lockcnt = 0;
        this.opselect = 1;
        this.shaded = false;
        this.flipo = 0;
        this.nextc = false;
        this.gatey = 0;
        this.looped = 1;
        this.selectedCar = new int[5];
        this.xstart = new int[]{0, -350, 350, 0, 0};
        this.zstart = new int[]{-100, 500, 500, 500, 1100};
        this.proba = new float[]{0.3f, 0.8f, 0.5f, 0.3f, 0.8f, 0.0f, 0.2f, 0.4f, 0.0f, 0.0f};
        this.dishandle = new float[]{0.65f, 0.6f, 0.55f, 0.77f, 0.7f, 0.9f, 0.7f, 0.4f, 1.0f, 0.85f};
        this.holdcnt = 0;
        this.winner = false;
        this.flexpix = new int[Config.SCREEN_PIXELS];
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
        this.rankImages = new Image[5];
        this.rankImagesSnap = new Image[5];
        this.countDownImages = new Image[4];
        this.countdownImagesSnap = new Image[4];
        this.gocnt = 0;
        this.engineSounds = new AudioClip[2][5];
        this.playingEngineSound = new boolean[5];
        final int[][] engineSignature = {
                {0, 0, 1, 1, 0},
                {0, 1, 1, 0, 1},
                new int[5],
                {0, 1, 1, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 1, 1, 1},
                {0, 1, 0, 1, 0},
                null,
                null,
                null};
        final int[] array = new int[5];
        array[0] = 1;
        engineSignature[7] = array;
        engineSignature[8] = new int[]{0, 1, 1, 1, 1};
        engineSignature[9] = new int[]{1, 1, 1, 1, 1};
        this.enginsignature = engineSignature;
        this.airSoundEffects = new AudioClip[6];
        this.aird = false;
        this.grrd = false;
        this.crashSoundEffects = new AudioClip[3];
        this.lowCrashSoundEffects = new AudioClip[3];
        this.pwastd = false;
        this.skidSoundEffects = new AudioClip[2];
        this.dustSkidSoundEffects = new AudioClip[2];
        this.isSoundMuted = false;
        this.soundTracks = new RadicalMod[11];
        this.loadedSoundTracks = new boolean[11];
        this.isMusicMuted = false;
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
        this.carNames = new String[]{"Tornado Shark", "Formula 7", "Wow Caninaro", "La vite Crab", "Nimi", "MAX Revenge", "Lead Oxide", "EL KING", "Radical One", "DR Monstaa"};
        this.dmcnt = 0;
        this.dmflk = false;
        this.pwcnt = 0;
        this.powerBlink = false;
        this.adj = new String[][]{{"Cool", "Alright", "Nice"}, {"Wicked", "Amazing", "Super"}, {"Awesome", "Ripping", "Radical"}, {"What the...?", "Your a super star!!!!", "Who are you again...?"}, {"surf style", "off the lip", "bounce back"}};
        this.exlm = new String[]{"!", "!!", "!!!"};
        this.loop = "";
        this.spin = "";
        this.asay = "";
        this.auscnt = 45;
        this.aflk = false;
        this.hipno = new int[]{0, 2, 2, 2, 2, 0, 2, 50, 2, 2, 6};
        this.sndsize = new int[]{39, 128, 23, 58, 106, 140, 81, 135, 38, 141, 80};
        this.kbload = 0;
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

        this.medium = medium;
        this.app = app;
        this.resourceLoader = new ResourceLoader(this);

        Toolkit.getDefaultToolkit();
        images = resourceLoader.loadImages();
        resourceLoader.loadTextures();

        this.loading(graphics, this.app);

        this.loading(graphics, this.app);
        this.next[1] = this.pressed(this.next[0]);
        this.back[1] = this.pressed(this.back[0]);
        this.next[2] = this.getButtonPressedImage(this.next[0]);
        this.back[2] = this.getButtonPressedImage(this.back[0]);
        this.contin1[1] = this.pressed(this.contin1[0]);
        this.contin2[1] = this.getButtonPressedImage(this.contin2[0]);
        this.contin1[1] = this.pressed(this.contin1[0]);
        this.contin2[1] = this.getButtonPressedImage(this.contin2[0]);
        this.star[2] = this.pressed(this.ostar[1]);

        // FIXME Move the sound loading logic to ResourceLoader

        resourceLoader.loadSoundEffects(this);

        this.cars = new RadicalMod("../../resources/music/cars.zipo", 500, 7900, 125);
        this.stages = new RadicalMod("../../resources/music/stages.zipo", 200, 9000, 145);
        int n8 = 0;
        do {
            this.loadedSoundTracks[n8] = false;
        } while (++n8 < 10);
    }

    public void framer(final int stageNumber, final Graphics graphics) {
        int r = (int) (230.0f - 230.0f * (this.medium.snapColor[0] / (float) (100 * this.hipno[stageNumber - 1])));
        r = Util.clampCol(r);
        int g = (int) (230.0f - 230.0f * (this.medium.snapColor[1] / (float) (100 * this.hipno[stageNumber - 1])));
        g = Util.clampCol(g);
        int b = (int) (230.0f - 230.0f * (this.medium.snapColor[2] / (float) (100 * this.hipno[stageNumber - 1])));
        b = Util.clampCol(b);

        if (this.hipno[stageNumber - 1] == 0) {
            r = 255;
            g = 230;
            b = 201;
        }

        fillBlankScreen(graphics, new Color(r, g, b));
        int xPos = Config.SCREEN_WIDTH / 2 - this.loadingmusic.getWidth(null) / 2;
        int yPos = Config.SCREEN_HEIGHT / 2 - this.loadingmusic.getHeight(null) / 2;
        graphics.drawImage(this.loadingmusic, xPos, yPos, null);

        if (stageNumber == 10) {
            this.drawCharacters(graphics, Config.SCREEN_HEIGHT / 2 - 50, "> Note: Guidance Arrow is disabled in this stage!", 100, 100, 100, 4);
        }

        if (stageNumber == this.unlocked) {
            this.drawCharacters(graphics, Config.SCREEN_HEIGHT / 2 - 50, getHintString(stageNumber), 100, 100, 100, 4);
        }
    }

    private String getHintString(int stageNumber) {
        switch (stageNumber) {
            case 1:
                return "> Don't forget, you must pass in all checkpoints to complete a lap...";
            case 2:
                return "> Don't forget, you need power to be up to race faster...";
            case 3:
                return "> Hint: its easier to waste the other cars then to finish 1st in this stage...";
            case 4:
                return "Hint: Press [A] to make Guidance Arrow point to cars";
            case 5:
                return "> Remember: the better the stunt the better the power you get...";
            case 6:
                return "> Remember: the more the power the more faster and powerful your car is...";

        }
        return "";
    }

    private void fillBlankScreen(Graphics graphics, Color c) {
        graphics.setColor(c);
        graphics.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    }

    public void cantGo(final Graphics graphics, final Control control) {
        this.pnext = 0;
        graphics.drawImage(this.trackbg, 0, 0, null);
        graphics.setFont(new Font("SansSerif", 1, 13));
        this.fontMetrics = graphics.getFontMetrics();
        this.drawCharacters(graphics, 110, "> You need to complete current stage " + this.unlocked + " before playing this one...", 0, 0, 0, 3);
        graphics.drawImage(this.pgate, 96, 150, null);

        Color color = new Color(this.aflk ? 0 : 255, 0, 0);
        this.drawCharacters(graphics, 160, "[ Stage " + (this.unlocked + 1) + " Locked ]", color, 3);
        this.aflk = !this.aflk;

        graphics.drawImage(this.selectStage, 201, 45, null);
        graphics.drawImage(this.barLeft, 0, 0, null);
        graphics.drawImage(this.barTop, 0, 0, null);
        graphics.drawImage(this.barRight, 509, 0, null);
        graphics.drawImage(this.barBottom, 0, 357, null);
        graphics.drawImage(this.back[this.pback], 245, 320, null);
        graphics.setFont(new Font("SansSerif", Font.BOLD, 11));
        this.fontMetrics = graphics.getFontMetrics();
        this.drawCharacters(graphics, 396, "Use keyboard Arrows and press Enter to continue", 0, 0, 0, 3);
        --this.lockcnt;
        if (this.lockcnt == 0 || control.enter || control.handb || control.left) {
            control.left = false;
            control.handb = false;
            control.enter = false;
            this.state = GameState.STAGE_PREVIEW;
        }
    }

    public boolean over(final Image image, final int n, final int n2, final int n3, final int n4) {
        final int height = image.getHeight(this.imageObserver);
        final int width = image.getWidth(this.imageObserver);
        return n > n3 - 5 && n < n3 + width + 5 && n2 > n4 - 5 && n2 < n4 + height + 5;
    }

    public void loadingStage(final int stageNumber, final Graphics graphics) {
        this.cars.stop();
        this.stages.stop();
        graphics.drawImage(this.trackbg, 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, null);
        graphics.setFont(new Font("SansSerif", Font.BOLD, 13));
        this.fontMetrics = graphics.getFontMetrics();
        this.drawCharacters(graphics, 180, "Loading Stage " + stageNumber + ", please wait...", 0, 0, 0, 3);
        drawStageSelectImgs(graphics);
        graphics.setFont(new Font("SansSerif", 1, 11));
        this.fontMetrics = graphics.getFontMetrics();
        this.drawCharacters(graphics, 396, "Use keyboard Arrows and press Enter to continue", 0, 0, 0, 3);
        this.app.repaint();
    }

    public void inst(final Graphics graphics, final Control control) {
        if (this.flipo == 0) {
            fillBlankScreen(graphics,new Color(214, 218, 252));
            graphics.drawImage(this.inst1, 0, 0,  Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, null);
            this.flipo = 1;
        }
        if (this.flipo == 2) {
            fillBlankScreen(graphics, new Color(214, 218, 252));
            graphics.drawImage(this.inst2, 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT,null);
            this.flipo = 3;
        }
        if (this.flipo == 4) {
            fillBlankScreen(graphics, new Color(214, 218, 252));
            graphics.drawImage(this.inst3, 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT,null);
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
                this.state = this.lastState;
                if (this.state == GameState.GAME_PAUSED_2_BLURRED) {
                    graphics.drawImage(this.fleximg, 0, 0, null);
                }
            }
            control.enter = false;
        }
    }

    public void flexImage(final Image img, final Graphics graphics, final int n) {
        if (n == 0) {
            final PixelGrabber pixelGrabber = new PixelGrabber(img, 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, this.flexpix, 0, Config.SCREEN_WIDTH);
            try {
                pixelGrabber.grabPixels();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            int n2 = 0;
            do {
                final Color color = new Color(this.flexpix[n2]);
                final float[] hsbvals = new float[3];
                Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsbvals);
                this.flexpix[n2] = Color.getHSBColor(0.7f, hsbvals[1], hsbvals[2]).getRGB();
            } while (++n2 < Config.SCREEN_PIXELS);
        }
        int n3 = 0;
        do {
            if (this.medium.random() > 0.2) {
                final Color color2 = new Color(this.flexpix[n3]);
                int r = (int) (color2.getRed() - color2.getRed() * 0.4);
                r = Util.clamp(r, 0, 255);
                int g = (int) (color2.getGreen() - color2.getGreen() * 0.35);
                g = Util.clamp(g, 0, 255);
                int b = (int) (color2.getBlue() - color2.getBlue() * (0.25 + n / 30.0f));
                b = Util.clamp(b, 0, 255);
                this.flexpix[n3] = new Color(r, g, b).getRGB();
            }
        } while (++n3 < Config.SCREEN_PIXELS);
        graphics.drawImage(this.fleximg = this.createImage(new MemoryImageSource(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, this.flexpix, 0, Config.SCREEN_WIDTH)), 0, 0, null);
    }

    public void arrow(final Graphics graphics, final int n, final int n2, final CheckPoints checkPoints, final boolean shouldPointAtCars) {
        final int[] xCoordinates = new int[7];
        final int[] yCoordinates = new int[7];
        int[] zCoordinates = new int[]{
                -90, -90, -90, -90, -90, -90, -90, -90,
        };

        final int xPosition = Config.SCREEN_WIDTH / 2;
        final int yPosition = 700 + (Config.SCREEN_HEIGHT - 400);

        xCoordinates[0] = xPosition;
        yCoordinates[0] = yPosition + 110;
        xCoordinates[1] = xPosition - 35;
        yCoordinates[1] = yPosition + 50;
        xCoordinates[2] = xPosition - 15;
        yCoordinates[2] = yPosition + 50;
        xCoordinates[3] = xPosition - 15;
        yCoordinates[3] = yPosition - 50;
        xCoordinates[4] = xPosition + 15;
        yCoordinates[4] = yPosition - 50;
        xCoordinates[5] = xPosition + 15;
        yCoordinates[5] = yPosition + 50;
        xCoordinates[6] = xPosition + 35;
        yCoordinates[6] = yPosition + 50;

        int n8;
        int n7 = 0;
        if (!shouldPointAtCars) {
            if (checkPoints.x[n] - checkPoints.opx[0] >= 0) {
                n7 = 180;
            }
            n8 = (int) (90 + n7 + Math.atan((checkPoints.z[n] - checkPoints.opz[0]) / (double) (checkPoints.x[n] - checkPoints.opx[0])) / 0.017453292519943295);
        } else {
            int py = -1;
            int n10 = 0;
            int n11 = 1;
            do {
                if ((this.py(checkPoints.opx[0] / 100, checkPoints.opx[n11] / 100, checkPoints.opz[0] / 100, checkPoints.opz[n11] / 100) < py || py == -1) && (n10 == 0 || checkPoints.onscreen[n11] != 0) && checkPoints.dested[n11] == 0) {
                    n7 = n11;
                    py = this.py(checkPoints.opx[0] / 100, checkPoints.opx[n11] / 100, checkPoints.opz[0] / 100, checkPoints.opz[n11] / 100);
                    if (checkPoints.onscreen[n11] == 0) {
                        continue;
                    }
                    n10 = 1;
                }
            } while (++n11 < 5);
            int n12 = 0;
            if (checkPoints.opx[n7] - checkPoints.opx[0] >= 0) {
                n12 = 180;
            }
            n8 = (int) (90 + n12 + Math.atan((checkPoints.opz[n7] - checkPoints.opz[0]) / (double) (checkPoints.opx[n7] - checkPoints.opx[0])) / 0.017453292519943295);
            this.drawCharacters(graphics, 13, "[                              ]", 76, 67, 240, 0);
            this.drawCharacters(graphics, 13, this.carNames[this.selectedCar[n7]], 0, 0, 0, 0);
        }
        float i;
        i = n8 + (int) this.medium.xz;
        while (i < 0) {
            i += 360;
        }
        while (i > 180) {
            i -= 360;
        }
        if (!shouldPointAtCars) {
            i = Util.clamp(i, -130, 130);
        } else {
            i = Util.clamp(i, -100, 100);

        }
        if (Math.abs(this.ana - i) < 180) {
            if (Math.abs(this.ana - i) < 10) {
                this.ana = i;
            } else if (this.ana < i) {
                this.ana += 10;
            } else {
                this.ana -= 10;
            }
        } else {
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
        this.rot(xCoordinates, yCoordinates, xPosition, yPosition, this.ana, 7);
        final float abs = Math.abs(this.ana);
        if (!shouldPointAtCars) {
            if (abs > 7 || n2 > 0 || n2 == -2 || this.cntan != 0) {
                int n13 = 0;
                do {
                    xCoordinates[n13] = (int) this.xs(xCoordinates[n13], yCoordinates[n13]);
                    zCoordinates[n13] = (int) this.ys(zCoordinates[n13], yCoordinates[n13]);
                } while (++n13 < 7);
                int r = (int) (190.0f + 190.0f * (this.medium.snapColor[0] / 100.0f));
                r = Util.clamp(r, 0, 255);
                int g = (int) (255.0f + 255.0f * (this.medium.snapColor[1] / 100.0f));
                g = Util.clamp(g, 0, 255);
                int b = 0;
                if (n2 <= 0) {
                    if (abs <= 45 && n2 != -2 && this.cntan == 0) {
                        r = (int) ((r * abs + this.medium.skyColor[0] * (45 - abs)) / 45);
                        g = (int) ((g * abs + this.medium.skyColor[1] * (45 - abs)) / 45);
                        b = (int) ((b * abs + this.medium.skyColor[2] * (45 - abs)) / 45);
                    }
                    if (abs >= 90) {
                        int n14 = (int) Util.clampCol(255.0f + 255.0f * (this.medium.snapColor[0] / 100.0f));
                        r = (int) Util.clampCol((r * (140 - abs) + n14 * (abs - 90)) / 50);
                    }
                } else if (this.flk) {
                    r = (int) Util.clampCol(255.0f + 255.0f * (this.medium.snapColor[0] / 100.0f));
                    this.flk = false;
                } else {
                    r = (int) Util.clampCol(255.0f + 255.0f * (this.medium.snapColor[0] / 100.0f));
                    g = (int) Util.clampCol(220.0f + 220.0f * (this.medium.snapColor[1] / 100.0f));
                    this.flk = true;
                }
                graphics.setColor(new Color(r, g, b));
                graphics.fillPolygon(xCoordinates, zCoordinates, 7);
                int r2 = (int) Util.clampCol(115.0f + 115.0f * (this.medium.snapColor[0] / 100.0f));
                int g2 = (int) Util.clampCol(170.0f + 170.0f * (this.medium.snapColor[1] / 100.0f));
                int b3 = 0;
                if (n2 <= 0) {
                    if (abs <= 45 && n2 != -2 && this.cntan == 0) {
                        r2 = (int) ((r2 * abs + this.medium.skyColor[0] * (45 - abs)) / 45);
                        g2 = (int) ((g2 * abs + this.medium.skyColor[1] * (45 - abs)) / 45);
                        b3 = (int) ((b3 * abs + this.medium.skyColor[2] * (45 - abs)) / 45);
                    }
                } else if (this.flk) {
                    r2 = (int) Util.clampCol(255.0f + 255.0f * (this.medium.snapColor[0] / 100.0f));
                    g2 = 0;
                }
                graphics.setColor(new Color(r2, g2, b3));
                graphics.drawPolygon(xCoordinates, zCoordinates, 7);
            }
        } else {
            int n15 = 0;
            do {
                xCoordinates[n15] = (int) this.xs(xCoordinates[n15], yCoordinates[n15]);
                zCoordinates[n15] = (int) this.ys(zCoordinates[n15], yCoordinates[n15]);
            } while (++n15 < 7);
            int r3 = (int) Util.clampCol(159.0f + 159.0f * (this.medium.snapColor[0] / 100.0f));
            int g3 = (int) Util.clampCol(207.0f + 207.0f * (this.medium.snapColor[1] / 100.0f));
            int b4 = (int) Util.clampCol(255.0f + 255.0f * (this.medium.snapColor[2] / 100.0f));
            graphics.setColor(new Color(r3, g3, b4));
            graphics.fillPolygon(xCoordinates, zCoordinates, 7);
            int r4 = (int) Util.clampCol(120.0f + 120.0f * (this.medium.snapColor[0] / 100.0f));
            int g4 = (int) Util.clampCol(114.0f + 114.0f * (this.medium.snapColor[1] / 100.0f));
            int b5 = (int) Util.clampCol(255.0f + 255.0f * (this.medium.snapColor[2] / 100.0f));
            graphics.setColor(new Color(r4, g4, b5));
            graphics.drawPolygon(xCoordinates, zCoordinates, 7);
        }
    }

    public void showGameHighlight(final Graphics graphics, final int isPlayerWasted, final int highlightTimestamp) {
        int xPos = Config.SCREEN_WIDTH / 2 - this.gameh.getWidth(imageObserver) / 2;
        graphics.drawImage(this.gameh, xPos, 20, null);
        if (isPlayerWasted != 0) {
            this.drawCharacters(graphics, 60, "You wasted 'em!", 16, 48, 96, 0);
        } else if (highlightTimestamp == 229) {
            this.drawCharacters(graphics, 60, "Wasted!", 16, 48, 96, 0);
        } else {
            this.drawCharacters(graphics, 60, "Stunts!", 16, 48, 96, 0);
        }
        this.drawCharacters(graphics, Config.SCREEN_HEIGHT - 20, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
    }

    public void playSounds(final Madness madness, final Control control, final int stageNumber) {
        if (this.state == GameState.GAMEPLAY && this.startCount < 35 && this.cntwis != 8 && !this.isSoundMuted) {
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
                            if (Math.abs(madness.speed) > 0.0f && Math.abs(madness.speed) <= madness.swits[madness.carIndex][0]) {
                                int n2 = (int) (3.0f * Math.abs(madness.speed) / madness.swits[madness.carIndex][0]);
                                if (n2 == 2) {
                                    if (this.pwait == 0) {
                                        n2 = 0;
                                    } else {
                                        --this.pwait;
                                    }
                                } else {
                                    this.pwait = 7;
                                }
                                this.sparkEngine(n2);
                            }
                            if (Math.abs(madness.speed) > madness.swits[madness.carIndex][0] && Math.abs(madness.speed) <= madness.swits[madness.carIndex][1]) {
                                int n3 = (int) (3.0f * (Math.abs(madness.speed) - madness.swits[madness.carIndex][0]) / (madness.swits[madness.carIndex][1] - madness.swits[madness.carIndex][0]));
                                if (n3 == 2) {
                                    if (this.pwait == 0) {
                                        n3 = 0;
                                    } else {
                                        --this.pwait;
                                    }
                                } else {
                                    this.pwait = 7;
                                }
                                this.sparkEngine(n3);
                            }
                            if (Math.abs(madness.speed) > madness.swits[madness.carIndex][1] && Math.abs(madness.speed) <= madness.swits[madness.carIndex][2]) {
                                this.sparkEngine((int) (3.0f * (Math.abs(madness.speed) - madness.swits[madness.carIndex][1]) / (madness.swits[madness.carIndex][2] - madness.swits[madness.carIndex][1])));
                            }
                        } else {
                            int n4 = 2;
                            if (this.pwait == 0) {
                                if (Math.abs(madness.speed) > madness.swits[madness.carIndex][1]) {
                                    n4 = 3;
                                }
                            } else {
                                --this.pwait;
                            }
                            this.sparkEngine(n4);
                        }
                    } else {
                        this.sparkEngine(-1);
                        if (b3) {
                            if (this.stopcnt <= 0) {
                                this.airSoundEffects[5].loop();
                                this.stopcnt = 10;
                            }
                        } else if (this.stopcnt <= -2) {
                            this.airSoundEffects[2 + (int) (this.medium.random() * 3.0f)].loop();
                            this.stopcnt = 7;
                        }
                    }
                } else {
                    this.sparkEngine(3);
                }
                this.grrd = false;
                this.aird = false;
            } else {
                this.pwait = 15;
                if (!madness.mtouch && !this.grrd && this.medium.random() > 0.4) {
                    this.airSoundEffects[(int) (this.medium.random() * 4.0f)].loop();
                    this.stopcnt = 5;
                    this.grrd = true;
                }
                if (!madness.wtouch && !this.aird) {
                    this.stopAirSounds();
                    this.airSoundEffects[(int) (this.medium.random() * 4.0f)].loop();
                    this.stopcnt = 10;
                    this.aird = true;
                }
                this.sparkEngine(-1);
            }
            if (madness.cntdest != 0 && this.cntwis < 7) {
                if (!this.pwastd) {
                    this.wastd.loop();
                    this.pwastd = true;
                }
            } else {
                if (this.pwastd) {
                    this.wastd.stop();
                    this.pwastd = false;
                }
                if (this.cntwis == 7 && !this.isSoundMuted) {
                    this.firewasted.play();
                }
            }
        } else {
            this.sparkEngine(-2);
            if (this.pwastd) {
                this.wastd.stop();
                this.pwastd = false;
            }
        }
        if (this.stopcnt != -20) {
            if (this.stopcnt == 1) {
                this.stopAirSounds();
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
        if (this.state == GameState.GAMEPLAY || this.state == GameState.LOADING_SOUNDTRACK_COMPLETE || this.state == GameState.PLAY_REPLAY || this.state == GameState.GAME_HIGHLIGHT_1 || this.state == GameState.GAME_HIGHLIGHT_2 || this.state == GameState.END_RACE_ANIMATION || this.state == GameState.END_RACE_CONGRATS) {
            if (this.isSoundMuted != control.sound_muted) {
                this.isSoundMuted = control.sound_muted;
            }
            if (control.music_muted != this.isMusicMuted) {
                this.isMusicMuted = control.music_muted;
                if (this.isMusicMuted) {
                    if (this.loadedSoundTracks[stageNumber - 1]) {
                        this.soundTracks[stageNumber - 1].stop();
                    }
                } else if (this.loadedSoundTracks[stageNumber - 1]) {
                    this.soundTracks[stageNumber - 1].resume();
                }
            }
        }
        if (madness.cntdest != 0 && this.cntwis < 7) {
            if (madness.dest) {
                ++this.cntwis;
            }
        } else {
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
            boolean isBetween = Math.abs(a) > 25.0f && Math.abs(a) < 170.0f;
            if (n == 0) {
                if (isBetween) {
                    if (!this.isSoundMuted) {
                        this.lowCrashSoundEffects[this.crshturn].play();
                    }
                    this.bfcrash = 2;
                }
                if (Math.abs(a) > 170.0f) {
                    if (!this.isSoundMuted) {
                        this.crashSoundEffects[this.crshturn].play();
                    }
                    this.bfcrash = 2;
                }
                if (Math.abs(a) > 25.0f) {
                    if (this.crashup) {
                        --this.crshturn;
                    } else {
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
                if (isBetween) {
                    if (!this.isSoundMuted) {
                        this.lowCrashSoundEffects[2].play();
                    }
                    this.bfcrash = 2;
                }
                if (Math.abs(a) > 170.0f) {
                    if (!this.isSoundMuted) {
                        this.crashSoundEffects[2].play();
                    }
                    this.bfcrash = 2;
                }
            }
            if (n == 1) {
                if (!this.isSoundMuted) {
                    this.tires.play();
                }
                this.bfcrash = 3;
            }
        }
    }

    public float ys(final int n, int n2) {
        n2 = Math.max(n2, 50);
        return ((n2 - this.medium.focusPoint) * (this.medium.centerY - n) / n2 + n);
    }

    public void replay(final Graphics graphics) {
        Color color = new Color(0, this.aflk ? 0 : 128, this.aflk ? 0 : 255);
        this.aflk = !this.aflk;
        this.drawCharacters(graphics, 30, "Replay  >", color, 0);
    }

    private Image pressed(final Image img) {
        final int[] pixels = Util.getPixelArray(img, imageObserver);
        for (int i = 0; i < pixels.length; ++i) {
            if (pixels[i] != pixels[pixels.length - 1]) {
                pixels[i] = 0xFFFFFF;
            }
        }
        return this.createImage(new MemoryImageSource(img.getWidth(imageObserver), img.getHeight(imageObserver), pixels, 0, img.getWidth(imageObserver)));
    }

    public void nofocus(final Graphics graphics) {
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(0, 0, 550, 20);
        graphics.fillRect(0, 0, 20, 400);
        graphics.fillRect(0, 380, 550, 20);
        graphics.fillRect(530, 0, 20, 400);
        graphics.setColor(new Color(192, 192, 192));
        graphics.drawRect(20, 20, 510, 360);
        graphics.setFont(new Font("SansSerif", Font.BOLD, 11));
        this.fontMetrics = graphics.getFontMetrics();
        this.drawCharacters(graphics, 14, "Game lost its focus -Click- screen with mouse to continue.", 100, 100, 100, 3);
        this.drawCharacters(graphics, 395, "Game lost its focus -Click- screen with mouse to continue.", 100, 100, 100, 3);
    }

    public void rot(final int[] array, final int[] array2, final float n, final float n2, final float n3, final int n4) {
        if (n3 != 0) {
            for (int i = 0; i < n4; ++i) {
                final int n5 = array[i];
                final int n6 = array2[i];
                array[i] = (int) (n + ((n5 - n) * Util.cos(n3) - (n6 - n2) * Util.sin(n3)));
                array2[i] = (int) (n2 + ((n5 - n) * Util.sin(n3) + (n6 - n2) * Util.cos(n3)));
            }
        }
    }

    public boolean overon(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return n5 > n && n5 < n + n3 && n6 > n2 && n6 < n2 + n4;
    }

    public void pauseImage(final Image img, final Graphics graphics) {
        final PixelGrabber pixelGrabber = new PixelGrabber(img, 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, this.flexpix, 0, Config.SCREEN_WIDTH);
        try {
            pixelGrabber.grabPixels();
        } catch (InterruptedException e) {
            System.err.println("Error creating paused image: " + e);
        }
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
            } else {
                n6 = (n3 = (color.getRed() + color.getGreen() + color.getBlue() + n3 * 30) / 33);
            }
            if (++n4 == Config.SCREEN_WIDTH) {
                n4 = 0;
            }
            if (n5 > Config.SCREEN_WIDTH * (106 + n2) + 156 && n2 < 188) {
                final int r = (n6 + 60) / 3;
                final int g = (n6 + 135) / 3;
                final int b = (n6 + 220) / 3;
                if (++n == 237) {
                    ++n2;
                    n = 0;
                }
                this.flexpix[n5] = new Color(r, g, b).getRGB();
            } else {
                this.flexpix[n5] = new Color(n6, n6, n6).getRGB();
            }
        } while (++n5 < Config.SCREEN_PIXELS);
        graphics.drawImage(this.fleximg = this.createImage(new MemoryImageSource(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, this.flexpix, 0, Config.SCREEN_WIDTH)), 0, 0, null);
        this.medium.flex = 0;
    }

    public void loadingMusicScreen(final int stageNumber, final int n2, final Graphics graphics) {
        int r = (int) (230.0f - 230.0f * (this.medium.snapColor[0] / (float) (100 * this.hipno[stageNumber - 1])));
        r = Util.clampCol(r);
        int g = (int) (230.0f - 230.0f * (this.medium.snapColor[1] / (float) (100 * this.hipno[stageNumber - 1])));
        g = Util.clampCol(g);
        int b = (int) (230.0f - 230.0f * (this.medium.snapColor[2] / (float) (100 * this.hipno[stageNumber - 1])));
        b = Util.clampCol(b);

        if (this.hipno[stageNumber - 1] == 0) {
            r = 255;
            g = 230;
            b = 201;
        }

        fillBlankScreen(graphics, new Color(r, g, b));
        int xPos = Config.SCREEN_WIDTH / 2 - this.loadingmusic.getWidth(null) / 2;
        int yPos = Config.SCREEN_HEIGHT / 2 - this.loadingmusic.getHeight(null) / 2;
        graphics.drawImage(this.loadingmusic, xPos, yPos, null);

        if (stageNumber == 10) {
            this.drawCharacters(graphics, Config.SCREEN_HEIGHT / 2 - 50, "> Note: Guidance Arrow is disabled in this stage!", 100, 100, 100, 4);
        }
        if (stageNumber == this.unlocked) {
            this.drawCharacters(graphics, Config.SCREEN_HEIGHT / 2 - 50, getHintString(stageNumber), 100, 100, 100, 4);
        }
        this.app.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        this.app.repaint();
        if (stageNumber == 1 && !this.loadedSoundTracks[0]) {
            this.soundTracks[0] = new RadicalMod("../../resources/music/stage1.zipo", 350, 8400, 135);
            if (this.soundTracks[0].stream != null) {
                this.loadedSoundTracks[0] = true;
            }
        }
        if (stageNumber == 2 && !this.loadedSoundTracks[1]) {
            this.soundTracks[1] = new RadicalMod("../../resources/music/stage2.zipo", 370, 9000, 145);
            if (this.soundTracks[1].stream != null) {
                this.loadedSoundTracks[1] = true;
            }
        }
        if (stageNumber == 3 && !this.loadedSoundTracks[2]) {
            this.soundTracks[2] = new RadicalMod("../../resources/music/stage3.zipo", 350, 8500, 145);
            if (this.soundTracks[2].stream != null) {
                this.loadedSoundTracks[2] = true;
            }
        }
        if (stageNumber == 4 && !this.loadedSoundTracks[3]) {
            this.soundTracks[3] = new RadicalMod("../../resources/music/stage4.zipo", 300, 7500, 125);
            if (this.soundTracks[3].stream != null) {
                this.loadedSoundTracks[3] = true;
            }
        }
        if (stageNumber == 5 && !this.loadedSoundTracks[4]) {
            this.soundTracks[4] = new RadicalMod("../../resources/music/stage5.zipo", 250, 7900, 125);
            if (this.soundTracks[4].stream != null) {
                this.loadedSoundTracks[4] = true;
            }
        }
        if (stageNumber == 6 && !this.loadedSoundTracks[5]) {
            this.soundTracks[5] = new RadicalMod("../../resources/music/stage6.zipo", 760, 7900, 125);
            if (this.soundTracks[5].stream != null) {
                this.loadedSoundTracks[5] = true;
            }
        }
        if (stageNumber == 7 && !this.loadedSoundTracks[6]) {
            this.soundTracks[6] = new RadicalMod("../../resources/music/stage7.zipo", 300, 7500, 125);
            if (this.soundTracks[6].stream != null) {
                this.loadedSoundTracks[6] = true;
            }
        }
        if (stageNumber == 8 && !this.loadedSoundTracks[7]) {
            this.soundTracks[7] = new RadicalMod("../../resources/music/stage8.zipo", 400, 7900, 125);
            if (this.soundTracks[7].stream != null) {
                this.loadedSoundTracks[7] = true;
            }
        }
        if (stageNumber == 9 && !this.loadedSoundTracks[8]) {
            this.soundTracks[8] = new RadicalMod("../../resources/music/stage9.zipo", 300, 7900, 125);
            if (this.soundTracks[8].stream != null) {
                this.loadedSoundTracks[8] = true;
            }
        }
        if (stageNumber == 10 && !this.loadedSoundTracks[9]) {
            this.soundTracks[9] = new RadicalMod("../../resources/music/stage10.zipo", 550, 8100, 145);
            if (this.soundTracks[9].stream != null) {
                this.loadedSoundTracks[9] = true;
            }
        }
        if (stageNumber == 11 && !this.loadedSoundTracks[10]) {
            this.soundTracks[10] = new RadicalMod("../../resources/music/stage11.zipo", 550, 9000, 145);
            if (this.soundTracks[10].stream != null) {
                this.loadedSoundTracks[10] = true;
            }
        }
        if (n2 == 0) {
            if (this.loadedSoundTracks[stageNumber - 1]) {
                this.soundTracks[stageNumber - 1].play();
            }
            this.app.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            this.state = GameState.LOADING_SOUNDTRACK_COMPLETE;
        } else {
            this.state = GameState.LOADING_SOUNDTRACK_2;
        }
        this.pcontin = 0;
        this.isMusicMuted = false;
        this.isSoundMuted = false;
    }

    public void gamePaused(final Graphics graphics, final int n, final Control control, final Record record) {
        if (control.up) {
            graphics.drawImage(this.fleximg, 0, 0, null);
            this.opselect = (this.opselect - 1) % 4;
            control.up = false;
        }
        if (control.down) {
            graphics.drawImage(this.fleximg, 0, 0, null);
            this.opselect = (this.opselect + 1) % 4;
            control.down = false;
        }
        if (this.shaded) {
            graphics.drawImage(this.fleximg, 0, 0, null);
        }
        if (this.opselect == 0) {
            graphics.setColor(new Color(64, 143, 223));
            graphics.fillRoundRect(Config.SCREEN_WIDTH / 2 - 137 / 2, 143, 137, 22, 7, 20);
            if (this.shaded) {
                graphics.setColor(new Color(225, 200, 255));
            } else {
                graphics.setColor(new Color(0, 89, 223));
            }
            graphics.drawRoundRect(Config.SCREEN_WIDTH / 2 - 137 / 2, 143, 137, 22, 7, 20);
        }
        if (this.opselect == 1) {
            graphics.setColor(new Color(64, 143, 223));
            graphics.fillRoundRect(Config.SCREEN_WIDTH / 2 - 155 / 2, 171, 155, 22, 7, 20);
            if (this.shaded) {
                graphics.setColor(new Color(225, 200, 255));
            } else {
                graphics.setColor(new Color(0, 89, 223));
            }
            graphics.drawRoundRect(Config.SCREEN_WIDTH / 2 - 155 / 2, 171, 155, 22, 7, 20);
        }
        if (this.opselect == 2) {
            graphics.setColor(new Color(64, 143, 223));
            graphics.fillRoundRect(Config.SCREEN_WIDTH / 2 - 190 / 2, 197, 190, 22, 7, 20);
            if (this.shaded) {
                graphics.setColor(new Color(225, 200, 255));
            } else {
                graphics.setColor(new Color(0, 89, 223));
            }
            graphics.drawRoundRect(Config.SCREEN_WIDTH / 2 - 190 / 2, 197, 190, 22, 7, 20);
        }
        if (this.opselect == 3) {
            graphics.setColor(new Color(64, 143, 223));
            graphics.fillRoundRect(Config.SCREEN_WIDTH / 2 - 109 / 2, 223, 109, 22, 7, 20);
            if (this.shaded) {
                graphics.setColor(new Color(225, 200, 255));
            } else {
                graphics.setColor(new Color(0, 89, 223));
            }
            graphics.drawRoundRect(Config.SCREEN_WIDTH / 2 - 109 / 2, 223, 109, 22, 7, 20);
        }
        graphics.drawImage(this.paused, Config.SCREEN_WIDTH / 2 - this.paused.getWidth(null) / 2, 106, null);
        if (control.enter || control.handb) {
            if (this.opselect == 0) {
                if (this.loadedSoundTracks[n - 1] && !this.isMusicMuted) {
                    this.soundTracks[n - 1].resume();
                }
                this.state = GameState.GAMEPLAY;
            }
            if (this.opselect == 1) {
                if (record.caught >= 300) {
                    if (this.loadedSoundTracks[n - 1] && !this.isMusicMuted) {
                        this.soundTracks[n - 1].resume();
                    }
                    this.state = GameState.PLAY_REPLAY;
                } else {
                    this.state = GameState.REPLAY_NOT_AVAILABLE;
                }
            }
            if (this.opselect == 2) {
                this.lastState = GameState.GAME_PAUSED_2_BLURRED;
                this.state = GameState.GAME_INSTRUCTIONS;
            }
            if (this.opselect == 3) {
                this.state = GameState.MAIN_MENU;
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
            drawRadicalPlayScren(graphics);
        }
        if (this.flipo == 2) {
            graphics.drawImage(this.bgmain, 0, 0, null);
            graphics.drawImage(this.omdness, Config.SCREEN_WIDTH / 2 - this.omdness.getWidth(null) / 2, 7, null);
            graphics.setFont(new Font("SansSerif", Font.BOLD, 13));
            this.fontMetrics = graphics.getFontMetrics();
            this.drawCharacters(graphics, 65, "At Radicalplay.com", 0, 0, 0, 3);
            this.drawCharacters(graphics, 100, "Cartoon 3D Engine, Game Programming, 3D Models, Graphics and Sound Effects", 0, 0, 0, 3);
            this.drawCharacters(graphics, 120, "By Omar Waly", 0, 0, 100, 3);
            this.drawCharacters(graphics, 180, "Thanks for Game Testing", 0, 0, 0, 3);
            this.drawCharacters(graphics, 200, "Khaled Helmy, Ismail Gorilaz,", 0, 0, 100, 3);
            this.drawCharacters(graphics, 215, "Karim AboSamra, Mahmoud Waly", 0, 0, 100, 3);
            this.drawCharacters(graphics, 230, "Karim Khadem, Ahmed Ismail", 0, 0, 100, 3);
            this.drawCharacters(graphics, 245, "and Mahmoud EzzElDien (Turbo)", 0, 0, 100, 3);
            this.drawCharacters(graphics, 305, "Music From", 0, 0, 0, 3);
            this.drawCharacters(graphics, 325, "www.ModArchive.com", 0, 0, 100, 3);
            this.drawCharacters(graphics, 385, "For any comments: Omar@radicalplay.com", 0, 0, 0, 3);
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
                this.state = GameState.MAIN_MENU;
            }
            control.enter = false;
            control.handb = false;
            control.right = false;
        }
    }

    public void drawRadicalPlayScren(Graphics graphics) {
        fillBlankScreen(graphics, Color.BLACK);
        graphics.drawImage(this.radicalplay, 87, 110, null);
        graphics.setFont(new Font("SansSerif", Font.BOLD, 13));
        this.fontMetrics = graphics.getFontMetrics();
        this.drawCharacters(graphics, 150 + (int) (10.0f * this.medium.random()), "www.radicalplay.com", 112, 120, 143, 3);
        graphics.setFont(new Font("SansSerif", Font.BOLD, 11));
        this.fontMetrics = graphics.getFontMetrics();
        if (this.aflk) {
            this.drawCharacters(graphics, 190, "And we are never going to find the new unless we get a little crazy...", 112, 120, 143, 3);
            this.aflk = false;
        } else {
            this.drawCharacters(graphics, 192, "And we are never going to find the new unless we get a little crazy...", 150, 150, 150, 3);
            this.aflk = true;
        }
        graphics.drawImage(this.rpro, 150, 240, null);
    }

    public void stat(final Madness madness, final CheckPoints checkPoints, final Control control, final boolean b, final Graphics graphics) {
        this.medium.flex = 1;
        int n = 0;
        if (allCarsAreWasted()) {
            graphics.setColor(new Color(this.medium.skyColor[0], this.medium.skyColor[1], this.medium.skyColor[2]));
            graphics.fillRect(Config.SCREEN_WIDTH / 2 - this.youwastedem.getWidth(null), 70, this.youwastedem.getWidth(this.imageObserver), this.youwastedem.getHeight(this.imageObserver));
            graphics.setColor(new Color(this.medium.fadeColor[0], this.medium.fadeColor[1], this.medium.fadeColor[2]));
            graphics.drawRect(Config.SCREEN_WIDTH / 2 - this.youwastedem.getWidth(null), 70, this.youwastedem.getWidth(this.imageObserver), this.youwastedem.getHeight(this.imageObserver));
            graphics.drawImage(this.youwastedem, Config.SCREEN_WIDTH / 2 - this.youwastedem.getWidth(null), 70, null);
            if (this.aflk) {
                this.drawCharacters(graphics, 120, "You Won, all cars have been wasted!", 0, 0, 0, 0);
                this.aflk = false;
            } else {
                this.drawCharacters(graphics, 120, "You Won, all cars have been wasted!", 0, 128, 255, 0);
                this.aflk = true;
            }
            this.drawCharacters(graphics, Config.SCREEN_HEIGHT - 20, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
            checkPoints.haltall = true;
            n = 1;
            this.winner = true;
        }
        if (n == 0 && madness.dest && this.cntwis == 8) {
            graphics.setColor(new Color(this.medium.skyColor[0], this.medium.skyColor[1], this.medium.skyColor[2]));
            graphics.fillRect(Config.SCREEN_WIDTH / 2 - this.yourwasted.getWidth(null) / 2, 70, this.yourwasted.getWidth(this.imageObserver), this.yourwasted.getHeight(this.imageObserver));
            graphics.setColor(new Color(this.medium.fadeColor[0], this.medium.fadeColor[1], this.medium.fadeColor[2]));
            graphics.drawRect(Config.SCREEN_WIDTH / 2 - this.yourwasted.getWidth(null) / 2, 70, this.yourwasted.getWidth(this.imageObserver), this.yourwasted.getHeight(this.imageObserver));
            graphics.drawImage(this.yourwasted, (Config.SCREEN_WIDTH / 2) - (this.yourwasted.getWidth(null) / 2), 70, null);
            this.drawCharacters(graphics, Config.SCREEN_HEIGHT - 20, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
            n = 1;
            this.winner = false;
        }
        if (n == 0) {
            int n2 = 0;
            do {
                if (checkPoints.clear[n2] == checkPoints.nlaps * checkPoints.nsp && checkPoints.pos[n2] == 0) {
                    if (n2 == 0) {
                        if (this.medium.flex != 2) {
                            graphics.setColor(new Color(this.medium.skyColor[0], this.medium.skyColor[1], this.medium.skyColor[2]));
                            graphics.fillRect(Config.SCREEN_WIDTH / 2 - this.youwon.getWidth(null) / 2, 70, this.youwon.getWidth(this.imageObserver), this.youwon.getHeight(this.imageObserver));
                            graphics.setColor(new Color(this.medium.fadeColor[0], this.medium.fadeColor[1], this.medium.fadeColor[2]));
                            graphics.drawRect(Config.SCREEN_WIDTH / 2 - this.youwon.getWidth(null) / 2, 70, this.youwon.getWidth(this.imageObserver), this.youwon.getHeight(this.imageObserver));
                        }
                        graphics.drawImage(this.youwon, Config.SCREEN_WIDTH / 2 - this.youwon.getWidth(null) / 2, 70, null);
                        if (this.aflk) {
                            this.drawCharacters(graphics, 120, "You finished first, nice job!", 0, 0, 0, 0);
                            this.aflk = false;
                        } else {
                            this.drawCharacters(graphics, 120, "You finished first, nice job!", 0, 128, 255, 0);
                            this.aflk = true;
                        }
                        this.winner = true;
                    } else {
                        if (this.medium.flex != 2) {
                            graphics.setColor(new Color(this.medium.skyColor[0], this.medium.skyColor[1], this.medium.skyColor[2]));
                            graphics.fillRect(Config.SCREEN_WIDTH / 2 - this.youlost.getWidth(null) / 2, 70, this.youlost.getWidth(this.imageObserver), this.youlost.getHeight(this.imageObserver));
                            graphics.setColor(new Color(this.medium.fadeColor[0], this.medium.fadeColor[1], this.medium.fadeColor[2]));
                            graphics.drawRect(Config.SCREEN_WIDTH / 2 - this.youlost.getWidth(null) / 2, 70, this.youlost.getWidth(this.imageObserver), this.youlost.getHeight(this.imageObserver));
                        }
                        graphics.drawImage(this.youlost, Config.SCREEN_WIDTH / 2 - this.youlost.getWidth(null) / 2, 70, null);
                        if (this.aflk) {
                            this.drawCharacters(graphics, 120, "" + this.carNames[this.selectedCar[n2]] + " finished first, race over!", 0, 0, 0, 0);
                            this.aflk = false;
                        } else {
                            this.drawCharacters(graphics, 120, "" + this.carNames[this.selectedCar[n2]] + " finished first, race over!", 0, 128, 255, 0);
                            this.aflk = true;
                        }
                        this.winner = false;
                    }
                    this.drawCharacters(graphics, Config.SCREEN_HEIGHT - 20, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
                    checkPoints.haltall = true;
                    n = 1;
                }
            } while (++n2 < 5);
        }
        if (n != 0) {
            ++this.holdcnt;
            if (control.enter || this.holdcnt > 250) {
                this.state = GameState.GAME_HIGHLIGHT_1;
                control.enter = false;
            }
        } else {
            if (this.holdcnt != 0) {
                this.holdcnt = 0;
            }
            if (control.enter) {
                if (this.loadedSoundTracks[checkPoints.stage - 1]) {
                    this.soundTracks[checkPoints.stage - 1].stop();
                }
                this.state = GameState.GAME_PAUSED;
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
            if (n == 0 && checkPoints.stage != 10 && this.startCount == 0) {
                this.arrow(graphics, madness.point, madness.missedCheckpoint, checkPoints, this.arrace);
                if (!this.arrace && this.auscnt == 45 && madness.capcnt == 0) {
                    if (madness.missedCheckpoint > 0) {
                        if (madness.missedCheckpoint > 15 && madness.missedCheckpoint < 50) {
                            if (this.flk) {
                                this.drawCharacters(graphics, 70, "CheckPoint Missed!", 255, 150, 0, 2);
                            } else {
                                this.drawCharacters(graphics, 70, "CheckPoint Missed!", 255, 0, 0, 2);
                            }
                        }
                        ++madness.missedCheckpoint;
                        if (madness.missedCheckpoint == 70) {
                            madness.missedCheckpoint = -2;
                        }
                    } else if (madness.mtouch && this.cntovn < 70) {
                        if (Math.abs(this.ana) > 100) {
                            ++this.cntan;
                        } else if (this.cntan != 0) {
                            --this.cntan;
                        }
                        if (this.cntan > 40) {
                            ++this.cntovn;
                            this.cntan = 40;
                            if (this.flk) {
                                this.drawCharacters(graphics, 70, "Wrong Way!", 255, 150, 0, 2);
                                this.flk = false;
                            } else {
                                this.drawCharacters(graphics, 70, "Wrong Way!", 255, 0, 0, 2);
                                this.flk = true;
                            }
                        }
                    }
                }
            }
            if (this.medium.flex != 2) {
                graphics.drawImage(this.damageMeter, Config.SCREEN_WIDTH - this.damageMeter.getWidth(null) - 14, 7, null);
                graphics.drawImage(this.powerMeter, Config.SCREEN_WIDTH - this.powerMeter.getWidth(null) - 14, 27, null);
                graphics.drawImage(this.lap, 19, 7, null);
                graphics.setColor(new Color(0, 0, 100));
                graphics.drawString("" + (madness.nlaps + 1) + " / " + checkPoints.nlaps + "", 51, 18);
                graphics.drawImage(this.was, 92, 7, null);
                graphics.setColor(new Color(0, 0, 100));
                graphics.drawString("" + checkPoints.wasted + " / 4", 150, 18);
                graphics.drawImage(this.pos, 42, 27, null);
                graphics.drawImage(this.rankImagesSnap[checkPoints.pos[madness.im]], 110, 28, null);
            } else {
                if (this.posit != checkPoints.pos[madness.im]) {
                    graphics.drawImage(this.rankImagesSnap[checkPoints.pos[madness.im]], 110, 28, null);
                    this.posit = checkPoints.pos[madness.im];
                }
                if (this.wasted != checkPoints.wasted) {
                    graphics.setColor(new Color(0, 0, 100));
                    graphics.drawString("" + checkPoints.wasted + " / 4", 150, 18);
                    this.wasted = checkPoints.wasted;
                }
                if (this.laps != madness.nlaps) {
                    graphics.setColor(new Color(0, 0, 100));
                    graphics.drawString("" + (madness.nlaps + 1) + " / " + checkPoints.nlaps + "", 51, 18);
                    this.laps = madness.nlaps;
                }
            }
            this.drawStat(graphics, madness.maxDamage[madness.carIndex], madness.currentDamage, madness.newcar, madness.power);
        }
        if (n == 0) {
            if (this.startCount != 0) {
                if (this.startCount == 35 && !this.isSoundMuted) {
                    this.three.play();
                }
                if (this.startCount == 24) {
                    this.gocnt = 2;
                    if (!this.isSoundMuted) {
                        this.two.play();
                    }
                }
                if (this.startCount == 13) {
                    this.gocnt = 1;
                    if (!this.isSoundMuted) {
                        this.one.play();
                    }
                }
                if (this.startCount == 2) {
                    this.gocnt = 0;
                    if (!this.isSoundMuted) {
                        this.go.play();
                    }
                }
                graphics.drawImage(this.countdownImagesSnap[this.gocnt], Config.SCREEN_WIDTH / 2 - this.countdownImagesSnap[this.gocnt].getWidth(null) / 2, 50, null);
            }
            if (this.looped != 0 && madness.airState == 2) {
                this.looped = 0;
            }
            if (madness.power < 45.0f) {
                if (this.tcnt == 30 && this.auscnt == 45 && madness.mtouch && madness.capcnt == 0) {
                    if (this.pwcnt < 70) {
                        if (this.looped != 3) {
                            if (this.powerBlink) {
                                this.drawCharacters(graphics, 110, "Power low, perform stunt!", 0, 0, 200, 0);
                                this.powerBlink = false;
                            } else {
                                this.drawCharacters(graphics, 110, "Power low, perform stunt!", 255, 100, 0, 0);
                                this.powerBlink = true;
                            }
                        } else if (this.powerBlink) {
                            this.drawCharacters(graphics, 110, ">> Press Enter for game instructions! <<", 0, 0, 200, 0);
                            this.powerBlink = false;
                        } else {
                            this.drawCharacters(graphics, 110, ">> Press Enter for game instructions! <<", 255, 100, 0, 0);
                            this.powerBlink = true;
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
            } else if (this.pwcnt != 0) {
                this.pwcnt = 0;
            }
            if (madness.capcnt == 0) {
                if (this.tcnt < 30) {
                    if (this.tflk) {
                        this.drawCharacters(graphics, 105, this.say, 0, 0, 0, 0);
                        this.tflk = false;
                    } else {
                        if (!this.wasay) {
                            this.drawCharacters(graphics, 105, this.say, 0, 128, 255, 0);
                        } else {
                            this.drawCharacters(graphics, 105, this.say, 255, 128, 0, 0);
                        }
                        this.tflk = true;
                    }
                    ++this.tcnt;
                } else if (this.wasay) {
                    this.wasay = false;
                }
                if (this.auscnt < 45) {
                    if (this.aflk) {
                        this.drawCharacters(graphics, 85, this.asay, 98, 176, 255, 0);
                        this.aflk = false;
                    } else {
                        this.drawCharacters(graphics, 85, this.asay, 0, 128, 255, 0);
                        this.aflk = true;
                    }
                    ++this.auscnt;
                }
            } else if (this.tflk) {
                this.drawCharacters(graphics, 110, "Bad Landing!", 0, 0, 200, 0);
                this.tflk = false;
            } else {
                this.drawCharacters(graphics, 110, "Bad Landing!", 255, 100, 0, 0);
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
                    } else if (madness.ftab || madness.btab) {
                        this.loop = "Tabletop";
                    }
                }
                if (n3 > 0 && madness.btab) {
                    this.loop = "Hanged " + this.loop;
                }
                if (n3 < 0 && madness.ftab) {
                    this.loop = "Hanged " + this.loop;
                }
                if (!this.loop.equals("")) {
                    this.asay = this.asay + " " + this.loop;
                }
                int n4 = 0;
                madness.travxy = Math.abs(madness.travxy);
                while (madness.travxy > 270) {
                    madness.travxy -= 360;
                    ++n4;
                }
                if (n4 == 0 && madness.rtab) {
                    if ("".equals(this.loop)) {
                        this.spin = "Tabletop";
                    } else {
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
                    if ("".equals(this.loop) && "".equals(this.spin)) {
                        this.asay = this.asay + " " + n5;
                    } else {
                        if (!"".equals(this.spin)) {
                            if ("".equals(this.loop)) {
                                this.asay = this.asay + " " + this.spin;
                            } else {
                                this.asay = this.asay + " with " + this.spin;
                            }
                        }
                        this.asay = this.asay + " by " + n5;
                    }
                    if (b2) {
                        this.asay += " and beyond";
                    }
                } else if (!"".equals(this.spin)) {
                    if ("".equals(this.loop)) {
                        this.asay = this.asay + " " + this.spin;
                    } else {
                        this.asay = this.asay + " by " + this.spin;
                    }
                }
                if (!"".equals(this.asay)) {
                    this.auscnt -= 15;
                }
                if (!"".equals(this.loop)) {
                    this.auscnt -= 25;
                }
                if (!"".equals(this.spin)) {
                    this.auscnt -= 25;
                }
                if (n5 != 0) {
                    this.auscnt -= 25;
                }
                if (this.auscnt < 45) {
                    if (!this.isSoundMuted) {
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
                        this.asay = " " + this.adj[4][(int) (this.medium.random() * 3.0f)] + this.asay;
                    }
                    if (n6 != 3) {
                        this.asay = this.adj[n6][(int) (this.medium.random() * 3.0f)] + this.asay + this.exlm[n6];
                    } else {
                        this.asay = this.adj[n6][(int) (this.medium.random() * 3.0f)];
                    }
                    if (!this.wasay) {
                        this.tcnt = this.auscnt;
                        if (madness.power != 98.0f) {
                            this.say = "Power Up " + (int) (100.0f * madness.powerup / 98.0f) + "%";
                        } else {
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
                this.crashup = !this.crashup;
            }
            if (this.clear != madness.clear && madness.clear != 0) {
                if (!this.wasay) {
                    this.say = "CheckPoint";
                    this.tcnt = 15;
                }
                this.clear = madness.clear;
                if (!this.isSoundMuted) {
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
                        this.say = "" + this.carNames[this.selectedCar[n7]] + " has been wasted!";
                        this.tcnt = -15;
                    }
                    if (this.dested[n7] != 2) {
                        continue;
                    }
                    this.wasay = true;
                    this.say = "You wasted " + this.carNames[this.selectedCar[n7]] + "!";
                    this.tcnt = -15;
                }
            } while (++n7 < 5);
        }
    }

    private boolean allCarsAreWasted() {
        return this.wasted == 4;
    }

    public void finish(final CheckPoints checkPoints, final Geometry[] array, final Control control, final Graphics graphics) {
        graphics.drawImage(this.fleximg, 0, 0, null);
        if (this.winner) {
            if (checkPoints.stage == this.unlocked) {
                if (checkPoints.stage != 11) {
                    graphics.drawImage(this.congrd, 140, 30, null);
                    this.drawCharacters(graphics, 80, "You completed stage " + checkPoints.stage + " : " + checkPoints.name + " !", 144, 167, 255, 3);
                } else {
                    graphics.drawImage(this.congrd, 135 + (int) (this.medium.random() * 10.0f), 30, null);
                }
                int n = 0;
                int y = 0;
                this.pin = 60;
                if (checkPoints.stage == 2) {
                    n = 5;
                    y = 60;
                    this.pin = 0;
                    this.selectedCar[0] = 5;
                }
                if (checkPoints.stage == 4) {
                    n = 6;
                    y = 40;
                    this.pin = 0;
                    this.selectedCar[0] = 6;
                }
                if (checkPoints.stage == 6) {
                    n = 7;
                    y = 25;
                    this.pin = 0;
                    this.selectedCar[0] = 7;
                }
                if (checkPoints.stage == 8) {
                    n = 8;
                    y = 70;
                    this.pin = 0;
                    this.selectedCar[0] = 8;
                }
                if (checkPoints.stage == 10) {
                    n = 9;
                    y = 80;
                    this.pin = 0;
                    this.selectedCar[0] = 9;
                }
                if (checkPoints.stage != 11) {
                    graphics.setFont(new Font("SansSerif", Font.BOLD, 13));
                    this.fontMetrics = graphics.getFontMetrics();
                    if (this.aflk) {
                        this.drawCharacters(graphics, 120 + this.pin, "Stage " + (checkPoints.stage + 1) + " unlocked!", 144, 167, 255, 3);
                    } else {
                        this.drawCharacters(graphics, 120 + this.pin, "Stage " + (checkPoints.stage + 1) + " unlocked!", 208, 240, 255, 3);
                    }
                    if (n != 0) {
                        if (this.aflk) {
                            this.drawCharacters(graphics, 150, "And:", 144, 167, 255, 3);
                        } else {
                            this.drawCharacters(graphics, 150, "And:", 208, 240, 255, 3);
                        }
                        graphics.setColor(new Color(208, 240, 255));
                        graphics.fillRect(200, 170, 150, 100);
                        graphics.setColor(new Color(176, 217, 240));
                        graphics.fillRect(201, 171, 148, 4);
                        graphics.fillRect(201, 171, 4, 98);
                        graphics.fillRect(201, 265, 148, 4);
                        graphics.fillRect(345, 171, 4, 98);
                        array[n].y = y;
                        this.medium.crs = true;
                        this.medium.positionX = -275;
                        this.medium.positionY = -500;
                        this.medium.positionZ = -50;
                        this.medium.xz = 0;
                        this.medium.zy = 10;
                        this.medium.ground = 2470;
                        array[n].z = 1500;
                        array[n].x = 0;
                        final Geometry geometry = array[n];
                        geometry.xz += 5;
                        array[n].zy = 0;
                        final Geometry geometry2 = array[n];
                        geometry2.wzy -= 10;
                        array[n].draw(graphics);
                        if (this.aflk) {
                            graphics.setColor(new Color(0, 128, 255));
                        } else {
                            graphics.setColor(new Color(255, 0, 0));
                        }
                        graphics.drawRect(197, 167, 155, 105);
                        if (this.aflk) {
                            this.drawCharacters(graphics, 300, "" + this.carNames[n] + " has been unlocked!", 144, 167, 255, 3);
                        } else {
                            this.drawCharacters(graphics, 300, "" + this.carNames[n] + " has been unlocked!", 208, 240, 255, 3);
                        }
                    }
                    graphics.setFont(new Font("SansSerif", Font.BOLD, 11));
                    this.fontMetrics = graphics.getFontMetrics();
                    if (this.aflk) {
                        this.drawCharacters(graphics, 335 - this.pin, "( Game Saved )", 112, 120, 143, 3);
                    } else {
                        this.drawCharacters(graphics, 335 - this.pin, "( Game Saved )", 150, 150, 150, 3);
                    }
                } else {
                    graphics.setFont(new Font("SansSerif", Font.BOLD, 13));
                    this.fontMetrics = graphics.getFontMetrics();
                    if (this.aflk) {
                        this.drawCharacters(graphics, 120, "Woohoooo you finished the game!!!", 144, 167, 255, 3);
                    } else {
                        this.drawCharacters(graphics, 120, "Woohoooo you finished the game!!!", 208, 240, 255, 3);
                    }
                    if (this.aflk) {
                        this.drawCharacters(graphics, 150, "Your Awesome!", 144, 167, 255, 3);
                    } else {
                        this.drawCharacters(graphics, 152, "Your Awesome!", 228, 240, 255, 3);
                    }
                    if (this.aflk) {
                        this.drawCharacters(graphics, 200, "Your Truly a RADICAL GAMER!", 144, 167, 255, 3);
                    } else {
                        this.drawCharacters(graphics, 200, "Your Truly a RADICAL GAMER!", 228, 240, 255, 3);
                    }
                    graphics.drawImage(this.radicalplay, 95, 205, null);
                    graphics.setFont(new Font("SansSerif", Font.BOLD, 11));
                    this.fontMetrics = graphics.getFontMetrics();
                    if (this.aflk) {
                        this.drawCharacters(graphics, 270, "Be sure to check Radicalplay.com for more action!", 144, 167, 255, 3);
                    } else {
                        this.drawCharacters(graphics, 270, "Be sure to check Radicalplay.com for more action!", 208, 240, 255, 3);
                    }
                    this.pin = 40;
                }
                this.aflk = !this.aflk;
            } else {
                this.pin = 100;
                graphics.drawImage(this.congrd, 140, 117, null);
                this.drawCharacters(graphics, 167, "You completed stage " + checkPoints.stage + " : " + checkPoints.name + " !", 144, 167, 255, 3);
            }
        } else {
            this.pin = 100;
            graphics.drawImage(this.gameov, (Config.SCREEN_WIDTH / 2) - this.gameov.getWidth(null) / 2, 117, null);
            this.drawCharacters(graphics, 167, "You lost at stage " + checkPoints.stage + " : " + checkPoints.name + " !", 144, 167, 255, 3);
            if (checkPoints.stage == this.unlocked) {
                if (checkPoints.stage == 1) {
                    this.drawCharacters(graphics, 205, "> Don't forget, you must pass in all checkpoints to complete a lap...", 128, 128, 128, 3);
                }
                if (checkPoints.stage == 2) {
                    this.drawCharacters(graphics, 205, "> Don't forget, you need power to be up to race faster...", 128, 128, 128, 3);
                }
                if (checkPoints.stage == 3) {
                    this.drawCharacters(graphics, 205, "> Hint: its easier to waste the other cars then to finish 1st in this stage...", 128, 128, 128, 3);
                    this.drawCharacters(graphics, 220, "( Press [A] to make Guidance Arrow point to cars )", 128, 128, 128, 3);
                }
                if (checkPoints.stage == 4) {
                    this.drawCharacters(graphics, 205, "> Remember the better the stunt the better the power you get...", 128, 128, 128, 3);
                }
                if (checkPoints.stage == 5) {
                    this.drawCharacters(graphics, 205, "> Remember the more the power the more faster and powerful your car is...", 128, 128, 128, 3);
                }
            }
        }
        graphics.drawImage(this.contin2[this.pcontin], 230, 350 - this.pin, null);
        if (control.enter || control.handb) {
            this.state = GameState.MAIN_MENU;
            if (this.loadedSoundTracks[checkPoints.stage - 1]) {
                this.soundTracks[checkPoints.stage - 1].stop();
            }
            if (checkPoints.stage == this.unlocked && this.winner && this.unlocked != 11) {
                ++checkPoints.stage;
                ++this.unlocked;
            }
            control.enter = false;
            control.handb = false;
        }
    }

    public void sortCars(final int n) {
        if (n != 0) {
            final boolean[] array = new boolean[5];
            if (this.unlocked == n && this.unlocked != 11) {
                this.selectedCar[4] = 4 + (n + 1) / 2;
                int n2 = 1;
                do {
                    array[n2] = false;
                    while (!array[n2]) {
                        this.selectedCar[n2] = (int) (Math.random() * (4 + (n + 1) / 2));
                        array[n2] = true;
                        int n3 = 0;
                        do {
                            if (n2 != n3 && this.selectedCar[n2] == this.selectedCar[n3]) {
                                array[n2] = false;
                            }
                        } while (++n3 < 5);
                        if (Math.random() < this.proba[this.selectedCar[n2]]) {
                            array[n2] = false;
                        }
                        if (this.selectedCar[n2] == 1 && n == 6) {
                            array[n2] = false;
                        }
                        if (this.selectedCar[n2] == 7 && (n == 8 || n == 9 || n == 7)) {
                            array[n2] = false;
                        }
                    }
                } while (++n2 < 4);
                if (n == 10) {
                    boolean b = false;
                    int n4 = 0;
                    if (this.selectedCar[0] != 7) {
                        do {
                            if (this.selectedCar[n4] == 7) {
                                b = true;
                            }
                        } while (++n4 < 5);
                        if (!b) {
                            if (Math.random() > Math.random()) {
                                this.selectedCar[1] = 7;
                            } else {
                                this.selectedCar[2] = 7;
                            }
                        }
                    } else {
                        do {
                            if (this.selectedCar[n4] == 8) {
                                b = true;
                            }
                        } while (++n4 < 5);
                        if (!b) {
                            if (Math.random() > Math.random()) {
                                this.selectedCar[1] = 8;
                            }
                            this.selectedCar[2] = 8;
                        }
                    }
                }
            } else {
                int n6 = 5;
                if (this.selectedCar[0] != 4 + (n + 1) / 2 && n != 11) {
                    this.selectedCar[4] = 4 + (n + 1) / 2;
                    n6 = 4;
                }
                for (int i = 1; i < n6; ++i) {
                    array[i] = false;
                    while (!array[i]) {
                        int unlocked = this.unlocked;
                        if (unlocked == 11) {
                            unlocked = 10;
                        }
                        this.selectedCar[i] = (int) (Math.random() * (5 + (unlocked + 1) / 2));
                        array[i] = true;
                        int n7 = 0;
                        do {
                            if (i != n7 && this.selectedCar[i] == this.selectedCar[n7]) {
                                array[i] = false;
                            }
                        } while (++n7 < 5);
                        if (Math.random() < this.proba[this.selectedCar[i]]) {
                            array[i] = false;
                        }
                    }
                }
            }
        }
    }

    public void sparkEngine(int n) {
        ++n;
        int n2 = 0;
        do {
            if (n == n2) {
                if (this.playingEngineSound[n2]) {
                    continue;
                }
                this.engineSounds[this.enginsignature[this.selectedCar[0]][n2]][n2].loop();
                this.playingEngineSound[n2] = true;
            } else {
                if (!this.playingEngineSound[n2]) {
                    continue;
                }
                this.engineSounds[this.enginsignature[this.selectedCar[0]][n2]][n2].stop();
                this.playingEngineSound[n2] = false;
            }
        } while (++n2 < 5);
    }

    public void drawCharacters(final Graphics graphics, final int yPosition, final String str, Color color, final int n2) {
        drawCharacters(graphics, yPosition, str, color.getRed(), color.getBlue(), color.getGreen(), n2);
    }


    public void drawCharacters(final Graphics graphics, final int yPosition, final String str, int r, int g, int b, final int n2) {
        if (n2 != 3 && n2 != 4) {
            r += (int) (r * (this.medium.snapColor[0] / 100.0f));
            r = Util.clamp(r, 0, 255);
            g += (int) (g * (this.medium.snapColor[1] / 100.0f));
            g = Util.clamp(g, 0, 255);
            b += (int) (b * (this.medium.snapColor[2] / 100.0f));
            b = Util.clamp(b, 0, 255);
        }
        if (n2 == 4) {
            r -= (int) (r * (this.medium.snapColor[0] / 100.0f));
            r = Util.clamp(r, 0, 255);
            g -= (int) (g * (this.medium.snapColor[1] / 100.0f));
            g = Util.clamp(g, 0, 255);
            b -= (int) (b * (this.medium.snapColor[2] / 100.0f));
            b = Util.clamp(b, 0, 255);

        }
        if (n2 == 1) {
            graphics.setColor(new Color(0, 0, 0));
            graphics.drawString(str, Config.SCREEN_WIDTH / 2 - this.fontMetrics.stringWidth(str) / 2 + 1, yPosition + 1);
        }
        if (n2 == 2) {
            graphics.setColor(new Color((r + this.medium.skyColor[0] * 2) / 3, (g + this.medium.skyColor[1] * 2) / 3, (b + this.medium.skyColor[2] * 2) / 3));
            graphics.drawString(str, Config.SCREEN_WIDTH / 2 - this.fontMetrics.stringWidth(str) / 2 + 1, yPosition + 1);
        }
        graphics.setColor(new Color(r, g, b));
        graphics.drawString(str, Config.SCREEN_WIDTH / 2 - this.fontMetrics.stringWidth(str) / 2, yPosition);
    }

    public int py(final int n, final int n2, final int n3, final int n4) {
        return (n - n2) * (n - n2) + (n3 - n4) * (n3 - n4);
    }

    public void stageSelectBg(final Graphics graphics) {
        if (this.stages.posit() > 240000 || this.medium.nrnd <= 1) {
            graphics.drawImage(this.trackbg, 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, null);
        }
    }

    void drawStageSelectImgs(Graphics graphics) {
        graphics.drawImage(this.barLeft, 0, 0, this.barLeft.getWidth(null), Config.SCREEN_HEIGHT, null);
        graphics.drawImage(this.barTop, 0, 0, Config.SCREEN_WIDTH, this.barTop.getHeight(null), null);
        graphics.drawImage(this.barRight, Config.SCREEN_WIDTH - this.barRight.getWidth(null), 0, this.barRight.getWidth(null), Config.SCREEN_HEIGHT, null);
        graphics.drawImage(this.barBottom, 0, Config.SCREEN_HEIGHT - this.barTop.getHeight(null), Config.SCREEN_WIDTH, this.barTop.getHeight(null), null);
        graphics.drawImage(this.selectStage, (Config.SCREEN_WIDTH / 2) - this.selectStage.getWidth(null), 45, null);
    }

    public void stageSelect(final Graphics graphics, final CheckPoints checkPoints, final Control control) {
        this.stages.play();
        drawStageSelectImgs(graphics);
        if (checkPoints.stage != 1) {
            graphics.drawImage(this.back[this.pback], 50, 110, null);
        }
        if (checkPoints.stage != 11) {
            graphics.drawImage(this.next[this.pnext], 440, 110, null);
        }
        graphics.drawImage(this.contin1[this.pcontin], 232, 170, null);
        graphics.setFont(new Font("SansSerif", Font.BOLD, 13));
        this.fontMetrics = graphics.getFontMetrics();
        if (checkPoints.stage != 11) {
            this.drawCharacters(graphics, 110, "Stage " + checkPoints.stage + "  >", 255, 255, 255, 3);
        } else {
            this.drawCharacters(graphics, 110, "Final Party Stage  >", 255, 255, 255, 3);
        }
        this.drawCharacters(graphics, 130, "| " + checkPoints.name + " |", 32, 48, 98, 3);
        graphics.setFont(new Font("SansSerif", Font.BOLD, 11));
        this.fontMetrics = graphics.getFontMetrics();
        this.drawCharacters(graphics, 396, "Use keyboard Arrows and press Enter to continue", 0, 0, 0, 3);
        if (control.handb || control.enter) {
            this.state = GameState.LOADING_SOUNDTRACK_1;
            this.medium.trk = false;
            control.handb = false;
            control.enter = false;
            this.stages.stop();
        }
        if (control.right && checkPoints.stage != 11) {
            if (checkPoints.stage != this.unlocked) {
                ++checkPoints.stage;
                this.state = GameState.LOADING_STAGE;
            } else {
                this.state = GameState.STAGE_LOCKED_UNSAFE;
                this.lockcnt = 70;
            }
            control.right = false;
        }
        if (control.left && checkPoints.stage != 1) {
            --checkPoints.stage;
            this.state = GameState.LOADING_STAGE;
            control.left = false;
        }
    }

    public void snapImages(final int n) {
        this.damageMeter = this.createSnappedImage(this.odmg);
        this.powerMeter = this.createSnappedImage(this.opwr);
        this.was = this.createSnappedImage(this.owas);
        this.lap = this.createSnappedImage(this.olap);
        this.pos = this.createSnappedImage(this.opos);
        for (int i = 0; i < 5; i++) {
            this.rankImagesSnap[i] = this.createSnappedImage(this.rankImages[i]);
        }
        for (int i = 0; i < 4; i++) {
            this.countdownImagesSnap[i] = this.createSnappedImage(this.countDownImages[i]);
        }
        this.yourwasted = this.createSnappedImage(this.oyourwasted);
        this.youlost = this.createSnappedImage(this.oyoulost);
        this.youwon = this.createSnappedImage(this.oyouwon);
        this.youwastedem = this.createSnappedImage(this.oyouwastedem);
        this.gameh = this.createSnappedImage(this.ogameh);
        this.mdness = this.createSnappedImage(this.omdness);
        this.loadingmusic = this.loadOpSnap(this.oloadingmusic, n);
        this.star[0] = this.loadOpSnap(this.ostar[0], n);
        this.star[1] = this.loadOpSnap(this.ostar[1], n);
    }

    private Image createSnappedImage(final Image img) {
        final int[] pixels = Util.getPixelArray(img, imageObserver);
        for (int i = 0; i < pixels.length; ++i) {
            if (pixels[i] != pixels[pixels.length - 1]) {
                int[] RGBs = snapRGBs(pixels[i], medium.snapColor);
                pixels[i] = new Color(RGBs[0], RGBs[1], RGBs[2], 255).getRGB();
            }
        }
        return this.createImage(new MemoryImageSource(img.getWidth(imageObserver), img.getHeight(imageObserver), pixels, 0, img.getWidth(imageObserver)));
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
        this.startCount = 90;
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
        this.sortCars(n);
    }

    public void drawStat(final Graphics graphics, final int maxDamage, int currentDamage, final boolean newCar, final float power) {
        final int[] xCoordinates = new int[4];
        final int[] yCoordinates = new int[4];
        if (newCar) {
            xCoordinates[0] = Config.SCREEN_WIDTH - 127;
            yCoordinates[0] = 11;
            xCoordinates[1] = Config.SCREEN_WIDTH - 127;
            yCoordinates[1] = 19;
            xCoordinates[2] = Config.SCREEN_WIDTH - 30;
            yCoordinates[2] = 19;
            xCoordinates[3] = Config.SCREEN_WIDTH - 30;
            yCoordinates[3] = 11;
            graphics.setColor(new Color(this.medium.skyColor[0], this.medium.skyColor[1], this.medium.skyColor[2]));
            graphics.fillPolygon(xCoordinates, yCoordinates, 4);
        }

        currentDamage = Math.min(currentDamage, maxDamage);

        final int damageBarWidth = (int) (98.0f * (currentDamage / (float) maxDamage));
        xCoordinates[0] = Config.SCREEN_WIDTH - 128;
        yCoordinates[0] = 11;
        xCoordinates[1] = Config.SCREEN_WIDTH - 128;
        yCoordinates[1] = 20;
        xCoordinates[2] = Config.SCREEN_WIDTH - 128 + damageBarWidth;
        yCoordinates[2] = 20;
        xCoordinates[3] = Config.SCREEN_WIDTH - 128 + damageBarWidth;
        yCoordinates[3] = 11;
        final int n5 = 244;
        int n6 = 244;
        final int n7 = 11;
        if (damageBarWidth > 33) {
            n6 = (int) (244.0f - 233.0f * ((damageBarWidth - 33) / 65.0f));
        }
        if (damageBarWidth > 70) {
            if (this.dmcnt < 10) {
                if (this.dmflk) {
                    n6 = 170;
                    this.dmflk = false;
                } else {
                    this.dmflk = true;
                }
            }
            ++this.dmcnt;
            if (this.dmcnt > 167.0 - damageBarWidth * 1.5) {
                this.dmcnt = 0;
            }
        }
        int r = (int) (n5 + n5 * (this.medium.snapColor[0] / 100.0f));
        r = Util.clamp(r, 0, 255);
        int g = (int) (n6 + n6 * (this.medium.snapColor[1] / 100.0f));
        g = Util.clamp(g, 0, 255);
        int b2 = (int) (n7 + n7 * (this.medium.snapColor[2] / 100.0f));
        b2 = Util.clamp(b2, 0, 255);
        graphics.setColor(new Color(r, g, b2));
        graphics.fillPolygon(xCoordinates, yCoordinates, 4);

        xCoordinates[0] = Config.SCREEN_WIDTH - 128;
        yCoordinates[0] = 31;
        xCoordinates[1] = Config.SCREEN_WIDTH - 128;
        yCoordinates[1] = 40;
        xCoordinates[2] = (int) (Config.SCREEN_WIDTH - 128 + power);
        yCoordinates[2] = 40;
        xCoordinates[3] = (int) (Config.SCREEN_WIDTH - 128 + power);
        yCoordinates[3] = 31;
        int n8 = 128;
        if (power == 98.0f) {
            n8 = 64;
        }
        int n9 = (int) (190.0 + power * 0.37);
        int n10 = 244;
        if (this.auscnt < 45 && this.aflk) {
            n8 = 128;
            n9 = 244;
            n10 = 244;
        }
        int r2 = (int) (n8 + n8 * (this.medium.snapColor[0] / 100.0f));
        r2 = Util.clamp(r2, 0, 255);
        int g2 = (int) (n9 + n9 * (this.medium.snapColor[1] / 100.0f));
        g2 = Util.clamp(g2, 0, 255);
        int b3 = (int) (n10 + n10 * (this.medium.snapColor[2] / 100.0f));
        b3 = Util.clamp(b3, 0, 255);
        graphics.setColor(new Color(r2, g2, b3));
        graphics.fillPolygon(xCoordinates, yCoordinates, 4);

        if (/*this.medium.flex == 2 && */power != 98.0f) {
            xCoordinates[0] = (int) (422.0f + power);
            yCoordinates[0] = 31;
            xCoordinates[1] = (int) (422.0f + power);
            yCoordinates[1] = 39;
            xCoordinates[2] = 520;
            yCoordinates[2] = 39;
            xCoordinates[3] = 520;
            yCoordinates[3] = 31;
            graphics.setColor(new Color(this.medium.skyColor[0], this.medium.skyColor[1], this.medium.skyColor[2]));
            graphics.fillPolygon(xCoordinates, yCoordinates, 4);
        }
    }

    private Image getButtonPressedImage(final Image img) {
        final int[] pixels = Util.getPixelArray(img, imageObserver);
        final Color color = new Color(206, 214, 255);
        for (int i = 0; i < pixels.length; ++i) {
            if (pixels[i] != pixels[pixels.length - 1]) {
                pixels[i] = color.getRGB();
            }
        }
        return this.createImage(new MemoryImageSource(img.getWidth(imageObserver), img.getHeight(imageObserver), pixels, 0, img.getWidth(imageObserver)));
    }

    public void loading(final Graphics graphics, final Applet applet) {
        fillBlankScreen(graphics, Color.BLACK);
        graphics.drawImage(this.sign, 237, 10, this);
        graphics.drawImage(this.hello, 0, 80, this);
        graphics.setColor(new Color(198, 214, 255));
        graphics.fillRoundRect(125, 315, 300, 80, 30, 70);
        graphics.setColor(new Color(128, 167, 255));
        graphics.drawRoundRect(125, 315, 300, 80, 30, 70);
        graphics.drawImage(this.loadbar, 156, 340, this);
        graphics.setFont(new Font("SansSerif", Font.PLAIN, 11));
        this.fontMetrics = graphics.getFontMetrics();
        this.drawCharacters(graphics, 333, "Loading game, please wait.", 0, 0, 0, 3);
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(170, 373, 210, 17);
        graphics.setColor(new Color(32, 64, 128));
        applet.repaint();
    }

    public void mainMenu(final Graphics graphics, final Control control) {
        if (this.flipo == 0) {
            graphics.drawImage(this.bgmain, 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, null);
            graphics.drawImage(this.maini, 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, null);
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
            } else {
                graphics.setColor(new Color(0, 0, 255));
                this.aflk = true;
            }
        } else {
            graphics.setColor(new Color(0, 0, 0));
        }
        graphics.drawRoundRect(218, 246, 110, 22, 7, 20);
        if (this.opselect == 1) {
            if (this.shaded) {
                graphics.setColor(new Color(32, 48, 160));
                graphics.fillRect(174, 275, 196, 22);
                this.aflk = false;
            }
            if (this.aflk) {
                graphics.setColor(new Color(143, 159, 239));
                this.aflk = false;
            } else {
                graphics.setColor(new Color(0, 0, 255));
                this.aflk = true;
            }
        } else {
            graphics.setColor(new Color(0, 0, 0));
        }
        graphics.drawRoundRect(174, 275, 196, 22, 7, 20);
        if (this.opselect == 2) {
            if (this.shaded) {
                graphics.setColor(new Color(32, 48, 160));
                graphics.fillRect(230, 306, 85, 22);
                this.aflk = false;
            }
            if (this.aflk) {
                graphics.setColor(new Color(143, 159, 239));
                this.aflk = false;
            } else {
                graphics.setColor(new Color(0, 0, 255));
                this.aflk = true;
            }
        } else {
            graphics.setColor(new Color(0, 0, 0));
        }
        graphics.drawRoundRect(230, 306, 85, 22, 7, 20);
        graphics.drawImage(this.opti, 181, 250, null);
        if (control.enter || control.handb) {
            if (this.opselect == 0) {
                if (this.unlocked == 1 && this.lastState == GameState.GAMEPLAY) {
                    this.lastState = GameState.CAR_SELECT;
                    this.state = GameState.GAME_INSTRUCTIONS;
                } else {
                    this.state = GameState.TRANSITION_MAIN_MENU_CAR_SELECT; //-9
                }
            }
            if (this.opselect == 1) {
                this.lastState = GameState.MAIN_MENU;
                this.state = GameState.GAME_INSTRUCTIONS;
            }
            if (this.opselect == 2) {
                this.state = GameState.CREDITS;
            }
            this.flipo = 0;
            control.enter = false;
            control.handb = false;
        }
    }

    public void loadingMusicComplete(final int stageNumber, final Graphics graphics, final Control control) {
        int r = (int) (230.0f - 230.0f * (this.medium.snapColor[0] / (float) (100 * this.hipno[stageNumber - 1])));
        r = Util.clampCol(r);
        int g = (int) (230.0f - 230.0f * (this.medium.snapColor[1] / (float) (100 * this.hipno[stageNumber - 1])));
        g = Util.clampCol(g);
        int b = (int) (230.0f - 230.0f * (this.medium.snapColor[2] / (float) (100 * this.hipno[stageNumber - 1])));
        b = Util.clampCol(b);

        if (this.hipno[stageNumber - 1] == 0) {
            r = 255;
            g = 230;
            b = 201;
        }

        fillBlankScreen(graphics, new Color(r, g, b));
        int xPos = Config.SCREEN_WIDTH / 2 - this.loadingmusic.getWidth(null) / 2;
        int yPos = Config.SCREEN_HEIGHT / 2 - this.loadingmusic.getHeight(null) / 2;
        graphics.drawImage(this.loadingmusic, xPos, yPos, null);

        graphics.setFont(new Font("SansSerif", Font.BOLD, 11));
        this.fontMetrics = graphics.getFontMetrics();
        this.drawCharacters(graphics, Config.SCREEN_HEIGHT / 2 + 100, "Loading complete!  press start to begin...", 0, 0, 0, 3);
        graphics.drawImage(this.star[this.pstar], 234, 280, null);
        if (stageNumber == 10) {
            int red = aflk ? 200 : 0;
            aflk = !aflk;
            this.drawCharacters(graphics, Config.SCREEN_HEIGHT - 10, "> Note: Guidance Arrow is disabled in this stage!", red, 0, 0, 3);
        }
        if (stageNumber == this.unlocked) {
            this.drawCharacters(graphics, Config.SCREEN_HEIGHT / 2 - 10, getHintString(stageNumber), 100, 100, 100, 4);
        }

        if (this.pstar != 2) {
            this.pstar = this.pstar == 0 ? 1 : 0;
        }

        if (control.handb || control.enter) {
            this.state = GameState.GAMEPLAY;
            control.handb = false;
            control.enter = false;
        }
    }

    public void skid(final int n, final float n2) {
        if (this.bfcrash == 0 && this.bfskid == 0 && n2 > 150.0f) {
            if (n == 0) {
                if (!this.isSoundMuted) {
                    this.skidSoundEffects[this.skflg].play();
                }
                if (this.skflg == 0) {
                    this.skflg = 1;
                } else {
                    this.skflg = 0;
                }
            } else {
                if (!this.isSoundMuted) {
                    this.dustSkidSoundEffects[this.dskflg].play();
                }
                if (this.dskflg == 0) {
                    this.dskflg = 1;
                } else {
                    this.dskflg = 0;
                }
            }
            this.bfskid = 35;
        }
    }

    public float xs(final int n, int n2) {
        n2 = Math.max(n2, 50);
        return ((n2 - this.medium.focusPoint) * (this.medium.centerX - n) / n2 + n);
    }

    public void cantreply(final Graphics graphics) {
        graphics.setColor(new Color(64, 143, 223));
        graphics.fillRoundRect(75, 171, 400, 23, 7, 20);
        graphics.setColor(new Color(0, 89, 223));
        graphics.drawRoundRect(75, 171, 400, 23, 7, 20);
        this.drawCharacters(graphics, 187, "Sorry not enough replay data to play available, please try again later.", 255, 255, 255, 1);
    }

    public void stopallnow() {
        int n = 0;
        do {
            if (this.loadedSoundTracks[n]) {
                this.soundTracks[n].outwithit();
            }
        } while (++n < 11);
        int n2 = 0;
        do {
            this.engineSounds[0][n2].stop();
            this.engineSounds[1][n2].stop();
        } while (++n2 < 5);
        int n3 = 0;
        do {
            this.airSoundEffects[n3].stop();
        } while (++n3 < 6);
        this.wastd.stop();
        this.cars.outwithit();
        this.stages.outwithit();
    }

    public void carselect(final Control control, final Geometry[] geometries, final Madness madness, final Graphics graphics) {
        this.cars.play();
        graphics.drawImage(this.carsbg, 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, null);
        graphics.drawImage(this.selectcar, (Config.SCREEN_WIDTH - this.selectcar.getWidth(null)) / 2, 100, null);
        this.medium.crs = true;
        this.medium.positionX = -Config.SCREEN_WIDTH / 2;
        this.medium.positionY = -Config.SCREEN_HEIGHT + (Config.SCREEN_HEIGHT / 6);
        this.medium.positionZ = -50;
        this.medium.xz = 0;
        this.medium.zy = 10;
        this.medium.ground = 720;
        Geometry geometry = geometries[this.selectedCar[0]];
        geometry.draw(graphics);
        if (this.flipo == 0) {
            graphics.setFont(new Font("SansSerif", Font.BOLD, 26));
            this.fontMetrics = graphics.getFontMetrics();
            if (this.aflk) {
                this.drawCharacters(graphics, 250, this.carNames[this.selectedCar[0]], 130, 130, 255, 3);
                this.aflk = false;
            } else {
                this.drawCharacters(graphics, 250, this.carNames[this.selectedCar[0]], 130, 215, 255, 3);
                this.aflk = true;
            }
            geometry.z = 950;
            geometry.y = -34 - geometry.grat;
            geometry.x = 0;
            geometry.xz += 5;
            geometry.zy = 0;
            geometry.wzy -= 10;
            if (geometry.wzy < -45) {
                geometry.wzy += 45;
            }
            if (this.selectedCar[0] != 0) {
                graphics.drawImage(this.back[this.pback], 23, 270, null);
            }
            if (this.selectedCar[0] != 9) {
                graphics.drawImage(this.next[this.pnext], 467, 270, null);
            }
            if ((this.selectedCar[0] - 4) * 2 >= this.unlocked) {
                graphics.drawImage(this.pgate, 96, 145 - this.gatey, null);
                if (this.gatey != 0) {
                    this.gatey -= 100;
                }
                this.drawCharacters(graphics, 320, "[ Car Locked ]", 224, 63, 63, 3);
                this.drawCharacters(graphics, 345, "This car unlocks when stage " + (this.selectedCar[0] - 4) * 2 + " is completed...", 160, 176, 255, 3);
            } else {
                graphics.setFont(new Font("SansSerif", Font.BOLD, 11));
                this.fontMetrics = graphics.getFontMetrics();
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
                final float n = (madness.swits[this.selectedCar[0]][2] - 220) / 90.0f;
                graphics.fillRect((int) (97.0f + 156.0f * n), 307, (int) (156.0f * (1.0f - n) + 1.0f), 7);
                final float n2 = madness.acelf[this.selectedCar[0]][1] * madness.acelf[this.selectedCar[0]][0] * madness.acelf[this.selectedCar[0]][2] * madness.grip[this.selectedCar[0]] / 7700.0f;
                graphics.fillRect((int) (97.0f + 156.0f * n2), 322, (int) (156.0f * (1.0f - n2) + 1.0f), 7);
                final float n3 = this.dishandle[this.selectedCar[0]];
                graphics.fillRect((int) (97.0f + 156.0f * n3), 337, (int) (156.0f * (1.0f - n3) + 1.0f), 7);
                float n4 = (madness.airc[this.selectedCar[0]] * madness.airs[this.selectedCar[0]] + 25.0f) / 125.0f;
                if (n4 > 1.0f) {
                    n4 = 1.0f;
                }
                graphics.fillRect((int) (371.0f + 156.0f * n4), 307, (int) (156.0f * (1.0f - n4) + 1.0f), 7);
                float n5 = madness.outdam[this.selectedCar[0]] * madness.moment[this.selectedCar[0]] / 150.0f;
                if (n5 > 1.0f) {
                    n5 = 1.0f;
                }
                graphics.fillRect((int) (371.0f + 156.0f * n5), 322, (int) (156.0f * (1.0f - n5) + 1.0f), 7);
                final float n6 = madness.powerloss[this.selectedCar[0]] / 5500000.0f;
                graphics.fillRect((int) (371.0f + 156.0f * n6), 337, (int) (156.0f * (1.0f - n6) + 1.0f), 7);
                graphics.drawImage(this.contin2[this.pcontin], 230, 357, null);
            }
        } else {
            this.pback = 0;
            this.pnext = 0;
            this.gatey = 300;
            if (this.flipo > 10) {
                geometry.y -= 100;
                geometry.zy += this.nextc ? 20 : -20;
            } else {
                if (this.flipo == 10) {
                    final int[] sc = this.selectedCar;
                    final int n7 = 0;
                    if (this.nextc) {
                        ++sc[n7];
                    } else {
                        --sc[n7];
                    }
                    geometry.z = 950;
                    geometry.y = -34 - geometry.grat - 1100;
                    geometry.x = 0;
                    geometry.zy = 0;
                }
                geometry.y += 100;
            }
            --this.flipo;
        }
        graphics.setFont(new Font("SansSerif", Font.BOLD, 11));
        this.fontMetrics = graphics.getFontMetrics();
        this.drawCharacters(graphics, 396, "Use keyboard Arrows and press Enter to continue", 112, 151, 208, 3);
        if (control.right) {
            control.right = false;
            if (this.selectedCar[0] != 9 && this.flipo == 0) {
                this.nextc = true;
                this.flipo = 20;
            }
        }
        if (control.left) {
            control.left = false;
            if (this.selectedCar[0] != 0 && this.flipo == 0) {
                this.nextc = false;
                this.flipo = 20;
            }
        }
        if (control.handb || control.enter) {
            if (this.flipo == 0 && (this.selectedCar[0] - 4) * 2 < this.unlocked) {
                this.medium.crs = false;
                this.state = GameState.LOADING_STAGE;
            }
            control.handb = false;
            control.enter = false;
        }
    }

    public void checkMouseClick(final int mouseX, final int mouseY, final int mouses, final Control control) {
        if (this.state == GameState.STAGE_PREVIEW) {
            if (mouses == 1) {
                if (this.over(this.next[0], mouseX, mouseY, 440, 110)) {
                    this.pnext = 1;
                }
                if (this.over(this.back[0], mouseX, mouseY, 50, 110)) {
                    this.pback = 1;
                }
                if (this.over(this.contin1[0], mouseX, mouseY, 232, 170)) {
                    this.pcontin = 1;
                }
            }
            if (mouses == 2) {
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
        if (this.state == GameState.LOADING_STAGE_FAILED) {
            if (mouses == 1 && this.over(this.contin1[0], mouseX, mouseY, 232, 270)) {
                this.pcontin = 1;
            }
            if (mouses == 2 && this.pcontin == 1) {
                control.enter = true;
                this.pcontin = 0;
            }
        }
        if (this.state == GameState.STAGE_LOCKED_UNSAFE) {
            if (mouses == 1 && this.over(this.back[0], mouseX, mouseY, 245, 320)) {
                this.pback = 1;
            }
            if (mouses == 2 && this.pback == 1) {
                control.enter = true;
                this.pback = 0;
            }
        }
        if (this.state == GameState.LOADING_SOUNDTRACK_COMPLETE) {
            if (mouses == 1 && this.over(this.star[0], mouseX, mouseY, 234, 280)) {
                this.pstar = 2;
            }
            if (mouses == 2 && this.pstar == 2) {
                control.enter = true;
                this.pstar = 1;
            }
        }
        if (this.state == GameState.CAR_SELECT) {
            if (mouses == 1) {
                if (this.over(this.next[0], mouseX, mouseY, 467, 276)) {
                    this.pnext = 2;
                }
                if (this.over(this.back[0], mouseX, mouseY, 23, 276)) {
                    this.pback = 2;
                }
                if (this.over(this.contin2[0], mouseX, mouseY, 232, 360)) {
                    this.pcontin = 1;
                }
            }
            if (mouses == 2) {
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
        if (this.state == GameState.END_RACE_CONGRATS) {
            if (mouses == 1 && this.over(this.contin2[0], mouseX, mouseY, 230, 350 - this.pin)) {
                this.pcontin = 1;
            }
            if (mouses == 2 && this.pcontin == 1) {
                control.enter = true;
                this.pcontin = 0;
            }
        }
        if (this.state == GameState.GAME_PAUSED_2_BLURRED) {
            if (mouses == 1) {
                if (this.overon(204, 143, 137, 22, mouseX, mouseY)) {
                    this.opselect = 0;
                    this.shaded = true;
                }
                if (this.overon(195, 171, 155, 22, mouseX, mouseY)) {
                    this.opselect = 1;
                    this.shaded = true;
                }
                if (this.overon(178, 197, 190, 22, mouseX, mouseY)) {
                    this.opselect = 2;
                    this.shaded = true;
                }
                if (this.overon(216, 223, 109, 22, mouseX, mouseY)) {
                    this.opselect = 3;
                    this.shaded = true;
                }
            }
            if (mouses == 2 && this.shaded) {
                control.enter = true;
                this.shaded = false;
            }
        }
        if (this.state == GameState.MAIN_MENU) {
            if (mouses == 1) {
                if (this.overon(218, 246, 110, 22, mouseX, mouseY)) {
                    this.opselect = 0;
                    this.shaded = true;
                }
                if (this.overon(174, 275, 196, 22, mouseX, mouseY)) {
                    this.opselect = 1;
                    this.shaded = true;
                }
                if (this.overon(230, 306, 85, 22, mouseX, mouseY)) {
                    this.opselect = 2;
                    this.shaded = true;
                }
            }
            if (mouses == 2 && this.shaded) {
                control.enter = true;
                this.shaded = false;
            }
        }
        if (this.state == GameState.GAME_INSTRUCTIONS) {
            if (this.flipo == 1 || this.flipo == 3) {
                if (mouses == 1 && this.over(this.next[0], mouseX, mouseY, 460, 370)) {
                    this.pnext = 1;
                }
                if (mouses == 2 && this.pnext == 1) {
                    control.enter = true;
                    this.pnext = 0;
                }
            }
            if (this.flipo == 5) {
                if (mouses == 1 && this.over(this.contin2[0], mouseX, mouseY, 230, 370)) {
                    this.pcontin = 1;
                }
                if (mouses == 2 && this.pcontin == 1) {
                    control.enter = true;
                    this.pcontin = 0;
                }
            }
        }
        if (this.state == GameState.CREDITS) {
            if (mouses == 1 && this.over(this.next[0], mouseX, mouseY, 460, 370)) {
                this.pnext = 1;
            }
            if (mouses == 2 && this.pnext == 1) {
                control.enter = true;
                this.pnext = 0;
            }
        }
    }

    public void stopAirSounds() {
        int n = 0;
        do {
            this.airSoundEffects[n].stop();
        } while (++n < 6);
    }

    private Image loadOpSnap(final Image img, final int n) {
        final int[] pixels = getPixelArray(img, imageObserver);
        for (int i = 0; i < pixels.length; ++i) {
            if (pixels[i] != pixels[76]) {
                final Color color = new Color(pixels[i]);
                int r, g, b;
                if (this.hipno[n - 1] != 0) {
                    r = (int) (color.getRed() - color.getRed() * (this.medium.snapColor[0] / (float) (50 * this.hipno[n - 1])));
                    g = (int) (color.getGreen() - color.getGreen() * (this.medium.snapColor[1] / (float) (50 * this.hipno[n - 1])));
                    b = (int) (color.getBlue() - color.getBlue() * (this.medium.snapColor[2] / (float) (50 * this.hipno[n - 1])));
                } else {
                    r = (int) (color.getRed() + color.getRed() * 0.25f);
                    g = color.getGreen();
                    b = (int) (color.getBlue() - color.getBlue() * 0.25f);
                }
                r = Util.clampCol(r);
                g = Util.clampCol(g);
                b = Util.clampCol(b);
                pixels[i] = new Color(r, g, b).getRGB();
            }
        }
        return this.createImage(new MemoryImageSource(img.getWidth(imageObserver), img.getHeight(imageObserver), pixels, 0, img.getWidth(imageObserver)));
    }

    public void loadingFailed(final int i, final Control control, final Graphics graphics) {
        graphics.drawImage(this.trackbg, 0, 0, null);
        graphics.drawImage(this.selectStage, 201, 45, null);
        graphics.setFont(new Font("SansSerif", Font.BOLD, 13));
        this.fontMetrics = graphics.getFontMetrics();
        this.drawCharacters(graphics, 140, "Error Loading Stage " + i, 200, 0, 70, 3);
        this.drawCharacters(graphics, 170, "Your internet connection may have been lost...", 0, 0, 0, 3);
        this.drawCharacters(graphics, 220, "Press Enter to try again.", 0, 0, 0, 3);
        graphics.drawImage(this.contin1[this.pcontin], 232, 270, null);
        graphics.drawImage(this.barLeft, 0, 0, null);
        graphics.drawImage(this.barTop, 0, 0, null);
        graphics.drawImage(this.barRight, 509, 0, null);
        graphics.drawImage(this.barBottom, 0, 357, null);
        if (control.handb || control.enter) {
            this.state = GameState.LOADING_STAGE;
            control.handb = false;
            control.enter = false;
        }
    }

}
