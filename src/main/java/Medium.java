package main.java;

import java.awt.*;
import java.util.List;
import java.util.stream.IntStream;



public class Medium {
    int focusPoint;
    float ground;
    int skyline;
    int[] fade;
    int[] skyColor;
    int[] groundColor;
    int[] fadeColor;
    int[] snapColor;
    int origfade;
    int flex;
    boolean trk;
    boolean crs;
    float centerX;
    float centerY;
    float centerZ;
    float xz;
    float zy;
    int positionX;
    int positionY;
    int positionZ;
    int width;
    int height;
    int nsp;
    int[] spx;
    int[] spz;
    int[] sprad;
    boolean td;
    int circleAmount;
    int adv;
    boolean vert;
    int trns;
    float[] tcos;
    float[] tsin;
    int randomCnt;
    boolean[] directionUp;
    int[] randomList;
    int turn;
    int hit;
    int ptr;
    int ptcnt;
    int nrnd;
    long trx;
    long trz;

    Medium() {
        this.focusPoint = Config.SCREEN_HEIGHT;
        this.ground = 250;
        this.skyline = -300;
        this.fade = new int[]{3000, 6000, 9000, 12000, 15000, 18000, 21000, 24000};
        this.skyColor = new int[]{217, 224, 255};
        this.groundColor = new int[]{205, 200, 200};
        this.fadeColor = new int[]{255, 220, 220};
        this.snapColor = new int[3];
        this.origfade = 3000;
        this.flex = 0;
        this.trk = false;
        this.crs = false;
        this.centerX = Config.SCREEN_WIDTH / 2F;
        this.centerY = Config.SCREEN_HEIGHT / 2F;
        this.centerZ = 70;
        this.xz = 0;
        this.zy = 0;
        this.positionX = 0;
        this.positionY = 0;
        this.positionZ = 0;
        this.width = Config.SCREEN_WIDTH;
        this.height = Config.SCREEN_HEIGHT;
        this.nsp = 0;
        this.spx = new int[5];
        this.spz = new int[5];
        this.sprad = new int[5];
        this.td = false;
        this.circleAmount = 180;
        this.adv = 500;
        this.vert = false;
        this.trns = 1;
        this.randomCnt = 0;
        this.directionUp = new boolean[3];
        this.randomList = new int[3];
        this.turn = 0;
        this.hit = 60000;
        this.ptr = 0;
        this.ptcnt = -10;
        this.nrnd = 0;
        this.trx = 0L;
        this.trz = 0L;
    }

    public int ys(final int n, int n2) {
        if (n2 < 10) {
            n2 = 10;
        }
        return (n2 - this.focusPoint) * ((int) this.centerY - n) / n2 + n;
    }

    float random() {
        if (this.randomCnt == 0) {
            for (int i = 0; i < 3; i++) {
                this.randomList[i] = (int) (10.0 * Math.random());
                this.directionUp[i] = Math.random() > 0.5d;
            }
            this.randomCnt = 20;
        } else {
            --this.randomCnt;
        }

        for (int i = 0; i < 3; i++) {
            this.randomList[i] = Math.floorMod(this.randomList[i] + (this.directionUp[i] ? 1 : -1), 10);
        }

        this.turn = (this.turn + 1) % 3;

        return this.randomList[this.turn] / 10.0f;
    }

    public float ys(final float n, float n2) {
        if (n2 < 10) {
            n2 = 10;
        }
        return (n2 - this.focusPoint) * (this.centerY - n) / n2 + n;
    }

    void setFade(List<Integer> fadeValues) {
        IntStream.range(0, 3)
                .forEach(i -> this.fadeColor[i] = (int) Util.clampCol(fadeValues.get(i) * (1 + 0.01f * this.snapColor[i])));
    }



    void draw(final Graphics graphics) {
        this.nsp = 0;
        this.zy = Util.clamp(this.zy, -90, 90);
        this.xz = this.xz % 360;
        if (this.positionY > 0) {
            this.positionY = 0;
        }
        this.ground = 250 - this.positionY;
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        int r = this.groundColor[0];
        int g = this.groundColor[1];
        int b = this.groundColor[2];
        int height = this.height;
        int n = 0;
        do {
            float n2 = this.fade[n];
            float ground = this.ground;
            if (this.zy != 0) {
                ground = this.centerY + (int) ((this.ground - this.centerY) * this.cos(this.zy) - (this.fade[n] - this.centerZ) * this.sin(this.zy));
                n2 = this.centerZ + (int) ((this.ground - this.centerY) * this.sin(this.zy) + (this.fade[n] - this.centerZ) * this.cos(this.zy));
            }
            array2[array[0] = 0] = (int) this.ys(ground, n2);
            if (array2[0] < 0) {
                array2[0] = 0;
            }
            array[1] = 0;
            array2[1] = height;
            array[2] = this.width;
            array2[2] = height;
            array[3] = this.width;
            array2[3] = array2[0];
            height = array2[0];
            if (n > 0) {
                r = (r * 3 + this.fadeColor[0]) / 4;
                g = (g * 3 + this.fadeColor[1]) / 4;
                b = (b * 3 + this.fadeColor[2]) / 4;
            }
            if (array2[0] < this.height && array2[1] > 0) {
                graphics.setColor(new Color(r, g, b));
                graphics.fillPolygon(array, array2, 4);
            }
        } while (++n < 8);
        int n6 = 0;
        if (this.flex == 2) {
            array[0] = 0;
            array2[0] = 45;
            array[1] = 0;
            array2[1] = 0;
            array[2] = Config.SCREEN_WIDTH;
            array2[2] = 0;
            array[3] = Config.SCREEN_WIDTH;
            array2[3] = 45;
            graphics.setColor(new Color(skyColor[0], skyColor[1], skyColor[2]));
            graphics.fillPolygon(array, array2, 4);
            n6 = 45;
        }
        //System.out.println("flex:" + this.flex);
        int n7 = 0;
        do {
            int n8 = this.fade[n7];
            int skyline = this.skyline;
            if (this.zy != 0) {
                skyline = (int) this.centerY + (int) ((this.skyline - this.centerY) * this.cos(this.zy) - (this.fade[n7] - this.centerZ) * this.sin(this.zy));
                n8 = (int) this.centerZ + (int) ((this.skyline - this.centerY) * this.sin(this.zy) + (this.fade[n7] - this.centerZ) * this.cos(this.zy));
            }
            array2[array[0] = 0] = this.ys(skyline, n8);
            if (array2[0] > this.height) {
                array2[0] = this.height;
            }
            array[1] = 0;
            array2[1] = n6;
            array[2] = this.width;
            array2[2] = n6;
            array[3] = this.width;
            array2[3] = array2[0];
            n6 = array2[0];
            int skyRed = this.skyColor[0];
            int skyGreen = this.skyColor[1];
            int skyBlue = this.skyColor[2];
            if (n7 > 0) {
                skyRed = (skyRed * 3 + this.fadeColor[0]) / 4;
                skyGreen = (skyGreen * 3 + this.fadeColor[1]) / 4;
                skyBlue = (skyBlue * 3 + this.fadeColor[2]) / 4;
            }
            //if (array2[0] > 0 && array2[1] < this.height) {
            graphics.setColor(new Color(skyRed, skyGreen, skyBlue));
            graphics.fillPolygon(array, array2, 4);
            //}
        } while (++n7 < 8);
        array2[array[0] = 0] = n6;
        array[1] = 0;
        array2[1] = height;
        array[2] = this.width;
        array2[2] = height;
        array[3] = this.width;
        array2[3] = n6;
        //if (array2[0] < this.height && array2[1] > 0) {
        graphics.setColor(new Color(this.fadeColor[0], this.fadeColor[1], this.fadeColor[2]));
        graphics.fillPolygon(array, array2, 4);
        //}
    }

    void watchFromStationaryPoint(final Geometry geometry, final float n) {
        if (this.flex != 0) {
            this.flex = 0;
        }
        if (this.td) {
            this.positionY = (int) (geometry.y - 300 - 1100.0f * this.random());
            this.positionX = geometry.x + (int) ((geometry.x + 400 - geometry.x) * this.cos(n) - (geometry.z + 5000 - geometry.z) * this.sin(n));
            this.positionZ = geometry.z + (int) ((geometry.x + 400 - geometry.x) * this.sin(n) + (geometry.z + 5000 - geometry.z) * this.cos(n));
            this.td = false;
        }
        int n2 = 0;
        if (geometry.x - this.positionX - this.centerX > 0) {
            n2 = 180;
        }
        final float n3 = -(float) (90 + n2 + Math.atan((geometry.z - this.positionZ) / (geometry.x - this.positionX - this.centerX)) / 0.017453292519943295);
        int n4 = 0;
        if (geometry.y - this.positionY - this.centerY < 0) {
            n4 = -180;
        }
        final float n5 = (float) (90 + n4 - Math.atan((float) Math.sqrt((geometry.z - this.positionZ) * (geometry.z - this.positionZ) + (geometry.x - this.positionX - this.centerX) * (geometry.x - this.positionX - this.centerX)) / (geometry.y - this.positionY - this.centerY)) / 0.017453292519943295);
        this.xz += (n3 - this.xz) / this.trns;
        if (this.trns != 1) {
            --this.trns;
        }
        this.zy += (n5 - this.zy) / 5;
        //System.out.println(this.zy);
        //System.out.println((n5 - this.zy) / 5);
        if ((int) Math.sqrt((geometry.z - this.positionZ) * (geometry.z - this.positionZ) + (geometry.x - this.positionX - this.centerX) * (geometry.x - this.positionX - this.centerX) + (geometry.y - this.positionY - this.centerY) * (geometry.y - this.positionY - this.centerY)) > 6000) {
            this.td = true;
        }
    }

    void setSnapColor(List<Integer> snapValues) {
        IntStream.range(0, 3).forEach(i -> this.snapColor[i] = snapValues.get(i));
    }

    void circleAroundCar(final Geometry geometry, final boolean isStartAnimation) {

        zy = Util.clamp(zy, -90, 90);
        xz = xz % 360;

        if (this.flex != 0) {
            this.flex = 0;
        }
        if (isStartAnimation) {
            this.adv -= 20;
        } else {
            if (!this.vert) {
                this.adv += 2;
            } else {
                this.adv -= 2;
            }
            if (this.adv > 900) {
                this.vert = true;
            }
            if (this.adv < 350) {
                this.vert = false;
            }
        }
        int n = 500 + this.adv;
        n = Math.max(n, 1500);
        //System.out.println(n);
        this.positionY = geometry.y - this.adv;
        if (this.positionY > 10) {
            this.vert = false;
        }
        this.positionX = geometry.x + (int) ((-n) * this.cos(this.circleAmount));
        this.positionZ = geometry.z + (int) ((-n) * this.sin(this.circleAmount));
        if (isStartAnimation) {
            this.circleAmount += 4;
        } else {
            this.circleAmount += 3;
        }
        int n2 = 0;
        int y = this.positionY;
        if (y > 0) {
            y = 0;
        }
        if (geometry.y - y - this.centerY < 0) {
            n2 = -180;
        }
        int n3 = (int) (90 + n2 - Math.atan((int) Math.sqrt((geometry.z - this.positionZ + this.centerZ) * (geometry.z - this.positionZ + this.centerZ) + (geometry.x - this.positionX - this.centerX) * (geometry.x - this.positionX - this.centerX)) / (double) (geometry.y - y - this.centerY)) / 0.017453292519943295);
        this.xz = -this.circleAmount + 90;
        if (isStartAnimation) {
            n3 -= 15;
        }
        this.zy += (n3 - this.zy) / 10;
        if (this.trns != 5) {
            this.trns = 5;
        }
        //System.out.println("hit:" + this.hit + ", circleAmount:" + this.circleAmount + ", zy:" + this.zy + ", xz:" + this.xz);

    }

    void setGround(List<Integer> groundValues) {
        IntStream.range(0, 3)
                .forEach(i -> this.groundColor[i] = (int) Util.clampCol(groundValues.get(i) * (1 + 0.01f * this.snapColor[i])));
    }

    void adjustFade(float n) {
        n = 30.0f;
        if (n < 15.0f) {
            this.fade[0] = (int) (this.origfade - 1000.0f * (15.0f - n));
            if (this.fade[0] < 3000) {
                this.fade[0] = 3000;
            }
            this.fadeFrom(this.fade[0]);
        } else if (this.fade[0] != this.origfade) {
            final int[] fade = this.fade;
            final int n2 = 0;
            fade[n2] += 500;
            if (this.fade[0] > this.origfade) {
                this.fade[0] = this.origfade;
            }
            this.fadeFrom(this.fade[0]);
        }
    }


    void addsp(final int n, final int n2, final int n3) {
        if (this.nsp != 5) {
            this.spx[this.nsp] = n;
            this.spz[this.nsp] = n2;
            this.sprad[this.nsp] = n3;
            ++this.nsp;
        }
    }

    void circleAroundStage(final CheckPoints checkPoints) {
        if (this.flex != 0) {
            this.flex = 0;
        }
        this.positionY = -this.hit;
        this.positionX = (int) this.centerX + (int) this.trx + (int) (12000.0f * this.cos(this.circleAmount));
        this.positionZ = (int) this.trz + (int) (12000.0f * this.sin(this.circleAmount));
        this.hit -= 3000;
        if (this.hit < 5000) {
            this.hit = 5000;
            this.trx -= (this.trx - checkPoints.x[this.ptr]) / 10L;
            this.trz -= (this.trz - checkPoints.z[this.ptr]) / 10L;
            if (this.ptcnt == 7) {
                ++this.ptr;
                if (this.ptr == checkPoints.n) {
                    this.ptr = 0;
                    ++this.nrnd;
                }
                this.ptcnt = 0;
            } else {
                ++this.ptcnt;
            }
        }
        this.circleAmount += 2;
        if (this.circleAmount > 360) {
            this.circleAmount -= 360;
        }
        this.xz = -this.circleAmount - 90;
        int n = 0;
        if (-this.positionY - this.centerY < 0) {
            n = -180;
        }
        final float n2 = (float) Math.sqrt((double) ((this.trz - this.positionZ + this.centerZ) * (this.trz - this.positionZ + this.centerZ) + (this.trx - this.positionX - this.centerX) * (this.trx - this.positionX - this.centerX)));
        if (this.zy < 30 && this.hit != 57000) {
            if (this.zy > 9) {
                --this.zy;
            } else {
                this.zy = 9;
            }
        } else {
            this.zy = (float) (90 + n - Math.atan(n2 / (double) (-this.positionY - this.centerY)) / 0.017453292519943295);
        }
    }

    void setSky(final int n, final int n2, final int n3) {
        this.skyColor[0] = (int) (n + n * (this.snapColor[0] / 100.0f));
        this.skyColor[1] = (int) (n2 + n2 * (this.snapColor[1] / 100.0f));
        this.skyColor[2] = (int) (n3 + n3 * (this.snapColor[2] / 100.0f));
        skyColor[0] = Util.clamp(skyColor[0], 0, 255);
        skyColor[1] = Util.clamp(skyColor[1], 0, 255);
        skyColor[2] = Util.clamp(skyColor[2], 0, 255);
    }

    void setSky(List<Integer> skyValues) {
        IntStream.range(0, 3)
                .forEach(i -> this.skyColor[i] = (int) Util.clampCol(skyValues.get(i) * (1 + 0.01f * this.snapColor[i])));
    }

    void fadeFrom(final int n) {
        int n2 = 0;
        do {
            this.fade[n2] = n * (n2 + 1);
        } while (++n2 < 8);
    }

    void fadeFrom(List<Integer> fadeIntervall) {
        IntStream.range(0, 8).forEach(i->this.fade[i] = fadeIntervall.get(0) * (i + 1));
    }

    void follow(final Geometry geometry, final float n) {
        this.zy = 10;
        this.xz = -n;
        this.positionX = geometry.x - (int) this.centerX + (int) (800 * this.sin(n));
        this.positionZ = geometry.z - (int) this.centerZ + (int) (-800 * this.cos(n));
        this.positionY = geometry.y - 250 - (int) this.centerY;
        if (this.trns != 1) {
            this.trns = 1;
        }
    }

    float cos(int i) {
        return (float) (Math.cos(i * 2 * Math.PI / 360));
    }

    float cos(float i) {
        return (float) (Math.cos(i * 2 * Math.PI / 360));
    }

    public float sin(int i) {
        return (float) (Math.sin(i * 2 * Math.PI / 360));
    }

    public float sin(float i) {
        return (float) (Math.sin(i * 2 * Math.PI / 360));
    }
}
