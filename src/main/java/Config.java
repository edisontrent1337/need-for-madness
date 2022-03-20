package main.java;

public class Config {

    static int SCREEN_WIDTH = 1280;
    static int SCREEN_HEIGHT = 720;
    static int SCREEN_PIXELS = SCREEN_WIDTH * SCREEN_HEIGHT;
    static float GAME_SPEED_MULTIPLIER = 2.6f;

    static int TOTAL_CARS = 5;

    public static final String[] modelNames = {"2000tornados", "formula7", "canyenaro", "lescrab", "nimi", "maxrevenge", "leadoxide", "king", "radicalone", "drmonster", "road", "froad", "twister2", "twister1", "turn", "offroad", "bumproad", "offturn", "nroad", "nturn", "roblend", "noblend", "rnblend", "roadend", "offroadend", "hpground", "ramp30", "cramp35", "dramp15", "dhilo15", "slide10", "takeoff", "sramp22", "offbump", "offramp", "sofframp", "halfpipe", "spikes", "rail", "thewall", "checkpoint", "fixpoint", "offcheckpoint"};

    public static void updateScreenSize(int width, int height) {
        SCREEN_WIDTH = width;
        SCREEN_HEIGHT = height;
        SCREEN_PIXELS = SCREEN_WIDTH * SCREEN_HEIGHT;
    }
}
