package main.java;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameSparker extends Applet implements Runnable, MouseListener, KeyListener {

    Graphics graphics;
    Image offscreenImage;
    Thread gameThread;
    Control[] controls;
    ResourceLoader resourceLoader;
    int mouseClick;
    int mouseX;
    int mouseY;
    boolean isWindowFocusLost;
    boolean exwist;
    int nob;
    int notb;
    int view;

    GameState lastState;

    GraphicsPanel graphicsPanel;
    Trackers trackers;
    CheckPoints checkPoints;
    Medium medium;
    Record record;
    Geometry[] models;
    Geometry[] loadedModels;
    Madness[] cars;

    public GameSparker() {
        this.controls = new Control[5];
        this.mouseClick = 0;
        this.mouseX = 0;
        this.mouseY = 0;
        this.isWindowFocusLost = false;
        this.exwist = true;
        this.nob = 0;
        this.notb = 0;
        this.view = 0;
        addMouseListener(this);
        addKeyListener(this);
    }

    public void stop() {
        if (this.exwist && this.gameThread != null) {
            this.gameThread.stop();
            this.gameThread = null;
        }
        this.exwist = true;
    }

    public boolean lostFocus(final Event event, final Object o) {
        if (!this.exwist) {
            this.isWindowFocusLost = true;
            this.mouseClick = 0;
            this.controls[0].resetControls();
        }
        return false;
    }

    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offscreenImage, 0, 0, this);
    }

    public void update(final Graphics graphics) {
        this.paint(graphics);
    }


    public void start() {
        if (this.gameThread == null) {
            this.gameThread = new Thread(this);
        }
        this.gameThread.start();
    }

    public void run() {
        fillScreen(Color.BLACK);
        this.repaint();

        int n4 = 80;

        graphicsPanel.unlocked = 0;

        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        int frames = 0;
        this.exwist = false;

        while (true) {
            if (graphicsPanel.state == GameState.TRANSITION_MAIN_MENU_CAR_SELECT) {
                if(frames < 5) {
                    fillScreen(Color.WHITE);
                    frames++;
                } else {
                    graphicsPanel.state = GameState.CAR_SELECT;
                    this.mouseClick = 0;
                    frames = 0;
                }
            }
            else if (graphicsPanel.state == GameState.CREDITS) {
                graphicsPanel.credits(this.graphics, this.controls[0]);
                graphicsPanel.checkMouseClick(this.mouseX, this.mouseY, this.mouseClick, this.controls[0]);
                if (this.mouseClick == 2) {
                    this.mouseClick = 0;
                }
                if (this.mouseClick == 1) {
                    this.mouseClick = 2;
                }
            }
            else if (graphicsPanel.state == GameState.MAIN_MENU) {
                graphicsPanel.maini(this.graphics, this.controls[0]);
                graphicsPanel.checkMouseClick(this.mouseX, this.mouseY, this.mouseClick, this.controls[0]);
                if (this.mouseClick == 2) {
                    this.mouseClick = 0;
                }
                if (this.mouseClick == 1) {
                    this.mouseClick = 2;
                }
            }
            else if (graphicsPanel.state == GameState.GAME_INSTRUCTIONS) {
                graphicsPanel.inst(this.graphics, this.controls[0]);
                graphicsPanel.checkMouseClick(this.mouseX, this.mouseY, this.mouseClick, this.controls[0]);
                if (this.mouseClick == 2) {
                    this.mouseClick = 0;
                }
                if (this.mouseClick == 1) {
                    this.mouseClick = 2;
                }
            }
            else if (graphicsPanel.state == GameState.END_RACE_CONGRATS) {
                graphicsPanel.finish(checkPoints, this.models, this.controls[0], this.graphics);
                if (n5 == 1) {
                    if (checkPoints.stage == graphicsPanel.unlocked && graphicsPanel.winner && graphicsPanel.unlocked != 11) {
                        this.saveCookie("unlocked", "" + (graphicsPanel.unlocked + 1));
                    }
                    this.saveCookie("usercar", "" + graphicsPanel.selectedCar[0]);
                    n5 = 0;
                }
                graphicsPanel.checkMouseClick(this.mouseX, this.mouseY, this.mouseClick, this.controls[0]);
                if (this.mouseClick == 2) {
                    this.mouseClick = 0;
                }
                if (this.mouseClick == 1) {
                    this.mouseClick = 2;
                }
            }
            else if (graphicsPanel.state == GameState.CAR_SELECT) {
                graphicsPanel.carselect(this.controls[0], this.models, cars[0], this.graphics);
                graphicsPanel.checkMouseClick(this.mouseX, this.mouseY, this.mouseClick, this.controls[0]);
                if (this.mouseClick == 2) {
                    this.mouseClick = 0;
                }
                if (this.mouseClick == 1) {
                    this.mouseClick = 2;
                }
            }
            else if (graphicsPanel.state == GameState.LOADING_SOUNDTRACK_COMPLETE) {
                graphicsPanel.loadingMusicComplete(checkPoints.stage, this.graphics, this.controls[0]);
                graphicsPanel.checkMouseClick(this.mouseX, this.mouseY, this.mouseClick, this.controls[0]);
                if (this.mouseClick == 2) {
                    this.mouseClick = 0;
                }
                if (this.mouseClick == 1) {
                    this.mouseClick = 2;
                }
            }
            else if (graphicsPanel.state == GameState.LOADING_SOUNDTRACK_1) {
                graphicsPanel.loadingMusicScreen(checkPoints.stage, n4, this.graphics);
                if (n5 == 0) {
                    this.saveCookie("usercar", "" + graphicsPanel.selectedCar[0]);
                    n5 = 1;
                }
            }
            else if (graphicsPanel.state == GameState.STAGE_LOCKED_UNSAFE) {
                graphicsPanel.cantgo(this.graphics, this.controls[0]);
                graphicsPanel.checkMouseClick(this.mouseX, this.mouseY, this.mouseClick, this.controls[0]);
                if (this.mouseClick == 2) {
                    this.mouseClick = 0;
                }
                if (this.mouseClick == 1) {
                    this.mouseClick = 2;
                }
            }
            else if (graphicsPanel.state == GameState.LOADING_STAGE_FAILED) {
                graphicsPanel.loadingFailed(checkPoints.stage, this.controls[0], this.graphics);
                graphicsPanel.checkMouseClick(this.mouseX, this.mouseY, this.mouseClick, this.controls[0]);
                if (this.mouseClick == 2) {
                    this.mouseClick = 0;
                }
                if (this.mouseClick == 1) {
                    this.mouseClick = 2;
                }
            }
            else if (graphicsPanel.state == GameState.LOADING_STAGE) {
                graphicsPanel.loadingStage(checkPoints.stage, this.graphics);
                graphicsPanel.getResourceLoader().loadStage(this, loadedModels, models, medium, trackers, checkPoints, graphicsPanel, cars, record);
                controls[0].resetControls();
            }
            else if (graphicsPanel.state == GameState.STAGE_PREVIEW) {
                graphicsPanel.stageSelectBg(this.graphics);
                medium.circleAroundStage(checkPoints);
                int n11 = 0;
                final int[] array4 = new int[200];
                for (int i = 5; i < this.notb; ++i) {
                    if (this.loadedModels[i].dist != 0) {
                        array4[n11] = i;
                        ++n11;
                    } else {
                        this.loadedModels[i].draw(this.graphics);
                    }
                }
                final int[] array5 = new int[n11];
                for (int j = 0; j < n11; ++j) {
                    array5[j] = 0;
                }
                for (int k = 0; k < n11; ++k) {
                    for (int l = k + 1; l < n11; ++l) {
                        if (this.loadedModels[array4[k]].dist != this.loadedModels[array4[l]].dist) {
                            if (this.loadedModels[array4[k]].dist < this.loadedModels[array4[l]].dist) {
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
                            this.loadedModels[array4[n17]].draw(this.graphics);
                        }
                    }
                }
                graphicsPanel.checkMouseClick(this.mouseX, this.mouseY, this.mouseClick, this.controls[0]);
                if (this.mouseClick == 2) {
                    this.mouseClick = 0;
                }
                if (this.mouseClick == 1) {
                    this.mouseClick = 2;
                }
                graphicsPanel.stageSelect(this.graphics, checkPoints, this.controls[0]);
            }
            else if (graphicsPanel.state == GameState.LOADING_SOUNDTRACK_2) {
                medium.draw(this.graphics);
                int count = 0;
                final int[] array10 = new int[100];
                for (int i = 0; i < this.nob; ++i) {
                    if (this.loadedModels[i].dist != 0) {
                        array10[count] = i;
                        ++count;
                    } else {
                        this.loadedModels[i].draw(this.graphics);
                    }
                }
                final int[] array11 = new int[count];
                for (int i = 0; i < count; ++i) {
                    array11[i] = 0;
                }
                for (int i = 0; i < count; ++i) {
                    for (int j = i + 1; j < count; ++j) {
                        if (this.loadedModels[array10[i]].dist != this.loadedModels[array10[j]].dist) {
                            if (this.loadedModels[array10[i]].dist < this.loadedModels[array10[j]].dist) {
                                ++array11[i];
                            } else {
                                ++array11[j];
                            }
                        } else if (j > i) {
                            ++array11[i];
                        } else {
                            ++array11[j];
                        }
                    }
                }
                for (int i = 0; i < count; ++i) {
                    for (int n28 = 0; n28 < count; ++n28) {
                        if (array11[n28] == i) {
                            this.loadedModels[array10[n28]].draw(this.graphics);
                        }
                    }
                }
                medium.follow(this.loadedModels[0], 0);
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
                    graphicsPanel.state = GameState.LOADING_SOUNDTRACK_COMPLETE;
                }
            }
            else if (graphicsPanel.state == GameState.GAMEPLAY) {
                for (int i = 0; i < 5; i++) {
                    if (cars[i].newcar) {
                        final int xz = this.loadedModels[i].xz;
                        final int xy = this.loadedModels[i].xy;
                        final int zy = this.loadedModels[i].zy;
                        this.loadedModels[i] = new Geometry(this.models[cars[i].carIndex], this.loadedModels[i].x, this.loadedModels[i].y, this.loadedModels[i].z, 0);
                        this.loadedModels[i].xz = xz;
                        this.loadedModels[i].xy = xy;
                        this.loadedModels[i].zy = zy;
                        cars[i].newcar = false;
                    }
                }
                medium.draw(this.graphics);
                int n30 = 0;
                final int[] array16 = new int[100];
                for (int n31 = 0; n31 < this.nob; ++n31) {
                    if (this.loadedModels[n31].dist != 0) {
                        array16[n30] = n31;
                        ++n30;
                    } else {
                        this.loadedModels[n31].draw(this.graphics);
                    }
                }
                final int[] array17 = new int[n30];
                for (int n32 = 0; n32 < n30; ++n32) {
                    array17[n32] = 0;
                }
                for (int n33 = 0; n33 < n30; ++n33) {
                    for (int n34 = n33 + 1; n34 < n30; ++n34) {
                        if (this.loadedModels[array16[n33]].dist != this.loadedModels[array16[n34]].dist) {
                            if (this.loadedModels[array16[n33]].dist < this.loadedModels[array16[n34]].dist) {
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
                            this.loadedModels[array16[n40]].draw(this.graphics);
                        }
                    }
                }
                if (graphicsPanel.starcnt == 0) {
                    int i = 0;
                    do {
                        int j = 0;
                        do if (j != i) {
                            cars[i].colide(this.loadedModels[i], cars[j], this.loadedModels[j]);
                        } while (++j < 5);
                    } while (++i < 5);
                    int n43 = 0;
                    do {
                        cars[n43].drive(this.controls[n43], this.loadedModels[n43], trackers, checkPoints);
                    } while (++n43 < 5);
                    int n44 = 0;
                    do {
                        record.rec(this.loadedModels[n44], n44, cars[n44].squash, cars[n44].lastcolido, cars[n44].cntdest);
                    } while (++n44 < 5);
                    checkPoints.checkstat(cars, this.loadedModels);
                    int n45 = 1;
                    do {
                        this.controls[n45].preform(cars[n45], this.loadedModels[n45], checkPoints, trackers);
                    } while (++n45 < 5);
                } else {
                    if (graphicsPanel.starcnt == 90) {
                        medium.adv = 1900;
                        medium.zy = 40;
                        medium.circleAmount = 70;
                        this.graphics.setColor(new Color(255, 255, 255));
                        this.graphics.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
                    }
                    if (graphicsPanel.starcnt != 0) {
                        --graphicsPanel.starcnt;
                    }
                }
                if (graphicsPanel.starcnt < 35) {
                    if (this.view == 0) {
                        medium.follow(this.loadedModels[0], cars[0].cxz);
                        graphicsPanel.stat(cars[0], checkPoints, this.controls[0], true, this.graphics);
                    }
                    if (this.view == 1) {
                        medium.circleAroundCar(this.loadedModels[0], false);
                        graphicsPanel.stat(cars[0], checkPoints, this.controls[0], false, this.graphics);
                    }
                    if (this.view == 2) {
                        medium.watchFromStationaryPoint(this.loadedModels[0], cars[0].mxz);
                        graphicsPanel.stat(cars[0], checkPoints, this.controls[0], false, this.graphics);
                    }
                    if (this.mouseClick == 1) {
                        this.controls[0].enter = true;
                        this.mouseClick = 0;
                    }
                } else {
                    medium.circleAroundCar(this.loadedModels[3], true);
                    if (this.controls[0].enter || this.controls[0].handb) {
                        graphicsPanel.starcnt = 35;
                        this.controls[0].enter = false;
                        this.controls[0].handb = false;
                    }
                    if (graphicsPanel.starcnt == 35) {
                        this.mouseClick = 0;
                        medium.vert = false;
                        medium.adv = 900;
                        medium.circleAmount = 180;
                        checkPoints.checkstat(cars, this.loadedModels);
                        medium.follow(this.loadedModels[0], cars[0].cxz);
                        graphicsPanel.stat(cars[0], checkPoints, this.controls[0], true, this.graphics);
                        this.graphics.setColor(new Color(255, 255, 255));
                        this.graphics.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
                    }
                }
            }
            else if (graphicsPanel.state == GameState.PLAY_REPLAY) {
                if (n8 == 0) {
                    int n46 = 0;
                    do {
                        record.ocar[n46] = new Geometry(this.loadedModels[n46], 0, 0, 0, 0);
                        this.loadedModels[n46] = new Geometry(record.car[0][n46], 0, 0, 0, 0);
                    } while (++n46 < 5);
                }
                medium.draw(this.graphics);
                int n47 = 0;
                final int[] array22 = new int[100];
                for (int n48 = 0; n48 < this.nob; ++n48) {
                    if (this.loadedModels[n48].dist != 0) {
                        array22[n47] = n48;
                        ++n47;
                    } else {
                        this.loadedModels[n48].draw(this.graphics);
                    }
                }
                final int[] array23 = new int[n47];
                for (int n49 = 0; n49 < n47; ++n49) {
                    array23[n49] = 0;
                }
                for (int n50 = 0; n50 < n47; ++n50) {
                    for (int n51 = n50 + 1; n51 < n47; ++n51) {
                        if (this.loadedModels[array22[n50]].dist != this.loadedModels[array22[n51]].dist) {
                            if (this.loadedModels[array22[n50]].dist < this.loadedModels[array22[n51]].dist) {
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
                            this.loadedModels[array22[n57]].draw(this.graphics);
                        }
                    }
                }
                if (this.controls[0].enter || this.controls[0].handb || this.mouseClick == 1) {
                    n8 = 299;
                    this.controls[0].enter = false;
                    this.controls[0].handb = false;
                    this.mouseClick = 0;
                }
                int n58 = 0;
                do {
                    if (record.fix[n58] == n8) {
                        if (this.loadedModels[n58].dist == 0) {
                            this.loadedModels[n58].fcnt = 8;
                        } else {
                            this.loadedModels[n58].fix = true;
                        }
                    }
                    if (this.loadedModels[n58].fcnt == 7 || this.loadedModels[n58].fcnt == 8) {
                        this.loadedModels[n58] = new Geometry(this.models[cars[n58].carIndex], 0, 0, 0, 0);
                        record.cntdest[n58] = 0;
                    }
                    if (n8 == 299) {
                        this.loadedModels[n58] = new Geometry(record.ocar[n58], 0, 0, 0, 0);
                    }
                    record.play(this.loadedModels[n58], cars[n58], n58, n8);
                } while (++n58 < 5);
                if (++n8 == 300) {
                    n8 = 0;
                    if (graphicsPanel.loadedSoundTracks[checkPoints.stage - 1]) {
                        graphicsPanel.soundTracks[checkPoints.stage - 1].stop();
                    }
                    graphicsPanel.state = GameState.GAME_PAUSED;
                } else {
                    graphicsPanel.replyn(this.graphics);
                }
                medium.circleAroundCar(this.loadedModels[0], false);
            }
            else if (graphicsPanel.state == GameState.GAME_HIGHLIGHT_1) {
                if (record.hcaught) {
                    medium.vert = !(medium.random() > 0.45);
                    medium.adv = (int) (900.0f * medium.random());
                    medium.circleAmount = 180;
                    n8 = 0;
                    graphicsPanel.state = GameState.GAME_HIGHLIGHT_2;
                    n9 = 0;
                    n10 = 0;
                } else {
                    this.graphics.drawImage(graphicsPanel.trackbg, 0, 0, null);
                    n8 = -2;
                    graphicsPanel.state = GameState.END_RACE_ANIMATION;
                }
            }
            else if (graphicsPanel.state == GameState.GAME_HIGHLIGHT_2) {
                if (n8 == 0) {
                    int n59 = 0;
                    do {
                        this.loadedModels[n59] = new Geometry(record.starcar[n59], 0, 0, 0, 0);
                    } while (++n59 < 5);
                }
                medium.draw(this.graphics);
                int n60 = 0;
                final int[] array28 = new int[100];
                for (int n61 = 0; n61 < this.nob; ++n61) {
                    if (this.loadedModels[n61].dist != 0) {
                        array28[n60] = n61;
                        ++n60;
                    } else {
                        this.loadedModels[n61].draw(this.graphics);
                    }
                }
                final int[] array29 = new int[n60];
                for (int n62 = 0; n62 < n60; ++n62) {
                    array29[n62] = 0;
                }
                for (int n63 = 0; n63 < n60; ++n63) {
                    for (int n64 = n63 + 1; n64 < n60; ++n64) {
                        if (this.loadedModels[array28[n63]].dist != this.loadedModels[array28[n64]].dist) {
                            if (this.loadedModels[array28[n63]].dist < this.loadedModels[array28[n64]].dist) {
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
                            this.loadedModels[array28[n70]].draw(this.graphics);
                        }
                    }
                }
                int n71 = 0;
                do {
                    if (record.hfix[n71] == n8) {
                        if (this.loadedModels[n71].dist == 0) {
                            this.loadedModels[n71].fcnt = 8;
                        } else {
                            this.loadedModels[n71].fix = true;
                        }
                    }
                    if (this.loadedModels[n71].fcnt == 7 || this.loadedModels[n71].fcnt == 8) {
                        this.loadedModels[n71] = new Geometry(this.models[cars[n71].carIndex], 0, 0, 0, 0);
                        record.cntdest[n71] = 0;
                    }
                    record.playh(this.loadedModels[n71], cars[n71], n71, n8);
                } while (++n71 < 5);
                if (n10 == 2 && n8 == 299) {
                    this.controls[0].enter = true;
                }
                if (this.controls[0].enter || this.controls[0].handb) {
                    graphicsPanel.state = GameState.END_RACE_ANIMATION;
                    this.controls[0].enter = false;
                    this.controls[0].handb = false;
                    n8 = -7;
                } else {
                    graphicsPanel.showGameHighlight(this.graphics, record.wasted, record.whenwasted);
                    if (n8 == 0 || n8 == 1 || n8 == 2) {
                        this.graphics.setColor(new Color(0, 0, 0));
                        this.graphics.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
                    }
                    if (record.wasted != 0) {
                        if (n9 == 10) {
                            this.graphics.setColor(new Color(255, 255, 255));
                            this.graphics.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
                        }
                        if (n9 >= 10) {
                            medium.circleAroundCar(this.loadedModels[record.wasted], false);
                        } else {
                            medium.circleAroundCar(this.loadedModels[0], false);
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
                            this.graphics.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
                        }
                        if (n8 > record.whenwasted && n9 != 67) {
                            ++n9;
                        }
                        medium.circleAroundCar(this.loadedModels[0], false);
                        if ((n9 == 0 || n9 == 67) && ++n8 == 300) {
                            n8 = 0;
                            n9 = 0;
                            ++n10;
                        }
                    }
                }
            }
            else if (graphicsPanel.state == GameState.END_RACE_ANIMATION) {
                if (n8 <= 0) {
                    this.graphics.drawImage(graphicsPanel.mdness, 164, 330, null);
                }
                if (n8 >= 0) {
                    graphicsPanel.flexImage(this.offscreenImage, this.graphics, n8);
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
                    graphicsPanel.state = GameState.END_RACE_CONGRATS;
                }
            }
            else if (graphicsPanel.state == GameState.GAME_PAUSED) {
                graphicsPanel.pauseImage(this.offscreenImage, this.graphics);
                graphicsPanel.state = GameState.GAME_PAUSED_2_BLURRED;
                this.mouseClick = 0;
            }
            else if (graphicsPanel.state == GameState.GAME_PAUSED_2_BLURRED) {
                graphicsPanel.pausedgame(this.graphics, checkPoints.stage, this.controls[0], record);
                if (n8 != 0) {
                    n8 = 0;
                }
                graphicsPanel.checkMouseClick(this.mouseX, this.mouseY, this.mouseClick, this.controls[0]);
                if (this.mouseClick == 2) {
                    this.mouseClick = 0;
                }
                if (this.mouseClick == 1) {
                    this.mouseClick = 2;
                }
            }
            else if (graphicsPanel.state == GameState.REPLAY_NOT_AVAILABLE) {
                graphicsPanel.cantreply(this.graphics);
                if (++n8 == 150 || this.controls[0].enter || this.controls[0].handb || this.mouseClick == 1) {
                    this.graphics.drawImage(graphicsPanel.fleximg, 0, 0, null);
                    this.graphics.drawImage(graphicsPanel.paused, 156, 106, null);
                    graphicsPanel.state = GameState.GAME_PAUSED_2_BLURRED;
                    this.mouseClick = 0;
                    this.controls[0].enter = false;
                    this.controls[0].handb = false;
                }
            }
            else if (this.isWindowFocusLost) {
                graphicsPanel.nofocus(this.graphics);
                if (graphicsPanel.state == GameState.GAMEPLAY) {
                    this.controls[0].enter = true;
                }
                if (this.mouseClick == 1 || this.mouseClick == 2) {
                    this.isWindowFocusLost = false;
                    if (graphicsPanel.state == GameState.MAIN_MENU) {
                        graphicsPanel.flipo = 0;
                    }
                    if (graphicsPanel.state == GameState.GAME_INSTRUCTIONS && graphicsPanel.flipo != 0) {
                        --graphicsPanel.flipo;
                    }
                    if (graphicsPanel.state == GameState.GAME_PAUSED_2_BLURRED) {
                        this.graphics.drawImage(graphicsPanel.fleximg, 0, 0, null);
                    }
                }
            }
            this.repaint();
            graphicsPanel.setFont(new Font("SansSerif", Font.BOLD, 11));
            graphicsPanel.drawCharacters(graphics, 25, "aflk: " + graphicsPanel.aflk, 0, 255, 0, 3);

            graphicsPanel.playsounds(cars[0], this.controls[0], checkPoints.stage);
            if (graphicsPanel.state == GameState.GAMEPLAY || graphicsPanel.state == GameState.PLAY_REPLAY || graphicsPanel.state == GameState.GAME_HIGHLIGHT_2) {
                if (n6 == 0) {
                    n6 = 1;
                    n7 = 0;
                }
                if (n7 == 10) {
                    if (graphicsPanel.starcnt == 0) {
                        // TODO: find out how this fade stuff works
                        //medium.adjustFade(a);
                    }
                    n7 = 0;
                } else {
                    ++n7;
                }
            } else {
                if (n6 != 0) {
                    n6 = 0;
                    n7 = 0;
                }
                if (n4 != 0 && graphicsPanel.state == GameState.LOADING_SOUNDTRACK_2) {
                    if (n4 == 79) {
                        n7 = 0;
                    }
                }
                if (n7 == 10) {
                    n7 = 0;
                } else {
                    ++n7;
                }
            }
            if (this.exwist) {
                this.graphics.dispose();
                graphicsPanel.stopallnow();
                this.gameThread.stop();
                this.gameThread = null;
            }
            try {
                Thread.sleep(1000 / 24);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(graphicsPanel.state != this.lastState) {
                this.lastState = graphicsPanel.state;
                System.out.println("Current State : " + graphicsPanel.state);
            }
            this.graphics.setColor(Color.WHITE);
        }
    }

    public void init() {
        this.offscreenImage = this.createImage(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        if (this.offscreenImage != null) {
            this.graphics = this.offscreenImage.getGraphics();
        }

        medium = new Medium();
        trackers = new Trackers();
        checkPoints = new CheckPoints();
        graphicsPanel = new GraphicsPanel(medium, this.graphics, this);
        cars = new Madness[5];
        record = new Record(medium);
        loadedModels = new Geometry[210];

        models = graphicsPanel.getResourceLoader().loadModels(medium, trackers);

        for(int i = 0; i < Config.TOTAL_CARS;i++) {
            cars[i] = new Madness(medium, record, graphicsPanel, i);
            this.controls[i] = new Control(medium);
        }

    }

    void fillScreen(Color c) {
        graphics.setColor(c);
        graphics.fillRect(0,0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    }

    public void saveCookie(final String str, final String str2) {
        //TODO: Handle savestate not with cookie?
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (!this.exwist && this.mouseClick == 0) {
            this.mouseX = mouseEvent.getX();
            this.mouseY = mouseEvent.getY();
            this.mouseClick = 1;
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        Config.updateScreenSize(width, height);

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
