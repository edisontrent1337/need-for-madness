package main.java;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//
// Decompiled by Procyon v0.5.36
//

public class GameSparker extends Applet implements Runnable, MouseListener, KeyListener {
    Graphics graphics;
    Image offImage;
    Thread gamer;
    Control[] controls;
    int mouses;
    int mouseX;
    int mouseY;
    boolean lostfcs;
    boolean exwist;
    int nob;
    int notb;
    int view;

    GraphicsPanel graphicsPanel;
    Trackers trackers;
    CheckPoints checkPoints;
    Medium medium;
    Record record;
    Geometry[] geometries;
    Madness[] cars;

    public GameSparker() {
        this.controls = new Control[5];
        this.mouses = 0;
        this.mouseX = 0;
        this.mouseY = 0;
        this.lostfcs = false;
        this.exwist = true;
        this.nob = 0;
        this.notb = 0;
        this.view = 0;
        addMouseListener(this);
        addKeyListener(this);
    }

    public void stop() {
        if (this.exwist && this.gamer != null) {
            this.gamer.stop();
            this.gamer = null;
        }
        this.exwist = true;
    }

    public boolean lostFocus(final Event event, final Object o) {
        if (!this.exwist) {
            this.lostfcs = true;
            this.mouses = 0;
            this.controls[0].falseo();
        }
        return false;
    }

    public int getInt(final String s, final String s2, final int n) {
        int n2 = 0;
        StringBuilder string = new StringBuilder();
        for (int i = s.length() + 1; i < s2.length(); ++i) {
            final String string2 = "" + s2.charAt(i);
            if (string2.equals(",") || string2.equals(")")) {
                ++n2;
                ++i;
            }
            if (n2 == n) {
                string.append(s2.charAt(i));
            }
        }
        return Integer.parseInt(string.toString());
    }

    /*public int readcookie(final String str) {
        int intValue = -1;
        try {
            final JSObject window = JSObject.getWindow(this);
            window.eval("drawShadow=GetCookie('" + str + "');");
            intValue = Integer.valueOf(String.valueOf(String.valueOf(window.getMember("drawShadow"))));
        }
        catch (Exception ex) {}
        return intValue;
    }*/

    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offImage, 0, 0, this);
    }

    public void loadBase(final Geometry[] array, final Medium medium, final Trackers trackers) {
        final String[] array2 = {"2000tornados", "formula7", "canyenaro", "lescrab", "nimi", "maxrevenge", "leadoxide", "king", "radicalone", "drmonster", "road", "froad", "twister2", "twister1", "turn", "offroad", "bumproad", "offturn", "nroad", "nturn", "roblend", "noblend", "rnblend", "roadend", "offroadend", "hpground", "ramp30", "cramp35", "dramp15", "dhilo15", "slide10", "takeoff", "sramp22", "offbump", "offramp", "sofframp", "halfpipe", "spikes", "rail", "thewall", "checkpoint", "fixpoint", "offcheckpoint"};
        try {
            //final DataInputStream in = new DataInputStream(new URL(this.getCodeBase(), "graphics/models.radq").openStream());
            //final ZipInputStream zipInputStream = new ZipInputStream(in);

            URL resource = this.getClass().getResource("../../resources/graphics/models.zipo");
            File file = new File(resource.toURI());
            final FileInputStream in = new FileInputStream(file);
            final ZipInputStream zipInputStream = new ZipInputStream(in);

            for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
                int n = 0;
                int n2 = 0;
                do {
                    if (zipEntry.getName().startsWith(array2[n2])) {
                        n = n2;
                    }
                } while (++n2 < 43);
                int i = (int) zipEntry.getSize();
                final byte[] b = new byte[i];
                int off = 0;
                while (i > 0) {
                    final int read = zipInputStream.read(b, off, i);
                    off += read;
                    i -= read;
                }
                array[n] = new Geometry(b, medium, trackers);
            }
            in.close();
            zipInputStream.close();
        } catch (Exception obj) {
            System.out.println("Error Reading Models: " + obj);
        }
    }

    public void update(final Graphics graphics) {
        this.paint(graphics);
    }

    public int sunytyp() {
        final String property = System.getProperty("java.version");
        if (!("" + this.getAppletContext()).startsWith("sun.")) {
            return 0;
        }
        if (property.startsWith("1.3")) {
            return 1;
        }
        return 2;
    }

    public void start() {
        if (this.gamer == null) {
            this.gamer = new Thread(this);
        }
        this.gamer.start();
    }

    public void loadStage(final Geometry[] stageGeometry, final Geometry[] availableGeometry, final Medium medium, final Trackers trackers, final CheckPoints checkPoints, final GraphicsPanel GraphicsPanel, final Madness[] array3, final Record record) {
        trackers.nt = 0;
        this.nob = 5;
        this.notb = 0;
        checkPoints.n = 0;
        checkPoints.nsp = 0;
        checkPoints.fn = 0;
        checkPoints.haltall = false;
        checkPoints.wasted = 0;
        medium.ground = 250;
        this.view = 0;
        String string = "";
        try {
            URL resource = this.getClass().getResource("../../resources/stages/" + checkPoints.stage + ".txt");
            File file = new File(resource.toURI());
            final DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));

            String line;
            while ((line = dataInputStream.readLine()) != null) {
                string = line.trim();
                final List<Integer> integerList = Util.getInts(string);
                if (string.startsWith("snap")) {
                    medium.setSnapColor(integerList);
                }
                if (string.startsWith("sky")) {
                    medium.setSky(integerList);
                    GraphicsPanel.snap(checkPoints.stage);
                }
                if (string.startsWith("ground")) {
                    medium.setGround(integerList);
                }
                if (string.startsWith("fog")) {
                    medium.setFade(integerList);
                }
                if (string.startsWith("fadefrom")) {
                    medium.fadeFrom(integerList);
                    medium.origfade = medium.fade[0];
                }
                if (string.startsWith("set")) {
                    final int geometryIndex = integerList.get(0);
                    stageGeometry[this.nob] = new Geometry(availableGeometry[geometryIndex], integerList.get(1), (int) medium.ground - availableGeometry[geometryIndex].grat, integerList.get(2), integerList.get(3));
                    if (string.contains(")p")) {
                        checkPoints.x[checkPoints.n] = integerList.get(1);
                        checkPoints.z[checkPoints.n] = integerList.get(2);
                        checkPoints.y[checkPoints.n] = 0;
                        checkPoints.typ[checkPoints.n] = 0;

                        //TODO: Checkpoint Types for Bots? Maybe smth better mayking anna
                        if (string.contains(")pt")) {
                            checkPoints.typ[checkPoints.n] = -1;
                        }
                        if (string.contains(")pr")) {
                            checkPoints.typ[checkPoints.n] = -2;
                        }
                        if (string.contains(")po")) {
                            checkPoints.typ[checkPoints.n] = -3;
                        }
                        if (string.contains(")ph")) {
                            checkPoints.typ[checkPoints.n] = -4;
                        }
                        ++checkPoints.n;
                        this.notb = this.nob + 1;
                    }
                    ++this.nob;
                }
                if (string.startsWith("chk")) {
                    final int checkpointIndex = integerList.get(0);
                    stageGeometry[this.nob] = new Geometry(availableGeometry[checkpointIndex], integerList.get(1), (int) medium.ground - availableGeometry[checkpointIndex].grat, integerList.get(2), integerList.get(3));
                    checkPoints.x[checkPoints.n] = integerList.get(1);
                    checkPoints.z[checkPoints.n] = integerList.get(2);
                    checkPoints.y[checkPoints.n] = (int) medium.ground - availableGeometry[checkpointIndex].grat;

                    checkPoints.typ[checkPoints.n] = integerList.get(3) == 0 ? 1 : 2;
                    checkPoints.pcs = checkPoints.n;

                    checkPoints.n++;
                    checkPoints.nsp++;
                    this.nob++;
                    this.notb = this.nob;
                }
                if (string.startsWith("fix")) {
					stageGeometry[this.nob] = new Geometry(availableGeometry[integerList.get(0)], integerList.get(1), integerList.get(3), integerList.get(2), integerList.get(4));
                    checkPoints.fx[checkPoints.fn] = integerList.get(1);
                    checkPoints.fz[checkPoints.fn] = integerList.get(2);
                    checkPoints.fy[checkPoints.fn] = integerList.get(3);

                    stageGeometry[this.nob].elec = true;
					checkPoints.rotated[checkPoints.fn] = integerList.get(4) != 0;
					stageGeometry[this.nob].roted = checkPoints.rotated[checkPoints.fn];

                    checkPoints.special[checkPoints.fn] = string.contains(")drawShadow");
                    ++checkPoints.fn;
                    ++this.nob;
                    this.notb = this.nob;
                }
                if (string.startsWith("nlaps")) {
                    checkPoints.nlaps = integerList.get(0);
                }
                if (string.startsWith("name")) {
                    checkPoints.name = Util.getName(string).replace('|', ',');
                }
                if(string.startsWith("max")) {
					final int angle, sign, xy, zy;
					angle = (string.startsWith("maxr") || string.startsWith("maxl")) ? 0 : 90;
					sign = (string.startsWith("maxr") || string.startsWith("maxt")) ? 1 : -1;

					for (int i = 0; i < integerList.get(0); ++i) {
                        int xPos = (string.startsWith("maxr") || string.startsWith("maxl")) ? integerList.get(1) : integerList.get(2) + i * 4800;
                        int zPos = (string.startsWith("maxr") || string.startsWith("maxl")) ? integerList.get(2) + i * 4800 : integerList.get(1);
                        stageGeometry[this.nob] = new Geometry(availableGeometry[39],  xPos, (int) medium.ground - availableGeometry[39].grat, zPos, angle);
						++this.nob;
					}

                    trackers.y[trackers.nt] = -5000;
                    trackers.rady[trackers.nt] = 7100;
                    trackers.dam[trackers.nt] = 1;

                    if (string.startsWith("maxr") || string.contains("maxl")) {
                        trackers.x[trackers.nt] = integerList.get(1) + (sign *  500);
                        trackers.z[trackers.nt] = integerList.get(0) * 4800 / 2 + integerList.get(2) - 2400;
                        trackers.radx[trackers.nt] = 600;
                        trackers.radz[trackers.nt] = integerList.get(0) * 4800 / 2;
                        trackers.xy[trackers.nt] = sign * 90;
                        trackers.zy[trackers.nt] = 0;
                    } else {
                        trackers.x[trackers.nt] = integerList.get(0) * 4800 / 2 + integerList.get(2) - 2400;
                        trackers.z[trackers.nt] = integerList.get(1) + (sign * 500);
                        trackers.radx[trackers.nt] = integerList.get(0) * 4800 / 2;
                        trackers.radz[trackers.nt] = 600;
                        trackers.xy[trackers.nt] = 0;
                        trackers.zy[trackers.nt] = sign * 90;
                    }

                    trackers.y[trackers.nt] = -5000;
                    trackers.rady[trackers.nt] = 7100;
                    trackers.dam[trackers.nt] = 1;
                    ++trackers.nt;
                }
            }
            dataInputStream.close();
        } catch (Exception e) {
            GraphicsPanel.stateInt = 3;
            System.out.println("Error in stage " + checkPoints.stage);
            System.out.println("" + e);
            e.printStackTrace();
            System.out.println("At line: " + string);
        }
        if (GraphicsPanel.stateInt == 2) {
            medium.trx = 0L;
            medium.trz = 0L;
            if (trackers.nt >= 4) {
                int n = 4;
                do {
                    medium.trx += trackers.x[trackers.nt - n];
                    medium.trz += trackers.z[trackers.nt - n];
                } while (--n > 0);
            }
            medium.trx /= 4L;
            medium.trz /= 4L;
            medium.ptr = 0;
            medium.ptcnt = -10;
            medium.hit = 60000;
            medium.nrnd = 0;
            medium.trk = true;
            GraphicsPanel.stateInt = 1;
            this.mouses = 0;
        }
        int n2 = 0;
        do {
            this.controls[n2].reset(checkPoints);
        } while (++n2 < 5);
        GraphicsPanel.resetstat(checkPoints.stage);
        int n3 = 0;
        do {
            stageGeometry[n3] = new Geometry(availableGeometry[GraphicsPanel.sc[n3]], GraphicsPanel.xstart[n3], 250 - availableGeometry[GraphicsPanel.sc[n3]].grat, GraphicsPanel.zstart[n3], 0);
            array3[n3].reseto(GraphicsPanel.sc[n3], stageGeometry[n3], checkPoints);
        } while (++n3 < 5);
        record.reset(stageGeometry);
    }

    public void run() {
        this.graphics.setColor(new Color(0, 0, 0));
        this.graphics.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.repaint();
        int n = 5;
        final int sunytyp = this.sunytyp();
        if (sunytyp != 0) {
            n = 15;
        }

        Geometry[] availableGeometries = new Geometry[43];
        this.loadBase(availableGeometries, medium, trackers);
        graphicsPanel.dnload += 29;
        graphicsPanel.loading(this.graphics, this);
        int n2 = 0;
        do {
            cars[n2] = new Madness(medium, record, graphicsPanel, n2);
            this.controls[n2] = new Control(medium);
        } while (++n2 < 5);

        float n3 = 35.0f;
        int n4 = 80;

        graphicsPanel.unlocked = 11;

        int n5 = 0;
        long time = new Date().getTime();
        float a = 30.0f;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        this.exwist = false;
        while (true) {
            final long time2 = new Date().getTime();
            if (graphicsPanel.stateInt == 111) {
                if (this.mouses == 1) {
                    n9 = 800;
                }
                if (n9 < 800) {
                    graphicsPanel.clicknow(this.graphics);
                    ++n9;
                } else {
                    n9 = 0;
                    graphicsPanel.stateInt = 9;
                    this.mouses = 0;
                    this.lostfcs = false;
                }
            }
            if (graphicsPanel.stateInt == 9) {
                if (n9 < 150) {
                    graphicsPanel.rad(this.graphics, n9);
                    ++n9;
                } else {
                    n9 = 0;
                    graphicsPanel.stateInt = 10;
                    this.mouses = 0;
                    this.controls[0].falseo();
                }
            }
            if (graphicsPanel.stateInt == -9) {
                if (n9 < 5) {
                    this.graphics.setColor(new Color(255, 255, 255));
                    this.graphics.fillRect(0, 0, 550, 400);
                    ++n9;
                } else {
                    n9 = 0;
                    graphicsPanel.stateInt = 7;
                    this.mouses = 0;
                }
            }
            if (graphicsPanel.stateInt == 8) {
                graphicsPanel.credits(this.graphics, this.controls[0]);
                graphicsPanel.ctachm(this.mouseX, this.mouseY, this.mouses, this.controls[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            if (graphicsPanel.stateInt == 10) {
                graphicsPanel.maini(this.graphics, this.controls[0]);
                graphicsPanel.ctachm(this.mouseX, this.mouseY, this.mouses, this.controls[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            if (graphicsPanel.stateInt == 11) {
                graphicsPanel.inst(this.graphics, this.controls[0]);
                graphicsPanel.ctachm(this.mouseX, this.mouseY, this.mouses, this.controls[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            if (graphicsPanel.stateInt == -5) {
                graphicsPanel.finish(checkPoints, availableGeometries, this.controls[0], this.graphics);
                if (n5 == 1) {
                    if (checkPoints.stage == graphicsPanel.unlocked && graphicsPanel.winner && graphicsPanel.unlocked != 11) {
                        this.saveCookie("unlocked", "" + (graphicsPanel.unlocked + 1));
                    }
                    this.saveCookie("gameprfact", "" + (int) n3);
                    this.saveCookie("usercar", "" + graphicsPanel.sc[0]);
                    n5 = 0;
                }
                graphicsPanel.ctachm(this.mouseX, this.mouseY, this.mouses, this.controls[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            if (graphicsPanel.stateInt == 7) {
                graphicsPanel.carselect(this.controls[0], availableGeometries, cars[0], this.graphics);
                graphicsPanel.ctachm(this.mouseX, this.mouseY, this.mouses, this.controls[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            if (graphicsPanel.stateInt == 6) {
                graphicsPanel.musicomp(checkPoints.stage, this.graphics, this.controls[0]);
                graphicsPanel.ctachm(this.mouseX, this.mouseY, this.mouses, this.controls[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            if (graphicsPanel.stateInt == 5) {
                graphicsPanel.loadmusic(checkPoints.stage, n4, this.graphics);
                if (n5 == 0) {
                    this.saveCookie("usercar", "" + graphicsPanel.sc[0]);
                    n5 = 1;
                }
            }
            if (graphicsPanel.stateInt == 4) {
                graphicsPanel.cantgo(this.graphics, this.controls[0]);
                graphicsPanel.ctachm(this.mouseX, this.mouseY, this.mouses, this.controls[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            if (graphicsPanel.stateInt == 3) {
                graphicsPanel.loadingFailed(checkPoints.stage, this.controls[0], this.graphics);
                graphicsPanel.ctachm(this.mouseX, this.mouseY, this.mouses, this.controls[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            if (graphicsPanel.stateInt == 2) {
                graphicsPanel.loadingStage(checkPoints.stage, this.graphics);
                this.loadStage(this.geometries, availableGeometries, medium, trackers, checkPoints, graphicsPanel, cars, record);
                this.controls[0].falseo();
            }
            if (graphicsPanel.stateInt == 1) {
                graphicsPanel.trackbg(this.graphics);
                medium.circleAroundStage(checkPoints);
                int n11 = 0;
                final int[] array4 = new int[200];
                for (int i = 5; i < this.notb; ++i) {
                    if (this.geometries[i].dist != 0) {
                        array4[n11] = i;
                        ++n11;
                    } else {
                        this.geometries[i].draw(this.graphics);
                    }
                }
                final int[] array5 = new int[n11];
                for (int j = 0; j < n11; ++j) {
                    array5[j] = 0;
                }
                for (int k = 0; k < n11; ++k) {
                    for (int l = k + 1; l < n11; ++l) {
                        if (this.geometries[array4[k]].dist != this.geometries[array4[l]].dist) {
                            if (this.geometries[array4[k]].dist < this.geometries[array4[l]].dist) {
                                ++array5[k];
                            } else {
                                ++array5[l];
                            }
                        } else if (l > k) {
                            ++array5[k];
                        } else {
                            ++array5[l];
                        }
                    }
                }
                for (int n16 = 0; n16 < n11; ++n16) {
                    for (int n17 = 0; n17 < n11; ++n17) {
                        if (array5[n17] == n16) {
                            this.geometries[array4[n17]].draw(this.graphics);
                        }
                    }
                }
                graphicsPanel.ctachm(this.mouseX, this.mouseY, this.mouses, this.controls[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
                graphicsPanel.stageselect(this.graphics, checkPoints, this.controls[0]);
            }
            if (graphicsPanel.stateInt == 176) {
                medium.draw(this.graphics);
                int n18 = 0;
                final int[] array10 = new int[100];
                for (int n19 = 0; n19 < this.nob; ++n19) {
                    if (this.geometries[n19].dist != 0) {
                        array10[n18] = n19;
                        ++n18;
                    } else {
                        this.geometries[n19].draw(this.graphics);
                    }
                }
                final int[] array11 = new int[n18];
                for (int n20 = 0; n20 < n18; ++n20) {
                    array11[n20] = 0;
                }
                for (int n21 = 0; n21 < n18; ++n21) {
                    for (int n22 = n21 + 1; n22 < n18; ++n22) {
                        if (this.geometries[array10[n21]].dist != this.geometries[array10[n22]].dist) {
                            if (this.geometries[array10[n21]].dist < this.geometries[array10[n22]].dist) {
                                ++array11[n21];
                            } else {
                                ++array11[n22];
                            }
                        } else if (n22 > n21) {
                            ++array11[n21];
                        } else {
                            ++array11[n22];
                        }
                    }
                }
                for (int n27 = 0; n27 < n18; ++n27) {
                    for (int n28 = 0; n28 < n18; ++n28) {
                        if (array11[n28] == n27) {
                            this.geometries[array10[n28]].draw(this.graphics);
                        }
                    }
                }
                medium.follow(this.geometries[0], 0);
                graphicsPanel.framer(checkPoints.stage, this.graphics);
                if (n4 != 0) {
                    --n4;
                } else {
                    this.controls[0].enter = false;
                    this.controls[0].handb = false;
                    if (graphicsPanel.loadedSoundTracks[checkPoints.stage - 1]) {
                        graphicsPanel.soundTracks[checkPoints.stage - 1].play();
                    }
                    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    graphicsPanel.stateInt = 6;
                }
            }
            if (graphicsPanel.stateInt == 0) {
                for (int i = 0; i < 5; i++) {
                    if (cars[i].newcar) {
                        final int xz = this.geometries[i].xz;
                        final int xy = this.geometries[i].xy;
                        final int zy = this.geometries[i].zy;
                        this.geometries[i] = new Geometry(availableGeometries[cars[i].carIndex], this.geometries[i].x, this.geometries[i].y, this.geometries[i].z, 0);
                        this.geometries[i].xz = xz;
                        this.geometries[i].xy = xy;
                        this.geometries[i].zy = zy;
                        cars[i].newcar = false;
                    }
                }
                medium.draw(this.graphics);
                int n30 = 0;
                final int[] array16 = new int[100];
                for (int n31 = 0; n31 < this.nob; ++n31) {
                    if (this.geometries[n31].dist != 0) {
                        array16[n30] = n31;
                        ++n30;
                    } else {
                        this.geometries[n31].draw(this.graphics);
                    }
                }
                final int[] array17 = new int[n30];
                for (int n32 = 0; n32 < n30; ++n32) {
                    array17[n32] = 0;
                }
                for (int n33 = 0; n33 < n30; ++n33) {
                    for (int n34 = n33 + 1; n34 < n30; ++n34) {
                        if (this.geometries[array16[n33]].dist != this.geometries[array16[n34]].dist) {
                            if (this.geometries[array16[n33]].dist < this.geometries[array16[n34]].dist) {
                                ++array17[n33];
                            } else {
                                ++array17[n34];
                            }
                        } else if (n34 > n33) {
                            ++array17[n33];
                        } else {
                            ++array17[n34];
                        }
                    }
                }
                for (int n39 = 0; n39 < n30; ++n39) {
                    for (int n40 = 0; n40 < n30; ++n40) {
                        if (array17[n40] == n39) {
                            this.geometries[array16[n40]].draw(this.graphics);
                        }
                    }
                }
                if (graphicsPanel.starcnt == 0) {
                    int i = 0;
                    do {
                        int j = 0;
                        do if (j != i) {
                            cars[i].colide(this.geometries[i], cars[j], this.geometries[j]);
                        } while (++j < 5);
                    } while (++i < 5);
                    int n43 = 0;
                    do {
                        cars[n43].drive(this.controls[n43], this.geometries[n43], trackers, checkPoints);
                    } while (++n43 < 5);
                    int n44 = 0;
                    do {
                        record.rec(this.geometries[n44], n44, cars[n44].squash, cars[n44].lastcolido, cars[n44].cntdest);
                    } while (++n44 < 5);
                    checkPoints.checkstat(cars, this.geometries);
                    int n45 = 1;
                    do {
                        this.controls[n45].preform(cars[n45], this.geometries[n45], checkPoints, trackers);
                    } while (++n45 < 5);
                } else {
                    if (graphicsPanel.starcnt == 90) {
                        medium.adv = 1900;
                        medium.zy = 40;
                        medium.circleAmount = 70;
                        this.graphics.setColor(new Color(255, 255, 255));
                        this.graphics.fillRect(0, 0, 550, 400);
                    }
                    if (graphicsPanel.starcnt != 0) {
                        --graphicsPanel.starcnt;
                    }
                }
                if (graphicsPanel.starcnt < 35) {
                    if (this.view == 0) {
                        medium.follow(this.geometries[0], cars[0].cxz);
                        graphicsPanel.stat(cars[0], checkPoints, this.controls[0], true, this.graphics);
                    }
                    if (this.view == 1) {
                        medium.circleAroundCar(this.geometries[0], false);
                        graphicsPanel.stat(cars[0], checkPoints, this.controls[0], false, this.graphics);
                    }
                    if (this.view == 2) {
                        medium.watchFromStationaryPoint(this.geometries[0], cars[0].mxz);
                        graphicsPanel.stat(cars[0], checkPoints, this.controls[0], false, this.graphics);
                    }
                    if (this.mouses == 1) {
                        this.controls[0].enter = true;
                        this.mouses = 0;
                    }
                } else {
                    medium.circleAroundCar(this.geometries[3], true);
                    if (this.controls[0].enter || this.controls[0].handb) {
                        graphicsPanel.starcnt = 35;
                        this.controls[0].enter = false;
                        this.controls[0].handb = false;
                    }
                    if (graphicsPanel.starcnt == 35) {
                        this.mouses = 0;
                        medium.vert = false;
                        medium.adv = 900;
                        medium.circleAmount = 180;
                        checkPoints.checkstat(cars, this.geometries);
                        medium.follow(this.geometries[0], cars[0].cxz);
                        graphicsPanel.stat(cars[0], checkPoints, this.controls[0], true, this.graphics);
                        this.graphics.setColor(new Color(255, 255, 255));
                        this.graphics.fillRect(0, 0, 550, 400);
                    }
                }
            }
            if (graphicsPanel.stateInt == -1) {
                if (n8 == 0) {
                    int n46 = 0;
                    do {
                        record.ocar[n46] = new Geometry(this.geometries[n46], 0, 0, 0, 0);
                        this.geometries[n46] = new Geometry(record.car[0][n46], 0, 0, 0, 0);
                    } while (++n46 < 5);
                }
                medium.draw(this.graphics);
                int n47 = 0;
                final int[] array22 = new int[100];
                for (int n48 = 0; n48 < this.nob; ++n48) {
                    if (this.geometries[n48].dist != 0) {
                        array22[n47] = n48;
                        ++n47;
                    } else {
                        this.geometries[n48].draw(this.graphics);
                    }
                }
                final int[] array23 = new int[n47];
                for (int n49 = 0; n49 < n47; ++n49) {
                    array23[n49] = 0;
                }
                for (int n50 = 0; n50 < n47; ++n50) {
                    for (int n51 = n50 + 1; n51 < n47; ++n51) {
                        if (this.geometries[array22[n50]].dist != this.geometries[array22[n51]].dist) {
                            if (this.geometries[array22[n50]].dist < this.geometries[array22[n51]].dist) {
                                ++array23[n50];
                            } else {
                                ++array23[n51];
                            }
                        } else if (n51 > n50) {
                            ++array23[n50];
                        } else {
                            ++array23[n51];
                        }
                    }
                }
                for (int n56 = 0; n56 < n47; ++n56) {
                    for (int n57 = 0; n57 < n47; ++n57) {
                        if (array23[n57] == n56) {
                            this.geometries[array22[n57]].draw(this.graphics);
                        }
                    }
                }
                if (this.controls[0].enter || this.controls[0].handb || this.mouses == 1) {
                    n8 = 299;
                    this.controls[0].enter = false;
                    this.controls[0].handb = false;
                    this.mouses = 0;
                }
                int n58 = 0;
                do {
                    if (record.fix[n58] == n8) {
                        if (this.geometries[n58].dist == 0) {
                            this.geometries[n58].fcnt = 8;
                        } else {
                            this.geometries[n58].fix = true;
                        }
                    }
                    if (this.geometries[n58].fcnt == 7 || this.geometries[n58].fcnt == 8) {
                        this.geometries[n58] = new Geometry(availableGeometries[cars[n58].carIndex], 0, 0, 0, 0);
                        record.cntdest[n58] = 0;
                    }
                    if (n8 == 299) {
                        this.geometries[n58] = new Geometry(record.ocar[n58], 0, 0, 0, 0);
                    }
                    record.play(this.geometries[n58], cars[n58], n58, n8);
                } while (++n58 < 5);
                if (++n8 == 300) {
                    n8 = 0;
                    if (graphicsPanel.loadedSoundTracks[checkPoints.stage - 1]) {
                        graphicsPanel.soundTracks[checkPoints.stage - 1].stop();
                    }
                    graphicsPanel.stateInt = -6;
                } else {
                    graphicsPanel.replyn(this.graphics);
                }
                medium.circleAroundCar(this.geometries[0], false);
            }
            if (graphicsPanel.stateInt == -2) {
                if (record.hcaught) {
                    medium.vert = !(medium.random() > 0.45);
                    medium.adv = (int) (900.0f * medium.random());
                    medium.circleAmount = 180;
                    n8 = 0;
                    graphicsPanel.stateInt = -3;
                    n9 = 0;
                    n10 = 0;
                } else {
                    this.graphics.drawImage(graphicsPanel.trackbg, 0, 0, null);
                    n8 = -2;
                    graphicsPanel.stateInt = -4;
                }
            }
            if (graphicsPanel.stateInt == -3) {
                if (n8 == 0) {
                    int n59 = 0;
                    do {
                        this.geometries[n59] = new Geometry(record.starcar[n59], 0, 0, 0, 0);
                    } while (++n59 < 5);
                }
                medium.draw(this.graphics);
                int n60 = 0;
                final int[] array28 = new int[100];
                for (int n61 = 0; n61 < this.nob; ++n61) {
                    if (this.geometries[n61].dist != 0) {
                        array28[n60] = n61;
                        ++n60;
                    } else {
                        this.geometries[n61].draw(this.graphics);
                    }
                }
                final int[] array29 = new int[n60];
                for (int n62 = 0; n62 < n60; ++n62) {
                    array29[n62] = 0;
                }
                for (int n63 = 0; n63 < n60; ++n63) {
                    for (int n64 = n63 + 1; n64 < n60; ++n64) {
                        if (this.geometries[array28[n63]].dist != this.geometries[array28[n64]].dist) {
                            if (this.geometries[array28[n63]].dist < this.geometries[array28[n64]].dist) {
                                ++array29[n63];
                            } else {
                                ++array29[n64];
                            }
                        } else if (n64 > n63) {
                            ++array29[n63];
                        } else {
                            ++array29[n64];
                        }
                    }
                }
                for (int n69 = 0; n69 < n60; ++n69) {
                    for (int n70 = 0; n70 < n60; ++n70) {
                        if (array29[n70] == n69) {
                            this.geometries[array28[n70]].draw(this.graphics);
                        }
                    }
                }
                int n71 = 0;
                do {
                    if (record.hfix[n71] == n8) {
                        if (this.geometries[n71].dist == 0) {
                            this.geometries[n71].fcnt = 8;
                        } else {
                            this.geometries[n71].fix = true;
                        }
                    }
                    if (this.geometries[n71].fcnt == 7 || this.geometries[n71].fcnt == 8) {
                        this.geometries[n71] = new Geometry(availableGeometries[cars[n71].carIndex], 0, 0, 0, 0);
                        record.cntdest[n71] = 0;
                    }
                    record.playh(this.geometries[n71], cars[n71], n71, n8);
                } while (++n71 < 5);
                if (n10 == 2 && n8 == 299) {
                    this.controls[0].enter = true;
                }
                if (this.controls[0].enter || this.controls[0].handb) {
                    graphicsPanel.stateInt = -4;
                    this.controls[0].enter = false;
                    this.controls[0].handb = false;
                    n8 = -7;
                } else {
                    graphicsPanel.levelhigh(this.graphics, record.wasted, record.whenwasted);
                    if (n8 == 0 || n8 == 1 || n8 == 2) {
                        this.graphics.setColor(new Color(0, 0, 0));
                        this.graphics.fillRect(0, 0, 550, 400);
                    }
                    if (record.wasted != 0) {
                        if (n9 == 10) {
                            this.graphics.setColor(new Color(255, 255, 255));
                            this.graphics.fillRect(0, 0, 550, 400);
                        }
                        if (n9 >= 10) {
                            medium.circleAroundCar(this.geometries[record.wasted], false);
                        } else {
                            medium.circleAroundCar(this.geometries[0], false);
                        }
                        if (n8 > record.whenwasted && n9 != 20) {
                            ++n9;
                        }
                        if ((n9 == 0 || n9 == 20) && ++n8 == 300) {
                            n8 = 0;
                            n9 = 0;
                            ++n10;
                        }
                    } else {
                        if (n9 == 3 || n9 == 31 || n9 == 66) {
                            this.graphics.setColor(new Color(255, 255, 255));
                            this.graphics.fillRect(0, 0, 550, 400);
                        }
                        if (n8 > record.whenwasted && n9 != 67) {
                            ++n9;
                        }
                        medium.circleAroundCar(this.geometries[0], false);
                        if ((n9 == 0 || n9 == 67) && ++n8 == 300) {
                            n8 = 0;
                            n9 = 0;
                            ++n10;
                        }
                    }
                }
            }
            if (graphicsPanel.stateInt == -4) {
                if (n8 <= 0) {
                    this.graphics.drawImage(graphicsPanel.mdness, 164, 330, null);
                }
                if (n8 >= 0) {
                    graphicsPanel.flexImage(this.offImage, this.graphics, n8);
                    if (n8 != 0) {
                        if (graphicsPanel.winner) {
                            if (checkPoints.stage == graphicsPanel.unlocked) {
                                this.graphics.drawImage(graphicsPanel.congrd, 140, 30, null);
                                if (this.controls[0].enter || this.controls[0].handb) {
                                    this.controls[0].enter = false;
                                    this.controls[0].handb = false;
                                }
                            } else {
                                this.graphics.drawImage(graphicsPanel.congrd, 140, 117, null);
                            }
                        } else {
                            this.graphics.drawImage(graphicsPanel.gameov, 190, 117, null);
                        }
                    }
                }
                if (++n8 == 6) {
                    graphicsPanel.stateInt = -5;
                }
            }
            if (graphicsPanel.stateInt == -6) {
                graphicsPanel.pauseimage(this.offImage, this.graphics);
                graphicsPanel.stateInt = -7;
                this.mouses = 0;
            }
            if (graphicsPanel.stateInt == -7) {
                graphicsPanel.pausedgame(this.graphics, checkPoints.stage, this.controls[0], record);
                if (n8 != 0) {
                    n8 = 0;
                }
                graphicsPanel.ctachm(this.mouseX, this.mouseY, this.mouses, this.controls[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            if (graphicsPanel.stateInt == -8) {
                graphicsPanel.cantreply(this.graphics);
                if (++n8 == 150 || this.controls[0].enter || this.controls[0].handb || this.mouses == 1) {
                    this.graphics.drawImage(graphicsPanel.fleximg, 0, 0, null);
                    this.graphics.drawImage(graphicsPanel.paused, 156, 106, null);
                    graphicsPanel.stateInt = -7;
                    this.mouses = 0;
                    this.controls[0].enter = false;
                    this.controls[0].handb = false;
                }
            }
            if (this.lostfcs && graphicsPanel.stateInt != 176 && graphicsPanel.stateInt != 111) {
                graphicsPanel.nofocus(this.graphics);
                if (graphicsPanel.stateInt == 0) {
                    this.controls[0].enter = true;
                }
                if (this.mouses == 1 || this.mouses == 2) {
                    this.lostfcs = false;
                    if (graphicsPanel.stateInt == 10) {
                        graphicsPanel.flipo = 0;
                    }
                    if (graphicsPanel.stateInt == 11 && graphicsPanel.flipo != 0) {
                        --graphicsPanel.flipo;
                    }
                    if (graphicsPanel.stateInt == -7) {
                        this.graphics.drawImage(graphicsPanel.fleximg, 0, 0, null);
                    }
                }
            }
            this.repaint();
            graphicsPanel.playsounds(cars[0], this.controls[0], checkPoints.stage);
            final long time3 = new Date().getTime();
            if (graphicsPanel.stateInt == 0 || graphicsPanel.stateInt == -1 || graphicsPanel.stateInt == -3) {
                if (n6 == 0) {
                    a = n3;
                    n6 = 1;
                    n7 = 0;
                }
                if (n7 == 10) {
                    if (time3 - time < 530L) {
                        a += 0.5;
                    } else {
                        a -= 0.5;
                        if (a < 5.0f) {
                            a = 5.0f;
                        }
                    }
                    if (graphicsPanel.starcnt == 0) {
                        medium.adjustFade(a);
                    }
                    time = time3;
                    n7 = 0;
                } else {
                    ++n7;
                }
            } else {
                if (n6 != 0) {
                    n3 = a;
                    n6 = 0;
                    n7 = 0;
                }
                if (n4 == 0 || graphicsPanel.stateInt != 176) {
                    if (n7 == 10) {
                        if (time3 - time < 400L) {
                            a += 3.5;
                        } else {
                            a -= 3.5;
                            if (a < 5.0f) {
                                a = 5.0f;
                            }
                        }
                        time = time3;
                        n7 = 0;
                    } else {
                        ++n7;
                    }
                } else {
                    if (n4 == 79) {
                        a = n3;
                        time = time3;
                        n7 = 0;
                    }
                    if (n7 == 10) {
                        if (time3 - time < 530L) {
                            a += 5.0f;
                        } else {
                            a -= 5.0f;
                            if (a < 5.0f) {
                                a = 5.0f;
                            }
                        }
                        time = time3;
                        n7 = 0;
                    } else {
                        ++n7;
                    }
                    if (n4 == 1) {
                        n3 = a;
                    }
                }
            }
            if (this.exwist) {
                this.graphics.dispose();
                graphicsPanel.stopallnow();
                this.gamer.stop();
                this.gamer = null;
            }
            long frametime = Math.round(a) - (time3 - time2);
            if (frametime < n) {
                frametime = n;
            }
            try {
                Thread.sleep(1000 / 23);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.graphics.setColor(new Color(255, 255, 255));
            this.graphics.drawString("FPS:" + 1000 / frametime, 100, 100);
            //System.out.println(frametime);
        }
    }

    public void init() {
        this.offImage = this.createImage(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        if (this.offImage != null) {
            this.graphics = this.offImage.getGraphics();
        }

        medium = new Medium();
        trackers = new Trackers();
        checkPoints = new CheckPoints();
        graphicsPanel = new GraphicsPanel(medium, this.graphics, this, 1);
        cars = new Madness[5];
        record = new Record(medium);
        // TODO: USE ARRAYLIST INSTEAD?
        geometries = new Geometry[210];

        ResourceLoader resourceLoader = new ResourceLoader(this, this.graphicsPanel);
        resourceLoader.loadResources();

    }

    public void saveCookie(final String str, final String str2) {
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        //System.out.println("Mouse pressed.");
        if (!this.exwist && this.mouses == 0) {
            this.mouseX = mouseEvent.getX();
            this.mouseY = mouseEvent.getY();
            this.mouses = 1;
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        Control controls = this.controls[0];
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                controls.up = true;
                break;
            case KeyEvent.VK_LEFT:
                controls.left = true;
                break;
            case KeyEvent.VK_DOWN:
                controls.down = true;
                break;
            case KeyEvent.VK_RIGHT:
                controls.right = true;
                break;
            case KeyEvent.VK_SPACE:
                controls.handb = true;
                break;
            case KeyEvent.VK_ENTER:
                controls.enter = true;
                break;
            case KeyEvent.VK_A:
                this.controls[0].arrace = !this.controls[0].arrace;
                break;
            case KeyEvent.VK_N:
                this.controls[0].sound_muted = !this.controls[0].sound_muted;
                break;
            case KeyEvent.VK_M:
                this.controls[0].music_muted = !this.controls[0].music_muted;
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        Control controls = this.controls[0];
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                controls.up = false;
                break;
            case KeyEvent.VK_LEFT:
                controls.left = false;
                break;
            case KeyEvent.VK_DOWN:
                controls.down = false;
                break;
            case KeyEvent.VK_RIGHT:
                controls.right = false;
                break;
            case KeyEvent.VK_SPACE:
                controls.handb = false;
                break;
            case KeyEvent.VK_ENTER:
                controls.enter = false;
                break;
            case KeyEvent.VK_V:
                this.view++;
                this.view %= 3;
                break;
        }
    }
}
